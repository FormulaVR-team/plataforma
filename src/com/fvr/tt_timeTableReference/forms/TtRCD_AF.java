package com.fvr.tt_timeTableReference.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.tt_timeTableReference.bean.TtBean;
import com.fvr.tt_timeTableReference.bean.TtBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class TtRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public TtBeanFiltro tt_filtro;
    
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
	public String tt_sincro; // sincro
	public String tt_mark; // mark
	public String tt_is_deleted; // is_deleted
	public String tt_author; // author
	public String tt_location_id; // location_id
	public String tt_day_type; // day_type
	public String tt_start_time; // start_time
	public long   tt_duration_minutes; // duration_minutes
	public String tt_isBlocked; // isBlocked
	public String tt_json; // json
    

    public TtRCD_AF() {
	super();
        if ( tt_filtro == null ) { tt_filtro = new TtBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new TtBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new TtBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        TtBean Destino = (TtBean)beanDestino;

	Destino.setTt_sincro( getTt_sincro() ); // sincro
	Destino.setTt_mark( getTt_mark() ); // mark
	Destino.setTt_is_deleted( getTt_is_deleted() ); // is_deleted
	Destino.setTt_author( getTt_author() ); // author
	Destino.setTt_location_id( getTt_location_id() ); // location_id
	Destino.setTt_day_type( getTt_day_type() ); // day_type
	Destino.setTt_start_time( getTt_start_time() ); // start_time
	Destino.setTt_duration_minutes( getTt_duration_minutes() ); // duration_minutes
	Destino.setTt_isBlocked( getTt_isBlocked() ); // isBlocked
	Destino.setTt_json( getTt_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        TtBean Origen = (TtBean)beanOrigen;

	setTt_sincro( Origen.getTt_sincro() ); // sincro
	setTt_mark( Origen.getTt_mark() ); // mark
	setTt_is_deleted( Origen.getTt_is_deleted() ); // is_deleted
	setTt_author( Origen.getTt_author() ); // author
	setTt_location_id( Origen.getTt_location_id() ); // location_id
	setTt_day_type( Origen.getTt_day_type() ); // day_type
	setTt_start_time( Origen.getTt_start_time() ); // start_time
	setTt_duration_minutes( Origen.getTt_duration_minutes() ); // duration_minutes
	setTt_isBlocked( Origen.getTt_isBlocked() ); // isBlocked
	setTt_json( Origen.getTt_json() ); // json
    }
    
    public void copyFrom(TtRCD_AF beanOrigen) {
        TtRCD_AF Origen = beanOrigen;

        setTt_filtro( Origen.getTt_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setTt_sincro( Origen.getTt_sincro() ); // sincro
	setTt_mark( Origen.getTt_mark() ); // mark
	setTt_is_deleted( Origen.getTt_is_deleted() ); // is_deleted
	setTt_author( Origen.getTt_author() ); // author
	setTt_location_id( Origen.getTt_location_id() ); // location_id
	setTt_day_type( Origen.getTt_day_type() ); // day_type
	setTt_start_time( Origen.getTt_start_time() ); // start_time
	setTt_duration_minutes( Origen.getTt_duration_minutes() ); // duration_minutes
	setTt_isBlocked( Origen.getTt_isBlocked() ); // isBlocked
	setTt_json( Origen.getTt_json() ); // json
    }
    
    public TtBeanFiltro getTt_filtro() { return tt_filtro; }
    
    public void setTt_filtro(TtBeanFiltro tt_filtro) { this.tt_filtro = tt_filtro; }

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
	public String getTt_sincro() {return tt_sincro;}
	/** Set sincro*/
	public void setTt_sincro(String tt_sincro) {this.tt_sincro = tt_sincro;}

	/** Get mark*/
	public String getTt_mark() {return tt_mark;}
	/** Set mark*/
	public void setTt_mark(String tt_mark) {this.tt_mark = tt_mark;}

	/** Get is_deleted*/
	public String getTt_is_deleted() {return tt_is_deleted;}
	/** Set is_deleted*/
	public void setTt_is_deleted(String tt_is_deleted) {this.tt_is_deleted = tt_is_deleted;}

	/** Get author*/
	public String getTt_author() {return tt_author;}
	/** Set author*/
	public void setTt_author(String tt_author) {this.tt_author = tt_author;}

	/** Get location_id*/
	public String getTt_location_id() {return tt_location_id;}
	/** Set location_id*/
	public void setTt_location_id(String tt_location_id) {this.tt_location_id = tt_location_id;}

	/** Get day_type*/
	public String getTt_day_type() {return tt_day_type;}
	/** Set day_type*/
	public void setTt_day_type(String tt_day_type) {this.tt_day_type = tt_day_type;}

	/** Get start_time*/
	public String getTt_start_time() {return tt_start_time;}
	/** Set start_time*/
	public void setTt_start_time(String tt_start_time) {this.tt_start_time = tt_start_time;}

	/** Get duration_minutes*/
	public long getTt_duration_minutes() {return tt_duration_minutes;}
	/** Set duration_minutes*/
	public void setTt_duration_minutes(long tt_duration_minutes) {this.tt_duration_minutes = tt_duration_minutes;}

	/** Get isBlocked*/
	public String getTt_isBlocked() {return tt_isBlocked;}
	/** Set isBlocked*/
	public void setTt_isBlocked(String tt_isBlocked) {this.tt_isBlocked = tt_isBlocked;}

	/** Get json*/
	public String getTt_json() {return tt_json;}
	/** Set json*/
	public void setTt_json(String tt_json) {this.tt_json = tt_json;}

}
