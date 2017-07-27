
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
			        <a href="#" onclick="if ( confirmar('<bean:message  key="common.client.pregunta_suprimir_marcados"/>',this)==true ) setOpcion('RsRCD_AF','Borrar', null);">
			           <bean:message  key="common.client.sumprimir_marcados"/>
			        </a>
			    </p>
			    <p>
			        <a  href="#" onclick="if ( confirmar('<bean:message  key="common.client.pregunta_exportar"/>',this)==true ) setOpcion('RsRCD_AF','Exportar', null);">
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
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_sincro', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;sincro
				</button>
			</td>
			<td><html:text maxlength="20" styleClass="form-control" property="rs_sincro"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_mark', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;mark
				</button>
			</td>
			<td><html:text maxlength="1" styleClass="form-control" property="rs_mark"  style="width: 7px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_is_deleted', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;is_deleted
				</button>
			</td>
			<td><html:text maxlength="1" styleClass="form-control" property="rs_is_deleted"  style="width: 7px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_author', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;author
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="rs_author"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_location_id', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;location_id
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="rs_location_id"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_user_id', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;user_id
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="rs_user_id"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_product_id', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;product_id
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="rs_product_id"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_quantity', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;quantity
				</button>
			</td>
			<td><html:text maxlength="5" styleClass="form-control" property="rs_quantity"  style="text-align: right; width: 35px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_product_id2', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;product_id2
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="rs_product_id2"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_product_id3', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;product_id3
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="rs_product_id3"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_amount', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;amount
				</button>
			</td>
			<td><html:text maxlength="12" styleClass="form-control" property="rs_amount"  style="text-align: right; width: 84px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_currency', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;currency
				</button>
			</td>
			<td><html:text maxlength="10" styleClass="form-control" property="rs_currency"  style="width: 70px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_phone', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;phone
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="rs_phone"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_pay_status', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;pay_status
				</button>
			</td>
			<td><html:text maxlength="10" styleClass="form-control" property="rs_pay_status"  style="width: 70px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_start_date', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;start_date
				</button>
			</td>
			<td><html:text maxlength="10" styleClass="form-control" property="rs_start_date"  style="width: 70px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_start_time', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;start_time
				</button>
			</td>
			<td><html:text maxlength="4" styleClass="form-control" property="rs_start_time"  style="width: 28px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_duration_minutes', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;duration_minutes
				</button>
			</td>
			<td><html:text maxlength="5" styleClass="form-control" property="rs_duration_minutes"  style="text-align: right; width: 35px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_places', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;places
				</button>
			</td>
			<td><html:text maxlength="5" styleClass="form-control" property="rs_places"  style="text-align: right; width: 35px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_coupon_id', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;coupon_id
				</button>
			</td>
			<td><html:text maxlength="50" styleClass="form-control" property="rs_coupon_id"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_executed_at', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;executed_at
				</button>
			</td>
			<td><html:text maxlength="20" styleClass="form-control" property="rs_executed_at"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_note', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;note
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="rs_note"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_comment', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;comment
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="rs_comment"  style="width: 105px;"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info pull-right" style="width:70%;" onclick="if ( confirmar('<bean:message key="common.client.pregunta_modificar_marcados"/>',this)==true ) setOpcion('RsRCD_AF','colectivo_json', null);"><span class="glyphicon glyphicon-ok pull-left"></span>
				&nbsp;json
				</button>
			</td>
			<td><html:text maxlength="100" styleClass="form-control" property="rs_json"  style="width: 105px;"/></td>
		</tr>
		    <tr><td colspan="3"><br/></tr>
    </table>
	<!-- MonoRegistro FINAL -->
                
            </div>
            
        </div>
    </div>
</div>
<!-- FINAL MENÚ FLOTANTE -->
