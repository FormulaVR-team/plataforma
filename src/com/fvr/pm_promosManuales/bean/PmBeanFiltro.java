package com.fvr.pm_promosManuales.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class PmBeanFiltro extends StBean {

	public String pm_sincro; // sincro
	public String pm_mark; // mark
	public String pm_is_deleted; // is_deleted
	public String pm_author; // author
	public String pm_coupon_id; // coupon_id
	public String pm_name; // name
	public String pm_uses_per_user; // uses_per_user
	public String pm_places; // places
	public String pm_location_id; // location_id
	public String pm_LO_name; // LO_name
	public String pm_product_id; // product_id
	public String pm_PT_name; // PT_name
	public String pm_PT_whoCanSelect_AFU; // PT_whoCanSelect_AFU
	public String pm_PT_deadline; // PT_deadline
	public String pm_product_id_promo; // product_id_promo
	public String pm_deadline; // deadline
	public String pm_json; // json
    
    public PmBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public PmBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setPm_sincro( "" ); // sincro
	this.setPm_mark( "" ); // mark
	this.setPm_is_deleted( "" ); // is_deleted
	this.setPm_author( "" ); // author
	this.setPm_coupon_id( "" ); // coupon_id
	this.setPm_name( "" ); // name
	this.setPm_uses_per_user( "" ); // uses_per_user
	this.setPm_places( "" ); // places
	this.setPm_location_id( "" ); // location_id
	this.setPm_LO_name( "" ); // LO_name
	this.setPm_product_id( "" ); // product_id
	this.setPm_PT_name( "" ); // PT_name
	this.setPm_PT_whoCanSelect_AFU( "" ); // PT_whoCanSelect_AFU
	this.setPm_PT_deadline( "" ); // PT_deadline
	this.setPm_product_id_promo( "" ); // product_id_promo
	this.setPm_deadline( "" ); // deadline
	this.setPm_json( "" ); // json
    } 

    public PmBeanFiltro coalesce(PmBeanFiltro pri, PmBeanFiltro sec) {
        PmBeanFiltro r = new PmBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setPm_sincro( (pri.getPm_sincro()==null)?sec.getPm_sincro():pri.getPm_sincro() );	// sincro
		r.setPm_mark( (pri.getPm_mark()==null)?sec.getPm_mark():pri.getPm_mark() );	// mark
		r.setPm_is_deleted( (pri.getPm_is_deleted()==null)?sec.getPm_is_deleted():pri.getPm_is_deleted() );	// is_deleted
		r.setPm_author( (pri.getPm_author()==null)?sec.getPm_author():pri.getPm_author() );	// author
		r.setPm_coupon_id( (pri.getPm_coupon_id()==null)?sec.getPm_coupon_id():pri.getPm_coupon_id() );	// coupon_id
		r.setPm_name( (pri.getPm_name()==null)?sec.getPm_name():pri.getPm_name() );	// name
		r.setPm_uses_per_user( (pri.getPm_uses_per_user()==null)?sec.getPm_uses_per_user():pri.getPm_uses_per_user() );	// uses_per_user
		r.setPm_places( (pri.getPm_places()==null)?sec.getPm_places():pri.getPm_places() );	// places
		r.setPm_location_id( (pri.getPm_location_id()==null)?sec.getPm_location_id():pri.getPm_location_id() );	// location_id
		r.setPm_LO_name( (pri.getPm_LO_name()==null)?sec.getPm_LO_name():pri.getPm_LO_name() );	// LO_name
		r.setPm_product_id( (pri.getPm_product_id()==null)?sec.getPm_product_id():pri.getPm_product_id() );	// product_id
		r.setPm_PT_name( (pri.getPm_PT_name()==null)?sec.getPm_PT_name():pri.getPm_PT_name() );	// PT_name
		r.setPm_PT_whoCanSelect_AFU( (pri.getPm_PT_whoCanSelect_AFU()==null)?sec.getPm_PT_whoCanSelect_AFU():pri.getPm_PT_whoCanSelect_AFU() );	// PT_whoCanSelect_AFU
		r.setPm_PT_deadline( (pri.getPm_PT_deadline()==null)?sec.getPm_PT_deadline():pri.getPm_PT_deadline() );	// PT_deadline
		r.setPm_product_id_promo( (pri.getPm_product_id_promo()==null)?sec.getPm_product_id_promo():pri.getPm_product_id_promo() );	// product_id_promo
		r.setPm_deadline( (pri.getPm_deadline()==null)?sec.getPm_deadline():pri.getPm_deadline() );	// deadline
		r.setPm_json( (pri.getPm_json()==null)?sec.getPm_json():pri.getPm_json() );	// json
        }
        return r;
    }
    
    public void copyTo(PmBeanFiltro Destino) {
	Destino.setPm_sincro( getPm_sincro() ); // sincro
	Destino.setPm_mark( getPm_mark() ); // mark
	Destino.setPm_is_deleted( getPm_is_deleted() ); // is_deleted
	Destino.setPm_author( getPm_author() ); // author
	Destino.setPm_coupon_id( getPm_coupon_id() ); // coupon_id
	Destino.setPm_name( getPm_name() ); // name
	Destino.setPm_uses_per_user( getPm_uses_per_user() ); // uses_per_user
	Destino.setPm_places( getPm_places() ); // places
	Destino.setPm_location_id( getPm_location_id() ); // location_id
	Destino.setPm_LO_name( getPm_LO_name() ); // LO_name
	Destino.setPm_product_id( getPm_product_id() ); // product_id
	Destino.setPm_PT_name( getPm_PT_name() ); // PT_name
	Destino.setPm_PT_whoCanSelect_AFU( getPm_PT_whoCanSelect_AFU() ); // PT_whoCanSelect_AFU
	Destino.setPm_PT_deadline( getPm_PT_deadline() ); // PT_deadline
	Destino.setPm_product_id_promo( getPm_product_id_promo() ); // product_id_promo
	Destino.setPm_deadline( getPm_deadline() ); // deadline
	Destino.setPm_json( getPm_json() ); // json
    }
    
    public void copyFrom(PmBeanFiltro Origen) {
	setPm_sincro( Origen.getPm_sincro() ); // sincro
	setPm_mark( Origen.getPm_mark() ); // mark
	setPm_is_deleted( Origen.getPm_is_deleted() ); // is_deleted
	setPm_author( Origen.getPm_author() ); // author
	setPm_coupon_id( Origen.getPm_coupon_id() ); // coupon_id
	setPm_name( Origen.getPm_name() ); // name
	setPm_uses_per_user( Origen.getPm_uses_per_user() ); // uses_per_user
	setPm_places( Origen.getPm_places() ); // places
	setPm_location_id( Origen.getPm_location_id() ); // location_id
	setPm_LO_name( Origen.getPm_LO_name() ); // LO_name
	setPm_product_id( Origen.getPm_product_id() ); // product_id
	setPm_PT_name( Origen.getPm_PT_name() ); // PT_name
	setPm_PT_whoCanSelect_AFU( Origen.getPm_PT_whoCanSelect_AFU() ); // PT_whoCanSelect_AFU
	setPm_PT_deadline( Origen.getPm_PT_deadline() ); // PT_deadline
	setPm_product_id_promo( Origen.getPm_product_id_promo() ); // product_id_promo
	setPm_deadline( Origen.getPm_deadline() ); // deadline
	setPm_json( Origen.getPm_json() ); // json
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getPm_sincro()==null?"":this.getPm_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getPm_mark()==null?"":this.getPm_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getPm_is_deleted()==null?"":this.getPm_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getPm_author()==null?"":this.getPm_author() ); // author
		out.append( _K.sepFld ); out.append( this.getPm_coupon_id()==null?"":this.getPm_coupon_id() ); // coupon_id
		out.append( _K.sepFld ); out.append( this.getPm_name()==null?"":this.getPm_name() ); // name
		out.append( _K.sepFld ); out.append( this.getPm_uses_per_user()==null?"":this.getPm_uses_per_user() ); // uses_per_user
		out.append( _K.sepFld ); out.append( this.getPm_places()==null?"":this.getPm_places() ); // places
		out.append( _K.sepFld ); out.append( this.getPm_location_id()==null?"":this.getPm_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getPm_LO_name()==null?"":this.getPm_LO_name() ); // LO_name
		out.append( _K.sepFld ); out.append( this.getPm_product_id()==null?"":this.getPm_product_id() ); // product_id
		out.append( _K.sepFld ); out.append( this.getPm_PT_name()==null?"":this.getPm_PT_name() ); // PT_name
		out.append( _K.sepFld ); out.append( this.getPm_PT_whoCanSelect_AFU()==null?"":this.getPm_PT_whoCanSelect_AFU() ); // PT_whoCanSelect_AFU
		out.append( _K.sepFld ); out.append( this.getPm_PT_deadline()==null?"":this.getPm_PT_deadline() ); // PT_deadline
		out.append( _K.sepFld ); out.append( this.getPm_product_id_promo()==null?"":this.getPm_product_id_promo() ); // product_id_promo
		out.append( _K.sepFld ); out.append( this.getPm_deadline()==null?"":this.getPm_deadline() ); // deadline
		out.append( _K.sepFld ); out.append( this.getPm_json()==null?"":this.getPm_json() ); // json

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
			
			try { this.setPm_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setPm_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setPm_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setPm_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setPm_coupon_id( trozos[4] ); } catch (Exception e) {;} // coupon_id
			try { this.setPm_name( trozos[5] ); } catch (Exception e) {;} // name
			try { this.setPm_uses_per_user( trozos[6] ); } catch (Exception e) {;} // uses_per_user
			try { this.setPm_places( trozos[7] ); } catch (Exception e) {;} // places
			try { this.setPm_location_id( trozos[8] ); } catch (Exception e) {;} // location_id
			try { this.setPm_LO_name( trozos[9] ); } catch (Exception e) {;} // LO_name
			try { this.setPm_product_id( trozos[10] ); } catch (Exception e) {;} // product_id
			try { this.setPm_PT_name( trozos[11] ); } catch (Exception e) {;} // PT_name
			try { this.setPm_PT_whoCanSelect_AFU( trozos[12] ); } catch (Exception e) {;} // PT_whoCanSelect_AFU
			try { this.setPm_PT_deadline( trozos[13] ); } catch (Exception e) {;} // PT_deadline
			try { this.setPm_product_id_promo( trozos[14] ); } catch (Exception e) {;} // product_id_promo
			try { this.setPm_deadline( trozos[15] ); } catch (Exception e) {;} // deadline
			try { this.setPm_json( trozos[16] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get sincro*/
	public String getPm_sincro() {return pm_sincro;}
	/** Set sincro*/
	public void setPm_sincro(String pm_sincro) {this.pm_sincro = pm_sincro;}

	/** Get mark*/
	public String getPm_mark() {return pm_mark;}
	/** Set mark*/
	public void setPm_mark(String pm_mark) {this.pm_mark = pm_mark;}

	/** Get is_deleted*/
	public String getPm_is_deleted() {return pm_is_deleted;}
	/** Set is_deleted*/
	public void setPm_is_deleted(String pm_is_deleted) {this.pm_is_deleted = pm_is_deleted;}

	/** Get author*/
	public String getPm_author() {return pm_author;}
	/** Set author*/
	public void setPm_author(String pm_author) {this.pm_author = pm_author;}

	/** Get coupon_id*/
	public String getPm_coupon_id() {return pm_coupon_id;}
	/** Set coupon_id*/
	public void setPm_coupon_id(String pm_coupon_id) {this.pm_coupon_id = pm_coupon_id;}

	/** Get name*/
	public String getPm_name() {return pm_name;}
	/** Set name*/
	public void setPm_name(String pm_name) {this.pm_name = pm_name;}

	/** Get uses_per_user*/
	public String getPm_uses_per_user() {return pm_uses_per_user;}
	/** Set uses_per_user*/
	public void setPm_uses_per_user(String pm_uses_per_user) {this.pm_uses_per_user = pm_uses_per_user;}

	/** Get places*/
	public String getPm_places() {return pm_places;}
	/** Set places*/
	public void setPm_places(String pm_places) {this.pm_places = pm_places;}

	/** Get location_id*/
	public String getPm_location_id() {return pm_location_id;}
	/** Set location_id*/
	public void setPm_location_id(String pm_location_id) {this.pm_location_id = pm_location_id;}

	/** Get LO_name*/
	public String getPm_LO_name() {return pm_LO_name;}
	/** Set LO_name*/
	public void setPm_LO_name(String pm_LO_name) {this.pm_LO_name = pm_LO_name;}

	/** Get product_id*/
	public String getPm_product_id() {return pm_product_id;}
	/** Set product_id*/
	public void setPm_product_id(String pm_product_id) {this.pm_product_id = pm_product_id;}

	/** Get PT_name*/
	public String getPm_PT_name() {return pm_PT_name;}
	/** Set PT_name*/
	public void setPm_PT_name(String pm_PT_name) {this.pm_PT_name = pm_PT_name;}

	/** Get PT_whoCanSelect_AFU*/
	public String getPm_PT_whoCanSelect_AFU() {return pm_PT_whoCanSelect_AFU;}
	/** Set PT_whoCanSelect_AFU*/
	public void setPm_PT_whoCanSelect_AFU(String pm_PT_whoCanSelect_AFU) {this.pm_PT_whoCanSelect_AFU = pm_PT_whoCanSelect_AFU;}

	/** Get PT_deadline*/
	public String getPm_PT_deadline() {return pm_PT_deadline;}
	/** Set PT_deadline*/
	public void setPm_PT_deadline(String pm_PT_deadline) {this.pm_PT_deadline = pm_PT_deadline;}

	/** Get product_id_promo*/
	public String getPm_product_id_promo() {return pm_product_id_promo;}
	/** Set product_id_promo*/
	public void setPm_product_id_promo(String pm_product_id_promo) {this.pm_product_id_promo = pm_product_id_promo;}

	/** Get deadline*/
	public String getPm_deadline() {return pm_deadline;}
	/** Set deadline*/
	public void setPm_deadline(String pm_deadline) {this.pm_deadline = pm_deadline;}

	/** Get json*/
	public String getPm_json() {return pm_json;}
	/** Set json*/
	public void setPm_json(String pm_json) {this.pm_json = pm_json;}

}
