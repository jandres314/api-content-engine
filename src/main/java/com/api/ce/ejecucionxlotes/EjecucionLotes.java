package com.api.ce.ejecucionxlotes;

import java.util.List;

import com.api.ce.pojo.DatosCreacionObjeto;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.UpdatingBatch;

public class EjecucionLotes {

	public void crearLoteCarpetas(ObjectStore os, List<DatosCreacionObjeto> carpetas) {

		UpdatingBatch batch = UpdatingBatch.createUpdatingBatchInstance(os.get_Domain(), RefreshMode.NO_REFRESH);

		carpetas.forEach(carpeta -> {
			final Folder folder = Factory.Folder.createInstance(os, carpeta.getClaseDocumental());
			carpeta.getValores().entrySet()
					.forEach(entry -> folder.getProperties().putObjectValue(entry.getKey(), entry.getValue()));
			folder.set_Parent(Factory.Folder.fetchInstance(os, carpeta.getIdFolderPadre(), null));
			batch.add(folder, null);
		});
		batch.updateBatch();
	}

}
