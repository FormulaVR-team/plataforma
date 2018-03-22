<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="LpADDRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>card_id</label>
				<input type="text" ng-model="actionForm.lp_card_id" />
			</md-input-container>
			<md-input-container>
				<label>TJ_user_id</label>
				<input type="text" ng-model="actionForm.lp_TJ_user_id" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>TJ_qr_image_base64</label>
				<input type="text" ng-model="actionForm.lp_TJ_qr_image_base64" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.lp_json" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="agregar()"/>
    </div>
</div>

