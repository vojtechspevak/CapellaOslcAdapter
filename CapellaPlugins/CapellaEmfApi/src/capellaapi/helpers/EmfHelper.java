package capellaapi.helpers;

import java.util.Optional;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.impl.PropertyImpl;

public class EmfHelper {

	public static void printEObjectInfo(EObject eObject) {
    	
		final EClass metaObject = eObject.eClass();
    	
    	System.out.print(metaObject.getName() + "\n attributes: ");
		for (EAttribute attr : metaObject.getEAllAttributes()) {
    		System.out.print(attr.getName() +":   " + attr.getEType() + "; ");
    	}
		
        System.out.print("\nContainments: ");
    	for (EReference ref : metaObject.getEAllContainments()) {
    		System.out.print(ref.getName() +":   " + ref.getEType() + "; ");
    	}
    	
        System.out.print("\nReferences:");
    	for (EReference ref : metaObject.getEAllReferences()) {
    		System.out.print(ref.getName() +":   " + ref.getEType() + "; ");
    	}
    	System.out.println("\n");
	} 
	
	
	public static void printEObjectProperty(EObject eObject, String propertyName) {
   	
  		Object feature = getRawEObjectProperty(eObject,propertyName);

  		if (feature == null) {
  			System.out.println("No such property");
    		return;
    	}
  		
  		if (feature instanceof EList<?>) {
   			for (Object item : (EList<Object>) feature) {
   	    		if (item instanceof EObject) {
   	    			EObject eItem = (EObject) item;
   	    			printEObjectInfo(eItem);
   	    		}
   			}
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
	
	public static void setEObjectProperty(EObject eObject, String propertyName, Object newValue) {
		final EList<EStructuralFeature> structuralFeatures = eObject.eClass().getEAllStructuralFeatures();
    	final Optional<EStructuralFeature> optionalFeature = structuralFeatures
    			.stream()
    			.filter(sf -> sf.getName().equals(propertyName))
    			.findFirst();
    	if (!optionalFeature.isPresent()) {
    		System.out.println("Error");
    		return;
    	}
//    	EStructuralFeature f = optionalFeature.get();
//    	if(f == null) {
//    		throw new NullPointerException();
//    	}
    	eObject.eSet(optionalFeature.get(), newValue);

	}
	

	public static void setEObjectProp(EObject element, String propertyName, String newValue,ExecutionManager executionManager) {
		
		//Session session = SessionManager.INSTANCE.getSession(element);
		if(executionManager == null) {
			throw new IllegalArgumentException("Need execution manager to edit things.");
		}
		executionManager.execute(new AbstractReadWriteCommand() {
			  @Override
			  public void run() {
				  element.eSet(element.eClass().getEStructuralFeature(propertyName), newValue); //set the name of the element
				  //((EList)object.eGet(object.eClass().getEStructuralFeature("ownedElements"))).add(element)
				  Object obj = element.eGet(element.eClass().getEStructuralFeature(propertyName));
				  if(obj == null) {
						throw new IllegalArgumentException("Bla.");
				  }
				  //setEObjectProperty(element,propertyName,newValue);
			  }
			});

	}
}
