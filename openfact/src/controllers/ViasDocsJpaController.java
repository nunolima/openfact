/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.ViasDocs;
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
public class ViasDocsJpaController {

    public ViasDocsJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ViasDocs viasDocs) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(viasDocs);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findViasDocs(viasDocs.getIdVia()) != null) {
                throw new PreexistingEntityException("ViasDocs " + viasDocs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ViasDocs viasDocs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            viasDocs = em.merge(viasDocs);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = viasDocs.getIdVia();
                if (findViasDocs(id) == null) {
                    throw new NonexistentEntityException("The viasDocs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ViasDocs viasDocs;
            try {
                viasDocs = em.getReference(ViasDocs.class, id);
                viasDocs.getIdVia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The viasDocs with id " + id + " no longer exists.", enfe);
            }
            em.remove(viasDocs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ViasDocs> findViasDocsEntities() {
        return findViasDocsEntities(true, -1, -1);
    }

    public List<ViasDocs> findViasDocsEntities(int maxResults, int firstResult) {
        return findViasDocsEntities(false, maxResults, firstResult);
    }

    private List<ViasDocs> findViasDocsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ViasDocs as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ViasDocs findViasDocs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ViasDocs.class, id);
        } finally {
            em.close();
        }
    }

    public int getViasDocsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ViasDocs as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
