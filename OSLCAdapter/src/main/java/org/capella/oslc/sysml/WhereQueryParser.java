package org.capella.oslc.sysml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.jena.atlas.logging.Log;
import org.eclipse.lyo.core.query.ComparisonTerm;
import org.eclipse.lyo.core.query.InTerm;
import org.eclipse.lyo.core.query.ParseException;
import org.eclipse.lyo.core.query.QueryUtils;
import org.eclipse.lyo.core.query.SimpleTerm;
import org.eclipse.lyo.core.query.Value;
import org.eclipse.lyo.core.query.WhereClause;

public class WhereQueryParser {

    private static Map<String,String> map = new HashMap<String,String>();
//    static {
//        map.put(OslcConstants.DCTERMS_NAMESPACE_PREFIX, OslcConstants.DCTERMS_NAMESPACE);
//    	map.put(OslcConstants.DCTERMS_NAMESPACE_PREFIX, OslcConstants.DCTERMS_NAMESPACE);
//    	map.put(OslcConstants.OSLC_CORE_NAMESPACE_PREFIX, OslcConstants.OSLC_CORE_NAMESPACE);
//    	map.put(OslcConstants.OSLC_DATA_NAMESPACE_PREFIX, OslcConstants.OSLC_DATA_NAMESPACE);
//    	map.put(OslcConstants.RDF_NAMESPACE_PREFIX, OslcConstants.RDF_NAMESPACE);
//    	map.put(OslcConstants.RDFS_NAMESPACE_PREFIX, OslcConstants.RDFS_NAMESPACE);
//    	map.put(Oslc_amDomainConstants.ARCHITECTURE_MANAGEMENT_NAMSPACE_PREFIX,
//    			Oslc_amDomainConstants.ARCHITECTURE_MANAGEMENT_NAMSPACE);
//    	map.put(DctermsDomainConstants.DUBLIN_CORE_NAMSPACE_PREFIX, DctermsDomainConstants.DUBLIN_CORE_NAMSPACE);
//    	map.put(FoafDomainConstants.FOAF_NAMSPACE_PREFIX, FoafDomainConstants.FOAF_NAMSPACE);
//    	map.put(OslcDomainConstants.OSLC_NAMSPACE_PREFIX, OslcDomainConstants.OSLC_NAMSPACE);
//    	map.put(SysmlDomainConstants.SYSML_NAMSPACE_PREFIX, SysmlDomainConstants.SYSML_NAMSPACE);
//    }
	
	public static String parseQueryToAql(String where, String prefix) {
		try {
			Map<String,String> prefixes = QueryUtils.parsePrefixes(prefix);
			WhereClause wc = QueryUtils.parseWhere(where, prefixes);
			List<SimpleTerm> terms = wc.children();
			String aqlQuery = "e | ".concat(transformTermToAql(terms.get(0)));
			for(int i=1; i< terms.size(); i++) {
				aqlQuery
					.concat(" and ")
					.concat(transformTermToAql(terms.get(i)));
			}
			return aqlQuery;
		} catch (ParseException e) {
			Log.error(WhereQueryParser.class, "Could not parse query '" + where + "'.");
			throw new WebApplicationException(e, Status.BAD_REQUEST);
		}
	}
	
	private static String transformTermToAql(SimpleTerm term) {
		switch(term.type()) {
		case COMPARISON:
		    ComparisonTerm comparisonTerm = (ComparisonTerm)term;
		    return transformComparisonTermToAql(comparisonTerm);
		case IN_TERM:
		    InTerm inTerm = (InTerm) term;
		    return transformInTermToAql(inTerm);
		default:
	        throw new WebApplicationException(new UnsupportedOperationException("Unsupported oslc.where query."),Status.BAD_REQUEST);                
		}
	}
	
	private static String transformInTermToAql(InTerm inTerm) {
		String aqlTerm = "( ";
		String transformedTermBase = "e."
				.concat(transformPropertyNameToAql(inTerm.property().local))
				.concat(" = ");
		
		List<Value> values = inTerm.values();
		aqlTerm = aqlTerm
			.concat(transformedTermBase)
			.concat(transformPropertyValueToAql(values.get(0)));
		for(int i=1; i< values.size(); i++) {
			aqlTerm = aqlTerm
				.concat(" or ")
				.concat(transformedTermBase)
				.concat(transformPropertyValueToAql(values.get(i)));
		}
		return aqlTerm.concat(" )");
	}

	private static String transformComparisonTermToAql(ComparisonTerm comparisonTerm) {
	    String operator;
		switch (comparisonTerm.operator()) {
	        case EQUALS:
	            operator = " = ";
	            break;
	        case NOT_EQUALS:
	            operator = " <> ";
	            break;
	        default:
	            throw new WebApplicationException(new UnsupportedOperationException("Unsupported oslc.where query."),Status.BAD_REQUEST);                
	    }
		return "e."
				.concat(transformPropertyNameToAql(comparisonTerm.property().local))
				.concat(operator)
				.concat(transformPropertyValueToAql(comparisonTerm.operand()));
	}
	
	private static String transformPropertyValueToAql(Value value) {
		switch(value.type()) {
	    	case STRING:
	    		return "'"+value.toString().substring(1,value.toString().length() -1)+"'";
	    	case URI_REF:
	    		return getIdFromUriRef(value.toString());
	        default:
	            throw new WebApplicationException(new UnsupportedOperationException("Unsupported oslc.where query."),Status.BAD_REQUEST);                
		}
	}
	
	private static String getIdFromUriRef(String uriRef) {
		int index = uriRef.toString().indexOf("elements/");
		if(index == -1) {
			Log.warn(WhereQueryParser.class, "Unsupported oslc.where uri ref value: '" + uriRef + "'.");
			return ""; 
		}
		return "'" + uriRef.substring(index + 9, uriRef.length() -1) + "'";
	}
	
	private static String transformPropertyNameToAql(String propertyName) {
		switch(propertyName){
        case "name":
        	return "name";
        case "owner":
        	return "eContainer().id";
        case "identifier":
        	return "id";
        default:
            throw new WebApplicationException(new UnsupportedOperationException("Unsupported oslc.where query."),Status.BAD_REQUEST);                
		}
	}
	
}
