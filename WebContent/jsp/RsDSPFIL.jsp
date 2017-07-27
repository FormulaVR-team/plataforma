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

<html:form action="/RsDSPFIL_A.do" style="margin: 10px 10px;">

<div class="container">       

  <div id="content" class="col-sm-12">
 
	<div class="panel panel-default">

		<div class="panel-heading">
		 
			<!-- menu -->
			<div class="btn-group pull-left">
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.refrescar"/>" data-rel="tooltip" onclick="setOpcion('RsRCD_AF','Filtrar', null);"><span class="glyphicon glyphicon-refresh" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.retroceder"/>" data-rel="tooltip" onclick="setOpcion('RsRCD_AF','RtPg', null);"><span class="glyphicon glyphicon-chevron-left" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.avanzar"/>" data-rel="tooltip" onclick="setOpcion('RsRCD_AF','AvPg', null);"><span class="glyphicon glyphicon-chevron-right" ></span></a>
				<!-- <a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.grabar"/>" data-rel="tooltip" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar"/>',this)==true ) setOpcion('RsRCD_AF','Grabar', null);"><span class="glyphicon glyphicon-ok" ></span></a> -->                
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.nuevo"/>" data-rel="tooltip" onclick="setOpcion('RsRCD_AF','Nuevo', null);"><span class="glyphicon glyphicon-file" ></i></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.marcar_todos"/>" data-rel="tooltip" onclick="setOpcion('RsRCD_AF','MarcarTodo', null);"><span class="glyphicon glyphicon-check" ></span></a>
				<a href="#" class="btn btn-default btn-round" title="<bean:message   key="common.client.desmarcar_todos"/>" data-rel="tooltip" onclick="setOpcion('RsRCD_AF','DesMarcarTodo', null);"><span class="glyphicon glyphicon-unchecked" ></span></a>                                                           
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
				<html:select styleClass="form-control" property="filasGrid" onchange="setOpcion('RsRCD_AF','ChgFilasGrid', null);">
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
				<td><br /></td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_sincro" maxlength="20" style="width: 140px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_mark" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_is_deleted" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_author" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_reservation_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_location_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_LO_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_user_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_US_country_id" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_US_nick" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_US_avatar" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_US_is_admin" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_US_first_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_US_last_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_product_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_name" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_deadline" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_isPercent" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><br />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_duration_minutes" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_quantity" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_product_id2" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_name2" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_deadline2" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_isPercent2" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><br />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_product_id3" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_name3" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_deadline3" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_PT_isPercent3" maxlength="1" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><br />
			</td>
			<td><br />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_currency" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_phone" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_pay_status" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_start_date" maxlength="10" style="width: 70px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_start_time" maxlength="4" style="width: 30px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_duration_minutes" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_places" maxlength="5" style="width: 35px; text-align: right;" onkeypress="return soloNumeros(event);" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_coupon_id" maxlength="50" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_executed_at" maxlength="20" style="width: 140px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_note" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_comment" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			<td><html:text styleClass="form-control input-sm" property="rs_filtro.rs_json" maxlength="100" style="width: 200px; text-align: left;" onclick="this.select();" />
			</td>
			</tr>
			<!-- -----------------------------------------------------------------------------------------  -->
			<!-- Para hacer editable cualquier campo del grid, cambiar su "html:hidden" por "html:text"     -->
			<!-- descomentar botón "Grabar" de botonera y actualizar el código del action "xxDSPFIL_A.java: -->
			<!--        persistirPosiblesCambios(...)"                                                      -->
			<!-- ATENCIÓN: el constructor de su ActionForm se llama para rellenar los valores recogidos     -->
			<!-- y es ahí donde se debe DIMENSIONAR el array "grid" que los traspasará al Action.           -->
			<!-- -----------------------------------------------------------------------------------------  -->
			<logic:iterate indexId="gridIdx" id="grid" name="RsRCD_AF" property="grid">
				<tr id="laFila${gridIdx}">
					<td>
						<div class="table-btn">
							<html:multibox property="clavesMarcadas"  value="${grid.rs_reservation_id}" onclick="javascript:multiboxClick('${gridIdx}');"/>
							<script type="text/javascript">multiboxClick('${gridIdx}');</script>
							<html:hidden name="grid" property="chg" indexed="true" />
							&nbsp;&nbsp;
							<a href="#" title="<bean:message key="common.client.modificar"/>" data-rel="tooltip" class="icon" onclick="setOpcion('RsRCD_AF','Edit${grid.rs_reservation_id}', null );">
							    <i class="glyphicon glyphicon-pencil"></i>
							</a>
						</div>
					</td>
<td>&nbsp;<bean:write name="grid" property="rs_sincro"/><html:hidden name="grid" property="rs_sincro" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- sincro -->
<td>&nbsp;<bean:write name="grid" property="rs_mark"/><html:hidden name="grid" property="rs_mark" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- mark -->
<td>&nbsp;<bean:write name="grid" property="rs_is_deleted"/><html:hidden name="grid" property="rs_is_deleted" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- is_deleted -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_author"/>',30));</script><html:hidden name="grid" property="rs_author" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- author -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_reservation_id"/>',30));</script><html:hidden name="grid" property="rs_reservation_id" indexed="true" />&nbsp;</td>   <!-- reservation_id -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_location_id"/>',30));</script><html:hidden name="grid" property="rs_location_id" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- location_id -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_LO_name"/>',30));</script><html:hidden name="grid" property="rs_LO_name" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- LO_name -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_user_id"/>',30));</script><html:hidden name="grid" property="rs_user_id" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- user_id -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_US_country_id"/><html:hidden name="grid" property="rs_US_country_id" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- US_country_id -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_US_nick"/>',30));</script><html:hidden name="grid" property="rs_US_nick" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- US_nick -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_US_avatar"/>',30));</script><html:hidden name="grid" property="rs_US_avatar" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- US_avatar -->
<td>&nbsp;<bean:write name="grid" property="rs_US_is_admin"/><html:hidden name="grid" property="rs_US_is_admin" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- US_is_admin -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_US_first_name"/>',30));</script><html:hidden name="grid" property="rs_US_first_name" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- US_first_name -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_US_last_name"/>',30));</script><html:hidden name="grid" property="rs_US_last_name" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- US_last_name -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_product_id"/>',30));</script><html:hidden name="grid" property="rs_product_id" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- product_id -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_PT_name"/>',30));</script><html:hidden name="grid" property="rs_PT_name" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_name -->
<td>&nbsp;<bean:write name="grid" property="rs_PT_deadline"/><html:hidden name="grid" property="rs_PT_deadline" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_deadline -->
<td>&nbsp;<bean:write name="grid" property="rs_PT_isPercent"/><html:hidden name="grid" property="rs_PT_isPercent" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_isPercent -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_PT_amount"/><html:hidden name="grid" property="rs_PT_amount" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_amount -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_PT_duration_minutes"/><html:hidden name="grid" property="rs_PT_duration_minutes" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_duration_minutes -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_quantity"/><html:hidden name="grid" property="rs_quantity" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- quantity -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_product_id2"/>',30));</script><html:hidden name="grid" property="rs_product_id2" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- product_id2 -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_PT_name2"/>',30));</script><html:hidden name="grid" property="rs_PT_name2" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_name2 -->
<td>&nbsp;<bean:write name="grid" property="rs_PT_deadline2"/><html:hidden name="grid" property="rs_PT_deadline2" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_deadline2 -->
<td>&nbsp;<bean:write name="grid" property="rs_PT_isPercent2"/><html:hidden name="grid" property="rs_PT_isPercent2" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_isPercent2 -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_PT_amount2"/><html:hidden name="grid" property="rs_PT_amount2" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_amount2 -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_product_id3"/>',30));</script><html:hidden name="grid" property="rs_product_id3" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- product_id3 -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_PT_name3"/>',30));</script><html:hidden name="grid" property="rs_PT_name3" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_name3 -->
<td>&nbsp;<bean:write name="grid" property="rs_PT_deadline3"/><html:hidden name="grid" property="rs_PT_deadline3" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_deadline3 -->
<td>&nbsp;<bean:write name="grid" property="rs_PT_isPercent3"/><html:hidden name="grid" property="rs_PT_isPercent3" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_isPercent3 -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_PT_amount3"/><html:hidden name="grid" property="rs_PT_amount3" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- PT_amount3 -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_amount"/><html:hidden name="grid" property="rs_amount" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- amount -->
<td>&nbsp;<bean:write name="grid" property="rs_currency"/><html:hidden name="grid" property="rs_currency" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- currency -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_phone"/>',30));</script><html:hidden name="grid" property="rs_phone" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- phone -->
<td>&nbsp;<bean:write name="grid" property="rs_pay_status"/><html:hidden name="grid" property="rs_pay_status" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- pay_status -->
<td>&nbsp;<bean:write name="grid" property="rs_start_date"/><html:hidden name="grid" property="rs_start_date" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- start_date -->
<td>&nbsp;<bean:write name="grid" property="rs_start_time"/><html:hidden name="grid" property="rs_start_time" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- start_time -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_duration_minutes"/><html:hidden name="grid" property="rs_duration_minutes" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- duration_minutes -->
<td style="text-align: right;">&nbsp;<bean:write name="grid" property="rs_places"/><html:hidden name="grid" property="rs_places" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- places -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_coupon_id"/>',30));</script><html:hidden name="grid" property="rs_coupon_id" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- coupon_id -->
<td>&nbsp;<bean:write name="grid" property="rs_executed_at"/><html:hidden name="grid" property="rs_executed_at" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- executed_at -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_note"/>',30));</script><html:hidden name="grid" property="rs_note" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- note -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_comment"/>',30));</script><html:hidden name="grid" property="rs_comment" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- comment -->
<td><script>document.write(recortarTexto('<bean:write name="grid" property="rs_json"/>',30));</script><html:hidden name="grid" property="rs_json" indexed="true" onchange="this.style.color='red';(document.getElementsByName('grid[${gridIdx}].chg'))[0].value='${grid.rs_reservation_id}';" />&nbsp;</td>   <!-- json -->
				</tr>
			</logic:iterate>
		</table>

  	</div><!--/.panel-->

  </div><!--/#content-->

</div><!--/.container-->  

<!-- modal -->
<%@include file="../inc/inc_RsDSPFIL_Flotante.jsp"%>

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
