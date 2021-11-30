package capellaserver.services;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import capellaapi.provider.CapellaEmfElementsProvider;
import capellaapi.provider.ICapellaEmfElementsProvider;
import capellaserver.domain.ProjectInfo;

public class ProjectService {
	
	public List<ProjectInfo> getProjects() {
		ICapellaEmfElementsProvider provider = new CapellaEmfElementsProvider();
		Base64.Encoder encoder = Base64.getUrlEncoder();
		return provider.getProjectNames()
				.stream()
				.map((pn) -> {return new ProjectInfo(pn,encoder.encodeToString(pn.getBytes()));})
				.collect(Collectors.toList());
	}
	
}
