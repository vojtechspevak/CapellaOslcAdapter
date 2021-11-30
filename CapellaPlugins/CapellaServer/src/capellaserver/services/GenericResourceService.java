package capellaserver.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;

public class GenericResourceService extends BaseService {

	public GenericResourceService(HttpServletRequest request) {
		super(request);
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
	
}
