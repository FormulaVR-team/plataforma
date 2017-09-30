package com.fvr.ac_activityCockpits.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun.TIME_Utility;
import com.fvr._comun._K;
import com.fvr.ac_activityCockpits.bean.AcBean;
import com.fvr.ac_activityCockpits.bean.AcBeanFiltro;
import com.fvr.ts_timeSlices.bean.TsBean;
import com.fvr.ts_timeSlices.bean.TsBeanFiltro;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class AcAccesoBaseDatos {
    public String tabla   = "T_AC_activityCockpits";
    public String lf_UPD  = "T_AC_activityCockpits";
    public String lf_RTV  = "V_AC_RTV_activityCockpits";
    public String lf_RTV_group10 = "V_AC_RTV_group10";
    public String lf_RTV_group20 = "V_AC_RTV_group20";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public AcAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// ac_activityCockpits:
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
    public void ac_crtObj(BDConexion bd, AcBean registro) throws StExcepcion {

    	registro.setAc_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ac_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ac_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ac_getParFS_RetCode(idOp);
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
//		", \"serial\"" + // serial
		", \"location_id\"" + // location_id
		", \"computername\"" + // computername
		", \"filename\"" + // filename
		", \"content\"" + // content
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getAc_sincro() + "'" + // sincro
		", '"  + registro.getAc_mark() + "'" + // mark
		", '"  + registro.getAc_is_deleted() + "'" + // is_deleted
		", '"  + registro.getAc_author() + "'" + // author
//		", "  + registro.getAc_serial() + "" + // serial
		", '"  + registro.getAc_location_id() + "'" + // location_id
		", '"  + registro.getAc_computername() + "'" + // computername
		", '"  + registro.getAc_filename() + "'" + // filename
		", '"  + registro.getAc_content() + "'" + // content
		", '"  + registro.getAc_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void ac_chgObj(BDConexion bd, AcBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ac_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ac_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ac_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getAc_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getAc_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getAc_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getAc_author() + "'" + // author
		", \"serial\" = "  + registro.getAc_serial() + "" + // serial
		", \"location_id\" = '"  + registro.getAc_location_id() + "'" + // location_id
		", \"computername\" = '"  + registro.getAc_computername() + "'" + // computername
		", \"filename\" = '"  + registro.getAc_filename() + "'" + // filename
		", \"content\" = '"  + registro.getAc_content() + "'" + // content
		", \"json\" = '"  + registro.getAc_json() + "'" + // json
                " WHERE " + 
		"  \"serial\" = " + registro.getAc_serial() + "" + // serial
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void ac_dltObj(BDConexion bd, AcBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ac_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ac_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ac_getParFS_RetCode(idOp);
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
		"  \"serial\" = " + registro.getAc_serial() + "" + // serial
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public AcBean   ac_getRcd(BDConexion dataBase, AcBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ac_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ac_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return ac_getParFS_GET(idOp);
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
		"  \"serial\" = " + registro.getAc_serial() + "" + // serial
                ""
                ;
        ResultSet rs = null;
        AcBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new AcBean();
                
		regRead.setAc_sincro( rs.getString("sincro") ); regRead.setAc_sincro( (regRead.getAc_sincro() == null)?"":regRead.getAc_sincro().trim() ); // sincro
		regRead.setAc_mark( rs.getString("mark") ); regRead.setAc_mark( (regRead.getAc_mark() == null)?"":regRead.getAc_mark().trim() ); // mark
		regRead.setAc_is_deleted( rs.getString("is_deleted") ); regRead.setAc_is_deleted( (regRead.getAc_is_deleted() == null)?"":regRead.getAc_is_deleted().trim() ); // is_deleted
		regRead.setAc_author( rs.getString("author") ); regRead.setAc_author( (regRead.getAc_author() == null)?"":regRead.getAc_author().trim() ); // author
		regRead.setAc_serial( rs.getLong("serial") );  // serial
		regRead.setAc_location_id( rs.getString("location_id") ); regRead.setAc_location_id( (regRead.getAc_location_id() == null)?"":regRead.getAc_location_id().trim() ); // location_id
		regRead.setAc_LO_name( rs.getString("LO_name") ); regRead.setAc_LO_name( (regRead.getAc_LO_name() == null)?"":regRead.getAc_LO_name().trim() ); // LO_name
		regRead.setAc_computername( rs.getString("computername") ); regRead.setAc_computername( (regRead.getAc_computername() == null)?"":regRead.getAc_computername().trim() ); // computername
		regRead.setAc_filename( rs.getString("filename") ); regRead.setAc_filename( (regRead.getAc_filename() == null)?"":regRead.getAc_filename().trim() ); // filename
		regRead.setAc_content( rs.getString("content") ); regRead.setAc_content( (regRead.getAc_content() == null)?"":regRead.getAc_content().trim() ); // content
		regRead.setAc_json( rs.getString("json") ); regRead.setAc_json( (regRead.getAc_json() == null)?"":regRead.getAc_json().trim() ); // json
		regRead.setAc_aaaa_mm( rs.getString("aaaa_mm") ); regRead.setAc_aaaa_mm( (regRead.getAc_aaaa_mm() == null)?"":regRead.getAc_aaaa_mm().trim() ); // aaaa_mm
		regRead.setAc_aaaa_mm_dd( rs.getString("aaaa_mm_dd") ); regRead.setAc_aaaa_mm_dd( (regRead.getAc_aaaa_mm_dd() == null)?"":regRead.getAc_aaaa_mm_dd().trim() ); // aaaa_mm_dd
		regRead.setAc_aaaa_mm_dd_hh( rs.getString("aaaa_mm_dd_hh") ); regRead.setAc_aaaa_mm_dd_hh( (regRead.getAc_aaaa_mm_dd_hh() == null)?"":regRead.getAc_aaaa_mm_dd_hh().trim() ); // aaaa_mm_dd_hh
		regRead.setAc_aaaa_mm_dd_hh_m0( rs.getString("aaaa_mm_dd_hh_m0") ); regRead.setAc_aaaa_mm_dd_hh_m0( (regRead.getAc_aaaa_mm_dd_hh_m0() == null)?"":regRead.getAc_aaaa_mm_dd_hh_m0().trim() ); // aaaa_mm_dd_hh_m0
		regRead.setAc_aaaa_mm_dd_hh_mm( rs.getString("aaaa_mm_dd_hh_mm") ); regRead.setAc_aaaa_mm_dd_hh_mm( (regRead.getAc_aaaa_mm_dd_hh_mm() == null)?"":regRead.getAc_aaaa_mm_dd_hh_mm().trim() ); // aaaa_mm_dd_hh_mm
		regRead.setAc_aaaa_mm_dd_hh_mm_ss( rs.getString("aaaa_mm_dd_hh_mm_ss") ); regRead.setAc_aaaa_mm_dd_hh_mm_ss( (regRead.getAc_aaaa_mm_dd_hh_mm_ss() == null)?"":regRead.getAc_aaaa_mm_dd_hh_mm_ss().trim() ); // aaaa_mm_dd_hh_mm_ss
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
    public AcBean[] ac_getSeq(BDConexion dataBase, ConfigPantalla extCfg, AcBeanFiltro rst ) throws StExcepcion {
        AcBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ac_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            ac_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return ac_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getNUM_EQ(rst.getAc_serial(),"serial",sqlWhere);   // serial
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_location_id(),"location_id",sqlWhere);   // location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_LO_name(),"LO_name",sqlWhere);   // LO_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_computername(),"computername",sqlWhere);   // computername
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_filename(),"filename",sqlWhere);   // filename
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_content(),"content",sqlWhere);   // content
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_json(),"json",sqlWhere);   // json
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm(),"aaaa_mm",sqlWhere);   // aaaa_mm
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd(),"aaaa_mm_dd",sqlWhere);   // aaaa_mm_dd
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd_hh(),"aaaa_mm_dd_hh",sqlWhere);   // aaaa_mm_dd_hh
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd_hh_m0(),"aaaa_mm_dd_hh_m0",sqlWhere);   // aaaa_mm_dd_hh_m0
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd_hh_mm(),"aaaa_mm_dd_hh_mm",sqlWhere);   // aaaa_mm_dd_hh_mm
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd_hh_mm_ss(),"aaaa_mm_dd_hh_mm_ss",sqlWhere);   // aaaa_mm_dd_hh_mm_ss
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"serial\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        AcBean regRead = null;
        ArrayList<AcBean> arrayTmp = new ArrayList<AcBean>();
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
                        regRead = new AcBean();
                        
		regRead.setAc_sincro( rs.getString("sincro") ); regRead.setAc_sincro( (regRead.getAc_sincro() == null)?"":regRead.getAc_sincro().trim() ); // sincro
		regRead.setAc_mark( rs.getString("mark") ); regRead.setAc_mark( (regRead.getAc_mark() == null)?"":regRead.getAc_mark().trim() ); // mark
		regRead.setAc_is_deleted( rs.getString("is_deleted") ); regRead.setAc_is_deleted( (regRead.getAc_is_deleted() == null)?"":regRead.getAc_is_deleted().trim() ); // is_deleted
		regRead.setAc_author( rs.getString("author") ); regRead.setAc_author( (regRead.getAc_author() == null)?"":regRead.getAc_author().trim() ); // author
		regRead.setAc_serial( rs.getLong("serial") );  // serial
		regRead.setAc_location_id( rs.getString("location_id") ); regRead.setAc_location_id( (regRead.getAc_location_id() == null)?"":regRead.getAc_location_id().trim() ); // location_id
		regRead.setAc_LO_name( rs.getString("LO_name") ); regRead.setAc_LO_name( (regRead.getAc_LO_name() == null)?"":regRead.getAc_LO_name().trim() ); // LO_name
		regRead.setAc_computername( rs.getString("computername") ); regRead.setAc_computername( (regRead.getAc_computername() == null)?"":regRead.getAc_computername().trim() ); // computername
		regRead.setAc_filename( rs.getString("filename") ); regRead.setAc_filename( (regRead.getAc_filename() == null)?"":regRead.getAc_filename().trim() ); // filename
		regRead.setAc_content( rs.getString("content") ); regRead.setAc_content( (regRead.getAc_content() == null)?"":regRead.getAc_content().trim() ); // content
		regRead.setAc_json( rs.getString("json") ); regRead.setAc_json( (regRead.getAc_json() == null)?"":regRead.getAc_json().trim() ); // json
		regRead.setAc_aaaa_mm( rs.getString("aaaa_mm") ); regRead.setAc_aaaa_mm( (regRead.getAc_aaaa_mm() == null)?"":regRead.getAc_aaaa_mm().trim() ); // aaaa_mm
		regRead.setAc_aaaa_mm_dd( rs.getString("aaaa_mm_dd") ); regRead.setAc_aaaa_mm_dd( (regRead.getAc_aaaa_mm_dd() == null)?"":regRead.getAc_aaaa_mm_dd().trim() ); // aaaa_mm_dd
		regRead.setAc_aaaa_mm_dd_hh( rs.getString("aaaa_mm_dd_hh") ); regRead.setAc_aaaa_mm_dd_hh( (regRead.getAc_aaaa_mm_dd_hh() == null)?"":regRead.getAc_aaaa_mm_dd_hh().trim() ); // aaaa_mm_dd_hh
		regRead.setAc_aaaa_mm_dd_hh_m0( rs.getString("aaaa_mm_dd_hh_m0") ); regRead.setAc_aaaa_mm_dd_hh_m0( (regRead.getAc_aaaa_mm_dd_hh_m0() == null)?"":regRead.getAc_aaaa_mm_dd_hh_m0().trim() ); // aaaa_mm_dd_hh_m0
		regRead.setAc_aaaa_mm_dd_hh_mm( rs.getString("aaaa_mm_dd_hh_mm") ); regRead.setAc_aaaa_mm_dd_hh_mm( (regRead.getAc_aaaa_mm_dd_hh_mm() == null)?"":regRead.getAc_aaaa_mm_dd_hh_mm().trim() ); // aaaa_mm_dd_hh_mm
		regRead.setAc_aaaa_mm_dd_hh_mm_ss( rs.getString("aaaa_mm_dd_hh_mm_ss") ); regRead.setAc_aaaa_mm_dd_hh_mm_ss( (regRead.getAc_aaaa_mm_dd_hh_mm_ss() == null)?"":regRead.getAc_aaaa_mm_dd_hh_mm_ss().trim() ); // aaaa_mm_dd_hh_mm_ss
                        
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
        filasRecuperadas = new AcBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "serial" + "</strong></td>";  // serial
				s += "<td><strong style='color:darkblue;'>" + "location_id" + "</strong></td>";  // location_id
				s += "<td><strong style='color:darkblue;'>" + "LO_name" + "</strong></td>";  // LO_name
				s += "<td><strong style='color:darkblue;'>" + "computername" + "</strong></td>";  // computername
				s += "<td><strong style='color:darkblue;'>" + "filename" + "</strong></td>";  // filename
				s += "<td><strong style='color:darkblue;'>" + "content" + "</strong></td>";  // content
				s += "<td><strong style='color:darkblue;'>" + "json" + "</strong></td>";  // json
				s += "<td><strong style='color:darkblue;'>" + "aaaa_mm" + "</strong></td>";  // aaaa_mm
				s += "<td><strong style='color:darkblue;'>" + "aaaa_mm_dd" + "</strong></td>";  // aaaa_mm_dd
				s += "<td><strong style='color:darkblue;'>" + "aaaa_mm_dd_hh" + "</strong></td>";  // aaaa_mm_dd_hh
				s += "<td><strong style='color:darkblue;'>" + "aaaa_mm_dd_hh_m0" + "</strong></td>";  // aaaa_mm_dd_hh_m0
				s += "<td><strong style='color:darkblue;'>" + "aaaa_mm_dd_hh_mm" + "</strong></td>";  // aaaa_mm_dd_hh_mm
				s += "<td><strong style='color:darkblue;'>" + "aaaa_mm_dd_hh_mm_ss" + "</strong></td>";  // aaaa_mm_dd_hh_mm_ss
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
    protected void getSeq_Sub_ExportMid(AcBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getAc_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getAc_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getAc_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getAc_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				s += "<td>" + new Long(registro.getAc_serial()).toString() + "</td>";  // serial
				tmp = registro.getAc_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getAc_LO_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_name
				tmp = registro.getAc_computername();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // computername
				tmp = registro.getAc_filename();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // filename
				tmp = registro.getAc_content();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // content
				tmp = registro.getAc_json();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // json
				tmp = registro.getAc_aaaa_mm();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // aaaa_mm
				tmp = registro.getAc_aaaa_mm_dd();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // aaaa_mm_dd
				tmp = registro.getAc_aaaa_mm_dd_hh();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // aaaa_mm_dd_hh
				tmp = registro.getAc_aaaa_mm_dd_hh_m0();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // aaaa_mm_dd_hh_m0
				tmp = registro.getAc_aaaa_mm_dd_hh_mm();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // aaaa_mm_dd_hh_mm
				tmp = registro.getAc_aaaa_mm_dd_hh_mm_ss();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // aaaa_mm_dd_hh_mm_ss
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
    protected void     ac_putParFS_bean( final String idOp, AcBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     ac_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, AcBeanFiltro rst ) throws StExcepcion {
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

    protected void     ac_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected AcBean   ac_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	AcBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        ac_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new AcBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected AcBean[] ac_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	AcBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        ac_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new AcBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new AcBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new AcBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( AcBean[] lista ) {
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
	public AcBean[] json2beanArray(JSONObject jsonObject) {
		AcBean[] resultado = null;

		ArrayList<AcBean> arrayTmp = new ArrayList<AcBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					AcBean registro = new AcBean();
					
				registro.setAc_sincro( jsonReg.getString(0) );	// sincro
				registro.setAc_mark( jsonReg.getString(1) );	// mark
				registro.setAc_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setAc_author( jsonReg.getString(3) );	// author
				registro.setAc_serial( jsonReg.getLong(4) );	// serial
				registro.setAc_location_id( jsonReg.getString(5) );	// location_id
				registro.setAc_LO_name( jsonReg.getString(6) );	// LO_name
				registro.setAc_computername( jsonReg.getString(7) );	// computername
				registro.setAc_filename( jsonReg.getString(8) );	// filename
				registro.setAc_content( jsonReg.getString(9) );	// content
				registro.setAc_json( jsonReg.getString(10) );	// json
				registro.setAc_aaaa_mm( jsonReg.getString(11) );	// aaaa_mm
				registro.setAc_aaaa_mm_dd( jsonReg.getString(12) );	// aaaa_mm_dd
				registro.setAc_aaaa_mm_dd_hh( jsonReg.getString(13) );	// aaaa_mm_dd_hh
				registro.setAc_aaaa_mm_dd_hh_m0( jsonReg.getString(14) );	// aaaa_mm_dd_hh_m0
				registro.setAc_aaaa_mm_dd_hh_mm( jsonReg.getString(15) );	// aaaa_mm_dd_hh_mm
				registro.setAc_aaaa_mm_dd_hh_mm_ss( jsonReg.getString(16) );	// aaaa_mm_dd_hh_mm_ss
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new AcBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////

    public AcBean[] ac_getSeq_group10(BDConexion dataBase, ConfigPantalla extCfg, AcBeanFiltro rst ) throws StExcepcion {
        AcBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ac_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            ac_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return ac_getParFS_GETSEQ( idOp, cfg );
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////////////////
        if (dataBase==null) dataBase= new BDConexion();
        ///////////////////////////////////////////////////////
        String sql =
                "SELECT \"A\".*" +
                " FROM \"" + Subrutinas.getG_DB_LIBDAT(dataBase.getCurrentDb()) + "\".\""  + this.lf_RTV_group10 + "\" \"A\""
                ;
        String sqlWhere = "";
        ///////////////////////////////////////////////////////
        // Filtros de la lista:
        RstAplicar fltOper = new RstAplicar(dataBase.getRwUpperCase(),dataBase.getRwLike(),dataBase.getRwAnyString());
	
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_sincro(),"sincro",sqlWhere);   // sincro
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_mark(),"mark",sqlWhere);   // mark
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_author(),"author",sqlWhere);   // author
//	sqlWhere = fltOper.getNUM_EQ(rst.getAc_serial(),"serial",sqlWhere);   // serial
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_location_id(),"location_id",sqlWhere);   // location_id
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_LO_name(),"LO_name",sqlWhere);   // LO_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_computername(),"computername",sqlWhere);   // computername
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_filename(),"filename",sqlWhere);   // filename
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_content(),"content",sqlWhere);   // content
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_json(),"json",sqlWhere);   // json
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm(),"aaaa_mm",sqlWhere);   // aaaa_mm
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd(),"aaaa_mm_dd",sqlWhere);   // aaaa_mm_dd
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd_hh(),"aaaa_mm_dd_hh",sqlWhere);   // aaaa_mm_dd_hh
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd_hh_m0(),"aaaa_mm_dd_hh_m0",sqlWhere);   // aaaa_mm_dd_hh_m0
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd_hh_mm(),"aaaa_mm_dd_hh_mm",sqlWhere);   // aaaa_mm_dd_hh_mm
//	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd_hh_mm_ss(),"aaaa_mm_dd_hh_mm_ss",sqlWhere);   // aaaa_mm_dd_hh_mm_ss

	sqlWhere = fltOper.getCHAR_LIKE(rst.getAc_aaaa_mm_dd(),"start_date",sqlWhere);   // aaaa_mm_dd

		//////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"location_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        AcBean regRead = null;
        ArrayList<AcBean> arrayTmp = new ArrayList<AcBean>();
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            ///////////////////////////////////////
            // Configuración del DSPFIL (NumFilas, NumPantallas...)
            if (cfg != null) {
                String sqlCount = "SELECT COUNT(*) AS nFilas FROM \"" + Subrutinas.getG_DB_LIBDAT(dataBase.getCurrentDb()) + "\".\""  + lf_RTV_group10 + "\" \"A\"";
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
                        regRead = new AcBean();
                        
		regRead.setAc_location_id( rs.getString("location_id") ); regRead.setAc_location_id( (regRead.getAc_location_id() == null)?"":regRead.getAc_location_id().trim() ); // location_id
//		regRead.setAc_LO_name( rs.getString("LO_name") ); regRead.setAc_LO_name( (regRead.getAc_LO_name() == null)?"":regRead.getAc_LO_name().trim() ); // LO_name
		regRead.setAc_computername( rs.getString("computername") ); regRead.setAc_computername( (regRead.getAc_computername() == null)?"":regRead.getAc_computername().trim() ); // computername

		regRead.setAc_aaaa_mm_dd( rs.getString("start_date") ); regRead.setAc_aaaa_mm_dd( (regRead.getAc_aaaa_mm_dd() == null)?"":regRead.getAc_aaaa_mm_dd().trim() ); // aaaa_mm_dd
		regRead.setAc_aaaa_mm_dd_hh_m0( regRead.getAc_aaaa_mm_dd() + " " + rs.getString("start_time") ); regRead.setAc_aaaa_mm_dd( (regRead.getAc_aaaa_mm_dd() == null)?"":regRead.getAc_aaaa_mm_dd().trim() ); // aaaa_mm_dd

		
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
        filasRecuperadas = new AcBean[arrayTmp.size()];
        filasRecuperadas = arrayTmp.toArray(filasRecuperadas);
        return filasRecuperadas;
    }

    public TsBean[] ac_getSeq_SumLocFecHor(BDConexion dataBase, ConfigPantalla extCfg, TsBeanFiltro rst, boolean isConvertLocalTime ) throws StExcepcion {
        TsBean[] filasRecuperadas = null;
        ///////////////////////////////////////////////////////
        ConfigPantalla cfg = (extCfg!=null)?extCfg:new ConfigPantalla();

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////
        if (dataBase==null) dataBase= new BDConexion();
        ///////////////////////////////////////////////////////
        String sql =
                "SELECT \"A\".*" +
                " FROM \"" + Subrutinas.getG_DB_LIBDAT(dataBase.getCurrentDb()) + "\".\""  + this.lf_RTV_group20 + "\" \"A\""
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
//        sql += " ORDER BY \"location_id\" ASC, \"start_date\" ASC, \"start_time\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        TsBean regRead = null;
        ArrayList<TsBean> arrayTmp = new ArrayList<TsBean>();
        //////////////////////////////////////////////
        try {

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
		regRead.setTs_RS_quantity( rs.getLong("ncp") );  // ncp
		
		if ( isConvertLocalTime ) {
			try {
				TimeZone to = TimeZone.getDefault();
				Date date = new SimpleDateFormat("yyyy-MM-dd HHmm").parse( regRead.getTs_start_date() + " " + regRead.getTs_start_time() );
				date = Subrutinas.cvtFec_mills__date( TIME_Utility.toLocalTime(date.getTime(), to) );
//				System.out.println( regRead.getTs_start_date() + " " + regRead.getTs_start_time() + " --> " + date.toString() );
				regRead.setTs_start_date( Subrutinas.getFecha_aaaa_mm_dd(date) );
				regRead.setTs_start_time( Subrutinas.getHora_HHMMSS(date).substring(0,4) );
			} catch (ParseException e) {;}
		}

                        arrayTmp.add( regRead );

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

}
