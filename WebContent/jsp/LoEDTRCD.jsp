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

	<html:form action="/LoEDTRCD_A.do" styleClass="form-horizontal">

		<div class="container">
			<!-- content starts -->
			<div id="content" class="col-sm-12">

				<fieldset>

					<!-- Form Name -->
					<legend>${cfgPantalla.tituloPantalla}</legend>

					<!-- errors -->
					<div class='form-group'>
						<div id="errorcillos" class="row col-md-offset-3 col-md-6 col-md-offset-3">
							<html:errors property="error" />
						</div>
					</div>

					<!-- Fields -->

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">sincro&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="20" styleId="textinput" styleClass="form-control" property="lo_sincro"  style="width: 140px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">mark&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="lo_mark"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">is_deleted&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="lo_is_deleted"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">author&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="lo_author"  style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>location_id&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="lo_location_id"  disabled="true" style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="lo_name"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">address&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="lo_address"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">city&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="lo_city"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">postal_code&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="lo_postal_code"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">province&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="lo_province"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">state_region&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="lo_state_region"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">country&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="lo_country"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">lat&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="lo_lat"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">lon&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="lo_lon"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">comment&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="lo_comment"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">json&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="lo_json"  style="width: 700px;"/>
			</div>
		</div>

					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3">
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.gestion_lista"/>" onclick="setOpcion('LoRCD_AF','Cerrar',null);"> 
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.leer"/>" onclick="setOpcion('LoRCD_AF','LeerReg',null);"> 
							<input class="btn btn-info"  type="button" value="<bean:message key="common.client.suprimir"/>" onclick="if ( confirmar('<bean:message key="common.client.pregunta_suprimir"/>',this)==true ) setOpcion('LoRCD_AF','Suprimir',null);">
							<input class="btn btn-primary" type="button" value="<bean:message key="common.client.grabar"/>"   onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar"/>',this)==true ) setOpcion('LoRCD_AF','CambiarReg',null);">
						</div>
					</div>

				</fieldset>

				<html:hidden property="logon_USR" />
				<html:hidden property="logon_HSH" />
				<html:hidden property="opcionPantalla" />
				<html:hidden property="opcionJSMenu" />

			</div>
			<!--/#content-->
		</div>
		<!--/.container-->

		<!-- Formato completo -->
		
	<!-- <html:hidden property="lo_sincro"/> sincro -->
	<!-- <html:hidden property="lo_mark"/> mark -->
	<!-- <html:hidden property="lo_is_deleted"/> is_deleted -->
	<!-- <html:hidden property="lo_author"/> author -->
	     <html:hidden property="lo_location_id"/> <!-- location_id -->
	<!-- <html:hidden property="lo_name"/> name -->
	<!-- <html:hidden property="lo_address"/> address -->
	<!-- <html:hidden property="lo_city"/> city -->
	<!-- <html:hidden property="lo_postal_code"/> postal_code -->
	<!-- <html:hidden property="lo_province"/> province -->
	<!-- <html:hidden property="lo_state_region"/> state_region -->
	<!-- <html:hidden property="lo_country"/> country -->
	<!-- <html:hidden property="lo_lat"/> lat -->
	<!-- <html:hidden property="lo_lon"/> lon -->
	<!-- <html:hidden property="lo_comment"/> comment -->
	<!-- <html:hidden property="lo_json"/> json -->

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
