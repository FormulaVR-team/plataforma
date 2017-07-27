
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
		'chart_controller',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				"$filter",
				'validationService',
				'app_services',
				function($scope, $state, $stateParams, $window, $timeout, $filter, validationService, app_services) {
					
//					var obj = $scope.$parent.varGlobal; alert( JSON.stringify(obj) );
					
					// Campos de pantalla:
					$scope.location_id;
					$scope.start_date;
					$scope.start_time;

					/////
					// Combos y auxiliares para componentes de presentación:
					$scope.lst_lo = null; // [ {value: "10", displayName: 'Diez'}, {value: "20", displayName: 'Veinte'} ];
					$scope.aux_rs_location_id = {value: "CENTRAL", displayName: 'La centralita'};
					$scope.aux_rs_start_date = new Date();

					// Combos y auxiliares para componentes de presentación:
					app_services.lo_lst().then(function(response) {if (response.rc === 'OK') {$scope.lst_lo = response.text;} else {alert( "ERROR: " + response.text); } },function(response) {console.error("Ha sucedido un error: " + response.statusText);});
					
					///////////////////////////////////////////////////////////////////////
					// Funciones internas:

					function load_chart( lst_ocupacion, lst_horario ) {
						var cols = lst_horario.registros.length, reservas = [], plazas = [], columnas = [];
						for ( var col = 0; col < cols; col++ ) {
							
if ( lst_horario.registros[col].tt_start_time >= "0900" && lst_horario.registros[col].tt_start_time <= "2200" ) {

								////////////
								columnas[ columnas.length ] = lst_horario.registros[col].tt_start_time; // padLeft(col,2);
								var isDistintoDeCero = false;
								var nReservas = 0, nPlazas = 0;
								for ( var i = 0; i< lst_ocupacion.registros.length; i++ ) {
									if ( lst_ocupacion.registros[i].ts_start_time === columnas[ columnas.length-1 ]  ) {
										isDistintoDeCero = true;
										nReservas += parseInt( lst_ocupacion.registros[i].ts_RS_quantity );
										nPlazas   += parseInt( lst_ocupacion.registros[i].ts_RS_places );
									}
								}
								// Valor de cada columna:
								if ( isDistintoDeCero  ) {
									reservas[ reservas.length ] = nReservas;
									plazas[ plazas.length ] = nPlazas;
								} else {
									reservas[ reservas.length ] = 0;
									plazas[ plazas.length ] = 0;
								}
								////////////

}

						}
						$scope.labels = columnas;
						$scope.series = ['Reservas','Plazas'];
						$scope.data = [reservas,plazas];
						
						///////////////////////////////////////////////////////////////////////
						  //$scope.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
						  //$scope.series = ['Plazas','Reservas'];
						  //$scope.data = [
						  //  [65, 59, 80, 81, 56, 55, 40],
						  //  [28, 48, 40, 19, 86, 27, 190]
						  //];				
						///////////////////////////////////////////////////////////////////////
					}

					function sincro_ocupacion() {
						/////
						$scope.location_id = $scope.aux_rs_location_id.value;
						$scope.start_date = $filter('date')($scope.aux_rs_start_date, 'yyyy-MM-dd');
						if ( '' !==  $scope.location_id ) {
							app_services.rtvOcu( $scope.location_id, $scope.start_date, null )
							.then( 
								function(response) {
									if (response.rc === 'OK') { 
										load_chart( response.text.ocupacion, response.text.horario );
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
					sincro_ocupacion();

					///////////////////////////////////////////////////////////////////////

					$scope.filtrar = function() {
						sincro_ocupacion();
					}

					///////////////////////////////////////////////////////////////////////
				} ]);
