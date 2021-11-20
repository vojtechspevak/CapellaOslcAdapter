package capellaserver.helpers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EObjectSerializer {

	public static String serializeEObjectToJson(EObject eObject) {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		for(EStructuralFeature structuralFeature : eObject.eClass().getEAllStructuralFeatures()) {
			json.addProperty(structuralFeature.getName(),  Boolean.valueOf(structuralFeature.isRequired()));
			if(structuralFeature.isMany()) {
				addArrayToJson(gson,structuralFeature,json,eObject);
			}
		}

		return json.toString();
	}
	
	private static void addArrayToJson(Gson gson, EStructuralFeature structuralFeature, JsonObject json,EObject eObject) {
		EList<EObject> eObjectList = (EList<EObject>) eObject.eGet(structuralFeature);
		if(eObjectList.isEmpty()) {
			return;
		}
		
		final EStructuralFeature idFeature = eObjectList
				.get(0)
			.eClass()
			.getEAllStructuralFeatures()
			.stream()
			.filter(sf -> sf.getName().equals("id"))
			.findFirst()
			.orElse(null);

		if(idFeature != null && idFeature.getEType().isInstance("")) {
			List<Object> listToAdd = eObjectList
					.stream()
					.map(e ->  {return new Object(){String id = (String) e.eGet(idFeature);};})
					.collect(Collectors.toList());
			json.addProperty(structuralFeature.getName(), gson.toJson(listToAdd));
		}
	}
	
	
	public static Object getRawEObjectProperty(EObject eObject, String propertyName) {
    	
		final EList<EStructuralFeature> structuralFeatures = eObject.eClass().getEAllStructuralFeatures();
    	final Optional<EStructuralFeature> optionalFeature = structuralFeatures
    			.stream()
    			.filter(sf -> sf.getName().equals(propertyName))
    			.findFirst();
    	
    	if (!optionalFeature.isPresent()) {
    		return null;
    	}
    	
  		return eObject.eGet(optionalFeature.get(),true);
   		
	}

	
	
	public static EObject getEObjectProperty(EObject eObject, String propertyName) {
    	
		Object object = getRawEObjectProperty(eObject,propertyName);
		
		if (object == null || !(object instanceof EObject)) {
			return null;
//			throw new IllegalArgumentException("Getting EObject property failed");
		}
		
   		return (EObject) object;
	}

	public static EList<EObject> getEObjectCollectionProperty(EObject eObject, String propertyName) {
    	
		Object object = getRawEObjectProperty(eObject,propertyName);
		
		if (object == null || !(object instanceof EList<?>)) {
			return null;
//			throw new IllegalArgumentException("Getting EList property failed");
		}
		
   		return (EList<EObject>) object;
   		
	}
}
