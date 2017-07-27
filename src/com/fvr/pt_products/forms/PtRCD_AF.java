package com.fvr.pt_products.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.pt_products.bean.PtBean;
import com.fvr.pt_products.bean.PtBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PtRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public PtBeanFiltro pt_filtro;
    
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
	public String pt_sincro; // sincro
	public String pt_mark; // mark
	public String pt_is_deleted; // is_deleted
	public String pt_author; // author
	public String pt_product_id; // product_id
	public String pt_name; // name
	public String pt_whoCanSelect_AFU; // whoCanSelect_AFU
	public String pt_deadline; // deadline
	public String pt_isPercent; // isPercent
	public double pt_amount; // amount
	public String pt_currency; // currency
	public long   pt_duration_minutes; // duration_minutes
	public String pt_json; // json
    

    public PtRCD_AF() {
	super();
        if ( pt_filtro == null ) { pt_filtro = new PtBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new PtBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new PtBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        PtBean Destino = (PtBean)beanDestino;

	Destino.setPt_sincro( getPt_sincro() ); // sincro
	Destino.setPt_mark( getPt_mark() ); // mark
	Destino.setPt_is_deleted( getPt_is_deleted() ); // is_deleted
	Destino.setPt_author( getPt_author() ); // author
	Destino.setPt_product_id( getPt_product_id() ); // product_id
	Destino.setPt_name( getPt_name() ); // name
	Destino.setPt_whoCanSelect_AFU( getPt_whoCanSelect_AFU() ); // whoCanSelect_AFU
	Destino.setPt_deadline( getPt_deadline() ); // deadline
	Destino.setPt_isPercent( getPt_isPercent() ); // isPercent
	Destino.setPt_amount( getPt_amount() ); // amount
	Destino.setPt_currency( getPt_currency() ); // currency
	Destino.setPt_duration_minutes( getPt_duration_minutes() ); // duration_minutes
	Destino.setPt_json( getPt_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        PtBean Origen = (PtBean)beanOrigen;

	setPt_sincro( Origen.getPt_sincro() ); // sincro
	setPt_mark( Origen.getPt_mark() ); // mark
	setPt_is_deleted( Origen.getPt_is_deleted() ); // is_deleted
	setPt_author( Origen.getPt_author() ); // author
	setPt_product_id( Origen.getPt_product_id() ); // product_id
	setPt_name( Origen.getPt_name() ); // name
	setPt_whoCanSelect_AFU( Origen.getPt_whoCanSelect_AFU() ); // whoCanSelect_AFU
	setPt_deadline( Origen.getPt_deadline() ); // deadline
	setPt_isPercent( Origen.getPt_isPercent() ); // isPercent
	setPt_amount( Origen.getPt_amount() ); // amount
	setPt_currency( Origen.getPt_currency() ); // currency
	setPt_duration_minutes( Origen.getPt_duration_minutes() ); // duration_minutes
	setPt_json( Origen.getPt_json() ); // json
    }
    
    public void copyFrom(PtRCD_AF beanOrigen) {
        PtRCD_AF Origen = beanOrigen;

        setPt_filtro( Origen.getPt_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setPt_sincro( Origen.getPt_sincro() ); // sincro
	setPt_mark( Origen.getPt_mark() ); // mark
	setPt_is_deleted( Origen.getPt_is_deleted() ); // is_deleted
	setPt_author( Origen.getPt_author() ); // author
	setPt_product_id( Origen.getPt_product_id() ); // product_id
	setPt_name( Origen.getPt_name() ); // name
	setPt_whoCanSelect_AFU( Origen.getPt_whoCanSelect_AFU() ); // whoCanSelect_AFU
	setPt_deadline( Origen.getPt_deadline() ); // deadline
	setPt_isPercent( Origen.getPt_isPercent() ); // isPercent
	setPt_amount( Origen.getPt_amount() ); // amount
	setPt_currency( Origen.getPt_currency() ); // currency
	setPt_duration_minutes( Origen.getPt_duration_minutes() ); // duration_minutes
	setPt_json( Origen.getPt_json() ); // json
    }
    
    public PtBeanFiltro getPt_filtro() { return pt_filtro; }
    
    public void setPt_filtro(PtBeanFiltro pt_filtro) { this.pt_filtro = pt_filtro; }

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
	public String getPt_sincro() {return pt_sincro;}
	/** Set sincro*/
	public void setPt_sincro(String pt_sincro) {this.pt_sincro = pt_sincro;}

	/** Get mark*/
	public String getPt_mark() {return pt_mark;}
	/** Set mark*/
	public void setPt_mark(String pt_mark) {this.pt_mark = pt_mark;}

	/** Get is_deleted*/
	public String getPt_is_deleted() {return pt_is_deleted;}
	/** Set is_deleted*/
	public void setPt_is_deleted(String pt_is_deleted) {this.pt_is_deleted = pt_is_deleted;}

	/** Get author*/
	public String getPt_author() {return pt_author;}
	/** Set author*/
	public void setPt_author(String pt_author) {this.pt_author = pt_author;}

	/** Get product_id*/
	public String getPt_product_id() {return pt_product_id;}
	/** Set product_id*/
	public void setPt_product_id(String pt_product_id) {this.pt_product_id = pt_product_id;}

	/** Get name*/
	public String getPt_name() {return pt_name;}
	/** Set name*/
	public void setPt_name(String pt_name) {this.pt_name = pt_name;}

	/** Get whoCanSelect_AFU*/
	public String getPt_whoCanSelect_AFU() {return pt_whoCanSelect_AFU;}
	/** Set whoCanSelect_AFU*/
	public void setPt_whoCanSelect_AFU(String pt_whoCanSelect_AFU) {this.pt_whoCanSelect_AFU = pt_whoCanSelect_AFU;}

	/** Get deadline*/
	public String getPt_deadline() {return pt_deadline;}
	/** Set deadline*/
	public void setPt_deadline(String pt_deadline) {this.pt_deadline = pt_deadline;}

	/** Get isPercent*/
	public String getPt_isPercent() {return pt_isPercent;}
	/** Set isPercent*/
	public void setPt_isPercent(String pt_isPercent) {this.pt_isPercent = pt_isPercent;}

	/** Get amount*/
	public double getPt_amount() {return pt_amount;}
	/** Set amount*/
	public void setPt_amount(double pt_amount) {this.pt_amount = pt_amount;}

	/** Get currency*/
	public String getPt_currency() {return pt_currency;}
	/** Set currency*/
	public void setPt_currency(String pt_currency) {this.pt_currency = pt_currency;}

	/** Get duration_minutes*/
	public long getPt_duration_minutes() {return pt_duration_minutes;}
	/** Set duration_minutes*/
	public void setPt_duration_minutes(long pt_duration_minutes) {this.pt_duration_minutes = pt_duration_minutes;}

	/** Get json*/
	public String getPt_json() {return pt_json;}
	/** Set json*/
	public void setPt_json(String pt_json) {this.pt_json = pt_json;}

}
