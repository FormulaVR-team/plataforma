<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PyDSPFIL_form">
    <div class="modal-header">

		<div class="row">
			<div class="col-xs-12" style="padding: 0px; min-height: 40px;"><div dynamic="adminMenu"></div></div>
		</div>

		<div class="row">

		<div layout="row" layout-align="space-between stretch">

			<div>
				<!-- Botones inicio -->
				<div class="btn-group">
					<md-button class="button-close md-fab md-mini" ng-click="filtrar()" title="<bean:message key="common.client.refrescar"/>"><md-icon> autorenew </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="rtPg()" title="<bean:message key="common.client.retroceder"/>"><md-icon> skip_previous </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="avPg()" title="<bean:message key="common.client.avanzar"/>"><md-icon> skip_next </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="initReg()" data-toggle="modal" data-target="#pyDSPFIL_ADDRCD_modal" title="<bean:message key="common.client.nuevo"/>"><md-icon> add </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" onclick="$('#filterIcon').toggleClass('rotate');$('#pyFilterBox').slideToggle();" title="<bean:message key="common.client.filtrar"/>"><md-icon id="filterIcon"> filter_list </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="exportar()" title="<bean:message key="common.client.exportar"/>"><md-icon> file_download </md-icon></md-button>
				</div>
				<!-- Botones final -->
			</div>

			<div>
				<h3>
					PAGOS CON PAYPAL
					<span dynamic="exportLink"></span>
				</h3>
			</div>

			<div>
				<!-- <span>{{actionForm.logon_USR}}</span> -->
				<!-- Paginador inicio -->
				<div>
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

		<div>
			<!-- Caja de filtros inicio -->
			<div id="pyFilterBox" style="display:none; border: black solid 1px;">
				<md-button class=" md-raised md-warn md-button md-ink-ripple" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#pyFilterBox').slideToggle();">Aplicar</md-button>
				<div class="alert alert-default fade in" style="display: block; padding: 0; margin: 0;">

					<md-input-container>
						<label>sincro</label>
						<input type="text" ng-model="actionForm.py_filtro.py_sincro" />
					</md-input-container>
					<md-input-container>
						<label>mark</label>
						<input type="text" ng-model="actionForm.py_filtro.py_mark" />
					</md-input-container>
					<md-input-container>
						<label>is_deleted</label>
						<input type="text" ng-model="actionForm.py_filtro.py_is_deleted" />
					</md-input-container>
					<md-input-container>
						<label>author</label>
						<input type="text" ng-model="actionForm.py_filtro.py_author" />
					</md-input-container>
					<md-input-container>
						<label>user_id</label>
						<input type="text" ng-model="actionForm.py_filtro.py_user_id" />
					</md-input-container>
					<md-input-container>
						<label>US_first_name</label>
						<input type="text" ng-model="actionForm.py_filtro.py_US_first_name" />
					</md-input-container>
					<md-input-container>
						<label>US_last_name</label>
						<input type="text" ng-model="actionForm.py_filtro.py_US_last_name" />
					</md-input-container>
					<md-input-container>
						<label>reservation_id</label>
						<input type="text" ng-model="actionForm.py_filtro.py_reservation_id" />
					</md-input-container>
					<md-input-container>
						<label>paypal_token_id</label>
						<input type="text" ng-model="actionForm.py_filtro.py_paypal_token_id" />
					</md-input-container>
					<md-input-container>
						<label>paypal_usr</label>
						<input type="text" ng-model="actionForm.py_filtro.py_paypal_usr" />
					</md-input-container>
					<md-input-container>
						<label>paypal_pwd</label>
						<input type="text" ng-model="actionForm.py_filtro.py_paypal_pwd" />
					</md-input-container>
					<md-input-container>
						<label>paypal_signature</label>
						<input type="text" ng-model="actionForm.py_filtro.py_paypal_signature" />
					</md-input-container>
					<md-input-container>
						<label>stsProceso</label>
						<input type="text" ng-model="actionForm.py_filtro.py_stsProceso" />
					</md-input-container>
					<md-input-container>
						<label>json</label>
						<input type="text" ng-model="actionForm.py_filtro.py_json" />
					</md-input-container>

				</div>
			</div>
			<!-- Caja de filtros final -->
		</div>

	</div>

	<div class="row">
		<table class="table table-striped table-hover">

			<tr>
				<th><!-- {{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}} --></th>
				<th>sincro</th>
				<th>mark</th>
				<th>is_deleted</th>
				<th>author</th>
				<th>user_id</th>
				<th>US_first_name</th>
				<th>US_last_name</th>
				<th>reservation_id</th>
				<th>paypal_token_id</th>
				<th>paypal_usr</th>
				<th>paypal_pwd</th>
				<th>paypal_signature</th>
				<th>stsProceso</th>
				<th>json</th>
			</tr>

			<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#pyDSPFIL_EDTRCD_modal">
				<td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td>
				<td>{{reg.py_sincro}}&nbsp;</td>
				<td>{{reg.py_mark}}&nbsp;</td>
				<td>{{reg.py_is_deleted}}&nbsp;</td>
				<td>{{reg.py_author}}&nbsp;</td>
				<td>{{reg.py_user_id}}&nbsp;</td>
				<td>{{reg.py_US_first_name}}&nbsp;</td>
				<td>{{reg.py_US_last_name}}&nbsp;</td>
				<td>{{reg.py_reservation_id}}&nbsp;</td>
				<td>{{reg.py_paypal_token_id}}&nbsp;</td>
				<td>{{reg.py_paypal_usr}}&nbsp;</td>
				<td>{{reg.py_paypal_pwd}}&nbsp;</td>
				<td>{{reg.py_paypal_signature}}&nbsp;</td>
				<td>{{reg.py_stsProceso}}&nbsp;</td>
				<td>{{reg.py_json}}&nbsp;</td>
			</tr>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="pyDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: PayPalTokens</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/py_PayPalTokens/views/PyADDRCD_view_basic.jsp'" />
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
  <div class="modal fade" id="pyDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: PayPalTokens</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/py_PayPalTokens/views/PyEDTRCD_view_basic.jsp'" />
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
</div>

