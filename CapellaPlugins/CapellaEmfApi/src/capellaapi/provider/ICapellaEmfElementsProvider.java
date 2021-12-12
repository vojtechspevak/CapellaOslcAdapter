package capellaapi.provider;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface to be used by the consumers of CapellaEmfApi
 */
public interface ICapellaEmfElementsProvider {
	
	/**
	 * lists name of the available Capella projects
	 * @return list of available project names
	 */
	List<String> getProjectNames();
	
	/**
	 * gets project element by id
	 * @param projectName name of the project containing the element
	 * @param elementId identifier of the element
	 * @return found element or null
	 */
	EObject getProjectElement(String projectName, String elementId);
	
	/**
	 * gets all project EMF resources
	 * @param projectName name of the project to get resources from
	 * @return all project resources
	 */
	List<EObject> getAllProjectElements(String projectName);
	
	/**
	 * Gets project elements that are of the specified types
	 * returns also sub-types
	 * @param projectName name of the project to search in
	 * @param classes types of the elements to get
	 * @return found elements
	 */
	List<EObject> getProjectElementsByType(String projectName, List<Class<?>> classes);
	
	/**
	 * Gets project elements that are of the specified types
	 * and satisfy the condition given by the passes AQL expression
	 * @param projectName name of the project to search in
	 * @param classes types of the elements to get
	 * @param aqlExpression logical AQL expression
	 * @return found elements
	 */
	List<EObject> getProjectElementsByTypeAndExpression(String projectName, List<Class<?>> classes, String aqlExpression);
	
	/**
	 * Gets project elements by full text search
	 * @param projectName name of the project to search in
	 * @param searchText string to be searched for
	 * @param classes types of the elements to get
	 * @return found elements
	 */
	List<EObject> getProjectElementsFullText(String projectName,String searchText, List<Class<?>> classes);
	
	/**
	 * Gets project elements by AQL query provided by the consumer
	 * @param projectName name of the project to search in
	 * @param aqlQuery query searching for the resources
	 * @return list of resources or null if the query is invalid or does not return a collection of resources
	 */
	List<EObject> getProjectElementsByAqlQuery(String projectName, String aqlQuery);
}
