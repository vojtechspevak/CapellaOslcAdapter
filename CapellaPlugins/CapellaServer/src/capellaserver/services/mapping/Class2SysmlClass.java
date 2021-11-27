package capellaserver.services.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

import capellaserver.domain.Element;
import capellaserver.domain.Link;
import capellaserver.domain.SysmlClass;

public class Class2SysmlClass extends AbstractMapping implements IMapping {

	public Class2SysmlClass() {
		_source = org.polarsys.capella.core.data.information.Class.class;
		_target = SysmlClass.class;
	}

	@Override
	public Element map(EObject eObject, String linkBaseUrl) {
		if(!(eObject instanceof org.polarsys.capella.core.data.information.Class)) {
			String errorMessage = "Cannot map argument of type " + eObject.getClass().getName() + " to type SysmlClass";
			throw new IllegalArgumentException(errorMessage);
		}
		org.polarsys.capella.core.data.information.Class capellaElement = 
				(org.polarsys.capella.core.data.information.Class) eObject;
		SysmlClass result = new SysmlClass();
		for(CapellaElement gen : capellaElement.getOwnedGeneralizations()) {
			result.addOwnedGeneralization(new Link(createURI(linkBaseUrl + gen.getId())));
		}
		for(CapellaElement feature : capellaElement.getFeatures()) {
			result.addFeature(new Link(createURI(linkBaseUrl + feature.getId())));
		}
		for(CapellaElement ownedFeature : capellaElement.getOwnedFeatures()) {
			result.addOwnedFeature(new Link(createURI(linkBaseUrl + ownedFeature.getId())));
		}
		for(CapellaElement ownedPropVal : capellaElement.getOwnedPropertyValues()) {
			result.addOwnedMember(new Link(createURI(linkBaseUrl + ownedPropVal.getId())));
		}
		for(AbstractConstraint constraint : capellaElement.getOwnedConstraints()) {
			result.addOwnedFeature(new Link(createURI(linkBaseUrl + constraint.getId())));
		}
		
		if(eObject.eContainer() != null && eObject.eContainer() instanceof CapellaElement) {
			Link ownerLink = new Link(createURI(linkBaseUrl +((CapellaElement) eObject.eContainer()).getId()));
			result.setOwner(ownerLink);
		}
		
		result.setIdentifier(capellaElement.getId());
		result.setSysmlIdentifier(capellaElement.getId());
		result.setName(capellaElement.getName());
		result.setDescription(capellaElement.getDescription());
		result.setTitle(capellaElement.getName());
		result.setShortTitle(capellaElement.getName());
		result.setIsAbstract(capellaElement.isAbstract());
		result.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
		return result;
	}
}
