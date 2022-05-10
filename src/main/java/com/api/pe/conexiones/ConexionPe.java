package com.api.pe.conexiones;

import com.api.pe.pojo.DatosConexionPe;

import filenet.vw.api.VWSession;

public class ConexionPe {

	public VWSession obtenerSesion(DatosConexionPe datosConexion) {

		VWSession myPESession = new VWSession();
		myPESession.setBootstrapCEURI(datosConexion.getUrl());

		// Log onto the Process Engine Server
		myPESession.logon(datosConexion.getUsuario(), datosConexion.getContrasena(), datosConexion.getPuntoConexion());
		return myPESession;
	}

	public void cerrarSesion(VWSession session) {
		if (session != null && session.isLoggedOn()) {
			session.logoff();
		}
	}

}
