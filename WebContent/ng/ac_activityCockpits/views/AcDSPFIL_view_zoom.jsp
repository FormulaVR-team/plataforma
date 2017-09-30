<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html>


<!-- Modal -->
<div class="modal fade" id="AcZomm_panel" role="dialog" data-backdrop="false">
    <div class="modal-dialog" style="width: 68%;">
    
      <!-- Modal content-->
      <div class="modal-content">

        <div class="modal-body">
        
			<button type="button" class="close" data-dismiss="modal">&times;</button>

			<div name="AcDSPFIL_form">
						
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
							<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#acDSPFIL_ADDRCD_modal">
								<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
								<md-icon> add </md-icon></md-button>
							<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#acFilterBox').slideToggle();">
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
						<div id="acFilterBox" class="box" style="display:none;">
							<div class="row row-filter">
								
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>sincro</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_sincro" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>mark</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_mark" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>is_deleted</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_is_deleted" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>author</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_author" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>serial</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_serial" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>location_id</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_location_id" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>LO_name</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_LO_name" />
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
										<label>filename</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_filename" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>content</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_content" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>json</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_json" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>aaaa_mm</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_aaaa_mm" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>aaaa_mm_dd</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_aaaa_mm_dd" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>aaaa_mm_dd_hh</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_aaaa_mm_dd_hh" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>aaaa_mm_dd_hh_m0</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_aaaa_mm_dd_hh_m0" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>aaaa_mm_dd_hh_mm</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_aaaa_mm_dd_hh_mm" />
									</md-input-container>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
									<md-input-container class="md-block">
										<label>aaaa_mm_dd_hh_mm_ss</label>
										<input type="text" ng-model="actionForm.ac_filtro.ac_aaaa_mm_dd_hh_mm_ss" />
									</md-input-container>
								</div>
			
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
									<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#acFilterBox').slideToggle();">Aplicar</md-button>
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
								<th>&nbsp;<!-- {{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}} --></th>
								<!-- <th>location_id</th> -->
								<th>location</th>
								<th>computername</th>
								<%-- <th>filename</th>
								<th>aaaa_mm</th>
								<th>aaaa_mm_dd</th>
								<th>aaaa_mm_dd_hh</th>
								<th>aaaa_mm_dd_hh_m0</th>
								<th>aaaa_mm_dd_hh_mm</th> --%>
								<th>UTC</th>
								<th>content</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#acDSPFIL_EDTRCD_modal">
								<td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td>
								<!-- <td>{{reg.ac_location_id}}&nbsp;</td> -->
								<td>{{reg.ac_LO_name}}&nbsp;</td>
								<td>{{reg.ac_computername}}&nbsp;</td>
								<%-- <td>{{reg.ac_filename}}&nbsp;</td>
								<td>{{reg.ac_aaaa_mm}}&nbsp;</td>
								<td>{{reg.ac_aaaa_mm_dd}}&nbsp;</td>
								<td>{{reg.ac_aaaa_mm_dd_hh}}&nbsp;</td>
								<td>{{reg.ac_aaaa_mm_dd_hh_m0}}&nbsp;</td>
								<td>{{reg.ac_aaaa_mm_dd_hh_mm}}&nbsp;</td> --%>
								<td>{{reg.ac_aaaa_mm_dd_hh_mm_ss}}&nbsp;</td>
								<td title="{{reg.ac_content}}">{{reg.ac_content | limitTo:'25'}}&nbsp;</td>
							</tr>
						</tbody>
				  </table>
				</div>
				
			</div>

        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>

      </div>
      
	</div>
</div>
