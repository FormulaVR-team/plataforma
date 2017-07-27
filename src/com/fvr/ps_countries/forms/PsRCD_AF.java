package com.fvr.ps_countries.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.ps_countries.bean.PsBean;
import com.fvr.ps_countries.bean.PsBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PsRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public PsBeanFiltro ps_filtro;
    
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
	public String ps_sincro; // sincro
	public String ps_mark; // mark
	public String ps_is_deleted; // is_deleted
	public String ps_author; // author
	public long   ps_country_id; // country_id
	public String ps_name; // name
	public String ps_alpha_2; // alpha_2
	public String ps_alpha_3; // alpha_3
	public String ps_flag_base64; // flag_base64
	public String ps_json; // json
    

    public PsRCD_AF() {
	super();
        if ( ps_filtro == null ) { ps_filtro = new PsBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new PsBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new PsBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        PsBean Destino = (PsBean)beanDestino;

	Destino.setPs_sincro( getPs_sincro() ); // sincro
	Destino.setPs_mark( getPs_mark() ); // mark
	Destino.setPs_is_deleted( getPs_is_deleted() ); // is_deleted
	Destino.setPs_author( getPs_author() ); // author
	Destino.setPs_country_id( getPs_country_id() ); // country_id
	Destino.setPs_name( getPs_name() ); // name
	Destino.setPs_alpha_2( getPs_alpha_2() ); // alpha_2
	Destino.setPs_alpha_3( getPs_alpha_3() ); // alpha_3
	Destino.setPs_flag_base64( getPs_flag_base64() ); // flag_base64
	Destino.setPs_json( getPs_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        PsBean Origen = (PsBean)beanOrigen;

	setPs_sincro( Origen.getPs_sincro() ); // sincro
	setPs_mark( Origen.getPs_mark() ); // mark
	setPs_is_deleted( Origen.getPs_is_deleted() ); // is_deleted
	setPs_author( Origen.getPs_author() ); // author
	setPs_country_id( Origen.getPs_country_id() ); // country_id
	setPs_name( Origen.getPs_name() ); // name
	setPs_alpha_2( Origen.getPs_alpha_2() ); // alpha_2
	setPs_alpha_3( Origen.getPs_alpha_3() ); // alpha_3
	setPs_flag_base64( Origen.getPs_flag_base64() ); // flag_base64
	setPs_json( Origen.getPs_json() ); // json
    }
    
    public void copyFrom(PsRCD_AF beanOrigen) {
        PsRCD_AF Origen = beanOrigen;

        setPs_filtro( Origen.getPs_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setPs_sincro( Origen.getPs_sincro() ); // sincro
	setPs_mark( Origen.getPs_mark() ); // mark
	setPs_is_deleted( Origen.getPs_is_deleted() ); // is_deleted
	setPs_author( Origen.getPs_author() ); // author
	setPs_country_id( Origen.getPs_country_id() ); // country_id
	setPs_name( Origen.getPs_name() ); // name
	setPs_alpha_2( Origen.getPs_alpha_2() ); // alpha_2
	setPs_alpha_3( Origen.getPs_alpha_3() ); // alpha_3
	setPs_flag_base64( Origen.getPs_flag_base64() ); // flag_base64
	setPs_json( Origen.getPs_json() ); // json
    }
    
    public PsBeanFiltro getPs_filtro() { return ps_filtro; }
    
    public void setPs_filtro(PsBeanFiltro ps_filtro) { this.ps_filtro = ps_filtro; }

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
	public String getPs_sincro() {return ps_sincro;}
	/** Set sincro*/
	public void setPs_sincro(String ps_sincro) {this.ps_sincro = ps_sincro;}

	/** Get mark*/
	public String getPs_mark() {return ps_mark;}
	/** Set mark*/
	public void setPs_mark(String ps_mark) {this.ps_mark = ps_mark;}

	/** Get is_deleted*/
	public String getPs_is_deleted() {return ps_is_deleted;}
	/** Set is_deleted*/
	public void setPs_is_deleted(String ps_is_deleted) {this.ps_is_deleted = ps_is_deleted;}

	/** Get author*/
	public String getPs_author() {return ps_author;}
	/** Set author*/
	public void setPs_author(String ps_author) {this.ps_author = ps_author;}

	/** Get country_id*/
	public long getPs_country_id() {return ps_country_id;}
	/** Set country_id*/
	public void setPs_country_id(long ps_country_id) {this.ps_country_id = ps_country_id;}

	/** Get name*/
	public String getPs_name() {return ps_name;}
	/** Set name*/
	public void setPs_name(String ps_name) {this.ps_name = ps_name;}

	/** Get alpha_2*/
	public String getPs_alpha_2() {return ps_alpha_2;}
	/** Set alpha_2*/
	public void setPs_alpha_2(String ps_alpha_2) {this.ps_alpha_2 = ps_alpha_2;}

	/** Get alpha_3*/
	public String getPs_alpha_3() {return ps_alpha_3;}
	/** Set alpha_3*/
	public void setPs_alpha_3(String ps_alpha_3) {this.ps_alpha_3 = ps_alpha_3;}

	/** Get flag_base64*/
	public String getPs_flag_base64() {return ps_flag_base64;}
	/** Set flag_base64*/
	public void setPs_flag_base64(String ps_flag_base64) {this.ps_flag_base64 = ps_flag_base64;}

	/** Get json*/
	public String getPs_json() {return ps_json;}
	/** Set json*/
	public void setPs_json(String ps_json) {this.ps_json = ps_json;}

}
