/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.UnidMedida;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.DocLinhas;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Itens;

/**
 *
 * @author User
 */
public class UnidMedidaJpaController {

    public UnidMedidaJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidMedida unidMedida) throws PreexistingEntityException, Exception {
        if (unidMedida.getDocLinhasCollection() == null) {
            unidMedida.setDocLinhasCollection(new ArrayList<DocLinhas>());
        }
        if (unidMedida.getItensCollection() == null) {
            unidMedida.setItensCollection(new ArrayList<Itens>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DocLinhas> attachedDocLinhasCollection = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionDocLinhasToAttach : unidMedida.getDocLinhasCollection()) {
                docLinhasCollectionDocLinhasToAttach = em.getReference(docLinhasCollectionDocLinhasToAttach.getClass(), docLinhasCollectionDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollection.add(docLinhasCollectionDocLinhasToAttach);
            }
            unidMedida.setDocLinhasCollection(attachedDocLinhasCollection);
            Collection<Itens> attachedItensCollection = new ArrayList<Itens>();
            for (Itens itensCollectionItensToAttach : unidMedida.getItensCollection()) {
                itensCollectionItensToAttach = em.getReference(itensCollectionItensToAttach.getClass(), itensCollectionItensToAttach.getIdItem());
                attachedItensCollection.add(itensCollectionItensToAttach);
            }
            unidMedida.setItensCollection(attachedItensCollection);
            em.persist(unidMedida);
            for (DocLinhas docLinhasCollectionDocLinhas : unidMedida.getDocLinhasCollection()) {
                UnidMedida oldUnidMedidaOfDocLinhasCollectionDocLinhas = docLinhasCollectionDocLinhas.getUnidMedida();
                docLinhasCollectionDocLinhas.setUnidMedida(unidMedida);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
                if (oldUnidMedidaOfDocLinhasCollectionDocLinhas != null) {
                    oldUnidMedidaOfDocLinhasCollectionDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionDocLinhas);
                    oldUnidMedidaOfDocLinhasCollectionDocLinhas = em.merge(oldUnidMedidaOfDocLinhasCollectionDocLinhas);
                }
            }
            for (Itens itensCollectionItens : unidMedida.getItensCollection()) {
                UnidMedida oldUnidMedidaOfItensCollectionItens = itensCollectionItens.getUnidMedida();
                itensCollectionItens.setUnidMedida(unidMedida);
                itensCollectionItens = em.merge(itensCollectionItens);
                if (oldUnidMedidaOfItensCollectionItens != null) {
                    oldUnidMedidaOfItensCollectionItens.getItensCollection().remove(itensCollectionItens);
                    oldUnidMedidaOfItensCollectionItens = em.merge(oldUnidMedidaOfItensCollectionItens);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUnidMedida(unidMedida.getUnidMedida()) != null) {
                throw new PreexistingEntityException("UnidMedida " + unidMedida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidMedida unidMedida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidMedida persistentUnidMedida = em.find(UnidMedida.class, unidMedida.getUnidMedida());
            Collection<DocLinhas> docLinhasCollectionOld = persistentUnidMedida.getDocLinhasCollection();
            Collection<DocLinhas> docLinhasCollectionNew = unidMedida.getDocLinhasCollection();
            Collection<Itens> itensCollectionOld = persistentUnidMedida.getItensCollection();
            Collection<Itens> itensCollectionNew = unidMedida.getItensCollection();
            Collection<DocLinhas> attachedDocLinhasCollectionNew = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionNewDocLinhasToAttach : docLinhasCollectionNew) {
                docLinhasCollectionNewDocLinhasToAttach = em.getReference(docLinhasCollectionNewDocLinhasToAttach.getClass(), docLinhasCollectionNewDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollectionNew.add(docLinhasCollectionNewDocLinhasToAttach);
            }
            docLinhasCollectionNew = attachedDocLinhasCollectionNew;
            unidMedida.setDocLinhasCollection(docLinhasCollectionNew);
            Collection<Itens> attachedItensCollectionNew = new ArrayList<Itens>();
            for (Itens itensCollectionNewItensToAttach : itensCollectionNew) {
                itensCollectionNewItensToAttach = em.getReference(itensCollectionNewItensToAttach.getClass(), itensCollectionNewItensToAttach.getIdItem());
                attachedItensCollectionNew.add(itensCollectionNewItensToAttach);
            }
            itensCollectionNew = attachedItensCollectionNew;
            unidMedida.setItensCollection(itensCollectionNew);
            unidMedida = em.merge(unidMedida);
            for (DocLinhas docLinhasCollectionOldDocLinhas : docLinhasCollectionOld) {
                if (!docLinhasCollectionNew.contains(docLinhasCollectionOldDocLinhas)) {
                    docLinhasCollectionOldDocLinhas.setUnidMedida(null);
                    docLinhasCollectionOldDocLinhas = em.merge(docLinhasCollectionOldDocLinhas);
                }
            }
            for (DocLinhas docLinhasCollectionNewDocLinhas : docLinhasCollectionNew) {
                if (!docLinhasCollectionOld.contains(docLinhasCollectionNewDocLinhas)) {
                    UnidMedida oldUnidMedidaOfDocLinhasCollectionNewDocLinhas = docLinhasCollectionNewDocLinhas.getUnidMedida();
                    docLinhasCollectionNewDocLinhas.setUnidMedida(unidMedida);
                    docLinhasCollectionNewDocLinhas = em.merge(docLinhasCollectionNewDocLinhas);
                    if (oldUnidMedidaOfDocLinhasCollectionNewDocLinhas != null && !oldUnidMedidaOfDocLinhasCollectionNewDocLinhas.equals(unidMedida)) {
                        oldUnidMedidaOfDocLinhasCollectionNewDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionNewDocLinhas);
                        oldUnidMedidaOfDocLinhasCollectionNewDocLinhas = em.merge(oldUnidMedidaOfDocLinhasCollectionNewDocLinhas);
                    }
                }
            }
            for (Itens itensCollectionOldItens : itensCollectionOld) {
                if (!itensCollectionNew.contains(itensCollectionOldItens)) {
                    itensCollectionOldItens.setUnidMedida(null);
                    itensCollectionOldItens = em.merge(itensCollectionOldItens);
                }
            }
            for (Itens itensCollectionNewItens : itensCollectionNew) {
                if (!itensCollectionOld.contains(itensCollectionNewItens)) {
                    UnidMedida oldUnidMedidaOfItensCollectionNewItens = itensCollectionNewItens.getUnidMedida();
                    itensCollectionNewItens.setUnidMedida(unidMedida);
                    itensCollectionNewItens = em.merge(itensCollectionNewItens);
                    if (oldUnidMedidaOfItensCollectionNewItens != null && !oldUnidMedidaOfItensCollectionNewItens.equals(unidMedida)) {
                        oldUnidMedidaOfItensCollectionNewItens.getItensCollection().remove(itensCollectionNewItens);
                        oldUnidMedidaOfItensCollectionNewItens = em.merge(oldUnidMedidaOfItensCollectionNewItens);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = unidMedida.getUnidMedida();
                if (findUnidMedida(id) == null) {
                    throw new NonexistentEntityException("The unidMedida with id " + id + " no longer exists.");
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
            UnidMedida unidMedida;
            try {
                unidMedida = em.getReference(UnidMedida.class, id);
                unidMedida.getUnidMedida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidMedida with id " + id + " no longer exists.", enfe);
            }
            Collection<DocLinhas> docLinhasCollection = unidMedida.getDocLinhasCollection();
            for (DocLinhas docLinhasCollectionDocLinhas : docLinhasCollection) {
                docLinhasCollectionDocLinhas.setUnidMedida(null);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
            }
            Collection<Itens> itensCollection = unidMedida.getItensCollection();
            for (Itens itensCollectionItens : itensCollection) {
                itensCollectionItens.setUnidMedida(null);
                itensCollectionItens = em.merge(itensCollectionItens);
            }
            em.remove(unidMedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidMedida> findUnidMedidaEntities() {
        return findUnidMedidaEntities(true, -1, -1);
    }

    public List<UnidMedida> findUnidMedidaEntities(int maxResults, int firstResult) {
        return findUnidMedidaEntities(false, maxResults, firstResult);
    }

    private List<UnidMedida> findUnidMedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from UnidMedida as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UnidMedida findUnidMedida(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidMedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidMedidaCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from UnidMedida as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
