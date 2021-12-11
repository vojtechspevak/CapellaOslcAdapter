package capellaserver.server;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import capellaserver.domain.Element;
import capellaserver.services.ResourceService;

/**
 * Servlet used to find Capella element by id and return it mapped to its most
 * suitable SysML counterpart
 */
public class ResourceServlet extends HttpServlet {

	private static final long serialVersionUID = 2L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectName = ServletHelper.getAndDecodeBase64Parameter(request, "projectName");
		String linkBaseUrl = request.getParameter("linkBaseUrl");
		String elementId = request.getParameter("elementId");
		if (projectName == null || elementId == null) {
			ServletHelper.setErrorResponse(response, "Project name and elementId need to be provided");
			return;
		}
		ResourceService resourceService = new ResourceService(linkBaseUrl);
		Element sysmlElement;
		try {
			sysmlElement = resourceService.getResourceById(projectName, elementId);
		} catch (NoSuchElementException e) {
			ServletHelper.setErrorResponse(response, e.getMessage());
			return;
		}
		if (sysmlElement == null) {
			ServletHelper.setErrorResponse(response, "Specified element does not exist");
			return;
		}
		ServletHelper.setOkResponse(response, sysmlElement);
	}

}
