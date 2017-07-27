
    // Un menú es un array de "items".
    // Un "Item" es una cadena de textos separados por el carácter "|".
    // Los campos de un item son: { Nivel, Literal, URL }
    // Ejemplo de item: "11|uno uno|gotoUnoUno"

    //////////////////////////////////////////
    // Requisitos en la página donde se incluya:
    //  <head>
    //    ...
    //    <link rel="stylesheet" type="text/css" href="./res/JSMenu_estilos.css" />
    //    <script type="text/javascript" src="./script/JSMenu.js"></script>
    //    ...
    //  </head>
    //  <body>
    //      <div id="elMenu"></div>
    //      <script type="text/javascript">
    //        function CARGAMENU() {
    //            var menu = new Array();
    //                // Los 'push' pueden estar en un '< logic : iterate...'
    //                menu.push("1|uno|.");
    //                menu.push("11|uno uno|gotoUnoUno");
    //                menu.push("12|uno dos|.");
    //                menu.push("121|uno dos uno|gotoUnoDosUno");
    //                menu.push("122|uno dos dos|gotoUnoDosDos");
    //                // etc...
    //            JSMenu_construirMenu( menu );
    //        }
    //      </script>
    //  </body>
    //////////////////////////////////////////

    function JSMenu_click( item, accion ) {
        // Se guarda 'item' en todos los formularios posibles para tratar de mantener su estado...
        for(var i=0; i<document.forms.length; i++) {document.forms[i].elements["opcionJSMenu"].value = item;}

        // Puede ser llamada externa a la red o interna a Struts (struts-config).
        if ( accion != null ) {
            // Llamada a una URL:
            if ( accion.length >= 7 && accion.toLowerCase().substr(0,7) == "http://" ) {
                window.navigate( accion );
            } else {
                // Si se usan llamadas internas struts, debe existir el form "menu_AF" !!!
                setOpcion("menu_AF",accion,null);   
            }
        }
    }

    function JSMenu_construirMenu( arrayItems ) {
        /////////////////////////////////////////
        // Niveles horizontales de árbol posibles:
        var niveles = new Array(4); 
        // Array de arrays, inicializar:
        for (var i=0; i< niveles.length; i++) { niveles[i] = new Array(); }

        // Los items se segregan en distintos arrays por niveles y luego se ordenan.
        niveles = JSMenu_agrupar_ordenar( arrayItems, niveles );

        /////////////////////////////////////////
        // Busca el valor 'idItemAbierto' memorizado en cualquier formulario posible...
        var idItemAbierto = "";
        for(var i=0; i<document.forms.length; i++) {
            var s = document.forms[i].elements["opcionJSMenu"].value;
            if ( s != null && s.length > idItemAbierto.length ) { idItemAbierto = s; }
        }
        var e = document.getElementById( "elMenu" );
        if (e==null || e=='undefined') { alert("Contenedor no definido:  <div id=\"elMenu\">"); }
        else { 
            e.innerHTML = JSMenu_transformar( niveles, idItemAbierto );
        //  document.write("<xmp>" + JSMenu_transformar( niveles, idItemAbierto ) + "</xmp>");
        }
        /////////////////////////////////////////
    }

    function JSMenu_agrupar_ordenar( arrayItems, niveles ) {
        // Los items se segregan en distintos arrays por niveles y luego se ordenan.
        var campos;
        for (var i=0; i< arrayItems.length; i++) {
            campos = arrayItems[i].split('|',3);
            switch ( campos[0].length ) {
              case 1 : niveles[0].push( arrayItems[i] ); break;
              case 2 : niveles[1].push( arrayItems[i] ); break;
              case 3 : niveles[2].push( arrayItems[i] ); break;
              case 4 : niveles[3].push( arrayItems[i] ); break;
              default: break;
            }
        }
        for (var i=0; i< niveles.length; i++) { niveles[i].sort(); }
        return niveles;
    }

    function JSMenu_transformar( profundidad, idItemAbierto ) {
        var resHTML = "";
        var nPadre      = profundidad[0];
        var nHijos      = profundidad[1];
        var nNietos     = profundidad[2];
        var nBisNietos  = profundidad[3];
        var camposN0 = null;
        var camposN1 = null;
        var camposN2 = null;
        var camposN3 = null;
        var hijos = null;
        var nietos = null;
        var bisNietos = null;
        var attVisual = null;
        //////////////////////////
        resHTML += escape("\n<table class=\"JSMenu_table\" style=\"display: block;\">");    // APERTURA GENERAL
        for (var i=0; i< nPadre.length; i++) {                              //////// PADRES
            camposN0 = nPadre[i].split('|',3);
            // Si tiene hijos,
            hijos = JSMenu_rtvHijos( nPadre[i], nHijos );
            if ( hijos != null && hijos.length > 0 ) {
                resHTML += escape("\n<tr class=\"JSMenu_tr\"><td class=\"JSMenu_td\">\n<span class=\"JSMenu_rama\" onmouseout=\"javascript:this.style.backgroundColor='transparent';\" onmouseover=\"javascript:this.style.backgroundColor='Chartreuse';\" onclick=\"JSMenu_bascular(this,'JSMenu_" + camposN0[0] + "' );\">");
                resHTML += escape( "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + camposN0[1] );
                resHTML += escape("</span>");
                /////////////////////////////
                // Para cada hijo:
                attVisual = attDisplay(camposN0[0],idItemAbierto);
                resHTML += escape("\n<table id=\"JSMenu_" + camposN0[0] + "\" class=\"JSMenu_table\" "+ attVisual + ">");    // APERTURA HIJOS
                for (var j=0; j< hijos.length; j++) {                        //////// HIJOS
                    camposN1 = hijos[j].split('|',3);
                    // Si tiene nietos, 
                    nietos = JSMenu_rtvHijos( hijos[j], nNietos );
                    if ( nietos != null && nietos.length > 0 ) {
                        resHTML += escape("\n<tr class=\"JSMenu_tr\"><td class=\"JSMenu_td\">\n<span class=\"JSMenu_rama\" onmouseout=\"javascript:this.style.backgroundColor='transparent';\" onmouseover=\"javascript:this.style.backgroundColor='Chartreuse';\" onclick=\"JSMenu_bascular(this,'JSMenu_" + camposN1[0] + "' );\">");
                        resHTML += escape( "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + camposN1[1] );
                        resHTML += escape("</span>");
                        /////////////////////////////
                        // Para cada nieto:
                        attVisual = attDisplay(camposN1[0],idItemAbierto);
                        resHTML += escape("\n<table id=\"JSMenu_" + camposN1[0] + "\" class=\"JSMenu_table\" "+ attVisual + ">");    // APERTURA NIETOS
                        for (var k=0; k< nietos.length; k++) {              //////// NIETOS
                            camposN2 = nietos[k].split('|',3);
                            // Si tiene bisNietos, 
                            bisNietos = JSMenu_rtvHijos( nietos[k], nBisNietos );
                            if ( bisNietos != null && bisNietos.length > 0 ) {
                                resHTML += escape("\n<tr class=\"JSMenu_tr\"><td class=\"JSMenu_td\">\n<span class=\"JSMenu_rama\" onmouseout=\"javascript:this.style.backgroundColor='transparent';\" onmouseover=\"javascript:this.style.backgroundColor='Chartreuse';\" onclick=\"JSMenu_bascular(this,'JSMenu_" + camposN2[0] + "' );\">");
                                resHTML += escape( "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + camposN2[1] );
                                resHTML += escape("</span>");
                                /////////////////////////////
                                // Para cada bisNieto:
                                attVisual = attDisplay(camposN2[0],idItemAbierto);
                                resHTML += escape("\n<table id=\"JSMenu_" + camposN2[0] + "\" class=\"JSMenu_table\" "+ attVisual + ">");    // APERTURA BISNIETOS
                                for (var l=0; l< bisNietos.length; l++) {              //////// BISNIETOS
                                    camposN3 = bisNietos[l].split('|',3);
                                    attColor = attStyleColor(camposN3[0],idItemAbierto);
                                    resHTML += escape("\n<tr class=\"JSMenu_tr\"><td class=\"JSMenu_td\">\n<span class=\"JSMenu_hoja\" "+ attColor + " onmouseout=\"javascript:this.style.backgroundColor='transparent';\" onmouseover=\"javascript:this.style.backgroundColor='Chartreuse';\" onclick=\"JSMenu_click('" + camposN3[0] + "','" + camposN3[2] + "');\">");
                                    resHTML += escape( camposN3[1] );
                                    resHTML += escape("</span>");
                                }
                                resHTML += escape("\n</table>");                // CIERRE BISNIETOS
                            } else {
                                attColor = attStyleColor(camposN2[0],idItemAbierto);
                                resHTML += escape("\n<tr class=\"JSMenu_tr\"><td class=\"JSMenu_td\">\n<span class=\"JSMenu_hoja\" "+ attColor + " onmouseout=\"javascript:this.style.backgroundColor='transparent';\" onmouseover=\"javascript:this.style.backgroundColor='Chartreuse';\" onclick=\"JSMenu_click('" + camposN2[0] + "','" + camposN2[2] + "');\">");
                                resHTML += escape( camposN2[1] );
                                resHTML += escape("</span>");
                            }
                            resHTML += escape("\n</td></tr>");
                        }
                        resHTML += escape("\n</table>");                // CIERRE NIETOS
                        /////////////////////////////
                    } else {
                        attColor = attStyleColor(camposN1[0],idItemAbierto);
                        resHTML += escape("\n<tr class=\"JSMenu_tr\"><td class=\"JSMenu_td\">\n<span class=\"JSMenu_hoja\" "+ attColor + " onmouseout=\"javascript:this.style.backgroundColor='transparent';\" onmouseover=\"javascript:this.style.backgroundColor='Chartreuse';\" onclick=\"JSMenu_click('" + camposN1[0] + "','" + camposN1[2] + "');\">");
                        resHTML += escape( camposN1[1] );
                        resHTML += escape("</span>");
                    }
                    resHTML += escape("\n</td></tr>");
                }
                resHTML += escape("\n</table>");                // CIERRE HIJOS
                /////////////////////////////
            } else {
                attColor = attStyleColor(camposN0[0],idItemAbierto);
                resHTML += escape("\n<tr class=\"JSMenu_tr\"><td class=\"JSMenu_td\">\n<span class=\"JSMenu_hoja\" "+ attColor + " onmouseout=\"javascript:this.style.backgroundColor='transparent';\" onmouseover=\"javascript:this.style.backgroundColor='Chartreuse';\" onclick=\"JSMenu_click('" + camposN0[0] + "','" + camposN0[2] + "');\">");
                resHTML += escape( camposN0[1] );
                resHTML += escape("</span>");
            }
            resHTML += escape("\n</td></tr>");
        }
        resHTML += escape("\n</table>");                // CIERRE GENERAL
        //////////////////////////
        return unescape(resHTML);
    }

    function JSMenu_rtvHijos( itemPadre, itemsSgteNivel ) {
        var hijos = new Array();
        var campos = itemPadre.split('|',3);
        var nvPadre = campos[0];
        var n = nvPadre.length;
        // alert( "TRATAR: " + nvPadre + ", de longitud: " + n );
        for (var i=0; i < itemsSgteNivel.length; i++ ) {
            campos = itemsSgteNivel[i].split('|',3);
            var trozoHijo = campos[0].substr(0,n);
            if ( trozoHijo == nvPadre ) {hijos.push( itemsSgteNivel[i] );}
        }
        return hijos;
    }

    function attDisplay( idItem, idItemAbierto ) {
        var valorInicial = "none";

        // ACTIVAR SI EN ESTA APLICACIÓN PREFIERO QUE SIEMPRE SALGA TODITO ABIERTO:
        // valorInicial = "block";

        var atributo = "style=\"display: " + valorInicial + ";\""; 
        var lon = idItem.length;
        var ref = idItemAbierto.substr(0,lon);
        if ( idItem == ref ) {
            atributo = "style=\"display: block;\"";
        }
        return atributo;
    }

    function attStyleColor( idItem, idItemAbierto ) {
        var atributo = "";
        if ( idItem == idItemAbierto ) {
            atributo = "style=\"color: blue;\"";
        }
        return atributo;
    }

    function JSMenu_bascular( eDisparador, id ) {
      var eLanzado = document.getElementById( id );
      if (eLanzado==null || eLanzado=='undefined') { return; }
      if (eLanzado.style.display=="none"){
        eLanzado.style.display="block";
        eDisparador.style.backgroundImage = "url(./res/guionAbierto.ico)";
      } else {
        eLanzado.style.display="none";
        eDisparador.style.backgroundImage = "url(./res/guionCerrado.ico)";
      }
      eDisparador.style.backgroundRepeat="no-repeat";
    }
