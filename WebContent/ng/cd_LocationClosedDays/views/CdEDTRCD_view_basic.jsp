<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="CdEDTRCD_form">
		<div class="row margin-0">


			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>sincro</label>
				<input type="text" ng-model="actionForm.cd_sincro" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>mark</label>
				<input type="text" ng-model="actionForm.cd_mark" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.cd_is_deleted" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>author</label>
				<input type="text" ng-model="actionForm.cd_author" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>location_id</label>
				<input type="text" ng-model="actionForm.cd_location_id" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>closed_day_aaaa_mm_dd</label>
				<input type="text" ng-model="actionForm.cd_closed_day_aaaa_mm_dd" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>json</label>
				<input type="text" ng-model="actionForm.cd_json" />
			</md-input-container>
			</div>


    </div>
	<div class="row margin-0">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 pull-right">
			<md-button class="md-raised md-primary btn-mfw" ng-click="cambiar()">Aceptar</md-button>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 pull-right">
			<md-button class="md-raised md-accent btn-mfw" ng-click="copiar()">Copiar</md-button>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 pull-right">
			<md-button class="md-raised md-warn btn-mfw" ng-click="suprimir()">Suprimir</md-button>
		</div>
	</div>
</div>

