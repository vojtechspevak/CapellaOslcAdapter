package capellaserver.services;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import capellaapi.provider.CapellaEmfElementsProvider;
import capellaapi.provider.ICapellaEmfElementsProvider;
import capellaserver.domain.ProjectInfo;

/**
 * Service responsible for getting the info about projects in Capella  
 */
public class ProjectService {
	
	/**
	 * retrieved the project names from the server and maps them to project info
	 * that contains the name encoded in URL safe version of Base64 as identifier 
	 * @return list of ProjectInfo elements, one for each project 
	 */
	public List<ProjectInfo> getProjects() {
		ICapellaEmfElementsProvider provider = new CapellaEmfElementsProvider();
		Base64.Encoder encoder = Base64.getUrlEncoder();
		return provider.getProjectNames()
				.stream()
				.map((pn) -> {return new ProjectInfo(pn,encoder.encodeToString(pn.getBytes()));})
				.collect(Collectors.toList());
	}
	
}
