
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
		'EsDSPFIL_Ctrl_basic',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				'$mdToast',
				'validationService',
				'app_services',
				'EsDSPFIL_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, validationService, app_services, EsDSPFIL_service) {
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
						    , es_filtro : {
								  es_sincro: "" // sincro
								, es_mark: "" // mark
								, es_is_deleted: "" // is_deleted
								, es_author: "" // author
								, es_event_id: "" // event_id
								, es_EV_location_id: "" // EV_location_id
								, es_LO_name: "" // LO_name
								, es_inscription_user_id: "" // inscription_user_id
								, es_first_name: "" // first_name
								, es_last_name: "" // last_name
								, es_phone: "" // phone
								, es_amount: "" // amount
								, es_currency: "" // currency
								, es_tpv_order: "" // tpv_order
								, es_pay_status: "" // pay_status
								, es_json: "" // json						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, es_sincro: "" // sincro
								, es_mark: "" // mark
								, es_is_deleted: "" // is_deleted
								, es_author: "" // author
								, es_event_id: "" // event_id
								, es_EV_location_id: "" // EV_location_id
								, es_LO_name: "" // LO_name
								, es_inscription_user_id: "" // inscription_user_id
								, es_first_name: "" // first_name
								, es_last_name: "" // last_name
								, es_phone: "" // phone
								, es_amount: "" // amount
								, es_currency: "" // currency
								, es_tpv_order: "" // tpv_order
								, es_pay_status: "" // pay_status
								, es_json: "" // json						
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
				    $scope.aux_es_EV_location_id = "";
				    $scope.lst_ev = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];
				    $scope.aux_es_event_id = "";
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
					$scope.actionForm.es_filtro = angular.fromJson( $window.sessionStorage.getItem("EsDSPFIL.es_filtro") );
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
					function tpv_submitForm(campos_tpv) {
						// Submitir formulario "TPV_form":
					    var f = document.createElement("FORM");
					    f.enctype = "application/x-www-form-urlencoded";
					    f.method = "post";
					    f.acceptCharset = "UTF-8";
					    f.action = campos_tpv.url_redirect;
					    f.setAttribute("id", "TPV_form");
					    document.body.appendChild(f);

					    var x = document.createElement("INPUT");
					    x.setAttribute("type", "hidden");
					    x.setAttribute("name", "ds_SignatureVersion");
					    x.setAttribute("value", campos_tpv.ds_SignatureVersion);
					    document.getElementById("TPV_form").appendChild(x);

					    var y = document.createElement("INPUT");
					    y.setAttribute("type", "hidden");
					    y.setAttribute("name", "ds_MerchantParameters");
					    y.setAttribute("value", campos_tpv.ds_MerchantParameters);
					    document.getElementById("TPV_form").appendChild(y);

					    var z = document.createElement("INPUT");
					    z.setAttribute("type", "hidden");
					    z.setAttribute("name", "ds_Signature");
					    z.setAttribute("value", campos_tpv.ds_Signature);
					    document.getElementById("TPV_form").appendChild(z);
					    
					    f.submit();
					
					};
					///////////////////////////////////////////////////////////////////////
					// Inicializar pantalla:
					EsDSPFIL_service
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
					app_services.lo_lst().then( function(response) {if (response.rc === 'OK') { $scope.lst_lo = response.text;} else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });

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
						EsDSPFIL_service
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
						EsDSPFIL_service
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
						EsDSPFIL_service
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
						EsDSPFIL_service
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
						EsDSPFIL_service
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
						$scope.actionForm.es_sincro = reg.es_sincro; // sincro
						$scope.actionForm.es_mark = reg.es_mark; // mark
						$scope.actionForm.es_is_deleted = reg.es_is_deleted; // is_deleted
						$scope.actionForm.es_author = reg.es_author; // author
						$scope.actionForm.es_event_id = reg.es_event_id; // event_id
						$scope.actionForm.es_EV_location_id = reg.es_EV_location_id; // EV_location_id
						$scope.actionForm.es_LO_name = reg.es_LO_name; // LO_name
						$scope.actionForm.es_inscription_user_id = reg.es_inscription_user_id; // inscription_user_id
						$scope.actionForm.es_first_name = reg.es_first_name; // first_name
						$scope.actionForm.es_last_name = reg.es_last_name; // last_name
						$scope.actionForm.es_phone = reg.es_phone; // phone
						$scope.actionForm.es_amount = reg.es_amount; // amount
						$scope.actionForm.es_currency = reg.es_currency; // currency
						$scope.actionForm.es_tpv_order = reg.es_tpv_order; // tpv_order
						$scope.actionForm.es_pay_status = reg.es_pay_status; // pay_status
						$scope.actionForm.es_json = reg.es_json; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_es_EV_location_id = {value: $scope.actionForm.es_EV_location_id, displayName: $scope.actionForm.es_LO_name};
						$scope.aux_es_event_id = {value: $scope.actionForm.es_event_id, displayName: ""};
					};

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.es_event_id = ""; // event_id
						$scope.actionForm.es_inscription_user_id = ""; // inscription_user_id						
					};

					$scope.initReg = function() {
						// Formato de registro:
						$scope.actionForm.es_sincro = ""; // sincro
						$scope.actionForm.es_mark = ""; // mark
						$scope.actionForm.es_is_deleted = ""; // is_deleted
						$scope.actionForm.es_author = ""; // author
						$scope.actionForm.es_event_id = ""; // event_id
						$scope.actionForm.es_EV_location_id = ""; // EV_location_id
						$scope.actionForm.es_LO_name = ""; // LO_name
						$scope.actionForm.es_inscription_user_id = ""; // inscription_user_id
						$scope.actionForm.es_first_name = ""; // first_name
						$scope.actionForm.es_last_name = ""; // last_name
						$scope.actionForm.es_phone = ""; // phone
						$scope.actionForm.es_amount = ""; // amount
						$scope.actionForm.es_currency = ""; // currency
						$scope.actionForm.es_tpv_order = ""; // tpv_order
						$scope.actionForm.es_pay_status = ""; // pay_status
						$scope.actionForm.es_json = ""; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_es_EV_location_id = {value: "", displayName: ""};
						$scope.aux_es_event_id = {value: "", displayName: ""};
					};

					$scope.marcarTodo = function() {

						// Combos y auxiliares para componentes de presentación:
						$scope.actionForm.filasGrid = $scope.aux_filasGrid.value;

						EsDSPFIL_service
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

						EsDSPFIL_service
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

					
	/////////////
	/////////////
	// Panel empotrado de "ADDRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.agregar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.es_EV_location_id = $scope.aux_es_EV_location_id.value;
						$scope.actionForm.es_event_id = $scope.aux_es_event_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						EsDSPFIL_service
								.add($scope.actionForm)
								.then(
									function(response) {
	
										if (response.data.rc === 'OK') {
//											$mdToast.showSimple( "Registro agregado" );
//											$('.modal-backdrop').remove();
//											$state.reload();
											if ( response.data.text.url_redirect != undefined ) {
												/////////////
												// TPV virtual:
												tpv_submitForm( response.data.text );
												/////////////
											} else {
												$state.reload();
											}
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
						$('#esDSPFIL_EDTRCD_modal').modal('hide');
						$('#esDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.es_EV_location_id = $scope.aux_es_EV_location_id.value;
						$scope.actionForm.es_event_id = $scope.aux_es_event_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						EsDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#esDSPFIL_EDTRCD_modal').modal('hide');
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

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.es_EV_location_id = $scope.aux_es_EV_location_id.value;
						$scope.actionForm.es_event_id = $scope.aux_es_event_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						EsDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#esDSPFIL_EDTRCD_modal').modal('hide');
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
					//////////////
					// Eventos de "location_id":
					$scope.location_id_onOpen = function() {
						$scope.lst_lo = null;
						app_services.lo_lst().then( function(response) {if (response.rc === 'OK') { $scope.lst_lo = response.text;} else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
					}
					$scope.location_id_onChange = function() {
						$scope.aux_es_event_id = {value: "", displayName: ""};
						$scope.actionForm.es_event_id = $scope.aux_es_event_id.value;
						$scope.lst_ev = null;
						app_services.ev_lst( $scope.aux_es_EV_location_id.value ).then( function(response) {if (response.rc === 'OK') { $scope.lst_ev = response.text;} else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
					}
//					$scope.location_id_onClose = function() {
//						$scope.lst_ev = null;
//						app_services.ev_lst( $scope.aux_es_EV_location_id.value ).then( function(response) {if (response.rc === 'OK') { $scope.lst_ev = response.text;} else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
//					}
					//////////////

				} ]);
