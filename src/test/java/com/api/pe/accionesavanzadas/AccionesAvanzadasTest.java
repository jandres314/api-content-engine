package com.api.pe.accionesavanzadas;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.api.factory.FactoryDatosConexion;
import com.api.pe.conexiones.ConexionPe;

import filenet.vw.api.VWSession;

public class AccionesAvanzadasTest {
	
	private AccionesAvanzadas accionesAvanzadas = new AccionesAvanzadas();
	private ConexionPe conexionPe = new ConexionPe();
	
	@Test
	public void leerRosters() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String[] rosters = accionesAvanzadas.leerRosters(session);
		assertTrue(rosters.length > 0);
		conexionPe.cerrarSesion(session);
	}
	
	@Test
	public void leerEspaciosDeAplicacion() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String[] apps = accionesAvanzadas.leerEspaciosDeAplicacion(session);
		assertTrue(apps.length > 0);
		conexionPe.cerrarSesion(session);
	}
	
	@Test
	public void leerRoles() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		boolean resultado = accionesAvanzadas.leerRoles(session, "DefaultApplication");
		assertTrue(resultado);
		conexionPe.cerrarSesion(session);
	}

}
