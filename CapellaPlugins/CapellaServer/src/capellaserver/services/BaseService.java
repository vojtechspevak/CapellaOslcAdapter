package capellaserver.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;

import capellaapi.provider.CapellaEmfElementsProvider;
import capellaapi.provider.ICapellaEmfElementsProvider;

public class BaseService {

	protected ICapellaEmfElementsProvider _capellaElementsProvider = new CapellaEmfElementsProvider();
	protected HttpServletRequest _request;
	
	public BaseService(HttpServletRequest request) {
		_request = request;
	}
	
	protected List<EObject> handlePaging(List<EObject> elements){
		try {
			int page = Integer.parseInt(_request.getParameter("page"));
			int limit = Integer.parseInt(_request.getParameter("limit"));
			int listSize = elements.size();
			return elements.subList(page*limit < listSize ? page*limit : listSize  
					, (page+1)*limit <= listSize ? (page+1)*limit : listSize);
		} catch(NumberFormatException e) {
			return elements;
		}
	}
	
}
