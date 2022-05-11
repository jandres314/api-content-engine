package com.api.pe.parametroswf;

import java.util.HashMap;
import java.util.Map;

import filenet.vw.api.VWFieldType;
import filenet.vw.api.VWModeType;
import filenet.vw.api.VWParameter;
import filenet.vw.api.VWStepElement;

public class ParametrosWorkflow {

	public void completarParametros(VWStepElement element, Map<String, Object> valores) {
		if (valores != null && !valores.isEmpty()) {
			VWParameter[] parametros = element.getParameters(VWFieldType.BASIC_FIELD_TYPES,
					VWStepElement.FIELD_USER_DEFINED);
			for (VWParameter parametro : parametros) {
				String nombre = parametro.getName();
				Object valor = valores.get(nombre);
				if (parametro.getMode() > VWModeType.MODE_TYPE_IN && valor != null) {
					element.setParameterValue(nombre, valor, Boolean.TRUE);
				}
			}
		}
	}

	public Map<String, Object> extraerValoresStepElement(VWStepElement step) {

		Map<String, Object> values = new HashMap<>();
		String[] parametros = step.getParameterNames();
		for (String key : parametros) {
			values.put(key, step.getParameterValue(key));
		}
		values.put("F_StepName", step.getStepName());
		values.put("F_WobNum", step.getWorkObjectNumber());
		values.put("ParticipantName", step.getParticipantName());
		values.put("QueueName", step.getQueueName());
		values.put("LockedUserPx", step.getLockedUserPx());
		return values;
	}

}
