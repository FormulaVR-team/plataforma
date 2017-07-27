package com.fvr.pr_promos.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class PrBeanFiltro extends StBean {

	public String pr_sincro; // sincro
	public String pr_mark; // mark
	public String pr_is_deleted; // is_deleted
	public String pr_author; // author
	public String pr_location_id; // location_id
	public String pr_LO_name; // LO_name
	public String pr_product_id; // product_id
	public String pr_PT_name; // PT_name
	public String pr_product_id_promo; // product_id_promo
	public String pr_PT_name_promo; // PT_name_promo
	public String pr_deadline; // deadline
	public String pr_min_quantity; // min_quantity
    
    public PrBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public PrBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setPr_sincro( "" ); // sincro
	this.setPr_mark( "" ); // mark
	this.setPr_is_deleted( "" ); // is_deleted
	this.setPr_author( "" ); // author
	this.setPr_location_id( "" ); // location_id
	this.setPr_LO_name( "" ); // LO_name
	this.setPr_product_id( "" ); // product_id
	this.setPr_PT_name( "" ); // PT_name
	this.setPr_product_id_promo( "" ); // product_id_promo
	this.setPr_PT_name_promo( "" ); // PT_name_promo
	this.setPr_deadline( "" ); // deadline
	this.setPr_min_quantity( "" ); // min_quantity
    } 

    public PrBeanFiltro coalesce(PrBeanFiltro pri, PrBeanFiltro sec) {
        PrBeanFiltro r = new PrBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setPr_sincro( (pri.getPr_sincro()==null)?sec.getPr_sincro():pri.getPr_sincro() );	// sincro
		r.setPr_mark( (pri.getPr_mark()==null)?sec.getPr_mark():pri.getPr_mark() );	// mark
		r.setPr_is_deleted( (pri.getPr_is_deleted()==null)?sec.getPr_is_deleted():pri.getPr_is_deleted() );	// is_deleted
		r.setPr_author( (pri.getPr_author()==null)?sec.getPr_author():pri.getPr_author() );	// author
		r.setPr_location_id( (pri.getPr_location_id()==null)?sec.getPr_location_id():pri.getPr_location_id() );	// location_id
		r.setPr_LO_name( (pri.getPr_LO_name()==null)?sec.getPr_LO_name():pri.getPr_LO_name() );	// LO_name
		r.setPr_product_id( (pri.getPr_product_id()==null)?sec.getPr_product_id():pri.getPr_product_id() );	// product_id
		r.setPr_PT_name( (pri.getPr_PT_name()==null)?sec.getPr_PT_name():pri.getPr_PT_name() );	// PT_name
		r.setPr_product_id_promo( (pri.getPr_product_id_promo()==null)?sec.getPr_product_id_promo():pri.getPr_product_id_promo() );	// product_id_promo
		r.setPr_PT_name_promo( (pri.getPr_PT_name_promo()==null)?sec.getPr_PT_name_promo():pri.getPr_PT_name_promo() );	// PT_name_promo
		r.setPr_deadline( (pri.getPr_deadline()==null)?sec.getPr_deadline():pri.getPr_deadline() );	// deadline
		r.setPr_min_quantity( (pri.getPr_min_quantity()==null)?sec.getPr_min_quantity():pri.getPr_min_quantity() );	// min_quantity
        }
        return r;
    }
    
    public void copyTo(PrBeanFiltro Destino) {
	Destino.setPr_sincro( getPr_sincro() ); // sincro
	Destino.setPr_mark( getPr_mark() ); // mark
	Destino.setPr_is_deleted( getPr_is_deleted() ); // is_deleted
	Destino.setPr_author( getPr_author() ); // author
	Destino.setPr_location_id( getPr_location_id() ); // location_id
	Destino.setPr_LO_name( getPr_LO_name() ); // LO_name
	Destino.setPr_product_id( getPr_product_id() ); // product_id
	Destino.setPr_PT_name( getPr_PT_name() ); // PT_name
	Destino.setPr_product_id_promo( getPr_product_id_promo() ); // product_id_promo
	Destino.setPr_PT_name_promo( getPr_PT_name_promo() ); // PT_name_promo
	Destino.setPr_deadline( getPr_deadline() ); // deadline
	Destino.setPr_min_quantity( getPr_min_quantity() ); // min_quantity
    }
    
    public void copyFrom(PrBeanFiltro Origen) {
	setPr_sincro( Origen.getPr_sincro() ); // sincro
	setPr_mark( Origen.getPr_mark() ); // mark
	setPr_is_deleted( Origen.getPr_is_deleted() ); // is_deleted
	setPr_author( Origen.getPr_author() ); // author
	setPr_location_id( Origen.getPr_location_id() ); // location_id
	setPr_LO_name( Origen.getPr_LO_name() ); // LO_name
	setPr_product_id( Origen.getPr_product_id() ); // product_id
	setPr_PT_name( Origen.getPr_PT_name() ); // PT_name
	setPr_product_id_promo( Origen.getPr_product_id_promo() ); // product_id_promo
	setPr_PT_name_promo( Origen.getPr_PT_name_promo() ); // PT_name_promo
	setPr_deadline( Origen.getPr_deadline() ); // deadline
	setPr_min_quantity( Origen.getPr_min_quantity() ); // min_quantity
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getPr_sincro()==null?"":this.getPr_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getPr_mark()==null?"":this.getPr_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getPr_is_deleted()==null?"":this.getPr_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getPr_author()==null?"":this.getPr_author() ); // author
		out.append( _K.sepFld ); out.append( this.getPr_location_id()==null?"":this.getPr_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getPr_LO_name()==null?"":this.getPr_LO_name() ); // LO_name
		out.append( _K.sepFld ); out.append( this.getPr_product_id()==null?"":this.getPr_product_id() ); // product_id
		out.append( _K.sepFld ); out.append( this.getPr_PT_name()==null?"":this.getPr_PT_name() ); // PT_name
		out.append( _K.sepFld ); out.append( this.getPr_product_id_promo()==null?"":this.getPr_product_id_promo() ); // product_id_promo
		out.append( _K.sepFld ); out.append( this.getPr_PT_name_promo()==null?"":this.getPr_PT_name_promo() ); // PT_name_promo
		out.append( _K.sepFld ); out.append( this.getPr_deadline()==null?"":this.getPr_deadline() ); // deadline
		out.append( _K.sepFld ); out.append( this.getPr_min_quantity()==null?"":this.getPr_min_quantity() ); // min_quantity

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
			
			try { this.setPr_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setPr_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setPr_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setPr_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setPr_location_id( trozos[4] ); } catch (Exception e) {;} // location_id
			try { this.setPr_LO_name( trozos[5] ); } catch (Exception e) {;} // LO_name
			try { this.setPr_product_id( trozos[6] ); } catch (Exception e) {;} // product_id
			try { this.setPr_PT_name( trozos[7] ); } catch (Exception e) {;} // PT_name
			try { this.setPr_product_id_promo( trozos[8] ); } catch (Exception e) {;} // product_id_promo
			try { this.setPr_PT_name_promo( trozos[9] ); } catch (Exception e) {;} // PT_name_promo
			try { this.setPr_deadline( trozos[10] ); } catch (Exception e) {;} // deadline
			try { this.setPr_min_quantity( trozos[11] ); } catch (Exception e) {;} // min_quantity
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get sincro*/
	public String getPr_sincro() {return pr_sincro;}
	/** Set sincro*/
	public void setPr_sincro(String pr_sincro) {this.pr_sincro = pr_sincro;}

	/** Get mark*/
	public String getPr_mark() {return pr_mark;}
	/** Set mark*/
	public void setPr_mark(String pr_mark) {this.pr_mark = pr_mark;}

	/** Get is_deleted*/
	public String getPr_is_deleted() {return pr_is_deleted;}
	/** Set is_deleted*/
	public void setPr_is_deleted(String pr_is_deleted) {this.pr_is_deleted = pr_is_deleted;}

	/** Get author*/
	public String getPr_author() {return pr_author;}
	/** Set author*/
	public void setPr_author(String pr_author) {this.pr_author = pr_author;}

	/** Get location_id*/
	public String getPr_location_id() {return pr_location_id;}
	/** Set location_id*/
	public void setPr_location_id(String pr_location_id) {this.pr_location_id = pr_location_id;}

	/** Get LO_name*/
	public String getPr_LO_name() {return pr_LO_name;}
	/** Set LO_name*/
	public void setPr_LO_name(String pr_LO_name) {this.pr_LO_name = pr_LO_name;}

	/** Get product_id*/
	public String getPr_product_id() {return pr_product_id;}
	/** Set product_id*/
	public void setPr_product_id(String pr_product_id) {this.pr_product_id = pr_product_id;}

	/** Get PT_name*/
	public String getPr_PT_name() {return pr_PT_name;}
	/** Set PT_name*/
	public void setPr_PT_name(String pr_PT_name) {this.pr_PT_name = pr_PT_name;}

	/** Get product_id_promo*/
	public String getPr_product_id_promo() {return pr_product_id_promo;}
	/** Set product_id_promo*/
	public void setPr_product_id_promo(String pr_product_id_promo) {this.pr_product_id_promo = pr_product_id_promo;}

	/** Get PT_name_promo*/
	public String getPr_PT_name_promo() {return pr_PT_name_promo;}
	/** Set PT_name_promo*/
	public void setPr_PT_name_promo(String pr_PT_name_promo) {this.pr_PT_name_promo = pr_PT_name_promo;}

	/** Get deadline*/
	public String getPr_deadline() {return pr_deadline;}
	/** Set deadline*/
	public void setPr_deadline(String pr_deadline) {this.pr_deadline = pr_deadline;}

	/** Get min_quantity*/
	public String getPr_min_quantity() {return pr_min_quantity;}
	/** Set min_quantity*/
	public void setPr_min_quantity(String pr_min_quantity) {this.pr_min_quantity = pr_min_quantity;}

}
