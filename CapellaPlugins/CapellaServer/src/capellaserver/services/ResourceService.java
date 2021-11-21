package capellaserver.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.NamedElement;

import capellaapi.provider.CapellaEmfElementsProvider;
import capellaapi.provider.ICapellaEmfElementsProvider;

public class ResourceService {

	private ICapellaEmfElementsProvider _capellaElementsProvider = new CapellaEmfElementsProvider();
	private HttpServletRequest _request;

	public ResourceService(HttpServletRequest request) {
		_request = request;
	}
	
	public List<EObject> getElements(String projectName, String typeString) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsByType(projectName, typeString);
		return handlePaging(elements);
	}

	public EObject getElementById(String projectName, String elementId) {
		return _capellaElementsProvider.getProjectElement(projectName, elementId);
	}

	public List<EObject> getElementsByFullTextSearch(String projectName, String searchText, String typeString) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsFullText(projectName, searchText, typeString);
		return handlePaging(elements);
	}

	private List<EObject> handlePaging(List<EObject> elements){
		try {
			int page = Integer.parseInt(_request.getParameter("page"));
			int limit = Integer.parseInt(_request.getParameter("limit"));
			int listSize = elements.size();
			return elements.subList(page*limit < listSize ? page*limit : listSize  
					, (page+1)*limit <= listSize ? (page+1)*limit : listSize);
		} catch(NumberFormatException e) {
			return elements;
		}
	}

	
}
