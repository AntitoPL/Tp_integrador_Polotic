package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int num_venta;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date fecha_venta;
    private String medio_pago;

    @ManyToOne
    private Cliente un_cliente;
    @ManyToOne
    private Servicio un_servicio;
    @ManyToOne
    private Paquete un_paquete;
    @ManyToOne
    private Empleado un_empleado;

    public Venta(int num_venta, Date fecha_venta, String medio_pago, Cliente un_cliente, Servicio un_servicio, Paquete un_paquete, Empleado un_empleado) {
        this.num_venta = num_venta;
        this.fecha_venta = fecha_venta;
        this.medio_pago = medio_pago;
        this.un_cliente = un_cliente;
        this.un_servicio = un_servicio;
        this.un_paquete = un_paquete;
        this.un_empleado = un_empleado;
    }

    public Venta() {
    }

    public int getNum_venta() {
        return num_venta;
    }

    public void setNum_venta(int num_venta) {
        this.num_venta = num_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public Cliente getUn_cliente() {
        return un_cliente;
    }

    public void setUn_cliente(Cliente un_cliente) {
        this.un_cliente = un_cliente;
    }

    public Servicio getUn_servicio() {
        return un_servicio;
    }

    public void setUn_servicio(Servicio un_servicio) {
        this.un_servicio = un_servicio;
    }

    public Paquete getUn_paquete() {
        return un_paquete;
    }

    public void setUn_paquete(Paquete un_paquete) {
        this.un_paquete = un_paquete;
    }

    public Empleado getUn_empleado() {
        return un_empleado;
    }

    public void setUn_empleado(Empleado un_empleado) {
        this.un_empleado = un_empleado;
    }
    
}
