package capellaserver.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;

import capellaserver.domain.Element;
import capellaserver.helpers.ServletHelper;
import capellaserver.services.ElementService;
import capellaserver.services.MappingService;

public class ElementServlet extends HttpServlet {

	private static final long serialVersionUID = 4L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectName = request.getParameter("projectName");
		String fullTextSearch = request.getParameter("fullTextSearch");
		String linkBaseUrl = request.getParameter("linkBaseUrl");

//		String projectName = ServletHelper.getAndDecodeParameter(request, "projectName"); 
//		String fullTextSearch = ServletHelper.getAndDecodeParameter(request, "fullTextSearch");
		
		if (projectName == null) {
			ServletHelper.setErrorResponse(response, "Project name needs to be provided");
			return;
		}

		ElementService elementService = new ElementService(request);
		MappingService mappingService = new MappingService(linkBaseUrl);

		if (fullTextSearch != null) {
			List<EObject> foundElements = elementService.getElementsByFullTextSearch(projectName, fullTextSearch);
			List<Element> sysmlElements = mappingService.mapFromEObject(foundElements);
			ServletHelper.setOkResponse(response, sysmlElements, "elements");
			return;
		}

		List<EObject> capellaElements = elementService.getElements(projectName);
		List<Element> sysmlElements = mappingService.mapFromEObject(capellaElements);

		ServletHelper.setOkResponse(response, sysmlElements, "elements");
	}

	
}
