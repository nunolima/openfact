/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.controller;

import entidades.Autores;
import entidades.controller.exceptions.NonexistentEntityException;
import entidades.controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.LivrosAutores;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class AutoresJpaController {

    public AutoresJpaController() {
        emf = Persistence.createEntityManagerFactory("JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autores autores) throws PreexistingEntityException, Exception {
        if (autores.getLivrosCollection() == null) {
            autores.setLivrosCollection(new ArrayList<LivrosAutores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<LivrosAutores> attachedLivrosCollection = new ArrayList<LivrosAutores>();
            for (LivrosAutores livrosCollectionLivrosAutoresToAttach : autores.getLivrosCollection()) {
                livrosCollectionLivrosAutoresToAttach = em.getReference(livrosCollectionLivrosAutoresToAttach.getClass(), livrosCollectionLivrosAutoresToAttach.getIdAutor());
                attachedLivrosCollection.add(livrosCollectionLivrosAutoresToAttach);
            }
            autores.setLivrosCollection(attachedLivrosCollection);
            em.persist(autores);
            for (LivrosAutores livrosCollectionLivrosAutores : autores.getLivrosCollection()) {
                Autores oldAutorOfLivrosCollectionLivrosAutores = livrosCollectionLivrosAutores.getAutor();
                livrosCollectionLivrosAutores.setAutor(autores);
                livrosCollectionLivrosAutores = em.merge(livrosCollectionLivrosAutores);
                if (oldAutorOfLivrosCollectionLivrosAutores != null) {
                    oldAutorOfLivrosCollectionLivrosAutores.getLivrosCollection().remove(livrosCollectionLivrosAutores);
                    oldAutorOfLivrosCollectionLivrosAutores = em.merge(oldAutorOfLivrosCollectionLivrosAutores);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutores(autores.getId()) != null) {
                throw new PreexistingEntityException("Autores " + autores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autores autores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autores persistentAutores = em.find(Autores.class, autores.getId());
            Collection<LivrosAutores> livrosCollectionOld = persistentAutores.getLivrosCollection();
            Collection<LivrosAutores> livrosCollectionNew = autores.getLivrosCollection();
            Collection<LivrosAutores> attachedLivrosCollectionNew = new ArrayList<LivrosAutores>();
            for (LivrosAutores livrosCollectionNewLivrosAutoresToAttach : livrosCollectionNew) {
                livrosCollectionNewLivrosAutoresToAttach = em.getReference(livrosCollectionNewLivrosAutoresToAttach.getClass(), livrosCollectionNewLivrosAutoresToAttach.getIdAutor());
                attachedLivrosCollectionNew.add(livrosCollectionNewLivrosAutoresToAttach);
            }
            livrosCollectionNew = attachedLivrosCollectionNew;
            autores.setLivrosCollection(livrosCollectionNew);
            autores = em.merge(autores);
            for (LivrosAutores livrosCollectionOldLivrosAutores : livrosCollectionOld) {
                if (!livrosCollectionNew.contains(livrosCollectionOldLivrosAutores)) {
                    livrosCollectionOldLivrosAutores.setAutor(null);
                    livrosCollectionOldLivrosAutores = em.merge(livrosCollectionOldLivrosAutores);
                }
            }
            for (LivrosAutores livrosCollectionNewLivrosAutores : livrosCollectionNew) {
                if (!livrosCollectionOld.contains(livrosCollectionNewLivrosAutores)) {
                    Autores oldAutorOfLivrosCollectionNewLivrosAutores = livrosCollectionNewLivrosAutores.getAutor();
                    livrosCollectionNewLivrosAutores.setAutor(autores);
                    livrosCollectionNewLivrosAutores = em.merge(livrosCollectionNewLivrosAutores);
                    if (oldAutorOfLivrosCollectionNewLivrosAutores != null && !oldAutorOfLivrosCollectionNewLivrosAutores.equals(autores)) {
                        oldAutorOfLivrosCollectionNewLivrosAutores.getLivrosCollection().remove(livrosCollectionNewLivrosAutores);
                        oldAutorOfLivrosCollectionNewLivrosAutores = em.merge(oldAutorOfLivrosCollectionNewLivrosAutores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = autores.getId();
                if (findAutores(id) == null) {
                    throw new NonexistentEntityException("The autores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autores autores;
            try {
                autores = em.getReference(Autores.class, id);
                autores.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autores with id " + id + " no longer exists.", enfe);
            }
            Collection<LivrosAutores> livrosCollection = autores.getLivrosCollection();
            for (LivrosAutores livrosCollectionLivrosAutores : livrosCollection) {
                livrosCollectionLivrosAutores.setAutor(null);
                livrosCollectionLivrosAutores = em.merge(livrosCollectionLivrosAutores);
            }
            em.remove(autores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autores> findAutoresEntities() {
        return findAutoresEntities(true, -1, -1);
    }

    public List<Autores> findAutoresEntities(int maxResults, int firstResult) {
        return findAutoresEntities(false, maxResults, firstResult);
    }

    private List<Autores> findAutoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Autores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Autores findAutores(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autores.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutoresCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Autores as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
