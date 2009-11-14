/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.CatItens;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TiposItens;
import entidades.TaxasDesc;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Itens;

/**
 *
 * @author User
 */
public class CatItensJpaController {

    public CatItensJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CatItens catItens) throws PreexistingEntityException, Exception {
        if (catItens.getTaxasDescCollection() == null) {
            catItens.setTaxasDescCollection(new ArrayList<TaxasDesc>());
        }
        if (catItens.getItensCollection() == null) {
            catItens.setItensCollection(new ArrayList<Itens>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposItens tipoItem = catItens.getTipoItem();
            if (tipoItem != null) {
                tipoItem = em.getReference(tipoItem.getClass(), tipoItem.getTipoItem());
                catItens.setTipoItem(tipoItem);
            }
            Collection<TaxasDesc> attachedTaxasDescCollection = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollectionTaxasDescToAttach : catItens.getTaxasDescCollection()) {
                taxasDescCollectionTaxasDescToAttach = em.getReference(taxasDescCollectionTaxasDescToAttach.getClass(), taxasDescCollectionTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollection.add(taxasDescCollectionTaxasDescToAttach);
            }
            catItens.setTaxasDescCollection(attachedTaxasDescCollection);
            Collection<Itens> attachedItensCollection = new ArrayList<Itens>();
            for (Itens itensCollectionItensToAttach : catItens.getItensCollection()) {
                itensCollectionItensToAttach = em.getReference(itensCollectionItensToAttach.getClass(), itensCollectionItensToAttach.getIdItem());
                attachedItensCollection.add(itensCollectionItensToAttach);
            }
            catItens.setItensCollection(attachedItensCollection);
            em.persist(catItens);
            if (tipoItem != null) {
                tipoItem.getCatItensCollection().add(catItens);
                tipoItem = em.merge(tipoItem);
            }
            for (TaxasDesc taxasDescCollectionTaxasDesc : catItens.getTaxasDescCollection()) {
                CatItens oldCatItensOfTaxasDescCollectionTaxasDesc = taxasDescCollectionTaxasDesc.getCatItens();
                taxasDescCollectionTaxasDesc.setCatItens(catItens);
                taxasDescCollectionTaxasDesc = em.merge(taxasDescCollectionTaxasDesc);
                if (oldCatItensOfTaxasDescCollectionTaxasDesc != null) {
                    oldCatItensOfTaxasDescCollectionTaxasDesc.getTaxasDescCollection().remove(taxasDescCollectionTaxasDesc);
                    oldCatItensOfTaxasDescCollectionTaxasDesc = em.merge(oldCatItensOfTaxasDescCollectionTaxasDesc);
                }
            }
            for (Itens itensCollectionItens : catItens.getItensCollection()) {
                CatItens oldIdCatItemOfItensCollectionItens = itensCollectionItens.getIdCatItem();
                itensCollectionItens.setIdCatItem(catItens);
                itensCollectionItens = em.merge(itensCollectionItens);
                if (oldIdCatItemOfItensCollectionItens != null) {
                    oldIdCatItemOfItensCollectionItens.getItensCollection().remove(itensCollectionItens);
                    oldIdCatItemOfItensCollectionItens = em.merge(oldIdCatItemOfItensCollectionItens);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatItens(catItens.getIdCatItem()) != null) {
                throw new PreexistingEntityException("CatItens " + catItens + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CatItens catItens) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatItens persistentCatItens = em.find(CatItens.class, catItens.getIdCatItem());
            TiposItens tipoItemOld = persistentCatItens.getTipoItem();
            TiposItens tipoItemNew = catItens.getTipoItem();
            Collection<TaxasDesc> taxasDescCollectionOld = persistentCatItens.getTaxasDescCollection();
            Collection<TaxasDesc> taxasDescCollectionNew = catItens.getTaxasDescCollection();
            Collection<Itens> itensCollectionOld = persistentCatItens.getItensCollection();
            Collection<Itens> itensCollectionNew = catItens.getItensCollection();
            List<String> illegalOrphanMessages = null;
            for (TaxasDesc taxasDescCollectionOldTaxasDesc : taxasDescCollectionOld) {
                if (!taxasDescCollectionNew.contains(taxasDescCollectionOldTaxasDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TaxasDesc " + taxasDescCollectionOldTaxasDesc + " since its catItens field is not nullable.");
                }
            }
            for (Itens itensCollectionOldItens : itensCollectionOld) {
                if (!itensCollectionNew.contains(itensCollectionOldItens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itens " + itensCollectionOldItens + " since its idCatItem field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoItemNew != null) {
                tipoItemNew = em.getReference(tipoItemNew.getClass(), tipoItemNew.getTipoItem());
                catItens.setTipoItem(tipoItemNew);
            }
            Collection<TaxasDesc> attachedTaxasDescCollectionNew = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollectionNewTaxasDescToAttach : taxasDescCollectionNew) {
                taxasDescCollectionNewTaxasDescToAttach = em.getReference(taxasDescCollectionNewTaxasDescToAttach.getClass(), taxasDescCollectionNewTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollectionNew.add(taxasDescCollectionNewTaxasDescToAttach);
            }
            taxasDescCollectionNew = attachedTaxasDescCollectionNew;
            catItens.setTaxasDescCollection(taxasDescCollectionNew);
            Collection<Itens> attachedItensCollectionNew = new ArrayList<Itens>();
            for (Itens itensCollectionNewItensToAttach : itensCollectionNew) {
                itensCollectionNewItensToAttach = em.getReference(itensCollectionNewItensToAttach.getClass(), itensCollectionNewItensToAttach.getIdItem());
                attachedItensCollectionNew.add(itensCollectionNewItensToAttach);
            }
            itensCollectionNew = attachedItensCollectionNew;
            catItens.setItensCollection(itensCollectionNew);
            catItens = em.merge(catItens);
            if (tipoItemOld != null && !tipoItemOld.equals(tipoItemNew)) {
                tipoItemOld.getCatItensCollection().remove(catItens);
                tipoItemOld = em.merge(tipoItemOld);
            }
            if (tipoItemNew != null && !tipoItemNew.equals(tipoItemOld)) {
                tipoItemNew.getCatItensCollection().add(catItens);
                tipoItemNew = em.merge(tipoItemNew);
            }
            for (TaxasDesc taxasDescCollectionNewTaxasDesc : taxasDescCollectionNew) {
                if (!taxasDescCollectionOld.contains(taxasDescCollectionNewTaxasDesc)) {
                    CatItens oldCatItensOfTaxasDescCollectionNewTaxasDesc = taxasDescCollectionNewTaxasDesc.getCatItens();
                    taxasDescCollectionNewTaxasDesc.setCatItens(catItens);
                    taxasDescCollectionNewTaxasDesc = em.merge(taxasDescCollectionNewTaxasDesc);
                    if (oldCatItensOfTaxasDescCollectionNewTaxasDesc != null && !oldCatItensOfTaxasDescCollectionNewTaxasDesc.equals(catItens)) {
                        oldCatItensOfTaxasDescCollectionNewTaxasDesc.getTaxasDescCollection().remove(taxasDescCollectionNewTaxasDesc);
                        oldCatItensOfTaxasDescCollectionNewTaxasDesc = em.merge(oldCatItensOfTaxasDescCollectionNewTaxasDesc);
                    }
                }
            }
            for (Itens itensCollectionNewItens : itensCollectionNew) {
                if (!itensCollectionOld.contains(itensCollectionNewItens)) {
                    CatItens oldIdCatItemOfItensCollectionNewItens = itensCollectionNewItens.getIdCatItem();
                    itensCollectionNewItens.setIdCatItem(catItens);
                    itensCollectionNewItens = em.merge(itensCollectionNewItens);
                    if (oldIdCatItemOfItensCollectionNewItens != null && !oldIdCatItemOfItensCollectionNewItens.equals(catItens)) {
                        oldIdCatItemOfItensCollectionNewItens.getItensCollection().remove(itensCollectionNewItens);
                        oldIdCatItemOfItensCollectionNewItens = em.merge(oldIdCatItemOfItensCollectionNewItens);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = catItens.getIdCatItem();
                if (findCatItens(id) == null) {
                    throw new NonexistentEntityException("The catItens with id " + id + " no longer exists.");
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
            CatItens catItens;
            try {
                catItens = em.getReference(CatItens.class, id);
                catItens.getIdCatItem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catItens with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TaxasDesc> taxasDescCollectionOrphanCheck = catItens.getTaxasDescCollection();
            for (TaxasDesc taxasDescCollectionOrphanCheckTaxasDesc : taxasDescCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CatItens (" + catItens + ") cannot be destroyed since the TaxasDesc " + taxasDescCollectionOrphanCheckTaxasDesc + " in its taxasDescCollection field has a non-nullable catItens field.");
            }
            Collection<Itens> itensCollectionOrphanCheck = catItens.getItensCollection();
            for (Itens itensCollectionOrphanCheckItens : itensCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CatItens (" + catItens + ") cannot be destroyed since the Itens " + itensCollectionOrphanCheckItens + " in its itensCollection field has a non-nullable idCatItem field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TiposItens tipoItem = catItens.getTipoItem();
            if (tipoItem != null) {
                tipoItem.getCatItensCollection().remove(catItens);
                tipoItem = em.merge(tipoItem);
            }
            em.remove(catItens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CatItens> findCatItensEntities() {
        return findCatItensEntities(true, -1, -1);
    }

    public List<CatItens> findCatItensEntities(int maxResults, int firstResult) {
        return findCatItensEntities(false, maxResults, firstResult);
    }

    private List<CatItens> findCatItensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CatItens as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CatItens findCatItens(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatItens.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatItensCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from CatItens as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
