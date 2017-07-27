angular.module('commonServices_module')
	.service('validationService',
    [
        '$http',

        function($http)
        {

            return {

                validarEmail : function (email) { // alert('validationService.validarEmail( ' + email +  ' )');
                	
                    var reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,10})+$/;
                    if (reg.test(email.trim()) && email !=='undefined') {
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            
            
            };
        }
    ]);