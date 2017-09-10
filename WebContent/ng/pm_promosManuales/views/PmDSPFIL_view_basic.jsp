<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="PmDSPFIL_form">

  <h1 class="page-title">CUPONES</h1>

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
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#pmDSPFIL_ADDRCD_modal">
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#pmFilterBox').slideToggle();">
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
			<div id="pmFilterBox" class="box" style="display:none;">
				<div class="row row-filter">
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>sincro</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_sincro" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>mark</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_mark" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>is_deleted</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_is_deleted" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>author</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_author" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>coupon_id</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_coupon_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>name</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>uses_per_user</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_uses_per_user" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>places</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_places" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>location_id</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_location_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>LO_name</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_LO_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>product_id</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_product_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_name</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_PT_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_whoCanSelect_AFU</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_PT_whoCanSelect_AFU" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_deadline</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_PT_deadline" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>product_id_promo</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_product_id_promo" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>deadline</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_deadline" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>json</label>
							<input type="text" ng-model="actionForm.pm_filtro.pm_json" />
						</md-input-container>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#pmFilterBox').slideToggle();">Aplicar</md-button>
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
					<th><!-- {{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}} --></th>
					<th>sincro</th>
					<!-- <th>mark</th> -->
					<!-- <th>is_deleted</th> -->
					<th>author</th>
					<th>coupon_id</th>
					<th>name</th>
					<th>uses_per_user</th>
					<th>places</th>
					<th>location_id</th>
					<th>LO_name</th>
					<th>product_id</th>
					<th>PT_name</th>
					<th>PT_whoCanSelect_AFU</th>
					<th>PT_deadline</th>
					<th>product_id_promo</th>
					<th>deadline</th>
					<!-- <th>json</th> -->
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#pmDSPFIL_EDTRCD_modal">
					<td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td>
					<td>{{reg.pm_sincro}}&nbsp;</td>
					<!-- <td>{{reg.pm_mark}}&nbsp;</td> -->
					<!-- <td>{{reg.pm_is_deleted}}&nbsp;</td> -->
					<td>{{reg.pm_author}}&nbsp;</td>
					<td>{{reg.pm_coupon_id}}&nbsp;</td>
					<td>{{reg.pm_name}}&nbsp;</td>
					<td>{{reg.pm_uses_per_user}}&nbsp;</td>
					<td>{{reg.pm_places}}&nbsp;</td>
					<td>{{reg.pm_location_id}}&nbsp;</td>
					<td>{{reg.pm_LO_name}}&nbsp;</td>
					<td>{{reg.pm_product_id}}&nbsp;</td>
					<td>{{reg.pm_PT_name}}&nbsp;</td>
					<td>{{reg.pm_PT_whoCanSelect_AFU}}&nbsp;</td>
					<td>{{reg.pm_PT_deadline}}&nbsp;</td>
					<td>{{reg.pm_product_id_promo}}&nbsp;</td>
					<td>{{reg.pm_deadline}}&nbsp;</td>
					<!-- <td>{{reg.pm_json}}&nbsp;</td> -->
				</tr>
			</tbody>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="pmDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: promosManuales</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/pm_promosManuales/views/PmADDRCD_view_basic.jsp'" />
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
  <div class="modal fade" id="pmDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: promosManuales</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/pm_promosManuales/views/PmEDTRCD_view_basic.jsp'" />
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

