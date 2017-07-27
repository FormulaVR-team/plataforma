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

    <html:form action="/Ad_rsADDRCD_A.do" styleClass="form-horizontal">

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
				<html:text maxlength="20" styleClass="form-control" property="ad_rs_sincro"  style="width: 140px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">mark&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleClass="form-control" property="ad_rs_mark"  style="width: 7px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">is_deleted&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleClass="form-control" property="ad_rs_is_deleted"  style="width: 7px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">author&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_author"  style="width: 350px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput"><span style='color:darkblue'>reservation_id&nbsp;</span></label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_reservation_id"  style="width: 350px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">location_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_location_id"  style="width: 350px;"/>
			</div>
		</div>
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">LO_name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_LO_name"  disabled="true" style="width: 700px;"/>
			</div>
		</div>
-->
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">user_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_user_id"  style="width: 350px;"/>
			</div>
		</div>
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">US_country_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleClass="form-control" property="ad_rs_US_country_id"  disabled="true" style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">US_nick&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_US_nick"  disabled="true" style="width: 350px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">US_avatar&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_US_avatar"  disabled="true" style="width: 700px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">US_is_admin&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleClass="form-control" property="ad_rs_US_is_admin"  disabled="true" style="width: 7px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">US_first_name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_US_first_name"  disabled="true" style="width: 700px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">US_last_name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_US_last_name"  disabled="true" style="width: 700px;"/>
			</div>
		</div>
-->
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">product_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_product_id"  style="width: 350px;"/>
			</div>
		</div>
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_name&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_PT_name"  disabled="true" style="width: 700px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_deadline&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleClass="form-control" property="ad_rs_PT_deadline"  disabled="true" style="width: 70px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_isPercent&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleClass="form-control" property="ad_rs_PT_isPercent"  disabled="true" style="width: 7px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_amount&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="12" styleClass="form-control" property="ad_rs_PT_amount"  disabled="true" style="text-align: right; width: 84px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_duration_minutes&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleClass="form-control" property="ad_rs_PT_duration_minutes"  disabled="true" style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
-->
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">quantity&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleClass="form-control" property="ad_rs_quantity"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">product_id2&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_product_id2"  style="width: 350px;"/>
			</div>
		</div>
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_name2&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_PT_name2"  disabled="true" style="width: 700px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_deadline2&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleClass="form-control" property="ad_rs_PT_deadline2"  disabled="true" style="width: 70px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_isPercent2&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleClass="form-control" property="ad_rs_PT_isPercent2"  disabled="true" style="width: 7px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_amount2&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="12" styleClass="form-control" property="ad_rs_PT_amount2"  disabled="true" style="text-align: right; width: 84px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
-->
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">product_id3&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_product_id3"  style="width: 350px;"/>
			</div>
		</div>
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_name3&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_PT_name3"  disabled="true" style="width: 700px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_deadline3&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleClass="form-control" property="ad_rs_PT_deadline3"  disabled="true" style="width: 70px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_isPercent3&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="1" styleClass="form-control" property="ad_rs_PT_isPercent3"  disabled="true" style="width: 7px;"/>
			</div>
		</div>
-->
<!-- CAMPO VIRTUAL
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">PT_amount3&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="12" styleClass="form-control" property="ad_rs_PT_amount3"  disabled="true" style="text-align: right; width: 84px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
-->
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">amount&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="12" styleClass="form-control" property="ad_rs_amount"  style="text-align: right; width: 84px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">currency&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleClass="form-control" property="ad_rs_currency"  style="width: 70px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">phone&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_phone"  style="width: 700px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">pay_status&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleClass="form-control" property="ad_rs_pay_status"  style="width: 70px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">start_date&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="10" styleClass="form-control" property="ad_rs_start_date"  style="width: 70px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">start_time&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="4" styleClass="form-control" property="ad_rs_start_time"  style="width: 28px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">duration_minutes&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleClass="form-control" property="ad_rs_duration_minutes"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">places&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="5" styleClass="form-control" property="ad_rs_places"  style="text-align: right; width: 35px;" onkeypress="return soloNumeros(event);"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">coupon_id&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="50" styleClass="form-control" property="ad_rs_coupon_id"  style="width: 350px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">executed_at&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="20" styleClass="form-control" property="ad_rs_executed_at"  style="width: 140px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">note&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_note"  style="width: 700px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">comment&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_comment"  style="width: 700px;"/>
			</div>
		</div>
		<div class='form-group'>
			<label class="col-sm-3 control-label" for="textinput">json&nbsp;</label>
			<div class="col-sm-9">
				<html:text maxlength="100" styleClass="form-control" property="ad_rs_json"  style="width: 700px;"/>
			</div>
		</div>

					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3">
							<input class="btn btn-info" type="button" value="<bean:message key="common.client.gestion_lista"/>" onclick="setOpcion('Ad_rsRCD_AF','Cerrar',null);"> 
							<input class="btn btn-primary" type="button" value="<bean:message key="common.client.nuevo"/>" onclick="if ( confirmar('<bean:message key="common.client.pregunta_grabar"/>',this)==true ) setOpcion('Ad_rsRCD_AF','NuevoReg',null);">
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
		<!-- <html:hidden property="ad_rs_sincro"/> sincro -->
		<!-- <html:hidden property="ad_rs_mark"/> mark -->
		<!-- <html:hidden property="ad_rs_is_deleted"/> is_deleted -->
		<!-- <html:hidden property="ad_rs_author"/> author -->
		<!-- <html:hidden property="ad_rs_reservation_id"/> reservation_id -->
		<!-- <html:hidden property="ad_rs_location_id"/> location_id -->
		<!-- <html:hidden property="ad_rs_user_id"/> user_id -->
		<!-- <html:hidden property="ad_rs_product_id"/> product_id -->
		<!-- <html:hidden property="ad_rs_quantity"/> quantity -->
		<!-- <html:hidden property="ad_rs_product_id2"/> product_id2 -->
		<!-- <html:hidden property="ad_rs_product_id3"/> product_id3 -->
		<!-- <html:hidden property="ad_rs_amount"/> amount -->
		<!-- <html:hidden property="ad_rs_currency"/> currency -->
		<!-- <html:hidden property="ad_rs_phone"/> phone -->
		<!-- <html:hidden property="ad_rs_pay_status"/> pay_status -->
		<!-- <html:hidden property="ad_rs_start_date"/> start_date -->
		<!-- <html:hidden property="ad_rs_start_time"/> start_time -->
		<!-- <html:hidden property="ad_rs_duration_minutes"/> duration_minutes -->
		<!-- <html:hidden property="ad_rs_places"/> places -->
		<!-- <html:hidden property="ad_rs_coupon_id"/> coupon_id -->
		<!-- <html:hidden property="ad_rs_executed_at"/> executed_at -->
		<!-- <html:hidden property="ad_rs_note"/> note -->
		<!-- <html:hidden property="ad_rs_comment"/> comment -->
		<!-- <html:hidden property="ad_rs_json"/> json -->

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
