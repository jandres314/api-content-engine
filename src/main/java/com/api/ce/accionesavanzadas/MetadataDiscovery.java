package com.api.ce.accionesavanzadas;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.api.ce.utils.FiltrosPropiedad;
import com.filenet.api.collection.PropertyDescriptionList;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.meta.ClassDescription;
import com.filenet.api.meta.PropertyDescription;

public class MetadataDiscovery {

	private FiltrosPropiedad filtros = new FiltrosPropiedad();

	public Map<String, Object> extraerTaxonomia(ObjectStore os, String claseDocumental) {

		ClassDescription classDescription = Factory.ClassDescription.fetchInstance(os, claseDocumental,
				filtros.obtenerFiltroDescripcionClase());

		Map<String, Object> values = new HashMap<>();
		values.put("name", classDescription.get_Name());
		values.put("symbolicName", classDescription.get_SymbolicName());
		values.put("displayName", classDescription.get_DisplayName());
		values.put("descriptiveText", classDescription.get_DescriptiveText());
		values.put("defaultInstanceOwner", classDescription.get_DefaultInstanceOwner());
		values.put("hasIncludeSubclasses", classDescription.get_HasIncludeSubclasses());
		values.put("id", classDescription.get_Id());
		values.put("isHidden", classDescription.get_IsHidden());
		values.put("properties", this.extraerInformacionPropiedades(classDescription.get_PropertyDescriptions()));
		return values;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> extraerInformacionPropiedades(PropertyDescriptionList list) {

		Map<String, Object> values = new HashMap<>();
		Iterator<PropertyDescription> it = list.iterator();
		PropertyDescription pd = null;
		while (it.hasNext()) {
			pd = it.next();
			values.put(pd.get_SymbolicName(), extraerInformacionPropiedad(pd));
		}
		return values;
	}

	private Object extraerInformacionPropiedad(PropertyDescription pd) {

		Map<String, Object> values = new HashMap<>();
		values.put("cardinality", pd.get_Cardinality().getValue());
		values.put("dataType", pd.get_DataType().getValue());
		values.put("descriptiveText", pd.get_DescriptiveText());
		values.put("displayName", pd.get_DisplayName());
		values.put("name", pd.get_Name());
		values.put("symbolicName", pd.get_SymbolicName());
		values.put("chiceList", pd.get_ChoiceList());
		values.put("id", pd.get_Id());
		values.put("isHidden", pd.get_IsHidden());
		values.put("isOrderable", pd.get_IsOrderable());
		values.put("isReadOnly", pd.get_IsReadOnly());
		values.put("isSearChable", pd.get_IsSearchable());
		values.put("isSystemGenerated", pd.get_IsSystemGenerated());
		values.put("isSystemOwned", pd.get_IsSystemOwned());
		values.put("isValueRequired", pd.get_IsValueRequired());
		return values;

	}
}