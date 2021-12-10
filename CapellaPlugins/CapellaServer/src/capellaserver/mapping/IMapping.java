package capellaserver.mapping;

import org.eclipse.emf.ecore.EObject;

import capellaserver.domain.Element;

/**
 * interface prescribing methods needed for the implemented mappings 
 * to be usable in Mapper class
 */
public interface IMapping {
	public Class<?> getSourceClass();
	public Class<?> getTargetClass();
	public Element map(EObject source, String linkBaseUrl);
}
