
angular
	.module('laAplicacion')
	.service(
		'AcDSPFIL_service',
	    [
	          '$http'
	        , '$window'
	        ,  function($http,$window) {
		
		        	return {
		        		filtrar: function(actionForm) {
		        			/////////////////////////
		        			// Persistir su filtro:
		        			$window.sessionStorage.setItem("AcDSPFIL.ac_filtro", angular.toJson( actionForm.ac_filtro ));
		        			$window.sessionStorage.setItem("filasGrid", angular.toJson( actionForm.filasGrid ));
		        			/////////////////////////
			       			actionForm.opcionPantalla = "Filtrar";
			       			return $http({
					                url: './AcDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
	        		  ,	filtrar_group10: function(actionForm) {
		        			/////////////////////////
		        			// Persistir su filtro:
		        			$window.sessionStorage.setItem("AcDSPFIL.ac_filtro", angular.toJson( actionForm.ac_filtro ));
		        			$window.sessionStorage.setItem("filasGrid", angular.toJson( actionForm.filasGrid ));
		        			/////////////////////////
			       			actionForm.opcionPantalla = "Filtrar";
			       			return $http({
					                url: './AcDSPFIL_group10_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
	        		  , reload: function(actionForm) {
			       			actionForm.opcionPantalla = "";
			       			return $http({
					                url: './AcDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
	        		  , reload_group10: function(actionForm) {
			       			actionForm.opcionPantalla = "";
			       			return $http({
					                url: './AcDSPFIL_group10_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
		        	  , exportar: function(actionForm){
		        			actionForm.opcionPantalla = "Exportar";
		        			return $http({
									url: './AcDSPFIL_A.do',
									method: 'POST',
									data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , exportar_group10: function(actionForm){
		        			actionForm.opcionPantalla = "Exportar";
		        			return $http({
									url: './AcDSPFIL_group10_A.do',
									method: 'POST',
									data: actionForm
				            }).then ( function( response) { return response; });
		        		}
	        		  , rtPg: function(actionForm) {
			       			actionForm.opcionPantalla = "RtPg";
			       			return $http({
					                url: './AcDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
	        		  , rtPg_group10: function(actionForm) {
			       			actionForm.opcionPantalla = "RtPg";
			       			return $http({
					                url: './AcDSPFIL_group10_A.do',
					                method: 'POST',
					                data: actionForm
		       				}).then ( function( response) { return response; });
		        		}	
	        		  , avPg: function(actionForm) {
			       			actionForm.opcionPantalla = "AvPg";
			       			return $http({
					                url: './AcDSPFIL_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
	        		  , avPg_group10: function(actionForm) {
			       			actionForm.opcionPantalla = "AvPg";
			       			return $http({
					                url: './AcDSPFIL_group10_A.do',
					                method: 'POST',
					                data: actionForm
				            }).then ( function( response) { return response; });
		        		}	
		        	  , add: function(actionForm){
		        			actionForm.opcionPantalla = "NuevoReg";
		        			return $http({
		        				url: './AcADDRCD_A.do',		// No es DSPFIL, es ADDRCD. ATENCIÓN !!!
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , chg: function(actionForm){
		        			actionForm.opcionPantalla = "CambiarReg";
		        			return $http({
		 		                url: './AcEDTRCD_A.do',		// No es DSPFIL, es EDTRCD. ATENCIÓN !!!
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , dlt: function(actionForm){
		        			actionForm.opcionPantalla = "Suprimir";
		        			return $http({
		 		                url: './AcEDTRCD_A.do',		// No es DSPFIL, es EDTRCD. ATENCIÓN !!!
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , marcarTodo: function(actionForm){
		        			actionForm.opcionPantalla = "MarcarTodo";
		        			return $http({
				                url: './AcDSPFIL_A.do',
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , marcarTodo_group10: function(actionForm){
		        			actionForm.opcionPantalla = "MarcarTodo";
		        			return $http({
				                url: './AcDSPFIL_group10_A.do',
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , marcados_suprimir: function(actionForm){
		        			actionForm.opcionPantalla = "Borrar";
		        			return $http({
				                url: './AcDSPFIL_A.do',
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , marcados_suprimir_group10: function(actionForm){
		        			actionForm.opcionPantalla = "Borrar";
		        			return $http({
				                url: './AcDSPFIL_group10_A.do',
		 		                method: 'POST',
		 		                data: actionForm
				            }).then ( function( response) { return response; });
		        		}
			        };
		      }
	
	    ]);
