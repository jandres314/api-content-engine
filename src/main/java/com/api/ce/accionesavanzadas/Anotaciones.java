package com.api.ce.accionesavanzadas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.api.ce.utils.FiltrosPropiedad;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Annotation;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.Id;

public class Anotaciones {
	
	private FiltrosPropiedad filtros = new FiltrosPropiedad();

	public boolean agregarAnotacionDocumento(ObjectStore os, String claseDocumental, String idDocumento,
			String description) {

		Document doc = Factory.Document.fetchInstance(os, new Id(idDocumento), null);

		Annotation anotacion = Factory.Annotation.createInstance(os, claseDocumental);
		anotacion.set_AnnotatedObject(doc);
		anotacion.set_DescriptiveText(description);
		anotacion.save(RefreshMode.NO_REFRESH);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<String> leerAnotacionesDocumento(ObjectStore os, String idDocumento) {

		List<String> descripciones = new ArrayList<>();

		Document doc = Factory.Document.fetchInstance(os, new Id(idDocumento), filtros.obtenerFiltroxAnotacion());
		Iterator<Annotation> it = doc.get_Annotations().iterator();
		while (it.hasNext()) {
			descripciones.add(it.next().get_DescriptiveText());
		}
		return descripciones;
	}

}
