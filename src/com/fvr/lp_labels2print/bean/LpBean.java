package com.fvr.lp_labels2print.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class LpBean extends StBean {

	public String lp_card_id; // card_id
	public String lp_qr_image_base64; // qr_image_base64
	public String lp_json; // json
    
    public LpBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public LpBean(Object nulo) { super(); }

    public void inicializar() {
	this.setLp_card_id( "" ); // card_id
	this.setLp_qr_image_base64( "" ); // qr_image_base64
	this.setLp_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        LpBean Destino = (LpBean)beanDestino;

	Destino.setLp_card_id( getLp_card_id() ); // card_id
	Destino.setLp_qr_image_base64( getLp_qr_image_base64() ); // qr_image_base64
	Destino.setLp_json( getLp_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        LpBean Origen = (LpBean)beanOrigen;

	setLp_card_id( Origen.getLp_card_id() ); // card_id
	setLp_qr_image_base64( Origen.getLp_qr_image_base64() ); // qr_image_base64
	setLp_json( Origen.getLp_json() ); // json
    }
*/


	/** Get card_id*/
	public String getLp_card_id() {return lp_card_id;}
	/** Set card_id*/
	public void setLp_card_id(String lp_card_id) {this.lp_card_id = lp_card_id;}

	/** Get qr_image_base64*/
	public String getLp_qr_image_base64() {return lp_qr_image_base64;}
	/** Set qr_image_base64*/
	public void setLp_qr_image_base64(String lp_qr_image_base64) {this.lp_qr_image_base64 = lp_qr_image_base64;}

	/** Get json*/
	public String getLp_json() {return lp_json;}
	/** Set json*/
	public void setLp_json(String lp_json) {this.lp_json = lp_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return lp_card_id;}

    public void setKey(String key){
            String k="";
	k = key; this.setLp_card_id( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getLp_card_id()==null?"":this.getLp_card_id() ); // card_id
		out.append( _K.sepFld ); out.append( this.getLp_qr_image_base64()==null?"":this.getLp_qr_image_base64() ); // qr_image_base64
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
			try { this.setLp_qr_image_base64( trozos[1] ); } catch (Exception e) {;} // qr_image_base64
			try { this.setLp_json( trozos[2] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
