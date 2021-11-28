package capellaserver.services.mapping;

import org.eclipse.emf.ecore.EObject;

import capellaserver.domain.Element;

public interface IMapping {
	public Class<?> getSourceClass();
	public Class<?> getTargetClass();
	public String getSourceClassName();
	public String getTargetClassName();
	public Element map(EObject source, String linkBaseUrl);
}
