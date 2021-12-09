package capellaserver.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import capellaserver.domain.ProjectInfo;
import capellaserver.services.ProjectService;

public class ProjectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		 throws ServletException, IOException {
    	ProjectService projectService = new ProjectService();
    	List<ProjectInfo> projects = projectService.getProjects();

    	ServletHelper.setOkResponse(response, projects, "projects");
    }
    
    
    
}
