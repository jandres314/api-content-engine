package com.api.pe.accionesavanzadas;

import filenet.vw.api.VWApplicationSpace;
import filenet.vw.api.VWRole;
import filenet.vw.api.VWSession;
import filenet.vw.api.VWWorkBasket;

public class AccionesAvanzadas {

	public String[] leerRosters(VWSession session) {
		return session.fetchRosterNames(false);
	}

	public String[] leerEspaciosDeAplicacion(VWSession session) {
		return session.fetchAppSpaceNames(false);
	}

	public boolean leerRoles(VWSession session, String espacioAplicacion) {
		VWApplicationSpace appSpace = session.fetchApplicationSpace(espacioAplicacion,
				VWSession.APPLICATIONSPACE_INCLUDING_ROLES);
		VWRole[] roles = appSpace.getRoles();
		for (VWRole rol : roles) {
			System.out.println(rol.getName());
			VWWorkBasket[] bandejas = rol.fetchWorkBaskets();
			for (VWWorkBasket bandeja : bandejas) {
				System.out.println(bandeja.getAuthoredName());
				System.out.println(bandeja.getQueueName());
				System.out.println(bandeja.fetchCount());
			}
		}
		return true;
	}

}
