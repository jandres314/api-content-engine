package com.api.pe.inicioworkflows;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.api.factory.FactoryDatosConexion;
import com.api.pe.conexiones.ConexionPe;

import filenet.vw.api.VWSession;

public class InicializacionWorkflowTest {

	private InicializacionWorkflow inicializacionWorkflow = new InicializacionWorkflow();
	private ConexionPe conexionPe = new ConexionPe();

	@Test
	public void lanzarWorkflow() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String nombreWorkflow = "capacitacion-api-process";
		Map<String, Object> valores = this.obtenerValoresLanzamientoWorkflow();
		String wob = inicializacionWorkflow.lazarWorkflow(session, nombreWorkflow, valores);
		assertNotNull(wob);
		conexionPe.cerrarSesion(session);
	}

	private Map<String, Object> obtenerValoresLanzamientoWorkflow() {

		Map<String, Object> valores = new HashMap<>();
		valores.put("Nombre", "Jaime Osorio");
		valores.put("Edad", 34);
		valores.put("Monto", 900000);
		valores.put("Identificacion", "1039420427");
		valores.put("TipoIdentificacion", "CC");
		valores.put("FechaSolicitud", new Date());
		return valores;
	}

}
