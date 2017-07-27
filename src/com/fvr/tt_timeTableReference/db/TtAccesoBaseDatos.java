package com.fvr.tt_timeTableReference.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.tt_timeTableReference.bean.TtBean;
import com.fvr.tt_timeTableReference.bean.TtBeanFiltro;
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


public class TtAccesoBaseDatos {
    public String tabla   = "T_TT_timeTableReference";
    public String lf_UPD  = "T_TT_timeTableReference";
    public String lf_RTV  = "V_TT_RTV_timeTableReference";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public TtAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// tt_timeTableReference:
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
    public void tt_crtObj(BDConexion bd, TtBean registro) throws StExcepcion {

    	registro.setTt_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tt_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	tt_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	tt_getParFS_RetCode(idOp);
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
		", \"day_type\"" + // day_type
		", \"start_time\"" + // start_time
		", \"duration_minutes\"" + // duration_minutes
		", \"isBlocked\"" + // isBlocked
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getTt_sincro() + "'" + // sincro
		", '"  + registro.getTt_mark() + "'" + // mark
		", '"  + registro.getTt_is_deleted() + "'" + // is_deleted
		", '"  + registro.getTt_author() + "'" + // author
		", '"  + registro.getTt_location_id() + "'" + // location_id
		", '"  + registro.getTt_day_type() + "'" + // day_type
		", '"  + registro.getTt_start_time() + "'" + // start_time
		", "  + registro.getTt_duration_minutes() + "" + // duration_minutes
		", '"  + registro.getTt_isBlocked() + "'" + // isBlocked
		", '"  + registro.getTt_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void tt_chgObj(BDConexion bd, TtBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tt_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	tt_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	tt_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getTt_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getTt_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getTt_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getTt_author() + "'" + // author
		", \"location_id\" = '"  + registro.getTt_location_id() + "'" + // location_id
		", \"day_type\" = '"  + registro.getTt_day_type() + "'" + // day_type
		", \"start_time\" = '"  + registro.getTt_start_time() + "'" + // start_time
		", \"duration_minutes\" = "  + registro.getTt_duration_minutes() + "" + // duration_minutes
		", \"isBlocked\" = '"  + registro.getTt_isBlocked() + "'" + // isBlocked
		", \"json\" = '"  + registro.getTt_json() + "'" + // json
                " WHERE " + 
		"  \"location_id\" = '" + registro.getTt_location_id() + "'" + // location_id
		"  AND \"day_type\" = '" + registro.getTt_day_type() + "'" + // day_type
		"  AND \"start_time\" = '" + registro.getTt_start_time() + "'" + // start_time
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void tt_dltObj(BDConexion bd, TtBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tt_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	tt_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	tt_getParFS_RetCode(idOp);
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
		"  \"location_id\" = '" + registro.getTt_location_id() + "'" + // location_id
		"  AND \"day_type\" = '" + registro.getTt_day_type() + "'" + // day_type
		"  AND \"start_time\" = '" + registro.getTt_start_time() + "'" + // start_time
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public TtBean   tt_getRcd(BDConexion dataBase, TtBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tt_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	tt_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return tt_getParFS_GET(idOp);
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
		"  \"location_id\" = '" + registro.getTt_location_id() + "'" + // location_id
		"  AND \"day_type\" = '" + registro.getTt_day_type() + "'" + // day_type
		"  AND \"start_time\" = '" + registro.getTt_start_time() + "'" + // start_time
                ""
                ;
        ResultSet rs = null;
        TtBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new TtBean();
                
		regRead.setTt_sincro( rs.getString("sincro") ); regRead.setTt_sincro( (regRead.getTt_sincro() == null)?"":regRead.getTt_sincro().trim() ); // sincro
		regRead.setTt_mark( rs.getString("mark") ); regRead.setTt_mark( (regRead.getTt_mark() == null)?"":regRead.getTt_mark().trim() ); // mark
		regRead.setTt_is_deleted( rs.getString("is_deleted") ); regRead.setTt_is_deleted( (regRead.getTt_is_deleted() == null)?"":regRead.getTt_is_deleted().trim() ); // is_deleted
		regRead.setTt_author( rs.getString("author") ); regRead.setTt_author( (regRead.getTt_author() == null)?"":regRead.getTt_author().trim() ); // author
		regRead.setTt_location_id( rs.getString("location_id") ); regRead.setTt_location_id( (regRead.getTt_location_id() == null)?"":regRead.getTt_location_id().trim() ); // location_id
		regRead.setTt_day_type( rs.getString("day_type") ); regRead.setTt_day_type( (regRead.getTt_day_type() == null)?"":regRead.getTt_day_type().trim() ); // day_type
		regRead.setTt_start_time( rs.getString("start_time") ); regRead.setTt_start_time( (regRead.getTt_start_time() == null)?"":regRead.getTt_start_time().trim() ); // start_time
		regRead.setTt_duration_minutes( rs.getLong("duration_minutes") );  // duration_minutes
		regRead.setTt_isBlocked( rs.getString("isBlocked") ); regRead.setTt_isBlocked( (regRead.getTt_isBlocked() == null)?"":regRead.getTt_isBlocked().trim() ); // isBlocked
		regRead.setTt_json( rs.getString("json") ); regRead.setTt_json( (regRead.getTt_json() == null)?"":regRead.getTt_json().trim() ); // json
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
    public TtBean[] tt_getSeq(BDConexion dataBase, ConfigPantalla extCfg, TtBeanFiltro rst ) throws StExcepcion {
        TtBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_tt_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            tt_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return tt_getParFS_GETSEQ( idOp, cfg );
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
	
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getTt_sincro(),"sincro",sqlWhere);   // sincro
    	sqlWhere = fltOper.getCHAR_EQ(rst.getTt_mark(),"mark",sqlWhere);   // mark
    	sqlWhere = fltOper.getCHAR_EQ(rst.getTt_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
    	sqlWhere = fltOper.getCHAR_EQ(rst.getTt_author(),"author",sqlWhere);   // author
    	sqlWhere = fltOper.getCHAR_EQ(rst.getTt_location_id(),"location_id",sqlWhere);   // location_id
    	sqlWhere = fltOper.getCHAR_EQ(rst.getTt_day_type(),"day_type",sqlWhere);   // day_type
    	sqlWhere = fltOper.getCHAR_EQ(rst.getTt_start_time(),"start_time",sqlWhere);   // start_time
    	sqlWhere = fltOper.getNUM_EQ(rst.getTt_duration_minutes(),"duration_minutes",sqlWhere);   // duration_minutes
//    	sqlWhere = fltOper.getCHAR_LIKE(rst.getTt_isBlocked(),"isBlocked",sqlWhere);   // isBlocked
    	sqlWhere = fltOper.getCHAR_LIKE(rst.getTt_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
	
		// Valores: 'S', 'N' o todos.
		if ( _K.SI.equalsIgnoreCase( rst.getTt_isBlocked() ) ) {
			sqlWhere = fltOper.getCHAR_EQ(rst.getTt_isBlocked(),"isBlocked",sqlWhere);   // isBlocked
		} else if ( _K.NO.equalsIgnoreCase( rst.getTt_isBlocked() ) ) {
			sqlWhere = fltOper.getCHAR_NE(_K.SI,"isBlocked",sqlWhere);   // isBlocked
		}
     

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"location_id\" ASC, \"day_type\" ASC, \"start_time\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        TtBean regRead = null;
        ArrayList<TtBean> arrayTmp = new ArrayList<TtBean>();
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
                        regRead = new TtBean();
                        
		regRead.setTt_sincro( rs.getString("sincro") ); regRead.setTt_sincro( (regRead.getTt_sincro() == null)?"":regRead.getTt_sincro().trim() ); // sincro
		regRead.setTt_mark( rs.getString("mark") ); regRead.setTt_mark( (regRead.getTt_mark() == null)?"":regRead.getTt_mark().trim() ); // mark
		regRead.setTt_is_deleted( rs.getString("is_deleted") ); regRead.setTt_is_deleted( (regRead.getTt_is_deleted() == null)?"":regRead.getTt_is_deleted().trim() ); // is_deleted
		regRead.setTt_author( rs.getString("author") ); regRead.setTt_author( (regRead.getTt_author() == null)?"":regRead.getTt_author().trim() ); // author
		regRead.setTt_location_id( rs.getString("location_id") ); regRead.setTt_location_id( (regRead.getTt_location_id() == null)?"":regRead.getTt_location_id().trim() ); // location_id
		regRead.setTt_day_type( rs.getString("day_type") ); regRead.setTt_day_type( (regRead.getTt_day_type() == null)?"":regRead.getTt_day_type().trim() ); // day_type
		regRead.setTt_start_time( rs.getString("start_time") ); regRead.setTt_start_time( (regRead.getTt_start_time() == null)?"":regRead.getTt_start_time().trim() ); // start_time
		regRead.setTt_duration_minutes( rs.getLong("duration_minutes") );  // duration_minutes
		regRead.setTt_isBlocked( rs.getString("isBlocked") ); regRead.setTt_isBlocked( (regRead.getTt_isBlocked() == null)?"":regRead.getTt_isBlocked().trim() ); // isBlocked
		regRead.setTt_json( rs.getString("json") ); regRead.setTt_json( (regRead.getTt_json() == null)?"":regRead.getTt_json().trim() ); // json
                        
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
        filasRecuperadas = new TtBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "day_type" + "</strong></td>";  // day_type
				s += "<td><strong style='color:darkblue;'>" + "start_time" + "</strong></td>";  // start_time
				s += "<td><strong style='color:darkblue;'>" + "duration_minutes" + "</strong></td>";  // duration_minutes
				s += "<td><strong style='color:darkblue;'>" + "isBlocked" + "</strong></td>";  // isBlocked
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
    protected void getSeq_Sub_ExportMid(TtBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getTt_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getTt_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getTt_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getTt_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getTt_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getTt_day_type();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // day_type
				tmp = registro.getTt_start_time();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // start_time
				s += "<td>" + new Long(registro.getTt_duration_minutes()).toString() + "</td>";  // duration_minutes
				tmp = registro.getTt_isBlocked();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // isBlocked
				tmp = registro.getTt_json();
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
    protected void     tt_putParFS_bean( final String idOp, TtBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     tt_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, TtBeanFiltro rst ) throws StExcepcion {
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

    protected void     tt_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected TtBean   tt_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	TtBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        tt_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new TtBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected TtBean[] tt_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	TtBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        tt_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new TtBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new TtBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new TtBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( TtBean[] lista ) {
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
	public TtBean[] json2beanArray(JSONObject jsonObject) {
		TtBean[] resultado = null;

		ArrayList<TtBean> arrayTmp = new ArrayList<TtBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					TtBean registro = new TtBean();
					
				registro.setTt_sincro( jsonReg.getString(0) );	// sincro
				registro.setTt_mark( jsonReg.getString(1) );	// mark
				registro.setTt_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setTt_author( jsonReg.getString(3) );	// author
				registro.setTt_location_id( jsonReg.getString(4) );	// location_id
				registro.setTt_day_type( jsonReg.getString(5) );	// day_type
				registro.setTt_start_time( jsonReg.getString(6) );	// start_time
				registro.setTt_duration_minutes( jsonReg.getLong(7) );	// duration_minutes
				registro.setTt_isBlocked( jsonReg.getString(8) );	// isBlocked
				registro.setTt_json( jsonReg.getString(9) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new TtBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
