<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PtDSPFIL_form">

	<h1 class="page-title">PRODUCTOS</h1>

	<div class="row">
		<div class="col-xs-12 col-sm-6">
			<%-- Botones inicio --%>
			<div class="btn-group" style="margin-top: 15px;margin-bottom: 10px;">
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="filtrar()" >
					<md-tooltip><bean:message key="common.client.refrescar"/></md-tooltip>
					<md-icon> autorenew </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="rtPg()" >
					<md-tooltip><bean:message key="common.client.retroceder"/></md-tooltip>
					<md-icon> skip_previous </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="avPg()" >
					<md-tooltip><bean:message key="common.client.avanzar"/></md-tooltip>
					<md-icon> skip_next </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#ptDSPFIL_ADDRCD_modal" >
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#ptFilterBox').slideToggle();" >
					<md-tooltip><bean:message key="common.client.filtrar"/></md-tooltip>
					<md-icon id="filterIcon"> filter_list </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="exportar()" >
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
			<div id="ptFilterBox" class="box" style="display:none;">
				<div class="row row-filter">
<!--
 					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>sincro</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_sincro" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>mark</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_mark" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>is_deleted</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_is_deleted" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>author</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_author" />
						</md-input-container>
					</div>

 -->			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>product_id</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_product_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>name</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>whoCanSelect_AFU</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_whoCanSelect_AFU" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>deadline</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_deadline" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>isPercent</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_isPercent" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>amount</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_amount" />
						</md-input-container>
					</div>
<!-- 
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>currency</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_currency" />
						</md-input-container>
					</div>
 -->
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>duration_minutes</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_duration_minutes" />
						</md-input-container>
					</div>
<!-- 
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>json</label>
							<input type="text" ng-model="actionForm.pt_filtro.pt_json" />
						</md-input-container>
					</div>
-->	

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#ptFilterBox').slideToggle();">Aplicar</md-button>
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
					<!-- <th>sincro</th> -->
					<!-- <th>M</th> -->
					<!-- <th>D</th> -->
					<!-- <th>author</th> -->
					<th>product_id</th>
					<th>name</th>
					<th>whoCanSelect_AFU</th>
					<th>deadline</th>
					<th>isPercent</th>
					<th>amount</th>
					<th>currency</th>
					<th>duration_minutes</th>
					<!-- <th>json</th> -->
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#ptDSPFIL_EDTRCD_modal">
					<!-- <td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td> -->
					<!-- <td>{{reg.pt_sincro}}&nbsp;</td> -->
					<!-- <td>{{reg.pt_mark}}&nbsp;</td> -->
					<!-- <td>{{reg.pt_is_deleted}}&nbsp;</td> -->
					<!-- <td>{{reg.pt_author}}&nbsp;</td> -->
					<td>{{reg.pt_product_id}}&nbsp;</td>
					<td>{{reg.pt_name}}&nbsp;</td>
					<td>{{reg.pt_whoCanSelect_AFU}}&nbsp;</td>
					<td>{{reg.pt_deadline}}&nbsp;</td>
					<td>{{reg.pt_isPercent}}&nbsp;</td>
					<td>{{reg.pt_amount}}&nbsp;</td>
					<td>{{reg.pt_currency}}&nbsp;</td>
					<td>{{reg.pt_duration_minutes}}&nbsp;</td>
					<!-- <td>{{reg.pt_json}}&nbsp;</td> -->
				</tr>
			</tbody>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="ptDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
			<i class="mfw-icon material-icons">clear</i>
		  </button>
          <h2 class="modal-title">Agregar: products</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/pt_products/views/PtADDRCD_view_basic.jsp'" />
        </div>
      </div>      
    </div>
  </div>
</div>
<!-- Panel ADDRCD final -->	
	
<!-- Panel EDTRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="ptDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
			<i class="mfw-icon material-icons">clear</i>
		  </button>
          <h2 class="modal-title">Modificar: products</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/pt_products/views/PtEDTRCD_view_basic.jsp'" />
        </div>
      </div>      
    </div>
  </div>
</div>
<!-- Panel EDTRCD final -->	

</div>

