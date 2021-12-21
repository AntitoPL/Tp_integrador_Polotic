package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Empleado;

@WebServlet(name = "SvModificarEmpleado", urlPatterns = {"/SvModificarEmpleado"})
public class SvModificarEmpleado extends HttpServlet {

    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
        
        int id_empleado = Integer.parseInt(request.getParameter("id_empleado"));
        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        
        control.modificarEmpleado(id_empleado,nombre,apellido,dni,direccion,celular,email,fecha_nac,nacionalidad,cargo,sueldo,nombre_usuario,pass,id_usuario);
        
        response.sendRedirect("empleados.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Empleado emple = control.buscarEmpleado(id);
        
        HttpSession miSession = request.getSession();
        miSession.setAttribute("empleado", emple);
        
        response.sendRedirect("modificar_empleado.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
