package com.api.ce.permisosyautorizacion;

import org.junit.Test;

import com.api.ce.conexiones.ConexionOs;
import com.api.ce.conexiones.FactoryDatosConexion;
import com.filenet.api.core.ObjectStore;

public class PermisosAutorizacionTest {
	
	private PermisosAutorizacion permisos = new PermisosAutorizacion();
	private ConexionOs conexionOs = new ConexionOs();
	
	@Test
	public void asingarPermisos() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
	}
	
	@Test
	public void retirarPermisos() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
	}

}
