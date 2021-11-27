package capellaapi.projectmanager;

import org.eclipse.emf.ecore.EObject;

public class EmfProjectRepresentation {
	private EObject _searchRoot;
	private String _name;

	public EmfProjectRepresentation(EObject searchRoot, String name) {
		_searchRoot = searchRoot;
		_name = name;
	}

	public EObject getSearchRoot() {
		return _searchRoot;
	}

	public String getName() {
		return _name;
	}

}
