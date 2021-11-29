package capellaapi.provider;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import capellaapi.aql.AqlSearcher;
import capellaapi.projectmanager.ProjectResourceManager;


public class CapellaEmfElementsProvider implements ICapellaEmfElementsProvider {

	private AqlSearcher _aqlSearcher = new AqlSearcher();
	private ProjectResourceManager _projectResourceManager = new ProjectResourceManager();
	
	@Override
	public List<String> getProjectNames() {
		return _projectResourceManager.getProjectNames();
	}

	@Override
	public List<EObject> getAllProjectElements(String projectName) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getAllProjectElements(searchRoot);
	}

	@Override
	public List<EObject> getProjectElementsByType(String projectName, Class<?> clazz) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByType(searchRoot, clazz);
	}

	@Override
	public List<EObject> getProjectElementsByType(String projectName, String typeString) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByType(searchRoot, typeString);
	}
	
	@Override
	public EObject getProjectElement(String projectName, String elementId) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getElementById(searchRoot, elementId);
	}

	@Override
	public List<EObject> getProjectElementsFullText(String projectName, String searchText, Class<?> clazz) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByFullTextSearch(searchRoot, searchText, clazz);
	}

	@Override
	public List<EObject> getProjectElementsFullText(String projectName, String searchText, String typeString) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByFullTextSearch(searchRoot, searchText, typeString);
	}

	@Override
	public List<EObject> getProjectElementsByType(String projectName, List<Class<?>> classes) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByType(searchRoot, classes);
	}

	@Override
	public List<EObject> getProjectElementsFullText(String projectName, String searchText, List<Class<?>> classes) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByFullTextSearch(searchRoot, searchText, classes);
	}

	@Override
	public List<EObject> getProjectElementsByTypeAndExpression(String projectName, List<Class<?>> classes, String aqlExpression) {
		EObject searchRoot = _projectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByType(searchRoot, classes, aqlExpression);
	}

}
