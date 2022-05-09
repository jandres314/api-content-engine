package com.api.ce.pojo;

import java.io.InputStream;

public class DatosContenidoDocumento {

	private String nombreArchivo;
	private boolean versionMayor;
	private String contentType;
	private InputStream stream;

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public boolean isVersionMayor() {
		return versionMayor;
	}

	public void setVersionMayor(boolean versionMayor) {
		this.versionMayor = versionMayor;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

}
