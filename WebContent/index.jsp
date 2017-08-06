<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="laAplicacion" ng-cloak="">
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="icon" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-32x32.png" sizes="32x32" />
<link rel="icon" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-192x192.png" sizes="192x192" />
<link rel="apple-touch-icon-precomposed" href="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-180x180.png" />
<meta name="msapplication-TileImage" content="https://www.formulavr.net/wp-content/uploads/2017/06/cropped-formulvr-ico-270x270.png" />

	<title>${cfgPantalla.tituloPantalla}</title>

	<!-- bootstrap -->
 	<script src="./ng/_lib/jquery-1.11.3/jquery.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/bootstrap-3.3.6/js/bootstrap.min.js?fvrVer=${miVersion}"></script>

	<script src="./ng/_custom/pagination.js?fvrVer=${miVersion}"></script>
	<script type="text/javascript" src="./script/rutinas.js?fvrVer=${miVersion}"></script>
	<script type="text/javascript" src="./script/img2D.js?fvrVer=${miVersion}"></script>

	<link id="" href="./ng/_lib//bootstrap-3.3.6/css/bootstrap.min.css?fvrVer=${miVersion}" rel="stylesheet" />
    <link href="./resBS/css/styles.css?fvrVer=${miVersion}" rel="stylesheet" />

    <!-- ==================================== -->
    <!-- Angular -->    
    <script src="./ng/_lib/ng/angular.min.js?fvrVer=${miVersion}"></script>
    <script src="./ng/_lib/ng/angular-ui-router.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-animate.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-aria.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-messages.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-md5.js?fvrVer=${miVersion}"></script>
	
	<script src="./ng/_lib/ng/i18n/angular-locale_es-es.min.js"></script>
	
	<!-- Angular Material Library -->
	<script src="./ng/_lib/ng/angular-material.min.js?fvrVer=${miVersion}"></script>
	<!-- Angular Material style sheet -->
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="./ng/_lib/ng/css/angular-material.min.css?fvrVer=${miVersion}" />
	<link rel="stylesheet" href="./ng/_lib/ng/css/angular-material.CUSTOM-FVR.css?fvrVer=${miVersion}" />
	<link rel="stylesheet" href="./ng/_lib/ng/css/icon.css?family=Material+Icons" />
    <!-- ==================================== -->

    <!-- ==================================== -->
    <!-- servicios comunes -->
	<script src="ng/_commonServices/commonServices_module.js?fvrVer=${miVersion}"></script>
	<script src="ng/_commonServices/app_service.js?fvrVer=${miVersion}"></script>
	<script src="ng/_commonServices/validation_service.js?fvrVer=${miVersion}"></script>

    <!-- ==================================== -->
		<logic:equal name="roleKey" value="A">
			<!-- estados de la aplicación -->
			<script src="ng/config_admin.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/_commonTemplates/nav_bar_controller.js?fvrVer=${miVersion}"></script>
			<script src="ng/_commonTemplates/header_controller.js?fvrVer=${miVersion}"></script>

			<!-- charts -->
			<script src="ng/_commonTemplates/chart_controller.js?fvrVer=${miVersion}"></script>
			<script src="ng/_lib/chart/Chart.min.js?fvrVer=${miVersion}"></script>
			<script src="ng/_lib/chart/angular-chart/angular-chart.min.js?fvrVer=${miVersion}"></script>

			<script src="ng/us_users/userRole_Ctrl.js?fvrVer=${miVersion}"></script>
			<script src="ng/us_users/userRole_service.js?fvrVer=${miVersion}"></script>

			<script src="ng/ac_activityCockpits/AcDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/ac_activityCockpits/AcDSPFIL_service.js?fvrVer=${miVersion}"></script>

			<script src="ng/cp_cockpits/CpDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/cp_cockpits/CpDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/lg_logs/LgDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/lg_logs/LgDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/lo_location/LoDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/lo_location/LoDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/cd_LocationClosedDays/CdDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/cd_LocationClosedDays/CdDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/pa_systemParameters/PaDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/pa_systemParameters/PaDSPFIL_service.js?fvrVer=${miVersion}"></script>

			<script src="ng/pm_promosManuales/PmDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/pm_promosManuales/PmDSPFIL_service.js?fvrVer=${miVersion}"></script>

			<script src="ng/pr_promos/PrDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/pr_promos/PrDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/ps_countries/PsDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/ps_countries/PsDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/pt_products/PtDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/pt_products/PtDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/py_PayPalTokens/PyDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/py_PayPalTokens/PyDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/ad_rs_reservations/Ad_rsDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/ad_rs_reservations/Ad_rsDSPFIL_service.js?fvrVer=${miVersion}"></script>
			<script src="ng/rs_reservations/RsDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/rs_reservations/RsDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/sg_publicProcesses/SgDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/sg_publicProcesses/SgDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/tk_tokens/TkDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/tk_tokens/TkDSPFIL_service.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/ts_timeSlices/TsDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/ts_timeSlices/TsDSPFIL_service.js?fvrVer=${miVersion}"></script>

			<script src="ng/tt_timeTableReference/TtDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/tt_timeTableReference/TtDSPFIL_service.js?fvrVer=${miVersion}"></script>

			<script src="ng/us_users/UsDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/us_users/UsDSPFIL_service.js?fvrVer=${miVersion}"></script>
			<script src="ng/us_users/rsUsEDTRCD_Ctrl.js?fvrVer=${miVersion}"></script>
		</logic:equal>

		<logic:equal name="roleKey" value="U">
			<!-- estados de la aplicación -->
			<script src="ng/config_user.js?fvrVer=${miVersion}"></script>
		
			<script src="ng/_commonTemplates/nav_bar_controller.js?fvrVer=${miVersion}"></script>
			<script src="ng/_commonTemplates/header_controller.js?fvrVer=${miVersion}"></script>

			<script src="ng/us_users/userRole_Ctrl.js?fvrVer=${miVersion}"></script>
			<script src="ng/us_users/userRole_service.js?fvrVer=${miVersion}"></script>
	
			<script src="ng/us_users/rsUsEDTRCD_Ctrl.js?fvrVer=${miVersion}"></script>

			<script src="ng/rs_reservations/RsDSPFIL_Ctrl_basic.js?fvrVer=${miVersion}"></script>
			<script src="ng/rs_reservations/RsDSPFIL_service.js?fvrVer=${miVersion}"></script>
		</logic:equal>
    <!-- ==================================== -->

	<div ng-init="varGlobal.logon_USR='${logon_USR}'"></div>
	<div ng-init="varGlobal.logon_HSH='${logon_HSH}'"></div>
	<div ng-init="varGlobal.contextVars='{}'"></div>
	<div ng-init="varGlobal.currentNavTabItem=''"></div>
    
    <!-- ==================================== -->
    <logic:equal name="roleKey" value="U">
		<script type="text/javascript">
		    window.smartlook||(function(d) {
		    var o=smartlook=function(){ o.api.push(arguments)},h=d.getElementsByTagName('head')[0];
		    var c=d.createElement('script');o.api=new Array();c.async=true;c.type='text/javascript';
		    c.charset='utf-8';c.src='https://rec.smartlook.com/recorder.js';h.appendChild(c);
		    })(document);
		    smartlook('init', '887d25f46d8f8b78b6eaf272a8303a326331a1bb');
		</script>
	</logic:equal>
</head>
<body id="body">
<div id="loader-container">
            <svg class="spinner" width="65px" height="65px" viewBox="0 0 66 66" xmlns="http://www.w3.org/2000/svg">
               <circle class="path" fill="none" stroke-width="6" stroke-linecap="round" cx="33" cy="33" r="30"></circle>
            </svg>
        </div>

        <div ui-preloader></div>
	<logic:notPresent name="roleKey">
		<h3>La sesión quedó invalidada.</h3>
		 Por favor vuelva a <a href="Off">iniciar sesión aqui.</a>
		<script>
			angular.module('laAplicacion',['ngMaterial', 'ngMessages'])
				.config(
					function($mdThemingProvider) {
						<%@include file="/script/mdThemingProvider.js"%>
					}
				)
				.controller(
					'AppCtrl'
					, function($scope, $timeout, $mdSidenav, $mdToast) {
						;
					}
				);
		</script>
	</logic:notPresent>

	<logic:present name="roleKey">
		<div id="container" class="full-height no-sidebar"> 
			<aside ui-view="rgnNavBar">
			
			</aside>
			<section class="app-page-container" style="border-top: 3px solid #CE1417;">
				<div class="app-header" ui-view="rgnHeader" ></div>
				<div class="app-content-wrapper">
					<div class="app-content">
						<div class="full-height">
							<div class="container-fluid">
								<%--<logic:present name="roleKey">
									<div>(
									<logic:equal name="roleKey" value="U">user</logic:equal>
									<logic:equal name="roleKey" value="F">franchise</logic:equal>
									<logic:equal name="roleKey" value="A">admin</logic:equal>
									)</div>
								</logic:present> --%>
								<div id="rgnMain" ui-view="rgnMain"></div>
								<div id="rgnChart" ui-view="rgnChart"></div>
							</div>
						</div>
					</div>
				</div>
			</section>

		</div>
	</logic:present>

<script type="text/javascript">
var toggleCollapsed = false;
	function toggleCollapsedNav(){
		if (toggleCollapsed == false){
			$('#container').addClass('nav-collapsed');
			toggleCollapsed = true;
		} else {
			$('#container').removeClass('nav-collapsed');
			toggleCollapsed = false;
		}
		
	}

	 $(".modal-lg").on('show.bs.modal', function () {
            alert('The modal is about to be shown.');
    });

(function () {
    var $body = $('#body')
    var $loader = $('#loader-container')

    $(window).on('load', function(){
        setTimeout( hideLoader , 1000)
    });

    function hideLoader() {
        $loader.fadeOut('slow')
    }
})(); 
</script>
</body>

</html>