package com.fvr.tk_tokens.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.tk_tokens.bean.TkBean;
import com.fvr.tk_tokens.bean.TkBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class TkRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public TkBeanFiltro tk_filtro;
    
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
	public String tk_sincro; // sincro
	public String tk_mark; // mark
	public String tk_is_deleted; // is_deleted
	public String tk_author; // author
	public String tk_token_id; // token_id
	public String tk_json; // json
    

    public TkRCD_AF() {
	super();
        if ( tk_filtro == null ) { tk_filtro = new TkBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new TkBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new TkBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        TkBean Destino = (TkBean)beanDestino;

	Destino.setTk_sincro( getTk_sincro() ); // sincro
	Destino.setTk_mark( getTk_mark() ); // mark
	Destino.setTk_is_deleted( getTk_is_deleted() ); // is_deleted
	Destino.setTk_author( getTk_author() ); // author
	Destino.setTk_token_id( getTk_token_id() ); // token_id
	Destino.setTk_json( getTk_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        TkBean Origen = (TkBean)beanOrigen;

	setTk_sincro( Origen.getTk_sincro() ); // sincro
	setTk_mark( Origen.getTk_mark() ); // mark
	setTk_is_deleted( Origen.getTk_is_deleted() ); // is_deleted
	setTk_author( Origen.getTk_author() ); // author
	setTk_token_id( Origen.getTk_token_id() ); // token_id
	setTk_json( Origen.getTk_json() ); // json
    }
    
    public void copyFrom(TkRCD_AF beanOrigen) {
        TkRCD_AF Origen = beanOrigen;

        setTk_filtro( Origen.getTk_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setTk_sincro( Origen.getTk_sincro() ); // sincro
	setTk_mark( Origen.getTk_mark() ); // mark
	setTk_is_deleted( Origen.getTk_is_deleted() ); // is_deleted
	setTk_author( Origen.getTk_author() ); // author
	setTk_token_id( Origen.getTk_token_id() ); // token_id
	setTk_json( Origen.getTk_json() ); // json
    }
    
    public TkBeanFiltro getTk_filtro() { return tk_filtro; }
    
    public void setTk_filtro(TkBeanFiltro tk_filtro) { this.tk_filtro = tk_filtro; }

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
	public String getTk_sincro() {return tk_sincro;}
	/** Set sincro*/
	public void setTk_sincro(String tk_sincro) {this.tk_sincro = tk_sincro;}

	/** Get mark*/
	public String getTk_mark() {return tk_mark;}
	/** Set mark*/
	public void setTk_mark(String tk_mark) {this.tk_mark = tk_mark;}

	/** Get is_deleted*/
	public String getTk_is_deleted() {return tk_is_deleted;}
	/** Set is_deleted*/
	public void setTk_is_deleted(String tk_is_deleted) {this.tk_is_deleted = tk_is_deleted;}

	/** Get author*/
	public String getTk_author() {return tk_author;}
	/** Set author*/
	public void setTk_author(String tk_author) {this.tk_author = tk_author;}

	/** Get token_id*/
	public String getTk_token_id() {return tk_token_id;}
	/** Set token_id*/
	public void setTk_token_id(String tk_token_id) {this.tk_token_id = tk_token_id;}

	/** Get json*/
	public String getTk_json() {return tk_json;}
	/** Set json*/
	public void setTk_json(String tk_json) {this.tk_json = tk_json;}

}
