package com.fvr.rs_reservations.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class RsBeanFiltro extends StBean {

	public String rs_sincro; // sincro
	public String rs_mark; // mark
	public String rs_is_deleted; // is_deleted
	public String rs_author; // author
	public String rs_reservation_id; // reservation_id
	public String rs_location_id; // location_id
	public String rs_LO_name; // LO_name
	public String rs_user_id; // user_id
	public String rs_US_country_id; // US_country_id
	public String rs_US_nick; // US_nick
	public String rs_US_avatar; // US_avatar
	public String rs_US_is_admin; // US_is_admin
	public String rs_US_first_name; // US_first_name
	public String rs_US_last_name; // US_last_name
	public String rs_product_id; // product_id
	public String rs_PT_name; // PT_name
	public String rs_PT_deadline; // PT_deadline
	public String rs_PT_isPercent; // PT_isPercent
	public String rs_PT_amount; // PT_amount
	public String rs_PT_duration_minutes; // PT_duration_minutes
	public String rs_quantity; // quantity
	public String rs_product_id2; // product_id2
	public String rs_PT_name2; // PT_name2
	public String rs_PT_deadline2; // PT_deadline2
	public String rs_PT_isPercent2; // PT_isPercent2
	public String rs_PT_amount2; // PT_amount2
	public String rs_product_id3; // product_id3
	public String rs_PT_name3; // PT_name3
	public String rs_PT_deadline3; // PT_deadline3
	public String rs_PT_isPercent3; // PT_isPercent3
	public String rs_PT_amount3; // PT_amount3
	public String rs_amount; // amount
	public String rs_currency; // currency
	public String rs_phone; // phone
	public String rs_pay_status; // pay_status
	public String rs_start_date; // start_date
	public String rs_start_time; // start_time
	public String rs_duration_minutes; // duration_minutes
	public String rs_places; // places
	public String rs_coupon_id; // coupon_id
	public String rs_executed_at; // executed_at
	public String rs_note; // note
	public String rs_comment; // comment
	public String rs_json; // json
    
    public RsBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public RsBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setRs_sincro( "" ); // sincro
	this.setRs_mark( "" ); // mark
	this.setRs_is_deleted( "" ); // is_deleted
	this.setRs_author( "" ); // author
	this.setRs_reservation_id( "" ); // reservation_id
	this.setRs_location_id( "" ); // location_id
	this.setRs_LO_name( "" ); // LO_name
	this.setRs_user_id( "" ); // user_id
	this.setRs_US_country_id( "" ); // US_country_id
	this.setRs_US_nick( "" ); // US_nick
	this.setRs_US_avatar( "" ); // US_avatar
	this.setRs_US_is_admin( "" ); // US_is_admin
	this.setRs_US_first_name( "" ); // US_first_name
	this.setRs_US_last_name( "" ); // US_last_name
	this.setRs_product_id( "" ); // product_id
	this.setRs_PT_name( "" ); // PT_name
	this.setRs_PT_deadline( "" ); // PT_deadline
	this.setRs_PT_isPercent( "" ); // PT_isPercent
	this.setRs_PT_amount( "" ); // PT_amount
	this.setRs_PT_duration_minutes( "" ); // PT_duration_minutes
	this.setRs_quantity( "" ); // quantity
	this.setRs_product_id2( "" ); // product_id2
	this.setRs_PT_name2( "" ); // PT_name2
	this.setRs_PT_deadline2( "" ); // PT_deadline2
	this.setRs_PT_isPercent2( "" ); // PT_isPercent2
	this.setRs_PT_amount2( "" ); // PT_amount2
	this.setRs_product_id3( "" ); // product_id3
	this.setRs_PT_name3( "" ); // PT_name3
	this.setRs_PT_deadline3( "" ); // PT_deadline3
	this.setRs_PT_isPercent3( "" ); // PT_isPercent3
	this.setRs_PT_amount3( "" ); // PT_amount3
	this.setRs_amount( "" ); // amount
	this.setRs_currency( "" ); // currency
	this.setRs_phone( "" ); // phone
	this.setRs_pay_status( "" ); // pay_status
	this.setRs_start_date( "" ); // start_date
	this.setRs_start_time( "" ); // start_time
	this.setRs_duration_minutes( "" ); // duration_minutes
	this.setRs_places( "" ); // places
	this.setRs_coupon_id( "" ); // coupon_id
	this.setRs_executed_at( "" ); // executed_at
	this.setRs_note( "" ); // note
	this.setRs_comment( "" ); // comment
	this.setRs_json( "" ); // json
    } 

    public RsBeanFiltro coalesce(RsBeanFiltro pri, RsBeanFiltro sec) {
        RsBeanFiltro r = new RsBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setRs_sincro( (pri.getRs_sincro()==null)?sec.getRs_sincro():pri.getRs_sincro() );	// sincro
		r.setRs_mark( (pri.getRs_mark()==null)?sec.getRs_mark():pri.getRs_mark() );	// mark
		r.setRs_is_deleted( (pri.getRs_is_deleted()==null)?sec.getRs_is_deleted():pri.getRs_is_deleted() );	// is_deleted
		r.setRs_author( (pri.getRs_author()==null)?sec.getRs_author():pri.getRs_author() );	// author
		r.setRs_reservation_id( (pri.getRs_reservation_id()==null)?sec.getRs_reservation_id():pri.getRs_reservation_id() );	// reservation_id
		r.setRs_location_id( (pri.getRs_location_id()==null)?sec.getRs_location_id():pri.getRs_location_id() );	// location_id
		r.setRs_LO_name( (pri.getRs_LO_name()==null)?sec.getRs_LO_name():pri.getRs_LO_name() );	// LO_name
		r.setRs_user_id( (pri.getRs_user_id()==null)?sec.getRs_user_id():pri.getRs_user_id() );	// user_id
		r.setRs_US_country_id( (pri.getRs_US_country_id()==null)?sec.getRs_US_country_id():pri.getRs_US_country_id() );	// US_country_id
		r.setRs_US_nick( (pri.getRs_US_nick()==null)?sec.getRs_US_nick():pri.getRs_US_nick() );	// US_nick
		r.setRs_US_avatar( (pri.getRs_US_avatar()==null)?sec.getRs_US_avatar():pri.getRs_US_avatar() );	// US_avatar
		r.setRs_US_is_admin( (pri.getRs_US_is_admin()==null)?sec.getRs_US_is_admin():pri.getRs_US_is_admin() );	// US_is_admin
		r.setRs_US_first_name( (pri.getRs_US_first_name()==null)?sec.getRs_US_first_name():pri.getRs_US_first_name() );	// US_first_name
		r.setRs_US_last_name( (pri.getRs_US_last_name()==null)?sec.getRs_US_last_name():pri.getRs_US_last_name() );	// US_last_name
		r.setRs_product_id( (pri.getRs_product_id()==null)?sec.getRs_product_id():pri.getRs_product_id() );	// product_id
		r.setRs_PT_name( (pri.getRs_PT_name()==null)?sec.getRs_PT_name():pri.getRs_PT_name() );	// PT_name
		r.setRs_PT_deadline( (pri.getRs_PT_deadline()==null)?sec.getRs_PT_deadline():pri.getRs_PT_deadline() );	// PT_deadline
		r.setRs_PT_isPercent( (pri.getRs_PT_isPercent()==null)?sec.getRs_PT_isPercent():pri.getRs_PT_isPercent() );	// PT_isPercent
		r.setRs_PT_amount( (pri.getRs_PT_amount()==null)?sec.getRs_PT_amount():pri.getRs_PT_amount() );	// PT_amount
		r.setRs_PT_duration_minutes( (pri.getRs_PT_duration_minutes()==null)?sec.getRs_PT_duration_minutes():pri.getRs_PT_duration_minutes() );	// PT_duration_minutes
		r.setRs_quantity( (pri.getRs_quantity()==null)?sec.getRs_quantity():pri.getRs_quantity() );	// quantity
		r.setRs_product_id2( (pri.getRs_product_id2()==null)?sec.getRs_product_id2():pri.getRs_product_id2() );	// product_id2
		r.setRs_PT_name2( (pri.getRs_PT_name2()==null)?sec.getRs_PT_name2():pri.getRs_PT_name2() );	// PT_name2
		r.setRs_PT_deadline2( (pri.getRs_PT_deadline2()==null)?sec.getRs_PT_deadline2():pri.getRs_PT_deadline2() );	// PT_deadline2
		r.setRs_PT_isPercent2( (pri.getRs_PT_isPercent2()==null)?sec.getRs_PT_isPercent2():pri.getRs_PT_isPercent2() );	// PT_isPercent2
		r.setRs_PT_amount2( (pri.getRs_PT_amount2()==null)?sec.getRs_PT_amount2():pri.getRs_PT_amount2() );	// PT_amount2
		r.setRs_product_id3( (pri.getRs_product_id3()==null)?sec.getRs_product_id3():pri.getRs_product_id3() );	// product_id3
		r.setRs_PT_name3( (pri.getRs_PT_name3()==null)?sec.getRs_PT_name3():pri.getRs_PT_name3() );	// PT_name3
		r.setRs_PT_deadline3( (pri.getRs_PT_deadline3()==null)?sec.getRs_PT_deadline3():pri.getRs_PT_deadline3() );	// PT_deadline3
		r.setRs_PT_isPercent3( (pri.getRs_PT_isPercent3()==null)?sec.getRs_PT_isPercent3():pri.getRs_PT_isPercent3() );	// PT_isPercent3
		r.setRs_PT_amount3( (pri.getRs_PT_amount3()==null)?sec.getRs_PT_amount3():pri.getRs_PT_amount3() );	// PT_amount3
		r.setRs_amount( (pri.getRs_amount()==null)?sec.getRs_amount():pri.getRs_amount() );	// amount
		r.setRs_currency( (pri.getRs_currency()==null)?sec.getRs_currency():pri.getRs_currency() );	// currency
		r.setRs_phone( (pri.getRs_phone()==null)?sec.getRs_phone():pri.getRs_phone() );	// phone
		r.setRs_pay_status( (pri.getRs_pay_status()==null)?sec.getRs_pay_status():pri.getRs_pay_status() );	// pay_status
		r.setRs_start_date( (pri.getRs_start_date()==null)?sec.getRs_start_date():pri.getRs_start_date() );	// start_date
		r.setRs_start_time( (pri.getRs_start_time()==null)?sec.getRs_start_time():pri.getRs_start_time() );	// start_time
		r.setRs_duration_minutes( (pri.getRs_duration_minutes()==null)?sec.getRs_duration_minutes():pri.getRs_duration_minutes() );	// duration_minutes
		r.setRs_places( (pri.getRs_places()==null)?sec.getRs_places():pri.getRs_places() );	// places
		r.setRs_coupon_id( (pri.getRs_coupon_id()==null)?sec.getRs_coupon_id():pri.getRs_coupon_id() );	// coupon_id
		r.setRs_executed_at( (pri.getRs_executed_at()==null)?sec.getRs_executed_at():pri.getRs_executed_at() );	// executed_at
		r.setRs_note( (pri.getRs_note()==null)?sec.getRs_note():pri.getRs_note() );	// note
		r.setRs_comment( (pri.getRs_comment()==null)?sec.getRs_comment():pri.getRs_comment() );	// comment
		r.setRs_json( (pri.getRs_json()==null)?sec.getRs_json():pri.getRs_json() );	// json
        }
        return r;
    }
    
    public void copyTo(RsBeanFiltro Destino) {
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
    
    public void copyFrom(RsBeanFiltro Origen) {
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
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getRs_sincro()==null?"":this.getRs_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getRs_mark()==null?"":this.getRs_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getRs_is_deleted()==null?"":this.getRs_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getRs_author()==null?"":this.getRs_author() ); // author
		out.append( _K.sepFld ); out.append( this.getRs_reservation_id()==null?"":this.getRs_reservation_id() ); // reservation_id
		out.append( _K.sepFld ); out.append( this.getRs_location_id()==null?"":this.getRs_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getRs_LO_name()==null?"":this.getRs_LO_name() ); // LO_name
		out.append( _K.sepFld ); out.append( this.getRs_user_id()==null?"":this.getRs_user_id() ); // user_id
		out.append( _K.sepFld ); out.append( this.getRs_US_country_id()==null?"":this.getRs_US_country_id() ); // US_country_id
		out.append( _K.sepFld ); out.append( this.getRs_US_nick()==null?"":this.getRs_US_nick() ); // US_nick
		out.append( _K.sepFld ); out.append( this.getRs_US_avatar()==null?"":this.getRs_US_avatar() ); // US_avatar
		out.append( _K.sepFld ); out.append( this.getRs_US_is_admin()==null?"":this.getRs_US_is_admin() ); // US_is_admin
		out.append( _K.sepFld ); out.append( this.getRs_US_first_name()==null?"":this.getRs_US_first_name() ); // US_first_name
		out.append( _K.sepFld ); out.append( this.getRs_US_last_name()==null?"":this.getRs_US_last_name() ); // US_last_name
		out.append( _K.sepFld ); out.append( this.getRs_product_id()==null?"":this.getRs_product_id() ); // product_id
		out.append( _K.sepFld ); out.append( this.getRs_PT_name()==null?"":this.getRs_PT_name() ); // PT_name
		out.append( _K.sepFld ); out.append( this.getRs_PT_deadline()==null?"":this.getRs_PT_deadline() ); // PT_deadline
		out.append( _K.sepFld ); out.append( this.getRs_PT_isPercent()==null?"":this.getRs_PT_isPercent() ); // PT_isPercent
		out.append( _K.sepFld ); out.append( this.getRs_PT_amount()==null?"":this.getRs_PT_amount() ); // PT_amount
		out.append( _K.sepFld ); out.append( this.getRs_PT_duration_minutes()==null?"":this.getRs_PT_duration_minutes() ); // PT_duration_minutes
		out.append( _K.sepFld ); out.append( this.getRs_quantity()==null?"":this.getRs_quantity() ); // quantity
		out.append( _K.sepFld ); out.append( this.getRs_product_id2()==null?"":this.getRs_product_id2() ); // product_id2
		out.append( _K.sepFld ); out.append( this.getRs_PT_name2()==null?"":this.getRs_PT_name2() ); // PT_name2
		out.append( _K.sepFld ); out.append( this.getRs_PT_deadline2()==null?"":this.getRs_PT_deadline2() ); // PT_deadline2
		out.append( _K.sepFld ); out.append( this.getRs_PT_isPercent2()==null?"":this.getRs_PT_isPercent2() ); // PT_isPercent2
		out.append( _K.sepFld ); out.append( this.getRs_PT_amount2()==null?"":this.getRs_PT_amount2() ); // PT_amount2
		out.append( _K.sepFld ); out.append( this.getRs_product_id3()==null?"":this.getRs_product_id3() ); // product_id3
		out.append( _K.sepFld ); out.append( this.getRs_PT_name3()==null?"":this.getRs_PT_name3() ); // PT_name3
		out.append( _K.sepFld ); out.append( this.getRs_PT_deadline3()==null?"":this.getRs_PT_deadline3() ); // PT_deadline3
		out.append( _K.sepFld ); out.append( this.getRs_PT_isPercent3()==null?"":this.getRs_PT_isPercent3() ); // PT_isPercent3
		out.append( _K.sepFld ); out.append( this.getRs_PT_amount3()==null?"":this.getRs_PT_amount3() ); // PT_amount3
		out.append( _K.sepFld ); out.append( this.getRs_amount()==null?"":this.getRs_amount() ); // amount
		out.append( _K.sepFld ); out.append( this.getRs_currency()==null?"":this.getRs_currency() ); // currency
		out.append( _K.sepFld ); out.append( this.getRs_phone()==null?"":this.getRs_phone() ); // phone
		out.append( _K.sepFld ); out.append( this.getRs_pay_status()==null?"":this.getRs_pay_status() ); // pay_status
		out.append( _K.sepFld ); out.append( this.getRs_start_date()==null?"":this.getRs_start_date() ); // start_date
		out.append( _K.sepFld ); out.append( this.getRs_start_time()==null?"":this.getRs_start_time() ); // start_time
		out.append( _K.sepFld ); out.append( this.getRs_duration_minutes()==null?"":this.getRs_duration_minutes() ); // duration_minutes
		out.append( _K.sepFld ); out.append( this.getRs_places()==null?"":this.getRs_places() ); // places
		out.append( _K.sepFld ); out.append( this.getRs_coupon_id()==null?"":this.getRs_coupon_id() ); // coupon_id
		out.append( _K.sepFld ); out.append( this.getRs_executed_at()==null?"":this.getRs_executed_at() ); // executed_at
		out.append( _K.sepFld ); out.append( this.getRs_note()==null?"":this.getRs_note() ); // note
		out.append( _K.sepFld ); out.append( this.getRs_comment()==null?"":this.getRs_comment() ); // comment
		out.append( _K.sepFld ); out.append( this.getRs_json()==null?"":this.getRs_json() ); // json

		out.append( _K.sepReg );
		
		return out.toString();
	}

	public void deserializar(String in) {
		inicializar();
		if ( in != null && in.length() > 0 ) {
			
			String s = in.replaceAll( _K.sepReg, "" );
                   s =  s.replaceAll( _K.sepReg_0x0D, "" );
                   s =  s.replaceAll( _K.sepReg_0x0A, "" );
			String[] trozos = s.split( _K.sepFld );
			
			try { this.setRs_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setRs_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setRs_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setRs_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setRs_reservation_id( trozos[4] ); } catch (Exception e) {;} // reservation_id
			try { this.setRs_location_id( trozos[5] ); } catch (Exception e) {;} // location_id
			try { this.setRs_LO_name( trozos[6] ); } catch (Exception e) {;} // LO_name
			try { this.setRs_user_id( trozos[7] ); } catch (Exception e) {;} // user_id
			try { this.setRs_US_country_id( trozos[8] ); } catch (Exception e) {;} // US_country_id
			try { this.setRs_US_nick( trozos[9] ); } catch (Exception e) {;} // US_nick
			try { this.setRs_US_avatar( trozos[10] ); } catch (Exception e) {;} // US_avatar
			try { this.setRs_US_is_admin( trozos[11] ); } catch (Exception e) {;} // US_is_admin
			try { this.setRs_US_first_name( trozos[12] ); } catch (Exception e) {;} // US_first_name
			try { this.setRs_US_last_name( trozos[13] ); } catch (Exception e) {;} // US_last_name
			try { this.setRs_product_id( trozos[14] ); } catch (Exception e) {;} // product_id
			try { this.setRs_PT_name( trozos[15] ); } catch (Exception e) {;} // PT_name
			try { this.setRs_PT_deadline( trozos[16] ); } catch (Exception e) {;} // PT_deadline
			try { this.setRs_PT_isPercent( trozos[17] ); } catch (Exception e) {;} // PT_isPercent
			try { this.setRs_PT_amount( trozos[18] ); } catch (Exception e) {;} // PT_amount
			try { this.setRs_PT_duration_minutes( trozos[19] ); } catch (Exception e) {;} // PT_duration_minutes
			try { this.setRs_quantity( trozos[20] ); } catch (Exception e) {;} // quantity
			try { this.setRs_product_id2( trozos[21] ); } catch (Exception e) {;} // product_id2
			try { this.setRs_PT_name2( trozos[22] ); } catch (Exception e) {;} // PT_name2
			try { this.setRs_PT_deadline2( trozos[23] ); } catch (Exception e) {;} // PT_deadline2
			try { this.setRs_PT_isPercent2( trozos[24] ); } catch (Exception e) {;} // PT_isPercent2
			try { this.setRs_PT_amount2( trozos[25] ); } catch (Exception e) {;} // PT_amount2
			try { this.setRs_product_id3( trozos[26] ); } catch (Exception e) {;} // product_id3
			try { this.setRs_PT_name3( trozos[27] ); } catch (Exception e) {;} // PT_name3
			try { this.setRs_PT_deadline3( trozos[28] ); } catch (Exception e) {;} // PT_deadline3
			try { this.setRs_PT_isPercent3( trozos[29] ); } catch (Exception e) {;} // PT_isPercent3
			try { this.setRs_PT_amount3( trozos[30] ); } catch (Exception e) {;} // PT_amount3
			try { this.setRs_amount( trozos[31] ); } catch (Exception e) {;} // amount
			try { this.setRs_currency( trozos[32] ); } catch (Exception e) {;} // currency
			try { this.setRs_phone( trozos[33] ); } catch (Exception e) {;} // phone
			try { this.setRs_pay_status( trozos[34] ); } catch (Exception e) {;} // pay_status
			try { this.setRs_start_date( trozos[35] ); } catch (Exception e) {;} // start_date
			try { this.setRs_start_time( trozos[36] ); } catch (Exception e) {;} // start_time
			try { this.setRs_duration_minutes( trozos[37] ); } catch (Exception e) {;} // duration_minutes
			try { this.setRs_places( trozos[38] ); } catch (Exception e) {;} // places
			try { this.setRs_coupon_id( trozos[39] ); } catch (Exception e) {;} // coupon_id
			try { this.setRs_executed_at( trozos[40] ); } catch (Exception e) {;} // executed_at
			try { this.setRs_note( trozos[41] ); } catch (Exception e) {;} // note
			try { this.setRs_comment( trozos[42] ); } catch (Exception e) {;} // comment
			try { this.setRs_json( trozos[43] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

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
	public String getRs_US_country_id() {return rs_US_country_id;}
	/** Set US_country_id*/
	public void setRs_US_country_id(String rs_US_country_id) {this.rs_US_country_id = rs_US_country_id;}

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
	public String getRs_PT_amount() {return rs_PT_amount;}
	/** Set PT_amount*/
	public void setRs_PT_amount(String rs_PT_amount) {this.rs_PT_amount = rs_PT_amount;}

	/** Get PT_duration_minutes*/
	public String getRs_PT_duration_minutes() {return rs_PT_duration_minutes;}
	/** Set PT_duration_minutes*/
	public void setRs_PT_duration_minutes(String rs_PT_duration_minutes) {this.rs_PT_duration_minutes = rs_PT_duration_minutes;}

	/** Get quantity*/
	public String getRs_quantity() {return rs_quantity;}
	/** Set quantity*/
	public void setRs_quantity(String rs_quantity) {this.rs_quantity = rs_quantity;}

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
	public String getRs_PT_amount2() {return rs_PT_amount2;}
	/** Set PT_amount2*/
	public void setRs_PT_amount2(String rs_PT_amount2) {this.rs_PT_amount2 = rs_PT_amount2;}

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
	public String getRs_PT_amount3() {return rs_PT_amount3;}
	/** Set PT_amount3*/
	public void setRs_PT_amount3(String rs_PT_amount3) {this.rs_PT_amount3 = rs_PT_amount3;}

	/** Get amount*/
	public String getRs_amount() {return rs_amount;}
	/** Set amount*/
	public void setRs_amount(String rs_amount) {this.rs_amount = rs_amount;}

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
	public String getRs_duration_minutes() {return rs_duration_minutes;}
	/** Set duration_minutes*/
	public void setRs_duration_minutes(String rs_duration_minutes) {this.rs_duration_minutes = rs_duration_minutes;}

	/** Get places*/
	public String getRs_places() {return rs_places;}
	/** Set places*/
	public void setRs_places(String rs_places) {this.rs_places = rs_places;}

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
