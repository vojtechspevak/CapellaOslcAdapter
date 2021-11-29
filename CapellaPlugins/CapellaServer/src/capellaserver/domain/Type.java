package capellaserver.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class Type extends Namespace

{
	private Boolean isAbstract;
	private Boolean isSufficient;
	private Boolean isConjugated;
	private Set<Link> ownedGeneralization = new HashSet<Link>();
	private Set<Link> ownedFeatureMembership_comp = new HashSet<Link>();
	private Set<Link> feature = new HashSet<Link>();
	private Set<Link> ownedFeature = new HashSet<Link>();
	private Set<Link> input = new HashSet<Link>();
	private Set<Link> output = new HashSet<Link>();
	private Set<Link> inheritedMembership = new HashSet<Link>();
	private Set<Link> endFeature = new HashSet<Link>();
	private Set<Link> ownedEndFeature = new HashSet<Link>();
	private Link ownedConjugator;
	private Set<Link> featureMembership = new HashSet<Link>();
	private Set<Link> inheritedFeature = new HashSet<Link>();
	private Link multiplicity;
	private Set<Link> ownedFeatureMembership = new HashSet<Link>();

	public Type() {
		super();

	}

	public Type(final URI about) {
		super(about);

	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result + "{a Local Type Resource} - update Type.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public void addOwnedGeneralization(final Link ownedGeneralization) {
		this.ownedGeneralization.add(ownedGeneralization);
	}

	public void addOwnedFeatureMembership_comp(final Link ownedFeatureMembership_comp) {
		this.ownedFeatureMembership_comp.add(ownedFeatureMembership_comp);
	}

	public void addFeature(final Link feature) {
		this.feature.add(feature);
	}

	public void addOwnedFeature(final Link ownedFeature) {
		this.ownedFeature.add(ownedFeature);
	}

	public void addInput(final Link input) {
		this.input.add(input);
	}

	public void addOutput(final Link output) {
		this.output.add(output);
	}

	public void addInheritedMembership(final Link inheritedMembership) {
		this.inheritedMembership.add(inheritedMembership);
	}

	public void addEndFeature(final Link endFeature) {
		this.endFeature.add(endFeature);
	}

	public void addOwnedEndFeature(final Link ownedEndFeature) {
		this.ownedEndFeature.add(ownedEndFeature);
	}

	public void addFeatureMembership(final Link featureMembership) {
		this.featureMembership.add(featureMembership);
	}

	public void addInheritedFeature(final Link inheritedFeature) {
		this.inheritedFeature.add(inheritedFeature);
	}

	public void addOwnedFeatureMembership(final Link ownedFeatureMembership) {
		this.ownedFeatureMembership.add(ownedFeatureMembership);
	}

	public Boolean isIsAbstract() {
		return isAbstract;
	}

	public Boolean isIsSufficient() {
		return isSufficient;
	}

	public Boolean isIsConjugated() {
		return isConjugated;
	}

	public Set<Link> getOwnedGeneralization() {
		return ownedGeneralization;
	}

	public Set<Link> getOwnedFeatureMembership_comp() {
		return ownedFeatureMembership_comp;
	}

	public Set<Link> getFeature() {
		return feature;
	}

	public Set<Link> getOwnedFeature() {
		return ownedFeature;
	}

	public Set<Link> getInput() {
		return input;
	}

	public Set<Link> getOutput() {
		return output;
	}

	public Set<Link> getInheritedMembership() {
		return inheritedMembership;
	}

	public Set<Link> getEndFeature() {
		return endFeature;
	}

	public Set<Link> getOwnedEndFeature() {
		return ownedEndFeature;
	}

	public Link getOwnedConjugator() {
		return ownedConjugator;
	}

	public Set<Link> getFeatureMembership() {
		return featureMembership;
	}

	public Set<Link> getInheritedFeature() {
		return inheritedFeature;
	}

	public Link getMultiplicity() {
		return multiplicity;
	}

	public Set<Link> getOwnedFeatureMembership() {
		return ownedFeatureMembership;
	}

	public void setIsAbstract(final Boolean isAbstract) {
		this.isAbstract = isAbstract;

	}

	public void setIsSufficient(final Boolean isSufficient) {
		this.isSufficient = isSufficient;

	}

	public void setIsConjugated(final Boolean isConjugated) {
		this.isConjugated = isConjugated;

	}

	public void setOwnedGeneralization(final Set<Link> ownedGeneralization) {
		this.ownedGeneralization.clear();
		if (ownedGeneralization != null) {
			this.ownedGeneralization.addAll(ownedGeneralization);
		}

	}

	public void setOwnedFeatureMembership_comp(final Set<Link> ownedFeatureMembership_comp) {
		this.ownedFeatureMembership_comp.clear();
		if (ownedFeatureMembership_comp != null) {
			this.ownedFeatureMembership_comp.addAll(ownedFeatureMembership_comp);
		}

	}

	public void setFeature(final Set<Link> feature) {
		this.feature.clear();
		if (feature != null) {
			this.feature.addAll(feature);
		}

	}

	public void setOwnedFeature(final Set<Link> ownedFeature) {
		this.ownedFeature.clear();
		if (ownedFeature != null) {
			this.ownedFeature.addAll(ownedFeature);
		}

	}

	public void setInput(final Set<Link> input) {
		this.input.clear();
		if (input != null) {
			this.input.addAll(input);
		}

	}

	public void setOutput(final Set<Link> output) {
		this.output.clear();
		if (output != null) {
			this.output.addAll(output);
		}

	}

	public void setInheritedMembership(final Set<Link> inheritedMembership) {
		this.inheritedMembership.clear();
		if (inheritedMembership != null) {
			this.inheritedMembership.addAll(inheritedMembership);
		}

	}

	public void setEndFeature(final Set<Link> endFeature) {
		this.endFeature.clear();
		if (endFeature != null) {
			this.endFeature.addAll(endFeature);
		}

	}

	public void setOwnedEndFeature(final Set<Link> ownedEndFeature) {
		this.ownedEndFeature.clear();
		if (ownedEndFeature != null) {
			this.ownedEndFeature.addAll(ownedEndFeature);
		}

	}

	public void setOwnedConjugator(final Link ownedConjugator) {
		this.ownedConjugator = ownedConjugator;

	}

	public void setFeatureMembership(final Set<Link> featureMembership) {
		this.featureMembership.clear();
		if (featureMembership != null) {
			this.featureMembership.addAll(featureMembership);
		}

	}

	public void setInheritedFeature(final Set<Link> inheritedFeature) {
		this.inheritedFeature.clear();
		if (inheritedFeature != null) {
			this.inheritedFeature.addAll(inheritedFeature);
		}

	}

	public void setMultiplicity(final Link multiplicity) {
		this.multiplicity = multiplicity;

	}

	public void setOwnedFeatureMembership(final Set<Link> ownedFeatureMembership) {
		this.ownedFeatureMembership.clear();
		if (ownedFeatureMembership != null) {
			this.ownedFeatureMembership.addAll(ownedFeatureMembership);
		}

	}

}
