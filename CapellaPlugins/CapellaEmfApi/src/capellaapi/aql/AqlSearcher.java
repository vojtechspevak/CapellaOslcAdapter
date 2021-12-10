package capellaapi.aql;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.acceleo.query.runtime.EvaluationResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryEvaluationEngine;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;

import com.google.common.collect.Maps;

import capellaapi.helpers.EmfHelper;

/**
 * AqlSearcher is responsible for executing the AQL queries
 */
public class AqlSearcher {
	
	private IQueryEnvironment _queryEnvironment;
	
	/**
	 * Constructor which registers necessary classes to the query environment
	 */
	public AqlSearcher() {
		_queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
		_queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
		_queryEnvironment.registerEPackage(CapellamodellerPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(CapellacorePackage.eINSTANCE);
		_queryEnvironment.registerEPackage(CapellacommonPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(CsPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(OaPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(CtxPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(LaPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(PaPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(DeploymentPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(EpbsPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(FaPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(SharedmodelPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(RequirementPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(InteractionPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(InformationPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(CommunicationPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(DatatypePackage.eINSTANCE);
		_queryEnvironment.registerEPackage(DatavaluePackage.eINSTANCE);
		_queryEnvironment.registerEPackage(ModellingcorePackage.eINSTANCE);
		_queryEnvironment.registerEPackage(BehaviorPackage.eINSTANCE);
		_queryEnvironment.registerEPackage(ActivityPackage.eINSTANCE);
	}
	
	/**
	 * method for returning all project elements
	 * @param searchRoot EObject to start the search from
	 * @return all project EMF resources
	 */
	public List<EObject> getAllProjectElements(EObject searchRoot) {
		String query = AqlQueryString.allContentsQuery();
		Object result = search(searchRoot, query);
		List<EObject> resultList = (List<EObject>) result;
		resultList.add(0,searchRoot);
		return resultList;
	}

	/**
	 * gets single element by id
	 * @param searchRoot EObject to start the search from
	 * @param elementId identifier of the searched element
	 * @return found resource or null
	 */
	public EObject getElementById(EObject searchRoot, String elementId) {
		String query = AqlQueryString.findByIdQuery(elementId);
		Object result = search(searchRoot, query);
		if(result == null &&  elementId.equals(EmfHelper.getRawEObjectProperty(searchRoot, "id"))) {
			return searchRoot;
		}
		return (EObject) result;
	}

	/**
	 * searches for project elements according to their type and additional AQL expression
	 * if the expression is null, only type is checked 
	 * @param searchRoot EObject to start the search from
	 * @param classes types to be filtered
	 * @param additionalExpression AQL boolean expression that elements have to satisfy
	 * @return found elements
	 */
	public List<EObject> getProjectElementsByTypeAndExpression(EObject searchRoot, Collection<Class<?>> classes, String additionalExpression) {
		List<String> typeStrings = classes.stream().map(c -> getAqlTypeNameFromClass(c)).collect(Collectors.toList());
		String query = AqlQueryString.findByTypeQuery(typeStrings);
		query = AqlQueryString.appendExpression(query,additionalExpression);
		Object result = search(searchRoot, query);
		Object isRootOfSearchedType = search(searchRoot, AqlQueryString.shouldIncludeRootInSearchResult(typeStrings, additionalExpression));
		List<EObject> resultList = (List<EObject>) result;
		if(Boolean.TRUE.equals(isRootOfSearchedType)) {
			resultList.add(0,searchRoot);
		};
		return resultList;
	}

	/**
	 * searches for project elements according to their type
	 * it is equivalent to getProjectElementsByType(searchRoot,classes,null)
	 * @param searchRoot EObject to start the search from
	 * @param classes types to be filtered
	 * @return found elements
	 * @see getProjectElementsByTypeAndExpression(EObject searchRoot, Collection<Class<?>> classes, String additionalExpression)
	 */
	public List<EObject> getProjectElementsByType(EObject searchRoot, Collection<Class<?>> classes) {
		return getProjectElementsByTypeAndExpression(searchRoot, classes, null);
	}

	/**
	 * searches for project elements based on their types and fulltext search term 
	 * @param searchRoot EObject to start the search from
	 * @param classes types to be filtered
	 * @param searchText types to be filtered
	 * @return
	 */
	public List<EObject> getProjectElementsByFullTextSearch(EObject searchRoot, List<Class<?>> classes, String searchText) {
		List<String> typeStrings = classes.stream().map(c -> getAqlTypeNameFromClass(c)).collect(Collectors.toList());
		String query = AqlQueryString.getFullTextSearchQuery(typeStrings, searchText);
		Object result = search(searchRoot, query);
		List<EObject> resultList = (List<EObject>) result;
		Object shouldIncludeRoot = search(searchRoot, AqlQueryString.shouldIncludeRootInFullTextSearchResult(typeStrings,searchText));
		if(Boolean.TRUE.equals(shouldIncludeRoot)) {
			resultList.add(0,searchRoot);
		};
		return resultList; 	
	}

	/**
	 * searches for project elements based on passes Aql query
	 * @param searchRoot the element to start the query from
	 * @param query the query to be executed
	 * @return result of the query execution
	 */
	public List<EObject> getElementsByQuery(EObject searchRoot, String query) {
		if(query == null || query.isEmpty()) {
			return null;
		}
		Object result = search(searchRoot, query);
		return (List<EObject>) result;
	}

	/**
	 * builds and executes the query passed as string
	 * @param searchRoot the element to start the query from
	 * @param query the query to be executed
	 * @return result of the query execution
	 */
	private Object search(EObject searchRoot, String query) {
			QueryBuilderEngine builder = new QueryBuilderEngine(_queryEnvironment);
			AstResult astResult = builder.build(query);
			QueryEvaluationEngine engine = new QueryEvaluationEngine(_queryEnvironment);
			Map<String, Object> variables = Maps.newHashMap();
			variables.put(AqlQueryString.VARIABLE_NAME, searchRoot);
			EvaluationResult evaluationResult = engine.eval(astResult, variables);
			return evaluationResult.getResult();
	}
	
	/**
	 * This method gets type name in a form that is usable in the AQL search query
	 * e.g. instead of "org.polarsys.capella.core.data.capellacore.NamedElement" AQL needs "capellacore::NamedElement"
	 * @param clazz class to get the type name of
	 * @return AQL friendly type name of the class
	 */
	private static String getAqlTypeNameFromClass(Class<?> clazz) {
		String[] packagePathSplits = clazz.getPackage().getName().split("\\.");
		String lastPackagePathPart = packagePathSplits[packagePathSplits.length - 1];
		return lastPackagePathPart + "::" + clazz.getSimpleName();
	}
	
}
