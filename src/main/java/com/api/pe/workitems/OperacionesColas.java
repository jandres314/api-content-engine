package com.api.pe.workitems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.api.pe.parametroswf.ParametrosWorkflow;

import filenet.vw.api.VWFetchType;
import filenet.vw.api.VWQueue;
import filenet.vw.api.VWQueueQuery;
import filenet.vw.api.VWSession;
import filenet.vw.api.VWStepElement;
import filenet.vw.api.VWWorkObjectNumber;

public class OperacionesColas {

	private ParametrosWorkflow parametrosWorkflow = new ParametrosWorkflow();

	public List<Map<String, Object>> consultarTareas(VWSession session, String nombreCola, String filtro,
			Object[] valoresSustitucion) {
		List<Map<String, Object>> list = new ArrayList<>();

		VWQueue queue = session.getQueue(nombreCola);
		System.out.println(String.format("Cantidad tareas cola: %s", queue.fetchCount()));
		int flags = VWQueue.QUEUE_TYPE_PROCESS;
		VWQueueQuery rosterQuery = queue.createQuery(null, null, null, flags, filtro, valoresSustitucion,
				VWFetchType.FETCH_TYPE_STEP_ELEMENT);

		VWStepElement stepElement = null;
		while (rosterQuery.hasNext()) {
			stepElement = (VWStepElement) rosterQuery.next();
			list.add(this.parametrosWorkflow.extraerValoresStepElement(stepElement));
		}

		return list;
	}

	public boolean avanzarTarea(VWSession session, String queueName, String wobNumber, Map<String, Object> valores) {

		VWStepElement element = recuperarStepElementPorWobNum(session, queueName, wobNumber);
		element.doLock(true);
		parametrosWorkflow.completarParametros(element, valores);
		element.doDispatch();
		return true;
	}

	public VWStepElement recuperarStepElementPorWobNum(VWSession session, String queueName, String wobNumber) {

		VWQueue queue = session.getQueue(queueName);

		int flags = VWQueue.QUERY_READ_UNWRITABLE + VWQueue.QUERY_MIN_VALUES_INCLUSIVE
				+ VWQueue.QUERY_MAX_VALUES_INCLUSIVE + VWQueue.QUEUE_TYPE_PROCESS;

		VWWorkObjectNumber[] values = { new VWWorkObjectNumber(wobNumber) };

		VWQueueQuery query = queue.createQuery("F_WobNum", values, values, flags, null, null,
				VWFetchType.FETCH_TYPE_STEP_ELEMENT);

		return (VWStepElement) query.next();
	}

}
