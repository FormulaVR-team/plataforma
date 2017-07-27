package com.fvr.cp_cockpits.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class CpBean extends StBean {

	public String cp_sincro; // sincro
	public String cp_mark; // mark
	public String cp_is_deleted; // is_deleted
	public String cp_author; // author
	public String cp_location_id; // location_id
	public String cp_LO_name; // LO_name
	public String cp_LO_address; // LO_address
	public String cp_LO_city; // LO_city
	public String cp_LO_postal_code; // LO_postal_code
	public long   cp_LO_lat; // LO_lat
	public long   cp_LO_lon; // LO_lon
	public String cp_cockpit_id; // cockpit_id
	public String cp_serial_number; // serial_number
	public String cp_name; // name
	public String cp_isBlocked; // isBlocked
	public long   cp_asignation_order; // asignation_order
	public String cp_install_date; // install_date
	public String cp_reset_date_used; // reset_date_used
	public long   cp_hours_used; // hours_used
	public String cp_note; // note
	public String cp_comment; // comment
	public String cp_observation; // observation
	public String cp_warning; // warning
	public String cp_contact_service; // contact_service
    
    public CpBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public CpBean(Object nulo) { super(); }

    public void inicializar() {
	this.setCp_sincro( "" ); // sincro
	this.setCp_mark( "" ); // mark
	this.setCp_is_deleted( "" ); // is_deleted
	this.setCp_author( "" ); // author
	this.setCp_location_id( "" ); // location_id
	this.setCp_LO_name( "" ); // LO_name
	this.setCp_LO_address( "" ); // LO_address
	this.setCp_LO_city( "" ); // LO_city
	this.setCp_LO_postal_code( "" ); // LO_postal_code
	this.setCp_LO_lat( 0 ); // LO_lat
	this.setCp_LO_lon( 0 ); // LO_lon
	this.setCp_cockpit_id( "" ); // cockpit_id
	this.setCp_serial_number( "" ); // serial_number
	this.setCp_name( "" ); // name
	this.setCp_isBlocked( "" ); // isBlocked
	this.setCp_asignation_order( 0 ); // asignation_order
	this.setCp_install_date( "" ); // install_date
	this.setCp_reset_date_used( "" ); // reset_date_used
	this.setCp_hours_used( 0 ); // hours_used
	this.setCp_note( "" ); // note
	this.setCp_comment( "" ); // comment
	this.setCp_observation( "" ); // observation
	this.setCp_warning( "" ); // warning
	this.setCp_contact_service( "" ); // contact_service
    } 
 /*
    public void copyTo(StBean beanDestino) {
        CpBean Destino = (CpBean)beanDestino;

	Destino.setCp_sincro( getCp_sincro() ); // sincro
	Destino.setCp_mark( getCp_mark() ); // mark
	Destino.setCp_is_deleted( getCp_is_deleted() ); // is_deleted
	Destino.setCp_author( getCp_author() ); // author
	Destino.setCp_location_id( getCp_location_id() ); // location_id
	Destino.setCp_LO_name( getCp_LO_name() ); // LO_name
	Destino.setCp_LO_address( getCp_LO_address() ); // LO_address
	Destino.setCp_LO_city( getCp_LO_city() ); // LO_city
	Destino.setCp_LO_postal_code( getCp_LO_postal_code() ); // LO_postal_code
	Destino.setCp_LO_lat( getCp_LO_lat() ); // LO_lat
	Destino.setCp_LO_lon( getCp_LO_lon() ); // LO_lon
	Destino.setCp_cockpit_id( getCp_cockpit_id() ); // cockpit_id
	Destino.setCp_serial_number( getCp_serial_number() ); // serial_number
	Destino.setCp_name( getCp_name() ); // name
	Destino.setCp_isBlocked( getCp_isBlocked() ); // isBlocked
	Destino.setCp_asignation_order( getCp_asignation_order() ); // asignation_order
	Destino.setCp_install_date( getCp_install_date() ); // install_date
	Destino.setCp_reset_date_used( getCp_reset_date_used() ); // reset_date_used
	Destino.setCp_hours_used( getCp_hours_used() ); // hours_used
	Destino.setCp_note( getCp_note() ); // note
	Destino.setCp_comment( getCp_comment() ); // comment
	Destino.setCp_observation( getCp_observation() ); // observation
	Destino.setCp_warning( getCp_warning() ); // warning
	Destino.setCp_contact_service( getCp_contact_service() ); // contact_service
    }
    
    public void copyFrom(StBean beanOrigen) {
        CpBean Origen = (CpBean)beanOrigen;

	setCp_sincro( Origen.getCp_sincro() ); // sincro
	setCp_mark( Origen.getCp_mark() ); // mark
	setCp_is_deleted( Origen.getCp_is_deleted() ); // is_deleted
	setCp_author( Origen.getCp_author() ); // author
	setCp_location_id( Origen.getCp_location_id() ); // location_id
	setCp_LO_name( Origen.getCp_LO_name() ); // LO_name
	setCp_LO_address( Origen.getCp_LO_address() ); // LO_address
	setCp_LO_city( Origen.getCp_LO_city() ); // LO_city
	setCp_LO_postal_code( Origen.getCp_LO_postal_code() ); // LO_postal_code
	setCp_LO_lat( Origen.getCp_LO_lat() ); // LO_lat
	setCp_LO_lon( Origen.getCp_LO_lon() ); // LO_lon
	setCp_cockpit_id( Origen.getCp_cockpit_id() ); // cockpit_id
	setCp_serial_number( Origen.getCp_serial_number() ); // serial_number
	setCp_name( Origen.getCp_name() ); // name
	setCp_isBlocked( Origen.getCp_isBlocked() ); // isBlocked
	setCp_asignation_order( Origen.getCp_asignation_order() ); // asignation_order
	setCp_install_date( Origen.getCp_install_date() ); // install_date
	setCp_reset_date_used( Origen.getCp_reset_date_used() ); // reset_date_used
	setCp_hours_used( Origen.getCp_hours_used() ); // hours_used
	setCp_note( Origen.getCp_note() ); // note
	setCp_comment( Origen.getCp_comment() ); // comment
	setCp_observation( Origen.getCp_observation() ); // observation
	setCp_warning( Origen.getCp_warning() ); // warning
	setCp_contact_service( Origen.getCp_contact_service() ); // contact_service
    }
*/


	/** Get sincro*/
	public String getCp_sincro() {return cp_sincro;}
	/** Set sincro*/
	public void setCp_sincro(String cp_sincro) {this.cp_sincro = cp_sincro;}

	/** Get mark*/
	public String getCp_mark() {return cp_mark;}
	/** Set mark*/
	public void setCp_mark(String cp_mark) {this.cp_mark = cp_mark;}

	/** Get is_deleted*/
	public String getCp_is_deleted() {return cp_is_deleted;}
	/** Set is_deleted*/
	public void setCp_is_deleted(String cp_is_deleted) {this.cp_is_deleted = cp_is_deleted;}

	/** Get author*/
	public String getCp_author() {return cp_author;}
	/** Set author*/
	public void setCp_author(String cp_author) {this.cp_author = cp_author;}

	/** Get location_id*/
	public String getCp_location_id() {return cp_location_id;}
	/** Set location_id*/
	public void setCp_location_id(String cp_location_id) {this.cp_location_id = cp_location_id;}

	/** Get LO_name*/
	public String getCp_LO_name() {return cp_LO_name;}
	/** Set LO_name*/
	public void setCp_LO_name(String cp_LO_name) {this.cp_LO_name = cp_LO_name;}

	/** Get LO_address*/
	public String getCp_LO_address() {return cp_LO_address;}
	/** Set LO_address*/
	public void setCp_LO_address(String cp_LO_address) {this.cp_LO_address = cp_LO_address;}

	/** Get LO_city*/
	public String getCp_LO_city() {return cp_LO_city;}
	/** Set LO_city*/
	public void setCp_LO_city(String cp_LO_city) {this.cp_LO_city = cp_LO_city;}

	/** Get LO_postal_code*/
	public String getCp_LO_postal_code() {return cp_LO_postal_code;}
	/** Set LO_postal_code*/
	public void setCp_LO_postal_code(String cp_LO_postal_code) {this.cp_LO_postal_code = cp_LO_postal_code;}

	/** Get LO_lat*/
	public long getCp_LO_lat() {return cp_LO_lat;}
	/** Set LO_lat*/
	public void setCp_LO_lat(long cp_LO_lat) {this.cp_LO_lat = cp_LO_lat;}

	/** Get LO_lon*/
	public long getCp_LO_lon() {return cp_LO_lon;}
	/** Set LO_lon*/
	public void setCp_LO_lon(long cp_LO_lon) {this.cp_LO_lon = cp_LO_lon;}

	/** Get cockpit_id*/
	public String getCp_cockpit_id() {return cp_cockpit_id;}
	/** Set cockpit_id*/
	public void setCp_cockpit_id(String cp_cockpit_id) {this.cp_cockpit_id = cp_cockpit_id;}

	/** Get serial_number*/
	public String getCp_serial_number() {return cp_serial_number;}
	/** Set serial_number*/
	public void setCp_serial_number(String cp_serial_number) {this.cp_serial_number = cp_serial_number;}

	/** Get name*/
	public String getCp_name() {return cp_name;}
	/** Set name*/
	public void setCp_name(String cp_name) {this.cp_name = cp_name;}

	/** Get isBlocked*/
	public String getCp_isBlocked() {return cp_isBlocked;}
	/** Set isBlocked*/
	public void setCp_isBlocked(String cp_isBlocked) {this.cp_isBlocked = cp_isBlocked;}

	/** Get asignation_order*/
	public long getCp_asignation_order() {return cp_asignation_order;}
	/** Set asignation_order*/
	public void setCp_asignation_order(long cp_asignation_order) {this.cp_asignation_order = cp_asignation_order;}

	/** Get install_date*/
	public String getCp_install_date() {return cp_install_date;}
	/** Set install_date*/
	public void setCp_install_date(String cp_install_date) {this.cp_install_date = cp_install_date;}

	/** Get reset_date_used*/
	public String getCp_reset_date_used() {return cp_reset_date_used;}
	/** Set reset_date_used*/
	public void setCp_reset_date_used(String cp_reset_date_used) {this.cp_reset_date_used = cp_reset_date_used;}

	/** Get hours_used*/
	public long getCp_hours_used() {return cp_hours_used;}
	/** Set hours_used*/
	public void setCp_hours_used(long cp_hours_used) {this.cp_hours_used = cp_hours_used;}

	/** Get note*/
	public String getCp_note() {return cp_note;}
	/** Set note*/
	public void setCp_note(String cp_note) {this.cp_note = cp_note;}

	/** Get comment*/
	public String getCp_comment() {return cp_comment;}
	/** Set comment*/
	public void setCp_comment(String cp_comment) {this.cp_comment = cp_comment;}

	/** Get observation*/
	public String getCp_observation() {return cp_observation;}
	/** Set observation*/
	public void setCp_observation(String cp_observation) {this.cp_observation = cp_observation;}

	/** Get warning*/
	public String getCp_warning() {return cp_warning;}
	/** Set warning*/
	public void setCp_warning(String cp_warning) {this.cp_warning = cp_warning;}

	/** Get contact_service*/
	public String getCp_contact_service() {return cp_contact_service;}
	/** Set contact_service*/
	public void setCp_contact_service(String cp_contact_service) {this.cp_contact_service = cp_contact_service;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return cp_cockpit_id + "^" + 
		cp_location_id;}

    public void setKey(String key){
            String k="";
	k = key.trim().substring( 0, key.indexOf("^") ); this.setCp_cockpit_id( k ); key = key.trim().substring( key.indexOf("^")+1 );
	k = key; this.setCp_location_id( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getCp_sincro()==null?"":this.getCp_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getCp_mark()==null?"":this.getCp_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getCp_is_deleted()==null?"":this.getCp_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getCp_author()==null?"":this.getCp_author() ); // author
		out.append( _K.sepFld ); out.append( this.getCp_location_id()==null?"":this.getCp_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getCp_LO_name()==null?"":this.getCp_LO_name() ); // LO_name
		out.append( _K.sepFld ); out.append( this.getCp_LO_address()==null?"":this.getCp_LO_address() ); // LO_address
		out.append( _K.sepFld ); out.append( this.getCp_LO_city()==null?"":this.getCp_LO_city() ); // LO_city
		out.append( _K.sepFld ); out.append( this.getCp_LO_postal_code()==null?"":this.getCp_LO_postal_code() ); // LO_postal_code
		out.append( _K.sepFld ); out.append( this.getCp_LO_lat() ); // LO_lat
		out.append( _K.sepFld ); out.append( this.getCp_LO_lon() ); // LO_lon
		out.append( _K.sepFld ); out.append( this.getCp_cockpit_id()==null?"":this.getCp_cockpit_id() ); // cockpit_id
		out.append( _K.sepFld ); out.append( this.getCp_serial_number()==null?"":this.getCp_serial_number() ); // serial_number
		out.append( _K.sepFld ); out.append( this.getCp_name()==null?"":this.getCp_name() ); // name
		out.append( _K.sepFld ); out.append( this.getCp_isBlocked()==null?"":this.getCp_isBlocked() ); // isBlocked
		out.append( _K.sepFld ); out.append( this.getCp_asignation_order() ); // asignation_order
		out.append( _K.sepFld ); out.append( this.getCp_install_date()==null?"":this.getCp_install_date() ); // install_date
		out.append( _K.sepFld ); out.append( this.getCp_reset_date_used()==null?"":this.getCp_reset_date_used() ); // reset_date_used
		out.append( _K.sepFld ); out.append( this.getCp_hours_used() ); // hours_used
		out.append( _K.sepFld ); out.append( this.getCp_note()==null?"":this.getCp_note() ); // note
		out.append( _K.sepFld ); out.append( this.getCp_comment()==null?"":this.getCp_comment() ); // comment
		out.append( _K.sepFld ); out.append( this.getCp_observation()==null?"":this.getCp_observation() ); // observation
		out.append( _K.sepFld ); out.append( this.getCp_warning()==null?"":this.getCp_warning() ); // warning
		out.append( _K.sepFld ); out.append( this.getCp_contact_service()==null?"":this.getCp_contact_service() ); // contact_service

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
			
			try { this.setCp_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setCp_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setCp_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setCp_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setCp_location_id( trozos[4] ); } catch (Exception e) {;} // location_id
			try { this.setCp_LO_name( trozos[5] ); } catch (Exception e) {;} // LO_name
			try { this.setCp_LO_address( trozos[6] ); } catch (Exception e) {;} // LO_address
			try { this.setCp_LO_city( trozos[7] ); } catch (Exception e) {;} // LO_city
			try { this.setCp_LO_postal_code( trozos[8] ); } catch (Exception e) {;} // LO_postal_code
			try { this.setCp_LO_lat( Subrutinas.parse_long( trozos[9] )); } catch (Exception e) {;} // LO_lat
			try { this.setCp_LO_lon( Subrutinas.parse_long( trozos[10] )); } catch (Exception e) {;} // LO_lon
			try { this.setCp_cockpit_id( trozos[11] ); } catch (Exception e) {;} // cockpit_id
			try { this.setCp_serial_number( trozos[12] ); } catch (Exception e) {;} // serial_number
			try { this.setCp_name( trozos[13] ); } catch (Exception e) {;} // name
			try { this.setCp_isBlocked( trozos[14] ); } catch (Exception e) {;} // isBlocked
			try { this.setCp_asignation_order( Subrutinas.parse_long( trozos[15] )); } catch (Exception e) {;} // asignation_order
			try { this.setCp_install_date( trozos[16] ); } catch (Exception e) {;} // install_date
			try { this.setCp_reset_date_used( trozos[17] ); } catch (Exception e) {;} // reset_date_used
			try { this.setCp_hours_used( Subrutinas.parse_long( trozos[18] )); } catch (Exception e) {;} // hours_used
			try { this.setCp_note( trozos[19] ); } catch (Exception e) {;} // note
			try { this.setCp_comment( trozos[20] ); } catch (Exception e) {;} // comment
			try { this.setCp_observation( trozos[21] ); } catch (Exception e) {;} // observation
			try { this.setCp_warning( trozos[22] ); } catch (Exception e) {;} // warning
			try { this.setCp_contact_service( trozos[23] ); } catch (Exception e) {;} // contact_service
			
		}
	}
	////////////////////////////////////////////////////////////

}
