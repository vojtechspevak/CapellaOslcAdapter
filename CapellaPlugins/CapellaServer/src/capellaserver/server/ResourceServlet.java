package capellaserver.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;

import capellaserver.domain.Element;
import capellaserver.helpers.ServletHelper;
import capellaserver.services.ElementService;
import capellaserver.services.MappingService;

public class ResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * This servlet is used to find element by id and return as best suitable sub-type
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		 throws ServletException, IOException {
    	String projectName = request.getParameter("projectName");
		String linkBaseUrl = request.getParameter("linkBaseUrl");
    	String elementId = request.getParameter("elementId");
    	if (projectName == null || elementId == null) {
			ServletHelper.setErrorResponse(response, "Project name and elementId needs to be provided");
    		return;
    	}
    	
    	ElementService elementService = new ElementService(request);
    	EObject capellaElement = elementService.getElementById(projectName, elementId);
    	
		MappingService mappingService = new MappingService(linkBaseUrl);
    	Element sysmlElement = mappingService.mapFromEObject(capellaElement);

    	ServletHelper.setOkResponse(response, sysmlElement);
    }

	
}
