package capellaserver.services;

import java.util.List;
import java.util.stream.Collectors;

import capellaapi.provider.CapellaEmfElementsProvider;
import capellaapi.provider.ICapellaEmfElementsProvider;
import capellaserver.domain.ProjectInfo;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ProjectService {

	
	public List<ProjectInfo> getProjects() {
		ICapellaEmfElementsProvider provider = new CapellaEmfElementsProvider();
		// TODO handle spaces here if necessary
		return provider.getProjectNames()
				.stream()
				.map((pn) -> {return new ProjectInfo(pn,URLEncoder.encode(pn, StandardCharsets.UTF_8));})
				.collect(Collectors.toList());

	}
	
}
