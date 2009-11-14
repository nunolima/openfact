/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.FormasPagam;
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
public class FormasPagamJpaController {

    public FormasPagamJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormasPagam formasPagam) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formasPagam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormasPagam(formasPagam.getFormaPagam()) != null) {
                throw new PreexistingEntityException("FormasPagam " + formasPagam + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormasPagam formasPagam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formasPagam = em.merge(formasPagam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = formasPagam.getFormaPagam();
                if (findFormasPagam(id) == null) {
                    throw new NonexistentEntityException("The formasPagam with id " + id + " no longer exists.");
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
            FormasPagam formasPagam;
            try {
                formasPagam = em.getReference(FormasPagam.class, id);
                formasPagam.getFormaPagam();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formasPagam with id " + id + " no longer exists.", enfe);
            }
            em.remove(formasPagam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormasPagam> findFormasPagamEntities() {
        return findFormasPagamEntities(true, -1, -1);
    }

    public List<FormasPagam> findFormasPagamEntities(int maxResults, int firstResult) {
        return findFormasPagamEntities(false, maxResults, firstResult);
    }

    private List<FormasPagam> findFormasPagamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from FormasPagam as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FormasPagam findFormasPagam(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormasPagam.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormasPagamCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from FormasPagam as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
