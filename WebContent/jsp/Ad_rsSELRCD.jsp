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
                            window.document.forms['Ad_rsRCD_AF'].elements["retFormulario"].value = parm[1];
                        if ( parm[0].toLowerCase() == 'element' ) 
                            window.document.forms['Ad_rsRCD_AF'].elements["retElemento"].value   = parm[1];
                        if ( parm[0].toLowerCase() == 'value' ) 
                            window.document.forms['Ad_rsRCD_AF'].elements["valorInicial"].value   = parm[1];
                    }
                }
                // Si es primera ejecución...(Si se le llamó con parámetros...)
                if ( parms.length > 1 ) {
                    // Inicializo primer restrictor con 'valor inicial' recibido:
                    window.document.forms['Ad_rsRCD_AF'].elements["ad_rs_filtro.ad_rs_sincro"].value   =                
                    window.document.forms['Ad_rsRCD_AF'].elements["valorInicial"].value;
                }
            }
            function setRetorno( retValor ) {
                var retFormulario = window.document.forms['Ad_rsRCD_AF'].elements["retFormulario"].value;
                var retElemento   = window.document.forms['Ad_rsRCD_AF'].elements["retElemento"].value;
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
        <title>reservations</title>

		<!-- The styles -->    
		<link id="bs-css" href="./resBS/css/bootstrap3.css" rel="stylesheet">
		<link href="./resBS/css/styles.css" rel="stylesheet">
		
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		 <![endif]-->
		
    </head>
    <body onload="javascript:persistePeticionario();" style="background-color: lightyellow; opacity:0.2;" onload="$('body').fadeTo('slow',1);">
        <html:form action="/Ad_rsSELRCD_A.do" style="margin: 10px 10px;">
            <table>
                <thead>
                    <tr>
                        <td colspan="100">
                            <fieldset style="text-align: left; height: 50px;">
                                <div style="font-size: 20px;">&nbsp;reservations</div>
                                &nbsp;
					            <div class="pull-left">
									<div class="btn-group">
					                <a href="#" class="btn btn-default btn-round" title="<bean:message key="common.client.salir"/>" data-rel="tooltip" onclick="setRetorno('');"><span class="glyphicon glyphicon-off" ></span></a>
					                <a href="#" class="btn btn-default btn-round" title="<bean:message key="common.client.refrescar"/>" data-rel="tooltip" onclick="setOpcion('Ad_rsRCD_AF','Filtrar', null);"><span class="glyphicon glyphicon-refresh" ></span></a>
					                <a href="#" class="btn btn-default btn-round" title="<bean:message key="common.client.retroceder"/>" data-rel="tooltip" onclick="setOpcion('Ad_rsRCD_AF','RtPg', null);"><span class="glyphicon glyphicon-chevron-left" ></span></a>
					                <a href="#" class="btn btn-default btn-round" title="<bean:message key="common.client.avanzar"/>" data-rel="tooltip" onclick="setOpcion('Ad_rsRCD_AF','AvPg', null);"><span class="glyphicon glyphicon-chevron-right" ></span></a>
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
	                                <html:select styleClass="form-control" property="filasGrid" onchange="setOpcion('Ad_rsRCD_AF','ChgFilasGrid', null);">
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
<th style="text-align: left;">reservation_id</th>
<th style="text-align: left;">location_id</th>
<th style="text-align: left;">LO_name</th>
<th style="text-align: left;">user_id</th>
<th style="text-align: right;">US_country_id</th>
<th style="text-align: left;">US_nick</th>
<th style="text-align: left;">US_avatar</th>
<th style="text-align: left;">US_is_admin</th>
<th style="text-align: left;">US_first_name</th>
<th style="text-align: left;">US_last_name</th>
<th style="text-align: left;">product_id</th>
<th style="text-align: left;">PT_name</th>
<th style="text-align: left;">PT_deadline</th>
<th style="text-align: left;">PT_isPercent</th>
<th style="text-align: right;">PT_amount</th>
<th style="text-align: right;">PT_duration_minutes</th>
<th style="text-align: right;">quantity</th>
<th style="text-align: left;">product_id2</th>
<th style="text-align: left;">PT_name2</th>
<th style="text-align: left;">PT_deadline2</th>
<th style="text-align: left;">PT_isPercent2</th>
<th style="text-align: right;">PT_amount2</th>
<th style="text-align: left;">product_id3</th>
<th style="text-align: left;">PT_name3</th>
<th style="text-align: left;">PT_deadline3</th>
<th style="text-align: left;">PT_isPercent3</th>
<th style="text-align: right;">PT_amount3</th>
<th style="text-align: right;">amount</th>
<th style="text-align: left;">currency</th>
<th style="text-align: left;">phone</th>
<th style="text-align: left;">pay_status</th>
<th style="text-align: left;">start_date</th>
<th style="text-align: left;">start_time</th>
<th style="text-align: right;">duration_minutes</th>
<th style="text-align: right;">places</th>
<th style="text-align: left;">coupon_id</th>
<th style="text-align: left;">executed_at</th>
<th style="text-align: left;">note</th>
<th style="text-align: left;">comment</th>
<th style="text-align: left;">json</th>
                </tr>
                <tr>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_sincro" maxlength="20" style="width: 140px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_mark" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_is_deleted" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_author" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_reservation_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_location_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_LO_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_user_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_US_country_id" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_US_nick" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_US_avatar" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_US_is_admin" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_US_first_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_US_last_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_product_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_deadline" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_isPercent" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><br />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_duration_minutes" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_quantity" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_product_id2" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_name2" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_deadline2" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_isPercent2" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><br />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_product_id3" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_name3" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_deadline3" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_PT_isPercent3" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><br />
			</td>
			<td><br />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_currency" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_phone" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_pay_status" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_start_date" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_start_time" maxlength="4" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_duration_minutes" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_places" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_coupon_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_executed_at" maxlength="20" style="width: 140px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_note" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_comment" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ad_rs_filtro.ad_rs_json" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
                </tr>
                <logic:iterate indexId="gridIdx" id="grid" name="Ad_rsRCD_AF" property="grid">
                    <tr id="laFila${gridIdx}">
                        
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_sincro"/><br/></td>   <!-- sincro -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_mark"/><br/></td>   <!-- mark -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_is_deleted"/><br/></td>   <!-- is_deleted -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_author"/><br/></td>   <!-- author -->
<td class="tbceldadetalle" onmouseover="onTdOver(this);" onmouseout="onTdOut(this);" onclick="javascript:setRetorno( '<bean:write name="grid" property="ad_rs_reservation_id"/>' );"><bean:write name="grid" property="ad_rs_reservation_id"/><br/></td>   <!-- reservation_id -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_location_id"/><br/></td>   <!-- location_id -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_LO_name"/><br/></td>   <!-- LO_name -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_user_id"/><br/></td>   <!-- user_id -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_US_country_id"/><br/></td>   <!-- US_country_id -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_US_nick"/><br/></td>   <!-- US_nick -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_US_avatar"/><br/></td>   <!-- US_avatar -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_US_is_admin"/><br/></td>   <!-- US_is_admin -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_US_first_name"/><br/></td>   <!-- US_first_name -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_US_last_name"/><br/></td>   <!-- US_last_name -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_product_id"/><br/></td>   <!-- product_id -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_name"/><br/></td>   <!-- PT_name -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_deadline"/><br/></td>   <!-- PT_deadline -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_isPercent"/><br/></td>   <!-- PT_isPercent -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_PT_amount"/><br/></td>   <!-- PT_amount -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_PT_duration_minutes"/><br/></td>   <!-- PT_duration_minutes -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_quantity"/><br/></td>   <!-- quantity -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_product_id2"/><br/></td>   <!-- product_id2 -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_name2"/><br/></td>   <!-- PT_name2 -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_deadline2"/><br/></td>   <!-- PT_deadline2 -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_isPercent2"/><br/></td>   <!-- PT_isPercent2 -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_PT_amount2"/><br/></td>   <!-- PT_amount2 -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_product_id3"/><br/></td>   <!-- product_id3 -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_name3"/><br/></td>   <!-- PT_name3 -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_deadline3"/><br/></td>   <!-- PT_deadline3 -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_PT_isPercent3"/><br/></td>   <!-- PT_isPercent3 -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_PT_amount3"/><br/></td>   <!-- PT_amount3 -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_amount"/><br/></td>   <!-- amount -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_currency"/><br/></td>   <!-- currency -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_phone"/><br/></td>   <!-- phone -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_pay_status"/><br/></td>   <!-- pay_status -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_start_date"/><br/></td>   <!-- start_date -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_start_time"/><br/></td>   <!-- start_time -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_duration_minutes"/><br/></td>   <!-- duration_minutes -->
<td class="tbceldadetalle" style="text-align: right;"><bean:write name="grid" property="ad_rs_places"/><br/></td>   <!-- places -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_coupon_id"/><br/></td>   <!-- coupon_id -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_executed_at"/><br/></td>   <!-- executed_at -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_note"/><br/></td>   <!-- note -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_comment"/><br/></td>   <!-- comment -->
<td class="tbceldadetalle"><bean:write name="grid" property="ad_rs_json"/><br/></td>   <!-- json -->
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
