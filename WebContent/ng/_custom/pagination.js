/**
 * @author Emilio Estecha 2016
 *
 */

//////////////////////////////////////////////////////////
//      Ejemplo con bootstrap:
//	        <ul class="pagination">
//	        <li><a href="#">1</a></li>
//	        <li><a href="#">2</a></li>
//	        <li><a href="#">3</a></li>
//	        <li><a href="#">4</a></li>
//	        <li><a href="#">5</a></li>
//	        </ul>
//       
//	NOTA:
//    Para incluir HTML dinámico en un documento se usa esto, PERO NO FUNCIONA el script que contenga:
//        <span ng-bind-html="toTrustedHTML( txtHtmlPaginador )"></span>
//    Para poder además inyectar script se agrega la siguiente directiva Angular:
//    	$compile para permitir bind de los ng-click dentro de un texto html:
//        http://stackoverflow.com/questions/18157305/angularjs-compiling-dynamic-html-strings-from-database
//////////////////////////////////////////////////////////

function paginador( pFilasTotales, pFilasGrid, pFilaInicioGrid ){

	var filasTotales = pFilasTotales;
	var filasGrid = pFilasGrid;
	var filaInicioGrid = pFilaInicioGrid;
	
	function toInt(value) { return ~~value; }

	function get_paginado_HTML() {
        var cadena = "";

        if ( filasTotales <= filasGrid ) return cadena;

//        int primerRegistro =  filaInicioGrid;
        var panActual  = getPaginaActual();
        var panTotales = getPaginasTotales();
        
        var panVistas = 5;// Queda mejor si es IMPAR!!.
        var mitadVistas  = toInt( (panVistas / 2.0) );
        var marcarMenos = true;
        var marcarMas   = true;

        var panInicio = panActual - mitadVistas;
        if ( panInicio <= 1 ) { 
            panInicio = 1;
            marcarMenos = false;
         }

        var panFinal  = panInicio + panVistas-1;
        if ( panFinal >= panTotales ) {
            panFinal = panTotales;
            marcarMas = false;
        }
        
//        if (marcarMenos) { --panFinal; }
//        if (marcarMas)   { --panFinal; }
        
        /////////////////
        cadena += "<ul class='pagination pagination-modificado'>";
        if (marcarMenos) {
        	panFinal;
        	cadena += '<li><button type="button" ng-click="goPage()">...</button></li>';
        }
        for (var i = panInicio; i <= panFinal ; i++) {
        	var fila = (1+((i-1) * filasGrid ));
        	cadena += '<li><button type="button" ng-click="goRow(' + fila + ')">' + i + '</button></li>';
        }
        if (marcarMas) {
        	cadena += '<li><button type="button" ng-click="goPage()">...</button></li>';
        }
        cadena += "</ul>";
        /////////////////
        
        return cadena;
	}
	function get_pagina_HTML() {
		var cadena = "";
        cadena += "({{actionForm.filasTotales}}&nbsp;filas)&nbsp;";
        cadena += "Pg: " + getPaginaActual();
        if ( filasTotales <= filasGrid ) return cadena;
        cadena += " / " + getPaginasTotales();
        return cadena;
	}
	function getPaginaActual() {
        if ( filaInicioGrid < 1 ) filaInicioGrid = 1;
        return toInt( ( (filaInicioGrid-1) / filasGrid) + 1 );
	}
	function getPaginasTotales() {
        var d = filasTotales / filasGrid;
        if ( d > toInt(d) ) d += 1.0;
        return ( toInt(d) );
	}

	return {
		  getPaginado_innerHTML : function() { return get_pagina_HTML() + " " + get_paginado_HTML(); }
		, get_paginado_HTML		: function() { return get_paginado_HTML(); }
		, get_pagina_HTML 		: function() { return get_pagina_HTML(); }
		, getPaginaActual 		: function() { return getPaginaActual(); }
		, getPaginasTotales		: function() { return getPaginasTotales(); }
	}
};