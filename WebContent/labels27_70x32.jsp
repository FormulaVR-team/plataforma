<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="theNgApp" ng-controller="AppCtrl">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>labels 27</title>


  <!-- Angular -->    
	<script src="./ng/_lib/ng/angular.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-ui-router.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-animate.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-aria.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-messages.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-sanitize.min.js?fvrVer=${miVersion}"></script>
	<script src="./ng/_lib/ng/angular-md5.js?fvrVer=${miVersion}"></script>
	
	<script src="./ng/_lib/ng/i18n/angular-locale_es-es.min.js"></script>
	
	<!-- Angular Material Library -->
	<script src="./ng/_lib/ng/angular-material.min.js?fvrVer=${miVersion}"></script>
	<!-- Angular Material style sheet -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./ng/_lib/ng/css/angular-material.min.css?fvrVer=${miVersion}" />
	<link rel="stylesheet" href="./ng/_lib/ng/css/angular-material.CUSTOM-FVR.css?fvrVer=${miVersion}" />
	<link rel="stylesheet" href="./ng/_lib/ng/css/icon.css?family=Material+Icons" />

	<style>
		body {
			padding: 10px;
		}
		.frame_labels {
			/* padding: 10px; */
			background-color: silver;
			/* border: 1px solid black; */
			/* height: 210mm; */
			/* width: 150mm; */
			width: 100%;
		}
		.frame_label {
			/* white-space: nowrap; */
			margin: 1px;
			/* width: 70mm; */
			/* height: 32mm; */
			width: 68mm;
			height: 30mm;
			background-color: white;
		}
		.frame_label_row {
			/* padding: 1px; */
		}
	</style>

	</head>
	<body>
		<main class="frame_labels">
			<div layout="column">
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
				<div layout="row" layout-align:"space-between end" class="frame_label_row">
					<div class="frame_label">
						* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
						1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 *
					 </div>
					<div class="frame_label">
						* 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 
						2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 *
					 </div>
					<div class="frame_label">
						* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 
						3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 *
					 </div>
				</div>
			</div>
		</main>

<script>
	var app = angular.module('theNgApp', ['ngMaterial', 'ngMessages']);
	app.controller('AppCtrl', function($scope, $timeout, $mdToast, $http) {
		$scope.cosa = "La mierda";
	});	
</script>
	
	</body>
</html>