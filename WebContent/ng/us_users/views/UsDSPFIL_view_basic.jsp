<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="UsDSPFIL_form">
			
	<h1 class="page-title">USUARIOS</h1>

	<div class="row">
		<div class="col-xs-12 col-sm-6">
			<%-- Botones inicio --%>
			<div class="btn-group" style="margin-top: 15px;margin-bottom: 10px;">
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="filtrar()">
					<md-tooltip><bean:message key="common.client.refrescar"/></md-tooltip>
					<md-icon> autorenew </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="rtPg()">
					<md-tooltip><bean:message key="common.client.retroceder"/></md-tooltip>
					<md-icon> skip_previous </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="avPg()">
					<md-tooltip><bean:message key="common.client.avanzar"/></md-tooltip>
					<md-icon> skip_next </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#usDSPFIL_ADDRCD_modal">
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#usFilterBox').slideToggle();">
					<md-tooltip><bean:message key="common.client.filtrar"/></md-tooltip>
					<md-icon id="filterIcon"> filter_list </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="exportar()">
					<md-tooltip><bean:message key="common.client.exportar"/></md-tooltip>
					<md-icon> file_download </md-icon></md-button>
			</div>
			<!-- Botones final -->
		</div>

		<div class="col-xs-12 col-sm-6 text-right">
			<%-- Paginador inicio --%>
			<div>
				<span dynamic="exportLink"></span>
				<input type="hidden" ng-model="actionForm.filaInicioGrid" />
				<input type="hidden" ng-model="actionForm.filasTotales" />
				<span dynamic="txtHtmlPaginador"></span>
				<md-input-container>
					<md-select ng-model="aux_filasGrid" md-on-close="filtrar()" ng-model-options="{trackBy: '$value.value'}" placeholder="Filas lista">
						<md-optgroup label="M&aacute;x.filas lista">
							<md-option ng-value="item" ng-repeat="item in rowsPerPage">{{ item.displayName }}</md-option>
						</md-optgroup>			
					</md-select>
				</md-input-container>
			</div>
			<!-- Paginador final -->
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<%-- Caja de filtros inicio --%>
			<div id="usFilterBox" class="box" style="display:none;">
				<div class="row row-filter">
<!-- 
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>aaaa-mm-dd</label>
							<input type="text" ng-model="actionForm.us_filtro.us_sincro" />
						</md-input-container>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>mark</label>
							<input type="text" ng-model="actionForm.us_filtro.us_mark" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>is_deleted</label>
							<input type="text" ng-model="actionForm.us_filtro.us_is_deleted" />
						</md-input-container>
					</div>
 -->
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>author</label>
							<input type="text" ng-model="actionForm.us_filtro.us_author" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>user_id</label>
							<input type="text" ng-model="actionForm.us_filtro.us_user_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>role_id</label>
							<input type="text" ng-model="actionForm.us_filtro.us_role_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>hash_code</label>
							<input type="text" ng-model="actionForm.us_filtro.us_hash_code" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>nick</label>
							<input type="text" ng-model="actionForm.us_filtro.us_nick" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>password</label>
							<input type="text" ng-model="actionForm.us_filtro.us_password" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>first_name</label>
							<input type="text" ng-model="actionForm.us_filtro.us_first_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>last_name</label>
							<input type="text" ng-model="actionForm.us_filtro.us_last_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>phone</label>
							<input type="text" ng-model="actionForm.us_filtro.us_phone" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>gender</label>
							<input type="text" ng-model="actionForm.us_filtro.us_gender" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>birth_day</label>
							<input type="text" ng-model="actionForm.us_filtro.us_birth_day" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>avatar</label>
							<input type="text" ng-model="actionForm.us_filtro.us_avatar" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>json</label>
							<input type="text" ng-model="actionForm.us_filtro.us_json" />
						</md-input-container>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#usFilterBox').slideToggle();">Aplicar</md-button>
					</div>
				</div>
			</div>
			<!-- Caja de filtros final -->
		</div>
	</div>

	<div class="table-responsive box">
  	<table class="table table-striped table-hover">
  		<thead>
				<tr>
					<!-- <th>{{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}}</th> -->
					<th>sincro</th>
	<!--
	 				<th>mark</th>
					<th>is_deleted</th>
					<th>author</th>
	 -->
					<th>user_id</th>
					<th>role</th>
	 				<th>avatar</th>
	 				<th>country</th>
					<!-- <th>hash_code</th> -->
					<th>nick</th>
					<!-- <th>password</th> -->
					<th>first_name</th>
					<th>last_name</th>
					<th>phone</th>
					<th>gender</th>
					<th>birth_day</th>
					<!-- <th>json</th> -->
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#usDSPFIL_EDTRCD_modal">
					<!-- <td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td> -->
	 				<td>
						{{ reg.us_sincro === '' ? '' : (reg.us_sincro.substring(4,6) + '/' + reg.us_sincro.substring(2,4) + '/' + reg.us_sincro.substring(0,2) + ' ' + reg.us_sincro.substring(6,8) + ':' + reg.us_sincro.substring(8,10)) }}&nbsp;
	 				</td>
	<!-- 
					<td>{{reg.us_mark}}&nbsp;</td>
					<td>{{reg.us_is_deleted}}&nbsp;</td>
					<td>{{reg.us_author}}&nbsp;</td>
	 -->
					<td title="Conectarse como {{reg.us_user_id}}" style="padding: 0;">
						<md-button class="md-icon-button md-warn" onclick="event.stopPropagation();" ng-click="conectarComo(reg)"><md-icon>  </md-icon></md-button>
						{{reg.us_user_id}}
					</td>
					<td style="text-align:center;"><strong>{{reg.us_role_id}}</strong></td>
	 				<td style="padding:0;"><img src="./FvrServlet?ACC=getUsrImg&BIN&USR={{reg.us_user_id}}" ></img></td>
					<td style="padding:0;"><img src="{{reg.us_PS_flag_base64}}" ></img>&nbsp;{{reg.us_PS_name}}</td>
					<!-- <td>{{reg.us_hash_code}}&nbsp;</td> -->
					<td>{{reg.us_nick}}&nbsp;</td>
					<!-- <td>{{reg.us_password}}&nbsp;</td> -->
					<td>{{reg.us_first_name}}&nbsp;</td>
					<td>{{reg.us_last_name}}&nbsp;</td>
					<td>{{reg.us_phone}}&nbsp;</td>
					<td>{{reg.us_gender}}&nbsp;</td>
					<td>{{reg.us_birth_day}}&nbsp;</td>
					<!-- <td>{{reg.us_json}}&nbsp;</td> -->
				</tr>
			<tbody>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="usDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: users</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/us_users/views/UsADDRCD_view_basic.jsp'" />
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>

      </div>
      
    </div>
  </div>
</div>
<!-- Panel ADDRCD final -->	
	
<!-- Panel EDTRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="usDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: users</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/us_users/views/UsEDTRCD_view_basic.jsp'" />
        </div>
 
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>

      </div>
      
    </div>
  </div>
</div>
<!-- Panel EDTRCD final -->	

</div>

