package com.fvr.py_PayPalTokens.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.py_PayPalTokens.bean.PyBean;
import com.fvr.py_PayPalTokens.bean.PyBeanFiltro;
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


public class PyAccesoBaseDatos {
    public String tabla   = "T_PY_PayPalTokens";
    public String lf_UPD  = "T_PY_PayPalTokens";
    public String lf_RTV  = "V_PY_RTV_PayPalTokens";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public PyAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// py_PayPalTokens:
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
    public void py_crtObj(BDConexion bd, PyBean registro) throws StExcepcion {

    	registro.setPy_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_py_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	py_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	py_getParFS_RetCode(idOp);
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
		", \"user_id\"" + // user_id
		", \"reservation_id\"" + // reservation_id
		", \"paypal_token_id\"" + // paypal_token_id
		", \"paypal_usr\"" + // paypal_usr
		", \"paypal_pwd\"" + // paypal_pwd
		", \"paypal_signature\"" + // paypal_signature
		", \"stsProceso\"" + // stsProceso
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getPy_sincro() + "'" + // sincro
		", '"  + registro.getPy_mark() + "'" + // mark
		", '"  + registro.getPy_is_deleted() + "'" + // is_deleted
		", '"  + registro.getPy_author() + "'" + // author
		", '"  + registro.getPy_user_id() + "'" + // user_id
		", '"  + registro.getPy_reservation_id() + "'" + // reservation_id
		", '"  + registro.getPy_paypal_token_id() + "'" + // paypal_token_id
		", '"  + registro.getPy_paypal_usr() + "'" + // paypal_usr
		", '"  + registro.getPy_paypal_pwd() + "'" + // paypal_pwd
		", '"  + registro.getPy_paypal_signature() + "'" + // paypal_signature
		", '"  + registro.getPy_stsProceso() + "'" + // stsProceso
		", '"  + registro.getPy_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void py_chgObj(BDConexion bd, PyBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_py_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	py_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	py_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getPy_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getPy_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getPy_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getPy_author() + "'" + // author
		", \"user_id\" = '"  + registro.getPy_user_id() + "'" + // user_id
		", \"reservation_id\" = '"  + registro.getPy_reservation_id() + "'" + // reservation_id
		", \"paypal_token_id\" = '"  + registro.getPy_paypal_token_id() + "'" + // paypal_token_id
		", \"paypal_usr\" = '"  + registro.getPy_paypal_usr() + "'" + // paypal_usr
		", \"paypal_pwd\" = '"  + registro.getPy_paypal_pwd() + "'" + // paypal_pwd
		", \"paypal_signature\" = '"  + registro.getPy_paypal_signature() + "'" + // paypal_signature
		", \"stsProceso\" = '"  + registro.getPy_stsProceso() + "'" + // stsProceso
		", \"json\" = '"  + registro.getPy_json() + "'" + // json
                " WHERE " + 
		"  \"user_id\" = '" + registro.getPy_user_id() + "'" + // user_id
		"  AND \"reservation_id\" = '" + registro.getPy_reservation_id() + "'" + // reservation_id
		"  AND \"paypal_token_id\" = '" + registro.getPy_paypal_token_id() + "'" + // paypal_token_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void py_dltObj(BDConexion bd, PyBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_py_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	py_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	py_getParFS_RetCode(idOp);
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
		"  \"user_id\" = '" + registro.getPy_user_id() + "'" + // user_id
		"  AND \"reservation_id\" = '" + registro.getPy_reservation_id() + "'" + // reservation_id
		"  AND \"paypal_token_id\" = '" + registro.getPy_paypal_token_id() + "'" + // paypal_token_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public PyBean   py_getRcd(BDConexion dataBase, PyBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_py_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	py_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return py_getParFS_GET(idOp);
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
		"  \"user_id\" = '" + registro.getPy_user_id() + "'" + // user_id
		"  AND \"reservation_id\" = '" + registro.getPy_reservation_id() + "'" + // reservation_id
		"  AND \"paypal_token_id\" = '" + registro.getPy_paypal_token_id() + "'" + // paypal_token_id
                ""
                ;
        ResultSet rs = null;
        PyBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new PyBean();
                
		regRead.setPy_sincro( rs.getString("sincro") ); regRead.setPy_sincro( (regRead.getPy_sincro() == null)?"":regRead.getPy_sincro().trim() ); // sincro
		regRead.setPy_mark( rs.getString("mark") ); regRead.setPy_mark( (regRead.getPy_mark() == null)?"":regRead.getPy_mark().trim() ); // mark
		regRead.setPy_is_deleted( rs.getString("is_deleted") ); regRead.setPy_is_deleted( (regRead.getPy_is_deleted() == null)?"":regRead.getPy_is_deleted().trim() ); // is_deleted
		regRead.setPy_author( rs.getString("author") ); regRead.setPy_author( (regRead.getPy_author() == null)?"":regRead.getPy_author().trim() ); // author
		regRead.setPy_user_id( rs.getString("user_id") ); regRead.setPy_user_id( (regRead.getPy_user_id() == null)?"":regRead.getPy_user_id().trim() ); // user_id
		regRead.setPy_US_first_name( rs.getString("US_first_name") ); regRead.setPy_US_first_name( (regRead.getPy_US_first_name() == null)?"":regRead.getPy_US_first_name().trim() ); // US_first_name
		regRead.setPy_US_last_name( rs.getString("US_last_name") ); regRead.setPy_US_last_name( (regRead.getPy_US_last_name() == null)?"":regRead.getPy_US_last_name().trim() ); // US_last_name
		regRead.setPy_reservation_id( rs.getString("reservation_id") ); regRead.setPy_reservation_id( (regRead.getPy_reservation_id() == null)?"":regRead.getPy_reservation_id().trim() ); // reservation_id
		regRead.setPy_paypal_token_id( rs.getString("paypal_token_id") ); regRead.setPy_paypal_token_id( (regRead.getPy_paypal_token_id() == null)?"":regRead.getPy_paypal_token_id().trim() ); // paypal_token_id
		regRead.setPy_paypal_usr( rs.getString("paypal_usr") ); regRead.setPy_paypal_usr( (regRead.getPy_paypal_usr() == null)?"":regRead.getPy_paypal_usr().trim() ); // paypal_usr
		regRead.setPy_paypal_pwd( rs.getString("paypal_pwd") ); regRead.setPy_paypal_pwd( (regRead.getPy_paypal_pwd() == null)?"":regRead.getPy_paypal_pwd().trim() ); // paypal_pwd
		regRead.setPy_paypal_signature( rs.getString("paypal_signature") ); regRead.setPy_paypal_signature( (regRead.getPy_paypal_signature() == null)?"":regRead.getPy_paypal_signature().trim() ); // paypal_signature
		regRead.setPy_stsProceso( rs.getString("stsProceso") ); regRead.setPy_stsProceso( (regRead.getPy_stsProceso() == null)?"":regRead.getPy_stsProceso().trim() ); // stsProceso
		regRead.setPy_json( rs.getString("json") ); regRead.setPy_json( (regRead.getPy_json() == null)?"":regRead.getPy_json().trim() ); // json
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
    public PyBean[] py_getSeq(BDConexion dataBase, ConfigPantalla extCfg, PyBeanFiltro rst ) throws StExcepcion {
        PyBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_py_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            py_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return py_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_user_id(),"user_id",sqlWhere);   // user_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_US_first_name(),"US_first_name",sqlWhere);   // US_first_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_US_last_name(),"US_last_name",sqlWhere);   // US_last_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_reservation_id(),"reservation_id",sqlWhere);   // reservation_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_paypal_token_id(),"paypal_token_id",sqlWhere);   // paypal_token_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_paypal_usr(),"paypal_usr",sqlWhere);   // paypal_usr
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_paypal_pwd(),"paypal_pwd",sqlWhere);   // paypal_pwd
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_paypal_signature(),"paypal_signature",sqlWhere);   // paypal_signature
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_stsProceso(),"stsProceso",sqlWhere);   // stsProceso
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPy_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"user_id\" ASC, \"reservation_id\" ASC, \"paypal_token_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        PyBean regRead = null;
        ArrayList<PyBean> arrayTmp = new ArrayList<PyBean>();
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
                        regRead = new PyBean();
                        
		regRead.setPy_sincro( rs.getString("sincro") ); regRead.setPy_sincro( (regRead.getPy_sincro() == null)?"":regRead.getPy_sincro().trim() ); // sincro
		regRead.setPy_mark( rs.getString("mark") ); regRead.setPy_mark( (regRead.getPy_mark() == null)?"":regRead.getPy_mark().trim() ); // mark
		regRead.setPy_is_deleted( rs.getString("is_deleted") ); regRead.setPy_is_deleted( (regRead.getPy_is_deleted() == null)?"":regRead.getPy_is_deleted().trim() ); // is_deleted
		regRead.setPy_author( rs.getString("author") ); regRead.setPy_author( (regRead.getPy_author() == null)?"":regRead.getPy_author().trim() ); // author
		regRead.setPy_user_id( rs.getString("user_id") ); regRead.setPy_user_id( (regRead.getPy_user_id() == null)?"":regRead.getPy_user_id().trim() ); // user_id
		regRead.setPy_US_first_name( rs.getString("US_first_name") ); regRead.setPy_US_first_name( (regRead.getPy_US_first_name() == null)?"":regRead.getPy_US_first_name().trim() ); // US_first_name
		regRead.setPy_US_last_name( rs.getString("US_last_name") ); regRead.setPy_US_last_name( (regRead.getPy_US_last_name() == null)?"":regRead.getPy_US_last_name().trim() ); // US_last_name
		regRead.setPy_reservation_id( rs.getString("reservation_id") ); regRead.setPy_reservation_id( (regRead.getPy_reservation_id() == null)?"":regRead.getPy_reservation_id().trim() ); // reservation_id
		regRead.setPy_paypal_token_id( rs.getString("paypal_token_id") ); regRead.setPy_paypal_token_id( (regRead.getPy_paypal_token_id() == null)?"":regRead.getPy_paypal_token_id().trim() ); // paypal_token_id
		regRead.setPy_paypal_usr( rs.getString("paypal_usr") ); regRead.setPy_paypal_usr( (regRead.getPy_paypal_usr() == null)?"":regRead.getPy_paypal_usr().trim() ); // paypal_usr
		regRead.setPy_paypal_pwd( rs.getString("paypal_pwd") ); regRead.setPy_paypal_pwd( (regRead.getPy_paypal_pwd() == null)?"":regRead.getPy_paypal_pwd().trim() ); // paypal_pwd
		regRead.setPy_paypal_signature( rs.getString("paypal_signature") ); regRead.setPy_paypal_signature( (regRead.getPy_paypal_signature() == null)?"":regRead.getPy_paypal_signature().trim() ); // paypal_signature
		regRead.setPy_stsProceso( rs.getString("stsProceso") ); regRead.setPy_stsProceso( (regRead.getPy_stsProceso() == null)?"":regRead.getPy_stsProceso().trim() ); // stsProceso
		regRead.setPy_json( rs.getString("json") ); regRead.setPy_json( (regRead.getPy_json() == null)?"":regRead.getPy_json().trim() ); // json
                        
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
        filasRecuperadas = new PyBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "user_id" + "</strong></td>";  // user_id
				s += "<td><strong style='color:darkblue;'>" + "US_first_name" + "</strong></td>";  // US_first_name
				s += "<td><strong style='color:darkblue;'>" + "US_last_name" + "</strong></td>";  // US_last_name
				s += "<td><strong style='color:darkblue;'>" + "reservation_id" + "</strong></td>";  // reservation_id
				s += "<td><strong style='color:darkblue;'>" + "paypal_token_id" + "</strong></td>";  // paypal_token_id
				s += "<td><strong style='color:darkblue;'>" + "paypal_usr" + "</strong></td>";  // paypal_usr
				s += "<td><strong style='color:darkblue;'>" + "paypal_pwd" + "</strong></td>";  // paypal_pwd
				s += "<td><strong style='color:darkblue;'>" + "paypal_signature" + "</strong></td>";  // paypal_signature
				s += "<td><strong style='color:darkblue;'>" + "stsProceso" + "</strong></td>";  // stsProceso
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
    protected void getSeq_Sub_ExportMid(PyBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getPy_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getPy_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getPy_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getPy_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getPy_user_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // user_id
				tmp = registro.getPy_US_first_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // US_first_name
				tmp = registro.getPy_US_last_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // US_last_name
				tmp = registro.getPy_reservation_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // reservation_id
				tmp = registro.getPy_paypal_token_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // paypal_token_id
				tmp = registro.getPy_paypal_usr();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // paypal_usr
				tmp = registro.getPy_paypal_pwd();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // paypal_pwd
				tmp = registro.getPy_paypal_signature();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // paypal_signature
				tmp = registro.getPy_stsProceso();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // stsProceso
				tmp = registro.getPy_json();
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
    protected void     py_putParFS_bean( final String idOp, PyBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     py_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, PyBeanFiltro rst ) throws StExcepcion {
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

    protected void     py_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected PyBean   py_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	PyBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        py_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new PyBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected PyBean[] py_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	PyBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        py_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new PyBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new PyBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new PyBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( PyBean[] lista ) {
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
	public PyBean[] json2beanArray(JSONObject jsonObject) {
		PyBean[] resultado = null;

		ArrayList<PyBean> arrayTmp = new ArrayList<PyBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					PyBean registro = new PyBean();
					
				registro.setPy_sincro( jsonReg.getString(0) );	// sincro
				registro.setPy_mark( jsonReg.getString(1) );	// mark
				registro.setPy_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setPy_author( jsonReg.getString(3) );	// author
				registro.setPy_user_id( jsonReg.getString(4) );	// user_id
				registro.setPy_US_first_name( jsonReg.getString(5) );	// US_first_name
				registro.setPy_US_last_name( jsonReg.getString(6) );	// US_last_name
				registro.setPy_reservation_id( jsonReg.getString(7) );	// reservation_id
				registro.setPy_paypal_token_id( jsonReg.getString(8) );	// paypal_token_id
				registro.setPy_paypal_usr( jsonReg.getString(9) );	// paypal_usr
				registro.setPy_paypal_pwd( jsonReg.getString(10) );	// paypal_pwd
				registro.setPy_paypal_signature( jsonReg.getString(11) );	// paypal_signature
				registro.setPy_stsProceso( jsonReg.getString(12) );	// stsProceso
				registro.setPy_json( jsonReg.getString(13) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new PyBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
