
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
			        <a href="#" onclick="if ( confirmar('<bean:message  key="common.client.pregunta_suprimir_marcados"/>',this)==true ) setOpcion('PmRCD_AF','Borrar', null);">
			           <bean:message  key="common.client.sumprimir_marcados"/>
			        </a>
			    </p>
			    <p>
			        <a  href="#" onclick="if ( confirmar('<bean:message  key="common.client.pregunta_exportar"/>',this)==true ) setOpcion('PmRCD_AF','Exportar', null);">
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
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_sincro', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;sincro
				</button>
			</td>
			<td><html:text maxlength="20" styleClass="form-control" property="pm_sincro"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_mark', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;mark
				</button>
			</td>
			<td><html:text maxlength="1" styleClass="form-control" property="pm_mark"  style="width: 7px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_is_deleted', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;is_deleted
				</button>
			</td>
			<td><html:text maxlength="1" styleClass="form-control" property="pm_is_deleted"  style="width: 7px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_author', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;author
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="pm_author"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_location_id', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;location_id
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="pm_location_id"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_name', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;name
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="pm_name"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_uses_per_user', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;uses_per_user
				</button>
			</td>
			<td><html:text maxlength="5" styleClass="form-control" property="pm_uses_per_user"  style="text-align: right; width: 35px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_places', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;places
				</button>
			</td>
			<td><html:text maxlength="5" styleClass="form-control" property="pm_places"  style="text-align: right; width: 35px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_product_id', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;product_id
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="pm_product_id"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_product_id_promo', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;product_id_promo
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="pm_product_id_promo"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_deadline', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;deadline
				</button>
			</td>
			<td><html:text maxlength="10" styleClass="form-control" property="pm_deadline"  style="width: 70px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('PmRCD_AF','colectivo_json', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;json
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="pm_json"  style="width: 105px;"/></td>
		</tr>
		    <tr><td colspan="3"><br/></tr>
    </table>
	<!-- MonoRegistro FINAL -->
                
            </div>
            
        </div>
    </div>
</div>
<!-- FINAL MENÚ FLOTANTE -->
