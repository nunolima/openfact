/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.TiposMovStock;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TiposDoc;
import java.util.ArrayList;
import java.util.Collection;
import entidades.MovStock;

/**
 *
 * @author User
 */
public class TiposMovStockJpaController {

    public TiposMovStockJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposMovStock tiposMovStock) throws PreexistingEntityException, Exception {
        if (tiposMovStock.getTiposDocCollection() == null) {
            tiposMovStock.setTiposDocCollection(new ArrayList<TiposDoc>());
        }
        if (tiposMovStock.getMovStockCollection() == null) {
            tiposMovStock.setMovStockCollection(new ArrayList<MovStock>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TiposDoc> attachedTiposDocCollection = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollectionTiposDocToAttach : tiposMovStock.getTiposDocCollection()) {
                tiposDocCollectionTiposDocToAttach = em.getReference(tiposDocCollectionTiposDocToAttach.getClass(), tiposDocCollectionTiposDocToAttach.getTipoDoc());
                attachedTiposDocCollection.add(tiposDocCollectionTiposDocToAttach);
            }
            tiposMovStock.setTiposDocCollection(attachedTiposDocCollection);
            Collection<MovStock> attachedMovStockCollection = new ArrayList<MovStock>();
            for (MovStock movStockCollectionMovStockToAttach : tiposMovStock.getMovStockCollection()) {
                movStockCollectionMovStockToAttach = em.getReference(movStockCollectionMovStockToAttach.getClass(), movStockCollectionMovStockToAttach.getIdMovStock());
                attachedMovStockCollection.add(movStockCollectionMovStockToAttach);
            }
            tiposMovStock.setMovStockCollection(attachedMovStockCollection);
            em.persist(tiposMovStock);
            for (TiposDoc tiposDocCollectionTiposDoc : tiposMovStock.getTiposDocCollection()) {
                TiposMovStock oldTipoMovStockOfTiposDocCollectionTiposDoc = tiposDocCollectionTiposDoc.getTipoMovStock();
                tiposDocCollectionTiposDoc.setTipoMovStock(tiposMovStock);
                tiposDocCollectionTiposDoc = em.merge(tiposDocCollectionTiposDoc);
                if (oldTipoMovStockOfTiposDocCollectionTiposDoc != null) {
                    oldTipoMovStockOfTiposDocCollectionTiposDoc.getTiposDocCollection().remove(tiposDocCollectionTiposDoc);
                    oldTipoMovStockOfTiposDocCollectionTiposDoc = em.merge(oldTipoMovStockOfTiposDocCollectionTiposDoc);
                }
            }
            for (MovStock movStockCollectionMovStock : tiposMovStock.getMovStockCollection()) {
                TiposMovStock oldTipoMovStockOfMovStockCollectionMovStock = movStockCollectionMovStock.getTipoMovStock();
                movStockCollectionMovStock.setTipoMovStock(tiposMovStock);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
                if (oldTipoMovStockOfMovStockCollectionMovStock != null) {
                    oldTipoMovStockOfMovStockCollectionMovStock.getMovStockCollection().remove(movStockCollectionMovStock);
                    oldTipoMovStockOfMovStockCollectionMovStock = em.merge(oldTipoMovStockOfMovStockCollectionMovStock);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiposMovStock(tiposMovStock.getTipoMovStock()) != null) {
                throw new PreexistingEntityException("TiposMovStock " + tiposMovStock + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposMovStock tiposMovStock) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposMovStock persistentTiposMovStock = em.find(TiposMovStock.class, tiposMovStock.getTipoMovStock());
            Collection<TiposDoc> tiposDocCollectionOld = persistentTiposMovStock.getTiposDocCollection();
            Collection<TiposDoc> tiposDocCollectionNew = tiposMovStock.getTiposDocCollection();
            Collection<MovStock> movStockCollectionOld = persistentTiposMovStock.getMovStockCollection();
            Collection<MovStock> movStockCollectionNew = tiposMovStock.getMovStockCollection();
            Collection<TiposDoc> attachedTiposDocCollectionNew = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollectionNewTiposDocToAttach : tiposDocCollectionNew) {
                tiposDocCollectionNewTiposDocToAttach = em.getReference(tiposDocCollectionNewTiposDocToAttach.getClass(), tiposDocCollectionNewTiposDocToAttach.getTipoDoc());
                attachedTiposDocCollectionNew.add(tiposDocCollectionNewTiposDocToAttach);
            }
            tiposDocCollectionNew = attachedTiposDocCollectionNew;
            tiposMovStock.setTiposDocCollection(tiposDocCollectionNew);
            Collection<MovStock> attachedMovStockCollectionNew = new ArrayList<MovStock>();
            for (MovStock movStockCollectionNewMovStockToAttach : movStockCollectionNew) {
                movStockCollectionNewMovStockToAttach = em.getReference(movStockCollectionNewMovStockToAttach.getClass(), movStockCollectionNewMovStockToAttach.getIdMovStock());
                attachedMovStockCollectionNew.add(movStockCollectionNewMovStockToAttach);
            }
            movStockCollectionNew = attachedMovStockCollectionNew;
            tiposMovStock.setMovStockCollection(movStockCollectionNew);
            tiposMovStock = em.merge(tiposMovStock);
            for (TiposDoc tiposDocCollectionOldTiposDoc : tiposDocCollectionOld) {
                if (!tiposDocCollectionNew.contains(tiposDocCollectionOldTiposDoc)) {
                    tiposDocCollectionOldTiposDoc.setTipoMovStock(null);
                    tiposDocCollectionOldTiposDoc = em.merge(tiposDocCollectionOldTiposDoc);
                }
            }
            for (TiposDoc tiposDocCollectionNewTiposDoc : tiposDocCollectionNew) {
                if (!tiposDocCollectionOld.contains(tiposDocCollectionNewTiposDoc)) {
                    TiposMovStock oldTipoMovStockOfTiposDocCollectionNewTiposDoc = tiposDocCollectionNewTiposDoc.getTipoMovStock();
                    tiposDocCollectionNewTiposDoc.setTipoMovStock(tiposMovStock);
                    tiposDocCollectionNewTiposDoc = em.merge(tiposDocCollectionNewTiposDoc);
                    if (oldTipoMovStockOfTiposDocCollectionNewTiposDoc != null && !oldTipoMovStockOfTiposDocCollectionNewTiposDoc.equals(tiposMovStock)) {
                        oldTipoMovStockOfTiposDocCollectionNewTiposDoc.getTiposDocCollection().remove(tiposDocCollectionNewTiposDoc);
                        oldTipoMovStockOfTiposDocCollectionNewTiposDoc = em.merge(oldTipoMovStockOfTiposDocCollectionNewTiposDoc);
                    }
                }
            }
            for (MovStock movStockCollectionOldMovStock : movStockCollectionOld) {
                if (!movStockCollectionNew.contains(movStockCollectionOldMovStock)) {
                    movStockCollectionOldMovStock.setTipoMovStock(null);
                    movStockCollectionOldMovStock = em.merge(movStockCollectionOldMovStock);
                }
            }
            for (MovStock movStockCollectionNewMovStock : movStockCollectionNew) {
                if (!movStockCollectionOld.contains(movStockCollectionNewMovStock)) {
                    TiposMovStock oldTipoMovStockOfMovStockCollectionNewMovStock = movStockCollectionNewMovStock.getTipoMovStock();
                    movStockCollectionNewMovStock.setTipoMovStock(tiposMovStock);
                    movStockCollectionNewMovStock = em.merge(movStockCollectionNewMovStock);
                    if (oldTipoMovStockOfMovStockCollectionNewMovStock != null && !oldTipoMovStockOfMovStockCollectionNewMovStock.equals(tiposMovStock)) {
                        oldTipoMovStockOfMovStockCollectionNewMovStock.getMovStockCollection().remove(movStockCollectionNewMovStock);
                        oldTipoMovStockOfMovStockCollectionNewMovStock = em.merge(oldTipoMovStockOfMovStockCollectionNewMovStock);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tiposMovStock.getTipoMovStock();
                if (findTiposMovStock(id) == null) {
                    throw new NonexistentEntityException("The tiposMovStock with id " + id + " no longer exists.");
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
            TiposMovStock tiposMovStock;
            try {
                tiposMovStock = em.getReference(TiposMovStock.class, id);
                tiposMovStock.getTipoMovStock();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposMovStock with id " + id + " no longer exists.", enfe);
            }
            Collection<TiposDoc> tiposDocCollection = tiposMovStock.getTiposDocCollection();
            for (TiposDoc tiposDocCollectionTiposDoc : tiposDocCollection) {
                tiposDocCollectionTiposDoc.setTipoMovStock(null);
                tiposDocCollectionTiposDoc = em.merge(tiposDocCollectionTiposDoc);
            }
            Collection<MovStock> movStockCollection = tiposMovStock.getMovStockCollection();
            for (MovStock movStockCollectionMovStock : movStockCollection) {
                movStockCollectionMovStock.setTipoMovStock(null);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
            }
            em.remove(tiposMovStock);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposMovStock> findTiposMovStockEntities() {
        return findTiposMovStockEntities(true, -1, -1);
    }

    public List<TiposMovStock> findTiposMovStockEntities(int maxResults, int firstResult) {
        return findTiposMovStockEntities(false, maxResults, firstResult);
    }

    private List<TiposMovStock> findTiposMovStockEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TiposMovStock as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TiposMovStock findTiposMovStock(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposMovStock.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposMovStockCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TiposMovStock as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
