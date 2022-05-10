package com.api.pe.conexiones;

import com.api.pe.pojo.DatosConexionPe;

import filenet.vw.api.VWSession;

public class ConexionPe {

	public VWSession obtenerSesion(DatosConexionPe datosConexion) {

		VWSession session = new VWSession();
		session.setBootstrapCEURI(datosConexion.getUrl());

		session.logon(datosConexion.getUsuario(), datosConexion.getContrasena(), datosConexion.getPuntoConexion());
		return session;
	}

	public void cerrarSesion(VWSession session) {
		if (session != null && session.isLoggedOn()) {
			session.logoff();
		}
	}

}
