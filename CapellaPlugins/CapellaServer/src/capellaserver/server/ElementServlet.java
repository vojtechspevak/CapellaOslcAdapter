package capellaserver.server;

import capellaserver.domain.Element;

/**
 * Servlet exposing SysML Elements
 * Functionality is inherited from BaseCollectionServlet
 */
public class ElementServlet extends BaseCollectionServlet {

	private static final long serialVersionUID = 4L;

	public ElementServlet() {
		super(Element.class);
	}


	
}
