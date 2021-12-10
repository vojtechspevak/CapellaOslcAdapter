package capellaapi.projectmanager;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;

import capellaapi.helpers.ConstHelper;

public class ProjectResourceManager {

	/**
	 * Gets List of opened Capella project names from the workspace
	 * @return List of Capella project names
	 */
	public static List<String> getProjectNames() {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

		return Arrays.stream(projects).filter(p -> {
			try {
				return p.isOpen() && p.hasNature(ConstHelper.CAPELLA_NATURE);
			} catch (CoreException e) {
				return false;
			}
		}).map(p -> p.getName()).collect(java.util.stream.Collectors.toList());
	}


	/**
	 * Retrieves the EObject that conatins the Capella project EMF model. 
	 * @param projectName name of the proejct to retrieve
	 * @return EObject contataining the Capella project model
	 * @throws NoSuchElementException if project cannot be loaded
	 */
	public static EObject getSearchRoot(String projectName) {

		IProject[] worksapceProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		IProject project = Arrays
				.stream(worksapceProjects)
				.filter(p -> p.getName().equals(projectName))
				.findFirst()
				.orElse(null);

		if (project == null || !project.isAccessible()) {
			throw new NoSuchElementException("Project ".concat(projectName).concat(" could not be loaded."));
		}
	
		IResource resource;
		try {
			resource = Arrays.asList(project.members()).stream()
					.filter(r -> r.getName().endsWith(ConstHelper.CAPELLA_EXTENSION_CURRENT)).findFirst().orElse(null);

			if (resource == null) {
				// try to load the resource from the older Capella versions
				resource = Arrays.asList(project.members()).stream()
						.filter(r -> r.getName().endsWith(ConstHelper.CAPELLA_EXTENSION_FORMER)).findFirst().orElse(null);
			}

		} catch (CoreException e) {
			throw new NoSuchElementException("Project ".concat(projectName).concat(" could not be loaded."));
		}

		if (resource == null || !(resource instanceof IFile)) {
			throw new NoSuchElementException("Project ".concat(projectName).concat(" could not be loaded."));
		}

		String path = ((IFile) resource).getFullPath().toString();
		URI uri = URI.createPlatformResourceURI(path, true);
		EObject searchRoot;
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		Resource emfResource = resourceSet.getResource(uri, true);
		searchRoot = emfResource
				.getContents()
				.stream()
				.filter(t -> t instanceof Project)
				.findFirst()
				.orElse(null);
		
		if (searchRoot == null ) {
			throw new NoSuchElementException("Project ".concat(projectName).concat(" could not be loaded."));
		}

		return searchRoot;
	}

	/**
	 * This method can be later used to retrieve ExecutionManager instance from
	 * project which is needed in order to edit the elements (so far it is unused in
	 * the Api)
	 * 
	 * @param project
	 * @return Execution manager
	 */
	private ExecutionManager loadExecutionManager(IProject project) {
		try {
			IResource airdResource = Arrays.asList(project.members()).stream()
					.filter(r -> r.getName().endsWith(ConstHelper.SIRIUS_DATA_FILE_EXTENSION)).findFirst().get();
			String airdPath = ((IFile) airdResource).getFullPath().toString();
			URI airdUri = URI.createPlatformResourceURI(airdPath, true);

			Session session = SessionManager.INSTANCE.getExistingSession(airdUri);
			return TransactionHelper.getExecutionManager(session);

		} catch (CoreException | NoSuchElementException e) {
			return null;
		}
	}

}
