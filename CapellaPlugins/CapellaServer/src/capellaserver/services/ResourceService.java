package capellaserver.services;

import org.eclipse.emf.ecore.EObject;

import capellaapi.provider.CapellaEmfElementsProvider;
import capellaapi.provider.ICapellaEmfElementsProvider;
import capellaserver.domain.Element;
import capellaserver.mapping.Mapper;

/**
 * Service responsible for finding an element by id 
 */
public class ResourceService {

	private ICapellaEmfElementsProvider _capellaElementsProvider = new CapellaEmfElementsProvider();
	private final String _linkBaseUrl;

	public ResourceService(String linkBaseUrl) {
		_linkBaseUrl = linkBaseUrl;
	}
	
	/**
	 * finds a single element by id
	 * @param projectName project to search in for the element
	 * @param elementId identifier of the element
	 * @return found element or null if element is not found
	 */
	public Element getResourceById(String projectName, String elementId) {
		EObject capellaElement = _capellaElementsProvider.getProjectElement(projectName, elementId);
		return Mapper.map(capellaElement, _linkBaseUrl);
	}

}