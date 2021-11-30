package capellaserver.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;
import capellaserver.domain.Element;
import capellaserver.services.mapping.Mapper;

public class ResourceCollectionService extends BaseService {

	private final List<Class<?>> _sourceElementClasses;
	private final Class<?> _targetElementClass;
	private final String _linkBaseUrl;
	
	public ResourceCollectionService(HttpServletRequest request, Class<?> targetElementClass, String linkBaseUrl) {
		super(request);
		_targetElementClass = targetElementClass;
		_sourceElementClasses = Mapper.getSourceClassesForTargetClass(targetElementClass);
		_linkBaseUrl = linkBaseUrl;
	}

	public List<Element> getElements(String projectName, String aqlExpression) {
		List<EObject> elements = aqlExpression == null 
				? _capellaElementsProvider.getProjectElementsByType(projectName, _sourceElementClasses)
				: _capellaElementsProvider.getProjectElementsByTypeAndExpression(projectName, _sourceElementClasses, aqlExpression);
		elements = handlePaging(elements);
		return Mapper.map(elements, _targetElementClass, _linkBaseUrl);
	}

	public List<Element> getElementsByFullTextSearch(String projectName, String searchText) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsFullText(projectName, searchText, _sourceElementClasses);
		elements = handlePaging(elements);
		return Mapper.map(elements, _targetElementClass, _linkBaseUrl);
	}

}
