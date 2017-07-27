package com.fvr.us_users.db;

import java.awt.image.BufferedImage;
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

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.RstAplicar;
import com.fvr._comun.StBean;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.img2D.util.ImageUtils;
import com.fvr.us_users.bean.UsBean;
import com.fvr.us_users.bean.UsBeanFiltro;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class UsAccesoBaseDatos {
    public String tabla   = "T_US_users";
    public String lf_UPD  = "T_US_users";
    public String lf_RTV  = "V_US_RTV_users";

    ////////////////////////////////////////////////////////////////////
    // Opcionalmente se pueden conectar las funciones CRUD+getRcd+getSeq
    // a un "Sistema Externo", convirtiendo sistemáticamente
    // sus parámetros en ficheros cada uno con su valor respectivo.
    // ACTIVAR para 'Sistema Externo' con paso de parámetros por FileSystem:
    protected final boolean isParmViaFS = false;
    ////////////////////////////////////////////////////////////////////
    protected File fo;
    protected BufferedWriter dout;
    public UsAccesoBaseDatos() {fo=null;dout=null;}
/////////////////////////////////////////////////
// us_users:
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
    public void us_crtObj(BDConexion bd, UsBean registro) throws StExcepcion {

    	registro.setUs_sincro( Subrutinas.getDateAuditoria() );
    	
    	if ( registro.getUs_avatar() == null || registro.getUs_avatar().trim().length() < 1 ) {
    		registro.setUs_avatar( _K.AVATAR_IMG_UNKNOWN );
    	}

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_us_CRT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	us_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	us_getParFS_RetCode(idOp);
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
		", \"role_id\"" + // role_id
		", \"hash_code\"" + // hash_code
		", \"country_id\"" + // country_id
		", \"nick\"" + // nick
		", \"password\"" + // password
		", \"first_name\"" + // first_name
		", \"last_name\"" + // last_name
		", \"phone\"" + // phone
		", \"gender\"" + // gender
		", \"birth_day\"" + // birth_day
		", \"avatar\"" + // avatar
		", \"location_id\"" + // location_id
		", \"json\"" + // json
                "  ) VALUES ( " + 
		"  '"  + registro.getUs_sincro() + "'" + // sincro
		", '"  + registro.getUs_mark() + "'" + // mark
		", '"  + registro.getUs_is_deleted() + "'" + // is_deleted
		", '"  + registro.getUs_author() + "'" + // author
		", '"  + registro.getUs_user_id() + "'" + // user_id
		", '"  + registro.getUs_role_id() + "'" + // role_id
		", '"  + registro.getUs_hash_code() + "'" + // hash_code
		", "  + registro.getUs_country_id() + "" + // country_id
		", '"  + registro.getUs_nick() + "'" + // nick
		", '"  + registro.getUs_password() + "'" + // password
		", '"  + registro.getUs_first_name() + "'" + // first_name
		", '"  + registro.getUs_last_name() + "'" + // last_name
		", '"  + registro.getUs_phone() + "'" + // phone
		", '"  + registro.getUs_gender() + "'" + // gender
		", '"  + registro.getUs_birth_day() + "'" + // birth_day
		", '"  + registro.getUs_avatar() + "'" + // avatar
		", '"  + registro.getUs_location_id() + "'" + // location_id
		", '"  + registro.getUs_json() + "'" + // json 
                ")"
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void us_chgObj(BDConexion bd, UsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_us_CHG";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	us_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	us_getParFS_RetCode(idOp);
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
		"  \"sincro\" = '"  + registro.getUs_sincro() + "'" + // sincro
		", \"mark\" = '"  + registro.getUs_mark() + "'" + // mark
		", \"is_deleted\" = '"  + registro.getUs_is_deleted() + "'" + // is_deleted
		", \"author\" = '"  + registro.getUs_author() + "'" + // author
		", \"user_id\" = '"  + registro.getUs_user_id() + "'" + // user_id
		", \"role_id\" = '"  + registro.getUs_role_id() + "'" + // role_id
		", \"hash_code\" = '"  + registro.getUs_hash_code() + "'" + // hash_code
		", \"country_id\" = "  + registro.getUs_country_id() + "" + // country_id
		", \"nick\" = '"  + registro.getUs_nick() + "'" + // nick
		", \"password\" = '"  + registro.getUs_password() + "'" + // password
		", \"first_name\" = '"  + registro.getUs_first_name() + "'" + // first_name
		", \"last_name\" = '"  + registro.getUs_last_name() + "'" + // last_name
		", \"phone\" = '"  + registro.getUs_phone() + "'" + // phone
		", \"gender\" = '"  + registro.getUs_gender() + "'" + // gender
		", \"birth_day\" = '"  + registro.getUs_birth_day() + "'" + // birth_day
		", \"avatar\" = '"  + registro.getUs_avatar() + "'" + // avatar
		", \"location_id\" = '"  + registro.getUs_location_id() + "'" + // location_id
		", \"json\" = '"  + registro.getUs_json() + "'" + // json
                " WHERE " + 
		"  \"user_id\" = '" + registro.getUs_user_id() + "'" + // user_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public void us_dltObj(BDConexion bd, UsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_us_DLT";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	us_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	us_getParFS_RetCode(idOp);
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
		"  \"user_id\" = '" + registro.getUs_user_id() + "'" + // user_id
                ""
                ;
        //////////////////////////////////////////////
        runSql(bd,sql);
        //////////////////////////////////////////////
    }
    public UsBean   us_getRcd(BDConexion dataBase, UsBean registro) throws StExcepcion {

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Variante para versión de paso de parámetros por FileSystem:
        if (isParmViaFS) {
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_us_GET";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
	    	us_putParFS_bean( idOp, registro );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
	    	return us_getParFS_GET(idOp);
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
		"  \"user_id\" = '" + registro.getUs_user_id() + "'" + // user_id
                ""
                ;
        ResultSet rs = null;
        UsBean regRead = null;
        //////////////////////////////////////////////
        //if (dataBase==null) dataBase = new BDConexion();
        try {
            rs = dataBase.executeQuery(sql);
            if (rs.next()){
                regRead = new UsBean();
                
		regRead.setUs_sincro( rs.getString("sincro") ); regRead.setUs_sincro( (regRead.getUs_sincro() == null)?"":regRead.getUs_sincro().trim() ); // sincro
		regRead.setUs_mark( rs.getString("mark") ); regRead.setUs_mark( (regRead.getUs_mark() == null)?"":regRead.getUs_mark().trim() ); // mark
		regRead.setUs_is_deleted( rs.getString("is_deleted") ); regRead.setUs_is_deleted( (regRead.getUs_is_deleted() == null)?"":regRead.getUs_is_deleted().trim() ); // is_deleted
		regRead.setUs_author( rs.getString("author") ); regRead.setUs_author( (regRead.getUs_author() == null)?"":regRead.getUs_author().trim() ); // author
		regRead.setUs_user_id( rs.getString("user_id") ); regRead.setUs_user_id( (regRead.getUs_user_id() == null)?"":regRead.getUs_user_id().trim() ); // user_id
		regRead.setUs_role_id( rs.getString("role_id") ); regRead.setUs_role_id( (regRead.getUs_role_id() == null)?"":regRead.getUs_role_id().trim() ); // role_id
		regRead.setUs_hash_code( rs.getString("hash_code") ); regRead.setUs_hash_code( (regRead.getUs_hash_code() == null)?"":regRead.getUs_hash_code().trim() ); // hash_code
		regRead.setUs_country_id( rs.getLong("country_id") );  // country_id
		regRead.setUs_PS_name( rs.getString("PS_name") ); regRead.setUs_PS_name( (regRead.getUs_PS_name() == null)?"":regRead.getUs_PS_name().trim() ); // PS_name
		regRead.setUs_PS_flag_base64( rs.getString("PS_flag_base64") ); regRead.setUs_PS_flag_base64( (regRead.getUs_PS_flag_base64() == null)?"":regRead.getUs_PS_flag_base64().trim() ); // PS_flag_base64
		regRead.setUs_nick( rs.getString("nick") ); regRead.setUs_nick( (regRead.getUs_nick() == null)?"":regRead.getUs_nick().trim() ); // nick
		regRead.setUs_password( rs.getString("password") ); regRead.setUs_password( (regRead.getUs_password() == null)?"":regRead.getUs_password().trim() ); // password
		regRead.setUs_first_name( rs.getString("first_name") ); regRead.setUs_first_name( (regRead.getUs_first_name() == null)?"":regRead.getUs_first_name().trim() ); // first_name
		regRead.setUs_last_name( rs.getString("last_name") ); regRead.setUs_last_name( (regRead.getUs_last_name() == null)?"":regRead.getUs_last_name().trim() ); // last_name
		regRead.setUs_phone( rs.getString("phone") ); regRead.setUs_phone( (regRead.getUs_phone() == null)?"":regRead.getUs_phone().trim() ); // phone
		regRead.setUs_gender( rs.getString("gender") ); regRead.setUs_gender( (regRead.getUs_gender() == null)?"":regRead.getUs_gender().trim() ); // gender
		regRead.setUs_birth_day( rs.getString("birth_day") ); regRead.setUs_birth_day( (regRead.getUs_birth_day() == null)?"":regRead.getUs_birth_day().trim() ); // birth_day
		regRead.setUs_avatar( rs.getString("avatar") ); regRead.setUs_avatar( (regRead.getUs_avatar() == null)?"":regRead.getUs_avatar().trim() ); // avatar
		regRead.setUs_location_id( rs.getString("location_id") ); regRead.setUs_location_id( (regRead.getUs_location_id() == null)?"":regRead.getUs_location_id().trim() ); // location_id
		regRead.setUs_json( rs.getString("json") ); regRead.setUs_json( (regRead.getUs_json() == null)?"":regRead.getUs_json().trim() ); // json
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
    public UsBean[] us_getSeq(BDConexion dataBase, ConfigPantalla extCfg, UsBeanFiltro rst, boolean isConAvatares ) throws StExcepcion {
        UsBean[] filasRecuperadas = null;
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
	    	final String idOp = Integer.toHexString(hashCode()).toUpperCase() + "_us_GETSEQ";
	        //////////////////////////////////////////////
	        // 1.grabar parámetros, 
            us_putParFS_GETSEQ( idOp, cfg, rst );
	        // 2.Invocar Sistema Externo SÍNCRONO!
	        callSistemaExterno( idOp );
	        // 3.Leer resultados
            return us_getParFS_GETSEQ( idOp, cfg );
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
	
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_sincro(),"sincro",sqlWhere);   // sincro
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_mark(),"mark",sqlWhere);   // mark
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_is_deleted(),"is_deleted",sqlWhere);   // is_deleted
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_author(),"author",sqlWhere);   // author
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_user_id(),"user_id",sqlWhere);   // user_id
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_role_id(),"role_id",sqlWhere);   // role_id
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_hash_code(),"hash_code",sqlWhere);   // hash_code
	sqlWhere = fltOper.getNUM_EQ(rst.getUs_country_id(),"country_id",sqlWhere);   // country_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_PS_name(),"PS_name",sqlWhere);   // PS_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_PS_flag_base64(),"PS_flag_base64",sqlWhere);   // PS_flag_base64
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_nick(),"nick",sqlWhere);   // nick
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_password(),"password",sqlWhere);   // password
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_first_name(),"first_name",sqlWhere);   // first_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_last_name(),"last_name",sqlWhere);   // last_name
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_phone(),"phone",sqlWhere);   // phone
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_gender(),"gender",sqlWhere);   // gender
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_birth_day(),"birth_day",sqlWhere);   // birth_day
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_avatar(),"avatar",sqlWhere);   // avatar
	sqlWhere = fltOper.getCHAR_EQ(rst.getUs_location_id(),"location_id",sqlWhere);   // location_id
	sqlWhere = fltOper.getCHAR_LIKE(rst.getUs_json(),"json",sqlWhere);   // json
        //////////////////////////////////////////////////////
        
	   

        //////////////////////////////////////////////////////
        sql += sqlWhere;
        // Campos de ordenación:
        sql += " ORDER BY \"user_id\" ASC";
        //////////////////////////////////////////////////////
        ResultSet rs = null;
        UsBean regRead = null;
        ArrayList<UsBean> arrayTmp = new ArrayList<UsBean>();
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
                        regRead = new UsBean();
                        
		regRead.setUs_sincro( rs.getString("sincro") ); regRead.setUs_sincro( (regRead.getUs_sincro() == null)?"":regRead.getUs_sincro().trim() ); // sincro
		regRead.setUs_mark( rs.getString("mark") ); regRead.setUs_mark( (regRead.getUs_mark() == null)?"":regRead.getUs_mark().trim() ); // mark
		regRead.setUs_is_deleted( rs.getString("is_deleted") ); regRead.setUs_is_deleted( (regRead.getUs_is_deleted() == null)?"":regRead.getUs_is_deleted().trim() ); // is_deleted
		regRead.setUs_author( rs.getString("author") ); regRead.setUs_author( (regRead.getUs_author() == null)?"":regRead.getUs_author().trim() ); // author
		regRead.setUs_user_id( rs.getString("user_id") ); regRead.setUs_user_id( (regRead.getUs_user_id() == null)?"":regRead.getUs_user_id().trim() ); // user_id
		regRead.setUs_role_id( rs.getString("role_id") ); regRead.setUs_role_id( (regRead.getUs_role_id() == null)?"":regRead.getUs_role_id().trim() ); // role_id
		regRead.setUs_hash_code( rs.getString("hash_code") ); regRead.setUs_hash_code( (regRead.getUs_hash_code() == null)?"":regRead.getUs_hash_code().trim() ); // hash_code
		regRead.setUs_country_id( rs.getLong("country_id") );  // country_id
		regRead.setUs_PS_name( rs.getString("PS_name") ); regRead.setUs_PS_name( (regRead.getUs_PS_name() == null)?"":regRead.getUs_PS_name().trim() ); // PS_name
		regRead.setUs_PS_flag_base64( rs.getString("PS_flag_base64") ); regRead.setUs_PS_flag_base64( (regRead.getUs_PS_flag_base64() == null)?"":regRead.getUs_PS_flag_base64().trim() ); // PS_flag_base64
		regRead.setUs_nick( rs.getString("nick") ); regRead.setUs_nick( (regRead.getUs_nick() == null)?"":regRead.getUs_nick().trim() ); // nick
		regRead.setUs_password( rs.getString("password") ); regRead.setUs_password( (regRead.getUs_password() == null)?"":regRead.getUs_password().trim() ); // password
		regRead.setUs_first_name( rs.getString("first_name") ); regRead.setUs_first_name( (regRead.getUs_first_name() == null)?"":regRead.getUs_first_name().trim() ); // first_name
		regRead.setUs_last_name( rs.getString("last_name") ); regRead.setUs_last_name( (regRead.getUs_last_name() == null)?"":regRead.getUs_last_name().trim() ); // last_name
		regRead.setUs_phone( rs.getString("phone") ); regRead.setUs_phone( (regRead.getUs_phone() == null)?"":regRead.getUs_phone().trim() ); // phone
		regRead.setUs_gender( rs.getString("gender") ); regRead.setUs_gender( (regRead.getUs_gender() == null)?"":regRead.getUs_gender().trim() ); // gender
		regRead.setUs_birth_day( rs.getString("birth_day") ); regRead.setUs_birth_day( (regRead.getUs_birth_day() == null)?"":regRead.getUs_birth_day().trim() ); // birth_day
		regRead.setUs_avatar( rs.getString("avatar") ); regRead.setUs_avatar( (regRead.getUs_avatar() == null)?"":regRead.getUs_avatar().trim() ); // avatar
		regRead.setUs_location_id( rs.getString("location_id") ); regRead.setUs_location_id( (regRead.getUs_location_id() == null)?"":regRead.getUs_location_id().trim() ); // location_id
		regRead.setUs_json( rs.getString("json") ); regRead.setUs_json( (regRead.getUs_json() == null)?"":regRead.getUs_json().trim() ); // json

		/////////////////
		if ( isConAvatares ) {
			// Reducir tamaño para aligerar transmisión:
			String avatar = rs.getString("avatar");
			if ( avatar != null && avatar.trim().length() > 0 ) {
				int idx = avatar.toLowerCase().indexOf("base64,");
				if ( idx > -1 ) { avatar = avatar.substring( idx + 7 ); }
				BufferedImage img = ImageUtils.decodeToImage(avatar);
				if ( img != null ) {
					img = ImageUtils.resize(img,32,32);
					if ( img != null ) {
						avatar = "data:image/png;base64," + ImageUtils.encodeToString(img,"png");
					}
				}
			}
			regRead.setUs_avatar( avatar ); regRead.setUs_avatar( (regRead.getUs_avatar() == null)?"":regRead.getUs_avatar().trim() ); // avatar
		}
		/////////////////
		
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
        filasRecuperadas = new UsBean[arrayTmp.size()];
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
				s += "<td><strong style='color:darkblue;'>" + "role_id" + "</strong></td>";  // role_id
				s += "<td><strong style='color:darkblue;'>" + "hash_code" + "</strong></td>";  // hash_code
				s += "<td><strong style='color:darkblue;'>" + "country_id" + "</strong></td>";  // country_id
				s += "<td><strong style='color:darkblue;'>" + "PS_name" + "</strong></td>";  // PS_name
				s += "<td><strong style='color:darkblue;'>" + "PS_flag_base64" + "</strong></td>";  // PS_flag_base64
				s += "<td><strong style='color:darkblue;'>" + "nick" + "</strong></td>";  // nick
				s += "<td><strong style='color:darkblue;'>" + "password" + "</strong></td>";  // password
				s += "<td><strong style='color:darkblue;'>" + "first_name" + "</strong></td>";  // first_name
				s += "<td><strong style='color:darkblue;'>" + "last_name" + "</strong></td>";  // last_name
				s += "<td><strong style='color:darkblue;'>" + "phone" + "</strong></td>";  // phone
				s += "<td><strong style='color:darkblue;'>" + "gender" + "</strong></td>";  // gender
				s += "<td><strong style='color:darkblue;'>" + "birth_day" + "</strong></td>";  // birth_day
				s += "<td><strong style='color:darkblue;'>" + "avatar" + "</strong></td>";  // avatar
				s += "<td><strong style='color:darkblue;'>" + "location_id" + "</strong></td>";  // location_id
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
    protected void getSeq_Sub_ExportMid(UsBean registro) throws StExcepcion {
        String s = "";
		String tmp = "";
		
		s += "<tr>";
				tmp = registro.getUs_sincro();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // sincro
				tmp = registro.getUs_mark();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // mark
				tmp = registro.getUs_is_deleted();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // is_deleted
				tmp = registro.getUs_author();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // author
				tmp = registro.getUs_user_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // user_id
				tmp = registro.getUs_role_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // role_id
				tmp = registro.getUs_hash_code();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // hash_code
				s += "<td>" + new Long(registro.getUs_country_id()).toString() + "</td>";  // country_id
				tmp = registro.getUs_PS_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PS_name
				tmp = registro.getUs_PS_flag_base64();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // PS_flag_base64
				tmp = registro.getUs_nick();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // nick
				tmp = registro.getUs_password();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // password
				tmp = registro.getUs_first_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // first_name
				tmp = registro.getUs_last_name();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // last_name
				tmp = registro.getUs_phone();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // phone
				tmp = registro.getUs_gender();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // gender
				tmp = registro.getUs_birth_day();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // birth_day
				tmp = registro.getUs_avatar();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // avatar
				tmp = registro.getUs_location_id();
				try {tmp = new String( tmp.getBytes(), "iso-8859-1" );} catch (UnsupportedEncodingException ex) {;}
				s += "<td>" + tmp + "</td>";  // location_id
				tmp = registro.getUs_json();
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
    protected void     us_putParFS_bean( final String idOp, UsBean par ) throws StExcepcion {
    	StringBuffer log = new StringBuffer();

    	new File( _K.caminoSalida  ).mkdirs();

    	// Propagar mis parámetros de ENTRADA:
    	final String pPar = _K.caminoSalida  + idOp + "_par" + _K.extFicParm;

    	// 1d3.Generar archivos de parámetros:
    	Subrutinas.grabFile(log, pPar, par.serializar().getBytes() ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
//    	  par.deserializar( Subrutinas.readFile(log, pPar ) );	// TEST

    }
    protected void     us_putParFS_GETSEQ( final String idOp, ConfigPantalla cfg, UsBeanFiltro rst ) throws StExcepcion {
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

    protected void     us_getParFS_RetCode( final String idOp ) throws StExcepcion {
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
    protected UsBean   us_getParFS_GET( final String idOp ) throws StExcepcion {
    	
    	UsBean reg = null;
    	
        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();
        
        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp + _K.extFicParm;
        
        // 3d3.Leer resultados
        us_getParFS_RetCode( idOp );
        String rg = Subrutinas.readFile(log, pResultados );	 if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        
        if ( rg != null && rg.trim().length() > 0 ) { 
            reg = new UsBean();
            reg.deserializar(rg);
        }

		return reg;
    }
    protected UsBean[] us_getParFS_GETSEQ( final String idOp, ConfigPantalla cfg ) throws StExcepcion {

    	UsBean[] resultado = null;

        StringBuffer log = new StringBuffer();

        new File( _K.caminoEntrada ).mkdirs();

        // Recoger mis parámetros de SALIDA:
        final String pResultados = _K.caminoEntrada + idOp          + _K.extFicParm;
        final String pConfigPant = _K.caminoEntrada + idOp + "_cfg" + _K.extFicParm;

        // 3d3.Leer resultados
        us_getParFS_RetCode( idOp );
        String regs = Subrutinas.readFile(log, pResultados ); if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}
        String sCfg = Subrutinas.readFile(log, pConfigPant ); //if(log.toString().trim().length()>0){throw new StExcepcion(log.toString());}

        try { new File(pResultados).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido
        try { new File(pConfigPant).delete(); } catch (Exception e) {;} // Sistema LECTOR, SUPRIME lo leido

        if ( regs != null && regs.trim().length() > 0 ) {
        	String[] lstRegs = regs.split( _K.sepReg );
        	resultado = new UsBean[lstRegs.length];
        	int i = 0;
        	for ( String e : lstRegs ) {
            	resultado[i] = new UsBean();
                if ( e != null && e.trim().length() > 0 ) { 
                	resultado[i].deserializar( e );
                }
                i++;
        	}
        } else {
        	resultado = new UsBean[0];
        }

		if ( sCfg != null && sCfg.trim().length() > 0 ) {
			cfg.deserializar(sCfg);
		}

        return resultado;
    }
/////////////////////////////////////////////////
	public JSONObject beanArray2json( UsBean[] lista ) {
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
	public UsBean[] json2beanArray(JSONObject jsonObject) {
		UsBean[] resultado = null;

		ArrayList<UsBean> arrayTmp = new ArrayList<UsBean>();
		JSONArray jsonReg = null;

		if ( jsonObject != null ) {
			int i = 1;
			do {
				jsonReg = null;
				try {
					jsonReg = jsonObject.getJSONArray( "r" + (i++) );
					UsBean registro = new UsBean();
					
				registro.setUs_sincro( jsonReg.getString(0) );	// sincro
				registro.setUs_mark( jsonReg.getString(1) );	// mark
				registro.setUs_is_deleted( jsonReg.getString(2) );	// is_deleted
				registro.setUs_author( jsonReg.getString(3) );	// author
				registro.setUs_user_id( jsonReg.getString(4) );	// user_id
				registro.setUs_role_id( jsonReg.getString(5) );	// role_id
				registro.setUs_hash_code( jsonReg.getString(6) );	// hash_code
				registro.setUs_country_id( jsonReg.getLong(7) );	// country_id
				registro.setUs_PS_name( jsonReg.getString(8) );	// PS_name
				registro.setUs_PS_flag_base64( jsonReg.getString(9) );	// PS_flag_base64
				registro.setUs_nick( jsonReg.getString(10) );	// nick
				registro.setUs_password( jsonReg.getString(11) );	// password
				registro.setUs_first_name( jsonReg.getString(12) );	// first_name
				registro.setUs_last_name( jsonReg.getString(13) );	// last_name
				registro.setUs_phone( jsonReg.getString(14) );	// phone
				registro.setUs_gender( jsonReg.getString(15) );	// gender
				registro.setUs_birth_day( jsonReg.getString(16) );	// birth_day
				registro.setUs_avatar( jsonReg.getString(17) );	// avatar
				registro.setUs_location_id( jsonReg.getString(18) );	// location_id
				registro.setUs_json( jsonReg.getString(19) );	// json
					
					arrayTmp.add(registro);
				} catch (Exception e) {;}
			} while( jsonReg != null );
		}

		//////////////////////////////////////////////
		resultado = new UsBean[arrayTmp.size()];
		resultado = arrayTmp.toArray(resultado);
		arrayTmp.clear();

		return resultado;

	}
/////////////////////////////////////////////////
}
