package capellaserver.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import capellaserver.server.generic.GenericResourceCollectionServlet;
import capellaserver.server.generic.GenericResourceServlet;

/**
 * class to be used to start the server in a separate thread so, the UI one is not blocked
 */
public class ServerRunnable implements Runnable {
    private int _port;
    public ServerRunnable(int port) {
       _port = port;
    }

    public void run() {
  	  runServer(_port);
    }
    
    /**
     * method for starting the server that initializes all the servlets
     * once started, interrupt signal is awaited to stop the server
     * @param port number that specifies the port for starting the server
     */
	public void runServer(int port){
        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(ProjectServlet.class, "/projects");
        handler.addServletWithMapping(ElementServlet.class, "/element");
        handler.addServletWithMapping(SysmlClassServlet.class, "/sysmlclass");
        handler.addServletWithMapping(GeneralizationServlet.class, "/generalization");
        handler.addServletWithMapping(RelationshipServlet.class, "/relationship");
        handler.addServletWithMapping(GenericResourceCollectionServlet.class, "/api/resourcecollection");
        handler.addServletWithMapping(GenericResourceServlet.class, "/api/resource");
        handler.addServletWithMapping(SysmlPackageServlet.class, "/sysmlpackage");
        handler.addServletWithMapping(ConnectorServlet.class, "/connector");
        handler.addServletWithMapping(PortUsageServlet.class, "/portusage");
        handler.addServletWithMapping(ResourceServlet.class, "/*");
        try {
			server.start();
			System.out.println("server started");
			while(true) {
				if(Thread.interrupted()) {
			        try {
						System.out.println("stopping the server");
						server.stop();
						server.destroy();
					} catch (InterruptedException eex) {
						eex.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
			server.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    
 }
