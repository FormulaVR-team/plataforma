package com.fvr.lp_labels2print.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.lp_labels2print.bean.LpBean;
import com.fvr.lp_labels2print.bean.LpBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class LpRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public LpBeanFiltro lp_filtro;
    
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
	public String lp_card_id; // card_id
	public String lp_TJ_user_id; // TJ_user_id
	public String lp_TJ_qr_image_base64; // TJ_qr_image_base64
	public String lp_json; // json
    

    public LpRCD_AF() {
	super();
        if ( lp_filtro == null ) { lp_filtro = new LpBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new LpBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new LpBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        LpBean Destino = (LpBean)beanDestino;

	Destino.setLp_card_id( getLp_card_id() ); // card_id
	Destino.setLp_TJ_user_id( getLp_TJ_user_id() ); // TJ_user_id
	Destino.setLp_TJ_qr_image_base64( getLp_TJ_qr_image_base64() ); // TJ_qr_image_base64
	Destino.setLp_json( getLp_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        LpBean Origen = (LpBean)beanOrigen;

	setLp_card_id( Origen.getLp_card_id() ); // card_id
	setLp_TJ_user_id( Origen.getLp_TJ_user_id() ); // TJ_user_id
	setLp_TJ_qr_image_base64( Origen.getLp_TJ_qr_image_base64() ); // TJ_qr_image_base64
	setLp_json( Origen.getLp_json() ); // json
    }
    
    public void copyFrom(LpRCD_AF beanOrigen) {
        LpRCD_AF Origen = beanOrigen;

        setLp_filtro( Origen.getLp_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setLp_card_id( Origen.getLp_card_id() ); // card_id
	setLp_TJ_user_id( Origen.getLp_TJ_user_id() ); // TJ_user_id
	setLp_TJ_qr_image_base64( Origen.getLp_TJ_qr_image_base64() ); // TJ_qr_image_base64
	setLp_json( Origen.getLp_json() ); // json
    }
    
    public LpBeanFiltro getLp_filtro() { return lp_filtro; }
    
    public void setLp_filtro(LpBeanFiltro lp_filtro) { this.lp_filtro = lp_filtro; }

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



	/** Get card_id*/
	public String getLp_card_id() {return lp_card_id;}
	/** Set card_id*/
	public void setLp_card_id(String lp_card_id) {this.lp_card_id = lp_card_id;}

	/** Get TJ_user_id*/
	public String getLp_TJ_user_id() {return lp_TJ_user_id;}
	/** Set TJ_user_id*/
	public void setLp_TJ_user_id(String lp_TJ_user_id) {this.lp_TJ_user_id = lp_TJ_user_id;}

	/** Get TJ_qr_image_base64*/
	public String getLp_TJ_qr_image_base64() {return lp_TJ_qr_image_base64;}
	/** Set TJ_qr_image_base64*/
	public void setLp_TJ_qr_image_base64(String lp_TJ_qr_image_base64) {this.lp_TJ_qr_image_base64 = lp_TJ_qr_image_base64;}

	/** Get json*/
	public String getLp_json() {return lp_json;}
	/** Set json*/
	public void setLp_json(String lp_json) {this.lp_json = lp_json;}

}
