package com.api.ce.ejecucionxlotes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.api.ce.conexiones.ConexionOs;
import com.api.ce.pojo.DatosCreacionObjeto;
import com.api.factory.FactoryDatosConexion;
import com.filenet.api.core.ObjectStore;

public class EjecucionLotesTest {
	
	private ConexionOs conexionOs = new ConexionOs();
	private EjecucionLotes ejecucionLotes = new EjecucionLotes();
	
	@Test
	public void ejecutarLote() {
		
		List<DatosCreacionObjeto> carpetas = new ArrayList<>();
		carpetas.add(this.generarObjetoCreacion());
		carpetas.add(this.generarObjetoCreacion());
		
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		ejecucionLotes.crearLoteCarpetas(os, carpetas);
		assertTrue(true);
	}
	
	private DatosCreacionObjeto generarObjetoCreacion() {

		Map<String, Object> valores = new HashMap<>();
		valores.put("FolderName", UUID.randomUUID().toString());

		DatosCreacionObjeto datos = new DatosCreacionObjeto();
		datos.setClaseDocumental("Folder");
		datos.setIdFolderPadre("{4C7929E3-8C2F-407E-807F-F494E504C392}");
		datos.setValores(valores);

		return datos;
	}

}
