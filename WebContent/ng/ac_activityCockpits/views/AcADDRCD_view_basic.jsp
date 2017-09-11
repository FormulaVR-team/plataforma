<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="AcADDRCD_form">

	<div class="row margin-0">
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>sincro</label>
				<input type="text" ng-model="actionForm.ac_sincro" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>mark</label>
				<input type="text" ng-model="actionForm.ac_mark" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.ac_is_deleted" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>author</label>
				<input type="text" ng-model="actionForm.ac_author" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>serial</label>
				<input type="text" ng-model="actionForm.ac_serial" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>location_id</label>
				<input type="text" ng-model="actionForm.ac_location_id" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>LO_name</label>
				<input type="text" ng-model="actionForm.ac_LO_name" disabled="disabled" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>computername</label>
				<input type="text" ng-model="actionForm.ac_computername" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>filename</label>
				<input type="text" ng-model="actionForm.ac_filename" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>content</label>
				<input type="text" ng-model="actionForm.ac_content" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>json</label>
				<input type="text" ng-model="actionForm.ac_json" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>aaaa_mm</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm" disabled="disabled" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>aaaa_mm_dd</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd" disabled="disabled" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>aaaa_mm_dd_hh</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd_hh" disabled="disabled" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>aaaa_mm_dd_hh_m0</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd_hh_m0" disabled="disabled" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>aaaa_mm_dd_hh_mm</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd_hh_mm" disabled="disabled" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>aaaa_mm_dd_hh_mm_ss</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd_hh_mm_ss" disabled="disabled" />
			</md-input-container>
		</div>
	</div>
  <div class="row margin-0">
  	<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 pull-right">
  		<md-button class="md-raised md-primary btn-mfw" ng-click="agregar()">Aceptar</md-button>
  	</div>
  </div>
</div>

