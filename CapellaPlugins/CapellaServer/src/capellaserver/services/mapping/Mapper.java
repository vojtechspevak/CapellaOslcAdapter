package capellaserver.services.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		_mappings.add(new LogicalComponent2SysmlClass());
		_mappings.add(new ComponentExchange2Connector());
		_mappings.add(new ComponentPort2PortUsage());
	}

	public static Element map(EObject source, String linkBaseUrl){
		if(source == null) {
			return null;
		}
		IMapping mapping = findMostSuitableMapping(source,_mappings);
		if (mapping == null) {
			throw new UnsupportedOperationException("Mapping not found.");
		}
		
		return mapping.map(source, linkBaseUrl);
	}

	public static List<Element> map(List<EObject> source, Class<?> targetClass, String linkBaseUrl){
		ArrayList<Element> result = new ArrayList<Element>(source.size());
		if(source.size() == 0) {
			return result;
		}
		
		List<IMapping> targetMappings = _mappings
				.stream()
				.filter(m -> m.getTargetClass().equals(targetClass)).collect(Collectors.toList());

		if (targetMappings.isEmpty()) {
			throw new UnsupportedOperationException("Mapping not found.");
		}

		// this is done to avoid the type checking of each element if it is not necessary
		if (targetMappings.size() == 1) {
			return source.stream()
					.map(s -> targetMappings.get(0).map(s, linkBaseUrl))
					.collect(Collectors.toList());
		}
		
		// if the reflection causes performance issues for large sets of resources, this can be rewritten to a form 
		// where the found  Class -> IMapping pairs are cached and not computed repeatedly
		for(EObject eObject : source) {
			IMapping mapping = findMostSuitableMapping(eObject, targetMappings);
			if(mapping == null) {
				throw new UnsupportedOperationException("Mapping not found.");
			}
			result.add(mapping.map(eObject, linkBaseUrl));
		}
		return result;
	}
	
	/**
	 * searches for the best suited mapping for the source
	 * i.e. the mapping going from the exact Class or the closest super Class
	 * @param source source object to find the mapping for
	 * @return best suitable mapping or null, if none was found
	 */
	private static IMapping findMostSuitableMapping(EObject source, List<IMapping> mappings) {
		Class<?> sourceClass = source.getClass();
	
		while (sourceClass != null) {
			List<Class<?>> interfaces = Arrays.asList(sourceClass.getInterfaces());
			for(IMapping mapping : mappings) {
				if(interfaces.contains(mapping.getSourceClass()) || sourceClass.equals(mapping.getSourceClass())) {
					return mapping;
				}
			}
			sourceClass = sourceClass.getSuperclass();
		}
		return null;
	}

	public static List<Class<?>> getSourceClassesForTargetClass(Class<?> targetElementClass) {
		return _mappings
				.stream()
				.filter(m -> m.getTargetClass().equals(targetElementClass))
				.map(m -> {return m.getSourceClass();})
				.collect(Collectors.toList());
	}
	
}
