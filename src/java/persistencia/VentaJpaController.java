/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Cliente;
import logica.Servicio;
import logica.Paquete;
import logica.Empleado;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Anto_PaizLoker
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public VentaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("TpIntegradorPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente un_cliente = venta.getUn_cliente();
            if (un_cliente != null) {
                un_cliente = em.getReference(un_cliente.getClass(), un_cliente.getId_persona());
                venta.setUn_cliente(un_cliente);
            }
            Servicio un_servicio = venta.getUn_servicio();
            if (un_servicio != null) {
                un_servicio = em.getReference(un_servicio.getClass(), un_servicio.getCodigo_servicio());
                venta.setUn_servicio(un_servicio);
            }
            Paquete un_paquete = venta.getUn_paquete();
            if (un_paquete != null) {
                un_paquete = em.getReference(un_paquete.getClass(), un_paquete.getCodigo_paquete());
                venta.setUn_paquete(un_paquete);
            }
            Empleado un_empleado = venta.getUn_empleado();
            if (un_empleado != null) {
                un_empleado = em.getReference(un_empleado.getClass(), un_empleado.getId_persona());
                venta.setUn_empleado(un_empleado);
            }
            em.persist(venta);
            if (un_cliente != null) {
                un_cliente.getLista_ventas().add(venta);
                un_cliente = em.merge(un_cliente);
            }
            if (un_servicio != null) {
                un_servicio.getLista_venta_serv().add(venta);
                un_servicio = em.merge(un_servicio);
            }
            if (un_paquete != null) {
                un_paquete.getLista_ventas().add(venta);
                un_paquete = em.merge(un_paquete);
            }
            if (un_empleado != null) {
                un_empleado.getLista_ventas_realizadas().add(venta);
                un_empleado = em.merge(un_empleado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getNum_venta());
            Cliente un_clienteOld = persistentVenta.getUn_cliente();
            Cliente un_clienteNew = venta.getUn_cliente();
            Servicio un_servicioOld = persistentVenta.getUn_servicio();
            Servicio un_servicioNew = venta.getUn_servicio();
            Paquete un_paqueteOld = persistentVenta.getUn_paquete();
            Paquete un_paqueteNew = venta.getUn_paquete();
            Empleado un_empleadoOld = persistentVenta.getUn_empleado();
            Empleado un_empleadoNew = venta.getUn_empleado();
            if (un_clienteNew != null) {
                un_clienteNew = em.getReference(un_clienteNew.getClass(), un_clienteNew.getId_persona());
                venta.setUn_cliente(un_clienteNew);
            }
            if (un_servicioNew != null) {
                un_servicioNew = em.getReference(un_servicioNew.getClass(), un_servicioNew.getCodigo_servicio());
                venta.setUn_servicio(un_servicioNew);
            }
            if (un_paqueteNew != null) {
                un_paqueteNew = em.getReference(un_paqueteNew.getClass(), un_paqueteNew.getCodigo_paquete());
                venta.setUn_paquete(un_paqueteNew);
            }
            if (un_empleadoNew != null) {
                un_empleadoNew = em.getReference(un_empleadoNew.getClass(), un_empleadoNew.getId_persona());
                venta.setUn_empleado(un_empleadoNew);
            }
            venta = em.merge(venta);
            if (un_clienteOld != null && !un_clienteOld.equals(un_clienteNew)) {
                un_clienteOld.getLista_ventas().remove(venta);
                un_clienteOld = em.merge(un_clienteOld);
            }
            if (un_clienteNew != null && !un_clienteNew.equals(un_clienteOld)) {
                un_clienteNew.getLista_ventas().add(venta);
                un_clienteNew = em.merge(un_clienteNew);
            }
            if (un_servicioOld != null && !un_servicioOld.equals(un_servicioNew)) {
                un_servicioOld.getLista_venta_serv().remove(venta);
                un_servicioOld = em.merge(un_servicioOld);
            }
            if (un_servicioNew != null && !un_servicioNew.equals(un_servicioOld)) {
                un_servicioNew.getLista_venta_serv().add(venta);
                un_servicioNew = em.merge(un_servicioNew);
            }
            if (un_paqueteOld != null && !un_paqueteOld.equals(un_paqueteNew)) {
                un_paqueteOld.getLista_ventas().remove(venta);
                un_paqueteOld = em.merge(un_paqueteOld);
            }
            if (un_paqueteNew != null && !un_paqueteNew.equals(un_paqueteOld)) {
                un_paqueteNew.getLista_ventas().add(venta);
                un_paqueteNew = em.merge(un_paqueteNew);
            }
            if (un_empleadoOld != null && !un_empleadoOld.equals(un_empleadoNew)) {
                un_empleadoOld.getLista_ventas_realizadas().remove(venta);
                un_empleadoOld = em.merge(un_empleadoOld);
            }
            if (un_empleadoNew != null && !un_empleadoNew.equals(un_empleadoOld)) {
                un_empleadoNew.getLista_ventas_realizadas().add(venta);
                un_empleadoNew = em.merge(un_empleadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = venta.getNum_venta();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getNum_venta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Cliente un_cliente = venta.getUn_cliente();
            if (un_cliente != null) {
                un_cliente.getLista_ventas().remove(venta);
                un_cliente = em.merge(un_cliente);
            }
            Servicio un_servicio = venta.getUn_servicio();
            if (un_servicio != null) {
                un_servicio.getLista_venta_serv().remove(venta);
                un_servicio = em.merge(un_servicio);
            }
            Paquete un_paquete = venta.getUn_paquete();
            if (un_paquete != null) {
                un_paquete.getLista_ventas().remove(venta);
                un_paquete = em.merge(un_paquete);
            }
            Empleado un_empleado = venta.getUn_empleado();
            if (un_empleado != null) {
                un_empleado.getLista_ventas_realizadas().remove(venta);
                un_empleado = em.merge(un_empleado);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Venta findVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
