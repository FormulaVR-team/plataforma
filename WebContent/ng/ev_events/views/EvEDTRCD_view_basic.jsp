<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="EvEDTRCD_form">
    <div class="modal-header">
<%-- 
	<logic:present name='roleKey'>
		<logic:equal name="roleKey" value="A">
			<span layout="row" layout-align="start start" style="background-color: lightyellow;">
				<md-input-container class="md-block">
					<label>sincro</label>
					<input type="text" ng-model="actionForm.ev_sincro" />
				</md-input-container>
				<md-input-container class="md-block">
					<label>mark</label>
					<input type="text" ng-model="actionForm.ev_mark" />
				</md-input-container>
				<md-input-container class="md-block">
					<label>is_deleted</label>
					<input type="text" ng-model="actionForm.ev_is_deleted" />
				</md-input-container>
				<md-input-container class="md-block">
					<label>author</label>
					<input type="text" ng-model="actionForm.ev_author" />
				</md-input-container>
				<md-input-container class="md-block">
					<label>json</label>
					<input type="text" ng-model="actionForm.ev_json" />
				</md-input-container>
			</span>
		</logic:equal>
	</logic:present>
 --%>

		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<!-- <label>location_id</label> -->
				<md-select placeholder="location_id" ng-model="$parent.aux_ev_location_id" md-on-close="filtrar()" ng-model-options="{trackBy: '$value.value'}">
					<md-optgroup label="location_id">
						<md-option ng-value="item" ng-repeat="item in lst_lo">{{ item.displayName }}</md-option>
					</md-optgroup>
				</md-select>
			</md-input-container>
		</div>

		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>event_id</label>
				<input type="text" ng-model="actionForm.ev_event_id" />
			</md-input-container>
		</div>
			
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>name</label>
				<input type="text" ng-model="actionForm.ev_name" />
			</md-input-container>
		</div>

		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>max_inscriptions</label>
				<input type="text" ng-model="actionForm.ev_max_inscriptions" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>amount</label>
				<input type="text" ng-model="actionForm.ev_amount" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>currency</label>
				<input type="text" ng-model="actionForm.ev_currency" />
			</md-input-container>

		</div>
		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<md-input-container class="md-block">
				<label>deadline</label>
				<input type="text" ng-model="actionForm.ev_deadline" />
			</md-input-container>
		</div>
<%-- 
			<md-input-container class="md-block">
				<label>comment</label>
				<input type="text" ng-model="actionForm.ev_comment" />
			</md-input-container>
			<md-input-container class="md-block">
				<label>date1</label>
				<input type="text" ng-model="actionForm.ev_date1" />
			</md-input-container>
			<md-input-container class="md-block">
				<label>date2</label>
				<input type="text" ng-model="actionForm.ev_date2" />
			</md-input-container>
			<md-input-container class="md-block">
				<label>date3</label>
				<input type="text" ng-model="actionForm.ev_date3" />
			</md-input-container>
			<md-input-container class="md-block">
				<label>date4</label>
				<input type="text" ng-model="actionForm.ev_date4" />
			</md-input-container>
--%>
    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-danger"  value="Suprimir" ng-click="suprimir()"/>
        <input type="button" class="btn btn-warning" value="Copiar" ng-click="copiar()"/>
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="cambiar()"/>
    </div>
</div>

