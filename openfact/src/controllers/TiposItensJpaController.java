/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.TiposItens;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.CatItens;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Itens;

/**
 *
 * @author User
 */
public class TiposItensJpaController {

    public TiposItensJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposItens tiposItens) throws PreexistingEntityException, Exception {
        if (tiposItens.getCatItensCollection() == null) {
            tiposItens.setCatItensCollection(new ArrayList<CatItens>());
        }
        if (tiposItens.getItensCollection() == null) {
            tiposItens.setItensCollection(new ArrayList<Itens>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CatItens> attachedCatItensCollection = new ArrayList<CatItens>();
            for (CatItens catItensCollectionCatItensToAttach : tiposItens.getCatItensCollection()) {
                catItensCollectionCatItensToAttach = em.getReference(catItensCollectionCatItensToAttach.getClass(), catItensCollectionCatItensToAttach.getIdCatItem());
                attachedCatItensCollection.add(catItensCollectionCatItensToAttach);
            }
            tiposItens.setCatItensCollection(attachedCatItensCollection);
            Collection<Itens> attachedItensCollection = new ArrayList<Itens>();
            for (Itens itensCollectionItensToAttach : tiposItens.getItensCollection()) {
                itensCollectionItensToAttach = em.getReference(itensCollectionItensToAttach.getClass(), itensCollectionItensToAttach.getIdItem());
                attachedItensCollection.add(itensCollectionItensToAttach);
            }
            tiposItens.setItensCollection(attachedItensCollection);
            em.persist(tiposItens);
            for (CatItens catItensCollectionCatItens : tiposItens.getCatItensCollection()) {
                TiposItens oldTipoItemOfCatItensCollectionCatItens = catItensCollectionCatItens.getTipoItem();
                catItensCollectionCatItens.setTipoItem(tiposItens);
                catItensCollectionCatItens = em.merge(catItensCollectionCatItens);
                if (oldTipoItemOfCatItensCollectionCatItens != null) {
                    oldTipoItemOfCatItensCollectionCatItens.getCatItensCollection().remove(catItensCollectionCatItens);
                    oldTipoItemOfCatItensCollectionCatItens = em.merge(oldTipoItemOfCatItensCollectionCatItens);
                }
            }
            for (Itens itensCollectionItens : tiposItens.getItensCollection()) {
                TiposItens oldTipoItemOfItensCollectionItens = itensCollectionItens.getTipoItem();
                itensCollectionItens.setTipoItem(tiposItens);
                itensCollectionItens = em.merge(itensCollectionItens);
                if (oldTipoItemOfItensCollectionItens != null) {
                    oldTipoItemOfItensCollectionItens.getItensCollection().remove(itensCollectionItens);
                    oldTipoItemOfItensCollectionItens = em.merge(oldTipoItemOfItensCollectionItens);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiposItens(tiposItens.getTipoItem()) != null) {
                throw new PreexistingEntityException("TiposItens " + tiposItens + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposItens tiposItens) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposItens persistentTiposItens = em.find(TiposItens.class, tiposItens.getTipoItem());
            Collection<CatItens> catItensCollectionOld = persistentTiposItens.getCatItensCollection();
            Collection<CatItens> catItensCollectionNew = tiposItens.getCatItensCollection();
            Collection<Itens> itensCollectionOld = persistentTiposItens.getItensCollection();
            Collection<Itens> itensCollectionNew = tiposItens.getItensCollection();
            List<String> illegalOrphanMessages = null;
            for (Itens itensCollectionOldItens : itensCollectionOld) {
                if (!itensCollectionNew.contains(itensCollectionOldItens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itens " + itensCollectionOldItens + " since its tipoItem field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CatItens> attachedCatItensCollectionNew = new ArrayList<CatItens>();
            for (CatItens catItensCollectionNewCatItensToAttach : catItensCollectionNew) {
                catItensCollectionNewCatItensToAttach = em.getReference(catItensCollectionNewCatItensToAttach.getClass(), catItensCollectionNewCatItensToAttach.getIdCatItem());
                attachedCatItensCollectionNew.add(catItensCollectionNewCatItensToAttach);
            }
            catItensCollectionNew = attachedCatItensCollectionNew;
            tiposItens.setCatItensCollection(catItensCollectionNew);
            Collection<Itens> attachedItensCollectionNew = new ArrayList<Itens>();
            for (Itens itensCollectionNewItensToAttach : itensCollectionNew) {
                itensCollectionNewItensToAttach = em.getReference(itensCollectionNewItensToAttach.getClass(), itensCollectionNewItensToAttach.getIdItem());
                attachedItensCollectionNew.add(itensCollectionNewItensToAttach);
            }
            itensCollectionNew = attachedItensCollectionNew;
            tiposItens.setItensCollection(itensCollectionNew);
            tiposItens = em.merge(tiposItens);
            for (CatItens catItensCollectionOldCatItens : catItensCollectionOld) {
                if (!catItensCollectionNew.contains(catItensCollectionOldCatItens)) {
                    catItensCollectionOldCatItens.setTipoItem(null);
                    catItensCollectionOldCatItens = em.merge(catItensCollectionOldCatItens);
                }
            }
            for (CatItens catItensCollectionNewCatItens : catItensCollectionNew) {
                if (!catItensCollectionOld.contains(catItensCollectionNewCatItens)) {
                    TiposItens oldTipoItemOfCatItensCollectionNewCatItens = catItensCollectionNewCatItens.getTipoItem();
                    catItensCollectionNewCatItens.setTipoItem(tiposItens);
                    catItensCollectionNewCatItens = em.merge(catItensCollectionNewCatItens);
                    if (oldTipoItemOfCatItensCollectionNewCatItens != null && !oldTipoItemOfCatItensCollectionNewCatItens.equals(tiposItens)) {
                        oldTipoItemOfCatItensCollectionNewCatItens.getCatItensCollection().remove(catItensCollectionNewCatItens);
                        oldTipoItemOfCatItensCollectionNewCatItens = em.merge(oldTipoItemOfCatItensCollectionNewCatItens);
                    }
                }
            }
            for (Itens itensCollectionNewItens : itensCollectionNew) {
                if (!itensCollectionOld.contains(itensCollectionNewItens)) {
                    TiposItens oldTipoItemOfItensCollectionNewItens = itensCollectionNewItens.getTipoItem();
                    itensCollectionNewItens.setTipoItem(tiposItens);
                    itensCollectionNewItens = em.merge(itensCollectionNewItens);
                    if (oldTipoItemOfItensCollectionNewItens != null && !oldTipoItemOfItensCollectionNewItens.equals(tiposItens)) {
                        oldTipoItemOfItensCollectionNewItens.getItensCollection().remove(itensCollectionNewItens);
                        oldTipoItemOfItensCollectionNewItens = em.merge(oldTipoItemOfItensCollectionNewItens);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tiposItens.getTipoItem();
                if (findTiposItens(id) == null) {
                    throw new NonexistentEntityException("The tiposItens with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposItens tiposItens;
            try {
                tiposItens = em.getReference(TiposItens.class, id);
                tiposItens.getTipoItem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposItens with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itens> itensCollectionOrphanCheck = tiposItens.getItensCollection();
            for (Itens itensCollectionOrphanCheckItens : itensCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TiposItens (" + tiposItens + ") cannot be destroyed since the Itens " + itensCollectionOrphanCheckItens + " in its itensCollection field has a non-nullable tipoItem field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CatItens> catItensCollection = tiposItens.getCatItensCollection();
            for (CatItens catItensCollectionCatItens : catItensCollection) {
                catItensCollectionCatItens.setTipoItem(null);
                catItensCollectionCatItens = em.merge(catItensCollectionCatItens);
            }
            em.remove(tiposItens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposItens> findTiposItensEntities() {
        return findTiposItensEntities(true, -1, -1);
    }

    public List<TiposItens> findTiposItensEntities(int maxResults, int firstResult) {
        return findTiposItensEntities(false, maxResults, firstResult);
    }

    private List<TiposItens> findTiposItensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TiposItens as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TiposItens findTiposItens(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposItens.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposItensCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TiposItens as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
