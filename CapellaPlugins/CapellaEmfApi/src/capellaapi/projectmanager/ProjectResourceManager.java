package capellaapi.projectmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;

import capellaapi.helpers.ConstHelper;



public class ProjectResourceManager {
	private List<EmfProjectRepresentation> projectResources = new ArrayList<EmfProjectRepresentation>();

	public List<String> getProjectNames() {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		return Arrays.stream(projects).filter(p -> {
			try {
				return p.hasNature(ConstHelper.CAPELLA_NATURE);
			} catch (CoreException e) {
				return false;
			}
		}).map(p -> p.getName()).collect(java.util.stream.Collectors.toList());
	}

	
	public EObject getSearchRoot(String projectName) {
		return getRepresentation(projectName).getSearchRoot();
	}

	
	private EmfProjectRepresentation getRepresentation(String projectName) {
		Optional<EmfProjectRepresentation> optionalRepresentation = 
				projectResources.stream()
				.filter(pr -> pr.getName().equals(projectName))
				.findFirst();
		
		if (optionalRepresentation.isPresent()) {
			return optionalRepresentation.get();
		}
		
		EmfProjectRepresentation representation = loadRepresentation(projectName);
		projectResources.add(representation);
		return representation;
	}

	
	private EmfProjectRepresentation loadRepresentation(String projectName) {

		IProject project = null;
		for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if(p.getName().equals(projectName)) {
				project = p;
				break;
			}
		}

		// TODO check if project exists, is opened and so on + proper error handling 
		IResource resource; 
		IResource airdResource; 
		try {
			resource = Arrays
					.asList(project.members())
					.stream()
					.filter(r -> r.getName().endsWith(ConstHelper.CAPELLA_EXTENSION_CURRENT)) 
					.findFirst()
					.orElse(null);

			if (resource == null) {
				// try to load the resource from the older capella versions
				resource = Arrays
						.asList(project.members())
						.stream()
						.filter(r -> r.getName().endsWith(ConstHelper.CAPELLA_EXTENSION_FORMER)) 
						.findFirst()
						.get();
			}
		
			
		} catch (CoreException | NoSuchElementException e) {
			// TODO Error Handling
			return null;
		}
		
		if (! (resource instanceof IFile)) {
			// TODO Error Handling
			return null;
		}

		//TODO if editation is needed add also the .aird file, session and execution manager
		String path = ((IFile)resource).getFullPath().toString();
		URI uri = URI.createPlatformResourceURI(path, true);
		EObject searchRoot;
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		Resource emfResource = resourceSet.getResource(uri, true);

		searchRoot = emfResource
				.getContents()
				.stream()
				.filter(t -> !(t instanceof DAnalysis)) // or has property predicate
				.findFirst()
				.get();
		
		return new EmfProjectRepresentation(searchRoot, projectName);
	}

	
	/**
	 * This method can be later used to retrieve ExecutionManager instance from project
	 * which is needed in order to edit the elements
	 * (so far it is unused in the Api) 
	 * @param project
	 * @return Exectuion manager
	 */
	private ExecutionManager loadExecutionManager(IProject project) {
		try {
			IResource  airdResource = Arrays
					.asList(project.members())
					.stream()
					.filter(r -> r.getName().endsWith(ConstHelper.SIRIUS_DATA_FILE_EXTENSION))
					.findFirst()
					.get();
			String airdPath = ((IFile)airdResource).getFullPath().toString();
			URI airdUri = URI.createPlatformResourceURI(airdPath, true);

			Session session = SessionManager.INSTANCE.getExistingSession(airdUri);
			return TransactionHelper.getExecutionManager(session);		
			
		} catch (CoreException | NoSuchElementException e) {
			return null;
		}
	}
	
}
