package com.api.ce.accionesavanzadas;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.api.ce.conexiones.ConexionOs;
import com.api.factory.FactoryDatosConexion;
import com.filenet.api.core.ObjectStore;

public class MetadataDiscoveryTest {
	
	private MetadataDiscovery metadataDiscovery = new MetadataDiscovery();
	private ConexionOs conexionOs = new ConexionOs();
	
	@Test
	public void extraerTaxonomia() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		Map<String, Object> metadata = metadataDiscovery.extraerTaxonomia(os, "Document");
		assertTrue(metadata.size() > 0);
	}

}
