package capellaserver.server;

import capellaserver.domain.Relationship;

/**
 * Servlet exposing SysML Relationships
 * Functionality is inherited from BaseCollectionServlet
 */
public class RelationshipServlet extends BaseCollectionServlet {

	private static final long serialVersionUID = 6L;

	public RelationshipServlet() {
		super(Relationship.class);
	}
	
}
