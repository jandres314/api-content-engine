package com.api.ce.accionesavanzadas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.api.ce.conexiones.ConexionOs;
import com.api.factory.FactoryDatosConexion;
import com.filenet.api.core.ObjectStore;

public class AnotacionesTest {

	private Anotaciones anotaciones = new Anotaciones();
	private ConexionOs conexionOs = new ConexionOs();

	@Test
	public void crearAnotacion() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		boolean completed = anotaciones.agregarAnotacionDocumento(os, "Annotation",
				"{B0E99980-0000-CF11-A7DA-41CFD71ADAD8}", "Anotacion_02");
		assertTrue(completed);
	}

	@Test
	public void leerAnotaciones() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		List<String> descripciones = anotaciones.leerAnotacionesDocumento(os, "{B0E99980-0000-CF11-A7DA-41CFD71ADAD8}");
		assertFalse(descripciones.isEmpty());
	}

}
