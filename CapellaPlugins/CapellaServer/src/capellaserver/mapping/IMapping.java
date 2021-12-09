package capellaserver.mapping;

import org.eclipse.emf.ecore.EObject;

import capellaserver.domain.Element;

public interface IMapping {
	public Class<?> getSourceClass();
	public Class<?> getTargetClass();
	public Element map(EObject source, String linkBaseUrl);
}
