package com.fvr.pa_systemParameters.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class PaBean extends StBean {

	public String pa_sincro; // sincro
	public String pa_mark; // mark
	public String pa_is_deleted; // is_deleted
	public String pa_author; // author
	public String pa_group_id; // group_id
	public String pa_key; // key
	public String pa_value; // value
    
    public PaBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public PaBean(Object nulo) { super(); }

    public void inicializar() {
	this.setPa_sincro( "" ); // sincro
	this.setPa_mark( "" ); // mark
	this.setPa_is_deleted( "" ); // is_deleted
	this.setPa_author( "" ); // author
	this.setPa_group_id( "" ); // group_id
	this.setPa_key( "" ); // key
	this.setPa_value( "" ); // value
    } 
 /*
    public void copyTo(StBean beanDestino) {
        PaBean Destino = (PaBean)beanDestino;

	Destino.setPa_sincro( getPa_sincro() ); // sincro
	Destino.setPa_mark( getPa_mark() ); // mark
	Destino.setPa_is_deleted( getPa_is_deleted() ); // is_deleted
	Destino.setPa_author( getPa_author() ); // author
	Destino.setPa_group_id( getPa_group_id() ); // group_id
	Destino.setPa_key( getPa_key() ); // key
	Destino.setPa_value( getPa_value() ); // value
    }
    
    public void copyFrom(StBean beanOrigen) {
        PaBean Origen = (PaBean)beanOrigen;

	setPa_sincro( Origen.getPa_sincro() ); // sincro
	setPa_mark( Origen.getPa_mark() ); // mark
	setPa_is_deleted( Origen.getPa_is_deleted() ); // is_deleted
	setPa_author( Origen.getPa_author() ); // author
	setPa_group_id( Origen.getPa_group_id() ); // group_id
	setPa_key( Origen.getPa_key() ); // key
	setPa_value( Origen.getPa_value() ); // value
    }
*/


	/** Get sincro*/
	public String getPa_sincro() {return pa_sincro;}
	/** Set sincro*/
	public void setPa_sincro(String pa_sincro) {this.pa_sincro = pa_sincro;}

	/** Get mark*/
	public String getPa_mark() {return pa_mark;}
	/** Set mark*/
	public void setPa_mark(String pa_mark) {this.pa_mark = pa_mark;}

	/** Get is_deleted*/
	public String getPa_is_deleted() {return pa_is_deleted;}
	/** Set is_deleted*/
	public void setPa_is_deleted(String pa_is_deleted) {this.pa_is_deleted = pa_is_deleted;}

	/** Get author*/
	public String getPa_author() {return pa_author;}
	/** Set author*/
	public void setPa_author(String pa_author) {this.pa_author = pa_author;}

	/** Get group_id*/
	public String getPa_group_id() {return pa_group_id;}
	/** Set group_id*/
	public void setPa_group_id(String pa_group_id) {this.pa_group_id = pa_group_id;}

	/** Get key*/
	public String getPa_key() {return pa_key;}
	/** Set key*/
	public void setPa_key(String pa_key) {this.pa_key = pa_key;}

	/** Get value*/
	public String getPa_value() {return pa_value;}
	/** Set value*/
	public void setPa_value(String pa_value) {this.pa_value = pa_value;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return pa_group_id + "^" + 
		pa_key;}

    public void setKey(String key){
            String k="";
	k = key.trim().substring( 0, key.indexOf("^") ); this.setPa_group_id( k ); key = key.trim().substring( key.indexOf("^")+1 );
	k = key; this.setPa_key( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getPa_sincro()==null?"":this.getPa_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getPa_mark()==null?"":this.getPa_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getPa_is_deleted()==null?"":this.getPa_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getPa_author()==null?"":this.getPa_author() ); // author
		out.append( _K.sepFld ); out.append( this.getPa_group_id()==null?"":this.getPa_group_id() ); // group_id
		out.append( _K.sepFld ); out.append( this.getPa_key()==null?"":this.getPa_key() ); // key
		out.append( _K.sepFld ); out.append( this.getPa_value()==null?"":this.getPa_value() ); // value

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
			
			try { this.setPa_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setPa_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setPa_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setPa_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setPa_group_id( trozos[4] ); } catch (Exception e) {;} // group_id
			try { this.setPa_key( trozos[5] ); } catch (Exception e) {;} // key
			try { this.setPa_value( trozos[6] ); } catch (Exception e) {;} // value
			
		}
	}
	////////////////////////////////////////////////////////////

}
