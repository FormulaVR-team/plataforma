<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html>
<html lang="es">
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
    <body>
        <%@include file="/inc/inc_menuForm.jsp"%>        
        
            <div class="col-xs-7 center">
                <div class="panel panel-default center">
                    <div class="panel-body">
                        <bean:message  key="menu.client.marquee"/>
                    </div>
                </div>
            </div>
            
            <!-- Carga y almacenamiento en cache de todos los recursos graficos de la aplicacion -->
            <!-- 
                <div style="display: none;">                                                          
                    <img src="./res/img/marker_831717.png">
                    <img src="./res/img/marker_0EA4FA.png">
                    <img src="./res/img/marker_FF8C00.png">
                    <img src="./res/img/marker_00008B.png">
                    <img src="./res/img/marker_FCFB94.png">
                    <img src="./res/img/marker_BBB9B9.png">
                    <img src="./res/img/marker_BD2BB8.png">
                    <img src="./res/img/marker_00892E.png">
                    <img src="./res/img/marker_29DCDA.png">
                    <img src="./res/img/marker_FFCECE.png">
                    <img src="./res/img/marker_FFD200.png">
                    <img src="./res/img/marker_00FFA2.png">
                    <img src="./res/img/marker_7E55FC.png">
                    <img src="./res/img/marker_6A8455.png">
                    <img src="./res/img/marker_1DBD1D.png">                    
                </div>
                 -->
        
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
		    $( "div" ).disableSelection();
		  });
		</script>
        
    </body>
</html>
