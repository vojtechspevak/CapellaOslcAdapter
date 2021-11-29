package org.capella.oslc.sysml;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.apache.jena.atlas.logging.Log;
import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;
import org.oasis.oslcop.sysml.Element;
import org.oasis.oslcop.sysml.Generalization;
import org.oasis.oslcop.sysml.Relationship;
import org.oasis.oslcop.sysml.SysmlClass;
import org.oasis.oslcop.sysml.SysmlPackage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class CapellaClient {

	private static final String API_URL_BASE = "http://localhost:3333";
	private static final String API_URL_PROJECTS_PATH = "/projects";
	private static final String API_URL_ELEMENTS_PATH = "/element";
	private static final String API_URL_SYSML_CLASSES_PATH = "/sysmlclass";
	private static final String API_URL_RELATIONSHIP_PATH = "/relationship";
	private static final String API_URL_GENERALIZATION_PATH = "/generalization";
	private static final String API_URL_SYSML_PACKAGES_PATH = "/sysmlpackage";
	private static final String API_URL_RESOURCES_PATH = "/resources";

	private static String getApiResourceByIdPathUrl(String projectName, String elementId) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		return API_URL_BASE + API_URL_RESOURCES_PATH + "?projectName=" + projectName + "&elementId=" + elementId
				+ "&linkBaseUrl=" + linkBaseUrl;
	}

	private static String getEncodedLinkBaseUrl(String projectName) {
		String url = OSLC4JUtils.getPublicURI().toString() + "/services/projects/" + projectName + "/elements/";
		return encodeUrlText(url);
	}

	private static String getApiSelectionPathUrl(String elementPath, String projectName, String searchTerm) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		String encodedSearchText = encodeUrlText(searchTerm);
		return API_URL_BASE + elementPath + "?projectName=" + projectName + "&fullTextSearch=" + encodedSearchText
				+ "&linkBaseUrl=" + linkBaseUrl;
	}

	private static String getApiCollectionUrl(String elementPath, String projectName, int page, int limit) {
		String linkBaseUrl = getEncodedLinkBaseUrl(projectName);
		return API_URL_BASE + elementPath + "?projectName=" + projectName + "&page=" + page + "&limit=" + limit
				+ "&linkBaseUrl=" + linkBaseUrl;
	}

	private static String encodeUrlText(String text) {
		try {
			return URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.warn(CapellaClient.class, e.getMessage());
			return URLEncoder.encode(text);
		}
	}

	// TODO error handling - capella api not available, different error
	public static List<ServiceProviderInfo> getProjects() {
		List<ServiceProviderInfo> serviceProviderInfos = new ArrayList<ServiceProviderInfo>();
		String urlString = API_URL_BASE + API_URL_PROJECTS_PATH;
		JsonObject jsonObject = sendGetRequest(urlString);
		JsonArray projectsJson = jsonObject.get("projects").getAsJsonArray();
		for (JsonElement p : projectsJson) {
			JsonObject jsonProject = p.getAsJsonObject();
			ServiceProviderInfo serviceProviderInfo = new ServiceProviderInfo();
			serviceProviderInfo.name = jsonProject.get("name").getAsString();
			serviceProviderInfo.projectId = jsonProject.get("id").getAsString();
			serviceProviderInfos.add(serviceProviderInfo);
		}
		return serviceProviderInfos;
	}

	public static Element getElementById(String projectId, String id) {
		String urlString = getApiResourceByIdPathUrl(projectId, id);
		JsonObject jsonObject = sendGetRequest(urlString);
		Gson gson = new Gson();
		java.lang.reflect.Type stringListType = new TypeToken<ArrayList<String>>() {
		}.getType();
		List<String> elementTypes = gson.fromJson(jsonObject.get("type"), stringListType);
		if (elementTypes.contains("LogicalComponentPkgImpl")) {
			return gson.fromJson(jsonObject, SysmlPackage.class);
		}
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
	}

	public static List<Element> selectProjectElements(String projectId, String terms) {
		String urlString = getApiSelectionPathUrl(API_URL_ELEMENTS_PATH, projectId, terms);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Element>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<Element> getProjectElements(String projectId, int page, int limit) {
		String urlString = getApiCollectionUrl(API_URL_ELEMENTS_PATH, projectId, page, limit + 1);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Element>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<SysmlClass> getProjectSysmlClasses(String projectId, int page, int limit) {
		String urlString = getApiCollectionUrl(API_URL_SYSML_CLASSES_PATH, projectId, page, limit + 1);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<SysmlClass>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<SysmlClass> selectProjectSysmlClasses(String projectId, String terms) {
		String urlString = getApiSelectionPathUrl(API_URL_SYSML_CLASSES_PATH, projectId, terms);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<SysmlClass>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<Relationship> getProjectRelationships(String projectId, int page, int limit) {
		String urlString = getApiCollectionUrl(API_URL_RELATIONSHIP_PATH, projectId, page, limit + 1);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Relationship>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<Relationship> selectProjectRelationships(String projectId, String terms) {
		String urlString = getApiSelectionPathUrl(API_URL_RELATIONSHIP_PATH, projectId, terms);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Relationship>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<Generalization> getProjectGeneralizations(String projectId, int page, int limit) {
		String urlString = getApiCollectionUrl(API_URL_GENERALIZATION_PATH, projectId, page, limit + 1);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Generalization>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<Generalization> selectProjectGeneralizations(String projectId, String terms) {
		String urlString = getApiSelectionPathUrl(API_URL_GENERALIZATION_PATH, projectId, terms);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Generalization>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<SysmlPackage> getSysmlPackages(String projectId, int page, int limit) {
		String urlString = getApiCollectionUrl(API_URL_SYSML_PACKAGES_PATH, projectId, page, limit + 1);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<SysmlPackage>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	public static List<SysmlPackage> selectSysmlPackages(String projectId, String terms) {
		String urlString = getApiSelectionPathUrl(API_URL_SYSML_PACKAGES_PATH, projectId, terms);
		JsonObject jsonObject = sendGetRequest(urlString);
		java.lang.reflect.Type listType = new TypeToken<ArrayList<SysmlPackage>>() {
		}.getType();
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.get("elements"), listType);
	}

	private static JsonObject sendGetRequest(String urlString) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if (status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}
			return JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();
		} catch (IOException e) {
			Log.error(CapellaClient.class, e.getMessage());
			throw new WebApplicationException(e.getMessage(), 500);
		}
	}

	// TODO decide if other elements should have these as well or keep it just for the element that handles it generically
	public static SysmlClass getSysmlClassById(String projectId, String id) {
		try { 
			URL url = new URL(getApiResourceByIdPathUrl(projectId, id));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			if (status != 200) {
				throw new WebApplicationException("Unable to fetch elements from capella server.", status);
			}
			Gson gson = new Gson();
			return gson.fromJson(new InputStreamReader(con.getInputStream()), SysmlClass.class);
		} catch (IOException e) {
			Log.warn(CapellaClient.class, e.getMessage());
		}
		return null;
	}

}
