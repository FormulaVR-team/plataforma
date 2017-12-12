package com.fvr._comun.disponiblidad;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.http.client.IHttpMethods;
import com.fvr._comun.http.client.ProxyHttpMethods;
import com.fvr.cd_LocationClosedDays.bean.CdBean;
import com.fvr.pm_promosManuales.bean.PmBean;
import com.fvr.pt_products.bean.PtBean;
import com.fvr.rs_reservations.bean.RsBean;
import com.fvr.rs_reservations.db.RsAccesoBaseDatos;
import com.fvr.tj_tarjetasPrepago.bean.TjBean;
import com.fvr.us_users.bean.UsBean;

public class Reservas implements Serializable{

	private static final long serialVersionUID = -2140368358063952095L;
	
	private static final String patron_sustitucion_nick = "/@NICK@/";
	
	public Reservas() { super(); }

	public synchronized static boolean crearReserva( BDConexion dataBase, RsBean reg_rs, ArrayList<String> errores ) throws StExcepcion {
		boolean resultado = false;
		String tag = "crearReserva()";
		
		determinarImporte(dataBase, reg_rs, errores);

		if ( ! checkDisponibilidad(dataBase, reg_rs, errores) ) {
			return resultado;
		}

		PtBean reg_pt = Subrutinas.getPtFromId(dataBase, reg_rs.getRs_product_id());
		if ( reg_pt == null || reg_pt.getPt_sincro() == null || reg_pt.getPt_sincro().trim().length() < 1 ) { errores.add(tag+": Falta product_id"); return resultado; }

		///////////////////
		// Cabecera:
		new RsAccesoBaseDatos().rs_crtObj( dataBase, reg_rs );
		// Arrastrar el tfno y el nick a la ficha del usuario:
		UsBean reg_us = Subrutinas.getUsFromId(dataBase, reg_rs.getRs_user_id());
		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
			boolean isRegrabar = false;
//			if ( reg_us.getUs_phone() == null || reg_us.getUs_phone().trim().length() < 1 ) {
				if ( reg_rs.getRs_phone() != null && reg_rs.getRs_phone().trim().length() > 0 ) {
					reg_us.setUs_phone( reg_rs.getRs_phone() );
					isRegrabar = true;
				}
//			}
//			if ( reg_us.getUs_nick() == null || reg_us.getUs_nick().trim().length() < 1 ) {
				if ( reg_rs.getRs_US_nick() != null && reg_rs.getRs_US_nick().trim().length() > 0 ) {
					reg_us.setUs_nick( reg_rs.getRs_US_nick() );
					isRegrabar = true;
				}
//			}
			if (isRegrabar) {
				new com.fvr.us_users.db.UsAccesoBaseDatos().us_chgObj(dataBase,reg_us);
			}
		}
		///////////////////
		

		///////////////////
		// Time slices: (Crear trozos de tiempo en intervalos del mínimo vendible)
		long slices = reg_pt.getPt_duration_minutes() / _K.TIME_SLICE;
		slices *= reg_rs.getRs_quantity();
		
		// Slice fecha/hora inicial de la reserva
		Date fecHorRsva = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
			fecHorRsva = sdf.parse( reg_rs.getRs_start_date() + " " + reg_rs.getRs_start_time() );
		} catch (ParseException e) { errores.add(tag+": " + e.getMessage()); return resultado; }
		if ( fecHorRsva == null ) { errores.add(tag+": error al procesar fecha/hora de la reserva: " + reg_rs.getRs_start_date() + " " + reg_rs.getRs_start_time() ); return resultado; }

		com.fvr.ts_timeSlices.db.TsAccesoBaseDatos dao_ts = new com.fvr.ts_timeSlices.db.TsAccesoBaseDatos();
		com.fvr.ts_timeSlices.bean.TsBean          reg_ts = new com.fvr.ts_timeSlices.bean.TsBean();

		// PK: (slice_id es SERIAL)
		reg_ts.setTs_reservation_id( reg_rs.getRs_reservation_id() );
		reg_ts.setTs_start_date( reg_rs.getRs_start_date() ); 			// Clave inicial en la secuencia de slices de la reserva
		reg_ts.setTs_start_time( reg_rs.getRs_start_time() ); 			// Clave inicial en la secuencia de slices de la reserva
		// Resto:
//		reg_ts.setTs_sincro( "" ); // sincro
//		reg_ts.setTs_mark( "" ); // mark
//		reg_ts.setTs_is_deleted( "" ); // is_deleted
		reg_ts.setTs_author( reg_rs.getRs_author() ); // author
		reg_ts.setTs_json( "" ); // json

		for ( long i = 0; i < slices; i++ ) {
			dao_ts.ts_crtObj(dataBase, reg_ts);
			// Siguiente slice de esta reserva
			fecHorRsva = Subrutinas.addMinutes(fecHorRsva, (int)_K.TIME_SLICE);	
			reg_ts.setTs_start_date( Subrutinas.getFecha_aaaa_mm_dd( fecHorRsva ) ); 			// Clave siguiente en la secuencia de slices de la reserva
			reg_ts.setTs_start_time( Subrutinas.getHora_HHMMSS( fecHorRsva ).substring(0,4) ); 	// Clave siguiente en la secuencia de slices de la reserva
		}

		///////////////////
		resultado = true;

		return resultado;
	}

	public static double determinarImporte( BDConexion dataBase, RsBean reg_rs, ArrayList<String> errores ) {
		double amount = 0.0;

		if ( reg_rs == null ) { return amount; }
		if ( reg_rs.getRs_user_id() == null || reg_rs.getRs_user_id().trim().length() < 1 ) { return amount; }
		if ( reg_rs.getRs_quantity() < 1 ) { reg_rs.setRs_quantity( 1L ); }
		if ( reg_rs.getRs_places() < 1 ) { reg_rs.setRs_places( 1L ); }
		
		////////////////////
		// Reserva tiene tres claves de producto:
		//		1. El principal seleccionado por el cliente (..o derivado del cupón si aparte del "producto descuent"o trae "producto de usuario" relleno)
		//		2. El que pone el sistema automáticamente como promoción automática. (Descuentos por volumen, etc...)
		//		3. El derivado de un cupón introducido por el usuario en la reserva.
		////////////////////
		
		String productoName2 = "";
		String productoName3 = "";
		ArrayList<String> comentarios = new ArrayList<String>();
		// Controla la vigencia de aplicación:
		long ahora = Subrutinas.parse_long( Subrutinas.getDateAuditoria() );
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//////////////
		// ¿HAY CUPÓN?
		if ( reg_rs.getRs_coupon_id() != null && reg_rs.getRs_coupon_id().trim().length() > 0 ) {
			PmBean reg_pm = Subrutinas.getPmFromId(dataBase, reg_rs.getRs_coupon_id());
			if ( reg_pm != null && reg_pm.getPm_product_id_promo() != null && reg_pm.getPm_product_id_promo().trim().length() > 0 ) {
				// ¿vigente?
				try {
					ahora = Subrutinas.parse_long( Subrutinas.getDateAuditoria() );
					long deadLinePromo = Subrutinas.parse_long( Subrutinas.getDateAuditoria( sdf.parse( reg_pm.getPm_deadline() ) ) );
					if ( ahora < deadLinePromo ) {
						// ¿Lo ha usado menos veces que las permitidas?
						RsBean[] lista = Subrutinas.getRsFromUs(dataBase, reg_rs.getRs_user_id(), reg_rs.getRs_coupon_id(), true);
						if ( lista == null || reg_pm.getPm_uses_per_user() > lista.length ) {

							///////////////////
							if ( reg_pm.getPm_product_id() != null && reg_pm.getPm_product_id().trim().length() > 0 ) {
								// Si solo se puede aplicar a un producto, se inserta el producto como si lo hubiera metido el usuario:
								reg_rs.setRs_product_id( reg_pm.getPm_product_id() );
							}
							if ( reg_pm.getPm_places() > 0L ) {
								// Si se quiere limitar las cabinas a usar, se especifica en el cupón:
								reg_rs.setRs_places( reg_pm.getPm_places() );
							}
							reg_rs.setRs_product_id3( reg_pm.getPm_product_id_promo() );
							///////////////////

						}
					}
				} catch (ParseException e) {;}
			}
		}
		//////////////

		//////////////////
		//////////////////
		// PROMOS. Ccompletar datos de la reserva:
		long deadLinePromo = 0L;
		com.fvr.pr_promos.db.PrAccesoBaseDatos dao_pr = new com.fvr.pr_promos.db.PrAccesoBaseDatos();
		com.fvr.pr_promos.bean.PrBean          reg_pr = new com.fvr.pr_promos.bean.PrBean();
		// PK:
		reg_pr.setPr_location_id( reg_rs.getRs_location_id() );
		reg_pr.setPr_product_id( reg_rs.getRs_product_id() );
		try {
			reg_pr = dao_pr.pr_getRcd(dataBase, reg_pr);
			if ( reg_pr != null ) {
				try {
					deadLinePromo = Subrutinas.parse_long( Subrutinas.getDateAuditoria( sdf.parse( reg_pr.getPr_deadline() ) ) );
				} catch (ParseException e) {;}
				if ( ahora < deadLinePromo ) {
					if ( reg_pr.getPr_min_quantity() <= reg_rs.getRs_places() ) {
						reg_rs.setRs_product_id2( reg_pr.getPr_product_id_promo() );
						comentarios.add("Promoción " + reg_pr.getPr_product_id_promo() + " programada para " + reg_pr.getPr_LO_name() );
					}
				}
			}
		} catch (StExcepcion e) {;}
		//////////////////
		//////////////////

		//////////////////
		long deadLine2 = 0L;
		long deadLine3 = 0L;

		//////////////////
		// Conseguir datos de todos los productos:
		PtBean reg_pt = null;
		if ( reg_rs.getRs_product_id() != null && reg_rs.getRs_product_id().trim().length() > 0 ) {
			reg_pt = Subrutinas.getPtFromId(dataBase, reg_rs.getRs_product_id());
			reg_rs.setRs_PT_deadline( reg_pt.getPt_deadline() );
			reg_rs.setRs_PT_isPercent( reg_pt.getPt_isPercent() );
			reg_rs.setRs_PT_amount( reg_pt.getPt_amount() );
		}
		if ( reg_rs.getRs_product_id2() != null && reg_rs.getRs_product_id2().trim().length() > 0 ) {
			reg_pt = Subrutinas.getPtFromId(dataBase, reg_rs.getRs_product_id2());
			reg_rs.setRs_PT_deadline2( reg_pt.getPt_deadline() );
			reg_rs.setRs_PT_isPercent2( reg_pt.getPt_isPercent() );
			reg_rs.setRs_PT_amount2( reg_pt.getPt_amount() );
			productoName2 = reg_pt.getPt_name();
			try {
				deadLine2 = Subrutinas.parse_long( Subrutinas.getDateAuditoria( sdf.parse( reg_pt.getPt_deadline() ) ) );
			} catch (ParseException e) {;}
		}
		if ( reg_rs.getRs_product_id3() != null && reg_rs.getRs_product_id3().trim().length() > 0 ) {
			reg_pt = Subrutinas.getPtFromId(dataBase, reg_rs.getRs_product_id3());
			reg_rs.setRs_PT_deadline3( reg_pt.getPt_deadline() );
			reg_rs.setRs_PT_isPercent3( reg_pt.getPt_isPercent() );
			reg_rs.setRs_PT_amount3( reg_pt.getPt_amount() );
			productoName3 = reg_pt.getPt_name();
			try {
				deadLine3 = Subrutinas.parse_long( Subrutinas.getDateAuditoria( sdf.parse( reg_pt.getPt_deadline() ) ) );
			} catch (ParseException e) {;}
		}

///////////////////////////////////////////////////	
//		1.	amount = PT_amount * quantity * places
//
//		2.	Si “product_id2” tiene valor y es aplicable (PT_deadline2):
//				Si “PT_isPercent2” es “S”:	amount = (amount * PT_amount2) / 100
//				Si no:						amount = amount + PT_amount2
//
//		3.	Si “product_id3” tiene valor y es aplicable (PT_deadline3):
//				Si “PT_isPercent3” es “S”:	amount = (amount * PT_amount3) / 100
//				Si no:						amount = amount + PT_amount3
///////////////////////////////////////////////////	

		// 1
		amount = reg_rs.getRs_PT_amount() * reg_rs.getRs_quantity() * reg_rs.getRs_places(); 

		// 2
		if ( reg_rs.getRs_product_id2() != null && reg_rs.getRs_product_id2().trim().length() > 0 ) {
			if ( ahora < deadLine2 ) {	// VIGENCIA
				comentarios.add( productoName2 );
				if ( _K.SI.equalsIgnoreCase( reg_rs.getRs_PT_isPercent2() ) ) {
					amount += (amount * reg_rs.getRs_PT_amount2() ) / 100.0;
				} else {
					amount += reg_rs.getRs_PT_amount2();
				}
			}
		}

		// 3
		if ( reg_rs.getRs_product_id3() != null && reg_rs.getRs_product_id3().trim().length() > 0 ) {
			if ( ahora < deadLine3 ) {	// VIGENCIA
				comentarios.add( productoName3 );
				if ( _K.SI.equalsIgnoreCase( reg_rs.getRs_PT_isPercent3() ) ) {
					amount += (amount * reg_rs.getRs_PT_amount3() ) / 100.0;
				} else {
					amount += reg_rs.getRs_PT_amount3();
				}
			}
		}
		
		////////////////
		// 2017-08-13. Redondeo a entero:
		amount = (double)((long)(amount + 0.5));
		////////////////

		reg_rs.setRs_comment( "" );
		reg_rs.setRs_amount( amount );
		
		if ( comentarios != null && ! comentarios.isEmpty() ) {
			String laCosa = "";
			for ( String item : comentarios ) { laCosa += item + ". "; }
			reg_rs.setRs_comment( laCosa );
		}

		return amount;
	}

	public static synchronized boolean checkDisponibilidad( BDConexion dataBase, RsBean reg_rs, ArrayList<String> errores ) {
		boolean resultado = false;
		String tag = "checkDisponibilidad()";
		
		// ===============
		// COMPROBACIONES:
		// ===============
		// Pay_status = "OK"
		// Location
		// Fecha				Se barre toda la fecha para determinar si encaja en algún hueco.
		// INICIO LAPSO + DURACIÓN + PLAZAS
		// Hora
		// Minutos
		// Plazas
		// ===============
		
		if ( reg_rs == null ) { errores.add(tag+": Error de parámetros"); return resultado; }
		if ( reg_rs.getRs_location_id() == null || reg_rs.getRs_location_id().trim().length() < 1 ) { errores.add(tag+": Falta location_id"); return resultado; }
		if ( reg_rs.getRs_start_date() == null || reg_rs.getRs_start_date().trim().length() < 1 ) { errores.add(tag+": Falta start_date"); return resultado; }
		if ( reg_rs.getRs_start_time() == null || reg_rs.getRs_start_time().trim().length() < 1 ) { errores.add(tag+": Falta start_time"); return resultado; }
		if ( reg_rs.getRs_duration_minutes()  < 1L ) { errores.add(tag+": Falta duration_minutes"); return resultado; }
		if ( reg_rs.getRs_places()  < 1L ) { errores.add(tag+": Falta places"); return resultado; }
		
		///////
		// Chequear que no es un día de cierre del local:
		com.fvr.cd_LocationClosedDays.bean.CdBean[] rgs_cd = Subrutinas.getCdFromLo(dataBase, reg_rs.getRs_location_id());
		if ( rgs_cd != null ) {
			for ( CdBean item : rgs_cd ) {
				if ( reg_rs.getRs_start_date().equalsIgnoreCase( item.getCd_closed_day_aaaa_mm_dd() ) ) {
					errores.add("El lugar se encuentra cerrado en la fecha seleccionada: " + reg_rs.getRs_start_date() + "."); 
					return resultado;
				}
			}
		}
		///////

		///////////////////
		// Time slices: (Crear trozos de tiempo en intervalos del mínimo vendible)
		PtBean reg_pt = Subrutinas.getPtFromId(dataBase, reg_rs.getRs_product_id());
		long slices = reg_pt.getPt_duration_minutes() / _K.TIME_SLICE;
		slices *= reg_rs.getRs_quantity();
		
		Date fecHorRsva = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
			fecHorRsva = sdf.parse( reg_rs.getRs_start_date() + " " + reg_rs.getRs_start_time() );
		} catch (ParseException e) { errores.add(tag+": " + e.getMessage()); return resultado; }
		if ( fecHorRsva == null ) { errores.add(tag+": error al procesar fecha/hora de la reserva: " + reg_rs.getRs_start_date() + " " + reg_rs.getRs_start_time() ); return resultado; }

		// Slice fecha/hora inicial de la reserva
		String start_date = Subrutinas.getFecha_aaaa_mm_dd( fecHorRsva );
		String start_time = Subrutinas.getHora_HHMMSS( fecHorRsva ).substring(0,4);

		boolean isTodoBien = true;
		for ( long i = 0; i < slices && isTodoBien; i++ ) {
			if ( ! checkDisponibilidad_places_LocFecHor(dataBase, reg_rs.getRs_location_id(), start_date, start_time, (int) reg_rs.getRs_places(), errores) ) {
//				System.out.println( reg_rs.getRs_location_id() + " " + start_date + " " + start_time + " " + reg_rs.getRs_places() );
				isTodoBien = false;
				continue;
			}
			// Siguiente slice de esta reserva
			fecHorRsva = Subrutinas.addMinutes(fecHorRsva, (int)_K.TIME_SLICE);	
			start_date = Subrutinas.getFecha_aaaa_mm_dd( fecHorRsva );
			start_time = Subrutinas.getHora_HHMMSS( fecHorRsva ).substring(0,4);
		}
		resultado = isTodoBien;

		return resultado;
	}

	private static synchronized boolean checkDisponibilidad_places_LocFecHor( BDConexion dataBase, String location_id, String iso_date, String hhmm_time, int plazasSolicitadas, ArrayList<String> errores ) {
		boolean resultado = false;

		int plazasOcupadas;
		try {
			plazasOcupadas = new com.fvr.ts_timeSlices.db.TsAccesoBaseDatos().ts_getSumPlazas_pagadas_o_pendientes( dataBase, location_id, iso_date, hhmm_time );

			com.fvr.cp_cockpits.db.CpAccesoBaseDatos dao_cp = new com.fvr.cp_cockpits.db.CpAccesoBaseDatos();
			com.fvr.cp_cockpits.bean.CpBeanFiltro    flt_cp = new com.fvr.cp_cockpits.bean.CpBeanFiltro();
			com.fvr.cp_cockpits.bean.CpBean[]        rgs_cp = null;
			flt_cp.setCp_location_id( location_id );
			flt_cp.setCp_isBlocked( _K.NO );
			rgs_cp = dao_cp.cp_getSeq(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_cp);
			if ( rgs_cp != null ) {
				int plazasPosibles = rgs_cp.length;
				if ( (plazasOcupadas + plazasSolicitadas) <= plazasPosibles ) {
					resultado = true;
				} else {
					errores.add("Superada capacidad en " + location_id + " " + iso_date + " " + hhmm_time + ". Plazas libres: " + (plazasPosibles-plazasOcupadas) );
				}
			}
		} catch (StExcepcion e) {
			errores.add(e.getMessage());
		}

		return resultado;
	}

	public static synchronized boolean tarjetaPrepago_descontarImporte( BDConexion dataBase, String card_id, double amount, ArrayList<String> errores, boolean isGrabarlo ) {
		boolean resultado = false;
		
		if ( card_id == null || card_id.trim().length() < 1) { errores.add("Falta clave de tarjeta"); return resultado; }
		if ( amount == 0.0 ) { resultado = true; }
		
		if ( ! resultado ) {
			
			TjBean reg_tj = Subrutinas.getTjFromId(dataBase, card_id);
			
			if ( reg_tj != null && reg_tj.getTj_sincro() != null && reg_tj.getTj_sincro().trim().length() > 1 ) {
				if ( amount > reg_tj.getTj_balance_current() ) {
					errores.add("Saldo superado");
				} else {
					reg_tj.setTj_balance_current( reg_tj.getTj_balance_current() - amount );
					reg_tj.setTj_last_sale_amount( amount );
					reg_tj.setTj_last_sale_moment( Subrutinas.getDateAuditoria() );
					try {
						if ( isGrabarlo) {
							new com.fvr.tj_tarjetasPrepago.db.TjAccesoBaseDatos().tj_chgObj(dataBase, reg_tj);
						}
						errores.add("Saldo restante: " + reg_tj.getTj_balance_current());
						resultado = true;
					} catch (StExcepcion e) {
						errores.add(e.getMessage());
					}
				}
			} else {
				errores.add("Tarjeta '" + card_id + "' no hallada");
			}
			
		}
		
		return resultado;
	}

	public static synchronized boolean sendCockpitConfig( BDConexion dataBase, String usr_nick, String net_user, String net_pass, String net_path ) {
		boolean resultado = false;
		
		String filename = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_COCKPIT_CONFIG_MASTER_FILE_NAME);
		String filecontent = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_COCKPIT_CONFIG_MASTER_FILE_CONTENT);

		if ( filecontent != null ) {
			filecontent = Subrutinas.macroSustituye(filecontent, patron_sustitucion_nick, usr_nick);
			
			///////////////////////
			String url = "http://localhost:47001";
			
			JSONObject json = new JSONObject();
			
			json.put("filename", filename);
			json.put("filecontent", filecontent);
			
			String charset = "UTF-8";
			Map<String,String> headers = new HashMap<String, String>();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String payload = json.toString();
			String resp = "";
			
//			headers.put("secretkey", secretKey);
			
			IHttpMethods httpMethods = new ProxyHttpMethods(charset);
			try {
				resp = httpMethods.doPost(url,params,headers, payload);
			} catch (StExcepcion e) {
				System.err.println( e.getMessage() );
			}
			httpMethods = null;
			System.out.println(resp);
			
			String[] resParts = resp.split("\\^");
			
			if (resParts!=null && resParts.length>1) {
				if ( "200".equalsIgnoreCase( resParts[0] ) ) {
					resultado = true;
				}
//				return resParts[1];
			} 
			///////////////////////

		}
		
		return resultado;
	}

	public static Date getPrimeraFechaPosible( BDConexion dataBase, String loc ) {
		
		Date hoy = Subrutinas.cvtFec_aaaa_mm_dd__date( Subrutinas.getFecha_aaaa_mm_dd() ); // Cero horas y cero minutos....
		Date minReservaPosible = hoy; 
		String rsFecMin = null;

//		LoBean reg_lo = Subrutinas.getLoFromId(dataBase, loc);
//		if ( reg_lo != null && reg_lo.getLo_sincro() != null && reg_lo.getLo_sincro().trim().length() > 0 ) {
			rsFecMin = Subrutinas.getDBValueFromKey(dataBase, loc, _K.PA_KEY_RS_MIN_FEC);
//		}
		
		if ( rsFecMin != null && rsFecMin.trim().length() == 10 ) {
			minReservaPosible = Subrutinas.cvtFec_aaaa_mm_dd__date( rsFecMin );
		}

		Date fecResultado = null;
		if ( minReservaPosible.after( hoy ) )	{ fecResultado = minReservaPosible; } 
		else 									{ fecResultado = hoy; }

		///////////////////////
		// Refinar la fecha propuesta según calendario de vacaciones y días de la semana del Centro
		///////////////////////
		
		boolean isFechaChequeada = false;
		int contadorIntentos_TOTAL = 0;
		
		while ( ! isFechaChequeada && contadorIntentos_TOTAL < 50) {

			// Controlar con "closed days"
			com.fvr.cd_LocationClosedDays.bean.CdBean[] rgs = Subrutinas.getCdFromLo(dataBase, loc);
			if ( rgs != null ) {
				boolean isCerrado = false;
				int i = 0;
				do {
					for ( isCerrado = false; i < rgs.length; i++ ) {
						Date d = Subrutinas.cvtFec_aaaa_mm_dd__date( rgs[i].getCd_closed_day_aaaa_mm_dd() );
						if ( d.equals( fecResultado ) ) {
							fecResultado = Subrutinas.addDays(fecResultado, 1);
							isCerrado = true;
							isFechaChequeada = false;
							break;
						} else if ( d.after( fecResultado ) ) { 
							// La serie de dias cerrados debe ser ascendente!!
							break; 
						}
					}
				} while (isCerrado);
			}
			
			isFechaChequeada = true;

			// Controlar si "día de la semana cerrado":
			String diasSemana = Subrutinas.getDBValueFromKey(dataBase, loc, _K.PA_KEY_WEEKLY_CALENDAR);
			if ( diasSemana == null ) { diasSemana = ""; }
			
			boolean isFechaAvanzada;
			int contadorIntentos_diasDeLaSemana = 0;
			do {
				isFechaAvanzada = false;
				Calendar cal = Calendar.getInstance(); cal.setTime(fecResultado);
				// Mi variable "diasSemana" es algo así: "0..3456" Domingo es cero y sábado es 6. Si no se trabaja no existe el número de ese día.
				// ( "Calendar.DAY_OF_WEEK" devuelve un número del 1 al 7, siendo 1 domingo y 7 sábado)
				int fecResultado_diaDeLaSemana = cal.get(Calendar.DAY_OF_WEEK) - 1;
				if ( ! diasSemana.contains( (""+fecResultado_diaDeLaSemana) ) ) {
					fecResultado = Subrutinas.addDays(fecResultado, 1);
					isFechaChequeada = false;
					isFechaAvanzada = true;
					contadorIntentos_diasDeLaSemana++;
				}
			} while (isFechaAvanzada && (contadorIntentos_diasDeLaSemana < 7) );
			
			contadorIntentos_TOTAL++;
		}

		return fecResultado;
	}

}
