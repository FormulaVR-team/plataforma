<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="AcEDTRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.ac_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.ac_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.ac_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.ac_author" />
			</md-input-container>
			<md-input-container>
				<label>serial</label>
				<input type="text" ng-model="actionForm.ac_serial" />
			</md-input-container>
			<md-input-container>
				<label>computername</label>
				<input type="text" ng-model="actionForm.ac_computername" />
			</md-input-container>
			<md-input-container>
				<label>filename</label>
				<input type="text" ng-model="actionForm.ac_filename" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.ac_json" />
			</md-input-container>
			<md-input-container>
				<label>aaaa_mm</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>aaaa_mm_dd</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>aaaa_mm_dd_hh</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd_hh" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>aaaa_mm_dd_hh_mm</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd_hh_mm" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>aaaa_mm_dd_hh_mm_ss</label>
				<input type="text" ng-model="actionForm.ac_aaaa_mm_dd_hh_mm_ss" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>content</label>
				<textarea rows="5" max-rows="5" style="width: 600px; background-color: silver;" ng-model="actionForm.ac_content" />
			</md-input-container>

    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-danger"  data-dismiss="modal" value="Suprimir" ng-click="suprimir()"/>
        <input type="button" class="btn btn-warning" data-dismiss="modal" value="Copiar" ng-click="copiar()"/>
        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Aceptar" ng-click="cambiar()"/>
    </div>
</div>

