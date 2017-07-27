package com.fvr.pr_promos.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.pr_promos.bean.PrBean;
import com.fvr.pr_promos.bean.PrBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PrRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public PrBeanFiltro pr_filtro;
    
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
	public String pr_sincro; // sincro
	public String pr_mark; // mark
	public String pr_is_deleted; // is_deleted
	public String pr_author; // author
	public String pr_location_id; // location_id
	public String pr_LO_name; // LO_name
	public String pr_product_id; // product_id
	public String pr_PT_name; // PT_name
	public String pr_product_id_promo; // product_id_promo
	public String pr_PT_name_promo; // PT_name_promo
	public String pr_deadline; // deadline
	public long   pr_min_quantity; // min_quantity
    

    public PrRCD_AF() {
	super();
        if ( pr_filtro == null ) { pr_filtro = new PrBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new PrBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new PrBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        PrBean Destino = (PrBean)beanDestino;

	Destino.setPr_sincro( getPr_sincro() ); // sincro
	Destino.setPr_mark( getPr_mark() ); // mark
	Destino.setPr_is_deleted( getPr_is_deleted() ); // is_deleted
	Destino.setPr_author( getPr_author() ); // author
	Destino.setPr_location_id( getPr_location_id() ); // location_id
	Destino.setPr_LO_name( getPr_LO_name() ); // LO_name
	Destino.setPr_product_id( getPr_product_id() ); // product_id
	Destino.setPr_PT_name( getPr_PT_name() ); // PT_name
	Destino.setPr_product_id_promo( getPr_product_id_promo() ); // product_id_promo
	Destino.setPr_PT_name_promo( getPr_PT_name_promo() ); // PT_name_promo
	Destino.setPr_deadline( getPr_deadline() ); // deadline
	Destino.setPr_min_quantity( getPr_min_quantity() ); // min_quantity
    }
    
    public void copyFrom(StBean beanOrigen) {
        PrBean Origen = (PrBean)beanOrigen;

	setPr_sincro( Origen.getPr_sincro() ); // sincro
	setPr_mark( Origen.getPr_mark() ); // mark
	setPr_is_deleted( Origen.getPr_is_deleted() ); // is_deleted
	setPr_author( Origen.getPr_author() ); // author
	setPr_location_id( Origen.getPr_location_id() ); // location_id
	setPr_LO_name( Origen.getPr_LO_name() ); // LO_name
	setPr_product_id( Origen.getPr_product_id() ); // product_id
	setPr_PT_name( Origen.getPr_PT_name() ); // PT_name
	setPr_product_id_promo( Origen.getPr_product_id_promo() ); // product_id_promo
	setPr_PT_name_promo( Origen.getPr_PT_name_promo() ); // PT_name_promo
	setPr_deadline( Origen.getPr_deadline() ); // deadline
	setPr_min_quantity( Origen.getPr_min_quantity() ); // min_quantity
    }
    
    public void copyFrom(PrRCD_AF beanOrigen) {
        PrRCD_AF Origen = beanOrigen;

        setPr_filtro( Origen.getPr_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setPr_sincro( Origen.getPr_sincro() ); // sincro
	setPr_mark( Origen.getPr_mark() ); // mark
	setPr_is_deleted( Origen.getPr_is_deleted() ); // is_deleted
	setPr_author( Origen.getPr_author() ); // author
	setPr_location_id( Origen.getPr_location_id() ); // location_id
	setPr_LO_name( Origen.getPr_LO_name() ); // LO_name
	setPr_product_id( Origen.getPr_product_id() ); // product_id
	setPr_PT_name( Origen.getPr_PT_name() ); // PT_name
	setPr_product_id_promo( Origen.getPr_product_id_promo() ); // product_id_promo
	setPr_PT_name_promo( Origen.getPr_PT_name_promo() ); // PT_name_promo
	setPr_deadline( Origen.getPr_deadline() ); // deadline
	setPr_min_quantity( Origen.getPr_min_quantity() ); // min_quantity
    }
    
    public PrBeanFiltro getPr_filtro() { return pr_filtro; }
    
    public void setPr_filtro(PrBeanFiltro pr_filtro) { this.pr_filtro = pr_filtro; }

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
	public String getPr_sincro() {return pr_sincro;}
	/** Set sincro*/
	public void setPr_sincro(String pr_sincro) {this.pr_sincro = pr_sincro;}

	/** Get mark*/
	public String getPr_mark() {return pr_mark;}
	/** Set mark*/
	public void setPr_mark(String pr_mark) {this.pr_mark = pr_mark;}

	/** Get is_deleted*/
	public String getPr_is_deleted() {return pr_is_deleted;}
	/** Set is_deleted*/
	public void setPr_is_deleted(String pr_is_deleted) {this.pr_is_deleted = pr_is_deleted;}

	/** Get author*/
	public String getPr_author() {return pr_author;}
	/** Set author*/
	public void setPr_author(String pr_author) {this.pr_author = pr_author;}

	/** Get location_id*/
	public String getPr_location_id() {return pr_location_id;}
	/** Set location_id*/
	public void setPr_location_id(String pr_location_id) {this.pr_location_id = pr_location_id;}

	/** Get LO_name*/
	public String getPr_LO_name() {return pr_LO_name;}
	/** Set LO_name*/
	public void setPr_LO_name(String pr_LO_name) {this.pr_LO_name = pr_LO_name;}

	/** Get product_id*/
	public String getPr_product_id() {return pr_product_id;}
	/** Set product_id*/
	public void setPr_product_id(String pr_product_id) {this.pr_product_id = pr_product_id;}

	/** Get PT_name*/
	public String getPr_PT_name() {return pr_PT_name;}
	/** Set PT_name*/
	public void setPr_PT_name(String pr_PT_name) {this.pr_PT_name = pr_PT_name;}

	/** Get product_id_promo*/
	public String getPr_product_id_promo() {return pr_product_id_promo;}
	/** Set product_id_promo*/
	public void setPr_product_id_promo(String pr_product_id_promo) {this.pr_product_id_promo = pr_product_id_promo;}

	/** Get PT_name_promo*/
	public String getPr_PT_name_promo() {return pr_PT_name_promo;}
	/** Set PT_name_promo*/
	public void setPr_PT_name_promo(String pr_PT_name_promo) {this.pr_PT_name_promo = pr_PT_name_promo;}

	/** Get deadline*/
	public String getPr_deadline() {return pr_deadline;}
	/** Set deadline*/
	public void setPr_deadline(String pr_deadline) {this.pr_deadline = pr_deadline;}

	/** Get min_quantity*/
	public long getPr_min_quantity() {return pr_min_quantity;}
	/** Set min_quantity*/
	public void setPr_min_quantity(long pr_min_quantity) {this.pr_min_quantity = pr_min_quantity;}

}
