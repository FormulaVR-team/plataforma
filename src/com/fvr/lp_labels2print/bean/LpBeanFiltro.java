package com.fvr.lp_labels2print.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class LpBeanFiltro extends StBean {

	public String lp_card_id; // card_id
	public String lp_TJ_user_id; // TJ_user_id
	public String lp_TJ_qr_image_base64; // TJ_qr_image_base64
	public String lp_json; // json
    
    public LpBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public LpBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setLp_card_id( "" ); // card_id
	this.setLp_TJ_user_id( "" ); // TJ_user_id
	this.setLp_TJ_qr_image_base64( "" ); // TJ_qr_image_base64
	this.setLp_json( "" ); // json
    } 

    public LpBeanFiltro coalesce(LpBeanFiltro pri, LpBeanFiltro sec) {
        LpBeanFiltro r = new LpBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setLp_card_id( (pri.getLp_card_id()==null)?sec.getLp_card_id():pri.getLp_card_id() );	// card_id
		r.setLp_TJ_user_id( (pri.getLp_TJ_user_id()==null)?sec.getLp_TJ_user_id():pri.getLp_TJ_user_id() );	// TJ_user_id
		r.setLp_TJ_qr_image_base64( (pri.getLp_TJ_qr_image_base64()==null)?sec.getLp_TJ_qr_image_base64():pri.getLp_TJ_qr_image_base64() );	// TJ_qr_image_base64
		r.setLp_json( (pri.getLp_json()==null)?sec.getLp_json():pri.getLp_json() );	// json
        }
        return r;
    }
    
    public void copyTo(LpBeanFiltro Destino) {
	Destino.setLp_card_id( getLp_card_id() ); // card_id
	Destino.setLp_TJ_user_id( getLp_TJ_user_id() ); // TJ_user_id
	Destino.setLp_TJ_qr_image_base64( getLp_TJ_qr_image_base64() ); // TJ_qr_image_base64
	Destino.setLp_json( getLp_json() ); // json
    }
    
    public void copyFrom(LpBeanFiltro Origen) {
	setLp_card_id( Origen.getLp_card_id() ); // card_id
	setLp_TJ_user_id( Origen.getLp_TJ_user_id() ); // TJ_user_id
	setLp_TJ_qr_image_base64( Origen.getLp_TJ_qr_image_base64() ); // TJ_qr_image_base64
	setLp_json( Origen.getLp_json() ); // json
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getLp_card_id()==null?"":this.getLp_card_id() ); // card_id
		out.append( _K.sepFld ); out.append( this.getLp_TJ_user_id()==null?"":this.getLp_TJ_user_id() ); // TJ_user_id
		out.append( _K.sepFld ); out.append( this.getLp_TJ_qr_image_base64()==null?"":this.getLp_TJ_qr_image_base64() ); // TJ_qr_image_base64
		out.append( _K.sepFld ); out.append( this.getLp_json()==null?"":this.getLp_json() ); // json

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
			
			try { this.setLp_card_id( trozos[0] ); } catch (Exception e) {;} // card_id
			try { this.setLp_TJ_user_id( trozos[1] ); } catch (Exception e) {;} // TJ_user_id
			try { this.setLp_TJ_qr_image_base64( trozos[2] ); } catch (Exception e) {;} // TJ_qr_image_base64
			try { this.setLp_json( trozos[3] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get card_id*/
	public String getLp_card_id() {return lp_card_id;}
	/** Set card_id*/
	public void setLp_card_id(String lp_card_id) {this.lp_card_id = lp_card_id;}

	/** Get TJ_user_id*/
	public String getLp_TJ_user_id() {return lp_TJ_user_id;}
	/** Set TJ_user_id*/
	public void setLp_TJ_user_id(String lp_TJ_user_id) {this.lp_TJ_user_id = lp_TJ_user_id;}

	/** Get TJ_qr_image_base64*/
	public String getLp_TJ_qr_image_base64() {return lp_TJ_qr_image_base64;}
	/** Set TJ_qr_image_base64*/
	public void setLp_TJ_qr_image_base64(String lp_TJ_qr_image_base64) {this.lp_TJ_qr_image_base64 = lp_TJ_qr_image_base64;}

	/** Get json*/
	public String getLp_json() {return lp_json;}
	/** Set json*/
	public void setLp_json(String lp_json) {this.lp_json = lp_json;}

}
