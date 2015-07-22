package com.eduonix.testserver;


import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by ubu on 19.07.15.
 */
public class ServerRunner {


    public static void main(String[] args)  {
        System.out.println("ServerRunner startSelf...");

        String host = "0.0.0.0";

        Server server = new Server();

        ServerConnector serverConnector = new ServerConnector(server);

        serverConnector.setHost(host);
        serverConnector.setPort(8080);
        serverConnector.setIdleTimeout(30000L);
        server.addConnector(serverConnector);

        final ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        server.setHandler(servletContextHandler);

        servletContextHandler.addServlet(org.eclipse.jetty.servlet.DefaultServlet.class, "/");
        servletContextHandler.addServlet(new ServletHolder(new FeedbackServlet()), "/dump/*");


        try {

            server.start();
            server.join();
        } catch (Exception e){
            System.out.println( "Failed on : " + e.getLocalizedMessage());
            System.exit(1);
        }

    }




}
