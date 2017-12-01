<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<div name="EsADDRCD_form">
    <div class="modal-header">
		<ul>
			<li>logon_USR &nbsp; {{actionForm.logon_USR}}</li>
		</ul>

			<md-input-container>
				<!-- Selector CENTRO -->
				<md-input-container class="md-block input-md">
					<label>Establecimiento</label>
					<!-- <md-icon>place</md-icon> -->
					<md-select placeholder="Seleccione Local" ng-model="$parent.aux_es_EV_location_id" md-on-open="location_id_onOpen()" md-on-close="location_id_onClose()" ng-change="location_id_onChange()" ng-model-options="{trackBy: '$value.value'}" required>
						<!-- <md-optgroup label="Local"> -->
							<md-option ng-value="item" ng-repeat="item in $parent.lst_lo">{{item.displayName}}</md-option>
						<!-- </md-optgroup> -->			
					</md-select>
	    		</md-input-container>
	    		<!-- Selector CENTRO fin -->
			</md-input-container>

			<md-input-container>
				<!-- Selector EVENTO -->
				<md-input-container class="md-block input-md">
					<label>Evento</label>
					<!-- <md-icon>place</md-icon> -->
					<md-select placeholder="Seleccione Evento" ng-model="$parent.aux_es_event_id" ng-model-options="{trackBy: '$value.value'}" required>
						<!-- <md-optgroup label="Evento"> -->
							<md-option ng-value="item" ng-repeat="item in $parent.lst_ev">{{item.displayName}}</md-option>
						<!-- </md-optgroup> -->			
					</md-select>
	    		</md-input-container>
	    		<!-- Selector EVENTO fin -->
			</md-input-container>

    </div>
    <div class="modal-footer">
        <input type="button" class="btn btn-primary" value="PAGAR" ng-click="agregar()"/>
    </div>
</div>

