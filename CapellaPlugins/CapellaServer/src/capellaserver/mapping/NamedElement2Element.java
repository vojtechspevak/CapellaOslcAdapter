package capellaserver.mapping;

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
	public Element map(EObject source, String linkBaseUrl) {
		if (!(source instanceof NamedElement)) {
			String errorMessage = "Cannot map argument of type " + source.getClass().getName() + " to type Element";
			throw new IllegalArgumentException(errorMessage);
		}
		NamedElement capellaElement = (NamedElement) source;
		Element target = new Element();
		Set<Link> contentsSet = source.eContents().stream().filter(e -> (e instanceof CapellaElement)) // ModelElement
																										// seems to be
																										// the most
																										// generic one
				.map(ne -> new Link(createURI(linkBaseUrl + ((CapellaElement) ne).getId())))
				.collect(Collectors.toSet());
		target.setOwnedElement(contentsSet);

		target.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
		target.setIdentifier(capellaElement.getId());
		target.setSysmlIdentifier(capellaElement.getId());
		target.setName(capellaElement.getName());
		target.setDescription(capellaElement.getDescription());
		target.setTitle(capellaElement.getName());
		target.setShortTitle(capellaElement.getName());

		setOwnerIfPresent(source, target, linkBaseUrl);
		addAllCapellaTypes(target, capellaElement.getClass());

		return target;
	}

}
