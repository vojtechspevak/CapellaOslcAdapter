package capellaserver.domain;

import java.net.URI;

public class PortUsage extends Usage {
	private Link portDefinition;
	private Link portOwningUsage;

	public PortUsage() {
		super();

	}

	public PortUsage(final URI about) {
		super(about);

	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result
					+ "{a Local PortUsage Resource} - update PortUsage.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public Link getPortDefinition() {
		return portDefinition;
	}

	public Link getPortOwningUsage() {
		return portOwningUsage;
	}

	public void setPortDefinition(final Link portDefinition) {
		this.portDefinition = portDefinition;

	}

	public void setPortOwningUsage(final Link portOwningUsage) {
		this.portOwningUsage = portOwningUsage;

	}

}
