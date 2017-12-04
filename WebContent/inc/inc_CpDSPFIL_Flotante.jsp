
<!-- INICIO MENÚ FLOTANTE -->
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<div class="modal fade" id="masOpciones">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><bean:message  key="common.client.seleccionar_opcion"/></h3>
            </div>
            <div class="modal-body">
                
			    <p>
			        <a href="#" onclick="if ( confirmar('<bean:message  key="common.client.pregunta_suprimir_marcados"/>',this)==true ) setOpcion('CpRCD_AF','Borrar', null);">
			           <bean:message  key="common.client.sumprimir_marcados"/>
			        </a>
			    </p>
			    <p>
			        <a  href="#" onclick="if ( confirmar('<bean:message  key="common.client.pregunta_exportar"/>',this)==true ) setOpcion('CpRCD_AF','Exportar', null);">
			            <bean:message  key="common.client.exportar"/>
			        </a>
			    </p>
			    <br/>
			    
			    <h5><strong><bean:message  key="common.client.modificacion_colectiva_marcados"/></strong></h5>
			    
	<!-- MonoRegistro INICIO -->
	<!-- Campos del formato físico, por si se quiere usar para actualizaciones colectivas: -->
    <table style="width: 90%" class="table table-nonfluid table-striped">
		    
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_sincro', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;sincro
				</button>
			</td>
			<td><html:text maxlength="20" styleClass="form-control" property="cp_sincro"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_mark', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;mark
				</button>
			</td>
			<td><html:text maxlength="1" styleClass="form-control" property="cp_mark"  style="width: 7px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_is_deleted', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;is_deleted
				</button>
			</td>
			<td><html:text maxlength="1" styleClass="form-control" property="cp_is_deleted"  style="width: 7px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_author', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;author
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="cp_author"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_serial_number', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;serial_number
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="cp_serial_number"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_name', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;name
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="cp_name"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_isBlocked', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;isBlocked
				</button>
			</td>
			<td><html:text maxlength="1" styleClass="form-control" property="cp_isBlocked"  style="width: 7px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_asignation_order', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;asignation_order
				</button>
			</td>
			<td><html:text maxlength="5" styleClass="form-control" property="cp_asignation_order"  style="text-align: right; width: 35px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_install_date', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;install_date
				</button>
			</td>
			<td><html:text maxlength="10" styleClass="form-control" property="cp_install_date"  style="width: 70px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_reset_date_used', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;reset_date_used
				</button>
			</td>
			<td><html:text maxlength="10" styleClass="form-control" property="cp_reset_date_used"  style="width: 70px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_hours_used', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;hours_used
				</button>
			</td>
			<td><html:text maxlength="5" styleClass="form-control" property="cp_hours_used"  style="text-align: right; width: 35px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_note', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;note
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="cp_note"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_comment', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;comment
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="cp_comment"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_observation', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;observation
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="cp_observation"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_warning', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;warning
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="cp_warning"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_contact_service', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;contact_service
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="cp_contact_service"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('CpRCD_AF','colectivo_json', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;json
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="cp_json"  style="width: 105px;"/></td>
		</tr>
		    <tr><td colspan="3"><br/></tr>
    </table>
	<!-- MonoRegistro FINAL -->
                
            </div>
            
        </div>
    </div>
</div>
<!-- FINAL MENÚ FLOTANTE -->
