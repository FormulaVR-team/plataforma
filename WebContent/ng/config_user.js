angular.module('laAplicacion', ['ui.router', 'commonServices_module', 'ngMaterial', 'ngMessages', 'angular-md5', 'ngSanitize', 'ngSlimScroll'])
.config(function ($stateProvider, $urlRouterProvider, $mdThemingProvider, $mdDateLocaleProvider) {
	var fvrRedMap = $mdThemingProvider.extendPalette('red', {
		 	'500': '#CE1417',
		 	'contrastDefaultColor': 'light'
	 	});

	// Register the new color palette map with the name <code>neonRed</code>
	$mdThemingProvider.definePalette('fvrRed', fvrRedMap);

// Available palettes: red, pink, purple, deep-purple, indigo, blue, light-blue, cyan, teal, green, light-green, lime, yellow, amber, orange, deep-orange, brown, grey, blue-grey
$mdThemingProvider
	.theme('default')
	.primaryPalette('fvrRed',{'default': '500'})
	.accentPalette('blue-grey',{'default': '900'})
	.warnPalette('orange',{'default': '500'})
	.backgroundPalette('grey')
	;
	$mdDateLocaleProvider.firstDayOfWeek = 1;
	$mdDateLocaleProvider.months = ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre'];
	$mdDateLocaleProvider.shortMonths = ['ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul', 'ago', 'sep', 'oct', 'nov', 'dic'];
	$mdDateLocaleProvider.days = ['domingo', 'lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado', 'domingo'];
	$mdDateLocaleProvider.shortDays = ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'];
	$mdDateLocaleProvider.formatDate = function (date) {
		if (date === undefined) {
			return "";
		}
		var dd = date.getDate();
		var mm = 1 + date.getMonth();
		var aaaa = date.getFullYear();
		var s = "0" + dd;
		dd = s.substr(s.length - 2);
		s = "0" + mm;
		mm = s.substr(s.length - 2);
		return dd + '/' + mm + '/' + aaaa;
	};
	$urlRouterProvider.otherwise(function ($injector, $location) {
		var $state = $injector.get('$state');
		$state.go('defaultState');
	});
	$stateProvider
	.state('rsUsCHGPWD', {
		url: "/rsUsCHGPWD/:token_id/:user_id",
		params: {
			token_id: {
				value: null
			},
			user_id: {
				value: null
			}
		},
		resolve: {
			greeting: function ($q, $timeout) {}
		},
		views: {
			"rgnNavBar": {
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake4",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake4",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake4"
			},
			"rgnMain": {
				templateUrl: "./ng/us_users/views/rsChangePassword.jsp?version=fake4",
				controller: "userRole_Ctrl"
			}
		}
	})
	.state('rsUsADDRCD', {
		url: "/rsUsADDRCD/:token_id/:user_id",
		params: {
			token_id: {
				value: null
			},
			user_id: {
				value: null
			}
		},
		resolve: {
			greeting: function ($q, $timeout) {}
		},
		views: {
			"rgnNavBar": {
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake4",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake4",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake4"
			},
			"rgnMain": {
				templateUrl: "./ng/us_users/views/rsRegistration.jsp?version=fake4",
				controller: "userRole_Ctrl"
			}
		}
	}).state('rsUsEDTRCD', {
		url: "/rsUsEDTRCD",
		params: {
			logon_USR: {
				value: null
			},
			logon_HSH: {
				value: null
			}
		},
		resolve: {
			greeting: function ($q, $timeout) {}
		},
		views: {
			"rgnNavBar": {
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake4",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake4",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake4"
			},
			"rgnMain": {
				templateUrl: "./ng/us_users/views/rsUsEDTRCD.jsp?version=fake4",
				controller: "rsUsEDTRCD_Ctrl"
			}
		}
	}).state('RsDSPFIL', {
		url: "/RsDSPFIL/:panel_add",
		params: {
			panel_add: {
				value: null
			}
		},
		resolve: {
			greeting: function ($q, $timeout) {}
		},
		views: {
			"rgnNavBar": {
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake4",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake4",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake4"
			},
			"rgnMain": {
				templateUrl: "./ng/rs_reservations/views/RsDSPFIL_view_basic.jsp?version=fake4",
				controller: "RsDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('EsADDRCD', {
		url: "/EsADDRCD/:panel_add",
		params: {
			logon_USR: {
				value: null
			},
			logon_HSH: {
				value: null
			},
			panel_add: {
				value: null
			}
		},
		resolve: {
			greeting: function ($q, $timeout) {}
		},
		views: {
			"rgnNavBar": {
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake4",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake4",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake4"
			},
			"rgnMain": {
				templateUrl: "./ng/es_eventSusbscriptions/views/EsADDRCD_view_CONTAINER.jsp?version=fake4",
				controller: "EsDSPFIL_Ctrl_basic"
			}
		}
	})
	.state("signoff", {
		url: "/",
		views: {
			"rgnNavBar": {
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake4",
				controller: function ($injector, $location) { window.location = "Off"; }
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake4"
			}
		}
	})
	.state("defaultState", {
		url: "/",
		views: {
			"rgnMain": {
				template: "defaultState...",
				controller: function ($injector) {
					window.location = "./";
				}
			}
		}
	});
});
