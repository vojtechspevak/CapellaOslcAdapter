package capellaserver.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class Feature extends Type {
	private Boolean isUnique;
	private Boolean isOrdered;
	private Boolean isComposite;
	private Boolean isEnd;
	private Boolean isNonunique;
	private Set<Link> ownedTypeFeaturing = new HashSet<Link>();
	private Link owningFeatureMembership;
	private Link owningType;
	private Link endOwningType;
	private Set<Link> sysmlType = new HashSet<Link>();
	private Set<Link> ownedRedefinition = new HashSet<Link>();
	private Set<Link> ownedSubsetting = new HashSet<Link>();
	private Set<Link> ownedTyping = new HashSet<Link>();
	private Set<Link> featuringType = new HashSet<Link>();

	public Feature() {
		super();

	}

	public Feature(final URI about) {
		super(about);

	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result + "{a Local Feature Resource} - update Feature.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public void addOwnedTypeFeaturing(final Link ownedTypeFeaturing) {
		this.ownedTypeFeaturing.add(ownedTypeFeaturing);
	}

	public void addSysmlType(final Link type) {
		this.sysmlType.add(type);
	}

	public void addOwnedRedefinition(final Link ownedRedefinition) {
		this.ownedRedefinition.add(ownedRedefinition);
	}

	public void addOwnedSubsetting(final Link ownedSubsetting) {
		this.ownedSubsetting.add(ownedSubsetting);
	}

	public void addOwnedTyping(final Link ownedTyping) {
		this.ownedTyping.add(ownedTyping);
	}

	public void addFeaturingType(final Link featuringType) {
		this.featuringType.add(featuringType);
	}

	public Boolean isIsUnique() {
		return isUnique;
	}

	public Boolean isIsOrdered() {
		return isOrdered;
	}

	public Boolean isIsComposite() {
		return isComposite;
	}

	public Boolean isIsEnd() {
		return isEnd;
	}

	public Boolean isIsNonunique() {
		return isNonunique;
	}

	public Set<Link> getOwnedTypeFeaturing() {
		return ownedTypeFeaturing;
	}

	public Link getOwningFeatureMembership() {
		return owningFeatureMembership;
	}

	public Link getOwningType() {
		return owningType;
	}

	public Link getEndOwningType() {
		return endOwningType;
	}

	public Set<Link> getSysmlType() {
		return sysmlType;
	}

	public Set<Link> getOwnedRedefinition() {
		return ownedRedefinition;
	}

	public Set<Link> getOwnedSubsetting() {
		return ownedSubsetting;
	}

	public Set<Link> getOwnedTyping() {
		return ownedTyping;
	}

	public Set<Link> getFeaturingType() {
		return featuringType;
	}

	public void setIsUnique(final Boolean isUnique) {
		this.isUnique = isUnique;

	}

	public void setIsOrdered(final Boolean isOrdered) {
		this.isOrdered = isOrdered;

	}

	public void setIsComposite(final Boolean isComposite) {
		this.isComposite = isComposite;

	}

	public void setIsEnd(final Boolean isEnd) {
		this.isEnd = isEnd;

	}

	public void setIsNonunique(final Boolean isNonunique) {
		this.isNonunique = isNonunique;

	}

	public void setOwnedTypeFeaturing(final Set<Link> ownedTypeFeaturing) {
		this.ownedTypeFeaturing.clear();
		if (ownedTypeFeaturing != null) {
			this.ownedTypeFeaturing.addAll(ownedTypeFeaturing);
		}

	}

	public void setOwningFeatureMembership(final Link owningFeatureMembership) {
		this.owningFeatureMembership = owningFeatureMembership;

	}

	public void setOwningType(final Link owningType) {
		this.owningType = owningType;

	}

	public void setEndOwningType(final Link endOwningType) {
		this.endOwningType = endOwningType;

	}

	public void setSysmlType(final Set<Link> type) {
		this.sysmlType.clear();
		if (type != null) {
			this.sysmlType.addAll(type);
		}

	}

	public void setOwnedRedefinition(final Set<Link> ownedRedefinition) {
		this.ownedRedefinition.clear();
		if (ownedRedefinition != null) {
			this.ownedRedefinition.addAll(ownedRedefinition);
		}

	}

	public void setOwnedSubsetting(final Set<Link> ownedSubsetting) {
		this.ownedSubsetting.clear();
		if (ownedSubsetting != null) {
			this.ownedSubsetting.addAll(ownedSubsetting);
		}

	}

	public void setOwnedTyping(final Set<Link> ownedTyping) {
		this.ownedTyping.clear();
		if (ownedTyping != null) {
			this.ownedTyping.addAll(ownedTyping);
		}

	}

	public void setFeaturingType(final Set<Link> featuringType) {
		this.featuringType.clear();
		if (featuringType != null) {
			this.featuringType.addAll(featuringType);
		}

	}

}
