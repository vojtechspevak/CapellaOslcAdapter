package org.capella.oslc.mapping;

import org.capella.oslc.domains.capella.EObjectMock;
import org.oasis.oslcop.sysml.Element;

public interface IMapping {

	public Class<?> getSourceClass();
	public Class<?> getTargetClass();
	public String getSourceClassName();
	public String getTargetClassName();
	
	public Element map(EObjectMock eObject, String linkBaseUrl);
}
