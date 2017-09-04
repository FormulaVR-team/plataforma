package com.fvr.tj_tarjetasPrepago.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class TjBean extends StBean {

	public String tj_sincro; // sincro
	public String tj_mark; // mark
	public String tj_is_deleted; // is_deleted
	public String tj_author; // author
	public String tj_card_id; // card_id
	public double tj_balance_initial; // balance_initial
	public double tj_balance_current; // balance_current
	public double tj_last_sale_amount; // last_sale_amount
	public String tj_last_sale_moment; // last_sale_moment
    
    public TjBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public TjBean(Object nulo) { super(); }

    public void inicializar() {
	this.setTj_sincro( "" ); // sincro
	this.setTj_mark( "" ); // mark
	this.setTj_is_deleted( "" ); // is_deleted
	this.setTj_author( "" ); // author
	this.setTj_card_id( "" ); // card_id
	this.setTj_balance_initial( 0.0 ); // balance_initial
	this.setTj_balance_current( 0.0 ); // balance_current
	this.setTj_last_sale_amount( 0.0 ); // last_sale_amount
	this.setTj_last_sale_moment( "" ); // last_sale_moment
    } 
 /*
    public void copyTo(StBean beanDestino) {
        TjBean Destino = (TjBean)beanDestino;

	Destino.setTj_sincro( getTj_sincro() ); // sincro
	Destino.setTj_mark( getTj_mark() ); // mark
	Destino.setTj_is_deleted( getTj_is_deleted() ); // is_deleted
	Destino.setTj_author( getTj_author() ); // author
	Destino.setTj_card_id( getTj_card_id() ); // card_id
	Destino.setTj_balance_initial( getTj_balance_initial() ); // balance_initial
	Destino.setTj_balance_current( getTj_balance_current() ); // balance_current
	Destino.setTj_last_sale_amount( getTj_last_sale_amount() ); // last_sale_amount
	Destino.setTj_last_sale_moment( getTj_last_sale_moment() ); // last_sale_moment
    }
    
    public void copyFrom(StBean beanOrigen) {
        TjBean Origen = (TjBean)beanOrigen;

	setTj_sincro( Origen.getTj_sincro() ); // sincro
	setTj_mark( Origen.getTj_mark() ); // mark
	setTj_is_deleted( Origen.getTj_is_deleted() ); // is_deleted
	setTj_author( Origen.getTj_author() ); // author
	setTj_card_id( Origen.getTj_card_id() ); // card_id
	setTj_balance_initial( Origen.getTj_balance_initial() ); // balance_initial
	setTj_balance_current( Origen.getTj_balance_current() ); // balance_current
	setTj_last_sale_amount( Origen.getTj_last_sale_amount() ); // last_sale_amount
	setTj_last_sale_moment( Origen.getTj_last_sale_moment() ); // last_sale_moment
    }
*/


	/** Get sincro*/
	public String getTj_sincro() {return tj_sincro;}
	/** Set sincro*/
	public void setTj_sincro(String tj_sincro) {this.tj_sincro = tj_sincro;}

	/** Get mark*/
	public String getTj_mark() {return tj_mark;}
	/** Set mark*/
	public void setTj_mark(String tj_mark) {this.tj_mark = tj_mark;}

	/** Get is_deleted*/
	public String getTj_is_deleted() {return tj_is_deleted;}
	/** Set is_deleted*/
	public void setTj_is_deleted(String tj_is_deleted) {this.tj_is_deleted = tj_is_deleted;}

	/** Get author*/
	public String getTj_author() {return tj_author;}
	/** Set author*/
	public void setTj_author(String tj_author) {this.tj_author = tj_author;}

	/** Get card_id*/
	public String getTj_card_id() {return tj_card_id;}
	/** Set card_id*/
	public void setTj_card_id(String tj_card_id) {this.tj_card_id = tj_card_id;}

	/** Get balance_initial*/
	public double getTj_balance_initial() {return tj_balance_initial;}
	/** Set balance_initial*/
	public void setTj_balance_initial(double tj_balance_initial) {this.tj_balance_initial = tj_balance_initial;}

	/** Get balance_current*/
	public double getTj_balance_current() {return tj_balance_current;}
	/** Set balance_current*/
	public void setTj_balance_current(double tj_balance_current) {this.tj_balance_current = tj_balance_current;}

	/** Get last_sale_amount*/
	public double getTj_last_sale_amount() {return tj_last_sale_amount;}
	/** Set last_sale_amount*/
	public void setTj_last_sale_amount(double tj_last_sale_amount) {this.tj_last_sale_amount = tj_last_sale_amount;}

	/** Get last_sale_moment*/
	public String getTj_last_sale_moment() {return tj_last_sale_moment;}
	/** Set last_sale_moment*/
	public void setTj_last_sale_moment(String tj_last_sale_moment) {this.tj_last_sale_moment = tj_last_sale_moment;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return tj_card_id;}

    public void setKey(String key){
            String k="";
	k = key; this.setTj_card_id( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getTj_sincro()==null?"":this.getTj_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getTj_mark()==null?"":this.getTj_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getTj_is_deleted()==null?"":this.getTj_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getTj_author()==null?"":this.getTj_author() ); // author
		out.append( _K.sepFld ); out.append( this.getTj_card_id()==null?"":this.getTj_card_id() ); // card_id
		out.append( _K.sepFld ); out.append( this.getTj_balance_initial() ); // balance_initial
		out.append( _K.sepFld ); out.append( this.getTj_balance_current() ); // balance_current
		out.append( _K.sepFld ); out.append( this.getTj_last_sale_amount() ); // last_sale_amount
		out.append( _K.sepFld ); out.append( this.getTj_last_sale_moment()==null?"":this.getTj_last_sale_moment() ); // last_sale_moment

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
			
			try { this.setTj_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setTj_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setTj_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setTj_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setTj_card_id( trozos[4] ); } catch (Exception e) {;} // card_id
			try { this.setTj_balance_initial( Subrutinas.parse_double( trozos[5] )); } catch (Exception e) {;} // balance_initial
			try { this.setTj_balance_current( Subrutinas.parse_double( trozos[6] )); } catch (Exception e) {;} // balance_current
			try { this.setTj_last_sale_amount( Subrutinas.parse_double( trozos[7] )); } catch (Exception e) {;} // last_sale_amount
			try { this.setTj_last_sale_moment( trozos[8] ); } catch (Exception e) {;} // last_sale_moment
			
		}
	}
	////////////////////////////////////////////////////////////

}
