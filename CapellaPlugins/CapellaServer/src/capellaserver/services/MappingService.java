package capellaserver.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

import capellaserver.domain.Link;
import capellaserver.domain.Resource;
import capellaserver.domain.Element;
import capellaserver.domain.SysmlClass;

public class MappingService {
	
	private final String _linkBaseUrl;
	public MappingService(String linkBaseUrl) {
		_linkBaseUrl = linkBaseUrl;
	}
	
	public List<Element> mapFromEObject(List<EObject> eObjects) {
		return eObjects
				.stream()
				.map(eo -> mapFromEObject(eo))
				.collect(Collectors.toList());
	} 

	
	public Element mapFromEObject(EObject eObject) { // TODO if possible let the supertypes map the rest(but then both types must have supertpyes mapped to each other?)
		if (eObject instanceof org.polarsys.capella.core.data.information.Class) {
			return class2SysmlClass((org.polarsys.capella.core.data.information.Class) eObject, new SysmlClass());
		}
		if (eObject instanceof Generalization) {
			return generalization2Generalization((Generalization) eObject, new capellaserver.domain.Generalization());
		}
		if (eObject instanceof Relationship) {
			return relationship2Relationship((Relationship) eObject, new capellaserver.domain.Relationship());
		}
		if (eObject instanceof NamedElement) {
			return namedElement2Element((NamedElement) eObject, new Element());
		}
		//TODO maybe throw or handle otherwise
		return null;
	} 

	private Element class2SysmlClass(org.polarsys.capella.core.data.information.Class capellaClass, SysmlClass sysmlClass) {
		for(Generalization gen : capellaClass.getOwnedGeneralizations()) {
			sysmlClass.addOwnedGeneralization(new Link(createURI(_linkBaseUrl + gen.getId())));
		}
		for(NamedElement feature : capellaClass.getFeatures()) {
			sysmlClass.addFeature(new Link(createURI(_linkBaseUrl + feature.getId())));
		}
		for(NamedElement ownedFeature : capellaClass.getOwnedFeatures()) {
			sysmlClass.addOwnedFeature(new Link(createURI(_linkBaseUrl + ownedFeature.getId())));
		}
		for(NamedElement ownedPropVal : capellaClass.getOwnedPropertyValues()) {
			sysmlClass.addOwnedMember(new Link(createURI(_linkBaseUrl + ownedPropVal.getId())));
		}
		for(NamedElement prop : capellaClass.getContainedProperties()) {
			sysmlClass.addMember(new Link(createURI(_linkBaseUrl + prop.getId())));
		}
		for(AbstractConstraint constraint : capellaClass.getConstraints()) {
			sysmlClass.addOwnedFeature(new Link(createURI(_linkBaseUrl + constraint.getId())));
		}
		
		sysmlClass.setIdentifier(capellaClass.getId());
		sysmlClass.setSysmlIdentifier(capellaClass.getId());
		sysmlClass.setName(capellaClass.getName());
		sysmlClass.setDescription(capellaClass.getDescription());
		sysmlClass.setTitle(capellaClass.getFullLabel());
		sysmlClass.setShortTitle(capellaClass.getLabel());
		sysmlClass.setIsAbstract(capellaClass.isAbstract());
		sysmlClass.setAbout(createURI(_linkBaseUrl + capellaClass.getId()));
		addAllCapellaTypes(sysmlClass,capellaClass.getClass());
		return sysmlClass;
	}

	
	private Element relationship2Relationship(Relationship capellaElement, capellaserver.domain.Relationship sysmlElement) {

		for(NamedElement feature : capellaElement.getFeatures()) {
			sysmlElement.addOwnedElement(new Link(createURI(_linkBaseUrl + feature.getId())));
		}
		for(NamedElement ownedPropVal : capellaElement.getOwnedPropertyValues()) {
			sysmlElement.addOwnedElement(new Link(createURI(_linkBaseUrl + ownedPropVal.getId())));
		}

		sysmlElement.addRelatedElement(new Link(createURI(_linkBaseUrl + capellaElement.getRealizedFlow())));
		sysmlElement.setIdentifier(capellaElement.getId());
		sysmlElement.setName(capellaElement.getLabel());
		sysmlElement.setDescription(capellaElement.getDescription());
		sysmlElement.setTitle(capellaElement.getFullLabel());
		sysmlElement.setShortTitle(capellaElement.getLabel());
		sysmlElement.setSysmlIdentifier(capellaElement.getId());
		sysmlElement.setAbout(createURI(_linkBaseUrl + capellaElement.getId()));
		addAllCapellaTypes(sysmlElement,capellaElement.getClass());
		return sysmlElement;
	}

	
	
	private Element generalization2Generalization(Generalization capellaGeneralization, capellaserver.domain.Generalization sysmlGeneralization) {

		for(NamedElement feature : capellaGeneralization.getFeatures()) {
			sysmlGeneralization.addOwnedElement(new Link(createURI(_linkBaseUrl + feature.getId())));
		}
		for(NamedElement ownedPropVal : capellaGeneralization.getOwnedPropertyValues()) {
			sysmlGeneralization.addOwnedElement(new Link(createURI(_linkBaseUrl + ownedPropVal.getId())));
		}

		sysmlGeneralization.addRelatedElement(new Link(createURI(_linkBaseUrl + capellaGeneralization.getRealizedFlow())));
		sysmlGeneralization.setGeneral(new Link(createURI(_linkBaseUrl + capellaGeneralization.getSuper().getId())));
		sysmlGeneralization.setSpecific(new Link(createURI(_linkBaseUrl + capellaGeneralization.getSub().getId())));
		sysmlGeneralization.setIdentifier(capellaGeneralization.getId());
		sysmlGeneralization.setName(capellaGeneralization.getLabel());
		sysmlGeneralization.setDescription(capellaGeneralization.getDescription());
		sysmlGeneralization.setTitle(capellaGeneralization.getFullLabel());
		sysmlGeneralization.setShortTitle(capellaGeneralization.getLabel());
		sysmlGeneralization.setSysmlIdentifier(capellaGeneralization.getId());
		sysmlGeneralization.setAbout(createURI(_linkBaseUrl + capellaGeneralization.getId()));
		addAllCapellaTypes(sysmlGeneralization,capellaGeneralization.getClass());
		return sysmlGeneralization;
	}
	
	
	
	private Element namedElement2Element(NamedElement capellaElement, Element sysmlElement) {
		// start generic test
		Set<Link> contentsSet = capellaElement.eContents()
			.stream()
			.filter(e -> (e instanceof NamedElement))
			.map(ne -> { try {
				return new Link(new URI(_linkBaseUrl +((NamedElement) ne).getId()), ((NamedElement) ne).getName());
			} catch (URISyntaxException ex) {
				ex.printStackTrace(); // TODO cleanup or use for each
			}
			return null;}).filter(ne -> (ne != null))
			.collect(Collectors.toSet());
		
		sysmlElement.setOwnedElement(contentsSet);
		if(capellaElement.eContainer() instanceof NamedElement) {
			NamedElement container = (NamedElement) capellaElement.eContainer();
			try {
				Link ownerLink = new Link(new URI(_linkBaseUrl + container.getId()), container.getName());
				sysmlElement.setOwner(ownerLink);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		
		try {
			sysmlElement.setAbout(new URI(_linkBaseUrl +capellaElement.getId()));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sysmlElement.setIdentifier(capellaElement.getId());

		sysmlElement.setSysmlIdentifier(capellaElement.getId());
		sysmlElement.setName(capellaElement.getName());
		sysmlElement.setDescription(capellaElement.getDescription());
		sysmlElement.setTitle(capellaElement.getFullLabel());
		sysmlElement.setShortTitle(capellaElement.getLabel());
		addAllCapellaTypes(sysmlElement,capellaElement.getClass());
		return sysmlElement;
	}
	
	private static URI createURI(String uriString) {

		try {
			return new URI(uriString);
		} catch (URISyntaxException e1) {
			return null;
		}
	}
	
	public static void addAllCapellaTypes(Element resultingSysmlElement, Class capellaElementClass) {
		resultingSysmlElement.addType(capellaElementClass.getSimpleName());
		capellaElementClass = capellaElementClass.getSuperclass();
	while(capellaElementClass!=null) {
		resultingSysmlElement.addType(capellaElementClass.getSimpleName());
		capellaElementClass = capellaElementClass.getSuperclass();
	}
}
	
}
