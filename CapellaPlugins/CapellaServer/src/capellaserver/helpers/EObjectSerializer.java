package capellaserver.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class EObjectSerializer {
	
	public static JsonObject serializeObjectWithMetaData(EObject eObject) {
		Gson gson = new Gson();
		JsonObject serializedObject = serializeEObjectToJson(eObject);
		JsonObject metaData = new JsonObject();
		JsonArray jsonArrayTypes = gson.toJsonTree(getAllObjectTypes(eObject.getClass())).getAsJsonArray();
		metaData.add("types",jsonArrayTypes);
		EObject container =  eObject.eContainer();
		if (container!= null) {
			EStructuralFeature idFeature = getIdStructuralFeature(container);
			if (idFeature != null && idFeature.getEType().isInstance("")) {
				IdentifiableReferenceObject containerProperty = new IdentifiableReferenceObject(
						(String) container.eGet(idFeature));
				metaData.add("eContainer", gson.toJsonTree(containerProperty));
			}
		}
		JsonObject elementWithMetaData = new JsonObject();
		elementWithMetaData.add("element", serializedObject);
		elementWithMetaData.add("types", metaData);
		return elementWithMetaData;
	}

	public static JsonObject serializeEObjectToJson(EObject eObject) {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		for (EStructuralFeature structuralFeature : eObject.eClass().getEAllStructuralFeatures()) {
			// TODO should skip the properties that are computed from others (could be
			// useful)?
			if (structuralFeature.isDerived()) {
				continue;
			}
			if (structuralFeature.isMany()) {
				serializeCollection(gson, structuralFeature, json, eObject);
			} else {
				serializeProperty(gson, structuralFeature, json, eObject);
			}
		}

		return json;
	}

	private static void serializeCollection(Gson gson, EStructuralFeature structuralFeature, JsonObject json,
			EObject eObject) {
		EList<Object> list = (EList<Object>) eObject.eGet(structuralFeature);

		if (list.isEmpty()) { // TODO maybe check if it is required?
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
		EObject eProperty = (EObject) property;
		EStructuralFeature idFeature = getIdStructuralFeature(eProperty);
		if (idFeature != null && idFeature.getEType().isInstance("")) {
			IdentifiableReferenceObject referenceProperty = new IdentifiableReferenceObject(
					(String) eProperty.eGet(idFeature));
			json.add(structuralFeature.getName(), gson.toJsonTree(referenceProperty));
		}

//		if(structuralFeature.getEType().getInstanceClassName() == null) {
//			
//		}
		// if(structuralFeature.isRequired() && ) TODO maybe consider handling required
		// null attributes

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

	public static List<String> getAllObjectTypes(Class elementClass) {
		List<String> classNameList = new ArrayList<String>();
		while (elementClass != null) {
			classNameList.add(elementClass.getSimpleName());
			elementClass = elementClass.getSuperclass();
		}
		return classNameList;
	}

}
