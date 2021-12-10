package capellaserver.server;

import capellaserver.domain.PortUsage;

/**
 * Servlet exposing SysML PortUsages
 * Functionality is inherited from BaseCollectionServlet
 */
public class PortUsageServlet extends BaseCollectionServlet {

	private static final long serialVersionUID = 10L;

	public PortUsageServlet() {
		super(PortUsage.class);
	}

}
