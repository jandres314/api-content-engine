package com.api.pe.inicioworkflows;

import java.util.Map;

import com.api.pe.parametroswf.ParametrosWorkflow;

import filenet.vw.api.VWSession;
import filenet.vw.api.VWStepElement;

public class InicializacionWorkflow {

	private ParametrosWorkflow parametrosWorkflow = new ParametrosWorkflow();

	public String lazarWorkflow(VWSession session, String nombreWorkflow, Map<String, Object> valores) {

		VWStepElement element = session.createWorkflow(nombreWorkflow);
		parametrosWorkflow.completarParametros(element, valores);
		element.doDispatch();
		return element.getWorkflowNumber();
	}

}
