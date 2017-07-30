<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PmADDRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.pm_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.pm_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.pm_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.pm_author" />
			</md-input-container>
			<md-input-container>
				<label>coupon_id</label>
				<input type="text" ng-model="actionForm.pm_coupon_id" />
			</md-input-container>
			<md-input-container>
				<label>name</label>
				<input type="text" ng-model="actionForm.pm_name" />
			</md-input-container>
			<md-input-container>
				<label>uses_per_user</label>
				<input type="text" ng-model="actionForm.pm_uses_per_user" />
			</md-input-container>
			<md-input-container>
				<label>places</label>
				<input type="text" ng-model="actionForm.pm_places" />
			</md-input-container>
			<md-input-container>
				<label>location_id</label>
				<input type="text" ng-model="actionForm.pm_location_id" />
			</md-input-container>
			<md-input-container>
				<label>LO_name</label>
				<input type="text" ng-model="actionForm.pm_LO_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>product_id</label>
				<input type="text" ng-model="actionForm.pm_product_id" />
			</md-input-container>
			<md-input-container>
				<label>PT_name</label>
				<input type="text" ng-model="actionForm.pm_PT_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>PT_whoCanSelect_AFU</label>
				<input type="text" ng-model="actionForm.pm_PT_whoCanSelect_AFU" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>PT_deadline</label>
				<input type="text" ng-model="actionForm.pm_PT_deadline" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>product_id_promo</label>
				<input type="text" ng-model="actionForm.pm_product_id_promo" />
			</md-input-container>
			<md-input-container>
				<label>deadline</label>
				<input type="text" ng-model="actionForm.pm_deadline" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.pm_json" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="agregar()"/>
    </div>
</div>

