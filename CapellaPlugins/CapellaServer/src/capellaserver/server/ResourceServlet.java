package capellaserver.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.information.impl.ClassImpl;

import com.google.gson.JsonObject;

import capellaserver.domain.Element;
import capellaserver.helpers.EObjectSerializer;
import capellaserver.helpers.ServletHelper;
import capellaserver.services.ElementService;
import capellaserver.services.MappingService;
import capellaserver.services.ResourceService;

public class ResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
