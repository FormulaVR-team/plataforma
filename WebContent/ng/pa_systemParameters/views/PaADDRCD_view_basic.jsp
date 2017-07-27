<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PaADDRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.pa_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.pa_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.pa_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.pa_author" />
			</md-input-container>
			<md-input-container>
				<label>group_id</label>
				<input type="text" ng-model="actionForm.pa_group_id" />
			</md-input-container>
			<md-input-container>
				<label>key</label>
				<input type="text" ng-model="actionForm.pa_key" />
			</md-input-container>
			<md-input-container>
				<label>value</label>
				<input type="text" ng-model="actionForm.pa_value" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="agregar()"/>
    </div>
</div>

