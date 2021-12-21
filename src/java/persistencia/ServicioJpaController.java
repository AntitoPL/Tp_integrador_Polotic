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
import logica.Paquete;
import logica.Servicio;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Anto_PaizLoker
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ServicioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("TpIntegradorPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getLista_venta_serv() == null) {
            servicio.setLista_venta_serv(new ArrayList<Venta>());
        }
        if (servicio.getLista_paquetes() == null) {
            servicio.setLista_paquetes(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedLista_venta_serv = new ArrayList<Venta>();
            for (Venta lista_venta_servVentaToAttach : servicio.getLista_venta_serv()) {
                lista_venta_servVentaToAttach = em.getReference(lista_venta_servVentaToAttach.getClass(), lista_venta_servVentaToAttach.getNum_venta());
                attachedLista_venta_serv.add(lista_venta_servVentaToAttach);
            }
            servicio.setLista_venta_serv(attachedLista_venta_serv);
            List<Paquete> attachedLista_paquetes = new ArrayList<Paquete>();
            for (Paquete lista_paquetesPaqueteToAttach : servicio.getLista_paquetes()) {
                lista_paquetesPaqueteToAttach = em.getReference(lista_paquetesPaqueteToAttach.getClass(), lista_paquetesPaqueteToAttach.getCodigo_paquete());
                attachedLista_paquetes.add(lista_paquetesPaqueteToAttach);
            }
            servicio.setLista_paquetes(attachedLista_paquetes);
            em.persist(servicio);
            for (Venta lista_venta_servVenta : servicio.getLista_venta_serv()) {
                Servicio oldUn_servicioOfLista_venta_servVenta = lista_venta_servVenta.getUn_servicio();
                lista_venta_servVenta.setUn_servicio(servicio);
                lista_venta_servVenta = em.merge(lista_venta_servVenta);
                if (oldUn_servicioOfLista_venta_servVenta != null) {
                    oldUn_servicioOfLista_venta_servVenta.getLista_venta_serv().remove(lista_venta_servVenta);
                    oldUn_servicioOfLista_venta_servVenta = em.merge(oldUn_servicioOfLista_venta_servVenta);
                }
            }
            for (Paquete lista_paquetesPaquete : servicio.getLista_paquetes()) {
                lista_paquetesPaquete.getLista_servicios().add(servicio);
                lista_paquetesPaquete = em.merge(lista_paquetesPaquete);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getCodigo_servicio());
            List<Venta> lista_venta_servOld = persistentServicio.getLista_venta_serv();
            List<Venta> lista_venta_servNew = servicio.getLista_venta_serv();
            List<Paquete> lista_paquetesOld = persistentServicio.getLista_paquetes();
            List<Paquete> lista_paquetesNew = servicio.getLista_paquetes();
            List<Venta> attachedLista_venta_servNew = new ArrayList<Venta>();
            for (Venta lista_venta_servNewVentaToAttach : lista_venta_servNew) {
                lista_venta_servNewVentaToAttach = em.getReference(lista_venta_servNewVentaToAttach.getClass(), lista_venta_servNewVentaToAttach.getNum_venta());
                attachedLista_venta_servNew.add(lista_venta_servNewVentaToAttach);
            }
            lista_venta_servNew = attachedLista_venta_servNew;
            servicio.setLista_venta_serv(lista_venta_servNew);
            List<Paquete> attachedLista_paquetesNew = new ArrayList<Paquete>();
            for (Paquete lista_paquetesNewPaqueteToAttach : lista_paquetesNew) {
                lista_paquetesNewPaqueteToAttach = em.getReference(lista_paquetesNewPaqueteToAttach.getClass(), lista_paquetesNewPaqueteToAttach.getCodigo_paquete());
                attachedLista_paquetesNew.add(lista_paquetesNewPaqueteToAttach);
            }
            lista_paquetesNew = attachedLista_paquetesNew;
            servicio.setLista_paquetes(lista_paquetesNew);
            servicio = em.merge(servicio);
            for (Venta lista_venta_servOldVenta : lista_venta_servOld) {
                if (!lista_venta_servNew.contains(lista_venta_servOldVenta)) {
                    lista_venta_servOldVenta.setUn_servicio(null);
                    lista_venta_servOldVenta = em.merge(lista_venta_servOldVenta);
                }
            }
            for (Venta lista_venta_servNewVenta : lista_venta_servNew) {
                if (!lista_venta_servOld.contains(lista_venta_servNewVenta)) {
                    Servicio oldUn_servicioOfLista_venta_servNewVenta = lista_venta_servNewVenta.getUn_servicio();
                    lista_venta_servNewVenta.setUn_servicio(servicio);
                    lista_venta_servNewVenta = em.merge(lista_venta_servNewVenta);
                    if (oldUn_servicioOfLista_venta_servNewVenta != null && !oldUn_servicioOfLista_venta_servNewVenta.equals(servicio)) {
                        oldUn_servicioOfLista_venta_servNewVenta.getLista_venta_serv().remove(lista_venta_servNewVenta);
                        oldUn_servicioOfLista_venta_servNewVenta = em.merge(oldUn_servicioOfLista_venta_servNewVenta);
                    }
                }
            }
            for (Paquete lista_paquetesOldPaquete : lista_paquetesOld) {
                if (!lista_paquetesNew.contains(lista_paquetesOldPaquete)) {
                    lista_paquetesOldPaquete.getLista_servicios().remove(servicio);
                    lista_paquetesOldPaquete = em.merge(lista_paquetesOldPaquete);
                }
            }
            for (Paquete lista_paquetesNewPaquete : lista_paquetesNew) {
                if (!lista_paquetesOld.contains(lista_paquetesNewPaquete)) {
                    lista_paquetesNewPaquete.getLista_servicios().add(servicio);
                    lista_paquetesNewPaquete = em.merge(lista_paquetesNewPaquete);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicio.getCodigo_servicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getCodigo_servicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Venta> lista_venta_serv = servicio.getLista_venta_serv();
            for (Venta lista_venta_servVenta : lista_venta_serv) {
                lista_venta_servVenta.setUn_servicio(null);
                lista_venta_servVenta = em.merge(lista_venta_servVenta);
            }
            List<Paquete> lista_paquetes = servicio.getLista_paquetes();
            for (Paquete lista_paquetesPaquete : lista_paquetes) {
                lista_paquetesPaquete.getLista_servicios().remove(servicio);
                lista_paquetesPaquete = em.merge(lista_paquetesPaquete);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
