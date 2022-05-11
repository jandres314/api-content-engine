package com.api.factory;

import com.api.ce.pojo.DatosConexionOs;
import com.api.pe.pojo.DatosConexionPe;

public class FactoryDatosConexion {
	
	private FactoryDatosConexion() {}
	
	public static DatosConexionOs obtenerDatosConexion() {
		DatosConexionOs datos = new DatosConexionOs();
		datos.setContrasena("");
		datos.setUrl("");
		datos.setDominio("");
		datos.setObjectStore("");
		datos.setUsuario("");
		datos.setConexionWsi(true);
		return datos;
	}
	
	public static DatosConexionPe obtenerDatosConexionPe() {
		
		DatosConexionPe datosConexion = new DatosConexionPe();
		datosConexion.setContrasena("");
		datosConexion.setPuntoConexion("");
		datosConexion.setUrl("");
		datosConexion.setUsuario("");
		return datosConexion;
	}

}
