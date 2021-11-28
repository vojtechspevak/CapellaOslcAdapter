package capellaserver.services;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;

import capellaserver.domain.Element;
import capellaserver.domain.SysmlPackage;
import capellaserver.services.mapping.Mapper;

public class SysmlPackageService extends BaseService {

	// Or retrieve thsi from the mapper?
	private static final List<Class<?>> CAPELLA_CLASSES = Arrays.asList(LogicalComponentPkg.class);
	private static final Class<?> SYSML_CLASS = SysmlPackage.class;
	
	protected final String _linkBaseUrl;
	public SysmlPackageService(HttpServletRequest request, String linkBaseUrl) {
		super(request);
		_linkBaseUrl = linkBaseUrl;
	}

	public List<EObject> getElements(String projectName) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsByType(projectName, CAPELLA_CLASSES.get(0)); // TODO get all classes
		return handlePaging(elements);
	}

	public List<Element> getSysmlElements(String projectName) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsByType(projectName, CAPELLA_CLASSES.get(0)); // TODO get all classes
		elements = handlePaging(elements);
		return Mapper.map(elements, SYSML_CLASS, _linkBaseUrl);
	}
	
	public List<Element> getElementsByFullTextSearch(String projectName, String searchText) {
		List<EObject> elements = _capellaElementsProvider.getProjectElementsFullText(projectName, searchText, CAPELLA_CLASSES.get(0));
		elements =  handlePaging(elements);
		return Mapper.map(elements, SYSML_CLASS, _linkBaseUrl);
	}
}
