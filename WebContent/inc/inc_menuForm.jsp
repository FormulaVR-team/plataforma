<!-- INICIO FORMULARIO 'MENÚ' -->
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<div class="navbar">    
    <div class="container">
	<html:form action="/menu_A.do">       
	    <input type="hidden" name="logon_USR" value="${logon_USR}">
	    <html:hidden property="opcionPantalla"/><html:hidden property="opcionJSMenu"/>

	    <span class="navbar-header" style="width:100%">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#laBarraMenus">
			    <span class="sr-only">Toggle navigation</span>
			    <span class="glyphicon glyphicon-align-justify"></span>            
			</button>
			
			<bean:message key="menu.client.title"/>

		    <div class="navbar-collapse collapse" id="laBarraMenus" style="height: 0px;">          
	
			    <ul class="nav navbar-nav">
			    
				    <li class="dropdown">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#"><bean:message  key="menu.client.menu_1"/> <b class="caret"></b></a>
						<ul class="dropdown-menu"  role="menu">
						    <li><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_1.item_1_dat"/>', null );"><bean:message  key="menu.client.menu_1.item_1"/></a></li>
						    <li><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_1.item_2_dat"/>', null );"><bean:message  key="menu.client.menu_1.item_2"/></a></li>
						    <li><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_1.item_3_dat"/>', null );"><bean:message  key="menu.client.menu_1.item_3"/></a></li>
						    <li><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_1.item_4_dat"/>', null );"><bean:message  key="menu.client.menu_1.item_4"/></a></li>                                                        
						    <li><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_1.item_5_dat"/>', null );"><bean:message  key="menu.client.menu_1.item_5"/></a></li>      
						</ul>
				    </li>  
	
				    <li class="dropdown">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#"><bean:message  key="menu.client.menu_2"/> <b class="caret"></b></a>
						<ul class="dropdown-menu"  role="menu">
						    <li>
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_2.item_1_dat"/>', null );"><bean:message  key="menu.client.menu_2.item_1"/></a>
							</li>
						    <li class="dropdown-submenu">
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_2.item_2_dat"/>', null );"><bean:message  key="menu.client.menu_2.item_2"/></a>
								<ul class="dropdown-menu">
								    <li class="noborder"><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_2.item_2.item_1_dat"/>', null );"><bean:message  key="menu.client.menu_2.item_2.item_1"/></a></li>
								</ul>
							</li>
						    <li>
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_2.item_3_dat"/>', null );"><bean:message  key="menu.client.menu_2.item_3"/></a>
							</li>
						    <li>
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_2.item_4_dat"/>', null );"><bean:message  key="menu.client.menu_2.item_4"/></a>
							</li>
						    <li>
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_2.item_5_dat"/>', null );"><bean:message  key="menu.client.menu_2.item_5"/></a>
							</li>
						</ul>
				    </li>  
	
				    <li class="dropdown">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#"><bean:message  key="menu.client.menu_3"/> <b class="caret"></b></a>
						<ul class="dropdown-menu"  role="menu">
						    <li>
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_3.item_1_dat"/>', null );"><bean:message  key="menu.client.menu_3.item_1"/></a>
							</li>
						    <li class="dropdown-submenu">
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_3.item_2_dat"/>', null );"><bean:message  key="menu.client.menu_3.item_2"/></a>
								<ul class="dropdown-menu">
								    <li class="noborder"><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_3.item_2.item_1_dat"/>', null );"><bean:message  key="menu.client.menu_3.item_2.item_1"/></a></li>
								    <li class="noborder"><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_3.item_2.item_2_dat"/>', null );"><bean:message  key="menu.client.menu_3.item_2.item_2"/></a></li>
								    <li class="noborder"><a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_3.item_2.item_3_dat"/>', null );"><bean:message  key="menu.client.menu_3.item_2.item_3"/></a></li>
								</ul>
							</li>
						    <li>
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_3.item_3_dat"/>', null );"><bean:message  key="menu.client.menu_3.item_3"/></a>
							</li>
						    <li>
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_3.item_4_dat"/>', null );"><bean:message  key="menu.client.menu_3.item_4"/></a>
							</li>
						    <li>
								<a href="javascript:setOpcion('menu_AF', '<bean:message  key="menu.client.menu_3.item_5_dat"/>', null );"><bean:message  key="menu.client.menu_3.item_5"/></a>
							</li>
						</ul>
				    </li>  
	
	                <li class="dropdown">
	                    <a data-toggle="dropdown" class="dropdown-toggle" href="#"><bean:message  key="menu.client.about"/> <b class="caret"></b></a>                        
	                    <ul class="dropdown-menu">
	                        <li class="disabled">
	                            <a href="#"> <bean:message  key="menu.client.about.line_1"/><br /><bean:message  key="menu.client.about.line_2"/></a>
	                        </li>
	                    </ul>
	                </li>                    
	
			    </ul>
	
				<div class="pull-right text-right">
				&nbsp;${logon_USR}&nbsp;(<bean:message key="configuracion.modo"/>)
				<br/>
				${cfgPantalla.nombrePantalla}
				</div>
	
		    </div>

	    </span>       


	</html:form>
    </div>   
</div>
<!-- FIN FORMULARIO 'MENÚ' -->
