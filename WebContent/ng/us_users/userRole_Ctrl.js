
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
		'userRole_Ctrl',
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
				'md5',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, $filter, validationService, app_services, userRole_service, md5) {
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
					$scope.aux_birth_day = new Date('1900-01-01');		// Para el objeto DatePicker...para que no se queje del tipo de dato.
					$scope.aux_us_country_id = {value: 724, displayName: "España"};		// España por omisión
					/////
					/////

					///////////////////////////////////////////////////////////////////////
					// Parametros de entrada:
					$scope.actionForm.us_user_id = $stateParams.user_id;
					$scope.actionForm.us_json =  JSON.stringify( {"crt_token_id":$stateParams.token_id} );
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

						// Control del paginado:
						scope.txtHtmlPaginador = 
							new paginador( model.filasTotales, model.filasGrid, model.filaInicioGrid )
								.getPaginado_innerHTML(); 
					};
					///////////////////////////////////////////////////////////////////////
					// Inicializar pantalla:

				    // Combos y auxiliares para componentes de presentación:

					///////////////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////////////
					// Acciones del controlador:
					///////////////////////////////////////////////////////////////////////

					$scope.toTrustedHTML = function( html_text ) { return app_services.toTrustedHTML( html_text ); };

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.us_user_id = ""; // user_id						
					};

					$scope.initReg = function() {
						// Formato de registro:
						$scope.actionForm.us_sincro = ""; // sincro
						$scope.actionForm.us_mark = ""; // mark
						$scope.actionForm.us_is_deleted = ""; // is_deleted
						$scope.actionForm.us_author = ""; // author
						$scope.actionForm.us_user_id = ""; // user_id
						$scope.actionForm.us_role_id = ""; // role_id
						$scope.actionForm.us_hash_code = ""; // hash_code
						$scope.actionForm.us_country_id = ""; // country_id
						$scope.actionForm.us_PS_name = ""; // PS_name
						$scope.actionForm.us_PS_flag_base64 = ""; // PS_flag_base64
						$scope.actionForm.us_nick = ""; // nick
						$scope.actionForm.us_password = ""; // password
						$scope.actionForm.us_first_name = ""; // first_name
						$scope.actionForm.us_last_name = ""; // last_name
						$scope.actionForm.us_phone = ""; // phone
						$scope.actionForm.us_gender = ""; // gender
						$scope.actionForm.us_birth_day = ""; // birth_day
						$scope.actionForm.us_avatar = ""; // avatar
						$scope.actionForm.us_location_id = ""; // location_id
						$scope.actionForm.us_json = ""; // json						

						// Combos y auxiliares para componentes de presentación:
						$scope.aux_us_country_id	= {value: 724, displayName: "España"};
					};

					$scope.agregar = function() {

//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.us_country_id = $scope.aux_us_country_id.value;

						$scope.actionForm.us_birth_day = $filter('date')($scope.aux_birth_day, 'yyyy-MM-dd');

						$scope.actionForm.us_password = md5.createHash($scope.actionForm.us_password || '');

						//llamo a su servicio pasandole el actionForm de entidad
						userRole_service
								.add($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$mdToast.showSimple( "Registro agregado" );

											var modelo = response.data.text;
											moveModelToView( $scope, modelo );
											
											$scope.$parent.$root.varGlobal.logon_USR = $scope.actionForm.logon_USR;
											$scope.$parent.$root.varGlobal.logon_HSH = $scope.actionForm.logon_HSH;
											
//											$('.modal-backdrop').remove();
//											$state.reload();
											
											$state.go( "RsDSPFIL", {logon_USR:$scope.actionForm.us_user_id, logon_HSH:$scope.actionForm.logon_HSH} );	
										} else {
//											app_services.errorComun(response.data.text);
											$mdToast.showSimple( response.data.text );

											$scope.actionForm.us_password = "";
										}

									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});

//						}
					};

					$scope.chgpwd = function() {

//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

						$scope.actionForm.us_password = md5.createHash($scope.actionForm.us_password || '');
						$scope.actionForm.us_first_name = md5.createHash($scope.actionForm.us_first_name || '');

						//llamo a su servicio pasandole el actionForm de entidad
						userRole_service
								.chgpwd($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$mdToast.showSimple( "Contraseña asignada" );

											var modelo = response.data.text;
											moveModelToView( $scope, modelo );
											
											$scope.$parent.$root.varGlobal.logon_USR = $scope.actionForm.logon_USR;
											$scope.$parent.$root.varGlobal.logon_HSH = $scope.actionForm.logon_HSH;
											
//											$('.modal-backdrop').remove();
//											$state.reload();
//											$state.go( "RsDSPFIL", {logon_USR:$scope.actionForm.us_user_id, logon_HSH:$scope.actionForm.logon_HSH} );
											window.location="Logon.jsp?panel-pwd-ON"	// Reconectarse al sistema!
											
										} else {
//											app_services.errorComun(response.data.text);
											$mdToast.showSimple( response.data.text );

											$scope.actionForm.us_password = "";
											$scope.actionForm.us_first_name = "";
										}

									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});

//						}
					};

				} ]);
