package com.api.ce.conexiones;

import javax.security.auth.Subject;

import com.api.ce.pojo.DatosConexionOs;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;

public class ConexionOs {

	public ObjectStore obtenerConexionObjectStore(DatosConexionOs datos) {

		Connection conn = Factory.Connection.getConnection(datos.getUrl());
		if (datos.isConexionWsi()) {
			Subject subject = UserContext.createSubject(conn, datos.getUsuario(), datos.getContrasena(),
					"FileNetP8WSI");
			UserContext uc = UserContext.get();
			uc.pushSubject(subject);
		}
		Domain domain = Factory.Domain.fetchInstance(conn, datos.getDominio(), null);
		return Factory.ObjectStore.fetchInstance(domain, datos.getObjectStore(), null);
	}

}
