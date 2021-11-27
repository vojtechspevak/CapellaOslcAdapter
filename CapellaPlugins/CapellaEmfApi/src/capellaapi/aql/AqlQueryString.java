package capellaapi.aql;

import org.polarsys.capella.core.data.capellacore.NamedElement;


public class AqlQueryString {
	public static final String VARIABLE_NAME = "self";

	public static String AllContentsQuery() {
		return String.format("%s.eAllContents()", VARIABLE_NAME);
	}
	
	public static String FindByIdQuery(String id) {
		return String.format("%s.eAllContents()->select(p | p.id.contains('%s'))->first()", 
				VARIABLE_NAME,id);
	}
	
	// TODO this or something else should probably take just initial query as string
	// and parse and compose AQL query from &&, || operators, handle data types and so on
//	public static String FindByGenericQuery(List<Pair<String,Object>> propNameValueList) {
//		return String.format("%s.eAllContents()", 
//				VARIABLE_NAME);
//	}
	
	//TODO implement and use in the above method
	private static String parseQuery(String queryString) {
		return null;
	}

	// takes arguments like "oa::OperationalCapability" or "information::Property"
	public static String FindByTypeQuery(String typeString) {
		return String.format("%s.eAllContents(%s)", 
				VARIABLE_NAME,typeString);
	}
	
	public static String getFullTextSearchQuery(String searchText, String typeString) {
 		return String.format("%s.eAllContents(%s)->select(e | e.name.contains('%s') or e.description.contains('%s') or e.getId.contains('%s'))", 
				VARIABLE_NAME,typeString,searchText,searchText,searchText);
	}
	
	public static String IsSearchRootOfType(String typeString) {
		return String.format("%s.oclIsKindOf(%s)", 
				VARIABLE_NAME,typeString);
	}

	public static String shouldIncludeRootInSearchResult(String searchText, String typeString) {
		return String.format("(%s.oclIsKindOf(%s) %s.name.contains('%s') or %s.description.contains('%s') or %s.getId.contains('%s'))", 
				VARIABLE_NAME,typeString,VARIABLE_NAME,searchText,VARIABLE_NAME,searchText,VARIABLE_NAME,searchText);
	}
	
}
