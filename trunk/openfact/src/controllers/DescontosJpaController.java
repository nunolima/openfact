/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Descontos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TaxasDesc;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class DescontosJpaController {

    public DescontosJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Descontos descontos) throws PreexistingEntityException, Exception {
        if (descontos.getTaxasDescCollection() == null) {
            descontos.setTaxasDescCollection(new ArrayList<TaxasDesc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TaxasDesc> attachedTaxasDescCollection = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollectionTaxasDescToAttach : descontos.getTaxasDescCollection()) {
                taxasDescCollectionTaxasDescToAttach = em.getReference(taxasDescCollectionTaxasDescToAttach.getClass(), taxasDescCollectionTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollection.add(taxasDescCollectionTaxasDescToAttach);
            }
            descontos.setTaxasDescCollection(attachedTaxasDescCollection);
            em.persist(descontos);
            for (TaxasDesc taxasDescCollectionTaxasDesc : descontos.getTaxasDescCollection()) {
                Descontos oldDescontosOfTaxasDescCollectionTaxasDesc = taxasDescCollectionTaxasDesc.getDescontos();
                taxasDescCollectionTaxasDesc.setDescontos(descontos);
                taxasDescCollectionTaxasDesc = em.merge(taxasDescCollectionTaxasDesc);
                if (oldDescontosOfTaxasDescCollectionTaxasDesc != null) {
                    oldDescontosOfTaxasDescCollectionTaxasDesc.getTaxasDescCollection().remove(taxasDescCollectionTaxasDesc);
                    oldDescontosOfTaxasDescCollectionTaxasDesc = em.merge(oldDescontosOfTaxasDescCollectionTaxasDesc);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDescontos(descontos.getDesconto()) != null) {
                throw new PreexistingEntityException("Descontos " + descontos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Descontos descontos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Descontos persistentDescontos = em.find(Descontos.class, descontos.getDesconto());
            Collection<TaxasDesc> taxasDescCollectionOld = persistentDescontos.getTaxasDescCollection();
            Collection<TaxasDesc> taxasDescCollectionNew = descontos.getTaxasDescCollection();
            List<String> illegalOrphanMessages = null;
            for (TaxasDesc taxasDescCollectionOldTaxasDesc : taxasDescCollectionOld) {
                if (!taxasDescCollectionNew.contains(taxasDescCollectionOldTaxasDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TaxasDesc " + taxasDescCollectionOldTaxasDesc + " since its descontos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TaxasDesc> attachedTaxasDescCollectionNew = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollectionNewTaxasDescToAttach : taxasDescCollectionNew) {
                taxasDescCollectionNewTaxasDescToAttach = em.getReference(taxasDescCollectionNewTaxasDescToAttach.getClass(), taxasDescCollectionNewTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollectionNew.add(taxasDescCollectionNewTaxasDescToAttach);
            }
            taxasDescCollectionNew = attachedTaxasDescCollectionNew;
            descontos.setTaxasDescCollection(taxasDescCollectionNew);
            descontos = em.merge(descontos);
            for (TaxasDesc taxasDescCollectionNewTaxasDesc : taxasDescCollectionNew) {
                if (!taxasDescCollectionOld.contains(taxasDescCollectionNewTaxasDesc)) {
                    Descontos oldDescontosOfTaxasDescCollectionNewTaxasDesc = taxasDescCollectionNewTaxasDesc.getDescontos();
                    taxasDescCollectionNewTaxasDesc.setDescontos(descontos);
                    taxasDescCollectionNewTaxasDesc = em.merge(taxasDescCollectionNewTaxasDesc);
                    if (oldDescontosOfTaxasDescCollectionNewTaxasDesc != null && !oldDescontosOfTaxasDescCollectionNewTaxasDesc.equals(descontos)) {
                        oldDescontosOfTaxasDescCollectionNewTaxasDesc.getTaxasDescCollection().remove(taxasDescCollectionNewTaxasDesc);
                        oldDescontosOfTaxasDescCollectionNewTaxasDesc = em.merge(oldDescontosOfTaxasDescCollectionNewTaxasDesc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = descontos.getDesconto();
                if (findDescontos(id) == null) {
                    throw new NonexistentEntityException("The descontos with id " + id + " no longer exists.");
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
            Descontos descontos;
            try {
                descontos = em.getReference(Descontos.class, id);
                descontos.getDesconto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descontos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TaxasDesc> taxasDescCollectionOrphanCheck = descontos.getTaxasDescCollection();
            for (TaxasDesc taxasDescCollectionOrphanCheckTaxasDesc : taxasDescCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Descontos (" + descontos + ") cannot be destroyed since the TaxasDesc " + taxasDescCollectionOrphanCheckTaxasDesc + " in its taxasDescCollection field has a non-nullable descontos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(descontos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Descontos> findDescontosEntities() {
        return findDescontosEntities(true, -1, -1);
    }

    public List<Descontos> findDescontosEntities(int maxResults, int firstResult) {
        return findDescontosEntities(false, maxResults, firstResult);
    }

    private List<Descontos> findDescontosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Descontos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Descontos findDescontos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Descontos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescontosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Descontos as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
