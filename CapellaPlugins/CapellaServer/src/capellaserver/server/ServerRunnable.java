package capellaserver.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServerRunnable implements Runnable {
    private int _port;
    public ServerRunnable(int port) {
       _port = port;
    }

    public void run() {
  	  runServer(_port);
    }
    
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
			System.out.println("joining the server from end");
			server.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    
 }
