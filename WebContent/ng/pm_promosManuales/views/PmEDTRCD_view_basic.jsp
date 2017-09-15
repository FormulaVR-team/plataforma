<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PmEDTRCD_form">
	<div class="row margin-0">


			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>sincro</label>
				<input type="text" ng-model="actionForm.pm_sincro" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>mark</label>
				<input type="text" ng-model="actionForm.pm_mark" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.pm_is_deleted" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>author</label>
				<input type="text" ng-model="actionForm.pm_author" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>coupon_id</label>
				<input type="text" ng-model="actionForm.pm_coupon_id" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>name</label>
				<input type="text" ng-model="actionForm.pm_name" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>uses_per_user</label>
				<input type="text" ng-model="actionForm.pm_uses_per_user" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>places</label>
				<input type="text" ng-model="actionForm.pm_places" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>location_id</label>
				<input type="text" ng-model="actionForm.pm_location_id" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>LO_name</label>
				<input type="text" ng-model="actionForm.pm_LO_name" disabled="disabled" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>product_id</label>
				<input type="text" ng-model="actionForm.pm_product_id" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>PT_name</label>
				<input type="text" ng-model="actionForm.pm_PT_name" disabled="disabled" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>PT_whoCanSelect_AFU</label>
				<input type="text" ng-model="actionForm.pm_PT_whoCanSelect_AFU" disabled="disabled" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>PT_deadline</label>
				<input type="text" ng-model="actionForm.pm_PT_deadline" disabled="disabled" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>product_id_promo</label>
				<input type="text" ng-model="actionForm.pm_product_id_promo" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>deadline</label>
				<input type="text" ng-model="actionForm.pm_deadline" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>json</label>
				<input type="text" ng-model="actionForm.pm_json" />
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

