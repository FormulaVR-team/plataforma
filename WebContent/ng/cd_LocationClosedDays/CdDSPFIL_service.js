
angular
	.module('laAplicacion')
	.service(
		'CdDSPFIL_service',
	    [
	          '$http'
	        , '$window'
	        ,  function($http,$window) {
		
		        	return {
		        		filtrar: function(actionForm) {
		        			/////////////////////////
		        			// Persistir su filtro:
		        			$window.sessionStorage.setItem("CdDSPFIL.cd_filtro", angular.toJson( actionForm.cd_filtro ));
		        			$window.sessionStorage.setItem("filasGrid", angular.toJson( actionForm.filasGrid ));
		        			/////////////////////////
			       			actionForm.opcionPantalla = "Filtrar";
			       			return $http({
					                url: './CdDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
	        		  , reload: function(actionForm) {
			       			actionForm.opcionPantalla = "";
			       			return $http({
					                url: './CdDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
		        	  , exportar: function(actionForm){
		        			actionForm.opcionPantalla = "Exportar";
		        			return $http({
									url: './CdDSPFIL_A.do',
									method: 'POST',
									data: actionForm
				            }).then ( function( response) { return response; });
		        		}
	        		  , rtPg: function(actionForm) {
			       			actionForm.opcionPantalla = "RtPg";
			       			return $http({
					                url: './CdDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
	        		  , avPg: function(actionForm) {
			       			actionForm.opcionPantalla = "AvPg";
			       			return $http({
					                url: './CdDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
		        	  , add: function(actionForm){
		        			actionForm.opcionPantalla = "NuevoReg";
		        			return $http({
		        				url: './CdADDRCD_A.do',		// No es DSPFIL, es ADDRCD. ATENCIÓN !!!
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , chg: function(actionForm){
		        			actionForm.opcionPantalla = "CambiarReg";
		        			return $http({
		 		                url: './CdEDTRCD_A.do',		// No es DSPFIL, es EDTRCD. ATENCIÓN !!!
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , dlt: function(actionForm){
		        			actionForm.opcionPantalla = "Suprimir";
		        			return $http({
		 		                url: './CdEDTRCD_A.do',		// No es DSPFIL, es EDTRCD. ATENCIÓN !!!
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , marcarTodo: function(actionForm){
		        			actionForm.opcionPantalla = "MarcarTodo";
		        			return $http({
				                url: './CdDSPFIL_A.do',
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , marcados_suprimir: function(actionForm){
		        			actionForm.opcionPantalla = "Borrar";
		        			return $http({
				                url: './CdDSPFIL_A.do',
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
			        };
		      }
	
	    ]);
