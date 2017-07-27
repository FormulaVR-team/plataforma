package com.fvr.ts_timeSlices.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class TsBeanFiltro extends StBean {

	public String ts_sincro; // sincro
	public String ts_mark; // mark
	public String ts_is_deleted; // is_deleted
	public String ts_author; // author
	public String ts_reservation_id; // reservation_id
	public String ts_RS_user_id; // RS_user_id
	public String ts_RS_location_id; // RS_location_id
	public String ts_RS_start_date; // RS_start_date
	public String ts_RS_start_time; // RS_start_time
	public String ts_RS_pay_status; // RS_pay_status
	public String ts_RS_product_id; // RS_product_id
	public String ts_RS_quantity; // RS_quantity
	public String ts_RS_duration_minutes; // RS_duration_minutes
	public String ts_RS_places; // RS_places
	public String ts_start_date; // start_date
	public String ts_start_time; // start_time
	public String ts_json; // json
    
    public TsBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public TsBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setTs_sincro( "" ); // sincro
	this.setTs_mark( "" ); // mark
	this.setTs_is_deleted( "" ); // is_deleted
	this.setTs_author( "" ); // author
	this.setTs_reservation_id( "" ); // reservation_id
	this.setTs_RS_user_id( "" ); // RS_user_id
	this.setTs_RS_location_id( "" ); // RS_location_id
	this.setTs_RS_start_date( "" ); // RS_start_date
	this.setTs_RS_start_time( "" ); // RS_start_time
	this.setTs_RS_pay_status( "" ); // RS_pay_status
	this.setTs_RS_product_id( "" ); // RS_product_id
	this.setTs_RS_quantity( "" ); // RS_quantity
	this.setTs_RS_duration_minutes( "" ); // RS_duration_minutes
	this.setTs_RS_places( "" ); // RS_places
	this.setTs_start_date( "" ); // start_date
	this.setTs_start_time( "" ); // start_time
	this.setTs_json( "" ); // json
    } 

    public TsBeanFiltro coalesce(TsBeanFiltro pri, TsBeanFiltro sec) {
        TsBeanFiltro r = new TsBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setTs_sincro( (pri.getTs_sincro()==null)?sec.getTs_sincro():pri.getTs_sincro() );	// sincro
		r.setTs_mark( (pri.getTs_mark()==null)?sec.getTs_mark():pri.getTs_mark() );	// mark
		r.setTs_is_deleted( (pri.getTs_is_deleted()==null)?sec.getTs_is_deleted():pri.getTs_is_deleted() );	// is_deleted
		r.setTs_author( (pri.getTs_author()==null)?sec.getTs_author():pri.getTs_author() );	// author
		r.setTs_reservation_id( (pri.getTs_reservation_id()==null)?sec.getTs_reservation_id():pri.getTs_reservation_id() );	// reservation_id
		r.setTs_RS_user_id( (pri.getTs_RS_user_id()==null)?sec.getTs_RS_user_id():pri.getTs_RS_user_id() );	// RS_user_id
		r.setTs_RS_location_id( (pri.getTs_RS_location_id()==null)?sec.getTs_RS_location_id():pri.getTs_RS_location_id() );	// RS_location_id
		r.setTs_RS_start_date( (pri.getTs_RS_start_date()==null)?sec.getTs_RS_start_date():pri.getTs_RS_start_date() );	// RS_start_date
		r.setTs_RS_start_time( (pri.getTs_RS_start_time()==null)?sec.getTs_RS_start_time():pri.getTs_RS_start_time() );	// RS_start_time
		r.setTs_RS_pay_status( (pri.getTs_RS_pay_status()==null)?sec.getTs_RS_pay_status():pri.getTs_RS_pay_status() );	// RS_pay_status
		r.setTs_RS_product_id( (pri.getTs_RS_product_id()==null)?sec.getTs_RS_product_id():pri.getTs_RS_product_id() );	// RS_product_id
		r.setTs_RS_quantity( (pri.getTs_RS_quantity()==null)?sec.getTs_RS_quantity():pri.getTs_RS_quantity() );	// RS_quantity
		r.setTs_RS_duration_minutes( (pri.getTs_RS_duration_minutes()==null)?sec.getTs_RS_duration_minutes():pri.getTs_RS_duration_minutes() );	// RS_duration_minutes
		r.setTs_RS_places( (pri.getTs_RS_places()==null)?sec.getTs_RS_places():pri.getTs_RS_places() );	// RS_places
		r.setTs_start_date( (pri.getTs_start_date()==null)?sec.getTs_start_date():pri.getTs_start_date() );	// start_date
		r.setTs_start_time( (pri.getTs_start_time()==null)?sec.getTs_start_time():pri.getTs_start_time() );	// start_time
		r.setTs_json( (pri.getTs_json()==null)?sec.getTs_json():pri.getTs_json() );	// json
        }
        return r;
    }
    
    public void copyTo(TsBeanFiltro Destino) {
	Destino.setTs_sincro( getTs_sincro() ); // sincro
	Destino.setTs_mark( getTs_mark() ); // mark
	Destino.setTs_is_deleted( getTs_is_deleted() ); // is_deleted
	Destino.setTs_author( getTs_author() ); // author
	Destino.setTs_reservation_id( getTs_reservation_id() ); // reservation_id
	Destino.setTs_RS_user_id( getTs_RS_user_id() ); // RS_user_id
	Destino.setTs_RS_location_id( getTs_RS_location_id() ); // RS_location_id
	Destino.setTs_RS_start_date( getTs_RS_start_date() ); // RS_start_date
	Destino.setTs_RS_start_time( getTs_RS_start_time() ); // RS_start_time
	Destino.setTs_RS_pay_status( getTs_RS_pay_status() ); // RS_pay_status
	Destino.setTs_RS_product_id( getTs_RS_product_id() ); // RS_product_id
	Destino.setTs_RS_quantity( getTs_RS_quantity() ); // RS_quantity
	Destino.setTs_RS_duration_minutes( getTs_RS_duration_minutes() ); // RS_duration_minutes
	Destino.setTs_RS_places( getTs_RS_places() ); // RS_places
	Destino.setTs_start_date( getTs_start_date() ); // start_date
	Destino.setTs_start_time( getTs_start_time() ); // start_time
	Destino.setTs_json( getTs_json() ); // json
    }
    
    public void copyFrom(TsBeanFiltro Origen) {
	setTs_sincro( Origen.getTs_sincro() ); // sincro
	setTs_mark( Origen.getTs_mark() ); // mark
	setTs_is_deleted( Origen.getTs_is_deleted() ); // is_deleted
	setTs_author( Origen.getTs_author() ); // author
	setTs_reservation_id( Origen.getTs_reservation_id() ); // reservation_id
	setTs_RS_user_id( Origen.getTs_RS_user_id() ); // RS_user_id
	setTs_RS_location_id( Origen.getTs_RS_location_id() ); // RS_location_id
	setTs_RS_start_date( Origen.getTs_RS_start_date() ); // RS_start_date
	setTs_RS_start_time( Origen.getTs_RS_start_time() ); // RS_start_time
	setTs_RS_pay_status( Origen.getTs_RS_pay_status() ); // RS_pay_status
	setTs_RS_product_id( Origen.getTs_RS_product_id() ); // RS_product_id
	setTs_RS_quantity( Origen.getTs_RS_quantity() ); // RS_quantity
	setTs_RS_duration_minutes( Origen.getTs_RS_duration_minutes() ); // RS_duration_minutes
	setTs_RS_places( Origen.getTs_RS_places() ); // RS_places
	setTs_start_date( Origen.getTs_start_date() ); // start_date
	setTs_start_time( Origen.getTs_start_time() ); // start_time
	setTs_json( Origen.getTs_json() ); // json
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getTs_sincro()==null?"":this.getTs_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getTs_mark()==null?"":this.getTs_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getTs_is_deleted()==null?"":this.getTs_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getTs_author()==null?"":this.getTs_author() ); // author
		out.append( _K.sepFld ); out.append( this.getTs_reservation_id()==null?"":this.getTs_reservation_id() ); // reservation_id
		out.append( _K.sepFld ); out.append( this.getTs_RS_user_id()==null?"":this.getTs_RS_user_id() ); // RS_user_id
		out.append( _K.sepFld ); out.append( this.getTs_RS_location_id()==null?"":this.getTs_RS_location_id() ); // RS_location_id
		out.append( _K.sepFld ); out.append( this.getTs_RS_start_date()==null?"":this.getTs_RS_start_date() ); // RS_start_date
		out.append( _K.sepFld ); out.append( this.getTs_RS_start_time()==null?"":this.getTs_RS_start_time() ); // RS_start_time
		out.append( _K.sepFld ); out.append( this.getTs_RS_pay_status()==null?"":this.getTs_RS_pay_status() ); // RS_pay_status
		out.append( _K.sepFld ); out.append( this.getTs_RS_product_id()==null?"":this.getTs_RS_product_id() ); // RS_product_id
		out.append( _K.sepFld ); out.append( this.getTs_RS_quantity()==null?"":this.getTs_RS_quantity() ); // RS_quantity
		out.append( _K.sepFld ); out.append( this.getTs_RS_duration_minutes()==null?"":this.getTs_RS_duration_minutes() ); // RS_duration_minutes
		out.append( _K.sepFld ); out.append( this.getTs_RS_places()==null?"":this.getTs_RS_places() ); // RS_places
		out.append( _K.sepFld ); out.append( this.getTs_start_date()==null?"":this.getTs_start_date() ); // start_date
		out.append( _K.sepFld ); out.append( this.getTs_start_time()==null?"":this.getTs_start_time() ); // start_time
		out.append( _K.sepFld ); out.append( this.getTs_json()==null?"":this.getTs_json() ); // json

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
			
			try { this.setTs_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setTs_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setTs_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setTs_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setTs_reservation_id( trozos[4] ); } catch (Exception e) {;} // reservation_id
			try { this.setTs_RS_user_id( trozos[5] ); } catch (Exception e) {;} // RS_user_id
			try { this.setTs_RS_location_id( trozos[6] ); } catch (Exception e) {;} // RS_location_id
			try { this.setTs_RS_start_date( trozos[7] ); } catch (Exception e) {;} // RS_start_date
			try { this.setTs_RS_start_time( trozos[8] ); } catch (Exception e) {;} // RS_start_time
			try { this.setTs_RS_pay_status( trozos[9] ); } catch (Exception e) {;} // RS_pay_status
			try { this.setTs_RS_product_id( trozos[10] ); } catch (Exception e) {;} // RS_product_id
			try { this.setTs_RS_quantity( trozos[11] ); } catch (Exception e) {;} // RS_quantity
			try { this.setTs_RS_duration_minutes( trozos[12] ); } catch (Exception e) {;} // RS_duration_minutes
			try { this.setTs_RS_places( trozos[13] ); } catch (Exception e) {;} // RS_places
			try { this.setTs_start_date( trozos[14] ); } catch (Exception e) {;} // start_date
			try { this.setTs_start_time( trozos[15] ); } catch (Exception e) {;} // start_time
			try { this.setTs_json( trozos[16] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get sincro*/
	public String getTs_sincro() {return ts_sincro;}
	/** Set sincro*/
	public void setTs_sincro(String ts_sincro) {this.ts_sincro = ts_sincro;}

	/** Get mark*/
	public String getTs_mark() {return ts_mark;}
	/** Set mark*/
	public void setTs_mark(String ts_mark) {this.ts_mark = ts_mark;}

	/** Get is_deleted*/
	public String getTs_is_deleted() {return ts_is_deleted;}
	/** Set is_deleted*/
	public void setTs_is_deleted(String ts_is_deleted) {this.ts_is_deleted = ts_is_deleted;}

	/** Get author*/
	public String getTs_author() {return ts_author;}
	/** Set author*/
	public void setTs_author(String ts_author) {this.ts_author = ts_author;}

	/** Get reservation_id*/
	public String getTs_reservation_id() {return ts_reservation_id;}
	/** Set reservation_id*/
	public void setTs_reservation_id(String ts_reservation_id) {this.ts_reservation_id = ts_reservation_id;}

	/** Get RS_user_id*/
	public String getTs_RS_user_id() {return ts_RS_user_id;}
	/** Set RS_user_id*/
	public void setTs_RS_user_id(String ts_RS_user_id) {this.ts_RS_user_id = ts_RS_user_id;}

	/** Get RS_location_id*/
	public String getTs_RS_location_id() {return ts_RS_location_id;}
	/** Set RS_location_id*/
	public void setTs_RS_location_id(String ts_RS_location_id) {this.ts_RS_location_id = ts_RS_location_id;}

	/** Get RS_start_date*/
	public String getTs_RS_start_date() {return ts_RS_start_date;}
	/** Set RS_start_date*/
	public void setTs_RS_start_date(String ts_RS_start_date) {this.ts_RS_start_date = ts_RS_start_date;}

	/** Get RS_start_time*/
	public String getTs_RS_start_time() {return ts_RS_start_time;}
	/** Set RS_start_time*/
	public void setTs_RS_start_time(String ts_RS_start_time) {this.ts_RS_start_time = ts_RS_start_time;}

	/** Get RS_pay_status*/
	public String getTs_RS_pay_status() {return ts_RS_pay_status;}
	/** Set RS_pay_status*/
	public void setTs_RS_pay_status(String ts_RS_pay_status) {this.ts_RS_pay_status = ts_RS_pay_status;}

	/** Get RS_product_id*/
	public String getTs_RS_product_id() {return ts_RS_product_id;}
	/** Set RS_product_id*/
	public void setTs_RS_product_id(String ts_RS_product_id) {this.ts_RS_product_id = ts_RS_product_id;}

	/** Get RS_quantity*/
	public String getTs_RS_quantity() {return ts_RS_quantity;}
	/** Set RS_quantity*/
	public void setTs_RS_quantity(String ts_RS_quantity) {this.ts_RS_quantity = ts_RS_quantity;}

	/** Get RS_duration_minutes*/
	public String getTs_RS_duration_minutes() {return ts_RS_duration_minutes;}
	/** Set RS_duration_minutes*/
	public void setTs_RS_duration_minutes(String ts_RS_duration_minutes) {this.ts_RS_duration_minutes = ts_RS_duration_minutes;}

	/** Get RS_places*/
	public String getTs_RS_places() {return ts_RS_places;}
	/** Set RS_places*/
	public void setTs_RS_places(String ts_RS_places) {this.ts_RS_places = ts_RS_places;}

	/** Get start_date*/
	public String getTs_start_date() {return ts_start_date;}
	/** Set start_date*/
	public void setTs_start_date(String ts_start_date) {this.ts_start_date = ts_start_date;}

	/** Get start_time*/
	public String getTs_start_time() {return ts_start_time;}
	/** Set start_time*/
	public void setTs_start_time(String ts_start_time) {this.ts_start_time = ts_start_time;}

	/** Get json*/
	public String getTs_json() {return ts_json;}
	/** Set json*/
	public void setTs_json(String ts_json) {this.ts_json = ts_json;}

}
