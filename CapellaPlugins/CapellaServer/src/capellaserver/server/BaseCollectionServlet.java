package capellaserver.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import capellaserver.domain.Element;
import capellaserver.helpers.ServletHelper;
import capellaserver.services.ResourceCollectionService;

public abstract class BaseCollectionServlet extends HttpServlet {

	private static final long serialVersionUID = 3L;
	
	protected final Class<?> _targetClass;
	
	/**
	 * This constructor enforces to set the _targetClass property in the derived Class 
	 * @param targetClass
	 */
	public BaseCollectionServlet(Class<?> targetClass) {
		_targetClass = targetClass;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectName = ServletHelper.getAndDecodeBase64Parameter(request,"projectName");
		String fullTextSearch = request.getParameter("fullTextSearch");
		String linkBaseUrl = request.getParameter("linkBaseUrl");
		String aqlExpression = request.getParameter("aqlExpr");
	
		if (projectName == null) {
			ServletHelper.setErrorResponse(response, "Project name needs to be provided");
			return;
		}

		ResourceCollectionService collectionService = new ResourceCollectionService(request,_targetClass,linkBaseUrl);

		if (fullTextSearch != null) {
			List<Element> elements = collectionService.getElementsByFullTextSearch(projectName, fullTextSearch);
			ServletHelper.setOkResponse(response, elements, "elements");
			return;
		}

		List<Element> elements = collectionService.getElements(projectName,aqlExpression);
		ServletHelper.setOkResponse(response, elements, "elements");
	}
}
