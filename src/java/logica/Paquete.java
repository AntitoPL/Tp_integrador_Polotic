package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Paquete implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo_paquete;
    @Basic
    private double costo;
    @ManyToMany
    private List<Servicio> lista_servicios;
    @OneToMany
    private List<Venta> lista_venta_paquetes;

    public Paquete(int codigo_paquete, double costo, List<Servicio> lista_servicios, List<Venta> lista_ventas) {
        this.codigo_paquete = codigo_paquete;
        this.costo = costo;
        this.lista_servicios = lista_servicios;
        this.lista_venta_paquetes = lista_ventas;
    }

    public Paquete() {
    }

    public int getCodigo_paquete() {
        return codigo_paquete;
    }

    public void setCodigo_paquete(int codigo_paquete) {
        this.codigo_paquete = codigo_paquete;
    }

    public double getCosto() {
        return costo;
    }
    
    // calcular costo:
    public double calcularCosto(){
        double costo_paquete = 0;
        for(Servicio serv:lista_servicios){
            costo_paquete += serv.getCosto();
        }
        //se descuenta el 10% --> el costo es el 90% de la suma de los serv.
        return costo_paquete*0.9;
    }  
    
    // TO DO ver si ésto se mantiene o hay que cambiarlo y llamar al calcular
    //public void setCosto(double costo) {
        //this.costo = costo;
    public void setCosto() {
        this.costo = this.calcularCosto();
    }

    public List<Servicio> getLista_servicios() {
        return lista_servicios;
    }

    public void setLista_servicios(List<Servicio> lista_servicios) {
        this.lista_servicios = lista_servicios;
    }
    
    public void agregarServicio(Servicio un_servicio){
        this.lista_servicios.add(un_servicio);
    }

    public List<Venta> getLista_ventas() {
        return lista_venta_paquetes;
    }

    public void setLista_ventas(List<Venta> lista_ventas) {
        this.lista_venta_paquetes = lista_ventas;
    }
    
    public void agregarVenta(Venta una_venta){
        this.lista_venta_paquetes.add(una_venta);
    }
}
