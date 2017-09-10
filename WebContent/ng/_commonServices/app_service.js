angular.module('commonServices_module')
	.service('app_services',
    [
        '$http',
        '$q',
        '$sce',
		'$mdDialog',

        function($http,$q,$sce,$mdDialog) {

            /* ---------- GESTION DE ERRORES DE SERVICIOS GENERICA ---------- */
						// Funcion comun para gestion de los errores en funcion del codigo http
            function tratarError(data, status, deferred) {
                if (status === 404 || status === 0) {
                    deferred.reject("Servicio no disponible");
                } else {
                    deferred.reject("Error: " + status);
                }
            }

            // Funcion comun para montar una llamada http y gestionar la respuesta con otra promesa
            function peticionHTTP(conf){
                var deferred = $q.defer();
                var promise = deferred.promise;

                $http(conf)
                    .then(function(response) {
                        deferred.resolve(response.data);
                    },function(data, status, headers, config) {
                        tratarError(data, status, deferred);
                    });

                return promise;
            }
            
            // Función para cuadros de alerta:
            function showAlert( content, title, ok_text ) {
				  title = title || 'Información';
				  content = content || 'Algo no ha ido bien.';
				  ok_text = ok_text || 'OK';

			      dlg = $mdDialog.alert({
			        title: 'Attention',
			        htmlContent: 'This is an example of how easy dialogs can be!',
			        ok: 'Close'
			      }).clickOutsideToClose(true)
			        .title(title)
			        .htmlContent(content)
			        .ok(ok_text);
		        // .ariaLabel('Alert Dialog Demo')

			      $mdDialog
			        .show( dlg )
			        .finally(function() {
			          dlg = undefined;
			        });

            }

            // Funcion para menu superior
            var originatorEv;
				    function openMenu($mdOpenMenu, ev)     {
				      originatorEv = ev;
				      $mdOpenMenu(ev);
				    };

            return {
	              errorComun : function( text  ) {
	            	  if ( text.includes("error de seguridad") ) {
		            	  alert(text);
	            		  window.location = "./Off";
	            	  } else {
	            		  showAlert( text, null, null );	            		  
	            	  }
	          		  return;
	            } 
	        	, showAlert : function( content, title, ok_text ) {
	        		showAlert(content, title, ok_text);
	        	} 
	        	, openMenu : function( $mdOpenMenu, ev ) {
	        		openMenu($mdOpenMenu, ev);
	        	} 
	        	, showAlertTemplate : function( templateContent ) {
	        		showAlertTemplate( templateContent );
	        	} 
            	, toTrustedHTML : function( html_text  ) {
            		  return $sce.trustAsHtml( html_text );	// $sce: Strict Contextual Escaping
            	} 
	            , admMnu : function ( user, hashCode ) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "admMnu", USR: user, KEY: hashCode }
	                });
	            }
	            , rtvOcu : function ( location_id, start_date, start_time ) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "rtvOcu", LOC: location_id, DAT: start_date, TIM: start_time }
	                });
	            }
	            , pt_lst : function ( user, hashCode ) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "pt_lst", USR: user, KEY: hashCode }
	                });
	            }
	            , ps_lst : function ( user, hashCode, isConBanderas ) {
	            	if (isConBanderas) {
		                return peticionHTTP({
		                    method: 'POST',
		                    url: './FvrServlet?FLAG',
		                    data :  { ACC: "ps_lst", USR: user, KEY: hashCode }
		                });
	            	} else {
		                return peticionHTTP({
		                    method: 'POST',
		                    url: './FvrServlet',
		                    data :  { ACC: "ps_lst", USR: user, KEY: hashCode }
		                });
	            	}
	            }
	            , lo_lst : function () {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "lo_lst" }
	                });
	            }
	            , getRsFecMin : function (location_id) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "getRsFecMin", LOC: location_id }
	                });
	            }
	            , cd_lst : function (location_id) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "cd_lst", LOC: location_id }
	                });
	            }
	            , cp_lst : function (location_id, isBlocked) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "cp_lst", LOC: location_id, BLK: isBlocked }
	                });
	            }
	            , tt_lst : function (location_id, day_type, isBlocked) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "tt_lst", LOC: location_id, TYP: day_type, BLK: isBlocked }
	                });
	            }
	            , rtvUsData : function ( user, hashCode ) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "rtvUsData", USR: user, KEY: hashCode }
	                });
	            }
	            , rtvTjData : function ( user, hashCode, card_id ) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "rtvTjData", USR: user, KEY: hashCode, DAT: card_id }
	                });
	            }
	            , md_date_filter_onlyWeekEnds : function ( date ) {
	            	if ( date === undefined ) { return false; }
	                var day = date.getDay();
	                return day === 0 || day === 6;
	            }
	            , md_date_filter_onlyWorkable : function ( date, lst_closedDays ) {
	            	if ( date === undefined ) { return false; }
	                var day = date.getDay();
	                
	                if ( lst_closedDays != null) {
	                	var aaaa = date.getFullYear();
	                	var mm = ("00" + (1+date.getMonth())); mm = mm.substring(mm.length-2);
		                var dd = "00" + date.getDate(); dd = dd.substring(dd.length-2);
	                	if ( lst_closedDays.includes( aaaa+"-"+mm+"-"+dd ) ) {
	                		// console.log( "NO LABORABLE: " + aaaa+"-"+mm+"-"+dd );
	                		return false;
	                	};
	                }
	                
//	                return day === 0 || day === 1 || day === 2 || day === 3 || day === 4 || day === 5 || day === 6;
	                return day === 0              || day === 2 || day === 3 || day === 4 || day === 5 || day === 6;
	            }
	            , gamingModule : function ( user, hashCode ) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "gamingModule", USR: user, KEY: hashCode }
	                });
	            }
	            , getPaymentMethods : function ( user, hashCode, location_id ) {
	                return peticionHTTP({
	                    method: 'POST',
	                    url: './FvrServlet',
	                    data :  { ACC: "getPaymentMethods", USR: user, KEY: hashCode, DAT: location_id }
	                });
	            }

            };
        }
    ]);