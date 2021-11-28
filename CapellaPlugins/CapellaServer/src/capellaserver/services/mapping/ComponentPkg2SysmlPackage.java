package capellaserver.services.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;

import capellaserver.domain.Element;
import capellaserver.domain.Link;
import capellaserver.domain.SysmlPackage;

public class ComponentPkg2SysmlPackage extends AbstractMapping implements IMapping {

	public ComponentPkg2SysmlPackage() {
		_source = LogicalComponentPkg.class;
		_target = SysmlPackage.class;
	}
	
	@Override
	public Element map(EObject source, String linkBaseUrl) {
		if(!(source instanceof LogicalComponentPkg)) {
			String errorMessage = "Cannot map argument of type " + source.getClass().getName() + " to type SysmlPackage";
			throw new IllegalArgumentException(errorMessage);
		}
		LogicalComponentPkg capellaElement = 
				(LogicalComponentPkg) source;
		SysmlPackage target = new SysmlPackage();
		for(CapellaElement component : capellaElement.getOwnedLogicalComponents()) {
			target.addOwnedMember(new Link(createURI(linkBaseUrl + component.getId())));
		}
		for(CapellaElement pkg : capellaElement.getOwnedLogicalComponentPkgs()) {
			target.addMember(new Link(createURI(linkBaseUrl + pkg.getId())));
		}
		for(CapellaElement ex : capellaElement.getOwnedComponentExchanges()) {
			target.addOwnedRelationship(new Link(createURI(linkBaseUrl + ex.getId())));
		}
		
		
		target.setIdentifier(capellaElement.getId());
		target.setSysmlIdentifier(capellaElement.getId());
		target.setName(capellaElement.getName());
		target.setDescription(capellaElement.getDescription());
		target.setTitle(capellaElement.getFullLabel());
		target.setShortTitle(capellaElement.getLabel());
		target.setAbout(createURI(linkBaseUrl + capellaElement.getId()));
		setOwnerIfPresent(source, target, linkBaseUrl);
		addAllCapellaTypes(target,capellaElement.getClass());
		return target;
	}

}
