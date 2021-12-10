package capellaserver.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class SysmlPackage extends Namespace {
	private Set<Link> filterCondition = new HashSet<Link>();

	public SysmlPackage() {
		super();

	}

	public SysmlPackage(final URI about) {
		super(about);

	}	

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result + "{a Local Package Resource} - update Package.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public void addFilterCondition(final Link filterCondition) {
		this.filterCondition.add(filterCondition);
	}

	public Set<Link> getFilterCondition() {
		return filterCondition;
	}

	public void setFilterCondition(final Set<Link> filterCondition) {
		this.filterCondition.clear();
		if (filterCondition != null) {
			this.filterCondition.addAll(filterCondition);
		}

	}
}
