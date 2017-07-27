package com.fvr.tk_tokens.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class TkBeanFiltro extends StBean {

	public String tk_sincro; // sincro
	public String tk_mark; // mark
	public String tk_is_deleted; // is_deleted
	public String tk_author; // author
	public String tk_token_id; // token_id
	public String tk_json; // json
    
    public TkBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public TkBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setTk_sincro( "" ); // sincro
	this.setTk_mark( "" ); // mark
	this.setTk_is_deleted( "" ); // is_deleted
	this.setTk_author( "" ); // author
	this.setTk_token_id( "" ); // token_id
	this.setTk_json( "" ); // json
    } 

    public TkBeanFiltro coalesce(TkBeanFiltro pri, TkBeanFiltro sec) {
        TkBeanFiltro r = new TkBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setTk_sincro( (pri.getTk_sincro()==null)?sec.getTk_sincro():pri.getTk_sincro() );	// sincro
		r.setTk_mark( (pri.getTk_mark()==null)?sec.getTk_mark():pri.getTk_mark() );	// mark
		r.setTk_is_deleted( (pri.getTk_is_deleted()==null)?sec.getTk_is_deleted():pri.getTk_is_deleted() );	// is_deleted
		r.setTk_author( (pri.getTk_author()==null)?sec.getTk_author():pri.getTk_author() );	// author
		r.setTk_token_id( (pri.getTk_token_id()==null)?sec.getTk_token_id():pri.getTk_token_id() );	// token_id
		r.setTk_json( (pri.getTk_json()==null)?sec.getTk_json():pri.getTk_json() );	// json
        }
        return r;
    }
    
    public void copyTo(TkBeanFiltro Destino) {
	Destino.setTk_sincro( getTk_sincro() ); // sincro
	Destino.setTk_mark( getTk_mark() ); // mark
	Destino.setTk_is_deleted( getTk_is_deleted() ); // is_deleted
	Destino.setTk_author( getTk_author() ); // author
	Destino.setTk_token_id( getTk_token_id() ); // token_id
	Destino.setTk_json( getTk_json() ); // json
    }
    
    public void copyFrom(TkBeanFiltro Origen) {
	setTk_sincro( Origen.getTk_sincro() ); // sincro
	setTk_mark( Origen.getTk_mark() ); // mark
	setTk_is_deleted( Origen.getTk_is_deleted() ); // is_deleted
	setTk_author( Origen.getTk_author() ); // author
	setTk_token_id( Origen.getTk_token_id() ); // token_id
	setTk_json( Origen.getTk_json() ); // json
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getTk_sincro()==null?"":this.getTk_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getTk_mark()==null?"":this.getTk_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getTk_is_deleted()==null?"":this.getTk_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getTk_author()==null?"":this.getTk_author() ); // author
		out.append( _K.sepFld ); out.append( this.getTk_token_id()==null?"":this.getTk_token_id() ); // token_id
		out.append( _K.sepFld ); out.append( this.getTk_json()==null?"":this.getTk_json() ); // json

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
			
			try { this.setTk_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setTk_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setTk_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setTk_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setTk_token_id( trozos[4] ); } catch (Exception e) {;} // token_id
			try { this.setTk_json( trozos[5] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get sincro*/
	public String getTk_sincro() {return tk_sincro;}
	/** Set sincro*/
	public void setTk_sincro(String tk_sincro) {this.tk_sincro = tk_sincro;}

	/** Get mark*/
	public String getTk_mark() {return tk_mark;}
	/** Set mark*/
	public void setTk_mark(String tk_mark) {this.tk_mark = tk_mark;}

	/** Get is_deleted*/
	public String getTk_is_deleted() {return tk_is_deleted;}
	/** Set is_deleted*/
	public void setTk_is_deleted(String tk_is_deleted) {this.tk_is_deleted = tk_is_deleted;}

	/** Get author*/
	public String getTk_author() {return tk_author;}
	/** Set author*/
	public void setTk_author(String tk_author) {this.tk_author = tk_author;}

	/** Get token_id*/
	public String getTk_token_id() {return tk_token_id;}
	/** Set token_id*/
	public void setTk_token_id(String tk_token_id) {this.tk_token_id = tk_token_id;}

	/** Get json*/
	public String getTk_json() {return tk_json;}
	/** Set json*/
	public void setTk_json(String tk_json) {this.tk_json = tk_json;}

}
