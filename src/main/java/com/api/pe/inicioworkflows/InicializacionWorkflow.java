package com.api.pe.inicioworkflows;

import java.util.Map;

import filenet.vw.api.VWFieldType;
import filenet.vw.api.VWModeType;
import filenet.vw.api.VWParameter;
import filenet.vw.api.VWSession;
import filenet.vw.api.VWStepElement;

public class InicializacionWorkflow {

	public String lazarWorkflow(VWSession session, String nombreWorkflow, Map<String, Object> valores) {

		VWStepElement element = session.createWorkflow(nombreWorkflow);
		
		VWParameter[] parametros = element.getParameters(VWFieldType.BASIC_FIELD_TYPES, VWStepElement.FIELD_USER_DEFINED);
		for (VWParameter parametro : parametros) {
			String nombre = parametro.getName();
			Object valor = valores.get(nombre);
			if(parametro.getMode() > VWModeType.MODE_TYPE_IN && valor != null) {
				element.setParameterValue(nombre, valor, Boolean.TRUE);
			}
		}
		element.doDispatch();
		return element.getWorkObjectNumber();
	}

}
