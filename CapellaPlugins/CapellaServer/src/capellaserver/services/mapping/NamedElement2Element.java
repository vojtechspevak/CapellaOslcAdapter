package capellaserver.services.mapping;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;

import capellaserver.domain.Element;
import capellaserver.domain.Link;

public class NamedElement2Element extends AbstractMapping implements IMapping {

	public NamedElement2Element() {
		_source = NamedElement.class;
		_target = Element.class;
	}

	@Override
	public Element map(EObject eObject, String linkBaseUrl) {
		if(!(eObject instanceof NamedElement)) {
			String errorMessage = "Cannot map argument of type " + eObject.getClass().getName() + " to type Element";
			throw new IllegalArgumentException(errorMessage);
		}
		NamedElement capellaElement = (NamedElement) eObject;
		Element result = new Element();
		Set<Link> contentsSet = eObject.eContents()
				.stream()
				.filter(e -> (e instanceof CapellaElement)) // ModelElement seems to be the most generic one
				.map(ne -> new Link(createURI(linkBaseUrl + ((CapellaElement) ne).getId())))
				.collect(Collectors.toSet());
			
			result.setOwnedElement(contentsSet);
			if(eObject.eContainer() != null && eObject.eContainer() instanceof CapellaElement) {
				Link ownerLink = new Link(createURI(linkBaseUrl +((CapellaElement) eObject.eContainer()).getId()));
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
