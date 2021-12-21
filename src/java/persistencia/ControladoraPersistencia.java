package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Cliente;
import logica.Empleado;
import logica.Servicio;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
    
    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();
    ClienteJpaController clienteJPA = new ClienteJpaController();
    ServicioJpaController servicioJPA = new ServicioJpaController();
    
////////////////////////////////////////////////////////////////////////////////
//--------------------------    EMPLEADO       -------------------------------//
////////////////////////////////////////////////////////////////////////////////
    public void crearEmpleado(Empleado empleado, Usuario usuario) {
        
        usuarioJPA.create(usuario);
        empleadoJPA.create(empleado);
        
    }
    
    public List<Empleado> listarEmpleados() {
        
        return empleadoJPA.findEmpleadoEntities();
        
    }
    
    public void borrarEmpleado(int id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Empleado buscarEmpleado(int id) {
        
        return empleadoJPA.findEmpleado(id);
        
    }

    public Usuario buscarUsuario(int id_usuario) {
        return usuarioJPA.findUsuario(id_usuario);
    }

    public void modificarEmpleado(Empleado empleado, Usuario usuario) {
        try {
            usuarioJPA.edit(usuario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            empleadoJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
////////////////////////////////////////////////////////////////////////////////
//--------------------------    CLIENTES       -------------------------------//
////////////////////////////////////////////////////////////////////////////////
    public List<Cliente> listarClientes() {
        
        return clienteJPA.findClienteEntities();
        
    }
    
    public void crearCliente(Cliente cliente) {
        
        clienteJPA.create(cliente);
        
    }

////////////////////////////////////////////////////////////////////////////////
//--------------------------    CLIENTES       -------------------------------//
////////////////////////////////////////////////////////////////////////////////


    public void crearServicio(Servicio servicio) {
        servicioJPA.create(servicio);
    }

    public List<Servicio> listarServicio() {
        return servicioJPA.findServicioEntities();
    }

    
}
