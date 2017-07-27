<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PrEDTRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.pr_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.pr_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.pr_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.pr_author" />
			</md-input-container>
			<md-input-container>
				<label>location_id</label>
				<input type="text" ng-model="actionForm.pr_location_id" />
			</md-input-container>
			<md-input-container>
				<label>LO_name</label>
				<input type="text" ng-model="actionForm.pr_LO_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>product_id</label>
				<input type="text" ng-model="actionForm.pr_product_id" />
			</md-input-container>
			<md-input-container>
				<label>PT_name</label>
				<input type="text" ng-model="actionForm.pr_PT_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>product_id_promo</label>
				<input type="text" ng-model="actionForm.pr_product_id_promo" />
			</md-input-container>
			<md-input-container>
				<label>PT_name_promo</label>
				<input type="text" ng-model="actionForm.pr_PT_name_promo" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>deadline</label>
				<input type="text" ng-model="actionForm.pr_deadline" />
			</md-input-container>
			<md-input-container>
				<label>min_quantity</label>
				<input type="text" ng-model="actionForm.pr_min_quantity" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-danger"  data-dismiss="modal" value="Suprimir" ng-click="suprimir()"/>
        <input type="button" class="btn btn-warning" data-dismiss="modal" value="Copiar" ng-click="copiar()"/>
        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Aceptar" ng-click="cambiar()"/>
    </div>
</div>

