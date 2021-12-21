package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empleado extends Persona implements Serializable {
    
    @Basic
    private String cargo;
    private double sueldo;
    
    @OneToOne
    private Usuario usuario;
    
    @OneToMany
    List<Venta> lista_ventas_realizadas;

    public Empleado(String cargo, double sueldo, Usuario usuario, List<Venta> lista_ventas_realizadas, int id_persona, String nombre, String apellido, String dni, String direccion, String celular, String email, Date fecha_nac, String nacionalidad) {
        super(id_persona, nombre, apellido, dni, direccion, celular, email, fecha_nac, nacionalidad);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.usuario = usuario;
        this.lista_ventas_realizadas = lista_ventas_realizadas;
    }

    public Empleado() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public List<Venta> getLista_ventas_realizadas() {
        return lista_ventas_realizadas;
    }

    public void setLista_ventas_realizadas(List<Venta> lista_ventas_realizadas) {
        this.lista_ventas_realizadas = lista_ventas_realizadas;
    }

    public void agregarVenta(Venta una_venta){
        this.lista_ventas_realizadas.add(una_venta);
    }
    
}
