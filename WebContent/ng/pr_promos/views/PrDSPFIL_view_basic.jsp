<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PrDSPFIL_form">
    <div class="modal-header">

		<div class="row">
			<div class="col-xs-12" style="padding: 0px; min-height: 40px;"><div dynamic="adminMenu"></div></div>
		</div>

		<div class="row">

		<div layout="row" layout-align="space-between stretch">

			<div>
				<h4>
					<!-- <md-input-container style="vertical-align: top;"> -->
						<!-- <label>location_id</label> -->
						<md-select placeholder="location_id" ng-model="aux_FLT_pr_location_id" md-on-close="filtrar()" ng-model-options="{trackBy: '$value.value'}">
							<md-optgroup label="location_id">
								<md-option ng-value="item" ng-repeat="item in lst_lo">{{ item.displayName }}</md-option>
							</md-optgroup>			
						</md-select>
					<!-- </md-input-container> -->
				</h4>
				<!-- Botones inicio -->
				<div class="btn-group">
					<md-button class="button-close md-fab md-mini" ng-click="filtrar()" title="<bean:message key="common.client.refrescar"/>"><md-icon> autorenew </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="rtPg()" title="<bean:message key="common.client.retroceder"/>"><md-icon> skip_previous </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="avPg()" title="<bean:message key="common.client.avanzar"/>"><md-icon> skip_next </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="initReg()" data-toggle="modal" data-target="#prDSPFIL_ADDRCD_modal" title="<bean:message key="common.client.nuevo"/>"><md-icon> add </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" onclick="$('#filterIcon').toggleClass('rotate');$('#prFilterBox').slideToggle();" title="<bean:message key="common.client.filtrar"/>"><md-icon id="filterIcon"> filter_list </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="exportar()" title="<bean:message key="common.client.exportar"/>"><md-icon> file_download </md-icon></md-button>
				</div>
				<!-- Botones final -->
			</div>

			<div>
				<h3>
					PROMOCIONES
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
			<div id="prFilterBox" style="display:none; border: black solid 1px;">
				<md-button class=" md-raised md-warn md-button md-ink-ripple" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#prFilterBox').slideToggle();">Aplicar</md-button>
				<div class="alert alert-default fade in" style="display: block; padding: 0; margin: 0;">

					<md-input-container>
						<label>sincro</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_sincro" />
					</md-input-container>
					<md-input-container>
						<label>mark</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_mark" />
					</md-input-container>
					<md-input-container>
						<label>is_deleted</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_is_deleted" />
					</md-input-container>
					<md-input-container>
						<label>author</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_author" />
					</md-input-container>

					<md-input-container>
						<label>product_id</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_product_id" />
					</md-input-container>
					<md-input-container>
						<label>PT_name</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_PT_name" />
					</md-input-container>
					<md-input-container>
						<label>product_id_promo</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_product_id_promo" />
					</md-input-container>
					<md-input-container>
						<label>PT_name_promo</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_PT_name_promo" />
					</md-input-container>
					<md-input-container>
						<label>deadline</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_deadline" />
					</md-input-container>
					<md-input-container>
						<label>min_quantity</label>
						<input type="text" ng-model="actionForm.pr_filtro.pr_min_quantity" />
					</md-input-container>

				</div>
			</div>
			<!-- Caja de filtros final -->
		</div>

	</div>

	<div class="row">
		<table class="table table-striped table-hover">

			<tr>
				<!-- <th>{{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}}</th> -->
				<!-- <th>sincro</th> -->
				<!-- <th>mark</th> -->
				<!-- <th>is_deleted</th> -->
				<!-- <th>author</th> -->
				<!-- <th>location_id</th> -->
				<th>LO_name</th>
				<th>product_id</th>
				<th>PT_name</th>
				<th>product_id_promo</th>
				<th>PT_name_promo</th>
				<th>deadline</th>
				<th>min_quantity</th>
			</tr>

			<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#prDSPFIL_EDTRCD_modal">
				<!-- <td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td> -->
				<!-- <td>{{reg.pr_sincro}}&nbsp;</td> -->
				<!-- <td>{{reg.pr_mark}}&nbsp;</td> -->
				<!-- <td>{{reg.pr_is_deleted}}&nbsp;</td> -->
				<!-- <td>{{reg.pr_author}}&nbsp;</td> -->
				<!-- <td>{{reg.pr_location_id}}&nbsp;</td> -->
				<td>{{reg.pr_LO_name}}&nbsp;</td>
				<td>{{reg.pr_product_id}}&nbsp;</td>
				<td>{{reg.pr_PT_name}}&nbsp;</td>
				<td>{{reg.pr_product_id_promo}}&nbsp;</td>
				<td>{{reg.pr_PT_name_promo}}&nbsp;</td>
				<td>{{reg.pr_deadline}}&nbsp;</td>
				<td>{{reg.pr_min_quantity}}&nbsp;</td>
			</tr>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="prDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: promos</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/pr_promos/views/PrADDRCD_view_basic.jsp'" />
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
  <div class="modal fade" id="prDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: promos</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/pr_promos/views/PrEDTRCD_view_basic.jsp'" />
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

