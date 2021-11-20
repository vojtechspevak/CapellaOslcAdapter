package capellaserver.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import capellaserver.domain.Link;

public class Relationship extends Element {
	private Set<Link> relatedElement = new HashSet<Link>();
	private Set<Link> target = new HashSet<Link>();
	private Set<Link> sysmlSource = new HashSet<Link>();
	private Link owningRelatedElement;
	private Set<Link> ownedRelatedElement_comp = new HashSet<Link>();
	private Set<Link> ownedRelatedElement = new HashSet<Link>();

	public Relationship() {
		super();

	}

	public Relationship(final URI about) {
		super(about);

	}

	public String toString() {
		return toString(false);
	}

	public String toString(boolean asLocalResource) {
		String result = "";

		if (asLocalResource) {
			result = result
					+ "{a Local Relationship Resource} - update Relationship.toString() to present resource as desired.";
		} else {
			result = String.valueOf(getAbout());
		}

		result = getShortTitle();

		return result;
	}

	public void addRelatedElement(final Link relatedElement) {
		this.relatedElement.add(relatedElement);
	}

	public void addTarget(final Link target) {
		this.target.add(target);
	}

	public void addSysmlSource(final Link source) {
		this.sysmlSource.add(source);
	}

	public void addOwnedRelatedElement_comp(final Link ownedRelatedElement_comp) {
		this.ownedRelatedElement_comp.add(ownedRelatedElement_comp);
	}

	public void addOwnedRelatedElement(final Link ownedRelatedElement) {
		this.ownedRelatedElement.add(ownedRelatedElement);
	}

	public Set<Link> getRelatedElement() {
		return relatedElement;
	}

	public Set<Link> getTarget() {
		return target;
	}

	public Set<Link> getSysmlSource() {
		return sysmlSource;
	}

	public Link getOwningRelatedElement() {
		return owningRelatedElement;
	}

	public Set<Link> getOwnedRelatedElement_comp() {
		return ownedRelatedElement_comp;
	}

	public Set<Link> getOwnedRelatedElement() {
		return ownedRelatedElement;
	}

	public void setRelatedElement(final Set<Link> relatedElement) {
		this.relatedElement.clear();
		if (relatedElement != null) {
			this.relatedElement.addAll(relatedElement);
		}

	}

	public void setTarget(final Set<Link> target) {
		this.target.clear();
		if (target != null) {
			this.target.addAll(target);
		}

	}

	public void setSysmlSource(final Set<Link> source) {
		this.sysmlSource.clear();
		if (source != null) {
			this.sysmlSource.addAll(source);
		}

	}

	public void setOwningRelatedElement(final Link owningRelatedElement) {
		this.owningRelatedElement = owningRelatedElement;

	}

	public void setOwnedRelatedElement_comp(final Set<Link> ownedRelatedElement_comp) {
		this.ownedRelatedElement_comp.clear();
		if (ownedRelatedElement_comp != null) {
			this.ownedRelatedElement_comp.addAll(ownedRelatedElement_comp);
		}

	}

	public void setOwnedRelatedElement(final Set<Link> ownedRelatedElement) {
		this.ownedRelatedElement.clear();
		if (ownedRelatedElement != null) {
			this.ownedRelatedElement.addAll(ownedRelatedElement);
		}

	}

}
