package com.api.ce.utils;

import java.util.Collection;

import com.filenet.api.constants.PropertyNames;
import com.filenet.api.property.FilterElement;
import com.filenet.api.property.PropertyFilter;

public class FiltrosPropiedad {

	public PropertyFilter obtenerFiltroxId() {

		PropertyFilter pf = new PropertyFilter();
		FilterElement fe = new FilterElement(1, null, Boolean.TRUE, "Id", null);
		pf.addIncludeProperty(fe);
		return pf;
	}

	public PropertyFilter obtenerFiltroxColeccion(Collection<String> list) {
		PropertyFilter pf = new PropertyFilter();
		list.forEach(
				propertyName -> pf.addIncludeProperty(new FilterElement(1, null, Boolean.TRUE, propertyName, null)));
		return pf;
	}

	public PropertyFilter obtenerFiltroContenido() {
		PropertyFilter pf = new PropertyFilter();
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.ID, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_SIZE, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_ELEMENTS, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.VERSION_STATUS, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.VERSION_SERIES, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.VERSION_SERIES_ID, null));
		return pf;
	}
	
	public PropertyFilter obtenerFiltroDescripcionClase() {
		PropertyFilter pf = new PropertyFilter();
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CLASS_DESCRIPTION, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.PROPERTY_DESCRIPTIONS, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.NAME, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.SYMBOLIC_NAME, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.DISPLAY_NAME, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.DESCRIPTIVE_TEXT, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.DEFAULT_INSTANCE_OWNER, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.HAS_INCLUDE_SUBCLASSES, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.ID, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.IS_HIDDEN, null));	
		return pf;
	}
	
	public PropertyFilter obtenerFiltroxAnotacion() {
		PropertyFilter pf = new PropertyFilter();
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.ANNOTATIONS, null));
		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.ANNOTATED_CONTENT_ELEMENT, null));
		return pf;
	}

}
