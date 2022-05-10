package com.api.ce.permisosyautorizacion;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.api.ce.conexiones.ConexionOs;
import com.api.factory.FactoryDatosConexion;
import com.filenet.api.core.ObjectStore;

public class PermisosAutorizacionTest {
	
	private PermisosAutorizacion permisos = new PermisosAutorizacion();
	private ConexionOs conexionOs = new ConexionOs();
	
	@Test
	public void asingarPermisos() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		String idDocumento = "{F0E69980-0000-C41D-979C-CEA83B0D4A5F}";
		boolean resultado = permisos.asignarPermisosFullControlUsuariosAutenticados(os, idDocumento);
		assertTrue(resultado);
	}
	
}
