/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.controller;

import entidades.Livros;
import entidades.controller.exceptions.NonexistentEntityException;
import entidades.controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Areas;
import entidades.Editoras;
import entidades.Autores;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author PedrodeSousa
 */
public class LivrosJpaController {

    public LivrosJpaController() {
        emf = Persistence.createEntityManagerFactory("JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Livros livros) throws PreexistingEntityException, Exception {
        if (livros.getAutoresCollection() == null) {
            livros.setAutoresCollection(new ArrayList<Autores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areas areaId = livros.getAreaId();
            if (areaId != null) {
                areaId = em.getReference(areaId.getClass(), areaId.getId());
                livros.setAreaId(areaId);
            }
            Editoras editoraId = livros.getEditoraId();
            if (editoraId != null) {
                editoraId = em.getReference(editoraId.getClass(), editoraId.getId());
                livros.setEditoraId(editoraId);
            }
            Collection<Autores> attachedAutoresCollection = new ArrayList<Autores>();
            for (Autores autoresCollectionAutoresToAttach : livros.getAutoresCollection()) {
                autoresCollectionAutoresToAttach = em.getReference(autoresCollectionAutoresToAttach.getClass(), autoresCollectionAutoresToAttach.getId());
                attachedAutoresCollection.add(autoresCollectionAutoresToAttach);
            }
            livros.setAutoresCollection(attachedAutoresCollection);
            em.persist(livros);
            if (areaId != null) {
                areaId.getLivrosCollection().add(livros);
                areaId = em.merge(areaId);
            }
            if (editoraId != null) {
                editoraId.getLivrosCollection().add(livros);
                editoraId = em.merge(editoraId);
            }
            for (Autores autoresCollectionAutores : livros.getAutoresCollection()) {
                autoresCollectionAutores.getLivrosCollection().add(livros);
                autoresCollectionAutores = em.merge(autoresCollectionAutores);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLivros(livros.getId()) != null) {
                throw new PreexistingEntityException("Livros " + livros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Livros livros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livros persistentLivros = em.find(Livros.class, livros.getId());
            Areas areaIdOld = persistentLivros.getAreaId();
            Areas areaIdNew = livros.getAreaId();
            Editoras editoraIdOld = persistentLivros.getEditoraId();
            Editoras editoraIdNew = livros.getEditoraId();
            Collection<Autores> autoresCollectionOld = persistentLivros.getAutoresCollection();
            Collection<Autores> autoresCollectionNew = livros.getAutoresCollection();
            if (areaIdNew != null) {
                areaIdNew = em.getReference(areaIdNew.getClass(), areaIdNew.getId());
                livros.setAreaId(areaIdNew);
            }
            if (editoraIdNew != null) {
                editoraIdNew = em.getReference(editoraIdNew.getClass(), editoraIdNew.getId());
                livros.setEditoraId(editoraIdNew);
            }
            Collection<Autores> attachedAutoresCollectionNew = new ArrayList<Autores>();
            for (Autores autoresCollectionNewAutoresToAttach : autoresCollectionNew) {
                autoresCollectionNewAutoresToAttach = em.getReference(autoresCollectionNewAutoresToAttach.getClass(), autoresCollectionNewAutoresToAttach.getId());
                attachedAutoresCollectionNew.add(autoresCollectionNewAutoresToAttach);
            }
            autoresCollectionNew = attachedAutoresCollectionNew;
            livros.setAutoresCollection(autoresCollectionNew);
            livros = em.merge(livros);
            if (areaIdOld != null && !areaIdOld.equals(areaIdNew)) {
                areaIdOld.getLivrosCollection().remove(livros);
                areaIdOld = em.merge(areaIdOld);
            }
            if (areaIdNew != null && !areaIdNew.equals(areaIdOld)) {
                areaIdNew.getLivrosCollection().add(livros);
                areaIdNew = em.merge(areaIdNew);
            }
            if (editoraIdOld != null && !editoraIdOld.equals(editoraIdNew)) {
                editoraIdOld.getLivrosCollection().remove(livros);
                editoraIdOld = em.merge(editoraIdOld);
            }
            if (editoraIdNew != null && !editoraIdNew.equals(editoraIdOld)) {
                editoraIdNew.getLivrosCollection().add(livros);
                editoraIdNew = em.merge(editoraIdNew);
            }
            for (Autores autoresCollectionOldAutores : autoresCollectionOld) {
                if (!autoresCollectionNew.contains(autoresCollectionOldAutores)) {
                    autoresCollectionOldAutores.getLivrosCollection().remove(livros);
                    autoresCollectionOldAutores = em.merge(autoresCollectionOldAutores);
                }
            }
            for (Autores autoresCollectionNewAutores : autoresCollectionNew) {
                if (!autoresCollectionOld.contains(autoresCollectionNewAutores)) {
                    autoresCollectionNewAutores.getLivrosCollection().add(livros);
                    autoresCollectionNewAutores = em.merge(autoresCollectionNewAutores);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = livros.getId();
                if (findLivros(id) == null) {
                    throw new NonexistentEntityException("The livros with id " + id + " no longer exists.");
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
            Livros livros;
            try {
                livros = em.getReference(Livros.class, id);
                livros.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The livros with id " + id + " no longer exists.", enfe);
            }
            Areas areaId = livros.getAreaId();
            if (areaId != null) {
                areaId.getLivrosCollection().remove(livros);
                areaId = em.merge(areaId);
            }
            Editoras editoraId = livros.getEditoraId();
            if (editoraId != null) {
                editoraId.getLivrosCollection().remove(livros);
                editoraId = em.merge(editoraId);
            }
            Collection<Autores> autoresCollection = livros.getAutoresCollection();
            for (Autores autoresCollectionAutores : autoresCollection) {
                autoresCollectionAutores.getLivrosCollection().remove(livros);
                autoresCollectionAutores = em.merge(autoresCollectionAutores);
            }
            em.remove(livros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Livros> findLivrosEntities() {
        return findLivrosEntities(true, -1, -1);
    }

    public List<Livros> findLivrosEntities(int maxResults, int firstResult) {
        return findLivrosEntities(false, maxResults, firstResult);
    }

    private List<Livros> findLivrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Livros as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Livros findLivros(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Livros.class, id);
        } finally {
            em.close();
        }
    }

    public int getLivrosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Livros as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
