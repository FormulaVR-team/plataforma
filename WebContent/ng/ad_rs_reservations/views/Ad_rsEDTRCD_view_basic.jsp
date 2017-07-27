<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="Ad_rsEDTRCD_form">
    <div class="modal-header">

		<div class="{{actionForm.ad_rs_executed_at === '' ? '' : 'hide'}}">
			<!-- No está ejecutada la reserva -->
	        <div class="{{actionForm.ad_rs_pay_status === 'CASH_PDT' ? 'hide' : ''}}">
				<md-button class="md-raised md-accent" data-dismiss="modal" data-toggle="modal" ng-click="executeReservation(null)" ><md-icon> gavel </md-icon>&nbsp;&nbsp;Ejecutar</md-button>
	        </div>
			<!-- Se quiere pagar en el establecimiento-->
	        <div class="{{actionForm.ad_rs_pay_status === 'CASH_PDT' ? '' : 'hide'}}">
				<md-button class="md-raised md-warn" data-dismiss="modal" data-toggle="modal" data-target="#ad_rsDSPFIL_CONFIRM_modal" ><md-icon> gavel </md-icon>&nbsp;&nbsp;Ejecutar</md-button>
	        </div>
		</div>

		<md-input-container>
			<label>sincro</label>
			<input type="text" ng-model="actionForm.ad_rs_sincro" />
		</md-input-container>
		<md-input-container>
			<label>mark</label>
			<input type="text" ng-model="actionForm.ad_rs_mark" />
		</md-input-container>
		<md-input-container>
			<label>is_deleted</label>
			<input type="text" ng-model="actionForm.ad_rs_is_deleted" />
		</md-input-container>
		<md-input-container>
			<label>author</label>
			<input type="text" ng-model="actionForm.ad_rs_author" />
		</md-input-container>
		<md-input-container>
			<label>reservation_id</label>
			<input type="text" ng-model="actionForm.ad_rs_reservation_id" />
		</md-input-container>
		<md-input-container>
			<label>location_id</label>
			<input type="text" ng-model="actionForm.ad_rs_location_id" />
		</md-input-container>
		<md-input-container>
			<label>LO_name</label>
			<input type="text" ng-model="actionForm.ad_rs_LO_name" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>user_id</label>
			<input type="text" ng-model="actionForm.ad_rs_user_id" />
		</md-input-container>
		<md-input-container>
			<label>US_country_id</label>
			<input type="text" ng-model="actionForm.ad_rs_US_country_id" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>US_nick</label>
			<input type="text" ng-model="actionForm.ad_rs_US_nick" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>US_avatar</label>
			<input type="text" ng-model="actionForm.ad_rs_US_avatar" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>US_is_admin</label>
			<input type="text" ng-model="actionForm.ad_rs_US_is_admin" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>US_first_name</label>
			<input type="text" ng-model="actionForm.ad_rs_US_first_name" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>US_last_name</label>
			<input type="text" ng-model="actionForm.ad_rs_US_last_name" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>product_id</label>
			<input type="text" ng-model="actionForm.ad_rs_product_id" />
		</md-input-container>
		<md-input-container>
			<label>PT_name</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_name" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_deadline</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_deadline" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_isPercent</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_isPercent" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_amount</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_amount" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_duration_minutes</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_duration_minutes" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>quantity</label>
			<input type="text" ng-model="actionForm.ad_rs_quantity" />
		</md-input-container>
		<md-input-container>
			<label>product_id2</label>
			<input type="text" ng-model="actionForm.ad_rs_product_id2" />
		</md-input-container>
		<md-input-container>
			<label>PT_name2</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_name2" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_deadline2</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_deadline2" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_isPercent2</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_isPercent2" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_amount2</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_amount2" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>product_id3</label>
			<input type="text" ng-model="actionForm.ad_rs_product_id3" />
		</md-input-container>
		<md-input-container>
			<label>PT_name3</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_name3" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_deadline3</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_deadline3" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_isPercent3</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_isPercent3" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>PT_amount3</label>
			<input type="text" ng-model="actionForm.ad_rs_PT_amount3" disabled="disabled" />
		</md-input-container>
		<md-input-container>
			<label>amount</label>
			<input type="text" ng-model="actionForm.ad_rs_amount" />
		</md-input-container>
		<md-input-container>
			<label>currency</label>
			<input type="text" ng-model="actionForm.ad_rs_currency" />
		</md-input-container>
		<md-input-container>
			<label>phone</label>
			<input type="text" ng-model="actionForm.ad_rs_phone" />
		</md-input-container>
		<md-input-container>
			<label>pay_status</label>
			<input type="text" ng-model="actionForm.ad_rs_pay_status" />
		</md-input-container>
		<md-input-container>
			<label>start_date</label>
			<input type="text" ng-model="actionForm.ad_rs_start_date" />
		</md-input-container>
		<md-input-container>
			<label>start_time</label>
			<input type="text" ng-model="actionForm.ad_rs_start_time" />
		</md-input-container>
		<md-input-container>
			<label>duration_minutes</label>
			<input type="text" ng-model="actionForm.ad_rs_duration_minutes" />
		</md-input-container>
		<md-input-container>
			<label>places</label>
			<input type="text" ng-model="actionForm.ad_rs_places" />
		</md-input-container>
		<md-input-container>
			<label>coupon_id</label>
			<input type="text" ng-model="actionForm.ad_rs_coupon_id" />
		</md-input-container>
		<md-input-container>
			<label>executed_at</label>
			<input type="text" ng-model="actionForm.ad_rs_executed_at" />
		</md-input-container>
		<md-input-container>
			<label>note</label>
			<input type="text" ng-model="actionForm.ad_rs_note" />
		</md-input-container>
		<md-input-container>
			<label>comment</label>
			<input type="text" ng-model="actionForm.ad_rs_comment" />
		</md-input-container>
		<md-input-container>
			<label>json</label>
			<input type="text" ng-model="actionForm.ad_rs_json" />
		</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-danger"  data-dismiss="modal" value="Suprimir" ng-click="suprimir()"/>
        <input type="button" class="btn btn-warning" data-dismiss="modal" value="Copiar" ng-click="copiar()"/>
        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Aceptar" ng-click="cambiar()"/>
    </div>
</div>

