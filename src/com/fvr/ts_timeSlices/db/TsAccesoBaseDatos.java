package com.fvr.ts_timeSlices.db;

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

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr.ts_timeSlices.bean.TsBean;
import com.fvr.ts_timeSlices.bean.TsBeanFiltro;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class TsAccesoBaseDatos {
    public String tabla   = "T_TS_timeSlices";
    public String lf_UPD  = "T_TS_timeSlices";
    public String lf_RTV  = "V_TS_RTV_timeSlices";
    public String lf_RTV_resumen  = "V_TS_RTV_timeSlicesSumLocFecHor";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public TsAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// ts_timeSlices:
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
    public void ts_crtObj(BDConexion bd, TsBean registro) throws StExcepcion {

    	registro.setTs_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ts_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ts_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ts_getParFS_RetCode(idOp);
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
		", \"start_date\"" + // start_date
		", \"start_time\"" + // start_time
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getTs_sincro() + "'" + // sincro
		", '"  + registro.getTs_mark() + "'" + // mark
		", '"  + registro.getTs_is_deleted() + "'" + // is_deleted
		", '"  + registro.getTs_author() + "'" + // author
		", '"  + registro.getTs_reservation_id() + "'" + // reservation_id
		", '"  + registro.getTs_start_date() + "'" + // start_date
		", '"  + registro.getTs_start_time() + "'" + // start_time
		", '"  + registro.getTs_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void ts_chgObj(BDConexion bd, TsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ts_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ts_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ts_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getTs_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getTs_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getTs_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getTs_author() + "'" + // author
		", \"reservation_id\" = '"  + registro.getTs_reservation_id() + "'" + // reservation_id
		", \"start_date\" = '"  + registro.getTs_start_date() + "'" + // start_date
		", \"start_time\" = '"  + registro.getTs_start_time() + "'" + // start_time
		", \"json\" = '"  + registro.getTs_json() + "'" + // json
                " WHERE " + 
		"  \"reservation_id\" = '" + registro.getTs_reservation_id() + "'" + // reservation_id
		"  AND \"start_date\" = '" + registro.getTs_start_date() + "'" + // start_date
		"  AND \"start_time\" = '" + registro.getTs_start_time() + "'" + // start_time
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void ts_dltObj(BDConexion bd, TsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ts_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ts_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ts_getParFS_RetCode(idOp);
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
		"  \"reservation_id\" = '" + registro.getTs_reservation_id() + "'" + // reservation_id
		"  AND \"start_date\" = '" + registro.getTs_start_date() + "'" + // start_date
		"  AND \"start_time\" = '" + registro.getTs_start_time() + "'" + // start_time
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public TsBean   ts_getRcd(BDConexion dataBase, TsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ts_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ts_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return ts_getParFS_GET(idOp);
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
		"  \"reservation_id\" = '" + registro.getTs_reservation_id() + "'" + // reservation_id
		"  AND \"start_date\" = '" + registro.getTs_start_date() + "'" + // start_date
		"  AND \"start_time\" = '" + registro.getTs_start_time() + "'" + // start_time
                ""
                ;
        ResultSet rs = null;
        TsBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new TsBean();
                
		regRead.setTs_sincro( rs.getString("sincro") ); regRead.setTs_sincro( (regRead.getTs_sincro() == null)?"":regRead.getTs_sincro().trim() ); // sincro
		regRead.setTs_mark( rs.getString("mark") ); regRead.setTs_mark( (regRead.getTs_mark() == null)?"":regRead.getTs_mark().trim() ); // mark
		regRead.setTs_is_deleted( rs.getString("is_deleted") ); regRead.setTs_is_deleted( (regRead.getTs_is_deleted() == null)?"":regRead.getTs_is_deleted().trim() ); // is_deleted
		regRead.setTs_author( rs.getString("author") ); regRead.setTs_author( (regRead.getTs_author() == null)?"":regRead.getTs_author().trim() ); // author
		regRead.setTs_reservation_id( rs.getString("reservation_id") ); regRead.setTs_reservation_id( (regRead.getTs_reservation_id() == null)?"":regRead.getTs_reservation_id().trim() ); // reservation_id
		regRead.setTs_RS_user_id( rs.getString("RS_user_id") ); regRead.setTs_RS_user_id( (regRead.getTs_RS_user_id() == null)?"":regRead.getTs_RS_user_id().trim() ); // RS_user_id
		regRead.setTs_RS_location_id( rs.getString("RS_location_id") ); regRead.setTs_RS_location_id( (regRead.getTs_RS_location_id() == null)?"":regRead.getTs_RS_location_id().trim() ); // RS_location_id
		regRead.setTs_RS_start_date( rs.getString("RS_start_date") ); regRead.setTs_RS_start_date( (regRead.getTs_RS_start_date() == null)?"":regRead.getTs_RS_start_date().trim() ); // RS_start_date
		regRead.setTs_RS_start_time( rs.getString("RS_start_time") ); regRead.setTs_RS_start_time( (regRead.getTs_RS_start_time() == null)?"":regRead.getTs_RS_start_time().trim() ); // RS_start_time
		regRead.setTs_RS_pay_status( rs.getString("RS_pay_status") ); regRead.setTs_RS_pay_status( (regRead.getTs_RS_pay_status() == null)?"":regRead.getTs_RS_pay_status().trim() ); // RS_pay_status
		regRead.setTs_RS_product_id( rs.getString("RS_product_id") ); regRead.setTs_RS_product_id( (regRead.getTs_RS_product_id() == null)?"":regRead.getTs_RS_product_id().trim() ); // RS_product_id
		regRead.setTs_RS_quantity( rs.getLong("RS_quantity") );  // RS_quantity
		regRead.setTs_RS_duration_minutes( rs.getLong("RS_duration_minutes") );  // RS_duration_minutes
		regRead.setTs_RS_places( rs.getLong("RS_places") );  // RS_places
		regRead.setTs_start_date( rs.getString("start_date") ); regRead.setTs_start_date( (regRead.getTs_start_date() == null)?"":regRead.getTs_start_date().trim() ); // start_date
		regRead.setTs_start_time( rs.getString("start_time") ); regRead.setTs_start_time( (regRead.getTs_start_time() == null)?"":regRead.getTs_start_time().trim() ); // start_time
		regRead.setTs_json( rs.getString("json") ); regRead.setTs_json( (regRead.getTs_json() == null)?"":regRead.getTs_json().trim() ); // json
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
    public TsBean[] ts_getSeq(BDConexion dataBase, ConfigPantalla extCfg, TsBeanFiltro rst ) throws StExcepcion {
        TsBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ts_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            ts_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return ts_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_reservation_id(),"reservation_id",sqlWhere);   // reservation_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_RS_user_id(),"RS_user_id",sqlWhere);   // RS_user_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_RS_location_id(),"RS_location_id",sqlWhere);   // RS_location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_RS_start_date(),"RS_start_date",sqlWhere);   // RS_start_date
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_RS_start_time(),"RS_start_time",sqlWhere);   // RS_start_time
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_RS_pay_status(),"RS_pay_status",sqlWhere);   // RS_pay_status
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_RS_product_id(),"RS_product_id",sqlWhere);   // RS_product_id
	sqlWhere = fltOper.getNUM_EQ(rst.getTs_RS_quantity(),"RS_quantity",sqlWhere);   // RS_quantity
	sqlWhere = fltOper.getNUM_EQ(rst.getTs_RS_duration_minutes(),"RS_duration_minutes",sqlWhere);   // RS_duration_minutes
	sqlWhere = fltOper.getNUM_EQ(rst.getTs_RS_places(),"RS_places",sqlWhere);   // RS_places
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_start_date(),"start_date",sqlWhere);   // start_date
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_start_time(),"start_time",sqlWhere);   // start_time
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTs_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"reservation_id\" ASC, \"start_date\" ASC, \"start_time\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        TsBean regRead = null;
        ArrayList<TsBean> arrayTmp = new ArrayList<TsBean>();
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
                        regRead = new TsBean();
                        
		regRead.setTs_sincro( rs.getString("sincro") ); regRead.setTs_sincro( (regRead.getTs_sincro() == null)?"":regRead.getTs_sincro().trim() ); // sincro
		regRead.setTs_mark( rs.getString("mark") ); regRead.setTs_mark( (regRead.getTs_mark() == null)?"":regRead.getTs_mark().trim() ); // mark
		regRead.setTs_is_deleted( rs.getString("is_deleted") ); regRead.setTs_is_deleted( (regRead.getTs_is_deleted() == null)?"":regRead.getTs_is_deleted().trim() ); // is_deleted
		regRead.setTs_author( rs.getString("author") ); regRead.setTs_author( (regRead.getTs_author() == null)?"":regRead.getTs_author().trim() ); // author
		regRead.setTs_reservation_id( rs.getString("reservation_id") ); regRead.setTs_reservation_id( (regRead.getTs_reservation_id() == null)?"":regRead.getTs_reservation_id().trim() ); // reservation_id
		regRead.setTs_RS_user_id( rs.getString("RS_user_id") ); regRead.setTs_RS_user_id( (regRead.getTs_RS_user_id() == null)?"":regRead.getTs_RS_user_id().trim() ); // RS_user_id
		regRead.setTs_RS_location_id( rs.getString("RS_location_id") ); regRead.setTs_RS_location_id( (regRead.getTs_RS_location_id() == null)?"":regRead.getTs_RS_location_id().trim() ); // RS_location_id
		regRead.setTs_RS_start_date( rs.getString("RS_start_date") ); regRead.setTs_RS_start_date( (regRead.getTs_RS_start_date() == null)?"":regRead.getTs_RS_start_date().trim() ); // RS_start_date
		regRead.setTs_RS_start_time( rs.getString("RS_start_time") ); regRead.setTs_RS_start_time( (regRead.getTs_RS_start_time() == null)?"":regRead.getTs_RS_start_time().trim() ); // RS_start_time
		regRead.setTs_RS_pay_status( rs.getString("RS_pay_status") ); regRead.setTs_RS_pay_status( (regRead.getTs_RS_pay_status() == null)?"":regRead.getTs_RS_pay_status().trim() ); // RS_pay_status
		regRead.setTs_RS_product_id( rs.getString("RS_product_id") ); regRead.setTs_RS_product_id( (regRead.getTs_RS_product_id() == null)?"":regRead.getTs_RS_product_id().trim() ); // RS_product_id
		regRead.setTs_RS_quantity( rs.getLong("RS_quantity") );  // RS_quantity
		regRead.setTs_RS_duration_minutes( rs.getLong("RS_duration_minutes") );  // RS_duration_minutes
		regRead.setTs_RS_places( rs.getLong("RS_places") );  // RS_places
		regRead.setTs_start_date( rs.getString("start_date") ); regRead.setTs_start_date( (regRead.getTs_start_date() == null)?"":regRead.getTs_start_date().trim() ); // start_date
		regRead.setTs_start_time( rs.getString("start_time") ); regRead.setTs_start_time( (regRead.getTs_start_time() == null)?"":regRead.getTs_start_time().trim() ); // start_time
		regRead.setTs_json( rs.getString("json") ); regRead.setTs_json( (regRead.getTs_json() == null)?"":regRead.getTs_json().trim() ); // json
                        
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
        filasRecuperadas = new TsBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "RS_user_id" + "</strong></td>";  // RS_user_id
				s += "<td><strong style='color:darkblue;'>" + "RS_location_id" + "</strong></td>";  // RS_location_id
				s += "<td><strong style='color:darkblue;'>" + "RS_start_date" + "</strong></td>";  // RS_start_date
				s += "<td><strong style='color:darkblue;'>" + "RS_start_time" + "</strong></td>";  // RS_start_time
				s += "<td><strong style='color:darkblue;'>" + "RS_pay_status" + "</strong></td>";  // RS_pay_status
				s += "<td><strong style='color:darkblue;'>" + "RS_product_id" + "</strong></td>";  // RS_product_id
				s += "<td><strong style='color:darkblue;'>" + "RS_quantity" + "</strong></td>";  // RS_quantity
				s += "<td><strong style='color:darkblue;'>" + "RS_duration_minutes" + "</strong></td>";  // RS_duration_minutes
				s += "<td><strong style='color:darkblue;'>" + "RS_places" + "</strong></td>";  // RS_places
				s += "<td><strong style='color:darkblue;'>" + "start_date" + "</strong></td>";  // start_date
				s += "<td><strong style='color:darkblue;'>" + "start_time" + "</strong></td>";  // start_time
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
    protected void getSeq_Sub_ExportMid(TsBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getTs_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getTs_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getTs_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getTs_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getTs_reservation_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // reservation_id
				tmp = registro.getTs_RS_user_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // RS_user_id
				tmp = registro.getTs_RS_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // RS_location_id
				tmp = registro.getTs_RS_start_date();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // RS_start_date
				tmp = registro.getTs_RS_start_time();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // RS_start_time
				tmp = registro.getTs_RS_pay_status();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // RS_pay_status
				tmp = registro.getTs_RS_product_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // RS_product_id
				s += "<td>" + new Long(registro.getTs_RS_quantity()).toString() + "</td>";  // RS_quantity
				s += "<td>" + new Long(registro.getTs_RS_duration_minutes()).toString() + "</td>";  // RS_duration_minutes
				s += "<td>" + new Long(registro.getTs_RS_places()).toString() + "</td>";  // RS_places
				tmp = registro.getTs_start_date();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // start_date
				tmp = registro.getTs_start_time();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // start_time
				tmp = registro.getTs_json();
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
    protected void     ts_putParFS_bean( final String idOp, TsBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     ts_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, TsBeanFiltro rst ) throws StExcepcion {
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

    protected void     ts_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected TsBean   ts_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	TsBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        ts_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new TsBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected TsBean[] ts_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	TsBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        ts_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new TsBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new TsBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new TsBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( TsBean[] lista ) {
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
	public TsBean[] json2beanArray(JSONObject jsonObject) {
		TsBean[] resultado = null;

		ArrayList<TsBean> arrayTmp = new ArrayList<TsBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					TsBean registro = new TsBean();
					
				registro.setTs_sincro( jsonReg.getString(0) );	// sincro
				registro.setTs_mark( jsonReg.getString(1) );	// mark
				registro.setTs_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setTs_author( jsonReg.getString(3) );	// author
				registro.setTs_reservation_id( jsonReg.getString(4) );	// reservation_id
				registro.setTs_RS_user_id( jsonReg.getString(5) );	// RS_user_id
				registro.setTs_RS_location_id( jsonReg.getString(6) );	// RS_location_id
				registro.setTs_RS_start_date( jsonReg.getString(7) );	// RS_start_date
				registro.setTs_RS_start_time( jsonReg.getString(8) );	// RS_start_time
				registro.setTs_RS_pay_status( jsonReg.getString(9) );	// RS_pay_status
				registro.setTs_RS_product_id( jsonReg.getString(10) );	// RS_product_id
				registro.setTs_RS_quantity( jsonReg.getLong(11) );	// RS_quantity
				registro.setTs_RS_duration_minutes( jsonReg.getLong(12) );	// RS_duration_minutes
				registro.setTs_RS_places( jsonReg.getLong(13) );	// RS_places
				registro.setTs_start_date( jsonReg.getString(14) );	// start_date
				registro.setTs_start_time( jsonReg.getString(15) );	// start_time
				registro.setTs_json( jsonReg.getString(16) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new TsBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
    public TsBean[] ts_getSeq_SumLocFecHor(BDConexion dataBase, ConfigPantalla extCfg, TsBeanFiltro rst ) throws StExcepcion {
        TsBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ts_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            ts_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return ts_getParFS_GETSEQ( idOp, cfg );
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////////////////
        if (dataBase==null) dataBase= new BDConexion();
        ///////////////////////////////////////////////////////
        String sql =
                "SELECT \"A\".*" +
                " FROM \"" + Subrutinas.getG_DB_LIBDAT(dataBase.getCurrentDb()) + "\".\""  + this.lf_RTV_resumen + "\" \"A\""
                ;
        String sqlWhere = "";
        ///////////////////////////////////////////////////////
        // Filtros de la lista:
        RstAplicar fltOper = new RstAplicar(dataBase.getRwUpperCase(),dataBase.getRwLike(),dataBase.getRwAnyString());
	
	sqlWhere = fltOper.getCHAR_EQ(rst.getTs_RS_location_id(),"location_id",sqlWhere);   // RS_location_id
	sqlWhere = fltOper.getCHAR_EQ(rst.getTs_start_date(),"start_date",sqlWhere);   // start_date
	sqlWhere = fltOper.getCHAR_EQ(rst.getTs_start_time(),"start_time",sqlWhere);   // start_time
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"location_id\" ASC, \"start_date\" ASC, \"start_time\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        TsBean regRead = null;
        ArrayList<TsBean> arrayTmp = new ArrayList<TsBean>();
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
//            ///////////////////////////////////////
//            // Configuración del DSPFIL (NumFilas, NumPantallas...)
//            if (cfg != null) {
//                String sqlCount = "SELECT COUNT(*) AS nFilas FROM \"" + Subrutinas.getG_DB_LIBDAT(dataBase.getCurrentDb()) + "\".\""  + lf_RTV_resumen + "\" \"A\"";
//                sqlCount += sqlWhere;
//                rs = dataBase.executeQuery(sqlCount);
//                cfg.setFilasTotales(0);
//                if ( rs.next() ) cfg.setFilasTotales( rs.getInt("nFilas") );
//                if ( rs != null ) { BDConexion.rsClose( dataBase, rs ); }
//                ///////////////////////////////////////
//                if ( cfg.isExportar() ) {
//                    if ( cfg.getFilasTotales() > 5000 ) {
//                        getSeq_Sub_ExportFin();
//                        throw new StExcepcion("Se permiten exportar hasta 5000 filas.\r\nPor favor aplique una selección mas restrictiva.");
//                    }
//                }
//            }
//            ///////////////////////////////////////

			// Código para postgres
            sql += " LIMIT "  + cfg.getFilasGrid();
            sql += " OFFSET " + (cfg.getFilaInicioGrid()-1);
            rs = dataBase.executeQuery(sql);
            if ( rs != null ) {
                int filas = 0;
                  if ( rs.next() ) {
                    do {
                        regRead = new TsBean();

        // REUTILIZAMOS estos campos para no tener que hacer otro bean específico. CUIDADO!!
		regRead.setTs_RS_location_id( rs.getString("location_id") ); regRead.setTs_RS_location_id( (regRead.getTs_RS_location_id() == null)?"":regRead.getTs_RS_location_id().trim() ); // RS_location_id
		regRead.setTs_start_date( rs.getString("start_date") ); regRead.setTs_start_date( (regRead.getTs_start_date() == null)?"":regRead.getTs_start_date().trim() ); // start_date
		regRead.setTs_start_time( rs.getString("start_time") ); regRead.setTs_start_time( (regRead.getTs_start_time() == null)?"":regRead.getTs_start_time().trim() ); // start_time
		regRead.setTs_RS_quantity( rs.getLong("nReservations") );  // RS_quantity
		regRead.setTs_RS_places( rs.getLong("nPlaces") );  // RS_places

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
        filasRecuperadas = new TsBean[arrayTmp.size()];
        filasRecuperadas = arrayTmp.toArray(filasRecuperadas);
        return filasRecuperadas;
    }
	
	public synchronized int ts_getSumPlazas_pagadas_o_pendientes(BDConexion dataBase, String location_id, String start_date, String start_time) throws StExcepcion {
		int resultado = 0;
		// Devuelve el número de reservas pagadas de una location para una fecha y hora
        TsBeanFiltro flt_ts = new TsBeanFiltro();
        flt_ts.setTs_RS_location_id(location_id);
        flt_ts.setTs_start_date(start_date);
        flt_ts.setTs_start_time(start_time);
		TsBean[] rgs_ts = null;
    	rgs_ts = ts_getSeq_SumLocFecHor(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_ts);
        if ( rgs_ts != null && rgs_ts.length > 0 ) {
        	resultado = (int) rgs_ts[0].getTs_RS_places();
        }
        return resultado;
	}
}
