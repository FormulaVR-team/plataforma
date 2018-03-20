
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
		'PrDSPFIL_Ctrl_basic',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				'$mdToast',
				'validationService',
				'app_services',
				'PrDSPFIL_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, validationService, app_services, PrDSPFIL_service) {
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
						    , pr_filtro : {
								  pr_sincro: "" // sincro
								, pr_mark: "" // mark
								, pr_is_deleted: "" // is_deleted
								, pr_author: "" // author
								, pr_location_id: "" // location_id
								, pr_LO_name: "" // LO_name
								, pr_product_id: "" // product_id
								, pr_PT_name: "" // PT_name
								, pr_product_id_promo: "" // product_id_promo
								, pr_PT_name_promo: "" // PT_name_promo
								, pr_deadline: "" // deadline
								, pr_min_quantity: "" // min_quantity						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, pr_sincro: "" // sincro
								, pr_mark: "" // mark
								, pr_is_deleted: "" // is_deleted
								, pr_author: "" // author
								, pr_location_id: "" // location_id
								, pr_LO_name: "" // LO_name
								, pr_product_id: "" // product_id
								, pr_PT_name: "" // PT_name
								, pr_product_id_promo: "" // product_id_promo
								, pr_PT_name_promo: "" // PT_name_promo
								, pr_deadline: "" // deadline
								, pr_min_quantity: "" // min_quantity						
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
				    $scope.aux_pr_location_id = "";
				    $scope.aux_FLT_pr_location_id = "";
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
					$scope.actionForm.pr_filtro = angular.fromJson( $window.sessionStorage.getItem("PrDSPFIL.pr_filtro") );
					if ( null != $scope.actionForm.pr_filtro ) {
					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_FLT_pr_location_id = {value: $scope.actionForm.pr_filtro.pr_location_id, displayName: ""};
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
					PrDSPFIL_service
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
					    $scope.actionForm.pr_filtro.pr_location_id = $scope.aux_FLT_pr_location_id.value;

						PrDSPFIL_service
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
						PrDSPFIL_service
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
						PrDSPFIL_service
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
						PrDSPFIL_service
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
						PrDSPFIL_service
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
						$scope.actionForm.pr_sincro = reg.pr_sincro; // sincro
						$scope.actionForm.pr_mark = reg.pr_mark; // mark
						$scope.actionForm.pr_is_deleted = reg.pr_is_deleted; // is_deleted
						$scope.actionForm.pr_author = reg.pr_author; // author
						$scope.actionForm.pr_location_id = reg.pr_location_id; // location_id
						$scope.actionForm.pr_LO_name = reg.pr_LO_name; // LO_name
						$scope.actionForm.pr_product_id = reg.pr_product_id; // product_id
						$scope.actionForm.pr_PT_name = reg.pr_PT_name; // PT_name
						$scope.actionForm.pr_product_id_promo = reg.pr_product_id_promo; // product_id_promo
						$scope.actionForm.pr_PT_name_promo = reg.pr_PT_name_promo; // PT_name_promo
						$scope.actionForm.pr_deadline = reg.pr_deadline; // deadline
						$scope.actionForm.pr_min_quantity = reg.pr_min_quantity; // min_quantity						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_pr_location_id = {value: $scope.actionForm.pr_location_id, 	displayName: ""};
					};

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.pr_location_id = ""; // location_id
						$scope.actionForm.pr_product_id = ""; // product_id						
					};

					$scope.initReg = function() {
						// Formato de registro:
						$scope.actionForm.pr_sincro = ""; // sincro
						$scope.actionForm.pr_mark = ""; // mark
						$scope.actionForm.pr_is_deleted = ""; // is_deleted
						$scope.actionForm.pr_author = ""; // author
						$scope.actionForm.pr_location_id = ""; // location_id
						$scope.actionForm.pr_LO_name = ""; // LO_name
						$scope.actionForm.pr_product_id = ""; // product_id
						$scope.actionForm.pr_PT_name = ""; // PT_name
						$scope.actionForm.pr_product_id_promo = ""; // product_id_promo
						$scope.actionForm.pr_PT_name_promo = ""; // PT_name_promo
						$scope.actionForm.pr_deadline = ""; // deadline
						$scope.actionForm.pr_min_quantity = ""; // min_quantity						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_pr_location_id	= {value: "", displayName: ""};
					};
					
	/////////////
	/////////////
	// Panel empotrado de "ADDRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.agregar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de combos:
						$scope.actionForm.ad_pr_location_id = $scope.aux_pr_location_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						PrDSPFIL_service
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
						$('#prDSPFIL_EDTRCD_modal').modal('hide');
						$('#prDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de combos:
						$scope.actionForm.ad_pr_location_id = $scope.aux_pr_location_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						PrDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#prDSPFIL_EDTRCD_modal').modal('hide');
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
						PrDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#prDSPFIL_EDTRCD_modal').modal('hide');
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
