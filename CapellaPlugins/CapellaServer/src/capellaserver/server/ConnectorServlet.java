package capellaserver.server;

import capellaserver.domain.Connector;

/**
 * Servlet exposing SysML Connectors
 * Functionality is inherited from BaseCollectionServlet
 */
public class ConnectorServlet extends BaseCollectionServlet {

	private static final long serialVersionUID = 9L;

	public ConnectorServlet() {
		super(Connector.class);
	}

}
