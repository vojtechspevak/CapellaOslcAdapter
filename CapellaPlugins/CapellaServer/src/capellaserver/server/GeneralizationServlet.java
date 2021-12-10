package capellaserver.server;

import capellaserver.domain.Generalization;

/**
 * Servlet exposing SysML Generalizations
 * Functionality is inherited from BaseCollectionServlet
 */
public class GeneralizationServlet extends BaseCollectionServlet {

	private static final long serialVersionUID = 5L;

	public GeneralizationServlet() {
		super(Generalization.class);
	}

}
