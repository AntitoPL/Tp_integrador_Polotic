package logica;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Persona{
    
    @OneToMany
    List<Venta> lista_ventas;

    public Cliente(List<Venta> lista_ventas, int id_persona, String nombre, String apellido, String dni, String direccion, String celular, String email, Date fecha_nac, String nacionalidad) {
        super(id_persona, nombre, apellido, dni, direccion, celular, email, fecha_nac, nacionalidad);
        this.lista_ventas = lista_ventas;
    }

    public Cliente() {
    }

    public List<Venta> getLista_ventas() {
        return lista_ventas;
    }

    public void setLista_ventas(List<Venta> lista_ventas) {
        this.lista_ventas = lista_ventas;
    }
    
    public void agregarVenta(Venta una_venta){
        this.lista_ventas.add(una_venta);
    }
}
