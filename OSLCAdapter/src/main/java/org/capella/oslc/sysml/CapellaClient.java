package org.capella.oslc.sysml;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.apache.jena.atlas.logging.Log;
import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;
//import org.oasis.oslcop.sysml.SysmlClass;
import org.oasis.oslcop.sysml.Element;
import org.oasis.oslcop.sysml.Generalization;
import org.oasis.oslcop.sysml.Relationship;
import org.oasis.oslcop.sysml.SysmlClass;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class CapellaClient {

	private static final String API_URL_BASE = "http://localhost:3333";
	private static final String API_URL_PROJECTS_PATH = "/projects";
	private static final String API_URL_ELEMENTS_IDS_PATH = "/resources?projectName=";
	private static final String API_URL_ELEMENTS_PATH = "/element";
	private static final String API_URL_SYSML_CLASSES_PATH = "/sysmlclass";
	private static final String API_URL_RELATIONSHIP_PATH = "/relationship";
	private static final String API_URL_GENERALIZATION_PATH = "/generalization";
	private static final String API_URL_RESOURCES_PATH = "/resources";
	
	private static String getApiUrlElementsPath(String projectName, int page, int limit) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		return API_URL_BASE + API_URL_ELEMENTS_PATH + "?projectName=" + projectName + "&page="+ page + "&limit=" + limit + "&linkBaseUrl=" + linkBaseUrl;
	}

	private static String getApiUrlElementByIdPath(String projectName, String elementId) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		return API_URL_BASE + API_URL_RESOURCES_PATH + "?projectName=" + projectName + "&elementId=" + elementId + "&linkBaseUrl=" + linkBaseUrl;
	}
	
	private static String getEncodedLinkBaseUrl(String projectName) {
		String url = OSLC4JUtils.getPublicURI().toString() + "/services/projects/" + projectName + "/elements/";
        return encodeUrlText(url);
	}
	
	private static String getApiUrlElementsSelectionPath(String projectName, String searchTerm) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		String encodedSearchText = encodeUrlText(searchTerm);
		return API_URL_BASE + API_URL_ELEMENTS_PATH + "?projectName=" + projectName + "&fullTextSearch="+ encodedSearchText + "&linkBaseUrl=" + linkBaseUrl;
	}
	
	private static String getApiUrlSysmlClassesPath(String projectName, int page, int limit) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		return API_URL_BASE + API_URL_SYSML_CLASSES_PATH + "?projectName=" + projectName + "&page="+ page + "&limit=" + limit + "&linkBaseUrl=" + linkBaseUrl;
	}
	
	private static String getApiUrlRelationshipsPath(String projectName, int page, int limit) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		return API_URL_BASE + API_URL_RELATIONSHIP_PATH + "?projectName=" + projectName + "&page="+ page + "&limit=" + limit + "&linkBaseUrl=" + linkBaseUrl;
	}

	private static String getApiUrlGeneralizationsPath(String projectName, int page, int limit) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		return API_URL_BASE + API_URL_GENERALIZATION_PATH + "?projectName=" + projectName + "&page="+ page + "&limit=" + limit + "&linkBaseUrl=" + linkBaseUrl;
	} 

	
	private static String encodeUrlText(String text) {
		try {
			return URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.warn(CapellaClient.class, e.getMessage());
			return URLEncoder.encode(text);
		}
	}
	
	//TODO error handling - capella api not available, different error
	public static List<ServiceProviderInfo> getProjects() {
		List<ServiceProviderInfo> serviceProviderInfos = new ArrayList<ServiceProviderInfo>();
		URL url;
		try {
			url = new URL(API_URL_BASE + API_URL_PROJECTS_PATH);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if(status != 200) {
				throw new WebApplicationException("Unable to fetch information about projects from capella server.", status);
			}
			JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
			JsonArray projectsJson = jsonObject.get("projects").getAsJsonArray();
			for (JsonElement p : projectsJson) {
				JsonObject jsonProject = p.getAsJsonObject();
				ServiceProviderInfo serviceProviderInfo = new ServiceProviderInfo(); 
				serviceProviderInfo.name = jsonProject.get("name").getAsString();
				serviceProviderInfo.projectId = jsonProject.get("id").getAsString();
				serviceProviderInfos.add(serviceProviderInfo);
			}
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return serviceProviderInfos;
	}

	
	public static List<Element> getProjectElements(String projectId, int page, int limit) {
		List<Element> elements = new ArrayList<Element>();
		try {
			URL url = new URL(getApiUrlElementsPath(projectId,page,limit+1));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if(status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}

			JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Element>>() {}.getType();
		    Gson gson = new Gson();
		    return gson.fromJson(jsonObject.get("elements"),  listType);
		    		
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return elements;
	}

	
	
	//TODO handle paging
	public static List<String> getElementsIdsForProject(String projectId) {
		List<String> elements = new ArrayList<String>();
		URL url;
		try {
			url = new URL(API_URL_BASE + API_URL_ELEMENTS_IDS_PATH + projectId);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
			JsonArray elementsJson = jsonObject.get("elementIds").getAsJsonArray();
			for (JsonElement p : elementsJson) {
				elements.add(p.getAsString());
			}
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return elements;
	}

	public static String getClassInfo(Class clazz) {
		StringBuilder sb = new StringBuilder();
		String intendation = "  ";
		sb.append(clazz.getName());
		for(Field f :  clazz.getDeclaredFields()) {
			sb.append("\n").append(intendation).append(f.getName()).append(" : ").append(f.getType().getName());
		}
		clazz = clazz.getSuperclass();
		while(clazz!=null) {
			sb.append("\n").append(intendation).append("Derived from ").append(clazz.getName());
			intendation += "  ";
			for(Field f :  clazz.getDeclaredFields()) {
				sb.append("\n").append(intendation).append(f.getName()).append(" : ").append(f.getType().getName());
			}
			clazz = clazz.getSuperclass();
		}
		return sb.toString();
	}

	public static Element getElementById(String projectId, String id) {
		try {
			URL url = new URL(getApiUrlElementByIdPath(projectId,id));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if(status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}
		    Gson gson = new Gson();
			JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
			java.lang.reflect.Type stringListType = new TypeToken<ArrayList<String>>() {}.getType();
			List<String> elementTypes = gson.fromJson(jsonObject.get("type"), stringListType);
			if (elementTypes.contains("ClassImpl")) {
			    return gson.fromJson(jsonObject, SysmlClass.class);
			}
			if (elementTypes.contains("GeneralizationImpl")) {
			    return gson.fromJson(jsonObject, Generalization.class);
			}
			if (elementTypes.contains("RelationshipImpl")) {
			    return gson.fromJson(jsonObject, Relationship.class);
			}
		    return gson.fromJson(jsonObject, Element.class);
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return null;
	}

	public static List<Element> selectProjectElements(String projectId, String terms) {
		try {
			URL url = new URL(getApiUrlElementsSelectionPath(projectId,terms));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if(status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}
			JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Element>>() {}.getType();
		    Gson gson = new Gson();
		    return gson.fromJson(jsonObject.get("elements"),  listType);
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return null;
	}

	public static List<SysmlClass> getProjectSysmlClasses(String projectId, int page, int limit) {
		List<SysmlClass> classes = new ArrayList<SysmlClass>();
		try {
			URL url = new URL(getApiUrlSysmlClassesPath(projectId,page,limit+1));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if(status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}
			JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
			java.lang.reflect.Type listType = new TypeToken<ArrayList<SysmlClass>>() {}.getType();
		    Gson gson = new Gson();
		    return gson.fromJson(jsonObject.get("elements"),  listType);
		    		
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return classes;
	}

	public static SysmlClass getSysmlClassById(String projectId, String id) {
		try { //TODO vyextrahovat spolecnou funkcionalitu do jine metody a mozna nejaky check. staci vlastne jen url a typ listu
			URL url = new URL(getApiUrlElementByIdPath(projectId,id));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if(status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}
		    Gson gson = new Gson();
		    return gson.fromJson(new InputStreamReader(con.getInputStream()),  SysmlClass.class);
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return null;
	}

	public static List<Relationship> getProjectRelationships(String projectId, int page, int limit) {
		List<Relationship> classes = new ArrayList<Relationship>();
		try {
			URL url = new URL(getApiUrlRelationshipsPath(projectId,page,limit+1));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if(status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}
			JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Relationship>>() {}.getType();
		    Gson gson = new Gson();
		    return gson.fromJson(jsonObject.get("elements"),  listType);
		    		
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return classes;
	}


	public static List<Generalization> getProjectGeneralizations(String projectId, int page, int limit) {
		List<Generalization> classes = new ArrayList<Generalization>();
		try {
			URL url = new URL(getApiUrlGeneralizationsPath(projectId,page,limit+1));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if(status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}
			JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Generalization>>() {}.getType();
		    Gson gson = new Gson();
		    return gson.fromJson(jsonObject.get("elements"),  listType);
		    		
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return classes;
	}


//	public static String TransformQueryForElement(String where, String prefix) {
//		where
//	}
	
}
