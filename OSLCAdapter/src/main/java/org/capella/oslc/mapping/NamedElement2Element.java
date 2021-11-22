package org.capella.oslc.mapping;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.jena.atlas.logging.Log;
import org.capella.oslc.domains.capella.EObjectMock;
import org.capella.oslc.domains.capella.NamedElement;
import org.eclipse.lyo.oslc4j.core.model.Link;
import org.oasis.oslcop.sysml.Element;

public class NamedElement2Element extends AbstractMapping implements IMapping {

	public NamedElement2Element() {
		_source = NamedElement.class;
		_target = Element.class;
	}

	@Override
	public Element map(EObjectMock eObject, String linkBaseUrl) {
		if(!(eObject instanceof NamedElement)) {
			String errorMessage = "Cannot map argument of type " + eObject.getClass().getName() + " to type Element";
			Log.error(getClass(), errorMessage );
			throw new IllegalArgumentException(errorMessage);
		}
		NamedElement capellaElement = (NamedElement) eObject;
		Element result = new Element();
		Set<Link> contentsSet = eObject.eContents()
				.stream()
				.map(ne -> new Link(createURI(linkBaseUrl + ne.getId())))
				.collect(Collectors.toSet());
			
			result.setOwnedElement(contentsSet);
			if(eObject.eContainer() != null) {
				Link ownerLink = new Link(createURI(linkBaseUrl +eObject. eContainer().getId()));
				result.setOwner(ownerLink);
			}
			
			result.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
			result.setIdentifier(capellaElement.getId());
			result.setSysmlIdentifier(capellaElement.getId());
			result.setName(capellaElement.getName());
			result.setDescription(capellaElement.getDescription());
			result.setTitle(capellaElement.getName());
			result.setShortTitle(capellaElement.getName());
			return result;
	}


	
}
