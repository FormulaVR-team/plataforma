package com.fvr.ev_events.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class EvBean extends StBean {

	public String ev_sincro; // sincro
	public String ev_mark; // mark
	public String ev_is_deleted; // is_deleted
	public String ev_author; // author
	public String ev_event_id; // event_id
	public String ev_location_id; // location_id
	public String ev_LO_name; // LO_name
	public String ev_name; // name
	public long   ev_max_inscriptions; // max_inscriptions
	public double ev_amount; // amount
	public String ev_currency; // currency
	public String ev_deadline; // deadline
	public String ev_comment; // comment
	public String ev_date1; // date1
	public String ev_date2; // date2
	public String ev_date3; // date3
	public String ev_date4; // date4
	public String ev_json; // json
    
    public EvBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public EvBean(Object nulo) { super(); }

    public void inicializar() {
	this.setEv_sincro( "" ); // sincro
	this.setEv_mark( "" ); // mark
	this.setEv_is_deleted( "" ); // is_deleted
	this.setEv_author( "" ); // author
	this.setEv_event_id( "" ); // event_id
	this.setEv_location_id( "" ); // location_id
	this.setEv_LO_name( "" ); // LO_name
	this.setEv_name( "" ); // name
	this.setEv_max_inscriptions( 0 ); // max_inscriptions
	this.setEv_amount( 0.0 ); // amount
	this.setEv_currency( "" ); // currency
	this.setEv_deadline( "" ); // deadline
	this.setEv_comment( "" ); // comment
	this.setEv_date1( "" ); // date1
	this.setEv_date2( "" ); // date2
	this.setEv_date3( "" ); // date3
	this.setEv_date4( "" ); // date4
	this.setEv_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        EvBean Destino = (EvBean)beanDestino;

	Destino.setEv_sincro( getEv_sincro() ); // sincro
	Destino.setEv_mark( getEv_mark() ); // mark
	Destino.setEv_is_deleted( getEv_is_deleted() ); // is_deleted
	Destino.setEv_author( getEv_author() ); // author
	Destino.setEv_event_id( getEv_event_id() ); // event_id
	Destino.setEv_location_id( getEv_location_id() ); // location_id
	Destino.setEv_LO_name( getEv_LO_name() ); // LO_name
	Destino.setEv_name( getEv_name() ); // name
	Destino.setEv_max_inscriptions( getEv_max_inscriptions() ); // max_inscriptions
	Destino.setEv_amount( getEv_amount() ); // amount
	Destino.setEv_currency( getEv_currency() ); // currency
	Destino.setEv_deadline( getEv_deadline() ); // deadline
	Destino.setEv_comment( getEv_comment() ); // comment
	Destino.setEv_date1( getEv_date1() ); // date1
	Destino.setEv_date2( getEv_date2() ); // date2
	Destino.setEv_date3( getEv_date3() ); // date3
	Destino.setEv_date4( getEv_date4() ); // date4
	Destino.setEv_json( getEv_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        EvBean Origen = (EvBean)beanOrigen;

	setEv_sincro( Origen.getEv_sincro() ); // sincro
	setEv_mark( Origen.getEv_mark() ); // mark
	setEv_is_deleted( Origen.getEv_is_deleted() ); // is_deleted
	setEv_author( Origen.getEv_author() ); // author
	setEv_event_id( Origen.getEv_event_id() ); // event_id
	setEv_location_id( Origen.getEv_location_id() ); // location_id
	setEv_LO_name( Origen.getEv_LO_name() ); // LO_name
	setEv_name( Origen.getEv_name() ); // name
	setEv_max_inscriptions( Origen.getEv_max_inscriptions() ); // max_inscriptions
	setEv_amount( Origen.getEv_amount() ); // amount
	setEv_currency( Origen.getEv_currency() ); // currency
	setEv_deadline( Origen.getEv_deadline() ); // deadline
	setEv_comment( Origen.getEv_comment() ); // comment
	setEv_date1( Origen.getEv_date1() ); // date1
	setEv_date2( Origen.getEv_date2() ); // date2
	setEv_date3( Origen.getEv_date3() ); // date3
	setEv_date4( Origen.getEv_date4() ); // date4
	setEv_json( Origen.getEv_json() ); // json
    }
*/


	/** Get sincro*/
	public String getEv_sincro() {return ev_sincro;}
	/** Set sincro*/
	public void setEv_sincro(String ev_sincro) {this.ev_sincro = ev_sincro;}

	/** Get mark*/
	public String getEv_mark() {return ev_mark;}
	/** Set mark*/
	public void setEv_mark(String ev_mark) {this.ev_mark = ev_mark;}

	/** Get is_deleted*/
	public String getEv_is_deleted() {return ev_is_deleted;}
	/** Set is_deleted*/
	public void setEv_is_deleted(String ev_is_deleted) {this.ev_is_deleted = ev_is_deleted;}

	/** Get author*/
	public String getEv_author() {return ev_author;}
	/** Set author*/
	public void setEv_author(String ev_author) {this.ev_author = ev_author;}

	/** Get event_id*/
	public String getEv_event_id() {return ev_event_id;}
	/** Set event_id*/
	public void setEv_event_id(String ev_event_id) {this.ev_event_id = ev_event_id;}

	/** Get location_id*/
	public String getEv_location_id() {return ev_location_id;}
	/** Set location_id*/
	public void setEv_location_id(String ev_location_id) {this.ev_location_id = ev_location_id;}

	/** Get LO_name*/
	public String getEv_LO_name() {return ev_LO_name;}
	/** Set LO_name*/
	public void setEv_LO_name(String ev_LO_name) {this.ev_LO_name = ev_LO_name;}

	/** Get name*/
	public String getEv_name() {return ev_name;}
	/** Set name*/
	public void setEv_name(String ev_name) {this.ev_name = ev_name;}

	/** Get max_inscriptions*/
	public long getEv_max_inscriptions() {return ev_max_inscriptions;}
	/** Set max_inscriptions*/
	public void setEv_max_inscriptions(long ev_max_inscriptions) {this.ev_max_inscriptions = ev_max_inscriptions;}

	/** Get amount*/
	public double getEv_amount() {return ev_amount;}
	/** Set amount*/
	public void setEv_amount(double ev_amount) {this.ev_amount = ev_amount;}

	/** Get currency*/
	public String getEv_currency() {return ev_currency;}
	/** Set currency*/
	public void setEv_currency(String ev_currency) {this.ev_currency = ev_currency;}

	/** Get deadline*/
	public String getEv_deadline() {return ev_deadline;}
	/** Set deadline*/
	public void setEv_deadline(String ev_deadline) {this.ev_deadline = ev_deadline;}

	/** Get comment*/
	public String getEv_comment() {return ev_comment;}
	/** Set comment*/
	public void setEv_comment(String ev_comment) {this.ev_comment = ev_comment;}

	/** Get date1*/
	public String getEv_date1() {return ev_date1;}
	/** Set date1*/
	public void setEv_date1(String ev_date1) {this.ev_date1 = ev_date1;}

	/** Get date2*/
	public String getEv_date2() {return ev_date2;}
	/** Set date2*/
	public void setEv_date2(String ev_date2) {this.ev_date2 = ev_date2;}

	/** Get date3*/
	public String getEv_date3() {return ev_date3;}
	/** Set date3*/
	public void setEv_date3(String ev_date3) {this.ev_date3 = ev_date3;}

	/** Get date4*/
	public String getEv_date4() {return ev_date4;}
	/** Set date4*/
	public void setEv_date4(String ev_date4) {this.ev_date4 = ev_date4;}

	/** Get json*/
	public String getEv_json() {return ev_json;}
	/** Set json*/
	public void setEv_json(String ev_json) {this.ev_json = ev_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return ev_event_id;}

    public void setKey(String key){
            String k="";
	k = key; this.setEv_event_id( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getEv_sincro()==null?"":this.getEv_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getEv_mark()==null?"":this.getEv_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getEv_is_deleted()==null?"":this.getEv_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getEv_author()==null?"":this.getEv_author() ); // author
		out.append( _K.sepFld ); out.append( this.getEv_event_id()==null?"":this.getEv_event_id() ); // event_id
		out.append( _K.sepFld ); out.append( this.getEv_location_id()==null?"":this.getEv_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getEv_LO_name()==null?"":this.getEv_LO_name() ); // LO_name
		out.append( _K.sepFld ); out.append( this.getEv_name()==null?"":this.getEv_name() ); // name
		out.append( _K.sepFld ); out.append( this.getEv_max_inscriptions() ); // max_inscriptions
		out.append( _K.sepFld ); out.append( this.getEv_amount() ); // amount
		out.append( _K.sepFld ); out.append( this.getEv_currency()==null?"":this.getEv_currency() ); // currency
		out.append( _K.sepFld ); out.append( this.getEv_deadline()==null?"":this.getEv_deadline() ); // deadline
		out.append( _K.sepFld ); out.append( this.getEv_comment()==null?"":this.getEv_comment() ); // comment
		out.append( _K.sepFld ); out.append( this.getEv_date1()==null?"":this.getEv_date1() ); // date1
		out.append( _K.sepFld ); out.append( this.getEv_date2()==null?"":this.getEv_date2() ); // date2
		out.append( _K.sepFld ); out.append( this.getEv_date3()==null?"":this.getEv_date3() ); // date3
		out.append( _K.sepFld ); out.append( this.getEv_date4()==null?"":this.getEv_date4() ); // date4
		out.append( _K.sepFld ); out.append( this.getEv_json()==null?"":this.getEv_json() ); // json

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
			
			try { this.setEv_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setEv_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setEv_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setEv_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setEv_event_id( trozos[4] ); } catch (Exception e) {;} // event_id
			try { this.setEv_location_id( trozos[5] ); } catch (Exception e) {;} // location_id
			try { this.setEv_LO_name( trozos[6] ); } catch (Exception e) {;} // LO_name
			try { this.setEv_name( trozos[7] ); } catch (Exception e) {;} // name
			try { this.setEv_max_inscriptions( Subrutinas.parse_long( trozos[8] )); } catch (Exception e) {;} // max_inscriptions
			try { this.setEv_amount( Subrutinas.parse_double( trozos[9] )); } catch (Exception e) {;} // amount
			try { this.setEv_currency( trozos[10] ); } catch (Exception e) {;} // currency
			try { this.setEv_deadline( trozos[11] ); } catch (Exception e) {;} // deadline
			try { this.setEv_comment( trozos[12] ); } catch (Exception e) {;} // comment
			try { this.setEv_date1( trozos[13] ); } catch (Exception e) {;} // date1
			try { this.setEv_date2( trozos[14] ); } catch (Exception e) {;} // date2
			try { this.setEv_date3( trozos[15] ); } catch (Exception e) {;} // date3
			try { this.setEv_date4( trozos[16] ); } catch (Exception e) {;} // date4
			try { this.setEv_json( trozos[17] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
