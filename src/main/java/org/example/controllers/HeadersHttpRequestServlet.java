package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/headers-request")
public class HeadersHttpRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String ip = req.getLocalAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme + "://" + host + contextPath + servletPath;
        String url2 = scheme + "://" + ip + ":" + port + contextPath + servletPath;

        try(PrintWriter out = resp.getWriter()){

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Headers HTTP Request</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Headers HTTP Request</h1>");
            out.println("        <ul>");
            out.println("            <li>Método HTTP: " + metodoHttp + "</li>");
            out.println("            <li>URI Request: " + requestUri + "</li>");
            out.println("            <li>URL Request: " + requestUrl + "</li>");
            out.println("            <li>Context Path: " + contextPath + "</li>");
            out.println("            <li>Servlet Path: " + servletPath + "</li>");
            out.println("            <li>IP Cliente: " + ipCliente + "</li>");
            out.println("            <li>IP: " + ip + "</li>");
            out.println("            <li>Port: " + port + "</li>");
            out.println("            <li>Scheme: " + scheme + "</li>");
            out.println("            <li>Host: " + host + "</li>");
            out.println("            <li>URL: " + url + "</li>");
            out.println("            <li>URL2: " + url2 + "</li>");

            Enumeration<String> headerNames = req.getHeaderNames();
            while(headerNames.hasMoreElements()){
                String header = headerNames.nextElement();
                out.println("<li>"+ header + ": " + req.getHeader(header) + "</li>");
            }
            out.println("        </ul>");
            out.println(    "</body>");
            out.println("</html>");
        }
    }
}
