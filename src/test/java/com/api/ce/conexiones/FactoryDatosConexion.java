package com.api.ce.conexiones;

import com.api.ce.pojo.DatosConexionOs;

public class FactoryDatosConexion {
	
	public static DatosConexionOs obtenerDatosConexion() {
		DatosConexionOs datos = new DatosConexionOs();
		datos.setContrasena("Pratech2016$");
		datos.setUrl("http://10.94.84.167:9080/wsi/FNCEWS40MTOM");
		datos.setDominio("P8DomainDev");
		datos.setObjectStore("OSDEV");
		datos.setUsuario("p8admindllo");
		datos.setConexionWsi(true);
		return datos;
	}

}
