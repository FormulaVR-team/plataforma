
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
		'header_controller',
		[
				'$scope',
				'$state',
				'$stateParams',
				'$window',
				'$timeout',
				'validationService',
				'app_services',

				function($scope, $state, $stateParams, $window, $timeout, validationService, app_services) {
					///////////////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////////////

					///////////////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////////////
				} ]);
