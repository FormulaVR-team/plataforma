package com.fvr.pa_systemParameters.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.pa_systemParameters.bean.PaBean;
import com.fvr.pa_systemParameters.bean.PaBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PaRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public PaBeanFiltro pa_filtro;
    
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
	public String pa_sincro; // sincro
	public String pa_mark; // mark
	public String pa_is_deleted; // is_deleted
	public String pa_author; // author
	public String pa_group_id; // group_id
	public String pa_key; // key
	public String pa_value; // value
    

    public PaRCD_AF() {
	super();
        if ( pa_filtro == null ) { pa_filtro = new PaBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new PaBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new PaBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        PaBean Destino = (PaBean)beanDestino;

	Destino.setPa_sincro( getPa_sincro() ); // sincro
	Destino.setPa_mark( getPa_mark() ); // mark
	Destino.setPa_is_deleted( getPa_is_deleted() ); // is_deleted
	Destino.setPa_author( getPa_author() ); // author
	Destino.setPa_group_id( getPa_group_id() ); // group_id
	Destino.setPa_key( getPa_key() ); // key
	Destino.setPa_value( getPa_value() ); // value
    }
    
    public void copyFrom(StBean beanOrigen) {
        PaBean Origen = (PaBean)beanOrigen;

	setPa_sincro( Origen.getPa_sincro() ); // sincro
	setPa_mark( Origen.getPa_mark() ); // mark
	setPa_is_deleted( Origen.getPa_is_deleted() ); // is_deleted
	setPa_author( Origen.getPa_author() ); // author
	setPa_group_id( Origen.getPa_group_id() ); // group_id
	setPa_key( Origen.getPa_key() ); // key
	setPa_value( Origen.getPa_value() ); // value
    }
    
    public void copyFrom(PaRCD_AF beanOrigen) {
        PaRCD_AF Origen = beanOrigen;

        setPa_filtro( Origen.getPa_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setPa_sincro( Origen.getPa_sincro() ); // sincro
	setPa_mark( Origen.getPa_mark() ); // mark
	setPa_is_deleted( Origen.getPa_is_deleted() ); // is_deleted
	setPa_author( Origen.getPa_author() ); // author
	setPa_group_id( Origen.getPa_group_id() ); // group_id
	setPa_key( Origen.getPa_key() ); // key
	setPa_value( Origen.getPa_value() ); // value
    }
    
    public PaBeanFiltro getPa_filtro() { return pa_filtro; }
    
    public void setPa_filtro(PaBeanFiltro pa_filtro) { this.pa_filtro = pa_filtro; }

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
	public String getPa_sincro() {return pa_sincro;}
	/** Set sincro*/
	public void setPa_sincro(String pa_sincro) {this.pa_sincro = pa_sincro;}

	/** Get mark*/
	public String getPa_mark() {return pa_mark;}
	/** Set mark*/
	public void setPa_mark(String pa_mark) {this.pa_mark = pa_mark;}

	/** Get is_deleted*/
	public String getPa_is_deleted() {return pa_is_deleted;}
	/** Set is_deleted*/
	public void setPa_is_deleted(String pa_is_deleted) {this.pa_is_deleted = pa_is_deleted;}

	/** Get author*/
	public String getPa_author() {return pa_author;}
	/** Set author*/
	public void setPa_author(String pa_author) {this.pa_author = pa_author;}

	/** Get group_id*/
	public String getPa_group_id() {return pa_group_id;}
	/** Set group_id*/
	public void setPa_group_id(String pa_group_id) {this.pa_group_id = pa_group_id;}

	/** Get key*/
	public String getPa_key() {return pa_key;}
	/** Set key*/
	public void setPa_key(String pa_key) {this.pa_key = pa_key;}

	/** Get value*/
	public String getPa_value() {return pa_value;}
	/** Set value*/
	public void setPa_value(String pa_value) {this.pa_value = pa_value;}

}
