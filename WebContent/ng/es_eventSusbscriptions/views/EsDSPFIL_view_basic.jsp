<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="EsDSPFIL_form">
    <div class="modal-header">

		<div class="row">
			<div class="col-xs-12" style="padding: 0px; min-height: 80px;"><div dynamic="adminMenu"></div></div>
		</div>

		<div class="row" style="padding: 0">

			<div layout="row" layout-align="space-between stretch">

				<div class="col-xs-4" style="padding: 0">
					<!-- Botones inicio -->
					<div class="btn-group">
						<md-button class="button-close md-fab md-mini" ng-click="filtrar()" title="<bean:message key="common.client.refrescar"/>"><md-icon> autorenew </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" ng-click="rtPg()" title="<bean:message key="common.client.retroceder"/>"><md-icon> skip_previous </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" ng-click="avPg()" title="<bean:message key="common.client.avanzar"/>"><md-icon> skip_next </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" ng-click="initReg()" data-toggle="modal" data-target="#esDSPFIL_ADDRCD_modal" title="<bean:message key="common.client.nuevo"/>"><md-icon> add </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" onclick="$('#filterIcon').toggleClass('rotate');$('#esFilterBox').slideToggle();" title="<bean:message key="common.client.filtrar"/>"><md-icon id="filterIcon"> filter_list </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" ng-click="exportar()" title="<bean:message key="common.client.exportar"/>"><md-icon> file_download </md-icon></md-button>
						&nbsp;&nbsp;
						<md-button class="button-close md-fab md-mini" ng-click="desMarcarTodo()" title="desmarcar todo"><md-icon> clear </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" ng-click="marcarTodo()" title="Marcar todo"><md-icon> done_all </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" ng-click="marcados_suprimir()" title="Suprimir marcados"><md-icon> delete </md-icon></md-button>
						&nbsp;&nbsp;
					</div>
					<!-- Botones final -->
				</div>

				<div class="col-xs-3" style="padding: 0">
					<h3>
						eventSusbscriptions
						<span dynamic="exportLink"></span>
					</h3>
				</div>

				<div class="col-xs-5" style="padding: 0">
					<!-- <span>{{actionForm.logon_USR}}</span> -->
					<!-- Paginador inicio -->
					<div class="pull-right">
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
				<div id="esFilterBox" style="display:none; border: black solid 1px;">
					<md-button class=" md-raised md-warn md-button md-ink-ripple" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#esFilterBox').slideToggle();">Aplicar</md-button>
					<div class="alert alert-default fade in" style="display: block; padding: 0; margin: 0;">

					<md-input-container>
						<label>sincro</label>
						<input type="text" ng-model="actionForm.es_filtro.es_sincro" />
					</md-input-container>
					<md-input-container>
						<label>mark</label>
						<input type="text" ng-model="actionForm.es_filtro.es_mark" />
					</md-input-container>
					<md-input-container>
						<label>is_deleted</label>
						<input type="text" ng-model="actionForm.es_filtro.es_is_deleted" />
					</md-input-container>
					<md-input-container>
						<label>author</label>
						<input type="text" ng-model="actionForm.es_filtro.es_author" />
					</md-input-container>
					<md-input-container>
						<label>event_id</label>
						<input type="text" ng-model="actionForm.es_filtro.es_event_id" />
					</md-input-container>
					<md-input-container>
						<label>EV_locaition_id</label>
						<input type="text" ng-model="actionForm.es_filtro.es_EV_locaition_id" />
					</md-input-container>
					<md-input-container>
						<label>LO_name</label>
						<input type="text" ng-model="actionForm.es_filtro.es_LO_name" />
					</md-input-container>
					<md-input-container>
						<label>inscription_user_id</label>
						<input type="text" ng-model="actionForm.es_filtro.es_inscription_user_id" />
					</md-input-container>
					<md-input-container>
						<label>first_name</label>
						<input type="text" ng-model="actionForm.es_filtro.es_first_name" />
					</md-input-container>
					<md-input-container>
						<label>last_name</label>
						<input type="text" ng-model="actionForm.es_filtro.es_last_name" />
					</md-input-container>
					<md-input-container>
						<label>phone</label>
						<input type="text" ng-model="actionForm.es_filtro.es_phone" />
					</md-input-container>
					<md-input-container>
						<label>amount</label>
						<input type="text" ng-model="actionForm.es_filtro.es_amount" />
					</md-input-container>
					<md-input-container>
						<label>currency</label>
						<input type="text" ng-model="actionForm.es_filtro.es_currency" />
					</md-input-container>
					<md-input-container>
						<label>pay_status</label>
						<input type="text" ng-model="actionForm.es_filtro.es_pay_status" />
					</md-input-container>
					<md-input-container>
						<label>json</label>
						<input type="text" ng-model="actionForm.es_filtro.es_json" />
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
				<th>event_id</th>
				<th>EV_locaition_id</th>
				<th>LO_name</th>
				<th>inscription_user_id</th>
				<th>first_name</th>
				<th>last_name</th>
				<th>phone</th>
				<th>amount</th>
				<th>currency</th>
				<th>pay_status</th>
				<th>json</th>
			</tr>

			<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#esDSPFIL_EDTRCD_modal">
				<td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td>
				<td>{{reg.es_sincro}}&nbsp;</td>
				<td>{{reg.es_mark}}&nbsp;</td>
				<td>{{reg.es_is_deleted}}&nbsp;</td>
				<td>{{reg.es_author}}&nbsp;</td>
				<td>{{reg.es_event_id}}&nbsp;</td>
				<td>{{reg.es_EV_locaition_id}}&nbsp;</td>
				<td>{{reg.es_LO_name}}&nbsp;</td>
				<td>{{reg.es_inscription_user_id}}&nbsp;</td>
				<td>{{reg.es_first_name}}&nbsp;</td>
				<td>{{reg.es_last_name}}&nbsp;</td>
				<td>{{reg.es_phone}}&nbsp;</td>
				<td>{{reg.es_amount}}&nbsp;</td>
				<td>{{reg.es_currency}}&nbsp;</td>
				<td>{{reg.es_pay_status}}&nbsp;</td>
				<td>{{reg.es_json}}&nbsp;</td>
			</tr>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="esDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: eventSusbscriptions</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/es_eventSusbscriptions/views/EsADDRCD_view_basic.jsp'" />
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
  <div class="modal fade" id="esDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: eventSusbscriptions</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/es_eventSusbscriptions/views/EsEDTRCD_view_basic.jsp'" />
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

