package capellaserver.services.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.OrientationPortKind;

import capellaserver.domain.Element;
import capellaserver.domain.Link;
import capellaserver.domain.PortUsage;

public class ComponentPort2PortUsage extends AbstractMapping implements IMapping {

	public ComponentPort2PortUsage() {
		_source = ComponentPort.class;
		_target = PortUsage.class;
	}
	
	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
		ComponentPort capellaElement = (ComponentPort) source;
		PortUsage target = new PortUsage();
		for(CapellaElement feature : capellaElement.getFeatures()) {
			target.addFeature(new Link(createURI(linkBaseUrl + feature.getId())));
		}

		target.setIsEnd(OrientationPortKind.IN.equals(capellaElement.getOrientation()));
		target.setIsOrdered(capellaElement.isOrdered());
		target.setIdentifier(capellaElement.getId());
		target.setSysmlIdentifier(capellaElement.getId());
		target.setName(capellaElement.getName());
		target.setDescription(capellaElement.getDescription());
		target.setTitle(capellaElement.getName());
		target.setShortTitle(capellaElement.getName());
		target.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
		setOwnerIfPresent(source, target, linkBaseUrl);
		addAllCapellaTypes(target,capellaElement.getClass());
		return target;
	}

}
