package com.fvr.cp_cockpits.db;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr.cp_cockpits.bean.CpBean;
import com.fvr.cp_cockpits.bean.CpBeanFiltro;
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


public class CpAccesoBaseDatos {
    public String tabla   = "T_CP_cockpits";
    public String lf_UPD  = "T_CP_cockpits";
    public String lf_RTV  = "V_CP_RTV_cockpits";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public CpAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// cp_cockpits:
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
    public void cp_crtObj(BDConexion bd, CpBean registro) throws StExcepcion {

    	registro.setCp_sincro( Subrutinas.getDateAuditoria() );

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_cp_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	cp_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	cp_getParFS_RetCode(idOp);
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
		", \"cockpit_id\"" + // cockpit_id
		", \"serial_number\"" + // serial_number
		", \"name\"" + // name
		", \"isBlocked\"" + // isBlocked
		", \"asignation_order\"" + // asignation_order
		", \"install_date\"" + // install_date
		", \"reset_date_used\"" + // reset_date_used
		", \"hours_used\"" + // hours_used
		", \"note\"" + // note
		", \"comment\"" + // comment
		", \"observation\"" + // observation
		", \"warning\"" + // warning
		", \"contact_service\"" + // contact_service
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getCp_sincro() + "'" + // sincro
		", '"  + registro.getCp_mark() + "'" + // mark
		", '"  + registro.getCp_is_deleted() + "'" + // is_deleted
		", '"  + registro.getCp_author() + "'" + // author
		", '"  + registro.getCp_location_id() + "'" + // location_id
		", '"  + registro.getCp_cockpit_id() + "'" + // cockpit_id
		", '"  + registro.getCp_serial_number() + "'" + // serial_number
		", '"  + registro.getCp_name() + "'" + // name
		", '"  + registro.getCp_isBlocked() + "'" + // isBlocked
		", "  + registro.getCp_asignation_order() + "" + // asignation_order
		", '"  + registro.getCp_install_date() + "'" + // install_date
		", '"  + registro.getCp_reset_date_used() + "'" + // reset_date_used
		", "  + registro.getCp_hours_used() + "" + // hours_used
		", '"  + registro.getCp_note() + "'" + // note
		", '"  + registro.getCp_comment() + "'" + // comment
		", '"  + registro.getCp_observation() + "'" + // observation
		", '"  + registro.getCp_warning() + "'" + // warning
		", '"  + registro.getCp_contact_service() + "'" + // contact_service
		", '"  + registro.getCp_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void cp_chgObj(BDConexion bd, CpBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_cp_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	cp_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	cp_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getCp_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getCp_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getCp_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getCp_author() + "'" + // author
		", \"location_id\" = '"  + registro.getCp_location_id() + "'" + // location_id
		", \"cockpit_id\" = '"  + registro.getCp_cockpit_id() + "'" + // cockpit_id
		", \"serial_number\" = '"  + registro.getCp_serial_number() + "'" + // serial_number
		", \"name\" = '"  + registro.getCp_name() + "'" + // name
		", \"isBlocked\" = '"  + registro.getCp_isBlocked() + "'" + // isBlocked
		", \"asignation_order\" = "  + registro.getCp_asignation_order() + "" + // asignation_order
		", \"install_date\" = '"  + registro.getCp_install_date() + "'" + // install_date
		", \"reset_date_used\" = '"  + registro.getCp_reset_date_used() + "'" + // reset_date_used
		", \"hours_used\" = "  + registro.getCp_hours_used() + "" + // hours_used
		", \"note\" = '"  + registro.getCp_note() + "'" + // note
		", \"comment\" = '"  + registro.getCp_comment() + "'" + // comment
		", \"observation\" = '"  + registro.getCp_observation() + "'" + // observation
		", \"warning\" = '"  + registro.getCp_warning() + "'" + // warning
		", \"contact_service\" = '"  + registro.getCp_contact_service() + "'" + // contact_service
		", \"json\" = '"  + registro.getCp_json() + "'" + // json
                " WHERE " + 
		"  \"cockpit_id\" = '" + registro.getCp_cockpit_id() + "'" + // cockpit_id
		"  AND \"location_id\" = '" + registro.getCp_location_id() + "'" + // location_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void cp_dltObj(BDConexion bd, CpBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_cp_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	cp_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	cp_getParFS_RetCode(idOp);
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
		"  \"cockpit_id\" = '" + registro.getCp_cockpit_id() + "'" + // cockpit_id
		"  AND \"location_id\" = '" + registro.getCp_location_id() + "'" + // location_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public CpBean   cp_getRcd(BDConexion dataBase, CpBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_cp_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	cp_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return cp_getParFS_GET(idOp);
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
		"  \"cockpit_id\" = '" + registro.getCp_cockpit_id() + "'" + // cockpit_id
		"  AND \"location_id\" = '" + registro.getCp_location_id() + "'" + // location_id
                ""
                ;
        ResultSet rs = null;
        CpBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new CpBean();
                
		regRead.setCp_sincro( rs.getString("sincro") ); regRead.setCp_sincro( (regRead.getCp_sincro() == null)?"":regRead.getCp_sincro().trim() ); // sincro
		regRead.setCp_mark( rs.getString("mark") ); regRead.setCp_mark( (regRead.getCp_mark() == null)?"":regRead.getCp_mark().trim() ); // mark
		regRead.setCp_is_deleted( rs.getString("is_deleted") ); regRead.setCp_is_deleted( (regRead.getCp_is_deleted() == null)?"":regRead.getCp_is_deleted().trim() ); // is_deleted
		regRead.setCp_author( rs.getString("author") ); regRead.setCp_author( (regRead.getCp_author() == null)?"":regRead.getCp_author().trim() ); // author
		regRead.setCp_location_id( rs.getString("location_id") ); regRead.setCp_location_id( (regRead.getCp_location_id() == null)?"":regRead.getCp_location_id().trim() ); // location_id
		regRead.setCp_LO_name( rs.getString("LO_name") ); regRead.setCp_LO_name( (regRead.getCp_LO_name() == null)?"":regRead.getCp_LO_name().trim() ); // LO_name
		regRead.setCp_LO_address( rs.getString("LO_address") ); regRead.setCp_LO_address( (regRead.getCp_LO_address() == null)?"":regRead.getCp_LO_address().trim() ); // LO_address
		regRead.setCp_LO_city( rs.getString("LO_city") ); regRead.setCp_LO_city( (regRead.getCp_LO_city() == null)?"":regRead.getCp_LO_city().trim() ); // LO_city
		regRead.setCp_LO_postal_code( rs.getString("LO_postal_code") ); regRead.setCp_LO_postal_code( (regRead.getCp_LO_postal_code() == null)?"":regRead.getCp_LO_postal_code().trim() ); // LO_postal_code
		regRead.setCp_LO_lat( rs.getLong("LO_lat") );  // LO_lat
		regRead.setCp_LO_lon( rs.getLong("LO_lon") );  // LO_lon
		regRead.setCp_cockpit_id( rs.getString("cockpit_id") ); regRead.setCp_cockpit_id( (regRead.getCp_cockpit_id() == null)?"":regRead.getCp_cockpit_id().trim() ); // cockpit_id
		regRead.setCp_serial_number( rs.getString("serial_number") ); regRead.setCp_serial_number( (regRead.getCp_serial_number() == null)?"":regRead.getCp_serial_number().trim() ); // serial_number
		regRead.setCp_name( rs.getString("name") ); regRead.setCp_name( (regRead.getCp_name() == null)?"":regRead.getCp_name().trim() ); // name
		regRead.setCp_isBlocked( rs.getString("isBlocked") ); regRead.setCp_isBlocked( (regRead.getCp_isBlocked() == null)?"":regRead.getCp_isBlocked().trim() ); // isBlocked
		regRead.setCp_asignation_order( rs.getLong("asignation_order") );  // asignation_order
		regRead.setCp_install_date( rs.getString("install_date") ); regRead.setCp_install_date( (regRead.getCp_install_date() == null)?"":regRead.getCp_install_date().trim() ); // install_date
		regRead.setCp_reset_date_used( rs.getString("reset_date_used") ); regRead.setCp_reset_date_used( (regRead.getCp_reset_date_used() == null)?"":regRead.getCp_reset_date_used().trim() ); // reset_date_used
		regRead.setCp_hours_used( rs.getLong("hours_used") );  // hours_used
		regRead.setCp_note( rs.getString("note") ); regRead.setCp_note( (regRead.getCp_note() == null)?"":regRead.getCp_note().trim() ); // note
		regRead.setCp_comment( rs.getString("comment") ); regRead.setCp_comment( (regRead.getCp_comment() == null)?"":regRead.getCp_comment().trim() ); // comment
		regRead.setCp_observation( rs.getString("observation") ); regRead.setCp_observation( (regRead.getCp_observation() == null)?"":regRead.getCp_observation().trim() ); // observation
		regRead.setCp_warning( rs.getString("warning") ); regRead.setCp_warning( (regRead.getCp_warning() == null)?"":regRead.getCp_warning().trim() ); // warning
		regRead.setCp_contact_service( rs.getString("contact_service") ); regRead.setCp_contact_service( (regRead.getCp_contact_service() == null)?"":regRead.getCp_contact_service().trim() ); // contact_service
		regRead.setCp_json( rs.getString("json") ); regRead.setCp_json( (regRead.getCp_json() == null)?"":regRead.getCp_json().trim() ); // json
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
    public CpBean[] cp_getSeq(BDConexion dataBase, ConfigPantalla extCfg, CpBeanFiltro rst ) throws StExcepcion {
        CpBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_cp_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            cp_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return cp_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_location_id(),"location_id",sqlWhere);   // location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_LO_name(),"LO_name",sqlWhere);   // LO_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_LO_address(),"LO_address",sqlWhere);   // LO_address
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_LO_city(),"LO_city",sqlWhere);   // LO_city
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_LO_postal_code(),"LO_postal_code",sqlWhere);   // LO_postal_code
	sqlWhere = fltOper.getNUM_EQ(rst.getCp_LO_lat(),"LO_lat",sqlWhere);   // LO_lat
	sqlWhere = fltOper.getNUM_EQ(rst.getCp_LO_lon(),"LO_lon",sqlWhere);   // LO_lon
	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_cockpit_id(),"cockpit_id",sqlWhere);   // cockpit_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_serial_number(),"serial_number",sqlWhere);   // serial_number

	// ATENCIÓN: se usa para determinar el "location_id" desde el servlet "FVRMonitor":
	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_name(),"name",sqlWhere);   // name

//	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_isBlocked(),"isBlocked",sqlWhere);   // isBlocked
	sqlWhere = fltOper.getNUM_EQ(rst.getCp_asignation_order(),"asignation_order",sqlWhere);   // asignation_order
	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_install_date(),"install_date",sqlWhere);   // install_date
	sqlWhere = fltOper.getCHAR_EQ(rst.getCp_reset_date_used(),"reset_date_used",sqlWhere);   // reset_date_used
	sqlWhere = fltOper.getNUM_EQ(rst.getCp_hours_used(),"hours_used",sqlWhere);   // hours_used
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_note(),"note",sqlWhere);   // note
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_comment(),"comment",sqlWhere);   // comment
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_observation(),"observation",sqlWhere);   // observation
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_warning(),"warning",sqlWhere);   // warning
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_contact_service(),"contact_service",sqlWhere);   // contact_service
	sqlWhere = fltOper.getCHAR_LIKE(rst.getCp_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
	
	// Valores: 'S', 'N' o todos.
	if ( _K.SI.equalsIgnoreCase( rst.getCp_isBlocked() ) ) {
		sqlWhere = fltOper.getCHAR_EQ(rst.getCp_isBlocked(),"isBlocked",sqlWhere);   // isBlocked
	} else if ( _K.NO.equalsIgnoreCase( rst.getCp_isBlocked() ) ) {
		sqlWhere = fltOper.getCHAR_NE(_K.SI,"isBlocked",sqlWhere);   // isBlocked
	}
 

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"cockpit_id\" ASC, \"location_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        CpBean regRead = null;
        ArrayList<CpBean> arrayTmp = new ArrayList<CpBean>();
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
                        regRead = new CpBean();
                        
		regRead.setCp_sincro( rs.getString("sincro") ); regRead.setCp_sincro( (regRead.getCp_sincro() == null)?"":regRead.getCp_sincro().trim() ); // sincro
		regRead.setCp_mark( rs.getString("mark") ); regRead.setCp_mark( (regRead.getCp_mark() == null)?"":regRead.getCp_mark().trim() ); // mark
		regRead.setCp_is_deleted( rs.getString("is_deleted") ); regRead.setCp_is_deleted( (regRead.getCp_is_deleted() == null)?"":regRead.getCp_is_deleted().trim() ); // is_deleted
		regRead.setCp_author( rs.getString("author") ); regRead.setCp_author( (regRead.getCp_author() == null)?"":regRead.getCp_author().trim() ); // author
		regRead.setCp_location_id( rs.getString("location_id") ); regRead.setCp_location_id( (regRead.getCp_location_id() == null)?"":regRead.getCp_location_id().trim() ); // location_id
		regRead.setCp_LO_name( rs.getString("LO_name") ); regRead.setCp_LO_name( (regRead.getCp_LO_name() == null)?"":regRead.getCp_LO_name().trim() ); // LO_name
		regRead.setCp_LO_address( rs.getString("LO_address") ); regRead.setCp_LO_address( (regRead.getCp_LO_address() == null)?"":regRead.getCp_LO_address().trim() ); // LO_address
		regRead.setCp_LO_city( rs.getString("LO_city") ); regRead.setCp_LO_city( (regRead.getCp_LO_city() == null)?"":regRead.getCp_LO_city().trim() ); // LO_city
		regRead.setCp_LO_postal_code( rs.getString("LO_postal_code") ); regRead.setCp_LO_postal_code( (regRead.getCp_LO_postal_code() == null)?"":regRead.getCp_LO_postal_code().trim() ); // LO_postal_code
		regRead.setCp_LO_lat( rs.getLong("LO_lat") );  // LO_lat
		regRead.setCp_LO_lon( rs.getLong("LO_lon") );  // LO_lon
		regRead.setCp_cockpit_id( rs.getString("cockpit_id") ); regRead.setCp_cockpit_id( (regRead.getCp_cockpit_id() == null)?"":regRead.getCp_cockpit_id().trim() ); // cockpit_id
		regRead.setCp_serial_number( rs.getString("serial_number") ); regRead.setCp_serial_number( (regRead.getCp_serial_number() == null)?"":regRead.getCp_serial_number().trim() ); // serial_number
		regRead.setCp_name( rs.getString("name") ); regRead.setCp_name( (regRead.getCp_name() == null)?"":regRead.getCp_name().trim() ); // name
		regRead.setCp_isBlocked( rs.getString("isBlocked") ); regRead.setCp_isBlocked( (regRead.getCp_isBlocked() == null)?"":regRead.getCp_isBlocked().trim() ); // isBlocked
		regRead.setCp_asignation_order( rs.getLong("asignation_order") );  // asignation_order
		regRead.setCp_install_date( rs.getString("install_date") ); regRead.setCp_install_date( (regRead.getCp_install_date() == null)?"":regRead.getCp_install_date().trim() ); // install_date
		regRead.setCp_reset_date_used( rs.getString("reset_date_used") ); regRead.setCp_reset_date_used( (regRead.getCp_reset_date_used() == null)?"":regRead.getCp_reset_date_used().trim() ); // reset_date_used
		regRead.setCp_hours_used( rs.getLong("hours_used") );  // hours_used
		regRead.setCp_note( rs.getString("note") ); regRead.setCp_note( (regRead.getCp_note() == null)?"":regRead.getCp_note().trim() ); // note
		regRead.setCp_comment( rs.getString("comment") ); regRead.setCp_comment( (regRead.getCp_comment() == null)?"":regRead.getCp_comment().trim() ); // comment
		regRead.setCp_observation( rs.getString("observation") ); regRead.setCp_observation( (regRead.getCp_observation() == null)?"":regRead.getCp_observation().trim() ); // observation
		regRead.setCp_warning( rs.getString("warning") ); regRead.setCp_warning( (regRead.getCp_warning() == null)?"":regRead.getCp_warning().trim() ); // warning
		regRead.setCp_contact_service( rs.getString("contact_service") ); regRead.setCp_contact_service( (regRead.getCp_contact_service() == null)?"":regRead.getCp_contact_service().trim() ); // contact_service
		regRead.setCp_json( rs.getString("json") ); regRead.setCp_json( (regRead.getCp_json() == null)?"":regRead.getCp_json().trim() ); // json
                        
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
        filasRecuperadas = new CpBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "LO_address" + "</strong></td>";  // LO_address
				s += "<td><strong style='color:darkblue;'>" + "LO_city" + "</strong></td>";  // LO_city
				s += "<td><strong style='color:darkblue;'>" + "LO_postal_code" + "</strong></td>";  // LO_postal_code
				s += "<td><strong style='color:darkblue;'>" + "LO_lat" + "</strong></td>";  // LO_lat
				s += "<td><strong style='color:darkblue;'>" + "LO_lon" + "</strong></td>";  // LO_lon
				s += "<td><strong style='color:darkblue;'>" + "cockpit_id" + "</strong></td>";  // cockpit_id
				s += "<td><strong style='color:darkblue;'>" + "serial_number" + "</strong></td>";  // serial_number
				s += "<td><strong style='color:darkblue;'>" + "name" + "</strong></td>";  // name
				s += "<td><strong style='color:darkblue;'>" + "isBlocked" + "</strong></td>";  // isBlocked
				s += "<td><strong style='color:darkblue;'>" + "asignation_order" + "</strong></td>";  // asignation_order
				s += "<td><strong style='color:darkblue;'>" + "install_date" + "</strong></td>";  // install_date
				s += "<td><strong style='color:darkblue;'>" + "reset_date_used" + "</strong></td>";  // reset_date_used
				s += "<td><strong style='color:darkblue;'>" + "hours_used" + "</strong></td>";  // hours_used
				s += "<td><strong style='color:darkblue;'>" + "note" + "</strong></td>";  // note
				s += "<td><strong style='color:darkblue;'>" + "comment" + "</strong></td>";  // comment
				s += "<td><strong style='color:darkblue;'>" + "observation" + "</strong></td>";  // observation
				s += "<td><strong style='color:darkblue;'>" + "warning" + "</strong></td>";  // warning
				s += "<td><strong style='color:darkblue;'>" + "contact_service" + "</strong></td>";  // contact_service
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
    protected void getSeq_Sub_ExportMid(CpBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getCp_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getCp_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getCp_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getCp_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getCp_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getCp_LO_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_name
				tmp = registro.getCp_LO_address();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_address
				tmp = registro.getCp_LO_city();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_city
				tmp = registro.getCp_LO_postal_code();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // LO_postal_code
				s += "<td>" + new Long(registro.getCp_LO_lat()).toString() + "</td>";  // LO_lat
				s += "<td>" + new Long(registro.getCp_LO_lon()).toString() + "</td>";  // LO_lon
				tmp = registro.getCp_cockpit_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // cockpit_id
				tmp = registro.getCp_serial_number();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // serial_number
				tmp = registro.getCp_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // name
				tmp = registro.getCp_isBlocked();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // isBlocked
				s += "<td>" + new Long(registro.getCp_asignation_order()).toString() + "</td>";  // asignation_order
				tmp = registro.getCp_install_date();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // install_date
				tmp = registro.getCp_reset_date_used();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // reset_date_used
				s += "<td>" + new Long(registro.getCp_hours_used()).toString() + "</td>";  // hours_used
				tmp = registro.getCp_note();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // note
				tmp = registro.getCp_comment();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // comment
				tmp = registro.getCp_observation();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // observation
				tmp = registro.getCp_warning();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // warning
				tmp = registro.getCp_contact_service();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // contact_service
				tmp = registro.getCp_json();
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
    protected void     cp_putParFS_bean( final String idOp, CpBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     cp_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, CpBeanFiltro rst ) throws StExcepcion {
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

    protected void     cp_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected CpBean   cp_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	CpBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        cp_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new CpBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected CpBean[] cp_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	CpBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        cp_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new CpBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new CpBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new CpBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( CpBean[] lista ) {
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
	public CpBean[] json2beanArray(JSONObject jsonObject) {
		CpBean[] resultado = null;

		ArrayList<CpBean> arrayTmp = new ArrayList<CpBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					CpBean registro = new CpBean();
					
				registro.setCp_sincro( jsonReg.getString(0) );	// sincro
				registro.setCp_mark( jsonReg.getString(1) );	// mark
				registro.setCp_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setCp_author( jsonReg.getString(3) );	// author
				registro.setCp_location_id( jsonReg.getString(4) );	// location_id
				registro.setCp_LO_name( jsonReg.getString(5) );	// LO_name
				registro.setCp_LO_address( jsonReg.getString(6) );	// LO_address
				registro.setCp_LO_city( jsonReg.getString(7) );	// LO_city
				registro.setCp_LO_postal_code( jsonReg.getString(8) );	// LO_postal_code
				registro.setCp_LO_lat( jsonReg.getLong(9) );	// LO_lat
				registro.setCp_LO_lon( jsonReg.getLong(10) );	// LO_lon
				registro.setCp_cockpit_id( jsonReg.getString(11) );	// cockpit_id
				registro.setCp_serial_number( jsonReg.getString(12) );	// serial_number
				registro.setCp_name( jsonReg.getString(13) );	// name
				registro.setCp_isBlocked( jsonReg.getString(14) );	// isBlocked
				registro.setCp_asignation_order( jsonReg.getLong(15) );	// asignation_order
				registro.setCp_install_date( jsonReg.getString(16) );	// install_date
				registro.setCp_reset_date_used( jsonReg.getString(17) );	// reset_date_used
				registro.setCp_hours_used( jsonReg.getLong(18) );	// hours_used
				registro.setCp_note( jsonReg.getString(19) );	// note
				registro.setCp_comment( jsonReg.getString(20) );	// comment
				registro.setCp_observation( jsonReg.getString(21) );	// observation
				registro.setCp_warning( jsonReg.getString(22) );	// warning
				registro.setCp_contact_service( jsonReg.getString(23) );	// contact_service
				registro.setCp_json( jsonReg.getString(24) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new CpBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
