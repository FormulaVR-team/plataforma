<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="EsDSPFIL_form">

	<h1 class="page-title"><span style="color:blue;">{{actionForm.es_filtro.es_inscription_user_id}}</span></br>Mis inscripciones</h1>
	
	<script>
	$('#esDSPFIL_ADDRCD_modal').modal("show");
	</script>
	
	<div layout="row" layout-align="space-between center">
		<div>
			<%-- Botones inicio --%>
			<div class="btn-group">
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="filtrar()">
					<md-tooltip><bean:message key="common.client.refrescar"/></md-tooltip>
					<md-icon> autorenew </md-icon></md-button>
<%-- 
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="rtPg()">
					<md-tooltip><bean:message key="common.client.retroceder"/></md-tooltip>
					<md-icon> skip_previous </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="avPg()">
					<md-tooltip><bean:message key="common.client.avanzar"/></md-tooltip>
					<md-icon> skip_next </md-icon></md-button>
--%>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#esDSPFIL_ADDRCD_modal">
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
			</div>
			<%-- Botones final --%>
		</div>
		<div>
			<%-- Paginador inicio --%>
<%-- 
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
--%>
			<%-- Paginador final --%>
		</div>
	</div>

	<div class="table-responsive box">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<%-- <th>{{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}}</th> --%>
					<%-- <th>sincro</th> --%>
					<%-- <th>mark</th> --%>
					<%-- <th>is_deleted</th> --%>
					<%-- <th>author</th> --%>
					<%-- <th>location_id</th> --%>
					<%-- <th>location</th> --%>
					<th>Evento</th>
					<%-- <th>user_id</th> --%>
					<%-- <th>first_name</th> --%>
					<%-- <th>last_name</th> --%>
					<%-- <th>phone</th> --%>
					<th style="text-align:right;">Importe</th>
					<%-- <th>currency</th> --%>
					<%-- <th>tpv_order</th> --%>
					<th>Pago</th>
					<%-- <th>json</th> --%>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#esDSPFIL_EDTRCD_modal">
					<%-- <td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td> --%>
					<%-- <td>{{reg.es_sincro}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_mark}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_is_deleted}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_author}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_EV_location_id}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_LO_name}}&nbsp;</td> --%>
					<td>{{reg.es_event_id}}&nbsp;</td>
					<%-- <td>{{reg.es_inscription_user_id}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_first_name}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_last_name}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_phone}}&nbsp;</td> --%>
					<td style="text-align:right;">{{reg.es_amount}}&nbsp;{{reg.es_currency}}</td>
					<%-- <td>{{reg.es_currency}}&nbsp;</td> --%>
					<%-- <td>{{reg.es_tpv_order}}&nbsp;</td> --%>
					<td>{{reg.es_pay_status}}&nbsp;</td>
					<%-- <td>{{reg.es_json}}&nbsp;</td> --%>
				</tr>
			</tbody>
		</table>
	</div>

<%-- Panel ADDRCD inicio --%>	
<div class="container">
  <%-- Modal --%>
  <div class="modal fade" id="esDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <%-- Modal content--%>
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">{{actionForm.logon_USR}}</h4>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/es_eventSusbscriptions/views/EsADDRCD_view_basic.jsp'" />
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>

      </div>
      
    </div>
  </div>
</div>
<%-- Panel ADDRCD final --%>	

</div>

