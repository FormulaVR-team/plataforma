package com.fvr.pr_promos.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.pr_promos.bean.PrBean;
import com.fvr.pr_promos.bean.PrBeanFiltro;
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


public class PrAccesoBaseDatos {
    public String tabla   = "T_PR_promos";
    public String lf_UPD  = "T_PR_promos";
    public String lf_RTV  = "V_PR_RTV_promos";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public PrAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// pr_promos:
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
    public void pr_crtObj(BDConexion bd, PrBean registro) throws StExcepcion {

    	registro.setPr_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pr_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pr_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pr_getParFS_RetCode(idOp);
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
		", \"location_id\"" + // location_id
		", \"product_id\"" + // product_id
		", \"product_id_promo\"" + // product_id_promo
		", \"deadline\"" + // deadline
		", \"min_quantity\"" + // min_quantity
                "  ) VALUES ( " + 
		"  '"  + registro.getPr_sincro() + "'" + // sincro
		", '"  + registro.getPr_mark() + "'" + // mark
		", '"  + registro.getPr_is_deleted() + "'" + // is_deleted
		", '"  + registro.getPr_author() + "'" + // author
		", '"  + registro.getPr_location_id() + "'" + // location_id
		", '"  + registro.getPr_product_id() + "'" + // product_id
		", '"  + registro.getPr_product_id_promo() + "'" + // product_id_promo
		", '"  + registro.getPr_deadline() + "'" + // deadline
		", "  + registro.getPr_min_quantity() + "" + // min_quantity 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void pr_chgObj(BDConexion bd, PrBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pr_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pr_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pr_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getPr_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getPr_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getPr_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getPr_author() + "'" + // author
		", \"location_id\" = '"  + registro.getPr_location_id() + "'" + // location_id
		", \"product_id\" = '"  + registro.getPr_product_id() + "'" + // product_id
		", \"product_id_promo\" = '"  + registro.getPr_product_id_promo() + "'" + // product_id_promo
		", \"deadline\" = '"  + registro.getPr_deadline() + "'" + // deadline
		", \"min_quantity\" = "  + registro.getPr_min_quantity() + "" + // min_quantity
                " WHERE " + 
		"  \"location_id\" = '" + registro.getPr_location_id() + "'" + // location_id
		"  AND \"product_id\" = '" + registro.getPr_product_id() + "'" + // product_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void pr_dltObj(BDConexion bd, PrBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pr_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pr_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	pr_getParFS_RetCode(idOp);
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
		"  \"location_id\" = '" + registro.getPr_location_id() + "'" + // location_id
		"  AND \"product_id\" = '" + registro.getPr_product_id() + "'" + // product_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public PrBean   pr_getRcd(BDConexion dataBase, PrBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pr_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	pr_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return pr_getParFS_GET(idOp);
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
		"  \"location_id\" = '" + registro.getPr_location_id() + "'" + // location_id
		"  AND \"product_id\" = '" + registro.getPr_product_id() + "'" + // product_id
                ""
                ;
        ResultSet rs = null;
        PrBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new PrBean();
                
		regRead.setPr_sincro( rs.getString("sincro") ); regRead.setPr_sincro( (regRead.getPr_sincro() == null)?"":regRead.getPr_sincro().trim() ); // sincro
		regRead.setPr_mark( rs.getString("mark") ); regRead.setPr_mark( (regRead.getPr_mark() == null)?"":regRead.getPr_mark().trim() ); // mark
		regRead.setPr_is_deleted( rs.getString("is_deleted") ); regRead.setPr_is_deleted( (regRead.getPr_is_deleted() == null)?"":regRead.getPr_is_deleted().trim() ); // is_deleted
		regRead.setPr_author( rs.getString("author") ); regRead.setPr_author( (regRead.getPr_author() == null)?"":regRead.getPr_author().trim() ); // author
		regRead.setPr_location_id( rs.getString("location_id") ); regRead.setPr_location_id( (regRead.getPr_location_id() == null)?"":regRead.getPr_location_id().trim() ); // location_id
		regRead.setPr_LO_name( rs.getString("LO_name") ); regRead.setPr_LO_name( (regRead.getPr_LO_name() == null)?"":regRead.getPr_LO_name().trim() ); // LO_name
		regRead.setPr_product_id( rs.getString("product_id") ); regRead.setPr_product_id( (regRead.getPr_product_id() == null)?"":regRead.getPr_product_id().trim() ); // product_id
		regRead.setPr_PT_name( rs.getString("PT_name") ); regRead.setPr_PT_name( (regRead.getPr_PT_name() == null)?"":regRead.getPr_PT_name().trim() ); // PT_name
		regRead.setPr_product_id_promo( rs.getString("product_id_promo") ); regRead.setPr_product_id_promo( (regRead.getPr_product_id_promo() == null)?"":regRead.getPr_product_id_promo().trim() ); // product_id_promo
		regRead.setPr_PT_name_promo( rs.getString("PT_name_promo") ); regRead.setPr_PT_name_promo( (regRead.getPr_PT_name_promo() == null)?"":regRead.getPr_PT_name_promo().trim() ); // PT_name_promo
		regRead.setPr_deadline( rs.getString("deadline") ); regRead.setPr_deadline( (regRead.getPr_deadline() == null)?"":regRead.getPr_deadline().trim() ); // deadline
		regRead.setPr_min_quantity( rs.getLong("min_quantity") );  // min_quantity
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
    public PrBean[] pr_getSeq(BDConexion dataBase, ConfigPantalla extCfg, PrBeanFiltro rst ) throws StExcepcion {
        PrBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_pr_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            pr_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return pr_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_location_id(),"location_id",sqlWhere);   // location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_LO_name(),"LO_name",sqlWhere);   // LO_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_product_id(),"product_id",sqlWhere);   // product_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_PT_name(),"PT_name",sqlWhere);   // PT_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_product_id_promo(),"product_id_promo",sqlWhere);   // product_id_promo
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_PT_name_promo(),"PT_name_promo",sqlWhere);   // PT_name_promo
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPr_deadline(),"deadline",sqlWhere);   // deadline
	sqlWhere = fltOper.getNUM_EQ(rst.getPr_min_quantity(),"min_quantity",sqlWhere);   // min_quantity
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"location_id\" ASC, \"product_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        PrBean regRead = null;
        ArrayList<PrBean> arrayTmp = new ArrayList<PrBean>();
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
                        regRead = new PrBean();
                        
		regRead.setPr_sincro( rs.getString("sincro") ); regRead.setPr_sincro( (regRead.getPr_sincro() == null)?"":regRead.getPr_sincro().trim() ); // sincro
		regRead.setPr_mark( rs.getString("mark") ); regRead.setPr_mark( (regRead.getPr_mark() == null)?"":regRead.getPr_mark().trim() ); // mark
		regRead.setPr_is_deleted( rs.getString("is_deleted") ); regRead.setPr_is_deleted( (regRead.getPr_is_deleted() == null)?"":regRead.getPr_is_deleted().trim() ); // is_deleted
		regRead.setPr_author( rs.getString("author") ); regRead.setPr_author( (regRead.getPr_author() == null)?"":regRead.getPr_author().trim() ); // author
		regRead.setPr_location_id( rs.getString("location_id") ); regRead.setPr_location_id( (regRead.getPr_location_id() == null)?"":regRead.getPr_location_id().trim() ); // location_id
		regRead.setPr_LO_name( rs.getString("LO_name") ); regRead.setPr_LO_name( (regRead.getPr_LO_name() == null)?"":regRead.getPr_LO_name().trim() ); // LO_name
		regRead.setPr_product_id( rs.getString("product_id") ); regRead.setPr_product_id( (regRead.getPr_product_id() == null)?"":regRead.getPr_product_id().trim() ); // product_id
		regRead.setPr_PT_name( rs.getString("PT_name") ); regRead.setPr_PT_name( (regRead.getPr_PT_name() == null)?"":regRead.getPr_PT_name().trim() ); // PT_name
		regRead.setPr_product_id_promo( rs.getString("product_id_promo") ); regRead.setPr_product_id_promo( (regRead.getPr_product_id_promo() == null)?"":regRead.getPr_product_id_promo().trim() ); // product_id_promo
		regRead.setPr_PT_name_promo( rs.getString("PT_name_promo") ); regRead.setPr_PT_name_promo( (regRead.getPr_PT_name_promo() == null)?"":regRead.getPr_PT_name_promo().trim() ); // PT_name_promo
		regRead.setPr_deadline( rs.getString("deadline") ); regRead.setPr_deadline( (regRead.getPr_deadline() == null)?"":regRead.getPr_deadline().trim() ); // deadline
		regRead.setPr_min_quantity( rs.getLong("min_quantity") );  // min_quantity
                        
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
        filasRecuperadas = new PrBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "location_id" + "</strong></td>";  // location_id
				s += "<td><strong style='color:darkblue;'>" + "LO_name" + "</strong></td>";  // LO_name
				s += "<td><strong style='color:darkblue;'>" + "product_id" + "</strong></td>";  // product_id
				s += "<td><strong style='color:darkblue;'>" + "PT_name" + "</strong></td>";  // PT_name
				s += "<td><strong style='color:darkblue;'>" + "product_id_promo" + "</strong></td>";  // product_id_promo
				s += "<td><strong style='color:darkblue;'>" + "PT_name_promo" + "</strong></td>";  // PT_name_promo
				s += "<td><strong style='color:darkblue;'>" + "deadline" + "</strong></td>";  // deadline
				s += "<td><strong style='color:darkblue;'>" + "min_quantity" + "</strong></td>";  // min_quantity
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
    protected void getSeq_Sub_ExportMid(PrBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getPr_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getPr_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getPr_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getPr_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getPr_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getPr_LO_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_name
				tmp = registro.getPr_product_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // product_id
				tmp = registro.getPr_PT_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_name
				tmp = registro.getPr_product_id_promo();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // product_id_promo
				tmp = registro.getPr_PT_name_promo();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PT_name_promo
				tmp = registro.getPr_deadline();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // deadline
				s += "<td>" + new Long(registro.getPr_min_quantity()).toString() + "</td>";  // min_quantity
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
    protected void     pr_putParFS_bean( final String idOp, PrBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     pr_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, PrBeanFiltro rst ) throws StExcepcion {
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

    protected void     pr_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected PrBean   pr_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	PrBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        pr_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new PrBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected PrBean[] pr_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	PrBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        pr_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new PrBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new PrBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new PrBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( PrBean[] lista ) {
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
	public PrBean[] json2beanArray(JSONObject jsonObject) {
		PrBean[] resultado = null;

		ArrayList<PrBean> arrayTmp = new ArrayList<PrBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					PrBean registro = new PrBean();
					
				registro.setPr_sincro( jsonReg.getString(0) );	// sincro
				registro.setPr_mark( jsonReg.getString(1) );	// mark
				registro.setPr_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setPr_author( jsonReg.getString(3) );	// author
				registro.setPr_location_id( jsonReg.getString(4) );	// location_id
				registro.setPr_LO_name( jsonReg.getString(5) );	// LO_name
				registro.setPr_product_id( jsonReg.getString(6) );	// product_id
				registro.setPr_PT_name( jsonReg.getString(7) );	// PT_name
				registro.setPr_product_id_promo( jsonReg.getString(8) );	// product_id_promo
				registro.setPr_PT_name_promo( jsonReg.getString(9) );	// PT_name_promo
				registro.setPr_deadline( jsonReg.getString(10) );	// deadline
				registro.setPr_min_quantity( jsonReg.getLong(11) );	// min_quantity
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new PrBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
