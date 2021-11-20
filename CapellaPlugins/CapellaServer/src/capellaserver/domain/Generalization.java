package capellaserver.domain;

import java.net.URI;

import capellaserver.domain.Link;

public class Generalization extends Relationship {
	private Link general;
	private Link specific;
	private Link owningType;

	public Generalization() {
		super();

	}

	public Generalization(final URI about) {
		super(about);

	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result
					+ "{a Local Generalization Resource} - update Generalization.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public Link getGeneral() {
		return general;
	}

	public Link getSpecific() {
		return specific;
	}

	public Link getOwningType() {
		return owningType;
	}

	public void setGeneral(final Link general) {
		this.general = general;

	}

	public void setSpecific(final Link specific) {
		this.specific = specific;

	}

	public void setOwningType(final Link owningType) {
		this.owningType = owningType;

	}

}
