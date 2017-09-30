<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="AcDSPFIL_form_group10">
			
	<h1 class="page-title">Activity Cockpits</h1>

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
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="exportar()">
					<md-tooltip><bean:message key="common.client.exportar"/></md-tooltip>
					<md-icon> file_download </md-icon></md-button>
				&nbsp;&nbsp;
				<md-button class="md-button md-fab md-mini" data-toggle="modal" data-target="#chart_panel">
					<md-tooltip>Gráficos</md-tooltip>
					<md-icon> timeline </md-icon></md-button>

			</div>
			<%-- Botones final --%>
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
			<%-- Paginador final --%>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<%-- Caja de filtros inicio --%>
			<div class="box" style="/* display:none; */">
				<div class="row row-filter" style="padding: 10px 0 0 0; margin: 0px 0px 10px 0px;">
					
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>location_id</label>
							<input type="text" ng-model="actionForm.ac_filtro.ac_location_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>computername</label>
							<input type="text" ng-model="actionForm.ac_filtro.ac_computername" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>aaaa_mm_dd</label>
							<input type="text" ng-model="actionForm.ac_filtro.ac_aaaa_mm_dd" />
						</md-input-container>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="//$('#filterIcon').toggleClass('rotate');$('#acFilterBox').slideToggle();">Aplicar</md-button>
					</div>
				</div>
				<%-- Caja de filtros final --%>
			</div>

		</div>
	</div>

	<div class="table-responsive box">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>location_id</th>
					<th>name</th>
					<th>Time segment</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#AcZomm_panel">
					<td>{{reg.ac_location_id}}&nbsp;</td>
					<td>{{reg.ac_computername}}&nbsp;</td>
					<td>{{reg.ac_aaaa_mm_dd_hh_m0}}&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>

</div>
