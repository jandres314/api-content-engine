package com.api.pe.status;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.api.factory.FactoryDatosConexion;
import com.api.pe.conexiones.ConexionPe;

import filenet.vw.api.VWSession;

public class EstadoProcesosTest {

	private ConexionPe conexionPe = new ConexionPe();
	private EstadoProcesos estadoProcesos = new EstadoProcesos();

	@Test
	public void consultaMilestones() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String nombreCola = "Inbox";
		String wobNum = "060897DD875EF4478F2DA5556BD6D0CB";
		boolean resultado = estadoProcesos.consultarMilestones(session, nombreCola, wobNum, 1);
		assertTrue(resultado);
		conexionPe.cerrarSesion(session);
	}

	@Test
	public void consultarHistorial() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String nombreCola = "Inbox";
		String wobNum = "060897DD875EF4478F2DA5556BD6D0CB";
		estadoProcesos.consultarHistorial(session, nombreCola, wobNum);
		conexionPe.cerrarSesion(session);
	}

}
