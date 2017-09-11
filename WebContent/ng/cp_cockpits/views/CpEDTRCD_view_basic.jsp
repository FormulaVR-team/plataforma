<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="CpEDTRCD_form">
	<div class="row margin-0">
<!-- 
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>sincro</label>
				<input type="text" ng-model="actionForm.cp_sincro" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>mark</label>
				<input type="text" ng-model="actionForm.cp_mark" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.cp_is_deleted" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>author</label>
				<input type="text" ng-model="actionForm.cp_author" />
			</md-input-container>
			</div>
 -->
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>location_id</label>
				<md-select placeholder="location_id" ng-model="$parent.aux_cp_location_id" md-on-close="" ng-model-options="{trackBy: '$value.value'}">
					<md-optgroup label="location_id">
						<md-option ng-value="item" ng-repeat="item in $parent.lst_lo">{{ item.displayName }}</md-option>
					</md-optgroup>			
				</md-select>
			</md-input-container>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>cockpit_id</label>
				<input type="text" ng-model="actionForm.cp_cockpit_id" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>serial_number</label>
				<input type="text" ng-model="actionForm.cp_serial_number" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>name</label>
				<input type="text" ng-model="actionForm.cp_name" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>isBlocked</label>
				<md-select placeholder="isBlocked" ng-model="actionForm.cp_isBlocked">
					<md-optgroup label="isBlocked">
						<md-option ng-repeat="item in lst_isBlocked" value="{{item}}">{{item}}</md-option>
					</md-optgroup>			
				</md-select>
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>asignation_order</label>
				<input type="text" ng-model="actionForm.cp_asignation_order" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>install_date</label>
				<input type="text" ng-model="actionForm.cp_install_date" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>reset_date_used</label>
				<input type="text" ng-model="actionForm.cp_reset_date_used" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>hours_used</label>
				<input type="text" ng-model="actionForm.cp_hours_used" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>note</label>
				<input type="text" ng-model="actionForm.cp_note" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>comment</label>
				<input type="text" ng-model="actionForm.cp_comment" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>observation</label>
				<input type="text" ng-model="actionForm.cp_observation" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>warning</label>
				<input type="text" ng-model="actionForm.cp_warning" />
			</md-input-container>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>contact_service</label>
				<input type="text" ng-model="actionForm.cp_contact_service" />
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

