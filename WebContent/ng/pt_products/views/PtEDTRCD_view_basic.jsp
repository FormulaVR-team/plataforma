<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PtEDTRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.pt_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.pt_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.pt_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.pt_author" />
			</md-input-container>
			<md-input-container>
				<label>product_id</label>
				<input type="text" ng-model="actionForm.pt_product_id" />
			</md-input-container>
			<md-input-container>
				<label>name</label>
				<input type="text" ng-model="actionForm.pt_name" />
			</md-input-container>
			<md-input-container>
				<label>whoCanSelect_AFU</label>
				<input type="text" ng-model="actionForm.pt_whoCanSelect_AFU" />
			</md-input-container>
			<md-input-container>
				<label>deadline</label>
				<input type="text" ng-model="actionForm.pt_deadline" />
			</md-input-container>
			<md-input-container>
				<label>isPercent</label>
				<input type="text" ng-model="actionForm.pt_isPercent" />
			</md-input-container>
			<md-input-container>
				<label>amount</label>
				<input type="text" ng-model="actionForm.pt_amount" />
			</md-input-container>
			<md-input-container>
				<label>currency</label>
				<input type="text" ng-model="actionForm.pt_currency" />
			</md-input-container>
			<md-input-container>
				<label>duration_minutes</label>
				<input type="text" ng-model="actionForm.pt_duration_minutes" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.pt_json" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-danger"  data-dismiss="modal" value="Suprimir" ng-click="suprimir()"/>
        <input type="button" class="btn btn-warning" data-dismiss="modal" value="Copiar" ng-click="copiar()"/>
        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Aceptar" ng-click="cambiar()"/>
    </div>
</div>

