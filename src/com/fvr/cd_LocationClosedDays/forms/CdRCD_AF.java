package com.fvr.cd_LocationClosedDays.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.cd_LocationClosedDays.bean.CdBean;
import com.fvr.cd_LocationClosedDays.bean.CdBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class CdRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public CdBeanFiltro cd_filtro;
    
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
	public String cd_sincro; // sincro
	public String cd_mark; // mark
	public String cd_is_deleted; // is_deleted
	public String cd_author; // author
	public String cd_location_id; // location_id
	public String cd_closed_day_aaaa_mm_dd; // closed_day_aaaa_mm_dd
	public String cd_json; // json
    

    public CdRCD_AF() {
	super();
        if ( cd_filtro == null ) { cd_filtro = new CdBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new CdBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new CdBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
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
    
    public void copyFrom(CdRCD_AF beanOrigen) {
        CdRCD_AF Origen = beanOrigen;

        setCd_filtro( Origen.getCd_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setCd_sincro( Origen.getCd_sincro() ); // sincro
	setCd_mark( Origen.getCd_mark() ); // mark
	setCd_is_deleted( Origen.getCd_is_deleted() ); // is_deleted
	setCd_author( Origen.getCd_author() ); // author
	setCd_location_id( Origen.getCd_location_id() ); // location_id
	setCd_closed_day_aaaa_mm_dd( Origen.getCd_closed_day_aaaa_mm_dd() ); // closed_day_aaaa_mm_dd
	setCd_json( Origen.getCd_json() ); // json
    }
    
    public CdBeanFiltro getCd_filtro() { return cd_filtro; }
    
    public void setCd_filtro(CdBeanFiltro cd_filtro) { this.cd_filtro = cd_filtro; }

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

}
