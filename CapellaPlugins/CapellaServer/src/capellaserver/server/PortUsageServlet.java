package capellaserver.server;

import capellaserver.domain.PortUsage;

public class PortUsageServlet extends BaseCollectionServlet {

	private static final long serialVersionUID = 10L;

	public PortUsageServlet() {
		super(PortUsage.class);
	}

}
