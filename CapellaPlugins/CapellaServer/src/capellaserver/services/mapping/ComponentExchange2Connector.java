package capellaserver.services.mapping;

import java.net.URI;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.ComponentExchange;

import capellaserver.domain.Connector;
import capellaserver.domain.Element;
import capellaserver.domain.Link;

public class ComponentExchange2Connector extends AbstractMapping implements IMapping {

	public ComponentExchange2Connector() {
		_source = ComponentExchange.class;
		_target = Connector.class;
	}
	
	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
		ComponentExchange capellaElement = (ComponentExchange) source;
		Connector target = new Connector();
		CapellaElement capellaSourcePort = capellaElement.getSourcePort();
		if(capellaSourcePort != null) {
			URI exchangeSourceUri = createURI(linkBaseUrl + capellaSourcePort.getId());
			target.addSysmlSource(new Link(exchangeSourceUri));
		}
		CapellaElement capellaTargetPort = capellaElement.getTargetPort();
		if(capellaTargetPort != null) {
			URI exchangeSourceUri = createURI(linkBaseUrl + capellaTargetPort.getId());
			target.addTarget(new Link(exchangeSourceUri));
		}
		CapellaElement capellaSourcePart = capellaElement.getSourcePart();
		if(capellaSourcePart != null) {
			URI exchangeSourceUri = createURI(linkBaseUrl + capellaSourcePart.getId());
			target.addConnectorEnd(new Link(exchangeSourceUri));
		}
		CapellaElement capellaTargetPart = capellaElement.getTargetPart();
		if(capellaTargetPart != null) {
			URI exchangeSourceUri = createURI(linkBaseUrl + capellaTargetPart.getId());
			target.addConnectorEnd(new Link(exchangeSourceUri));
		}
		for(CapellaElement feature : capellaElement.getFeatures()) {
			target.addFeature(new Link(createURI(linkBaseUrl + feature.getId())));
		}

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
