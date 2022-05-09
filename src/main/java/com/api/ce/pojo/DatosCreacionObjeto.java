package com.api.ce.pojo;

import java.util.Map;

public class DatosCreacionObjeto {

	private String claseDocumental;
	private Map<String, Object> valores;
	private String idFolderPadre;

	public String getClaseDocumental() {
		return claseDocumental;
	}

	public void setClaseDocumental(String claseDocumental) {
		this.claseDocumental = claseDocumental;
	}

	public Map<String, Object> getValores() {
		return valores;
	}

	public void setValores(Map<String, Object> valores) {
		this.valores = valores;
	}

	public String getIdFolderPadre() {
		return idFolderPadre;
	}

	public void setIdFolderPadre(String idFolderPadre) {
		this.idFolderPadre = idFolderPadre;
	}

}
