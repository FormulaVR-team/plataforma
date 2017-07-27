<!-- INICIO CABECERA COMÚN-->
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!-- <script type="text/javascript" src="./script/scriptacolous/prototype.js"></script> -->
<!-- <script type="text/javascript" src="./script/scriptacolous/scriptaculous.js"></script> -->
<tr>
    <td colspan="100">
        <div id="cabComun" class="fondoLogo" style="text-align: right; height: 60px; width: 800px;">
            <div style="font-size: 12px; color: #888888;">${cfgPantalla.fechaHora}&nbsp;&nbsp;</div>
            <div style="text-align: center;">
                <div style="font-size: 20px; color: #444444;">&nbsp;&nbsp;&nbsp;${cfgPantalla.tituloPantalla}</div>
            </div>
            <div style="font-size: 10px; color: #888888;"><!--[${logon_USR}]&nbsp;-->${cfgPantalla.nombrePantalla} &nbsp;&nbsp;</div>
            <p style="display: none;"><img src="./res/smile.gif" alt="Espere..."/></p>
        </div>
        <html:errors property="error"/>
    </td>
</tr>
<!-- FINAL CABECERA COMÚN -->
