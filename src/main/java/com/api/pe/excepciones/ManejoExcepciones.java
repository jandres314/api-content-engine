package com.api.pe.excepciones;

import com.api.pe.workitems.OperacionesColas;

import filenet.vw.api.VWException;
import filenet.vw.api.VWSession;
import filenet.vw.api.VWStepElement;

public class ManejoExcepciones {

	private OperacionesColas operacionesColas = new OperacionesColas();

	public boolean manejarExcepcion(VWSession session, String queueName, String wobNumber) {

		try {
			VWStepElement element = operacionesColas.recuperarStepElementPorWobNum(session, queueName, wobNumber);
			element.doDispatch();
			return true;
		} catch (VWException e) {
			System.out.println(e.getKey());
			System.out.println(e.getCauseClassName());
			System.out.println(e.getCauseDescription());
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

}
