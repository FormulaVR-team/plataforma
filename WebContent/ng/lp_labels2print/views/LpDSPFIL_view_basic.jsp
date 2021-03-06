<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="LpDSPFIL_form">
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
						<md-button class="button-close md-fab md-mini" ng-click="initReg()" data-toggle="modal" data-target="#lpDSPFIL_ADDRCD_modal" title="<bean:message key="common.client.nuevo"/>"><md-icon> add </md-icon></md-button>
						<md-button class="button-close md-fab md-mini" onclick="$('#filterIcon').toggleClass('rotate');$('#lpFilterBox').slideToggle();" title="<bean:message key="common.client.filtrar"/>"><md-icon id="filterIcon"> filter_list </md-icon></md-button>
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
						labels2print
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
				<div id="lpFilterBox" style="display:none; border: black solid 1px;">
					<md-button class=" md-raised md-warn md-button md-ink-ripple" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#lpFilterBox').slideToggle();">Aplicar</md-button>
					<div class="alert alert-default fade in" style="display: block; padding: 0; margin: 0;">

					<md-input-container>
						<label>card_id</label>
						<input type="text" ng-model="actionForm.lp_filtro.lp_card_id" />
					</md-input-container>
					<md-input-container>
						<label>TJ_user_id</label>
						<input type="text" ng-model="actionForm.lp_filtro.lp_TJ_user_id" />
					</md-input-container>
					<md-input-container>
						<label>TJ_qr_image_base64</label>
						<input type="text" ng-model="actionForm.lp_filtro.lp_TJ_qr_image_base64" />
					</md-input-container>
					<md-input-container>
						<label>json</label>
						<input type="text" ng-model="actionForm.lp_filtro.lp_json" />
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
				<th>card_id</th>
				<th>TJ_user_id</th>
				<th>TJ_qr_image_base64</th>
				<th>json</th>
			</tr>

			<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#lpDSPFIL_EDTRCD_modal">
				<td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td>
				<td>{{reg.lp_card_id}}&nbsp;</td>
				<td>{{reg.lp_TJ_user_id}}&nbsp;</td>
				<td>{{reg.lp_TJ_qr_image_base64}}&nbsp;</td>
				<td>{{reg.lp_json}}&nbsp;</td>
			</tr>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="lpDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: labels2print</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/lp_labels2print/views/LpADDRCD_view_basic.jsp'" />
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
  <div class="modal fade" id="lpDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: labels2print</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/lp_labels2print/views/LpEDTRCD_view_basic.jsp'" />
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

