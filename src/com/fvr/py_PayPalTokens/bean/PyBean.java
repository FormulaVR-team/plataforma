package com.fvr.py_PayPalTokens.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class PyBean extends StBean {

	public String py_sincro; // sincro
	public String py_mark; // mark
	public String py_is_deleted; // is_deleted
	public String py_author; // author
	public String py_user_id; // user_id
	public String py_US_first_name; // US_first_name
	public String py_US_last_name; // US_last_name
	public String py_reservation_id; // reservation_id
	public String py_paypal_token_id; // paypal_token_id
	public String py_paypal_usr; // paypal_usr
	public String py_paypal_pwd; // paypal_pwd
	public String py_paypal_signature; // paypal_signature
	public String py_stsProceso; // stsProceso
	public String py_json; // json
    
    public PyBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public PyBean(Object nulo) { super(); }

    public void inicializar() {
	this.setPy_sincro( "" ); // sincro
	this.setPy_mark( "" ); // mark
	this.setPy_is_deleted( "" ); // is_deleted
	this.setPy_author( "" ); // author
	this.setPy_user_id( "" ); // user_id
	this.setPy_US_first_name( "" ); // US_first_name
	this.setPy_US_last_name( "" ); // US_last_name
	this.setPy_reservation_id( "" ); // reservation_id
	this.setPy_paypal_token_id( "" ); // paypal_token_id
	this.setPy_paypal_usr( "" ); // paypal_usr
	this.setPy_paypal_pwd( "" ); // paypal_pwd
	this.setPy_paypal_signature( "" ); // paypal_signature
	this.setPy_stsProceso( "" ); // stsProceso
	this.setPy_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        PyBean Destino = (PyBean)beanDestino;

	Destino.setPy_sincro( getPy_sincro() ); // sincro
	Destino.setPy_mark( getPy_mark() ); // mark
	Destino.setPy_is_deleted( getPy_is_deleted() ); // is_deleted
	Destino.setPy_author( getPy_author() ); // author
	Destino.setPy_user_id( getPy_user_id() ); // user_id
	Destino.setPy_US_first_name( getPy_US_first_name() ); // US_first_name
	Destino.setPy_US_last_name( getPy_US_last_name() ); // US_last_name
	Destino.setPy_reservation_id( getPy_reservation_id() ); // reservation_id
	Destino.setPy_paypal_token_id( getPy_paypal_token_id() ); // paypal_token_id
	Destino.setPy_paypal_usr( getPy_paypal_usr() ); // paypal_usr
	Destino.setPy_paypal_pwd( getPy_paypal_pwd() ); // paypal_pwd
	Destino.setPy_paypal_signature( getPy_paypal_signature() ); // paypal_signature
	Destino.setPy_stsProceso( getPy_stsProceso() ); // stsProceso
	Destino.setPy_json( getPy_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        PyBean Origen = (PyBean)beanOrigen;

	setPy_sincro( Origen.getPy_sincro() ); // sincro
	setPy_mark( Origen.getPy_mark() ); // mark
	setPy_is_deleted( Origen.getPy_is_deleted() ); // is_deleted
	setPy_author( Origen.getPy_author() ); // author
	setPy_user_id( Origen.getPy_user_id() ); // user_id
	setPy_US_first_name( Origen.getPy_US_first_name() ); // US_first_name
	setPy_US_last_name( Origen.getPy_US_last_name() ); // US_last_name
	setPy_reservation_id( Origen.getPy_reservation_id() ); // reservation_id
	setPy_paypal_token_id( Origen.getPy_paypal_token_id() ); // paypal_token_id
	setPy_paypal_usr( Origen.getPy_paypal_usr() ); // paypal_usr
	setPy_paypal_pwd( Origen.getPy_paypal_pwd() ); // paypal_pwd
	setPy_paypal_signature( Origen.getPy_paypal_signature() ); // paypal_signature
	setPy_stsProceso( Origen.getPy_stsProceso() ); // stsProceso
	setPy_json( Origen.getPy_json() ); // json
    }
*/


	/** Get sincro*/
	public String getPy_sincro() {return py_sincro;}
	/** Set sincro*/
	public void setPy_sincro(String py_sincro) {this.py_sincro = py_sincro;}

	/** Get mark*/
	public String getPy_mark() {return py_mark;}
	/** Set mark*/
	public void setPy_mark(String py_mark) {this.py_mark = py_mark;}

	/** Get is_deleted*/
	public String getPy_is_deleted() {return py_is_deleted;}
	/** Set is_deleted*/
	public void setPy_is_deleted(String py_is_deleted) {this.py_is_deleted = py_is_deleted;}

	/** Get author*/
	public String getPy_author() {return py_author;}
	/** Set author*/
	public void setPy_author(String py_author) {this.py_author = py_author;}

	/** Get user_id*/
	public String getPy_user_id() {return py_user_id;}
	/** Set user_id*/
	public void setPy_user_id(String py_user_id) {this.py_user_id = py_user_id;}

	/** Get US_first_name*/
	public String getPy_US_first_name() {return py_US_first_name;}
	/** Set US_first_name*/
	public void setPy_US_first_name(String py_US_first_name) {this.py_US_first_name = py_US_first_name;}

	/** Get US_last_name*/
	public String getPy_US_last_name() {return py_US_last_name;}
	/** Set US_last_name*/
	public void setPy_US_last_name(String py_US_last_name) {this.py_US_last_name = py_US_last_name;}

	/** Get reservation_id*/
	public String getPy_reservation_id() {return py_reservation_id;}
	/** Set reservation_id*/
	public void setPy_reservation_id(String py_reservation_id) {this.py_reservation_id = py_reservation_id;}

	/** Get paypal_token_id*/
	public String getPy_paypal_token_id() {return py_paypal_token_id;}
	/** Set paypal_token_id*/
	public void setPy_paypal_token_id(String py_paypal_token_id) {this.py_paypal_token_id = py_paypal_token_id;}

	/** Get paypal_usr*/
	public String getPy_paypal_usr() {return py_paypal_usr;}
	/** Set paypal_usr*/
	public void setPy_paypal_usr(String py_paypal_usr) {this.py_paypal_usr = py_paypal_usr;}

	/** Get paypal_pwd*/
	public String getPy_paypal_pwd() {return py_paypal_pwd;}
	/** Set paypal_pwd*/
	public void setPy_paypal_pwd(String py_paypal_pwd) {this.py_paypal_pwd = py_paypal_pwd;}

	/** Get paypal_signature*/
	public String getPy_paypal_signature() {return py_paypal_signature;}
	/** Set paypal_signature*/
	public void setPy_paypal_signature(String py_paypal_signature) {this.py_paypal_signature = py_paypal_signature;}

	/** Get stsProceso*/
	public String getPy_stsProceso() {return py_stsProceso;}
	/** Set stsProceso*/
	public void setPy_stsProceso(String py_stsProceso) {this.py_stsProceso = py_stsProceso;}

	/** Get json*/
	public String getPy_json() {return py_json;}
	/** Set json*/
	public void setPy_json(String py_json) {this.py_json = py_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return py_user_id + "^" + 
		py_reservation_id + "^" + 
		py_paypal_token_id;}

    public void setKey(String key){
            String k="";
	k = key.trim().substring( 0, key.indexOf("^") ); this.setPy_user_id( k ); key = key.trim().substring( key.indexOf("^")+1 );
	k = key.trim().substring( 0, key.indexOf("^") ); this.setPy_reservation_id( k ); key = key.trim().substring( key.indexOf("^")+1 );
	k = key; this.setPy_paypal_token_id( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getPy_sincro()==null?"":this.getPy_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getPy_mark()==null?"":this.getPy_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getPy_is_deleted()==null?"":this.getPy_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getPy_author()==null?"":this.getPy_author() ); // author
		out.append( _K.sepFld ); out.append( this.getPy_user_id()==null?"":this.getPy_user_id() ); // user_id
		out.append( _K.sepFld ); out.append( this.getPy_US_first_name()==null?"":this.getPy_US_first_name() ); // US_first_name
		out.append( _K.sepFld ); out.append( this.getPy_US_last_name()==null?"":this.getPy_US_last_name() ); // US_last_name
		out.append( _K.sepFld ); out.append( this.getPy_reservation_id()==null?"":this.getPy_reservation_id() ); // reservation_id
		out.append( _K.sepFld ); out.append( this.getPy_paypal_token_id()==null?"":this.getPy_paypal_token_id() ); // paypal_token_id
		out.append( _K.sepFld ); out.append( this.getPy_paypal_usr()==null?"":this.getPy_paypal_usr() ); // paypal_usr
		out.append( _K.sepFld ); out.append( this.getPy_paypal_pwd()==null?"":this.getPy_paypal_pwd() ); // paypal_pwd
		out.append( _K.sepFld ); out.append( this.getPy_paypal_signature()==null?"":this.getPy_paypal_signature() ); // paypal_signature
		out.append( _K.sepFld ); out.append( this.getPy_stsProceso()==null?"":this.getPy_stsProceso() ); // stsProceso
		out.append( _K.sepFld ); out.append( this.getPy_json()==null?"":this.getPy_json() ); // json

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
			
			try { this.setPy_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setPy_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setPy_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setPy_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setPy_user_id( trozos[4] ); } catch (Exception e) {;} // user_id
			try { this.setPy_US_first_name( trozos[5] ); } catch (Exception e) {;} // US_first_name
			try { this.setPy_US_last_name( trozos[6] ); } catch (Exception e) {;} // US_last_name
			try { this.setPy_reservation_id( trozos[7] ); } catch (Exception e) {;} // reservation_id
			try { this.setPy_paypal_token_id( trozos[8] ); } catch (Exception e) {;} // paypal_token_id
			try { this.setPy_paypal_usr( trozos[9] ); } catch (Exception e) {;} // paypal_usr
			try { this.setPy_paypal_pwd( trozos[10] ); } catch (Exception e) {;} // paypal_pwd
			try { this.setPy_paypal_signature( trozos[11] ); } catch (Exception e) {;} // paypal_signature
			try { this.setPy_stsProceso( trozos[12] ); } catch (Exception e) {;} // stsProceso
			try { this.setPy_json( trozos[13] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
