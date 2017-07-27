package com.fvr.rs_reservations.forms;

import com.fvr._comun.ContextVars;
import com.fvr._comun.StBean;
import com.fvr.rs_reservations.bean.RsBean;
import com.fvr.rs_reservations.bean.RsBeanFiltro;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class RsRCD_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class..."

    // Para multiregistro:
    public RsBeanFiltro rs_filtro;
    
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
	public String rs_sincro; // sincro
	public String rs_mark; // mark
	public String rs_is_deleted; // is_deleted
	public String rs_author; // author
	public String rs_reservation_id; // reservation_id
	public String rs_location_id; // location_id
	public String rs_LO_name; // LO_name
	public String rs_user_id; // user_id
	public long   rs_US_country_id; // US_country_id
	public String rs_US_nick; // US_nick
	public String rs_US_avatar; // US_avatar
	public String rs_US_is_admin; // US_is_admin
	public String rs_US_first_name; // US_first_name
	public String rs_US_last_name; // US_last_name
	public String rs_product_id; // product_id
	public String rs_PT_name; // PT_name
	public String rs_PT_deadline; // PT_deadline
	public String rs_PT_isPercent; // PT_isPercent
	public double rs_PT_amount; // PT_amount
	public long   rs_PT_duration_minutes; // PT_duration_minutes
	public long   rs_quantity; // quantity
	public String rs_product_id2; // product_id2
	public String rs_PT_name2; // PT_name2
	public String rs_PT_deadline2; // PT_deadline2
	public String rs_PT_isPercent2; // PT_isPercent2
	public double rs_PT_amount2; // PT_amount2
	public String rs_product_id3; // product_id3
	public String rs_PT_name3; // PT_name3
	public String rs_PT_deadline3; // PT_deadline3
	public String rs_PT_isPercent3; // PT_isPercent3
	public double rs_PT_amount3; // PT_amount3
	public double rs_amount; // amount
	public String rs_currency; // currency
	public String rs_phone; // phone
	public String rs_pay_status; // pay_status
	public String rs_start_date; // start_date
	public String rs_start_time; // start_time
	public long   rs_duration_minutes; // duration_minutes
	public long   rs_places; // places
	public String rs_coupon_id; // coupon_id
	public String rs_executed_at; // executed_at
	public String rs_note; // note
	public String rs_comment; // comment
	public String rs_json; // json
    

    public RsRCD_AF() {
	super();
        if ( rs_filtro == null ) { rs_filtro = new RsBeanFiltro(null); }
        if (this.getGrid() == null) {
            setGrid( new RsBean[50]  );
            for (int i=0; i<50; i++) {
                grid[i] = new RsBean();
            }
        }
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public void copyTo(StBean beanDestino) {
        RsBean Destino = (RsBean)beanDestino;

	Destino.setRs_sincro( getRs_sincro() ); // sincro
	Destino.setRs_mark( getRs_mark() ); // mark
	Destino.setRs_is_deleted( getRs_is_deleted() ); // is_deleted
	Destino.setRs_author( getRs_author() ); // author
	Destino.setRs_reservation_id( getRs_reservation_id() ); // reservation_id
	Destino.setRs_location_id( getRs_location_id() ); // location_id
	Destino.setRs_LO_name( getRs_LO_name() ); // LO_name
	Destino.setRs_user_id( getRs_user_id() ); // user_id
	Destino.setRs_US_country_id( getRs_US_country_id() ); // US_country_id
	Destino.setRs_US_nick( getRs_US_nick() ); // US_nick
	Destino.setRs_US_avatar( getRs_US_avatar() ); // US_avatar
	Destino.setRs_US_is_admin( getRs_US_is_admin() ); // US_is_admin
	Destino.setRs_US_first_name( getRs_US_first_name() ); // US_first_name
	Destino.setRs_US_last_name( getRs_US_last_name() ); // US_last_name
	Destino.setRs_product_id( getRs_product_id() ); // product_id
	Destino.setRs_PT_name( getRs_PT_name() ); // PT_name
	Destino.setRs_PT_deadline( getRs_PT_deadline() ); // PT_deadline
	Destino.setRs_PT_isPercent( getRs_PT_isPercent() ); // PT_isPercent
	Destino.setRs_PT_amount( getRs_PT_amount() ); // PT_amount
	Destino.setRs_PT_duration_minutes( getRs_PT_duration_minutes() ); // PT_duration_minutes
	Destino.setRs_quantity( getRs_quantity() ); // quantity
	Destino.setRs_product_id2( getRs_product_id2() ); // product_id2
	Destino.setRs_PT_name2( getRs_PT_name2() ); // PT_name2
	Destino.setRs_PT_deadline2( getRs_PT_deadline2() ); // PT_deadline2
	Destino.setRs_PT_isPercent2( getRs_PT_isPercent2() ); // PT_isPercent2
	Destino.setRs_PT_amount2( getRs_PT_amount2() ); // PT_amount2
	Destino.setRs_product_id3( getRs_product_id3() ); // product_id3
	Destino.setRs_PT_name3( getRs_PT_name3() ); // PT_name3
	Destino.setRs_PT_deadline3( getRs_PT_deadline3() ); // PT_deadline3
	Destino.setRs_PT_isPercent3( getRs_PT_isPercent3() ); // PT_isPercent3
	Destino.setRs_PT_amount3( getRs_PT_amount3() ); // PT_amount3
	Destino.setRs_amount( getRs_amount() ); // amount
	Destino.setRs_currency( getRs_currency() ); // currency
	Destino.setRs_phone( getRs_phone() ); // phone
	Destino.setRs_pay_status( getRs_pay_status() ); // pay_status
	Destino.setRs_start_date( getRs_start_date() ); // start_date
	Destino.setRs_start_time( getRs_start_time() ); // start_time
	Destino.setRs_duration_minutes( getRs_duration_minutes() ); // duration_minutes
	Destino.setRs_places( getRs_places() ); // places
	Destino.setRs_coupon_id( getRs_coupon_id() ); // coupon_id
	Destino.setRs_executed_at( getRs_executed_at() ); // executed_at
	Destino.setRs_note( getRs_note() ); // note
	Destino.setRs_comment( getRs_comment() ); // comment
	Destino.setRs_json( getRs_json() ); // json
    }
    
    public void copyFrom(StBean beanOrigen) {
        RsBean Origen = (RsBean)beanOrigen;

	setRs_sincro( Origen.getRs_sincro() ); // sincro
	setRs_mark( Origen.getRs_mark() ); // mark
	setRs_is_deleted( Origen.getRs_is_deleted() ); // is_deleted
	setRs_author( Origen.getRs_author() ); // author
	setRs_reservation_id( Origen.getRs_reservation_id() ); // reservation_id
	setRs_location_id( Origen.getRs_location_id() ); // location_id
	setRs_LO_name( Origen.getRs_LO_name() ); // LO_name
	setRs_user_id( Origen.getRs_user_id() ); // user_id
	setRs_US_country_id( Origen.getRs_US_country_id() ); // US_country_id
	setRs_US_nick( Origen.getRs_US_nick() ); // US_nick
	setRs_US_avatar( Origen.getRs_US_avatar() ); // US_avatar
	setRs_US_is_admin( Origen.getRs_US_is_admin() ); // US_is_admin
	setRs_US_first_name( Origen.getRs_US_first_name() ); // US_first_name
	setRs_US_last_name( Origen.getRs_US_last_name() ); // US_last_name
	setRs_product_id( Origen.getRs_product_id() ); // product_id
	setRs_PT_name( Origen.getRs_PT_name() ); // PT_name
	setRs_PT_deadline( Origen.getRs_PT_deadline() ); // PT_deadline
	setRs_PT_isPercent( Origen.getRs_PT_isPercent() ); // PT_isPercent
	setRs_PT_amount( Origen.getRs_PT_amount() ); // PT_amount
	setRs_PT_duration_minutes( Origen.getRs_PT_duration_minutes() ); // PT_duration_minutes
	setRs_quantity( Origen.getRs_quantity() ); // quantity
	setRs_product_id2( Origen.getRs_product_id2() ); // product_id2
	setRs_PT_name2( Origen.getRs_PT_name2() ); // PT_name2
	setRs_PT_deadline2( Origen.getRs_PT_deadline2() ); // PT_deadline2
	setRs_PT_isPercent2( Origen.getRs_PT_isPercent2() ); // PT_isPercent2
	setRs_PT_amount2( Origen.getRs_PT_amount2() ); // PT_amount2
	setRs_product_id3( Origen.getRs_product_id3() ); // product_id3
	setRs_PT_name3( Origen.getRs_PT_name3() ); // PT_name3
	setRs_PT_deadline3( Origen.getRs_PT_deadline3() ); // PT_deadline3
	setRs_PT_isPercent3( Origen.getRs_PT_isPercent3() ); // PT_isPercent3
	setRs_PT_amount3( Origen.getRs_PT_amount3() ); // PT_amount3
	setRs_amount( Origen.getRs_amount() ); // amount
	setRs_currency( Origen.getRs_currency() ); // currency
	setRs_phone( Origen.getRs_phone() ); // phone
	setRs_pay_status( Origen.getRs_pay_status() ); // pay_status
	setRs_start_date( Origen.getRs_start_date() ); // start_date
	setRs_start_time( Origen.getRs_start_time() ); // start_time
	setRs_duration_minutes( Origen.getRs_duration_minutes() ); // duration_minutes
	setRs_places( Origen.getRs_places() ); // places
	setRs_coupon_id( Origen.getRs_coupon_id() ); // coupon_id
	setRs_executed_at( Origen.getRs_executed_at() ); // executed_at
	setRs_note( Origen.getRs_note() ); // note
	setRs_comment( Origen.getRs_comment() ); // comment
	setRs_json( Origen.getRs_json() ); // json
    }
    
    public void copyFrom(RsRCD_AF beanOrigen) {
        RsRCD_AF Origen = beanOrigen;

        setRs_filtro( Origen.getRs_filtro() );

        setRetFormulario( Origen.getRetFormulario()  );
        setRetElemento( Origen.getRetElemento()  );
        setValorInicial(Origen.getValorInicial());
        
        setGrid( Origen.getGrid() );
        setClavesMarcadas( Origen.getClavesMarcadas() );
        setFilaInicioGrid( Origen.getFilaInicioGrid() );
        setFilasGrid( Origen.getFilasGrid() );
        setOpcionPantalla( Origen.getOpcionPantalla() );
        setOpcionJSMenu( Origen.getOpcionJSMenu() );

	setRs_sincro( Origen.getRs_sincro() ); // sincro
	setRs_mark( Origen.getRs_mark() ); // mark
	setRs_is_deleted( Origen.getRs_is_deleted() ); // is_deleted
	setRs_author( Origen.getRs_author() ); // author
	setRs_reservation_id( Origen.getRs_reservation_id() ); // reservation_id
	setRs_location_id( Origen.getRs_location_id() ); // location_id
	setRs_LO_name( Origen.getRs_LO_name() ); // LO_name
	setRs_user_id( Origen.getRs_user_id() ); // user_id
	setRs_US_country_id( Origen.getRs_US_country_id() ); // US_country_id
	setRs_US_nick( Origen.getRs_US_nick() ); // US_nick
	setRs_US_avatar( Origen.getRs_US_avatar() ); // US_avatar
	setRs_US_is_admin( Origen.getRs_US_is_admin() ); // US_is_admin
	setRs_US_first_name( Origen.getRs_US_first_name() ); // US_first_name
	setRs_US_last_name( Origen.getRs_US_last_name() ); // US_last_name
	setRs_product_id( Origen.getRs_product_id() ); // product_id
	setRs_PT_name( Origen.getRs_PT_name() ); // PT_name
	setRs_PT_deadline( Origen.getRs_PT_deadline() ); // PT_deadline
	setRs_PT_isPercent( Origen.getRs_PT_isPercent() ); // PT_isPercent
	setRs_PT_amount( Origen.getRs_PT_amount() ); // PT_amount
	setRs_PT_duration_minutes( Origen.getRs_PT_duration_minutes() ); // PT_duration_minutes
	setRs_quantity( Origen.getRs_quantity() ); // quantity
	setRs_product_id2( Origen.getRs_product_id2() ); // product_id2
	setRs_PT_name2( Origen.getRs_PT_name2() ); // PT_name2
	setRs_PT_deadline2( Origen.getRs_PT_deadline2() ); // PT_deadline2
	setRs_PT_isPercent2( Origen.getRs_PT_isPercent2() ); // PT_isPercent2
	setRs_PT_amount2( Origen.getRs_PT_amount2() ); // PT_amount2
	setRs_product_id3( Origen.getRs_product_id3() ); // product_id3
	setRs_PT_name3( Origen.getRs_PT_name3() ); // PT_name3
	setRs_PT_deadline3( Origen.getRs_PT_deadline3() ); // PT_deadline3
	setRs_PT_isPercent3( Origen.getRs_PT_isPercent3() ); // PT_isPercent3
	setRs_PT_amount3( Origen.getRs_PT_amount3() ); // PT_amount3
	setRs_amount( Origen.getRs_amount() ); // amount
	setRs_currency( Origen.getRs_currency() ); // currency
	setRs_phone( Origen.getRs_phone() ); // phone
	setRs_pay_status( Origen.getRs_pay_status() ); // pay_status
	setRs_start_date( Origen.getRs_start_date() ); // start_date
	setRs_start_time( Origen.getRs_start_time() ); // start_time
	setRs_duration_minutes( Origen.getRs_duration_minutes() ); // duration_minutes
	setRs_places( Origen.getRs_places() ); // places
	setRs_coupon_id( Origen.getRs_coupon_id() ); // coupon_id
	setRs_executed_at( Origen.getRs_executed_at() ); // executed_at
	setRs_note( Origen.getRs_note() ); // note
	setRs_comment( Origen.getRs_comment() ); // comment
	setRs_json( Origen.getRs_json() ); // json
    }
    
    public RsBeanFiltro getRs_filtro() { return rs_filtro; }
    
    public void setRs_filtro(RsBeanFiltro rs_filtro) { this.rs_filtro = rs_filtro; }

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
	public String getRs_sincro() {return rs_sincro;}
	/** Set sincro*/
	public void setRs_sincro(String rs_sincro) {this.rs_sincro = rs_sincro;}

	/** Get mark*/
	public String getRs_mark() {return rs_mark;}
	/** Set mark*/
	public void setRs_mark(String rs_mark) {this.rs_mark = rs_mark;}

	/** Get is_deleted*/
	public String getRs_is_deleted() {return rs_is_deleted;}
	/** Set is_deleted*/
	public void setRs_is_deleted(String rs_is_deleted) {this.rs_is_deleted = rs_is_deleted;}

	/** Get author*/
	public String getRs_author() {return rs_author;}
	/** Set author*/
	public void setRs_author(String rs_author) {this.rs_author = rs_author;}

	/** Get reservation_id*/
	public String getRs_reservation_id() {return rs_reservation_id;}
	/** Set reservation_id*/
	public void setRs_reservation_id(String rs_reservation_id) {this.rs_reservation_id = rs_reservation_id;}

	/** Get location_id*/
	public String getRs_location_id() {return rs_location_id;}
	/** Set location_id*/
	public void setRs_location_id(String rs_location_id) {this.rs_location_id = rs_location_id;}

	/** Get LO_name*/
	public String getRs_LO_name() {return rs_LO_name;}
	/** Set LO_name*/
	public void setRs_LO_name(String rs_LO_name) {this.rs_LO_name = rs_LO_name;}

	/** Get user_id*/
	public String getRs_user_id() {return rs_user_id;}
	/** Set user_id*/
	public void setRs_user_id(String rs_user_id) {this.rs_user_id = rs_user_id;}

	/** Get US_country_id*/
	public long getRs_US_country_id() {return rs_US_country_id;}
	/** Set US_country_id*/
	public void setRs_US_country_id(long rs_US_country_id) {this.rs_US_country_id = rs_US_country_id;}

	/** Get US_nick*/
	public String getRs_US_nick() {return rs_US_nick;}
	/** Set US_nick*/
	public void setRs_US_nick(String rs_US_nick) {this.rs_US_nick = rs_US_nick;}

	/** Get US_avatar*/
	public String getRs_US_avatar() {return rs_US_avatar;}
	/** Set US_avatar*/
	public void setRs_US_avatar(String rs_US_avatar) {this.rs_US_avatar = rs_US_avatar;}

	/** Get US_is_admin*/
	public String getRs_US_is_admin() {return rs_US_is_admin;}
	/** Set US_is_admin*/
	public void setRs_US_is_admin(String rs_US_is_admin) {this.rs_US_is_admin = rs_US_is_admin;}

	/** Get US_first_name*/
	public String getRs_US_first_name() {return rs_US_first_name;}
	/** Set US_first_name*/
	public void setRs_US_first_name(String rs_US_first_name) {this.rs_US_first_name = rs_US_first_name;}

	/** Get US_last_name*/
	public String getRs_US_last_name() {return rs_US_last_name;}
	/** Set US_last_name*/
	public void setRs_US_last_name(String rs_US_last_name) {this.rs_US_last_name = rs_US_last_name;}

	/** Get product_id*/
	public String getRs_product_id() {return rs_product_id;}
	/** Set product_id*/
	public void setRs_product_id(String rs_product_id) {this.rs_product_id = rs_product_id;}

	/** Get PT_name*/
	public String getRs_PT_name() {return rs_PT_name;}
	/** Set PT_name*/
	public void setRs_PT_name(String rs_PT_name) {this.rs_PT_name = rs_PT_name;}

	/** Get PT_deadline*/
	public String getRs_PT_deadline() {return rs_PT_deadline;}
	/** Set PT_deadline*/
	public void setRs_PT_deadline(String rs_PT_deadline) {this.rs_PT_deadline = rs_PT_deadline;}

	/** Get PT_isPercent*/
	public String getRs_PT_isPercent() {return rs_PT_isPercent;}
	/** Set PT_isPercent*/
	public void setRs_PT_isPercent(String rs_PT_isPercent) {this.rs_PT_isPercent = rs_PT_isPercent;}

	/** Get PT_amount*/
	public double getRs_PT_amount() {return rs_PT_amount;}
	/** Set PT_amount*/
	public void setRs_PT_amount(double rs_PT_amount) {this.rs_PT_amount = rs_PT_amount;}

	/** Get PT_duration_minutes*/
	public long getRs_PT_duration_minutes() {return rs_PT_duration_minutes;}
	/** Set PT_duration_minutes*/
	public void setRs_PT_duration_minutes(long rs_PT_duration_minutes) {this.rs_PT_duration_minutes = rs_PT_duration_minutes;}

	/** Get quantity*/
	public long getRs_quantity() {return rs_quantity;}
	/** Set quantity*/
	public void setRs_quantity(long rs_quantity) {this.rs_quantity = rs_quantity;}

	/** Get product_id2*/
	public String getRs_product_id2() {return rs_product_id2;}
	/** Set product_id2*/
	public void setRs_product_id2(String rs_product_id2) {this.rs_product_id2 = rs_product_id2;}

	/** Get PT_name2*/
	public String getRs_PT_name2() {return rs_PT_name2;}
	/** Set PT_name2*/
	public void setRs_PT_name2(String rs_PT_name2) {this.rs_PT_name2 = rs_PT_name2;}

	/** Get PT_deadline2*/
	public String getRs_PT_deadline2() {return rs_PT_deadline2;}
	/** Set PT_deadline2*/
	public void setRs_PT_deadline2(String rs_PT_deadline2) {this.rs_PT_deadline2 = rs_PT_deadline2;}

	/** Get PT_isPercent2*/
	public String getRs_PT_isPercent2() {return rs_PT_isPercent2;}
	/** Set PT_isPercent2*/
	public void setRs_PT_isPercent2(String rs_PT_isPercent2) {this.rs_PT_isPercent2 = rs_PT_isPercent2;}

	/** Get PT_amount2*/
	public double getRs_PT_amount2() {return rs_PT_amount2;}
	/** Set PT_amount2*/
	public void setRs_PT_amount2(double rs_PT_amount2) {this.rs_PT_amount2 = rs_PT_amount2;}

	/** Get product_id3*/
	public String getRs_product_id3() {return rs_product_id3;}
	/** Set product_id3*/
	public void setRs_product_id3(String rs_product_id3) {this.rs_product_id3 = rs_product_id3;}

	/** Get PT_name3*/
	public String getRs_PT_name3() {return rs_PT_name3;}
	/** Set PT_name3*/
	public void setRs_PT_name3(String rs_PT_name3) {this.rs_PT_name3 = rs_PT_name3;}

	/** Get PT_deadline3*/
	public String getRs_PT_deadline3() {return rs_PT_deadline3;}
	/** Set PT_deadline3*/
	public void setRs_PT_deadline3(String rs_PT_deadline3) {this.rs_PT_deadline3 = rs_PT_deadline3;}

	/** Get PT_isPercent3*/
	public String getRs_PT_isPercent3() {return rs_PT_isPercent3;}
	/** Set PT_isPercent3*/
	public void setRs_PT_isPercent3(String rs_PT_isPercent3) {this.rs_PT_isPercent3 = rs_PT_isPercent3;}

	/** Get PT_amount3*/
	public double getRs_PT_amount3() {return rs_PT_amount3;}
	/** Set PT_amount3*/
	public void setRs_PT_amount3(double rs_PT_amount3) {this.rs_PT_amount3 = rs_PT_amount3;}

	/** Get amount*/
	public double getRs_amount() {return rs_amount;}
	/** Set amount*/
	public void setRs_amount(double rs_amount) {this.rs_amount = rs_amount;}

	/** Get currency*/
	public String getRs_currency() {return rs_currency;}
	/** Set currency*/
	public void setRs_currency(String rs_currency) {this.rs_currency = rs_currency;}

	/** Get phone*/
	public String getRs_phone() {return rs_phone;}
	/** Set phone*/
	public void setRs_phone(String rs_phone) {this.rs_phone = rs_phone;}

	/** Get pay_status*/
	public String getRs_pay_status() {return rs_pay_status;}
	/** Set pay_status*/
	public void setRs_pay_status(String rs_pay_status) {this.rs_pay_status = rs_pay_status;}

	/** Get start_date*/
	public String getRs_start_date() {return rs_start_date;}
	/** Set start_date*/
	public void setRs_start_date(String rs_start_date) {this.rs_start_date = rs_start_date;}

	/** Get start_time*/
	public String getRs_start_time() {return rs_start_time;}
	/** Set start_time*/
	public void setRs_start_time(String rs_start_time) {this.rs_start_time = rs_start_time;}

	/** Get duration_minutes*/
	public long getRs_duration_minutes() {return rs_duration_minutes;}
	/** Set duration_minutes*/
	public void setRs_duration_minutes(long rs_duration_minutes) {this.rs_duration_minutes = rs_duration_minutes;}

	/** Get places*/
	public long getRs_places() {return rs_places;}
	/** Set places*/
	public void setRs_places(long rs_places) {this.rs_places = rs_places;}

	/** Get coupon_id*/
	public String getRs_coupon_id() {return rs_coupon_id;}
	/** Set coupon_id*/
	public void setRs_coupon_id(String rs_coupon_id) {this.rs_coupon_id = rs_coupon_id;}

	/** Get executed_at*/
	public String getRs_executed_at() {return rs_executed_at;}
	/** Set executed_at*/
	public void setRs_executed_at(String rs_executed_at) {this.rs_executed_at = rs_executed_at;}

	/** Get note*/
	public String getRs_note() {return rs_note;}
	/** Set note*/
	public void setRs_note(String rs_note) {this.rs_note = rs_note;}

	/** Get comment*/
	public String getRs_comment() {return rs_comment;}
	/** Set comment*/
	public void setRs_comment(String rs_comment) {this.rs_comment = rs_comment;}

	/** Get json*/
	public String getRs_json() {return rs_json;}
	/** Set json*/
	public void setRs_json(String rs_json) {this.rs_json = rs_json;}

}
