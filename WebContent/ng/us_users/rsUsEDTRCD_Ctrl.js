
angular
	.module('laAplicacion')
	.directive('dynamic', function ($compile) {
		// Para inyectar en runtime texto con expresiones AngularJS. (Compilar).  
		return {
			restrict: 'A',	// 'A' - matches attribute name, 'E' - matches element name, 'C' - matches class name, 'M' - matches comment. Ejemplo: 'AEC'.
			replace: true,
			link: function (scope, ele, attrs) {
				scope.$watch(attrs.dynamic, function(html) {
					ele.html(html);
					$compile(ele.contents())(scope);	// Compila el contenido del elemento con el atributo 'dynamic'
				});
			}
//			scope: { dynamic: '=dynamic'},
		};
	})
	.controller(
		'rsUsEDTRCD_Ctrl',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				'$mdToast',
				'$filter',
				'validationService',
				'app_services',
				'userRole_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, $filter, validationService, app_services, userRole_service) {
					///////////////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////////////
					$scope.actionForm = {
							/////////////////////////////////////////////////
							// ACTION-FORM
//						    // Para SELRCD:
//						    , retFormulario					: ""
//						    , retElemento					: ""
//						    , valorInicial					: ""
						    // Para todas (mono y multi)
						      opcionPantalla				: ""
						    , opcionJSMenu					: ""
						    , logon_USR						: ""
						    , logon_HSH						: ""
						    , contextVars					: ""	// Objeto "ContextVars.java" en json. Lo gestiona en exclusiva el servidor
							// Para multiregistro:
						    , grid 							: ""	// { bean[50] }
						    , filasGrid						: ""
						    , filaInicioGrid				: ""
						    , filasTotales					: ""
							, clavesMarcadas				: []	// String[]
							, filasMarcadas					: []	// boolean[] - No pertenece al ActionForm real. Solo se mete aqui como soporte del componente visual (checkbox)
						    , us_filtro : {
								  us_sincro: "" // sincro
								, us_mark: "" // mark
								, us_is_deleted: "" // is_deleted
								, us_author: "" // author
								, us_user_id: "" // user_id
								, us_role_id: "" // role_id
								, us_hash_code: "" // hash_code
								, us_country_id: "" // country_id
								, us_PS_name: "" // PS_name
								, us_PS_flag_base64: "" // PS_flag_base64
								, us_nick: "" // nick
								, us_password: "" // password
								, us_first_name: "" // first_name
								, us_last_name: "" // last_name
								, us_phone: "" // phone
								, us_gender: "" // gender
								, us_birth_day: "" // birth_day
								, us_avatar: "" // avatar
								, us_location_id: "" // location_id
								, us_json: "" // json						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, us_sincro: "" // sincro
								, us_mark: "" // mark
								, us_is_deleted: "" // is_deleted
								, us_author: "" // author
								, us_user_id: "" // user_id
								, us_role_id: "" // role_id
								, us_hash_code: "" // hash_code
								, us_country_id: "" // country_id
								, us_PS_name: "" // PS_name
								, us_PS_flag_base64: "" // PS_flag_base64
								, us_nick: "" // nick
								, us_password: "" // password
								, us_first_name: "" // first_name
								, us_last_name: "" // last_name
								, us_phone: "" // phone
								, us_gender: "" // gender
								, us_birth_day: "" // birth_day
								, us_avatar: "" // avatar
								, us_location_id: "" // location_id
								, us_json: "" // json						
							/////////////////////////////////////////////////
					};

					/////
					/////
					// Combos y auxiliares para componentes de presentación:
					$scope.lst_ps = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];

					$scope.aux_birth_day = new Date('1900-01-01');		// Para el objeto DatePicker...para que no se queje del tipo de dato.
					$scope.aux_us_country_id = "";		// España por omisión
					/////
					/////

					///////////////////////////////////////////////////////////////////////
					// Parametros de entrada:
					$scope.actionForm.logon_USR = $stateParams.logon_USR;	// Usuario activo.
					$scope.actionForm.logon_HSH = $stateParams.logon_HSH;	// Clave de operaciones.
					if ( $scope.actionForm.logon_USR == null ) {
						$scope.actionForm.logon_USR = $scope.$parent.$root.varGlobal.logon_USR;
					}
					if ( $scope.actionForm.logon_HSH == null ) {
						$scope.actionForm.logon_HSH = $scope.$parent.$root.varGlobal.logon_HSH;
					}

					///////////////////////////////////////////////////////////////////////
					// Funciones internas:
					function moveModelToView( scope, model ) {
						// Cargar pantalla:
						for (var item in model) {
							scope.actionForm[item] = model[item];
						}

						// Menú especial enviado desde el server:
						scope.adminMenu = scope.actionForm.contextVars.specialMenu;
						
						// Variables globales desde el servidor:
						scope.$parent.$root.varGlobal.contextVars = scope.actionForm.contextVars;

						// Combos y tipos especiales:
						scope.aux_us_country_id = {value: scope.actionForm.us_country_id, displayName: scope.actionForm.us_PS_name};
						scope.aux_birth_day = new Date( scope.actionForm.us_birth_day );
					    $("#imgUsr").prop( "src", scope.actionForm.us_avatar );
					    $("#imgUsrBig").prop( "src", scope.actionForm.us_avatar );
						
						// Control del paginado:
						scope.txtHtmlPaginador = 
							new paginador( model.filasTotales, model.filasGrid, model.filaInicioGrid )
								.getPaginado_innerHTML(); 
					};

					///////////////////////////////////////////////////////////////////////
					// Inicializar pantalla:
					
					// Preparar su PK:
					$scope.actionForm.us_user_id = $scope.actionForm.logon_USR;
				
					userRole_service
						.get($scope.actionForm)
						.then(
							function(response) {
								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
								} else {
									app_services.errorComun(response.data.text);
								}
							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});

				    // Combos y auxiliares para componentes de presentación:
					// app_services.ps_lst( $scope.actionForm.logon_USR, $scope.actionForm.logon_HSH,true).then( function(response) {if (response.rc === 'OK') { $scope.lst_ps = response.text; } else { app_services.showAlert( "ERROR: " + response.text); } },function(response) { console.error("Ha sucedido un error: " + response.statusText); });
					
					if ( (""+window.location).includes( "CHGPWD_MAIL_SENDED=true" ) ) {
						app_services.errorComun( "<h3>Revisa tu correo para obtener una contraseña</h3><br/>" + $scope.actionForm.logon_USR );
					}
					
					///////////////////////////////////////////////////////////////////////
					// Acciones del controlador:
					///////////////////////////////////////////////////////////////////////

					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.us_country_id = $scope.aux_us_country_id.value;

						$scope.actionForm.us_birth_day = $filter('date')($scope.aux_birth_day, 'yyyy-MM-dd');

						//llamo a su servicio pasandole el actionForm de entidad
						userRole_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$mdToast.show($mdToast.simple().textContent("Registro cambiado").position('top right').hideDelay(500));

											var modelo = response.data.text;
											moveModelToView( $scope, modelo );
											
											$scope.$parent.$root.varGlobal.logon_USR = $scope.actionForm.logon_USR;
											$scope.$parent.$root.varGlobal.logon_HSH = $scope.actionForm.logon_HSH;
											
//											$('.modal-backdrop').remove();
//											$state.reload();
											
//											$state.go( "RsDSPFIL", {logon_USR:$scope.actionForm.us_user_id, logon_HSH:$scope.actionForm.logon_HSH}, {reload: true} );	
										} else {
											app_services.errorComun(response.data.text);
										}
	
									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});

//						}
					};
/////////////
// Eventos de componentes:
					$scope.country_id_onOpen = function() {
						if ( $scope.lst_ps === null ) {
							app_services.ps_lst($scope.actionForm.logon_USR,$scope.actionForm.logon_HSH,true).then( function(response) {if (response.rc === 'OK') { $scope.lst_ps = response.text;} else { alert( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
						}
					}
/////////////

				} ]);
