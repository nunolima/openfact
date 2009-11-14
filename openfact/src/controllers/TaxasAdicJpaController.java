/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.TaxasAdic;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Itens;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class TaxasAdicJpaController {

    public TaxasAdicJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TaxasAdic taxasAdic) throws PreexistingEntityException, Exception {
        if (taxasAdic.getItensCollection() == null) {
            taxasAdic.setItensCollection(new ArrayList<Itens>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itens> attachedItensCollection = new ArrayList<Itens>();
            for (Itens itensCollectionItensToAttach : taxasAdic.getItensCollection()) {
                itensCollectionItensToAttach = em.getReference(itensCollectionItensToAttach.getClass(), itensCollectionItensToAttach.getIdItem());
                attachedItensCollection.add(itensCollectionItensToAttach);
            }
            taxasAdic.setItensCollection(attachedItensCollection);
            em.persist(taxasAdic);
            for (Itens itensCollectionItens : taxasAdic.getItensCollection()) {
                itensCollectionItens.getTaxasAdicCollection().add(taxasAdic);
                itensCollectionItens = em.merge(itensCollectionItens);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTaxasAdic(taxasAdic.getIdTaxaAdic()) != null) {
                throw new PreexistingEntityException("TaxasAdic " + taxasAdic + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TaxasAdic taxasAdic) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TaxasAdic persistentTaxasAdic = em.find(TaxasAdic.class, taxasAdic.getIdTaxaAdic());
            Collection<Itens> itensCollectionOld = persistentTaxasAdic.getItensCollection();
            Collection<Itens> itensCollectionNew = taxasAdic.getItensCollection();
            Collection<Itens> attachedItensCollectionNew = new ArrayList<Itens>();
            for (Itens itensCollectionNewItensToAttach : itensCollectionNew) {
                itensCollectionNewItensToAttach = em.getReference(itensCollectionNewItensToAttach.getClass(), itensCollectionNewItensToAttach.getIdItem());
                attachedItensCollectionNew.add(itensCollectionNewItensToAttach);
            }
            itensCollectionNew = attachedItensCollectionNew;
            taxasAdic.setItensCollection(itensCollectionNew);
            taxasAdic = em.merge(taxasAdic);
            for (Itens itensCollectionOldItens : itensCollectionOld) {
                if (!itensCollectionNew.contains(itensCollectionOldItens)) {
                    itensCollectionOldItens.getTaxasAdicCollection().remove(taxasAdic);
                    itensCollectionOldItens = em.merge(itensCollectionOldItens);
                }
            }
            for (Itens itensCollectionNewItens : itensCollectionNew) {
                if (!itensCollectionOld.contains(itensCollectionNewItens)) {
                    itensCollectionNewItens.getTaxasAdicCollection().add(taxasAdic);
                    itensCollectionNewItens = em.merge(itensCollectionNewItens);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = taxasAdic.getIdTaxaAdic();
                if (findTaxasAdic(id) == null) {
                    throw new NonexistentEntityException("The taxasAdic with id " + id + " no longer exists.");
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
            TaxasAdic taxasAdic;
            try {
                taxasAdic = em.getReference(TaxasAdic.class, id);
                taxasAdic.getIdTaxaAdic();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The taxasAdic with id " + id + " no longer exists.", enfe);
            }
            Collection<Itens> itensCollection = taxasAdic.getItensCollection();
            for (Itens itensCollectionItens : itensCollection) {
                itensCollectionItens.getTaxasAdicCollection().remove(taxasAdic);
                itensCollectionItens = em.merge(itensCollectionItens);
            }
            em.remove(taxasAdic);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TaxasAdic> findTaxasAdicEntities() {
        return findTaxasAdicEntities(true, -1, -1);
    }

    public List<TaxasAdic> findTaxasAdicEntities(int maxResults, int firstResult) {
        return findTaxasAdicEntities(false, maxResults, firstResult);
    }

    private List<TaxasAdic> findTaxasAdicEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TaxasAdic as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TaxasAdic findTaxasAdic(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TaxasAdic.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaxasAdicCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TaxasAdic as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
