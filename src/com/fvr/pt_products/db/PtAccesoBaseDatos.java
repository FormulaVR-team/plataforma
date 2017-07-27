package com.fvr.pt_products.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.pt_products.bean.PtBean;
import com.fvr.pt_products.bean.PtBeanFiltro;
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


public class PtAccesoBaseDatos {
    public String tabla   = "T_PT_products";
    public String lf_UPD  = "T_PT_products";
    public String lf_RTV  = "V_PT_RTV_products";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public PtAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// pt_products:
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
    public void pt_crtObj(BDConexion bd, PtBean registro) throws StExcepcion {

    	registro.setPt_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pt_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pt_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pt_getParFS_RetCode(idOp);
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
		", \"product_id\"" + // product_id
		", \"name\"" + // name
		", \"whoCanSelect_AFU\"" + // whoCanSelect_AFU
		", \"deadline\"" + // deadline
		", \"isPercent\"" + // isPercent
		", \"amount\"" + // amount
		", \"currency\"" + // currency
		", \"duration_minutes\"" + // duration_minutes
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getPt_sincro() + "'" + // sincro
		", '"  + registro.getPt_mark() + "'" + // mark
		", '"  + registro.getPt_is_deleted() + "'" + // is_deleted
		", '"  + registro.getPt_author() + "'" + // author
		", '"  + registro.getPt_product_id() + "'" + // product_id
		", '"  + registro.getPt_name() + "'" + // name
		", '"  + registro.getPt_whoCanSelect_AFU() + "'" + // whoCanSelect_AFU
		", '"  + registro.getPt_deadline() + "'" + // deadline
		", '"  + registro.getPt_isPercent() + "'" + // isPercent
		", "  + registro.getPt_amount() + "" + // amount
		", '"  + registro.getPt_currency() + "'" + // currency
		", "  + registro.getPt_duration_minutes() + "" + // duration_minutes
		", '"  + registro.getPt_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void pt_chgObj(BDConexion bd, PtBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pt_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pt_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pt_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getPt_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getPt_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getPt_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getPt_author() + "'" + // author
		", \"product_id\" = '"  + registro.getPt_product_id() + "'" + // product_id
		", \"name\" = '"  + registro.getPt_name() + "'" + // name
		", \"whoCanSelect_AFU\" = '"  + registro.getPt_whoCanSelect_AFU() + "'" + // whoCanSelect_AFU
		", \"deadline\" = '"  + registro.getPt_deadline() + "'" + // deadline
		", \"isPercent\" = '"  + registro.getPt_isPercent() + "'" + // isPercent
		", \"amount\" = "  + registro.getPt_amount() + "" + // amount
		", \"currency\" = '"  + registro.getPt_currency() + "'" + // currency
		", \"duration_minutes\" = "  + registro.getPt_duration_minutes() + "" + // duration_minutes
		", \"json\" = '"  + registro.getPt_json() + "'" + // json
                " WHERE " + 
		"  \"product_id\" = '" + registro.getPt_product_id() + "'" + // product_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void pt_dltObj(BDConexion bd, PtBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pt_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pt_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pt_getParFS_RetCode(idOp);
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
		"  \"product_id\" = '" + registro.getPt_product_id() + "'" + // product_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public PtBean   pt_getRcd(BDConexion dataBase, PtBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pt_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pt_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return pt_getParFS_GET(idOp);
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
		"  \"product_id\" = '" + registro.getPt_product_id() + "'" + // product_id
                ""
                ;
        ResultSet rs = null;
        PtBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new PtBean();
                
		regRead.setPt_sincro( rs.getString("sincro") ); regRead.setPt_sincro( (regRead.getPt_sincro() == null)?"":regRead.getPt_sincro().trim() ); // sincro
		regRead.setPt_mark( rs.getString("mark") ); regRead.setPt_mark( (regRead.getPt_mark() == null)?"":regRead.getPt_mark().trim() ); // mark
		regRead.setPt_is_deleted( rs.getString("is_deleted") ); regRead.setPt_is_deleted( (regRead.getPt_is_deleted() == null)?"":regRead.getPt_is_deleted().trim() ); // is_deleted
		regRead.setPt_author( rs.getString("author") ); regRead.setPt_author( (regRead.getPt_author() == null)?"":regRead.getPt_author().trim() ); // author
		regRead.setPt_product_id( rs.getString("product_id") ); regRead.setPt_product_id( (regRead.getPt_product_id() == null)?"":regRead.getPt_product_id().trim() ); // product_id
		regRead.setPt_name( rs.getString("name") ); regRead.setPt_name( (regRead.getPt_name() == null)?"":regRead.getPt_name().trim() ); // name
		regRead.setPt_whoCanSelect_AFU( rs.getString("whoCanSelect_AFU") ); regRead.setPt_whoCanSelect_AFU( (regRead.getPt_whoCanSelect_AFU() == null)?"":regRead.getPt_whoCanSelect_AFU().trim() ); // whoCanSelect_AFU
		regRead.setPt_deadline( rs.getString("deadline") ); regRead.setPt_deadline( (regRead.getPt_deadline() == null)?"":regRead.getPt_deadline().trim() ); // deadline
		regRead.setPt_isPercent( rs.getString("isPercent") ); regRead.setPt_isPercent( (regRead.getPt_isPercent() == null)?"":regRead.getPt_isPercent().trim() ); // isPercent
		regRead.setPt_amount( rs.getDouble("amount") );  // amount
		regRead.setPt_currency( rs.getString("currency") ); regRead.setPt_currency( (regRead.getPt_currency() == null)?"":regRead.getPt_currency().trim() ); // currency
		regRead.setPt_duration_minutes( rs.getLong("duration_minutes") );  // duration_minutes
		regRead.setPt_json( rs.getString("json") ); regRead.setPt_json( (regRead.getPt_json() == null)?"":regRead.getPt_json().trim() ); // json
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
    public PtBean[] pt_getSeq(BDConexion dataBase, ConfigPantalla extCfg, PtBeanFiltro rst ) throws StExcepcion {
        PtBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pt_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            pt_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return pt_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_product_id(),"product_id",sqlWhere);   // product_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_name(),"name",sqlWhere);   // name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_whoCanSelect_AFU(),"whoCanSelect_AFU",sqlWhere);   // whoCanSelect_AFU
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_deadline(),"deadline",sqlWhere);   // deadline
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_isPercent(),"isPercent",sqlWhere);   // isPercent
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_currency(),"currency",sqlWhere);   // currency
	sqlWhere = fltOper.getNUM_EQ(rst.getPt_duration_minutes(),"duration_minutes",sqlWhere);   // duration_minutes
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPt_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"product_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        PtBean regRead = null;
        ArrayList<PtBean> arrayTmp = new ArrayList<PtBean>();
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
                        regRead = new PtBean();
                        
		regRead.setPt_sincro( rs.getString("sincro") ); regRead.setPt_sincro( (regRead.getPt_sincro() == null)?"":regRead.getPt_sincro().trim() ); // sincro
		regRead.setPt_mark( rs.getString("mark") ); regRead.setPt_mark( (regRead.getPt_mark() == null)?"":regRead.getPt_mark().trim() ); // mark
		regRead.setPt_is_deleted( rs.getString("is_deleted") ); regRead.setPt_is_deleted( (regRead.getPt_is_deleted() == null)?"":regRead.getPt_is_deleted().trim() ); // is_deleted
		regRead.setPt_author( rs.getString("author") ); regRead.setPt_author( (regRead.getPt_author() == null)?"":regRead.getPt_author().trim() ); // author
		regRead.setPt_product_id( rs.getString("product_id") ); regRead.setPt_product_id( (regRead.getPt_product_id() == null)?"":regRead.getPt_product_id().trim() ); // product_id
		regRead.setPt_name( rs.getString("name") ); regRead.setPt_name( (regRead.getPt_name() == null)?"":regRead.getPt_name().trim() ); // name
		regRead.setPt_whoCanSelect_AFU( rs.getString("whoCanSelect_AFU") ); regRead.setPt_whoCanSelect_AFU( (regRead.getPt_whoCanSelect_AFU() == null)?"":regRead.getPt_whoCanSelect_AFU().trim() ); // whoCanSelect_AFU
		regRead.setPt_deadline( rs.getString("deadline") ); regRead.setPt_deadline( (regRead.getPt_deadline() == null)?"":regRead.getPt_deadline().trim() ); // deadline
		regRead.setPt_isPercent( rs.getString("isPercent") ); regRead.setPt_isPercent( (regRead.getPt_isPercent() == null)?"":regRead.getPt_isPercent().trim() ); // isPercent
		regRead.setPt_amount( rs.getDouble("amount") );  // amount
		regRead.setPt_currency( rs.getString("currency") ); regRead.setPt_currency( (regRead.getPt_currency() == null)?"":regRead.getPt_currency().trim() ); // currency
		regRead.setPt_duration_minutes( rs.getLong("duration_minutes") );  // duration_minutes
		regRead.setPt_json( rs.getString("json") ); regRead.setPt_json( (regRead.getPt_json() == null)?"":regRead.getPt_json().trim() ); // json
                        
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
        filasRecuperadas = new PtBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "product_id" + "</strong></td>";  // product_id
				s += "<td><strong style='color:darkblue;'>" + "name" + "</strong></td>";  // name
				s += "<td><strong style='color:darkblue;'>" + "whoCanSelect_AFU" + "</strong></td>";  // whoCanSelect_AFU
				s += "<td><strong style='color:darkblue;'>" + "deadline" + "</strong></td>";  // deadline
				s += "<td><strong style='color:darkblue;'>" + "isPercent" + "</strong></td>";  // isPercent
				s += "<td><strong style='color:darkblue;'>" + "amount" + "</strong></td>";  // amount
				s += "<td><strong style='color:darkblue;'>" + "currency" + "</strong></td>";  // currency
				s += "<td><strong style='color:darkblue;'>" + "duration_minutes" + "</strong></td>";  // duration_minutes
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
    protected void getSeq_Sub_ExportMid(PtBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getPt_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getPt_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getPt_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getPt_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getPt_product_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // product_id
				tmp = registro.getPt_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // name
				tmp = registro.getPt_whoCanSelect_AFU();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // whoCanSelect_AFU
				tmp = registro.getPt_deadline();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // deadline
				tmp = registro.getPt_isPercent();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // isPercent
				s += "<td>" + new Double(registro.getPt_amount()).toString() + "</td>";  // amount
				tmp = registro.getPt_currency();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // currency
				s += "<td>" + new Long(registro.getPt_duration_minutes()).toString() + "</td>";  // duration_minutes
				tmp = registro.getPt_json();
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
    protected void     pt_putParFS_bean( final String idOp, PtBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     pt_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, PtBeanFiltro rst ) throws StExcepcion {
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

    protected void     pt_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected PtBean   pt_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	PtBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        pt_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new PtBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected PtBean[] pt_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	PtBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        pt_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new PtBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new PtBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new PtBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( PtBean[] lista ) {
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
	public PtBean[] json2beanArray(JSONObject jsonObject) {
		PtBean[] resultado = null;

		ArrayList<PtBean> arrayTmp = new ArrayList<PtBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					PtBean registro = new PtBean();
					
				registro.setPt_sincro( jsonReg.getString(0) );	// sincro
				registro.setPt_mark( jsonReg.getString(1) );	// mark
				registro.setPt_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setPt_author( jsonReg.getString(3) );	// author
				registro.setPt_product_id( jsonReg.getString(4) );	// product_id
				registro.setPt_name( jsonReg.getString(5) );	// name
				registro.setPt_whoCanSelect_AFU( jsonReg.getString(6) );	// whoCanSelect_AFU
				registro.setPt_deadline( jsonReg.getString(7) );	// deadline
				registro.setPt_isPercent( jsonReg.getString(8) );	// isPercent
				registro.setPt_amount( jsonReg.getDouble(9) );	// amount
				registro.setPt_currency( jsonReg.getString(10) );	// currency
				registro.setPt_duration_minutes( jsonReg.getLong(11) );	// duration_minutes
				registro.setPt_json( jsonReg.getString(12) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new PtBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
