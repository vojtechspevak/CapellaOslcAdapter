package capellaserver.mapping;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;

import capellaserver.domain.Element;
import capellaserver.domain.Link;
import capellaserver.domain.SysmlPackage;

public class ComponentPkg2SysmlPackage extends AbstractMapping {

	public ComponentPkg2SysmlPackage() {
		_source = LogicalComponentPkg.class;
		_target = SysmlPackage.class;
	}
	
	@Override
	public Element map(EObject source, String linkBaseUrl) {
		checkIfSourceHasCorrectType(source);
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
		addAllSysmlTypes(target,getTargetClass());
		return target;
	}

}
