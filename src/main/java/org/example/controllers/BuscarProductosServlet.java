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
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImp();
        String nombre = req.getParameter("producto");

        Optional <Producto> encontrado = service.getAll().stream().filter(p -> {
            if (nombre == null || nombre.isBlank()){
                return false;
            }
            return p.getNombre().contains(nombre);
        }).findFirst();

        if(encontrado.isPresent()){
            Producto producto = encontrado.get();
            resp.setContentType("text/html;charset=UTF-8");
            try(var out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Producto encontrado</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Producto encontrado</h1>");
                out.println("        <ul>");
                out.println("            <li>Id: " + encontrado.get().getId() + "</li>");
                out.println("            <li>Nombre: " + encontrado.get().getNombre() + "</li>");
                out.println("            <li>Tipo: " + encontrado.get().getTipo() + "</li>");
                out.println("            <li>Precio: " + encontrado.get().getPrecio() + "</li>");
                out.println("        </ul>");
                out.println(    "</body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado como: " + nombre);
        }

    }
}
