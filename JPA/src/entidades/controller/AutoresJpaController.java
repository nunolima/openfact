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
import entidades.AutoresLivros;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author PedrodeSousa
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
        if (autores.getAutoresLivrosCollection() == null) {
            autores.setAutoresLivrosCollection(new ArrayList<AutoresLivros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<AutoresLivros> attachedAutoresLivrosCollection = new ArrayList<AutoresLivros>();
            for (AutoresLivros autoresLivrosCollectionAutoresLivrosToAttach : autores.getAutoresLivrosCollection()) {
                autoresLivrosCollectionAutoresLivrosToAttach = em.getReference(autoresLivrosCollectionAutoresLivrosToAttach.getClass(), autoresLivrosCollectionAutoresLivrosToAttach.getId());
                attachedAutoresLivrosCollection.add(autoresLivrosCollectionAutoresLivrosToAttach);
            }
            autores.setAutoresLivrosCollection(attachedAutoresLivrosCollection);
            em.persist(autores);
            for (AutoresLivros autoresLivrosCollectionAutoresLivros : autores.getAutoresLivrosCollection()) {
                Autores oldAutorIdOfAutoresLivrosCollectionAutoresLivros = autoresLivrosCollectionAutoresLivros.getAutorId();
                autoresLivrosCollectionAutoresLivros.setAutorId(autores);
                autoresLivrosCollectionAutoresLivros = em.merge(autoresLivrosCollectionAutoresLivros);
                if (oldAutorIdOfAutoresLivrosCollectionAutoresLivros != null) {
                    oldAutorIdOfAutoresLivrosCollectionAutoresLivros.getAutoresLivrosCollection().remove(autoresLivrosCollectionAutoresLivros);
                    oldAutorIdOfAutoresLivrosCollectionAutoresLivros = em.merge(oldAutorIdOfAutoresLivrosCollectionAutoresLivros);
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
            Collection<AutoresLivros> autoresLivrosCollectionOld = persistentAutores.getAutoresLivrosCollection();
            Collection<AutoresLivros> autoresLivrosCollectionNew = autores.getAutoresLivrosCollection();
            Collection<AutoresLivros> attachedAutoresLivrosCollectionNew = new ArrayList<AutoresLivros>();
            for (AutoresLivros autoresLivrosCollectionNewAutoresLivrosToAttach : autoresLivrosCollectionNew) {
                autoresLivrosCollectionNewAutoresLivrosToAttach = em.getReference(autoresLivrosCollectionNewAutoresLivrosToAttach.getClass(), autoresLivrosCollectionNewAutoresLivrosToAttach.getId());
                attachedAutoresLivrosCollectionNew.add(autoresLivrosCollectionNewAutoresLivrosToAttach);
            }
            autoresLivrosCollectionNew = attachedAutoresLivrosCollectionNew;
            autores.setAutoresLivrosCollection(autoresLivrosCollectionNew);
            autores = em.merge(autores);
            for (AutoresLivros autoresLivrosCollectionOldAutoresLivros : autoresLivrosCollectionOld) {
                if (!autoresLivrosCollectionNew.contains(autoresLivrosCollectionOldAutoresLivros)) {
                    autoresLivrosCollectionOldAutoresLivros.setAutorId(null);
                    autoresLivrosCollectionOldAutoresLivros = em.merge(autoresLivrosCollectionOldAutoresLivros);
                }
            }
            for (AutoresLivros autoresLivrosCollectionNewAutoresLivros : autoresLivrosCollectionNew) {
                if (!autoresLivrosCollectionOld.contains(autoresLivrosCollectionNewAutoresLivros)) {
                    Autores oldAutorIdOfAutoresLivrosCollectionNewAutoresLivros = autoresLivrosCollectionNewAutoresLivros.getAutorId();
                    autoresLivrosCollectionNewAutoresLivros.setAutorId(autores);
                    autoresLivrosCollectionNewAutoresLivros = em.merge(autoresLivrosCollectionNewAutoresLivros);
                    if (oldAutorIdOfAutoresLivrosCollectionNewAutoresLivros != null && !oldAutorIdOfAutoresLivrosCollectionNewAutoresLivros.equals(autores)) {
                        oldAutorIdOfAutoresLivrosCollectionNewAutoresLivros.getAutoresLivrosCollection().remove(autoresLivrosCollectionNewAutoresLivros);
                        oldAutorIdOfAutoresLivrosCollectionNewAutoresLivros = em.merge(oldAutorIdOfAutoresLivrosCollectionNewAutoresLivros);
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
            Collection<AutoresLivros> autoresLivrosCollection = autores.getAutoresLivrosCollection();
            for (AutoresLivros autoresLivrosCollectionAutoresLivros : autoresLivrosCollection) {
                autoresLivrosCollectionAutoresLivros.setAutorId(null);
                autoresLivrosCollectionAutoresLivros = em.merge(autoresLivrosCollectionAutoresLivros);
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