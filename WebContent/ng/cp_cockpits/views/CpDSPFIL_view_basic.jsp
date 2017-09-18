<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="CpDSPFIL_form">

	<h1 class="page-title">COCKPITS</h1>

	<div class="row">
		<div class="col-xs-12 col-sm-5" style="margin-top: 15px;margin-bottom: 10px;">
			<!-- Botones inicio -->
			<div class="btn-group">
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="filtrar()">
					<md-tooltip><bean:message key="common.client.refrescar"/></md-tooltip>
					<md-icon> autorenew </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="rtPg()">
					<md-tooltip><bean:message key="common.client.retroceder"/></md-tooltip>
					<md-icon> skip_previous </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="avPg()">
					<md-tooltip><bean:message key="common.client.avanzar"/></md-tooltip>
					<md-icon> skip_next </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#cpDSPFIL_ADDRCD_modal">
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#cpFilterBox').slideToggle();">
					<md-tooltip><bean:message key="common.client.filtrar"/></md-tooltip>
					<md-icon id="filterIcon"> filter_list </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="exportar()">
					<md-tooltip><bean:message key="common.client.exportar"/></md-tooltip>
					<md-icon> file_download </md-icon></md-button>
			</div>
			<!-- Botones final -->
		</div>
		<div class="col-xs-12 col-sm-2 text-right">
  	<!-- <md-input-container style="vertical-align: top;"> -->
			<!-- <label>location_id</label> -->
			<md-select placeholder="location_id" ng-model="aux_FLT_cp_location_id" md-on-close="filtrar()" ng-model-options="{trackBy: '$value.value'}">
				<md-optgroup label="location_id">
					<md-option ng-value="item" ng-repeat="item in lst_lo">{{ item.displayName }}</md-option>
				</md-optgroup>			
			</md-select>
		<!-- </md-input-container> -->
		</div>

		<div class="col-xs-12 col-sm-5 text-right">
			<!-- Paginador inicio -->
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
			<!-- Caja de filtros inicio -->
			<div id="cpFilterBox" class="box" style="display:none;">
				<div class="row row-filter">
				
<!-- 
					<md-input-container>
						<label>sincro</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_sincro" />
					</md-input-container>
					<md-input-container>
						<label>mark</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_mark" />
					</md-input-container>
					<md-input-container>
						<label>is_deleted</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_is_deleted" />
					</md-input-container>
					<md-input-container>
						<label>author</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_author" />
					</md-input-container>
 					<md-input-container>
						<label>LO_name</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_LO_name" />
					</md-input-container>
					<md-input-container>
						<label>LO_address</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_LO_address" />
					</md-input-container>
					<md-input-container>
						<label>LO_city</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_LO_city" />
					</md-input-container>
					<md-input-container>
						<label>LO_postal_code</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_LO_postal_code" />
					</md-input-container>
					<md-input-container>
						<label>LO_lat</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_LO_lat" />
					</md-input-container>
					<md-input-container>
						<label>LO_lon</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_LO_lon" />
					</md-input-container>
 -->
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>cockpit_id</label>
							<input type="text" ng-model="actionForm.cp_filtro.cp_cockpit_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>serial_number</label>
							<input type="text" ng-model="actionForm.cp_filtro.cp_serial_number" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>name</label>
							<input type="text" ng-model="actionForm.cp_filtro.cp_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>isBlocked</label>
							<md-select placeholder="isBlocked" ng-model="actionForm.cp_filtro.cp_isBlocked">
							<md-optgroup label="isBlocked">
									<md-option ng-repeat="item in lst_isBlocked" value="{{item}}">{{item}}</md-option>
								</md-optgroup>			
							</md-select>
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container>
							<label>json</label>
							<input type="text" ng-model="actionForm.cp_filtro.cp_json" />
						</md-input-container>
					</div>
<!-- 
					<md-input-container>
						<label>asignation_order</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_asignation_order" />
					</md-input-container>
					<md-input-container>
						<label>install_date</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_install_date" />
					</md-input-container>
					<md-input-container>
						<label>reset_date_used</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_reset_date_used" />
					</md-input-container>
					<md-input-container>
						<label>hours_used</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_hours_used" />
					</md-input-container>
					<md-input-container>
						<label>note</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_note" />
					</md-input-container>
					<md-input-container>
						<label>comment</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_comment" />
					</md-input-container>
					<md-input-container>
						<label>observation</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_observation" />
					</md-input-container>
					<md-input-container>
						<label>warning</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_warning" />
					</md-input-container>
					<md-input-container>
						<label>contact_service</label>
						<input type="text" ng-model="actionForm.cp_filtro.cp_contact_service" />
					</md-input-container>
 -->
 					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#cpFilterBox').slideToggle();">Aplicar</md-button>
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
	<!-- 
					<th>{{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}}</th>
					<th>sincro</th>
					<th>mark</th>
					<th>is_deleted</th>
					<th>author</th>
	 				<th>location_id</th>
	-->
					<th>location</th>
	<!-- 
					<th>LO_address</th>
					<th>LO_city</th>
					<th>LO_postal_code</th>
					<th>LO_lat</th>
					<th>LO_lon</th>
	 -->
	 				<th>cockpit</th>
					<th>serial_number</th>
					<th>name</th>
					<th>isBlocked</th>
					<th>asignation_order</th>
					<th>install_date</th>
					<th>reset_date_used</th>
					<th>hours_used</th>
					<th>note</th>
					<th>comment</th>
					<th>observation</th>
					<th>warning</th>
	<!--
	 				<th>contact_service</th>
	 -->
					<th>json</th>
	 			</tr>
  		</thead>
  		<tbody>
  			<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#cpDSPFIL_EDTRCD_modal">
	<!--
					<td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td>

					<td>{{reg.cp_sincro}}&nbsp;</td>
					<td>{{reg.cp_mark}}&nbsp;</td>
					<td>{{reg.cp_is_deleted}}&nbsp;</td>
					<td>{{reg.cp_author}}&nbsp;</td>
	 				<td>{{reg.cp_location_id}}&nbsp;</td>
	 -->
	 				<td>{{reg.cp_LO_name}}&nbsp;</td>
	<!-- 
					<td>{{reg.cp_LO_address}}&nbsp;</td>
					<td>{{reg.cp_LO_city}}&nbsp;</td>
					<td>{{reg.cp_LO_postal_code}}&nbsp;</td>
					<td>{{reg.cp_LO_lat}}&nbsp;</td>
					<td>{{reg.cp_LO_lon}}&nbsp;</td>
	 -->
	 				<td>{{reg.cp_cockpit_id}}&nbsp;</td>
					<td>{{reg.cp_serial_number}}&nbsp;</td>
					<td>{{reg.cp_name}}&nbsp;</td>
					<td>{{reg.cp_isBlocked}}&nbsp;</td>
					<td>{{reg.cp_asignation_order}}&nbsp;</td>
					<td>{{reg.cp_install_date}}&nbsp;</td>
					<td>{{reg.cp_reset_date_used}}&nbsp;</td>
					<td>{{reg.cp_hours_used}}&nbsp;</td>
					<td>{{reg.cp_note}}&nbsp;</td>
					<td>{{reg.cp_comment}}&nbsp;</td>
					<td>{{reg.cp_observation}}&nbsp;</td>
					<td>{{reg.cp_warning}}&nbsp;</td>
	<!--
	 				<td>{{reg.cp_contact_service}}&nbsp;</td>
	 -->
					<td>{{reg.cp_json}}&nbsp;</td>
	 			</tr>
  		</tbody>
  	</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <div class="modal fade modal-full-width" id="cpDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
			<i class="mfw-icon material-icons">clear</i>
		  </button>
          <h2 class="modal-title">Agregar: cockpits</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/cp_cockpits/views/CpADDRCD_view_basic.jsp'" />
        </div>
      </div>      
    </div>
  </div>
</div>
<!-- Panel ADDRCD final -->	
	
<!-- Panel EDTRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="cpDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
          	<i class="mfw-icon material-icons">clear</i>
          </button>
          <h2 class="modal-title">Modificar: cockpits</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/cp_cockpits/views/CpEDTRCD_view_basic.jsp'" />
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Panel EDTRCD final -->	

</div>

