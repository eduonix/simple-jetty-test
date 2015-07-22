package com.eduonix.testserver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ubu on 19.07.15.
 */
public class FeedbackServlet extends HttpServlet {

    private static Integer cnt = 0;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>FeedbackServlet</h1><pre>");
        response.getWriter().println("requestURI=" + request.getRequestURI());
        response.getWriter().println("contextPath=" + request.getContextPath());
        response.getWriter().println("servletPath=" + request.getServletPath());
        response.getWriter().println("pathInfo=" + request.getPathInfo());
        response.getWriter().println("session=" + request.getSession(true).getId());

        String r=request.getParameter("resource");
        if (r!=null) {
            r = r.replace("&", "&and;");
            r = r.replace("<", "&lt;");
            r = r.replace(">", "&gt;");
            response.getWriter().println("resource(" + r + ")=" + getServletContext().getResource(r));
        }
        response.getWriter().println("authType=" + request.getAuthType());

        response.getWriter().println("Free=" + Runtime.getRuntime().freeMemory());
        response.getWriter().println("Max=" + Runtime.getRuntime().maxMemory());
        response.getWriter().println("Total=" + Runtime.getRuntime().totalMemory());
        response.getWriter().println("count=" + cnt);
        //noinspection SynchronizeOnNonFinalField
        synchronized (cnt) {
            cnt++;
        }
        response.getWriter().println("</pre>");
    }


}
