
angular
	.module('laAplicacion')
	.service(
		'TtDSPFIL_service',
	    [
	          '$http'
	        , '$window'
	        ,  function($http,$window) {
		
		        	return {
		        		filtrar: function(actionForm) {
		        			/////////////////////////
		        			// Persistir su filtro:
		        			$window.sessionStorage.setItem("TtDSPFIL.tt_filtro", angular.toJson( actionForm.tt_filtro ));
		        			$window.sessionStorage.setItem("filasGrid", angular.toJson( actionForm.filasGrid ));
		        			/////////////////////////
			       			actionForm.opcionPantalla = "Filtrar";
			       			return $http({
					                url: './TtDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
	        		  , reload: function(actionForm) {
			       			actionForm.opcionPantalla = "";
			       			return $http({
					                url: './TtDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
		        	  , exportar: function(actionForm){
		        			actionForm.opcionPantalla = "Exportar";
		        			return $http({
									url: './TtDSPFIL_A.do',
									method: 'POST',
									data: actionForm
				            }).then ( function( response) { return response; });
		        		}
	        		  , rtPg: function(actionForm) {
			       			actionForm.opcionPantalla = "RtPg";
			       			return $http({
					                url: './TtDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
	        		  , avPg: function(actionForm) {
			       			actionForm.opcionPantalla = "AvPg";
			       			return $http({
					                url: './TtDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
		        	  , add: function(actionForm){
		        			actionForm.opcionPantalla = "NuevoReg";
		        			return $http({
		        				url: './TtADDRCD_A.do',		// No es DSPFIL, es ADDRCD. ATENCIÓN !!!
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , chg: function(actionForm){
		        			actionForm.opcionPantalla = "CambiarReg";
		        			return $http({
		 		                url: './TtEDTRCD_A.do',		// No es DSPFIL, es EDTRCD. ATENCIÓN !!!
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , dlt: function(actionForm){
		        			actionForm.opcionPantalla = "Suprimir";
		        			return $http({
		 		                url: './TtEDTRCD_A.do',		// No es DSPFIL, es EDTRCD. ATENCIÓN !!!
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
			        };
		      }
	
	    ]);
