package com.fvr.pt_products.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class PtBeanFiltro extends StBean {

	public String pt_sincro; // sincro
	public String pt_mark; // mark
	public String pt_is_deleted; // is_deleted
	public String pt_author; // author
	public String pt_product_id; // product_id
	public String pt_name; // name
	public String pt_whoCanSelect_AFU; // whoCanSelect_AFU
	public String pt_deadline; // deadline
	public String pt_isPercent; // isPercent
	public String pt_amount; // amount
	public String pt_currency; // currency
	public String pt_duration_minutes; // duration_minutes
	public String pt_json; // json
    
    public PtBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public PtBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setPt_sincro( "" ); // sincro
	this.setPt_mark( "" ); // mark
	this.setPt_is_deleted( "" ); // is_deleted
	this.setPt_author( "" ); // author
	this.setPt_product_id( "" ); // product_id
	this.setPt_name( "" ); // name
	this.setPt_whoCanSelect_AFU( "" ); // whoCanSelect_AFU
	this.setPt_deadline( "" ); // deadline
	this.setPt_isPercent( "" ); // isPercent
	this.setPt_amount( "" ); // amount
	this.setPt_currency( "" ); // currency
	this.setPt_duration_minutes( "" ); // duration_minutes
	this.setPt_json( "" ); // json
    } 

    public PtBeanFiltro coalesce(PtBeanFiltro pri, PtBeanFiltro sec) {
        PtBeanFiltro r = new PtBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setPt_sincro( (pri.getPt_sincro()==null)?sec.getPt_sincro():pri.getPt_sincro() );	// sincro
		r.setPt_mark( (pri.getPt_mark()==null)?sec.getPt_mark():pri.getPt_mark() );	// mark
		r.setPt_is_deleted( (pri.getPt_is_deleted()==null)?sec.getPt_is_deleted():pri.getPt_is_deleted() );	// is_deleted
		r.setPt_author( (pri.getPt_author()==null)?sec.getPt_author():pri.getPt_author() );	// author
		r.setPt_product_id( (pri.getPt_product_id()==null)?sec.getPt_product_id():pri.getPt_product_id() );	// product_id
		r.setPt_name( (pri.getPt_name()==null)?sec.getPt_name():pri.getPt_name() );	// name
		r.setPt_whoCanSelect_AFU( (pri.getPt_whoCanSelect_AFU()==null)?sec.getPt_whoCanSelect_AFU():pri.getPt_whoCanSelect_AFU() );	// whoCanSelect_AFU
		r.setPt_deadline( (pri.getPt_deadline()==null)?sec.getPt_deadline():pri.getPt_deadline() );	// deadline
		r.setPt_isPercent( (pri.getPt_isPercent()==null)?sec.getPt_isPercent():pri.getPt_isPercent() );	// isPercent
		r.setPt_amount( (pri.getPt_amount()==null)?sec.getPt_amount():pri.getPt_amount() );	// amount
		r.setPt_currency( (pri.getPt_currency()==null)?sec.getPt_currency():pri.getPt_currency() );	// currency
		r.setPt_duration_minutes( (pri.getPt_duration_minutes()==null)?sec.getPt_duration_minutes():pri.getPt_duration_minutes() );	// duration_minutes
		r.setPt_json( (pri.getPt_json()==null)?sec.getPt_json():pri.getPt_json() );	// json
        }
        return r;
    }
    
    public void copyTo(PtBeanFiltro Destino) {
	Destino.setPt_sincro( getPt_sincro() ); // sincro
	Destino.setPt_mark( getPt_mark() ); // mark
	Destino.setPt_is_deleted( getPt_is_deleted() ); // is_deleted
	Destino.setPt_author( getPt_author() ); // author
	Destino.setPt_product_id( getPt_product_id() ); // product_id
	Destino.setPt_name( getPt_name() ); // name
	Destino.setPt_whoCanSelect_AFU( getPt_whoCanSelect_AFU() ); // whoCanSelect_AFU
	Destino.setPt_deadline( getPt_deadline() ); // deadline
	Destino.setPt_isPercent( getPt_isPercent() ); // isPercent
	Destino.setPt_amount( getPt_amount() ); // amount
	Destino.setPt_currency( getPt_currency() ); // currency
	Destino.setPt_duration_minutes( getPt_duration_minutes() ); // duration_minutes
	Destino.setPt_json( getPt_json() ); // json
    }
    
    public void copyFrom(PtBeanFiltro Origen) {
	setPt_sincro( Origen.getPt_sincro() ); // sincro
	setPt_mark( Origen.getPt_mark() ); // mark
	setPt_is_deleted( Origen.getPt_is_deleted() ); // is_deleted
	setPt_author( Origen.getPt_author() ); // author
	setPt_product_id( Origen.getPt_product_id() ); // product_id
	setPt_name( Origen.getPt_name() ); // name
	setPt_whoCanSelect_AFU( Origen.getPt_whoCanSelect_AFU() ); // whoCanSelect_AFU
	setPt_deadline( Origen.getPt_deadline() ); // deadline
	setPt_isPercent( Origen.getPt_isPercent() ); // isPercent
	setPt_amount( Origen.getPt_amount() ); // amount
	setPt_currency( Origen.getPt_currency() ); // currency
	setPt_duration_minutes( Origen.getPt_duration_minutes() ); // duration_minutes
	setPt_json( Origen.getPt_json() ); // json
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getPt_sincro()==null?"":this.getPt_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getPt_mark()==null?"":this.getPt_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getPt_is_deleted()==null?"":this.getPt_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getPt_author()==null?"":this.getPt_author() ); // author
		out.append( _K.sepFld ); out.append( this.getPt_product_id()==null?"":this.getPt_product_id() ); // product_id
		out.append( _K.sepFld ); out.append( this.getPt_name()==null?"":this.getPt_name() ); // name
		out.append( _K.sepFld ); out.append( this.getPt_whoCanSelect_AFU()==null?"":this.getPt_whoCanSelect_AFU() ); // whoCanSelect_AFU
		out.append( _K.sepFld ); out.append( this.getPt_deadline()==null?"":this.getPt_deadline() ); // deadline
		out.append( _K.sepFld ); out.append( this.getPt_isPercent()==null?"":this.getPt_isPercent() ); // isPercent
		out.append( _K.sepFld ); out.append( this.getPt_amount()==null?"":this.getPt_amount() ); // amount
		out.append( _K.sepFld ); out.append( this.getPt_currency()==null?"":this.getPt_currency() ); // currency
		out.append( _K.sepFld ); out.append( this.getPt_duration_minutes()==null?"":this.getPt_duration_minutes() ); // duration_minutes
		out.append( _K.sepFld ); out.append( this.getPt_json()==null?"":this.getPt_json() ); // json

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
			
			try { this.setPt_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setPt_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setPt_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setPt_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setPt_product_id( trozos[4] ); } catch (Exception e) {;} // product_id
			try { this.setPt_name( trozos[5] ); } catch (Exception e) {;} // name
			try { this.setPt_whoCanSelect_AFU( trozos[6] ); } catch (Exception e) {;} // whoCanSelect_AFU
			try { this.setPt_deadline( trozos[7] ); } catch (Exception e) {;} // deadline
			try { this.setPt_isPercent( trozos[8] ); } catch (Exception e) {;} // isPercent
			try { this.setPt_amount( trozos[9] ); } catch (Exception e) {;} // amount
			try { this.setPt_currency( trozos[10] ); } catch (Exception e) {;} // currency
			try { this.setPt_duration_minutes( trozos[11] ); } catch (Exception e) {;} // duration_minutes
			try { this.setPt_json( trozos[12] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

	/** Get sincro*/
	public String getPt_sincro() {return pt_sincro;}
	/** Set sincro*/
	public void setPt_sincro(String pt_sincro) {this.pt_sincro = pt_sincro;}

	/** Get mark*/
	public String getPt_mark() {return pt_mark;}
	/** Set mark*/
	public void setPt_mark(String pt_mark) {this.pt_mark = pt_mark;}

	/** Get is_deleted*/
	public String getPt_is_deleted() {return pt_is_deleted;}
	/** Set is_deleted*/
	public void setPt_is_deleted(String pt_is_deleted) {this.pt_is_deleted = pt_is_deleted;}

	/** Get author*/
	public String getPt_author() {return pt_author;}
	/** Set author*/
	public void setPt_author(String pt_author) {this.pt_author = pt_author;}

	/** Get product_id*/
	public String getPt_product_id() {return pt_product_id;}
	/** Set product_id*/
	public void setPt_product_id(String pt_product_id) {this.pt_product_id = pt_product_id;}

	/** Get name*/
	public String getPt_name() {return pt_name;}
	/** Set name*/
	public void setPt_name(String pt_name) {this.pt_name = pt_name;}

	/** Get whoCanSelect_AFU*/
	public String getPt_whoCanSelect_AFU() {return pt_whoCanSelect_AFU;}
	/** Set whoCanSelect_AFU*/
	public void setPt_whoCanSelect_AFU(String pt_whoCanSelect_AFU) {this.pt_whoCanSelect_AFU = pt_whoCanSelect_AFU;}

	/** Get deadline*/
	public String getPt_deadline() {return pt_deadline;}
	/** Set deadline*/
	public void setPt_deadline(String pt_deadline) {this.pt_deadline = pt_deadline;}

	/** Get isPercent*/
	public String getPt_isPercent() {return pt_isPercent;}
	/** Set isPercent*/
	public void setPt_isPercent(String pt_isPercent) {this.pt_isPercent = pt_isPercent;}

	/** Get amount*/
	public String getPt_amount() {return pt_amount;}
	/** Set amount*/
	public void setPt_amount(String pt_amount) {this.pt_amount = pt_amount;}

	/** Get currency*/
	public String getPt_currency() {return pt_currency;}
	/** Set currency*/
	public void setPt_currency(String pt_currency) {this.pt_currency = pt_currency;}

	/** Get duration_minutes*/
	public String getPt_duration_minutes() {return pt_duration_minutes;}
	/** Set duration_minutes*/
	public void setPt_duration_minutes(String pt_duration_minutes) {this.pt_duration_minutes = pt_duration_minutes;}

	/** Get json*/
	public String getPt_json() {return pt_json;}
	/** Set json*/
	public void setPt_json(String pt_json) {this.pt_json = pt_json;}

}
