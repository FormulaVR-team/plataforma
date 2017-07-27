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

    <html:form action="/LgADDRCD_A.do" styleClass="form-horizontal">

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
				<html:text maxlength="20" styleClass="form-control" property="lg_sincro"  style="width: 140px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">mark&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleClass="form-control" property="lg_mark"  style="width: 7px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">is_deleted&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleClass="form-control" property="lg_is_deleted"  style="width: 7px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">author&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="lg_author"  style="width: 350px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>serial&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleClass="form-control" property="lg_serial"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">text_1&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="lg_text_1"  style="width: 700px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">text_2&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="lg_text_2"  style="width: 700px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">text_3&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="lg_text_3"  style="width: 700px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">json&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="lg_json"  style="width: 700px;"/>
			</div>
		</div>

					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3">
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.gestion_lista"/>" onclick="setOpcion('LgRCD_AF','Cerrar',null);"> 
							<input class="btn btn-primary" type="button" value="<bean:message key="common.client.nuevo"/>" onclick="if ( confirmar('<bean:message key="common.client.pregunta_grabar"/>',this)==true ) setOpcion('LgRCD_AF','NuevoReg',null);">
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

        <!-- FORMATO COMPLETO: -->
		<!-- <html:hidden property="lg_sincro"/> sincro -->
		<!-- <html:hidden property="lg_mark"/> mark -->
		<!-- <html:hidden property="lg_is_deleted"/> is_deleted -->
		<!-- <html:hidden property="lg_author"/> author -->
		<!-- <html:hidden property="lg_serial"/> serial -->
		<!-- <html:hidden property="lg_text_1"/> text_1 -->
		<!-- <html:hidden property="lg_text_2"/> text_2 -->
		<!-- <html:hidden property="lg_text_3"/> text_3 -->
		<!-- <html:hidden property="lg_json"/> json -->

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
