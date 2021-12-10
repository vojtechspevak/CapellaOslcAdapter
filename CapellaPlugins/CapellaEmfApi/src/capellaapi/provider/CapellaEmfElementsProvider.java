package capellaapi.provider;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import capellaapi.aql.AqlSearcher;
import capellaapi.projectmanager.ProjectResourceManager;

/**
 * Implementation of the provider that exposes the methods for obtaining Capella EMF model data to the consumers
 */
public class CapellaEmfElementsProvider implements ICapellaEmfElementsProvider {

	private AqlSearcher _aqlSearcher = new AqlSearcher();
	
	@Override
	public List<String> getProjectNames() {
		return ProjectResourceManager.getProjectNames();
	}

	@Override
	public List<EObject> getAllProjectElements(String projectName) {
		EObject searchRoot = ProjectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getAllProjectElements(searchRoot);
	}
	
	@Override
	public EObject getProjectElement(String projectName, String elementId) {
		EObject searchRoot = ProjectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getElementById(searchRoot, elementId);
	}

	@Override
	public List<EObject> getProjectElementsByType(String projectName, List<Class<?>> classes) {
		EObject searchRoot = ProjectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByType(searchRoot, classes);
	}

	@Override
	public List<EObject> getProjectElementsFullText(String projectName, String searchText, List<Class<?>> classes) {
		EObject searchRoot = ProjectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByFullTextSearch(searchRoot, classes, searchText);
	}

	@Override
	public List<EObject> getProjectElementsByTypeAndExpression(String projectName, List<Class<?>> classes, String aqlExpression) {
		EObject searchRoot = ProjectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getProjectElementsByTypeAndExpression(searchRoot, classes, aqlExpression);
	}


	@Override
	public List<EObject> getProjectElementsByAqlQuery(String projectName, String aqlQuery) {
		EObject searchRoot = ProjectResourceManager.getSearchRoot(projectName);
		return _aqlSearcher.getElementsByQuery(searchRoot, aqlQuery);
	}

}
