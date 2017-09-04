<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="TjADDRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.tj_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.tj_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.tj_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.tj_author" />
			</md-input-container>
			<md-input-container>
				<label>card_id</label>
				<input type="text" ng-model="actionForm.tj_card_id" />
			</md-input-container>
			<md-input-container>
				<label>balance_initial</label>
				<input type="text" ng-model="actionForm.tj_balance_initial" />
			</md-input-container>
			<md-input-container>
				<label>balance_current</label>
				<input type="text" ng-model="actionForm.tj_balance_current" />
			</md-input-container>
			<md-input-container>
				<label>last_sale_amount</label>
				<input type="text" ng-model="actionForm.tj_last_sale_amount" />
			</md-input-container>
			<md-input-container>
				<label>last_sale_moment</label>
				<input type="text" ng-model="actionForm.tj_last_sale_moment" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="Aceptar" ng-click="agregar()"/>
    </div>
</div>

