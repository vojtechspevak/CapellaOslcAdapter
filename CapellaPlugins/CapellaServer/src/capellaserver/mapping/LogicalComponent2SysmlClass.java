package capellaserver.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.la.LogicalComponent;

import capellaserver.domain.Element;
import capellaserver.domain.Link;
import capellaserver.domain.SysmlClass;

public class LogicalComponent2SysmlClass extends AbstractMapping {

	public LogicalComponent2SysmlClass() {
		_source = LogicalComponent.class;
		_target = SysmlClass.class;
	}
	
	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
		LogicalComponent capellaElement = (LogicalComponent) source;
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
		for(CapellaElement component : capellaElement.getOwnedLogicalComponents()) {
			target.addOwnedMember(new Link(createURI(linkBaseUrl + component.getId())));
		}
		
		for(CapellaElement exchange : capellaElement.getOwnedComponentExchanges()) {
			target.addOwnedRelationship(new Link(createURI(linkBaseUrl + exchange.getId())));
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
		addAllSysmlTypes(target,getTargetClass());
		return target;
	}

}
