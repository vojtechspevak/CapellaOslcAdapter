package capellaserver.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.Relationship;

public class RelationshipService extends BaseService {

	private static final Class<?> RELATIONSHIP_CLASS = Relationship.class;

	public RelationshipService(HttpServletRequest request) {
		super(request);
	}

	public List<EObject> getElements(String projectName) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsByType(projectName, RELATIONSHIP_CLASS); // TODO move these to the base service?
		return handlePaging(elements);
	}

	public EObject getElementById(String projectName, String elementId) {
		return _capellaElementsProvider.getProjectElement(projectName, elementId);
	}
}
