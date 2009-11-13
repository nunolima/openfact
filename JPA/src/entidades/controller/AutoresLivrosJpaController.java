/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.controller;

import entidades.AutoresLivros;
import entidades.controller.exceptions.NonexistentEntityException;
import entidades.controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Autores;
import entidades.Livros;

/**
 *
 * @author PedrodeSousa
 */
public class AutoresLivrosJpaController {

    public AutoresLivrosJpaController() {
        emf = Persistence.createEntityManagerFactory("JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AutoresLivros autoresLivros) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autores autorId = autoresLivros.getAutorId();
            if (autorId != null) {
                autorId = em.getReference(autorId.getClass(), autorId.getId());
                autoresLivros.setAutorId(autorId);
            }
            Livros livroId = autoresLivros.getLivroId();
            if (livroId != null) {
                livroId = em.getReference(livroId.getClass(), livroId.getId());
                autoresLivros.setLivroId(livroId);
            }
            em.persist(autoresLivros);
            if (autorId != null) {
                autorId.getAutoresLivrosCollection().add(autoresLivros);
                autorId = em.merge(autorId);
            }
            if (livroId != null) {
                livroId.getAutoresLivrosCollection().add(autoresLivros);
                livroId = em.merge(livroId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutoresLivros(autoresLivros.getId()) != null) {
                throw new PreexistingEntityException("AutoresLivros " + autoresLivros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AutoresLivros autoresLivros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutoresLivros persistentAutoresLivros = em.find(AutoresLivros.class, autoresLivros.getId());
            Autores autorIdOld = persistentAutoresLivros.getAutorId();
            Autores autorIdNew = autoresLivros.getAutorId();
            Livros livroIdOld = persistentAutoresLivros.getLivroId();
            Livros livroIdNew = autoresLivros.getLivroId();
            if (autorIdNew != null) {
                autorIdNew = em.getReference(autorIdNew.getClass(), autorIdNew.getId());
                autoresLivros.setAutorId(autorIdNew);
            }
            if (livroIdNew != null) {
                livroIdNew = em.getReference(livroIdNew.getClass(), livroIdNew.getId());
                autoresLivros.setLivroId(livroIdNew);
            }
            autoresLivros = em.merge(autoresLivros);
            if (autorIdOld != null && !autorIdOld.equals(autorIdNew)) {
                autorIdOld.getAutoresLivrosCollection().remove(autoresLivros);
                autorIdOld = em.merge(autorIdOld);
            }
            if (autorIdNew != null && !autorIdNew.equals(autorIdOld)) {
                autorIdNew.getAutoresLivrosCollection().add(autoresLivros);
                autorIdNew = em.merge(autorIdNew);
            }
            if (livroIdOld != null && !livroIdOld.equals(livroIdNew)) {
                livroIdOld.getAutoresLivrosCollection().remove(autoresLivros);
                livroIdOld = em.merge(livroIdOld);
            }
            if (livroIdNew != null && !livroIdNew.equals(livroIdOld)) {
                livroIdNew.getAutoresLivrosCollection().add(autoresLivros);
                livroIdNew = em.merge(livroIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = autoresLivros.getId();
                if (findAutoresLivros(id) == null) {
                    throw new NonexistentEntityException("The autoresLivros with id " + id + " no longer exists.");
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
            AutoresLivros autoresLivros;
            try {
                autoresLivros = em.getReference(AutoresLivros.class, id);
                autoresLivros.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autoresLivros with id " + id + " no longer exists.", enfe);
            }
            Autores autorId = autoresLivros.getAutorId();
            if (autorId != null) {
                autorId.getAutoresLivrosCollection().remove(autoresLivros);
                autorId = em.merge(autorId);
            }
            Livros livroId = autoresLivros.getLivroId();
            if (livroId != null) {
                livroId.getAutoresLivrosCollection().remove(autoresLivros);
                livroId = em.merge(livroId);
            }
            em.remove(autoresLivros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AutoresLivros> findAutoresLivrosEntities() {
        return findAutoresLivrosEntities(true, -1, -1);
    }

    public List<AutoresLivros> findAutoresLivrosEntities(int maxResults, int firstResult) {
        return findAutoresLivrosEntities(false, maxResults, firstResult);
    }

    private List<AutoresLivros> findAutoresLivrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AutoresLivros as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AutoresLivros findAutoresLivros(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AutoresLivros.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutoresLivrosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from AutoresLivros as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
