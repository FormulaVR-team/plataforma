package com.fvr.ad_rs_reservations.bean;

import com.fvr._comun.StBean;
import com.fvr._comun._K;

public class Ad_rsBeanFiltro extends StBean {

	public String ad_rs_sincro; // sincro
	public String ad_rs_mark; // mark
	public String ad_rs_is_deleted; // is_deleted
	public String ad_rs_author; // author
	public String ad_rs_reservation_id; // reservation_id
	public String ad_rs_location_id; // location_id
	public String ad_rs_LO_name; // LO_name
	public String ad_rs_user_id; // user_id
	public String ad_rs_US_country_id; // US_country_id
	public String ad_rs_US_nick; // US_nick
	public String ad_rs_US_avatar; // US_avatar
	public String ad_rs_US_is_admin; // US_is_admin
	public String ad_rs_US_first_name; // US_first_name
	public String ad_rs_US_last_name; // US_last_name
	public String ad_rs_product_id; // product_id
	public String ad_rs_PT_name; // PT_name
	public String ad_rs_PT_deadline; // PT_deadline
	public String ad_rs_PT_isPercent; // PT_isPercent
	public String ad_rs_PT_amount; // PT_amount
	public String ad_rs_PT_duration_minutes; // PT_duration_minutes
	public String ad_rs_quantity; // quantity
	public String ad_rs_product_id2; // product_id2
	public String ad_rs_PT_name2; // PT_name2
	public String ad_rs_PT_deadline2; // PT_deadline2
	public String ad_rs_PT_isPercent2; // PT_isPercent2
	public String ad_rs_PT_amount2; // PT_amount2
	public String ad_rs_product_id3; // product_id3
	public String ad_rs_PT_name3; // PT_name3
	public String ad_rs_PT_deadline3; // PT_deadline3
	public String ad_rs_PT_isPercent3; // PT_isPercent3
	public String ad_rs_PT_amount3; // PT_amount3
	public String ad_rs_amount; // amount
	public String ad_rs_currency; // currency
	public String ad_rs_phone; // phone
	public String ad_rs_pay_status; // pay_status
	public String ad_rs_start_date; // start_date
	public String ad_rs_start_time; // start_time
	public String ad_rs_duration_minutes; // duration_minutes
	public String ad_rs_places; // places
	public String ad_rs_coupon_id; // coupon_id
	public String ad_rs_executed_at; // executed_at
	public String ad_rs_note; // note
	public String ad_rs_comment; // comment
	public String ad_rs_json; // json
    
    public Ad_rsBeanFiltro() {
        super();
        inicializar();
    }
    
    // Constructor que deja 'null' en todos sus miembros
    public Ad_rsBeanFiltro(Object nulo) { super(); }

    public void inicializar() {
	this.setAd_rs_sincro( "" ); // sincro
	this.setAd_rs_mark( "" ); // mark
	this.setAd_rs_is_deleted( "" ); // is_deleted
	this.setAd_rs_author( "" ); // author
	this.setAd_rs_reservation_id( "" ); // reservation_id
	this.setAd_rs_location_id( "" ); // location_id
	this.setAd_rs_LO_name( "" ); // LO_name
	this.setAd_rs_user_id( "" ); // user_id
	this.setAd_rs_US_country_id( "" ); // US_country_id
	this.setAd_rs_US_nick( "" ); // US_nick
	this.setAd_rs_US_avatar( "" ); // US_avatar
	this.setAd_rs_US_is_admin( "" ); // US_is_admin
	this.setAd_rs_US_first_name( "" ); // US_first_name
	this.setAd_rs_US_last_name( "" ); // US_last_name
	this.setAd_rs_product_id( "" ); // product_id
	this.setAd_rs_PT_name( "" ); // PT_name
	this.setAd_rs_PT_deadline( "" ); // PT_deadline
	this.setAd_rs_PT_isPercent( "" ); // PT_isPercent
	this.setAd_rs_PT_amount( "" ); // PT_amount
	this.setAd_rs_PT_duration_minutes( "" ); // PT_duration_minutes
	this.setAd_rs_quantity( "" ); // quantity
	this.setAd_rs_product_id2( "" ); // product_id2
	this.setAd_rs_PT_name2( "" ); // PT_name2
	this.setAd_rs_PT_deadline2( "" ); // PT_deadline2
	this.setAd_rs_PT_isPercent2( "" ); // PT_isPercent2
	this.setAd_rs_PT_amount2( "" ); // PT_amount2
	this.setAd_rs_product_id3( "" ); // product_id3
	this.setAd_rs_PT_name3( "" ); // PT_name3
	this.setAd_rs_PT_deadline3( "" ); // PT_deadline3
	this.setAd_rs_PT_isPercent3( "" ); // PT_isPercent3
	this.setAd_rs_PT_amount3( "" ); // PT_amount3
	this.setAd_rs_amount( "" ); // amount
	this.setAd_rs_currency( "" ); // currency
	this.setAd_rs_phone( "" ); // phone
	this.setAd_rs_pay_status( "" ); // pay_status
	this.setAd_rs_start_date( "" ); // start_date
	this.setAd_rs_start_time( "" ); // start_time
	this.setAd_rs_duration_minutes( "" ); // duration_minutes
	this.setAd_rs_places( "" ); // places
	this.setAd_rs_coupon_id( "" ); // coupon_id
	this.setAd_rs_executed_at( "" ); // executed_at
	this.setAd_rs_note( "" ); // note
	this.setAd_rs_comment( "" ); // comment
	this.setAd_rs_json( "" ); // json
    } 

    public Ad_rsBeanFiltro coalesce(Ad_rsBeanFiltro pri, Ad_rsBeanFiltro sec) {
        Ad_rsBeanFiltro r = new Ad_rsBeanFiltro(null);
        if ( pri!=null && sec!=null ) {
		r.setAd_rs_sincro( (pri.getAd_rs_sincro()==null)?sec.getAd_rs_sincro():pri.getAd_rs_sincro() );	// sincro
		r.setAd_rs_mark( (pri.getAd_rs_mark()==null)?sec.getAd_rs_mark():pri.getAd_rs_mark() );	// mark
		r.setAd_rs_is_deleted( (pri.getAd_rs_is_deleted()==null)?sec.getAd_rs_is_deleted():pri.getAd_rs_is_deleted() );	// is_deleted
		r.setAd_rs_author( (pri.getAd_rs_author()==null)?sec.getAd_rs_author():pri.getAd_rs_author() );	// author
		r.setAd_rs_reservation_id( (pri.getAd_rs_reservation_id()==null)?sec.getAd_rs_reservation_id():pri.getAd_rs_reservation_id() );	// reservation_id
		r.setAd_rs_location_id( (pri.getAd_rs_location_id()==null)?sec.getAd_rs_location_id():pri.getAd_rs_location_id() );	// location_id
		r.setAd_rs_LO_name( (pri.getAd_rs_LO_name()==null)?sec.getAd_rs_LO_name():pri.getAd_rs_LO_name() );	// LO_name
		r.setAd_rs_user_id( (pri.getAd_rs_user_id()==null)?sec.getAd_rs_user_id():pri.getAd_rs_user_id() );	// user_id
		r.setAd_rs_US_country_id( (pri.getAd_rs_US_country_id()==null)?sec.getAd_rs_US_country_id():pri.getAd_rs_US_country_id() );	// US_country_id
		r.setAd_rs_US_nick( (pri.getAd_rs_US_nick()==null)?sec.getAd_rs_US_nick():pri.getAd_rs_US_nick() );	// US_nick
		r.setAd_rs_US_avatar( (pri.getAd_rs_US_avatar()==null)?sec.getAd_rs_US_avatar():pri.getAd_rs_US_avatar() );	// US_avatar
		r.setAd_rs_US_is_admin( (pri.getAd_rs_US_is_admin()==null)?sec.getAd_rs_US_is_admin():pri.getAd_rs_US_is_admin() );	// US_is_admin
		r.setAd_rs_US_first_name( (pri.getAd_rs_US_first_name()==null)?sec.getAd_rs_US_first_name():pri.getAd_rs_US_first_name() );	// US_first_name
		r.setAd_rs_US_last_name( (pri.getAd_rs_US_last_name()==null)?sec.getAd_rs_US_last_name():pri.getAd_rs_US_last_name() );	// US_last_name
		r.setAd_rs_product_id( (pri.getAd_rs_product_id()==null)?sec.getAd_rs_product_id():pri.getAd_rs_product_id() );	// product_id
		r.setAd_rs_PT_name( (pri.getAd_rs_PT_name()==null)?sec.getAd_rs_PT_name():pri.getAd_rs_PT_name() );	// PT_name
		r.setAd_rs_PT_deadline( (pri.getAd_rs_PT_deadline()==null)?sec.getAd_rs_PT_deadline():pri.getAd_rs_PT_deadline() );	// PT_deadline
		r.setAd_rs_PT_isPercent( (pri.getAd_rs_PT_isPercent()==null)?sec.getAd_rs_PT_isPercent():pri.getAd_rs_PT_isPercent() );	// PT_isPercent
		r.setAd_rs_PT_amount( (pri.getAd_rs_PT_amount()==null)?sec.getAd_rs_PT_amount():pri.getAd_rs_PT_amount() );	// PT_amount
		r.setAd_rs_PT_duration_minutes( (pri.getAd_rs_PT_duration_minutes()==null)?sec.getAd_rs_PT_duration_minutes():pri.getAd_rs_PT_duration_minutes() );	// PT_duration_minutes
		r.setAd_rs_quantity( (pri.getAd_rs_quantity()==null)?sec.getAd_rs_quantity():pri.getAd_rs_quantity() );	// quantity
		r.setAd_rs_product_id2( (pri.getAd_rs_product_id2()==null)?sec.getAd_rs_product_id2():pri.getAd_rs_product_id2() );	// product_id2
		r.setAd_rs_PT_name2( (pri.getAd_rs_PT_name2()==null)?sec.getAd_rs_PT_name2():pri.getAd_rs_PT_name2() );	// PT_name2
		r.setAd_rs_PT_deadline2( (pri.getAd_rs_PT_deadline2()==null)?sec.getAd_rs_PT_deadline2():pri.getAd_rs_PT_deadline2() );	// PT_deadline2
		r.setAd_rs_PT_isPercent2( (pri.getAd_rs_PT_isPercent2()==null)?sec.getAd_rs_PT_isPercent2():pri.getAd_rs_PT_isPercent2() );	// PT_isPercent2
		r.setAd_rs_PT_amount2( (pri.getAd_rs_PT_amount2()==null)?sec.getAd_rs_PT_amount2():pri.getAd_rs_PT_amount2() );	// PT_amount2
		r.setAd_rs_product_id3( (pri.getAd_rs_product_id3()==null)?sec.getAd_rs_product_id3():pri.getAd_rs_product_id3() );	// product_id3
		r.setAd_rs_PT_name3( (pri.getAd_rs_PT_name3()==null)?sec.getAd_rs_PT_name3():pri.getAd_rs_PT_name3() );	// PT_name3
		r.setAd_rs_PT_deadline3( (pri.getAd_rs_PT_deadline3()==null)?sec.getAd_rs_PT_deadline3():pri.getAd_rs_PT_deadline3() );	// PT_deadline3
		r.setAd_rs_PT_isPercent3( (pri.getAd_rs_PT_isPercent3()==null)?sec.getAd_rs_PT_isPercent3():pri.getAd_rs_PT_isPercent3() );	// PT_isPercent3
		r.setAd_rs_PT_amount3( (pri.getAd_rs_PT_amount3()==null)?sec.getAd_rs_PT_amount3():pri.getAd_rs_PT_amount3() );	// PT_amount3
		r.setAd_rs_amount( (pri.getAd_rs_amount()==null)?sec.getAd_rs_amount():pri.getAd_rs_amount() );	// amount
		r.setAd_rs_currency( (pri.getAd_rs_currency()==null)?sec.getAd_rs_currency():pri.getAd_rs_currency() );	// currency
		r.setAd_rs_phone( (pri.getAd_rs_phone()==null)?sec.getAd_rs_phone():pri.getAd_rs_phone() );	// phone
		r.setAd_rs_pay_status( (pri.getAd_rs_pay_status()==null)?sec.getAd_rs_pay_status():pri.getAd_rs_pay_status() );	// pay_status
		r.setAd_rs_start_date( (pri.getAd_rs_start_date()==null)?sec.getAd_rs_start_date():pri.getAd_rs_start_date() );	// start_date
		r.setAd_rs_start_time( (pri.getAd_rs_start_time()==null)?sec.getAd_rs_start_time():pri.getAd_rs_start_time() );	// start_time
		r.setAd_rs_duration_minutes( (pri.getAd_rs_duration_minutes()==null)?sec.getAd_rs_duration_minutes():pri.getAd_rs_duration_minutes() );	// duration_minutes
		r.setAd_rs_places( (pri.getAd_rs_places()==null)?sec.getAd_rs_places():pri.getAd_rs_places() );	// places
		r.setAd_rs_coupon_id( (pri.getAd_rs_coupon_id()==null)?sec.getAd_rs_coupon_id():pri.getAd_rs_coupon_id() );	// coupon_id
		r.setAd_rs_executed_at( (pri.getAd_rs_executed_at()==null)?sec.getAd_rs_executed_at():pri.getAd_rs_executed_at() );	// executed_at
		r.setAd_rs_note( (pri.getAd_rs_note()==null)?sec.getAd_rs_note():pri.getAd_rs_note() );	// note
		r.setAd_rs_comment( (pri.getAd_rs_comment()==null)?sec.getAd_rs_comment():pri.getAd_rs_comment() );	// comment
		r.setAd_rs_json( (pri.getAd_rs_json()==null)?sec.getAd_rs_json():pri.getAd_rs_json() );	// json
        }
        return r;
    }
    
    public void copyTo(Ad_rsBeanFiltro Destino) {
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
    
    public void copyFrom(Ad_rsBeanFiltro Origen) {
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
	////////////////////////////////////////////////////////////
	public String serializar() {
		StringBuffer out = new StringBuffer();
		
		                         out.append( this.getAd_rs_sincro()==null?"":this.getAd_rs_sincro() ); // sincro
		out.append( _K.sepFld ); out.append( this.getAd_rs_mark()==null?"":this.getAd_rs_mark() ); // mark
		out.append( _K.sepFld ); out.append( this.getAd_rs_is_deleted()==null?"":this.getAd_rs_is_deleted() ); // is_deleted
		out.append( _K.sepFld ); out.append( this.getAd_rs_author()==null?"":this.getAd_rs_author() ); // author
		out.append( _K.sepFld ); out.append( this.getAd_rs_reservation_id()==null?"":this.getAd_rs_reservation_id() ); // reservation_id
		out.append( _K.sepFld ); out.append( this.getAd_rs_location_id()==null?"":this.getAd_rs_location_id() ); // location_id
		out.append( _K.sepFld ); out.append( this.getAd_rs_LO_name()==null?"":this.getAd_rs_LO_name() ); // LO_name
		out.append( _K.sepFld ); out.append( this.getAd_rs_user_id()==null?"":this.getAd_rs_user_id() ); // user_id
		out.append( _K.sepFld ); out.append( this.getAd_rs_US_country_id()==null?"":this.getAd_rs_US_country_id() ); // US_country_id
		out.append( _K.sepFld ); out.append( this.getAd_rs_US_nick()==null?"":this.getAd_rs_US_nick() ); // US_nick
		out.append( _K.sepFld ); out.append( this.getAd_rs_US_avatar()==null?"":this.getAd_rs_US_avatar() ); // US_avatar
		out.append( _K.sepFld ); out.append( this.getAd_rs_US_is_admin()==null?"":this.getAd_rs_US_is_admin() ); // US_is_admin
		out.append( _K.sepFld ); out.append( this.getAd_rs_US_first_name()==null?"":this.getAd_rs_US_first_name() ); // US_first_name
		out.append( _K.sepFld ); out.append( this.getAd_rs_US_last_name()==null?"":this.getAd_rs_US_last_name() ); // US_last_name
		out.append( _K.sepFld ); out.append( this.getAd_rs_product_id()==null?"":this.getAd_rs_product_id() ); // product_id
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_name()==null?"":this.getAd_rs_PT_name() ); // PT_name
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_deadline()==null?"":this.getAd_rs_PT_deadline() ); // PT_deadline
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_isPercent()==null?"":this.getAd_rs_PT_isPercent() ); // PT_isPercent
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_amount()==null?"":this.getAd_rs_PT_amount() ); // PT_amount
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_duration_minutes()==null?"":this.getAd_rs_PT_duration_minutes() ); // PT_duration_minutes
		out.append( _K.sepFld ); out.append( this.getAd_rs_quantity()==null?"":this.getAd_rs_quantity() ); // quantity
		out.append( _K.sepFld ); out.append( this.getAd_rs_product_id2()==null?"":this.getAd_rs_product_id2() ); // product_id2
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_name2()==null?"":this.getAd_rs_PT_name2() ); // PT_name2
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_deadline2()==null?"":this.getAd_rs_PT_deadline2() ); // PT_deadline2
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_isPercent2()==null?"":this.getAd_rs_PT_isPercent2() ); // PT_isPercent2
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_amount2()==null?"":this.getAd_rs_PT_amount2() ); // PT_amount2
		out.append( _K.sepFld ); out.append( this.getAd_rs_product_id3()==null?"":this.getAd_rs_product_id3() ); // product_id3
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_name3()==null?"":this.getAd_rs_PT_name3() ); // PT_name3
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_deadline3()==null?"":this.getAd_rs_PT_deadline3() ); // PT_deadline3
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_isPercent3()==null?"":this.getAd_rs_PT_isPercent3() ); // PT_isPercent3
		out.append( _K.sepFld ); out.append( this.getAd_rs_PT_amount3()==null?"":this.getAd_rs_PT_amount3() ); // PT_amount3
		out.append( _K.sepFld ); out.append( this.getAd_rs_amount()==null?"":this.getAd_rs_amount() ); // amount
		out.append( _K.sepFld ); out.append( this.getAd_rs_currency()==null?"":this.getAd_rs_currency() ); // currency
		out.append( _K.sepFld ); out.append( this.getAd_rs_phone()==null?"":this.getAd_rs_phone() ); // phone
		out.append( _K.sepFld ); out.append( this.getAd_rs_pay_status()==null?"":this.getAd_rs_pay_status() ); // pay_status
		out.append( _K.sepFld ); out.append( this.getAd_rs_start_date()==null?"":this.getAd_rs_start_date() ); // start_date
		out.append( _K.sepFld ); out.append( this.getAd_rs_start_time()==null?"":this.getAd_rs_start_time() ); // start_time
		out.append( _K.sepFld ); out.append( this.getAd_rs_duration_minutes()==null?"":this.getAd_rs_duration_minutes() ); // duration_minutes
		out.append( _K.sepFld ); out.append( this.getAd_rs_places()==null?"":this.getAd_rs_places() ); // places
		out.append( _K.sepFld ); out.append( this.getAd_rs_coupon_id()==null?"":this.getAd_rs_coupon_id() ); // coupon_id
		out.append( _K.sepFld ); out.append( this.getAd_rs_executed_at()==null?"":this.getAd_rs_executed_at() ); // executed_at
		out.append( _K.sepFld ); out.append( this.getAd_rs_note()==null?"":this.getAd_rs_note() ); // note
		out.append( _K.sepFld ); out.append( this.getAd_rs_comment()==null?"":this.getAd_rs_comment() ); // comment
		out.append( _K.sepFld ); out.append( this.getAd_rs_json()==null?"":this.getAd_rs_json() ); // json

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
			
			try { this.setAd_rs_sincro( trozos[0] ); } catch (Exception e) {;} // sincro
			try { this.setAd_rs_mark( trozos[1] ); } catch (Exception e) {;} // mark
			try { this.setAd_rs_is_deleted( trozos[2] ); } catch (Exception e) {;} // is_deleted
			try { this.setAd_rs_author( trozos[3] ); } catch (Exception e) {;} // author
			try { this.setAd_rs_reservation_id( trozos[4] ); } catch (Exception e) {;} // reservation_id
			try { this.setAd_rs_location_id( trozos[5] ); } catch (Exception e) {;} // location_id
			try { this.setAd_rs_LO_name( trozos[6] ); } catch (Exception e) {;} // LO_name
			try { this.setAd_rs_user_id( trozos[7] ); } catch (Exception e) {;} // user_id
			try { this.setAd_rs_US_country_id( trozos[8] ); } catch (Exception e) {;} // US_country_id
			try { this.setAd_rs_US_nick( trozos[9] ); } catch (Exception e) {;} // US_nick
			try { this.setAd_rs_US_avatar( trozos[10] ); } catch (Exception e) {;} // US_avatar
			try { this.setAd_rs_US_is_admin( trozos[11] ); } catch (Exception e) {;} // US_is_admin
			try { this.setAd_rs_US_first_name( trozos[12] ); } catch (Exception e) {;} // US_first_name
			try { this.setAd_rs_US_last_name( trozos[13] ); } catch (Exception e) {;} // US_last_name
			try { this.setAd_rs_product_id( trozos[14] ); } catch (Exception e) {;} // product_id
			try { this.setAd_rs_PT_name( trozos[15] ); } catch (Exception e) {;} // PT_name
			try { this.setAd_rs_PT_deadline( trozos[16] ); } catch (Exception e) {;} // PT_deadline
			try { this.setAd_rs_PT_isPercent( trozos[17] ); } catch (Exception e) {;} // PT_isPercent
			try { this.setAd_rs_PT_amount( trozos[18] ); } catch (Exception e) {;} // PT_amount
			try { this.setAd_rs_PT_duration_minutes( trozos[19] ); } catch (Exception e) {;} // PT_duration_minutes
			try { this.setAd_rs_quantity( trozos[20] ); } catch (Exception e) {;} // quantity
			try { this.setAd_rs_product_id2( trozos[21] ); } catch (Exception e) {;} // product_id2
			try { this.setAd_rs_PT_name2( trozos[22] ); } catch (Exception e) {;} // PT_name2
			try { this.setAd_rs_PT_deadline2( trozos[23] ); } catch (Exception e) {;} // PT_deadline2
			try { this.setAd_rs_PT_isPercent2( trozos[24] ); } catch (Exception e) {;} // PT_isPercent2
			try { this.setAd_rs_PT_amount2( trozos[25] ); } catch (Exception e) {;} // PT_amount2
			try { this.setAd_rs_product_id3( trozos[26] ); } catch (Exception e) {;} // product_id3
			try { this.setAd_rs_PT_name3( trozos[27] ); } catch (Exception e) {;} // PT_name3
			try { this.setAd_rs_PT_deadline3( trozos[28] ); } catch (Exception e) {;} // PT_deadline3
			try { this.setAd_rs_PT_isPercent3( trozos[29] ); } catch (Exception e) {;} // PT_isPercent3
			try { this.setAd_rs_PT_amount3( trozos[30] ); } catch (Exception e) {;} // PT_amount3
			try { this.setAd_rs_amount( trozos[31] ); } catch (Exception e) {;} // amount
			try { this.setAd_rs_currency( trozos[32] ); } catch (Exception e) {;} // currency
			try { this.setAd_rs_phone( trozos[33] ); } catch (Exception e) {;} // phone
			try { this.setAd_rs_pay_status( trozos[34] ); } catch (Exception e) {;} // pay_status
			try { this.setAd_rs_start_date( trozos[35] ); } catch (Exception e) {;} // start_date
			try { this.setAd_rs_start_time( trozos[36] ); } catch (Exception e) {;} // start_time
			try { this.setAd_rs_duration_minutes( trozos[37] ); } catch (Exception e) {;} // duration_minutes
			try { this.setAd_rs_places( trozos[38] ); } catch (Exception e) {;} // places
			try { this.setAd_rs_coupon_id( trozos[39] ); } catch (Exception e) {;} // coupon_id
			try { this.setAd_rs_executed_at( trozos[40] ); } catch (Exception e) {;} // executed_at
			try { this.setAd_rs_note( trozos[41] ); } catch (Exception e) {;} // note
			try { this.setAd_rs_comment( trozos[42] ); } catch (Exception e) {;} // comment
			try { this.setAd_rs_json( trozos[43] ); } catch (Exception e) {;} // json
			
		}
	}
	////////////////////////////////////////////////////////////

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
	public String getAd_rs_US_country_id() {return ad_rs_US_country_id;}
	/** Set US_country_id*/
	public void setAd_rs_US_country_id(String ad_rs_US_country_id) {this.ad_rs_US_country_id = ad_rs_US_country_id;}

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
	public String getAd_rs_PT_amount() {return ad_rs_PT_amount;}
	/** Set PT_amount*/
	public void setAd_rs_PT_amount(String ad_rs_PT_amount) {this.ad_rs_PT_amount = ad_rs_PT_amount;}

	/** Get PT_duration_minutes*/
	public String getAd_rs_PT_duration_minutes() {return ad_rs_PT_duration_minutes;}
	/** Set PT_duration_minutes*/
	public void setAd_rs_PT_duration_minutes(String ad_rs_PT_duration_minutes) {this.ad_rs_PT_duration_minutes = ad_rs_PT_duration_minutes;}

	/** Get quantity*/
	public String getAd_rs_quantity() {return ad_rs_quantity;}
	/** Set quantity*/
	public void setAd_rs_quantity(String ad_rs_quantity) {this.ad_rs_quantity = ad_rs_quantity;}

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
	public String getAd_rs_PT_amount2() {return ad_rs_PT_amount2;}
	/** Set PT_amount2*/
	public void setAd_rs_PT_amount2(String ad_rs_PT_amount2) {this.ad_rs_PT_amount2 = ad_rs_PT_amount2;}

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
	public String getAd_rs_PT_amount3() {return ad_rs_PT_amount3;}
	/** Set PT_amount3*/
	public void setAd_rs_PT_amount3(String ad_rs_PT_amount3) {this.ad_rs_PT_amount3 = ad_rs_PT_amount3;}

	/** Get amount*/
	public String getAd_rs_amount() {return ad_rs_amount;}
	/** Set amount*/
	public void setAd_rs_amount(String ad_rs_amount) {this.ad_rs_amount = ad_rs_amount;}

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
	public String getAd_rs_duration_minutes() {return ad_rs_duration_minutes;}
	/** Set duration_minutes*/
	public void setAd_rs_duration_minutes(String ad_rs_duration_minutes) {this.ad_rs_duration_minutes = ad_rs_duration_minutes;}

	/** Get places*/
	public String getAd_rs_places() {return ad_rs_places;}
	/** Set places*/
	public void setAd_rs_places(String ad_rs_places) {this.ad_rs_places = ad_rs_places;}

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
