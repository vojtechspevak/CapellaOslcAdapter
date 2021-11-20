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
import capellaserver.services.GeneralizationService;
import capellaserver.services.MappingService;

public class GeneralizationServlet extends HttpServlet {

	private static final long serialVersionUID = 7L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectName = request.getParameter("projectName");
		String linkBaseUrl = request.getParameter("linkBaseUrl");
		
		if (projectName == null) {
			ServletHelper.setErrorResponse(response, "Project name needs to be provided");
			return;
		}

		GeneralizationService generalizationService = new GeneralizationService(request);
		MappingService mappingService = new MappingService(linkBaseUrl);

		List<EObject> capellaElements = generalizationService.getElements(projectName);
		List<Element> sysmlElements = mappingService.mapFromEObject(capellaElements);

		ServletHelper.setOkResponse(response, sysmlElements, "elements");
	}
	
}
