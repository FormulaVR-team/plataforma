<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="TtADDRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.tt_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.tt_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.tt_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.tt_author" />
			</md-input-container>
			<md-input-container>
				<label>location_id</label>
				<input type="text" ng-model="actionForm.tt_location_id" />
			</md-input-container>
			<md-input-container>
				<label>day_type</label>
				<input type="text" ng-model="actionForm.tt_day_type" />
			</md-input-container>
			<md-input-container>
				<label>start_time</label>
				<input type="text" ng-model="actionForm.tt_start_time" />
			</md-input-container>
			<md-input-container>
				<label>duration_minutes</label>
				<input type="text" ng-model="actionForm.tt_duration_minutes" />
			</md-input-container>
			<md-input-container>
				<label>isBlocked</label>
				<input type="text" ng-model="actionForm.tt_isBlocked" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.tt_json" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="agregar()"/>
    </div>
</div>

