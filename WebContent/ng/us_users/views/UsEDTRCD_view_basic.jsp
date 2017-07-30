<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="UsEDTRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>


			<md-input-container>
				<label>sincro</label>
				<input type="text" ng-model="actionForm.us_sincro" />
			</md-input-container>
			<md-input-container>
				<label>mark</label>
				<input type="text" ng-model="actionForm.us_mark" />
			</md-input-container>
			<md-input-container>
				<label>is_deleted</label>
				<input type="text" ng-model="actionForm.us_is_deleted" />
			</md-input-container>
			<md-input-container>
				<label>author</label>
				<input type="text" ng-model="actionForm.us_author" />
			</md-input-container>
			<md-input-container>
				<label>user_id</label>
				<input type="text" ng-model="actionForm.us_user_id" />
			</md-input-container>
			<md-input-container>
				<label>role_id</label>
				<input type="text" ng-model="actionForm.us_role_id" />
			</md-input-container>
			<md-input-container>
				<label>hash_code</label>
				<input type="text" ng-model="actionForm.us_hash_code" />
			</md-input-container>

   			<!-- Selector PAIS -->
			<md-input-container>
				<label>País</label>
				<md-select placeholder="Cambiar país" ng-model="$parent.aux_us_country_id" md-on-open="country_id_onOpen()" ng-model-options="{trackBy: '$value.value'}" required>
					<md-optgroup label="Paises">
						<md-option ng-value="item" ng-repeat="item in lst_ps">
							<img width="40px" ng-src="{{item.flag}}" ><img>
							&nbsp;{{item.displayName}}
						</md-option>
					</md-optgroup>			
				</md-select>
    		</md-input-container>
    		<!-- Selector PAIS fin -->
			
			<md-input-container>
				<label>PS_name</label>
				<input type="text" ng-model="actionForm.us_PS_name" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>PS_flag_base64</label>
				<input type="text" ng-model="actionForm.us_PS_flag_base64" disabled="disabled" />
			</md-input-container>
			<md-input-container>
				<label>nick</label>
				<input type="text" ng-model="actionForm.us_nick" />
			</md-input-container>
			<md-input-container>
				<label>password</label>
				<input type="text" ng-model="actionForm.us_password" />
			</md-input-container>
			<md-input-container>
				<label>first_name</label>
				<input type="text" ng-model="actionForm.us_first_name" />
			</md-input-container>
			<md-input-container>
				<label>last_name</label>
				<input type="text" ng-model="actionForm.us_last_name" />
			</md-input-container>
			<md-input-container>
				<label>phone</label>
				<input type="text" ng-model="actionForm.us_phone" />
			</md-input-container>
			<md-input-container>
				<label>gender</label>
				<input type="text" ng-model="actionForm.us_gender" />
			</md-input-container>
			<md-input-container>
				<label>birth_day</label>
				<input type="text" ng-model="actionForm.us_birth_day" />
			</md-input-container>
			<md-input-container>
				<label>avatar</label>
				<input type="text" ng-model="actionForm.us_avatar" />
			</md-input-container>
			<md-input-container>
				<label>json</label>
				<input type="text" ng-model="actionForm.us_json" />
			</md-input-container>


    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-danger"  data-dismiss="modal" value="Suprimir" ng-click="suprimir()"/>
        <input type="button" class="btn btn-warning" data-dismiss="modal" value="Copiar" ng-click="copiar()"/>
        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Aceptar" ng-click="cambiar()"/>
    </div>
</div>

