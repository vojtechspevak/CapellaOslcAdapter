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
import capellaserver.services.SysmlClassService;
import capellaserver.services.SysmlPackageService;

public class SysmlPackageServlet extends HttpServlet {

	private static final long serialVersionUID = 8L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectName = request.getParameter("projectName");
		String fullTextSearch = request.getParameter("fullTextSearch");
		String linkBaseUrl = request.getParameter("linkBaseUrl");
		
		if (projectName == null) {
			ServletHelper.setErrorResponse(response, "Project name needs to be provided");
			return;
		}

		SysmlPackageService sysmlPackageService = new SysmlPackageService(request, linkBaseUrl);

		if (fullTextSearch != null) {
			List<Element> sysmlElements = sysmlPackageService.getElementsByFullTextSearch(projectName,fullTextSearch);
			ServletHelper.setOkResponse(response, sysmlElements, "elements");
			return;
		}
	
		List<Element> sysmlElements = sysmlPackageService.getSysmlElements(projectName);

		ServletHelper.setOkResponse(response, sysmlElements, "elements");
	}
	
}
