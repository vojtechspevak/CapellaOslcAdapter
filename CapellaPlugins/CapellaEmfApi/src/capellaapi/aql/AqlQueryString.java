package capellaapi.aql;

import java.util.List;

/**
 * Class providing utility methods for constructing AQL query strings
 */
public class AqlQueryString {
	public static final String VARIABLE_NAME = "self";

	/**
	 * constructs a query for getting all resources contained in the search root
	 * @return constructed query
	 */
	public static String allContentsQuery() {
		return String.format("%s.eAllContents()", VARIABLE_NAME);
	}

	/**
	 * constructs a query for finding an element by its identifier
	 * @param id identifier of the searched element
	 * @return constructed query
	 */
	public static String findByIdQuery(String id) {
		return String.format("%s.eAllContents()->select(p | p.id.contains('%s'))->first()", 
				VARIABLE_NAME,id);
	}
	
	/**
	 * constructs a query that searches for elements of some type 
	 * @param typeStrings strings corresponding to the types of searched resources, like "oa::OperationalCapability"
	 * @return constructed query
	 */
	public static String findByTypeQuery(List<String> typeStrings) {
		if(typeStrings.size() == 0) {
			return String.format("%s.eAllContents()", 
					VARIABLE_NAME);
		}
		
		String query = String.format("%s.eAllContents()->select(e | e.oclIsKindOf(%s)", 
				VARIABLE_NAME,typeStrings.get(0));
		
		//skip the first and concat with "or" operators
		for(int i = 1; i< typeStrings.size(); i++) {
			query += " or e.oclIsKindOf(" + typeStrings.get(i) + ")";
		}
		query += ")";
		return query;
	}

	/**
	 * constructs a query for fulltext search on given types
	 * @param typeStrings strings corresponding to the types of searched resources, like "oa::OperationalCapability"
	 * @return constructed query
	 */
	public static String getFullTextSearchQuery(List<String> typeStrings, String searchText) {
		String queryByType = findByTypeQuery(typeStrings);
 		return String.format("%s->select(e | e.id.contains('%s') or e.name.contains('%s') or e.description.contains('%s'))", 
				queryByType,searchText,searchText,searchText);
	}

	/**
	 * constructs a query for checking whether the search root has any of the given types
	 * @param typeStrings strings corresponding to the types of searched resources, like "oa::OperationalCapability"
	 * @return constructed query
	 */
	public static String isSearchRootOfType(List<String> typeStrings) {
		if(typeStrings.size() == 0) {
			return "true";
		}
		
		String query = String.format("(%s.oclIsKindOf(%s)", 
				VARIABLE_NAME,typeStrings.get(0));
		
		//skip the first and concat with "or" operators
		for(int i = 1; i< typeStrings.size(); i++) {
			query = query
					.concat(" or ")
					.concat(VARIABLE_NAME)
					.concat(".oclIsKindOf(")
					.concat(typeStrings.get(i))
					.concat(")");
		}
		return query.concat(")");
	}

	/**
	 * constructs a query for checking if the search root meets the search criteria 
	 * given by its type and additional AQl logical expression
	 * @param additionalExpression AQL expression
	 * @param typeStrings strings corresponding to the types of searched resources
	 * @return constructed AQL query
	 */
	public static String shouldIncludeRootInSearchResult(List<String> typeStrings, String additionalExpression) {
		String queryByType = isSearchRootOfType(typeStrings);

		if(additionalExpression == null || additionalExpression.isEmpty()) {
			return queryByType;
		}
		
		return String.format("(%s and (OrderedSet{%s}->exist(%s))", 
				queryByType,VARIABLE_NAME,additionalExpression);
	}
	
	/**
	 * constructs a query for checking if the search root meets the search criteria
	 * @param searchText fullltext search input
	 * @param typeStrings strings corresponding to the types of searched resources
	 * @return constructed AQL query
	 */
	public static String shouldIncludeRootInFullTextSearchResult(List<String> typeStrings, String searchText) {
		String queryByType = isSearchRootOfType(typeStrings);
		return String.format("(%s and (%s.name.contains('%s') or %s.description.contains('%s') or %s.getId.contains('%s')))", 
				queryByType,VARIABLE_NAME,searchText,VARIABLE_NAME,searchText,VARIABLE_NAME,searchText);
	}

	/**
	 * Appends the AQL expression wrapped in parentheses to the query
	 * @param query existing query
	 * @param additionalExpression expression to append
	 * @return query with appended expression
	 */
	public static String appendExpression(String query, String additionalExpression) {
		if(additionalExpression == null || additionalExpression.isEmpty()) {
			return query;
		}
		return query
				.concat("->select(")
				.concat(additionalExpression)
				.concat(")");
	}
	
}
