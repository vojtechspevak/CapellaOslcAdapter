package capellaserver.services.mapping;

import java.util.ArrayList;
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
//		_mappings.add(new NamedElement2Element());
//		_mappings.add(new Class2SysmlClass());
	}

	// TODO change this, get by target type or better provide just a map method
	public static IMapping getMappingByClass(Class<?> sourceClass){
		return _mappings
				.stream()
				.filter(m -> m.getSourceClass().equals(sourceClass))
				.findFirst()
				.orElse(null);
	}

	public static List<IMapping> getMappings(){
		return _mappings;
	}

	
	public static Element map(EObject source, String linkBaseUrl){
		IMapping mapping = _mappings
				.stream()
				.filter(m -> m.getSourceClass().isAssignableFrom(source.getClass()))
				.findFirst()
				.orElse(null);
		
		if (mapping != null) {
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
		
		return source.stream().map(s -> mapping.map(s, linkBaseUrl)).collect(Collectors.toList());
		
	}
	
	
	//TODO check if class is collection and infer the type
//    public static boolean isClassCollection(Class<?> c) {
//        return Collection.class.isAssignableFrom(c)
//                || Map.class.isAssignableFrom(c);
//    }
}
