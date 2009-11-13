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
import entidades.Livros;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author nunolima
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
            autores.setLivrosCollection(new ArrayList<Livros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Livros> attachedLivrosCollection = new ArrayList<Livros>();
            for (Livros livrosCollectionLivrosToAttach : autores.getLivrosCollection()) {
                livrosCollectionLivrosToAttach = em.getReference(livrosCollectionLivrosToAttach.getClass(), livrosCollectionLivrosToAttach.getId());
                attachedLivrosCollection.add(livrosCollectionLivrosToAttach);
            }
            autores.setLivrosCollection(attachedLivrosCollection);
            em.persist(autores);
//            for (Livros livrosCollectionLivros : autores.getLivrosCollection()) {
//                livrosCollectionLivros.getAutoresCollection().add(autores);
//                livrosCollectionLivros = em.merge(livrosCollectionLivros);
//            }
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
            Collection<Livros> livrosCollectionOld = persistentAutores.getLivrosCollection();
            Collection<Livros> livrosCollectionNew = autores.getLivrosCollection();
            Collection<Livros> attachedLivrosCollectionNew = new ArrayList<Livros>();
            for (Livros livrosCollectionNewLivrosToAttach : livrosCollectionNew) {
                livrosCollectionNewLivrosToAttach = em.getReference(livrosCollectionNewLivrosToAttach.getClass(), livrosCollectionNewLivrosToAttach.getId());
                attachedLivrosCollectionNew.add(livrosCollectionNewLivrosToAttach);
            }
            livrosCollectionNew = attachedLivrosCollectionNew;
            autores.setLivrosCollection(livrosCollectionNew);
            autores = em.merge(autores);
            for (Livros livrosCollectionOldLivros : livrosCollectionOld) {
                if (!livrosCollectionNew.contains(livrosCollectionOldLivros)) {
                    livrosCollectionOldLivros.getAutoresCollection().remove(autores);
                    livrosCollectionOldLivros = em.merge(livrosCollectionOldLivros);
                }
            }
            for (Livros livrosCollectionNewLivros : livrosCollectionNew) {
                if (!livrosCollectionOld.contains(livrosCollectionNewLivros)) {
                    livrosCollectionNewLivros.getAutoresCollection().add(autores);
                    livrosCollectionNewLivros = em.merge(livrosCollectionNewLivros);
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
            Collection<Livros> livrosCollection = autores.getLivrosCollection();
            for (Livros livrosCollectionLivros : livrosCollection) {
                livrosCollectionLivros.getAutoresCollection().remove(autores);
                livrosCollectionLivros = em.merge(livrosCollectionLivros);
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
