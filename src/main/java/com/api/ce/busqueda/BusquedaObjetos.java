package com.api.ce.busqueda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.filenet.api.collection.IndependentObjectSet;
import com.filenet.api.core.IndependentObject;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.property.Properties;
import com.filenet.api.property.Property;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.query.SearchScope;

public class BusquedaObjetos {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> ejecutarConsulta(ObjectStore store, String query) {

		List<Map<String, Object>> list = new ArrayList<>();

		// scope
		SearchScope scope = new SearchScope(store);

		// ejecucion consulta
		IndependentObjectSet independentObjectSet = scope.fetchObjects(new SearchSQL(query), null, null, null);

		// extraccion de resultados
		Iterator<IndependentObject> it = independentObjectSet.iterator();
		while (it.hasNext()) {
			IndependentObject independentObject = it.next();
			Map<String, Object> map = extraerPropiedades(independentObject);
			list.add(map);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> extraerPropiedades(IndependentObject independentObject) {

		Map<String, Object> map = new HashMap<>();
		Properties properties = independentObject.getProperties();

		Iterator<Property> it = properties.iterator();
		while (it.hasNext()) {
			Property property = it.next();
			map.put(property.getPropertyName(), property.getObjectValue());
		}

		return map;
	}

}
