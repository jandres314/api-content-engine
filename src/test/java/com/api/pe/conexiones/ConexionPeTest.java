package com.api.pe.conexiones;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.api.factory.FactoryDatosConexion;

import filenet.vw.api.VWSession;

public class ConexionPeTest {

	private ConexionPe conexionPe = new ConexionPe();

	@Test
	public void obtenerSesion() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		assertTrue(session.isLoggedOn());
		conexionPe.cerrarSesion(session);
	}

}
