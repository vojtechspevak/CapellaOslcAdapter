package capellaserver.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import capellaserver.domain.Element;
import capellaserver.services.ResourceCollectionService;

/**
 * Base class that handles all functionality needed for exposing colelctions of resources for specifis SysML types
 * The derived classes only need to specify the target class and all the functionality is inherited 
 */
public abstract class BaseCollectionServlet extends HttpServlet {

	private static final long serialVersionUID = 3L;
	
	protected final Class<?> _targetClass;
	
	/**
	 * This constructor enforces the _targetClass property to be set in the derived Class 
	 * @param targetClass
	 */
	public BaseCollectionServlet(Class<?> targetClass) {
		_targetClass = targetClass;
	}
	
	/**
	 * generic functionality for handling the request, the method parses the request parameters 
	 * and performs the required search depending on the type defined in the derived class
	 */
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
