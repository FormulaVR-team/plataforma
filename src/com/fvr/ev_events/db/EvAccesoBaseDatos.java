package com.fvr.ev_events.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.ev_events.bean.EvBean;
import com.fvr.ev_events.bean.EvBeanFiltro;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class EvAccesoBaseDatos {
    public String tabla   = "T_EV_events";
    public String lf_UPD  = "T_EV_events";
    public String lf_RTV  = "V_EV_RTV_events";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public EvAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// ev_events:
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
    public void ev_crtObj(BDConexion bd, EvBean registro) throws StExcepcion {

    	registro.setEv_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ev_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ev_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ev_getParFS_RetCode(idOp);
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
		", \"event_id\"" + // event_id
		", \"location_id\"" + // location_id
		", \"name\"" + // name
		", \"amount\"" + // amount
		", \"currency\"" + // currency
		", \"deadline\"" + // deadline
		", \"max_inscriptions\"" + // max_inscriptions
		", \"comment\"" + // comment
		", \"date1\"" + // date1
		", \"date2\"" + // date2
		", \"date3\"" + // date3
		", \"date4\"" + // date4
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getEv_sincro() + "'" + // sincro
		", '"  + registro.getEv_mark() + "'" + // mark
		", '"  + registro.getEv_is_deleted() + "'" + // is_deleted
		", '"  + registro.getEv_author() + "'" + // author
		", '"  + registro.getEv_event_id() + "'" + // event_id
		", '"  + registro.getEv_location_id() + "'" + // location_id
		", '"  + registro.getEv_name() + "'" + // name
		", "  + registro.getEv_amount() + "" + // amount
		", '"  + registro.getEv_currency() + "'" + // currency
		", '"  + registro.getEv_deadline() + "'" + // deadline
		", "  + registro.getEv_max_inscriptions() + "" + // max_inscriptions
		", '"  + registro.getEv_comment() + "'" + // comment
		", '"  + registro.getEv_date1() + "'" + // date1
		", '"  + registro.getEv_date2() + "'" + // date2
		", '"  + registro.getEv_date3() + "'" + // date3
		", '"  + registro.getEv_date4() + "'" + // date4
		", '"  + registro.getEv_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void ev_chgObj(BDConexion bd, EvBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ev_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ev_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ev_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getEv_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getEv_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getEv_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getEv_author() + "'" + // author
		", \"event_id\" = '"  + registro.getEv_event_id() + "'" + // event_id
		", \"location_id\" = '"  + registro.getEv_location_id() + "'" + // location_id
		", \"name\" = '"  + registro.getEv_name() + "'" + // name
		", \"amount\" = "  + registro.getEv_amount() + "" + // amount
		", \"currency\" = '"  + registro.getEv_currency() + "'" + // currency
		", \"deadline\" = '"  + registro.getEv_deadline() + "'" + // deadline
		", \"max_inscriptions\" = "  + registro.getEv_max_inscriptions() + "" + // max_inscriptions
		", \"comment\" = '"  + registro.getEv_comment() + "'" + // comment
		", \"date1\" = '"  + registro.getEv_date1() + "'" + // date1
		", \"date2\" = '"  + registro.getEv_date2() + "'" + // date2
		", \"date3\" = '"  + registro.getEv_date3() + "'" + // date3
		", \"date4\" = '"  + registro.getEv_date4() + "'" + // date4
		", \"json\" = '"  + registro.getEv_json() + "'" + // json
                " WHERE " + 
		"  \"event_id\" = '" + registro.getEv_event_id() + "'" + // event_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void ev_dltObj(BDConexion bd, EvBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ev_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ev_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ev_getParFS_RetCode(idOp);
	    	return;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
    	
        //////////////////////////////////////////////
        if (bd==null) bd = new BDConexion();
        //////////////////////////////////////////////
        String sql =
                "DELETE " +
                " FROM \"" + Subrutinas.getG_DB_LIBDAT(bd.getCurrentDb()) + "\".\""  + this.lf_UPD + "\" " +
                " WHERE " + 
		"  \"event_id\" = '" + registro.getEv_event_id() + "'" + // event_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public EvBean   ev_getRcd(BDConexion dataBase, EvBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ev_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ev_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return ev_getParFS_GET(idOp);
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
		"  \"event_id\" = '" + registro.getEv_event_id() + "'" + // event_id
                ""
                ;
        ResultSet rs = null;
        EvBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new EvBean();
                
		regRead.setEv_sincro( rs.getString("sincro") ); regRead.setEv_sincro( (regRead.getEv_sincro() == null)?"":regRead.getEv_sincro().trim() ); // sincro
		regRead.setEv_mark( rs.getString("mark") ); regRead.setEv_mark( (regRead.getEv_mark() == null)?"":regRead.getEv_mark().trim() ); // mark
		regRead.setEv_is_deleted( rs.getString("is_deleted") ); regRead.setEv_is_deleted( (regRead.getEv_is_deleted() == null)?"":regRead.getEv_is_deleted().trim() ); // is_deleted
		regRead.setEv_author( rs.getString("author") ); regRead.setEv_author( (regRead.getEv_author() == null)?"":regRead.getEv_author().trim() ); // author
		regRead.setEv_event_id( rs.getString("event_id") ); regRead.setEv_event_id( (regRead.getEv_event_id() == null)?"":regRead.getEv_event_id().trim() ); // event_id
		regRead.setEv_location_id( rs.getString("location_id") ); regRead.setEv_location_id( (regRead.getEv_location_id() == null)?"":regRead.getEv_location_id().trim() ); // location_id
		regRead.setEv_LO_name( rs.getString("LO_name") ); regRead.setEv_LO_name( (regRead.getEv_LO_name() == null)?"":regRead.getEv_LO_name().trim() ); // LO_name
		regRead.setEv_name( rs.getString("name") ); regRead.setEv_name( (regRead.getEv_name() == null)?"":regRead.getEv_name().trim() ); // name
		regRead.setEv_max_inscriptions( rs.getLong("max_inscriptions") );  // max_inscriptions
		regRead.setEv_amount( rs.getDouble("amount") );  // amount
		regRead.setEv_currency( rs.getString("currency") ); regRead.setEv_currency( (regRead.getEv_currency() == null)?"":regRead.getEv_currency().trim() ); // currency
		regRead.setEv_deadline( rs.getString("deadline") ); regRead.setEv_deadline( (regRead.getEv_deadline() == null)?"":regRead.getEv_deadline().trim() ); // deadline
		regRead.setEv_comment( rs.getString("comment") ); regRead.setEv_comment( (regRead.getEv_comment() == null)?"":regRead.getEv_comment().trim() ); // comment
		regRead.setEv_date1( rs.getString("date1") ); regRead.setEv_date1( (regRead.getEv_date1() == null)?"":regRead.getEv_date1().trim() ); // date1
		regRead.setEv_date2( rs.getString("date2") ); regRead.setEv_date2( (regRead.getEv_date2() == null)?"":regRead.getEv_date2().trim() ); // date2
		regRead.setEv_date3( rs.getString("date3") ); regRead.setEv_date3( (regRead.getEv_date3() == null)?"":regRead.getEv_date3().trim() ); // date3
		regRead.setEv_date4( rs.getString("date4") ); regRead.setEv_date4( (regRead.getEv_date4() == null)?"":regRead.getEv_date4().trim() ); // date4
		regRead.setEv_json( rs.getString("json") ); regRead.setEv_json( (regRead.getEv_json() == null)?"":regRead.getEv_json().trim() ); // json
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
    public EvBean[] ev_getSeq(BDConexion dataBase, ConfigPantalla extCfg, EvBeanFiltro rst ) throws StExcepcion {
        EvBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ev_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            ev_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return ev_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_event_id(),"event_id",sqlWhere);   // event_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_location_id(),"location_id",sqlWhere);   // location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_LO_name(),"LO_name",sqlWhere);   // LO_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_name(),"name",sqlWhere);   // name
	sqlWhere = fltOper.getNUM_EQ(rst.getEv_max_inscriptions(),"max_inscriptions",sqlWhere);   // max_inscriptions
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_currency(),"currency",sqlWhere);   // currency
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_deadline(),"deadline",sqlWhere);   // deadline
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_comment(),"comment",sqlWhere);   // comment
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_date1(),"date1",sqlWhere);   // date1
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_date2(),"date2",sqlWhere);   // date2
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_date3(),"date3",sqlWhere);   // date3
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_date4(),"date4",sqlWhere);   // date4
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEv_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"event_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        EvBean regRead = null;
        ArrayList<EvBean> arrayTmp = new ArrayList<EvBean>();
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
                        regRead = new EvBean();
                        
		regRead.setEv_sincro( rs.getString("sincro") ); regRead.setEv_sincro( (regRead.getEv_sincro() == null)?"":regRead.getEv_sincro().trim() ); // sincro
		regRead.setEv_mark( rs.getString("mark") ); regRead.setEv_mark( (regRead.getEv_mark() == null)?"":regRead.getEv_mark().trim() ); // mark
		regRead.setEv_is_deleted( rs.getString("is_deleted") ); regRead.setEv_is_deleted( (regRead.getEv_is_deleted() == null)?"":regRead.getEv_is_deleted().trim() ); // is_deleted
		regRead.setEv_author( rs.getString("author") ); regRead.setEv_author( (regRead.getEv_author() == null)?"":regRead.getEv_author().trim() ); // author
		regRead.setEv_event_id( rs.getString("event_id") ); regRead.setEv_event_id( (regRead.getEv_event_id() == null)?"":regRead.getEv_event_id().trim() ); // event_id
		regRead.setEv_location_id( rs.getString("location_id") ); regRead.setEv_location_id( (regRead.getEv_location_id() == null)?"":regRead.getEv_location_id().trim() ); // location_id
		regRead.setEv_LO_name( rs.getString("LO_name") ); regRead.setEv_LO_name( (regRead.getEv_LO_name() == null)?"":regRead.getEv_LO_name().trim() ); // LO_name
		regRead.setEv_name( rs.getString("name") ); regRead.setEv_name( (regRead.getEv_name() == null)?"":regRead.getEv_name().trim() ); // name
		regRead.setEv_max_inscriptions( rs.getLong("max_inscriptions") );  // max_inscriptions
		regRead.setEv_amount( rs.getDouble("amount") );  // amount
		regRead.setEv_currency( rs.getString("currency") ); regRead.setEv_currency( (regRead.getEv_currency() == null)?"":regRead.getEv_currency().trim() ); // currency
		regRead.setEv_deadline( rs.getString("deadline") ); regRead.setEv_deadline( (regRead.getEv_deadline() == null)?"":regRead.getEv_deadline().trim() ); // deadline
		regRead.setEv_comment( rs.getString("comment") ); regRead.setEv_comment( (regRead.getEv_comment() == null)?"":regRead.getEv_comment().trim() ); // comment
		regRead.setEv_date1( rs.getString("date1") ); regRead.setEv_date1( (regRead.getEv_date1() == null)?"":regRead.getEv_date1().trim() ); // date1
		regRead.setEv_date2( rs.getString("date2") ); regRead.setEv_date2( (regRead.getEv_date2() == null)?"":regRead.getEv_date2().trim() ); // date2
		regRead.setEv_date3( rs.getString("date3") ); regRead.setEv_date3( (regRead.getEv_date3() == null)?"":regRead.getEv_date3().trim() ); // date3
		regRead.setEv_date4( rs.getString("date4") ); regRead.setEv_date4( (regRead.getEv_date4() == null)?"":regRead.getEv_date4().trim() ); // date4
		regRead.setEv_json( rs.getString("json") ); regRead.setEv_json( (regRead.getEv_json() == null)?"":regRead.getEv_json().trim() ); // json
                        
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
        filasRecuperadas = new EvBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "event_id" + "</strong></td>";  // event_id
				s += "<td><strong style='color:darkblue;'>" + "location_id" + "</strong></td>";  // location_id
				s += "<td><strong style='color:darkblue;'>" + "LO_name" + "</strong></td>";  // LO_name
				s += "<td><strong style='color:darkblue;'>" + "name" + "</strong></td>";  // name
				s += "<td><strong style='color:darkblue;'>" + "max_inscriptions" + "</strong></td>";  // max_inscriptions
				s += "<td><strong style='color:darkblue;'>" + "amount" + "</strong></td>";  // amount
				s += "<td><strong style='color:darkblue;'>" + "currency" + "</strong></td>";  // currency
				s += "<td><strong style='color:darkblue;'>" + "deadline" + "</strong></td>";  // deadline
				s += "<td><strong style='color:darkblue;'>" + "comment" + "</strong></td>";  // comment
				s += "<td><strong style='color:darkblue;'>" + "date1" + "</strong></td>";  // date1
				s += "<td><strong style='color:darkblue;'>" + "date2" + "</strong></td>";  // date2
				s += "<td><strong style='color:darkblue;'>" + "date3" + "</strong></td>";  // date3
				s += "<td><strong style='color:darkblue;'>" + "date4" + "</strong></td>";  // date4
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
    protected void getSeq_Sub_ExportMid(EvBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getEv_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getEv_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getEv_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getEv_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getEv_event_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // event_id
				tmp = registro.getEv_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getEv_LO_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_name
				tmp = registro.getEv_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // name
				s += "<td>" + new Long(registro.getEv_max_inscriptions()).toString() + "</td>";  // max_inscriptions
				s += "<td>" + new Double(registro.getEv_amount()).toString() + "</td>";  // amount
				tmp = registro.getEv_currency();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // currency
				tmp = registro.getEv_deadline();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // deadline
				tmp = registro.getEv_comment();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // comment
				tmp = registro.getEv_date1();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // date1
				tmp = registro.getEv_date2();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // date2
				tmp = registro.getEv_date3();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // date3
				tmp = registro.getEv_date4();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // date4
				tmp = registro.getEv_json();
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
    protected void     ev_putParFS_bean( final String idOp, EvBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     ev_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, EvBeanFiltro rst ) throws StExcepcion {
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

    protected void     ev_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected EvBean   ev_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	EvBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        ev_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new EvBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected EvBean[] ev_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	EvBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        ev_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new EvBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new EvBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new EvBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( EvBean[] lista ) {
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
	public EvBean[] json2beanArray(JSONObject jsonObject) {
		EvBean[] resultado = null;

		ArrayList<EvBean> arrayTmp = new ArrayList<EvBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					EvBean registro = new EvBean();
					
				registro.setEv_sincro( jsonReg.getString(0) );	// sincro
				registro.setEv_mark( jsonReg.getString(1) );	// mark
				registro.setEv_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setEv_author( jsonReg.getString(3) );	// author
				registro.setEv_event_id( jsonReg.getString(4) );	// event_id
				registro.setEv_location_id( jsonReg.getString(5) );	// location_id
				registro.setEv_LO_name( jsonReg.getString(6) );	// LO_name
				registro.setEv_name( jsonReg.getString(7) );	// name
				registro.setEv_max_inscriptions( jsonReg.getLong(8) );	// max_inscriptions
				registro.setEv_amount( jsonReg.getDouble(9) );	// amount
				registro.setEv_currency( jsonReg.getString(10) );	// currency
				registro.setEv_deadline( jsonReg.getString(11) );	// deadline
				registro.setEv_comment( jsonReg.getString(12) );	// comment
				registro.setEv_date1( jsonReg.getString(13) );	// date1
				registro.setEv_date2( jsonReg.getString(14) );	// date2
				registro.setEv_date3( jsonReg.getString(15) );	// date3
				registro.setEv_date4( jsonReg.getString(16) );	// date4
				registro.setEv_json( jsonReg.getString(17) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new EvBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
