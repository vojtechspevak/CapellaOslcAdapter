package capellaserver.server.generic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;

import com.google.gson.JsonObject;

import capellaserver.server.ServletHelper;
import capellaserver.services.GenericResourceService;

/**
 * The generic servelts and related functionality is present to show how the eObject can e serialized using its reflective API
 * the resources are not mapped from the Capella representation ad the client can choose how to handle them itself
 * This servlet is used to find element by id
 */
public class GenericResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 11L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		 throws ServletException, IOException {

    	String projectName = ServletHelper.getAndDecodeBase64Parameter(request, "projectId");
    	String elementId = request.getParameter("elementId");
    	boolean includeTypes = Boolean.parseBoolean(request.getParameter("includeTypes"));
    	if (projectName == null || elementId == null) {
			ServletHelper.setErrorResponse(response, "Project id and element id needs to be provided");
    		return;
    	}

    	GenericResourceService resourceService = new GenericResourceService(request);
    	EObject element = resourceService.getElementById(projectName, elementId);
    	if(element == null) {
    		ServletHelper.setErrorResponse(response, "Specified element does not exist");
    		return;
    	}
		
		if(!includeTypes) {
			response.getWriter().println(EObjectSerializer.serializeEObject(element).toString());
		} else {
			JsonObject elementWithTypes = new JsonObject();
			elementWithTypes.add("element", EObjectSerializer.serializeEObject(element));
			elementWithTypes.add("types", EObjectSerializer.serializeObjectTypes(element));
			response.getWriter().println(elementWithTypes.toString());
		}
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
    }
   
	
}
