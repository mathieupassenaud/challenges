package fr.mathieupassenaud.busroutechallenge.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import fr.mathieupassenaud.busroutechallenge.controller.API;
import fr.mathieupassenaud.busroutechallenge.decoder.InputFileDecoder;

public class Main {
		public static void main(String[] args) throws Exception {
			
			
			new InputFileDecoder(args[0]);
			
	        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	        context.setContextPath("/");

	        Server jettyServer = new Server(8088);
	        jettyServer.setHandler(context);

	        ServletHolder jerseyServlet = context.addServlet(
	             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
	        jerseyServlet.setInitOrder(0);

	        // Tells the Jersey Servlet which REST service/class to load.
	        jerseyServlet.setInitParameter(
	           "jersey.config.server.provider.classnames",
	           API.class.getCanonicalName());

	        try {
	            jettyServer.start();
	            jettyServer.join();
	        } finally {
	            jettyServer.destroy();
	        } 
	}

}
