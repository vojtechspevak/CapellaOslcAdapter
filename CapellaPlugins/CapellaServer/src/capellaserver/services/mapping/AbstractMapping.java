package capellaserver.services.mapping;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractMapping implements IMapping{
	protected Class<?> _source; 
	protected Class<?> _target; 

	@Override
	public Class<?> getSourceClass() {
		return _source;
	}

	@Override
	public Class<?> getTargetClass() {
		return _target;
	}

	@Override
	public String getSourceClassName() {
		return _source.getSimpleName();
	}

	@Override
	public String getTargetClassName() {
		return _target.getSimpleName();
	}
	
	protected static URI createURI(String uriString) {
		try {
			return new URI(uriString);
		} catch (URISyntaxException e1) {
			return null;
		}
	}

}
