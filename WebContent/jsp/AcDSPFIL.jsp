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

<html:form action="/AcDSPFIL_A.do" style="margin: 10px 10px;">

<div class="container">       

  <div id="content" class="col-sm-12">
 
	<div class="panel panel-default">

		<div class="panel-heading">
		 
			<!-- menu -->
			<div class="btn-group pull-left">
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.refrescar"/>" data-rel="tooltip" onclick="setOpcion('AcRCD_AF','Filtrar', null);"><span class="glyphicon glyphicon-refresh" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.retroceder"/>" data-rel="tooltip" onclick="setOpcion('AcRCD_AF','RtPg', null);"><span class="glyphicon glyphicon-chevron-left" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.avanzar"/>" data-rel="tooltip" onclick="setOpcion('AcRCD_AF','AvPg', null);"><span class="glyphicon glyphicon-chevron-right" ></span></a>
				<!-- <a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.grabar"/>" data-rel="tooltip" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar"/>',this)==true ) setOpcion('AcRCD_AF','Grabar', null);"><span class="glyphicon glyphicon-ok" ></span></a> -->                
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.nuevo"/>" data-rel="tooltip" onclick="setOpcion('AcRCD_AF','Nuevo', null);"><span class="glyphicon glyphicon-file" ></i></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.marcar_todos"/>" data-rel="tooltip" onclick="setOpcion('AcRCD_AF','MarcarTodo', null);"><span class="glyphicon glyphicon-check" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.desmarcar_todos"/>" data-rel="tooltip" onclick="setOpcion('AcRCD_AF','DesMarcarTodo', null);"><span class="glyphicon glyphicon-unchecked" ></span></a>                                                           
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
				<html:select styleClass="form-control" property="filasGrid" onchange="setOpcion('AcRCD_AF','ChgFilasGrid', null);">
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
<th style="text-align: right;">serial</th>
<th style="text-align: left;">location_id</th>
<th style="text-align: left;">LO_name</th>
<th style="text-align: left;">computername</th>
<th style="text-align: left;">filename</th>
<th style="text-align: left;">content</th>
<th style="text-align: left;">json</th>
<th style="text-align: left;">aaaa_mm</th>
<th style="text-align: left;">aaaa_mm_dd</th>
<th style="text-align: left;">aaaa_mm_dd_hh</th>
<th style="text-align: left;">aaaa_mm_dd_hh_m0</th>
<th style="text-align: left;">aaaa_mm_dd_hh_mm</th>
<th style="text-align: left;">aaaa_mm_dd_hh_mm_ss</th>
			</tr>
			<tr>
				<td><br /></td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_sincro" maxlength="20" style="width: 140px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_mark" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_is_deleted" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_author" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_serial" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_location_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_LO_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_computername" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_filename" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_content" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_json" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_aaaa_mm" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_aaaa_mm_dd" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_aaaa_mm_dd_hh" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_aaaa_mm_dd_hh_m0" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_aaaa_mm_dd_hh_mm" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="ac_filtro.ac_aaaa_mm_dd_hh_mm_ss" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			</tr>
			<!-- -----------------------------------------------------------------------------------------  -->
			<!-- Para hacer editable cualquier campo del grid, cambiar su "html:hidden" por "html:text"     -->
			<!-- descomentar botón "Grabar" de botonera y actualizar el código del action "xxDSPFIL_A.java: -->
			<!--        persistirPosiblesCambios(...)"                                                      -->
			<!-- ATENCIÓN: el constructor de su ActionForm se llama para rellenar los valores recogidos     -->
			<!-- y es ahí donde se debe DIMENSIONAR el array "grid" que los traspasará al Action.           -->
			<!-- -----------------------------------------------------------------------------------------  -->
			<logic:iterate indexId="gridIdx" id="grid" name="AcRCD_AF" property="grid">
				<tr id="laFila${gridIdx}">
					<td>
						<div class="table-btn">
							<html:multibox property="clavesMarcadas"  value="${grid.ac_serial}" onclick="javascript:multiboxClick('${gridIdx}');"/>
							<script type="text/javascript">multiboxClick('${gridIdx}');</script>
							<html:hidden name="grid" property="chg" indexed="true" />
							&nbsp;&nbsp;
							<a href="#" title="<bean:message key="common.client.modificar"/>" data-rel="tooltip" class="icon" onclick="setOpcion('AcRCD_AF','Edit${grid.ac_serial}', null );">
							    <i class="glyphicon glyphicon-pencil"></i>
							</a>
						</div>
					</td>
<td>&nbsp;<bean:write name="grid" property="ac_sincro"/><html:hidden name="grid" property="ac_sincro" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- sincro -->
<td>&nbsp;<bean:write name="grid" property="ac_mark"/><html:hidden name="grid" property="ac_mark" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- mark -->
<td>&nbsp;<bean:write name="grid" property="ac_is_deleted"/><html:hidden name="grid" property="ac_is_deleted" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- is_deleted -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_author"/>',30));</script><html:hidden name="grid" property="ac_author" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- author -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="ac_serial"/><html:hidden name="grid" property="ac_serial" indexed="true" />&nbsp;</td>   <!-- serial -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_location_id"/>',30));</script><html:hidden name="grid" property="ac_location_id" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- location_id -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_LO_name"/>',30));</script><html:hidden name="grid" property="ac_LO_name" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- LO_name -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_computername"/>',30));</script><html:hidden name="grid" property="ac_computername" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- computername -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_filename"/>',30));</script><html:hidden name="grid" property="ac_filename" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- filename -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_content"/>',30));</script><html:hidden name="grid" property="ac_content" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- content -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_json"/>',30));</script><html:hidden name="grid" property="ac_json" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- json -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_aaaa_mm"/>',30));</script><html:hidden name="grid" property="ac_aaaa_mm" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- aaaa_mm -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_aaaa_mm_dd"/>',30));</script><html:hidden name="grid" property="ac_aaaa_mm_dd" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- aaaa_mm_dd -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_aaaa_mm_dd_hh"/>',30));</script><html:hidden name="grid" property="ac_aaaa_mm_dd_hh" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- aaaa_mm_dd_hh -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_aaaa_mm_dd_hh_m0"/>',30));</script><html:hidden name="grid" property="ac_aaaa_mm_dd_hh_m0" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- aaaa_mm_dd_hh_m0 -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_aaaa_mm_dd_hh_mm"/>',30));</script><html:hidden name="grid" property="ac_aaaa_mm_dd_hh_mm" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- aaaa_mm_dd_hh_mm -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="ac_aaaa_mm_dd_hh_mm_ss"/>',30));</script><html:hidden name="grid" property="ac_aaaa_mm_dd_hh_mm_ss" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.ac_serial}';" />&nbsp;</td>   <!-- aaaa_mm_dd_hh_mm_ss -->
				</tr>
			</logic:iterate>
		</table>

  	</div><!--/.panel-->

  </div><!--/#content-->

</div><!--/.container-->  

<!-- modal -->
<%@include file="../inc/inc_AcDSPFIL_Flotante.jsp"%>

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
