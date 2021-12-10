package capellaserver.server.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Class holding the functionality for generic serialization of EObjects 
 */
public class EObjectSerializer {
	
	/**
	 * Serializes a single eObject using the reflective API
	 * references are replaced with a simple object containing only id
	 * or not added at all if the reference object does not have id. 
	 * @param eObject object to be serialized
	 * @return object serialized to json
	 */
	public static JsonObject serializeEObject(EObject eObject) {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		
		EObject container =  eObject.eContainer();
		IdentifiableReferenceObject containerProperty = getIdentifiableReferenceObjectFromEObject(container);
		if(containerProperty != null) {
			json.add("eContainer", gson.toJsonTree(containerProperty));
		}
		
		for (EStructuralFeature structuralFeature : eObject.eClass().getEAllStructuralFeatures()) {
			// if desired, properties computed from others can be skipped by uncommenting the following code 
			//if (structuralFeature.isDerived()) {
			//	continue;
			//}
			if (structuralFeature.isMany()) {
				serializeCollection(gson, structuralFeature, json, eObject);
			} else {
				serializeProperty(gson, structuralFeature, json, eObject);
			}
		}

		return json;
	}
	
	/**
	 * method for List serialization using the serializeEObject method 
	 * @param eObjects list to be serialized
	 * @return jsonArray representation of the list 
	 */
	public static JsonArray serializeEObjectCollection(List<EObject> eObjects) {
		JsonArray resultArray = new JsonArray(eObjects.size());
		for(EObject eObject : eObjects){
			resultArray.add(serializeEObject(eObject));
		}
		return resultArray;
	}

	/**
	 * method for serializing all object types into json
	 * @param eObject object for which the types should be serialized
	 * @return jsonArray with the types
	 */
	public static JsonArray serializeObjectTypes(EObject eObject) {
		Gson gson = new Gson();
		return gson.toJsonTree(getAllObjectTypes(eObject.getClass())).getAsJsonArray();
	}
	
	
	/**
	 * Class used for serialization of the reference objects
	 */
	static class IdentifiableReferenceObject {

		private String id;

		public IdentifiableReferenceObject(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}
	}

	/**
	 * returns list of object types and super-types simple names
	 * @param clazz Class to get types of
	 * @return list of strings retrieved as class.getSimpleName() from clazz and its super classes
	 */
	private static List<String> getAllObjectTypes(Class<?> clazz) {
		List<String> classNameList = new ArrayList<String>();
		while (clazz != null) {
			classNameList.add(clazz.getSimpleName());
			clazz = clazz.getSuperclass();
		}
		return classNameList;
	}
	
	/**
	 * helper method for serializing a single property
	 * if property is a reference to another eObject with attribute id, 
	 * it is replaced with simple object that only contains attribute id 
	 * to be traceable from the response  
	 * @param gson object used for the serialization
	 * @param structuralFeature feature that is being srialized 
	 * @param json resulting json
	 * @param eObject serialized object
	 */
	private static void serializeProperty(Gson gson, EStructuralFeature structuralFeature, JsonObject json,
			EObject eObject) {
		Object property = eObject.eGet(structuralFeature);
		if (property == null) {
			return;
		}
		if (property instanceof String) {
			json.addProperty(structuralFeature.getName(), (String) property);
			return;
		}
		if (property instanceof Boolean) {
			json.addProperty(structuralFeature.getName(), (Boolean) property);
			return;
		}
		if (property instanceof Number) {
			json.addProperty(structuralFeature.getName(), (Number) property);
			return;
		}
		if (!(property instanceof EObject)) {
			return;
		}
		
		IdentifiableReferenceObject referenceProperty = getIdentifiableReferenceObjectFromEObject((EObject) property);
		if (referenceProperty != null) {
			json.add(structuralFeature.getName(), gson.toJsonTree(referenceProperty));
		}
	}

	/**
	 * helper method for serializing collection of properties
	 * @param gson object used for the serialization
	 * @param structuralFeature feature that is being serialized 
	 * @param json resulting json
	 * @param eObject serialized object
	 */
	private static void serializeCollection(Gson gson, EStructuralFeature structuralFeature, JsonObject json,
			EObject eObject) {
		EList<Object> list = (EList<Object>) eObject.eGet(structuralFeature);

		if (list.isEmpty()) {
			return;
		}

		if (!(list.get(0) instanceof EObject)) {
			serializeValueTypeCollection(gson, list, json, structuralFeature);
			return;
		}
		
		EList<EObject> eObjectList = (EList) list;

		final EStructuralFeature idFeature = getIdStructuralFeature(eObjectList.get(0));

		if (idFeature != null && idFeature.getEType().isInstance("")) {
			List<Object> listToAdd = eObjectList.stream().map(e -> {
				return new IdentifiableReferenceObject((String) e.eGet(idFeature));
			}).collect(Collectors.toList());
			JsonArray jsonArray = gson.toJsonTree(listToAdd).getAsJsonArray();
			json.add(structuralFeature.getName(), jsonArray);
		}
	}

	/**
	 * Serializes object of value types (meaning String, bool or numeric value) to
	 * the resulting json if the value is not either of these, it is no added.
	 * Expects the list to be nonempty.
	 * 
	 * @param gson              - Gson object used for serialization
	 * @param list              - The nonempty list of values to serialize
	 * @param json              - result json being created
	 * @param structuralFeature - feature corresponding to the collection property
	 */
	private static void serializeValueTypeCollection(Gson gson, EList<Object> list, JsonObject json,
			EStructuralFeature structuralFeature) {
		Object firstElement = list.get(0);

		if (firstElement instanceof String || firstElement instanceof Boolean || firstElement instanceof Number) {
			JsonArray jsonArray = gson.toJsonTree(json).getAsJsonArray();
			json.add(structuralFeature.getName(), jsonArray);
		}

	}


	/**
	 * helper method to extract the structural feature corresponding to id property
	 * 
	 * @param eObject EObject to extract the feature from
	 * @return structural feature corresponding to id property if exists, null
	 *         otherwise
	 */
	private static EStructuralFeature getIdStructuralFeature(EObject eObject) {
		return eObject.eClass().getEAllStructuralFeatures().stream().filter(sf -> sf.getName().equals("id")).findFirst()
				.orElse(null);
	}

	
	/**
	 * helper method to extract the structural feature corresponding to id property
	 * 
	 * @param eObject EObject to extract the feature from
	 * @return structural feature corresponding to id property if exists, null
	 *         otherwise
	 */
	private static IdentifiableReferenceObject getIdentifiableReferenceObjectFromEObject(EObject eObject) {
		if(eObject == null) {
			return null;
		}
		EStructuralFeature idFeature = getIdStructuralFeature(eObject);
		
		if(idFeature == null || !idFeature.getEType().isInstance("")) {
			return null;
		}
		return  new IdentifiableReferenceObject((String) eObject.eGet(idFeature));
	}
	
}
