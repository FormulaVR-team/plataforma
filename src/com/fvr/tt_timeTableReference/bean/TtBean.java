package com.fvr.tt_timeTableReference.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class TtBean extends StBean {

	public String tt_sincro; // sincro
	public String tt_mark; // mark
	public String tt_is_deleted; // is_deleted
	public String tt_author; // author
	public String tt_location_id; // location_id
	public String tt_day_type; // day_type
	public String tt_start_time; // start_time
	public long   tt_duration_minutes; // duration_minutes
	public String tt_isBlocked; // isBlocked
	public String tt_json; // json
    
    public TtBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public TtBean(Object nulo) { super(); }

    public void inicializar() {
	this.setTt_sincro( "" ); // sincro
	this.setTt_mark( "" ); // mark
	this.setTt_is_deleted( "" ); // is_deleted
	this.setTt_author( "" ); // author
	this.setTt_location_id( "" ); // location_id
	this.setTt_day_type( "" ); // day_type
	this.setTt_start_time( "" ); // start_time
	this.setTt_duration_minutes( 0 ); // duration_minutes
	this.setTt_isBlocked( "" ); // isBlocked
	this.setTt_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        TtBean Destino = (TtBean)beanDestino;

	Destino.setTt_sincro( getTt_sincro() ); // sincro
	Destino.setTt_mark( getTt_mark() ); // mark
	Destino.setTt_is_deleted( getTt_is_deleted() ); // is_deleted
	Destino.setTt_author( getTt_author() ); // author
	Destino.setTt_location_id( getTt_location_id() ); // location_id
	Destino.setTt_day_type( getTt_day_type() ); // day_type
	Destino.setTt_start_time( getTt_start_time() ); // start_time
	Destino.setTt_duration_minutes( getTt_duration_minutes() ); // duration_minutes
	Destino.setTt_isBlocked( getTt_isBlocked() ); // isBlocked
	Destino.setTt_json( getTt_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        TtBean Origen = (TtBean)beanOrigen;

	setTt_sincro( Origen.getTt_sincro() ); // sincro
	setTt_mark( Origen.getTt_mark() ); // mark
	setTt_is_deleted( Origen.getTt_is_deleted() ); // is_deleted
	setTt_author( Origen.getTt_author() ); // author
	setTt_location_id( Origen.getTt_location_id() ); // location_id
	setTt_day_type( Origen.getTt_day_type() ); // day_type
	setTt_start_time( Origen.getTt_start_time() ); // start_time
	setTt_duration_minutes( Origen.getTt_duration_minutes() ); // duration_minutes
	setTt_isBlocked( Origen.getTt_isBlocked() ); // isBlocked
	setTt_json( Origen.getTt_json() ); // json
    }
*/


	/** Get sincro*/
	public String getTt_sincro() {return tt_sincro;}
	/** Set sincro*/
	public void setTt_sincro(String tt_sincro) {this.tt_sincro = tt_sincro;}

	/** Get mark*/
	public String getTt_mark() {return tt_mark;}
	/** Set mark*/
	public void setTt_mark(String tt_mark) {this.tt_mark = tt_mark;}

	/** Get is_deleted*/
	public String getTt_is_deleted() {return tt_is_deleted;}
	/** Set is_deleted*/
	public void setTt_is_deleted(String tt_is_deleted) {this.tt_is_deleted = tt_is_deleted;}

	/** Get author*/
	public String getTt_author() {return tt_author;}
	/** Set author*/
	public void setTt_author(String tt_author) {this.tt_author = tt_author;}

	/** Get location_id*/
	public String getTt_location_id() {return tt_location_id;}
	/** Set location_id*/
	public void setTt_location_id(String tt_location_id) {this.tt_location_id = tt_location_id;}

	/** Get day_type*/
	public String getTt_day_type() {return tt_day_type;}
	/** Set day_type*/
	public void setTt_day_type(String tt_day_type) {this.tt_day_type = tt_day_type;}

	/** Get start_time*/
	public String getTt_start_time() {return tt_start_time;}
	/** Set start_time*/
	public void setTt_start_time(String tt_start_time) {this.tt_start_time = tt_start_time;}

	/** Get duration_minutes*/
	public long getTt_duration_minutes() {return tt_duration_minutes;}
	/** Set duration_minutes*/
	public void setTt_duration_minutes(long tt_duration_minutes) {this.tt_duration_minutes = tt_duration_minutes;}

	/** Get isBlocked*/
	public String getTt_isBlocked() {return tt_isBlocked;}
	/** Set isBlocked*/
	public void setTt_isBlocked(String tt_isBlocked) {this.tt_isBlocked = tt_isBlocked;}

	/** Get json*/
	public String getTt_json() {return tt_json;}
	/** Set json*/
	public void setTt_json(String tt_json) {this.tt_json = tt_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return tt_location_id + "^" + 
		tt_day_type + "^" + 
		tt_start_time;}

    public void setKey(String key){
            String k="";
	k = key.trim().substring( 0, key.indexOf("^") ); this.setTt_location_id( k ); key = key.trim().substring( key.indexOf("^")+1 );
	k = key.trim().substring( 0, key.indexOf("^") ); this.setTt_day_type( k ); key = key.trim().substring( key.indexOf("^")+1 );
	k = key; this.setTt_start_time( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getTt_sincro()==null?"":this.getTt_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getTt_mark()==null?"":this.getTt_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getTt_is_deleted()==null?"":this.getTt_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getTt_author()==null?"":this.getTt_author() ); // author
		out.append( _K.sepFld ); out.append( this.getTt_location_id()==null?"":this.getTt_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getTt_day_type()==null?"":this.getTt_day_type() ); // day_type
		out.append( _K.sepFld ); out.append( this.getTt_start_time()==null?"":this.getTt_start_time() ); // start_time
		out.append( _K.sepFld ); out.append( this.getTt_duration_minutes() ); // duration_minutes
		out.append( _K.sepFld ); out.append( this.getTt_isBlocked()==null?"":this.getTt_isBlocked() ); // isBlocked
		out.append( _K.sepFld ); out.append( this.getTt_json()==null?"":this.getTt_json() ); // json

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
			
			try { this.setTt_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setTt_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setTt_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setTt_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setTt_location_id( trozos[4] ); } catch (Exception e) {;} // location_id
			try { this.setTt_day_type( trozos[5] ); } catch (Exception e) {;} // day_type
			try { this.setTt_start_time( trozos[6] ); } catch (Exception e) {;} // start_time
			try { this.setTt_duration_minutes( Subrutinas.parse_long( trozos[7] )); } catch (Exception e) {;} // duration_minutes
			try { this.setTt_isBlocked( trozos[8] ); } catch (Exception e) {;} // isBlocked
			try { this.setTt_json( trozos[9] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
