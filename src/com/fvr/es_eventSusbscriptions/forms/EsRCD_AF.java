package com.fvr.es_eventSusbscriptions.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.es_eventSusbscriptions.bean.EsBean;
import com.fvr.es_eventSusbscriptions.bean.EsBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class EsRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public EsBeanFiltro es_filtro;
    
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
	public String es_sincro; // sincro
	public String es_mark; // mark
	public String es_is_deleted; // is_deleted
	public String es_author; // author
	public String es_event_id; // event_id
	public String es_EV_location_id; // EV_location_id
	public String es_LO_name; // LO_name
	public String es_inscription_user_id; // inscription_user_id
	public String es_first_name; // first_name
	public String es_last_name; // last_name
	public String es_phone; // phone
	public double es_amount; // amount
	public String es_currency; // currency
	public String es_pay_status; // pay_status
	public String es_json; // json
    

    public EsRCD_AF() {
	super();
        if ( es_filtro == null ) { es_filtro = new EsBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new EsBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new EsBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        EsBean Destino = (EsBean)beanDestino;

	Destino.setEs_sincro( getEs_sincro() ); // sincro
	Destino.setEs_mark( getEs_mark() ); // mark
	Destino.setEs_is_deleted( getEs_is_deleted() ); // is_deleted
	Destino.setEs_author( getEs_author() ); // author
	Destino.setEs_event_id( getEs_event_id() ); // event_id
	Destino.setEs_EV_location_id( getEs_EV_location_id() ); // EV_location_id
	Destino.setEs_LO_name( getEs_LO_name() ); // LO_name
	Destino.setEs_inscription_user_id( getEs_inscription_user_id() ); // inscription_user_id
	Destino.setEs_first_name( getEs_first_name() ); // first_name
	Destino.setEs_last_name( getEs_last_name() ); // last_name
	Destino.setEs_phone( getEs_phone() ); // phone
	Destino.setEs_amount( getEs_amount() ); // amount
	Destino.setEs_currency( getEs_currency() ); // currency
	Destino.setEs_pay_status( getEs_pay_status() ); // pay_status
	Destino.setEs_json( getEs_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        EsBean Origen = (EsBean)beanOrigen;

	setEs_sincro( Origen.getEs_sincro() ); // sincro
	setEs_mark( Origen.getEs_mark() ); // mark
	setEs_is_deleted( Origen.getEs_is_deleted() ); // is_deleted
	setEs_author( Origen.getEs_author() ); // author
	setEs_event_id( Origen.getEs_event_id() ); // event_id
	setEs_EV_location_id( Origen.getEs_EV_location_id() ); // EV_location_id
	setEs_LO_name( Origen.getEs_LO_name() ); // LO_name
	setEs_inscription_user_id( Origen.getEs_inscription_user_id() ); // inscription_user_id
	setEs_first_name( Origen.getEs_first_name() ); // first_name
	setEs_last_name( Origen.getEs_last_name() ); // last_name
	setEs_phone( Origen.getEs_phone() ); // phone
	setEs_amount( Origen.getEs_amount() ); // amount
	setEs_currency( Origen.getEs_currency() ); // currency
	setEs_pay_status( Origen.getEs_pay_status() ); // pay_status
	setEs_json( Origen.getEs_json() ); // json
    }
    
    public void copyFrom(EsRCD_AF beanOrigen) {
        EsRCD_AF Origen = beanOrigen;

        setEs_filtro( Origen.getEs_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setEs_sincro( Origen.getEs_sincro() ); // sincro
	setEs_mark( Origen.getEs_mark() ); // mark
	setEs_is_deleted( Origen.getEs_is_deleted() ); // is_deleted
	setEs_author( Origen.getEs_author() ); // author
	setEs_event_id( Origen.getEs_event_id() ); // event_id
	setEs_EV_location_id( Origen.getEs_EV_location_id() ); // EV_location_id
	setEs_LO_name( Origen.getEs_LO_name() ); // LO_name
	setEs_inscription_user_id( Origen.getEs_inscription_user_id() ); // inscription_user_id
	setEs_first_name( Origen.getEs_first_name() ); // first_name
	setEs_last_name( Origen.getEs_last_name() ); // last_name
	setEs_phone( Origen.getEs_phone() ); // phone
	setEs_amount( Origen.getEs_amount() ); // amount
	setEs_currency( Origen.getEs_currency() ); // currency
	setEs_pay_status( Origen.getEs_pay_status() ); // pay_status
	setEs_json( Origen.getEs_json() ); // json
    }
    
    public EsBeanFiltro getEs_filtro() { return es_filtro; }
    
    public void setEs_filtro(EsBeanFiltro es_filtro) { this.es_filtro = es_filtro; }

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
	public String getEs_sincro() {return es_sincro;}
	/** Set sincro*/
	public void setEs_sincro(String es_sincro) {this.es_sincro = es_sincro;}

	/** Get mark*/
	public String getEs_mark() {return es_mark;}
	/** Set mark*/
	public void setEs_mark(String es_mark) {this.es_mark = es_mark;}

	/** Get is_deleted*/
	public String getEs_is_deleted() {return es_is_deleted;}
	/** Set is_deleted*/
	public void setEs_is_deleted(String es_is_deleted) {this.es_is_deleted = es_is_deleted;}

	/** Get author*/
	public String getEs_author() {return es_author;}
	/** Set author*/
	public void setEs_author(String es_author) {this.es_author = es_author;}

	/** Get event_id*/
	public String getEs_event_id() {return es_event_id;}
	/** Set event_id*/
	public void setEs_event_id(String es_event_id) {this.es_event_id = es_event_id;}

	/** Get EV_location_id*/
	public String getEs_EV_location_id() {return es_EV_location_id;}
	/** Set EV_location_id*/
	public void setEs_EV_location_id(String es_EV_location_id) {this.es_EV_location_id = es_EV_location_id;}

	/** Get LO_name*/
	public String getEs_LO_name() {return es_LO_name;}
	/** Set LO_name*/
	public void setEs_LO_name(String es_LO_name) {this.es_LO_name = es_LO_name;}

	/** Get inscription_user_id*/
	public String getEs_inscription_user_id() {return es_inscription_user_id;}
	/** Set inscription_user_id*/
	public void setEs_inscription_user_id(String es_inscription_user_id) {this.es_inscription_user_id = es_inscription_user_id;}

	/** Get first_name*/
	public String getEs_first_name() {return es_first_name;}
	/** Set first_name*/
	public void setEs_first_name(String es_first_name) {this.es_first_name = es_first_name;}

	/** Get last_name*/
	public String getEs_last_name() {return es_last_name;}
	/** Set last_name*/
	public void setEs_last_name(String es_last_name) {this.es_last_name = es_last_name;}

	/** Get phone*/
	public String getEs_phone() {return es_phone;}
	/** Set phone*/
	public void setEs_phone(String es_phone) {this.es_phone = es_phone;}

	/** Get amount*/
	public double getEs_amount() {return es_amount;}
	/** Set amount*/
	public void setEs_amount(double es_amount) {this.es_amount = es_amount;}

	/** Get currency*/
	public String getEs_currency() {return es_currency;}
	/** Set currency*/
	public void setEs_currency(String es_currency) {this.es_currency = es_currency;}

	/** Get pay_status*/
	public String getEs_pay_status() {return es_pay_status;}
	/** Set pay_status*/
	public void setEs_pay_status(String es_pay_status) {this.es_pay_status = es_pay_status;}

	/** Get json*/
	public String getEs_json() {return es_json;}
	/** Set json*/
	public void setEs_json(String es_json) {this.es_json = es_json;}

}
