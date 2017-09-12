<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="LgEDTRCD_form">
	<div class="row margin-0">

			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>serial</label>
				<input type="text" ng-model="actionForm.lg_serial" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>text_1</label>
				<input type="text" ng-model="actionForm.lg_text_1" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-8 col-md-6 col-lg-4">
			<md-input-container class="md-block">
				<label>text_2</label>
				<input type="text" ng-model="actionForm.lg_text_2" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>text_3</label>
				<input type="text" ng-model="actionForm.lg_text_3" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<md-input-container class="md-block">
				<label>json</label>
				<input type="text" ng-model="actionForm.lg_json" />
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

