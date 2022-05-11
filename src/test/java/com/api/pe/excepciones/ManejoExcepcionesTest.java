package com.api.pe.excepciones;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.api.factory.FactoryDatosConexion;
import com.api.pe.conexiones.ConexionPe;

import filenet.vw.api.VWSession;

public class ManejoExcepcionesTest {

	private ManejoExcepciones manejoExcepciones = new ManejoExcepciones();
	private ConexionPe conexionPe = new ConexionPe();

	@Test
	public void manejarExcpecion() {
		VWSession session = conexionPe.obtenerSesion(FactoryDatosConexion.obtenerDatosConexionPe());
		String wobNumber = "CC0381A1CE16AD4B9035793904512865";
		String nombreCola = "Inbox";
		boolean resultado = manejoExcepciones.manejarExcepcion(session, nombreCola, wobNumber);
		assertFalse(resultado);
		conexionPe.cerrarSesion(session);
	}

}
