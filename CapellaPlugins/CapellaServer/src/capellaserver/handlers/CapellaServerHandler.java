package capellaserver.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import capellaserver.server.ServerRunnable;

public class CapellaServerHandler extends AbstractHandler {

	private static boolean _serverRuns = false;
	private static Thread _serverThread;
	private static final String DEFAULT_PORT = "3333";
	
	/**
	 * function handling the ui event
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		if(!_serverRuns) {
			return showStartServerOption(event,window);
		} else {
			return showStopServerOption(event,window);
		}
	}
	
	/**
	 * functionality for asking user whether the server should be started
	 * if so, server is started in separate thread 
	 * @param event
	 * @param window
	 * @throws ExecutionException 
	 */
	private Object showStartServerOption(ExecutionEvent event, IWorkbenchWindow window) throws ExecutionException {
		  InputDialog dlg = new InputDialog(
				  HandlerUtil.getActiveShellChecked(event), "Capella Server",
		  	      "Start Capella Server on the specified port:", DEFAULT_PORT, new IInputValidator() 
				    {
				        @Override
				        public String isValid(String newText) {
				        	if(isInputTextValidPortNumber(newText)) {
				        		return null;
				        	} else {
				        		return "Please insert a valid port number.";
				        	}
				        }
				    });
		  	if (dlg.open() != Window.OK) {
		  		return null;
		  	}
  		final int portNumber = Integer.parseInt(dlg.getValue().trim());
		_serverThread = new Thread(new ServerRunnable(portNumber));
		_serverThread.start();
		_serverRuns = true;
		String message = "Server is running on port " + portNumber + ".";
		MessageDialog.openInformation(
				window.getShell(),
				"Capella Server",
				message);
		return null;
	}

	/**
	 * functionality for asking user whether the server should be stoped
	 * if so, the running server is stopped
	 * @param window
	 * @return
	 */
	private Object showStopServerOption(ExecutionEvent event, IWorkbenchWindow window) {
		if(! MessageDialog.openQuestion(window.getShell(), "Capella Server", "Server is now running, do you want to stop it?")) {
			return null;
		}
		_serverThread.interrupt();
		_serverRuns = false;
		MessageDialog.openInformation(
				window.getShell(),
				"Embedded Server",
				"Server stopped.");
		return null;
	}

	/**
	 * Validation for the port number input for Embedded jetty
	 * @param text the port number as String
	 * @return true if the text input represents valid port number, false otherwise
	 */
	private boolean isInputTextValidPortNumber(String text) {
		text = text.trim();
		if (text.length() > 4 || text.length() < 1) {
			return false;
		}
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
