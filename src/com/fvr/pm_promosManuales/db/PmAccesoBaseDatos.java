package com.fvr.pm_promosManuales.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.pm_promosManuales.bean.PmBean;
import com.fvr.pm_promosManuales.bean.PmBeanFiltro;
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


public class PmAccesoBaseDatos {
    public String tabla   = "T_PM_promosManuales";
    public String lf_UPD  = "T_PM_promosManuales";
    public String lf_RTV  = "V_PM_RTV_promosManuales";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public PmAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// pm_promosManuales:
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
    public void pm_crtObj(BDConexion bd, PmBean registro) throws StExcepcion {

    	registro.setPm_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pm_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pm_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pm_getParFS_RetCode(idOp);
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
		", \"coupon_id\"" + // coupon_id
		", \"name\"" + // name
		", \"uses_per_user\"" + // uses_per_user
		", \"product_id\"" + // product_id
		", \"location_id\"" + // location_id
		", \"deadline\"" + // deadline
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getPm_sincro() + "'" + // sincro
		", '"  + registro.getPm_mark() + "'" + // mark
		", '"  + registro.getPm_is_deleted() + "'" + // is_deleted
		", '"  + registro.getPm_author() + "'" + // author
		", '"  + registro.getPm_coupon_id() + "'" + // coupon_id
		", '"  + registro.getPm_name() + "'" + // name
		", "  + registro.getPm_uses_per_user() + "" + // uses_per_user
		", '"  + registro.getPm_product_id() + "'" + // product_id
		", '"  + registro.getPm_location_id() + "'" + // location_id
		", '"  + registro.getPm_deadline() + "'" + // deadline
		", '"  + registro.getPm_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void pm_chgObj(BDConexion bd, PmBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pm_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pm_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pm_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getPm_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getPm_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getPm_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getPm_author() + "'" + // author
		", \"coupon_id\" = '"  + registro.getPm_coupon_id() + "'" + // coupon_id
		", \"name\" = '"  + registro.getPm_name() + "'" + // name
		", \"uses_per_user\" = "  + registro.getPm_uses_per_user() + "" + // uses_per_user
		", \"product_id\" = '"  + registro.getPm_product_id() + "'" + // product_id
		", \"location_id\" = '"  + registro.getPm_location_id() + "'" + // location_id
		", \"deadline\" = '"  + registro.getPm_deadline() + "'" + // deadline
		", \"json\" = '"  + registro.getPm_json() + "'" + // json
                " WHERE " + 
		"  \"coupon_id\" = '" + registro.getPm_coupon_id() + "'" + // coupon_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void pm_dltObj(BDConexion bd, PmBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pm_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pm_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pm_getParFS_RetCode(idOp);
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
		"  \"coupon_id\" = '" + registro.getPm_coupon_id() + "'" + // coupon_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public PmBean   pm_getRcd(BDConexion dataBase, PmBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pm_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pm_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return pm_getParFS_GET(idOp);
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
		"  \"coupon_id\" = '" + registro.getPm_coupon_id() + "'" + // coupon_id
                ""
                ;
        ResultSet rs = null;
        PmBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new PmBean();
                
		regRead.setPm_sincro( rs.getString("sincro") ); regRead.setPm_sincro( (regRead.getPm_sincro() == null)?"":regRead.getPm_sincro().trim() ); // sincro
		regRead.setPm_mark( rs.getString("mark") ); regRead.setPm_mark( (regRead.getPm_mark() == null)?"":regRead.getPm_mark().trim() ); // mark
		regRead.setPm_is_deleted( rs.getString("is_deleted") ); regRead.setPm_is_deleted( (regRead.getPm_is_deleted() == null)?"":regRead.getPm_is_deleted().trim() ); // is_deleted
		regRead.setPm_author( rs.getString("author") ); regRead.setPm_author( (regRead.getPm_author() == null)?"":regRead.getPm_author().trim() ); // author
		regRead.setPm_coupon_id( rs.getString("coupon_id") ); regRead.setPm_coupon_id( (regRead.getPm_coupon_id() == null)?"":regRead.getPm_coupon_id().trim() ); // coupon_id
		regRead.setPm_name( rs.getString("name") ); regRead.setPm_name( (regRead.getPm_name() == null)?"":regRead.getPm_name().trim() ); // name
		regRead.setPm_uses_per_user( rs.getLong("uses_per_user") );  // uses_per_user
		regRead.setPm_product_id( rs.getString("product_id") ); regRead.setPm_product_id( (regRead.getPm_product_id() == null)?"":regRead.getPm_product_id().trim() ); // product_id
		regRead.setPm_PT_name( rs.getString("PT_name") ); regRead.setPm_PT_name( (regRead.getPm_PT_name() == null)?"":regRead.getPm_PT_name().trim() ); // PT_name
		regRead.setPm_PT_whoCanSelect_AFU( rs.getString("PT_whoCanSelect_AFU") ); regRead.setPm_PT_whoCanSelect_AFU( (regRead.getPm_PT_whoCanSelect_AFU() == null)?"":regRead.getPm_PT_whoCanSelect_AFU().trim() ); // PT_whoCanSelect_AFU
		regRead.setPm_PT_deadline( rs.getString("PT_deadline") ); regRead.setPm_PT_deadline( (regRead.getPm_PT_deadline() == null)?"":regRead.getPm_PT_deadline().trim() ); // PT_deadline
		regRead.setPm_location_id( rs.getString("location_id") ); regRead.setPm_location_id( (regRead.getPm_location_id() == null)?"":regRead.getPm_location_id().trim() ); // location_id
		regRead.setPm_LO_name( rs.getString("LO_name") ); regRead.setPm_LO_name( (regRead.getPm_LO_name() == null)?"":regRead.getPm_LO_name().trim() ); // LO_name
		regRead.setPm_deadline( rs.getString("deadline") ); regRead.setPm_deadline( (regRead.getPm_deadline() == null)?"":regRead.getPm_deadline().trim() ); // deadline
		regRead.setPm_json( rs.getString("json") ); regRead.setPm_json( (regRead.getPm_json() == null)?"":regRead.getPm_json().trim() ); // json
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
    public PmBean[] pm_getSeq(BDConexion dataBase, ConfigPantalla extCfg, PmBeanFiltro rst ) throws StExcepcion {
        PmBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pm_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            pm_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return pm_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_coupon_id(),"coupon_id",sqlWhere);   // coupon_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_name(),"name",sqlWhere);   // name
	sqlWhere = fltOper.getNUM_EQ(rst.getPm_uses_per_user(),"uses_per_user",sqlWhere);   // uses_per_user
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_product_id(),"product_id",sqlWhere);   // product_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_PT_name(),"PT_name",sqlWhere);   // PT_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_PT_whoCanSelect_AFU(),"PT_whoCanSelect_AFU",sqlWhere);   // PT_whoCanSelect_AFU
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_PT_deadline(),"PT_deadline",sqlWhere);   // PT_deadline
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_location_id(),"location_id",sqlWhere);   // location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_LO_name(),"LO_name",sqlWhere);   // LO_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_deadline(),"deadline",sqlWhere);   // deadline
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPm_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"coupon_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        PmBean regRead = null;
        ArrayList<PmBean> arrayTmp = new ArrayList<PmBean>();
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
                        regRead = new PmBean();
                        
		regRead.setPm_sincro( rs.getString("sincro") ); regRead.setPm_sincro( (regRead.getPm_sincro() == null)?"":regRead.getPm_sincro().trim() ); // sincro
		regRead.setPm_mark( rs.getString("mark") ); regRead.setPm_mark( (regRead.getPm_mark() == null)?"":regRead.getPm_mark().trim() ); // mark
		regRead.setPm_is_deleted( rs.getString("is_deleted") ); regRead.setPm_is_deleted( (regRead.getPm_is_deleted() == null)?"":regRead.getPm_is_deleted().trim() ); // is_deleted
		regRead.setPm_author( rs.getString("author") ); regRead.setPm_author( (regRead.getPm_author() == null)?"":regRead.getPm_author().trim() ); // author
		regRead.setPm_coupon_id( rs.getString("coupon_id") ); regRead.setPm_coupon_id( (regRead.getPm_coupon_id() == null)?"":regRead.getPm_coupon_id().trim() ); // coupon_id
		regRead.setPm_name( rs.getString("name") ); regRead.setPm_name( (regRead.getPm_name() == null)?"":regRead.getPm_name().trim() ); // name
		regRead.setPm_uses_per_user( rs.getLong("uses_per_user") );  // uses_per_user
		regRead.setPm_product_id( rs.getString("product_id") ); regRead.setPm_product_id( (regRead.getPm_product_id() == null)?"":regRead.getPm_product_id().trim() ); // product_id
		regRead.setPm_PT_name( rs.getString("PT_name") ); regRead.setPm_PT_name( (regRead.getPm_PT_name() == null)?"":regRead.getPm_PT_name().trim() ); // PT_name
		regRead.setPm_PT_whoCanSelect_AFU( rs.getString("PT_whoCanSelect_AFU") ); regRead.setPm_PT_whoCanSelect_AFU( (regRead.getPm_PT_whoCanSelect_AFU() == null)?"":regRead.getPm_PT_whoCanSelect_AFU().trim() ); // PT_whoCanSelect_AFU
		regRead.setPm_PT_deadline( rs.getString("PT_deadline") ); regRead.setPm_PT_deadline( (regRead.getPm_PT_deadline() == null)?"":regRead.getPm_PT_deadline().trim() ); // PT_deadline
		regRead.setPm_location_id( rs.getString("location_id") ); regRead.setPm_location_id( (regRead.getPm_location_id() == null)?"":regRead.getPm_location_id().trim() ); // location_id
		regRead.setPm_LO_name( rs.getString("LO_name") ); regRead.setPm_LO_name( (regRead.getPm_LO_name() == null)?"":regRead.getPm_LO_name().trim() ); // LO_name
		regRead.setPm_deadline( rs.getString("deadline") ); regRead.setPm_deadline( (regRead.getPm_deadline() == null)?"":regRead.getPm_deadline().trim() ); // deadline
		regRead.setPm_json( rs.getString("json") ); regRead.setPm_json( (regRead.getPm_json() == null)?"":regRead.getPm_json().trim() ); // json
                        
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
        filasRecuperadas = new PmBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "coupon_id" + "</strong></td>";  // coupon_id
				s += "<td><strong style='color:darkblue;'>" + "name" + "</strong></td>";  // name
				s += "<td><strong style='color:darkblue;'>" + "uses_per_user" + "</strong></td>";  // uses_per_user
				s += "<td><strong style='color:darkblue;'>" + "product_id" + "</strong></td>";  // product_id
				s += "<td><strong style='color:darkblue;'>" + "PT_name" + "</strong></td>";  // PT_name
				s += "<td><strong style='color:darkblue;'>" + "PT_whoCanSelect_AFU" + "</strong></td>";  // PT_whoCanSelect_AFU
				s += "<td><strong style='color:darkblue;'>" + "PT_deadline" + "</strong></td>";  // PT_deadline
				s += "<td><strong style='color:darkblue;'>" + "location_id" + "</strong></td>";  // location_id
				s += "<td><strong style='color:darkblue;'>" + "LO_name" + "</strong></td>";  // LO_name
				s += "<td><strong style='color:darkblue;'>" + "deadline" + "</strong></td>";  // deadline
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
    protected void getSeq_Sub_ExportMid(PmBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getPm_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getPm_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getPm_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getPm_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getPm_coupon_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // coupon_id
				tmp = registro.getPm_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // name
				s += "<td>" + new Long(registro.getPm_uses_per_user()).toString() + "</td>";  // uses_per_user
				tmp = registro.getPm_product_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // product_id
				tmp = registro.getPm_PT_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_name
				tmp = registro.getPm_PT_whoCanSelect_AFU();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_whoCanSelect_AFU
				tmp = registro.getPm_PT_deadline();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_deadline
				tmp = registro.getPm_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getPm_LO_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_name
				tmp = registro.getPm_deadline();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // deadline
				tmp = registro.getPm_json();
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
    protected void     pm_putParFS_bean( final String idOp, PmBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     pm_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, PmBeanFiltro rst ) throws StExcepcion {
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

    protected void     pm_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected PmBean   pm_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	PmBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        pm_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new PmBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected PmBean[] pm_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	PmBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        pm_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new PmBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new PmBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new PmBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( PmBean[] lista ) {
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
	public PmBean[] json2beanArray(JSONObject jsonObject) {
		PmBean[] resultado = null;

		ArrayList<PmBean> arrayTmp = new ArrayList<PmBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					PmBean registro = new PmBean();
					
				registro.setPm_sincro( jsonReg.getString(0) );	// sincro
				registro.setPm_mark( jsonReg.getString(1) );	// mark
				registro.setPm_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setPm_author( jsonReg.getString(3) );	// author
				registro.setPm_coupon_id( jsonReg.getString(4) );	// coupon_id
				registro.setPm_name( jsonReg.getString(5) );	// name
				registro.setPm_uses_per_user( jsonReg.getLong(6) );	// uses_per_user
				registro.setPm_product_id( jsonReg.getString(7) );	// product_id
				registro.setPm_PT_name( jsonReg.getString(8) );	// PT_name
				registro.setPm_PT_whoCanSelect_AFU( jsonReg.getString(9) );	// PT_whoCanSelect_AFU
				registro.setPm_PT_deadline( jsonReg.getString(10) );	// PT_deadline
				registro.setPm_location_id( jsonReg.getString(11) );	// location_id
				registro.setPm_LO_name( jsonReg.getString(12) );	// LO_name
				registro.setPm_deadline( jsonReg.getString(13) );	// deadline
				registro.setPm_json( jsonReg.getString(14) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new PmBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
