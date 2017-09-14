
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
		'PyDSPFIL_Ctrl_basic',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				'$mdToast',
				'validationService',
				'app_services',
				'PyDSPFIL_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, validationService, app_services, PyDSPFIL_service) {
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
						    , py_filtro : {
								  py_sincro: "" // sincro
								, py_mark: "" // mark
								, py_is_deleted: "" // is_deleted
								, py_author: "" // author
								, py_user_id: "" // user_id
								, py_US_first_name: "" // US_first_name
								, py_US_last_name: "" // US_last_name
								, py_reservation_id: "" // reservation_id
								, py_paypal_token_id: "" // paypal_token_id
								, py_paypal_usr: "" // paypal_usr
								, py_paypal_pwd: "" // paypal_pwd
								, py_paypal_signature: "" // paypal_signature
								, py_stsProceso: "" // stsProceso
								, py_json: "" // json						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, py_sincro: "" // sincro
								, py_mark: "" // mark
								, py_is_deleted: "" // is_deleted
								, py_author: "" // author
								, py_user_id: "" // user_id
								, py_US_first_name: "" // US_first_name
								, py_US_last_name: "" // US_last_name
								, py_reservation_id: "" // reservation_id
								, py_paypal_token_id: "" // paypal_token_id
								, py_paypal_usr: "" // paypal_usr
								, py_paypal_pwd: "" // paypal_pwd
								, py_paypal_signature: "" // paypal_signature
								, py_stsProceso: "" // stsProceso
								, py_json: "" // json						
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
					$scope.actionForm.py_filtro = angular.fromJson( $window.sessionStorage.getItem("PyDSPFIL.py_filtro") );
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
					PyDSPFIL_service
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
						PyDSPFIL_service
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
						PyDSPFIL_service
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
						PyDSPFIL_service
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
						PyDSPFIL_service
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
						PyDSPFIL_service
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
						$scope.actionForm.py_sincro = reg.py_sincro; // sincro
						$scope.actionForm.py_mark = reg.py_mark; // mark
						$scope.actionForm.py_is_deleted = reg.py_is_deleted; // is_deleted
						$scope.actionForm.py_author = reg.py_author; // author
						$scope.actionForm.py_user_id = reg.py_user_id; // user_id
						$scope.actionForm.py_US_first_name = reg.py_US_first_name; // US_first_name
						$scope.actionForm.py_US_last_name = reg.py_US_last_name; // US_last_name
						$scope.actionForm.py_reservation_id = reg.py_reservation_id; // reservation_id
						$scope.actionForm.py_paypal_token_id = reg.py_paypal_token_id; // paypal_token_id
						$scope.actionForm.py_paypal_usr = reg.py_paypal_usr; // paypal_usr
						$scope.actionForm.py_paypal_pwd = reg.py_paypal_pwd; // paypal_pwd
						$scope.actionForm.py_paypal_signature = reg.py_paypal_signature; // paypal_signature
						$scope.actionForm.py_stsProceso = reg.py_stsProceso; // stsProceso
						$scope.actionForm.py_json = reg.py_json; // json						
					};

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.py_user_id = ""; // user_id
						$scope.actionForm.py_reservation_id = ""; // reservation_id
						$scope.actionForm.py_paypal_token_id = ""; // paypal_token_id						
					};

					$scope.initReg = function() {
						// Formato de registro:
						$scope.actionForm.py_sincro = ""; // sincro
						$scope.actionForm.py_mark = ""; // mark
						$scope.actionForm.py_is_deleted = ""; // is_deleted
						$scope.actionForm.py_author = ""; // author
						$scope.actionForm.py_user_id = ""; // user_id
						$scope.actionForm.py_US_first_name = ""; // US_first_name
						$scope.actionForm.py_US_last_name = ""; // US_last_name
						$scope.actionForm.py_reservation_id = ""; // reservation_id
						$scope.actionForm.py_paypal_token_id = ""; // paypal_token_id
						$scope.actionForm.py_paypal_usr = ""; // paypal_usr
						$scope.actionForm.py_paypal_pwd = ""; // paypal_pwd
						$scope.actionForm.py_paypal_signature = ""; // paypal_signature
						$scope.actionForm.py_stsProceso = ""; // stsProceso
						$scope.actionForm.py_json = ""; // json						
					};
					
	/////////////
	/////////////
	// Panel empotrado de "ADDRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.agregar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

						//llamo a su servicio pasandole el actionForm de entidad
						PyDSPFIL_service
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
						$('#pyDSPFIL_EDTRCD_modal').modal('hide');
						$('#pyDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

						//llamo a su servicio pasandole el actionForm de entidad
						PyDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#pyDSPFIL_EDTRCD_modal').modal('hide');
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
						PyDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#pyDSPFIL_EDTRCD_modal').modal('hide');
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

				} ]);
