
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
		'TtDSPFIL_Ctrl_basic',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				'$mdToast',
				'validationService',
				'app_services',
				'TtDSPFIL_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, validationService, app_services, TtDSPFIL_service) {
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
						    , tt_filtro : {
								  tt_sincro: "" // sincro
								, tt_mark: "" // mark
								, tt_is_deleted: "" // is_deleted
								, tt_author: "" // author
								, tt_location_id: "" // location_id
								, tt_day_type: "" // day_type
								, tt_start_time: "" // start_time
								, tt_duration_minutes: "" // duration_minutes
								, tt_isBlocked: "" // isBlocked
								, tt_json: "" // json						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, tt_sincro: "" // sincro
								, tt_mark: "" // mark
								, tt_is_deleted: "" // is_deleted
								, tt_author: "" // author
								, tt_location_id: "" // location_id
								, tt_day_type: "" // day_type
								, tt_start_time: "" // start_time
								, tt_duration_minutes: "" // duration_minutes
								, tt_isBlocked: "" // isBlocked
								, tt_json: "" // json						
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
				    $scope.lst_lo = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];
				    $scope.aux_tt_location_id = "";
				    $scope.aux_FLT_tt_location_id = "";
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
					$scope.actionForm.tt_filtro = angular.fromJson( $window.sessionStorage.getItem("TtDSPFIL.tt_filtro") );
					if ( null != $scope.actionForm.tt_filtro ) {
					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_FLT_tt_location_id = {value: $scope.actionForm.tt_filtro.tt_location_id, displayName: ""};
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
						
						// Control del paginado:
						scope.txtHtmlPaginador = 
							new paginador( model.filasTotales, model.filasGrid, model.filaInicioGrid )
								.getPaginado_innerHTML(); 
					};
					///////////////////////////////////////////////////////////////////////
					// Inicializar pantalla:
					TtDSPFIL_service
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
					app_services.lo_lst().then( function(response) {if (response.rc === 'OK') { $scope.lst_lo = response.text;} else { alert( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
					
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

					    // Combos y auxiliares para componentes de presentación:
					    $scope.actionForm.tt_filtro.tt_location_id = $scope.aux_FLT_tt_location_id.value;

						TtDSPFIL_service
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
						TtDSPFIL_service
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
						TtDSPFIL_service
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
						TtDSPFIL_service
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
						TtDSPFIL_service
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
						$scope.actionForm.tt_sincro = reg.tt_sincro; // sincro
						$scope.actionForm.tt_mark = reg.tt_mark; // mark
						$scope.actionForm.tt_is_deleted = reg.tt_is_deleted; // is_deleted
						$scope.actionForm.tt_author = reg.tt_author; // author
						$scope.actionForm.tt_location_id = reg.tt_location_id; // location_id
						$scope.actionForm.tt_day_type = reg.tt_day_type; // day_type
						$scope.actionForm.tt_start_time = reg.tt_start_time; // start_time
						$scope.actionForm.tt_duration_minutes = reg.tt_duration_minutes; // duration_minutes
						$scope.actionForm.tt_isBlocked = reg.tt_isBlocked; // isBlocked
						$scope.actionForm.tt_json = reg.tt_json; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_tt_location_id = {value: $scope.actionForm.tt_location_id, 	displayName: ""};
					};

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.tt_location_id = ""; // location_id
						$scope.actionForm.tt_day_type = ""; // day_type
						$scope.actionForm.tt_start_time = ""; // start_time						
					};

					$scope.initReg = function() {
						// Formato de registro:
						$scope.actionForm.tt_sincro = ""; // sincro
						$scope.actionForm.tt_mark = ""; // mark
						$scope.actionForm.tt_is_deleted = ""; // is_deleted
						$scope.actionForm.tt_author = ""; // author
						$scope.actionForm.tt_location_id = ""; // location_id
						$scope.actionForm.tt_day_type = ""; // day_type
						$scope.actionForm.tt_start_time = ""; // start_time
						$scope.actionForm.tt_duration_minutes = ""; // duration_minutes
						$scope.actionForm.tt_isBlocked = ""; // isBlocked
						$scope.actionForm.tt_json = ""; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_tt_location_id	= {value: "", displayName: ""};
					};

					$scope.marcarTodo = function() {

						// Combos y auxiliares para componentes de presentación:
						$scope.actionForm.filasGrid = $scope.aux_filasGrid.value;

						TtDSPFIL_service
						.marcarTodo($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
									for ( var i = 0; i < $scope.actionForm.clavesMarcadas.length; i++ ) {
										$scope.actionForm.filasMarcadas[i] = true;
									}
								} else {
									app_services.errorComun(response.data.text);
								}

							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});
					};

					$scope.desMarcarTodo = function() {
						$scope.filtrar();
					};

					$scope.marcados_suprimir = function() {
						if ( ! confirmar('Suprimir las filas marcadas, ¿está seguro?',this) ) { return; }

						// Combos y auxiliares para componentes de presentación:
						$scope.actionForm.filasGrid = $scope.aux_filasGrid.value;

						TtDSPFIL_service
						.marcados_suprimir($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
									$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
								} else {
									app_services.errorComun(response.data.text);
								}

							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});
					};

					$scope.marcados_copiar = function() {
						if ( ! confirmar('Copiar las filas marcadas a otro location, ¿está seguro?',this) ) { return; }

						var location_id_destino = prompt("Teclear el LOCATION_ID destino de la copia", "");
						if (location_id_destino === "") { return; }
						
						// Combos y auxiliares para componentes de presentación:
						$scope.actionForm.filasGrid = $scope.aux_filasGrid.value;
					    // Combos y auxiliares para componentes de presentación:
					    $scope.actionForm.tt_filtro.tt_location_id = $scope.aux_FLT_tt_location_id.value;
						
						// Usar este campo para llevar la clave destino de la copia:
						$scope.actionForm.tt_json = location_id_destino;

						TtDSPFIL_service
						.marcados_copiar($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
									$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
								} else {
									app_services.errorComun(response.data.text);
								}

							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});
					};

					
	/////////////
	/////////////
	// Panel empotrado de "ADDRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.agregar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de combos:
						$scope.actionForm.tt_location_id = $scope.aux_tt_location_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						TtDSPFIL_service
								.add($scope.actionForm)
								.then(
									function(response) {
	
										if (response.data.rc === 'OK') {
											$mdToast.show($mdToast.simple().textContent("Registro agregado").position('top right').hideDelay(500));
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
						$('#ttDSPFIL_EDTRCD_modal').modal('hide');
						$('#ttDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de combos:
						$scope.actionForm.tt_location_id = $scope.aux_tt_location_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						TtDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#ttDSPFIL_EDTRCD_modal').modal('hide');
											$mdToast.show($mdToast.simple().textContent("Registro cambiado").position('top right').hideDelay(500));
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
						TtDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#ttDSPFIL_EDTRCD_modal').modal('hide');
											$mdToast.show($mdToast.simple().textContent("Registro suprimido").position('top right').hideDelay(500));
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

				} ]);
