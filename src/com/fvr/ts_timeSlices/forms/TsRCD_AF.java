package com.fvr.ts_timeSlices.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.ts_timeSlices.bean.TsBean;
import com.fvr.ts_timeSlices.bean.TsBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class TsRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public TsBeanFiltro ts_filtro;
    
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
	public String ts_sincro; // sincro
	public String ts_mark; // mark
	public String ts_is_deleted; // is_deleted
	public String ts_author; // author
	public String ts_reservation_id; // reservation_id
	public String ts_RS_user_id; // RS_user_id
	public String ts_RS_location_id; // RS_location_id
	public String ts_RS_start_date; // RS_start_date
	public String ts_RS_start_time; // RS_start_time
	public String ts_RS_pay_status; // RS_pay_status
	public String ts_RS_product_id; // RS_product_id
	public long   ts_RS_quantity; // RS_quantity
	public long   ts_RS_duration_minutes; // RS_duration_minutes
	public long   ts_RS_places; // RS_places
	public String ts_start_date; // start_date
	public String ts_start_time; // start_time
	public String ts_json; // json
    

    public TsRCD_AF() {
	super();
        if ( ts_filtro == null ) { ts_filtro = new TsBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new TsBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new TsBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        TsBean Destino = (TsBean)beanDestino;

	Destino.setTs_sincro( getTs_sincro() ); // sincro
	Destino.setTs_mark( getTs_mark() ); // mark
	Destino.setTs_is_deleted( getTs_is_deleted() ); // is_deleted
	Destino.setTs_author( getTs_author() ); // author
	Destino.setTs_reservation_id( getTs_reservation_id() ); // reservation_id
	Destino.setTs_RS_user_id( getTs_RS_user_id() ); // RS_user_id
	Destino.setTs_RS_location_id( getTs_RS_location_id() ); // RS_location_id
	Destino.setTs_RS_start_date( getTs_RS_start_date() ); // RS_start_date
	Destino.setTs_RS_start_time( getTs_RS_start_time() ); // RS_start_time
	Destino.setTs_RS_pay_status( getTs_RS_pay_status() ); // RS_pay_status
	Destino.setTs_RS_product_id( getTs_RS_product_id() ); // RS_product_id
	Destino.setTs_RS_quantity( getTs_RS_quantity() ); // RS_quantity
	Destino.setTs_RS_duration_minutes( getTs_RS_duration_minutes() ); // RS_duration_minutes
	Destino.setTs_RS_places( getTs_RS_places() ); // RS_places
	Destino.setTs_start_date( getTs_start_date() ); // start_date
	Destino.setTs_start_time( getTs_start_time() ); // start_time
	Destino.setTs_json( getTs_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        TsBean Origen = (TsBean)beanOrigen;

	setTs_sincro( Origen.getTs_sincro() ); // sincro
	setTs_mark( Origen.getTs_mark() ); // mark
	setTs_is_deleted( Origen.getTs_is_deleted() ); // is_deleted
	setTs_author( Origen.getTs_author() ); // author
	setTs_reservation_id( Origen.getTs_reservation_id() ); // reservation_id
	setTs_RS_user_id( Origen.getTs_RS_user_id() ); // RS_user_id
	setTs_RS_location_id( Origen.getTs_RS_location_id() ); // RS_location_id
	setTs_RS_start_date( Origen.getTs_RS_start_date() ); // RS_start_date
	setTs_RS_start_time( Origen.getTs_RS_start_time() ); // RS_start_time
	setTs_RS_pay_status( Origen.getTs_RS_pay_status() ); // RS_pay_status
	setTs_RS_product_id( Origen.getTs_RS_product_id() ); // RS_product_id
	setTs_RS_quantity( Origen.getTs_RS_quantity() ); // RS_quantity
	setTs_RS_duration_minutes( Origen.getTs_RS_duration_minutes() ); // RS_duration_minutes
	setTs_RS_places( Origen.getTs_RS_places() ); // RS_places
	setTs_start_date( Origen.getTs_start_date() ); // start_date
	setTs_start_time( Origen.getTs_start_time() ); // start_time
	setTs_json( Origen.getTs_json() ); // json
    }
    
    public void copyFrom(TsRCD_AF beanOrigen) {
        TsRCD_AF Origen = beanOrigen;

        setTs_filtro( Origen.getTs_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setTs_sincro( Origen.getTs_sincro() ); // sincro
	setTs_mark( Origen.getTs_mark() ); // mark
	setTs_is_deleted( Origen.getTs_is_deleted() ); // is_deleted
	setTs_author( Origen.getTs_author() ); // author
	setTs_reservation_id( Origen.getTs_reservation_id() ); // reservation_id
	setTs_RS_user_id( Origen.getTs_RS_user_id() ); // RS_user_id
	setTs_RS_location_id( Origen.getTs_RS_location_id() ); // RS_location_id
	setTs_RS_start_date( Origen.getTs_RS_start_date() ); // RS_start_date
	setTs_RS_start_time( Origen.getTs_RS_start_time() ); // RS_start_time
	setTs_RS_pay_status( Origen.getTs_RS_pay_status() ); // RS_pay_status
	setTs_RS_product_id( Origen.getTs_RS_product_id() ); // RS_product_id
	setTs_RS_quantity( Origen.getTs_RS_quantity() ); // RS_quantity
	setTs_RS_duration_minutes( Origen.getTs_RS_duration_minutes() ); // RS_duration_minutes
	setTs_RS_places( Origen.getTs_RS_places() ); // RS_places
	setTs_start_date( Origen.getTs_start_date() ); // start_date
	setTs_start_time( Origen.getTs_start_time() ); // start_time
	setTs_json( Origen.getTs_json() ); // json
    }
    
    public TsBeanFiltro getTs_filtro() { return ts_filtro; }
    
    public void setTs_filtro(TsBeanFiltro ts_filtro) { this.ts_filtro = ts_filtro; }

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
	public String getTs_sincro() {return ts_sincro;}
	/** Set sincro*/
	public void setTs_sincro(String ts_sincro) {this.ts_sincro = ts_sincro;}

	/** Get mark*/
	public String getTs_mark() {return ts_mark;}
	/** Set mark*/
	public void setTs_mark(String ts_mark) {this.ts_mark = ts_mark;}

	/** Get is_deleted*/
	public String getTs_is_deleted() {return ts_is_deleted;}
	/** Set is_deleted*/
	public void setTs_is_deleted(String ts_is_deleted) {this.ts_is_deleted = ts_is_deleted;}

	/** Get author*/
	public String getTs_author() {return ts_author;}
	/** Set author*/
	public void setTs_author(String ts_author) {this.ts_author = ts_author;}

	/** Get reservation_id*/
	public String getTs_reservation_id() {return ts_reservation_id;}
	/** Set reservation_id*/
	public void setTs_reservation_id(String ts_reservation_id) {this.ts_reservation_id = ts_reservation_id;}

	/** Get RS_user_id*/
	public String getTs_RS_user_id() {return ts_RS_user_id;}
	/** Set RS_user_id*/
	public void setTs_RS_user_id(String ts_RS_user_id) {this.ts_RS_user_id = ts_RS_user_id;}

	/** Get RS_location_id*/
	public String getTs_RS_location_id() {return ts_RS_location_id;}
	/** Set RS_location_id*/
	public void setTs_RS_location_id(String ts_RS_location_id) {this.ts_RS_location_id = ts_RS_location_id;}

	/** Get RS_start_date*/
	public String getTs_RS_start_date() {return ts_RS_start_date;}
	/** Set RS_start_date*/
	public void setTs_RS_start_date(String ts_RS_start_date) {this.ts_RS_start_date = ts_RS_start_date;}

	/** Get RS_start_time*/
	public String getTs_RS_start_time() {return ts_RS_start_time;}
	/** Set RS_start_time*/
	public void setTs_RS_start_time(String ts_RS_start_time) {this.ts_RS_start_time = ts_RS_start_time;}

	/** Get RS_pay_status*/
	public String getTs_RS_pay_status() {return ts_RS_pay_status;}
	/** Set RS_pay_status*/
	public void setTs_RS_pay_status(String ts_RS_pay_status) {this.ts_RS_pay_status = ts_RS_pay_status;}

	/** Get RS_product_id*/
	public String getTs_RS_product_id() {return ts_RS_product_id;}
	/** Set RS_product_id*/
	public void setTs_RS_product_id(String ts_RS_product_id) {this.ts_RS_product_id = ts_RS_product_id;}

	/** Get RS_quantity*/
	public long getTs_RS_quantity() {return ts_RS_quantity;}
	/** Set RS_quantity*/
	public void setTs_RS_quantity(long ts_RS_quantity) {this.ts_RS_quantity = ts_RS_quantity;}

	/** Get RS_duration_minutes*/
	public long getTs_RS_duration_minutes() {return ts_RS_duration_minutes;}
	/** Set RS_duration_minutes*/
	public void setTs_RS_duration_minutes(long ts_RS_duration_minutes) {this.ts_RS_duration_minutes = ts_RS_duration_minutes;}

	/** Get RS_places*/
	public long getTs_RS_places() {return ts_RS_places;}
	/** Set RS_places*/
	public void setTs_RS_places(long ts_RS_places) {this.ts_RS_places = ts_RS_places;}

	/** Get start_date*/
	public String getTs_start_date() {return ts_start_date;}
	/** Set start_date*/
	public void setTs_start_date(String ts_start_date) {this.ts_start_date = ts_start_date;}

	/** Get start_time*/
	public String getTs_start_time() {return ts_start_time;}
	/** Set start_time*/
	public void setTs_start_time(String ts_start_time) {this.ts_start_time = ts_start_time;}

	/** Get json*/
	public String getTs_json() {return ts_json;}
	/** Set json*/
	public void setTs_json(String ts_json) {this.ts_json = ts_json;}

}
