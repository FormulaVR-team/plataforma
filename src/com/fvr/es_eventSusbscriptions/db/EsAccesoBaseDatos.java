package com.fvr.es_eventSusbscriptions.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.es_eventSusbscriptions.bean.EsBean;
import com.fvr.es_eventSusbscriptions.bean.EsBeanFiltro;
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


public class EsAccesoBaseDatos {
    public String tabla   = "T_ES_eventSusbscriptions";
    public String lf_UPD  = "T_ES_eventSusbscriptions";
    public String lf_RTV  = "V_ES_RTV_eventSusbscriptions";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public EsAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// es_eventSusbscriptions:
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
    public void es_crtObj(BDConexion bd, EsBean registro) throws StExcepcion {

    	registro.setEs_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_es_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	es_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	es_getParFS_RetCode(idOp);
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
		", \"inscription_user_id\"" + // inscription_user_id
		", \"first_name\"" + // first_name
		", \"last_name\"" + // last_name
		", \"phone\"" + // phone
		", \"amount\"" + // amount
		", \"currency\"" + // currency
		", \"pay_status\"" + // pay_status
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getEs_sincro() + "'" + // sincro
		", '"  + registro.getEs_mark() + "'" + // mark
		", '"  + registro.getEs_is_deleted() + "'" + // is_deleted
		", '"  + registro.getEs_author() + "'" + // author
		", '"  + registro.getEs_event_id() + "'" + // event_id
		", '"  + registro.getEs_inscription_user_id() + "'" + // inscription_user_id
		", '"  + registro.getEs_first_name() + "'" + // first_name
		", '"  + registro.getEs_last_name() + "'" + // last_name
		", '"  + registro.getEs_phone() + "'" + // phone
		", "  + registro.getEs_amount() + "" + // amount
		", '"  + registro.getEs_currency() + "'" + // currency
		", '"  + registro.getEs_pay_status() + "'" + // pay_status
		", '"  + registro.getEs_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void es_chgObj(BDConexion bd, EsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_es_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	es_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	es_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getEs_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getEs_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getEs_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getEs_author() + "'" + // author
		", \"event_id\" = '"  + registro.getEs_event_id() + "'" + // event_id
		", \"inscription_user_id\" = '"  + registro.getEs_inscription_user_id() + "'" + // inscription_user_id
		", \"first_name\" = '"  + registro.getEs_first_name() + "'" + // first_name
		", \"last_name\" = '"  + registro.getEs_last_name() + "'" + // last_name
		", \"phone\" = '"  + registro.getEs_phone() + "'" + // phone
		", \"amount\" = "  + registro.getEs_amount() + "" + // amount
		", \"currency\" = '"  + registro.getEs_currency() + "'" + // currency
		", \"pay_status\" = '"  + registro.getEs_pay_status() + "'" + // pay_status
		", \"json\" = '"  + registro.getEs_json() + "'" + // json
                " WHERE " + 
		"  \"event_id\" = '" + registro.getEs_event_id() + "'" + // event_id
		"  AND \"inscription_user_id\" = '" + registro.getEs_inscription_user_id() + "'" + // inscription_user_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void es_dltObj(BDConexion bd, EsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_es_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	es_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	es_getParFS_RetCode(idOp);
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
		"  \"event_id\" = '" + registro.getEs_event_id() + "'" + // event_id
		"  AND \"inscription_user_id\" = '" + registro.getEs_inscription_user_id() + "'" + // inscription_user_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public EsBean   es_getRcd(BDConexion dataBase, EsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_es_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	es_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return es_getParFS_GET(idOp);
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
		"  \"event_id\" = '" + registro.getEs_event_id() + "'" + // event_id
		"  AND \"inscription_user_id\" = '" + registro.getEs_inscription_user_id() + "'" + // inscription_user_id
                ""
                ;
        ResultSet rs = null;
        EsBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new EsBean();
                
		regRead.setEs_sincro( rs.getString("sincro") ); regRead.setEs_sincro( (regRead.getEs_sincro() == null)?"":regRead.getEs_sincro().trim() ); // sincro
		regRead.setEs_mark( rs.getString("mark") ); regRead.setEs_mark( (regRead.getEs_mark() == null)?"":regRead.getEs_mark().trim() ); // mark
		regRead.setEs_is_deleted( rs.getString("is_deleted") ); regRead.setEs_is_deleted( (regRead.getEs_is_deleted() == null)?"":regRead.getEs_is_deleted().trim() ); // is_deleted
		regRead.setEs_author( rs.getString("author") ); regRead.setEs_author( (regRead.getEs_author() == null)?"":regRead.getEs_author().trim() ); // author
		regRead.setEs_event_id( rs.getString("event_id") ); regRead.setEs_event_id( (regRead.getEs_event_id() == null)?"":regRead.getEs_event_id().trim() ); // event_id
		regRead.setEs_EV_location_id( rs.getString("EV_location_id") ); regRead.setEs_EV_location_id( (regRead.getEs_EV_location_id() == null)?"":regRead.getEs_EV_location_id().trim() ); // EV_location_id
		regRead.setEs_LO_name( rs.getString("LO_name") ); regRead.setEs_LO_name( (regRead.getEs_LO_name() == null)?"":regRead.getEs_LO_name().trim() ); // LO_name
		regRead.setEs_inscription_user_id( rs.getString("inscription_user_id") ); regRead.setEs_inscription_user_id( (regRead.getEs_inscription_user_id() == null)?"":regRead.getEs_inscription_user_id().trim() ); // inscription_user_id
		regRead.setEs_first_name( rs.getString("first_name") ); regRead.setEs_first_name( (regRead.getEs_first_name() == null)?"":regRead.getEs_first_name().trim() ); // first_name
		regRead.setEs_last_name( rs.getString("last_name") ); regRead.setEs_last_name( (regRead.getEs_last_name() == null)?"":regRead.getEs_last_name().trim() ); // last_name
		regRead.setEs_phone( rs.getString("phone") ); regRead.setEs_phone( (regRead.getEs_phone() == null)?"":regRead.getEs_phone().trim() ); // phone
		regRead.setEs_amount( rs.getDouble("amount") );  // amount
		regRead.setEs_currency( rs.getString("currency") ); regRead.setEs_currency( (regRead.getEs_currency() == null)?"":regRead.getEs_currency().trim() ); // currency
		regRead.setEs_pay_status( rs.getString("pay_status") ); regRead.setEs_pay_status( (regRead.getEs_pay_status() == null)?"":regRead.getEs_pay_status().trim() ); // pay_status
		regRead.setEs_json( rs.getString("json") ); regRead.setEs_json( (regRead.getEs_json() == null)?"":regRead.getEs_json().trim() ); // json
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
    public EsBean[] es_getSeq(BDConexion dataBase, ConfigPantalla extCfg, EsBeanFiltro rst ) throws StExcepcion {
        EsBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_es_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            es_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return es_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_event_id(),"event_id",sqlWhere);   // event_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_EV_location_id(),"EV_location_id",sqlWhere);   // EV_location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_LO_name(),"LO_name",sqlWhere);   // LO_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_inscription_user_id(),"inscription_user_id",sqlWhere);   // inscription_user_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_first_name(),"first_name",sqlWhere);   // first_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_last_name(),"last_name",sqlWhere);   // last_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_phone(),"phone",sqlWhere);   // phone
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_currency(),"currency",sqlWhere);   // currency
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_pay_status(),"pay_status",sqlWhere);   // pay_status
	sqlWhere = fltOper.getCHAR_LIKE(rst.getEs_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"event_id\" ASC, \"inscription_user_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        EsBean regRead = null;
        ArrayList<EsBean> arrayTmp = new ArrayList<EsBean>();
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
                        regRead = new EsBean();
                        
		regRead.setEs_sincro( rs.getString("sincro") ); regRead.setEs_sincro( (regRead.getEs_sincro() == null)?"":regRead.getEs_sincro().trim() ); // sincro
		regRead.setEs_mark( rs.getString("mark") ); regRead.setEs_mark( (regRead.getEs_mark() == null)?"":regRead.getEs_mark().trim() ); // mark
		regRead.setEs_is_deleted( rs.getString("is_deleted") ); regRead.setEs_is_deleted( (regRead.getEs_is_deleted() == null)?"":regRead.getEs_is_deleted().trim() ); // is_deleted
		regRead.setEs_author( rs.getString("author") ); regRead.setEs_author( (regRead.getEs_author() == null)?"":regRead.getEs_author().trim() ); // author
		regRead.setEs_event_id( rs.getString("event_id") ); regRead.setEs_event_id( (regRead.getEs_event_id() == null)?"":regRead.getEs_event_id().trim() ); // event_id
		regRead.setEs_EV_location_id( rs.getString("EV_location_id") ); regRead.setEs_EV_location_id( (regRead.getEs_EV_location_id() == null)?"":regRead.getEs_EV_location_id().trim() ); // EV_location_id
		regRead.setEs_LO_name( rs.getString("LO_name") ); regRead.setEs_LO_name( (regRead.getEs_LO_name() == null)?"":regRead.getEs_LO_name().trim() ); // LO_name
		regRead.setEs_inscription_user_id( rs.getString("inscription_user_id") ); regRead.setEs_inscription_user_id( (regRead.getEs_inscription_user_id() == null)?"":regRead.getEs_inscription_user_id().trim() ); // inscription_user_id
		regRead.setEs_first_name( rs.getString("first_name") ); regRead.setEs_first_name( (regRead.getEs_first_name() == null)?"":regRead.getEs_first_name().trim() ); // first_name
		regRead.setEs_last_name( rs.getString("last_name") ); regRead.setEs_last_name( (regRead.getEs_last_name() == null)?"":regRead.getEs_last_name().trim() ); // last_name
		regRead.setEs_phone( rs.getString("phone") ); regRead.setEs_phone( (regRead.getEs_phone() == null)?"":regRead.getEs_phone().trim() ); // phone
		regRead.setEs_amount( rs.getDouble("amount") );  // amount
		regRead.setEs_currency( rs.getString("currency") ); regRead.setEs_currency( (regRead.getEs_currency() == null)?"":regRead.getEs_currency().trim() ); // currency
		regRead.setEs_pay_status( rs.getString("pay_status") ); regRead.setEs_pay_status( (regRead.getEs_pay_status() == null)?"":regRead.getEs_pay_status().trim() ); // pay_status
		regRead.setEs_json( rs.getString("json") ); regRead.setEs_json( (regRead.getEs_json() == null)?"":regRead.getEs_json().trim() ); // json
                        
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
        filasRecuperadas = new EsBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "EV_location_id" + "</strong></td>";  // EV_location_id
				s += "<td><strong style='color:darkblue;'>" + "LO_name" + "</strong></td>";  // LO_name
				s += "<td><strong style='color:darkblue;'>" + "inscription_user_id" + "</strong></td>";  // inscription_user_id
				s += "<td><strong style='color:darkblue;'>" + "first_name" + "</strong></td>";  // first_name
				s += "<td><strong style='color:darkblue;'>" + "last_name" + "</strong></td>";  // last_name
				s += "<td><strong style='color:darkblue;'>" + "phone" + "</strong></td>";  // phone
				s += "<td><strong style='color:darkblue;'>" + "amount" + "</strong></td>";  // amount
				s += "<td><strong style='color:darkblue;'>" + "currency" + "</strong></td>";  // currency
				s += "<td><strong style='color:darkblue;'>" + "pay_status" + "</strong></td>";  // pay_status
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
    protected void getSeq_Sub_ExportMid(EsBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getEs_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getEs_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getEs_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getEs_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getEs_event_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // event_id
				tmp = registro.getEs_EV_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // EV_location_id
				tmp = registro.getEs_LO_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_name
				tmp = registro.getEs_inscription_user_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // inscription_user_id
				tmp = registro.getEs_first_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // first_name
				tmp = registro.getEs_last_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // last_name
				tmp = registro.getEs_phone();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // phone
				s += "<td>" + new Double(registro.getEs_amount()).toString() + "</td>";  // amount
				tmp = registro.getEs_currency();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // currency
				tmp = registro.getEs_pay_status();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // pay_status
				tmp = registro.getEs_json();
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
    protected void     es_putParFS_bean( final String idOp, EsBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     es_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, EsBeanFiltro rst ) throws StExcepcion {
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

    protected void     es_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected EsBean   es_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	EsBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        es_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new EsBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected EsBean[] es_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	EsBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        es_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new EsBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new EsBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new EsBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( EsBean[] lista ) {
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
	public EsBean[] json2beanArray(JSONObject jsonObject) {
		EsBean[] resultado = null;

		ArrayList<EsBean> arrayTmp = new ArrayList<EsBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					EsBean registro = new EsBean();
					
				registro.setEs_sincro( jsonReg.getString(0) );	// sincro
				registro.setEs_mark( jsonReg.getString(1) );	// mark
				registro.setEs_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setEs_author( jsonReg.getString(3) );	// author
				registro.setEs_event_id( jsonReg.getString(4) );	// event_id
				registro.setEs_EV_location_id( jsonReg.getString(5) );	// EV_location_id
				registro.setEs_LO_name( jsonReg.getString(6) );	// LO_name
				registro.setEs_inscription_user_id( jsonReg.getString(7) );	// inscription_user_id
				registro.setEs_first_name( jsonReg.getString(8) );	// first_name
				registro.setEs_last_name( jsonReg.getString(9) );	// last_name
				registro.setEs_phone( jsonReg.getString(10) );	// phone
				registro.setEs_amount( jsonReg.getDouble(11) );	// amount
				registro.setEs_currency( jsonReg.getString(12) );	// currency
				registro.setEs_pay_status( jsonReg.getString(13) );	// pay_status
				registro.setEs_json( jsonReg.getString(14) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new EsBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
