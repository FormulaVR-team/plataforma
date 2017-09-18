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
<title>${cfgPantalla.tituloPantalla}</title>

<!-- The styles -->    
<link id="bs-css" href="./resBS/css/bootstrap3.css" rel="stylesheet">
<link href="./resBS/css/styles.css" rel="stylesheet">

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
 <![endif]-->

</head>
<body style="opacity:0.2;" onload="$('body').fadeTo('slow',1);">

<%@include file="../inc/inc_menuForm.jsp"%>

<html:form action="/CpDSPFIL_A.do" style="margin: 10px 10px;">

<div class="container">       

  <div id="content" class="col-sm-12">
 
	<div class="panel panel-default">

		<div class="panel-heading">
		 
			<!-- menu -->
			<div class="btn-group pull-left">
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.refrescar"/>" data-rel="tooltip" onclick="setOpcion('CpRCD_AF','Filtrar', null);"><span class="glyphicon glyphicon-refresh" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.retroceder"/>" data-rel="tooltip" onclick="setOpcion('CpRCD_AF','RtPg', null);"><span class="glyphicon glyphicon-chevron-left" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.avanzar"/>" data-rel="tooltip" onclick="setOpcion('CpRCD_AF','AvPg', null);"><span class="glyphicon glyphicon-chevron-right" ></span></a>
				<!-- <a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.grabar"/>" data-rel="tooltip" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar"/>',this)==true ) setOpcion('CpRCD_AF','Grabar', null);"><span class="glyphicon glyphicon-ok" ></span></a> -->                
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.nuevo"/>" data-rel="tooltip" onclick="setOpcion('CpRCD_AF','Nuevo', null);"><span class="glyphicon glyphicon-file" ></i></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.marcar_todos"/>" data-rel="tooltip" onclick="setOpcion('CpRCD_AF','MarcarTodo', null);"><span class="glyphicon glyphicon-check" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.desmarcar_todos"/>" data-rel="tooltip" onclick="setOpcion('CpRCD_AF','DesMarcarTodo', null);"><span class="glyphicon glyphicon-unchecked" ></span></a>                                                           
				<a href="#masOpciones" class="btn btn-default btn-round" title="<bean:message   key="common.client.mas_opciones"/>" data-rel="tooltip" data-toggle="modal" data-backdrop="false" class="label ttip_b"><span class="glyphicon glyphicon-comment" ></span></a>
			</div>
			
			<div class="btn">
				<ol class="breadcrumb">
					<li>
						<a href="javascript:setOpcion('menu_AF', 'GotoMenuPpal_A', null );"><bean:message  key="menu.client.home"/></a>
					</li>
					<li>
						<a href="#">${cfgPantalla.tituloPantalla}</a>
					</li>
				</ol>   
			</div>
			
			<div class="pull-right">
				&nbsp;(${cfgPantalla.filasTotales} <bean:message   key="common.client.filas"/>)&nbsp;
				<bean:write filter="false" name="cfgPantalla" property="paginado_innerHTML"/>
				<html:hidden property="logon_USR"/><html:hidden property="logon_HSH"/><html:hidden property="opcionPantalla"/><html:hidden property="opcionJSMenu"/><html:hidden property="filaInicioGrid"/>
				<html:select styleClass="form-control" property="filasGrid" onchange="setOpcion('CpRCD_AF','ChgFilasGrid', null);">
					<html:optionsCollection name="lstLineasPantalla" value="key" label="value"/>
				</html:select>           
			</div>
			
		</div> <!-- panel-heading -->
	  
		<div class="panel-body">
		   <h2 class="pull-left text-left">${cfgPantalla.tituloPantalla}</h2>
			<!-- errors -->
			<div id="errorcillos" class="row col-md-offset-3 col-md-6 col-md-offset-3">
				<html:errors property="error" />
			</div>
		</div> <!-- panel-body -->

		<!-- <table class="table table-nonfluid table-striped table-curved table-hover"> -->
		<table class="table table-striped table-curved table-hover table-conFondo">
			<tr>
				<th><br /></th>
<th style="text-align: left;">sincro</th>
<th style="text-align: left;">mark</th>
<th style="text-align: left;">is_deleted</th>
<th style="text-align: left;">author</th>
<th style="text-align: left;">location_id</th>
<th style="text-align: left;">LO_name</th>
<th style="text-align: left;">LO_address</th>
<th style="text-align: left;">LO_city</th>
<th style="text-align: left;">LO_postal_code</th>
<th style="text-align: right;">LO_lat</th>
<th style="text-align: right;">LO_lon</th>
<th style="text-align: left;">cockpit_id</th>
<th style="text-align: left;">serial_number</th>
<th style="text-align: left;">name</th>
<th style="text-align: left;">isBlocked</th>
<th style="text-align: right;">asignation_order</th>
<th style="text-align: left;">install_date</th>
<th style="text-align: left;">reset_date_used</th>
<th style="text-align: right;">hours_used</th>
<th style="text-align: left;">note</th>
<th style="text-align: left;">comment</th>
<th style="text-align: left;">observation</th>
<th style="text-align: left;">warning</th>
<th style="text-align: left;">contact_service</th>
<th style="text-align: left;">json</th>
			</tr>
			<tr>
				<td><br /></td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_sincro" maxlength="20" style="width: 140px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_mark" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_is_deleted" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_author" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_location_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_LO_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_LO_address" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_LO_city" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_LO_postal_code" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_LO_lat" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_LO_lon" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_cockpit_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_serial_number" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_isBlocked" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_asignation_order" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_install_date" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_reset_date_used" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_hours_used" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_note" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_comment" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_observation" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_warning" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_contact_service" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="cp_filtro.cp_json" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			</tr>
			<!-- -----------------------------------------------------------------------------------------  -->
			<!-- Para hacer editable cualquier campo del grid, cambiar su "html:hidden" por "html:text"     -->
			<!-- descomentar botón "Grabar" de botonera y actualizar el código del action "xxDSPFIL_A.java: -->
			<!--        persistirPosiblesCambios(...)"                                                      -->
			<!-- ATENCIÓN: el constructor de su ActionForm se llama para rellenar los valores recogidos     -->
			<!-- y es ahí donde se debe DIMENSIONAR el array "grid" que los traspasará al Action.           -->
			<!-- -----------------------------------------------------------------------------------------  -->
			<logic:iterate indexId="gridIdx" id="grid" name="CpRCD_AF" property="grid">
				<tr id="laFila${gridIdx}">
					<td>
						<div class="table-btn">
							<html:multibox property="clavesMarcadas"  value="${grid.cp_cockpit_id}^${grid.cp_location_id}" onclick="javascript:multiboxClick('${gridIdx}');"/>
							<script type="text/javascript">multiboxClick('${gridIdx}');</script>
							<html:hidden name="grid" property="chg" indexed="true" />
							&nbsp;&nbsp;
							<a href="#" title="<bean:message key="common.client.modificar"/>" data-rel="tooltip" class="icon" onclick="setOpcion('CpRCD_AF','Edit${grid.cp_cockpit_id}^${grid.cp_location_id}', null );">
							    <i class="glyphicon glyphicon-pencil"></i>
							</a>
						</div>
					</td>
<td>&nbsp;<bean:write name="grid" property="cp_sincro"/><html:hidden name="grid" property="cp_sincro" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- sincro -->
<td>&nbsp;<bean:write name="grid" property="cp_mark"/><html:hidden name="grid" property="cp_mark" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- mark -->
<td>&nbsp;<bean:write name="grid" property="cp_is_deleted"/><html:hidden name="grid" property="cp_is_deleted" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- is_deleted -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_author"/>',30));</script><html:hidden name="grid" property="cp_author" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- author -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_location_id"/>',30));</script><html:hidden name="grid" property="cp_location_id" indexed="true" />&nbsp;</td>   <!-- location_id -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_LO_name"/>',30));</script><html:hidden name="grid" property="cp_LO_name" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- LO_name -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_LO_address"/>',30));</script><html:hidden name="grid" property="cp_LO_address" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- LO_address -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_LO_city"/>',30));</script><html:hidden name="grid" property="cp_LO_city" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- LO_city -->
<td>&nbsp;<bean:write name="grid" property="cp_LO_postal_code"/><html:hidden name="grid" property="cp_LO_postal_code" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- LO_postal_code -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="cp_LO_lat"/><html:hidden name="grid" property="cp_LO_lat" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- LO_lat -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="cp_LO_lon"/><html:hidden name="grid" property="cp_LO_lon" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- LO_lon -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_cockpit_id"/>',30));</script><html:hidden name="grid" property="cp_cockpit_id" indexed="true" />&nbsp;</td>   <!-- cockpit_id -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_serial_number"/>',30));</script><html:hidden name="grid" property="cp_serial_number" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- serial_number -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_name"/>',30));</script><html:hidden name="grid" property="cp_name" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- name -->
<td>&nbsp;<bean:write name="grid" property="cp_isBlocked"/><html:hidden name="grid" property="cp_isBlocked" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- isBlocked -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="cp_asignation_order"/><html:hidden name="grid" property="cp_asignation_order" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- asignation_order -->
<td>&nbsp;<bean:write name="grid" property="cp_install_date"/><html:hidden name="grid" property="cp_install_date" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- install_date -->
<td>&nbsp;<bean:write name="grid" property="cp_reset_date_used"/><html:hidden name="grid" property="cp_reset_date_used" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- reset_date_used -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="cp_hours_used"/><html:hidden name="grid" property="cp_hours_used" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- hours_used -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_note"/>',30));</script><html:hidden name="grid" property="cp_note" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- note -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_comment"/>',30));</script><html:hidden name="grid" property="cp_comment" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- comment -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_observation"/>',30));</script><html:hidden name="grid" property="cp_observation" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- observation -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_warning"/>',30));</script><html:hidden name="grid" property="cp_warning" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- warning -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_contact_service"/>',30));</script><html:hidden name="grid" property="cp_contact_service" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- contact_service -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="cp_json"/>',30));</script><html:hidden name="grid" property="cp_json" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.cp_cockpit_id}^${grid.cp_location_id}';" />&nbsp;</td>   <!-- json -->
				</tr>
			</logic:iterate>
		</table>

  	</div><!--/.panel-->

  </div><!--/#content-->

</div><!--/.container-->  

<!-- modal -->
<%@include file="../inc/inc_CpDSPFIL_Flotante.jsp"%>

<!-- external javascript for ui-->       
<!-- bootstrap -->
<script src="./resBS/js/jquery-1.10.2.min.js"></script>
<script src="./resBS/js/bootstrap3.min.js"></script>  
<!-- drag&drop -->
<script src="./resBS/js/jquery-ui-1.10.4.custom.min.js"></script>

<script type="text/javascript">
$(function() { $('[data-rel="tooltip"]').tooltip( {'placement': 'top'} ); });
$(function() { $('[data-rel="tooltip-right"]').tooltip( {'placement': 'right'} ); });
$(function() { $('[data-rel="tooltip-left"]').tooltip( {'placement': 'left'} ); });
$(function() { $('[data-rel="tooltip-bottom"]').tooltip( {'placement': 'bottom'} ); });

$(function() { $( ".modal-content" ).draggable({ handle: ".modal-header" }); });

$( "#errorcillos" ).hide(); $( "#errorcillos" ).slideDown( "slow" );
</script>

</html:form>
</body>
</html>
