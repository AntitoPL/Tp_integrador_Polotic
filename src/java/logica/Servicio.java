package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Servicio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo_servicio;
    @Basic
    private String nombre;
    private String descripcion;
    private String destino;
    private String tipo;
    @Temporal(TemporalType.DATE)
    private Date fecha_servicio;
    private double costo;
    
    @OneToMany
    private List<Venta> lista_venta_serv;
    
    @ManyToMany
    private List<Paquete> lista_paquetes;

    public Servicio(int codigo_servicio, String nombre, String descripcion, String destino, String tipo, Date fecha_servicio, double costo, List<Venta> lista_venta_serv, List<Paquete> lista_paquetes) {
        this.codigo_servicio = codigo_servicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destino = destino;
        this.tipo = tipo;
        this.fecha_servicio = fecha_servicio;
        this.costo = costo;
        this.lista_venta_serv = lista_venta_serv;
        this.lista_paquetes = lista_paquetes;
    }
    
    public Servicio() {
    }

    public int getCodigo_servicio() {
        return codigo_servicio;
    }

    public void setCodigo_servicio(int codigo_servicio) {
        this.codigo_servicio = codigo_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha_servicio() {
        return fecha_servicio;
    }

    public void setFecha_servicio(Date fecha_servicio) {
        this.fecha_servicio = fecha_servicio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Venta> getLista_venta_serv() {
        return lista_venta_serv;
    }

    public void setLista_venta_serv(List<Venta> lista_venta_serv) {
        this.lista_venta_serv = lista_venta_serv;
    }
    
    public void agregarVenta(Venta una_venta){
        this.lista_venta_serv.add(una_venta);
    }
    
    public List<Paquete> getLista_paquetes() {
        return lista_paquetes;
    }

    public void setLista_paquetes(List<Paquete> lista_paquetes) {
        this.lista_paquetes = lista_paquetes;
    }
        
    public void agregarPaquete(Paquete un_paquete){
        this.lista_paquetes.add(un_paquete);
    }
}
