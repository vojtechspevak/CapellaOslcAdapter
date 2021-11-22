package org.capella.oslc.mapping;

import org.apache.jena.atlas.logging.Log;
import org.capella.oslc.domains.capella.EObjectMock;
import org.capella.oslc.domains.capella.IdentifiableReferenceObject;
import org.eclipse.lyo.oslc4j.core.model.Link;
import org.oasis.oslcop.sysml.Element;
import org.oasis.oslcop.sysml.SysmlClass;

public class Class2SysmlClass extends AbstractMapping implements IMapping {

	public Class2SysmlClass() {
		_source = org.capella.oslc.domains.capella.Class.class;
		_target = SysmlClass.class;
	}

	@Override
	public Element map(EObjectMock eObject, String linkBaseUrl) {
		if(!(eObject instanceof org.capella.oslc.domains.capella.Class)) {
			String errorMessage = "Cannot map argument of type " + eObject.getClass().getName() + " to type SysmlClass";
			Log.error(getClass(), errorMessage );
			throw new IllegalArgumentException(errorMessage);
		}
		org.capella.oslc.domains.capella.Class capellaElement = (org.capella.oslc.domains.capella.Class) eObject;
		SysmlClass result = new SysmlClass();
		for(IdentifiableReferenceObject gen : capellaElement.getOwnedGeneralizations()) {
			result.addOwnedGeneralization(new Link(createURI(linkBaseUrl + gen.getId())));
		}
		for(IdentifiableReferenceObject feature : capellaElement.getFeatures()) {
			result.addFeature(new Link(createURI(linkBaseUrl + feature.getId())));
		}
		for(IdentifiableReferenceObject ownedFeature : capellaElement.getOwnedFeatures()) {
			result.addOwnedFeature(new Link(createURI(linkBaseUrl + ownedFeature.getId())));
		}
		for(IdentifiableReferenceObject ownedPropVal : capellaElement.getOwnedPropertyValues()) {
			result.addOwnedMember(new Link(createURI(linkBaseUrl + ownedPropVal.getId())));
		}
		for(IdentifiableReferenceObject constraint : capellaElement.getOwnedConstraints()) {
			result.addOwnedFeature(new Link(createURI(linkBaseUrl + constraint.getId())));
		}
		
		if(eObject.eContainer() != null) {
			Link ownerLink = new Link(createURI(linkBaseUrl +eObject. eContainer().getId()));
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
