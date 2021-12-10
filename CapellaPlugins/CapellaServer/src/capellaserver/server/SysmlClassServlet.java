package capellaserver.server;

import capellaserver.domain.SysmlClass;

/**
 * Servlet exposing SysmlClasses
 * Functionality is inherited from BaseCollectionServlet
 */
public class SysmlClassServlet extends BaseCollectionServlet {

	private static final long serialVersionUID = 7L;

	public SysmlClassServlet() {
		super(SysmlClass.class);
	}
	
}
