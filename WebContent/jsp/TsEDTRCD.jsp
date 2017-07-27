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

	<html:form action="/TsEDTRCD_A.do" styleClass="form-horizontal">

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
				<html:text maxlength="20" styleId="textinput" styleClass="form-control" property="ts_sincro"  style="width: 140px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">mark&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="ts_mark"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">is_deleted&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="ts_is_deleted"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">author&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="ts_author"  style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>reservation_id&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="ts_reservation_id"  disabled="true" style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_user_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="ts_RS_user_id"  disabled="true" style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_location_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="ts_RS_location_id"  disabled="true" style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_start_date&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ts_RS_start_date"  disabled="true" style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_start_time&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="4" styleId="textinput" styleClass="form-control" property="ts_RS_start_time"  disabled="true" style="width: 28px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_pay_status&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ts_RS_pay_status"  disabled="true" style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_product_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="ts_RS_product_id"  disabled="true" style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_quantity&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="ts_RS_quantity"  disabled="true" style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_duration_minutes&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="ts_RS_duration_minutes"  disabled="true" style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">RS_places&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="ts_RS_places"  disabled="true" style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>start_date&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ts_start_date"  disabled="true" style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>start_time&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="4" styleId="textinput" styleClass="form-control" property="ts_start_time"  disabled="true" style="width: 28px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">json&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="ts_json"  style="width: 700px;"/>
			</div>
		</div>

					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3">
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.gestion_lista"/>" onclick="setOpcion('TsRCD_AF','Cerrar',null);"> 
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.leer"/>" onclick="setOpcion('TsRCD_AF','LeerReg',null);"> 
							<input class="btn btn-info"  type="button" value="<bean:message key="common.client.suprimir"/>" onclick="if ( confirmar('<bean:message key="common.client.pregunta_suprimir"/>',this)==true ) setOpcion('TsRCD_AF','Suprimir',null);">
							<input class="btn btn-primary" type="button" value="<bean:message key="common.client.grabar"/>"   onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar"/>',this)==true ) setOpcion('TsRCD_AF','CambiarReg',null);">
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
		
	<!-- <html:hidden property="ts_sincro"/> sincro -->
	<!-- <html:hidden property="ts_mark"/> mark -->
	<!-- <html:hidden property="ts_is_deleted"/> is_deleted -->
	<!-- <html:hidden property="ts_author"/> author -->
	     <html:hidden property="ts_reservation_id"/> <!-- reservation_id -->
	     <html:hidden property="ts_RS_user_id"/> <!-- RS_user_id -->
	     <html:hidden property="ts_RS_location_id"/> <!-- RS_location_id -->
	     <html:hidden property="ts_RS_start_date"/> <!-- RS_start_date -->
	     <html:hidden property="ts_RS_start_time"/> <!-- RS_start_time -->
	     <html:hidden property="ts_RS_pay_status"/> <!-- RS_pay_status -->
	     <html:hidden property="ts_RS_product_id"/> <!-- RS_product_id -->
	     <html:hidden property="ts_RS_quantity"/> <!-- RS_quantity -->
	     <html:hidden property="ts_RS_duration_minutes"/> <!-- RS_duration_minutes -->
	     <html:hidden property="ts_RS_places"/> <!-- RS_places -->
	     <html:hidden property="ts_start_date"/> <!-- start_date -->
	     <html:hidden property="ts_start_time"/> <!-- start_time -->
	<!-- <html:hidden property="ts_json"/> json -->

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
