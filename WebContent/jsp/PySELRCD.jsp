<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="./script/rutinas.js"></script>
        <script type="text/javascript">
            function persistePeticionario() {
                // Extraigo cadena de los parámetros de entrada:
                var v = (window.location.search.length>0)?window.location.search.substring(1):"";
                // aislo cada parámetro:
                parms = v.split('&');
                for (i=0;i<parms.length;i++) {
                    parm = parms[i].split('=');
                    // y de cada uno, su nombre y su valor:
                    if ( parm.length == 2 ) {
                        // persisto los datos de retorno:
                        if ( parm[0].toLowerCase() == 'form' )    
                            window.document.forms['PyRCD_AF'].elements["retFormulario"].value = parm[1];
                        if ( parm[0].toLowerCase() == 'element' ) 
                            window.document.forms['PyRCD_AF'].elements["retElemento"].value   = parm[1];
                        if ( parm[0].toLowerCase() == 'value' ) 
                            window.document.forms['PyRCD_AF'].elements["valorInicial"].value   = parm[1];
                    }
                }
                // Si es primera ejecución...(Si se le llamó con parámetros...)
                if ( parms.length > 1 ) {
                    // Inicializo primer restrictor con 'valor inicial' recibido:
                    window.document.forms['PyRCD_AF'].elements["py_filtro.py_sincro"].value   =                
                    window.document.forms['PyRCD_AF'].elements["valorInicial"].value;
                }
            }
            function setRetorno( retValor ) {
                var retFormulario = window.document.forms['PyRCD_AF'].elements["retFormulario"].value;
                var retElemento   = window.document.forms['PyRCD_AF'].elements["retElemento"].value;
                if ( retFormulario.length > 0 && retElemento.length > 0 ) {
                    var formulario = "window.parent.document.forms['" + retFormulario + "']";
                    var destino = formulario + ".elements['" + retElemento + "'].value";
                    if ( retValor.length > 0 )
                        eval( destino + " = '" + retValor +"'" );
                    eval( formulario + ".elements['opcionPantalla'].value = 'retornoSelect'" );
                    eval( formulario + ".submit()" );
                }
            }
            var bckF, bckC;
            function onTdOver( oTD )  { bckF=oTD.style.backgroundColor;bckC=oTD.style.color; oTD.style.backgroundColor='#1c9dda';oTD.style.color='#eeeeee'; }
            function onTdOut( oTD )   { oTD.style.backgroundColor=bckF;oTD.style.color=bckC; }
                               </script>
        <title>PayPalTokens</title>

		<!-- The styles -->    
		<link id="bs-css" href="./resBS/css/bootstrap3.css" rel="stylesheet">
		<link href="./resBS/css/styles.css" rel="stylesheet">
		
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		 <![endif]-->
		
    </head>
    <body onload="javascript:persistePeticionario();" style="background-color: lightyellow; opacity:0.2;" onload="$('body').fadeTo('slow',1);">
        <html:form action="/PySELRCD_A.do" style="margin: 10px 10px;">
            <table>
                <thead>
                    <tr>
                        <td colspan="100">
                            <fieldset style="text-align: left; height: 50px;">
                                <div style="font-size: 20px;">&nbsp;PayPalTokens</div>
                                &nbsp;
					            <div class="pull-left">
									<div class="btn-group">
					                <a href="#" class="btn btn-default btn-round" title="<bean:message key="common.client.salir"/>" data-rel="tooltip" onclick="setRetorno('');"><span class="glyphicon glyphicon-off" ></span></a>
					                <a href="#" class="btn btn-default btn-round" title="<bean:message key="common.client.refrescar"/>" data-rel="tooltip" onclick="setOpcion('PyRCD_AF','Filtrar', null);"><span class="glyphicon glyphicon-refresh" ></span></a>
					                <a href="#" class="btn btn-default btn-round" title="<bean:message key="common.client.retroceder"/>" data-rel="tooltip" onclick="setOpcion('PyRCD_AF','RtPg', null);"><span class="glyphicon glyphicon-chevron-left" ></span></a>
					                <a href="#" class="btn btn-default btn-round" title="<bean:message key="common.client.avanzar"/>" data-rel="tooltip" onclick="setOpcion('PyRCD_AF','AvPg', null);"><span class="glyphicon glyphicon-chevron-right" ></span></a>
									</div>
								</div>
                                
					            <div class="pull-right">
	                                &nbsp;(${cfgPantalla.filasTotales} filas)&nbsp;
	                                <bean:write filter="false" name="cfgPantalla" property="paginado_innerHTML"/>
	                                <html:hidden property="logon_USR"/><html:hidden property="logon_HSH"/>
	                                <html:hidden property="opcionPantalla"/><html:hidden property="opcionJSMenu"/>
	                                <html:hidden property="filaInicioGrid"/>
	                                <html:hidden property="retFormulario"/>
	                                <html:hidden property="retElemento"/>
	                                <html:hidden property="valorInicial"/>
	                                
	                                &nbsp;
	                                <html:select styleClass="form-control" property="filasGrid" onchange="setOpcion('PyRCD_AF','ChgFilasGrid', null);">
	                                    <html:optionsCollection name="lstLineasPantalla" value="key" label="value"/>
	                                </html:select>
						        </div>
                            </fieldset>
                        </td>
                    </tr>
                    <tr><td><br/></td></tr>
                </thead>
            </table>
			<!-- errors -->
			<div id="errorcillos" class="row">
				<html:errors property="error" />
			</div>
            <table class="table table-nonfluid table-striped table-curved table-hover">
                <tr>
<th style="text-align: left;">sincro</th>
<th style="text-align: left;">mark</th>
<th style="text-align: left;">is_deleted</th>
<th style="text-align: left;">author</th>
<th style="text-align: left;">user_id</th>
<th style="text-align: left;">US_first_name</th>
<th style="text-align: left;">US_last_name</th>
<th style="text-align: left;">reservation_id</th>
<th style="text-align: left;">paypal_token_id</th>
<th style="text-align: left;">paypal_usr</th>
<th style="text-align: left;">paypal_pwd</th>
<th style="text-align: left;">paypal_signature</th>
<th style="text-align: left;">stsProceso</th>
<th style="text-align: left;">json</th>
                </tr>
                <tr>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_sincro" maxlength="20" style="width: 140px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_mark" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_is_deleted" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_author" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_user_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_US_first_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_US_last_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_reservation_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_paypal_token_id" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_paypal_usr" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_paypal_pwd" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_paypal_signature" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_stsProceso" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="py_filtro.py_json" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
                </tr>
                <logic:iterate indexId="gridIdx" id="grid" name="PyRCD_AF" property="grid">
                    <tr id="laFila${gridIdx}">
                        
<td class="tbceldadetalle"><bean:write name="grid" property="py_sincro"/><br/></td>   <!-- sincro -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_mark"/><br/></td>   <!-- mark -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_is_deleted"/><br/></td>   <!-- is_deleted -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_author"/><br/></td>   <!-- author -->
<td class="tbceldadetalle" onmouseover="onTdOver(this);" onmouseout="onTdOut(this);" onclick="javascript:setRetorno( '<bean:write name="grid" property="py_user_id"/>' );"><bean:write name="grid" property="py_user_id"/><br/></td>   <!-- user_id -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_US_first_name"/><br/></td>   <!-- US_first_name -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_US_last_name"/><br/></td>   <!-- US_last_name -->
<td class="tbceldadetalle" onmouseover="onTdOver(this);" onmouseout="onTdOut(this);" onclick="javascript:setRetorno( '<bean:write name="grid" property="py_reservation_id"/>' );"><bean:write name="grid" property="py_reservation_id"/><br/></td>   <!-- reservation_id -->
<td class="tbceldadetalle" onmouseover="onTdOver(this);" onmouseout="onTdOut(this);" onclick="javascript:setRetorno( '<bean:write name="grid" property="py_paypal_token_id"/>' );"><bean:write name="grid" property="py_paypal_token_id"/><br/></td>   <!-- paypal_token_id -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_paypal_usr"/><br/></td>   <!-- paypal_usr -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_paypal_pwd"/><br/></td>   <!-- paypal_pwd -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_paypal_signature"/><br/></td>   <!-- paypal_signature -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_stsProceso"/><br/></td>   <!-- stsProceso -->
<td class="tbceldadetalle"><bean:write name="grid" property="py_json"/><br/></td>   <!-- json -->
                    </tr>
                </logic:iterate>
            </table>

		<!-- external javascript for ui-->       
		<!-- bootstrap -->
		<script src="./resBS/js/jquery-1.10.2.min.js"></script>
		<script src="./resBS/js/bootstrap3.min.js"></script>  
		<!-- drag&drop -->
		<script src="./resBS/js/jquery-ui-1.10.4.custom.min.js"></script>
		
		<script type="text/javascript">
		   $(function() {
		    $('[data-rel="tooltip"]').tooltip(
		    {'placement': 'top'}
		    ); 
		});
		
		$(function() {               
		    $( ".modal-content" ).draggable({ handle: ".modal-header" });
		    // $( "div" ).disableSelection();
		  });

		$( "#errorcillos" ).hide(); $( "#errorcillos" ).slideDown( "slow" );

		</script>
		
        </html:form>
    </body>
</html>
