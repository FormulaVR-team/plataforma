
angular
	.module('laAplicacion')
	.service(
		'userRole_service',
	    [
	          '$http'
	        , '$window'
	        ,  function($http,$window) {
		
		        	return {
		        	    add: function(actionForm){
		        			actionForm.opcionPantalla = "NuevoReg";
		        			return $http({
		        				url: './RsUsADDRCD_A.do',
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , chgpwd: function(actionForm){
		        			actionForm.opcionPantalla = "ChgPwd";
		        			return $http({
		        				url: './RsUsADDRCD_A.do',
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , get: function(actionForm){
		        			actionForm.opcionPantalla = "LeerReg";
		        			return $http({
		        				url: './RsUsEDTRCD_A.do',
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
		        	  , chg: function(actionForm){
		        			actionForm.opcionPantalla = "CambiarReg";
		        			return $http({
		        				url: './RsUsEDTRCD_A.do',
		        				method: 'POST',
		        				data: actionForm
				            }).then ( function( response) { return response; });
		        		}
			        };
		      }
	
	    ]);
