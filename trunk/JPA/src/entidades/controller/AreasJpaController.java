/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.controller;

import entidades.Areas;
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
public class AreasJpaController {

    public AreasJpaController() {
        emf = Persistence.createEntityManagerFactory("JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Areas areas) throws PreexistingEntityException, Exception {
        if (areas.getLivrosCollection() == null) {
            areas.setLivrosCollection(new ArrayList<Livros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Livros> attachedLivrosCollection = new ArrayList<Livros>();
            for (Livros livrosCollectionLivrosToAttach : areas.getLivrosCollection()) {
                livrosCollectionLivrosToAttach = em.getReference(livrosCollectionLivrosToAttach.getClass(), livrosCollectionLivrosToAttach.getId());
                attachedLivrosCollection.add(livrosCollectionLivrosToAttach);
            }
            areas.setLivrosCollection(attachedLivrosCollection);
            em.persist(areas);
            for (Livros livrosCollectionLivros : areas.getLivrosCollection()) {
                Areas oldAreaIdOfLivrosCollectionLivros = livrosCollectionLivros.getAreaId();
                livrosCollectionLivros.setAreaId(areas);
                livrosCollectionLivros = em.merge(livrosCollectionLivros);
                if (oldAreaIdOfLivrosCollectionLivros != null) {
                    oldAreaIdOfLivrosCollectionLivros.getLivrosCollection().remove(livrosCollectionLivros);
                    oldAreaIdOfLivrosCollectionLivros = em.merge(oldAreaIdOfLivrosCollectionLivros);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAreas(areas.getId()) != null) {
                throw new PreexistingEntityException("Areas " + areas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Areas areas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areas persistentAreas = em.find(Areas.class, areas.getId());
            Collection<Livros> livrosCollectionOld = persistentAreas.getLivrosCollection();
            Collection<Livros> livrosCollectionNew = areas.getLivrosCollection();
            List<String> illegalOrphanMessages = null;
            for (Livros livrosCollectionOldLivros : livrosCollectionOld) {
                if (!livrosCollectionNew.contains(livrosCollectionOldLivros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Livros " + livrosCollectionOldLivros + " since its areaId field is not nullable.");
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
            areas.setLivrosCollection(livrosCollectionNew);
            areas = em.merge(areas);
            for (Livros livrosCollectionNewLivros : livrosCollectionNew) {
                if (!livrosCollectionOld.contains(livrosCollectionNewLivros)) {
                    Areas oldAreaIdOfLivrosCollectionNewLivros = livrosCollectionNewLivros.getAreaId();
                    livrosCollectionNewLivros.setAreaId(areas);
                    livrosCollectionNewLivros = em.merge(livrosCollectionNewLivros);
                    if (oldAreaIdOfLivrosCollectionNewLivros != null && !oldAreaIdOfLivrosCollectionNewLivros.equals(areas)) {
                        oldAreaIdOfLivrosCollectionNewLivros.getLivrosCollection().remove(livrosCollectionNewLivros);
                        oldAreaIdOfLivrosCollectionNewLivros = em.merge(oldAreaIdOfLivrosCollectionNewLivros);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = areas.getId();
                if (findAreas(id) == null) {
                    throw new NonexistentEntityException("The areas with id " + id + " no longer exists.");
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
            Areas areas;
            try {
                areas = em.getReference(Areas.class, id);
                areas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Livros> livrosCollectionOrphanCheck = areas.getLivrosCollection();
            for (Livros livrosCollectionOrphanCheckLivros : livrosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Areas (" + areas + ") cannot be destroyed since the Livros " + livrosCollectionOrphanCheckLivros + " in its livrosCollection field has a non-nullable areaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(areas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Areas> findAreasEntities() {
        return findAreasEntities(true, -1, -1);
    }

    public List<Areas> findAreasEntities(int maxResults, int firstResult) {
        return findAreasEntities(false, maxResults, firstResult);
    }

    private List<Areas> findAreasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Areas.class));
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

    public Areas findAreas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Areas.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiscountCode> rt = cq.from(Areas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
