<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="UsADDRCD_form" style="position: fixed;
top: 0px;
z-index: 1001;
background-color: #F1F1F1;
width: 100%;
height: 100vh;
padding-top: 50px;
">
 	<main flex layout="row" layout-align="center center" style="position: relative;max-width: 480px;margin: 0 auto;">
			<md-content style="min-width:100%; width: 400px;background-color: transparent;">

					<input type="hidden" ng-model="actionForm.us_json" />

					<section ng-hide="showRegister" style="background-color: transparent;">
			      <md-card>
			        <%-- <img ng-src="{{imagePath}}" class="md-card-image" alt="Washed Out"> --%>
			        <md-card-title>
			          <md-card-title-text>
			           <picture style="text-align: center;">
						  <img class="brand-img" src="./resBS/img/formulavr.png" width="300" alt="Formula VR"/>
						</picture> 
			            <h4>Cambiar contraseña</h4>
			          </md-card-title-text>
			        </md-card-title>
			        <md-card-content layout="column">
			        <p>Usuario: <b>{{varGlobal.logon_USR}}</b></p> 
			        <input type="hidden" ng-model="actionForm.us_user_id" required readonly="readonly"/>
			          <md-input-container>
									<label>contraseña</label>									
					<input type="password" ng-model="actionForm.us_password" required />
								</md-input-container>
			          <md-input-container>
									<label>Repite la contraseña</label>
					<input type="password" ng-model="actionForm.us_first_name" required />
								</md-input-container>
			        </md-card-content>
			        <md-card-actions layout="column">
			          <md-button class="md-raised md-primary" ng-click="chgpwd()">
			          	Confirmar
								</md-button>
				     </md-card-actions>
			      </md-card>
			      
			    </section>
			</md-content>
		</main>
		</div>

