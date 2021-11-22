package org.capella.oslc.mapping;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.capella.oslc.domains.capella.EObjectMock;

/**
 * This class holds all of the used mappings
 */

public class Mapper {

	private static final List<IMapping> _mappings;
	
	/**
	 * This is the place for registering new mappings to be used in the application
	 */
	static
	{
		_mappings = new ArrayList<IMapping>();
		_mappings.add(new NamedElement2Element());
		_mappings.add(new Class2SysmlClass());
	}

	// TODO change this, get by target type or better provide just a map method
	public static IMapping getMappingByClass(Class<?> sourceClass){
		return _mappings
				.stream()
				.filter(m -> m.getSourceClass().equals(sourceClass))
				.findFirst()
				.orElse(null);
	}

	/// I wanted to solve this without the need to specify the targetType
	// https://stackoverflow.com/questions/3437897/how-do-i-get-a-class-instance-of-generic-type-t
	// it should be possible but it looks horrible
	// can be further modified to check for best fit of the source type
//	public static <T> T map(EObjectMock source, Class<T> targetType){
//		IMapping mapping = _mappings
//				.stream()
//				.filter(m -> m.getTargetClass().equals(targetType))
//				.findFirst()
//				.orElse(null);
//		if (mapping == null) {
//			
//		}
//		mapping.getSourceClass()
//	    TypeVariable<?>[] parameters = instance.getClass().getTypeParameters(); 
//	    Class<?> targetClass = (Class<T>)parameters[0].getClass();
//		return null;
//	}


	public static List<IMapping> getMappings(){
		return _mappings;
	}
	
	//TODO check if class is collection and infer the type
    public static boolean isClassCollection(Class<?> c) {
        return Collection.class.isAssignableFrom(c)
                || Map.class.isAssignableFrom(c);
    }
}
