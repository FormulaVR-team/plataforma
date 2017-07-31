<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-32x32.png" sizes="32x32" />
		<link rel="icon" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-192x192.png" sizes="192x192" />
		<link rel="apple-touch-icon-precomposed" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-180x180.png" />
		<meta name="msapplication-TileImage" content="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-270x270.png" />

		<title>Logon</title>

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
			body {			
			background: url('./resBS/img/formula-vr-head-wh.jpg') no-repeat center center fixed; 
			-webkit-background-size: cover;
			-moz-background-size: cover;
			-o-background-size: cover;
			background-size: cover;
						
			background-color: #F1F1F1;
			}	
			/* LOGON */

			.logon-main {
				position: relative;
				max-width: 480px;
				margin: 0 auto;
			}
			.logon-content{
				min-width:320px;
				width: 400px;
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
							<%-- <img ng-src="{{imagePath}}" class="md-card-image" alt="Washed Out"> --%>

							<md-card-title>
								<md-card-title-text>
									<picture style="text-align: center;">
										<img class="brand-img" src="./resBS/img/formulavr.png" width="250" alt="Formula VR"/>
									</picture> 
									<h4>Introduce tu CORREO ELECTRÓNICO</h4>
									<small style="min-height:20px;">{{ (logon_USR_1d2 + "@" + logon_USR_2d2)==="@"? "" : (logon_USR_1d2 + "@" + logon_USR_2d2) }}</small>
								</md-card-title-text>
							</md-card-title>

							<md-card-content layout="column">
								<%-- <md-input-container>
									<label>Correo electrónico</label>
									<input type="text" ng-model="logon_USR" required ng-pattern="/^.+@.+\..+$/" id="logon_USR" name="logon_USR" style="text-transform: lowercase;" ng-keyup="detect_pressEnter($event,'USR');" />
								</md-input-container> --%>
								<div layout="row">
									<md-input-container>
										<label>Usuario</label>
										<input type="text" auto-focus id="logon_USR_1d2" ng-model="logon_USR_1d2" style="text-transform: lowercase;" ng-keyup="detect_pressEnter($event,'goto_logon_USR_2d2');"/>
									</md-input-container>
									<h4>@</h4>
									<md-input-container>
										<label><small>...hotmail.es, gmail.com...</small></label>
										<input type="text" id="logon_USR_2d2" ng-model="logon_USR_2d2" style="text-transform: lowercase;" ng-keyup="detect_pressEnter($event,'USR');"/>
									</md-input-container>
								</div>						
								<md-input-container>
									<input type="hidden" ng-model="logon_USR" id="logon_USR" name="logon_USR" /></br>
								</md-input-container>
							</md-card-content>

							<md-card-actions layout="column">
								<md-button class="md-raised md-primary" ng-click="reservaExpress()">
									INICIAR
									<!-- <md-tooltip>Reserva sin registrarte</md-tooltip> -->
								</md-button>
								<!-- <md-button class="md-primary" ng-click="showRegister = ! showRegister">Ya tengo cuenta</md-button> -->
							</md-card-actions>

							<md-card-content layout="column"><small>Recuerda que esta cuenta de correo la usaremos para todas las comunicaciones contigo.</br>Te mandaremos las <span style="color:blue;">confirmaciones de pago y reservas</span>.</br>Por favor, asegúrate de escribirla correctamente</small></md-card-content>

						</md-card>
								
					</section>

					<section ng-show="showRegister">

						<md-card>
							<%-- <img ng-src="{{imagePath}}" class="md-card-image" alt="Washed Out"> --%>

							<md-card-title>
								<md-card-title-text>
								<picture style="text-align: center;">
									<img class="brand-img" src="./resBS/img/formulavr.png" width="250" alt="Formula VR"/>
								</picture> 
								<!-- <h4>Acceder a Formula VR</h4> -->
								</md-card-title-text>
							</md-card-title>

							<md-card-content layout="column">
								<md-input-container>
										<label>Correo electrónico</label>
										<input type="text" ng-model="logon_USR" required ng-pattern="/^.+@.+\..+$/" id="logon_USR" name="logon_USR" style="text-transform: lowercase;"/>
								</md-input-container>
								<div layout="row" layout-align="space-between start">
									<md-input-container>
										<label>Contraseña</label>
										<input type="password" auto-focus ng-model="logon_PWD" id="logon_PWD" name="logon_PWD" ng-keyup="detect_pressEnter($event,'PASS');"/>
									</md-input-container>
									<logic:present name="noPwd-button-ON">
										<md-button class="md-raised md-warn" ng-click="forgotPass()" style="font-size: 12px; text-transform: uppercase;">Pedir contraseña</md-button>
									</logic:present>
								</div>
							</md-card-content>

							<md-card-content layout="column">
							<!-- <md-card-actions layout="column"> -->
								<md-button class="md-raised md-primary" ng-click="submitForm()">
									Acceder con contraseña
									<md-tooltip>Entrar al sistema</md-tooltip>
								</md-button>
								<div style="padding-top: 10px;padding-bottom: 10px;">
									<div style="display: inline-block;">
										<md-button class="md-raised" ng-click="forgotPass()" style="font-size: 12px;">Olvidé la contraseña</md-button>
									</div>
<%-- 								<div style="display: inline-block; float: right;">
										<md-button class="md-primary" ng-click="newUser()" style="font-size: 12px;">Crear nuevo usuario</md-button>
									</div>									
--%>
								</div>
 							<!-- </md-card-actions> -->
							</md-card-content>

						</md-card>

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
 			.controller('AppCtrl', function($scope, $timeout, $mdSidenav, $mdToast) {
				$scope.logon_USR = '${logon_USR}';

				$scope.logon_USR_1d2 = $scope.logon_USR.split("@")[0];
				$scope.logon_USR_2d2 = $scope.logon_USR.split("@")[1];

				var errmsg = document.getElementById('errmsg').value;
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
					$scope.logon_USR = ($scope.logon_USR_1d2!=undefined?$scope.logon_USR_1d2:"") + "@" + ($scope.logon_USR_2d2!=undefined?$scope.logon_USR_2d2:"");
					document.getElementsByName('logon_USR')[0].value=$scope.logon_USR;
					document.getElementsByName('logon_USR')[1].value=$scope.logon_USR;
					document.getElementsByName('opcionPantalla')[0].value='reservaExpress'; 
					document.forms[0].submit(); 
				}
				$scope.forgotPass = function () { 
					 if ( confirm('ENVIAREMOS UN CORREO A TU CUENTA.\n\nPor favor sigue las instrucciones que contiene.\n') ) {
						 document.getElementsByName('opcionPantalla')[0].value='forgotPass'; 
						 document.forms[0].submit();
					} 				
				}
				$scope.newUser = function () { 
					document.getElementsByName('opcionPantalla')[0].value='newUser'; document.forms[0].submit();
				}
				$scope.submitForm = function () { 
					$scope.logon_USR = ($scope.logon_USR_1d2!=undefined?$scope.logon_USR_1d2:"") + "@" + ($scope.logon_USR_2d2!=undefined?$scope.logon_USR_2d2:"");
					document.getElementsByName('logon_USR')[0].value=$scope.logon_USR;
					document.getElementsByName('logon_USR')[1].value=$scope.logon_USR;
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
						else if ( id === 'goto_logon_USR_2d2') {
							 $timeout(function() {
								    $('#logon_USR_2d2').trigger('focus');
							}, 0);
						}
					}
				}
				////////////////////

			}
			);

	</script>

	</body>
</html>
