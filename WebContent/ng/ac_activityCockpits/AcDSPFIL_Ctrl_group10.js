
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
		'AcDSPFIL_Ctrl_group10',
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
				'AcDSPFIL_service',
				'$rootScope',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, $filter, validationService, app_services, AcDSPFIL_service, $rootScope) {
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
						    , ac_filtro : {
								  ac_sincro: "" // sincro
								, ac_mark: "" // mark
								, ac_is_deleted: "" // is_deleted
								, ac_author: "" // author
								, ac_serial: "" // serial
								, ac_location_id: "" // location_id
								, ac_LO_name: "" // LO_name
								, ac_computername: "" // computername
								, ac_filename: "" // filename
								, ac_content: "" // content
								, ac_json: "" // json
								, ac_aaaa_mm: "" // aaaa_mm
								, ac_aaaa_mm_dd: "" // aaaa_mm_dd
								, ac_aaaa_mm_dd_hh: "" // aaaa_mm_dd_hh
								, ac_aaaa_mm_dd_hh_m0: "" // aaaa_mm_dd_hh_m0
								, ac_aaaa_mm_dd_hh_mm: "" // aaaa_mm_dd_hh_mm
								, ac_aaaa_mm_dd_hh_mm_ss: "" // aaaa_mm_dd_hh_mm_ss						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, ac_sincro: "" // sincro
								, ac_mark: "" // mark
								, ac_is_deleted: "" // is_deleted
								, ac_author: "" // author
								, ac_serial: "" // serial
								, ac_location_id: "" // location_id
								, ac_LO_name: "" // LO_name
								, ac_computername: "" // computername
								, ac_filename: "" // filename
								, ac_content: "" // content
								, ac_json: "" // json
								, ac_aaaa_mm: "" // aaaa_mm
								, ac_aaaa_mm_dd: "" // aaaa_mm_dd
								, ac_aaaa_mm_dd_hh: "" // aaaa_mm_dd_hh
								, ac_aaaa_mm_dd_hh_m0: "" // aaaa_mm_dd_hh_m0
								, ac_aaaa_mm_dd_hh_mm: "" // aaaa_mm_dd_hh_mm
								, ac_aaaa_mm_dd_hh_mm_ss: "" // aaaa_mm_dd_hh_mm_ss						
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

					// Combos y auxiliares para componentes de presentación:
				    $scope.aux_FLT_ac_aaaa_mm_dd = new Date();	// Panel de filtros

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
					$scope.actionForm.ac_filtro = angular.fromJson( $window.sessionStorage.getItem("AcDSPFIL.ac_filtro") );
					if ( $scope.actionForm.ac_filtro != null && $scope.actionForm.ac_filtro.ac_aaaa_mm_dd != undefined && $scope.actionForm.ac_filtro.ac_aaaa_mm_dd != null && $scope.actionForm.ac_filtro.ac_aaaa_mm_dd != "" ) {
						$scope.aux_FLT_ac_aaaa_mm_dd = new Date( $scope.actionForm.ac_filtro.ac_aaaa_mm_dd );
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
					AcDSPFIL_service
						.reload_group10($scope.actionForm)
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

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
					    if ( null == $scope.aux_FLT_ac_aaaa_mm_dd ) {
						    $scope.actionForm.ac_filtro.ac_aaaa_mm_dd = '';
					    } else {
						    $scope.actionForm.ac_filtro.ac_aaaa_mm_dd = $filter('date')($scope.aux_FLT_ac_aaaa_mm_dd, 'yyyy-MM-dd');
					    }

					    AcDSPFIL_service
						.filtrar_group10($scope.actionForm)
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
						AcDSPFIL_service
						.exportar_group10($scope.actionForm)
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
						AcDSPFIL_service
						.avPg_group10($scope.actionForm)
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
						AcDSPFIL_service
						.rtPg_group10($scope.actionForm)
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
						AcDSPFIL_service
						.reload_group10($scope.actionForm)
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
						$scope.actionForm.ac_sincro = reg.ac_sincro; // sincro
						$scope.actionForm.ac_mark = reg.ac_mark; // mark
						$scope.actionForm.ac_is_deleted = reg.ac_is_deleted; // is_deleted
						$scope.actionForm.ac_author = reg.ac_author; // author
						$scope.actionForm.ac_serial = reg.ac_serial; // serial
						$scope.actionForm.ac_location_id = reg.ac_location_id; // location_id
						$scope.actionForm.ac_LO_name = reg.ac_LO_name; // LO_name
						$scope.actionForm.ac_computername = reg.ac_computername; // computername
						$scope.actionForm.ac_filename = reg.ac_filename; // filename
						$scope.actionForm.ac_content = reg.ac_content; // content
						$scope.actionForm.ac_json = reg.ac_json; // json
						$scope.actionForm.ac_aaaa_mm = reg.ac_aaaa_mm; // aaaa_mm
						$scope.actionForm.ac_aaaa_mm_dd = reg.ac_aaaa_mm_dd; // aaaa_mm_dd
						$scope.actionForm.ac_aaaa_mm_dd_hh = reg.ac_aaaa_mm_dd_hh; // aaaa_mm_dd_hh
						$scope.actionForm.ac_aaaa_mm_dd_hh_m0 = reg.ac_aaaa_mm_dd_hh_m0; // aaaa_mm_dd_hh_m0
						$scope.actionForm.ac_aaaa_mm_dd_hh_mm = reg.ac_aaaa_mm_dd_hh_mm; // aaaa_mm_dd_hh_mm
						$scope.actionForm.ac_aaaa_mm_dd_hh_mm_ss = reg.ac_aaaa_mm_dd_hh_mm_ss; // aaaa_mm_dd_hh_mm_ss						
					};

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.ac_serial = ""; // serial						
					};

					$scope.initReg = function() {
						// Formato de registro:
						$scope.actionForm.ac_sincro = ""; // sincro
						$scope.actionForm.ac_mark = ""; // mark
						$scope.actionForm.ac_is_deleted = ""; // is_deleted
						$scope.actionForm.ac_author = ""; // author
						$scope.actionForm.ac_serial = ""; // serial
						$scope.actionForm.ac_location_id = ""; // location_id
						$scope.actionForm.ac_LO_name = ""; // LO_name
						$scope.actionForm.ac_computername = ""; // computername
						$scope.actionForm.ac_filename = ""; // filename
						$scope.actionForm.ac_content = ""; // content
						$scope.actionForm.ac_json = ""; // json
						$scope.actionForm.ac_aaaa_mm = ""; // aaaa_mm
						$scope.actionForm.ac_aaaa_mm_dd = ""; // aaaa_mm_dd
						$scope.actionForm.ac_aaaa_mm_dd_hh = ""; // aaaa_mm_dd_hh
						$scope.actionForm.ac_aaaa_mm_dd_hh_m0 = ""; // aaaa_mm_dd_hh_m0
						$scope.actionForm.ac_aaaa_mm_dd_hh_mm = ""; // aaaa_mm_dd_hh_mm
						$scope.actionForm.ac_aaaa_mm_dd_hh_mm_ss = ""; // aaaa_mm_dd_hh_mm_ss						
					};

					$scope.marcarTodo = function() {

						// Combos y auxiliares para componentes de presentación:
						$scope.actionForm.filasGrid = $scope.aux_filasGrid.value;

						AcDSPFIL_service
						.marcarTodo_group10($scope.actionForm)
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

						AcDSPFIL_service
						.marcados_suprimir_group10($scope.actionForm)
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

						//llamo a su servicio pasandole el actionForm de entidad
						AcDSPFIL_service
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
						$('#acDSPFIL_EDTRCD_modal').modal('hide');
						$('#acDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

						//llamo a su servicio pasandole el actionForm de entidad
						AcDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#acDSPFIL_EDTRCD_modal').modal('hide');
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

/////////////
					$scope.suprimir = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

						if ( ! confirmar('Suprimir el registro, ¿está seguro?',this) ) { return; }

						//llamo a su servicio pasandole el actionForm de entidad
						AcDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#acDSPFIL_EDTRCD_modal').modal('hide');
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
					$scope.setParms2Zoom = function(reg) {
						// Actualizar el formulario de zoom:
						$rootScope.$emit('group10_refreshEvent', [reg.ac_location_id, reg.ac_computername, reg.ac_aaaa_mm_dd]);
					}
	/////////////
	/////////////

				} ]);
