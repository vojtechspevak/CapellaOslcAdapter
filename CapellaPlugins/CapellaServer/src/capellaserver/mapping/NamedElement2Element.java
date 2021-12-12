package capellaserver.mapping;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;

import capellaserver.domain.Element;
import capellaserver.domain.Link;

/**
 * Sample implementation of a Mapping to be used in the Mapper class
 * @see AbstractMapping 
 */
public class NamedElement2Element extends AbstractMapping {

	public NamedElement2Element() {
		_source = NamedElement.class;
		_target = Element.class;
	}

	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
		NamedElement capellaElement = (NamedElement) source;
		Element target = new Element();
		Set<Link> contentsSet = source.eContents().stream().filter(e -> (e instanceof CapellaElement))
				.map(ne -> new Link(createURI(linkBaseUrl + ((CapellaElement) ne).getId())))
				.collect(Collectors.toSet());
		target.setOwnedElement(contentsSet);

		target.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
		target.setIdentifier(capellaElement.getId());
		target.setSysmlIdentifier(capellaElement.getId());
		target.setName(capellaElement.getName());
		target.setDescription(capellaElement.getDescription());
		target.setTitle(capellaElement.getFullLabel());
		target.setShortTitle(capellaElement.getLabel());

		setOwnerIfPresent(source, target, linkBaseUrl);
		addAllSysmlTypes(target,getTargetClass());

		return target;
	}

}
