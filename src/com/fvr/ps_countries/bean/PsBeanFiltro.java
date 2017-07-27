package com.fvr.ps_countries.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class PsBeanFiltro extends StBean {

	public String ps_sincro; // sincro
	public String ps_mark; // mark
	public String ps_is_deleted; // is_deleted
	public String ps_author; // author
	public String ps_country_id; // country_id
	public String ps_name; // name
	public String ps_alpha_2; // alpha_2
	public String ps_alpha_3; // alpha_3
	public String ps_flag_base64; // flag_base64
	public String ps_json; // json
    
    public PsBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public PsBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setPs_sincro( "" ); // sincro
	this.setPs_mark( "" ); // mark
	this.setPs_is_deleted( "" ); // is_deleted
	this.setPs_author( "" ); // author
	this.setPs_country_id( "" ); // country_id
	this.setPs_name( "" ); // name
	this.setPs_alpha_2( "" ); // alpha_2
	this.setPs_alpha_3( "" ); // alpha_3
	this.setPs_flag_base64( "" ); // flag_base64
	this.setPs_json( "" ); // json
    } 

    public PsBeanFiltro coalesce(PsBeanFiltro pri, PsBeanFiltro sec) {
        PsBeanFiltro r = new PsBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setPs_sincro( (pri.getPs_sincro()==null)?sec.getPs_sincro():pri.getPs_sincro() );	// sincro
		r.setPs_mark( (pri.getPs_mark()==null)?sec.getPs_mark():pri.getPs_mark() );	// mark
		r.setPs_is_deleted( (pri.getPs_is_deleted()==null)?sec.getPs_is_deleted():pri.getPs_is_deleted() );	// is_deleted
		r.setPs_author( (pri.getPs_author()==null)?sec.getPs_author():pri.getPs_author() );	// author
		r.setPs_country_id( (pri.getPs_country_id()==null)?sec.getPs_country_id():pri.getPs_country_id() );	// country_id
		r.setPs_name( (pri.getPs_name()==null)?sec.getPs_name():pri.getPs_name() );	// name
		r.setPs_alpha_2( (pri.getPs_alpha_2()==null)?sec.getPs_alpha_2():pri.getPs_alpha_2() );	// alpha_2
		r.setPs_alpha_3( (pri.getPs_alpha_3()==null)?sec.getPs_alpha_3():pri.getPs_alpha_3() );	// alpha_3
		r.setPs_flag_base64( (pri.getPs_flag_base64()==null)?sec.getPs_flag_base64():pri.getPs_flag_base64() );	// flag_base64
		r.setPs_json( (pri.getPs_json()==null)?sec.getPs_json():pri.getPs_json() );	// json
        }
        return r;
    }
    
    public void copyTo(PsBeanFiltro Destino) {
	Destino.setPs_sincro( getPs_sincro() ); // sincro
	Destino.setPs_mark( getPs_mark() ); // mark
	Destino.setPs_is_deleted( getPs_is_deleted() ); // is_deleted
	Destino.setPs_author( getPs_author() ); // author
	Destino.setPs_country_id( getPs_country_id() ); // country_id
	Destino.setPs_name( getPs_name() ); // name
	Destino.setPs_alpha_2( getPs_alpha_2() ); // alpha_2
	Destino.setPs_alpha_3( getPs_alpha_3() ); // alpha_3
	Destino.setPs_flag_base64( getPs_flag_base64() ); // flag_base64
	Destino.setPs_json( getPs_json() ); // json
    }
    
    public void copyFrom(PsBeanFiltro Origen) {
	setPs_sincro( Origen.getPs_sincro() ); // sincro
	setPs_mark( Origen.getPs_mark() ); // mark
	setPs_is_deleted( Origen.getPs_is_deleted() ); // is_deleted
	setPs_author( Origen.getPs_author() ); // author
	setPs_country_id( Origen.getPs_country_id() ); // country_id
	setPs_name( Origen.getPs_name() ); // name
	setPs_alpha_2( Origen.getPs_alpha_2() ); // alpha_2
	setPs_alpha_3( Origen.getPs_alpha_3() ); // alpha_3
	setPs_flag_base64( Origen.getPs_flag_base64() ); // flag_base64
	setPs_json( Origen.getPs_json() ); // json
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getPs_sincro()==null?"":this.getPs_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getPs_mark()==null?"":this.getPs_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getPs_is_deleted()==null?"":this.getPs_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getPs_author()==null?"":this.getPs_author() ); // author
		out.append( _K.sepFld ); out.append( this.getPs_country_id()==null?"":this.getPs_country_id() ); // country_id
		out.append( _K.sepFld ); out.append( this.getPs_name()==null?"":this.getPs_name() ); // name
		out.append( _K.sepFld ); out.append( this.getPs_alpha_2()==null?"":this.getPs_alpha_2() ); // alpha_2
		out.append( _K.sepFld ); out.append( this.getPs_alpha_3()==null?"":this.getPs_alpha_3() ); // alpha_3
		out.append( _K.sepFld ); out.append( this.getPs_flag_base64()==null?"":this.getPs_flag_base64() ); // flag_base64
		out.append( _K.sepFld ); out.append( this.getPs_json()==null?"":this.getPs_json() ); // json

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
			
			try { this.setPs_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setPs_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setPs_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setPs_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setPs_country_id( trozos[4] ); } catch (Exception e) {;} // country_id
			try { this.setPs_name( trozos[5] ); } catch (Exception e) {;} // name
			try { this.setPs_alpha_2( trozos[6] ); } catch (Exception e) {;} // alpha_2
			try { this.setPs_alpha_3( trozos[7] ); } catch (Exception e) {;} // alpha_3
			try { this.setPs_flag_base64( trozos[8] ); } catch (Exception e) {;} // flag_base64
			try { this.setPs_json( trozos[9] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get sincro*/
	public String getPs_sincro() {return ps_sincro;}
	/** Set sincro*/
	public void setPs_sincro(String ps_sincro) {this.ps_sincro = ps_sincro;}

	/** Get mark*/
	public String getPs_mark() {return ps_mark;}
	/** Set mark*/
	public void setPs_mark(String ps_mark) {this.ps_mark = ps_mark;}

	/** Get is_deleted*/
	public String getPs_is_deleted() {return ps_is_deleted;}
	/** Set is_deleted*/
	public void setPs_is_deleted(String ps_is_deleted) {this.ps_is_deleted = ps_is_deleted;}

	/** Get author*/
	public String getPs_author() {return ps_author;}
	/** Set author*/
	public void setPs_author(String ps_author) {this.ps_author = ps_author;}

	/** Get country_id*/
	public String getPs_country_id() {return ps_country_id;}
	/** Set country_id*/
	public void setPs_country_id(String ps_country_id) {this.ps_country_id = ps_country_id;}

	/** Get name*/
	public String getPs_name() {return ps_name;}
	/** Set name*/
	public void setPs_name(String ps_name) {this.ps_name = ps_name;}

	/** Get alpha_2*/
	public String getPs_alpha_2() {return ps_alpha_2;}
	/** Set alpha_2*/
	public void setPs_alpha_2(String ps_alpha_2) {this.ps_alpha_2 = ps_alpha_2;}

	/** Get alpha_3*/
	public String getPs_alpha_3() {return ps_alpha_3;}
	/** Set alpha_3*/
	public void setPs_alpha_3(String ps_alpha_3) {this.ps_alpha_3 = ps_alpha_3;}

	/** Get flag_base64*/
	public String getPs_flag_base64() {return ps_flag_base64;}
	/** Set flag_base64*/
	public void setPs_flag_base64(String ps_flag_base64) {this.ps_flag_base64 = ps_flag_base64;}

	/** Get json*/
	public String getPs_json() {return ps_json;}
	/** Set json*/
	public void setPs_json(String ps_json) {this.ps_json = ps_json;}

}
