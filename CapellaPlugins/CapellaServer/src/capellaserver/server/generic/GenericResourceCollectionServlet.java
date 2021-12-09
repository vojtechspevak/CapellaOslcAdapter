package capellaserver.server.generic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;

import com.google.gson.JsonObject;

import capellaserver.server.ServletHelper;
import capellaserver.services.GenericResourceService;

public class GenericResourceCollectionServlet extends HttpServlet {

    private static final long serialVersionUID = 12L;
    private static final String DEFAULT_TYPE = "capellacore::NamedElement";

    /**
     * The generic servelts and related functionality is present to show how the eObject can e serialized using its reflective API
     * the resources are not mapped from the Capella representation ad the client can choose how to handle them itself
     * This servlet is used for handling collection requests
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

    	GenericResourceService resourceService = new GenericResourceService(request);

		if (fullTextSearch != null) {
			List<EObject> foundElements = resourceService.getElementsByFullTextSearch(projectName, fullTextSearch, type);
			JsonObject foundElementsJson = new JsonObject();
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
