<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="EvDSPFIL_form">
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
						<md-button class="button-close md-fab md-mini" ng-click="initReg()" data-toggle="modal" data-target="#evDSPFIL_ADDRCD_modal" title="<bean:message key="common.client.nuevo"/>"><md-icon> add </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" onclick="$('#filterIcon').toggleClass('rotate');$('#evFilterBox').slideToggle();" title="<bean:message key="common.client.filtrar"/>"><md-icon id="filterIcon"> filter_list </md-icon></md-button>
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
						events
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
				<div id="evFilterBox" style="display:none; border: black solid 1px;">
					<md-button class=" md-raised md-warn md-button md-ink-ripple" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#evFilterBox').slideToggle();">Aplicar</md-button>
					<div class="alert alert-default fade in" style="display: block; padding: 0; margin: 0;">

					<md-input-container>
						<label>sincro</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_sincro" />
					</md-input-container>
					<md-input-container>
						<label>mark</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_mark" />
					</md-input-container>
					<md-input-container>
						<label>is_deleted</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_is_deleted" />
					</md-input-container>
					<md-input-container>
						<label>author</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_author" />
					</md-input-container>
					<md-input-container>
						<label>event_id</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_event_id" />
					</md-input-container>
					<md-input-container>
						<label>location_id</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_location_id" />
					</md-input-container>
					<md-input-container>
						<label>LO_name</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_LO_name" />
					</md-input-container>
					<md-input-container>
						<label>name</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_name" />
					</md-input-container>
					<md-input-container>
						<label>max_inscriptions</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_max_inscriptions" />
					</md-input-container>
					<md-input-container>
						<label>amount</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_amount" />
					</md-input-container>
					<md-input-container>
						<label>currency</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_currency" />
					</md-input-container>
					<md-input-container>
						<label>deadline</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_deadline" />
					</md-input-container>
					<md-input-container>
						<label>comment</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_comment" />
					</md-input-container>
					<md-input-container>
						<label>date1</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_date1" />
					</md-input-container>
					<md-input-container>
						<label>date2</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_date2" />
					</md-input-container>
					<md-input-container>
						<label>date3</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_date3" />
					</md-input-container>
					<md-input-container>
						<label>date4</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_date4" />
					</md-input-container>
					<md-input-container>
						<label>json</label>
						<input type="text" ng-model="actionForm.ev_filtro.ev_json" />
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
				<th>location_id</th>
				<th>LO_name</th>
				<th>name</th>
				<th>max_inscriptions</th>
				<th>amount</th>
				<th>currency</th>
				<th>deadline</th>
				<th>comment</th>
				<th>date1</th>
				<th>date2</th>
				<th>date3</th>
				<th>date4</th>
				<th>json</th>
			</tr>

			<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#evDSPFIL_EDTRCD_modal">
				<td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td>
				<td>{{reg.ev_sincro}}&nbsp;</td>
				<td>{{reg.ev_mark}}&nbsp;</td>
				<td>{{reg.ev_is_deleted}}&nbsp;</td>
				<td>{{reg.ev_author}}&nbsp;</td>
				<td>{{reg.ev_event_id}}&nbsp;</td>
				<td>{{reg.ev_location_id}}&nbsp;</td>
				<td>{{reg.ev_LO_name}}&nbsp;</td>
				<td>{{reg.ev_name}}&nbsp;</td>
				<td>{{reg.ev_max_inscriptions}}&nbsp;</td>
				<td>{{reg.ev_amount}}&nbsp;</td>
				<td>{{reg.ev_currency}}&nbsp;</td>
				<td>{{reg.ev_deadline}}&nbsp;</td>
				<td>{{reg.ev_comment}}&nbsp;</td>
				<td>{{reg.ev_date1}}&nbsp;</td>
				<td>{{reg.ev_date2}}&nbsp;</td>
				<td>{{reg.ev_date3}}&nbsp;</td>
				<td>{{reg.ev_date4}}&nbsp;</td>
				<td>{{reg.ev_json}}&nbsp;</td>
			</tr>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="evDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: events</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/ev_events/views/EvADDRCD_view_basic.jsp'" />
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
  <div class="modal fade" id="evDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: events</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/ev_events/views/EvEDTRCD_view_basic.jsp'" />
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

