package capellaserver.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import capellaserver.domain.Link;

public class Classifier extends Type {
	private Set<Link> ownedSuperclassing = new HashSet<Link>();

	public Classifier() {
		super();

	}

	public Classifier(final URI about) {
		super(about);

	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result
					+ "{a Local Classifier Resource} - update Classifier.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public void addOwnedSuperclassing(final Link ownedSuperclassing) {
		this.ownedSuperclassing.add(ownedSuperclassing);
	}

	public Set<Link> getOwnedSuperclassing() {
		return ownedSuperclassing;
	}

	public void setOwnedSuperclassing(final Set<Link> ownedSuperclassing) {
		this.ownedSuperclassing.clear();
		if (ownedSuperclassing != null) {
			this.ownedSuperclassing.addAll(ownedSuperclassing);
		}

	}

}
