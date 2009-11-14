/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.ZonasIva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author User
 */
public class ZonasIvaJpaController {

    public ZonasIvaJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ZonasIva zonasIva) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(zonasIva);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findZonasIva(zonasIva.getZonaIva()) != null) {
                throw new PreexistingEntityException("ZonasIva " + zonasIva + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ZonasIva zonasIva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            zonasIva = em.merge(zonasIva);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = zonasIva.getZonaIva();
                if (findZonasIva(id) == null) {
                    throw new NonexistentEntityException("The zonasIva with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ZonasIva zonasIva;
            try {
                zonasIva = em.getReference(ZonasIva.class, id);
                zonasIva.getZonaIva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The zonasIva with id " + id + " no longer exists.", enfe);
            }
            em.remove(zonasIva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ZonasIva> findZonasIvaEntities() {
        return findZonasIvaEntities(true, -1, -1);
    }

    public List<ZonasIva> findZonasIvaEntities(int maxResults, int firstResult) {
        return findZonasIvaEntities(false, maxResults, firstResult);
    }

    private List<ZonasIva> findZonasIvaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ZonasIva as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ZonasIva findZonasIva(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ZonasIva.class, id);
        } finally {
            em.close();
        }
    }

    public int getZonasIvaCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ZonasIva as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
