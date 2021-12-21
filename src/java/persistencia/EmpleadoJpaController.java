/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Empleado;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Anto_PaizLoker
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EmpleadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("TpIntegradorPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getLista_ventas_realizadas() == null) {
            empleado.setLista_ventas_realizadas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedLista_ventas_realizadas = new ArrayList<Venta>();
            for (Venta lista_ventas_realizadasVentaToAttach : empleado.getLista_ventas_realizadas()) {
                lista_ventas_realizadasVentaToAttach = em.getReference(lista_ventas_realizadasVentaToAttach.getClass(), lista_ventas_realizadasVentaToAttach.getNum_venta());
                attachedLista_ventas_realizadas.add(lista_ventas_realizadasVentaToAttach);
            }
            empleado.setLista_ventas_realizadas(attachedLista_ventas_realizadas);
            em.persist(empleado);
            for (Venta lista_ventas_realizadasVenta : empleado.getLista_ventas_realizadas()) {
                Empleado oldUn_empleadoOfLista_ventas_realizadasVenta = lista_ventas_realizadasVenta.getUn_empleado();
                lista_ventas_realizadasVenta.setUn_empleado(empleado);
                lista_ventas_realizadasVenta = em.merge(lista_ventas_realizadasVenta);
                if (oldUn_empleadoOfLista_ventas_realizadasVenta != null) {
                    oldUn_empleadoOfLista_ventas_realizadasVenta.getLista_ventas_realizadas().remove(lista_ventas_realizadasVenta);
                    oldUn_empleadoOfLista_ventas_realizadasVenta = em.merge(oldUn_empleadoOfLista_ventas_realizadasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getId_persona());
            List<Venta> lista_ventas_realizadasOld = persistentEmpleado.getLista_ventas_realizadas();
            List<Venta> lista_ventas_realizadasNew = empleado.getLista_ventas_realizadas();
            List<Venta> attachedLista_ventas_realizadasNew = new ArrayList<Venta>();
            for (Venta lista_ventas_realizadasNewVentaToAttach : lista_ventas_realizadasNew) {
                lista_ventas_realizadasNewVentaToAttach = em.getReference(lista_ventas_realizadasNewVentaToAttach.getClass(), lista_ventas_realizadasNewVentaToAttach.getNum_venta());
                attachedLista_ventas_realizadasNew.add(lista_ventas_realizadasNewVentaToAttach);
            }
            lista_ventas_realizadasNew = attachedLista_ventas_realizadasNew;
            empleado.setLista_ventas_realizadas(lista_ventas_realizadasNew);
            empleado = em.merge(empleado);
            for (Venta lista_ventas_realizadasOldVenta : lista_ventas_realizadasOld) {
                if (!lista_ventas_realizadasNew.contains(lista_ventas_realizadasOldVenta)) {
                    lista_ventas_realizadasOldVenta.setUn_empleado(null);
                    lista_ventas_realizadasOldVenta = em.merge(lista_ventas_realizadasOldVenta);
                }
            }
            for (Venta lista_ventas_realizadasNewVenta : lista_ventas_realizadasNew) {
                if (!lista_ventas_realizadasOld.contains(lista_ventas_realizadasNewVenta)) {
                    Empleado oldUn_empleadoOfLista_ventas_realizadasNewVenta = lista_ventas_realizadasNewVenta.getUn_empleado();
                    lista_ventas_realizadasNewVenta.setUn_empleado(empleado);
                    lista_ventas_realizadasNewVenta = em.merge(lista_ventas_realizadasNewVenta);
                    if (oldUn_empleadoOfLista_ventas_realizadasNewVenta != null && !oldUn_empleadoOfLista_ventas_realizadasNewVenta.equals(empleado)) {
                        oldUn_empleadoOfLista_ventas_realizadasNewVenta.getLista_ventas_realizadas().remove(lista_ventas_realizadasNewVenta);
                        oldUn_empleadoOfLista_ventas_realizadasNewVenta = em.merge(oldUn_empleadoOfLista_ventas_realizadasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = empleado.getId_persona();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId_persona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<Venta> lista_ventas_realizadas = empleado.getLista_ventas_realizadas();
            for (Venta lista_ventas_realizadasVenta : lista_ventas_realizadas) {
                lista_ventas_realizadasVenta.setUn_empleado(null);
                lista_ventas_realizadasVenta = em.merge(lista_ventas_realizadasVenta);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
