package capellaserver.services.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;

import capellaserver.domain.Element;

public class Mapper {

	private static final List<IMapping> _mappings;
	
	/**
	 * This is the place for registering new mappings to be used in the application
	 */
	static
	{
		_mappings = new ArrayList<IMapping>();
		_mappings.add(new ComponentPkg2SysmlPackage());
		_mappings.add(new NamedElement2Element());
		_mappings.add(new Class2SysmlClass());
		_mappings.add(new Generalization2Generalization());
		_mappings.add(new Relationship2Relationship());
	}

	public static Element map(EObject source, String linkBaseUrl){
		if(source == null) {
			return null;
		}
		IMapping mapping = findMostSuitableMapping(source);
		if (mapping == null) {
			throw new UnsupportedOperationException("Mapping not found.");
		}
		
		return mapping.map(source, linkBaseUrl);
	}

	public static List<Element> map(List<EObject> source, Class<?> targetClass, String linkBaseUrl){
		if(source.size() == 0) {
			return new ArrayList<Element>();
		}
		IMapping mapping = _mappings
				.stream()
				.filter(m -> m.getTargetClass().equals(targetClass) 
						&& m.getSourceClass().isAssignableFrom(source.get(0).getClass()))
				.findFirst()
				.orElse(null);
		if (mapping == null) {
			throw new UnsupportedOperationException("Mapping not found.");
		}
		
		return source.stream()
				.map(s -> mapping.map(s, linkBaseUrl))
				.collect(Collectors.toList());
	}
	
	/**
	 * searches for the best suited mapping for the source
	 * i.e. the mapping going from the exact Class or the closest super Class
	 * @param source source object to find the mapping for
	 * @return best suitable mapping or null, if none was found
	 */
	private static IMapping findMostSuitableMapping(EObject source) {
		Class<?> sourceClass = source.getClass();
		while (sourceClass != null) {
			for(IMapping mapping : _mappings) {
				if(mapping.getSourceClass().isAssignableFrom(sourceClass)) {
					return mapping;
				}
			}
			sourceClass = sourceClass.getSuperclass();
		}
		return null;
	}
	
}
