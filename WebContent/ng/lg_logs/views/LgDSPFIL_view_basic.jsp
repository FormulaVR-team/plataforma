<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="LgDSPFIL_form">

  <h1 class="page-title">LOGS</h1>

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
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#lgDSPFIL_ADDRCD_modal">
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#lgFilterBox').slideToggle();">
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
			<div id="lgFilterBox" class="box" style="display:none;">
				<div class="row row-filter">
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>serial</label>
							<input type="text" ng-model="actionForm.lg_filtro.lg_serial" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>text_1</label>
							<input type="text" ng-model="actionForm.lg_filtro.lg_text_1" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>text_2</label>
							<input type="text" ng-model="actionForm.lg_filtro.lg_text_2" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>text_3</label>
							<input type="text" ng-model="actionForm.lg_filtro.lg_text_3" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>json</label>
							<input type="text" ng-model="actionForm.lg_filtro.lg_json" />
						</md-input-container>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#lgFilterBox').slideToggle();">Aplicar</md-button>
					</div>
				</div>
			</div>
			<!-- Caja de filtros final -->
		</div>
	</div>

	<div class="table-responsive box">
  	<table class="table table-striped">
  		<thead>
  			<tr>
					<!-- <th>{{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}}</th> -->
					<th>sincro</th>
					<th>author</th>
					<th>serial</th>
					<th>text_1</th>
					<th>text_2</th>
					<th>text_3</th>
					<th>json</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#lgDSPFIL_EDTRCD_modal">
					<!-- <td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td> -->
					<td>{{reg.lg_sincro}}&nbsp;</td>
					<td>{{reg.lg_author}}&nbsp;</td>
					<td>{{reg.lg_serial}}</td>
					<td title="{{reg.lg_text_1}}">{{reg.lg_text_1 | limitTo:'30'}}</td>
					<td title="{{reg.lg_text_2}}">{{reg.lg_text_2 | limitTo:'30'}}</td>
					<td title="{{reg.lg_text_3}}">{{reg.lg_text_3 | limitTo:'30'}}</div></td>
					<td title="{{reg.lg_json}}">{{reg.lg_json | limitTo:'30'}}</td>
				</tr>
			</tbody>
		</table>
	</div>


	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="lgDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
							<i class="mfw-icon material-icons">clear</i>
					</button>
          <h2 class="modal-title">Agregar: logs</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/lg_logs/views/LgADDRCD_view_basic.jsp'" />
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Panel ADDRCD final -->	
	
<!-- Panel EDTRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="lgDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
							<i class="mfw-icon material-icons">clear</i>
					</button>
          <h2 class="modal-title">Modificar: logs</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/lg_logs/views/LgEDTRCD_view_basic.jsp'" />
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Panel EDTRCD final -->	

</div>

