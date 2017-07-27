package com.fvr.ps_countries.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.ps_countries.bean.PsBean;
import com.fvr.ps_countries.bean.PsBeanFiltro;
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


public class PsAccesoBaseDatos {
    public String tabla   = "T_PS_countries";
    public String lf_UPD  = "T_PS_countries";
    public String lf_RTV  = "V_PS_RTV_countries";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public PsAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// ps_countries:
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
    public void ps_crtObj(BDConexion bd, PsBean registro) throws StExcepcion {

    	registro.setPs_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ps_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ps_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ps_getParFS_RetCode(idOp);
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
		", \"country_id\"" + // country_id
		", \"name\"" + // name
		", \"alpha_2\"" + // alpha_2
		", \"alpha_3\"" + // alpha_3
		", \"flag_base64\"" + // flag_base64
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getPs_sincro() + "'" + // sincro
		", '"  + registro.getPs_mark() + "'" + // mark
		", '"  + registro.getPs_is_deleted() + "'" + // is_deleted
		", '"  + registro.getPs_author() + "'" + // author
		", "  + registro.getPs_country_id() + "" + // country_id
		", '"  + registro.getPs_name() + "'" + // name
		", '"  + registro.getPs_alpha_2() + "'" + // alpha_2
		", '"  + registro.getPs_alpha_3() + "'" + // alpha_3
		", '"  + registro.getPs_flag_base64() + "'" + // flag_base64
		", '"  + registro.getPs_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void ps_chgObj(BDConexion bd, PsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ps_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ps_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ps_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getPs_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getPs_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getPs_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getPs_author() + "'" + // author
		", \"country_id\" = "  + registro.getPs_country_id() + "" + // country_id
		", \"name\" = '"  + registro.getPs_name() + "'" + // name
		", \"alpha_2\" = '"  + registro.getPs_alpha_2() + "'" + // alpha_2
		", \"alpha_3\" = '"  + registro.getPs_alpha_3() + "'" + // alpha_3
		", \"flag_base64\" = '"  + registro.getPs_flag_base64() + "'" + // flag_base64
		", \"json\" = '"  + registro.getPs_json() + "'" + // json
                " WHERE " + 
		"  \"country_id\" = " + registro.getPs_country_id() + "" + // country_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void ps_dltObj(BDConexion bd, PsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ps_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ps_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	ps_getParFS_RetCode(idOp);
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
		"  \"country_id\" = " + registro.getPs_country_id() + "" + // country_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public PsBean   ps_getRcd(BDConexion dataBase, PsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ps_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	ps_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return ps_getParFS_GET(idOp);
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
		"  \"country_id\" = " + registro.getPs_country_id() + "" + // country_id
                ""
                ;
        ResultSet rs = null;
        PsBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new PsBean();
                
		regRead.setPs_sincro( rs.getString("sincro") ); regRead.setPs_sincro( (regRead.getPs_sincro() == null)?"":regRead.getPs_sincro().trim() ); // sincro
		regRead.setPs_mark( rs.getString("mark") ); regRead.setPs_mark( (regRead.getPs_mark() == null)?"":regRead.getPs_mark().trim() ); // mark
		regRead.setPs_is_deleted( rs.getString("is_deleted") ); regRead.setPs_is_deleted( (regRead.getPs_is_deleted() == null)?"":regRead.getPs_is_deleted().trim() ); // is_deleted
		regRead.setPs_author( rs.getString("author") ); regRead.setPs_author( (regRead.getPs_author() == null)?"":regRead.getPs_author().trim() ); // author
		regRead.setPs_country_id( rs.getLong("country_id") );  // country_id
		regRead.setPs_name( rs.getString("name") ); regRead.setPs_name( (regRead.getPs_name() == null)?"":regRead.getPs_name().trim() ); // name
		regRead.setPs_alpha_2( rs.getString("alpha_2") ); regRead.setPs_alpha_2( (regRead.getPs_alpha_2() == null)?"":regRead.getPs_alpha_2().trim() ); // alpha_2
		regRead.setPs_alpha_3( rs.getString("alpha_3") ); regRead.setPs_alpha_3( (regRead.getPs_alpha_3() == null)?"":regRead.getPs_alpha_3().trim() ); // alpha_3
		regRead.setPs_flag_base64( rs.getString("flag_base64") ); regRead.setPs_flag_base64( (regRead.getPs_flag_base64() == null)?"":regRead.getPs_flag_base64().trim() ); // flag_base64
		regRead.setPs_json( rs.getString("json") ); regRead.setPs_json( (regRead.getPs_json() == null)?"":regRead.getPs_json().trim() ); // json
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
    public PsBean[] ps_getSeq(BDConexion dataBase, ConfigPantalla extCfg, PsBeanFiltro rst ) throws StExcepcion {
        PsBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_ps_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            ps_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return ps_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPs_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_EQ(rst.getPs_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_EQ(rst.getPs_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_EQ(rst.getPs_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getNUM_EQ(rst.getPs_country_id(),"country_id",sqlWhere);   // country_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPs_name(),"name",sqlWhere);   // name
	sqlWhere = fltOper.getCHAR_EQ(rst.getPs_alpha_2(),"alpha_2",sqlWhere);   // alpha_2
	sqlWhere = fltOper.getCHAR_EQ(rst.getPs_alpha_3(),"alpha_3",sqlWhere);   // alpha_3
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPs_flag_base64(),"flag_base64",sqlWhere);   // flag_base64
	sqlWhere = fltOper.getCHAR_LIKE(rst.getPs_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"name\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        PsBean regRead = null;
        ArrayList<PsBean> arrayTmp = new ArrayList<PsBean>();
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
                        regRead = new PsBean();
                        
		regRead.setPs_sincro( rs.getString("sincro") ); regRead.setPs_sincro( (regRead.getPs_sincro() == null)?"":regRead.getPs_sincro().trim() ); // sincro
		regRead.setPs_mark( rs.getString("mark") ); regRead.setPs_mark( (regRead.getPs_mark() == null)?"":regRead.getPs_mark().trim() ); // mark
		regRead.setPs_is_deleted( rs.getString("is_deleted") ); regRead.setPs_is_deleted( (regRead.getPs_is_deleted() == null)?"":regRead.getPs_is_deleted().trim() ); // is_deleted
		regRead.setPs_author( rs.getString("author") ); regRead.setPs_author( (regRead.getPs_author() == null)?"":regRead.getPs_author().trim() ); // author
		regRead.setPs_country_id( rs.getLong("country_id") );  // country_id
		regRead.setPs_name( rs.getString("name") ); regRead.setPs_name( (regRead.getPs_name() == null)?"":regRead.getPs_name().trim() ); // name
		regRead.setPs_alpha_2( rs.getString("alpha_2") ); regRead.setPs_alpha_2( (regRead.getPs_alpha_2() == null)?"":regRead.getPs_alpha_2().trim() ); // alpha_2
		regRead.setPs_alpha_3( rs.getString("alpha_3") ); regRead.setPs_alpha_3( (regRead.getPs_alpha_3() == null)?"":regRead.getPs_alpha_3().trim() ); // alpha_3
		regRead.setPs_flag_base64( rs.getString("flag_base64") ); regRead.setPs_flag_base64( (regRead.getPs_flag_base64() == null)?"":regRead.getPs_flag_base64().trim() ); // flag_base64
		regRead.setPs_json( rs.getString("json") ); regRead.setPs_json( (regRead.getPs_json() == null)?"":regRead.getPs_json().trim() ); // json
                        
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
        filasRecuperadas = new PsBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "country_id" + "</strong></td>";  // country_id
				s += "<td><strong style='color:darkblue;'>" + "name" + "</strong></td>";  // name
				s += "<td><strong style='color:darkblue;'>" + "alpha_2" + "</strong></td>";  // alpha_2
				s += "<td><strong style='color:darkblue;'>" + "alpha_3" + "</strong></td>";  // alpha_3
				s += "<td><strong style='color:darkblue;'>" + "flag_base64" + "</strong></td>";  // flag_base64
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
    protected void getSeq_Sub_ExportMid(PsBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getPs_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getPs_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getPs_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getPs_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				s += "<td>" + new Long(registro.getPs_country_id()).toString() + "</td>";  // country_id
				tmp = registro.getPs_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // name
				tmp = registro.getPs_alpha_2();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // alpha_2
				tmp = registro.getPs_alpha_3();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // alpha_3
				tmp = registro.getPs_flag_base64();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // flag_base64
				tmp = registro.getPs_json();
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
    protected void     ps_putParFS_bean( final String idOp, PsBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     ps_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, PsBeanFiltro rst ) throws StExcepcion {
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

    protected void     ps_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected PsBean   ps_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	PsBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        ps_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new PsBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected PsBean[] ps_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	PsBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        ps_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new PsBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new PsBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new PsBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( PsBean[] lista ) {
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
	public PsBean[] json2beanArray(JSONObject jsonObject) {
		PsBean[] resultado = null;

		ArrayList<PsBean> arrayTmp = new ArrayList<PsBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					PsBean registro = new PsBean();
					
				registro.setPs_sincro( jsonReg.getString(0) );	// sincro
				registro.setPs_mark( jsonReg.getString(1) );	// mark
				registro.setPs_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setPs_author( jsonReg.getString(3) );	// author
				registro.setPs_country_id( jsonReg.getLong(4) );	// country_id
				registro.setPs_name( jsonReg.getString(5) );	// name
				registro.setPs_alpha_2( jsonReg.getString(6) );	// alpha_2
				registro.setPs_alpha_3( jsonReg.getString(7) );	// alpha_3
				registro.setPs_flag_base64( jsonReg.getString(8) );	// flag_base64
				registro.setPs_json( jsonReg.getString(9) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new PsBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
