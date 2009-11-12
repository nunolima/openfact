/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.controller;

import entidades.Editoras;
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
import entidades.Livros;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author PedrodeSousa
 */
public class EditorasJpaController {

    public EditorasJpaController() {
        emf = Persistence.createEntityManagerFactory("JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Editoras editoras) throws PreexistingEntityException, Exception {
        if (editoras.getLivrosCollection() == null) {
            editoras.setLivrosCollection(new ArrayList<Livros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Livros> attachedLivrosCollection = new ArrayList<Livros>();
            for (Livros livrosCollectionLivrosToAttach : editoras.getLivrosCollection()) {
                livrosCollectionLivrosToAttach = em.getReference(livrosCollectionLivrosToAttach.getClass(), livrosCollectionLivrosToAttach.getId());
                attachedLivrosCollection.add(livrosCollectionLivrosToAttach);
            }
            editoras.setLivrosCollection(attachedLivrosCollection);
            em.persist(editoras);
            for (Livros livrosCollectionLivros : editoras.getLivrosCollection()) {
                Editoras oldEditoraIdOfLivrosCollectionLivros = livrosCollectionLivros.getEditoraId();
                livrosCollectionLivros.setEditoraId(editoras);
                livrosCollectionLivros = em.merge(livrosCollectionLivros);
                if (oldEditoraIdOfLivrosCollectionLivros != null) {
                    oldEditoraIdOfLivrosCollectionLivros.getLivrosCollection().remove(livrosCollectionLivros);
                    oldEditoraIdOfLivrosCollectionLivros = em.merge(oldEditoraIdOfLivrosCollectionLivros);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEditoras(editoras.getId()) != null) {
                throw new PreexistingEntityException("Editoras " + editoras + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Editoras editoras) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Editoras persistentEditoras = em.find(Editoras.class, editoras.getId());
            Collection<Livros> livrosCollectionOld = persistentEditoras.getLivrosCollection();
            Collection<Livros> livrosCollectionNew = editoras.getLivrosCollection();
            List<String> illegalOrphanMessages = null;
            for (Livros livrosCollectionOldLivros : livrosCollectionOld) {
                if (!livrosCollectionNew.contains(livrosCollectionOldLivros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Livros " + livrosCollectionOldLivros + " since its editoraId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Livros> attachedLivrosCollectionNew = new ArrayList<Livros>();
            for (Livros livrosCollectionNewLivrosToAttach : livrosCollectionNew) {
                livrosCollectionNewLivrosToAttach = em.getReference(livrosCollectionNewLivrosToAttach.getClass(), livrosCollectionNewLivrosToAttach.getId());
                attachedLivrosCollectionNew.add(livrosCollectionNewLivrosToAttach);
            }
            livrosCollectionNew = attachedLivrosCollectionNew;
            editoras.setLivrosCollection(livrosCollectionNew);
            editoras = em.merge(editoras);
            for (Livros livrosCollectionNewLivros : livrosCollectionNew) {
                if (!livrosCollectionOld.contains(livrosCollectionNewLivros)) {
                    Editoras oldEditoraIdOfLivrosCollectionNewLivros = livrosCollectionNewLivros.getEditoraId();
                    livrosCollectionNewLivros.setEditoraId(editoras);
                    livrosCollectionNewLivros = em.merge(livrosCollectionNewLivros);
                    if (oldEditoraIdOfLivrosCollectionNewLivros != null && !oldEditoraIdOfLivrosCollectionNewLivros.equals(editoras)) {
                        oldEditoraIdOfLivrosCollectionNewLivros.getLivrosCollection().remove(livrosCollectionNewLivros);
                        oldEditoraIdOfLivrosCollectionNewLivros = em.merge(oldEditoraIdOfLivrosCollectionNewLivros);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = editoras.getId();
                if (findEditoras(id) == null) {
                    throw new NonexistentEntityException("The editoras with id " + id + " no longer exists.");
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
            Editoras editoras;
            try {
                editoras = em.getReference(Editoras.class, id);
                editoras.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The editoras with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Livros> livrosCollectionOrphanCheck = editoras.getLivrosCollection();
            for (Livros livrosCollectionOrphanCheckLivros : livrosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Editoras (" + editoras + ") cannot be destroyed since the Livros " + livrosCollectionOrphanCheckLivros + " in its livrosCollection field has a non-nullable editoraId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(editoras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Editoras> findEditorasEntities() {
        return findEditorasEntities(true, -1, -1);
    }

    public List<Editoras> findEditorasEntities(int maxResults, int firstResult) {
        return findEditorasEntities(false, maxResults, firstResult);
    }

    private List<Editoras> findEditorasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Editoras.class));
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

    public Editoras findEditoras(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Editoras.class, id);
        } finally {
            em.close();
        }
    }

    public int getEditorasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiscountCode> rt = cq.from(Editoras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
