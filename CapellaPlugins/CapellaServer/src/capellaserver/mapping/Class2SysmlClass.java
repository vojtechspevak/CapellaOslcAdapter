package capellaserver.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

import capellaserver.domain.Element;
import capellaserver.domain.Link;
import capellaserver.domain.SysmlClass;

public class Class2SysmlClass extends AbstractMapping {

	public Class2SysmlClass() {
		_source = org.polarsys.capella.core.data.information.Class.class;
		_target = SysmlClass.class;
	}

	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
		org.polarsys.capella.core.data.information.Class capellaElement = 
				(org.polarsys.capella.core.data.information.Class) source;
		SysmlClass target = new SysmlClass();
		for(CapellaElement gen : capellaElement.getOwnedGeneralizations()) {
			target.addOwnedGeneralization(new Link(createURI(linkBaseUrl + gen.getId())));
		}
		for(CapellaElement feature : capellaElement.getFeatures()) {
			target.addFeature(new Link(createURI(linkBaseUrl + feature.getId())));
		}
		for(CapellaElement ownedFeature : capellaElement.getOwnedFeatures()) {
			target.addOwnedFeature(new Link(createURI(linkBaseUrl + ownedFeature.getId())));
		}
		for(CapellaElement ownedPropVal : capellaElement.getOwnedPropertyValues()) {
			target.addOwnedMember(new Link(createURI(linkBaseUrl + ownedPropVal.getId())));
		}
		for(AbstractConstraint constraint : capellaElement.getOwnedConstraints()) {
			target.addOwnedFeature(new Link(createURI(linkBaseUrl + constraint.getId())));
		}

		target.setIdentifier(capellaElement.getId());
		target.setSysmlIdentifier(capellaElement.getId());
		target.setName(capellaElement.getName());
		target.setDescription(capellaElement.getDescription());
		target.setTitle(capellaElement.getName());
		target.setShortTitle(capellaElement.getName());
		target.setIsAbstract(capellaElement.isAbstract());
		target.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
		setOwnerIfPresent(source, target, linkBaseUrl);
		addAllCapellaTypes(target,capellaElement.getClass());
		return target;
	}
}
