<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="TsDSPFIL_form">
			
	<h1 class="page-title">TIME SLICES (Ocupación tiempo/recursos)</h1>

	<div class="row">
		<div class="col-xs-12 col-sm-5">
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
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#tsDSPFIL_ADDRCD_modal">
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#tsFilterBox').slideToggle();">
					<md-tooltip><bean:message key="common.client.filtrar"/></md-tooltip>
					<md-icon id="filterIcon"> filter_list </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="exportar()">
					<md-tooltip><bean:message key="common.client.exportar"/></md-tooltip>
					<md-icon> file_download </md-icon></md-button>
				&nbsp;&nbsp;
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="desMarcarTodo()">
					<md-tooltip>desmarcar todo</md-tooltip>
					<md-icon> clear </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="marcarTodo()">
					<md-tooltip>Marcar todo</md-tooltip>
					<md-icon> done_all </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="marcados_suprimir()">
					<md-tooltip>Suprimir marcados</md-tooltip>
					<md-icon> delete </md-icon></md-button>
				&nbsp;&nbsp;
				<md-button class="md-button md-fab md-mini" data-toggle="modal" data-target="#chart_panel">
					<md-tooltip>Gráficos</md-tooltip>
					<md-icon> timeline </md-icon></md-button>
			</div>
			<!-- Botones final -->
		</div>
		
		<div class="col-xs-12 col-sm-2">
			<!-- <md-input-container style="vertical-align: top;"> -->
				<!-- <label>location_id</label> -->
				<md-select placeholder="location_id" ng-model="aux_FLT_ts_RS_location_id" md-on-close="filtrar()" ng-model-options="{trackBy: '$value.value'}">
					<md-optgroup label="location_id">
						<md-option ng-value="item" ng-repeat="item in lst_lo">{{ item.displayName }}</md-option>
					</md-optgroup>			
				</md-select>
			<!-- </md-input-container> -->
		</div>

		<div class="col-xs-12 col-sm-5 text-right">
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
			<div id="tsFilterBox" class="box" style="display:none;">
				<div class="row row-filter">

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>sincro</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_sincro" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>mark</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_mark" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>is_deleted</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_is_deleted" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>author</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_author" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>reservation_id</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_reservation_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>RS_user_id</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_RS_user_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>RS_start_date</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_RS_start_date" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>RS_start_time</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_RS_start_time" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>RS_pay_status</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_RS_pay_status" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>RS_product_id</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_RS_product_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>RS_quantity</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_RS_quantity" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>RS_duration_minutes</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_RS_duration_minutes" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>RS_places</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_RS_places" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>start_date</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_start_date" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>start_time</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_start_time" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>json</label>
							<input type="text" ng-model="actionForm.ts_filtro.ts_json" />
						</md-input-container>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#tsFilterBox').slideToggle();">Aplicar</md-button>
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
					<th>&nbsp;<!-- {{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}} --></th>
					<!-- <th>sincro</th> -->
					<!-- <th>mark</th> -->
					<!-- <th>is_deleted</th> -->
					<!-- <th>author</th> -->
					<th>RS_location_id</th>
					<th>reservation_id</th>
					<th>RS_user_id</th>
					<!-- <th>RS_start_date</th> -->
					<!-- <th>RS_start_time</th> -->
					<th>RS_product_id</th>
					<th>RS_pay_status</th>
					<!-- <th>RS_quantity</th> -->
					<!-- <th>RS_duration_minutes</th> -->
					<th>start_date</th>
					<th>start_time</th>
					<th>RS_places</th>
					<!-- <th>json</th> -->
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#tsDSPFIL_EDTRCD_modal">
					<td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td>
					<!-- <td>{{reg.ts_sincro}}&nbsp;</td> -->
					<!-- <td>{{reg.ts_mark}}&nbsp;</td> -->
					<!-- <td>{{reg.ts_is_deleted}}&nbsp;</td> -->
					<!-- <td>{{reg.ts_author}}&nbsp;</td> -->
					<td>{{reg.ts_RS_location_id}}&nbsp;</td>
					<td>{{reg.ts_reservation_id}}&nbsp;</td>
					<td>{{reg.ts_RS_user_id}}&nbsp;</td>
					<!-- <td>{{reg.ts_RS_start_date}}&nbsp;</td> -->
					<!-- <td>{{reg.ts_RS_start_time}}&nbsp;</td> -->
					<td>{{reg.ts_RS_product_id}}&nbsp;</td>
					<td>{{reg.ts_RS_pay_status}}&nbsp;</td>
					<!-- <td>{{reg.ts_RS_quantity}}&nbsp;</td> -->
					<!-- <td>{{reg.ts_RS_duration_minutes}}&nbsp;</td> -->
					<td>{{reg.ts_start_date}}&nbsp;</td>
					<td>{{reg.ts_start_time}}&nbsp;</td>
					<td>{{reg.ts_RS_places}}&nbsp;</td>
					<!-- <td>{{reg.ts_json}}&nbsp;</td> -->
				</tr>
			<tbody>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="tsDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
				<i class="mfw-icon material-icons">clear</i>
		  </button>
          <h2 class="modal-title">Agregar: timeSlices</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/ts_timeSlices/views/TsADDRCD_view_basic.jsp'" />
        </div>
      </div>      
    </div>
  </div>
</div>
<!-- Panel ADDRCD final -->	
	
<!-- Panel EDTRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="tsDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
				<i class="mfw-icon material-icons">clear</i>
		  </button>
          <h2 class="modal-title">Modificar: timeSlices</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/ts_timeSlices/views/TsEDTRCD_view_basic.jsp'" />
        </div>
      </div>      
    </div>
  </div>
</div>
<!-- Panel EDTRCD final -->	

</div>

