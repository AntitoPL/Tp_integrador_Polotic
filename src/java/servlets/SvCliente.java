package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;

@WebServlet(name = "SvCliente", urlPatterns = {"/SvCliente"})
public class SvCliente extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombreCliente");
        String apellido = request.getParameter("apellidoCliente");
        String dni = request.getParameter("dniCliente");
        String direccion = request.getParameter("direccionCliente");
        String celular = request.getParameter("celCliente");
        String email = request.getParameter("emailCliente");

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_nac = null;
        try {
            fecha_nac = formato.parse(request.getParameter("fechaNacCliente"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        String nacionalidad = request.getParameter("nacCliente");

        control.crearCliente(nombre, apellido, dni, direccion, celular, email, fecha_nac, nacionalidad);

        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
