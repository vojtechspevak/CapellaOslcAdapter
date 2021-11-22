package org.capella.oslc.domains.capella;

import java.util.ArrayList;
import java.util.List;

public class EObjectMock {
	protected IdentifiableReferenceObject eContainer;
	protected List<IdentifiableReferenceObject> eContents = new ArrayList<IdentifiableReferenceObject>();;

	public IdentifiableReferenceObject eContainer() {
		return eContainer;
	}
	public void seteContainer(IdentifiableReferenceObject eContainer) {
		this.eContainer = eContainer;
	}
	public List<IdentifiableReferenceObject> eContents() {
		return eContents;
	}
	public void seteContents(List<IdentifiableReferenceObject> eContents) {
		this.eContents = eContents;
	}
}
