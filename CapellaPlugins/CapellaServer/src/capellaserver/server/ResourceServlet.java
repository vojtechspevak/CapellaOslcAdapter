package capellaserver.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import capellaserver.domain.Element;
import capellaserver.helpers.ServletHelper;
import capellaserver.services.ResourceService;

public class ResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;

    /**
     * This servlet is used to find element by id and return it mapped to its most suitable sysml counterpart
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		 throws ServletException, IOException {
    	String projectName = request.getParameter("projectName");
		String linkBaseUrl = request.getParameter("linkBaseUrl");
    	String elementId = request.getParameter("elementId");
    	if (projectName == null || elementId == null) {
			ServletHelper.setErrorResponse(response, "Project name and elementId need to be provided");
    		return;
    	}
    	ResourceService resourceService = new ResourceService(linkBaseUrl);
    	Element sysmlElement = resourceService.getResourceById(projectName, elementId);
    	ServletHelper.setOkResponse(response, sysmlElement);
    }

	
}
