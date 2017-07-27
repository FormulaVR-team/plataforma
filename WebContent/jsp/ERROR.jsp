<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<html:html locale="true">
    <head>
        <link rel="stylesheet" type="text/css" href="./res/estilos.css" />
        <title>Error</title>
    </head>
    <body>
        <logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
            <div style="color='red'">
                ERROR:Application resources not loaded -- check servlet container
                logs for error messages.
            </div>
        </logic:notPresent>
        <h3><html:errors property="error"/></h3>
        &nbsp;&nbsp;&nbsp;
        <fieldset style="width: 300px; text-align: left;">
            <legend>&nbsp;Acciones posibles&nbsp;</legend>
            &nbsp;&nbsp;&nbsp;<a href="javascript:history.back();">Volver atrás</a>
            &nbsp;&nbsp;&nbsp;<a href="Off">Volver a conectarse</a>
        </fieldset>
    </body>
</html:html>
