package com.fvr.rs_reservations.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr.rs_reservations.bean.RsBean;
import com.fvr.rs_reservations.bean.RsBeanFiltro;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class RsAccesoBaseDatos {
    public String tabla   = "T_RS_reservations";
    public String lf_UPD  = "T_RS_reservations";
    public String lf_RTV  = "V_RS_RTV_reservations";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public RsAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// rs_reservations:
/////////////////////////////////////////////////
    protected void callSistemaExterno( final String idOp ) throws StExcepcion {
    	// System.out.println("\r\n*** callSistemaExterno( "+idOp+" )");

    	String[] params = new String [4];
    	params[0] = _K.caminoExecExterno + _K.ejecutableExterno;
    	params[1] = _K.caminoExecExterno;
    	params[2] = idOp;
    	params[3] = _K.unidadIntercambio;
    	
    	// String salidaTerminal = 
    			Subrutinas.run_comando_sincro(params);
    	// System.out.println( salidaTerminal );
    }
    protected void runSql(BDConexion dataBase, String sql) throws StExcepcion {
        //////////////////////////////////////////////
        try {
            if (dataBase==null) dataBase = new BDConexion();
            dataBase.executeUpdate(sql);
        } catch (StExcepcion ex) {
            throw ex;
        }
        //////////////////////////////////////////////
    }
    public void rs_crtObj(BDConexion bd, RsBean registro) throws StExcepcion {

    	registro.setRs_sincro( Subrutinas.getDateAuditoria() );

//		if ( registro.getRs_reservation_id() == null || registro.getRs_reservation_id().trim().length() < 1 ) {
	    	long clave = Subrutinas.getRsNextKey(bd,true);
	    	if ( clave > 0L ) {
	        	registro.setRs_reservation_id( ""+clave );
	    	} else {
	            throw new StExcepcion("Fallo al conseguir una clave para la reserva.");
	    	}
//		}
	    	
	    if ( registro.getRs_amount() == 0.0 ) {
	    	registro.setRs_pay_status( _K.PAY_STS_GRATIS_Completado );
	    }

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_rs_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	rs_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	rs_getParFS_RetCode(idOp);
	    	return;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
    	
        //////////////////////////////////////////////
        if (bd==null) bd = new BDConexion();
        //////////////////////////////////////////////
        String sql =
                "INSERT INTO \"" + Subrutinas.getG_DB_LIBDAT(bd.getCurrentDb()) + "\".\""  + this.lf_UPD + "\" " +
                "( " + 
		"  \"sincro\"" + // sincro
		", \"mark\"" + // mark
		", \"is_deleted\"" + // is_deleted
		", \"author\"" + // author
		", \"reservation_id\"" + // reservation_id
		", \"location_id\"" + // location_id
		", \"user_id\"" + // user_id
		", \"product_id\"" + // product_id
		", \"quantity\"" + // quantity
		", \"product_id2\"" + // product_id2
		", \"product_id3\"" + // product_id3
		", \"amount\"" + // amount
		", \"currency\"" + // currency
		", \"phone\"" + // phone
		", \"pay_status\"" + // pay_status
		", \"start_date\"" + // start_date
		", \"start_time\"" + // start_time
		", \"duration_minutes\"" + // duration_minutes
		", \"places\"" + // places
		", \"coupon_id\"" + // coupon_id
		", \"executed_at\"" + // executed_at
		", \"note\"" + // note
		", \"comment\"" + // comment
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getRs_sincro() + "'" + // sincro
		", '"  + registro.getRs_mark() + "'" + // mark
		", '"  + registro.getRs_is_deleted() + "'" + // is_deleted
		", '"  + registro.getRs_author() + "'" + // author
		", '"  + registro.getRs_reservation_id() + "'" + // reservation_id
		", '"  + registro.getRs_location_id() + "'" + // location_id
		", '"  + registro.getRs_user_id() + "'" + // user_id
		", '"  + registro.getRs_product_id() + "'" + // product_id
		", "  + registro.getRs_quantity() + "" + // quantity
		", '"  + registro.getRs_product_id2() + "'" + // product_id2
		", '"  + registro.getRs_product_id3() + "'" + // product_id3
		", "  + registro.getRs_amount() + "" + // amount
		", '"  + registro.getRs_currency() + "'" + // currency
		", '"  + registro.getRs_phone() + "'" + // phone
		", '"  + registro.getRs_pay_status() + "'" + // pay_status
		", '"  + registro.getRs_start_date() + "'" + // start_date
		", '"  + registro.getRs_start_time() + "'" + // start_time
		", "  + registro.getRs_duration_minutes() + "" + // duration_minutes
		", "  + registro.getRs_places() + "" + // places
		", '"  + registro.getRs_coupon_id() + "'" + // coupon_id
		", '"  + registro.getRs_executed_at() + "'" + // executed_at
		", '"  + registro.getRs_note() + "'" + // note
		", '"  + registro.getRs_comment() + "'" + // comment
		", '"  + registro.getRs_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void rs_chgObj(BDConexion bd, RsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_rs_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	rs_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	rs_getParFS_RetCode(idOp);
	    	return;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
    	
        //////////////////////////////////////////////
        if (bd==null) bd = new BDConexion();
        //////////////////////////////////////////////
        String sql =
                "UPDATE \"" + Subrutinas.getG_DB_LIBDAT(bd.getCurrentDb()) + "\".\""  + this.lf_UPD + "\" " +
                "   SET " + 
		"  \"sincro\" = '"  + registro.getRs_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getRs_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getRs_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getRs_author() + "'" + // author
		", \"reservation_id\" = '"  + registro.getRs_reservation_id() + "'" + // reservation_id
		", \"location_id\" = '"  + registro.getRs_location_id() + "'" + // location_id
		", \"user_id\" = '"  + registro.getRs_user_id() + "'" + // user_id
		", \"product_id\" = '"  + registro.getRs_product_id() + "'" + // product_id
		", \"quantity\" = "  + registro.getRs_quantity() + "" + // quantity
		", \"product_id2\" = '"  + registro.getRs_product_id2() + "'" + // product_id2
		", \"product_id3\" = '"  + registro.getRs_product_id3() + "'" + // product_id3
		", \"amount\" = "  + registro.getRs_amount() + "" + // amount
		", \"currency\" = '"  + registro.getRs_currency() + "'" + // currency
		", \"phone\" = '"  + registro.getRs_phone() + "'" + // phone
		", \"pay_status\" = '"  + registro.getRs_pay_status() + "'" + // pay_status
		", \"start_date\" = '"  + registro.getRs_start_date() + "'" + // start_date
		", \"start_time\" = '"  + registro.getRs_start_time() + "'" + // start_time
		", \"duration_minutes\" = "  + registro.getRs_duration_minutes() + "" + // duration_minutes
		", \"places\" = "  + registro.getRs_places() + "" + // places
		", \"coupon_id\" = '"  + registro.getRs_coupon_id() + "'" + // coupon_id
		", \"executed_at\" = '"  + registro.getRs_executed_at() + "'" + // executed_at
		", \"note\" = '"  + registro.getRs_note() + "'" + // note
		", \"comment\" = '"  + registro.getRs_comment() + "'" + // comment
		", \"json\" = '"  + registro.getRs_json() + "'" + // json
                " WHERE " + 
		"  \"reservation_id\" = '" + registro.getRs_reservation_id() + "'" + // reservation_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void rs_dltObj(BDConexion bd, RsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_rs_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	rs_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	rs_getParFS_RetCode(idOp);
	    	return;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
    	
        //////////////////////////////////////////////
        if (bd==null) bd = new BDConexion();
        //////////////////////////////////////////////
        
        registro = rs_getRcd(bd, registro);
        if ( registro != null ) {
        	registro.setRs_is_deleted( _K.SI );
        	rs_chgObj(bd, registro);
        }

//        String sql =
//                "DELETE " +
//                " FROM \"" + Subrutinas.getG_DB_LIBDAT(bd.getCurrentDb()) + "\".\""  + this.lf_UPD + "\" " +
//                " WHERE " + 
//		"  \"reservation_id\" = '" + registro.getRs_reservation_id() + "'" + // reservation_id
//                ""
//                ;
//        //////////////////////////////////////////////
//        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public RsBean   rs_getRcd(BDConexion dataBase, RsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_rs_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	rs_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return rs_getParFS_GET(idOp);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
    	
        //////////////////////////////////////////////
        if (dataBase==null) dataBase= new BDConexion();
        //////////////////////////////////////////////
        String sql =
                "SELECT \"A\".*" +
                " FROM \"" + Subrutinas.getG_DB_LIBDAT(dataBase.getCurrentDb()) + "\".\""  + this.lf_RTV + "\" \"A\"" +
                " WHERE " + 
		"  \"reservation_id\" = '" + registro.getRs_reservation_id() + "'" + // reservation_id
                ""
                ;
        ResultSet rs = null;
        RsBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new RsBean();
                
		regRead.setRs_sincro( rs.getString("sincro") ); regRead.setRs_sincro( (regRead.getRs_sincro() == null)?"":regRead.getRs_sincro().trim() ); // sincro
		regRead.setRs_mark( rs.getString("mark") ); regRead.setRs_mark( (regRead.getRs_mark() == null)?"":regRead.getRs_mark().trim() ); // mark
		regRead.setRs_is_deleted( rs.getString("is_deleted") ); regRead.setRs_is_deleted( (regRead.getRs_is_deleted() == null)?"":regRead.getRs_is_deleted().trim() ); // is_deleted
		regRead.setRs_author( rs.getString("author") ); regRead.setRs_author( (regRead.getRs_author() == null)?"":regRead.getRs_author().trim() ); // author
		regRead.setRs_reservation_id( rs.getString("reservation_id") ); regRead.setRs_reservation_id( (regRead.getRs_reservation_id() == null)?"":regRead.getRs_reservation_id().trim() ); // reservation_id
		regRead.setRs_location_id( rs.getString("location_id") ); regRead.setRs_location_id( (regRead.getRs_location_id() == null)?"":regRead.getRs_location_id().trim() ); // location_id
		regRead.setRs_LO_name( rs.getString("LO_name") ); regRead.setRs_LO_name( (regRead.getRs_LO_name() == null)?"":regRead.getRs_LO_name().trim() ); // LO_name
		regRead.setRs_user_id( rs.getString("user_id") ); regRead.setRs_user_id( (regRead.getRs_user_id() == null)?"":regRead.getRs_user_id().trim() ); // user_id
		regRead.setRs_US_country_id( rs.getLong("US_country_id") );  // US_country_id
		regRead.setRs_US_nick( rs.getString("US_nick") ); regRead.setRs_US_nick( (regRead.getRs_US_nick() == null)?"":regRead.getRs_US_nick().trim() ); // US_nick
		regRead.setRs_US_avatar( rs.getString("US_avatar") ); regRead.setRs_US_avatar( (regRead.getRs_US_avatar() == null)?"":regRead.getRs_US_avatar().trim() ); // US_avatar
		regRead.setRs_US_is_admin( rs.getString("US_is_admin") ); regRead.setRs_US_is_admin( (regRead.getRs_US_is_admin() == null)?"":regRead.getRs_US_is_admin().trim() ); // US_is_admin
		regRead.setRs_US_first_name( rs.getString("US_first_name") ); regRead.setRs_US_first_name( (regRead.getRs_US_first_name() == null)?"":regRead.getRs_US_first_name().trim() ); // US_first_name
		regRead.setRs_US_last_name( rs.getString("US_last_name") ); regRead.setRs_US_last_name( (regRead.getRs_US_last_name() == null)?"":regRead.getRs_US_last_name().trim() ); // US_last_name
		regRead.setRs_product_id( rs.getString("product_id") ); regRead.setRs_product_id( (regRead.getRs_product_id() == null)?"":regRead.getRs_product_id().trim() ); // product_id
		regRead.setRs_PT_name( rs.getString("PT_name") ); regRead.setRs_PT_name( (regRead.getRs_PT_name() == null)?"":regRead.getRs_PT_name().trim() ); // PT_name
		regRead.setRs_PT_deadline( rs.getString("PT_deadline") ); regRead.setRs_PT_deadline( (regRead.getRs_PT_deadline() == null)?"":regRead.getRs_PT_deadline().trim() ); // PT_deadline
		regRead.setRs_PT_isPercent( rs.getString("PT_isPercent") ); regRead.setRs_PT_isPercent( (regRead.getRs_PT_isPercent() == null)?"":regRead.getRs_PT_isPercent().trim() ); // PT_isPercent
		regRead.setRs_PT_amount( rs.getDouble("PT_amount") );  // PT_amount
		regRead.setRs_PT_duration_minutes( rs.getLong("PT_duration_minutes") );  // PT_duration_minutes
		regRead.setRs_quantity( rs.getLong("quantity") );  // quantity
		regRead.setRs_product_id2( rs.getString("product_id2") ); regRead.setRs_product_id2( (regRead.getRs_product_id2() == null)?"":regRead.getRs_product_id2().trim() ); // product_id2
		regRead.setRs_PT_name2( rs.getString("PT_name2") ); regRead.setRs_PT_name2( (regRead.getRs_PT_name2() == null)?"":regRead.getRs_PT_name2().trim() ); // PT_name2
		regRead.setRs_PT_deadline2( rs.getString("PT_deadline2") ); regRead.setRs_PT_deadline2( (regRead.getRs_PT_deadline2() == null)?"":regRead.getRs_PT_deadline2().trim() ); // PT_deadline2
		regRead.setRs_PT_isPercent2( rs.getString("PT_isPercent2") ); regRead.setRs_PT_isPercent2( (regRead.getRs_PT_isPercent2() == null)?"":regRead.getRs_PT_isPercent2().trim() ); // PT_isPercent2
		regRead.setRs_PT_amount2( rs.getDouble("PT_amount2") );  // PT_amount2
		regRead.setRs_product_id3( rs.getString("product_id3") ); regRead.setRs_product_id3( (regRead.getRs_product_id3() == null)?"":regRead.getRs_product_id3().trim() ); // product_id3
		regRead.setRs_PT_name3( rs.getString("PT_name3") ); regRead.setRs_PT_name3( (regRead.getRs_PT_name3() == null)?"":regRead.getRs_PT_name3().trim() ); // PT_name3
		regRead.setRs_PT_deadline3( rs.getString("PT_deadline3") ); regRead.setRs_PT_deadline3( (regRead.getRs_PT_deadline3() == null)?"":regRead.getRs_PT_deadline3().trim() ); // PT_deadline3
		regRead.setRs_PT_isPercent3( rs.getString("PT_isPercent3") ); regRead.setRs_PT_isPercent3( (regRead.getRs_PT_isPercent3() == null)?"":regRead.getRs_PT_isPercent3().trim() ); // PT_isPercent3
		regRead.setRs_PT_amount3( rs.getDouble("PT_amount3") );  // PT_amount3
		regRead.setRs_amount( rs.getDouble("amount") );  // amount
		regRead.setRs_currency( rs.getString("currency") ); regRead.setRs_currency( (regRead.getRs_currency() == null)?"":regRead.getRs_currency().trim() ); // currency
		regRead.setRs_phone( rs.getString("phone") ); regRead.setRs_phone( (regRead.getRs_phone() == null)?"":regRead.getRs_phone().trim() ); // phone
		regRead.setRs_pay_status( rs.getString("pay_status") ); regRead.setRs_pay_status( (regRead.getRs_pay_status() == null)?"":regRead.getRs_pay_status().trim() ); // pay_status
		regRead.setRs_start_date( rs.getString("start_date") ); regRead.setRs_start_date( (regRead.getRs_start_date() == null)?"":regRead.getRs_start_date().trim() ); // start_date
		regRead.setRs_start_time( rs.getString("start_time") ); regRead.setRs_start_time( (regRead.getRs_start_time() == null)?"":regRead.getRs_start_time().trim() ); // start_time
		regRead.setRs_duration_minutes( rs.getLong("duration_minutes") );  // duration_minutes
		regRead.setRs_places( rs.getLong("places") );  // places
		regRead.setRs_coupon_id( rs.getString("coupon_id") ); regRead.setRs_coupon_id( (regRead.getRs_coupon_id() == null)?"":regRead.getRs_coupon_id().trim() ); // coupon_id
		regRead.setRs_executed_at( rs.getString("executed_at") ); regRead.setRs_executed_at( (regRead.getRs_executed_at() == null)?"":regRead.getRs_executed_at().trim() ); // executed_at
		regRead.setRs_note( rs.getString("note") ); regRead.setRs_note( (regRead.getRs_note() == null)?"":regRead.getRs_note().trim() ); // note
		regRead.setRs_comment( rs.getString("comment") ); regRead.setRs_comment( (regRead.getRs_comment() == null)?"":regRead.getRs_comment().trim() ); // comment
		regRead.setRs_json( rs.getString("json") ); regRead.setRs_json( (regRead.getRs_json() == null)?"":regRead.getRs_json().trim() ); // json
            }
        } catch (SQLException ex0) {
            throw new StExcepcion(ex0.getMessage());
        } catch (StExcepcion ex1) {
            throw new StExcepcion(ex1.getMessage());
        } finally {
            try {
                if ( rs != null ) { BDConexion.rsClose( dataBase, rs ); }
            } catch (SQLException ex2) {
                throw new StExcepcion(ex2.getMessage());
            }
        }
        //////////////////////////////////////////////
        
        return regRead;
    }
    public RsBean[] rs_getSeq(BDConexion dataBase, ConfigPantalla extCfg, RsBeanFiltro rst, boolean isSoloPagadas ) throws StExcepcion {
        RsBean[] filasRecuperadas = null;
        ///////////////////////////////////////////////////////
        ConfigPantalla cfg = (extCfg!=null)?extCfg:new ConfigPantalla();
        if ( cfg.isExportar() ) {
            cfg.setFilaInicioGrid(1);
            cfg.setFilasGrid(Integer.MAX_VALUE);
            cfg.setFilasTotales(0);
            getSeq_Sub_ExportIni( cfg.getTituloPantalla() );
        }

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_rs_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            rs_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return rs_getParFS_GETSEQ( idOp, cfg );
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////////////////
        if (dataBase==null) dataBase= new BDConexion();
        ///////////////////////////////////////////////////////
        String sql =
                "SELECT \"A\".*" +
                " FROM \"" + Subrutinas.getG_DB_LIBDAT(dataBase.getCurrentDb()) + "\".\""  + this.lf_RTV + "\" \"A\""
                ;
        String sqlWhere = "";
        ///////////////////////////////////////////////////////
        // Filtros de la lista:
        RstAplicar fltOper = new RstAplicar(dataBase.getRwUpperCase(),dataBase.getRwLike(),dataBase.getRwAnyString());
	
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_sincro(),"sincro",sqlWhere);   // sincro
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_mark(),"mark",sqlWhere);   // mark
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_author(),"author",sqlWhere);   // author
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_reservation_id(),"reservation_id",sqlWhere);   // reservation_id
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_location_id(),"location_id",sqlWhere);   // location_id
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_LO_name(),"LO_name",sqlWhere);   // LO_name
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_user_id(),"user_id",sqlWhere);   // user_id
    	sqlWhere = fltOper.getNUM_EQ(rst.getRs_US_country_id(),"US_country_id",sqlWhere);   // US_country_id
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_US_is_admin(),"US_is_admin",sqlWhere);   // US_is_admin
        sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_US_nick(),"US_nick",sqlWhere);   // US_nick
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_US_avatar(),"US_avatar",sqlWhere);   // US_avatar
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_US_first_name(),"US_first_name",sqlWhere);   // US_first_name
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_US_last_name(),"US_last_name",sqlWhere);   // US_last_name
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_product_id(),"product_id",sqlWhere);   // product_id
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_PT_name(),"PT_name",sqlWhere);   // PT_name
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_PT_deadline(),"PT_deadline",sqlWhere);   // PT_deadline
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_PT_isPercent(),"PT_isPercent",sqlWhere);   // PT_isPercent
    	sqlWhere = fltOper.getNUM_EQ(rst.getRs_PT_duration_minutes(),"PT_duration_minutes",sqlWhere);   // PT_duration_minutes
    	sqlWhere = fltOper.getNUM_EQ(rst.getRs_quantity(),"quantity",sqlWhere);   // quantity
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_product_id2(),"product_id2",sqlWhere);   // product_id2
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_PT_name2(),"PT_name2",sqlWhere);   // PT_name2
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_PT_deadline2(),"PT_deadline2",sqlWhere);   // PT_deadline2
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_PT_isPercent2(),"PT_isPercent2",sqlWhere);   // PT_isPercent2
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_product_id3(),"product_id3",sqlWhere);   // product_id3
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_PT_name3(),"PT_name3",sqlWhere);   // PT_name3
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_PT_deadline3(),"PT_deadline3",sqlWhere);   // PT_deadline3
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_PT_isPercent3(),"PT_isPercent3",sqlWhere);   // PT_isPercent3
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_currency(),"currency",sqlWhere);   // currency
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_phone(),"phone",sqlWhere);   // phone
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_pay_status(),"pay_status",sqlWhere);   // pay_status
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_start_date(),"start_date",sqlWhere);   // start_date
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_start_time(),"start_time",sqlWhere);   // start_time
    	sqlWhere = fltOper.getNUM_EQ(rst.getRs_duration_minutes(),"duration_minutes",sqlWhere);   // duration_minutes
    	sqlWhere = fltOper.getNUM_EQ(rst.getRs_places(),"places",sqlWhere);   // places
    	sqlWhere = fltOper.getCHAR_EQ(rst.getRs_coupon_id(),"coupon_id",sqlWhere);   // coupon_id
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_executed_at(),"executed_at",sqlWhere);   // executed_at
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_note(),"note",sqlWhere);   // note
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_comment(),"comment",sqlWhere);   // comment
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getRs_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
		if (isSoloPagadas) {
			List<String> lista = new ArrayList<String>();
			lista.add(_K.PAY_STS_PAYPAL_Completado);
			lista.add(_K.PAY_STS_TPV_Completado);
			lista.add(_K.PAY_STS_GRATIS_Completado);
			sqlWhere = fltOper.getCHAR_IN(lista, "pay_status", sqlWhere);
		}

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"reservation_id\" DESC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        RsBean regRead = null;
        ArrayList<RsBean> arrayTmp = new ArrayList<RsBean>();
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            ///////////////////////////////////////
            // Configuración del DSPFIL (NumFilas, NumPantallas...)
            if (cfg != null) {
                String sqlCount = "SELECT COUNT(*) AS nFilas FROM \"" + Subrutinas.getG_DB_LIBDAT(dataBase.getCurrentDb()) + "\".\""  + lf_RTV + "\" \"A\"";
                sqlCount += sqlWhere;
                rs = dataBase.executeQuery(sqlCount);
                cfg.setFilasTotales(0);
                if ( rs.next() ) cfg.setFilasTotales( rs.getInt("nFilas") );
                if ( rs != null ) { BDConexion.rsClose( dataBase, rs ); }
                ///////////////////////////////////////
                if ( cfg.isExportar() ) {
                    if ( cfg.getFilasTotales() > 5000 ) {
                        getSeq_Sub_ExportFin();
                        throw new StExcepcion("Se permiten exportar hasta 5000 filas.\r\nPor favor aplique una selección mas restrictiva.");
                    }
                }
            }
            ///////////////////////////////////////

			// Código para postgres
            sql += " LIMIT "  + cfg.getFilasGrid();
            sql += " OFFSET " + (cfg.getFilaInicioGrid()-1);
            rs = dataBase.executeQuery(sql);
            if ( rs != null ) {
                int filas = 0;
                  if ( rs.next() ) {
                    do {
                        regRead = new RsBean();
                        
		regRead.setRs_sincro( rs.getString("sincro") ); regRead.setRs_sincro( (regRead.getRs_sincro() == null)?"":regRead.getRs_sincro().trim() ); // sincro
		regRead.setRs_mark( rs.getString("mark") ); regRead.setRs_mark( (regRead.getRs_mark() == null)?"":regRead.getRs_mark().trim() ); // mark
		regRead.setRs_is_deleted( rs.getString("is_deleted") ); regRead.setRs_is_deleted( (regRead.getRs_is_deleted() == null)?"":regRead.getRs_is_deleted().trim() ); // is_deleted
		regRead.setRs_author( rs.getString("author") ); regRead.setRs_author( (regRead.getRs_author() == null)?"":regRead.getRs_author().trim() ); // author
		regRead.setRs_reservation_id( rs.getString("reservation_id") ); regRead.setRs_reservation_id( (regRead.getRs_reservation_id() == null)?"":regRead.getRs_reservation_id().trim() ); // reservation_id
		regRead.setRs_location_id( rs.getString("location_id") ); regRead.setRs_location_id( (regRead.getRs_location_id() == null)?"":regRead.getRs_location_id().trim() ); // location_id
		regRead.setRs_LO_name( rs.getString("LO_name") ); regRead.setRs_LO_name( (regRead.getRs_LO_name() == null)?"":regRead.getRs_LO_name().trim() ); // LO_name
		regRead.setRs_user_id( rs.getString("user_id") ); regRead.setRs_user_id( (regRead.getRs_user_id() == null)?"":regRead.getRs_user_id().trim() ); // user_id
		regRead.setRs_US_country_id( rs.getLong("US_country_id") );  // US_country_id
		regRead.setRs_US_nick( rs.getString("US_nick") ); regRead.setRs_US_nick( (regRead.getRs_US_nick() == null)?"":regRead.getRs_US_nick().trim() ); // US_nick
		regRead.setRs_US_avatar( rs.getString("US_avatar") ); regRead.setRs_US_avatar( (regRead.getRs_US_avatar() == null)?"":regRead.getRs_US_avatar().trim() ); // US_avatar
		regRead.setRs_US_is_admin( rs.getString("US_is_admin") ); regRead.setRs_US_is_admin( (regRead.getRs_US_is_admin() == null)?"":regRead.getRs_US_is_admin().trim() ); // US_is_admin
		regRead.setRs_US_first_name( rs.getString("US_first_name") ); regRead.setRs_US_first_name( (regRead.getRs_US_first_name() == null)?"":regRead.getRs_US_first_name().trim() ); // US_first_name
		regRead.setRs_US_last_name( rs.getString("US_last_name") ); regRead.setRs_US_last_name( (regRead.getRs_US_last_name() == null)?"":regRead.getRs_US_last_name().trim() ); // US_last_name
		regRead.setRs_product_id( rs.getString("product_id") ); regRead.setRs_product_id( (regRead.getRs_product_id() == null)?"":regRead.getRs_product_id().trim() ); // product_id
		regRead.setRs_PT_name( rs.getString("PT_name") ); regRead.setRs_PT_name( (regRead.getRs_PT_name() == null)?"":regRead.getRs_PT_name().trim() ); // PT_name
		regRead.setRs_PT_deadline( rs.getString("PT_deadline") ); regRead.setRs_PT_deadline( (regRead.getRs_PT_deadline() == null)?"":regRead.getRs_PT_deadline().trim() ); // PT_deadline
		regRead.setRs_PT_isPercent( rs.getString("PT_isPercent") ); regRead.setRs_PT_isPercent( (regRead.getRs_PT_isPercent() == null)?"":regRead.getRs_PT_isPercent().trim() ); // PT_isPercent
		regRead.setRs_PT_amount( rs.getDouble("PT_amount") );  // PT_amount
		regRead.setRs_PT_duration_minutes( rs.getLong("PT_duration_minutes") );  // PT_duration_minutes
		regRead.setRs_quantity( rs.getLong("quantity") );  // quantity
		regRead.setRs_product_id2( rs.getString("product_id2") ); regRead.setRs_product_id2( (regRead.getRs_product_id2() == null)?"":regRead.getRs_product_id2().trim() ); // product_id2
		regRead.setRs_PT_name2( rs.getString("PT_name2") ); regRead.setRs_PT_name2( (regRead.getRs_PT_name2() == null)?"":regRead.getRs_PT_name2().trim() ); // PT_name2
		regRead.setRs_PT_deadline2( rs.getString("PT_deadline2") ); regRead.setRs_PT_deadline2( (regRead.getRs_PT_deadline2() == null)?"":regRead.getRs_PT_deadline2().trim() ); // PT_deadline2
		regRead.setRs_PT_isPercent2( rs.getString("PT_isPercent2") ); regRead.setRs_PT_isPercent2( (regRead.getRs_PT_isPercent2() == null)?"":regRead.getRs_PT_isPercent2().trim() ); // PT_isPercent2
		regRead.setRs_PT_amount2( rs.getDouble("PT_amount2") );  // PT_amount2
		regRead.setRs_product_id3( rs.getString("product_id3") ); regRead.setRs_product_id3( (regRead.getRs_product_id3() == null)?"":regRead.getRs_product_id3().trim() ); // product_id3
		regRead.setRs_PT_name3( rs.getString("PT_name3") ); regRead.setRs_PT_name3( (regRead.getRs_PT_name3() == null)?"":regRead.getRs_PT_name3().trim() ); // PT_name3
		regRead.setRs_PT_deadline3( rs.getString("PT_deadline3") ); regRead.setRs_PT_deadline3( (regRead.getRs_PT_deadline3() == null)?"":regRead.getRs_PT_deadline3().trim() ); // PT_deadline3
		regRead.setRs_PT_isPercent3( rs.getString("PT_isPercent3") ); regRead.setRs_PT_isPercent3( (regRead.getRs_PT_isPercent3() == null)?"":regRead.getRs_PT_isPercent3().trim() ); // PT_isPercent3
		regRead.setRs_PT_amount3( rs.getDouble("PT_amount3") );  // PT_amount3
		regRead.setRs_amount( rs.getDouble("amount") );  // amount
		regRead.setRs_currency( rs.getString("currency") ); regRead.setRs_currency( (regRead.getRs_currency() == null)?"":regRead.getRs_currency().trim() ); // currency
		regRead.setRs_phone( rs.getString("phone") ); regRead.setRs_phone( (regRead.getRs_phone() == null)?"":regRead.getRs_phone().trim() ); // phone
		regRead.setRs_pay_status( rs.getString("pay_status") ); regRead.setRs_pay_status( (regRead.getRs_pay_status() == null)?"":regRead.getRs_pay_status().trim() ); // pay_status
		regRead.setRs_start_date( rs.getString("start_date") ); regRead.setRs_start_date( (regRead.getRs_start_date() == null)?"":regRead.getRs_start_date().trim() ); // start_date
		regRead.setRs_start_time( rs.getString("start_time") ); regRead.setRs_start_time( (regRead.getRs_start_time() == null)?"":regRead.getRs_start_time().trim() ); // start_time
		regRead.setRs_duration_minutes( rs.getLong("duration_minutes") );  // duration_minutes
		regRead.setRs_places( rs.getLong("places") );  // places
		regRead.setRs_coupon_id( rs.getString("coupon_id") ); regRead.setRs_coupon_id( (regRead.getRs_coupon_id() == null)?"":regRead.getRs_coupon_id().trim() ); // coupon_id
		regRead.setRs_executed_at( rs.getString("executed_at") ); regRead.setRs_executed_at( (regRead.getRs_executed_at() == null)?"":regRead.getRs_executed_at().trim() ); // executed_at
		regRead.setRs_note( rs.getString("note") ); regRead.setRs_note( (regRead.getRs_note() == null)?"":regRead.getRs_note().trim() ); // note
		regRead.setRs_comment( rs.getString("comment") ); regRead.setRs_comment( (regRead.getRs_comment() == null)?"":regRead.getRs_comment().trim() ); // comment
		regRead.setRs_json( rs.getString("json") ); regRead.setRs_json( (regRead.getRs_json() == null)?"":regRead.getRs_json().trim() ); // json
                        
                        if ( cfg.isExportar() ) getSeq_Sub_ExportMid( regRead );
                        else                    arrayTmp.add( regRead );

                        filas++;
                    } while( rs.next() && filas < (  (cfg!=null)?cfg.getFilasGrid():(new ConfigPantalla()).getFilasGrid() ) );
                }
            }
        } catch (SQLException ex0) {
            throw new StExcepcion(ex0.getMessage());
        } catch (StExcepcion ex1) {
            throw new StExcepcion(ex1.getMessage());
        } finally {
            try {
                if ( rs != null ) { BDConexion.rsClose( dataBase, rs ); }
            } catch (SQLException ex2) {
                throw new StExcepcion(ex2.getMessage());
            }
        }
        //////////////////////////////////////////////
        if ( cfg.isExportar() ) {
            getSeq_Sub_ExportFin();
        }
        //////////////////////////////////////////////
        filasRecuperadas = new RsBean[arrayTmp.size()];
        filasRecuperadas = arrayTmp.toArray(filasRecuperadas);
        return filasRecuperadas;
    }
    protected void getSeq_Sub_ExportIni( String NombreArchivo ) throws StExcepcion {
        /////////////////////////////
        // Nombre completo del archivo viene por parámetros.
        /////////////////////////////
        fo = new File( NombreArchivo );
        try {
        	
//        	int i = fo.getAbsolutePath().lastIndexOf(File.separator);
//        	if ( i > -1 ) {
//        		try { 
//        			new File(fo.getAbsolutePath().substring(0, i)).mkdirs();
//				} catch (Exception e) {;}
//        	}
        	
            dout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fo)));
            if (dout!=null) {
				String s = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">";
				s += "\r\n<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\"><title>"+fo.getName()+"</title></head><body><table>\r\n";
				s += "<tr>";
				s += "<td><strong style='color:darkblue;'>" + "sincro" + "</strong></td>";  // sincro
				s += "<td><strong style='color:darkblue;'>" + "mark" + "</strong></td>";  // mark
				s += "<td><strong style='color:darkblue;'>" + "is_deleted" + "</strong></td>";  // is_deleted
				s += "<td><strong style='color:darkblue;'>" + "author" + "</strong></td>";  // author
				s += "<td><strong style='color:darkblue;'>" + "reservation_id" + "</strong></td>";  // reservation_id
				s += "<td><strong style='color:darkblue;'>" + "location_id" + "</strong></td>";  // location_id
				s += "<td><strong style='color:darkblue;'>" + "LO_name" + "</strong></td>";  // LO_name
				s += "<td><strong style='color:darkblue;'>" + "user_id" + "</strong></td>";  // user_id
				s += "<td><strong style='color:darkblue;'>" + "US_country_id" + "</strong></td>";  // US_country_id
				s += "<td><strong style='color:darkblue;'>" + "US_nick" + "</strong></td>";  // US_nick
				s += "<td><strong style='color:darkblue;'>" + "US_avatar" + "</strong></td>";  // US_avatar
				s += "<td><strong style='color:darkblue;'>" + "US_is_admin" + "</strong></td>";  // US_is_admin
				s += "<td><strong style='color:darkblue;'>" + "US_first_name" + "</strong></td>";  // US_first_name
				s += "<td><strong style='color:darkblue;'>" + "US_last_name" + "</strong></td>";  // US_last_name
				s += "<td><strong style='color:darkblue;'>" + "product_id" + "</strong></td>";  // product_id
				s += "<td><strong style='color:darkblue;'>" + "PT_name" + "</strong></td>";  // PT_name
				s += "<td><strong style='color:darkblue;'>" + "PT_deadline" + "</strong></td>";  // PT_deadline
				s += "<td><strong style='color:darkblue;'>" + "PT_isPercent" + "</strong></td>";  // PT_isPercent
				s += "<td><strong style='color:darkblue;'>" + "PT_amount" + "</strong></td>";  // PT_amount
				s += "<td><strong style='color:darkblue;'>" + "PT_duration_minutes" + "</strong></td>";  // PT_duration_minutes
				s += "<td><strong style='color:darkblue;'>" + "quantity" + "</strong></td>";  // quantity
				s += "<td><strong style='color:darkblue;'>" + "product_id2" + "</strong></td>";  // product_id2
				s += "<td><strong style='color:darkblue;'>" + "PT_name2" + "</strong></td>";  // PT_name2
				s += "<td><strong style='color:darkblue;'>" + "PT_deadline2" + "</strong></td>";  // PT_deadline2
				s += "<td><strong style='color:darkblue;'>" + "PT_isPercent2" + "</strong></td>";  // PT_isPercent2
				s += "<td><strong style='color:darkblue;'>" + "PT_amount2" + "</strong></td>";  // PT_amount2
				s += "<td><strong style='color:darkblue;'>" + "product_id3" + "</strong></td>";  // product_id3
				s += "<td><strong style='color:darkblue;'>" + "PT_name3" + "</strong></td>";  // PT_name3
				s += "<td><strong style='color:darkblue;'>" + "PT_deadline3" + "</strong></td>";  // PT_deadline3
				s += "<td><strong style='color:darkblue;'>" + "PT_isPercent3" + "</strong></td>";  // PT_isPercent3
				s += "<td><strong style='color:darkblue;'>" + "PT_amount3" + "</strong></td>";  // PT_amount3
				s += "<td><strong style='color:darkblue;'>" + "amount" + "</strong></td>";  // amount
				s += "<td><strong style='color:darkblue;'>" + "currency" + "</strong></td>";  // currency
				s += "<td><strong style='color:darkblue;'>" + "phone" + "</strong></td>";  // phone
				s += "<td><strong style='color:darkblue;'>" + "pay_status" + "</strong></td>";  // pay_status
				s += "<td><strong style='color:darkblue;'>" + "start_date" + "</strong></td>";  // start_date
				s += "<td><strong style='color:darkblue;'>" + "start_time" + "</strong></td>";  // start_time
				s += "<td><strong style='color:darkblue;'>" + "duration_minutes" + "</strong></td>";  // duration_minutes
				s += "<td><strong style='color:darkblue;'>" + "places" + "</strong></td>";  // places
				s += "<td><strong style='color:darkblue;'>" + "coupon_id" + "</strong></td>";  // coupon_id
				s += "<td><strong style='color:darkblue;'>" + "executed_at" + "</strong></td>";  // executed_at
				s += "<td><strong style='color:darkblue;'>" + "note" + "</strong></td>";  // note
				s += "<td><strong style='color:darkblue;'>" + "comment" + "</strong></td>";  // comment
				s += "<td><strong style='color:darkblue;'>" + "json" + "</strong></td>";  // json
				s += "</tr>\r\n";
                dout.write(s);
            }
        } catch (FileNotFoundException ex1) {
            throw new StExcepcion(ex1.getMessage());
        } catch (IOException ex2) {
            throw new StExcepcion(ex2.getMessage());
        }
        /////////////////////////////
    }
    protected void getSeq_Sub_ExportMid(RsBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getRs_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getRs_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getRs_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getRs_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getRs_reservation_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // reservation_id
				tmp = registro.getRs_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getRs_LO_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_name
				tmp = registro.getRs_user_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // user_id
				s += "<td>" + new Long(registro.getRs_US_country_id()).toString() + "</td>";  // US_country_id
				tmp = registro.getRs_US_nick();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // US_nick
				tmp = registro.getRs_US_avatar();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // US_avatar
				tmp = registro.getRs_US_is_admin();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // US_is_admin
				tmp = registro.getRs_US_first_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // US_first_name
				tmp = registro.getRs_US_last_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // US_last_name
				tmp = registro.getRs_product_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // product_id
				tmp = registro.getRs_PT_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_name
				tmp = registro.getRs_PT_deadline();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_deadline
				tmp = registro.getRs_PT_isPercent();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_isPercent
				s += "<td>" + new Double(registro.getRs_PT_amount()).toString() + "</td>";  // PT_amount
				s += "<td>" + new Long(registro.getRs_PT_duration_minutes()).toString() + "</td>";  // PT_duration_minutes
				s += "<td>" + new Long(registro.getRs_quantity()).toString() + "</td>";  // quantity
				tmp = registro.getRs_product_id2();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // product_id2
				tmp = registro.getRs_PT_name2();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_name2
				tmp = registro.getRs_PT_deadline2();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_deadline2
				tmp = registro.getRs_PT_isPercent2();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_isPercent2
				s += "<td>" + new Double(registro.getRs_PT_amount2()).toString() + "</td>";  // PT_amount2
				tmp = registro.getRs_product_id3();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // product_id3
				tmp = registro.getRs_PT_name3();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_name3
				tmp = registro.getRs_PT_deadline3();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_deadline3
				tmp = registro.getRs_PT_isPercent3();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_isPercent3
				s += "<td>" + new Double(registro.getRs_PT_amount3()).toString() + "</td>";  // PT_amount3
				s += "<td>" + new Double(registro.getRs_amount()).toString() + "</td>";  // amount
				tmp = registro.getRs_currency();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // currency
				tmp = registro.getRs_phone();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // phone
				tmp = registro.getRs_pay_status();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // pay_status
				tmp = registro.getRs_start_date();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // start_date
				tmp = registro.getRs_start_time();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // start_time
				s += "<td>" + new Long(registro.getRs_duration_minutes()).toString() + "</td>";  // duration_minutes
				s += "<td>" + new Long(registro.getRs_places()).toString() + "</td>";  // places
				tmp = registro.getRs_coupon_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // coupon_id
				tmp = registro.getRs_executed_at();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // executed_at
				tmp = registro.getRs_note();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // note
				tmp = registro.getRs_comment();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // comment
				tmp = registro.getRs_json();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // json
		s += "</tr>\r\n";

        // Grabar en el archivo de salida:
        if (fo==null) return;
        if (dout==null) return;
        try {
            dout.write(s);
        } catch (FileNotFoundException ex1) {
            throw new StExcepcion(ex1.getMessage());
        } catch (IOException ex2) {
            throw new StExcepcion(ex2.getMessage());
        }
    }
    protected void getSeq_Sub_ExportFin() throws StExcepcion {
        try {
            dout.write("</table></body></html>");
            dout.close();
        } catch (IOException ex) {
            throw new StExcepcion(ex.getMessage());
        }
    }
/////////////////////////////////////////////////
    protected void     rs_putParFS_bean( final String idOp, RsBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     rs_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, RsBeanFiltro rst ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pCfg = _K.caminoSalida  + idOp + "_cfg" + _K.extFicParm;
    	final String pRst = _K.caminoSalida  + idOp + "_rst" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pCfg, cfg.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
    	Subrutinas.grabFile(log, pRst, rst.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

//        cfg.deserializar( Subrutinas.readFile(log, pCfg ) );	// TEST
//        rst.deserializar( Subrutinas.readFile(log, pRst ) );	// TEST

    }

    protected void     rs_getParFS_RetCode( final String idOp ) throws StExcepcion {
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pRC = _K.caminoEntrada + idOp + "_RC" + _K.extFicParm;
        
        // 3d3.Leer resultados
        String rc = Subrutinas.readFile(log, pRC ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pRC).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( rc == null ) { throw new StExcepcion("El sistema externo no retorna valor."); }
        if ( rc != null && rc.trim().length() > 0 ) { throw new StExcepcion(rc); }
        
    }
    protected RsBean   rs_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	RsBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        rs_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new RsBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected RsBean[] rs_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	RsBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        rs_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new RsBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new RsBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new RsBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( RsBean[] lista ) {
		JSONObject jsonObject = new JSONObject(); 
		JSONArray jsonArray = new JSONArray();
		//////////////////////
		if ( lista != null ) {
			for ( StBean item : lista ) {
				jsonArray.add( item.toString() );
			}
		}
		jsonObject.put("registros", jsonArray);
		return jsonObject;
	}
	public RsBean[] json2beanArray(JSONObject jsonObject) {
		RsBean[] resultado = null;

		ArrayList<RsBean> arrayTmp = new ArrayList<RsBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					RsBean registro = new RsBean();
					
				registro.setRs_sincro( jsonReg.getString(0) );	// sincro
				registro.setRs_mark( jsonReg.getString(1) );	// mark
				registro.setRs_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setRs_author( jsonReg.getString(3) );	// author
				registro.setRs_reservation_id( jsonReg.getString(4) );	// reservation_id
				registro.setRs_location_id( jsonReg.getString(5) );	// location_id
				registro.setRs_LO_name( jsonReg.getString(6) );	// LO_name
				registro.setRs_user_id( jsonReg.getString(7) );	// user_id
				registro.setRs_US_country_id( jsonReg.getLong(8) );	// US_country_id
				registro.setRs_US_nick( jsonReg.getString(9) );	// US_nick
				registro.setRs_US_avatar( jsonReg.getString(10) );	// US_avatar
				registro.setRs_US_is_admin( jsonReg.getString(11) );	// US_is_admin
				registro.setRs_US_first_name( jsonReg.getString(12) );	// US_first_name
				registro.setRs_US_last_name( jsonReg.getString(13) );	// US_last_name
				registro.setRs_product_id( jsonReg.getString(14) );	// product_id
				registro.setRs_PT_name( jsonReg.getString(15) );	// PT_name
				registro.setRs_PT_deadline( jsonReg.getString(16) );	// PT_deadline
				registro.setRs_PT_isPercent( jsonReg.getString(17) );	// PT_isPercent
				registro.setRs_PT_amount( jsonReg.getDouble(18) );	// PT_amount
				registro.setRs_PT_duration_minutes( jsonReg.getLong(19) );	// PT_duration_minutes
				registro.setRs_quantity( jsonReg.getLong(20) );	// quantity
				registro.setRs_product_id2( jsonReg.getString(21) );	// product_id2
				registro.setRs_PT_name2( jsonReg.getString(22) );	// PT_name2
				registro.setRs_PT_deadline2( jsonReg.getString(23) );	// PT_deadline2
				registro.setRs_PT_isPercent2( jsonReg.getString(24) );	// PT_isPercent2
				registro.setRs_PT_amount2( jsonReg.getDouble(25) );	// PT_amount2
				registro.setRs_product_id3( jsonReg.getString(26) );	// product_id3
				registro.setRs_PT_name3( jsonReg.getString(27) );	// PT_name3
				registro.setRs_PT_deadline3( jsonReg.getString(28) );	// PT_deadline3
				registro.setRs_PT_isPercent3( jsonReg.getString(29) );	// PT_isPercent3
				registro.setRs_PT_amount3( jsonReg.getDouble(30) );	// PT_amount3
				registro.setRs_amount( jsonReg.getDouble(31) );	// amount
				registro.setRs_currency( jsonReg.getString(32) );	// currency
				registro.setRs_phone( jsonReg.getString(33) );	// phone
				registro.setRs_pay_status( jsonReg.getString(34) );	// pay_status
				registro.setRs_start_date( jsonReg.getString(35) );	// start_date
				registro.setRs_start_time( jsonReg.getString(36) );	// start_time
				registro.setRs_duration_minutes( jsonReg.getLong(37) );	// duration_minutes
				registro.setRs_places( jsonReg.getLong(38) );	// places
				registro.setRs_coupon_id( jsonReg.getString(39) );	// coupon_id
				registro.setRs_executed_at( jsonReg.getString(40) );	// executed_at
				registro.setRs_note( jsonReg.getString(41) );	// note
				registro.setRs_comment( jsonReg.getString(42) );	// comment
				registro.setRs_json( jsonReg.getString(43) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new RsBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
