package com.api.pe.status;

import com.api.pe.workitems.OperacionesColas;

import filenet.vw.api.VWMapDefinition;
import filenet.vw.api.VWMilestoneElement;
import filenet.vw.api.VWProcess;
import filenet.vw.api.VWSession;
import filenet.vw.api.VWStepElement;
import filenet.vw.api.VWStepHistory;
import filenet.vw.api.VWStepOccurrenceHistory;
import filenet.vw.api.VWWorkflowDefinition;
import filenet.vw.api.VWWorkflowHistory;
import filenet.vw.api.VWWorkflowMilestones;

public class EstadoProcesos {

	private OperacionesColas operacionesColas = new OperacionesColas();

	public boolean consultarMilestones(VWSession session, String queueName, String wobNumber, int level) {
		VWStepElement step = operacionesColas.recuperarStepElementPorWobNum(session, queueName, wobNumber);
		VWProcess proceso = step.fetchProcess();
		VWWorkflowMilestones milestones = proceso.fetchReachedWorkflowMilestones(level);
		while (milestones.hasNext()) {
			VWMilestoneElement milestone = milestones.next();
			System.out.println(milestone.getLevel());
			System.out.println(milestone.getName());
			System.out.println(milestone.getMessage());
			System.out.println(milestone.getTimestamp());
		}
		return true;
	}

	public boolean consultarHistorial(VWSession session, String queueName, String wobNumber) {
		VWStepElement step = operacionesColas.recuperarStepElementPorWobNum(session, queueName, wobNumber);
		VWProcess proceso = step.fetchProcess();
		VWWorkflowDefinition workflowDefinition = proceso.fetchWorkflowDefinition(false);
		VWMapDefinition[] workflowMaps = workflowDefinition.getMaps();
		for (VWMapDefinition vwMapDefinition : workflowMaps) {
			VWWorkflowHistory historial = proceso.fetchWorkflowHistory(vwMapDefinition.getMapId());
			while (historial.hasNext()) {
				VWStepHistory history = historial.next();
				System.out.println(history.getStepName());
				while (history.hasNext()) {
					VWStepOccurrenceHistory ocurrencia = history.next();
					System.out.println("fechaInicio: " +ocurrencia.getDateReceived());
					System.out.println("fechaFin: " +ocurrencia.getCompletionDate());
				}
				System.out.println("----------------------------------------------");
			}
		}

		return true;
	}

}
