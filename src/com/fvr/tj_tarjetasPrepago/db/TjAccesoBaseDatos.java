package com.fvr.tj_tarjetasPrepago.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.tj_tarjetasPrepago.bean.TjBean;
import com.fvr.tj_tarjetasPrepago.bean.TjBeanFiltro;
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


public class TjAccesoBaseDatos {
    public String tabla   = "T_TJ_tarjetasPrepago";
    public String lf_UPD  = "T_TJ_tarjetasPrepago";
    public String lf_RTV  = "V_TJ_RTV_tarjetasPrepago";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public TjAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// tj_tarjetasPrepago:
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
    public void tj_crtObj(BDConexion bd, TjBean registro) throws StExcepcion {

    	registro.setTj_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tj_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	tj_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	tj_getParFS_RetCode(idOp);
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
		", \"card_id\"" + // card_id
		", \"balance_initial\"" + // balance_initial
		", \"balance_current\"" + // balance_current
		", \"last_sale_amount\"" + // last_sale_amount
		", \"last_sale_moment\"" + // last_sale_moment
		", \"qr_image_base64\"" + // qr_image_base64
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getTj_sincro() + "'" + // sincro
		", '"  + registro.getTj_mark() + "'" + // mark
		", '"  + registro.getTj_is_deleted() + "'" + // is_deleted
		", '"  + registro.getTj_author() + "'" + // author
		", '"  + registro.getTj_card_id() + "'" + // card_id
		", "  + registro.getTj_balance_initial() + "" + // balance_initial
		", "  + registro.getTj_balance_current() + "" + // balance_current
		", "  + registro.getTj_last_sale_amount() + "" + // last_sale_amount
		", '"  + registro.getTj_last_sale_moment() + "'" + // last_sale_moment
		", '"  + registro.getTj_qr_image_base64() + "'" + // qr_image_base64
		", '"  + registro.getTj_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void tj_chgObj(BDConexion bd, TjBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tj_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	tj_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	tj_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getTj_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getTj_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getTj_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getTj_author() + "'" + // author
		", \"card_id\" = '"  + registro.getTj_card_id() + "'" + // card_id
		", \"balance_initial\" = "  + registro.getTj_balance_initial() + "" + // balance_initial
		", \"balance_current\" = "  + registro.getTj_balance_current() + "" + // balance_current
		", \"last_sale_amount\" = "  + registro.getTj_last_sale_amount() + "" + // last_sale_amount
		", \"last_sale_moment\" = '"  + registro.getTj_last_sale_moment() + "'" + // last_sale_moment
		", \"qr_image_base64\" = '"  + registro.getTj_qr_image_base64() + "'" + // qr_image_base64
		", \"json\" = '"  + registro.getTj_json() + "'" + // json
                " WHERE " + 
		"  \"card_id\" = '" + registro.getTj_card_id() + "'" + // card_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void tj_dltObj(BDConexion bd, TjBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tj_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	tj_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	tj_getParFS_RetCode(idOp);
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
		"  \"card_id\" = '" + registro.getTj_card_id() + "'" + // card_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public TjBean   tj_getRcd(BDConexion dataBase, TjBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tj_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	tj_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return tj_getParFS_GET(idOp);
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
		"  \"card_id\" = '" + registro.getTj_card_id() + "'" + // card_id
                ""
                ;
        ResultSet rs = null;
        TjBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new TjBean();
                
		regRead.setTj_sincro( rs.getString("sincro") ); regRead.setTj_sincro( (regRead.getTj_sincro() == null)?"":regRead.getTj_sincro().trim() ); // sincro
		regRead.setTj_mark( rs.getString("mark") ); regRead.setTj_mark( (regRead.getTj_mark() == null)?"":regRead.getTj_mark().trim() ); // mark
		regRead.setTj_is_deleted( rs.getString("is_deleted") ); regRead.setTj_is_deleted( (regRead.getTj_is_deleted() == null)?"":regRead.getTj_is_deleted().trim() ); // is_deleted
		regRead.setTj_author( rs.getString("author") ); regRead.setTj_author( (regRead.getTj_author() == null)?"":regRead.getTj_author().trim() ); // author
		regRead.setTj_card_id( rs.getString("card_id") ); regRead.setTj_card_id( (regRead.getTj_card_id() == null)?"":regRead.getTj_card_id().trim() ); // card_id
		regRead.setTj_balance_initial( rs.getDouble("balance_initial") );  // balance_initial
		regRead.setTj_balance_current( rs.getDouble("balance_current") );  // balance_current
		regRead.setTj_last_sale_amount( rs.getDouble("last_sale_amount") );  // last_sale_amount
		regRead.setTj_last_sale_moment( rs.getString("last_sale_moment") ); regRead.setTj_last_sale_moment( (regRead.getTj_last_sale_moment() == null)?"":regRead.getTj_last_sale_moment().trim() ); // last_sale_moment
		regRead.setTj_qr_image_base64( rs.getString("qr_image_base64") ); regRead.setTj_qr_image_base64( (regRead.getTj_qr_image_base64() == null)?"":regRead.getTj_qr_image_base64().trim() ); // qr_image_base64
		regRead.setTj_json( rs.getString("json") ); regRead.setTj_json( (regRead.getTj_json() == null)?"":regRead.getTj_json().trim() ); // json
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
    public TjBean[] tj_getSeq(BDConexion dataBase, ConfigPantalla extCfg, TjBeanFiltro rst ) throws StExcepcion {
        TjBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tj_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            tj_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return tj_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTj_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTj_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTj_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTj_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTj_card_id(),"card_id",sqlWhere);   // card_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTj_last_sale_moment(),"last_sale_moment",sqlWhere);   // last_sale_moment
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTj_qr_image_base64(),"qr_image_base64",sqlWhere);   // qr_image_base64
	sqlWhere = fltOper.getCHAR_LIKE(rst.getTj_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"card_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        TjBean regRead = null;
        ArrayList<TjBean> arrayTmp = new ArrayList<TjBean>();
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
                        regRead = new TjBean();
                        
		regRead.setTj_sincro( rs.getString("sincro") ); regRead.setTj_sincro( (regRead.getTj_sincro() == null)?"":regRead.getTj_sincro().trim() ); // sincro
		regRead.setTj_mark( rs.getString("mark") ); regRead.setTj_mark( (regRead.getTj_mark() == null)?"":regRead.getTj_mark().trim() ); // mark
		regRead.setTj_is_deleted( rs.getString("is_deleted") ); regRead.setTj_is_deleted( (regRead.getTj_is_deleted() == null)?"":regRead.getTj_is_deleted().trim() ); // is_deleted
		regRead.setTj_author( rs.getString("author") ); regRead.setTj_author( (regRead.getTj_author() == null)?"":regRead.getTj_author().trim() ); // author
		regRead.setTj_card_id( rs.getString("card_id") ); regRead.setTj_card_id( (regRead.getTj_card_id() == null)?"":regRead.getTj_card_id().trim() ); // card_id
		regRead.setTj_balance_initial( rs.getDouble("balance_initial") );  // balance_initial
		regRead.setTj_balance_current( rs.getDouble("balance_current") );  // balance_current
		regRead.setTj_last_sale_amount( rs.getDouble("last_sale_amount") );  // last_sale_amount
		regRead.setTj_last_sale_moment( rs.getString("last_sale_moment") ); regRead.setTj_last_sale_moment( (regRead.getTj_last_sale_moment() == null)?"":regRead.getTj_last_sale_moment().trim() ); // last_sale_moment
		regRead.setTj_qr_image_base64( rs.getString("qr_image_base64") ); regRead.setTj_qr_image_base64( (regRead.getTj_qr_image_base64() == null)?"":regRead.getTj_qr_image_base64().trim() ); // qr_image_base64
		regRead.setTj_json( rs.getString("json") ); regRead.setTj_json( (regRead.getTj_json() == null)?"":regRead.getTj_json().trim() ); // json
                        
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
        filasRecuperadas = new TjBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "card_id" + "</strong></td>";  // card_id
				s += "<td><strong style='color:darkblue;'>" + "balance_initial" + "</strong></td>";  // balance_initial
				s += "<td><strong style='color:darkblue;'>" + "balance_current" + "</strong></td>";  // balance_current
				s += "<td><strong style='color:darkblue;'>" + "last_sale_amount" + "</strong></td>";  // last_sale_amount
				s += "<td><strong style='color:darkblue;'>" + "last_sale_moment" + "</strong></td>";  // last_sale_moment
				s += "<td><strong style='color:darkblue;'>" + "qr_image_base64" + "</strong></td>";  // qr_image_base64
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
    protected void getSeq_Sub_ExportMid(TjBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getTj_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getTj_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getTj_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getTj_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getTj_card_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // card_id
				s += "<td>" + new Double(registro.getTj_balance_initial()).toString() + "</td>";  // balance_initial
				s += "<td>" + new Double(registro.getTj_balance_current()).toString() + "</td>";  // balance_current
				s += "<td>" + new Double(registro.getTj_last_sale_amount()).toString() + "</td>";  // last_sale_amount
				tmp = registro.getTj_last_sale_moment();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // last_sale_moment
				tmp = registro.getTj_qr_image_base64();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // qr_image_base64
				tmp = registro.getTj_json();
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
    protected void     tj_putParFS_bean( final String idOp, TjBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     tj_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, TjBeanFiltro rst ) throws StExcepcion {
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

    protected void     tj_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected TjBean   tj_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	TjBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        tj_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new TjBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected TjBean[] tj_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	TjBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        tj_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new TjBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new TjBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new TjBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( TjBean[] lista ) {
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
	public TjBean[] json2beanArray(JSONObject jsonObject) {
		TjBean[] resultado = null;

		ArrayList<TjBean> arrayTmp = new ArrayList<TjBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					TjBean registro = new TjBean();
					
				registro.setTj_sincro( jsonReg.getString(0) );	// sincro
				registro.setTj_mark( jsonReg.getString(1) );	// mark
				registro.setTj_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setTj_author( jsonReg.getString(3) );	// author
				registro.setTj_card_id( jsonReg.getString(4) );	// card_id
				registro.setTj_balance_initial( jsonReg.getDouble(5) );	// balance_initial
				registro.setTj_balance_current( jsonReg.getDouble(6) );	// balance_current
				registro.setTj_last_sale_amount( jsonReg.getDouble(7) );	// last_sale_amount
				registro.setTj_last_sale_moment( jsonReg.getString(8) );	// last_sale_moment
				registro.setTj_qr_image_base64( jsonReg.getString(9) );	// qr_image_base64
				registro.setTj_json( jsonReg.getString(10) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new TjBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
