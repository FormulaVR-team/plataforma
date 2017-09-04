
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
		'Ad_rsDSPFIL_Ctrl_basic',
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
				'Ad_rsDSPFIL_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, $filter, validationService, app_services, Ad_rsDSPFIL_service) {
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
						    , ad_rs_filtro : {
								  ad_rs_sincro: "" // sincro
								, ad_rs_mark: "" // mark
								, ad_rs_is_deleted: "" // is_deleted
								, ad_rs_author: "" // author
								, ad_rs_reservation_id: "" // reservation_id
								, ad_rs_location_id: "" // location_id
								, ad_rs_LO_name: "" // LO_name
								, ad_rs_user_id: "" // user_id
								, ad_rs_US_country_id: "" // US_country_id
								, ad_rs_US_nick: "" // US_nick
								, ad_rs_US_avatar: "" // US_avatar
								, ad_rs_US_is_admin: "" // US_is_admin
								, ad_rs_US_first_name: "" // US_first_name
								, ad_rs_US_last_name: "" // US_last_name
								, ad_rs_product_id: "" // product_id
								, ad_rs_PT_name: "" // PT_name
								, ad_rs_PT_deadline: "" // PT_deadline
								, ad_rs_PT_isPercent: "" // PT_isPercent
								, ad_rs_PT_amount: "" // PT_amount
								, ad_rs_PT_duration_minutes: "" // PT_duration_minutes
								, ad_rs_quantity: "" // quantity
								, ad_rs_product_id2: "" // product_id2
								, ad_rs_PT_name2: "" // PT_name2
								, ad_rs_PT_deadline2: "" // PT_deadline2
								, ad_rs_PT_isPercent2: "" // PT_isPercent2
								, ad_rs_PT_amount2: "" // PT_amount2
								, ad_rs_product_id3: "" // product_id3
								, ad_rs_PT_name3: "" // PT_name3
								, ad_rs_PT_deadline3: "" // PT_deadline3
								, ad_rs_PT_isPercent3: "" // PT_isPercent3
								, ad_rs_PT_amount3: "" // PT_amount3
								, ad_rs_amount: "" // amount
								, ad_rs_currency: "" // currency
								, ad_rs_phone: "" // phone
								, ad_rs_pay_status: "" // pay_status
								, ad_rs_start_date: $filter('date')(new Date(), 'yyyy-MM-dd') // start_date
								, ad_rs_start_time: "" // start_time
								, ad_rs_duration_minutes: "" // duration_minutes
								, ad_rs_places: "" // places
								, ad_rs_coupon_id: "" // coupon_id
								, ad_rs_executed_at: "" // executed_at
								, ad_rs_note: "" // note
								, ad_rs_comment: "" // comment
								, ad_rs_json: "" // json						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, ad_rs_sincro: "" // sincro
								, ad_rs_mark: "" // mark
								, ad_rs_is_deleted: "" // is_deleted
								, ad_rs_author: "" // author
								, ad_rs_reservation_id: "" // reservation_id
								, ad_rs_location_id: "" // location_id
								, ad_rs_LO_name: "" // LO_name
								, ad_rs_user_id: "" // user_id
								, ad_rs_US_country_id: "" // US_country_id
								, ad_rs_US_nick: "" // US_nick
								, ad_rs_US_avatar: "" // US_avatar
								, ad_rs_US_is_admin: "" // US_is_admin
								, ad_rs_US_first_name: "" // US_first_name
								, ad_rs_US_last_name: "" // US_last_name
								, ad_rs_product_id: "" // product_id
								, ad_rs_PT_name: "" // PT_name
								, ad_rs_PT_deadline: "" // PT_deadline
								, ad_rs_PT_isPercent: "" // PT_isPercent
								, ad_rs_PT_amount: "" // PT_amount
								, ad_rs_PT_duration_minutes: "" // PT_duration_minutes
								, ad_rs_quantity: "" // quantity
								, ad_rs_product_id2: "" // product_id2
								, ad_rs_PT_name2: "" // PT_name2
								, ad_rs_PT_deadline2: "" // PT_deadline2
								, ad_rs_PT_isPercent2: "" // PT_isPercent2
								, ad_rs_PT_amount2: "" // PT_amount2
								, ad_rs_product_id3: "" // product_id3
								, ad_rs_PT_name3: "" // PT_name3
								, ad_rs_PT_deadline3: "" // PT_deadline3
								, ad_rs_PT_isPercent3: "" // PT_isPercent3
								, ad_rs_PT_amount3: "" // PT_amount3
								, ad_rs_amount: "" // amount
								, ad_rs_currency: "" // currency
								, ad_rs_phone: "" // phone
								, ad_rs_pay_status: "" // pay_status
								, ad_rs_start_date: "" // start_date
								, ad_rs_start_time: "" // start_time
								, ad_rs_duration_minutes: "" // duration_minutes
								, ad_rs_places: "" // places
								, ad_rs_coupon_id: "" // coupon_id
								, ad_rs_executed_at: "" // executed_at
								, ad_rs_note: "" // note
								, ad_rs_comment: "" // comment
								, ad_rs_json: "" // json						
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

				    // Para búsquedas retardadas.
				    var delay = 500;
				    $scope.timer = false;		

				    /////
				    /////
				    // Combos y auxiliares para componentes de presentación:
				    $scope.lst_lo = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];
				    $scope.aux_rs_location_id = "";
				    $scope.aux_FLT_rs_location_id = "";
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
					var cosa = angular.fromJson( $window.sessionStorage.getItem("Ad_rsDSPFIL.ad_rs_filtro") );
					if ( null != cosa ) {
						$scope.actionForm.ad_rs_filtro = cosa;
					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_FLT_rs_location_id = {value: $scope.actionForm.ad_rs_filtro.ad_rs_location_id, displayName: ""};
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
						
						// Otros:
						sincro_ocupacion( $scope.actionForm.ad_rs_filtro.ad_rs_location_id, $scope.actionForm.ad_rs_filtro.ad_rs_start_date );

					};
					function sincro_ocupacion( location_id, start_date ) {
						/////
						$scope.lst_ocupacion = "";
						// $scope.actionForm.rs_start_date = $filter('date')($scope.aux_rs_start_date, 'yyyy-MM-dd');
						if ( null !=  location_id && '' !==  location_id ) {
							app_services.rtvOcu( location_id, start_date, null )
							.then( 
								function(response) {
									if (response.rc === 'OK') { 
										$scope.lst_ocupacion = response.text.ocupacion;
									} else { 
										alert( "ERROR: " + response.text); }
									}
								,function(response) { 
									console.error("Ha sucedido un error: " + response.statusText); 
									}
								);
						}
						/////
					}
					///////////////////////////////////////////////////////////////////////
					// Inicializar pantalla:
					Ad_rsDSPFIL_service
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
					    $scope.actionForm.ad_rs_filtro.ad_rs_location_id = $scope.aux_FLT_rs_location_id.value;

					    Ad_rsDSPFIL_service
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

					function initSearchTimer() {
						//var txt = txt;
						$scope.timer = $timeout(function() {
							$scope.filtrar();
						}, delay);
					}
					$scope.filtrar_deferred = function() {
						if ($scope.timer) {
							$timeout.cancel($scope.timer)
						}
						initSearchTimer();
					};

					$scope.exportar = function() {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
					    $scope.actionForm.filasGrid = $scope.aux_filasGrid.value;
						Ad_rsDSPFIL_service
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
						Ad_rsDSPFIL_service
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
						Ad_rsDSPFIL_service
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
						Ad_rsDSPFIL_service
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
						$scope.actionForm.ad_rs_sincro = reg.ad_rs_sincro; // sincro
						$scope.actionForm.ad_rs_mark = reg.ad_rs_mark; // mark
						$scope.actionForm.ad_rs_is_deleted = reg.ad_rs_is_deleted; // is_deleted
						$scope.actionForm.ad_rs_author = reg.ad_rs_author; // author
						$scope.actionForm.ad_rs_reservation_id = reg.ad_rs_reservation_id; // reservation_id
						$scope.actionForm.ad_rs_location_id = reg.ad_rs_location_id; // location_id
						$scope.actionForm.ad_rs_LO_name = reg.ad_rs_LO_name; // LO_name
						$scope.actionForm.ad_rs_user_id = reg.ad_rs_user_id; // user_id
						$scope.actionForm.ad_rs_US_country_id = reg.ad_rs_US_country_id; // US_country_id
						$scope.actionForm.ad_rs_US_nick = reg.ad_rs_US_nick; // US_nick
						$scope.actionForm.ad_rs_US_avatar = reg.ad_rs_US_avatar; // US_avatar
						$scope.actionForm.ad_rs_US_is_admin = reg.ad_rs_US_is_admin; // US_is_admin
						$scope.actionForm.ad_rs_US_first_name = reg.ad_rs_US_first_name; // US_first_name
						$scope.actionForm.ad_rs_US_last_name = reg.ad_rs_US_last_name; // US_last_name
						$scope.actionForm.ad_rs_product_id = reg.ad_rs_product_id; // product_id
						$scope.actionForm.ad_rs_PT_name = reg.ad_rs_PT_name; // PT_name
						$scope.actionForm.ad_rs_PT_deadline = reg.ad_rs_PT_deadline; // PT_deadline
						$scope.actionForm.ad_rs_PT_isPercent = reg.ad_rs_PT_isPercent; // PT_isPercent
						$scope.actionForm.ad_rs_PT_amount = reg.ad_rs_PT_amount; // PT_amount
						$scope.actionForm.ad_rs_PT_duration_minutes = reg.ad_rs_PT_duration_minutes; // PT_duration_minutes
						$scope.actionForm.ad_rs_quantity = reg.ad_rs_quantity; // quantity
						$scope.actionForm.ad_rs_product_id2 = reg.ad_rs_product_id2; // product_id2
						$scope.actionForm.ad_rs_PT_name2 = reg.ad_rs_PT_name2; // PT_name2
						$scope.actionForm.ad_rs_PT_deadline2 = reg.ad_rs_PT_deadline2; // PT_deadline2
						$scope.actionForm.ad_rs_PT_isPercent2 = reg.ad_rs_PT_isPercent2; // PT_isPercent2
						$scope.actionForm.ad_rs_PT_amount2 = reg.ad_rs_PT_amount2; // PT_amount2
						$scope.actionForm.ad_rs_product_id3 = reg.ad_rs_product_id3; // product_id3
						$scope.actionForm.ad_rs_PT_name3 = reg.ad_rs_PT_name3; // PT_name3
						$scope.actionForm.ad_rs_PT_deadline3 = reg.ad_rs_PT_deadline3; // PT_deadline3
						$scope.actionForm.ad_rs_PT_isPercent3 = reg.ad_rs_PT_isPercent3; // PT_isPercent3
						$scope.actionForm.ad_rs_PT_amount3 = reg.ad_rs_PT_amount3; // PT_amount3
						$scope.actionForm.ad_rs_amount = reg.ad_rs_amount; // amount
						$scope.actionForm.ad_rs_currency = reg.ad_rs_currency; // currency
						$scope.actionForm.ad_rs_phone = reg.ad_rs_phone; // phone
						$scope.actionForm.ad_rs_pay_status = reg.ad_rs_pay_status; // pay_status
						$scope.actionForm.ad_rs_start_date = reg.ad_rs_start_date; // start_date
						$scope.actionForm.ad_rs_start_time = reg.ad_rs_start_time; // start_time
						$scope.actionForm.ad_rs_duration_minutes = reg.ad_rs_duration_minutes; // duration_minutes
						$scope.actionForm.ad_rs_places = reg.ad_rs_places; // places
						$scope.actionForm.ad_rs_coupon_id = reg.ad_rs_coupon_id; // coupon_id
						$scope.actionForm.ad_rs_executed_at = reg.ad_rs_executed_at; // executed_at
						$scope.actionForm.ad_rs_note = reg.ad_rs_note; // note
						$scope.actionForm.ad_rs_comment = reg.ad_rs_comment; // comment
						$scope.actionForm.ad_rs_json = reg.ad_rs_json; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_rs_location_id = {value: $scope.actionForm.ad_rs_location_id, 	displayName: ""};
					};

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.ad_rs_reservation_id = ""; // reservation_id						
					};

					$scope.initReg = function() {
						// Formato de registro:
						$scope.actionForm.ad_rs_sincro = ""; // sincro
						$scope.actionForm.ad_rs_mark = ""; // mark
						$scope.actionForm.ad_rs_is_deleted = ""; // is_deleted
						$scope.actionForm.ad_rs_author = ""; // author
						$scope.actionForm.ad_rs_reservation_id = ""; // reservation_id
						$scope.actionForm.ad_rs_location_id = ""; // location_id
						$scope.actionForm.ad_rs_LO_name = ""; // LO_name
						$scope.actionForm.ad_rs_user_id = ""; // user_id
						$scope.actionForm.ad_rs_US_country_id = ""; // US_country_id
						$scope.actionForm.ad_rs_US_nick = ""; // US_nick
						$scope.actionForm.ad_rs_US_avatar = ""; // US_avatar
						$scope.actionForm.ad_rs_US_is_admin = ""; // US_is_admin
						$scope.actionForm.ad_rs_US_first_name = ""; // US_first_name
						$scope.actionForm.ad_rs_US_last_name = ""; // US_last_name
						$scope.actionForm.ad_rs_product_id = ""; // product_id
						$scope.actionForm.ad_rs_PT_name = ""; // PT_name
						$scope.actionForm.ad_rs_PT_deadline = ""; // PT_deadline
						$scope.actionForm.ad_rs_PT_isPercent = ""; // PT_isPercent
						$scope.actionForm.ad_rs_PT_amount = ""; // PT_amount
						$scope.actionForm.ad_rs_PT_duration_minutes = ""; // PT_duration_minutes
						$scope.actionForm.ad_rs_quantity = ""; // quantity
						$scope.actionForm.ad_rs_product_id2 = ""; // product_id2
						$scope.actionForm.ad_rs_PT_name2 = ""; // PT_name2
						$scope.actionForm.ad_rs_PT_deadline2 = ""; // PT_deadline2
						$scope.actionForm.ad_rs_PT_isPercent2 = ""; // PT_isPercent2
						$scope.actionForm.ad_rs_PT_amount2 = ""; // PT_amount2
						$scope.actionForm.ad_rs_product_id3 = ""; // product_id3
						$scope.actionForm.ad_rs_PT_name3 = ""; // PT_name3
						$scope.actionForm.ad_rs_PT_deadline3 = ""; // PT_deadline3
						$scope.actionForm.ad_rs_PT_isPercent3 = ""; // PT_isPercent3
						$scope.actionForm.ad_rs_PT_amount3 = ""; // PT_amount3
						$scope.actionForm.ad_rs_amount = ""; // amount
						$scope.actionForm.ad_rs_currency = ""; // currency
						$scope.actionForm.ad_rs_phone = ""; // phone
						$scope.actionForm.ad_rs_pay_status = ""; // pay_status
						$scope.actionForm.ad_rs_start_date = ""; // start_date
						$scope.actionForm.ad_rs_start_time = ""; // start_time
						$scope.actionForm.ad_rs_duration_minutes = ""; // duration_minutes
						$scope.actionForm.ad_rs_places = ""; // places
						$scope.actionForm.ad_rs_coupon_id = ""; // coupon_id
						$scope.actionForm.ad_rs_executed_at = ""; // executed_at
						$scope.actionForm.ad_rs_note = ""; // note
						$scope.actionForm.ad_rs_comment = ""; // comment
						$scope.actionForm.ad_rs_json = ""; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_rs_location_id	= {value: "", displayName: ""};
					};
					
	/////////////
	/////////////
	// Panel empotrado de "ADDRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.agregar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de combos:
						$scope.actionForm.ad_rs_location_id = $scope.aux_rs_location_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						Ad_rsDSPFIL_service
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
						$('#ad_rsDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de combos:
						$scope.actionForm.ad_rs_location_id = $scope.aux_rs_location_id.value;

						//llamo a su servicio pasandole el actionForm de entidad
						Ad_rsDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
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
						Ad_rsDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
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
					$scope.card_view_details = function() {
						if ( $scope.actionForm.ad_rs_coupon_id == undefined || $scope.actionForm.ad_rs_coupon_id.length < 1 ) {
							alert("Hay que indicar una TARJETA");
							return;
						}
						
						app_services
							.rtvTjData($scope.actionForm.logon_USR,$scope.actionForm.logon_HSH,$scope.actionForm.ad_rs_coupon_id)
							.then(
								function(response) {
	
									if (response.rc === 'OK') {
										app_services.errorComun( 
												       "Propietario: " + response.text.tj_author 
												+ "</br>Tarjeta: " + response.text.tj_card_id 
												+ "</br>Carga inicial: " + response.text.tj_balance_initial
												+ "</br><h2>Saldo: " + response.text.tj_balance_current + "</h2>" 
												);
									} else {
										app_services.errorComun(response.text);
									}
	
								},
								function(response) {
									console.error("Ha sucedido un error: " + response.statusText);
								});
						
					};
					$scope.executeReservation = function(caso) {
						
						if ( caso === 'prepago' ) {
							if ( $scope.actionForm.ad_rs_coupon_id == undefined || $scope.actionForm.ad_rs_coupon_id.length < 1 ) {
								alert("Hay que indicar una TARJETA");
								return;
							}
						}
						
						if ( confirmar('¿Estas seguro de confirmar esta reserva?',this)==true ) {
							//llamo a su servicio pasandole el actionForm de entidad
							Ad_rsDSPFIL_service
									.executeReservation($scope.actionForm,caso)
									.then(
										function(response) {

											if (response.data.rc === 'OK') {
												$mdToast.showSimple( "Reserva ejecutada!!" );
												// $('.modal-backdrop').remove();
												// $state.reload();
												$scope.goRow( $scope.actionForm.filaInicioGrid );

												$('#ad_rsDSPFIL_CONFIRM_modal').modal('hide');
												
											} else {
												app_services.errorComun(response.data.text);
											}
		
										},
										function(response) {
											console.error("Ha sucedido un error: " + response.statusText);
										});
						}
					}
	/////////////
	/////////////

				} ]);
