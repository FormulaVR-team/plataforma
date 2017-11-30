angular.module('laAplicacion', ['ui.router', 'commonServices_module', 'ngMaterial', 'ngMessages', 'angular-md5', 'ngSanitize', 'chart.js', 'ngSlimScroll'])
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
	$stateProvider.state('rsUsCHGPWD', {
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/us_users/views/rsChangePassword.jsp?version=fake2",
				controller: "userRole_Ctrl"
			}
		}
	})
	.state('rsUsEDTRCD', {
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/us_users/views/rsUsEDTRCD.jsp?version=fake2",
				controller: "rsUsEDTRCD_Ctrl"
			}
		}
	})
	.state('AcDSPFIL', {
		url: "/AcDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnChart": {
				templateUrl: "./ng/_commonTemplates/chart_view.html",
				controller: "chart_controller"
			},
			"rgnZoom": {
				templateUrl: "./ng/ac_activityCockpits/views/AcDSPFIL_view_zoom.jsp?version=fake2",
				controller: "AcDSPFIL_Ctrl_basic"
			},
			"rgnMain": {
				templateUrl: "./ng/ac_activityCockpits/views/AcDSPFIL_view_group10.jsp?version=fake2",
				controller: "AcDSPFIL_Ctrl_group10"
			}
		}
	})
	.state('CpDSPFIL', {
		url: "/CpDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/cp_cockpits/views/CpDSPFIL_view_basic.jsp?version=fake2",
				controller: "CpDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('EsDSPFIL', {
		url: "/EsDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/es_eventSusbscriptions/views/EsDSPFIL_view_basic.jsp?version=fake2",
				controller: "EsDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('EvDSPFIL', {
		url: "/EvDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/ev_events/views/EvDSPFIL_view_basic.jsp?version=fake2",
				controller: "EvDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('LgDSPFIL', {
		url: "/LgDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/lg_logs/views/LgDSPFIL_view_basic.jsp?version=fake2",
				controller: "LgDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('LoDSPFIL', {
		url: "/LoDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/lo_location/views/LoDSPFIL_view_basic.jsp?version=fake2",
				controller: "LoDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('CdDSPFIL', {
		url: "/CdDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/cd_LocationClosedDays/views/CdDSPFIL_view_basic.jsp?version=fake2",
				controller: "CdDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('PaDSPFIL', {
		url: "/PaDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/pa_systemParameters/views/PaDSPFIL_view_basic.jsp?version=fake2",
				controller: "PaDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('PmDSPFIL', {
		url: "/PmDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/pm_promosManuales/views/PmDSPFIL_view_basic.jsp?version=fake2",
				controller: "PmDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('PrDSPFIL', {
		url: "/PrDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/pr_promos/views/PrDSPFIL_view_basic.jsp?version=fake2",
				controller: "PrDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('PsDSPFIL', {
		url: "/PsDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/ps_countries/views/PsDSPFIL_view_basic.jsp?version=fake2",
				controller: "PsDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('PtDSPFIL', {
		url: "/PtDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/pt_products/views/PtDSPFIL_view_basic.jsp?version=fake2",
				controller: "PtDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('PyDSPFIL', {
		url: "/PyDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/py_PayPalTokens/views/PyDSPFIL_view_basic.jsp?version=fake2",
				controller: "PyDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('allRsDSPFIL', {
		url: "/allRsDSPFIL/:panel_add",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/ad_rs_reservations/views/Ad_rsDSPFIL_view_basic.jsp?version=fake2",
				controller: "Ad_rsDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('RsDSPFIL', {
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/rs_reservations/views/RsDSPFIL_view_basic.jsp?version=fake2",
				controller: "RsDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('SgDSPFIL', {
		url: "/SgDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/sg_publicProcesses/views/SgDSPFIL_view_basic.jsp?version=fake2",
				controller: "SgDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('TjDSPFIL', {
		url: "/TjDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/tj_tarjetasPrepago/views/TjDSPFIL_view_basic.jsp?version=fake2",
				controller: "TjDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('TkDSPFIL', {
		url: "/TkDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/tk_tokens/views/TkDSPFIL_view_basic.jsp?version=fake2",
				controller: "TkDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('TsDSPFIL', {
		url: "/TsDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnChart": {
				templateUrl: "./ng/_commonTemplates/chart_view.html",
				controller: "chart_controller"
			},
			"rgnMain": {
				templateUrl: "./ng/ts_timeSlices/views/TsDSPFIL_view_basic.jsp?version=fake2",
				controller: "TsDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('TtDSPFIL', {
		url: "/TtDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/tt_timeTableReference/views/TtDSPFIL_view_basic.jsp?version=fake2",
				controller: "TtDSPFIL_Ctrl_basic"
			}
		}
	})
	.state('UsDSPFIL', {
		url: "/UsDSPFIL",
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
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: "nav_bar_controller"
			},
			"rgnHeader": {
				templateUrl: "./ng/_commonTemplates/header_view.html?version=fake2",
				controller: "header_controller"
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			},
			"rgnMain": {
				templateUrl: "./ng/us_users/views/UsDSPFIL_view_basic.jsp?version=fake2",
				controller: "UsDSPFIL_Ctrl_basic"
			}
		}
	})
	.state("signoff", {
		url: "/",
		views: {
			"rgnNavBar": {
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: function ($injector, $location) { window.location = "Off"; }
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			}
		}
	})
	.state("defaultState", {
		url: "/",
		views: {
			"rgnNavBar": {
				templateUrl: "./ng/_commonTemplates/nav_bar_view.html?version=fake2",
				controller: function ($injector, $location) {
					var $state = $injector.get('$state');
					$state.go('TtDSPFIL');
				}
			},
			"rgnFooter": {
				templateUrl: "./ng/_commonTemplates/footer.html?version=fake2"
			}
		}
	});
});
