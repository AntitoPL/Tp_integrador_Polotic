package logica;

import java.util.Date;
import java.util.List;
import persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
////////////////////////////////////////////////////////////////////////////////
//--------------------------    EMPLEADO       -------------------------------//
////////////////////////////////////////////////////////////////////////////////
    public void crearEmpleado(String nombre, String apellido, String dni, String direccion, String celular, String email, Date fecha_nac, String nacionalidad, String cargo, double sueldo, String nombre_usuario, String pass) {

        Empleado empleado = new Empleado();
        Usuario usuario = new Usuario();

        //asigno valores de Usuario
        usuario.setNombre_usuario(nombre_usuario);
        usuario.setPass(pass);

        //asigno valores de Persona
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setDni(dni);
        empleado.setDireccion(direccion);
        empleado.setCelular(celular);
        empleado.setEmail(email);
        empleado.setFecha_nac(fecha_nac);
        empleado.setNacionalidad(nacionalidad);

        //asigno valores de Empleado
        empleado.setCargo(cargo);
        empleado.setSueldo(sueldo);
        empleado.setUsuario(usuario);

        controlPersis.crearEmpleado(empleado, usuario);
    }

    public List<Empleado> listarEmpleado() {

        return controlPersis.listarEmpleados();

    }
    
    public void borrarEmpleado(int id) {
        
        controlPersis.borrarEmpleado(id);
        
    }
    
     public Empleado buscarEmpleado(int id) {
        
        return controlPersis.buscarEmpleado(id);
        
    }

    public void modificarEmpleado(int id_empleado, String nombre, String apellido, String dni, String direccion, String celular, String email, Date fecha_nac, String nacionalidad, String cargo, double sueldo, String nombre_usuario, String pass, int id_usuario) {
        Empleado empleado = controlPersis.buscarEmpleado(id_empleado);
        Usuario usuario = controlPersis.buscarUsuario(id_usuario);
        
        //asigno valores de Usuario
        usuario.setNombre_usuario(nombre_usuario);
        usuario.setPass(pass);

        //asigno valores de Persona
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setDni(dni);
        empleado.setDireccion(direccion);
        empleado.setCelular(celular);
        empleado.setEmail(email);
        empleado.setFecha_nac(fecha_nac);
        empleado.setNacionalidad(nacionalidad);

        //asigno valores de Empleado
        empleado.setCargo(cargo);
        empleado.setSueldo(sueldo);
        empleado.setUsuario(usuario);
        
        controlPersis.modificarEmpleado(empleado,usuario);
    }
    
////////////////////////////////////////////////////////////////////////////////
//--------------------------    CLIENTE       -------------------------------//
////////////////////////////////////////////////////////////////////////////////
    public List<Cliente> listarClientes() {
        return controlPersis.listarClientes();
    }

    public void crearCliente(String nombre, String apellido, String dni, String direccion, String celular, String email, Date fecha_nac, String nacionalidad) {
        Cliente cliente = new Cliente();
        
        //asigno valores de Persona
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setDireccion(direccion);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        cliente.setFecha_nac(fecha_nac);
        cliente.setNacionalidad(nacionalidad);
        
        controlPersis.crearCliente(cliente);
    }

////////////////////////////////////////////////////////////////////////////////
//--------------------------    SERVICIO       -------------------------------//
////////////////////////////////////////////////////////////////////////////////
    public void crearServicio(String nombre, String descripcion, String destino, Date fecha_servicio, String tipo, double costo) {
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setDestino(destino);
        servicio.setFecha_servicio(fecha_servicio);
        servicio.setTipo(tipo);
        servicio.setCosto(costo);
        
        controlPersis.crearServicio(servicio);
    }

    public List<Servicio> listarServicio() {

        return controlPersis.listarServicio();

    }  


}
