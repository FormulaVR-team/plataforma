<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-32x32.png" sizes="32x32" />
		<link rel="icon" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-192x192.png" sizes="192x192" />
		<link rel="apple-touch-icon-precomposed" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-180x180.png" />
		<meta name="msapplication-TileImage" content="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-270x270.png" />

		<title>Formula VR | Logon</title>

		<!-- ==================================== -->
			<script src="./ng/_lib/jquery-1.11.3/jquery.min.js?fvrVer=${miVersion}"></script>
		<!-- Angular --> 
			<script src="./ng/_lib/ng/angular.min.js?fvrVer=${miVersion}"></script>
			<script src="./ng/_lib/ng/angular-ui-router.min.js?fvrVer=${miVersion}"></script>
			<script src="./ng/_lib/ng/angular-animate.min.js?fvrVer=${miVersion}"></script>
			<script src="./ng/_lib/ng/angular-aria.min.js?fvrVer=${miVersion}"></script>
			<script src="./ng/_lib/ng/angular-messages.min.js?fvrVer=${miVersion}"></script>
			<!-- Angular Material Library -->
			<script src="./ng/_lib/ng/angular-material.min.js?fvrVer=${miVersion}"></script>
			<!-- Angular Material style sheet -->
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="./ng/_lib/ng/css/angular-material.min.css?fvrVer=${miVersion}">
			<link rel="stylesheet" href="./ng/_lib/ng/css/angular-material.CUSTOM-FVR.css?fvrVer=${miVersion}">
			<link rel="stylesheet" href="./ng/_lib/ng/css/icon.css?family=Material+Icons">
		<!-- ==================================== -->
		
		<script type="text/javascript" src="./script/md5Obj.js?fvrVer=${miVersion}"></script>
		<style type="text/css">
			body, html {			
						
			background-color: #F0F0F0;
			}	
			/* LOGON */

			.logon-main {
				position: relative;
				max-width: 480px;
				margin: 0 auto;
			}
			.logon-content{
				min-width:320px;
				width: 450px;
				background-color: transparent;
			}
			@media (max-width: 767px) {

				.logon-content{
					min-width:320px;
					width: 320px;
					background-color: transparent;
				}
			}
	</style>

	<script type="text/javascript">
		window.smartlook||(function(d) {
		var o=smartlook=function(){ o.api.push(arguments)},h=d.getElementsByTagName('head')[0];
		var c=d.createElement('script');o.api=new Array();c.async=true;c.type='text/javascript';
		c.charset='utf-8';c.src='https://rec.smartlook.com/recorder.js';h.appendChild(c);
		})(document);
		smartlook('init', '887d25f46d8f8b78b6eaf272a8303a326331a1bb');
	</script>

	</head>
	<body layout="column" flex ng-controller="AppCtrl" ng-app="theNgApp" ng-cloak="" style="">
		<%-- <bean:message key="menu.client.title"/> --%>
		<main flex layout="row" layout-align="center center" class="logon-main" style="">
			<md-content class="logon-content" style="">

				<form name="Logon_AF" action="./Logon_A.do" method="post" ng-submit="Logon_AF.$valid && submitForm(this)" >

					<input type="hidden" name="opcionPantalla"/>
					<input type="hidden" id="errmsg" value="<html:errors property="error"/>"/>

					<section ng-hide="showRegister" style="background-color: transparent;">

						<md-card>
							<md-card-title>
							<div ui-preloader="" id="preloaderbar1" class="preloaderbar hide"><span class="bar"></span></div>
								<md-card-title-text>
									<picture style="margin-bottom: 5px;">
										<img class="brand-img" src="./resBS/img/formulavr.png" width="250" alt="Formula VR"/>
									</picture>
								</md-card-title-text>
							</md-card-title>

							<md-card-content>
								<h4>Iniciar Sesión</h4>
								<h6 style="margin-top: 0px;">Utiliza tu cuenta de correo electrónico</h6>
								<p>&nbsp;</p>
									<md-input-container class="md-block" >
										<label>Correo electrónico</label>
										<input type="text" auto-focus 
											ng-model="logon_USR" 
											id="logon_USR" 
											name="logon_USR" 
											style="text-transform: lowercase;" 
											onclick="this.select();" 
											ng-keyup="detect_pressEnter($event,'USR');"
											pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,20}$"/>		
											<div ng-messages="Logon_AF.logon_USR.$error" role="alert" md-auto-hide="true" ng-if="Logon_AF.logon_USR.$touched">
							          <div ng-message-exp="['required', 'pattern']">
							            El email es incorrecto.
							          </div>
							        </div>
									</md-input-container>	
							</md-card-content>

							<md-card-actions>
								<md-button class="md-raised md-primary" ng-click="reservaExpress()" style="float: right;">
									SIGUIENTE
								</md-button>
							</md-card-actions>
						</md-card>
						<md-button class="" ng-href="https://www.formulavr.net" target="_self">
						Ir a la web
						</md-button>
						<md-button class="md-primary" ng-href="https://www.formulavr.net" target="_blank">
						Condiciones legales
						</md-button>		
					</section>

					<section ng-show="showRegister">

						<md-card>
							<md-card-title>
							<div ui-preloader="" id="preloaderbar2" class="preloaderbar hide"><span class="bar"></span></div>
								<md-card-title-text>
								<picture  style="margin-bottom: 5px;">
									<img class="brand-img" src="./resBS/img/formulavr.png" width="250" alt="Formula VR"/>
								</picture> 
								</md-card-title-text>
							</md-card-title>

							<md-card-content>
								<h4>Iniciar Sesión</h4>
								<p>
								<img id="imgUSR" style="width: 40px;border-radius: 50px;vertical-align: middle;margin-right: 10px;"/>
								{{ logon_USR  }}
								<md-icon ng-click="swapScreen()" style="cursor: pointer;">keyboard_arrow_down</md-icon>
								<span id="volver" class="mythemes-icon-down-open" style="float: right; cursor: pointer;margin-top: 10px;"/></p><br />
								<p>&nbsp;</p>
								<md-input-container class="md-block">
									<label>Contraseña</label>
									<input type="password" auto-focus ng-model="logon_PWD" id="logon_PWD" name="logon_PWD" onclick="this.select();" ng-keyup="detect_pressEnter($event,'PASS');"/>
								</md-input-container>
							</md-card-content>

							<md-card-actions>
								<md-button class="md-raised md-primary" ng-click="submitForm()" style="float: right;">
									Siguiente
								</md-button>
								<md-button class="md-primary btn-lnk" ng-click="forgotPass()" style="float: left;font-size: 12px;">
								¿Has olvidado tu contraseña?
								</md-button>
							</md-card-actions>

						</md-card>
						<md-button class="" ng-href="https://www.formulavr.net" target="_self">
						Ir a la web
						</md-button>
						<md-button class="md-primary" ng-href="https://www.formulavr.net" target="_blank">
						Condiciones legales
						</md-button>	

						<inpunt class="hide" type="submit" />

					</section>

				</form>

			</md-content> 

	</main>

	<script>

		angular.module('theNgApp',['ngMaterial', 'ngMessages'])
			.config(
				function($mdThemingProvider) {
					<%@include file="/script/mdThemingProvider.js"%>
				}
			)
 			.directive('autoFocus', function($timeout) {
			    return {
			        restrict: 'A',
			        link: function(_scope, _element) {
			            $timeout(function(){
			                _element[0].focus();
			            }, 500);
			        }
			    };
			})
 			.controller('AppCtrl', function($scope, $timeout, $mdSidenav, $mdToast, $http) {
				$scope.logon_USR = ('${logon_USR}' !== 'undefined') ? '${logon_USR}' : "";

				var errmsg = document.getElementById('errmsg').value.replace('\'undefined\'','');;
				if ( "" !== errmsg ) { 
					var myToast = $mdToast
						.simple()
						.textContent( errmsg )
						.capsule(true)
						.position( 'bottom left' )
						.hideDelay( 10000 )
						;
					$mdToast.show(myToast);
				}

				<logic:present name="panel-pwd-ON">
					$scope.showRegister = ! $scope.showRegister;
				</logic:present>
				<logic:notPresent name="panel-pwd-ON">
					if ( location.search.includes("panel-pwd-ON") ) {
						$scope.showRegister = ! $scope.showRegister;
					}
				</logic:notPresent>

				////////////////////
				$scope.reservaExpress = function () {
					document.getElementById('preloaderbar1').className = "preloaderbar active";
					document.getElementsByName('logon_USR')[0].value=$scope.logon_USR;
					document.getElementsByName('opcionPantalla')[0].value='reservaExpress'; 
					document.forms[0].submit(); 
				}
				$scope.forgotPass = function () {
					document.getElementById('preloaderbar2').className = "preloaderbar active";
					// if ( confirm('ENVIAREMOS UN CORREO A TU CUENTA.\n\nPor favor sigue las instrucciones que contiene.\n') ) {
						document.getElementsByName('logon_USR')[0].value=$scope.logon_USR;
						document.getElementsByName('opcionPantalla')[0].value='forgotPass'; 
						document.forms[0].submit();
					// } 				

				}
				$scope.newUser = function () {
					document.getElementById('preloaderbar1').className = "preloaderbar active";
					document.getElementsByName('logon_USR')[0].value=$scope.logon_USR;
					document.getElementsByName('opcionPantalla')[0].value='newUser'; document.forms[0].submit();

				}
				$scope.submitForm = function () {
					document.getElementById('preloaderbar2').className = "preloaderbar active";
					document.getElementsByName('logon_USR')[0].value=$scope.logon_USR;
					document.getElementsByName('opcionPantalla')[0].value='ENTER';
					document.getElementsByName('logon_PWD')[0].value=md5Obj.md5(document.getElementsByName('logon_PWD')[0].value);
					document.forms[0].submit();

				}
				$scope.detect_pressEnter = function (e,id) {
					e.preventDefault();
					e.stopPropagation();
					(e.keyCode)?k=e.keyCode:k=e.which;
					if(k==13) {
						if ( id === "USR" )	$scope.reservaExpress();
						else if ( id === 'PASS') $scope.submitForm();
						
					}
				}
				$scope.swapScreen = function () {
					$scope.logon_USR = "";
					$scope.showRegister = ! $scope.showRegister;
				}
				////////////////////
								/////////////////////////////
				$scope.getUsrImg = function(){
					var accion = "getUsrImg";
					$http( {
									url: "./FvrServlet?ACC=" + accion + "&USR=" + $scope.USR,
									method: 'POST',
									headers : {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}
								}
					)
						.success(function(response){
							if (response.rc === 'OK') {
								document.getElementById('imgUSR').setAttribute("src", response.text);
							}
						}
					)
						.error(function(err){
								alert(err);
							}
						)
					;
				}
				$scope.getUsrImg();
				// src="./FvrServlet?ACC=getUsrImg&USR={{logon_USR}}" 
			}
			);

	</script>

	</body>
</html>
