/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.controller;

import entidades.Livros;
import entidades.controller.exceptions.IllegalOrphanException;
import entidades.controller.exceptions.NonexistentEntityException;
import entidades.controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Areas;
import entidades.Editoras;
import entidades.AutoresLivros;
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
        if (livros.getAutoresLivrosCollection() == null) {
            livros.setAutoresLivrosCollection(new ArrayList<AutoresLivros>());
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
            Collection<AutoresLivros> attachedAutoresLivrosCollection = new ArrayList<AutoresLivros>();
            for (AutoresLivros autoresLivrosCollectionAutoresLivrosToAttach : livros.getAutoresLivrosCollection()) {
                autoresLivrosCollectionAutoresLivrosToAttach = em.getReference(autoresLivrosCollectionAutoresLivrosToAttach.getClass(), autoresLivrosCollectionAutoresLivrosToAttach.getId());
                attachedAutoresLivrosCollection.add(autoresLivrosCollectionAutoresLivrosToAttach);
            }
            livros.setAutoresLivrosCollection(attachedAutoresLivrosCollection);
            em.persist(livros);
            if (areaId != null) {
                areaId.getLivrosCollection().add(livros);
                areaId = em.merge(areaId);
            }
            if (editoraId != null) {
                editoraId.getLivrosCollection().add(livros);
                editoraId = em.merge(editoraId);
            }
            for (AutoresLivros autoresLivrosCollectionAutoresLivros : livros.getAutoresLivrosCollection()) {
                Livros oldLivroIdOfAutoresLivrosCollectionAutoresLivros = autoresLivrosCollectionAutoresLivros.getLivroId();
                autoresLivrosCollectionAutoresLivros.setLivroId(livros);
                autoresLivrosCollectionAutoresLivros = em.merge(autoresLivrosCollectionAutoresLivros);
                if (oldLivroIdOfAutoresLivrosCollectionAutoresLivros != null) {
                    oldLivroIdOfAutoresLivrosCollectionAutoresLivros.getAutoresLivrosCollection().remove(autoresLivrosCollectionAutoresLivros);
                    oldLivroIdOfAutoresLivrosCollectionAutoresLivros = em.merge(oldLivroIdOfAutoresLivrosCollectionAutoresLivros);
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

    public void edit(Livros livros) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livros persistentLivros = em.find(Livros.class, livros.getId());
            Areas areaIdOld = persistentLivros.getAreaId();
            Areas areaIdNew = livros.getAreaId();
            Editoras editoraIdOld = persistentLivros.getEditoraId();
            Editoras editoraIdNew = livros.getEditoraId();
            Collection<AutoresLivros> autoresLivrosCollectionOld = persistentLivros.getAutoresLivrosCollection();
            Collection<AutoresLivros> autoresLivrosCollectionNew = livros.getAutoresLivrosCollection();
            List<String> illegalOrphanMessages = null;
            for (AutoresLivros autoresLivrosCollectionOldAutoresLivros : autoresLivrosCollectionOld) {
                if (!autoresLivrosCollectionNew.contains(autoresLivrosCollectionOldAutoresLivros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AutoresLivros " + autoresLivrosCollectionOldAutoresLivros + " since its livroId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (areaIdNew != null) {
                areaIdNew = em.getReference(areaIdNew.getClass(), areaIdNew.getId());
                livros.setAreaId(areaIdNew);
            }
            if (editoraIdNew != null) {
                editoraIdNew = em.getReference(editoraIdNew.getClass(), editoraIdNew.getId());
                livros.setEditoraId(editoraIdNew);
            }
            Collection<AutoresLivros> attachedAutoresLivrosCollectionNew = new ArrayList<AutoresLivros>();
            for (AutoresLivros autoresLivrosCollectionNewAutoresLivrosToAttach : autoresLivrosCollectionNew) {
                autoresLivrosCollectionNewAutoresLivrosToAttach = em.getReference(autoresLivrosCollectionNewAutoresLivrosToAttach.getClass(), autoresLivrosCollectionNewAutoresLivrosToAttach.getId());
                attachedAutoresLivrosCollectionNew.add(autoresLivrosCollectionNewAutoresLivrosToAttach);
            }
            autoresLivrosCollectionNew = attachedAutoresLivrosCollectionNew;
            livros.setAutoresLivrosCollection(autoresLivrosCollectionNew);
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
            for (AutoresLivros autoresLivrosCollectionNewAutoresLivros : autoresLivrosCollectionNew) {
                if (!autoresLivrosCollectionOld.contains(autoresLivrosCollectionNewAutoresLivros)) {
                    Livros oldLivroIdOfAutoresLivrosCollectionNewAutoresLivros = autoresLivrosCollectionNewAutoresLivros.getLivroId();
                    autoresLivrosCollectionNewAutoresLivros.setLivroId(livros);
                    autoresLivrosCollectionNewAutoresLivros = em.merge(autoresLivrosCollectionNewAutoresLivros);
                    if (oldLivroIdOfAutoresLivrosCollectionNewAutoresLivros != null && !oldLivroIdOfAutoresLivrosCollectionNewAutoresLivros.equals(livros)) {
                        oldLivroIdOfAutoresLivrosCollectionNewAutoresLivros.getAutoresLivrosCollection().remove(autoresLivrosCollectionNewAutoresLivros);
                        oldLivroIdOfAutoresLivrosCollectionNewAutoresLivros = em.merge(oldLivroIdOfAutoresLivrosCollectionNewAutoresLivros);
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

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<AutoresLivros> autoresLivrosCollectionOrphanCheck = livros.getAutoresLivrosCollection();
            for (AutoresLivros autoresLivrosCollectionOrphanCheckAutoresLivros : autoresLivrosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Livros (" + livros + ") cannot be destroyed since the AutoresLivros " + autoresLivrosCollectionOrphanCheckAutoresLivros + " in its autoresLivrosCollection field has a non-nullable livroId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Livros.class));
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
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiscountCode> rt = cq.from(Livros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
