<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="TsADDRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.ts_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.ts_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.ts_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.ts_author" />
			</md-input-container>
			<md-input-container>
				<label>reservation_id</label>
				<input type="text" ng-model="actionForm.ts_reservation_id" />
			</md-input-container>
			<md-input-container>
				<label>RS_user_id</label>
				<input type="text" ng-model="actionForm.ts_RS_user_id" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>RS_location_id</label>
				<input type="text" ng-model="actionForm.ts_RS_location_id" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>RS_start_date</label>
				<input type="text" ng-model="actionForm.ts_RS_start_date" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>RS_start_time</label>
				<input type="text" ng-model="actionForm.ts_RS_start_time" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>RS_pay_status</label>
				<input type="text" ng-model="actionForm.ts_RS_pay_status" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>RS_product_id</label>
				<input type="text" ng-model="actionForm.ts_RS_product_id" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>RS_quantity</label>
				<input type="text" ng-model="actionForm.ts_RS_quantity" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>RS_duration_minutes</label>
				<input type="text" ng-model="actionForm.ts_RS_duration_minutes" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>RS_places</label>
				<input type="text" ng-model="actionForm.ts_RS_places" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>start_date</label>
				<input type="text" ng-model="actionForm.ts_start_date" />
			</md-input-container>
			<md-input-container>
				<label>start_time</label>
				<input type="text" ng-model="actionForm.ts_start_time" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.ts_json" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="agregar()"/>
    </div>
</div>

