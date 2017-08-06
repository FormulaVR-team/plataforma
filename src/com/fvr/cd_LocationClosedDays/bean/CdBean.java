package com.fvr.cd_LocationClosedDays.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class CdBean extends StBean {

	public String cd_sincro; // sincro
	public String cd_mark; // mark
	public String cd_is_deleted; // is_deleted
	public String cd_author; // author
	public String cd_location_id; // location_id
	public String cd_closed_day_aaaa_mm_dd; // closed_day_aaaa_mm_dd
	public String cd_json; // json
    
    public CdBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public CdBean(Object nulo) { super(); }

    public void inicializar() {
	this.setCd_sincro( "" ); // sincro
	this.setCd_mark( "" ); // mark
	this.setCd_is_deleted( "" ); // is_deleted
	this.setCd_author( "" ); // author
	this.setCd_location_id( "" ); // location_id
	this.setCd_closed_day_aaaa_mm_dd( "" ); // closed_day_aaaa_mm_dd
	this.setCd_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        CdBean Destino = (CdBean)beanDestino;

	Destino.setCd_sincro( getCd_sincro() ); // sincro
	Destino.setCd_mark( getCd_mark() ); // mark
	Destino.setCd_is_deleted( getCd_is_deleted() ); // is_deleted
	Destino.setCd_author( getCd_author() ); // author
	Destino.setCd_location_id( getCd_location_id() ); // location_id
	Destino.setCd_closed_day_aaaa_mm_dd( getCd_closed_day_aaaa_mm_dd() ); // closed_day_aaaa_mm_dd
	Destino.setCd_json( getCd_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        CdBean Origen = (CdBean)beanOrigen;

	setCd_sincro( Origen.getCd_sincro() ); // sincro
	setCd_mark( Origen.getCd_mark() ); // mark
	setCd_is_deleted( Origen.getCd_is_deleted() ); // is_deleted
	setCd_author( Origen.getCd_author() ); // author
	setCd_location_id( Origen.getCd_location_id() ); // location_id
	setCd_closed_day_aaaa_mm_dd( Origen.getCd_closed_day_aaaa_mm_dd() ); // closed_day_aaaa_mm_dd
	setCd_json( Origen.getCd_json() ); // json
    }
*/


	/** Get sincro*/
	public String getCd_sincro() {return cd_sincro;}
	/** Set sincro*/
	public void setCd_sincro(String cd_sincro) {this.cd_sincro = cd_sincro;}

	/** Get mark*/
	public String getCd_mark() {return cd_mark;}
	/** Set mark*/
	public void setCd_mark(String cd_mark) {this.cd_mark = cd_mark;}

	/** Get is_deleted*/
	public String getCd_is_deleted() {return cd_is_deleted;}
	/** Set is_deleted*/
	public void setCd_is_deleted(String cd_is_deleted) {this.cd_is_deleted = cd_is_deleted;}

	/** Get author*/
	public String getCd_author() {return cd_author;}
	/** Set author*/
	public void setCd_author(String cd_author) {this.cd_author = cd_author;}

	/** Get location_id*/
	public String getCd_location_id() {return cd_location_id;}
	/** Set location_id*/
	public void setCd_location_id(String cd_location_id) {this.cd_location_id = cd_location_id;}

	/** Get closed_day_aaaa_mm_dd*/
	public String getCd_closed_day_aaaa_mm_dd() {return cd_closed_day_aaaa_mm_dd;}
	/** Set closed_day_aaaa_mm_dd*/
	public void setCd_closed_day_aaaa_mm_dd(String cd_closed_day_aaaa_mm_dd) {this.cd_closed_day_aaaa_mm_dd = cd_closed_day_aaaa_mm_dd;}

	/** Get json*/
	public String getCd_json() {return cd_json;}
	/** Set json*/
	public void setCd_json(String cd_json) {this.cd_json = cd_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return cd_location_id + "^" + 
		cd_closed_day_aaaa_mm_dd;}

    public void setKey(String key){
            String k="";
	k = key.trim().substring( 0, key.indexOf("^") ); this.setCd_location_id( k ); key = key.trim().substring( key.indexOf("^")+1 );
	k = key; this.setCd_closed_day_aaaa_mm_dd( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getCd_sincro()==null?"":this.getCd_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getCd_mark()==null?"":this.getCd_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getCd_is_deleted()==null?"":this.getCd_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getCd_author()==null?"":this.getCd_author() ); // author
		out.append( _K.sepFld ); out.append( this.getCd_location_id()==null?"":this.getCd_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getCd_closed_day_aaaa_mm_dd()==null?"":this.getCd_closed_day_aaaa_mm_dd() ); // closed_day_aaaa_mm_dd
		out.append( _K.sepFld ); out.append( this.getCd_json()==null?"":this.getCd_json() ); // json

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
			
			try { this.setCd_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setCd_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setCd_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setCd_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setCd_location_id( trozos[4] ); } catch (Exception e) {;} // location_id
			try { this.setCd_closed_day_aaaa_mm_dd( trozos[5] ); } catch (Exception e) {;} // closed_day_aaaa_mm_dd
			try { this.setCd_json( trozos[6] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
