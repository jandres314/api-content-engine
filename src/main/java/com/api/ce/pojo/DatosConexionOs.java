package com.api.ce.pojo;

public class DatosConexionOs {

	private String usuario;
	private String contrasena;
	private String dominio;
	private String url;
	private String objectStore;
	private boolean conexionWsi;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getObjectStore() {
		return objectStore;
	}

	public void setObjectStore(String objectStore) {
		this.objectStore = objectStore;
	}

	public boolean isConexionWsi() {
		return conexionWsi;
	}

	public void setConexionWsi(boolean conexionWsi) {
		this.conexionWsi = conexionWsi;
	}

}
