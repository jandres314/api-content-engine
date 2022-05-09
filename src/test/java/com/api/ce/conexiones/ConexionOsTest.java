package com.api.ce.conexiones;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.api.ce.pojo.DatosConexionOs;
import com.filenet.api.core.ObjectStore;

public class ConexionOsTest {
	
	private ConexionOs conexionOs = new ConexionOs();
	
	@Test
	public void probarConextionObjectStore() {
		DatosConexionOs datosConexion = FactoryDatosConexion.obtenerDatosConexion();
		ObjectStore os = conexionOs.obtenerConexionObjectStore(datosConexion);
		assertEquals(os.get_Name(), "OSDEV");
	}

}
