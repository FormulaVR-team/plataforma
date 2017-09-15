<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="Ad_rsDSPFIL_form">
			
	<h1 class="page-title">Reservations</h1>

	<div class="row">
		<div class="col-xs-12 col-sm-5">
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
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="initReg()" data-toggle="modal" data-target="#ad_rsDSPFIL_ADDRCD_modal">
					<md-tooltip><bean:message key="common.client.nuevo"/></md-tooltip>
					<md-icon> add </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" onclick="$('#filterIcon').toggleClass('rotate');$('#ad_rsFilterBox').slideToggle();">
					<md-tooltip><bean:message key="common.client.filtrar"/></md-tooltip>
					<md-icon id="filterIcon"> filter_list </md-icon></md-button>
				<md-button class="md-icon-button md-accent md-button md-ink-ripple margin-0" ng-click="exportar()">
					<md-tooltip><bean:message key="common.client.exportar"/></md-tooltip>
					<md-icon> file_download </md-icon></md-button>
				&nbsp;&nbsp;
				<md-button class="md-button md-fab md-mini" onclick="$('#ad_rsDSPFIL_OCUPACION_box').slideToggle();">
					<md-tooltip>Ocupación</md-tooltip>
					<md-icon id="ocupacionIcon"> people </md-icon></md-button>
			</div>
			<!-- Botones final -->
		</div>

		<div class="col-xs-12 col-sm-2 text-right">
			<md-input-container>
				<label>location</label>
				<md-select placeholder="Local" ng-model="aux_FLT_rs_location_id" md-on-close="filtrar()" ng-model-options="{trackBy: '$value.value'}">
					<md-optgroup label="Local">
						<md-option ng-value="item" ng-repeat="item in lst_lo">{{ item.displayName }}</md-option>
					</md-optgroup>			
				</md-select>
			</md-input-container>
		</div>

		<div class="col-xs-12 col-sm-5 text-right">
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
		<div class="col-xs-12 col-sm-3">
			<md-input-container class="md-block">
				<label>aaaa-mm-dd</label>
				<input type="text" ng-keyup="filtrar_deferred()" ng-model="actionForm.ad_rs_filtro.ad_rs_start_date" debounce="800" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-3">
			<md-input-container class="md-block">
				<label>mail</label>
				<input type="text" ng-keyup="filtrar_deferred()" ng-model="actionForm.ad_rs_filtro.ad_rs_user_id" debounce="800" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-3">
			<md-input-container class="md-block">
				<label>nick</label>
				<input type="text" ng-keyup="filtrar_deferred()" ng-model="actionForm.ad_rs_filtro.ad_rs_US_nick" debounce="800" />
			</md-input-container>
		</div>
		<div class="col-xs-12 col-sm-3">
			<md-input-container class="md-block">
				<label>Núm.reserva</label>
				<input type="text" ng-keyup="filtrar_deferred()" ng-model="actionForm.ad_rs_filtro.ad_rs_reservation_id" debounce="800" />
			</md-input-container>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<%-- Caja de filtros inicio --%>
			<div id="ad_rsDSPFIL_OCUPACION_box" class="box" style="display:none;">
				<div class="row row-filter">
					<h4>Local:&nbsp;{{aux_FLT_rs_location_id.displayName}}<h4>
					<h5>Fecha:&nbsp;{{actionForm.ad_rs_filtro.ad_rs_start_date | date:'EEEE, d MMMM y' }}</h5>
					<div class="table-responsive box">
						<table class="table table-striped" style="border: 1px solid #ddd">
							<thead>
								<tr>
									<th>Hora</th>
									<th>Reservas</th>
									<th>Plazas</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in lst_ocupacion.registros" ng-click="null">
									<td>{{item.ts_start_time.substr(0,2)}}:{{item.ts_start_time.substr(2,2)}}</td>
									<td>{{item.ts_RS_quantity}}</td>
									<td>{{item.ts_RS_places}}</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" onclick="$('#ad_rsDSPFIL_OCUPACION_box').slideToggle();">Cerrar</md-button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<%-- Caja de filtros inicio --%>
			<div id="ad_rsFilterBox" class="box" style="display:none;">
				<div class="row row-filter">
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>sincro</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_sincro" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>mark</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_mark" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>is_deleted</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_is_deleted" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>author</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_author" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>LO_name</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_LO_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>US_country_id</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_US_country_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>US_avatar</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_US_avatar" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>US_is_admin</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_US_is_admin" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>US_first_name</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_US_first_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>US_last_name</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_US_last_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>product_id</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_product_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_name</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_name" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_deadline</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_deadline" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_isPercent</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_isPercent" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_amount</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_amount" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_duration_minutes</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_duration_minutes" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>quantity</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_quantity" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>product_id2</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_product_id2" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_name2</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_name2" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_deadline2</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_deadline2" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_isPercent2</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_isPercent2" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_amount2</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_amount2" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>product_id3</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_product_id3" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_name3</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_name3" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_deadline3</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_deadline3" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_isPercent3</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_isPercent3" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>PT_amount3</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_PT_amount3" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>amount</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_amount" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>currency</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_currency" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>phone</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_phone" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>pay_status</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_pay_status" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>start_time</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_start_time" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>duration_minutes</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_duration_minutes" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>places</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_places" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>coupon_id</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_coupon_id" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>executed_at</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_executed_at" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>note</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_note" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>comment</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_comment" />
						</md-input-container>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<md-input-container class="md-block">
							<label>json</label>
							<input type="text" ng-model="actionForm.ad_rs_filtro.ad_rs_json" />
						</md-input-container>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 pull-right">
						<md-button class=" md-raised md-primary md-button md-ink-ripple width-100" ng-click="filtrar()" onclick="$('#filterIcon').toggleClass('rotate');$('#ad_rsFilterBox').slideToggle();">Aplicar</md-button>
					</div>
				</div>
			</div>
			<!-- Caja de filtros final -->
		</div>
	</div>
		
	<style>
		.PAYPAL_OK, .TPV_OK, .GRATIS_OK, .CASH_OK, .e_CASH_OK, .t_CASH_OK, .c_CASH_OK {
			/* color: white; */
			background-color: #a0ff00;
		}
		.PAYPAL_KO, .TPV_KO {
			color: white;
			background-color: #ff4000;
		}
		.CASH_PDT {
			color: white;
			background-color: #8000ff;
		}
		.executed {
			/* color: white; */
			background-color: #a0ffff;
		}
	</style>

	<div class="table-responsive box">
  	<table class="table table-striped table-hover">
  		<thead>	
				<tr>
					<!-- <th>{{actionForm.filasMarcadas}}&nbsp;{{actionForm.clavesMarcadas}}</th> -->
					<th>created_at</th>
					<th>exec_at</th>
					<th>pay_sts</th>
					<!-- <th>mark</th> -->
					<!-- <th>is_deleted</th> -->
					<!-- <th>author</th> -->
					<th>reservation</th>
					<!-- <th>location_id</th> -->
					<!-- <th>LO_name</th> -->
					<th>avtr</th>
					<th>flag</th>
					<th>nick</th>
					<th>mail</th>
					<!-- <th>US_is_admin</th> -->
					<!-- <th>US_first_name</th> -->
					<!-- <th>US_last_name</th> -->
					<!-- <th>product_id</th> -->
					<th>product</th>
					<!-- <th>PT_deadline</th> -->
					<!-- <th>PT_isPercent</th> -->
					<!-- <th>PT_amount</th> -->
					<!-- <th>PT_duration_minutes</th> -->
					<!-- <th>quantity</th> -->
					<th>Bonus auto</th>
					<!-- <th>PT_name2</th> -->
					<!-- <th>PT_deadline2</th> -->
					<!-- <th>PT_isPercent2</th> -->
					<!-- <th>PT_amount2</th> -->
					<th>Bonus man</th>
					<!-- <th>PT_name3</th> -->
					<!-- <th>PT_deadline3</th> -->
					<!-- <th>PT_isPercent3</th> -->
					<!-- <th>PT_amount3</th> -->
					<th>coupon</th>
					<th>amount</th>
					<!-- <th>currency</th> -->
					<th>phone</th>
					<th>date time minutes</th>
					<!-- <th>start_time</th> -->
					<!-- <th>duration_minutes</th> -->
					<th>places</th>
					<!-- <th>note</th> -->
					<!-- <th>comment</th> -->
					<!-- <th>json</th> -->
				</tr>
			</thead>
			<tbody>
				<tr title="{{reg.ad_rs_pay_status}}" ng-repeat="reg in actionForm.grid" ng-click="putRecordAsTheCurrent(reg)" data-toggle="modal" data-target="#ad_rsDSPFIL_EDTRCD_modal">
					<!-- <td><input type="checkbox" onclick="event.stopPropagation();" ng-model="actionForm.filasMarcadas[$index]" ng-click="setClaveMarcada( this.reg.key, $index );"/></td> -->
					<td>
						{{ reg.ad_rs_sincro === '' ? '' : (reg.ad_rs_sincro.substring(4,6) + '/' + reg.ad_rs_sincro.substring(2,4) + '/' + reg.ad_rs_sincro.substring(0,2) + ' ' + reg.ad_rs_sincro.substring(6,8) + ':' + reg.ad_rs_sincro.substring(8,10)) }}&nbsp;
					</td>
					<td class="{{ reg.ad_rs_executed_at === '' ? '' : 'executed'}}">
						{{ reg.ad_rs_executed_at === '' ? '' : (reg.ad_rs_executed_at.substring(4,6) + '/' + reg.ad_rs_executed_at.substring(2,4) + '/' + reg.ad_rs_executed_at.substring(0,2) + ' ' + reg.ad_rs_executed_at.substring(6,8) + ':' + reg.ad_rs_executed_at.substring(8,10)) }}&nbsp;
					</td>
					<td class="{{reg.ad_rs_pay_status}}" style="text-align: center;">{{reg.ad_rs_pay_status}}&nbsp;</td>
					<!-- <td>{{reg.ad_rs_mark}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_is_deleted}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_author}}&nbsp;</td> -->
					<th>{{reg.ad_rs_reservation_id}}&nbsp;</th>
					<!-- <td>{{reg.ad_rs_location_id}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_LO_name}}&nbsp;</td> -->
					<td style="padding:0;"><img src="./FvrServlet?ACC=getUsrImg&BIN&USR={{reg.ad_rs_user_id}}" ></img></td>
					<td style="padding:0;"><img src="./FvrServlet?ACC=getFlgImg&KPS={{reg.ad_rs_US_country_id}}" ></img></td>
					<td>{{reg.ad_rs_US_nick | limitTo:'10'}}&nbsp;</td>
					<td>{{reg.ad_rs_user_id}}&nbsp;</td>
					<!-- <td>{{reg.ad_rs_US_is_admin}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_US_first_name}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_US_last_name}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_product_id}}&nbsp;</td> -->
					<td title="{{reg.ad_rs_product_id}}">{{reg.ad_rs_PT_name}}&nbsp;</td>
					<!-- <td>{{reg.ad_rs_PT_deadline}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_isPercent}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_amount}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_duration_minutes}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_quantity}}&nbsp;</td> -->
					<td title="{{reg.ad_rs_PT_name2}}">{{reg.ad_rs_product_id2}}&nbsp;</td>
					<!-- <td>{{reg.ad_rs_PT_name2}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_deadline2}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_isPercent2}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_amount2}}&nbsp;</td> -->
					<td title="{{reg.ad_rs_PT_name3}}">{{reg.ad_rs_product_id3}}&nbsp;</td>
					<!-- <td>{{reg.ad_rs_PT_name3}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_deadline3}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_isPercent3}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_PT_amount3}}&nbsp;</td> -->
					<td title="{{reg.ad_rs_coupon_id}}">{{reg.ad_rs_coupon_id | limitTo:'10'}}&nbsp;</td>
					<th style="text-align: right;">{{ reg.ad_rs_amount === 0 ? '' : reg.ad_rs_amount }}&nbsp;</th>
					<!-- <td>{{reg.ad_rs_currency}}&nbsp;</td> -->
					<td>{{reg.ad_rs_phone}}&nbsp;</td>
					<td>{{reg.ad_rs_start_date}}&nbsp;{{reg.ad_rs_start_time}}&nbsp;{{reg.ad_rs_PT_duration_minutes}}&nbsp;</td>
					<td>{{reg.ad_rs_places}}&nbsp;</td>
					<!-- <td>{{reg.ad_rs_note}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_comment}}&nbsp;</td> -->
					<!-- <td>{{reg.ad_rs_json}}&nbsp;</td> -->
				</tr>
			</tbody>
		</table>
	</div>

	
<!-- Panel ADDRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="ad_rsDSPFIL_ADDRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
			<i class="mfw-icon material-icons">clear</i>
		  </button>
          <h2 class="modal-title">Agregar: reservations</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/ad_rs_reservations/views/Ad_rsADDRCD_view_basic.jsp'" />
        </div>
      </div>      
    </div>
  </div>
</div>
<!-- Panel ADDRCD final -->	
	
<!-- Panel EDTRCD inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade modal-full-width" id="ad_rsDSPFIL_EDTRCD_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
			<i class="mfw-icon material-icons">clear</i>
		  </button>
          <h2 class="modal-title">Modificar: reservations</h2>
        </div>
        <div class="modal-body">
			<span ng-include="'ng/ad_rs_reservations/views/Ad_rsEDTRCD_view_basic.jsp'" />
        </div>
      </div>      
    </div>
  </div>
</div>
<!-- Panel EDTRCD final -->	

<!-- Panel CONFIRMAR RESERVA inicio -->	
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="ad_rsDSPFIL_CONFIRM_modal" role="dialog" data-backdrop="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="color: white; background-color: red;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">EJECUTAR RESERVA ({{actionForm.ad_rs_pay_status}})</h4>
        </div>
        <div class="modal-body">

	        <div class="{{actionForm.ad_rs_pay_status === 'CASH_PDT' ? '' : 'hide'}}" layout="column" layout-align="space-around start">
	        	<div>
	        		<h3>{{actionForm.ad_rs_reservation_id}}, Euros: {{actionForm.ad_rs_amount}}</h3> 
	        	</div>
</br>
	        	<div>
					<md-button class="md-raised md-primary" data-dismiss="modal" data-toggle="modal" ng-click="executeReservation('t')" >t&nbsp;<md-icon> settings_remote </md-icon>&nbsp;&nbsp;TPV</md-button>
					<md-button class="md-raised md-warn" data-dismiss="modal" data-toggle="modal" ng-click="executeReservation('e')" >e&nbsp;<md-icon> euro_symbol </md-icon>&nbsp;&nbsp;Efectivo</md-button>
					<md-button class="md-raised md-accent" data-dismiss="modal" data-toggle="modal" ng-click="executeReservation('c')" >c&nbsp;<md-icon> gavel </md-icon>&nbsp;&nbsp;Compensación</md-button>
	        	</div>
</br>
				<div layout="row" layout-align="start center">
					<md-input-container>
						<label>Tarjeta prepago</label>
						<h3><input id="laCaca" type="text" ng-model="actionForm.ad_rs_coupon_id" /></h3>
					</md-input-container>
					<md-button class="md-raised md-accent md-fab md-mini" ng-click="card_view_details()" ><md-icon> help_outline </md-icon></md-button>
					<md-button class="md-raised md-warn" ng-click="executeReservation('prepago')" ><md-icon> card_membership </md-icon>&nbsp;&nbsp;Descontar de tarjeta</md-button>
				</div>
	        </div>

        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>

      </div>
      
    </div>
  </div>
</div>
<!-- Panel CONFIRMAR RESERVA final -->	


</div>

