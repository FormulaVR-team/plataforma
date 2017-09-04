package com.fvr.tj_tarjetasPrepago.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.tj_tarjetasPrepago.bean.TjBean;
import com.fvr.tj_tarjetasPrepago.bean.TjBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class TjRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public TjBeanFiltro tj_filtro;
    
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
	public String tj_sincro; // sincro
	public String tj_mark; // mark
	public String tj_is_deleted; // is_deleted
	public String tj_author; // author
	public String tj_card_id; // card_id
	public double tj_balance_initial; // balance_initial
	public double tj_balance_current; // balance_current
	public double tj_last_sale_amount; // last_sale_amount
	public String tj_last_sale_moment; // last_sale_moment
    

    public TjRCD_AF() {
	super();
        if ( tj_filtro == null ) { tj_filtro = new TjBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new TjBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new TjBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        TjBean Destino = (TjBean)beanDestino;

	Destino.setTj_sincro( getTj_sincro() ); // sincro
	Destino.setTj_mark( getTj_mark() ); // mark
	Destino.setTj_is_deleted( getTj_is_deleted() ); // is_deleted
	Destino.setTj_author( getTj_author() ); // author
	Destino.setTj_card_id( getTj_card_id() ); // card_id
	Destino.setTj_balance_initial( getTj_balance_initial() ); // balance_initial
	Destino.setTj_balance_current( getTj_balance_current() ); // balance_current
	Destino.setTj_last_sale_amount( getTj_last_sale_amount() ); // last_sale_amount
	Destino.setTj_last_sale_moment( getTj_last_sale_moment() ); // last_sale_moment
    }
    
    public void copyFrom(StBean beanOrigen) {
        TjBean Origen = (TjBean)beanOrigen;

	setTj_sincro( Origen.getTj_sincro() ); // sincro
	setTj_mark( Origen.getTj_mark() ); // mark
	setTj_is_deleted( Origen.getTj_is_deleted() ); // is_deleted
	setTj_author( Origen.getTj_author() ); // author
	setTj_card_id( Origen.getTj_card_id() ); // card_id
	setTj_balance_initial( Origen.getTj_balance_initial() ); // balance_initial
	setTj_balance_current( Origen.getTj_balance_current() ); // balance_current
	setTj_last_sale_amount( Origen.getTj_last_sale_amount() ); // last_sale_amount
	setTj_last_sale_moment( Origen.getTj_last_sale_moment() ); // last_sale_moment
    }
    
    public void copyFrom(TjRCD_AF beanOrigen) {
        TjRCD_AF Origen = beanOrigen;

        setTj_filtro( Origen.getTj_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setTj_sincro( Origen.getTj_sincro() ); // sincro
	setTj_mark( Origen.getTj_mark() ); // mark
	setTj_is_deleted( Origen.getTj_is_deleted() ); // is_deleted
	setTj_author( Origen.getTj_author() ); // author
	setTj_card_id( Origen.getTj_card_id() ); // card_id
	setTj_balance_initial( Origen.getTj_balance_initial() ); // balance_initial
	setTj_balance_current( Origen.getTj_balance_current() ); // balance_current
	setTj_last_sale_amount( Origen.getTj_last_sale_amount() ); // last_sale_amount
	setTj_last_sale_moment( Origen.getTj_last_sale_moment() ); // last_sale_moment
    }
    
    public TjBeanFiltro getTj_filtro() { return tj_filtro; }
    
    public void setTj_filtro(TjBeanFiltro tj_filtro) { this.tj_filtro = tj_filtro; }

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
	public String getTj_sincro() {return tj_sincro;}
	/** Set sincro*/
	public void setTj_sincro(String tj_sincro) {this.tj_sincro = tj_sincro;}

	/** Get mark*/
	public String getTj_mark() {return tj_mark;}
	/** Set mark*/
	public void setTj_mark(String tj_mark) {this.tj_mark = tj_mark;}

	/** Get is_deleted*/
	public String getTj_is_deleted() {return tj_is_deleted;}
	/** Set is_deleted*/
	public void setTj_is_deleted(String tj_is_deleted) {this.tj_is_deleted = tj_is_deleted;}

	/** Get author*/
	public String getTj_author() {return tj_author;}
	/** Set author*/
	public void setTj_author(String tj_author) {this.tj_author = tj_author;}

	/** Get card_id*/
	public String getTj_card_id() {return tj_card_id;}
	/** Set card_id*/
	public void setTj_card_id(String tj_card_id) {this.tj_card_id = tj_card_id;}

	/** Get balance_initial*/
	public double getTj_balance_initial() {return tj_balance_initial;}
	/** Set balance_initial*/
	public void setTj_balance_initial(double tj_balance_initial) {this.tj_balance_initial = tj_balance_initial;}

	/** Get balance_current*/
	public double getTj_balance_current() {return tj_balance_current;}
	/** Set balance_current*/
	public void setTj_balance_current(double tj_balance_current) {this.tj_balance_current = tj_balance_current;}

	/** Get last_sale_amount*/
	public double getTj_last_sale_amount() {return tj_last_sale_amount;}
	/** Set last_sale_amount*/
	public void setTj_last_sale_amount(double tj_last_sale_amount) {this.tj_last_sale_amount = tj_last_sale_amount;}

	/** Get last_sale_moment*/
	public String getTj_last_sale_moment() {return tj_last_sale_moment;}
	/** Set last_sale_moment*/
	public void setTj_last_sale_moment(String tj_last_sale_moment) {this.tj_last_sale_moment = tj_last_sale_moment;}

}
