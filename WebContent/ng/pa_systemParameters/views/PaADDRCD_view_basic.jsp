<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PaADDRCD_form">
		<div class="row margin-0">


			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>sincro</label>
				<input type="text" ng-model="actionForm.pa_sincro" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>mark</label>
				<input type="text" ng-model="actionForm.pa_mark" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.pa_is_deleted" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>author</label>
				<input type="text" ng-model="actionForm.pa_author" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>group_id</label>
				<input type="text" ng-model="actionForm.pa_group_id" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>key</label>
				<input type="text" ng-model="actionForm.pa_key" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>value</label>
				<input type="text" ng-model="actionForm.pa_value" />
			</md-input-container>
			</div>


    </div>
	<div class="row margin-0">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 pull-right">
			<md-button class="md-raised md-primary btn-mfw" ng-click="agregar()">Aceptar</md-button>
		</div>
	</div>
</div>

