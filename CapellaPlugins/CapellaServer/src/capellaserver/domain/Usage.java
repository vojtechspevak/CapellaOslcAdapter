package capellaserver.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class Usage extends Feature {
	private Boolean isVariation;
	private Set<Link> nestedUsage = new HashSet<Link>();
	private Link owningUsage;
	private Set<Link> nestedPort = new HashSet<Link>();
	private Set<Link> nestedAction = new HashSet<Link>();
	private Set<Link> nestedState = new HashSet<Link>();
	private Set<Link> nestedConstraint = new HashSet<Link>();
	private Set<Link> nestedTransition = new HashSet<Link>();
	private Set<Link> nestedRequirement = new HashSet<Link>();
	private Set<Link> nestedCalculation = new HashSet<Link>();
	private Set<Link> flowFeature = new HashSet<Link>();
	private Set<Link> nestedCase = new HashSet<Link>();
	private Set<Link> nestedAnalysisCase = new HashSet<Link>();
	private Set<Link> variantMembership_comp = new HashSet<Link>();
	private Set<Link> usage = new HashSet<Link>();
	private Set<Link> variant = new HashSet<Link>();
	private Set<Link> nestedReference = new HashSet<Link>();
	private Set<Link> nestedConnection = new HashSet<Link>();
	private Set<Link> nestedItem = new HashSet<Link>();
	private Set<Link> nestedPart = new HashSet<Link>();
	private Set<Link> nestedIndividual = new HashSet<Link>();
	private Set<Link> nestedInterface = new HashSet<Link>();
	private Set<Link> nestedAttribute = new HashSet<Link>();
	private Set<Link> nestedView = new HashSet<Link>();
	private Set<Link> nestedViewpoint = new HashSet<Link>();
	private Set<Link> nestedRendering = new HashSet<Link>();
	private Set<Link> nestedVerificationCase = new HashSet<Link>();
	private Set<Link> nestedEnumeration = new HashSet<Link>();
	private Set<Link> nestedAllocation = new HashSet<Link>();
	private Link owningDefinition;
	private Set<Link> variantMembership = new HashSet<Link>();

	public Usage() {
		super();

	}

	public Usage(final URI about) {
		super(about);

	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result + "{a Local Usage Resource} - update Usage.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public void addNestedUsage(final Link nestedUsage) {
		this.nestedUsage.add(nestedUsage);
	}

	public void addNestedPort(final Link nestedPort) {
		this.nestedPort.add(nestedPort);
	}

	public void addNestedAction(final Link nestedAction) {
		this.nestedAction.add(nestedAction);
	}

	public void addNestedState(final Link nestedState) {
		this.nestedState.add(nestedState);
	}

	public void addNestedConstraint(final Link nestedConstraint) {
		this.nestedConstraint.add(nestedConstraint);
	}

	public void addNestedTransition(final Link nestedTransition) {
		this.nestedTransition.add(nestedTransition);
	}

	public void addNestedRequirement(final Link nestedRequirement) {
		this.nestedRequirement.add(nestedRequirement);
	}

	public void addNestedCalculation(final Link nestedCalculation) {
		this.nestedCalculation.add(nestedCalculation);
	}

	public void addFlowFeature(final Link flowFeature) {
		this.flowFeature.add(flowFeature);
	}

	public void addNestedCase(final Link nestedCase) {
		this.nestedCase.add(nestedCase);
	}

	public void addNestedAnalysisCase(final Link nestedAnalysisCase) {
		this.nestedAnalysisCase.add(nestedAnalysisCase);
	}

	public void addVariantMembership_comp(final Link variantMembership_comp) {
		this.variantMembership_comp.add(variantMembership_comp);
	}

	public void addUsage(final Link usage) {
		this.usage.add(usage);
	}

	public void addVariant(final Link variant) {
		this.variant.add(variant);
	}

	public void addNestedReference(final Link nestedReference) {
		this.nestedReference.add(nestedReference);
	}

	public void addNestedConnection(final Link nestedConnection) {
		this.nestedConnection.add(nestedConnection);
	}

	public void addNestedItem(final Link nestedItem) {
		this.nestedItem.add(nestedItem);
	}

	public void addNestedPart(final Link nestedPart) {
		this.nestedPart.add(nestedPart);
	}

	public void addNestedIndividual(final Link nestedIndividual) {
		this.nestedIndividual.add(nestedIndividual);
	}

	public void addNestedInterface(final Link nestedInterface) {
		this.nestedInterface.add(nestedInterface);
	}

	public void addNestedAttribute(final Link nestedAttribute) {
		this.nestedAttribute.add(nestedAttribute);
	}

	public void addNestedView(final Link nestedView) {
		this.nestedView.add(nestedView);
	}

	public void addNestedViewpoint(final Link nestedViewpoint) {
		this.nestedViewpoint.add(nestedViewpoint);
	}

	public void addNestedRendering(final Link nestedRendering) {
		this.nestedRendering.add(nestedRendering);
	}

	public void addNestedVerificationCase(final Link nestedVerificationCase) {
		this.nestedVerificationCase.add(nestedVerificationCase);
	}

	public void addNestedEnumeration(final Link nestedEnumeration) {
		this.nestedEnumeration.add(nestedEnumeration);
	}

	public void addNestedAllocation(final Link nestedAllocation) {
		this.nestedAllocation.add(nestedAllocation);
	}

	public void addVariantMembership(final Link variantMembership) {
		this.variantMembership.add(variantMembership);
	}

	public Boolean isIsVariation() {
		return isVariation;
	}

	public Set<Link> getNestedUsage() {
		return nestedUsage;
	}

	public Link getOwningUsage() {
		return owningUsage;
	}

	public Set<Link> getNestedPort() {
		return nestedPort;
	}

	public Set<Link> getNestedAction() {
		return nestedAction;
	}

	public Set<Link> getNestedState() {
		return nestedState;
	}

	public Set<Link> getNestedConstraint() {
		return nestedConstraint;
	}

	public Set<Link> getNestedTransition() {
		return nestedTransition;
	}

	public Set<Link> getNestedRequirement() {
		return nestedRequirement;
	}

	public Set<Link> getNestedCalculation() {
		return nestedCalculation;
	}

	public Set<Link> getFlowFeature() {
		return flowFeature;
	}

	public Set<Link> getNestedCase() {
		return nestedCase;
	}

	public Set<Link> getNestedAnalysisCase() {
		return nestedAnalysisCase;
	}

	public Set<Link> getVariantMembership_comp() {
		return variantMembership_comp;
	}

	public Set<Link> getUsage() {
		return usage;
	}

	public Set<Link> getVariant() {
		return variant;
	}

	public Set<Link> getNestedReference() {
		return nestedReference;
	}

	public Set<Link> getNestedConnection() {
		return nestedConnection;
	}

	public Set<Link> getNestedItem() {
		return nestedItem;
	}

	public Set<Link> getNestedPart() {
		return nestedPart;
	}

	public Set<Link> getNestedIndividual() {
		return nestedIndividual;
	}

	public Set<Link> getNestedInterface() {
		return nestedInterface;
	}

	public Set<Link> getNestedAttribute() {
		return nestedAttribute;
	}

	public Set<Link> getNestedView() {
		return nestedView;
	}

	public Set<Link> getNestedViewpoint() {
		return nestedViewpoint;
	}

	public Set<Link> getNestedRendering() {
		return nestedRendering;
	}

	public Set<Link> getNestedVerificationCase() {
		return nestedVerificationCase;
	}

	public Set<Link> getNestedEnumeration() {
		return nestedEnumeration;
	}

	public Set<Link> getNestedAllocation() {
		return nestedAllocation;
	}

	public Link getOwningDefinition() {
		return owningDefinition;
	}

	public Set<Link> getVariantMembership() {
		return variantMembership;
	}

	public void setIsVariation(final Boolean isVariation) {
		this.isVariation = isVariation;

	}

	public void setNestedUsage(final Set<Link> nestedUsage) {
		this.nestedUsage.clear();
		if (nestedUsage != null) {
			this.nestedUsage.addAll(nestedUsage);
		}

	}

	public void setOwningUsage(final Link owningUsage) {
		this.owningUsage = owningUsage;

	}

	public void setNestedPort(final Set<Link> nestedPort) {
		this.nestedPort.clear();
		if (nestedPort != null) {
			this.nestedPort.addAll(nestedPort);
		}

	}

	public void setNestedAction(final Set<Link> nestedAction) {
		this.nestedAction.clear();
		if (nestedAction != null) {
			this.nestedAction.addAll(nestedAction);
		}

	}

	public void setNestedState(final Set<Link> nestedState) {
		this.nestedState.clear();
		if (nestedState != null) {
			this.nestedState.addAll(nestedState);
		}

	}

	public void setNestedConstraint(final Set<Link> nestedConstraint) {
		this.nestedConstraint.clear();
		if (nestedConstraint != null) {
			this.nestedConstraint.addAll(nestedConstraint);
		}

	}

	public void setNestedTransition(final Set<Link> nestedTransition) {
		this.nestedTransition.clear();
		if (nestedTransition != null) {
			this.nestedTransition.addAll(nestedTransition);
		}

	}

	public void setNestedRequirement(final Set<Link> nestedRequirement) {
		this.nestedRequirement.clear();
		if (nestedRequirement != null) {
			this.nestedRequirement.addAll(nestedRequirement);
		}

	}

	public void setNestedCalculation(final Set<Link> nestedCalculation) {
		this.nestedCalculation.clear();
		if (nestedCalculation != null) {
			this.nestedCalculation.addAll(nestedCalculation);
		}

	}

	public void setFlowFeature(final Set<Link> flowFeature) {
		this.flowFeature.clear();
		if (flowFeature != null) {
			this.flowFeature.addAll(flowFeature);
		}

	}

	public void setNestedCase(final Set<Link> nestedCase) {
		this.nestedCase.clear();
		if (nestedCase != null) {
			this.nestedCase.addAll(nestedCase);
		}

	}

	public void setNestedAnalysisCase(final Set<Link> nestedAnalysisCase) {
		this.nestedAnalysisCase.clear();
		if (nestedAnalysisCase != null) {
			this.nestedAnalysisCase.addAll(nestedAnalysisCase);
		}

	}

	public void setVariantMembership_comp(final Set<Link> variantMembership_comp) {
		this.variantMembership_comp.clear();
		if (variantMembership_comp != null) {
			this.variantMembership_comp.addAll(variantMembership_comp);
		}

	}

	public void setUsage(final Set<Link> usage) {
		this.usage.clear();
		if (usage != null) {
			this.usage.addAll(usage);
		}

	}

	public void setVariant(final Set<Link> variant) {
		this.variant.clear();
		if (variant != null) {
			this.variant.addAll(variant);
		}

	}

	public void setNestedReference(final Set<Link> nestedReference) {
		this.nestedReference.clear();
		if (nestedReference != null) {
			this.nestedReference.addAll(nestedReference);
		}

	}

	public void setNestedConnection(final Set<Link> nestedConnection) {
		this.nestedConnection.clear();
		if (nestedConnection != null) {
			this.nestedConnection.addAll(nestedConnection);
		}

	}

	public void setNestedItem(final Set<Link> nestedItem) {
		this.nestedItem.clear();
		if (nestedItem != null) {
			this.nestedItem.addAll(nestedItem);
		}

	}

	public void setNestedPart(final Set<Link> nestedPart) {
		this.nestedPart.clear();
		if (nestedPart != null) {
			this.nestedPart.addAll(nestedPart);
		}

	}

	public void setNestedIndividual(final Set<Link> nestedIndividual) {
		this.nestedIndividual.clear();
		if (nestedIndividual != null) {
			this.nestedIndividual.addAll(nestedIndividual);
		}

	}

	public void setNestedInterface(final Set<Link> nestedInterface) {
		this.nestedInterface.clear();
		if (nestedInterface != null) {
			this.nestedInterface.addAll(nestedInterface);
		}

	}

	public void setNestedAttribute(final Set<Link> nestedAttribute) {
		this.nestedAttribute.clear();
		if (nestedAttribute != null) {
			this.nestedAttribute.addAll(nestedAttribute);
		}

	}

	public void setNestedView(final Set<Link> nestedView) {
		this.nestedView.clear();
		if (nestedView != null) {
			this.nestedView.addAll(nestedView);
		}

	}

	public void setNestedViewpoint(final Set<Link> nestedViewpoint) {
		this.nestedViewpoint.clear();
		if (nestedViewpoint != null) {
			this.nestedViewpoint.addAll(nestedViewpoint);
		}

	}

	public void setNestedRendering(final Set<Link> nestedRendering) {
		this.nestedRendering.clear();
		if (nestedRendering != null) {
			this.nestedRendering.addAll(nestedRendering);
		}

	}

	public void setNestedVerificationCase(final Set<Link> nestedVerificationCase) {
		this.nestedVerificationCase.clear();
		if (nestedVerificationCase != null) {
			this.nestedVerificationCase.addAll(nestedVerificationCase);
		}

	}

	public void setNestedEnumeration(final Set<Link> nestedEnumeration) {
		this.nestedEnumeration.clear();
		if (nestedEnumeration != null) {
			this.nestedEnumeration.addAll(nestedEnumeration);
		}

	}

	public void setNestedAllocation(final Set<Link> nestedAllocation) {
		this.nestedAllocation.clear();
		if (nestedAllocation != null) {
			this.nestedAllocation.addAll(nestedAllocation);
		}

	}

	public void setOwningDefinition(final Link owningDefinition) {
		this.owningDefinition = owningDefinition;

	}

	public void setVariantMembership(final Set<Link> variantMembership) {
		this.variantMembership.clear();
		if (variantMembership != null) {
			this.variantMembership.addAll(variantMembership);
		}

	}

}
