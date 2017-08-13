<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="UsEDTRCD_form">
	<div class="row">
		<div class="col-xs-12" style="padding: 0px; min-height: 50px;">

		<div dynamic="adminMenu"></div>

		</div>
	</div>
<!-- 
	<div class="row">
		<div class="col-xs-12">
			<h3>Mis datos</h3>
		</div>
	</div>
 -->
	<div class="row">

		<div class="col-xs-12 col-sm-8">

			<div class="row" style="margin: 0px">
		     	<div class="col-xs-12 box" style="padding: 15px 15px 25px;">
		
		     		<div class="row">
		     			<div class="col-xs-12 col-sm-6 col-md-4">
		     				<md-input-container class="md-block input-md">
							<label>Correo electrónico</label>
							<input type="text" ng-model="actionForm.us_user_id" required readonly="readonly"/>
						</md-input-container>
		     			</div>
		     			<div class="col-xs-12 col-sm-6 col-md-4">
		     				<md-input-container class="md-block input-md">
							<label>Nombre</label>
							<input type="text" ng-model="actionForm.us_first_name" required onclick="this.select();" />
						</md-input-container>
		     			</div>     			
		     			<div class="col-xs-12 col-sm-6 col-md-4">
		     				<md-input-container class="md-block input-md">
							<label>Apellidos</label>
							<input type="text" ng-model="actionForm.us_last_name" required onclick="this.select();" />
						</md-input-container>
		     			</div>     			
		     			<div class="col-xs-12 col-sm-6 col-md-4">
		     				<md-input-container class="md-block input-md">
							<label>Teléfono</label>
							<input type="text" ng-model="actionForm.us_phone" required onclick="this.select();" />
						</md-input-container>
		     			</div>     			
		     			<div class="col-xs-12 col-sm-6 col-md-4">
						<md-input-container class="md-block input-md">
							<label>Alias de piloto (Nick)</label>
							<input type="text" ng-model="actionForm.us_nick" required onclick="this.select();" />
						</md-input-container>
		     			</div>  			
		     			<div class="col-xs-12 col-sm-6 col-md-4">
		     			<md-input-container class="md-block input-md">
							<!-- <label>País actual</label> -->
			     			<span style="font-size: 16px;"><img src="{{actionForm.us_PS_flag_base64}}" ></img>&nbsp;&nbsp;&nbsp;{{actionForm.us_PS_name}}</span>
						</md-input-container>
		     			</div>	
		     		</div>
		
		     		<div class="row">
		     			<div class="col-xs-12 col-sm-6 col-md-4">
		     			<md-input-container class="md-block input-md">
							<label>Género</label>
						    <md-input-container class="md-block input-md">
						        <md-radio-group ng-model="actionForm.us_gender">
						            <md-radio-button name="gender" value="M">Hombre</md-radio-button><br>
						            <md-radio-button name="gender" value="F">Mujer</md-radio-button>
						        </md-radio-group>
						    </md-input-container>					
						</md-input-container>
		     			</div>	
		     			<div class="col-xs-12 col-sm-6 col-md-4">
		     			<md-input-container class="md-block input-md">
							<label>Nacimiento</label>
							<md-datepicker ng-model="aux_birth_day"></md-datepicker>
						</md-input-container>
		     			</div>	
		     			<div class="col-xs-12 col-sm-6 col-md-4">
		     			<!-- Selector PAIS -->
						<md-input-container class="md-block input-md">
							<label>Cambiar país</label>
							<md-select placeholder="Cambiar país" ng-model="aux_us_country_id" md-on-open="country_id_onOpen()" ng-model-options="{trackBy: '$value.value'}" required>
								<md-optgroup label="Paises">
									<md-option ng-value="item" ng-repeat="item in lst_ps">
										<img width="40px" ng-src="{{item.flag}}" ><img>
										&nbsp;{{item.displayName}}
									</md-option>
								</md-optgroup>			
							</md-select>
			    		</md-input-container>
			    		<!-- Selector PAIS fin -->
		     			</div>		
		     		</div>
		
		     		<div class="row">
		     			<div class="col-xs-12 text-right">
		     			<md-button class="md-raised md-primary md-button" ng-click="cambiar()">Guardar cambios</md-button>
		     			</div>
		     		</div>
		
		     	</div>
     		</div>

     	</div>
     	<div class="col-xs-12 col-sm-4">

     			<md-input-container style="margin-top: 0px;">
					<input type="text" id="avatar" ng-model="actionForm.us_avatar" aria-label="avatar" style="display: none;" />
					<md-card>
						<md-card-header>
							<md-card-avatar layout="row" layout-align="start center">
								<img class="md-user-avatar" id="imgUsr"/>
								<span style="padding: 5px;">{{actionForm.us_nick}}</span>
							</<md-card-avatar>
						</md-card-header>
							<img id="imgUsrBig"><img>
				        <md-card-content>
				          <p>
				            {{actionForm.us_first_name}}&nbsp;{{actionForm.us_last_name}}
				          </p>
				        </md-card-content>
					</md-card>
				</md-input-container>
				<input type="file" id="browse" name="browse" size="" placeholder="Avatar" checked="checked" class="upload"/>
				<script>
					$(".upload").change(function () {
					    var fileObj = this, file;
					    if (fileObj.files) {
					        file = fileObj.files[0];
					        var fr = new FileReader;
					        fr.onloadend = changeimg;
					        fr.readAsDataURL(file)
					    } else {
					        file = fileObj.value;
					        changeimg(file);
					    }
					});
					function changeimg(str) {
					    if(typeof str === "object") { str = str.target.result; }
					    // Limitar tamaño de la imagen:
					    img2DObj.base64_resize(str,260);	// Lanza el evento"imageResized"		
					    $(document).on("imageResized", function (event) {
					        if (event.url) {
					            str = event.url;
							    $("#imgUsr").prop( "src", str );
							    $("#imgUsrBig").prop( "src", str );
							    $("#avatar").val( str );
							    $("#avatar").trigger('change');
					        }
					    });					    
					}
				</script>

     			</div>		
     </div>	

	<input type="hidden" ng-model="actionForm.us_json" />

</div>
