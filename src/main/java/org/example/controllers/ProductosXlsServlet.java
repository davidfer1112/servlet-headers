package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Producto;
import org.example.services.ProductoService;
import org.example.services.ProductoServiceImp;

import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;

@WebServlet({"/productos.xls", "/productos"})
public class ProductosXlsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImp();
        List<Producto> productos = service.getAll();

        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");
        if (esXls){
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment; filename=productos.xls");
        }
        try(PrintWriter out = resp.getWriter()){

            if (!esXls){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Listado de productos</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Listado de productos</h1>");
                out.println("        <p><a href=\""+req.getContextPath()+"/productos.xls"+"\">exportar a xls</a></p>");
                out.println("        <p><a href=\""+req.getContextPath()+"/productos.json"+"\">exportar a json</a></p>");
            }

            out.println("        <table>");
            out.println("            <tr>");
            out.println("                <th>Id</th>");
            out.println("                <th>Nombre</th>");
            out.println("                <th>Tipo</th>");
            out.println("                <th>Precio</th>");
            out.println("            </tr>");
            productos.forEach(p ->{
                out.println("            <tr>");
                out.println("                <td>" + p.getId() + "</td>");
                out.println("                <td>" + p.getNombre() + "</td>");
                out.println("                <td>" + p.getTipo() + "</td>");
                out.println("                <td>" + p.getPrecio() + "</td>");
                out.println("            </tr>");
            });
            out.println("        </table>");
            if (!esXls){
                out.println("    </body>");
                out.println("</html>");
            }
        }

    }
}
