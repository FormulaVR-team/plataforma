package com.fvr.pm_promosManuales.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.pm_promosManuales.bean.PmBean;
import com.fvr.pm_promosManuales.bean.PmBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PmRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public PmBeanFiltro pm_filtro;
    
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
	public String pm_sincro; // sincro
	public String pm_mark; // mark
	public String pm_is_deleted; // is_deleted
	public String pm_author; // author
	public String pm_coupon_id; // coupon_id
	public String pm_name; // name
	public long   pm_uses_per_user; // uses_per_user
	public long   pm_places; // places
	public String pm_location_id; // location_id
	public String pm_LO_name; // LO_name
	public String pm_product_id; // product_id
	public String pm_PT_name; // PT_name
	public String pm_PT_whoCanSelect_AFU; // PT_whoCanSelect_AFU
	public String pm_PT_deadline; // PT_deadline
	public String pm_product_id_promo; // product_id_promo
	public String pm_deadline; // deadline
	public String pm_json; // json
    

    public PmRCD_AF() {
	super();
        if ( pm_filtro == null ) { pm_filtro = new PmBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new PmBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new PmBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        PmBean Destino = (PmBean)beanDestino;

	Destino.setPm_sincro( getPm_sincro() ); // sincro
	Destino.setPm_mark( getPm_mark() ); // mark
	Destino.setPm_is_deleted( getPm_is_deleted() ); // is_deleted
	Destino.setPm_author( getPm_author() ); // author
	Destino.setPm_coupon_id( getPm_coupon_id() ); // coupon_id
	Destino.setPm_name( getPm_name() ); // name
	Destino.setPm_uses_per_user( getPm_uses_per_user() ); // uses_per_user
	Destino.setPm_places( getPm_places() ); // places
	Destino.setPm_location_id( getPm_location_id() ); // location_id
	Destino.setPm_LO_name( getPm_LO_name() ); // LO_name
	Destino.setPm_product_id( getPm_product_id() ); // product_id
	Destino.setPm_PT_name( getPm_PT_name() ); // PT_name
	Destino.setPm_PT_whoCanSelect_AFU( getPm_PT_whoCanSelect_AFU() ); // PT_whoCanSelect_AFU
	Destino.setPm_PT_deadline( getPm_PT_deadline() ); // PT_deadline
	Destino.setPm_product_id_promo( getPm_product_id_promo() ); // product_id_promo
	Destino.setPm_deadline( getPm_deadline() ); // deadline
	Destino.setPm_json( getPm_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        PmBean Origen = (PmBean)beanOrigen;

	setPm_sincro( Origen.getPm_sincro() ); // sincro
	setPm_mark( Origen.getPm_mark() ); // mark
	setPm_is_deleted( Origen.getPm_is_deleted() ); // is_deleted
	setPm_author( Origen.getPm_author() ); // author
	setPm_coupon_id( Origen.getPm_coupon_id() ); // coupon_id
	setPm_name( Origen.getPm_name() ); // name
	setPm_uses_per_user( Origen.getPm_uses_per_user() ); // uses_per_user
	setPm_places( Origen.getPm_places() ); // places
	setPm_location_id( Origen.getPm_location_id() ); // location_id
	setPm_LO_name( Origen.getPm_LO_name() ); // LO_name
	setPm_product_id( Origen.getPm_product_id() ); // product_id
	setPm_PT_name( Origen.getPm_PT_name() ); // PT_name
	setPm_PT_whoCanSelect_AFU( Origen.getPm_PT_whoCanSelect_AFU() ); // PT_whoCanSelect_AFU
	setPm_PT_deadline( Origen.getPm_PT_deadline() ); // PT_deadline
	setPm_product_id_promo( Origen.getPm_product_id_promo() ); // product_id_promo
	setPm_deadline( Origen.getPm_deadline() ); // deadline
	setPm_json( Origen.getPm_json() ); // json
    }
    
    public void copyFrom(PmRCD_AF beanOrigen) {
        PmRCD_AF Origen = beanOrigen;

        setPm_filtro( Origen.getPm_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setPm_sincro( Origen.getPm_sincro() ); // sincro
	setPm_mark( Origen.getPm_mark() ); // mark
	setPm_is_deleted( Origen.getPm_is_deleted() ); // is_deleted
	setPm_author( Origen.getPm_author() ); // author
	setPm_coupon_id( Origen.getPm_coupon_id() ); // coupon_id
	setPm_name( Origen.getPm_name() ); // name
	setPm_uses_per_user( Origen.getPm_uses_per_user() ); // uses_per_user
	setPm_places( Origen.getPm_places() ); // places
	setPm_location_id( Origen.getPm_location_id() ); // location_id
	setPm_LO_name( Origen.getPm_LO_name() ); // LO_name
	setPm_product_id( Origen.getPm_product_id() ); // product_id
	setPm_PT_name( Origen.getPm_PT_name() ); // PT_name
	setPm_PT_whoCanSelect_AFU( Origen.getPm_PT_whoCanSelect_AFU() ); // PT_whoCanSelect_AFU
	setPm_PT_deadline( Origen.getPm_PT_deadline() ); // PT_deadline
	setPm_product_id_promo( Origen.getPm_product_id_promo() ); // product_id_promo
	setPm_deadline( Origen.getPm_deadline() ); // deadline
	setPm_json( Origen.getPm_json() ); // json
    }
    
    public PmBeanFiltro getPm_filtro() { return pm_filtro; }
    
    public void setPm_filtro(PmBeanFiltro pm_filtro) { this.pm_filtro = pm_filtro; }

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
	public String getPm_sincro() {return pm_sincro;}
	/** Set sincro*/
	public void setPm_sincro(String pm_sincro) {this.pm_sincro = pm_sincro;}

	/** Get mark*/
	public String getPm_mark() {return pm_mark;}
	/** Set mark*/
	public void setPm_mark(String pm_mark) {this.pm_mark = pm_mark;}

	/** Get is_deleted*/
	public String getPm_is_deleted() {return pm_is_deleted;}
	/** Set is_deleted*/
	public void setPm_is_deleted(String pm_is_deleted) {this.pm_is_deleted = pm_is_deleted;}

	/** Get author*/
	public String getPm_author() {return pm_author;}
	/** Set author*/
	public void setPm_author(String pm_author) {this.pm_author = pm_author;}

	/** Get coupon_id*/
	public String getPm_coupon_id() {return pm_coupon_id;}
	/** Set coupon_id*/
	public void setPm_coupon_id(String pm_coupon_id) {this.pm_coupon_id = pm_coupon_id;}

	/** Get name*/
	public String getPm_name() {return pm_name;}
	/** Set name*/
	public void setPm_name(String pm_name) {this.pm_name = pm_name;}

	/** Get uses_per_user*/
	public long getPm_uses_per_user() {return pm_uses_per_user;}
	/** Set uses_per_user*/
	public void setPm_uses_per_user(long pm_uses_per_user) {this.pm_uses_per_user = pm_uses_per_user;}

	/** Get places*/
	public long getPm_places() {return pm_places;}
	/** Set places*/
	public void setPm_places(long pm_places) {this.pm_places = pm_places;}

	/** Get location_id*/
	public String getPm_location_id() {return pm_location_id;}
	/** Set location_id*/
	public void setPm_location_id(String pm_location_id) {this.pm_location_id = pm_location_id;}

	/** Get LO_name*/
	public String getPm_LO_name() {return pm_LO_name;}
	/** Set LO_name*/
	public void setPm_LO_name(String pm_LO_name) {this.pm_LO_name = pm_LO_name;}

	/** Get product_id*/
	public String getPm_product_id() {return pm_product_id;}
	/** Set product_id*/
	public void setPm_product_id(String pm_product_id) {this.pm_product_id = pm_product_id;}

	/** Get PT_name*/
	public String getPm_PT_name() {return pm_PT_name;}
	/** Set PT_name*/
	public void setPm_PT_name(String pm_PT_name) {this.pm_PT_name = pm_PT_name;}

	/** Get PT_whoCanSelect_AFU*/
	public String getPm_PT_whoCanSelect_AFU() {return pm_PT_whoCanSelect_AFU;}
	/** Set PT_whoCanSelect_AFU*/
	public void setPm_PT_whoCanSelect_AFU(String pm_PT_whoCanSelect_AFU) {this.pm_PT_whoCanSelect_AFU = pm_PT_whoCanSelect_AFU;}

	/** Get PT_deadline*/
	public String getPm_PT_deadline() {return pm_PT_deadline;}
	/** Set PT_deadline*/
	public void setPm_PT_deadline(String pm_PT_deadline) {this.pm_PT_deadline = pm_PT_deadline;}

	/** Get product_id_promo*/
	public String getPm_product_id_promo() {return pm_product_id_promo;}
	/** Set product_id_promo*/
	public void setPm_product_id_promo(String pm_product_id_promo) {this.pm_product_id_promo = pm_product_id_promo;}

	/** Get deadline*/
	public String getPm_deadline() {return pm_deadline;}
	/** Set deadline*/
	public void setPm_deadline(String pm_deadline) {this.pm_deadline = pm_deadline;}

	/** Get json*/
	public String getPm_json() {return pm_json;}
	/** Set json*/
	public void setPm_json(String pm_json) {this.pm_json = pm_json;}

}
