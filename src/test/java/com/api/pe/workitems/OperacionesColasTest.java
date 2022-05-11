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

public class OperacionesColasTest {

	private ConexionPe conexionPe = new ConexionPe();
	private OperacionesColas operacionesColas = new OperacionesColas();

	@Test
	public void consultarTareas() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String nombreCola = "Inbox";
		String filtro = "F_Subject = :a";
		Object[] valoresSustitucion = new Object[] { "aprobacion-credito" };
		List<Map<String, Object>> tareas = operacionesColas.consultarTareas(session, nombreCola, filtro,
				valoresSustitucion);
		assertFalse(tareas.isEmpty());
		conexionPe.cerrarSesion(session);
	}

	@Test
	public void avanzarTarea() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String wobNumber = "124DD57C1B4C5148A9E738F7E5988CFF";
		Map<String, Object> valores = this.obtenerValoresPasoEvaluarSolicitud();
		String nombreCola = "Inbox";
		boolean resultado = operacionesColas.avanzarTarea(session, nombreCola, wobNumber, valores);
		assertTrue(resultado);
		conexionPe.cerrarSesion(session);
	}

	private Map<String, Object> obtenerValoresPasoEvaluarSolicitud() {
		Map<String, Object> valores = new HashMap<>();
		valores.put("Status", "Rechazado");
		return valores;
	}

}
