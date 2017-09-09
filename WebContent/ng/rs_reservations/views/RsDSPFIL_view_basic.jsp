<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="RsDSPFIL_form">
	<div class="row">
		<div class="col-xs-12" style="padding: 0px; min-height: 40px;">

		<div dynamic="adminMenu"></div>

		</div>
	</div>


	<div class="row">

		<div class="col-xs-12 col-sm-6">
			<!-- <h3 style="margin-top: 10px;">Mis reservas</h3> -->
		</div>

		<div class="col-xs-12 col-sm-6" style="text-align: right">
			<span dynamic="exportLink" style="display: inline-block"></span>
			<span> <!-- class="{{ actionForm.rs_US_is_admin === 'A' ? '' : 'hide' }}" -->
			<md-button class="md-raised md-primary" ng-click="gamingModule()" title="Resultados"><md-icon> extension </md-icon> Resultados </md-button>
			</span>
			<md-button class="md-raised md-accent" ng-click="initReg()" data-toggle="modal" data-target="#rsDSPFIL_ADDRCD_modal" title="<bean:message key="common.client.nuevo"/>"><md-icon> add </md-icon> Nueva reserva </md-button>
		</div>

	</div>


	<div class="row">
		
		<div class="col-xs-12" style="margin-top: 10px;">
			<div class="row" style="margin: 0px">
				<div class="col-xs-12 box">
					<div class="row">

						<div style="padding: 0px 8px">
							<div layout="row" layout-align="space-between center">
								<div class="list-head-first" style="padding:0px; width: 50%;">
									<div>
										<div>
											<md-button class="md-fab md-mini" style="padding: 0;" aria-label="avatar">
												<img width="40px" ng-src="{{actionForm.rs_US_avatar}}" />
											</md-button>
											<img src="./FvrServlet?ACC=getFlgImg&KPS={{actionForm.rs_US_country_id}}" ></img>
											{{actionForm.rs_US_nick}}
										</div>
<%-- 
									<md-input-container style="margin: 0px 20px;">
										<label class="hidden">fecha</label>
										<md-datepicker 
											ng-model="aux_FLT_rs_start_date"
											md-hide-icons="calendar"
											ng-change="filtrar()"
										></md-datepicker>
									</md-input-container>

 &nbsp;&nbsp;&nbsp;&nbsp;
										<div>
											<h3>Mis reservas</h3>
										</div>
 --%>
									</div>
								</div>
								<div>
									<md-button class="btn-head-list md-icon-button md-primary md-button md-ink-ripple" ng-click="filtrar()" title="<bean:message key="common.client.refrescar"/>"><md-icon> autorenew </md-icon></md-button>
<%--
	 								<md-button class="btn-head-list md-icon-button md-primary md-button md-ink-ripple" ng-click="exportar()" title="<bean:message key="common.client.exportar"/>"><md-icon> file_download </md-icon></md-button>
									<md-button class="btn-head-list md-icon-button md-primary md-button md-ink-ripple" onclick="$('#filterIcon').toggleClass('rotate');$('#filaFiltros').slideToggle(400);" title="<bean:message key="common.client.filtrar"/>"><md-icon id="filterIcon"> filter_list </md-icon></md-button>
--%>
									<md-button class="btn-head-list md-icon-button md-primary md-button md-ink-ripple" ng-click="rtPg()" title="<bean:message key="common.client.retroceder"/>"><md-icon style=" -ms-transform: rotate(180deg); /* IE 9 */
    -webkit-transform: rotate(180deg); /* Chrome, Safari, Opera */
    transform: rotate(180deg);"> forward </md-icon></md-button>
									<md-button class="btn-head-list md-icon-button md-primary md-button md-ink-ripple" ng-click="avPg()" title="<bean:message key="common.client.avanzar"/>"><md-icon> forward </md-icon></md-button>
									<!-- <span>{{actionForm.logon_USR}}</span> -->
									<!-- Paginador inicio -->
									<div style="display: inline-block;margin-left:10px;">
										<input type="hidden" ng-model="actionForm.filaInicioGrid" />
										<input type="hidden" ng-model="actionForm.filasTotales" />
										<input type="hidden" ng-model="actionForm.rs_filtro.rs_user_id"/>
										<span dynamic="txtHtmlPaginador"></span>
										<md-input-container style="margin:0px;">
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
						</div>

						<div class="col-xs-12 hidden">
							<div class="list">
								<div>
									<span>Reserva</span>
									<span>local</span>
									<span class="hidden">usuario</span>
									<span class="hidden">tfno.</span>
									<span>concepto</span>
									<span><!-- Bonificaciones --></span>
									<span class="hidden">cantidad</span>
									<span class="hidden">bonif.1</span>
									<span class="hidden">bonif.2</span>
									<span style="text-align:right;">importe</span>
									<span class="hidden">moneda</span>
									<span>pago</span>
									<span>fecha, hora, minutos</span>
									<span class="hidden">hora</span>
									<span class="hidden">minutos</span>
									<span class="hidden">Ejecutado</span>
									<span style="text-align:right;">plazas</span>
									<span class="hidden">nota</span>
								</div>
							</div>
						</div>


						<div class="col-xs-12">
							<div class="list" ng-repeat="reg in actionForm.grid" ng-class="reg.rs_pay_status.includes('_OK') ? 'list success' : (reg.rs_pay_status.includes('_KO') ? 'list cancel' : 'default')">
								<div class="status-area">
									<div class="metadata-row">
      										<md-chip>{{reg.rs_pay_status.includes('_OK') ? reg.rs_pay_status.split('_OK').join('') :
										(reg.rs_pay_status.includes('_KO') ? reg.rs_pay_status.split('_KO').join('') :
										reg.rs_pay_status.split('_PDT').join('')) }}</md-chip>
									</div>
									<div class="metadata-row">
									<md-icon style="font-size: 20px;padding-top: 2px;">local_offer</md-icon> {{reg.rs_reservation_id}}
									</div>
									
								</div>

								<div class="list-info">
									<div class="list-info-container">
										<div class="list-info-header">
											{{reg.rs_PT_name}}&nbsp;&nbsp;&nbsp; 
											{{reg.rs_PT_name2}}&nbsp;&nbsp;&nbsp;
											{{reg.rs_PT_name3}}
										</div>
										<div class="list-info-content">						    	
											{{ reg.rs_coupon_id === '' ? '' : ('Cupón: ' + reg.rs_coupon_id) }}&nbsp;
											<span class="hidden">{{reg.rs_quantity}}</span>
											<span class="hidden">{{reg.rs_US_nick}}</span>
											<span class="hidden">{{reg.rs_phone}}</span>	
											{{ reg.rs_executed_at === '' ? '' : (' CONSUMIDA: ' + reg.rs_executed_at.substring(4,6) + '/' + reg.rs_executed_at.substring(2,4) + '/' + reg.rs_executed_at.substring(0,2) + ' ' + reg.rs_executed_at.substring(6,8) + ':' + reg.rs_executed_at.substring(8,10)) }}&nbsp;

											{{reg.rs_note | limitTo:'30'}}

											<span class="hidden">
												<md-button class="md-fab md-mini" style="padding: 0;" aria-label="avatar" title="{{reg.rs_user_id}}">
													<img width="40px" ng-src="{{reg.rs_US_avatar}}" />
												</md-button>
											</span>
										</div>
									</div>
								</div>


								<div class="metadata">
									<div class="metadata-row">
										<span class="metadata-item"><i class="list-icon material-icons">group</i> {{reg.rs_places}}</span>
										<span class="metadata-item"><i class="list-icon material-icons">access_time</i> {{reg.rs_PT_duration_minutes}} min</span>
									</div>
									<div class="metadata-row">
										<span class="metadata-item"><i class="list-icon material-icons">alarm_on</i> {{reg.rs_start_time.substr(0,2)}}:{{reg.rs_start_time.substr(2,2)}}</span>
										<span class="metadata-item"><i class="list-icon material-icons">event_available</i> {{reg.rs_start_date}}</span>
									</div>
								</div>

								<div class="list-price">
									{{reg.rs_amount}}&nbsp;{{reg.rs_currency}}
								</div>
							</div>
						</div>	

					</div>
				</div>
			</div>
		</div>
<!-- Panel ADDRCD inicio -->	
<div class="container">
	<!-- Modal -->
	<!-- <div class="modal animate-modal" id="rsDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false" style="background-color: #F5F5F5;"> -->
	<div class="modal fade" id="rsDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false" style="background-color: #F5F5F5;">
		<div class="modal-dialog modal-lg" >

			<!-- Modal content-->
			<div class="modal-content" style="background-color: #F5F5F5;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" ng-click="filtrar()">&times;</button>
					<h6 class="modal-title" style="margin-top: 4px;" layout="row" layout-align="space-between none">
						<picture>
							<img class="brand-img hidden-xs" style="width: 250px;margin-left: 20px;" src="./resBS/img/formulavr.png" alt="Formula VR" onclick="window.location ='./index.jsp';"/>
							<img class="brand-img show-xs" style="width: 200px;margin-left: 20px;" src="./resBS/img/formulavr.png" alt="Formula VR" onclick="window.location ='./index.jsp';"/>
						</picture>
						<span class="modal-spn-title hidden-xs">{{varGlobal.logon_USR}}&nbsp;&nbsp;</span>
					</h6>
				</div>
				<div class="modal-body" style="padding: 0px 15px 15px 15px;">
					<span ng-include="'ng/rs_reservations/views/RsADDRCD_view_basic.jsp'" />
				</div>

				<div class="modal-footer hidden">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				</div>

			</div>

		</div>
	</div>
</div>
<!-- Panel ADDRCD final -->	

	</div>
