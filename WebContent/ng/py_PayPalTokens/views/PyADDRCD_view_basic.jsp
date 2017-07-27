<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PyADDRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.py_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.py_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.py_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.py_author" />
			</md-input-container>
			<md-input-container>
				<label>user_id</label>
				<input type="text" ng-model="actionForm.py_user_id" />
			</md-input-container>
			<md-input-container>
				<label>US_first_name</label>
				<input type="text" ng-model="actionForm.py_US_first_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>US_last_name</label>
				<input type="text" ng-model="actionForm.py_US_last_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>reservation_id</label>
				<input type="text" ng-model="actionForm.py_reservation_id" />
			</md-input-container>
			<md-input-container>
				<label>paypal_token_id</label>
				<input type="text" ng-model="actionForm.py_paypal_token_id" />
			</md-input-container>
			<md-input-container>
				<label>paypal_usr</label>
				<input type="text" ng-model="actionForm.py_paypal_usr" />
			</md-input-container>
			<md-input-container>
				<label>paypal_pwd</label>
				<input type="text" ng-model="actionForm.py_paypal_pwd" />
			</md-input-container>
			<md-input-container>
				<label>paypal_signature</label>
				<input type="text" ng-model="actionForm.py_paypal_signature" />
			</md-input-container>
			<md-input-container>
				<label>stsProceso</label>
				<input type="text" ng-model="actionForm.py_stsProceso" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.py_json" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="agregar()"/>
    </div>
</div>

