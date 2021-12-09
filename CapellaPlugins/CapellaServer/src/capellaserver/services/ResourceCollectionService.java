package capellaserver.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;
import capellaserver.domain.Element;
import capellaserver.mapping.Mapper;

/**
 * Service responsible for fetching the elements from CapellaEmfElementsProvider 
 * according to the type passed in a constructor
 * the obtained elements are mapped to the most suitable target from the SysML domain
 */
public class ResourceCollectionService extends BaseService {

	private final List<Class<?>> _sourceElementClasses;
	private final String _linkBaseUrl;
	
	public ResourceCollectionService(HttpServletRequest request, Class<?> targetElementClass, String linkBaseUrl) {
		super(request);
		_sourceElementClasses = Mapper.getSourceClassesForTargetClass(targetElementClass);
		_linkBaseUrl = linkBaseUrl;
	}

	/**
	 * obtains elements according according to the type and aqlExpression (if provided)
	 * and maps them to the SysML domain
	 * @param projectName name of the project to search in
	 * @param aqlExpression string aql logical expression to be used in a query
	 * @return mapped and paged list of found elements
	 */
	public List<Element> getElements(String projectName, String aqlExpression) {
		List<EObject> elements = aqlExpression == null 
				? _capellaElementsProvider.getProjectElementsByType(projectName, _sourceElementClasses)
				: _capellaElementsProvider.getProjectElementsByTypeAndExpression(projectName, _sourceElementClasses, aqlExpression);
		elements = handlePaging(elements);
		return Mapper.map(elements, _linkBaseUrl);
	}

	/**
	 * obtains elements according according to the type and aqlExpression (if provided)
	 * and maps them to the SysML domain
	 * @param projectName name of the project to search in
	 * @param searchText string to be used for the fulltext search
	 * @return mapped and paged list of found elements
	 */
	public List<Element> getElementsByFullTextSearch(String projectName, String searchText) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsFullText(projectName, searchText, _sourceElementClasses);
		elements = handlePaging(elements);
		return Mapper.map(elements, _linkBaseUrl);
	}

}
