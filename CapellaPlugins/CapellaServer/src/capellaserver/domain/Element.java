package capellaserver.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import capellaserver.domain.Link;
import capellaserver.domain.Resource;

public class Element extends Resource {
	private String sysmlIdentifier;
	private String name;
	private String qualifiedName;
	private Set<String> aliasId = new HashSet<String>();
	private String humanId;
	private Link owningMembership;
	private Set<Link> ownedRelationship_comp = new HashSet<Link>();
	private Link owningRelationship;
	private Link owningNamespace;
	private Link owner;
	private Set<Link> ownedElement = new HashSet<Link>();
	private Set<Link> documentation_comp = new HashSet<Link>();
	private Set<Link> ownedAnnotation_comp = new HashSet<Link>();
	private Set<Link> documentationComment = new HashSet<Link>();
	private Set<Link> ownedTextualRepresentation = new HashSet<Link>();
	private Set<Link> ownedRelationship = new HashSet<Link>();
	private Set<Link> documentation = new HashSet<Link>();
	private Set<Link> ownedAnnotation = new HashSet<Link>();

	public Element() {
		super();

	}

	public Element(final URI about) {
		super(about);

	}
// TODO probably delete this
//public static ResourceShape createResourceShape() throws OslcCoreApplicationException, URISyntaxException {
//    return ResourceShapeFactory.createResourceShape(OSLC4JUtils.getServletURI(),
//    OslcConstants.PATH_RESOURCE_SHAPES,
//    SysmlDomainConstants.ELEMENT_PATH,
//    Element.class);
//}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result + "{a Local Element Resource} - update Element.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public void addAliasId(final String aliasId) {
		this.aliasId.add(aliasId);
	}

	public void addOwnedRelationship_comp(final Link ownedRelationship_comp) {
		this.ownedRelationship_comp.add(ownedRelationship_comp);
	}

	public void addOwnedElement(final Link ownedElement) {
		this.ownedElement.add(ownedElement);
	}

	public void addDocumentation_comp(final Link documentation_comp) {
		this.documentation_comp.add(documentation_comp);
	}

	public void addOwnedAnnotation_comp(final Link ownedAnnotation_comp) {
		this.ownedAnnotation_comp.add(ownedAnnotation_comp);
	}

	public void addDocumentationComment(final Link documentationComment) {
		this.documentationComment.add(documentationComment);
	}

	public void addOwnedTextualRepresentation(final Link ownedTextualRepresentation) {
		this.ownedTextualRepresentation.add(ownedTextualRepresentation);
	}

	public void addOwnedRelationship(final Link ownedRelationship) {
		this.ownedRelationship.add(ownedRelationship);
	}

	public void addDocumentation(final Link documentation) {
		this.documentation.add(documentation);
	}

	public void addOwnedAnnotation(final Link ownedAnnotation) {
		this.ownedAnnotation.add(ownedAnnotation);
	}

	public String getSysmlIdentifier() {
		return sysmlIdentifier;
	}

	public String getName() {
		return name;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}

	public Set<String> getAliasId() {
		return aliasId;
	}

	public String getHumanId() {
		return humanId;
	}

	public Link getOwningMembership() {
		return owningMembership;
	}

	public Set<Link> getOwnedRelationship_comp() {
		return ownedRelationship_comp;
	}

	public Link getOwningRelationship() {
		return owningRelationship;
	}

	public Link getOwningNamespace() {
		return owningNamespace;
	}

	public Link getOwner() {
		return owner;
	}

	public Set<Link> getOwnedElement() {
		return ownedElement;
	}

	public Set<Link> getDocumentation_comp() {
		return documentation_comp;
	}

	public Set<Link> getOwnedAnnotation_comp() {
		return ownedAnnotation_comp;
	}

	public Set<Link> getDocumentationComment() {
		return documentationComment;
	}

	public Set<Link> getOwnedTextualRepresentation() {
		return ownedTextualRepresentation;
	}

	public Set<Link> getOwnedRelationship() {
		return ownedRelationship;
	}

	public Set<Link> getDocumentation() {
		return documentation;
	}

	public Set<Link> getOwnedAnnotation() {
		return ownedAnnotation;
	}

	public void setSysmlIdentifier(final String identifier) {
		this.sysmlIdentifier = identifier;

	}

	public void setName(final String name) {
		this.name = name;

	}

	public void setQualifiedName(final String qualifiedName) {
		this.qualifiedName = qualifiedName;

	}

	public void setAliasId(final Set<String> aliasId) {
		this.aliasId.clear();
		if (aliasId != null) {
			this.aliasId.addAll(aliasId);
		}

	}

	public void setHumanId(final String humanId) {
		this.humanId = humanId;

	}

	public void setOwningMembership(final Link owningMembership) {
		this.owningMembership = owningMembership;

	}

	public void setOwnedRelationship_comp(final Set<Link> ownedRelationship_comp) {
		this.ownedRelationship_comp.clear();
		if (ownedRelationship_comp != null) {
			this.ownedRelationship_comp.addAll(ownedRelationship_comp);
		}

	}

	public void setOwningRelationship(final Link owningRelationship) {
		this.owningRelationship = owningRelationship;

	}

	public void setOwningNamespace(final Link owningNamespace) {
		this.owningNamespace = owningNamespace;

	}

	public void setOwner(final Link owner) {
		this.owner = owner;

	}

	public void setOwnedElement(final Set<Link> ownedElement) {
		this.ownedElement.clear();
		if (ownedElement != null) {
			this.ownedElement.addAll(ownedElement);
		}

	}

	public void setDocumentation_comp(final Set<Link> documentation_comp) {
		this.documentation_comp.clear();
		if (documentation_comp != null) {
			this.documentation_comp.addAll(documentation_comp);
		}

	}

	public void setOwnedAnnotation_comp(final Set<Link> ownedAnnotation_comp) {
		this.ownedAnnotation_comp.clear();
		if (ownedAnnotation_comp != null) {
			this.ownedAnnotation_comp.addAll(ownedAnnotation_comp);
		}

	}

	public void setDocumentationComment(final Set<Link> documentationComment) {
		this.documentationComment.clear();
		if (documentationComment != null) {
			this.documentationComment.addAll(documentationComment);
		}

	}

	public void setOwnedTextualRepresentation(final Set<Link> ownedTextualRepresentation) {
		this.ownedTextualRepresentation.clear();
		if (ownedTextualRepresentation != null) {
			this.ownedTextualRepresentation.addAll(ownedTextualRepresentation);
		}

	}

	public void setOwnedRelationship(final Set<Link> ownedRelationship) {
		this.ownedRelationship.clear();
		if (ownedRelationship != null) {
			this.ownedRelationship.addAll(ownedRelationship);
		}

	}

	public void setDocumentation(final Set<Link> documentation) {
		this.documentation.clear();
		if (documentation != null) {
			this.documentation.addAll(documentation);
		}

	}

	public void setOwnedAnnotation(final Set<Link> ownedAnnotation) {
		this.ownedAnnotation.clear();
		if (ownedAnnotation != null) {
			this.ownedAnnotation.addAll(ownedAnnotation);
		}

	}

}
