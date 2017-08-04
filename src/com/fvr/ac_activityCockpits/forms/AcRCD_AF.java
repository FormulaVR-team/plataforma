package com.fvr.ac_activityCockpits.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.ac_activityCockpits.bean.AcBean;
import com.fvr.ac_activityCockpits.bean.AcBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class AcRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public AcBeanFiltro ac_filtro;
    
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
	public String ac_sincro; // sincro
	public String ac_mark; // mark
	public String ac_is_deleted; // is_deleted
	public String ac_author; // author
	public long   ac_serial; // serial
	public String ac_computername; // computername
	public String ac_filename; // filename
	public String ac_content; // content
	public String ac_json; // json
    

    public AcRCD_AF() {
	super();
        if ( ac_filtro == null ) { ac_filtro = new AcBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new AcBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new AcBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        AcBean Destino = (AcBean)beanDestino;

	Destino.setAc_sincro( getAc_sincro() ); // sincro
	Destino.setAc_mark( getAc_mark() ); // mark
	Destino.setAc_is_deleted( getAc_is_deleted() ); // is_deleted
	Destino.setAc_author( getAc_author() ); // author
	Destino.setAc_serial( getAc_serial() ); // serial
	Destino.setAc_computername( getAc_computername() ); // computername
	Destino.setAc_filename( getAc_filename() ); // filename
	Destino.setAc_content( getAc_content() ); // content
	Destino.setAc_json( getAc_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        AcBean Origen = (AcBean)beanOrigen;

	setAc_sincro( Origen.getAc_sincro() ); // sincro
	setAc_mark( Origen.getAc_mark() ); // mark
	setAc_is_deleted( Origen.getAc_is_deleted() ); // is_deleted
	setAc_author( Origen.getAc_author() ); // author
	setAc_serial( Origen.getAc_serial() ); // serial
	setAc_computername( Origen.getAc_computername() ); // computername
	setAc_filename( Origen.getAc_filename() ); // filename
	setAc_content( Origen.getAc_content() ); // content
	setAc_json( Origen.getAc_json() ); // json
    }
    
    public void copyFrom(AcRCD_AF beanOrigen) {
        AcRCD_AF Origen = beanOrigen;

        setAc_filtro( Origen.getAc_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setAc_sincro( Origen.getAc_sincro() ); // sincro
	setAc_mark( Origen.getAc_mark() ); // mark
	setAc_is_deleted( Origen.getAc_is_deleted() ); // is_deleted
	setAc_author( Origen.getAc_author() ); // author
	setAc_serial( Origen.getAc_serial() ); // serial
	setAc_computername( Origen.getAc_computername() ); // computername
	setAc_filename( Origen.getAc_filename() ); // filename
	setAc_content( Origen.getAc_content() ); // content
	setAc_json( Origen.getAc_json() ); // json
    }
    
    public AcBeanFiltro getAc_filtro() { return ac_filtro; }
    
    public void setAc_filtro(AcBeanFiltro ac_filtro) { this.ac_filtro = ac_filtro; }

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
	public String getAc_sincro() {return ac_sincro;}
	/** Set sincro*/
	public void setAc_sincro(String ac_sincro) {this.ac_sincro = ac_sincro;}

	/** Get mark*/
	public String getAc_mark() {return ac_mark;}
	/** Set mark*/
	public void setAc_mark(String ac_mark) {this.ac_mark = ac_mark;}

	/** Get is_deleted*/
	public String getAc_is_deleted() {return ac_is_deleted;}
	/** Set is_deleted*/
	public void setAc_is_deleted(String ac_is_deleted) {this.ac_is_deleted = ac_is_deleted;}

	/** Get author*/
	public String getAc_author() {return ac_author;}
	/** Set author*/
	public void setAc_author(String ac_author) {this.ac_author = ac_author;}

	/** Get serial*/
	public long getAc_serial() {return ac_serial;}
	/** Set serial*/
	public void setAc_serial(long ac_serial) {this.ac_serial = ac_serial;}

	/** Get computername*/
	public String getAc_computername() {return ac_computername;}
	/** Set computername*/
	public void setAc_computername(String ac_computername) {this.ac_computername = ac_computername;}

	/** Get filename*/
	public String getAc_filename() {return ac_filename;}
	/** Set filename*/
	public void setAc_filename(String ac_filename) {this.ac_filename = ac_filename;}

	/** Get content*/
	public String getAc_content() {return ac_content;}
	/** Set content*/
	public void setAc_content(String ac_content) {this.ac_content = ac_content;}

	/** Get json*/
	public String getAc_json() {return ac_json;}
	/** Set json*/
	public void setAc_json(String ac_json) {this.ac_json = ac_json;}

}
