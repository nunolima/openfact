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
import entidades.LivrosAutores;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
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
            livros.setAutoresCollection(new ArrayList<LivrosAutores>());
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
            Collection<LivrosAutores> attachedAutoresCollection = new ArrayList<LivrosAutores>();
            for (LivrosAutores autoresCollectionLivrosAutoresToAttach : livros.getAutoresCollection()) {
                autoresCollectionLivrosAutoresToAttach = em.getReference(autoresCollectionLivrosAutoresToAttach.getClass(), autoresCollectionLivrosAutoresToAttach.getIdLivrosAutores());
                attachedAutoresCollection.add(autoresCollectionLivrosAutoresToAttach);
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
            for (LivrosAutores autoresCollectionLivrosAutores : livros.getAutoresCollection()) {
                Livros oldLivroOfAutoresCollectionLivrosAutores = autoresCollectionLivrosAutores.getLivro();
                autoresCollectionLivrosAutores.setLivro(livros);
                autoresCollectionLivrosAutores = em.merge(autoresCollectionLivrosAutores);
                if (oldLivroOfAutoresCollectionLivrosAutores != null) {
                    oldLivroOfAutoresCollectionLivrosAutores.getAutoresCollection().remove(autoresCollectionLivrosAutores);
                    oldLivroOfAutoresCollectionLivrosAutores = em.merge(oldLivroOfAutoresCollectionLivrosAutores);
                }
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
            Collection<LivrosAutores> autoresCollectionOld = persistentLivros.getAutoresCollection();
            Collection<LivrosAutores> autoresCollectionNew = livros.getAutoresCollection();
            if (areaIdNew != null) {
                areaIdNew = em.getReference(areaIdNew.getClass(), areaIdNew.getId());
                livros.setAreaId(areaIdNew);
            }
            if (editoraIdNew != null) {
                editoraIdNew = em.getReference(editoraIdNew.getClass(), editoraIdNew.getId());
                livros.setEditoraId(editoraIdNew);
            }
            Collection<LivrosAutores> attachedAutoresCollectionNew = new ArrayList<LivrosAutores>();
            for (LivrosAutores autoresCollectionNewLivrosAutoresToAttach : autoresCollectionNew) {
                autoresCollectionNewLivrosAutoresToAttach = em.getReference(autoresCollectionNewLivrosAutoresToAttach.getClass(), autoresCollectionNewLivrosAutoresToAttach.getIdAutor());
                attachedAutoresCollectionNew.add(autoresCollectionNewLivrosAutoresToAttach);
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
            for (LivrosAutores autoresCollectionOldLivrosAutores : autoresCollectionOld) {
                if (!autoresCollectionNew.contains(autoresCollectionOldLivrosAutores)) {
                    autoresCollectionOldLivrosAutores.setLivro(null);
                    autoresCollectionOldLivrosAutores = em.merge(autoresCollectionOldLivrosAutores);
                }
            }
            for (LivrosAutores autoresCollectionNewLivrosAutores : autoresCollectionNew) {
                if (!autoresCollectionOld.contains(autoresCollectionNewLivrosAutores)) {
                    Livros oldLivroOfAutoresCollectionNewLivrosAutores = autoresCollectionNewLivrosAutores.getLivro();
                    autoresCollectionNewLivrosAutores.setLivro(livros);
                    autoresCollectionNewLivrosAutores = em.merge(autoresCollectionNewLivrosAutores);
                    if (oldLivroOfAutoresCollectionNewLivrosAutores != null && !oldLivroOfAutoresCollectionNewLivrosAutores.equals(livros)) {
                        oldLivroOfAutoresCollectionNewLivrosAutores.getAutoresCollection().remove(autoresCollectionNewLivrosAutores);
                        oldLivroOfAutoresCollectionNewLivrosAutores = em.merge(oldLivroOfAutoresCollectionNewLivrosAutores);
                    }
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
            Collection<LivrosAutores> autoresCollection = livros.getAutoresCollection();
            for (LivrosAutores autoresCollectionLivrosAutores : autoresCollection) {
                autoresCollectionLivrosAutores.setLivro(null);
                autoresCollectionLivrosAutores = em.merge(autoresCollectionLivrosAutores);
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
