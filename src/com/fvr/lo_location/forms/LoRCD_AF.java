package com.fvr.lo_location.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.lo_location.bean.LoBean;
import com.fvr.lo_location.bean.LoBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class LoRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public LoBeanFiltro lo_filtro;
    
    // Para SELRCD:
    public String retFormulario;
    public String retElemento;
    public String valorInicial;
    
    // Para multiregistro:
    public StBean[] grid;
    public int    filasGrid;
    public int    filaInicioGrid;
    public int    filasTotales;
    public String[] clavesMarcadas;
    // Para todas (mono y multi):
    public String opcionPantalla;
    public String opcionJSMenu;
    
    public String logon_USR;
    public String logon_HSH;
    
    // Datos calculados, no de BD:
    public ContextVars contextVars = new ContextVars();
    
    // Formato de registro:
	public String lo_sincro; // sincro
	public String lo_mark; // mark
	public String lo_is_deleted; // is_deleted
	public String lo_author; // author
	public String lo_location_id; // location_id
	public String lo_name; // name
	public String lo_address; // address
	public String lo_city; // city
	public String lo_postal_code; // postal_code
	public String lo_province; // province
	public String lo_state_region; // state_region
	public String lo_country; // country
	public long   lo_lat; // lat
	public long   lo_lon; // lon
	public String lo_comment; // comment
	public String lo_json; // json
    

    public LoRCD_AF() {
	super();
        if ( lo_filtro == null ) { lo_filtro = new LoBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new LoBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new LoBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        LoBean Destino = (LoBean)beanDestino;

	Destino.setLo_sincro( getLo_sincro() ); // sincro
	Destino.setLo_mark( getLo_mark() ); // mark
	Destino.setLo_is_deleted( getLo_is_deleted() ); // is_deleted
	Destino.setLo_author( getLo_author() ); // author
	Destino.setLo_location_id( getLo_location_id() ); // location_id
	Destino.setLo_name( getLo_name() ); // name
	Destino.setLo_address( getLo_address() ); // address
	Destino.setLo_city( getLo_city() ); // city
	Destino.setLo_postal_code( getLo_postal_code() ); // postal_code
	Destino.setLo_province( getLo_province() ); // province
	Destino.setLo_state_region( getLo_state_region() ); // state_region
	Destino.setLo_country( getLo_country() ); // country
	Destino.setLo_lat( getLo_lat() ); // lat
	Destino.setLo_lon( getLo_lon() ); // lon
	Destino.setLo_comment( getLo_comment() ); // comment
	Destino.setLo_json( getLo_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        LoBean Origen = (LoBean)beanOrigen;

	setLo_sincro( Origen.getLo_sincro() ); // sincro
	setLo_mark( Origen.getLo_mark() ); // mark
	setLo_is_deleted( Origen.getLo_is_deleted() ); // is_deleted
	setLo_author( Origen.getLo_author() ); // author
	setLo_location_id( Origen.getLo_location_id() ); // location_id
	setLo_name( Origen.getLo_name() ); // name
	setLo_address( Origen.getLo_address() ); // address
	setLo_city( Origen.getLo_city() ); // city
	setLo_postal_code( Origen.getLo_postal_code() ); // postal_code
	setLo_province( Origen.getLo_province() ); // province
	setLo_state_region( Origen.getLo_state_region() ); // state_region
	setLo_country( Origen.getLo_country() ); // country
	setLo_lat( Origen.getLo_lat() ); // lat
	setLo_lon( Origen.getLo_lon() ); // lon
	setLo_comment( Origen.getLo_comment() ); // comment
	setLo_json( Origen.getLo_json() ); // json
    }
    
    public void copyFrom(LoRCD_AF beanOrigen) {
        LoRCD_AF Origen = beanOrigen;

        setLo_filtro( Origen.getLo_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setLo_sincro( Origen.getLo_sincro() ); // sincro
	setLo_mark( Origen.getLo_mark() ); // mark
	setLo_is_deleted( Origen.getLo_is_deleted() ); // is_deleted
	setLo_author( Origen.getLo_author() ); // author
	setLo_location_id( Origen.getLo_location_id() ); // location_id
	setLo_name( Origen.getLo_name() ); // name
	setLo_address( Origen.getLo_address() ); // address
	setLo_city( Origen.getLo_city() ); // city
	setLo_postal_code( Origen.getLo_postal_code() ); // postal_code
	setLo_province( Origen.getLo_province() ); // province
	setLo_state_region( Origen.getLo_state_region() ); // state_region
	setLo_country( Origen.getLo_country() ); // country
	setLo_lat( Origen.getLo_lat() ); // lat
	setLo_lon( Origen.getLo_lon() ); // lon
	setLo_comment( Origen.getLo_comment() ); // comment
	setLo_json( Origen.getLo_json() ); // json
    }
    
    public LoBeanFiltro getLo_filtro() { return lo_filtro; }
    
    public void setLo_filtro(LoBeanFiltro lo_filtro) { this.lo_filtro = lo_filtro; }

    public StBean[] getGrid() { return grid; }

    public void setGrid(StBean[] grid) { this.grid = grid; }

    public int getFilasGrid() { return filasGrid; }

    public void setFilasGrid(int filasGrid) { this.filasGrid = filasGrid; }

    public int getFilaInicioGrid() { return filaInicioGrid; }

    public void setFilaInicioGrid(int filaInicioGrid) { this.filaInicioGrid = filaInicioGrid; }

    public int getFilasTotales() { return filasTotales; }

	public void setFilasTotales(int filasTotales) { this.filasTotales = filasTotales; }

    public String[] getClavesMarcadas() { return clavesMarcadas; }

    public void setClavesMarcadas(String[] clavesMarcadas) { this.clavesMarcadas = clavesMarcadas; }

    public String getOpcionPantalla() { return opcionPantalla; }

    public void setOpcionPantalla(String opcionPantalla) { this.opcionPantalla = opcionPantalla; }

    public String getOpcionJSMenu() { return opcionJSMenu; }

    public void setOpcionJSMenu(String opcionJSMenu) { this.opcionJSMenu = opcionJSMenu; }

    public String getLogon_USR() { return logon_USR; }

    public void setLogon_USR(String logon_USR) { this.logon_USR = logon_USR; }

    public String getLogon_HSH() { return logon_HSH; }

	public void setLogon_HSH(String logon_HSH) { this.logon_HSH = logon_HSH; }

    public String getRetFormulario() { return retFormulario; }

    public void setRetFormulario(String retFormulario) { this.retFormulario = retFormulario; }

    public String getRetElemento() { return retElemento; }

    public void setRetElemento(String retElemento) { this.retElemento = retElemento; }

    public String getValorInicial() { return valorInicial; }

    public void setValorInicial(String valorInicial) { this.valorInicial = valorInicial; }

	public ContextVars getContextVars() { return contextVars; }

	public void setContextVars(ContextVars contextVars) { this.contextVars = contextVars; }



	/** Get sincro*/
	public String getLo_sincro() {return lo_sincro;}
	/** Set sincro*/
	public void setLo_sincro(String lo_sincro) {this.lo_sincro = lo_sincro;}

	/** Get mark*/
	public String getLo_mark() {return lo_mark;}
	/** Set mark*/
	public void setLo_mark(String lo_mark) {this.lo_mark = lo_mark;}

	/** Get is_deleted*/
	public String getLo_is_deleted() {return lo_is_deleted;}
	/** Set is_deleted*/
	public void setLo_is_deleted(String lo_is_deleted) {this.lo_is_deleted = lo_is_deleted;}

	/** Get author*/
	public String getLo_author() {return lo_author;}
	/** Set author*/
	public void setLo_author(String lo_author) {this.lo_author = lo_author;}

	/** Get location_id*/
	public String getLo_location_id() {return lo_location_id;}
	/** Set location_id*/
	public void setLo_location_id(String lo_location_id) {this.lo_location_id = lo_location_id;}

	/** Get name*/
	public String getLo_name() {return lo_name;}
	/** Set name*/
	public void setLo_name(String lo_name) {this.lo_name = lo_name;}

	/** Get address*/
	public String getLo_address() {return lo_address;}
	/** Set address*/
	public void setLo_address(String lo_address) {this.lo_address = lo_address;}

	/** Get city*/
	public String getLo_city() {return lo_city;}
	/** Set city*/
	public void setLo_city(String lo_city) {this.lo_city = lo_city;}

	/** Get postal_code*/
	public String getLo_postal_code() {return lo_postal_code;}
	/** Set postal_code*/
	public void setLo_postal_code(String lo_postal_code) {this.lo_postal_code = lo_postal_code;}

	/** Get province*/
	public String getLo_province() {return lo_province;}
	/** Set province*/
	public void setLo_province(String lo_province) {this.lo_province = lo_province;}

	/** Get state_region*/
	public String getLo_state_region() {return lo_state_region;}
	/** Set state_region*/
	public void setLo_state_region(String lo_state_region) {this.lo_state_region = lo_state_region;}

	/** Get country*/
	public String getLo_country() {return lo_country;}
	/** Set country*/
	public void setLo_country(String lo_country) {this.lo_country = lo_country;}

	/** Get lat*/
	public long getLo_lat() {return lo_lat;}
	/** Set lat*/
	public void setLo_lat(long lo_lat) {this.lo_lat = lo_lat;}

	/** Get lon*/
	public long getLo_lon() {return lo_lon;}
	/** Set lon*/
	public void setLo_lon(long lo_lon) {this.lo_lon = lo_lon;}

	/** Get comment*/
	public String getLo_comment() {return lo_comment;}
	/** Set comment*/
	public void setLo_comment(String lo_comment) {this.lo_comment = lo_comment;}

	/** Get json*/
	public String getLo_json() {return lo_json;}
	/** Set json*/
	public void setLo_json(String lo_json) {this.lo_json = lo_json;}

}
