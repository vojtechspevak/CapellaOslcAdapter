package capellaserver.mapping;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

import capellaserver.domain.Element;
import capellaserver.domain.Link;

/**
 * Sample implementation of a Mapping to be used in the Mapper class
 * @see AbstractMapping 
 */
public class ModelElement2Element extends AbstractMapping {

	public ModelElement2Element() {
		_source = ModelElement.class;
		_target = Element.class;
	}

	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
		ModelElement capellaElement = (ModelElement) source;
		Element target = new Element();
		Set<Link> contentsSet = source.eContents().stream().filter(e -> (e instanceof CapellaElement)) 
				.map(ne -> new Link(createURI(linkBaseUrl + ((CapellaElement) ne).getId())))
				.collect(Collectors.toSet());
		target.setOwnedElement(contentsSet);

		target.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
		target.setIdentifier(capellaElement.getId());
		target.setSysmlIdentifier(capellaElement.getId());

		setOwnerIfPresent(source, target, linkBaseUrl);
		addAllSysmlTypes(target,getTargetClass());

		return target;
	}

}
