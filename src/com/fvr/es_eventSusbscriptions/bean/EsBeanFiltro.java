package com.fvr.es_eventSusbscriptions.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class EsBeanFiltro extends StBean {

	public String es_sincro; // sincro
	public String es_mark; // mark
	public String es_is_deleted; // is_deleted
	public String es_author; // author
	public String es_event_id; // event_id
	public String es_EV_name; // EV_name
	public String es_EV_location_id; // EV_location_id
	public String es_LO_name; // LO_name
	public String es_inscription_user_id; // inscription_user_id
	public String es_first_name; // first_name
	public String es_last_name; // last_name
	public String es_phone; // phone
	public String es_amount; // amount
	public String es_currency; // currency
	public String es_tpv_order; // tpv_order
	public String es_pay_status; // pay_status
	public String es_json; // json
    
    public EsBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public EsBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setEs_sincro( "" ); // sincro
	this.setEs_mark( "" ); // mark
	this.setEs_is_deleted( "" ); // is_deleted
	this.setEs_author( "" ); // author
	this.setEs_event_id( "" ); // event_id
	this.setEs_EV_name( "" ); // EV_name
	this.setEs_EV_location_id( "" ); // EV_location_id
	this.setEs_LO_name( "" ); // LO_name
	this.setEs_inscription_user_id( "" ); // inscription_user_id
	this.setEs_first_name( "" ); // first_name
	this.setEs_last_name( "" ); // last_name
	this.setEs_phone( "" ); // phone
	this.setEs_amount( "" ); // amount
	this.setEs_currency( "" ); // currency
	this.setEs_tpv_order( "" ); // tpv_order
	this.setEs_pay_status( "" ); // pay_status
	this.setEs_json( "" ); // json
    } 

    public EsBeanFiltro coalesce(EsBeanFiltro pri, EsBeanFiltro sec) {
        EsBeanFiltro r = new EsBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setEs_sincro( (pri.getEs_sincro()==null)?sec.getEs_sincro():pri.getEs_sincro() );	// sincro
		r.setEs_mark( (pri.getEs_mark()==null)?sec.getEs_mark():pri.getEs_mark() );	// mark
		r.setEs_is_deleted( (pri.getEs_is_deleted()==null)?sec.getEs_is_deleted():pri.getEs_is_deleted() );	// is_deleted
		r.setEs_author( (pri.getEs_author()==null)?sec.getEs_author():pri.getEs_author() );	// author
		r.setEs_event_id( (pri.getEs_event_id()==null)?sec.getEs_event_id():pri.getEs_event_id() );	// event_id
		r.setEs_EV_name( (pri.getEs_EV_name()==null)?sec.getEs_EV_name():pri.getEs_EV_name() );	// EV_name
		r.setEs_EV_location_id( (pri.getEs_EV_location_id()==null)?sec.getEs_EV_location_id():pri.getEs_EV_location_id() );	// EV_location_id
		r.setEs_LO_name( (pri.getEs_LO_name()==null)?sec.getEs_LO_name():pri.getEs_LO_name() );	// LO_name
		r.setEs_inscription_user_id( (pri.getEs_inscription_user_id()==null)?sec.getEs_inscription_user_id():pri.getEs_inscription_user_id() );	// inscription_user_id
		r.setEs_first_name( (pri.getEs_first_name()==null)?sec.getEs_first_name():pri.getEs_first_name() );	// first_name
		r.setEs_last_name( (pri.getEs_last_name()==null)?sec.getEs_last_name():pri.getEs_last_name() );	// last_name
		r.setEs_phone( (pri.getEs_phone()==null)?sec.getEs_phone():pri.getEs_phone() );	// phone
		r.setEs_amount( (pri.getEs_amount()==null)?sec.getEs_amount():pri.getEs_amount() );	// amount
		r.setEs_currency( (pri.getEs_currency()==null)?sec.getEs_currency():pri.getEs_currency() );	// currency
		r.setEs_tpv_order( (pri.getEs_tpv_order()==null)?sec.getEs_tpv_order():pri.getEs_tpv_order() );	// tpv_order
		r.setEs_pay_status( (pri.getEs_pay_status()==null)?sec.getEs_pay_status():pri.getEs_pay_status() );	// pay_status
		r.setEs_json( (pri.getEs_json()==null)?sec.getEs_json():pri.getEs_json() );	// json
        }
        return r;
    }
    
    public void copyTo(EsBeanFiltro Destino) {
	Destino.setEs_sincro( getEs_sincro() ); // sincro
	Destino.setEs_mark( getEs_mark() ); // mark
	Destino.setEs_is_deleted( getEs_is_deleted() ); // is_deleted
	Destino.setEs_author( getEs_author() ); // author
	Destino.setEs_event_id( getEs_event_id() ); // event_id
	Destino.setEs_EV_name( getEs_EV_name() ); // EV_name
	Destino.setEs_EV_location_id( getEs_EV_location_id() ); // EV_location_id
	Destino.setEs_LO_name( getEs_LO_name() ); // LO_name
	Destino.setEs_inscription_user_id( getEs_inscription_user_id() ); // inscription_user_id
	Destino.setEs_first_name( getEs_first_name() ); // first_name
	Destino.setEs_last_name( getEs_last_name() ); // last_name
	Destino.setEs_phone( getEs_phone() ); // phone
	Destino.setEs_amount( getEs_amount() ); // amount
	Destino.setEs_currency( getEs_currency() ); // currency
	Destino.setEs_tpv_order( getEs_tpv_order() ); // tpv_order
	Destino.setEs_pay_status( getEs_pay_status() ); // pay_status
	Destino.setEs_json( getEs_json() ); // json
    }
    
    public void copyFrom(EsBeanFiltro Origen) {
	setEs_sincro( Origen.getEs_sincro() ); // sincro
	setEs_mark( Origen.getEs_mark() ); // mark
	setEs_is_deleted( Origen.getEs_is_deleted() ); // is_deleted
	setEs_author( Origen.getEs_author() ); // author
	setEs_event_id( Origen.getEs_event_id() ); // event_id
	setEs_EV_name( Origen.getEs_EV_name() ); // EV_name
	setEs_EV_location_id( Origen.getEs_EV_location_id() ); // EV_location_id
	setEs_LO_name( Origen.getEs_LO_name() ); // LO_name
	setEs_inscription_user_id( Origen.getEs_inscription_user_id() ); // inscription_user_id
	setEs_first_name( Origen.getEs_first_name() ); // first_name
	setEs_last_name( Origen.getEs_last_name() ); // last_name
	setEs_phone( Origen.getEs_phone() ); // phone
	setEs_amount( Origen.getEs_amount() ); // amount
	setEs_currency( Origen.getEs_currency() ); // currency
	setEs_tpv_order( Origen.getEs_tpv_order() ); // tpv_order
	setEs_pay_status( Origen.getEs_pay_status() ); // pay_status
	setEs_json( Origen.getEs_json() ); // json
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getEs_sincro()==null?"":this.getEs_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getEs_mark()==null?"":this.getEs_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getEs_is_deleted()==null?"":this.getEs_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getEs_author()==null?"":this.getEs_author() ); // author
		out.append( _K.sepFld ); out.append( this.getEs_event_id()==null?"":this.getEs_event_id() ); // event_id
		out.append( _K.sepFld ); out.append( this.getEs_EV_name()==null?"":this.getEs_EV_name() ); // EV_name
		out.append( _K.sepFld ); out.append( this.getEs_EV_location_id()==null?"":this.getEs_EV_location_id() ); // EV_location_id
		out.append( _K.sepFld ); out.append( this.getEs_LO_name()==null?"":this.getEs_LO_name() ); // LO_name
		out.append( _K.sepFld ); out.append( this.getEs_inscription_user_id()==null?"":this.getEs_inscription_user_id() ); // inscription_user_id
		out.append( _K.sepFld ); out.append( this.getEs_first_name()==null?"":this.getEs_first_name() ); // first_name
		out.append( _K.sepFld ); out.append( this.getEs_last_name()==null?"":this.getEs_last_name() ); // last_name
		out.append( _K.sepFld ); out.append( this.getEs_phone()==null?"":this.getEs_phone() ); // phone
		out.append( _K.sepFld ); out.append( this.getEs_amount()==null?"":this.getEs_amount() ); // amount
		out.append( _K.sepFld ); out.append( this.getEs_currency()==null?"":this.getEs_currency() ); // currency
		out.append( _K.sepFld ); out.append( this.getEs_tpv_order()==null?"":this.getEs_tpv_order() ); // tpv_order
		out.append( _K.sepFld ); out.append( this.getEs_pay_status()==null?"":this.getEs_pay_status() ); // pay_status
		out.append( _K.sepFld ); out.append( this.getEs_json()==null?"":this.getEs_json() ); // json

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
			
			try { this.setEs_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setEs_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setEs_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setEs_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setEs_event_id( trozos[4] ); } catch (Exception e) {;} // event_id
			try { this.setEs_EV_name( trozos[5] ); } catch (Exception e) {;} // EV_name
			try { this.setEs_EV_location_id( trozos[6] ); } catch (Exception e) {;} // EV_location_id
			try { this.setEs_LO_name( trozos[7] ); } catch (Exception e) {;} // LO_name
			try { this.setEs_inscription_user_id( trozos[8] ); } catch (Exception e) {;} // inscription_user_id
			try { this.setEs_first_name( trozos[9] ); } catch (Exception e) {;} // first_name
			try { this.setEs_last_name( trozos[10] ); } catch (Exception e) {;} // last_name
			try { this.setEs_phone( trozos[11] ); } catch (Exception e) {;} // phone
			try { this.setEs_amount( trozos[12] ); } catch (Exception e) {;} // amount
			try { this.setEs_currency( trozos[13] ); } catch (Exception e) {;} // currency
			try { this.setEs_tpv_order( trozos[14] ); } catch (Exception e) {;} // tpv_order
			try { this.setEs_pay_status( trozos[15] ); } catch (Exception e) {;} // pay_status
			try { this.setEs_json( trozos[16] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get sincro*/
	public String getEs_sincro() {return es_sincro;}
	/** Set sincro*/
	public void setEs_sincro(String es_sincro) {this.es_sincro = es_sincro;}

	/** Get mark*/
	public String getEs_mark() {return es_mark;}
	/** Set mark*/
	public void setEs_mark(String es_mark) {this.es_mark = es_mark;}

	/** Get is_deleted*/
	public String getEs_is_deleted() {return es_is_deleted;}
	/** Set is_deleted*/
	public void setEs_is_deleted(String es_is_deleted) {this.es_is_deleted = es_is_deleted;}

	/** Get author*/
	public String getEs_author() {return es_author;}
	/** Set author*/
	public void setEs_author(String es_author) {this.es_author = es_author;}

	/** Get event_id*/
	public String getEs_event_id() {return es_event_id;}
	/** Set event_id*/
	public void setEs_event_id(String es_event_id) {this.es_event_id = es_event_id;}

	/** Get EV_name*/
	public String getEs_EV_name() {return es_EV_name;}
	/** Set EV_name*/
	public void setEs_EV_name(String es_EV_name) {this.es_EV_name = es_EV_name;}

	/** Get EV_location_id*/
	public String getEs_EV_location_id() {return es_EV_location_id;}
	/** Set EV_location_id*/
	public void setEs_EV_location_id(String es_EV_location_id) {this.es_EV_location_id = es_EV_location_id;}

	/** Get LO_name*/
	public String getEs_LO_name() {return es_LO_name;}
	/** Set LO_name*/
	public void setEs_LO_name(String es_LO_name) {this.es_LO_name = es_LO_name;}

	/** Get inscription_user_id*/
	public String getEs_inscription_user_id() {return es_inscription_user_id;}
	/** Set inscription_user_id*/
	public void setEs_inscription_user_id(String es_inscription_user_id) {this.es_inscription_user_id = es_inscription_user_id;}

	/** Get first_name*/
	public String getEs_first_name() {return es_first_name;}
	/** Set first_name*/
	public void setEs_first_name(String es_first_name) {this.es_first_name = es_first_name;}

	/** Get last_name*/
	public String getEs_last_name() {return es_last_name;}
	/** Set last_name*/
	public void setEs_last_name(String es_last_name) {this.es_last_name = es_last_name;}

	/** Get phone*/
	public String getEs_phone() {return es_phone;}
	/** Set phone*/
	public void setEs_phone(String es_phone) {this.es_phone = es_phone;}

	/** Get amount*/
	public String getEs_amount() {return es_amount;}
	/** Set amount*/
	public void setEs_amount(String es_amount) {this.es_amount = es_amount;}

	/** Get currency*/
	public String getEs_currency() {return es_currency;}
	/** Set currency*/
	public void setEs_currency(String es_currency) {this.es_currency = es_currency;}

	/** Get tpv_order*/
	public String getEs_tpv_order() {return es_tpv_order;}
	/** Set tpv_order*/
	public void setEs_tpv_order(String es_tpv_order) {this.es_tpv_order = es_tpv_order;}

	/** Get pay_status*/
	public String getEs_pay_status() {return es_pay_status;}
	/** Set pay_status*/
	public void setEs_pay_status(String es_pay_status) {this.es_pay_status = es_pay_status;}

	/** Get json*/
	public String getEs_json() {return es_json;}
	/** Set json*/
	public void setEs_json(String es_json) {this.es_json = es_json;}

}
