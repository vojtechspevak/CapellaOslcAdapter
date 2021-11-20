package capellaserver.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.NamedElement;

public class ElementService extends BaseService {

	private static final Class ELEMENT_CLASS = NamedElement.class;

	public ElementService(HttpServletRequest request) {
		super(request);
	}

	public List<EObject> getElements(String projectName) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsByType(projectName, ELEMENT_CLASS);
		return handlePaging(elements);
	}

	public EObject getElementById(String projectName, String elementId) {
		return _capellaElementsProvider.getProjectElement(projectName, elementId);
	}

	public List<EObject> getElementsByFullTextSearch(String projectName, String searchText) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsFullText(projectName, searchText);
		return handlePaging(elements);
	}

}
