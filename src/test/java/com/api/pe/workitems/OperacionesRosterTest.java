package com.api.pe.workitems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.api.factory.FactoryDatosConexion;
import com.api.pe.conexiones.ConexionPe;

import filenet.vw.api.VWSession;

public class OperacionesRosterTest {

	private ConexionPe conexionPe = new ConexionPe();
	private OperacionesRoster operacionesRoster = new OperacionesRoster();

	@Test
	public void consultarTareas() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String nombreRoster = "DefaultRoster";
		String filtro = "F_Subject = :a";
		Object[] valoresSustitucion = new Object[] { "aprobacion-credito" };
		List<Map<String, Object>> tareas = operacionesRoster.consultarTareas(session, nombreRoster, filtro,
				valoresSustitucion);
		assertFalse(tareas.isEmpty());
		conexionPe.cerrarSesion(session);
	}

	@Test
	public void avanzarTarea() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String nombreRoster = "DefaultRoster";
		String wobNumber = "2C69F4461E4A5946B1452907BD74213C";
		Map<String, Object> valores = this.obtenerValoresPasoEvaluarSolicitud();
		boolean resultado = operacionesRoster.avanzarTarea(session, nombreRoster, wobNumber, valores);
		assertTrue(resultado);
		conexionPe.cerrarSesion(session);
	}

	private Map<String, Object> obtenerValoresPasoEvaluarSolicitud() {
		Map<String, Object> valores = new HashMap<>();
		valores.put("Status", "Aprobado");
		return valores;
	}

}
