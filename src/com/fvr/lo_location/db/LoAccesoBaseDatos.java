package com.fvr.lo_location.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.lo_location.bean.LoBean;
import com.fvr.lo_location.bean.LoBeanFiltro;
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


public class LoAccesoBaseDatos {
    public String tabla   = "T_LO_location";
    public String lf_UPD  = "T_LO_location";
    public String lf_RTV  = "V_LO_RTV_location";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public LoAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// lo_location:
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
    public void lo_crtObj(BDConexion bd, LoBean registro) throws StExcepcion {

    	registro.setLo_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_lo_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	lo_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	lo_getParFS_RetCode(idOp);
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
		", \"name\"" + // name
		", \"address\"" + // address
		", \"city\"" + // city
		", \"postal_code\"" + // postal_code
		", \"province\"" + // province
		", \"state_region\"" + // state_region
		", \"country\"" + // country
		", \"lat\"" + // lat
		", \"lon\"" + // lon
		", \"comment\"" + // comment
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getLo_sincro() + "'" + // sincro
		", '"  + registro.getLo_mark() + "'" + // mark
		", '"  + registro.getLo_is_deleted() + "'" + // is_deleted
		", '"  + registro.getLo_author() + "'" + // author
		", '"  + registro.getLo_location_id() + "'" + // location_id
		", '"  + registro.getLo_name() + "'" + // name
		", '"  + registro.getLo_address() + "'" + // address
		", '"  + registro.getLo_city() + "'" + // city
		", '"  + registro.getLo_postal_code() + "'" + // postal_code
		", '"  + registro.getLo_province() + "'" + // province
		", '"  + registro.getLo_state_region() + "'" + // state_region
		", '"  + registro.getLo_country() + "'" + // country
		", "  + registro.getLo_lat() + "" + // lat
		", "  + registro.getLo_lon() + "" + // lon
		", '"  + registro.getLo_comment() + "'" + // comment
		", '"  + registro.getLo_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void lo_chgObj(BDConexion bd, LoBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_lo_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	lo_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	lo_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getLo_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getLo_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getLo_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getLo_author() + "'" + // author
		", \"location_id\" = '"  + registro.getLo_location_id() + "'" + // location_id
		", \"name\" = '"  + registro.getLo_name() + "'" + // name
		", \"address\" = '"  + registro.getLo_address() + "'" + // address
		", \"city\" = '"  + registro.getLo_city() + "'" + // city
		", \"postal_code\" = '"  + registro.getLo_postal_code() + "'" + // postal_code
		", \"province\" = '"  + registro.getLo_province() + "'" + // province
		", \"state_region\" = '"  + registro.getLo_state_region() + "'" + // state_region
		", \"country\" = '"  + registro.getLo_country() + "'" + // country
		", \"lat\" = "  + registro.getLo_lat() + "" + // lat
		", \"lon\" = "  + registro.getLo_lon() + "" + // lon
		", \"comment\" = '"  + registro.getLo_comment() + "'" + // comment
		", \"json\" = '"  + registro.getLo_json() + "'" + // json
                " WHERE " + 
		"  \"location_id\" = '" + registro.getLo_location_id() + "'" + // location_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void lo_dltObj(BDConexion bd, LoBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_lo_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	lo_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	lo_getParFS_RetCode(idOp);
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
		"  \"location_id\" = '" + registro.getLo_location_id() + "'" + // location_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public LoBean   lo_getRcd(BDConexion dataBase, LoBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_lo_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	lo_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return lo_getParFS_GET(idOp);
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
		"  \"location_id\" = '" + registro.getLo_location_id() + "'" + // location_id
                ""
                ;
        ResultSet rs = null;
        LoBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new LoBean();
                
		regRead.setLo_sincro( rs.getString("sincro") ); regRead.setLo_sincro( (regRead.getLo_sincro() == null)?"":regRead.getLo_sincro().trim() ); // sincro
		regRead.setLo_mark( rs.getString("mark") ); regRead.setLo_mark( (regRead.getLo_mark() == null)?"":regRead.getLo_mark().trim() ); // mark
		regRead.setLo_is_deleted( rs.getString("is_deleted") ); regRead.setLo_is_deleted( (regRead.getLo_is_deleted() == null)?"":regRead.getLo_is_deleted().trim() ); // is_deleted
		regRead.setLo_author( rs.getString("author") ); regRead.setLo_author( (regRead.getLo_author() == null)?"":regRead.getLo_author().trim() ); // author
		regRead.setLo_location_id( rs.getString("location_id") ); regRead.setLo_location_id( (regRead.getLo_location_id() == null)?"":regRead.getLo_location_id().trim() ); // location_id
		regRead.setLo_name( rs.getString("name") ); regRead.setLo_name( (regRead.getLo_name() == null)?"":regRead.getLo_name().trim() ); // name
		regRead.setLo_address( rs.getString("address") ); regRead.setLo_address( (regRead.getLo_address() == null)?"":regRead.getLo_address().trim() ); // address
		regRead.setLo_city( rs.getString("city") ); regRead.setLo_city( (regRead.getLo_city() == null)?"":regRead.getLo_city().trim() ); // city
		regRead.setLo_postal_code( rs.getString("postal_code") ); regRead.setLo_postal_code( (regRead.getLo_postal_code() == null)?"":regRead.getLo_postal_code().trim() ); // postal_code
		regRead.setLo_province( rs.getString("province") ); regRead.setLo_province( (regRead.getLo_province() == null)?"":regRead.getLo_province().trim() ); // province
		regRead.setLo_state_region( rs.getString("state_region") ); regRead.setLo_state_region( (regRead.getLo_state_region() == null)?"":regRead.getLo_state_region().trim() ); // state_region
		regRead.setLo_country( rs.getString("country") ); regRead.setLo_country( (regRead.getLo_country() == null)?"":regRead.getLo_country().trim() ); // country
		regRead.setLo_lat( rs.getLong("lat") );  // lat
		regRead.setLo_lon( rs.getLong("lon") );  // lon
		regRead.setLo_comment( rs.getString("comment") ); regRead.setLo_comment( (regRead.getLo_comment() == null)?"":regRead.getLo_comment().trim() ); // comment
		regRead.setLo_json( rs.getString("json") ); regRead.setLo_json( (regRead.getLo_json() == null)?"":regRead.getLo_json().trim() ); // json
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
    public LoBean[] lo_getSeq(BDConexion dataBase, ConfigPantalla extCfg, LoBeanFiltro rst ) throws StExcepcion {
        LoBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_lo_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            lo_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return lo_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_location_id(),"location_id",sqlWhere);   // location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_name(),"name",sqlWhere);   // name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_address(),"address",sqlWhere);   // address
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_city(),"city",sqlWhere);   // city
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_postal_code(),"postal_code",sqlWhere);   // postal_code
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_province(),"province",sqlWhere);   // province
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_state_region(),"state_region",sqlWhere);   // state_region
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_country(),"country",sqlWhere);   // country
	sqlWhere = fltOper.getNUM_EQ(rst.getLo_lat(),"lat",sqlWhere);   // lat
	sqlWhere = fltOper.getNUM_EQ(rst.getLo_lon(),"lon",sqlWhere);   // lon
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_comment(),"comment",sqlWhere);   // comment
	sqlWhere = fltOper.getCHAR_LIKE(rst.getLo_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"location_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        LoBean regRead = null;
        ArrayList<LoBean> arrayTmp = new ArrayList<LoBean>();
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
                        regRead = new LoBean();
                        
		regRead.setLo_sincro( rs.getString("sincro") ); regRead.setLo_sincro( (regRead.getLo_sincro() == null)?"":regRead.getLo_sincro().trim() ); // sincro
		regRead.setLo_mark( rs.getString("mark") ); regRead.setLo_mark( (regRead.getLo_mark() == null)?"":regRead.getLo_mark().trim() ); // mark
		regRead.setLo_is_deleted( rs.getString("is_deleted") ); regRead.setLo_is_deleted( (regRead.getLo_is_deleted() == null)?"":regRead.getLo_is_deleted().trim() ); // is_deleted
		regRead.setLo_author( rs.getString("author") ); regRead.setLo_author( (regRead.getLo_author() == null)?"":regRead.getLo_author().trim() ); // author
		regRead.setLo_location_id( rs.getString("location_id") ); regRead.setLo_location_id( (regRead.getLo_location_id() == null)?"":regRead.getLo_location_id().trim() ); // location_id
		regRead.setLo_name( rs.getString("name") ); regRead.setLo_name( (regRead.getLo_name() == null)?"":regRead.getLo_name().trim() ); // name
		regRead.setLo_address( rs.getString("address") ); regRead.setLo_address( (regRead.getLo_address() == null)?"":regRead.getLo_address().trim() ); // address
		regRead.setLo_city( rs.getString("city") ); regRead.setLo_city( (regRead.getLo_city() == null)?"":regRead.getLo_city().trim() ); // city
		regRead.setLo_postal_code( rs.getString("postal_code") ); regRead.setLo_postal_code( (regRead.getLo_postal_code() == null)?"":regRead.getLo_postal_code().trim() ); // postal_code
		regRead.setLo_province( rs.getString("province") ); regRead.setLo_province( (regRead.getLo_province() == null)?"":regRead.getLo_province().trim() ); // province
		regRead.setLo_state_region( rs.getString("state_region") ); regRead.setLo_state_region( (regRead.getLo_state_region() == null)?"":regRead.getLo_state_region().trim() ); // state_region
		regRead.setLo_country( rs.getString("country") ); regRead.setLo_country( (regRead.getLo_country() == null)?"":regRead.getLo_country().trim() ); // country
		regRead.setLo_lat( rs.getLong("lat") );  // lat
		regRead.setLo_lon( rs.getLong("lon") );  // lon
		regRead.setLo_comment( rs.getString("comment") ); regRead.setLo_comment( (regRead.getLo_comment() == null)?"":regRead.getLo_comment().trim() ); // comment
		regRead.setLo_json( rs.getString("json") ); regRead.setLo_json( (regRead.getLo_json() == null)?"":regRead.getLo_json().trim() ); // json
                        
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
        filasRecuperadas = new LoBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "name" + "</strong></td>";  // name
				s += "<td><strong style='color:darkblue;'>" + "address" + "</strong></td>";  // address
				s += "<td><strong style='color:darkblue;'>" + "city" + "</strong></td>";  // city
				s += "<td><strong style='color:darkblue;'>" + "postal_code" + "</strong></td>";  // postal_code
				s += "<td><strong style='color:darkblue;'>" + "province" + "</strong></td>";  // province
				s += "<td><strong style='color:darkblue;'>" + "state_region" + "</strong></td>";  // state_region
				s += "<td><strong style='color:darkblue;'>" + "country" + "</strong></td>";  // country
				s += "<td><strong style='color:darkblue;'>" + "lat" + "</strong></td>";  // lat
				s += "<td><strong style='color:darkblue;'>" + "lon" + "</strong></td>";  // lon
				s += "<td><strong style='color:darkblue;'>" + "comment" + "</strong></td>";  // comment
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
    protected void getSeq_Sub_ExportMid(LoBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getLo_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getLo_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getLo_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getLo_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getLo_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getLo_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // name
				tmp = registro.getLo_address();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // address
				tmp = registro.getLo_city();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // city
				tmp = registro.getLo_postal_code();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // postal_code
				tmp = registro.getLo_province();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // province
				tmp = registro.getLo_state_region();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // state_region
				tmp = registro.getLo_country();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // country
				s += "<td>" + new Long(registro.getLo_lat()).toString() + "</td>";  // lat
				s += "<td>" + new Long(registro.getLo_lon()).toString() + "</td>";  // lon
				tmp = registro.getLo_comment();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // comment
				tmp = registro.getLo_json();
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
    protected void     lo_putParFS_bean( final String idOp, LoBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     lo_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, LoBeanFiltro rst ) throws StExcepcion {
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

    protected void     lo_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected LoBean   lo_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	LoBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        lo_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new LoBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected LoBean[] lo_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	LoBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        lo_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new LoBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new LoBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new LoBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( LoBean[] lista ) {
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
	public LoBean[] json2beanArray(JSONObject jsonObject) {
		LoBean[] resultado = null;

		ArrayList<LoBean> arrayTmp = new ArrayList<LoBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					LoBean registro = new LoBean();
					
				registro.setLo_sincro( jsonReg.getString(0) );	// sincro
				registro.setLo_mark( jsonReg.getString(1) );	// mark
				registro.setLo_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setLo_author( jsonReg.getString(3) );	// author
				registro.setLo_location_id( jsonReg.getString(4) );	// location_id
				registro.setLo_name( jsonReg.getString(5) );	// name
				registro.setLo_address( jsonReg.getString(6) );	// address
				registro.setLo_city( jsonReg.getString(7) );	// city
				registro.setLo_postal_code( jsonReg.getString(8) );	// postal_code
				registro.setLo_province( jsonReg.getString(9) );	// province
				registro.setLo_state_region( jsonReg.getString(10) );	// state_region
				registro.setLo_country( jsonReg.getString(11) );	// country
				registro.setLo_lat( jsonReg.getLong(12) );	// lat
				registro.setLo_lon( jsonReg.getLong(13) );	// lon
				registro.setLo_comment( jsonReg.getString(14) );	// comment
				registro.setLo_json( jsonReg.getString(15) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new LoBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
