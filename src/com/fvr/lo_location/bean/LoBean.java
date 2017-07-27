package com.fvr.lo_location.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class LoBean extends StBean {

	public String lo_sincro; // sincro
	public String lo_mark; // mark
	public String lo_is_deleted; // is_deleted
	public String lo_author; // author
	public String lo_location_id; // location_id
	public String lo_name; // name
	public String lo_address; // address
	public String lo_city; // city
	public String lo_postal_code; // postal_code
	public String lo_province; // province
	public String lo_state_region; // state_region
	public String lo_country; // country
	public long   lo_lat; // lat
	public long   lo_lon; // lon
	public String lo_comment; // comment
	public String lo_json; // json
    
    public LoBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public LoBean(Object nulo) { super(); }

    public void inicializar() {
	this.setLo_sincro( "" ); // sincro
	this.setLo_mark( "" ); // mark
	this.setLo_is_deleted( "" ); // is_deleted
	this.setLo_author( "" ); // author
	this.setLo_location_id( "" ); // location_id
	this.setLo_name( "" ); // name
	this.setLo_address( "" ); // address
	this.setLo_city( "" ); // city
	this.setLo_postal_code( "" ); // postal_code
	this.setLo_province( "" ); // province
	this.setLo_state_region( "" ); // state_region
	this.setLo_country( "" ); // country
	this.setLo_lat( 0 ); // lat
	this.setLo_lon( 0 ); // lon
	this.setLo_comment( "" ); // comment
	this.setLo_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        LoBean Destino = (LoBean)beanDestino;

	Destino.setLo_sincro( getLo_sincro() ); // sincro
	Destino.setLo_mark( getLo_mark() ); // mark
	Destino.setLo_is_deleted( getLo_is_deleted() ); // is_deleted
	Destino.setLo_author( getLo_author() ); // author
	Destino.setLo_location_id( getLo_location_id() ); // location_id
	Destino.setLo_name( getLo_name() ); // name
	Destino.setLo_address( getLo_address() ); // address
	Destino.setLo_city( getLo_city() ); // city
	Destino.setLo_postal_code( getLo_postal_code() ); // postal_code
	Destino.setLo_province( getLo_province() ); // province
	Destino.setLo_state_region( getLo_state_region() ); // state_region
	Destino.setLo_country( getLo_country() ); // country
	Destino.setLo_lat( getLo_lat() ); // lat
	Destino.setLo_lon( getLo_lon() ); // lon
	Destino.setLo_comment( getLo_comment() ); // comment
	Destino.setLo_json( getLo_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        LoBean Origen = (LoBean)beanOrigen;

	setLo_sincro( Origen.getLo_sincro() ); // sincro
	setLo_mark( Origen.getLo_mark() ); // mark
	setLo_is_deleted( Origen.getLo_is_deleted() ); // is_deleted
	setLo_author( Origen.getLo_author() ); // author
	setLo_location_id( Origen.getLo_location_id() ); // location_id
	setLo_name( Origen.getLo_name() ); // name
	setLo_address( Origen.getLo_address() ); // address
	setLo_city( Origen.getLo_city() ); // city
	setLo_postal_code( Origen.getLo_postal_code() ); // postal_code
	setLo_province( Origen.getLo_province() ); // province
	setLo_state_region( Origen.getLo_state_region() ); // state_region
	setLo_country( Origen.getLo_country() ); // country
	setLo_lat( Origen.getLo_lat() ); // lat
	setLo_lon( Origen.getLo_lon() ); // lon
	setLo_comment( Origen.getLo_comment() ); // comment
	setLo_json( Origen.getLo_json() ); // json
    }
*/


	/** Get sincro*/
	public String getLo_sincro() {return lo_sincro;}
	/** Set sincro*/
	public void setLo_sincro(String lo_sincro) {this.lo_sincro = lo_sincro;}

	/** Get mark*/
	public String getLo_mark() {return lo_mark;}
	/** Set mark*/
	public void setLo_mark(String lo_mark) {this.lo_mark = lo_mark;}

	/** Get is_deleted*/
	public String getLo_is_deleted() {return lo_is_deleted;}
	/** Set is_deleted*/
	public void setLo_is_deleted(String lo_is_deleted) {this.lo_is_deleted = lo_is_deleted;}

	/** Get author*/
	public String getLo_author() {return lo_author;}
	/** Set author*/
	public void setLo_author(String lo_author) {this.lo_author = lo_author;}

	/** Get location_id*/
	public String getLo_location_id() {return lo_location_id;}
	/** Set location_id*/
	public void setLo_location_id(String lo_location_id) {this.lo_location_id = lo_location_id;}

	/** Get name*/
	public String getLo_name() {return lo_name;}
	/** Set name*/
	public void setLo_name(String lo_name) {this.lo_name = lo_name;}

	/** Get address*/
	public String getLo_address() {return lo_address;}
	/** Set address*/
	public void setLo_address(String lo_address) {this.lo_address = lo_address;}

	/** Get city*/
	public String getLo_city() {return lo_city;}
	/** Set city*/
	public void setLo_city(String lo_city) {this.lo_city = lo_city;}

	/** Get postal_code*/
	public String getLo_postal_code() {return lo_postal_code;}
	/** Set postal_code*/
	public void setLo_postal_code(String lo_postal_code) {this.lo_postal_code = lo_postal_code;}

	/** Get province*/
	public String getLo_province() {return lo_province;}
	/** Set province*/
	public void setLo_province(String lo_province) {this.lo_province = lo_province;}

	/** Get state_region*/
	public String getLo_state_region() {return lo_state_region;}
	/** Set state_region*/
	public void setLo_state_region(String lo_state_region) {this.lo_state_region = lo_state_region;}

	/** Get country*/
	public String getLo_country() {return lo_country;}
	/** Set country*/
	public void setLo_country(String lo_country) {this.lo_country = lo_country;}

	/** Get lat*/
	public long getLo_lat() {return lo_lat;}
	/** Set lat*/
	public void setLo_lat(long lo_lat) {this.lo_lat = lo_lat;}

	/** Get lon*/
	public long getLo_lon() {return lo_lon;}
	/** Set lon*/
	public void setLo_lon(long lo_lon) {this.lo_lon = lo_lon;}

	/** Get comment*/
	public String getLo_comment() {return lo_comment;}
	/** Set comment*/
	public void setLo_comment(String lo_comment) {this.lo_comment = lo_comment;}

	/** Get json*/
	public String getLo_json() {return lo_json;}
	/** Set json*/
	public void setLo_json(String lo_json) {this.lo_json = lo_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return lo_location_id;}

    public void setKey(String key){
            String k="";
	k = key; this.setLo_location_id( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getLo_sincro()==null?"":this.getLo_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getLo_mark()==null?"":this.getLo_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getLo_is_deleted()==null?"":this.getLo_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getLo_author()==null?"":this.getLo_author() ); // author
		out.append( _K.sepFld ); out.append( this.getLo_location_id()==null?"":this.getLo_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getLo_name()==null?"":this.getLo_name() ); // name
		out.append( _K.sepFld ); out.append( this.getLo_address()==null?"":this.getLo_address() ); // address
		out.append( _K.sepFld ); out.append( this.getLo_city()==null?"":this.getLo_city() ); // city
		out.append( _K.sepFld ); out.append( this.getLo_postal_code()==null?"":this.getLo_postal_code() ); // postal_code
		out.append( _K.sepFld ); out.append( this.getLo_province()==null?"":this.getLo_province() ); // province
		out.append( _K.sepFld ); out.append( this.getLo_state_region()==null?"":this.getLo_state_region() ); // state_region
		out.append( _K.sepFld ); out.append( this.getLo_country()==null?"":this.getLo_country() ); // country
		out.append( _K.sepFld ); out.append( this.getLo_lat() ); // lat
		out.append( _K.sepFld ); out.append( this.getLo_lon() ); // lon
		out.append( _K.sepFld ); out.append( this.getLo_comment()==null?"":this.getLo_comment() ); // comment
		out.append( _K.sepFld ); out.append( this.getLo_json()==null?"":this.getLo_json() ); // json

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
			
			try { this.setLo_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setLo_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setLo_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setLo_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setLo_location_id( trozos[4] ); } catch (Exception e) {;} // location_id
			try { this.setLo_name( trozos[5] ); } catch (Exception e) {;} // name
			try { this.setLo_address( trozos[6] ); } catch (Exception e) {;} // address
			try { this.setLo_city( trozos[7] ); } catch (Exception e) {;} // city
			try { this.setLo_postal_code( trozos[8] ); } catch (Exception e) {;} // postal_code
			try { this.setLo_province( trozos[9] ); } catch (Exception e) {;} // province
			try { this.setLo_state_region( trozos[10] ); } catch (Exception e) {;} // state_region
			try { this.setLo_country( trozos[11] ); } catch (Exception e) {;} // country
			try { this.setLo_lat( Subrutinas.parse_long( trozos[12] )); } catch (Exception e) {;} // lat
			try { this.setLo_lon( Subrutinas.parse_long( trozos[13] )); } catch (Exception e) {;} // lon
			try { this.setLo_comment( trozos[14] ); } catch (Exception e) {;} // comment
			try { this.setLo_json( trozos[15] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
