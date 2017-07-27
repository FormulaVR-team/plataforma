package com.fvr.ad_rs_reservations.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.ad_rs_reservations.bean.Ad_rsBean;
import com.fvr.ad_rs_reservations.bean.Ad_rsBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class Ad_rsRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public Ad_rsBeanFiltro ad_rs_filtro;
    
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
	public String ad_rs_sincro; // sincro
	public String ad_rs_mark; // mark
	public String ad_rs_is_deleted; // is_deleted
	public String ad_rs_author; // author
	public String ad_rs_reservation_id; // reservation_id
	public String ad_rs_location_id; // location_id
	public String ad_rs_LO_name; // LO_name
	public String ad_rs_user_id; // user_id
	public long   ad_rs_US_country_id; // US_country_id
	public String ad_rs_US_nick; // US_nick
	public String ad_rs_US_avatar; // US_avatar
	public String ad_rs_US_is_admin; // US_is_admin
	public String ad_rs_US_first_name; // US_first_name
	public String ad_rs_US_last_name; // US_last_name
	public String ad_rs_product_id; // product_id
	public String ad_rs_PT_name; // PT_name
	public String ad_rs_PT_deadline; // PT_deadline
	public String ad_rs_PT_isPercent; // PT_isPercent
	public double ad_rs_PT_amount; // PT_amount
	public long   ad_rs_PT_duration_minutes; // PT_duration_minutes
	public long   ad_rs_quantity; // quantity
	public String ad_rs_product_id2; // product_id2
	public String ad_rs_PT_name2; // PT_name2
	public String ad_rs_PT_deadline2; // PT_deadline2
	public String ad_rs_PT_isPercent2; // PT_isPercent2
	public double ad_rs_PT_amount2; // PT_amount2
	public String ad_rs_product_id3; // product_id3
	public String ad_rs_PT_name3; // PT_name3
	public String ad_rs_PT_deadline3; // PT_deadline3
	public String ad_rs_PT_isPercent3; // PT_isPercent3
	public double ad_rs_PT_amount3; // PT_amount3
	public double ad_rs_amount; // amount
	public String ad_rs_currency; // currency
	public String ad_rs_phone; // phone
	public String ad_rs_pay_status; // pay_status
	public String ad_rs_start_date; // start_date
	public String ad_rs_start_time; // start_time
	public long   ad_rs_duration_minutes; // duration_minutes
	public long   ad_rs_places; // places
	public String ad_rs_coupon_id; // coupon_id
	public String ad_rs_executed_at; // executed_at
	public String ad_rs_note; // note
	public String ad_rs_comment; // comment
	public String ad_rs_json; // json
    

    public Ad_rsRCD_AF() {
	super();
        if ( ad_rs_filtro == null ) { ad_rs_filtro = new Ad_rsBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new Ad_rsBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new Ad_rsBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        Ad_rsBean Destino = (Ad_rsBean)beanDestino;

	Destino.setAd_rs_sincro( getAd_rs_sincro() ); // sincro
	Destino.setAd_rs_mark( getAd_rs_mark() ); // mark
	Destino.setAd_rs_is_deleted( getAd_rs_is_deleted() ); // is_deleted
	Destino.setAd_rs_author( getAd_rs_author() ); // author
	Destino.setAd_rs_reservation_id( getAd_rs_reservation_id() ); // reservation_id
	Destino.setAd_rs_location_id( getAd_rs_location_id() ); // location_id
	Destino.setAd_rs_LO_name( getAd_rs_LO_name() ); // LO_name
	Destino.setAd_rs_user_id( getAd_rs_user_id() ); // user_id
	Destino.setAd_rs_US_country_id( getAd_rs_US_country_id() ); // US_country_id
	Destino.setAd_rs_US_nick( getAd_rs_US_nick() ); // US_nick
	Destino.setAd_rs_US_avatar( getAd_rs_US_avatar() ); // US_avatar
	Destino.setAd_rs_US_is_admin( getAd_rs_US_is_admin() ); // US_is_admin
	Destino.setAd_rs_US_first_name( getAd_rs_US_first_name() ); // US_first_name
	Destino.setAd_rs_US_last_name( getAd_rs_US_last_name() ); // US_last_name
	Destino.setAd_rs_product_id( getAd_rs_product_id() ); // product_id
	Destino.setAd_rs_PT_name( getAd_rs_PT_name() ); // PT_name
	Destino.setAd_rs_PT_deadline( getAd_rs_PT_deadline() ); // PT_deadline
	Destino.setAd_rs_PT_isPercent( getAd_rs_PT_isPercent() ); // PT_isPercent
	Destino.setAd_rs_PT_amount( getAd_rs_PT_amount() ); // PT_amount
	Destino.setAd_rs_PT_duration_minutes( getAd_rs_PT_duration_minutes() ); // PT_duration_minutes
	Destino.setAd_rs_quantity( getAd_rs_quantity() ); // quantity
	Destino.setAd_rs_product_id2( getAd_rs_product_id2() ); // product_id2
	Destino.setAd_rs_PT_name2( getAd_rs_PT_name2() ); // PT_name2
	Destino.setAd_rs_PT_deadline2( getAd_rs_PT_deadline2() ); // PT_deadline2
	Destino.setAd_rs_PT_isPercent2( getAd_rs_PT_isPercent2() ); // PT_isPercent2
	Destino.setAd_rs_PT_amount2( getAd_rs_PT_amount2() ); // PT_amount2
	Destino.setAd_rs_product_id3( getAd_rs_product_id3() ); // product_id3
	Destino.setAd_rs_PT_name3( getAd_rs_PT_name3() ); // PT_name3
	Destino.setAd_rs_PT_deadline3( getAd_rs_PT_deadline3() ); // PT_deadline3
	Destino.setAd_rs_PT_isPercent3( getAd_rs_PT_isPercent3() ); // PT_isPercent3
	Destino.setAd_rs_PT_amount3( getAd_rs_PT_amount3() ); // PT_amount3
	Destino.setAd_rs_amount( getAd_rs_amount() ); // amount
	Destino.setAd_rs_currency( getAd_rs_currency() ); // currency
	Destino.setAd_rs_phone( getAd_rs_phone() ); // phone
	Destino.setAd_rs_pay_status( getAd_rs_pay_status() ); // pay_status
	Destino.setAd_rs_start_date( getAd_rs_start_date() ); // start_date
	Destino.setAd_rs_start_time( getAd_rs_start_time() ); // start_time
	Destino.setAd_rs_duration_minutes( getAd_rs_duration_minutes() ); // duration_minutes
	Destino.setAd_rs_places( getAd_rs_places() ); // places
	Destino.setAd_rs_coupon_id( getAd_rs_coupon_id() ); // coupon_id
	Destino.setAd_rs_executed_at( getAd_rs_executed_at() ); // executed_at
	Destino.setAd_rs_note( getAd_rs_note() ); // note
	Destino.setAd_rs_comment( getAd_rs_comment() ); // comment
	Destino.setAd_rs_json( getAd_rs_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        Ad_rsBean Origen = (Ad_rsBean)beanOrigen;

	setAd_rs_sincro( Origen.getAd_rs_sincro() ); // sincro
	setAd_rs_mark( Origen.getAd_rs_mark() ); // mark
	setAd_rs_is_deleted( Origen.getAd_rs_is_deleted() ); // is_deleted
	setAd_rs_author( Origen.getAd_rs_author() ); // author
	setAd_rs_reservation_id( Origen.getAd_rs_reservation_id() ); // reservation_id
	setAd_rs_location_id( Origen.getAd_rs_location_id() ); // location_id
	setAd_rs_LO_name( Origen.getAd_rs_LO_name() ); // LO_name
	setAd_rs_user_id( Origen.getAd_rs_user_id() ); // user_id
	setAd_rs_US_country_id( Origen.getAd_rs_US_country_id() ); // US_country_id
	setAd_rs_US_nick( Origen.getAd_rs_US_nick() ); // US_nick
	setAd_rs_US_avatar( Origen.getAd_rs_US_avatar() ); // US_avatar
	setAd_rs_US_is_admin( Origen.getAd_rs_US_is_admin() ); // US_is_admin
	setAd_rs_US_first_name( Origen.getAd_rs_US_first_name() ); // US_first_name
	setAd_rs_US_last_name( Origen.getAd_rs_US_last_name() ); // US_last_name
	setAd_rs_product_id( Origen.getAd_rs_product_id() ); // product_id
	setAd_rs_PT_name( Origen.getAd_rs_PT_name() ); // PT_name
	setAd_rs_PT_deadline( Origen.getAd_rs_PT_deadline() ); // PT_deadline
	setAd_rs_PT_isPercent( Origen.getAd_rs_PT_isPercent() ); // PT_isPercent
	setAd_rs_PT_amount( Origen.getAd_rs_PT_amount() ); // PT_amount
	setAd_rs_PT_duration_minutes( Origen.getAd_rs_PT_duration_minutes() ); // PT_duration_minutes
	setAd_rs_quantity( Origen.getAd_rs_quantity() ); // quantity
	setAd_rs_product_id2( Origen.getAd_rs_product_id2() ); // product_id2
	setAd_rs_PT_name2( Origen.getAd_rs_PT_name2() ); // PT_name2
	setAd_rs_PT_deadline2( Origen.getAd_rs_PT_deadline2() ); // PT_deadline2
	setAd_rs_PT_isPercent2( Origen.getAd_rs_PT_isPercent2() ); // PT_isPercent2
	setAd_rs_PT_amount2( Origen.getAd_rs_PT_amount2() ); // PT_amount2
	setAd_rs_product_id3( Origen.getAd_rs_product_id3() ); // product_id3
	setAd_rs_PT_name3( Origen.getAd_rs_PT_name3() ); // PT_name3
	setAd_rs_PT_deadline3( Origen.getAd_rs_PT_deadline3() ); // PT_deadline3
	setAd_rs_PT_isPercent3( Origen.getAd_rs_PT_isPercent3() ); // PT_isPercent3
	setAd_rs_PT_amount3( Origen.getAd_rs_PT_amount3() ); // PT_amount3
	setAd_rs_amount( Origen.getAd_rs_amount() ); // amount
	setAd_rs_currency( Origen.getAd_rs_currency() ); // currency
	setAd_rs_phone( Origen.getAd_rs_phone() ); // phone
	setAd_rs_pay_status( Origen.getAd_rs_pay_status() ); // pay_status
	setAd_rs_start_date( Origen.getAd_rs_start_date() ); // start_date
	setAd_rs_start_time( Origen.getAd_rs_start_time() ); // start_time
	setAd_rs_duration_minutes( Origen.getAd_rs_duration_minutes() ); // duration_minutes
	setAd_rs_places( Origen.getAd_rs_places() ); // places
	setAd_rs_coupon_id( Origen.getAd_rs_coupon_id() ); // coupon_id
	setAd_rs_executed_at( Origen.getAd_rs_executed_at() ); // executed_at
	setAd_rs_note( Origen.getAd_rs_note() ); // note
	setAd_rs_comment( Origen.getAd_rs_comment() ); // comment
	setAd_rs_json( Origen.getAd_rs_json() ); // json
    }
    
    public void copyFrom(Ad_rsRCD_AF beanOrigen) {
        Ad_rsRCD_AF Origen = beanOrigen;

        setAd_rs_filtro( Origen.getAd_rs_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setAd_rs_sincro( Origen.getAd_rs_sincro() ); // sincro
	setAd_rs_mark( Origen.getAd_rs_mark() ); // mark
	setAd_rs_is_deleted( Origen.getAd_rs_is_deleted() ); // is_deleted
	setAd_rs_author( Origen.getAd_rs_author() ); // author
	setAd_rs_reservation_id( Origen.getAd_rs_reservation_id() ); // reservation_id
	setAd_rs_location_id( Origen.getAd_rs_location_id() ); // location_id
	setAd_rs_LO_name( Origen.getAd_rs_LO_name() ); // LO_name
	setAd_rs_user_id( Origen.getAd_rs_user_id() ); // user_id
	setAd_rs_US_country_id( Origen.getAd_rs_US_country_id() ); // US_country_id
	setAd_rs_US_nick( Origen.getAd_rs_US_nick() ); // US_nick
	setAd_rs_US_avatar( Origen.getAd_rs_US_avatar() ); // US_avatar
	setAd_rs_US_is_admin( Origen.getAd_rs_US_is_admin() ); // US_is_admin
	setAd_rs_US_first_name( Origen.getAd_rs_US_first_name() ); // US_first_name
	setAd_rs_US_last_name( Origen.getAd_rs_US_last_name() ); // US_last_name
	setAd_rs_product_id( Origen.getAd_rs_product_id() ); // product_id
	setAd_rs_PT_name( Origen.getAd_rs_PT_name() ); // PT_name
	setAd_rs_PT_deadline( Origen.getAd_rs_PT_deadline() ); // PT_deadline
	setAd_rs_PT_isPercent( Origen.getAd_rs_PT_isPercent() ); // PT_isPercent
	setAd_rs_PT_amount( Origen.getAd_rs_PT_amount() ); // PT_amount
	setAd_rs_PT_duration_minutes( Origen.getAd_rs_PT_duration_minutes() ); // PT_duration_minutes
	setAd_rs_quantity( Origen.getAd_rs_quantity() ); // quantity
	setAd_rs_product_id2( Origen.getAd_rs_product_id2() ); // product_id2
	setAd_rs_PT_name2( Origen.getAd_rs_PT_name2() ); // PT_name2
	setAd_rs_PT_deadline2( Origen.getAd_rs_PT_deadline2() ); // PT_deadline2
	setAd_rs_PT_isPercent2( Origen.getAd_rs_PT_isPercent2() ); // PT_isPercent2
	setAd_rs_PT_amount2( Origen.getAd_rs_PT_amount2() ); // PT_amount2
	setAd_rs_product_id3( Origen.getAd_rs_product_id3() ); // product_id3
	setAd_rs_PT_name3( Origen.getAd_rs_PT_name3() ); // PT_name3
	setAd_rs_PT_deadline3( Origen.getAd_rs_PT_deadline3() ); // PT_deadline3
	setAd_rs_PT_isPercent3( Origen.getAd_rs_PT_isPercent3() ); // PT_isPercent3
	setAd_rs_PT_amount3( Origen.getAd_rs_PT_amount3() ); // PT_amount3
	setAd_rs_amount( Origen.getAd_rs_amount() ); // amount
	setAd_rs_currency( Origen.getAd_rs_currency() ); // currency
	setAd_rs_phone( Origen.getAd_rs_phone() ); // phone
	setAd_rs_pay_status( Origen.getAd_rs_pay_status() ); // pay_status
	setAd_rs_start_date( Origen.getAd_rs_start_date() ); // start_date
	setAd_rs_start_time( Origen.getAd_rs_start_time() ); // start_time
	setAd_rs_duration_minutes( Origen.getAd_rs_duration_minutes() ); // duration_minutes
	setAd_rs_places( Origen.getAd_rs_places() ); // places
	setAd_rs_coupon_id( Origen.getAd_rs_coupon_id() ); // coupon_id
	setAd_rs_executed_at( Origen.getAd_rs_executed_at() ); // executed_at
	setAd_rs_note( Origen.getAd_rs_note() ); // note
	setAd_rs_comment( Origen.getAd_rs_comment() ); // comment
	setAd_rs_json( Origen.getAd_rs_json() ); // json
    }
    
    public Ad_rsBeanFiltro getAd_rs_filtro() { return ad_rs_filtro; }
    
    public void setAd_rs_filtro(Ad_rsBeanFiltro ad_rs_filtro) { this.ad_rs_filtro = ad_rs_filtro; }

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
	public String getAd_rs_sincro() {return ad_rs_sincro;}
	/** Set sincro*/
	public void setAd_rs_sincro(String ad_rs_sincro) {this.ad_rs_sincro = ad_rs_sincro;}

	/** Get mark*/
	public String getAd_rs_mark() {return ad_rs_mark;}
	/** Set mark*/
	public void setAd_rs_mark(String ad_rs_mark) {this.ad_rs_mark = ad_rs_mark;}

	/** Get is_deleted*/
	public String getAd_rs_is_deleted() {return ad_rs_is_deleted;}
	/** Set is_deleted*/
	public void setAd_rs_is_deleted(String ad_rs_is_deleted) {this.ad_rs_is_deleted = ad_rs_is_deleted;}

	/** Get author*/
	public String getAd_rs_author() {return ad_rs_author;}
	/** Set author*/
	public void setAd_rs_author(String ad_rs_author) {this.ad_rs_author = ad_rs_author;}

	/** Get reservation_id*/
	public String getAd_rs_reservation_id() {return ad_rs_reservation_id;}
	/** Set reservation_id*/
	public void setAd_rs_reservation_id(String ad_rs_reservation_id) {this.ad_rs_reservation_id = ad_rs_reservation_id;}

	/** Get location_id*/
	public String getAd_rs_location_id() {return ad_rs_location_id;}
	/** Set location_id*/
	public void setAd_rs_location_id(String ad_rs_location_id) {this.ad_rs_location_id = ad_rs_location_id;}

	/** Get LO_name*/
	public String getAd_rs_LO_name() {return ad_rs_LO_name;}
	/** Set LO_name*/
	public void setAd_rs_LO_name(String ad_rs_LO_name) {this.ad_rs_LO_name = ad_rs_LO_name;}

	/** Get user_id*/
	public String getAd_rs_user_id() {return ad_rs_user_id;}
	/** Set user_id*/
	public void setAd_rs_user_id(String ad_rs_user_id) {this.ad_rs_user_id = ad_rs_user_id;}

	/** Get US_country_id*/
	public long getAd_rs_US_country_id() {return ad_rs_US_country_id;}
	/** Set US_country_id*/
	public void setAd_rs_US_country_id(long ad_rs_US_country_id) {this.ad_rs_US_country_id = ad_rs_US_country_id;}

	/** Get US_nick*/
	public String getAd_rs_US_nick() {return ad_rs_US_nick;}
	/** Set US_nick*/
	public void setAd_rs_US_nick(String ad_rs_US_nick) {this.ad_rs_US_nick = ad_rs_US_nick;}

	/** Get US_avatar*/
	public String getAd_rs_US_avatar() {return ad_rs_US_avatar;}
	/** Set US_avatar*/
	public void setAd_rs_US_avatar(String ad_rs_US_avatar) {this.ad_rs_US_avatar = ad_rs_US_avatar;}

	/** Get US_is_admin*/
	public String getAd_rs_US_is_admin() {return ad_rs_US_is_admin;}
	/** Set US_is_admin*/
	public void setAd_rs_US_is_admin(String ad_rs_US_is_admin) {this.ad_rs_US_is_admin = ad_rs_US_is_admin;}

	/** Get US_first_name*/
	public String getAd_rs_US_first_name() {return ad_rs_US_first_name;}
	/** Set US_first_name*/
	public void setAd_rs_US_first_name(String ad_rs_US_first_name) {this.ad_rs_US_first_name = ad_rs_US_first_name;}

	/** Get US_last_name*/
	public String getAd_rs_US_last_name() {return ad_rs_US_last_name;}
	/** Set US_last_name*/
	public void setAd_rs_US_last_name(String ad_rs_US_last_name) {this.ad_rs_US_last_name = ad_rs_US_last_name;}

	/** Get product_id*/
	public String getAd_rs_product_id() {return ad_rs_product_id;}
	/** Set product_id*/
	public void setAd_rs_product_id(String ad_rs_product_id) {this.ad_rs_product_id = ad_rs_product_id;}

	/** Get PT_name*/
	public String getAd_rs_PT_name() {return ad_rs_PT_name;}
	/** Set PT_name*/
	public void setAd_rs_PT_name(String ad_rs_PT_name) {this.ad_rs_PT_name = ad_rs_PT_name;}

	/** Get PT_deadline*/
	public String getAd_rs_PT_deadline() {return ad_rs_PT_deadline;}
	/** Set PT_deadline*/
	public void setAd_rs_PT_deadline(String ad_rs_PT_deadline) {this.ad_rs_PT_deadline = ad_rs_PT_deadline;}

	/** Get PT_isPercent*/
	public String getAd_rs_PT_isPercent() {return ad_rs_PT_isPercent;}
	/** Set PT_isPercent*/
	public void setAd_rs_PT_isPercent(String ad_rs_PT_isPercent) {this.ad_rs_PT_isPercent = ad_rs_PT_isPercent;}

	/** Get PT_amount*/
	public double getAd_rs_PT_amount() {return ad_rs_PT_amount;}
	/** Set PT_amount*/
	public void setAd_rs_PT_amount(double ad_rs_PT_amount) {this.ad_rs_PT_amount = ad_rs_PT_amount;}

	/** Get PT_duration_minutes*/
	public long getAd_rs_PT_duration_minutes() {return ad_rs_PT_duration_minutes;}
	/** Set PT_duration_minutes*/
	public void setAd_rs_PT_duration_minutes(long ad_rs_PT_duration_minutes) {this.ad_rs_PT_duration_minutes = ad_rs_PT_duration_minutes;}

	/** Get quantity*/
	public long getAd_rs_quantity() {return ad_rs_quantity;}
	/** Set quantity*/
	public void setAd_rs_quantity(long ad_rs_quantity) {this.ad_rs_quantity = ad_rs_quantity;}

	/** Get product_id2*/
	public String getAd_rs_product_id2() {return ad_rs_product_id2;}
	/** Set product_id2*/
	public void setAd_rs_product_id2(String ad_rs_product_id2) {this.ad_rs_product_id2 = ad_rs_product_id2;}

	/** Get PT_name2*/
	public String getAd_rs_PT_name2() {return ad_rs_PT_name2;}
	/** Set PT_name2*/
	public void setAd_rs_PT_name2(String ad_rs_PT_name2) {this.ad_rs_PT_name2 = ad_rs_PT_name2;}

	/** Get PT_deadline2*/
	public String getAd_rs_PT_deadline2() {return ad_rs_PT_deadline2;}
	/** Set PT_deadline2*/
	public void setAd_rs_PT_deadline2(String ad_rs_PT_deadline2) {this.ad_rs_PT_deadline2 = ad_rs_PT_deadline2;}

	/** Get PT_isPercent2*/
	public String getAd_rs_PT_isPercent2() {return ad_rs_PT_isPercent2;}
	/** Set PT_isPercent2*/
	public void setAd_rs_PT_isPercent2(String ad_rs_PT_isPercent2) {this.ad_rs_PT_isPercent2 = ad_rs_PT_isPercent2;}

	/** Get PT_amount2*/
	public double getAd_rs_PT_amount2() {return ad_rs_PT_amount2;}
	/** Set PT_amount2*/
	public void setAd_rs_PT_amount2(double ad_rs_PT_amount2) {this.ad_rs_PT_amount2 = ad_rs_PT_amount2;}

	/** Get product_id3*/
	public String getAd_rs_product_id3() {return ad_rs_product_id3;}
	/** Set product_id3*/
	public void setAd_rs_product_id3(String ad_rs_product_id3) {this.ad_rs_product_id3 = ad_rs_product_id3;}

	/** Get PT_name3*/
	public String getAd_rs_PT_name3() {return ad_rs_PT_name3;}
	/** Set PT_name3*/
	public void setAd_rs_PT_name3(String ad_rs_PT_name3) {this.ad_rs_PT_name3 = ad_rs_PT_name3;}

	/** Get PT_deadline3*/
	public String getAd_rs_PT_deadline3() {return ad_rs_PT_deadline3;}
	/** Set PT_deadline3*/
	public void setAd_rs_PT_deadline3(String ad_rs_PT_deadline3) {this.ad_rs_PT_deadline3 = ad_rs_PT_deadline3;}

	/** Get PT_isPercent3*/
	public String getAd_rs_PT_isPercent3() {return ad_rs_PT_isPercent3;}
	/** Set PT_isPercent3*/
	public void setAd_rs_PT_isPercent3(String ad_rs_PT_isPercent3) {this.ad_rs_PT_isPercent3 = ad_rs_PT_isPercent3;}

	/** Get PT_amount3*/
	public double getAd_rs_PT_amount3() {return ad_rs_PT_amount3;}
	/** Set PT_amount3*/
	public void setAd_rs_PT_amount3(double ad_rs_PT_amount3) {this.ad_rs_PT_amount3 = ad_rs_PT_amount3;}

	/** Get amount*/
	public double getAd_rs_amount() {return ad_rs_amount;}
	/** Set amount*/
	public void setAd_rs_amount(double ad_rs_amount) {this.ad_rs_amount = ad_rs_amount;}

	/** Get currency*/
	public String getAd_rs_currency() {return ad_rs_currency;}
	/** Set currency*/
	public void setAd_rs_currency(String ad_rs_currency) {this.ad_rs_currency = ad_rs_currency;}

	/** Get phone*/
	public String getAd_rs_phone() {return ad_rs_phone;}
	/** Set phone*/
	public void setAd_rs_phone(String ad_rs_phone) {this.ad_rs_phone = ad_rs_phone;}

	/** Get pay_status*/
	public String getAd_rs_pay_status() {return ad_rs_pay_status;}
	/** Set pay_status*/
	public void setAd_rs_pay_status(String ad_rs_pay_status) {this.ad_rs_pay_status = ad_rs_pay_status;}

	/** Get start_date*/
	public String getAd_rs_start_date() {return ad_rs_start_date;}
	/** Set start_date*/
	public void setAd_rs_start_date(String ad_rs_start_date) {this.ad_rs_start_date = ad_rs_start_date;}

	/** Get start_time*/
	public String getAd_rs_start_time() {return ad_rs_start_time;}
	/** Set start_time*/
	public void setAd_rs_start_time(String ad_rs_start_time) {this.ad_rs_start_time = ad_rs_start_time;}

	/** Get duration_minutes*/
	public long getAd_rs_duration_minutes() {return ad_rs_duration_minutes;}
	/** Set duration_minutes*/
	public void setAd_rs_duration_minutes(long ad_rs_duration_minutes) {this.ad_rs_duration_minutes = ad_rs_duration_minutes;}

	/** Get places*/
	public long getAd_rs_places() {return ad_rs_places;}
	/** Set places*/
	public void setAd_rs_places(long ad_rs_places) {this.ad_rs_places = ad_rs_places;}

	/** Get coupon_id*/
	public String getAd_rs_coupon_id() {return ad_rs_coupon_id;}
	/** Set coupon_id*/
	public void setAd_rs_coupon_id(String ad_rs_coupon_id) {this.ad_rs_coupon_id = ad_rs_coupon_id;}

	/** Get executed_at*/
	public String getAd_rs_executed_at() {return ad_rs_executed_at;}
	/** Set executed_at*/
	public void setAd_rs_executed_at(String ad_rs_executed_at) {this.ad_rs_executed_at = ad_rs_executed_at;}

	/** Get note*/
	public String getAd_rs_note() {return ad_rs_note;}
	/** Set note*/
	public void setAd_rs_note(String ad_rs_note) {this.ad_rs_note = ad_rs_note;}

	/** Get comment*/
	public String getAd_rs_comment() {return ad_rs_comment;}
	/** Set comment*/
	public void setAd_rs_comment(String ad_rs_comment) {this.ad_rs_comment = ad_rs_comment;}

	/** Get json*/
	public String getAd_rs_json() {return ad_rs_json;}
	/** Set json*/
	public void setAd_rs_json(String ad_rs_json) {this.ad_rs_json = ad_rs_json;}

}
