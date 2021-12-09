package capellaserver.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import capellaserver.domain.Element;

/**
 * This class holds all of the mappings and is intended 
 * to be used statically in the code (i.e. Mapper.Map(...))
 * The implemented mappings are to be registered below 
 */
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

	/**
	 * finds the best suitable target for a single object and maps it
	 * @param source object to map
	 * @param linkBaseUrl base URL to create links to from the mapped SysML element
	 * @throws UnsupportedOperationException if mapping is not found
	 * @return element mapped to the most specific type for a given source
	 */
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

	/**
	 * maps a collection of source EObjects to best suitable found target SysML Objects
	 * if general source type was provided, it may happen, 
	 * that the concrete types returned in the collection are not the same
	 * @param source list of objects to map
	 * @param linkBaseUrl base URL to create links to from the mapped SysML element
	 * @throws UnsupportedOperationException if mapping is not found
	 * @return list of elements, each mapped to the most specific type for a given source
	 */
	public static List<Element> map(List<EObject> source, String linkBaseUrl){
		ArrayList<Element> result = new ArrayList<Element>(source.size());
		if(source.size() == 0) {
			return result;
		}

		// this is done, so the mapping is not searched for repeatedly, 
		// it may be worth changing it to cache on a class level
		Map<Class<?>,IMapping> cachedMappings = new HashMap<Class<?>,IMapping>();
		for(EObject eObject : source) {
			IMapping mapping;
			if(cachedMappings.containsKey(eObject.getClass())){
				mapping = cachedMappings.get(eObject.getClass());
			} else {
				mapping = findMostSuitableMapping(eObject, _mappings);
				if(mapping == null) {
					throw new UnsupportedOperationException("Mapping not found.");
				}
				cachedMappings.put(eObject.getClass(), mapping);
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

	/**
	 * this method is used to obtain all classes that maps 
	 * to the specified target in the registered mappings
	 * @param targetElementClass
	 * @return List of source classes that are mapped to the specified target
	 */
	public static List<Class<?>> getSourceClassesForTargetClass(Class<?> targetElementClass) {
		return _mappings
				.stream()
				.filter(m -> m.getTargetClass().equals(targetElementClass))
				.map(m -> {return m.getSourceClass();})
				.collect(Collectors.toList());
	}
	
}
