package com.api.ce.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import com.api.ce.conexiones.ConexionOs;
import com.api.ce.conexiones.FactoryDatosConexion;
import com.api.ce.pojo.DatosContenidoDocumento;
import com.api.ce.pojo.DatosCreacionObjeto;
import com.filenet.api.core.ObjectStore;

public class CrudDocumentoTest {

	private CrudDocumento crud = new CrudDocumento();
	private ConexionOs conexionOs = new ConexionOs();

	@Test
	public void crearDocumento() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		DatosCreacionObjeto datosCreacionObjeto = this.generarObjetoCreacion();
		DatosContenidoDocumento contenido = this.generarObjetoContenido();
		String id = crud.crearDocumento(os, datosCreacionObjeto, contenido);
		assertNotNull(id);
	}
	
	@Test
	public void versionarDocumento() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		DatosContenidoDocumento contenido = this.generarObjetoContenido();
		String idDocumento = "{B0D89980-0000-C11B-837B-9234933C0FCA}";
		String id = crud.versionarDocumento(os, idDocumento, contenido);
		assertNotNull(id);
	}
	
	@Test
	public void descargarContenido() {
		ObjectStore os = conexionOs.obtenerConexionObjectStore(FactoryDatosConexion.obtenerDatosConexion());
		String idDocumento = "{B0E99980-0000-CF11-A7DA-41CFD71ADAD8}";
		DatosContenidoDocumento contenido = crud.extraerContenido(os, idDocumento);
		String content = new BufferedReader(new InputStreamReader(contenido.getStream(), StandardCharsets.UTF_8)).lines()
		        .collect(Collectors.joining("\n"));
		assertEquals(contenido.getContentType(), "text/plain");
		assertNotNull(contenido.getNombreArchivo());
		assertNotNull(contenido.getNombreArchivo());
		assertNotNull(content);
	}

	private DatosContenidoDocumento generarObjetoContenido() {
		String contenidoDocumento = "contenido del documento";
		InputStream stream = new ByteArrayInputStream(contenidoDocumento.getBytes());

		DatosContenidoDocumento contenido = new DatosContenidoDocumento();
		contenido.setContentType("text/plain");
		contenido.setNombreArchivo(UUID.randomUUID().toString());
		contenido.setVersionMayor(Boolean.TRUE);
		contenido.setStream(stream);
		return contenido;
	}

	private DatosCreacionObjeto generarObjetoCreacion() {

		Map<String, Object> valores = new HashMap<>();
		valores.put("DocumentTitle", UUID.randomUUID().toString());

		DatosCreacionObjeto datos = new DatosCreacionObjeto();
		datos.setClaseDocumental("Document");
		datos.setIdFolderPadre("{4C7929E3-8C2F-407E-807F-F494E504C392}");
		datos.setValores(valores);

		return datos;
	}

}
