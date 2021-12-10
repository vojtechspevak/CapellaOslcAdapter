package capellaapi.helpers;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Helper methods for EObjects manipulation
 */
public class EmfHelper {

	/**
	 * retrieves a property of a given name if such property is present in the EObject
	 * and returns it as Object
	 * @param eObject
	 * @param propertyName
	 * @return retrieved property or null if the eObject does not contain such property
	 */
	public static Object getRawEObjectProperty(EObject eObject, String propertyName) {
    	
		final EList<EStructuralFeature> structuralFeatures = eObject.eClass().getEAllStructuralFeatures();
    	final EStructuralFeature structuralFeature = structuralFeatures
    			.stream()
    			.filter(sf -> sf.getName().equals(propertyName))
    			.findFirst().orElse(null);
    	
    	return structuralFeature == null 
    			? null
    			: eObject.eGet(structuralFeature);   		
	}

	/**
	 * return EObject property of a given name if such property is present in the EObject
	 * @param eObject
	 * @param propertyName
	 * @return EObject or null if the eObject does not contain such EObject property
	 */
	public static EObject getEObjectProperty(EObject eObject, String propertyName) {
    	
		Object object = getRawEObjectProperty(eObject,propertyName);
		
		if (object == null || !(object instanceof EObject)) {
			return null;
		}
		
   		return (EObject) object;
	}

	/**
	 * return EList property of a given name if such property is present in the EObject
	 * @param eObject
	 * @param propertyName
	 * @return EList or null if the eObject does not contain such EList property
	 */
	public static EList<Object> getEObjectCollectionProperty(EObject eObject, String propertyName) {
    	
		Object object = getRawEObjectProperty(eObject,propertyName);
		
		if (object == null || !(object instanceof EList<?>)) {
			return null;
		}
		
   		return (EList<Object>) object;
	}
	
	
}
