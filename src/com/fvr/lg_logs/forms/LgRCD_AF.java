package com.fvr.lg_logs.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.lg_logs.bean.LgBean;
import com.fvr.lg_logs.bean.LgBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class LgRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public LgBeanFiltro lg_filtro;
    
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
	public String lg_sincro; // sincro
	public String lg_mark; // mark
	public String lg_is_deleted; // is_deleted
	public String lg_author; // author
	public long   lg_serial; // serial
	public String lg_text_1; // text_1
	public String lg_text_2; // text_2
	public String lg_text_3; // text_3
	public String lg_json; // json
    

    public LgRCD_AF() {
	super();
        if ( lg_filtro == null ) { lg_filtro = new LgBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new LgBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new LgBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        LgBean Destino = (LgBean)beanDestino;

	Destino.setLg_sincro( getLg_sincro() ); // sincro
	Destino.setLg_mark( getLg_mark() ); // mark
	Destino.setLg_is_deleted( getLg_is_deleted() ); // is_deleted
	Destino.setLg_author( getLg_author() ); // author
	Destino.setLg_serial( getLg_serial() ); // serial
	Destino.setLg_text_1( getLg_text_1() ); // text_1
	Destino.setLg_text_2( getLg_text_2() ); // text_2
	Destino.setLg_text_3( getLg_text_3() ); // text_3
	Destino.setLg_json( getLg_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        LgBean Origen = (LgBean)beanOrigen;

	setLg_sincro( Origen.getLg_sincro() ); // sincro
	setLg_mark( Origen.getLg_mark() ); // mark
	setLg_is_deleted( Origen.getLg_is_deleted() ); // is_deleted
	setLg_author( Origen.getLg_author() ); // author
	setLg_serial( Origen.getLg_serial() ); // serial
	setLg_text_1( Origen.getLg_text_1() ); // text_1
	setLg_text_2( Origen.getLg_text_2() ); // text_2
	setLg_text_3( Origen.getLg_text_3() ); // text_3
	setLg_json( Origen.getLg_json() ); // json
    }
    
    public void copyFrom(LgRCD_AF beanOrigen) {
        LgRCD_AF Origen = beanOrigen;

        setLg_filtro( Origen.getLg_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setLg_sincro( Origen.getLg_sincro() ); // sincro
	setLg_mark( Origen.getLg_mark() ); // mark
	setLg_is_deleted( Origen.getLg_is_deleted() ); // is_deleted
	setLg_author( Origen.getLg_author() ); // author
	setLg_serial( Origen.getLg_serial() ); // serial
	setLg_text_1( Origen.getLg_text_1() ); // text_1
	setLg_text_2( Origen.getLg_text_2() ); // text_2
	setLg_text_3( Origen.getLg_text_3() ); // text_3
	setLg_json( Origen.getLg_json() ); // json
    }
    
    public LgBeanFiltro getLg_filtro() { return lg_filtro; }
    
    public void setLg_filtro(LgBeanFiltro lg_filtro) { this.lg_filtro = lg_filtro; }

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
	public String getLg_sincro() {return lg_sincro;}
	/** Set sincro*/
	public void setLg_sincro(String lg_sincro) {this.lg_sincro = lg_sincro;}

	/** Get mark*/
	public String getLg_mark() {return lg_mark;}
	/** Set mark*/
	public void setLg_mark(String lg_mark) {this.lg_mark = lg_mark;}

	/** Get is_deleted*/
	public String getLg_is_deleted() {return lg_is_deleted;}
	/** Set is_deleted*/
	public void setLg_is_deleted(String lg_is_deleted) {this.lg_is_deleted = lg_is_deleted;}

	/** Get author*/
	public String getLg_author() {return lg_author;}
	/** Set author*/
	public void setLg_author(String lg_author) {this.lg_author = lg_author;}

	/** Get serial*/
	public long getLg_serial() {return lg_serial;}
	/** Set serial*/
	public void setLg_serial(long lg_serial) {this.lg_serial = lg_serial;}

	/** Get text_1*/
	public String getLg_text_1() {return lg_text_1;}
	/** Set text_1*/
	public void setLg_text_1(String lg_text_1) {this.lg_text_1 = lg_text_1;}

	/** Get text_2*/
	public String getLg_text_2() {return lg_text_2;}
	/** Set text_2*/
	public void setLg_text_2(String lg_text_2) {this.lg_text_2 = lg_text_2;}

	/** Get text_3*/
	public String getLg_text_3() {return lg_text_3;}
	/** Set text_3*/
	public void setLg_text_3(String lg_text_3) {this.lg_text_3 = lg_text_3;}

	/** Get json*/
	public String getLg_json() {return lg_json;}
	/** Set json*/
	public void setLg_json(String lg_json) {this.lg_json = lg_json;}

}
