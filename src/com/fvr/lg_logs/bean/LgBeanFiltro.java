package com.fvr.lg_logs.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class LgBeanFiltro extends StBean {

	public String lg_sincro; // sincro
	public String lg_mark; // mark
	public String lg_is_deleted; // is_deleted
	public String lg_author; // author
	public String lg_serial; // serial
	public String lg_text_1; // text_1
	public String lg_text_2; // text_2
	public String lg_text_3; // text_3
	public String lg_json; // json
    
    public LgBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public LgBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setLg_sincro( "" ); // sincro
	this.setLg_mark( "" ); // mark
	this.setLg_is_deleted( "" ); // is_deleted
	this.setLg_author( "" ); // author
	this.setLg_serial( "" ); // serial
	this.setLg_text_1( "" ); // text_1
	this.setLg_text_2( "" ); // text_2
	this.setLg_text_3( "" ); // text_3
	this.setLg_json( "" ); // json
    } 

    public LgBeanFiltro coalesce(LgBeanFiltro pri, LgBeanFiltro sec) {
        LgBeanFiltro r = new LgBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setLg_sincro( (pri.getLg_sincro()==null)?sec.getLg_sincro():pri.getLg_sincro() );	// sincro
		r.setLg_mark( (pri.getLg_mark()==null)?sec.getLg_mark():pri.getLg_mark() );	// mark
		r.setLg_is_deleted( (pri.getLg_is_deleted()==null)?sec.getLg_is_deleted():pri.getLg_is_deleted() );	// is_deleted
		r.setLg_author( (pri.getLg_author()==null)?sec.getLg_author():pri.getLg_author() );	// author
		r.setLg_serial( (pri.getLg_serial()==null)?sec.getLg_serial():pri.getLg_serial() );	// serial
		r.setLg_text_1( (pri.getLg_text_1()==null)?sec.getLg_text_1():pri.getLg_text_1() );	// text_1
		r.setLg_text_2( (pri.getLg_text_2()==null)?sec.getLg_text_2():pri.getLg_text_2() );	// text_2
		r.setLg_text_3( (pri.getLg_text_3()==null)?sec.getLg_text_3():pri.getLg_text_3() );	// text_3
		r.setLg_json( (pri.getLg_json()==null)?sec.getLg_json():pri.getLg_json() );	// json
        }
        return r;
    }
    
    public void copyTo(LgBeanFiltro Destino) {
	Destino.setLg_sincro( getLg_sincro() ); // sincro
	Destino.setLg_mark( getLg_mark() ); // mark
	Destino.setLg_is_deleted( getLg_is_deleted() ); // is_deleted
	Destino.setLg_author( getLg_author() ); // author
	Destino.setLg_serial( getLg_serial() ); // serial
	Destino.setLg_text_1( getLg_text_1() ); // text_1
	Destino.setLg_text_2( getLg_text_2() ); // text_2
	Destino.setLg_text_3( getLg_text_3() ); // text_3
	Destino.setLg_json( getLg_json() ); // json
    }
    
    public void copyFrom(LgBeanFiltro Origen) {
	setLg_sincro( Origen.getLg_sincro() ); // sincro
	setLg_mark( Origen.getLg_mark() ); // mark
	setLg_is_deleted( Origen.getLg_is_deleted() ); // is_deleted
	setLg_author( Origen.getLg_author() ); // author
	setLg_serial( Origen.getLg_serial() ); // serial
	setLg_text_1( Origen.getLg_text_1() ); // text_1
	setLg_text_2( Origen.getLg_text_2() ); // text_2
	setLg_text_3( Origen.getLg_text_3() ); // text_3
	setLg_json( Origen.getLg_json() ); // json
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getLg_sincro()==null?"":this.getLg_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getLg_mark()==null?"":this.getLg_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getLg_is_deleted()==null?"":this.getLg_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getLg_author()==null?"":this.getLg_author() ); // author
		out.append( _K.sepFld ); out.append( this.getLg_serial()==null?"":this.getLg_serial() ); // serial
		out.append( _K.sepFld ); out.append( this.getLg_text_1()==null?"":this.getLg_text_1() ); // text_1
		out.append( _K.sepFld ); out.append( this.getLg_text_2()==null?"":this.getLg_text_2() ); // text_2
		out.append( _K.sepFld ); out.append( this.getLg_text_3()==null?"":this.getLg_text_3() ); // text_3
		out.append( _K.sepFld ); out.append( this.getLg_json()==null?"":this.getLg_json() ); // json

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
			
			try { this.setLg_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setLg_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setLg_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setLg_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setLg_serial( trozos[4] ); } catch (Exception e) {;} // serial
			try { this.setLg_text_1( trozos[5] ); } catch (Exception e) {;} // text_1
			try { this.setLg_text_2( trozos[6] ); } catch (Exception e) {;} // text_2
			try { this.setLg_text_3( trozos[7] ); } catch (Exception e) {;} // text_3
			try { this.setLg_json( trozos[8] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get sincro*/
	public String getLg_sincro() {return lg_sincro;}
	/** Set sincro*/
	public void setLg_sincro(String lg_sincro) {this.lg_sincro = lg_sincro;}

	/** Get mark*/
	public String getLg_mark() {return lg_mark;}
	/** Set mark*/
	public void setLg_mark(String lg_mark) {this.lg_mark = lg_mark;}

	/** Get is_deleted*/
	public String getLg_is_deleted() {return lg_is_deleted;}
	/** Set is_deleted*/
	public void setLg_is_deleted(String lg_is_deleted) {this.lg_is_deleted = lg_is_deleted;}

	/** Get author*/
	public String getLg_author() {return lg_author;}
	/** Set author*/
	public void setLg_author(String lg_author) {this.lg_author = lg_author;}

	/** Get serial*/
	public String getLg_serial() {return lg_serial;}
	/** Set serial*/
	public void setLg_serial(String lg_serial) {this.lg_serial = lg_serial;}

	/** Get text_1*/
	public String getLg_text_1() {return lg_text_1;}
	/** Set text_1*/
	public void setLg_text_1(String lg_text_1) {this.lg_text_1 = lg_text_1;}

	/** Get text_2*/
	public String getLg_text_2() {return lg_text_2;}
	/** Set text_2*/
	public void setLg_text_2(String lg_text_2) {this.lg_text_2 = lg_text_2;}

	/** Get text_3*/
	public String getLg_text_3() {return lg_text_3;}
	/** Set text_3*/
	public void setLg_text_3(String lg_text_3) {this.lg_text_3 = lg_text_3;}

	/** Get json*/
	public String getLg_json() {return lg_json;}
	/** Set json*/
	public void setLg_json(String lg_json) {this.lg_json = lg_json;}

}
