package com.fvr.py_PayPalTokens.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.py_PayPalTokens.bean.PyBean;
import com.fvr.py_PayPalTokens.bean.PyBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PyRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public PyBeanFiltro py_filtro;
    
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
	public String py_sincro; // sincro
	public String py_mark; // mark
	public String py_is_deleted; // is_deleted
	public String py_author; // author
	public String py_user_id; // user_id
	public String py_US_first_name; // US_first_name
	public String py_US_last_name; // US_last_name
	public String py_reservation_id; // reservation_id
	public String py_paypal_token_id; // paypal_token_id
	public String py_paypal_usr; // paypal_usr
	public String py_paypal_pwd; // paypal_pwd
	public String py_paypal_signature; // paypal_signature
	public String py_stsProceso; // stsProceso
	public String py_json; // json
    

    public PyRCD_AF() {
	super();
        if ( py_filtro == null ) { py_filtro = new PyBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new PyBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new PyBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        PyBean Destino = (PyBean)beanDestino;

	Destino.setPy_sincro( getPy_sincro() ); // sincro
	Destino.setPy_mark( getPy_mark() ); // mark
	Destino.setPy_is_deleted( getPy_is_deleted() ); // is_deleted
	Destino.setPy_author( getPy_author() ); // author
	Destino.setPy_user_id( getPy_user_id() ); // user_id
	Destino.setPy_US_first_name( getPy_US_first_name() ); // US_first_name
	Destino.setPy_US_last_name( getPy_US_last_name() ); // US_last_name
	Destino.setPy_reservation_id( getPy_reservation_id() ); // reservation_id
	Destino.setPy_paypal_token_id( getPy_paypal_token_id() ); // paypal_token_id
	Destino.setPy_paypal_usr( getPy_paypal_usr() ); // paypal_usr
	Destino.setPy_paypal_pwd( getPy_paypal_pwd() ); // paypal_pwd
	Destino.setPy_paypal_signature( getPy_paypal_signature() ); // paypal_signature
	Destino.setPy_stsProceso( getPy_stsProceso() ); // stsProceso
	Destino.setPy_json( getPy_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        PyBean Origen = (PyBean)beanOrigen;

	setPy_sincro( Origen.getPy_sincro() ); // sincro
	setPy_mark( Origen.getPy_mark() ); // mark
	setPy_is_deleted( Origen.getPy_is_deleted() ); // is_deleted
	setPy_author( Origen.getPy_author() ); // author
	setPy_user_id( Origen.getPy_user_id() ); // user_id
	setPy_US_first_name( Origen.getPy_US_first_name() ); // US_first_name
	setPy_US_last_name( Origen.getPy_US_last_name() ); // US_last_name
	setPy_reservation_id( Origen.getPy_reservation_id() ); // reservation_id
	setPy_paypal_token_id( Origen.getPy_paypal_token_id() ); // paypal_token_id
	setPy_paypal_usr( Origen.getPy_paypal_usr() ); // paypal_usr
	setPy_paypal_pwd( Origen.getPy_paypal_pwd() ); // paypal_pwd
	setPy_paypal_signature( Origen.getPy_paypal_signature() ); // paypal_signature
	setPy_stsProceso( Origen.getPy_stsProceso() ); // stsProceso
	setPy_json( Origen.getPy_json() ); // json
    }
    
    public void copyFrom(PyRCD_AF beanOrigen) {
        PyRCD_AF Origen = beanOrigen;

        setPy_filtro( Origen.getPy_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setPy_sincro( Origen.getPy_sincro() ); // sincro
	setPy_mark( Origen.getPy_mark() ); // mark
	setPy_is_deleted( Origen.getPy_is_deleted() ); // is_deleted
	setPy_author( Origen.getPy_author() ); // author
	setPy_user_id( Origen.getPy_user_id() ); // user_id
	setPy_US_first_name( Origen.getPy_US_first_name() ); // US_first_name
	setPy_US_last_name( Origen.getPy_US_last_name() ); // US_last_name
	setPy_reservation_id( Origen.getPy_reservation_id() ); // reservation_id
	setPy_paypal_token_id( Origen.getPy_paypal_token_id() ); // paypal_token_id
	setPy_paypal_usr( Origen.getPy_paypal_usr() ); // paypal_usr
	setPy_paypal_pwd( Origen.getPy_paypal_pwd() ); // paypal_pwd
	setPy_paypal_signature( Origen.getPy_paypal_signature() ); // paypal_signature
	setPy_stsProceso( Origen.getPy_stsProceso() ); // stsProceso
	setPy_json( Origen.getPy_json() ); // json
    }
    
    public PyBeanFiltro getPy_filtro() { return py_filtro; }
    
    public void setPy_filtro(PyBeanFiltro py_filtro) { this.py_filtro = py_filtro; }

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
	public String getPy_sincro() {return py_sincro;}
	/** Set sincro*/
	public void setPy_sincro(String py_sincro) {this.py_sincro = py_sincro;}

	/** Get mark*/
	public String getPy_mark() {return py_mark;}
	/** Set mark*/
	public void setPy_mark(String py_mark) {this.py_mark = py_mark;}

	/** Get is_deleted*/
	public String getPy_is_deleted() {return py_is_deleted;}
	/** Set is_deleted*/
	public void setPy_is_deleted(String py_is_deleted) {this.py_is_deleted = py_is_deleted;}

	/** Get author*/
	public String getPy_author() {return py_author;}
	/** Set author*/
	public void setPy_author(String py_author) {this.py_author = py_author;}

	/** Get user_id*/
	public String getPy_user_id() {return py_user_id;}
	/** Set user_id*/
	public void setPy_user_id(String py_user_id) {this.py_user_id = py_user_id;}

	/** Get US_first_name*/
	public String getPy_US_first_name() {return py_US_first_name;}
	/** Set US_first_name*/
	public void setPy_US_first_name(String py_US_first_name) {this.py_US_first_name = py_US_first_name;}

	/** Get US_last_name*/
	public String getPy_US_last_name() {return py_US_last_name;}
	/** Set US_last_name*/
	public void setPy_US_last_name(String py_US_last_name) {this.py_US_last_name = py_US_last_name;}

	/** Get reservation_id*/
	public String getPy_reservation_id() {return py_reservation_id;}
	/** Set reservation_id*/
	public void setPy_reservation_id(String py_reservation_id) {this.py_reservation_id = py_reservation_id;}

	/** Get paypal_token_id*/
	public String getPy_paypal_token_id() {return py_paypal_token_id;}
	/** Set paypal_token_id*/
	public void setPy_paypal_token_id(String py_paypal_token_id) {this.py_paypal_token_id = py_paypal_token_id;}

	/** Get paypal_usr*/
	public String getPy_paypal_usr() {return py_paypal_usr;}
	/** Set paypal_usr*/
	public void setPy_paypal_usr(String py_paypal_usr) {this.py_paypal_usr = py_paypal_usr;}

	/** Get paypal_pwd*/
	public String getPy_paypal_pwd() {return py_paypal_pwd;}
	/** Set paypal_pwd*/
	public void setPy_paypal_pwd(String py_paypal_pwd) {this.py_paypal_pwd = py_paypal_pwd;}

	/** Get paypal_signature*/
	public String getPy_paypal_signature() {return py_paypal_signature;}
	/** Set paypal_signature*/
	public void setPy_paypal_signature(String py_paypal_signature) {this.py_paypal_signature = py_paypal_signature;}

	/** Get stsProceso*/
	public String getPy_stsProceso() {return py_stsProceso;}
	/** Set stsProceso*/
	public void setPy_stsProceso(String py_stsProceso) {this.py_stsProceso = py_stsProceso;}

	/** Get json*/
	public String getPy_json() {return py_json;}
	/** Set json*/
	public void setPy_json(String py_json) {this.py_json = py_json;}

}
