<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="EsEDTRCD_form">
    <div class="modal-header">

			<md-input-container>
				<label>event_id</label>
				<input type="text" ng-model="actionForm.es_event_id" />
			</md-input-container>
			<md-input-container>
				<label>EV_name</label>
				<input type="text" ng-model="actionForm.es_EV_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>EV_location_id</label>
				<input type="text" ng-model="actionForm.es_EV_location_id" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>LO_name</label>
				<input type="text" ng-model="actionForm.es_LO_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>inscription_user_id</label>
				<input type="text" ng-model="actionForm.es_inscription_user_id" />
			</md-input-container>
			<md-input-container>
				<label>first_name</label>
				<input type="text" ng-model="actionForm.es_first_name" />
			</md-input-container>
			<md-input-container>
				<label>last_name</label>
				<input type="text" ng-model="actionForm.es_last_name" />
			</md-input-container>
			<md-input-container>
				<label>phone</label>
				<input type="text" ng-model="actionForm.es_phone" />
			</md-input-container>
			<md-input-container>
				<label>amount</label>
				<input type="text" ng-model="actionForm.es_amount" />
			</md-input-container>
			<md-input-container>
				<label>currency</label>
				<input type="text" ng-model="actionForm.es_currency" />
			</md-input-container>
			<md-input-container>
				<label>tpv_order</label>
				<input type="text" ng-model="actionForm.es_tpv_order" />
			</md-input-container>
			<md-input-container>
				<label>pay_status</label>
				<input type="text" ng-model="actionForm.es_pay_status" />
			</md-input-container>

    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-danger"  data-dismiss="modal" value="Suprimir" ng-click="suprimir()"/>
        <input type="button" class="btn btn-warning" data-dismiss="modal" value="Copiar" ng-click="copiar()"/>
        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Aceptar" ng-click="cambiar()"/>
    </div>
</div>

