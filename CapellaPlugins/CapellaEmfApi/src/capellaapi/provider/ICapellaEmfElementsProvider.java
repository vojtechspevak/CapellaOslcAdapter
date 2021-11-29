package capellaapi.provider;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface ICapellaEmfElementsProvider {
	List<String> getProjectNames();
	List<EObject> getAllProjectElements(String projectName);
	EObject getProjectElement(String projectName, String elementId);
	List<EObject> getProjectElementsByType(String projectName, List<Class<?>> classes);
	List<EObject> getProjectElementsByTypeAndExpression(String projectName, List<Class<?>> classes, String aqlExpression);
	List<EObject> getProjectElementsByType(String projectName, Class<?> clazz);
	List<EObject> getProjectElementsByType(String projectName, String typeString);
	List<EObject> getProjectElementsFullText(String projectName,String searchText, Class<?> clazz);
	List<EObject> getProjectElementsFullText(String projectName,String searchText, List<Class<?>> clazz);
	List<EObject> getProjectElementsFullText(String projectName,String searchText, String typeString);
}
