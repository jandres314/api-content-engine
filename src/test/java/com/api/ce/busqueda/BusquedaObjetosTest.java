package com.api.ce.busqueda;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.api.ce.conexiones.ConexionOs;
import com.api.factory.FactoryDatosConexion;
import com.filenet.api.core.ObjectStore;

public class BusquedaObjetosTest {

	private BusquedaObjetos busqueda = new BusquedaObjetos();
	private ConexionOs conexionOs = new ConexionOs();

	@Test
	public void ejecutarBusqueda() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		//String query = "SELECT DocumentTitle from Document WHERE DateCreated > 20220501T050000Z";
		String query = "SELECT Id, FolderName, DateCreated from Folder WHERE DateCreated > 20220501T050000Z";
		List<Map<String, Object>> list = busqueda.ejecutarConsulta(os, query);
		assertFalse(list.isEmpty());
	}

}
