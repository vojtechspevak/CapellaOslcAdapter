package capellaapi.aql;

import java.util.List;
import java.util.Map;

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
import org.polarsys.capella.core.data.capellacore.NamedElement;
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


public class AqlSearcher {
	
	private IQueryEnvironment _queryEnvironment;
	
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
	
	public List<EObject> getAllProjectElements(EObject searchRoot) {
		String query = AqlQueryString.AllContentsQuery();
		Object result = search(searchRoot, query);
		List<EObject> resultList = (List<EObject>) result;
		resultList.add(searchRoot);
		return resultList;
	}

	public EObject getElementById(EObject searchRoot, String elementId) {
		String query = AqlQueryString.FindByIdQuery(elementId);
		Object result = search(searchRoot, query);
		if(result == null &&  elementId.equals(EmfHelper.getRawEObjectProperty(searchRoot, "id"))) {
			return searchRoot;
		}
		return (EObject) result;
	}

	public List<EObject> getProjectElementsByType(EObject searchRoot, Class clazz) {
		String typeString = getAqlTypeNameFromClass(clazz);
		return getProjectElementsByType(searchRoot, typeString);
	}

	public List<EObject> getProjectElementsByType(EObject searchRoot, String typeString) {
		String query = AqlQueryString.FindByTypeQuery(typeString);
		Object result = search(searchRoot, query);
		Object isRootOfSearchedType =search(searchRoot, AqlQueryString.IsSearchRootOfType(typeString));
		List<EObject> resultList = (List<EObject>) result;
		if(Boolean.TRUE.equals(isRootOfSearchedType)) {
			resultList.add(0,searchRoot);
		};
		return resultList;
	}
	
	public List<EObject> getProjectElementsByFullTextSearch(EObject searchRoot, String searchText, Class clazz) {
		String typeString = getAqlTypeNameFromClass(clazz);
		return getProjectElementsByFullTextSearch(searchRoot,searchText,typeString); 
	}
	
	public List<EObject> getProjectElementsByFullTextSearch(EObject searchRoot, String searchText, String typeString) {
		String query = AqlQueryString.getFullTextSearchQuery(searchText,typeString);
		Object result = search(searchRoot, query);
		List<EObject> resultList = (List<EObject>) result;
		Object shouldIncludeRoot = search(searchRoot, AqlQueryString.shouldIncludeRootInSearchResult(searchText,typeString));
		if(Boolean.TRUE.equals(shouldIncludeRoot)) {
			resultList.add(0,searchRoot);
		};
		return resultList; 
	}
	
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
	private static String getAqlTypeNameFromClass(Class clazz) {
		String[] packagePathSplits = clazz.getPackageName().split("\\.");
		String lastPackagePathPart = packagePathSplits[packagePathSplits.length - 1];
		return lastPackagePathPart + "::" + clazz.getSimpleName();
	}
	
}
