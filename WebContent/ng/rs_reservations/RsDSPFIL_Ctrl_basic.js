
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
		'RsDSPFIL_Ctrl_basic',
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
				'RsDSPFIL_service',

				function($scope, $state, $stateParams, $window, $timeout, $mdToast, $filter, validationService, app_services, RsDSPFIL_service) {
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
						    , rs_filtro : {
								  rs_sincro: "" // sincro
								, rs_mark: "" // mark
								, rs_is_deleted: "" // is_deleted
								, rs_author: "" // author
								, rs_reservation_id: "" // reservation_id
								, rs_location_id: "" // location_id
								, rs_LO_name: "" // LO_name
								, rs_user_id: "" // user_id
								, rs_US_country_id: "" // US_country_id
								, rs_US_nick: "" // US_nick
								, rs_US_avatar: "" // US_avatar
								, rs_US_is_admin: "" // US_is_admin
								, rs_US_first_name: "" // US_first_name
								, rs_US_last_name: "" // US_last_name
								, rs_product_id: "" // product_id
								, rs_PT_name: "" // PT_name
								, rs_PT_deadline: "" // PT_deadline
								, rs_PT_isPercent: "" // PT_isPercent
								, rs_PT_amount: "" // PT_amount
								, rs_PT_duration_minutes: "" // PT_duration_minutes
								, rs_quantity: "" // quantity
								, rs_product_id2: "" // product_id2
								, rs_PT_name2: "" // PT_name2
								, rs_PT_deadline2: "" // PT_deadline2
								, rs_PT_isPercent2: "" // PT_isPercent2
								, rs_PT_amount2: "" // PT_amount2
								, rs_product_id3: "" // product_id3
								, rs_PT_name3: "" // PT_name3
								, rs_PT_deadline3: "" // PT_deadline3
								, rs_PT_isPercent3: "" // PT_isPercent3
								, rs_PT_amount3: "" // PT_amount3
								, rs_amount: "" // amount
								, rs_currency: "€" // currency
								, rs_phone: "" // phone
								, rs_pay_status: "" // pay_status
								, rs_start_date: "" // start_date
								, rs_start_time: "" // start_time
								, rs_duration_minutes: "" // duration_minutes
								, rs_places: "" // places
								, rs_coupon_id: "" // coupon_id
								, rs_executed_at: "" // executed_at
								, rs_note: "" // note
								, rs_comment: "" // comment
								, rs_json: "" // json						
							}
						    // Datos calculados, no de BD:
						    // Formato de registro:
								, rs_sincro: "" // sincro
								, rs_mark: "" // mark
								, rs_is_deleted: "" // is_deleted
								, rs_author: "" // author
								, rs_reservation_id: "" // reservation_id
								, rs_location_id: "" // location_id
								, rs_LO_name: "" // LO_name
								, rs_user_id: "" // user_id
								, rs_US_country_id: "" // US_country_id
								, rs_US_nick: "" // US_nick
								, rs_US_avatar: "" // US_avatar
								, rs_US_is_admin: "" // US_is_admin
								, rs_US_first_name: "" // US_first_name
								, rs_US_last_name: "" // US_last_name
								, rs_product_id: "" // product_id
								, rs_PT_name: "" // PT_name
								, rs_PT_deadline: "" // PT_deadline
								, rs_PT_isPercent: "" // PT_isPercent
								, rs_PT_amount: "" // PT_amount
								, rs_PT_duration_minutes: "" // PT_duration_minutes
								, rs_quantity: "" // quantity
								, rs_product_id2: "" // product_id2
								, rs_PT_name2: "" // PT_name2
								, rs_PT_deadline2: "" // PT_deadline2
								, rs_PT_isPercent2: "" // PT_isPercent2
								, rs_PT_amount2: "" // PT_amount2
								, rs_product_id3: "" // product_id3
								, rs_PT_name3: "" // PT_name3
								, rs_PT_deadline3: "" // PT_deadline3
								, rs_PT_isPercent3: "" // PT_isPercent3
								, rs_PT_amount3: "" // PT_amount3
								, rs_amount: "" // amount
								, rs_currency: "" // currency
								, rs_phone: "" // phone
								, rs_pay_status: "" // pay_status
								, rs_start_date: "" // start_date
								, rs_start_time: "" // start_time
								, rs_duration_minutes: "" // duration_minutes
								, rs_places: "" // places
								, rs_coupon_id: "" // coupon_id
								, rs_executed_at: "" // executed_at
								, rs_note: "" // note
								, rs_comment: "" // comment
								, rs_json: "" // json						
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
					$scope.lst_tt = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];
					$scope.lst_pt = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];
					$scope.lst_cp = null; // [1,2,3,4,5,6];	// Solo VALORES!!!
					$scope.lst_cd = null; // ["2017-08-07","2017-08-08"];	// Solo VALORES!!!
					$scope.lst_cd_weekly = null;	// "0..3456" Días de la semana abiertos
					$scope.lst_pay_status = ["","TPV_OK","PAYPAL_OK"];
					$scope.lst_durations = [10,20,30];

					$scope.aux_FLT_rs_location_id = "";	// Panel de filtros
//					$scope.aux_FLT_rs_start_date = new Date();	// Panel de filtros

					$scope.aux_rs_location_id = "";
					$scope.aux_rs_product_id = "";
					$scope.aux_rs_start_time = "";
					$scope.aux_rs_start_date			= new Date();		// Inicializar el objeto DatePicker...para que no se queje del tipo de dato.
					$scope.aux_rs_start_date_minDate	= new Date();
					$scope.aux_rs_start_date_maxDate	= new Date(); $scope.aux_rs_start_date_maxDate.setDate($scope.aux_rs_start_date_minDate.getDate()+60 );
					$scope.aux_rs_start_date_filterFnc	= null;
					
//					$scope.showPayments = false;
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
					$scope.actionForm.rs_filtro = angular.fromJson( $window.sessionStorage.getItem("RsDSPFIL.rs_filtro") );
					if ( null != $scope.actionForm.cp_filtro ) {
					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_FLT_rs_location_id = {value: $scope.actionForm.rs_filtro.rs_location_id, displayName: ""};
//						if ( $scope.actionForm.rs_filtro.rs_start_date != undefined && $scope.actionForm.rs_filtro.rs_start_date != null && $scope.actionForm.rs_filtro.rs_start_date != "" ) {
//							$scope.aux_FLT_rs_start_date = new Date( $scope.actionForm.rs_filtro.rs_start_date );
//						}
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
						scope.aux_rs_start_date = new Date( scope.actionForm.rs_start_date );
//						if ( $scope.actionForm.rs_filtro.rs_start_date != undefined && $scope.actionForm.rs_filtro.rs_start_date != null && $scope.actionForm.rs_filtro.rs_start_date != "" ) {
//							$scope.aux_FLT_rs_start_date = new Date( $scope.actionForm.rs_filtro.rs_start_date );
//						}

						// Control del paginado:
						scope.txtHtmlPaginador = 
							new paginador( model.filasTotales, model.filasGrid, model.filaInicioGrid )
								.getPaginado_innerHTML(); 
					};
					function sincro_ocupacion() {
						/////
						$scope.lst_ocupacion = "";
						$scope.actionForm.rs_location_id = $scope.aux_rs_location_id.value;
						$scope.actionForm.rs_LO_name = $scope.aux_rs_location_id.displayName;
						$scope.actionForm.rs_start_date = $filter('date')($scope.aux_rs_start_date, 'yyyy-MM-dd');
						if ( '' !==  $scope.actionForm.rs_location_id ) {
							app_services.rtvOcu( $scope.actionForm.rs_location_id, $scope.actionForm.rs_start_date, null )
							.then( 
								function(response) {
									if (response.rc === 'OK') { 
										$scope.lst_ocupacion = response.text.ocupacion;
									} else { 
										app_services.errorComun( "ERROR: " + response.text); }
									}
								,function(response) { 
									console.error("Ha sucedido un error: " + response.statusText); 
									}
								);
						}
						/////
						// Refrescar los modos de pago del location_id:
						$scope.getPaymentMethods();
						/////
					}
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
					
					}

					///////////////////////////////////////////////////////////////////////
					// Inicializar pantalla:
					RsDSPFIL_service
						.reload($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
								} else {
									app_services.errorComun( response.data.text );
								}

							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});

				    // Combos y auxiliares para componentes de presentación:
					app_services.lo_lst().then( function(response) {if (response.rc === 'OK') { $scope.lst_lo = response.text;} else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
					app_services.pt_lst($scope.actionForm.logon_USR,$scope.actionForm.logon_HSH).then( function(response) {if (response.rc === 'OK') { $scope.lst_pt = response.text;} else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });

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
					    $scope.actionForm.rs_filtro.rs_location_id = $scope.aux_FLT_rs_location_id.value;
//					    $scope.actionForm.rs_filtro.rs_start_date = $filter('date')($scope.aux_FLT_rs_start_date, 'yyyy-MM-dd');

						RsDSPFIL_service
						.filtrar($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
								} else {
									app_services.errorComun( response.data.text );
								}

							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});
					};

					$scope.exportar = function() {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
					    $scope.actionForm.filasGrid = $scope.aux_filasGrid.value;
						RsDSPFIL_service
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
						RsDSPFIL_service
						.avPg($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
								} else {
									app_services.errorComun( response.data.text );
								}

							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});
					};

					$scope.rtPg = function() {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
						RsDSPFIL_service
						.rtPg($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
								} else {
									app_services.errorComun( response.data.text );
								}

							},
							function(response) {
								console.error("Ha sucedido un error: " + response.statusText);
							});
					};

					$scope.goRow = function( nFila ) {
						$scope.actionForm.clavesMarcadas.length = 0; $scope.actionForm.filasMarcadas.length = 0;	// Borrar los selectores de fila.
						$scope.actionForm.filaInicioGrid = nFila;
						RsDSPFIL_service
						.reload($scope.actionForm)
						.then(
							function(response) {

								if (response.data.rc === 'OK') {
									var modelo = response.data.text;
									moveModelToView( $scope, modelo );
								} else {
									app_services.errorComun( response.data.text );
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
						$scope.actionForm.rs_sincro = reg.rs_sincro; // sincro
						$scope.actionForm.rs_mark = reg.rs_mark; // mark
						$scope.actionForm.rs_is_deleted = reg.rs_is_deleted; // is_deleted
						$scope.actionForm.rs_author = reg.rs_author; // author
						$scope.actionForm.rs_reservation_id = reg.rs_reservation_id; // reservation_id
						$scope.actionForm.rs_location_id = reg.rs_location_id; // location_id
						$scope.actionForm.rs_LO_name = reg.rs_LO_name; // LO_name
						$scope.actionForm.rs_user_id = reg.rs_user_id; // user_id
						$scope.actionForm.rs_US_country_id = reg.rs_US_country_id; // US_country_id
						$scope.actionForm.rs_US_nick = reg.rs_US_nick; // US_nick
						$scope.actionForm.rs_US_avatar = reg.rs_US_avatar; // US_avatar
						$scope.actionForm.rs_US_is_admin = reg.rs_US_is_admin; // US_is_admin
						$scope.actionForm.rs_US_first_name = reg.rs_US_first_name; // US_first_name
						$scope.actionForm.rs_US_last_name = reg.rs_US_last_name; // US_last_name
						$scope.actionForm.rs_product_id = reg.rs_product_id; // product_id
						$scope.actionForm.rs_PT_name = reg.rs_PT_name; // PT_name
						$scope.actionForm.rs_PT_deadline = reg.rs_PT_deadline; // PT_deadline
						$scope.actionForm.rs_PT_isPercent = reg.rs_PT_isPercent; // PT_isPercent
						$scope.actionForm.rs_PT_amount = reg.rs_PT_amount; // PT_amount
						$scope.actionForm.rs_PT_duration_minutes = reg.rs_PT_duration_minutes; // PT_duration_minutes
						$scope.actionForm.rs_quantity = reg.rs_quantity; // quantity
						$scope.actionForm.rs_product_id2 = reg.rs_product_id2; // product_id2
						$scope.actionForm.rs_PT_name2 = reg.rs_PT_name2; // PT_name2
						$scope.actionForm.rs_PT_deadline2 = reg.rs_PT_deadline2; // PT_deadline2
						$scope.actionForm.rs_PT_isPercent2 = reg.rs_PT_isPercent2; // PT_isPercent2
						$scope.actionForm.rs_PT_amount2 = reg.rs_PT_amount2; // PT_amount2
						$scope.actionForm.rs_product_id3 = reg.rs_product_id3; // product_id3
						$scope.actionForm.rs_PT_name3 = reg.rs_PT_name3; // PT_name3
						$scope.actionForm.rs_PT_deadline3 = reg.rs_PT_deadline3; // PT_deadline3
						$scope.actionForm.rs_PT_isPercent3 = reg.rs_PT_isPercent3; // PT_isPercent3
						$scope.actionForm.rs_PT_amount3 = reg.rs_PT_amount3; // PT_amount3
						$scope.actionForm.rs_amount = reg.rs_amount; // amount
						$scope.actionForm.rs_currency = reg.rs_currency; // currency
						$scope.actionForm.rs_phone = reg.rs_phone; // phone
						$scope.actionForm.rs_pay_status = reg.rs_pay_status; // pay_status
						$scope.actionForm.rs_start_date = reg.rs_start_date; // start_date
						$scope.actionForm.rs_start_time = reg.rs_start_time; // start_time
						$scope.actionForm.rs_duration_minutes = reg.rs_duration_minutes; // duration_minutes
						$scope.actionForm.rs_places = reg.rs_places; // places
						$scope.actionForm.rs_coupon_id = reg.rs_coupon_id; // coupon_id
						$scope.actionForm.rs_note = reg.rs_note; // note
						$scope.actionForm.rs_executed_at = reg.rs_executed_at; // executed_at
						$scope.actionForm.rs_comment = reg.rs_comment; // comment
						$scope.actionForm.rs_json = reg.rs_json; // json						

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_rs_location_id = {value: $scope.actionForm.rs_location_id, 	displayName: $scope.actionForm.rs_LO_name};
						$scope.aux_rs_product_id = {value: $scope.actionForm.rs_product_id, 	displayName: ""};
						$scope.aux_rs_start_time = {value: $scope.actionForm.rs_start_time, 	displayName: ""};

						// Derivados:
						sincro_ocupacion();

					};

					$scope.initKey = function() {
						// Formato de registro:
						$scope.actionForm.rs_reservation_id = ""; // reservation_id						
					};

					$scope.initReg = function() {

						// Inicializar:
//						$scope.showPayments = false;
						var date = new Date();
						$scope.lst_cd = null;
						$scope.lst_cd_weekly = null;

						// Formato de registro:
						$scope.actionForm.rs_sincro = ""; // sincro
						$scope.actionForm.rs_mark = ""; // mark
						$scope.actionForm.rs_is_deleted = ""; // is_deleted
//						$scope.actionForm.rs_author = ""; // author
						$scope.actionForm.rs_reservation_id = ""; // reservation_id
						$scope.actionForm.rs_location_id = ""; // location_id
						$scope.actionForm.rs_LO_name = ""; // LO_name
						$scope.actionForm.rs_user_id = ""; // user_id
						$scope.actionForm.rs_US_country_id = ""; // US_country_id
						$scope.actionForm.rs_US_nick = ""; // US_nick
						$scope.actionForm.rs_US_avatar = ""; // US_avatar
						$scope.actionForm.rs_US_is_admin = ""; // US_is_admin
						$scope.actionForm.rs_US_first_name = ""; // US_first_name
						$scope.actionForm.rs_US_last_name = ""; // US_last_name
						$scope.actionForm.rs_product_id = ""; // product_id
						$scope.actionForm.rs_PT_name = ""; // PT_name
						$scope.actionForm.rs_PT_deadline = ""; // PT_deadline
						$scope.actionForm.rs_PT_isPercent = ""; // PT_isPercent
						$scope.actionForm.rs_PT_amount = ""; // PT_amount
						$scope.actionForm.rs_PT_duration_minutes = ""; // PT_duration_minutes
						$scope.actionForm.rs_quantity = "1"; // quantity
						$scope.actionForm.rs_product_id2 = ""; // product_id2
						$scope.actionForm.rs_PT_name2 = ""; // PT_name2
						$scope.actionForm.rs_PT_deadline2 = ""; // PT_deadline2
						$scope.actionForm.rs_PT_isPercent2 = ""; // PT_isPercent2
						$scope.actionForm.rs_PT_amount2 = ""; // PT_amount2
						$scope.actionForm.rs_product_id3 = ""; // product_id3
						$scope.actionForm.rs_PT_name3 = ""; // PT_name3
						$scope.actionForm.rs_PT_deadline3 = ""; // PT_deadline3
						$scope.actionForm.rs_PT_isPercent3 = ""; // PT_isPercent3
						$scope.actionForm.rs_PT_amount3 = ""; // PT_amount3
						$scope.actionForm.rs_amount = ""; // amount
						$scope.actionForm.rs_currency = "€"; // currency
						$scope.actionForm.rs_phone = ""; // phone
						$scope.actionForm.rs_pay_status = ""; // pay_status
//						$scope.actionForm.rs_start_date = ""; // start_date
						$scope.actionForm.rs_start_time = ""; // start_time
//						$scope.actionForm.rs_duration_minutes = ""; // duration_minutes
//						$scope.actionForm.rs_places = ""; // places
						$scope.actionForm.rs_coupon_id = ""; // coupon_id
						$scope.actionForm.rs_executed_at = ""; // executed_at
						$scope.actionForm.rs_note = ""; // note
						$scope.actionForm.rs_comment = ""; // comment
						$scope.actionForm.rs_json = ""; // json					

						// Inicializar:
						$scope.paymentCheck = { checked: 'TPV' };
						var date = new Date();
						// Only workable:
						var cuentaLimite = 0;
						while ( ! app_services.md_date_filter_onlyWorkable( date, $scope.lst_cd, $scope.lst_cd_weekly ) && cuentaLimite <= 30 ) {
							date.setDate( date.getDate() + 1 );
							cuentaLimite++;
						}
						if ( cuentaLimite > 10 ) { date = new Date(); }
						
						var aaaa = date.getFullYear();
						var mm = ("00"+(1+date.getMonth())).substr(("00"+(1+date.getMonth())).length-2);
						var dd = ("00"+date.getDate()).substr(("00"+date.getDate()).length-2);
						$scope.actionForm.rs_start_date = aaaa+"-"+mm+"-"+dd;
						$scope.actionForm.rs_duration_minutes = 10;
						$scope.actionForm.rs_places = 1; // places

					    // Combos y auxiliares para componentes de presentación:
						$scope.aux_rs_location_id	= {value: "", displayName: ""};
						$scope.aux_rs_product_id	= {value: "", displayName: ""};
						$scope.aux_rs_start_time	= {value: "", displayName: ""};
						$scope.aux_rs_start_date 	= new Date( $scope.actionForm.rs_start_date );
						
						// Precarga del tfno si lo tiene registrado el usuario:
						app_services
							.rtvUsData( $scope.actionForm.logon_USR, $scope.actionForm.logon_HSH )
							.then(
									function(response) {
										if (response.rc === 'OK') { 
											$scope.actionForm.rs_phone = response.text.us_phone; 
											$scope.actionForm.rs_US_nick = response.text.us_nick; 
											}
									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});
						
					};

	/////////////
	/////////////
	// Panel empotrado de "ADDRCD" (por si no se quiere tener esta función en una vista independiente)
					$scope.agregar_PAYPAL = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

						if ( ! confirmar('¿Pagar la reserva?',this) ) { return; }

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.rs_location_id = $scope.aux_rs_location_id.value;
						$scope.actionForm.rs_LO_name = $scope.aux_rs_location_id.displayName;
						$scope.actionForm.rs_product_id = $scope.aux_rs_product_id.value;
						$scope.actionForm.rs_start_date = $filter('date')($scope.aux_rs_start_date, 'yyyy-MM-dd');
						$scope.actionForm.rs_start_time = $scope.aux_rs_start_time.value;

						//llamo a su servicio pasandole el actionForm de entidad
						RsDSPFIL_service
								.add_paypal($scope.actionForm)
								.then(
									function(response) {
	
										if (response.data.rc === 'OK') {
//											$mdToast.showSimple( "Registro agregado" );
//											$('.modal-backdrop').remove();
//											$state.reload();
											/////////////
											// Paypal:
											window.location = response.data.text.rs_note; 
											/////////////
										} else {
											app_services.errorComun( response.data.text );
										}
	
									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});
	
//						}
					};
					$scope.agregar_TPV = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

						if ( ! confirmar('¿Pagar la reserva?',this) ) { return; }

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.rs_location_id = $scope.aux_rs_location_id.value;
						$scope.actionForm.rs_LO_name = $scope.aux_rs_location_id.displayName;
						$scope.actionForm.rs_product_id = $scope.aux_rs_product_id.value;
						$scope.actionForm.rs_start_date = $filter('date')($scope.aux_rs_start_date, 'yyyy-MM-dd');
						$scope.actionForm.rs_start_time = $scope.aux_rs_start_time.value;

						//llamo a su servicio pasandole el actionForm de entidad
						RsDSPFIL_service
								.add_tpv($scope.actionForm)
								.then(
									function(response) {
	
										if (response.data.rc === 'OK') {
//											$mdToast.showSimple( "Registro agregado" );
//											$('.modal-backdrop').remove();
//											$state.reload();
											/////////////
											// TPV virtual:
											tpv_submitForm( response.data.text );
											/////////////
										} else {
											app_services.errorComun( response.data.text );
										}
	
									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});
	
//						}
					};
					$scope.agregar_CASH = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {
						
						if ( ! confirmar('¿Crear la reserva?',this) ) { return; }

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.rs_location_id = $scope.aux_rs_location_id.value;
						$scope.actionForm.rs_LO_name = $scope.aux_rs_location_id.displayName;
						$scope.actionForm.rs_product_id = $scope.aux_rs_product_id.value;
						$scope.actionForm.rs_start_date = $filter('date')($scope.aux_rs_start_date, 'yyyy-MM-dd');
						$scope.actionForm.rs_start_time = $scope.aux_rs_start_time.value;

						//llamo a su servicio pasandole el actionForm de entidad
						RsDSPFIL_service
								.add_cash($scope.actionForm)
								.then(
									function(response) {
	
										if (response.data.rc === 'OK') {
											$("#rsDSPFIL_ADDRCD_modal").modal("hide");
											$scope.filtrar();
										} else {
											app_services.errorComun( response.data.text );
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
						$('#rsDSPFIL_EDTRCD_modal').modal('hide');
						$('#rsDSPFIL_ADDRCD_modal').modal('show');
					};
					$scope.cambiar = function() {
						
//						if (validationService.validarEmail($scope.actionForm.logon_USR)) {

					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.rs_location_id = $scope.aux_rs_location_id.value;
						$scope.actionForm.rs_LO_name = $scope.aux_rs_location_id.displayName;
						$scope.actionForm.rs_product_id = $scope.aux_rs_product_id.value;
						$scope.actionForm.rs_start_date = $filter('date')($scope.aux_rs_start_date, 'yyyy-MM-dd');
						$scope.actionForm.rs_start_time = $scope.aux_rs_start_time.value;

						//llamo a su servicio pasandole el actionForm de entidad
						RsDSPFIL_service
								.chg($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#rsDSPFIL_EDTRCD_modal').modal('hide');
											$mdToast.showSimple( "Registro cambiado" );
											// $('.modal-backdrop').remove();
											// $state.reload();
											$scope.goRow( $scope.actionForm.filaInicioGrid );
										} else {
											app_services.errorComun( response.data.text );
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
						RsDSPFIL_service
								.dlt($scope.actionForm)
								.then(
									function(response) {

										if (response.data.rc === 'OK') {
											$('#rsDSPFIL_EDTRCD_modal').modal('hide');
											$mdToast.showSimple( "Registro suprimido" );
											// $('.modal-backdrop').remove();
											// $state.reload();
											$scope.goRow( $scope.actionForm.filaInicioGrid );
										} else {
											app_services.errorComun( response.data.text );
										}
	
									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});

//						}
					};
					$scope.payment = function(){
						if ($scope.paymentCheck.checked === 'TPV') {
							$scope.agregar_TPV();
						} else if ($scope.paymentCheck.checked === 'PAYPAL') {
							$scope.agregar_PAYPAL();
						} else if ($scope.paymentCheck.checked === 'CASH') {
							$scope.agregar_CASH();
						}
						
						$('#contentReservar').slideToggle(400);
						$('#contentPayment').slideToggle(400);
					}
					$scope.revisarReserva = function(){
						$('#contentReservar').slideToggle(400);
						$('#contentPayment').slideToggle(400);
					}
					$scope.check = function(isOnlyForValoration) {

						isOnlyForValoration = typeof isOnlyForValoration !== 'undefined' ? isOnlyForValoration : false;
					    // Combos y auxiliares para componentes de presentación:
						// Recoger valores de los combos:
						$scope.actionForm.rs_location_id = $scope.aux_rs_location_id.value;
						$scope.actionForm.rs_LO_name = $scope.aux_rs_location_id.displayName;
						$scope.actionForm.rs_product_id = $scope.aux_rs_product_id.value;
						$scope.actionForm.rs_start_date = $filter('date')($scope.aux_rs_start_date, 'yyyy-MM-dd');
						$scope.actionForm.rs_start_time = $scope.aux_rs_start_time.value;
						
						// Anular campos autocompletados por el servidor:
						$scope.actionForm.rs_product_id2 = "";	// Promo automática
						$scope.actionForm.rs_product_id3 = "";	// Promo manual derivada de un cupón

						//llamo a su servicio pasandole el actionForm de entidad
						RsDSPFIL_service
								.addCheck($scope.actionForm)
								.then(
									function(response) {

//										$scope.showPayments = false;

										if (response.data.rc === 'OK') {

											$scope.putRecordAsTheCurrent( response.data.text );

											/////////////////////////
											// NOTAS PARA QUE LAS VEA EL USUARIO:
											// (ESPECIAL: SI LE SALE GRATIS YA LA HABRÁ GENERADO EL SERVIDOR)
											if ( "" != $scope.actionForm.rs_comment ) {
												if ( $scope.actionForm.rs_comment.toUpperCase().includes( 'GRATIS' ) ) {
													// SI LE SALE GRATIS YA LA HABRÁ CREADO EL SERVIDOR. SALIR DE AQUI!!!
													app_services.showAlert($scope.actionForm.rs_comment, null, null);
													$("#rsDSPFIL_ADDRCD_modal").modal("hide");
													$scope.filtrar();	// SALIDA DEL PANEL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
												} else {
													app_services.showAlert($scope.actionForm.rs_comment, null, null);
												}
											}
											/////////////////////////

											if (!isOnlyForValoration) {
//												if ( "" == $scope.actionForm.rs_comment ) {
														// a ver que coño hago aqui
													$('#contentReservar').slideToggle(400);
													$('#contentPayment').slideToggle(400);
//												}
											}

										} else {
//											if (!isOnlyForValoration) {
												app_services.showAlert(response.data.text, 'Por favor, revise los campos del formulario', 'OK' );
												//app_services.errorComun( response.data.text );
//											}
										}
	
									},
									function(response) {
										console.error("Ha sucedido un error: " + response.statusText);
									});

//						}
					}


					//////////////
					// Eventos de "location_id":
					$scope.location_id_onOpen = function() {
						$scope.lst_lo = null;
						app_services.lo_lst().then( function(response) {if (response.rc === 'OK') { $scope.lst_lo = response.text;} else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
					}
					$scope.location_id_onClose = function() {
						$scope.aux_rs_start_time = {value: "", displayName: ""};
						var location_id = $scope.aux_rs_location_id.value; 
						if ( location_id != undefined && location_id.length  > 0) {
						    // Combos dependientes de cambio en location:
							app_services.tt_lst(location_id,"NORMAL","N").then( function(response) { if (response.rc === 'OK') { $scope.lst_tt = response.text; } else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
							app_services.cp_lst(location_id,"N").then( function(response) { if (response.rc === 'OK') { $scope.lst_cp = response.text; } else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
							app_services.cd_lst_weekly(location_id).then( function(response) { if (response.rc === 'OK') { $scope.lst_cd_weekly = response.text; } else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
							app_services.cd_lst(location_id).then( 
									function(response) { 
										if (response.rc === 'OK') { 
											$scope.lst_cd = response.text; 
											
											///////////////
											app_services.getRsFecMin(location_id).then( function(response) {
												if (response.rc === 'OK') {
													var aaaa = response.text.substring(0,4); 
													var   mm = response.text.substring(5,7); 
													var   dd = response.text.substring(8); 
													var minFec = new Date( response.text ); // minFec.setFullYear(aaaa); minFec.setMonth(mm); minFec.setDate(dd);
													$scope.aux_rs_start_date			= new Date( minFec.getTime() ); //$scope.aux_rs_start_date.setDate( minFec.getDate() );
													$scope.aux_rs_start_date_minDate	= new Date( minFec.getTime() ); //$scope.aux_rs_start_date_minDate.setDate( minFec.getDate() );
													$scope.aux_rs_start_date_maxDate	= new Date( minFec.getTime() ); $scope.aux_rs_start_date_maxDate.setDate( $scope.aux_rs_start_date_maxDate.getDate() + 60 );
													$scope.aux_rs_start_date_filterFnc	= function(date) { return app_services.md_date_filter_onlyWorkable( date, $scope.lst_cd, $scope.lst_cd_weekly ); }
												} else { app_services.errorComun( "ERROR: " + response.text); }},function(response) { console.error("Ha sucedido un error: " + response.statusText); });
											///////////////

										} else { 
											app_services.errorComun( "ERROR: " + response.text); 
										}
									}
									,function(response) { console.error("Ha sucedido un error: " + response.statusText); });
						}
						sincro_ocupacion();
					}
					// Eventos de "start_date":
					$scope.start_date_onChange = function() {
						sincro_ocupacion();
					}
					// Eventos for show price
					$scope.getprice = function() {
						if ($scope.aux_rs_location_id.value !== ""
							&& $scope.aux_rs_product_id.value !== ""
							&& $scope.aux_rs_start_date !== ""
							&& $scope.aux_rs_start_time.value !== "") {
								$scope.check(true);
						} 
					}
					//////////////

					/////////////
					// Llamada al módulo de gamificación:
					$scope.gamingModule = function() {
						app_services.gamingModule(  $scope.actionForm.logon_USR, $scope.actionForm.logon_HSH  )
							.then( function(response) { 
										if (response.rc === 'OK') { 
											console.log( JSON.stringify( response.text ) );

	//////////
	// Submitir formulario de entrada a módulo de gamificación:
	var f = document.createElement("FORM");
	f.enctype = "application/x-www-form-urlencoded";
	f.method = "post";
	f.acceptCharset = "UTF-8";
	f.action = response.text.url_redirect;
	f.setAttribute("id", "gmFormId");
	document.body.appendChild(f);
	
	var x = document.createElement("INPUT");
	x.setAttribute("type", "hidden");
	x.setAttribute("name", "gm_user_id");
	x.setAttribute("value", response.text.user_id);
	document.getElementById("gmFormId").appendChild(x);
	
	var y = document.createElement("INPUT");
	y.setAttribute("type", "hidden");
	y.setAttribute("name", "gm_hash_code");
	y.setAttribute("value", response.text.hash_code);
	document.getElementById("gmFormId").appendChild(y);
	
	var z = document.createElement("INPUT");
	z.setAttribute("type", "hidden");
	z.setAttribute("name", "gm_data");
	z.setAttribute("value", JSON.stringify(response.text.data) );
	document.getElementById("gmFormId").appendChild(z);
	
	f.submit();
	//////////
										
										} else { 
											app_services.errorComun( "ERROR: " + response.text); }
										}
										,function(response) { 
											console.error("Ha sucedido un error: " + response.statusText); 
										}
								);
					}
					//////////////
					$scope.getPaymentMethods = function() {

						if ( $scope.actionForm.rs_location_id == undefined || $scope.actionForm.rs_location_id.trim() === '' ) {
							return;
						}

						app_services.getPaymentMethods(  $scope.actionForm.logon_USR, $scope.actionForm.logon_HSH, $scope.actionForm.rs_location_id  )
							.then( function(response) { 
										if (response.rc === 'OK') { 
											if ( response.text.includes("TPV") ) { $("#radio_TPV").show(); } else { $("#radio_TPV").hide(); }
											if ( response.text.includes("PAYPAL") ) { $("#radio_PAYPAL").show(); } else { $("#radio_PAYPAL").hide(); }
										} else { 
											app_services.errorComun( "ERROR: " + response.text); }
										}
										,function(response) { 
											console.error("Ha sucedido un error: " + response.statusText); 
										}
								);
					}

					//////////////
					$scope.go_misEventos = function() {
						$state.go( "EsADDRCD" );	
					}

/////////////
/////////////

					// Esto se pone aqui para evitar problemas de "forward references":
					if ( $stateParams.panel_add === 'panel_add' ) {
						if ( (""+window.location).includes( "CHGPWD_MAIL_SENDED=true" ) ) {
							app_services.errorComun( "<h3>Revisa tu correo para obtener una contraseña</h3><br/>" + $scope.actionForm.logon_USR );
						}
						$scope.initReg();
						$('#rsDSPFIL_ADDRCD_modal').modal("show");
					}


				} ]);
