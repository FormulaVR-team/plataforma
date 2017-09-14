
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
		'UsDSPFIL_Ctrl_basic',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				'$mdToast',
				'validationService',
				'app_services',
				'UsDSPFIL_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, validationService, app_services, UsDSPFIL_service) {
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
					
				    $scope.rowsPerPage = [
					                     {value: 10, displayName: '10 filas'},
					                     {value: 15, displayName: '15 filas'},
					                     {value: 30, displayName: '30 filas'},
					                     {value: 50, displayName: '50 filas'}
					                  ];
				    $scope.actionForm.filasGrid = angular.fromJson( $window.sessionStorage.getItem("filasGrid") );
				    $scope.actionForm.filasGrid = $scope.actionForm.filasGrid==null?10:$scope.actionForm.filasGrid;
					
				    // Especial para que Material Design lo use en las "md-select"
				    $scope.aux_filasGrid = {value: 0};
				    $scope.aux_filasGrid.value = $scope.actionForm.filasGrid;

					/////
					/////
					// Combos y auxiliares para componentes de presentación:
					$scope.lst_ps = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];

					$scope.aux_us_country_id = "";
					/////
					/////

				    $scope.exportLink = "<span></span>";	// Recoge el link al fichero para exportar.
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
					// Aplicar su filtro persistente:
					$scope.actionForm.us_filtro = angular.fromJson( $window.sessionStorage.getItem("UsDSPFIL.us_filtro") );
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
						scope.aux_us_country_id.value = scope.actionForm.us_country_id;
						
						// Control del paginado:
						scope.txtHtmlPaginador = 
							new paginador( model.filasTotales, model.filasGrid, model.filaInicioGrid )
								.getPaginado_innerHTML(); 
					};
					///////////////////////////////////////////////////////////////////////
					// Inicializar pantalla:
					UsDSPFIL_service
						.reload($scope.actionForm)
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
					// app_services.ps_lst($scope.actionForm.logon_USR,$scope.actionForm.logon_HSH,true).then( function(response) {if (response.rc === 'OK') { $scope.lst_ps = response.text;} else { alert( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
					///////////////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////////////
					// Acciones del controlador:
					///////////////////////////////////////////////////////////////////////

					$scope.sidenavToggle = function()  { app_services.sidenavToggle(); }	// Acciona menu lateral
					$scope.toTrustedHTML = function( html_text ) { return app_services.toTrustedHTML( html_text ); };

					// $scope.fld_onfocus = function(e,id) { e.target.parentElement.classList.add("focused"); }
					// $scope.fld_onblur = function(e) { if ( e.target.value.length == 0 ) e.target.parentElement.classList.remove("focused"); }
					$scope.setClaveMarcada = function ( key, nFila ) {
						// Las claves marcadas se agregan a un array y se apoyan en un array de booleanos como componentes visuales. 
						var idx = $scope.actionForm.clavesMarcadas.indexOf( key );	// ¿Estaba?
						if ( idx != -1 ) { $scope.actionForm.clavesMarcadas.splice(idx, 1); } // Si ya estaba, quitarlo
						if ( $scope.actionForm.filasMarcadas[nFila] ) {
							if ( idx == -1 ) { $scope.actionForm.clavesMarcadas.push( key ); }	// Si checkbox activado, se agrega
						}
					};
					
					$scope.filtrar = function() {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
					    $scope.actionForm.filasGrid = $scope.aux_filasGrid.value;
						UsDSPFIL_service
						.filtrar($scope.actionForm)
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
					};

					$scope.exportar = function() {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
					    $scope.actionForm.filasGrid = $scope.aux_filasGrid.value;
						UsDSPFIL_service
						.exportar($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
								} else {
									$scope.exportLink = response.data.text;
								}

							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});
					};

					$scope.avPg = function() {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
						UsDSPFIL_service
						.avPg($scope.actionForm)
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
					};

					$scope.rtPg = function() {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
						UsDSPFIL_service
						.rtPg($scope.actionForm)
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
					};

					$scope.goRow = function( nFila ) {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
						$scope.actionForm.filaInicioGrid = nFila;
						UsDSPFIL_service
						.reload($scope.actionForm)
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
					};
					
					$scope.goPage = function() {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
						var p = prompt( "Introduzca número de página","1" );
						p = ( isNaN( p ) )?parseInt( "1" ):parseInt( p );		
						var fila = (1+((p-1) * $scope.actionForm.filasGrid ));
						$scope.goRow( fila );
					};

					$scope.gotoEDTRCD = function( key ) {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
						$state.go( "estado2", {logon_USR:$scope.actionForm.logon_USR, pk:key} );	
					};

					$scope.putRecordAsTheCurrent = function( reg ) {
						// Formato de registro:
						$scope.actionForm.us_sincro = reg.us_sincro; // sincro
						$scope.actionForm.us_mark = reg.us_mark; // mark
						$scope.actionForm.us_is_deleted = reg.us_is_deleted; // is_deleted
						$scope.actionForm.us_author = reg.us_author; // author
						$scope.actionForm.us_user_id = reg.us_user_id; // user_id
						$scope.actionForm.us_role_id = reg.us_role_id; // role_id
						$scope.actionForm.us_hash_code = reg.us_hash_code; // hash_code
						$scope.actionForm.us_country_id = reg.us_country_id; // country_id
						$scope.actionForm.us_PS_name = reg.us_PS_name; // PS_name
						$scope.actionForm.us_PS_flag_base64 = reg.us_PS_flag_base64; // PS_flag_base64
						$scope.actionForm.us_nick = reg.us_nick; // nick
						$scope.actionForm.us_password = reg.us_password; // password
						$scope.actionForm.us_first_name = reg.us_first_name; // first_name
						$scope.actionForm.us_last_name = reg.us_last_name; // last_name
						$scope.actionForm.us_phone = reg.us_phone; // phone
						$scope.actionForm.us_gender = reg.us_gender; // gender
						$scope.actionForm.us_birth_day = reg.us_birth_day; // birth_day
						$scope.actionForm.us_avatar = reg.us_avatar; // avatar
						$scope.actionForm.us_location_id = reg.us_location_id; // location_id
						$scope.actionForm.us_json = reg.us_json; // json						

						// Combos y auxiliares para componentes de presentación:
						$scope.aux_us_country_id = {value: $scope.actionForm.us_country_id, 	displayName: ""};
					};

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
					
					$scope.conectarComo = function( reg ) {
						if ( confirmar('¿Estas seguro de conectarte con el usuario ' + reg.us_user_id + '?',this)==true ) {
							$scope.putRecordAsTheCurrent(reg);
							//llamo a su servicio pasandole el actionForm de entidad
							UsDSPFIL_service
									.conectarComo($scope.actionForm)
									.then(
										function(response) {
											if (response.data.rc === 'OK') {
												// window.open(response.data.text.us_hash_code); 
												window.location = response.data.text.us_hash_code;
											} else {
												app_services.errorComun(response.data.text);
											}
										},
										function(response) {
											console.error("Ha sucedido un error: " + response.statusText);
										});
						}
					};

	/////////////
	/////////////
	// Panel empotrado de "ADDRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.agregar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.us_country_id = $scope.aux_us_country_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						UsDSPFIL_service
								.add($scope.actionForm)
								.then(
									function(response) {
	
										if (response.data.rc === 'OK') {
											$mdToast.showSimple( "Registro agregado" );
											$('.modal-backdrop').remove();
											$state.reload();
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
	// Panel empotrado de "EDTRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.copiar = function() {
						$scope.initKey();
						$('#usDSPFIL_EDTRCD_modal').modal('hide');
						$('#usDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.us_country_id = $scope.aux_us_country_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						UsDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#usDSPFIL_EDTRCD_modal').modal('hide');
											$mdToast.showSimple( "Registro cambiado" );
											// $('.modal-backdrop').remove();
											// $state.reload();
											$scope.goRow( $scope.actionForm.filaInicioGrid );
										} else {
											app_services.errorComun(response.data.text);
										}
	
									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});

//						}
					};
					$scope.suprimir = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

						if ( ! confirmar('Suprimir el registro, ¿está seguro?',this) ) { return; }

						//llamo a su servicio pasandole el actionForm de entidad
						UsDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#usDSPFIL_EDTRCD_modal').modal('hide');
											$mdToast.showSimple( "Registro suprimido" );
											// $('.modal-backdrop').remove();
											// $state.reload();
											$scope.goRow( $scope.actionForm.filaInicioGrid );
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
	/////////////
	// Eventos de componentes:
					$scope.country_id_onOpen = function() {
						if ( $scope.lst_ps === null ) {
							app_services.ps_lst($scope.actionForm.logon_USR,$scope.actionForm.logon_HSH,true).then( function(response) {if (response.rc === 'OK') { $scope.lst_ps = response.text;} else { alert( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
						}
					}
	/////////////

				} ]);
