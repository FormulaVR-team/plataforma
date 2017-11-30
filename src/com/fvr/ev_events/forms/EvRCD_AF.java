package com.fvr.ev_events.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.ev_events.bean.EvBean;
import com.fvr.ev_events.bean.EvBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class EvRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public EvBeanFiltro ev_filtro;
    
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
	public String ev_sincro; // sincro
	public String ev_mark; // mark
	public String ev_is_deleted; // is_deleted
	public String ev_author; // author
	public String ev_event_id; // event_id
	public String ev_location_id; // location_id
	public String ev_LO_name; // LO_name
	public String ev_name; // name
	public long   ev_max_inscriptions; // max_inscriptions
	public double ev_amount; // amount
	public String ev_currency; // currency
	public String ev_deadline; // deadline
	public String ev_comment; // comment
	public String ev_date1; // date1
	public String ev_date2; // date2
	public String ev_date3; // date3
	public String ev_date4; // date4
	public String ev_json; // json
    

    public EvRCD_AF() {
	super();
        if ( ev_filtro == null ) { ev_filtro = new EvBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new EvBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new EvBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        EvBean Destino = (EvBean)beanDestino;

	Destino.setEv_sincro( getEv_sincro() ); // sincro
	Destino.setEv_mark( getEv_mark() ); // mark
	Destino.setEv_is_deleted( getEv_is_deleted() ); // is_deleted
	Destino.setEv_author( getEv_author() ); // author
	Destino.setEv_event_id( getEv_event_id() ); // event_id
	Destino.setEv_location_id( getEv_location_id() ); // location_id
	Destino.setEv_LO_name( getEv_LO_name() ); // LO_name
	Destino.setEv_name( getEv_name() ); // name
	Destino.setEv_max_inscriptions( getEv_max_inscriptions() ); // max_inscriptions
	Destino.setEv_amount( getEv_amount() ); // amount
	Destino.setEv_currency( getEv_currency() ); // currency
	Destino.setEv_deadline( getEv_deadline() ); // deadline
	Destino.setEv_comment( getEv_comment() ); // comment
	Destino.setEv_date1( getEv_date1() ); // date1
	Destino.setEv_date2( getEv_date2() ); // date2
	Destino.setEv_date3( getEv_date3() ); // date3
	Destino.setEv_date4( getEv_date4() ); // date4
	Destino.setEv_json( getEv_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        EvBean Origen = (EvBean)beanOrigen;

	setEv_sincro( Origen.getEv_sincro() ); // sincro
	setEv_mark( Origen.getEv_mark() ); // mark
	setEv_is_deleted( Origen.getEv_is_deleted() ); // is_deleted
	setEv_author( Origen.getEv_author() ); // author
	setEv_event_id( Origen.getEv_event_id() ); // event_id
	setEv_location_id( Origen.getEv_location_id() ); // location_id
	setEv_LO_name( Origen.getEv_LO_name() ); // LO_name
	setEv_name( Origen.getEv_name() ); // name
	setEv_max_inscriptions( Origen.getEv_max_inscriptions() ); // max_inscriptions
	setEv_amount( Origen.getEv_amount() ); // amount
	setEv_currency( Origen.getEv_currency() ); // currency
	setEv_deadline( Origen.getEv_deadline() ); // deadline
	setEv_comment( Origen.getEv_comment() ); // comment
	setEv_date1( Origen.getEv_date1() ); // date1
	setEv_date2( Origen.getEv_date2() ); // date2
	setEv_date3( Origen.getEv_date3() ); // date3
	setEv_date4( Origen.getEv_date4() ); // date4
	setEv_json( Origen.getEv_json() ); // json
    }
    
    public void copyFrom(EvRCD_AF beanOrigen) {
        EvRCD_AF Origen = beanOrigen;

        setEv_filtro( Origen.getEv_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setEv_sincro( Origen.getEv_sincro() ); // sincro
	setEv_mark( Origen.getEv_mark() ); // mark
	setEv_is_deleted( Origen.getEv_is_deleted() ); // is_deleted
	setEv_author( Origen.getEv_author() ); // author
	setEv_event_id( Origen.getEv_event_id() ); // event_id
	setEv_location_id( Origen.getEv_location_id() ); // location_id
	setEv_LO_name( Origen.getEv_LO_name() ); // LO_name
	setEv_name( Origen.getEv_name() ); // name
	setEv_max_inscriptions( Origen.getEv_max_inscriptions() ); // max_inscriptions
	setEv_amount( Origen.getEv_amount() ); // amount
	setEv_currency( Origen.getEv_currency() ); // currency
	setEv_deadline( Origen.getEv_deadline() ); // deadline
	setEv_comment( Origen.getEv_comment() ); // comment
	setEv_date1( Origen.getEv_date1() ); // date1
	setEv_date2( Origen.getEv_date2() ); // date2
	setEv_date3( Origen.getEv_date3() ); // date3
	setEv_date4( Origen.getEv_date4() ); // date4
	setEv_json( Origen.getEv_json() ); // json
    }
    
    public EvBeanFiltro getEv_filtro() { return ev_filtro; }
    
    public void setEv_filtro(EvBeanFiltro ev_filtro) { this.ev_filtro = ev_filtro; }

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
	public String getEv_sincro() {return ev_sincro;}
	/** Set sincro*/
	public void setEv_sincro(String ev_sincro) {this.ev_sincro = ev_sincro;}

	/** Get mark*/
	public String getEv_mark() {return ev_mark;}
	/** Set mark*/
	public void setEv_mark(String ev_mark) {this.ev_mark = ev_mark;}

	/** Get is_deleted*/
	public String getEv_is_deleted() {return ev_is_deleted;}
	/** Set is_deleted*/
	public void setEv_is_deleted(String ev_is_deleted) {this.ev_is_deleted = ev_is_deleted;}

	/** Get author*/
	public String getEv_author() {return ev_author;}
	/** Set author*/
	public void setEv_author(String ev_author) {this.ev_author = ev_author;}

	/** Get event_id*/
	public String getEv_event_id() {return ev_event_id;}
	/** Set event_id*/
	public void setEv_event_id(String ev_event_id) {this.ev_event_id = ev_event_id;}

	/** Get location_id*/
	public String getEv_location_id() {return ev_location_id;}
	/** Set location_id*/
	public void setEv_location_id(String ev_location_id) {this.ev_location_id = ev_location_id;}

	/** Get LO_name*/
	public String getEv_LO_name() {return ev_LO_name;}
	/** Set LO_name*/
	public void setEv_LO_name(String ev_LO_name) {this.ev_LO_name = ev_LO_name;}

	/** Get name*/
	public String getEv_name() {return ev_name;}
	/** Set name*/
	public void setEv_name(String ev_name) {this.ev_name = ev_name;}

	/** Get max_inscriptions*/
	public long getEv_max_inscriptions() {return ev_max_inscriptions;}
	/** Set max_inscriptions*/
	public void setEv_max_inscriptions(long ev_max_inscriptions) {this.ev_max_inscriptions = ev_max_inscriptions;}

	/** Get amount*/
	public double getEv_amount() {return ev_amount;}
	/** Set amount*/
	public void setEv_amount(double ev_amount) {this.ev_amount = ev_amount;}

	/** Get currency*/
	public String getEv_currency() {return ev_currency;}
	/** Set currency*/
	public void setEv_currency(String ev_currency) {this.ev_currency = ev_currency;}

	/** Get deadline*/
	public String getEv_deadline() {return ev_deadline;}
	/** Set deadline*/
	public void setEv_deadline(String ev_deadline) {this.ev_deadline = ev_deadline;}

	/** Get comment*/
	public String getEv_comment() {return ev_comment;}
	/** Set comment*/
	public void setEv_comment(String ev_comment) {this.ev_comment = ev_comment;}

	/** Get date1*/
	public String getEv_date1() {return ev_date1;}
	/** Set date1*/
	public void setEv_date1(String ev_date1) {this.ev_date1 = ev_date1;}

	/** Get date2*/
	public String getEv_date2() {return ev_date2;}
	/** Set date2*/
	public void setEv_date2(String ev_date2) {this.ev_date2 = ev_date2;}

	/** Get date3*/
	public String getEv_date3() {return ev_date3;}
	/** Set date3*/
	public void setEv_date3(String ev_date3) {this.ev_date3 = ev_date3;}

	/** Get date4*/
	public String getEv_date4() {return ev_date4;}
	/** Set date4*/
	public void setEv_date4(String ev_date4) {this.ev_date4 = ev_date4;}

	/** Get json*/
	public String getEv_json() {return ev_json;}
	/** Set json*/
	public void setEv_json(String ev_json) {this.ev_json = ev_json;}

}
