package capellaserver.mapping;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

import capellaserver.domain.Element;
import capellaserver.domain.Link;

/**
 * Provides the reusable functionality for implemented mappings
 * New mapping should be derived from this class, specify its _target and _source
 * and provide the map method. Then it can be registered in the Mapper class.
 */
public abstract class AbstractMapping implements IMapping {
	protected Class<?> _source;
	protected Class<?> _target;

	@Override
	public Class<?> getSourceClass() {
		return _source;
	}

	@Override
	public Class<?> getTargetClass() {
		return _target;
	}

	/**
	 * helper method for the URI creation
	 * @param uriString string to create the URI from
	 * @return created URI
	 */
	protected static URI createURI(String uriString) {
		try {
			return new URI(uriString);
		} catch (URISyntaxException e1) {
			return null;
		}
	}

	/**
	 * helper method for adding the types of the original Capella element 
	 * @param target target of the map
	 * @param capellaElementClass class of the Capella source
	 */
	protected static void addAllCapellaTypes(Element target, Class<?> capellaElementClass) {
		target.addType(capellaElementClass.getSimpleName());
		capellaElementClass = capellaElementClass.getSuperclass();
		while (capellaElementClass != null) {
			target.addType(capellaElementClass.getSimpleName());
			capellaElementClass = capellaElementClass.getSuperclass();
		}
	}

	/**
	 * helper method for adding the soruces eContainer as owner to the target 
	 * @param target target of the map
	 * @param linkBaseUrl URL passed from the server
	 */
	protected static void setOwnerIfPresent(EObject source, Element target, String linkBaseUrl) {
		if (source.eContainer() != null && source.eContainer() instanceof CapellaElement) {
			Link ownerLink = new Link(createURI(linkBaseUrl + ((CapellaElement) source.eContainer()).getId()));
			target.setOwner(ownerLink);
		}
	}

	protected void checkIfSourceHasCorrectType(EObject source) {
		if(!(_source.isAssignableFrom(source.getClass()))) {
			String errorMessage = "Cannot map argument of type " + source.getClass().getName() + " to type " + _target.getSimpleName();
			throw new IllegalArgumentException(errorMessage);
		}
	}

}
