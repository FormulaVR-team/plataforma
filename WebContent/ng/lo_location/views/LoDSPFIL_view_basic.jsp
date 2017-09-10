<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="LoDSPFIL_form">

  <h1 class="page-title">LOCALES</h1>

  <div class="row">
		<div class="col-xs-12 col-sm-6">
			<!-- Botones inicio -->
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
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#loDSPFIL_ADDRCD_modal">
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#loFilterBox').slideToggle();">
					<md-tooltip><bean:message key="common.client.filtrar"/></md-tooltip>
					<md-icon id="filterIcon"> filter_list </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="exportar()">
					<md-tooltip><bean:message key="common.client.exportar"/></md-tooltip>
					<md-icon> file_download </md-icon></md-button>
			</div>
			<!-- Botones final -->
		</div>
		<div class="col-xs-12 col-sm-6 text-right">
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
			<div id="loFilterBox" class="box" style="display:none;">
				<div class="row row-filter">
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>sincro</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_sincro" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
							<md-input-container class="md-block">
							<label>mark</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_mark" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>is_deleted</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_is_deleted" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>author</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_author" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>location_id</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_location_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>name</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>address</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_address" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>city</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_city" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>postal_code</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_postal_code" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>province</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_province" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>state_region</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_state_region" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>country</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_country" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>lat</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_lat" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>lon</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_lon" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
							<md-input-container class="md-block">
							<label>comment</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_comment" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>json</label>
							<input type="text" ng-model="actionForm.lo_filtro.lo_json" />
						</md-input-container>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#loFilterBox').slideToggle();">Aplicar</md-button>
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
					<!-- <th>mark</th> -->
					<!-- <th>is_deleted</th> -->
					<!-- <th>author</th> -->
					<th>location_id</th>
					<th>name</th>
					<th>address</th>
					<th>city</th>
					<th>postal_code</th>
					<th>province</th>
					<th>state_region</th>
					<th>country</th>
					<th>lat</th>
					<th>lon</th>
					<th>comment</th>
					<!-- <th>json</th> -->
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#loDSPFIL_EDTRCD_modal">
					<!-- <td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td> -->
					<td>{{reg.lo_sincro}}&nbsp;</td>
					<!-- <td>{{reg.lo_mark}}&nbsp;</td> -->
					<!-- <td>{{reg.lo_is_deleted}}&nbsp;</td> -->
					<!-- <td>{{reg.lo_author}}&nbsp;</td> -->
					<td>{{reg.lo_location_id}}&nbsp;</td>
					<td>{{reg.lo_name}}&nbsp;</td>
					<td>{{reg.lo_address}}&nbsp;</td>
					<td>{{reg.lo_city}}&nbsp;</td>
					<td>{{reg.lo_postal_code}}&nbsp;</td>
					<td>{{reg.lo_province}}&nbsp;</td>
					<td>{{reg.lo_state_region}}&nbsp;</td>
					<td>{{reg.lo_country}}&nbsp;</td>
					<td>{{reg.lo_lat}}&nbsp;</td>
					<td>{{reg.lo_lon}}&nbsp;</td>
					<td>{{reg.lo_comment}}&nbsp;</td>
					<!-- <td>{{reg.lo_json}}&nbsp;</td> -->
				</tr>
			</tbody>
		</table>
	</div>



	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="loDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: location</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/lo_location/views/LoADDRCD_view_basic.jsp'" />
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
  <div class="modal fade" id="loDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: location</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/lo_location/views/LoEDTRCD_view_basic.jsp'" />
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

