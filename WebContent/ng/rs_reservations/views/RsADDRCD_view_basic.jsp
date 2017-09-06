<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<form name="RsADDRCD_form" class="modal-open md-dialog-is-showing">

<section>

	<div class="row">

	     <div class="col-xs-12 col-sm-8 col-md-8">
	
		     <div class="row" style="margin: 15px 0px">
		
		     	<div class="col-xs-12 box" style="padding: 0px 15px 0px;">
		
				<div class="row">
					<div class="col-xs-12" style="color: #FFF;background-color: #333C44;margin-bottom: 25px;">
					<h5>1 - Reserva<h5><span class="modal-spn-title hidden-sm hidden-md hidden-lg">&nbsp;{{varGlobal.logon_USR}}</span>
					</div>
				</div>
				<div class="row">
				<div id="contentReservar" class="col-xs-12">
		     	<div class="row">

		     		<div class="col-xs-12 col-sm-6 col-md-4">
						<!-- Selector CENTRO -->
						<md-input-container class="md-block input-md">
							<label>Establecimiento</label>
							<md-icon>place</md-icon>
							<md-select placeholder="Seleccione Local" ng-model="$parent.aux_rs_location_id" md-on-open="location_id_onOpen()" md-on-close="location_id_onClose()" ng-model-options="{trackBy: '$value.value'}" ng-change="getprice()" required>
								<!-- <md-optgroup label="Local"> -->
									<md-option ng-value="item" ng-repeat="item in $parent.lst_lo">{{item.displayName}}</md-option>
								<!-- </md-optgroup> -->			
							</md-select>
			    		</md-input-container>
			    		<!-- Selector CENTRO fin -->
		     		</div>
	
		     		<div class="col-xs-12 col-sm-6 col-md-4">
		     			<!-- Fecha -->
						<md-input-container class="md-block input-md">
							<label>Fecha a Reservar</label>
							<md-icon>today</md-icon>
							<md-datepicker 
									ng-model="$parent.aux_rs_start_date"
									md-min-date="$parent.aux_rs_start_date_minDate"
									md-max-date="$parent.aux_rs_start_date_maxDate"
									md-date-filter="$parent.aux_rs_start_date_filterFnc"
									md-hide-icons="calendar"
									md-open-on-focus="true"
									style="margin-left: 30px;"
									ng-change="start_date_onChange();getprice();"
									required
							></md-datepicker>
						</md-input-container>
						<!-- Fecha fin -->
		     		</div>
	
		     		<div class="col-xs-12 col-sm-6 col-md-4">
		     			<!-- hora -->
						<md-input-container class="md-block input-md">
							<label>Hora</label>
							<md-icon>access_time</md-icon>
							<md-select placeholder="Seleccione Hora" ng-model="$parent.aux_rs_start_time" ng-model-options="{trackBy: '$value.value'}" ng-change="getprice();" required>
								<!-- <md-optgroup label="Hora"> -->
									<md-option ng-value="item" ng-repeat="item in $parent.lst_tt">{{item.displayName}}</md-option>
								<!-- </md-optgroup> -->			
							</md-select>
						</md-input-container>
						<!-- hora fin -->
		     		</div>
		     	</div>	
		
		     	<div class="row">
	
		     		<div class="col-xs-12 col-sm-6 col-md-4">
			     		<!-- Selector PRODUCTO -->
						<md-input-container class="md-block input-md">
							<label>Modalidad</label>
							<md-icon>local_offer</md-icon>
							<md-select placeholder="Seleccione Modalidad" ng-model="$parent.aux_rs_product_id" ng-change="getprice();" ng-model-options="{trackBy: '$value.value'}" required>
								<!-- <md-optgroup label="Opciones"> -->
									<md-option ng-value="item" ng-repeat="item in $parent.lst_pt">{{item.displayName}}</md-option>
								<!-- </md-optgroup> -->			
							</md-select>
						</md-input-container>
			    		<!-- Selector PRODUCTO fin -->
		     		</div>
	
		     		<div class="col-xs-12 col-sm-6 col-md-4">
		     			<!-- plazas -->
						<md-input-container class="md-block input-md">
							<label>Coches</label>
							<md-icon>supervisor_account</md-icon>
							<md-select placeholder="Seleccione Plazas" ng-model="$parent.actionForm.rs_places" ng-change="getprice();" required>
								<!-- <md-optgroup label="Plazas"> -->
									<md-option ng-repeat="item in $parent.lst_cp" value="{{item}}">{{item}} cabinas</md-option>
								<!-- </md-optgroup> -->			
							</md-select>
						</md-input-container>
						<!-- plazas fin -->
		     		</div>
	
		     		<div class="col-xs-12 col-sm-6 col-md-4">
		     			<!-- phone -->
						<md-input-container class="md-block input-md">
							<label>Teléfono</label>
							<md-icon> phone </md-icon>
							<input type="text" ng-model="actionForm.rs_phone" required />
						</md-input-container>
						<!-- phone fin -->
		     		</div>
	
		     	</div>	
		
		     	<div class="row">
		
		     		<div class="col-xs-12 col-sm-6 col-md-4">
		     			<!-- Nick -->
						<md-input-container class="md-block input-md">
							<label> Alias de piloto (Nick)</label>
							<md-icon>person</md-icon>
							<input type="text" ng-model="actionForm.rs_US_nick" required />
						</md-input-container>
						<!-- Nick fin -->
		     		</div>
	
		     		<div class="col-xs-12 col-sm-6 col-md-4">
			     		<md-input-container class="md-block input-md">
							<label>Notas o comentario (opcional)</label>
							<md-icon> message </md-icon>
							<textarea name="rs_note" md-maxlength="200" cols="100" rows="1" ng-model="actionForm.rs_note"></textarea>
						    <div ng-messages="RsADDRCD_form.rs_note.$error" ng-show="RsADDRCD_form.rs_note.$dirty">
						      <div ng-message="md-maxlength">Comentario demasiado largo!</div>
						    </div>
			 			</md-input-container>
		     		</div>
	
		     		<div class="col-xs-12 col-sm-6 col-md-4">
						<md-input-container class="md-block input-md">
							<label>Cupón promocional (opcional)</label>
							<md-icon> card_giftcard </md-icon>
							<input type="text" ng-model="actionForm.rs_coupon_id" ng-change="//getprice();"/>
						</md-input-container>
		     		</div>
	
		     	</div>
		
		     	<div class="row text-right">
		     		<div class="col-xs-12">
		     			<h3 style="margin-top: 10px;text-align:right;" md-colors="{color: 'default-accent-800'}">Total a pagar: <span style="padding-left: 20px;font-weight: bolder;">{{actionForm.rs_amount | number:2 }}&nbsp;€</span></h3>
		     		</div>
		     		<div class="col-xs-12 text-right" style="margin-bottom: 20px;">
		     			<md-button class="md-raised md-accent" ng-click="check()" style="font-size: 1.2em;padding: 5px 25px;">Siguiente</md-button>
		     		</div>
		     	</div>

		    </div> 	
		    </div>
				<div class="row">
					<div class="col-xs-12" style="color: #FFF;background-color: #333C44;margin-bottom: 25px;">
					<h5>2 - Finalizar Reserva<h5>
					</div>
				</div>
		     	<div id="contentPayment" style="display: none" class="row">

		     		<div class="col-xs-12 col-sm-6">
			     		<h5>Resumen</h5>
			     		<div class="row" style="margin: 0px;">
		     				<div class="col-xs-12">
					     		<div class="row" style="line-height: 35px;border-top: 1px solid #EAEAEA;">
						     		<div class="col-xs-5">Lugar:</div>
						     		<div class="col-xs-7"><b>{{ actionForm.rs_LO_name }}</b></div>
						     	</div>
					     		<div class="row" style="line-height: 35px;border-top: 1px solid #EAEAEA;">	
					     			<div class="col-xs-5">Día:</div>
					     			<div class="col-xs-7"><b>{{actionForm.rs_start_date | date:'EEEE, d MMMM y'}}</b></div>
									</div>
					     		<div class="row" style="line-height: 35px;border-top: 1px solid #EAEAEA;">
					     			<div class="col-xs-5">Hora:</div>
					     			<div class="col-xs-7"><b>{{actionForm.rs_start_time.substr(0,2)}}:{{actionForm.rs_start_time.substr(2,2)}}</b></div>
					     		</div>
					     		<div class="row" style="line-height: 35px;border-top: 1px solid #EAEAEA;">
					     			<div class="col-xs-5">Plazas:</div>
					     			<div class="col-xs-7"><b>{{ actionForm.rs_places }}</b></div>
					     		</div>
					     		<div class="row" style="line-height: 35px;border-top: 1px solid #EAEAEA;border-bottom: 1px solid #EAEAEA;">
					     			<div class="col-xs-5">Total:</div>
					     			<div class="col-xs-7"><b>{{actionForm.rs_amount | number:2 }}&nbsp;€</b></div>
					     		</div>
					     	</div>
					    </div>
			     		<div class="row" >
			     			<div class="col-xs-12" style="margin-top: 20px;margin-bottom: 20px;">
			     				<md-button class="md-raised" ng-click="revisarReserva();">Revisar</md-button> 
			     			</div>
			     		</div>
		     		</div>
		     		<div class="col-xs-12 col-sm-6">
							<h5>Selecciona una forma de pago</h5> 
							<p>&nbsp;</p>	 
					    <md-radio-group ng-model="paymentCheck.checked"> 

					      <md-radio-button style="display: none;" id="radio_TPV" value="TPV" class="md-primary" style="border-top: 1px dashed;border-bottom: 1px dashed;line-height: 50px;"><b>Tarjeta</b> <img class="logo-icon-img" style="height: 40px;padding-left: 20px;" src="./resBS/img/tarjetas.png" alt="" /></md-radio-button> 
					      <md-radio-button style="display: none;" id="radio_PAYPAL" value="PAYPAL" class="md-primary" style="border-top: 1px dashed;border-bottom: 1px dashed;line-height: 50px;"><b>PayPal</b> <img class="logo-icon-img" style="height: 40px;padding-left: 20px;" src="./resBS/img/paypal.png" alt=""/></md-radio-button> 
					      <md-radio-button value="CASH" class="md-primary" style="border-top: 1px dashed; border-bottom: 1px dashed;line-height: 50px;"><b>Efectivo / Tarjeta en establecimiento</b></md-radio-button> 

					    </md-radio-group> 

							<div class="row">
								<div class="col-xs-12 text-right" style="margin-bottom:20px;">
									<md-button class="md-raised md-accent" ng-click="payment();" style="font-size: 1.2em;padding: 5px 25px;">Reservar</md-button>
								</div>
							</div>
						</div>
		     	</div>
		
				<div class="hide">
							<md-input-container class="col-sm-3">
								<label>quantity</label>
								<input type="text" ng-model="$parent.actionForm.rs_quantity" required/>
							</md-input-container>
				 			<md-input-container class="col-sm-3">
								<label>amount</label>
								<input type="text" ng-model="actionForm.rs_amount" />
							</md-input-container>
							<md-input-container class="col-sm-3">
								<label>currency</label>
								<input type="text" ng-model="actionForm.rs_currency" readonly="readonly"/>
							</md-input-container>
							<md-input-container class="col-sm-3">
								<label>pay_status</label>
								<input type="text" ng-model="actionForm.rs_pay_status" />
							</md-input-container>
						</div>		

		 			</div>		
		
		 	</div>		
	
	 	</div>		


	    <div class="col-xs-12 col-sm-4 col-md-4">
	
			<div class="row" style="margin: 15px 0px">
				<div class="col-xs-12 box" style="padding: 0px 15px 25px;">
					<div class="row">
						<div class="col-xs-12" style="color: #FFF;background-color: #333C44;margin-bottom: 0px;">
							<h5>Ocupación:&nbsp;{{actionForm.rs_LO_name}}<h5>
						</div>
					</div>
					<h4>{{actionForm.rs_start_date | date:'EEEE, d MMMM y' }}</h4>
					<div class="table-responsive">
						<table class="table table-striped" style="border: 1px solid #ddd">
							<thead>
								<tr>
									<th>Hora</th>
									<th>Ocupados</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in lst_ocupacion.registros" ng-click="null">
									<td>{{item.ts_start_time.substr(0,2)}}:{{item.ts_start_time.substr(2,2)}}</td>
									<td>{{item.ts_RS_places}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
	
		</div>

	</div>		

</section>
    <div class="modal-footer hidden">
    	<div layout="row" layout-align="start start">

    	</div>
    </div>

</form>
