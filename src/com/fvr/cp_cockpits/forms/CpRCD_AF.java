package com.fvr.cp_cockpits.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.cp_cockpits.bean.CpBean;
import com.fvr.cp_cockpits.bean.CpBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class CpRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public CpBeanFiltro cp_filtro;
    
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
	public String cp_sincro; // sincro
	public String cp_mark; // mark
	public String cp_is_deleted; // is_deleted
	public String cp_author; // author
	public String cp_location_id; // location_id
	public String cp_LO_name; // LO_name
	public String cp_LO_address; // LO_address
	public String cp_LO_city; // LO_city
	public String cp_LO_postal_code; // LO_postal_code
	public long   cp_LO_lat; // LO_lat
	public long   cp_LO_lon; // LO_lon
	public String cp_cockpit_id; // cockpit_id
	public String cp_serial_number; // serial_number
	public String cp_name; // name
	public String cp_isBlocked; // isBlocked
	public long   cp_asignation_order; // asignation_order
	public String cp_install_date; // install_date
	public String cp_reset_date_used; // reset_date_used
	public long   cp_hours_used; // hours_used
	public String cp_note; // note
	public String cp_comment; // comment
	public String cp_observation; // observation
	public String cp_warning; // warning
	public String cp_contact_service; // contact_service
	public String cp_json; // json
    

    public CpRCD_AF() {
	super();
        if ( cp_filtro == null ) { cp_filtro = new CpBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new CpBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new CpBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        CpBean Destino = (CpBean)beanDestino;

	Destino.setCp_sincro( getCp_sincro() ); // sincro
	Destino.setCp_mark( getCp_mark() ); // mark
	Destino.setCp_is_deleted( getCp_is_deleted() ); // is_deleted
	Destino.setCp_author( getCp_author() ); // author
	Destino.setCp_location_id( getCp_location_id() ); // location_id
	Destino.setCp_LO_name( getCp_LO_name() ); // LO_name
	Destino.setCp_LO_address( getCp_LO_address() ); // LO_address
	Destino.setCp_LO_city( getCp_LO_city() ); // LO_city
	Destino.setCp_LO_postal_code( getCp_LO_postal_code() ); // LO_postal_code
	Destino.setCp_LO_lat( getCp_LO_lat() ); // LO_lat
	Destino.setCp_LO_lon( getCp_LO_lon() ); // LO_lon
	Destino.setCp_cockpit_id( getCp_cockpit_id() ); // cockpit_id
	Destino.setCp_serial_number( getCp_serial_number() ); // serial_number
	Destino.setCp_name( getCp_name() ); // name
	Destino.setCp_isBlocked( getCp_isBlocked() ); // isBlocked
	Destino.setCp_asignation_order( getCp_asignation_order() ); // asignation_order
	Destino.setCp_install_date( getCp_install_date() ); // install_date
	Destino.setCp_reset_date_used( getCp_reset_date_used() ); // reset_date_used
	Destino.setCp_hours_used( getCp_hours_used() ); // hours_used
	Destino.setCp_note( getCp_note() ); // note
	Destino.setCp_comment( getCp_comment() ); // comment
	Destino.setCp_observation( getCp_observation() ); // observation
	Destino.setCp_warning( getCp_warning() ); // warning
	Destino.setCp_contact_service( getCp_contact_service() ); // contact_service
	Destino.setCp_json( getCp_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        CpBean Origen = (CpBean)beanOrigen;

	setCp_sincro( Origen.getCp_sincro() ); // sincro
	setCp_mark( Origen.getCp_mark() ); // mark
	setCp_is_deleted( Origen.getCp_is_deleted() ); // is_deleted
	setCp_author( Origen.getCp_author() ); // author
	setCp_location_id( Origen.getCp_location_id() ); // location_id
	setCp_LO_name( Origen.getCp_LO_name() ); // LO_name
	setCp_LO_address( Origen.getCp_LO_address() ); // LO_address
	setCp_LO_city( Origen.getCp_LO_city() ); // LO_city
	setCp_LO_postal_code( Origen.getCp_LO_postal_code() ); // LO_postal_code
	setCp_LO_lat( Origen.getCp_LO_lat() ); // LO_lat
	setCp_LO_lon( Origen.getCp_LO_lon() ); // LO_lon
	setCp_cockpit_id( Origen.getCp_cockpit_id() ); // cockpit_id
	setCp_serial_number( Origen.getCp_serial_number() ); // serial_number
	setCp_name( Origen.getCp_name() ); // name
	setCp_isBlocked( Origen.getCp_isBlocked() ); // isBlocked
	setCp_asignation_order( Origen.getCp_asignation_order() ); // asignation_order
	setCp_install_date( Origen.getCp_install_date() ); // install_date
	setCp_reset_date_used( Origen.getCp_reset_date_used() ); // reset_date_used
	setCp_hours_used( Origen.getCp_hours_used() ); // hours_used
	setCp_note( Origen.getCp_note() ); // note
	setCp_comment( Origen.getCp_comment() ); // comment
	setCp_observation( Origen.getCp_observation() ); // observation
	setCp_warning( Origen.getCp_warning() ); // warning
	setCp_contact_service( Origen.getCp_contact_service() ); // contact_service
	setCp_json( Origen.getCp_json() ); // json
    }
    
    public void copyFrom(CpRCD_AF beanOrigen) {
        CpRCD_AF Origen = beanOrigen;

        setCp_filtro( Origen.getCp_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setCp_sincro( Origen.getCp_sincro() ); // sincro
	setCp_mark( Origen.getCp_mark() ); // mark
	setCp_is_deleted( Origen.getCp_is_deleted() ); // is_deleted
	setCp_author( Origen.getCp_author() ); // author
	setCp_location_id( Origen.getCp_location_id() ); // location_id
	setCp_LO_name( Origen.getCp_LO_name() ); // LO_name
	setCp_LO_address( Origen.getCp_LO_address() ); // LO_address
	setCp_LO_city( Origen.getCp_LO_city() ); // LO_city
	setCp_LO_postal_code( Origen.getCp_LO_postal_code() ); // LO_postal_code
	setCp_LO_lat( Origen.getCp_LO_lat() ); // LO_lat
	setCp_LO_lon( Origen.getCp_LO_lon() ); // LO_lon
	setCp_cockpit_id( Origen.getCp_cockpit_id() ); // cockpit_id
	setCp_serial_number( Origen.getCp_serial_number() ); // serial_number
	setCp_name( Origen.getCp_name() ); // name
	setCp_isBlocked( Origen.getCp_isBlocked() ); // isBlocked
	setCp_asignation_order( Origen.getCp_asignation_order() ); // asignation_order
	setCp_install_date( Origen.getCp_install_date() ); // install_date
	setCp_reset_date_used( Origen.getCp_reset_date_used() ); // reset_date_used
	setCp_hours_used( Origen.getCp_hours_used() ); // hours_used
	setCp_note( Origen.getCp_note() ); // note
	setCp_comment( Origen.getCp_comment() ); // comment
	setCp_observation( Origen.getCp_observation() ); // observation
	setCp_warning( Origen.getCp_warning() ); // warning
	setCp_contact_service( Origen.getCp_contact_service() ); // contact_service
	setCp_json( Origen.getCp_json() ); // json
    }
    
    public CpBeanFiltro getCp_filtro() { return cp_filtro; }
    
    public void setCp_filtro(CpBeanFiltro cp_filtro) { this.cp_filtro = cp_filtro; }

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
	public String getCp_sincro() {return cp_sincro;}
	/** Set sincro*/
	public void setCp_sincro(String cp_sincro) {this.cp_sincro = cp_sincro;}

	/** Get mark*/
	public String getCp_mark() {return cp_mark;}
	/** Set mark*/
	public void setCp_mark(String cp_mark) {this.cp_mark = cp_mark;}

	/** Get is_deleted*/
	public String getCp_is_deleted() {return cp_is_deleted;}
	/** Set is_deleted*/
	public void setCp_is_deleted(String cp_is_deleted) {this.cp_is_deleted = cp_is_deleted;}

	/** Get author*/
	public String getCp_author() {return cp_author;}
	/** Set author*/
	public void setCp_author(String cp_author) {this.cp_author = cp_author;}

	/** Get location_id*/
	public String getCp_location_id() {return cp_location_id;}
	/** Set location_id*/
	public void setCp_location_id(String cp_location_id) {this.cp_location_id = cp_location_id;}

	/** Get LO_name*/
	public String getCp_LO_name() {return cp_LO_name;}
	/** Set LO_name*/
	public void setCp_LO_name(String cp_LO_name) {this.cp_LO_name = cp_LO_name;}

	/** Get LO_address*/
	public String getCp_LO_address() {return cp_LO_address;}
	/** Set LO_address*/
	public void setCp_LO_address(String cp_LO_address) {this.cp_LO_address = cp_LO_address;}

	/** Get LO_city*/
	public String getCp_LO_city() {return cp_LO_city;}
	/** Set LO_city*/
	public void setCp_LO_city(String cp_LO_city) {this.cp_LO_city = cp_LO_city;}

	/** Get LO_postal_code*/
	public String getCp_LO_postal_code() {return cp_LO_postal_code;}
	/** Set LO_postal_code*/
	public void setCp_LO_postal_code(String cp_LO_postal_code) {this.cp_LO_postal_code = cp_LO_postal_code;}

	/** Get LO_lat*/
	public long getCp_LO_lat() {return cp_LO_lat;}
	/** Set LO_lat*/
	public void setCp_LO_lat(long cp_LO_lat) {this.cp_LO_lat = cp_LO_lat;}

	/** Get LO_lon*/
	public long getCp_LO_lon() {return cp_LO_lon;}
	/** Set LO_lon*/
	public void setCp_LO_lon(long cp_LO_lon) {this.cp_LO_lon = cp_LO_lon;}

	/** Get cockpit_id*/
	public String getCp_cockpit_id() {return cp_cockpit_id;}
	/** Set cockpit_id*/
	public void setCp_cockpit_id(String cp_cockpit_id) {this.cp_cockpit_id = cp_cockpit_id;}

	/** Get serial_number*/
	public String getCp_serial_number() {return cp_serial_number;}
	/** Set serial_number*/
	public void setCp_serial_number(String cp_serial_number) {this.cp_serial_number = cp_serial_number;}

	/** Get name*/
	public String getCp_name() {return cp_name;}
	/** Set name*/
	public void setCp_name(String cp_name) {this.cp_name = cp_name;}

	/** Get isBlocked*/
	public String getCp_isBlocked() {return cp_isBlocked;}
	/** Set isBlocked*/
	public void setCp_isBlocked(String cp_isBlocked) {this.cp_isBlocked = cp_isBlocked;}

	/** Get asignation_order*/
	public long getCp_asignation_order() {return cp_asignation_order;}
	/** Set asignation_order*/
	public void setCp_asignation_order(long cp_asignation_order) {this.cp_asignation_order = cp_asignation_order;}

	/** Get install_date*/
	public String getCp_install_date() {return cp_install_date;}
	/** Set install_date*/
	public void setCp_install_date(String cp_install_date) {this.cp_install_date = cp_install_date;}

	/** Get reset_date_used*/
	public String getCp_reset_date_used() {return cp_reset_date_used;}
	/** Set reset_date_used*/
	public void setCp_reset_date_used(String cp_reset_date_used) {this.cp_reset_date_used = cp_reset_date_used;}

	/** Get hours_used*/
	public long getCp_hours_used() {return cp_hours_used;}
	/** Set hours_used*/
	public void setCp_hours_used(long cp_hours_used) {this.cp_hours_used = cp_hours_used;}

	/** Get note*/
	public String getCp_note() {return cp_note;}
	/** Set note*/
	public void setCp_note(String cp_note) {this.cp_note = cp_note;}

	/** Get comment*/
	public String getCp_comment() {return cp_comment;}
	/** Set comment*/
	public void setCp_comment(String cp_comment) {this.cp_comment = cp_comment;}

	/** Get observation*/
	public String getCp_observation() {return cp_observation;}
	/** Set observation*/
	public void setCp_observation(String cp_observation) {this.cp_observation = cp_observation;}

	/** Get warning*/
	public String getCp_warning() {return cp_warning;}
	/** Set warning*/
	public void setCp_warning(String cp_warning) {this.cp_warning = cp_warning;}

	/** Get contact_service*/
	public String getCp_contact_service() {return cp_contact_service;}
	/** Set contact_service*/
	public void setCp_contact_service(String cp_contact_service) {this.cp_contact_service = cp_contact_service;}

	/** Get json*/
	public String getCp_json() {return cp_json;}
	/** Set json*/
	public void setCp_json(String cp_json) {this.cp_json = cp_json;}

}
