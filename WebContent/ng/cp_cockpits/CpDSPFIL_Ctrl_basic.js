
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
		'CpDSPFIL_Ctrl_basic',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				'$mdToast',
				'validationService',
				'app_services',
				'CpDSPFIL_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, validationService, app_services, CpDSPFIL_service) {
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
						    , cp_filtro : {
								  cp_sincro: "" // sincro
								, cp_mark: "" // mark
								, cp_is_deleted: "" // is_deleted
								, cp_author: "" // author
								, cp_location_id: "" // location_id
								, cp_LO_name: "" // LO_name
								, cp_LO_address: "" // LO_address
								, cp_LO_city: "" // LO_city
								, cp_LO_postal_code: "" // LO_postal_code
								, cp_LO_lat: "" // LO_lat
								, cp_LO_lon: "" // LO_lon
								, cp_cockpit_id: "" // cockpit_id
								, cp_serial_number: "" // serial_number
								, cp_name: "" // name
								, cp_isBlocked: "" // isBlocked
								, cp_asignation_order: "" // asignation_order
								, cp_install_date: "" // install_date
								, cp_reset_date_used: "" // reset_date_used
								, cp_hours_used: "" // hours_used
								, cp_note: "" // note
								, cp_comment: "" // comment
								, cp_observation: "" // observation
								, cp_warning: "" // warning
								, cp_contact_service: "" // contact_service
								, cp_json: "" // json						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, cp_sincro: "" // sincro
								, cp_mark: "" // mark
								, cp_is_deleted: "" // is_deleted
								, cp_author: "" // author
								, cp_location_id: "" // location_id
								, cp_LO_name: "" // LO_name
								, cp_LO_address: "" // LO_address
								, cp_LO_city: "" // LO_city
								, cp_LO_postal_code: "" // LO_postal_code
								, cp_LO_lat: "" // LO_lat
								, cp_LO_lon: "" // LO_lon
								, cp_cockpit_id: "" // cockpit_id
								, cp_serial_number: "" // serial_number
								, cp_name: "" // name
								, cp_isBlocked: "" // isBlocked
								, cp_asignation_order: "" // asignation_order
								, cp_install_date: "" // install_date
								, cp_reset_date_used: "" // reset_date_used
								, cp_hours_used: "" // hours_used
								, cp_note: "" // note
								, cp_comment: "" // comment
								, cp_observation: "" // observation
								, cp_warning: "" // warning
								, cp_contact_service: "" // contact_service
								, cp_json: "" // json						
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
					$scope.lst_isBlocked = ["","S"];
				    $scope.lst_lo = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];
				    $scope.aux_cp_location_id = "";
				    $scope.aux_FLT_cp_location_id = "";
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
					$scope.actionForm.cp_filtro = angular.fromJson( $window.sessionStorage.getItem("CpDSPFIL.cp_filtro") );
					if ( null != $scope.actionForm.cp_filtro ) {
					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_FLT_cp_location_id = {value: $scope.actionForm.cp_filtro.cp_location_id, displayName: ""};
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
					CpDSPFIL_service
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
					    $scope.actionForm.cp_filtro.cp_location_id = $scope.aux_FLT_cp_location_id.value;

					    CpDSPFIL_service
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
						CpDSPFIL_service
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
						CpDSPFIL_service
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
						CpDSPFIL_service
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
						CpDSPFIL_service
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
						$scope.actionForm.cp_sincro = reg.cp_sincro; // sincro
						$scope.actionForm.cp_mark = reg.cp_mark; // mark
						$scope.actionForm.cp_is_deleted = reg.cp_is_deleted; // is_deleted
						$scope.actionForm.cp_author = reg.cp_author; // author
						$scope.actionForm.cp_location_id = reg.cp_location_id; // location_id
						$scope.actionForm.cp_LO_name = reg.cp_LO_name; // LO_name
						$scope.actionForm.cp_LO_address = reg.cp_LO_address; // LO_address
						$scope.actionForm.cp_LO_city = reg.cp_LO_city; // LO_city
						$scope.actionForm.cp_LO_postal_code = reg.cp_LO_postal_code; // LO_postal_code
						$scope.actionForm.cp_LO_lat = reg.cp_LO_lat; // LO_lat
						$scope.actionForm.cp_LO_lon = reg.cp_LO_lon; // LO_lon
						$scope.actionForm.cp_cockpit_id = reg.cp_cockpit_id; // cockpit_id
						$scope.actionForm.cp_serial_number = reg.cp_serial_number; // serial_number
						$scope.actionForm.cp_name = reg.cp_name; // name
						$scope.actionForm.cp_isBlocked = reg.cp_isBlocked; // isBlocked
						$scope.actionForm.cp_asignation_order = reg.cp_asignation_order; // asignation_order
						$scope.actionForm.cp_install_date = reg.cp_install_date; // install_date
						$scope.actionForm.cp_reset_date_used = reg.cp_reset_date_used; // reset_date_used
						$scope.actionForm.cp_hours_used = reg.cp_hours_used; // hours_used
						$scope.actionForm.cp_note = reg.cp_note; // note
						$scope.actionForm.cp_comment = reg.cp_comment; // comment
						$scope.actionForm.cp_observation = reg.cp_observation; // observation
						$scope.actionForm.cp_warning = reg.cp_warning; // warning
						$scope.actionForm.cp_contact_service = reg.cp_contact_service; // contact_service						
						$scope.actionForm.cp_json = reg.cp_json; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_cp_location_id = {value: $scope.actionForm.cp_location_id, 	displayName: ""};
					};

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.cp_cockpit_id = ""; // cockpit_id
						$scope.actionForm.cp_location_id = ""; // location_id						
					};

					$scope.initReg = function() {
						// Formato de registro:
						$scope.actionForm.cp_sincro = ""; // sincro
						$scope.actionForm.cp_mark = ""; // mark
						$scope.actionForm.cp_is_deleted = ""; // is_deleted
						$scope.actionForm.cp_author = ""; // author
						$scope.actionForm.cp_location_id = ""; // location_id
						$scope.actionForm.cp_LO_name = ""; // LO_name
						$scope.actionForm.cp_LO_address = ""; // LO_address
						$scope.actionForm.cp_LO_city = ""; // LO_city
						$scope.actionForm.cp_LO_postal_code = ""; // LO_postal_code
						$scope.actionForm.cp_LO_lat = ""; // LO_lat
						$scope.actionForm.cp_LO_lon = ""; // LO_lon
						$scope.actionForm.cp_cockpit_id = ""; // cockpit_id
						$scope.actionForm.cp_serial_number = ""; // serial_number
						$scope.actionForm.cp_name = ""; // name
						$scope.actionForm.cp_isBlocked = ""; // isBlocked
						$scope.actionForm.cp_asignation_order = ""; // asignation_order
						$scope.actionForm.cp_install_date = ""; // install_date
						$scope.actionForm.cp_reset_date_used = ""; // reset_date_used
						$scope.actionForm.cp_hours_used = ""; // hours_used
						$scope.actionForm.cp_note = ""; // note
						$scope.actionForm.cp_comment = ""; // comment
						$scope.actionForm.cp_observation = ""; // observation
						$scope.actionForm.cp_warning = ""; // warning
						$scope.actionForm.cp_contact_service = ""; // contact_service						
						$scope.actionForm.cp_json = ""; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_cp_location_id	= {value: "", displayName: ""};
					};
					
	/////////////
	/////////////
	// Panel empotrado de "ADDRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.agregar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de combos:
						$scope.actionForm.cp_location_id = $scope.aux_cp_location_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						CpDSPFIL_service
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
						$('#cpDSPFIL_EDTRCD_modal').modal('hide');
						$('#cpDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de combos:
						$scope.actionForm.cp_location_id = $scope.aux_cp_location_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						CpDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#cpDSPFIL_EDTRCD_modal').modal('hide');
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
						CpDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#cpDSPFIL_EDTRCD_modal').modal('hide');
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
