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

@WebServlet(name = "SvEmpleado", urlPatterns = {"/SvEmpleado"})
public class SvEmpleado extends HttpServlet {

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

        String nombre = request.getParameter("nombreEmpleado");
        String apellido = request.getParameter("apellidoEmpleado");
        String dni = request.getParameter("dniEmpleado");
        String direccion = request.getParameter("direccionEmpleado");
        String celular = request.getParameter("celEmpleado");
        String email = request.getParameter("emailEmpleado");
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_nac = null;
        try {
            fecha_nac = formato.parse(request.getParameter("fechaNacEmpleado"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String nacionalidad = request.getParameter("nacEmpleado");
        String cargo = request.getParameter("cargoEmpleado");
        double sueldo = Double.parseDouble(request.getParameter("sueldoEmpleado"));
        String nombre_usuario = request.getParameter("usuarioEmpleado");
        String pass = request.getParameter("passEmpleado");
        
        control.crearEmpleado(nombre,apellido,dni,direccion,celular,email,fecha_nac,nacionalidad,cargo,sueldo,nombre_usuario,pass);
        
        response.sendRedirect("index.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
