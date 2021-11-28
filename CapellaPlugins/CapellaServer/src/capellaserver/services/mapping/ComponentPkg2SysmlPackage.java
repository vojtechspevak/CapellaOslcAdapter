package capellaserver.services.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;

import capellaserver.domain.Element;
import capellaserver.domain.Link;
import capellaserver.domain.SysmlClass;
import capellaserver.domain.SysmlPackage;

public class ComponentPkg2SysmlPackage extends AbstractMapping implements IMapping {

	public ComponentPkg2SysmlPackage() {
		_source = LogicalComponentPkg.class;
		_target = SysmlPackage.class;
	}
	
	@Override
	public Element map(EObject eObject, String linkBaseUrl) {
		if(!(eObject instanceof LogicalComponentPkg)) {
			String errorMessage = "Cannot map argument of type " + eObject.getClass().getName() + " to type SysmlPackage";
			throw new IllegalArgumentException(errorMessage);
		}
		LogicalComponentPkg capellaElement = 
				(LogicalComponentPkg) eObject;
		SysmlPackage result = new SysmlPackage();
		for(CapellaElement component : capellaElement.getOwnedLogicalComponents()) {
			result.addOwnedMember(new Link(createURI(linkBaseUrl + component.getId())));
		}
		for(CapellaElement pkg : capellaElement.getOwnedLogicalComponentPkgs()) {
			result.addMember(new Link(createURI(linkBaseUrl + pkg.getId())));
		}
		for(CapellaElement ex : capellaElement.getOwnedComponentExchanges()) {
			result.addOwnedRelationship(new Link(createURI(linkBaseUrl + ex.getId())));
		}
//		for(CapellaElement pkg : capellaElement.getOwnedLogicalComponentPkgs()) {
//			result.addMember(new Link(createURI(linkBaseUrl + pkg.getId())));
//		}
//		
//		for(CapellaElement ownedFeature : capellaElement.getOwnedFeatures()) {
//			result.addOwnedFeature(new Link(createURI(linkBaseUrl + ownedFeature.getId())));
//		}
//		for(CapellaElement ownedPropVal : capellaElement.getOwnedPropertyValues()) {
//			result.addOwnedMember(new Link(createURI(linkBaseUrl + ownedPropVal.getId())));
//		}
//		for(AbstractConstraint constraint : capellaElement.getOwnedConstraints()) {
//			result.addOwnedFeature(new Link(createURI(linkBaseUrl + constraint.getId())));
//		}
		
		if(eObject.eContainer() != null && eObject.eContainer() instanceof CapellaElement) {
			Link ownerLink = new Link(createURI(linkBaseUrl +((CapellaElement) eObject.eContainer()).getId()));
			result.setOwner(ownerLink);
		}
		
		result.setIdentifier(capellaElement.getId());
		result.setSysmlIdentifier(capellaElement.getId());
		result.setName(capellaElement.getName());
		result.setDescription(capellaElement.getDescription());
		result.setTitle(capellaElement.getFullLabel());
		result.setShortTitle(capellaElement.getLabel());
		//result.setIsAbstract(capellaElement.isAbstract());
		result.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
		return result;
	}

}
