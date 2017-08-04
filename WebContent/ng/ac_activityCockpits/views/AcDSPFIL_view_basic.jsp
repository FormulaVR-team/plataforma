<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="AcDSPFIL_form">
    <div class="modal-header">

		<div class="row">
			<div class="col-xs-12" style="padding: 0px; min-height: 40px;"><div dynamic="adminMenu"></div></div>
		</div>

		<div class="row">

		<div layout="row" layout-align="space-between stretch">

			<div>
				<%-- Botones inicio --%>
				<div class="btn-group">
					<md-button class="button-close md-fab md-mini" ng-click="filtrar()" title="<bean:message key="common.client.refrescar"/>"><md-icon> autorenew </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="rtPg()" title="<bean:message key="common.client.retroceder"/>"><md-icon> skip_previous </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="avPg()" title="<bean:message key="common.client.avanzar"/>"><md-icon> skip_next </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="initReg()" data-toggle="modal" data-target="#acDSPFIL_ADDRCD_modal" title="<bean:message key="common.client.nuevo"/>"><md-icon> add </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" onclick="$('#filterIcon').toggleClass('rotate');$('#acFilterBox').slideToggle();" title="<bean:message key="common.client.filtrar"/>"><md-icon id="filterIcon"> filter_list </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="exportar()" title="<bean:message key="common.client.exportar"/>"><md-icon> file_download </md-icon></md-button>
<%--
					&nbsp;&nbsp;
					<md-button class="button-close md-fab md-mini" ng-click="desMarcarTodo()" title="desmarcar todo"><md-icon> clear </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="marcarTodo()" title="Marcar todo"><md-icon> done_all </md-icon></md-button>
					<md-button class="button-close md-fab md-mini" ng-click="marcados_suprimir()" title="Suprimir marcados"><md-icon> delete </md-icon></md-button>
					&nbsp;&nbsp;
 --%>
 				</div>
				<%-- Botones final --%>
			</div>

			<div>
				<h3>
					activityCockpits
					<span dynamic="exportLink"></span>
				</h3>
			</div>

			<div>
				<%-- <span>{{actionForm.logon_USR}}</span> --%>
				<%-- Paginador inicio --%>
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
				<%-- Paginador final --%>
			</div>

		</div>

		<div>
			<%-- Caja de filtros inicio --%>
			<div id="acFilterBox" style="display:none; border: black solid 1px;">
				<md-button class=" md-raised md-warn md-button md-ink-ripple" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#acFilterBox').slideToggle();">Aplicar</md-button>
				<div class="alert alert-default fade in" style="display: block; padding: 0; margin: 0;">

					<md-input-container>
						<label>sincro</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_sincro" />
					</md-input-container>
					<md-input-container>
						<label>mark</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_mark" />
					</md-input-container>
					<md-input-container>
						<label>is_deleted</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_is_deleted" />
					</md-input-container>
					<md-input-container>
						<label>author</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_author" />
					</md-input-container>
					<md-input-container>
						<label>serial</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_serial" />
					</md-input-container>
					<md-input-container>
						<label>client</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_computername" />
					</md-input-container>
					<md-input-container>
						<label>filename</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_filename" />
					</md-input-container>
					<md-input-container>
						<label>content</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_content" />
					</md-input-container>
					<md-input-container>
						<label>json</label>
						<input type="text" ng-model="actionForm.ac_filtro.ac_json" />
					</md-input-container>

				</div>
			</div>
			<%-- Caja de filtros final --%>
		</div>

	</div>

	<div class="row">
		<table class="table table-striped table-hover">

			<tr>
				<%-- <th>{{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}}</th> --%>
				<th>sincro</th>
				<%-- <th>mark</th> --%>
				<%-- <th>is_deleted</th> --%>
				<%-- <th>author</th> --%>
				<th>serial</th>
				<th>client</th>
				<th>filename</th>
				<th>content</th>
				<th>json</th>
			</tr>

			<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#acDSPFIL_EDTRCD_modal">
				<%-- <td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td> --%>
				<td>{{reg.ac_sincro}}&nbsp;</td>
				<%-- <td>{{reg.ac_mark}}&nbsp;</td> --%>
				<%-- <td>{{reg.ac_is_deleted}}&nbsp;</td> --%>
				<%-- <td>{{reg.ac_author}}&nbsp;</td> --%>
				<td>{{reg.ac_serial}}&nbsp;</td>
				<td>{{reg.ac_computername}}&nbsp;</td>
				<td>{{reg.ac_filename}}&nbsp;</td>
				<td>{{reg.ac_content}}&nbsp;</td>
				<td>{{reg.ac_json}}&nbsp;</td>
			</tr>
		</table>
	</div>

	
<%-- Panel ADDRCD inicio --%>	
<div class="container">
  <%-- Modal --%>
  <div class="modal fade" id="acDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <%-- Modal content--%>
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Agregar: activityCockpits</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/ac_activityCockpits/views/AcADDRCD_view_basic.jsp'" />
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>

      </div>
      
    </div>
  </div>
</div>
<%-- Panel ADDRCD final --%>	
	
<%-- Panel EDTRCD inicio --%>	
<div class="container">
  <%-- Modal --%>
  <div class="modal fade" id="acDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <%-- Modal content--%>
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modificar: activityCockpits</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/ac_activityCockpits/views/AcEDTRCD_view_basic.jsp'" />
        </div>
 
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>

      </div>
      
    </div>
  </div>
</div>
<%-- Panel EDTRCD final --%>	

    </div>
</div>

