package com.api.ce.crud;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.api.ce.conexiones.ConexionOs;
import com.api.ce.conexiones.FactoryDatosConexion;
import com.api.ce.pojo.DatosCreacionObjeto;
import com.filenet.api.core.ObjectStore;

public class CrudFolderTest {

	private CrudFolder crud = new CrudFolder();
	private ConexionOs conexionOs = new ConexionOs();

	@Test
	public void crearFolder() {

		DatosCreacionObjeto datosCreacionObjeto = this.generarObjetoCreacion();
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		String idFolder = crud.crearFolder(os, datosCreacionObjeto);
		assertNotNull(idFolder);
	}

	@Test
	public void actualizarFolder() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		Map<String, Object> valores = new HashMap<>();
		valores.put("FolderName", "Nombre_Folder_09");
		String idFolder = "{A0229680-0000-C911-AC49-F1B4AFB00A50}";
		int propiedadesActualizadas = crud.actualizarPropiedaesFolder(os, idFolder, valores);
		assertTrue(propiedadesActualizadas > 0);
	}
	
	@Test
	public void borrarFolder() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		String claseDocumental = "Folder";
		String idFolder = "{309F9680-0000-CB19-90EF-9A890C7CD344}";
		boolean folderBorrado = crud.borrarFolder(os, claseDocumental, idFolder);
		assertTrue(folderBorrado);
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
