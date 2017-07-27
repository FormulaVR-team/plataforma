package com.fvr.sg_publicProcesses.bean;

import com.fvr._comun.StBean;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class SgBean extends StBean {

	public String sg_sincro; // sincro
	public String sg_mark; // mark
	public String sg_is_deleted; // is_deleted
	public String sg_author; // author
	public String sg_role_id; // role_id
	public String sg_process_id; // process_id
	public String sg_json; // json
    
    public SgBean() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public SgBean(Object nulo) { super(); }

    public void inicializar() {
	this.setSg_sincro( "" ); // sincro
	this.setSg_mark( "" ); // mark
	this.setSg_is_deleted( "" ); // is_deleted
	this.setSg_author( "" ); // author
	this.setSg_role_id( "" ); // role_id
	this.setSg_process_id( "" ); // process_id
	this.setSg_json( "" ); // json
    } 
 /*
    public void copyTo(StBean beanDestino) {
        SgBean Destino = (SgBean)beanDestino;

	Destino.setSg_sincro( getSg_sincro() ); // sincro
	Destino.setSg_mark( getSg_mark() ); // mark
	Destino.setSg_is_deleted( getSg_is_deleted() ); // is_deleted
	Destino.setSg_author( getSg_author() ); // author
	Destino.setSg_role_id( getSg_role_id() ); // role_id
	Destino.setSg_process_id( getSg_process_id() ); // process_id
	Destino.setSg_json( getSg_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        SgBean Origen = (SgBean)beanOrigen;

	setSg_sincro( Origen.getSg_sincro() ); // sincro
	setSg_mark( Origen.getSg_mark() ); // mark
	setSg_is_deleted( Origen.getSg_is_deleted() ); // is_deleted
	setSg_author( Origen.getSg_author() ); // author
	setSg_role_id( Origen.getSg_role_id() ); // role_id
	setSg_process_id( Origen.getSg_process_id() ); // process_id
	setSg_json( Origen.getSg_json() ); // json
    }
*/


	/** Get sincro*/
	public String getSg_sincro() {return sg_sincro;}
	/** Set sincro*/
	public void setSg_sincro(String sg_sincro) {this.sg_sincro = sg_sincro;}

	/** Get mark*/
	public String getSg_mark() {return sg_mark;}
	/** Set mark*/
	public void setSg_mark(String sg_mark) {this.sg_mark = sg_mark;}

	/** Get is_deleted*/
	public String getSg_is_deleted() {return sg_is_deleted;}
	/** Set is_deleted*/
	public void setSg_is_deleted(String sg_is_deleted) {this.sg_is_deleted = sg_is_deleted;}

	/** Get author*/
	public String getSg_author() {return sg_author;}
	/** Set author*/
	public void setSg_author(String sg_author) {this.sg_author = sg_author;}

	/** Get role_id*/
	public String getSg_role_id() {return sg_role_id;}
	/** Set role_id*/
	public void setSg_role_id(String sg_role_id) {this.sg_role_id = sg_role_id;}

	/** Get process_id*/
	public String getSg_process_id() {return sg_process_id;}
	/** Set process_id*/
	public void setSg_process_id(String sg_process_id) {this.sg_process_id = sg_process_id;}

	/** Get json*/
	public String getSg_json() {return sg_json;}
	/** Set json*/
	public void setSg_json(String sg_json) {this.sg_json = sg_json;}


	////////////////////////////////////////////////////////////
    public String getKey(){
		 return sg_role_id + "^" + 
		sg_process_id;}

    public void setKey(String key){
            String k="";
	k = key.trim().substring( 0, key.indexOf("^") ); this.setSg_role_id( k ); key = key.trim().substring( key.indexOf("^")+1 );
	k = key; this.setSg_process_id( k );
    }
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getSg_sincro()==null?"":this.getSg_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getSg_mark()==null?"":this.getSg_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getSg_is_deleted()==null?"":this.getSg_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getSg_author()==null?"":this.getSg_author() ); // author
		out.append( _K.sepFld ); out.append( this.getSg_role_id()==null?"":this.getSg_role_id() ); // role_id
		out.append( _K.sepFld ); out.append( this.getSg_process_id()==null?"":this.getSg_process_id() ); // process_id
		out.append( _K.sepFld ); out.append( this.getSg_json()==null?"":this.getSg_json() ); // json

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
			
			try { this.setSg_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setSg_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setSg_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setSg_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setSg_role_id( trozos[4] ); } catch (Exception e) {;} // role_id
			try { this.setSg_process_id( trozos[5] ); } catch (Exception e) {;} // process_id
			try { this.setSg_json( trozos[6] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

}
