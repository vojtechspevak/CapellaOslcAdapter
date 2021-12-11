package capellaserver.server.generic;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;

import com.google.gson.JsonObject;

import capellaserver.server.ServletHelper;
import capellaserver.services.GenericResourceService;

/**
 * The generic servelts and related functionality is present to show how the
 * eObject can e serialized using its reflective API the resources are not
 * mapped from the Capella representation ad the client can choose how to handle
 * them itself This servlet is used for handling collection requests
 */
public class GenericResourceCollectionServlet extends HttpServlet {

	private static final long serialVersionUID = 12L;

	/**
	 * requests can fetch resources based on fulltext search or custom aqlQuery
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectName = ServletHelper.getAndDecodeBase64Parameter(request, "projectId");
		String fullTextSearch = request.getParameter("fullTextSearch");
		String aqlQuery = request.getParameter("aqlQuery");

		if (projectName == null) {
			ServletHelper.setErrorResponse(response, "Project name needs to be provided");
			return;
		}

		GenericResourceService resourceService = new GenericResourceService(request);

		try {
			if (fullTextSearch != null) {
				List<EObject> foundElements = resourceService.getElementsByFullTextSearch(projectName, fullTextSearch);
				JsonObject foundElementsJson = new JsonObject();
				foundElementsJson.add("elements", EObjectSerializer.serializeEObjectCollection(foundElements));
				response.getWriter().println(foundElementsJson.toString());
				ServletHelper.setOkResponse(response);
				return;
			}

			List<EObject> elements = resourceService.getElements(projectName, aqlQuery);
			if(elements == null) {
				ServletHelper.setErrorResponse(response, "null result.");
				return;
			}
			JsonObject elementsJson = new JsonObject();
			elementsJson.add("elements", EObjectSerializer.serializeEObjectCollection(elements));
			response.getWriter().println(elementsJson.toString());
			ServletHelper.setOkResponse(response);
		} catch (NoSuchElementException e) {
			ServletHelper.setErrorResponse(response, e.getMessage());
		}

	}

}
