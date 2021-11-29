package capellaserver.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class Namespace extends Element {
	private Set<Link> ownedMembership_comp = new HashSet<Link>();
	private Set<Link> ownedMember = new HashSet<Link>();
	private Set<Link> membership = new HashSet<Link>();
	private Set<Link> ownedImport_comp = new HashSet<Link>();
	private Set<Link> member = new HashSet<Link>();
	private Set<Link> importedMembership = new HashSet<Link>();
	private Set<Link> ownedMembership = new HashSet<Link>();
	private Set<Link> ownedImport = new HashSet<Link>();

	public Namespace() {
		super();

	}

	public Namespace(final URI about) {
		super(about);

	}

	public void addOwnedMembership_comp(final Link ownedMembership_comp) {
		this.ownedMembership_comp.add(ownedMembership_comp);
	}

	public void addOwnedMember(final Link ownedMember) {
		this.ownedMember.add(ownedMember);
	}

	public void addMembership(final Link membership) {
		this.membership.add(membership);
	}

	public void addOwnedImport_comp(final Link ownedImport_comp) {
		this.ownedImport_comp.add(ownedImport_comp);
	}

	public void addMember(final Link member) {
		this.member.add(member);
	}

	public void addImportedMembership(final Link importedMembership) {
		this.importedMembership.add(importedMembership);
	}

	public void addOwnedMembership(final Link ownedMembership) {
		this.ownedMembership.add(ownedMembership);
	}

	public void addOwnedImport(final Link ownedImport) {
		this.ownedImport.add(ownedImport);
	}

	public Set<Link> getOwnedMembership_comp() {
		return ownedMembership_comp;
	}

	public Set<Link> getOwnedMember() {
		return ownedMember;
	}

	public Set<Link> getMembership() {
		return membership;
	}

	public Set<Link> getOwnedImport_comp() {
		return ownedImport_comp;
	}

	public Set<Link> getMember() {
		return member;
	}

	public Set<Link> getImportedMembership() {
		return importedMembership;
	}

	public Set<Link> getOwnedMembership() {
		return ownedMembership;
	}

	public Set<Link> getOwnedImport() {
		return ownedImport;
	}

	public void setOwnedMembership_comp(final Set<Link> ownedMembership_comp) {
		this.ownedMembership_comp.clear();
		if (ownedMembership_comp != null) {
			this.ownedMembership_comp.addAll(ownedMembership_comp);
		}

	}

	public void setOwnedMember(final Set<Link> ownedMember) {
		this.ownedMember.clear();
		if (ownedMember != null) {
			this.ownedMember.addAll(ownedMember);
		}

	}

	public void setMembership(final Set<Link> membership) {
		this.membership.clear();
		if (membership != null) {
			this.membership.addAll(membership);
		}

	}

	public void setOwnedImport_comp(final Set<Link> ownedImport_comp) {
		this.ownedImport_comp.clear();
		if (ownedImport_comp != null) {
			this.ownedImport_comp.addAll(ownedImport_comp);
		}

	}

	public void setMember(final Set<Link> member) {
		this.member.clear();
		if (member != null) {
			this.member.addAll(member);
		}

	}

	public void setImportedMembership(final Set<Link> importedMembership) {
		this.importedMembership.clear();
		if (importedMembership != null) {
			this.importedMembership.addAll(importedMembership);
		}

	}

	public void setOwnedMembership(final Set<Link> ownedMembership) {
		this.ownedMembership.clear();
		if (ownedMembership != null) {
			this.ownedMembership.addAll(ownedMembership);
		}

	}

	public void setOwnedImport(final Set<Link> ownedImport) {
		this.ownedImport.clear();
		if (ownedImport != null) {
			this.ownedImport.addAll(ownedImport);
		}

	}

}
