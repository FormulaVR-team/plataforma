<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="UsADDRCD_form">

    <div>

	    <div>
				<h3>Datos de registro en FórmulaVR</h3>
		</div>

		<div layout="row" layout-align="start start">
			<div layout="column" layout-align="start start" class="col-sm-3">
				<md-input-container class="col-sm-12">
					<label>user_id</label>
					<input type="text" ng-model="actionForm.us_user_id" required readonly="readonly"/>
				</md-input-container>
				<md-input-container class="col-sm-12">
					<label>password</label>
					<input type="password" ng-model="actionForm.us_password" required />
				</md-input-container>
			</div>
			<div layout="column" layout-align="start start" class="col-sm-3">
				<md-input-container class="col-sm-12">
					<label>first_name</label>
					<input type="text" ng-model="actionForm.us_first_name" required />
				</md-input-container>
				<md-input-container class="col-sm-12">
					<label>last_name</label>
					<input type="text" ng-model="actionForm.us_last_name" required />
				</md-input-container>
				<md-input-container class="col-sm-12">
					<label>phone</label>
					<input type="text" ng-model="actionForm.us_phone" required />
				</md-input-container>
			</div>
			<div layout="column" layout-align="start start" class="col-sm-3">
				<md-input-container class="col-sm-12">
					<label>nick</label>
					<input type="text" ng-model="actionForm.us_nick" required />
				</md-input-container>
				<md-input-container class="col-sm-12">
					<label>avatar</label>
					<input type="text" ng-model="actionForm.us_avatar" />
				</md-input-container>
			</div>
			<div layout="row" layout-align="start start" class="col-sm-2">
				<md-input-container class="col-sm-12">
					<label>gender</label>
				    <md-input-container class="md-block">
				        <md-radio-group ng-model="actionForm.us_gender">
				            <md-radio-button name="gender" value="M">Hombre</md-radio-button><br>
				            <md-radio-button name="gender" value="F">Mujer</md-radio-button>
				        </md-radio-group>
				    </md-input-container>					
				</md-input-container>
				<md-input-container>
					<label>birth_day</label>
					<md-datepicker ng-model="aux_birth_day"></md-datepicker>
				</md-input-container>
			</div>
		</div>

    </div>

    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="agregar()"/>
    </div>

	<input type="hidden" ng-model="actionForm.us_json" />

</div>

