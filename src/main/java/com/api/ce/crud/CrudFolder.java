package com.api.ce.crud;

import java.util.Map;

import com.api.ce.pojo.DatosCreacionObjeto;
import com.api.ce.utils.FiltrosPropiedad;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.property.PropertyFilter;
import com.filenet.api.util.Id;

public class CrudFolder {

	private final FiltrosPropiedad filtrosPropiedad = new FiltrosPropiedad();

	public String crearFolder(ObjectStore os, DatosCreacionObjeto datosCreacionObjeto) {

		// creacion objeto
		Folder folder = Factory.Folder.createInstance(os, datosCreacionObjeto.getClaseDocumental());

		// set propiedades
		datosCreacionObjeto.getValores().entrySet()
				.forEach(entry -> folder.getProperties().putObjectValue(entry.getKey(), entry.getValue()));

		// referencia folder padre
		Folder folderPadre = Factory.Folder.fetchInstance(os, datosCreacionObjeto.getIdFolderPadre(), null);
		folder.set_Parent(folderPadre);

		// commit
		folder.save(RefreshMode.REFRESH, filtrosPropiedad.obtenerFiltroxId());
		return folder.get_Id().toString();
	}

	public int actualizarPropiedaesFolder(ObjectStore os, String idFolder, Map<String, Object> valores) {

		// recuperacion objeto
		PropertyFilter pf = this.filtrosPropiedad.obtenerFiltroxColeccion(valores.keySet());
		Folder folder = Factory.Folder.fetchInstance(os, new Id(idFolder), pf);

		// set propiedades
		valores.entrySet().forEach(entry -> folder.getProperties().putObjectValue(entry.getKey(), entry.getValue()));

		// commit
		folder.save(RefreshMode.NO_REFRESH);
		return valores.size();
	}
	
	public boolean borrarFolder(ObjectStore os, String claseDocumental, String idFolder) {

		// recuperacion objeto
		Folder folder = Factory.Folder.getInstance(os, claseDocumental, new Id(idFolder));
		
		// delete
		folder.delete();
		
		// commit
		folder.save(RefreshMode.NO_REFRESH);
		return Boolean.TRUE;
	}

}
