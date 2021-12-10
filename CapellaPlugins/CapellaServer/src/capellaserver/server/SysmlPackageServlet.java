package capellaserver.server;

import capellaserver.domain.SysmlPackage;

/**
 * Servlet exposing SysmlPackages
 * Functionality is inherited from BaseCollectionServlet
 */
public class SysmlPackageServlet extends BaseCollectionServlet {
	private static final long serialVersionUID = 8L;

	public SysmlPackageServlet() {
		super(SysmlPackage.class);
	}
}
