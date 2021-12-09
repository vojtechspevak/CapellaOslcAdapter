package capellaserver.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import capellaserver.domain.Element;
import capellaserver.domain.Link;

public class Generalization2Generalization extends AbstractMapping {

	public Generalization2Generalization() {
		_source = Generalization.class;
		_target = capellaserver.domain.Generalization.class;
	}

	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
		Generalization capellaElement = (Generalization) source;
		capellaserver.domain.Generalization target = new capellaserver.domain.Generalization();
			
			for(NamedElement feature : capellaElement.getFeatures()) {
				target.addOwnedElement(new Link(createURI(linkBaseUrl + feature.getId())));
			}
			for(NamedElement ownedPropVal : capellaElement.getOwnedPropertyValues()) {
				target.addOwnedElement(new Link(createURI(linkBaseUrl + ownedPropVal.getId())));
			}

			target.setGeneral(new Link(createURI(linkBaseUrl + capellaElement.getSuper().getId())));
			target.setSpecific(new Link(createURI(linkBaseUrl + capellaElement.getSub().getId())));
			target.addRelatedElement(new Link(createURI(linkBaseUrl + capellaElement.getRealizedFlow())));
			target.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
			target.setIdentifier(capellaElement.getId());
			target.setSysmlIdentifier(capellaElement.getId());
			target.setDescription(capellaElement.getDescription());
			target.setTitle(capellaElement.getFullLabel());
			target.setShortTitle(capellaElement.getLabel());
			setOwnerIfPresent(source, target, linkBaseUrl);
			addAllCapellaTypes(target,capellaElement.getClass());
			return target;
	}


	
}
