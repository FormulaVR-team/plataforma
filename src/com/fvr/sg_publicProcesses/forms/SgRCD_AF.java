package com.fvr.sg_publicProcesses.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.sg_publicProcesses.bean.SgBean;
import com.fvr.sg_publicProcesses.bean.SgBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class SgRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public SgBeanFiltro sg_filtro;
    
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
	public String sg_sincro; // sincro
	public String sg_mark; // mark
	public String sg_is_deleted; // is_deleted
	public String sg_author; // author
	public String sg_role_id; // role_id
	public String sg_process_id; // process_id
	public String sg_json; // json
    

    public SgRCD_AF() {
	super();
        if ( sg_filtro == null ) { sg_filtro = new SgBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new SgBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new SgBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        SgBean Destino = (SgBean)beanDestino;

	Destino.setSg_sincro( getSg_sincro() ); // sincro
	Destino.setSg_mark( getSg_mark() ); // mark
	Destino.setSg_is_deleted( getSg_is_deleted() ); // is_deleted
	Destino.setSg_author( getSg_author() ); // author
	Destino.setSg_role_id( getSg_role_id() ); // role_id
	Destino.setSg_process_id( getSg_process_id() ); // process_id
	Destino.setSg_json( getSg_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        SgBean Origen = (SgBean)beanOrigen;

	setSg_sincro( Origen.getSg_sincro() ); // sincro
	setSg_mark( Origen.getSg_mark() ); // mark
	setSg_is_deleted( Origen.getSg_is_deleted() ); // is_deleted
	setSg_author( Origen.getSg_author() ); // author
	setSg_role_id( Origen.getSg_role_id() ); // role_id
	setSg_process_id( Origen.getSg_process_id() ); // process_id
	setSg_json( Origen.getSg_json() ); // json
    }
    
    public void copyFrom(SgRCD_AF beanOrigen) {
        SgRCD_AF Origen = beanOrigen;

        setSg_filtro( Origen.getSg_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setSg_sincro( Origen.getSg_sincro() ); // sincro
	setSg_mark( Origen.getSg_mark() ); // mark
	setSg_is_deleted( Origen.getSg_is_deleted() ); // is_deleted
	setSg_author( Origen.getSg_author() ); // author
	setSg_role_id( Origen.getSg_role_id() ); // role_id
	setSg_process_id( Origen.getSg_process_id() ); // process_id
	setSg_json( Origen.getSg_json() ); // json
    }
    
    public SgBeanFiltro getSg_filtro() { return sg_filtro; }
    
    public void setSg_filtro(SgBeanFiltro sg_filtro) { this.sg_filtro = sg_filtro; }

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
	public String getSg_sincro() {return sg_sincro;}
	/** Set sincro*/
	public void setSg_sincro(String sg_sincro) {this.sg_sincro = sg_sincro;}

	/** Get mark*/
	public String getSg_mark() {return sg_mark;}
	/** Set mark*/
	public void setSg_mark(String sg_mark) {this.sg_mark = sg_mark;}

	/** Get is_deleted*/
	public String getSg_is_deleted() {return sg_is_deleted;}
	/** Set is_deleted*/
	public void setSg_is_deleted(String sg_is_deleted) {this.sg_is_deleted = sg_is_deleted;}

	/** Get author*/
	public String getSg_author() {return sg_author;}
	/** Set author*/
	public void setSg_author(String sg_author) {this.sg_author = sg_author;}

	/** Get role_id*/
	public String getSg_role_id() {return sg_role_id;}
	/** Set role_id*/
	public void setSg_role_id(String sg_role_id) {this.sg_role_id = sg_role_id;}

	/** Get process_id*/
	public String getSg_process_id() {return sg_process_id;}
	/** Set process_id*/
	public void setSg_process_id(String sg_process_id) {this.sg_process_id = sg_process_id;}

	/** Get json*/
	public String getSg_json() {return sg_json;}
	/** Set json*/
	public void setSg_json(String sg_json) {this.sg_json = sg_json;}

}
