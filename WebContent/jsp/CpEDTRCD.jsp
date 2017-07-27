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

	<html:form action="/CpEDTRCD_A.do" styleClass="form-horizontal">

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
				<html:text maxlength="20" styleId="textinput" styleClass="form-control" property="cp_sincro"  style="width: 140px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">mark&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="cp_mark"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">is_deleted&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="cp_is_deleted"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">author&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="cp_author"  style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>location_id&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="cp_location_id"  disabled="true" style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">LO_name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_LO_name"  disabled="true" style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">LO_address&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_LO_address"  disabled="true" style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">LO_city&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_LO_city"  disabled="true" style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">LO_postal_code&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="cp_LO_postal_code"  disabled="true" style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">LO_lat&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="cp_LO_lat"  disabled="true" style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">LO_lon&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="cp_LO_lon"  disabled="true" style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>cockpit_id&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="cp_cockpit_id"  disabled="true" style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">serial_number&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_serial_number"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_name"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">isBlocked&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="cp_isBlocked"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">asignation_order&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="cp_asignation_order"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">install_date&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="cp_install_date"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">reset_date_used&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="cp_reset_date_used"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">hours_used&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="cp_hours_used"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">note&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_note"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">comment&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_comment"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">observation&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_observation"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">warning&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_warning"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">contact_service&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="cp_contact_service"  style="width: 700px;"/>
			</div>
		</div>

					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3">
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.gestion_lista"/>" onclick="setOpcion('CpRCD_AF','Cerrar',null);"> 
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.leer"/>" onclick="setOpcion('CpRCD_AF','LeerReg',null);"> 
							<input class="btn btn-info"  type="button" value="<bean:message key="common.client.suprimir"/>" onclick="if ( confirmar('<bean:message key="common.client.pregunta_suprimir"/>',this)==true ) setOpcion('CpRCD_AF','Suprimir',null);">
							<input class="btn btn-primary" type="button" value="<bean:message key="common.client.grabar"/>"   onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar"/>',this)==true ) setOpcion('CpRCD_AF','CambiarReg',null);">
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
		
	<!-- <html:hidden property="cp_sincro"/> sincro -->
	<!-- <html:hidden property="cp_mark"/> mark -->
	<!-- <html:hidden property="cp_is_deleted"/> is_deleted -->
	<!-- <html:hidden property="cp_author"/> author -->
	     <html:hidden property="cp_location_id"/> <!-- location_id -->
	     <html:hidden property="cp_LO_name"/> <!-- LO_name -->
	     <html:hidden property="cp_LO_address"/> <!-- LO_address -->
	     <html:hidden property="cp_LO_city"/> <!-- LO_city -->
	     <html:hidden property="cp_LO_postal_code"/> <!-- LO_postal_code -->
	     <html:hidden property="cp_LO_lat"/> <!-- LO_lat -->
	     <html:hidden property="cp_LO_lon"/> <!-- LO_lon -->
	     <html:hidden property="cp_cockpit_id"/> <!-- cockpit_id -->
	<!-- <html:hidden property="cp_serial_number"/> serial_number -->
	<!-- <html:hidden property="cp_name"/> name -->
	<!-- <html:hidden property="cp_isBlocked"/> isBlocked -->
	<!-- <html:hidden property="cp_asignation_order"/> asignation_order -->
	<!-- <html:hidden property="cp_install_date"/> install_date -->
	<!-- <html:hidden property="cp_reset_date_used"/> reset_date_used -->
	<!-- <html:hidden property="cp_hours_used"/> hours_used -->
	<!-- <html:hidden property="cp_note"/> note -->
	<!-- <html:hidden property="cp_comment"/> comment -->
	<!-- <html:hidden property="cp_observation"/> observation -->
	<!-- <html:hidden property="cp_warning"/> warning -->
	<!-- <html:hidden property="cp_contact_service"/> contact_service -->

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
