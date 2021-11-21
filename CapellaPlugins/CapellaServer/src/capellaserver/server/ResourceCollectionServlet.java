package capellaserver.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;

import com.google.gson.JsonObject;

import capellaserver.helpers.EObjectSerializer;
import capellaserver.helpers.ServletHelper;
import capellaserver.services.ResourceService;

public class ResourceCollectionServlet extends HttpServlet {

    private static final long serialVersionUID = 12L;
    private static final String DEFAULT_TYPE = "capellacore::NamedElement";

    /**
     * This servlet provides generic access to resources and enables filtering based on various criteria
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		 throws ServletException, IOException {
    	String projectName = request.getParameter("projectName");
		String fullTextSearch = request.getParameter("fullTextSearch");
		String type = request.getParameter("type");
		if(type == null) {
			type = DEFAULT_TYPE;
		}


		if (projectName == null) {
			ServletHelper.setErrorResponse(response, "Project name needs to be provided");
			return;
		}

    	ResourceService resourceService = new ResourceService(request);

		if (fullTextSearch != null) {
			List<EObject> foundElements = resourceService.getElementsByFullTextSearch(projectName, fullTextSearch, type);
			JsonObject foundElementsJson = new JsonObject(); // TODO move the serialization to servlet helper?
			foundElementsJson.add("elements", EObjectSerializer.serializeEObjectCollection(foundElements));
			response.getWriter().println(foundElementsJson.toString());
			ServletHelper.setOkResponse(response);
			return;
		}
		
		
		List<EObject> elements = resourceService.getElements(projectName, type);
		JsonObject elementsJson = new JsonObject();
		elementsJson.add("elements", EObjectSerializer.serializeEObjectCollection(elements));
		response.getWriter().println(elementsJson.toString());
		ServletHelper.setOkResponse(response);
    }
	
}
