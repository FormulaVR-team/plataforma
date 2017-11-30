<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="EvEDTRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.ev_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.ev_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.ev_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.ev_author" />
			</md-input-container>
			<md-input-container>
				<label>event_id</label>
				<input type="text" ng-model="actionForm.ev_event_id" />
			</md-input-container>
			<md-input-container>
				<label>location_id</label>
				<input type="text" ng-model="actionForm.ev_location_id" />
			</md-input-container>
			<md-input-container>
				<label>LO_name</label>
				<input type="text" ng-model="actionForm.ev_LO_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>name</label>
				<input type="text" ng-model="actionForm.ev_name" />
			</md-input-container>
			<md-input-container>
				<label>max_inscriptions</label>
				<input type="text" ng-model="actionForm.ev_max_inscriptions" />
			</md-input-container>
			<md-input-container>
				<label>amount</label>
				<input type="text" ng-model="actionForm.ev_amount" />
			</md-input-container>
			<md-input-container>
				<label>currency</label>
				<input type="text" ng-model="actionForm.ev_currency" />
			</md-input-container>
			<md-input-container>
				<label>deadline</label>
				<input type="text" ng-model="actionForm.ev_deadline" />
			</md-input-container>
			<md-input-container>
				<label>comment</label>
				<input type="text" ng-model="actionForm.ev_comment" />
			</md-input-container>
			<md-input-container>
				<label>date1</label>
				<input type="text" ng-model="actionForm.ev_date1" />
			</md-input-container>
			<md-input-container>
				<label>date2</label>
				<input type="text" ng-model="actionForm.ev_date2" />
			</md-input-container>
			<md-input-container>
				<label>date3</label>
				<input type="text" ng-model="actionForm.ev_date3" />
			</md-input-container>
			<md-input-container>
				<label>date4</label>
				<input type="text" ng-model="actionForm.ev_date4" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.ev_json" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-danger"  data-dismiss="modal" value="Suprimir" ng-click="suprimir()"/>
        <input type="button" class="btn btn-warning" data-dismiss="modal" value="Copiar" ng-click="copiar()"/>
        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Aceptar" ng-click="cambiar()"/>
    </div>
</div>

