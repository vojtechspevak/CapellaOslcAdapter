package capellaserver.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;

import com.google.gson.JsonObject;

import capellaserver.helpers.EObjectSerializer;
import capellaserver.helpers.ServletHelper;
import capellaserver.services.ResourceService;

public class GenericResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 11L;

    /**
     * This servlet is used to find element by id
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		 throws ServletException, IOException {


    	String projectName = request.getParameter("projectName");
    	String elementId = request.getParameter("elementId");
    	boolean includeTypes = Boolean.parseBoolean(request.getParameter("includeTypes"));
    	if (projectName == null || elementId == null) {
			ServletHelper.setErrorResponse(response, "Project name and elementId needs to be provided");
    		return;
    	}

    	ResourceService resourceService = new ResourceService(request);
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
