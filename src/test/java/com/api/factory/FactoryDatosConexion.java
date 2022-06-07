package com.api.factory;

import com.api.ce.pojo.DatosConexionOs;
import com.api.pe.pojo.DatosConexionPe;

public class FactoryDatosConexion {
	
	private FactoryDatosConexion() {}
	
	public static DatosConexionOs obtenerDatosConexion() {
		DatosConexionOs datos = new DatosConexionOs();
		datos.setContrasena("Pratech2016$");
		datos.setUrl("http://10.94.84.167:9080/wsi/FNCEWS40MTOM");
		datos.setDominio("P8DomainDev");
		datos.setObjectStore("CMCOMUL");
		datos.setUsuario("p8admindllo");
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
