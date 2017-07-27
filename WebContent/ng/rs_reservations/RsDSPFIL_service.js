
angular
	.module('laAplicacion')
	.service(
		'RsDSPFIL_service',
	    [
	          '$http'
	        , '$window'
	        ,  function($http,$window) {
		
		        	return {
		        		filtrar: function(actionForm) {
		        			/////////////////////////
		        			// Persistir su filtro:
		        			$window.sessionStorage.setItem("RsDSPFIL.rs_filtro", angular.toJson( actionForm.rs_filtro ));
		        			$window.sessionStorage.setItem("filasGrid", angular.toJson( actionForm.filasGrid ));
		        			/////////////////////////
			       			actionForm.opcionPantalla = "Filtrar";
			       			return $http({
					                url: './RsDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
	        		  , reload: function(actionForm) {
			       			actionForm.opcionPantalla = "";
			       			return $http({
					                url: './RsDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
		        	  , exportar: function(actionForm){
		        			actionForm.opcionPantalla = "Exportar";
		        			return $http({
									url: './RsDSPFIL_A.do',
									method: 'POST',
									data: actionForm
				            }).then ( function( response) { return response; });
		        		}
	        		  , rtPg: function(actionForm) {
			       			actionForm.opcionPantalla = "RtPg";
			       			return $http({
					                url: './RsDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
	        		  , avPg: function(actionForm) {
			       			actionForm.opcionPantalla = "AvPg";
			       			return $http({
					                url: './RsDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
		        	  , add_paypal: function(actionForm){
		        			actionForm.opcionPantalla = "NuevoReg_paypal";
		        			return $http({
		        				url: './RsADDRCD_A.do',		// No es DSPFIL, es ADDRCD. ATENCIÓN !!!
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , add_tpv: function(actionForm){
		        			actionForm.opcionPantalla = "NuevoReg_tpv";
		        			return $http({
		        				url: './RsADDRCD_A.do',		// No es DSPFIL, es ADDRCD. ATENCIÓN !!!
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , add_cash: function(actionForm){
		        			actionForm.opcionPantalla = "NuevoReg_cash";
		        			return $http({
		        				url: './RsADDRCD_A.do',		// No es DSPFIL, es ADDRCD. ATENCIÓN !!!
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , addCheck: function(actionForm){
		        			actionForm.opcionPantalla = "checkDisponibilidad";
		        			return $http({
		        				url: './RsADDRCD_A.do',		// No es DSPFIL, es ADDRCD. ATENCIÓN !!!
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , chg: function(actionForm){
		        			actionForm.opcionPantalla = "CambiarReg";
		        			return $http({
		 		                url: './RsEDTRCD_A.do',		// No es DSPFIL, es EDTRCD. ATENCIÓN !!!
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , dlt: function(actionForm){
		        			actionForm.opcionPantalla = "Suprimir";
		        			return $http({
		 		                url: './RsEDTRCD_A.do',		// No es DSPFIL, es EDTRCD. ATENCIÓN !!!
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
			        };
		      }
	
	    ]);
