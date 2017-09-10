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
	public String ac_location_id; // location_id
	public String ac_LO_name; // LO_name
	public String ac_computername; // computername
	public String ac_filename; // filename
	public String ac_content; // content
	public String ac_json; // json
	public String ac_aaaa_mm; // aaaa_mm
	public String ac_aaaa_mm_dd; // aaaa_mm_dd
	public String ac_aaaa_mm_dd_hh; // aaaa_mm_dd_hh
	public String ac_aaaa_mm_dd_hh_m0; // aaaa_mm_dd_hh_m0
	public String ac_aaaa_mm_dd_hh_mm; // aaaa_mm_dd_hh_mm
	public String ac_aaaa_mm_dd_hh_mm_ss; // aaaa_mm_dd_hh_mm_ss
    
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
	this.setAc_location_id( "" ); // location_id
	this.setAc_LO_name( "" ); // LO_name
	this.setAc_computername( "" ); // computername
	this.setAc_filename( "" ); // filename
	this.setAc_content( "" ); // content
	this.setAc_json( "" ); // json
	this.setAc_aaaa_mm( "" ); // aaaa_mm
	this.setAc_aaaa_mm_dd( "" ); // aaaa_mm_dd
	this.setAc_aaaa_mm_dd_hh( "" ); // aaaa_mm_dd_hh
	this.setAc_aaaa_mm_dd_hh_m0( "" ); // aaaa_mm_dd_hh_m0
	this.setAc_aaaa_mm_dd_hh_mm( "" ); // aaaa_mm_dd_hh_mm
	this.setAc_aaaa_mm_dd_hh_mm_ss( "" ); // aaaa_mm_dd_hh_mm_ss
    } 
 /*
    public void copyTo(StBean beanDestino) {
        AcBean Destino = (AcBean)beanDestino;

	Destino.setAc_sincro( getAc_sincro() ); // sincro
	Destino.setAc_mark( getAc_mark() ); // mark
	Destino.setAc_is_deleted( getAc_is_deleted() ); // is_deleted
	Destino.setAc_author( getAc_author() ); // author
	Destino.setAc_serial( getAc_serial() ); // serial
	Destino.setAc_location_id( getAc_location_id() ); // location_id
	Destino.setAc_LO_name( getAc_LO_name() ); // LO_name
	Destino.setAc_computername( getAc_computername() ); // computername
	Destino.setAc_filename( getAc_filename() ); // filename
	Destino.setAc_content( getAc_content() ); // content
	Destino.setAc_json( getAc_json() ); // json
	Destino.setAc_aaaa_mm( getAc_aaaa_mm() ); // aaaa_mm
	Destino.setAc_aaaa_mm_dd( getAc_aaaa_mm_dd() ); // aaaa_mm_dd
	Destino.setAc_aaaa_mm_dd_hh( getAc_aaaa_mm_dd_hh() ); // aaaa_mm_dd_hh
	Destino.setAc_aaaa_mm_dd_hh_m0( getAc_aaaa_mm_dd_hh_m0() ); // aaaa_mm_dd_hh_m0
	Destino.setAc_aaaa_mm_dd_hh_mm( getAc_aaaa_mm_dd_hh_mm() ); // aaaa_mm_dd_hh_mm
	Destino.setAc_aaaa_mm_dd_hh_mm_ss( getAc_aaaa_mm_dd_hh_mm_ss() ); // aaaa_mm_dd_hh_mm_ss
    }
    
    public void copyFrom(StBean beanOrigen) {
        AcBean Origen = (AcBean)beanOrigen;

	setAc_sincro( Origen.getAc_sincro() ); // sincro
	setAc_mark( Origen.getAc_mark() ); // mark
	setAc_is_deleted( Origen.getAc_is_deleted() ); // is_deleted
	setAc_author( Origen.getAc_author() ); // author
	setAc_serial( Origen.getAc_serial() ); // serial
	setAc_location_id( Origen.getAc_location_id() ); // location_id
	setAc_LO_name( Origen.getAc_LO_name() ); // LO_name
	setAc_computername( Origen.getAc_computername() ); // computername
	setAc_filename( Origen.getAc_filename() ); // filename
	setAc_content( Origen.getAc_content() ); // content
	setAc_json( Origen.getAc_json() ); // json
	setAc_aaaa_mm( Origen.getAc_aaaa_mm() ); // aaaa_mm
	setAc_aaaa_mm_dd( Origen.getAc_aaaa_mm_dd() ); // aaaa_mm_dd
	setAc_aaaa_mm_dd_hh( Origen.getAc_aaaa_mm_dd_hh() ); // aaaa_mm_dd_hh
	setAc_aaaa_mm_dd_hh_m0( Origen.getAc_aaaa_mm_dd_hh_m0() ); // aaaa_mm_dd_hh_m0
	setAc_aaaa_mm_dd_hh_mm( Origen.getAc_aaaa_mm_dd_hh_mm() ); // aaaa_mm_dd_hh_mm
	setAc_aaaa_mm_dd_hh_mm_ss( Origen.getAc_aaaa_mm_dd_hh_mm_ss() ); // aaaa_mm_dd_hh_mm_ss
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

	/** Get location_id*/
	public String getAc_location_id() {return ac_location_id;}
	/** Set location_id*/
	public void setAc_location_id(String ac_location_id) {this.ac_location_id = ac_location_id;}

	/** Get LO_name*/
	public String getAc_LO_name() {return ac_LO_name;}
	/** Set LO_name*/
	public void setAc_LO_name(String ac_LO_name) {this.ac_LO_name = ac_LO_name;}

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

	/** Get aaaa_mm*/
	public String getAc_aaaa_mm() {return ac_aaaa_mm;}
	/** Set aaaa_mm*/
	public void setAc_aaaa_mm(String ac_aaaa_mm) {this.ac_aaaa_mm = ac_aaaa_mm;}

	/** Get aaaa_mm_dd*/
	public String getAc_aaaa_mm_dd() {return ac_aaaa_mm_dd;}
	/** Set aaaa_mm_dd*/
	public void setAc_aaaa_mm_dd(String ac_aaaa_mm_dd) {this.ac_aaaa_mm_dd = ac_aaaa_mm_dd;}

	/** Get aaaa_mm_dd_hh*/
	public String getAc_aaaa_mm_dd_hh() {return ac_aaaa_mm_dd_hh;}
	/** Set aaaa_mm_dd_hh*/
	public void setAc_aaaa_mm_dd_hh(String ac_aaaa_mm_dd_hh) {this.ac_aaaa_mm_dd_hh = ac_aaaa_mm_dd_hh;}

	/** Get aaaa_mm_dd_hh_m0*/
	public String getAc_aaaa_mm_dd_hh_m0() {return ac_aaaa_mm_dd_hh_m0;}
	/** Set aaaa_mm_dd_hh_m0*/
	public void setAc_aaaa_mm_dd_hh_m0(String ac_aaaa_mm_dd_hh_m0) {this.ac_aaaa_mm_dd_hh_m0 = ac_aaaa_mm_dd_hh_m0;}

	/** Get aaaa_mm_dd_hh_mm*/
	public String getAc_aaaa_mm_dd_hh_mm() {return ac_aaaa_mm_dd_hh_mm;}
	/** Set aaaa_mm_dd_hh_mm*/
	public void setAc_aaaa_mm_dd_hh_mm(String ac_aaaa_mm_dd_hh_mm) {this.ac_aaaa_mm_dd_hh_mm = ac_aaaa_mm_dd_hh_mm;}

	/** Get aaaa_mm_dd_hh_mm_ss*/
	public String getAc_aaaa_mm_dd_hh_mm_ss() {return ac_aaaa_mm_dd_hh_mm_ss;}
	/** Set aaaa_mm_dd_hh_mm_ss*/
	public void setAc_aaaa_mm_dd_hh_mm_ss(String ac_aaaa_mm_dd_hh_mm_ss) {this.ac_aaaa_mm_dd_hh_mm_ss = ac_aaaa_mm_dd_hh_mm_ss;}


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
		out.append( _K.sepFld ); out.append( this.getAc_location_id()==null?"":this.getAc_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getAc_LO_name()==null?"":this.getAc_LO_name() ); // LO_name
		out.append( _K.sepFld ); out.append( this.getAc_computername()==null?"":this.getAc_computername() ); // computername
		out.append( _K.sepFld ); out.append( this.getAc_filename()==null?"":this.getAc_filename() ); // filename
		out.append( _K.sepFld ); out.append( this.getAc_content()==null?"":this.getAc_content() ); // content
		out.append( _K.sepFld ); out.append( this.getAc_json()==null?"":this.getAc_json() ); // json
		out.append( _K.sepFld ); out.append( this.getAc_aaaa_mm()==null?"":this.getAc_aaaa_mm() ); // aaaa_mm
		out.append( _K.sepFld ); out.append( this.getAc_aaaa_mm_dd()==null?"":this.getAc_aaaa_mm_dd() ); // aaaa_mm_dd
		out.append( _K.sepFld ); out.append( this.getAc_aaaa_mm_dd_hh()==null?"":this.getAc_aaaa_mm_dd_hh() ); // aaaa_mm_dd_hh
		out.append( _K.sepFld ); out.append( this.getAc_aaaa_mm_dd_hh_m0()==null?"":this.getAc_aaaa_mm_dd_hh_m0() ); // aaaa_mm_dd_hh_m0
		out.append( _K.sepFld ); out.append( this.getAc_aaaa_mm_dd_hh_mm()==null?"":this.getAc_aaaa_mm_dd_hh_mm() ); // aaaa_mm_dd_hh_mm
		out.append( _K.sepFld ); out.append( this.getAc_aaaa_mm_dd_hh_mm_ss()==null?"":this.getAc_aaaa_mm_dd_hh_mm_ss() ); // aaaa_mm_dd_hh_mm_ss

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
			try { this.setAc_location_id( trozos[5] ); } catch (Exception e) {;} // location_id
			try { this.setAc_LO_name( trozos[6] ); } catch (Exception e) {;} // LO_name
			try { this.setAc_computername( trozos[7] ); } catch (Exception e) {;} // computername
			try { this.setAc_filename( trozos[8] ); } catch (Exception e) {;} // filename
			try { this.setAc_content( trozos[9] ); } catch (Exception e) {;} // content
			try { this.setAc_json( trozos[10] ); } catch (Exception e) {;} // json
			try { this.setAc_aaaa_mm( trozos[11] ); } catch (Exception e) {;} // aaaa_mm
			try { this.setAc_aaaa_mm_dd( trozos[12] ); } catch (Exception e) {;} // aaaa_mm_dd
			try { this.setAc_aaaa_mm_dd_hh( trozos[13] ); } catch (Exception e) {;} // aaaa_mm_dd_hh
			try { this.setAc_aaaa_mm_dd_hh_m0( trozos[14] ); } catch (Exception e) {;} // aaaa_mm_dd_hh_m0
			try { this.setAc_aaaa_mm_dd_hh_mm( trozos[15] ); } catch (Exception e) {;} // aaaa_mm_dd_hh_mm
			try { this.setAc_aaaa_mm_dd_hh_mm_ss( trozos[16] ); } catch (Exception e) {;} // aaaa_mm_dd_hh_mm_ss
			
		}
	}
	////////////////////////////////////////////////////////////

}
