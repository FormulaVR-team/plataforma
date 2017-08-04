package com.fvr.ac_activityCockpits.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class AcBean extends StBean {

	public String ac_sincro; // sincro
	public String ac_mark; // mark
	public String ac_is_deleted; // is_deleted
	public String ac_author; // author
	public long   ac_serial; // serial
	public String ac_computername; // computername
	public String ac_filename; // filename
	public String ac_content; // content
	public String ac_json; // json
    
    public AcBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public AcBean(Object nulo) { super(); }

    public void inicializar() {
	this.setAc_sincro( "" ); // sincro
	this.setAc_mark( "" ); // mark
	this.setAc_is_deleted( "" ); // is_deleted
	this.setAc_author( "" ); // author
	this.setAc_serial( 0 ); // serial
	this.setAc_computername( "" ); // computername
	this.setAc_filename( "" ); // filename
	this.setAc_content( "" ); // content
	this.setAc_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        AcBean Destino = (AcBean)beanDestino;

	Destino.setAc_sincro( getAc_sincro() ); // sincro
	Destino.setAc_mark( getAc_mark() ); // mark
	Destino.setAc_is_deleted( getAc_is_deleted() ); // is_deleted
	Destino.setAc_author( getAc_author() ); // author
	Destino.setAc_serial( getAc_serial() ); // serial
	Destino.setAc_computername( getAc_computername() ); // computername
	Destino.setAc_filename( getAc_filename() ); // filename
	Destino.setAc_content( getAc_content() ); // content
	Destino.setAc_json( getAc_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        AcBean Origen = (AcBean)beanOrigen;

	setAc_sincro( Origen.getAc_sincro() ); // sincro
	setAc_mark( Origen.getAc_mark() ); // mark
	setAc_is_deleted( Origen.getAc_is_deleted() ); // is_deleted
	setAc_author( Origen.getAc_author() ); // author
	setAc_serial( Origen.getAc_serial() ); // serial
	setAc_computername( Origen.getAc_computername() ); // computername
	setAc_filename( Origen.getAc_filename() ); // filename
	setAc_content( Origen.getAc_content() ); // content
	setAc_json( Origen.getAc_json() ); // json
    }
*/


	/** Get sincro*/
	public String getAc_sincro() {return ac_sincro;}
	/** Set sincro*/
	public void setAc_sincro(String ac_sincro) {this.ac_sincro = ac_sincro;}

	/** Get mark*/
	public String getAc_mark() {return ac_mark;}
	/** Set mark*/
	public void setAc_mark(String ac_mark) {this.ac_mark = ac_mark;}

	/** Get is_deleted*/
	public String getAc_is_deleted() {return ac_is_deleted;}
	/** Set is_deleted*/
	public void setAc_is_deleted(String ac_is_deleted) {this.ac_is_deleted = ac_is_deleted;}

	/** Get author*/
	public String getAc_author() {return ac_author;}
	/** Set author*/
	public void setAc_author(String ac_author) {this.ac_author = ac_author;}

	/** Get serial*/
	public long getAc_serial() {return ac_serial;}
	/** Set serial*/
	public void setAc_serial(long ac_serial) {this.ac_serial = ac_serial;}

	/** Get computername*/
	public String getAc_computername() {return ac_computername;}
	/** Set computername*/
	public void setAc_computername(String ac_computername) {this.ac_computername = ac_computername;}

	/** Get filename*/
	public String getAc_filename() {return ac_filename;}
	/** Set filename*/
	public void setAc_filename(String ac_filename) {this.ac_filename = ac_filename;}

	/** Get content*/
	public String getAc_content() {return ac_content;}
	/** Set content*/
	public void setAc_content(String ac_content) {this.ac_content = ac_content;}

	/** Get json*/
	public String getAc_json() {return ac_json;}
	/** Set json*/
	public void setAc_json(String ac_json) {this.ac_json = ac_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return new Long(ac_serial).toString();}

    public void setKey(String key){
            String k="";
	k = key; this.setAc_serial( new Long(k).longValue() );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getAc_sincro()==null?"":this.getAc_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getAc_mark()==null?"":this.getAc_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getAc_is_deleted()==null?"":this.getAc_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getAc_author()==null?"":this.getAc_author() ); // author
		out.append( _K.sepFld ); out.append( this.getAc_serial() ); // serial
		out.append( _K.sepFld ); out.append( this.getAc_computername()==null?"":this.getAc_computername() ); // computername
		out.append( _K.sepFld ); out.append( this.getAc_filename()==null?"":this.getAc_filename() ); // filename
		out.append( _K.sepFld ); out.append( this.getAc_content()==null?"":this.getAc_content() ); // content
		out.append( _K.sepFld ); out.append( this.getAc_json()==null?"":this.getAc_json() ); // json

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
			
			try { this.setAc_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setAc_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setAc_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setAc_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setAc_serial( Subrutinas.parse_long( trozos[4] )); } catch (Exception e) {;} // serial
			try { this.setAc_computername( trozos[5] ); } catch (Exception e) {;} // computername
			try { this.setAc_filename( trozos[6] ); } catch (Exception e) {;} // filename
			try { this.setAc_content( trozos[7] ); } catch (Exception e) {;} // content
			try { this.setAc_json( trozos[8] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
