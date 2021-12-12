package capellaserver.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * This class contains functionality for retrieving the data from the CapellaEmfApi
 * and is used by the generic servlets without mapping the elements to the SysML domain
 */
public class GenericResourceService extends BaseService {

	public GenericResourceService(HttpServletRequest request) {
		super(request);
	}
	
	/**
	 * gets project elements using the query provided by the client
	 * @param projectName name of the project to search in
	 * @param aqlQuery AQL search query
	 * @return found elements or null if the query does not return a collection
	 */
	public List<EObject> getElements(String projectName, String aqlQuery) {
		List<EObject> elements;
		if (aqlQuery == null || aqlQuery.trim().isEmpty()) {
			elements = _capellaElementsProvider.getAllProjectElements(projectName);
		} else {
			elements = _capellaElementsProvider.getProjectElementsByAqlQuery(projectName, aqlQuery);
		}
		return handlePaging(elements);
	}

	public EObject getElementById(String projectName, String elementId) {
		return _capellaElementsProvider.getProjectElement(projectName, elementId);
	}

	/**
	 * searches for elements by a simple full text search using the provided search text 
	 * @param projectName name of the project to search in
	 * @param searchText text to search for
	 * @return found elements
	 */
	public List<EObject> getElementsByFullTextSearch(String projectName, String searchText) {
		List<Class<?>> typeList = new ArrayList<Class<?>>();
		typeList.add(NamedElement.class);
		List<EObject> elements = _capellaElementsProvider.getProjectElementsFullText(projectName, searchText, typeList);
		return handlePaging(elements);
	}
	
}
