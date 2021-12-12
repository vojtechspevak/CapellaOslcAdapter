package capellaserver.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Relationship;

import capellaserver.domain.Element;
import capellaserver.domain.Link;

/**
 * Sample implementation of a Mapping to be used in the Mapper class
 * @see AbstractMapping 
 */
public class Relationship2Relationship extends AbstractMapping {

	public Relationship2Relationship() {
		_source = Relationship.class;
		_target = capellaserver.domain.Relationship.class;
	}

	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
		Relationship capellaElement = (Relationship) source;
		capellaserver.domain.Relationship target = new capellaserver.domain.Relationship();
			
			for(NamedElement feature : capellaElement.getFeatures()) {
				target.addOwnedElement(new Link(createURI(linkBaseUrl + feature.getId())));
			}
			for(NamedElement ownedPropVal : capellaElement.getOwnedPropertyValues()) {
				target.addOwnedElement(new Link(createURI(linkBaseUrl + ownedPropVal.getId())));
			}

			target.addRelatedElement(new Link(createURI(linkBaseUrl + capellaElement.getRealizedFlow())));
			target.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
			target.setIdentifier(capellaElement.getId());
			target.setSysmlIdentifier(capellaElement.getId());
			target.setDescription(capellaElement.getDescription());
			target.setTitle(capellaElement.getFullLabel());
			target.setShortTitle(capellaElement.getLabel());
			setOwnerIfPresent(source, target, linkBaseUrl);
			addAllSysmlTypes(target,getTargetClass());
			return target;
	}


	
}
