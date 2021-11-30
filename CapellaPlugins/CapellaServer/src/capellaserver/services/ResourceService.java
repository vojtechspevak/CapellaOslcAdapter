package capellaserver.services;

import org.eclipse.emf.ecore.EObject;

import capellaapi.provider.CapellaEmfElementsProvider;
import capellaapi.provider.ICapellaEmfElementsProvider;
import capellaserver.domain.Element;
import capellaserver.mapping.Mapper;

public class ResourceService {

	private ICapellaEmfElementsProvider _capellaElementsProvider = new CapellaEmfElementsProvider();
	private final String _linkBaseUrl;

	public ResourceService(String linkBaseUrl) {
		_linkBaseUrl = linkBaseUrl;
	}
	
	public Element getResourceById(String projectName, String elementId) {
		EObject capellaElement = _capellaElementsProvider.getProjectElement(projectName, elementId);
		return Mapper.map(capellaElement, _linkBaseUrl);
	}

}