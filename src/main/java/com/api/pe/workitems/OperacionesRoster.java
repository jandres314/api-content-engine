package com.api.pe.workitems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.api.pe.parametroswf.ParametrosWorkflow;

import filenet.vw.api.VWFetchType;
import filenet.vw.api.VWRoster;
import filenet.vw.api.VWRosterQuery;
import filenet.vw.api.VWSession;
import filenet.vw.api.VWStepElement;
import filenet.vw.api.VWWorkObjectNumber;

public class OperacionesRoster {

	private ParametrosWorkflow parametrosWorkflow = new ParametrosWorkflow();

	public List<Map<String, Object>> consultarTareas(VWSession session, String rosterName, String filtro,
			Object[] valoresSustitucion) {
		List<Map<String, Object>> list = new ArrayList<>();

		VWRoster roster = session.getRoster(rosterName);
		System.out.println(String.format("Cantidad tareas roster: %s", roster.fetchCount()));
		int flags = VWRoster.QUERY_NO_OPTIONS;
		VWRosterQuery rosterQuery = roster.createQuery(null, null, null, flags, filtro, valoresSustitucion,
				VWFetchType.FETCH_TYPE_STEP_ELEMENT);

		VWStepElement stepElement = null;
		while (rosterQuery.hasNext()) {
			stepElement = (VWStepElement) rosterQuery.next();
			list.add(this.parametrosWorkflow.extraerValoresStepElement(stepElement));
		}

		return list;
	}

	public boolean avanzarTarea(VWSession session, String nombreRoster, String wobNumber, Map<String, Object> valores) {

		VWRoster roster = session.getRoster(nombreRoster);
		int flags = VWRoster.QUERY_MAX_VALUES_INCLUSIVE + VWRoster.QUERY_MIN_VALUES_INCLUSIVE;
		String index = "F_WobNum";
		VWWorkObjectNumber[] valoresSustitucion = { new VWWorkObjectNumber(wobNumber) };
		VWRosterQuery rosterQuery = roster.createQuery(index, valoresSustitucion, valoresSustitucion, flags, null, null,
				VWFetchType.FETCH_TYPE_STEP_ELEMENT);

		VWStepElement element = (VWStepElement) rosterQuery.next();
		element.doLock(true);
		parametrosWorkflow.completarParametros(element, valores);
		element.doSave(true);
		return true;
	}

}
