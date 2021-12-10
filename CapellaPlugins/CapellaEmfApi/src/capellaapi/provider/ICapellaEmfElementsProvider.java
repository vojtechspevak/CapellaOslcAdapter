package capellaapi.provider;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface ICapellaEmfElementsProvider {
	List<String> getProjectNames();
	EObject getProjectElement(String projectName, String elementId);
	List<EObject> getAllProjectElements(String projectName);
	List<EObject> getProjectElementsByType(String projectName, List<Class<?>> classes);
	List<EObject> getProjectElementsByTypeAndExpression(String projectName, List<Class<?>> classes, String aqlExpression);
	List<EObject> getProjectElementsFullText(String projectName,String searchText, List<Class<?>> clazz);
	List<EObject> getProjectElementsByAqlQuery(String projectName,String aqlQuery);
}
