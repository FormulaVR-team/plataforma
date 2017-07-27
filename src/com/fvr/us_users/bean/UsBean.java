package com.fvr.us_users.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class UsBean extends StBean {

	public String us_sincro; // sincro
	public String us_mark; // mark
	public String us_is_deleted; // is_deleted
	public String us_author; // author
	public String us_user_id; // user_id
	public String us_role_id; // role_id
	public String us_hash_code; // hash_code
	public long   us_country_id; // country_id
	public String us_PS_name; // PS_name
	public String us_PS_flag_base64; // PS_flag_base64
	public String us_nick; // nick
	public String us_password; // password
	public String us_first_name; // first_name
	public String us_last_name; // last_name
	public String us_phone; // phone
	public String us_gender; // gender
	public String us_birth_day; // birth_day
	public String us_avatar; // avatar
	public String us_location_id; // location_id
	public String us_json; // json
    
    public UsBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public UsBean(Object nulo) { super(); }

    public void inicializar() {
	this.setUs_sincro( "" ); // sincro
	this.setUs_mark( "" ); // mark
	this.setUs_is_deleted( "" ); // is_deleted
	this.setUs_author( "" ); // author
	this.setUs_user_id( "" ); // user_id
	this.setUs_role_id( "" ); // role_id
	this.setUs_hash_code( "" ); // hash_code
	this.setUs_country_id( 0 ); // country_id
	this.setUs_PS_name( "" ); // PS_name
	this.setUs_PS_flag_base64( "" ); // PS_flag_base64
	this.setUs_nick( "" ); // nick
	this.setUs_password( "" ); // password
	this.setUs_first_name( "" ); // first_name
	this.setUs_last_name( "" ); // last_name
	this.setUs_phone( "" ); // phone
	this.setUs_gender( "" ); // gender
	this.setUs_birth_day( "" ); // birth_day
	this.setUs_avatar( "" ); // avatar
	this.setUs_location_id( "" ); // location_id
	this.setUs_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        UsBean Destino = (UsBean)beanDestino;

	Destino.setUs_sincro( getUs_sincro() ); // sincro
	Destino.setUs_mark( getUs_mark() ); // mark
	Destino.setUs_is_deleted( getUs_is_deleted() ); // is_deleted
	Destino.setUs_author( getUs_author() ); // author
	Destino.setUs_user_id( getUs_user_id() ); // user_id
	Destino.setUs_role_id( getUs_role_id() ); // role_id
	Destino.setUs_hash_code( getUs_hash_code() ); // hash_code
	Destino.setUs_country_id( getUs_country_id() ); // country_id
	Destino.setUs_PS_name( getUs_PS_name() ); // PS_name
	Destino.setUs_PS_flag_base64( getUs_PS_flag_base64() ); // PS_flag_base64
	Destino.setUs_nick( getUs_nick() ); // nick
	Destino.setUs_password( getUs_password() ); // password
	Destino.setUs_first_name( getUs_first_name() ); // first_name
	Destino.setUs_last_name( getUs_last_name() ); // last_name
	Destino.setUs_phone( getUs_phone() ); // phone
	Destino.setUs_gender( getUs_gender() ); // gender
	Destino.setUs_birth_day( getUs_birth_day() ); // birth_day
	Destino.setUs_avatar( getUs_avatar() ); // avatar
	Destino.setUs_location_id( getUs_location_id() ); // location_id
	Destino.setUs_json( getUs_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        UsBean Origen = (UsBean)beanOrigen;

	setUs_sincro( Origen.getUs_sincro() ); // sincro
	setUs_mark( Origen.getUs_mark() ); // mark
	setUs_is_deleted( Origen.getUs_is_deleted() ); // is_deleted
	setUs_author( Origen.getUs_author() ); // author
	setUs_user_id( Origen.getUs_user_id() ); // user_id
	setUs_role_id( Origen.getUs_role_id() ); // role_id
	setUs_hash_code( Origen.getUs_hash_code() ); // hash_code
	setUs_country_id( Origen.getUs_country_id() ); // country_id
	setUs_PS_name( Origen.getUs_PS_name() ); // PS_name
	setUs_PS_flag_base64( Origen.getUs_PS_flag_base64() ); // PS_flag_base64
	setUs_nick( Origen.getUs_nick() ); // nick
	setUs_password( Origen.getUs_password() ); // password
	setUs_first_name( Origen.getUs_first_name() ); // first_name
	setUs_last_name( Origen.getUs_last_name() ); // last_name
	setUs_phone( Origen.getUs_phone() ); // phone
	setUs_gender( Origen.getUs_gender() ); // gender
	setUs_birth_day( Origen.getUs_birth_day() ); // birth_day
	setUs_avatar( Origen.getUs_avatar() ); // avatar
	setUs_location_id( Origen.getUs_location_id() ); // location_id
	setUs_json( Origen.getUs_json() ); // json
    }
*/


	/** Get sincro*/
	public String getUs_sincro() {return us_sincro;}
	/** Set sincro*/
	public void setUs_sincro(String us_sincro) {this.us_sincro = us_sincro;}

	/** Get mark*/
	public String getUs_mark() {return us_mark;}
	/** Set mark*/
	public void setUs_mark(String us_mark) {this.us_mark = us_mark;}

	/** Get is_deleted*/
	public String getUs_is_deleted() {return us_is_deleted;}
	/** Set is_deleted*/
	public void setUs_is_deleted(String us_is_deleted) {this.us_is_deleted = us_is_deleted;}

	/** Get author*/
	public String getUs_author() {return us_author;}
	/** Set author*/
	public void setUs_author(String us_author) {this.us_author = us_author;}

	/** Get user_id*/
	public String getUs_user_id() {return us_user_id;}
	/** Set user_id*/
	public void setUs_user_id(String us_user_id) {this.us_user_id = us_user_id;}

	/** Get role_id*/
	public String getUs_role_id() {return us_role_id;}
	/** Set role_id*/
	public void setUs_role_id(String us_role_id) {this.us_role_id = us_role_id;}

	/** Get hash_code*/
	public String getUs_hash_code() {return us_hash_code;}
	/** Set hash_code*/
	public void setUs_hash_code(String us_hash_code) {this.us_hash_code = us_hash_code;}

	/** Get country_id*/
	public long getUs_country_id() {return us_country_id;}
	/** Set country_id*/
	public void setUs_country_id(long us_country_id) {this.us_country_id = us_country_id;}

	/** Get PS_name*/
	public String getUs_PS_name() {return us_PS_name;}
	/** Set PS_name*/
	public void setUs_PS_name(String us_PS_name) {this.us_PS_name = us_PS_name;}

	/** Get PS_flag_base64*/
	public String getUs_PS_flag_base64() {return us_PS_flag_base64;}
	/** Set PS_flag_base64*/
	public void setUs_PS_flag_base64(String us_PS_flag_base64) {this.us_PS_flag_base64 = us_PS_flag_base64;}

	/** Get nick*/
	public String getUs_nick() {return us_nick;}
	/** Set nick*/
	public void setUs_nick(String us_nick) {this.us_nick = us_nick;}

	/** Get password*/
	public String getUs_password() {return us_password;}
	/** Set password*/
	public void setUs_password(String us_password) {this.us_password = us_password;}

	/** Get first_name*/
	public String getUs_first_name() {return us_first_name;}
	/** Set first_name*/
	public void setUs_first_name(String us_first_name) {this.us_first_name = us_first_name;}

	/** Get last_name*/
	public String getUs_last_name() {return us_last_name;}
	/** Set last_name*/
	public void setUs_last_name(String us_last_name) {this.us_last_name = us_last_name;}

	/** Get phone*/
	public String getUs_phone() {return us_phone;}
	/** Set phone*/
	public void setUs_phone(String us_phone) {this.us_phone = us_phone;}

	/** Get gender*/
	public String getUs_gender() {return us_gender;}
	/** Set gender*/
	public void setUs_gender(String us_gender) {this.us_gender = us_gender;}

	/** Get birth_day*/
	public String getUs_birth_day() {return us_birth_day;}
	/** Set birth_day*/
	public void setUs_birth_day(String us_birth_day) {this.us_birth_day = us_birth_day;}

	/** Get avatar*/
	public String getUs_avatar() {return us_avatar;}
	/** Set avatar*/
	public void setUs_avatar(String us_avatar) {this.us_avatar = us_avatar;}

	/** Get location_id*/
	public String getUs_location_id() {return us_location_id;}
	/** Set location_id*/
	public void setUs_location_id(String us_location_id) {this.us_location_id = us_location_id;}

	/** Get json*/
	public String getUs_json() {return us_json;}
	/** Set json*/
	public void setUs_json(String us_json) {this.us_json = us_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return us_user_id;}

    public void setKey(String key){
            String k="";
	k = key; this.setUs_user_id( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getUs_sincro()==null?"":this.getUs_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getUs_mark()==null?"":this.getUs_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getUs_is_deleted()==null?"":this.getUs_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getUs_author()==null?"":this.getUs_author() ); // author
		out.append( _K.sepFld ); out.append( this.getUs_user_id()==null?"":this.getUs_user_id() ); // user_id
		out.append( _K.sepFld ); out.append( this.getUs_role_id()==null?"":this.getUs_role_id() ); // role_id
		out.append( _K.sepFld ); out.append( this.getUs_hash_code()==null?"":this.getUs_hash_code() ); // hash_code
		out.append( _K.sepFld ); out.append( this.getUs_country_id() ); // country_id
		out.append( _K.sepFld ); out.append( this.getUs_PS_name()==null?"":this.getUs_PS_name() ); // PS_name
		out.append( _K.sepFld ); out.append( this.getUs_PS_flag_base64()==null?"":this.getUs_PS_flag_base64() ); // PS_flag_base64
		out.append( _K.sepFld ); out.append( this.getUs_nick()==null?"":this.getUs_nick() ); // nick
		out.append( _K.sepFld ); out.append( this.getUs_password()==null?"":this.getUs_password() ); // password
		out.append( _K.sepFld ); out.append( this.getUs_first_name()==null?"":this.getUs_first_name() ); // first_name
		out.append( _K.sepFld ); out.append( this.getUs_last_name()==null?"":this.getUs_last_name() ); // last_name
		out.append( _K.sepFld ); out.append( this.getUs_phone()==null?"":this.getUs_phone() ); // phone
		out.append( _K.sepFld ); out.append( this.getUs_gender()==null?"":this.getUs_gender() ); // gender
		out.append( _K.sepFld ); out.append( this.getUs_birth_day()==null?"":this.getUs_birth_day() ); // birth_day
		out.append( _K.sepFld ); out.append( this.getUs_avatar()==null?"":this.getUs_avatar() ); // avatar
		out.append( _K.sepFld ); out.append( this.getUs_location_id()==null?"":this.getUs_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getUs_json()==null?"":this.getUs_json() ); // json

		out.append( _K.sepReg );
		
		return out.toString();
	}
	public void deserializar(String in) {
		inicializar();
		if ( in != null && in.length() > 0 ) {
			
			String s = in.replaceAll( _K.sepReg, "" );
                   s =  s.replaceAll( _K.sepReg_0x0D, "" );
                   s =  s.replaceAll( _K.sepReg_0x0A, "" );
			String[] trozos = s.split( _K.sepFld );
			
			try { this.setUs_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setUs_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setUs_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setUs_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setUs_user_id( trozos[4] ); } catch (Exception e) {;} // user_id
			try { this.setUs_role_id( trozos[5] ); } catch (Exception e) {;} // role_id
			try { this.setUs_hash_code( trozos[6] ); } catch (Exception e) {;} // hash_code
			try { this.setUs_country_id( Subrutinas.parse_long( trozos[7] )); } catch (Exception e) {;} // country_id
			try { this.setUs_PS_name( trozos[8] ); } catch (Exception e) {;} // PS_name
			try { this.setUs_PS_flag_base64( trozos[9] ); } catch (Exception e) {;} // PS_flag_base64
			try { this.setUs_nick( trozos[10] ); } catch (Exception e) {;} // nick
			try { this.setUs_password( trozos[11] ); } catch (Exception e) {;} // password
			try { this.setUs_first_name( trozos[12] ); } catch (Exception e) {;} // first_name
			try { this.setUs_last_name( trozos[13] ); } catch (Exception e) {;} // last_name
			try { this.setUs_phone( trozos[14] ); } catch (Exception e) {;} // phone
			try { this.setUs_gender( trozos[15] ); } catch (Exception e) {;} // gender
			try { this.setUs_birth_day( trozos[16] ); } catch (Exception e) {;} // birth_day
			try { this.setUs_avatar( trozos[17] ); } catch (Exception e) {;} // avatar
			try { this.setUs_location_id( trozos[18] ); } catch (Exception e) {;} // location_id
			try { this.setUs_json( trozos[19] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
