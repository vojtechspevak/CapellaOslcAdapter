package capellaserver.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// This is the representation of oslc_am resource
public class Resource {
	// TODO private Map<QName, Object> extendedProperties = new HashMap<QName,Object>(); ?

	private URI about;
	private Collection<URI> types = new ArrayList<URI>();
	private Set<Link> contributor = new HashSet<Link>();
	private Date created;
	private Set<Link> creator = new HashSet<Link>();
	private String description;
	private String identifier;
	private Date modified;
	private URI source;
	private String title;
	private Set<String> type = new HashSet<String>();
	private Set<Link> instanceShape = new HashSet<Link>();
	private Set<Link> serviceProvider = new HashSet<Link>();
	private String shortTitle;
	private Set<Link> external = new HashSet<Link>();
	private Set<Link> trace = new HashSet<Link>();
	private Set<Link> refine = new HashSet<Link>();
	private Set<Link> derives = new HashSet<Link>();
	private Set<Link> elaborates = new HashSet<Link>();
	private Set<Link> satisfy = new HashSet<Link>();

	public Resource(final URI about) {
		this.about = about;
	}

	public Resource() {
	}

	public final URI getAbout() {
		return about;
	}

	public final void setAbout(final URI about) {
		this.about = about;
	}

//	public void setExtendedProperties(final Map<QName, Object> properties)
//	{
//		this.extendedProperties = properties;
//	}

//	public Map<QName, Object> getExtendedProperties()
//	{
//		return extendedProperties;
//	}

	public Collection<URI> getTypes() {
		return types;
	}

	public void setTypes(final Collection<URI> type) {
		this.types = type;
	}

	public void addType(final URI type) {
		this.types.add(type);
	}

	public void addContributor(final Link contributor) {
		this.contributor.add(contributor);
	}

	public void addCreator(final Link creator) {
		this.creator.add(creator);
	}

	public void addType(final String type) {
		this.type.add(type);
	}

	public void addInstanceShape(final Link instanceShape) {
		this.instanceShape.add(instanceShape);
	}

	public void addServiceProvider(final Link serviceProvider) {
		this.serviceProvider.add(serviceProvider);
	}

	public void addExternal(final Link external) {
		this.external.add(external);
	}

	public void addTrace(final Link trace) {
		this.trace.add(trace);
	}

	public void addRefine(final Link refine) {
		this.refine.add(refine);
	}

	public void addDerives(final Link derives) {
		this.derives.add(derives);
	}

	public void addElaborates(final Link elaborates) {
		this.elaborates.add(elaborates);
	}

	public void addSatisfy(final Link satisfy) {
		this.satisfy.add(satisfy);
	}

	public Set<Link> getContributor() {
		return contributor;
	}

	public Date getCreated() {
		return created;
	}

	public Set<Link> getCreator() {
		return creator;
	}

	public String getDescription() {
		return description;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Date getModified() {
		return modified;
	}

	public URI getSource() {
		return source;
	}

	public String getTitle() {
		return title;
	}

	public Set<String> getType() {
		return type;
	}

	public Set<Link> getInstanceShape() {
		return instanceShape;
	}

	public Set<Link> getServiceProvider() {
		return serviceProvider;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public Set<Link> getExternal() {
		return external;
	}

	public Set<Link> getTrace() {
		return trace;
	}

	public Set<Link> getRefine() {
		return refine;
	}

	public Set<Link> getDerives() {
		return derives;
	}

	public Set<Link> getElaborates() {
		return elaborates;
	}

	public Set<Link> getSatisfy() {
		return satisfy;
	}

	public void setContributor(final Set<Link> contributor) {
		this.contributor.clear();
		if (contributor != null) {
			this.contributor.addAll(contributor);
		}

	}

	public void setCreated(final Date created) {
		this.created = created;

	}

	public void setCreator(final Set<Link> creator) {
		this.creator.clear();
		if (creator != null) {
			this.creator.addAll(creator);
		}

	}

	public void setDescription(final String description) {
		this.description = description;

	}

	public void setIdentifier(final String identifier) {
		this.identifier = identifier;

	}

	public void setModified(final Date modified) {
		this.modified = modified;

	}

	public void setSource(final URI source) {
		this.source = source;

	}

	public void setTitle(final String title) {
		this.title = title;

	}

	public void setType(final Set<String> type) {
		this.type.clear();
		if (type != null) {
			this.type.addAll(type);
		}

	}

	public void setInstanceShape(final Set<Link> instanceShape) {
		this.instanceShape.clear();
		if (instanceShape != null) {
			this.instanceShape.addAll(instanceShape);
		}

	}

	public void setServiceProvider(final Set<Link> serviceProvider) {
		this.serviceProvider.clear();
		if (serviceProvider != null) {
			this.serviceProvider.addAll(serviceProvider);
		}

	}

	public void setShortTitle(final String shortTitle) {
		this.shortTitle = shortTitle;

	}

	public void setExternal(final Set<Link> external) {
		this.external.clear();
		if (external != null) {
			this.external.addAll(external);
		}

	}

	public void setTrace(final Set<Link> trace) {
		this.trace.clear();
		if (trace != null) {
			this.trace.addAll(trace);
		}

	}

	public void setRefine(final Set<Link> refine) {
		this.refine.clear();
		if (refine != null) {
			this.refine.addAll(refine);
		}

	}

	public void setDerives(final Set<Link> derives) {
		this.derives.clear();
		if (derives != null) {
			this.derives.addAll(derives);
		}

	}

	public void setElaborates(final Set<Link> elaborates) {
		this.elaborates.clear();
		if (elaborates != null) {
			this.elaborates.addAll(elaborates);
		}

	}

	public void setSatisfy(final Set<Link> satisfy) {
		this.satisfy.clear();
		if (satisfy != null) {
			this.satisfy.addAll(satisfy);
		}

	}

}
