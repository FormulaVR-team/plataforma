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

	<html:form action="/EvEDTRCD_A.do" styleClass="form-horizontal">

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
				<html:text maxlength="20" styleId="textinput" styleClass="form-control" property="ev_sincro"  style="width: 140px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">mark&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="ev_mark"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">is_deleted&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleId="textinput" styleClass="form-control" property="ev_is_deleted"  style="width: 7px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">author&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="ev_author"  style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>event_id&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="ev_event_id"  disabled="true" style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">location_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleId="textinput" styleClass="form-control" property="ev_location_id"  style="width: 350px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">LO_name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="ev_LO_name"  disabled="true" style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="ev_name"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">max_inscriptions&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleId="textinput" styleClass="form-control" property="ev_max_inscriptions"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">amount&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="12" styleId="textinput" styleClass="form-control" property="ev_amount"  style="text-align: right; width: 84px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">currency&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ev_currency"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">deadline&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ev_deadline"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">comment&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="ev_comment"  style="width: 700px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">date1&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ev_date1"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">date2&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ev_date2"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">date3&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ev_date3"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">date4&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleId="textinput" styleClass="form-control" property="ev_date4"  style="width: 70px;"/>
			</div>
		</div>

		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">json&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleId="textinput" styleClass="form-control" property="ev_json"  style="width: 700px;"/>
			</div>
		</div>

					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3">
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.gestion_lista"/>" onclick="setOpcion('EvRCD_AF','Cerrar',null);"> 
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.leer"/>" onclick="setOpcion('EvRCD_AF','LeerReg',null);"> 
							<input class="btn btn-info"  type="button" value="<bean:message key="common.client.suprimir"/>" onclick="if ( confirmar('<bean:message key="common.client.pregunta_suprimir"/>',this)==true ) setOpcion('EvRCD_AF','Suprimir',null);">
							<input class="btn btn-primary" type="button" value="<bean:message key="common.client.grabar"/>"   onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar"/>',this)==true ) setOpcion('EvRCD_AF','CambiarReg',null);">
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
		
	<!-- <html:hidden property="ev_sincro"/> sincro -->
	<!-- <html:hidden property="ev_mark"/> mark -->
	<!-- <html:hidden property="ev_is_deleted"/> is_deleted -->
	<!-- <html:hidden property="ev_author"/> author -->
	     <html:hidden property="ev_event_id"/> <!-- event_id -->
	<!-- <html:hidden property="ev_location_id"/> location_id -->
	     <html:hidden property="ev_LO_name"/> <!-- LO_name -->
	<!-- <html:hidden property="ev_name"/> name -->
	<!-- <html:hidden property="ev_max_inscriptions"/> max_inscriptions -->
	<!-- <html:hidden property="ev_amount"/> amount -->
	<!-- <html:hidden property="ev_currency"/> currency -->
	<!-- <html:hidden property="ev_deadline"/> deadline -->
	<!-- <html:hidden property="ev_comment"/> comment -->
	<!-- <html:hidden property="ev_date1"/> date1 -->
	<!-- <html:hidden property="ev_date2"/> date2 -->
	<!-- <html:hidden property="ev_date3"/> date3 -->
	<!-- <html:hidden property="ev_date4"/> date4 -->
	<!-- <html:hidden property="ev_json"/> json -->

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