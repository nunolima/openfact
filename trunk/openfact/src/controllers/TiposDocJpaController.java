/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TiposMovStock;
import entidades.Utilizadores;
import entidades.TiposDoc;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Docs;
import entidades.NrDocs;

/**
 *
 * @author User
 */
public class TiposDocJpaController {

    public TiposDocJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposDoc tiposDoc) throws PreexistingEntityException, Exception {
        if (tiposDoc.getTiposDocCollection() == null) {
            tiposDoc.setTiposDocCollection(new ArrayList<TiposDoc>());
        }
        if (tiposDoc.getTiposDocCollection1() == null) {
            tiposDoc.setTiposDocCollection1(new ArrayList<TiposDoc>());
        }
        if (tiposDoc.getDocsCollection() == null) {
            tiposDoc.setDocsCollection(new ArrayList<Docs>());
        }
        if (tiposDoc.getNrDocsCollection() == null) {
            tiposDoc.setNrDocsCollection(new ArrayList<NrDocs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposMovStock tipoMovStock = tiposDoc.getTipoMovStock();
            if (tipoMovStock != null) {
                tipoMovStock = em.getReference(tipoMovStock.getClass(), tipoMovStock.getTipoMovStock());
                tiposDoc.setTipoMovStock(tipoMovStock);
            }
            Utilizadores userAlteracao = tiposDoc.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                tiposDoc.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = tiposDoc.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                tiposDoc.setUserCriacao(userCriacao);
            }
            Collection<TiposDoc> attachedTiposDocCollection = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollectionTiposDocToAttach : tiposDoc.getTiposDocCollection()) {
                tiposDocCollectionTiposDocToAttach = em.getReference(tiposDocCollectionTiposDocToAttach.getClass(), tiposDocCollectionTiposDocToAttach.getTipoDoc());
                attachedTiposDocCollection.add(tiposDocCollectionTiposDocToAttach);
            }
            tiposDoc.setTiposDocCollection(attachedTiposDocCollection);
            Collection<TiposDoc> attachedTiposDocCollection1 = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollection1TiposDocToAttach : tiposDoc.getTiposDocCollection1()) {
                tiposDocCollection1TiposDocToAttach = em.getReference(tiposDocCollection1TiposDocToAttach.getClass(), tiposDocCollection1TiposDocToAttach.getTipoDoc());
                attachedTiposDocCollection1.add(tiposDocCollection1TiposDocToAttach);
            }
            tiposDoc.setTiposDocCollection1(attachedTiposDocCollection1);
            Collection<Docs> attachedDocsCollection = new ArrayList<Docs>();
            for (Docs docsCollectionDocsToAttach : tiposDoc.getDocsCollection()) {
                docsCollectionDocsToAttach = em.getReference(docsCollectionDocsToAttach.getClass(), docsCollectionDocsToAttach.getIdDoc());
                attachedDocsCollection.add(docsCollectionDocsToAttach);
            }
            tiposDoc.setDocsCollection(attachedDocsCollection);
            Collection<NrDocs> attachedNrDocsCollection = new ArrayList<NrDocs>();
            for (NrDocs nrDocsCollectionNrDocsToAttach : tiposDoc.getNrDocsCollection()) {
                nrDocsCollectionNrDocsToAttach = em.getReference(nrDocsCollectionNrDocsToAttach.getClass(), nrDocsCollectionNrDocsToAttach.getNrDocsPK());
                attachedNrDocsCollection.add(nrDocsCollectionNrDocsToAttach);
            }
            tiposDoc.setNrDocsCollection(attachedNrDocsCollection);
            em.persist(tiposDoc);
            if (tipoMovStock != null) {
                tipoMovStock.getTiposDocCollection().add(tiposDoc);
                tipoMovStock = em.merge(tipoMovStock);
            }
            if (userAlteracao != null) {
                userAlteracao.getTiposDocCollection().add(tiposDoc);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getTiposDocCollection().add(tiposDoc);
                userCriacao = em.merge(userCriacao);
            }
            for (TiposDoc tiposDocCollectionTiposDoc : tiposDoc.getTiposDocCollection()) {
                tiposDocCollectionTiposDoc.getTiposDocCollection().add(tiposDoc);
                tiposDocCollectionTiposDoc = em.merge(tiposDocCollectionTiposDoc);
            }
            for (TiposDoc tiposDocCollection1TiposDoc : tiposDoc.getTiposDocCollection1()) {
                tiposDocCollection1TiposDoc.getTiposDocCollection().add(tiposDoc);
                tiposDocCollection1TiposDoc = em.merge(tiposDocCollection1TiposDoc);
            }
            for (Docs docsCollectionDocs : tiposDoc.getDocsCollection()) {
                TiposDoc oldTipoDocOfDocsCollectionDocs = docsCollectionDocs.getTipoDoc();
                docsCollectionDocs.setTipoDoc(tiposDoc);
                docsCollectionDocs = em.merge(docsCollectionDocs);
                if (oldTipoDocOfDocsCollectionDocs != null) {
                    oldTipoDocOfDocsCollectionDocs.getDocsCollection().remove(docsCollectionDocs);
                    oldTipoDocOfDocsCollectionDocs = em.merge(oldTipoDocOfDocsCollectionDocs);
                }
            }
            for (NrDocs nrDocsCollectionNrDocs : tiposDoc.getNrDocsCollection()) {
                TiposDoc oldTiposDocOfNrDocsCollectionNrDocs = nrDocsCollectionNrDocs.getTiposDoc();
                nrDocsCollectionNrDocs.setTiposDoc(tiposDoc);
                nrDocsCollectionNrDocs = em.merge(nrDocsCollectionNrDocs);
                if (oldTiposDocOfNrDocsCollectionNrDocs != null) {
                    oldTiposDocOfNrDocsCollectionNrDocs.getNrDocsCollection().remove(nrDocsCollectionNrDocs);
                    oldTiposDocOfNrDocsCollectionNrDocs = em.merge(oldTiposDocOfNrDocsCollectionNrDocs);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiposDoc(tiposDoc.getTipoDoc()) != null) {
                throw new PreexistingEntityException("TiposDoc " + tiposDoc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposDoc tiposDoc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposDoc persistentTiposDoc = em.find(TiposDoc.class, tiposDoc.getTipoDoc());
            TiposMovStock tipoMovStockOld = persistentTiposDoc.getTipoMovStock();
            TiposMovStock tipoMovStockNew = tiposDoc.getTipoMovStock();
            Utilizadores userAlteracaoOld = persistentTiposDoc.getUserAlteracao();
            Utilizadores userAlteracaoNew = tiposDoc.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentTiposDoc.getUserCriacao();
            Utilizadores userCriacaoNew = tiposDoc.getUserCriacao();
            Collection<TiposDoc> tiposDocCollectionOld = persistentTiposDoc.getTiposDocCollection();
            Collection<TiposDoc> tiposDocCollectionNew = tiposDoc.getTiposDocCollection();
            Collection<TiposDoc> tiposDocCollection1Old = persistentTiposDoc.getTiposDocCollection1();
            Collection<TiposDoc> tiposDocCollection1New = tiposDoc.getTiposDocCollection1();
            Collection<Docs> docsCollectionOld = persistentTiposDoc.getDocsCollection();
            Collection<Docs> docsCollectionNew = tiposDoc.getDocsCollection();
            Collection<NrDocs> nrDocsCollectionOld = persistentTiposDoc.getNrDocsCollection();
            Collection<NrDocs> nrDocsCollectionNew = tiposDoc.getNrDocsCollection();
            List<String> illegalOrphanMessages = null;
            for (Docs docsCollectionOldDocs : docsCollectionOld) {
                if (!docsCollectionNew.contains(docsCollectionOldDocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docs " + docsCollectionOldDocs + " since its tipoDoc field is not nullable.");
                }
            }
            for (NrDocs nrDocsCollectionOldNrDocs : nrDocsCollectionOld) {
                if (!nrDocsCollectionNew.contains(nrDocsCollectionOldNrDocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NrDocs " + nrDocsCollectionOldNrDocs + " since its tiposDoc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoMovStockNew != null) {
                tipoMovStockNew = em.getReference(tipoMovStockNew.getClass(), tipoMovStockNew.getTipoMovStock());
                tiposDoc.setTipoMovStock(tipoMovStockNew);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                tiposDoc.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                tiposDoc.setUserCriacao(userCriacaoNew);
            }
            Collection<TiposDoc> attachedTiposDocCollectionNew = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollectionNewTiposDocToAttach : tiposDocCollectionNew) {
                tiposDocCollectionNewTiposDocToAttach = em.getReference(tiposDocCollectionNewTiposDocToAttach.getClass(), tiposDocCollectionNewTiposDocToAttach.getTipoDoc());
                attachedTiposDocCollectionNew.add(tiposDocCollectionNewTiposDocToAttach);
            }
            tiposDocCollectionNew = attachedTiposDocCollectionNew;
            tiposDoc.setTiposDocCollection(tiposDocCollectionNew);
            Collection<TiposDoc> attachedTiposDocCollection1New = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollection1NewTiposDocToAttach : tiposDocCollection1New) {
                tiposDocCollection1NewTiposDocToAttach = em.getReference(tiposDocCollection1NewTiposDocToAttach.getClass(), tiposDocCollection1NewTiposDocToAttach.getTipoDoc());
                attachedTiposDocCollection1New.add(tiposDocCollection1NewTiposDocToAttach);
            }
            tiposDocCollection1New = attachedTiposDocCollection1New;
            tiposDoc.setTiposDocCollection1(tiposDocCollection1New);
            Collection<Docs> attachedDocsCollectionNew = new ArrayList<Docs>();
            for (Docs docsCollectionNewDocsToAttach : docsCollectionNew) {
                docsCollectionNewDocsToAttach = em.getReference(docsCollectionNewDocsToAttach.getClass(), docsCollectionNewDocsToAttach.getIdDoc());
                attachedDocsCollectionNew.add(docsCollectionNewDocsToAttach);
            }
            docsCollectionNew = attachedDocsCollectionNew;
            tiposDoc.setDocsCollection(docsCollectionNew);
            Collection<NrDocs> attachedNrDocsCollectionNew = new ArrayList<NrDocs>();
            for (NrDocs nrDocsCollectionNewNrDocsToAttach : nrDocsCollectionNew) {
                nrDocsCollectionNewNrDocsToAttach = em.getReference(nrDocsCollectionNewNrDocsToAttach.getClass(), nrDocsCollectionNewNrDocsToAttach.getNrDocsPK());
                attachedNrDocsCollectionNew.add(nrDocsCollectionNewNrDocsToAttach);
            }
            nrDocsCollectionNew = attachedNrDocsCollectionNew;
            tiposDoc.setNrDocsCollection(nrDocsCollectionNew);
            tiposDoc = em.merge(tiposDoc);
            if (tipoMovStockOld != null && !tipoMovStockOld.equals(tipoMovStockNew)) {
                tipoMovStockOld.getTiposDocCollection().remove(tiposDoc);
                tipoMovStockOld = em.merge(tipoMovStockOld);
            }
            if (tipoMovStockNew != null && !tipoMovStockNew.equals(tipoMovStockOld)) {
                tipoMovStockNew.getTiposDocCollection().add(tiposDoc);
                tipoMovStockNew = em.merge(tipoMovStockNew);
            }
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getTiposDocCollection().remove(tiposDoc);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getTiposDocCollection().add(tiposDoc);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getTiposDocCollection().remove(tiposDoc);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getTiposDocCollection().add(tiposDoc);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            for (TiposDoc tiposDocCollectionOldTiposDoc : tiposDocCollectionOld) {
                if (!tiposDocCollectionNew.contains(tiposDocCollectionOldTiposDoc)) {
                    tiposDocCollectionOldTiposDoc.getTiposDocCollection().remove(tiposDoc);
                    tiposDocCollectionOldTiposDoc = em.merge(tiposDocCollectionOldTiposDoc);
                }
            }
            for (TiposDoc tiposDocCollectionNewTiposDoc : tiposDocCollectionNew) {
                if (!tiposDocCollectionOld.contains(tiposDocCollectionNewTiposDoc)) {
                    tiposDocCollectionNewTiposDoc.getTiposDocCollection().add(tiposDoc);
                    tiposDocCollectionNewTiposDoc = em.merge(tiposDocCollectionNewTiposDoc);
                }
            }
            for (TiposDoc tiposDocCollection1OldTiposDoc : tiposDocCollection1Old) {
                if (!tiposDocCollection1New.contains(tiposDocCollection1OldTiposDoc)) {
                    tiposDocCollection1OldTiposDoc.getTiposDocCollection().remove(tiposDoc);
                    tiposDocCollection1OldTiposDoc = em.merge(tiposDocCollection1OldTiposDoc);
                }
            }
            for (TiposDoc tiposDocCollection1NewTiposDoc : tiposDocCollection1New) {
                if (!tiposDocCollection1Old.contains(tiposDocCollection1NewTiposDoc)) {
                    tiposDocCollection1NewTiposDoc.getTiposDocCollection().add(tiposDoc);
                    tiposDocCollection1NewTiposDoc = em.merge(tiposDocCollection1NewTiposDoc);
                }
            }
            for (Docs docsCollectionNewDocs : docsCollectionNew) {
                if (!docsCollectionOld.contains(docsCollectionNewDocs)) {
                    TiposDoc oldTipoDocOfDocsCollectionNewDocs = docsCollectionNewDocs.getTipoDoc();
                    docsCollectionNewDocs.setTipoDoc(tiposDoc);
                    docsCollectionNewDocs = em.merge(docsCollectionNewDocs);
                    if (oldTipoDocOfDocsCollectionNewDocs != null && !oldTipoDocOfDocsCollectionNewDocs.equals(tiposDoc)) {
                        oldTipoDocOfDocsCollectionNewDocs.getDocsCollection().remove(docsCollectionNewDocs);
                        oldTipoDocOfDocsCollectionNewDocs = em.merge(oldTipoDocOfDocsCollectionNewDocs);
                    }
                }
            }
            for (NrDocs nrDocsCollectionNewNrDocs : nrDocsCollectionNew) {
                if (!nrDocsCollectionOld.contains(nrDocsCollectionNewNrDocs)) {
                    TiposDoc oldTiposDocOfNrDocsCollectionNewNrDocs = nrDocsCollectionNewNrDocs.getTiposDoc();
                    nrDocsCollectionNewNrDocs.setTiposDoc(tiposDoc);
                    nrDocsCollectionNewNrDocs = em.merge(nrDocsCollectionNewNrDocs);
                    if (oldTiposDocOfNrDocsCollectionNewNrDocs != null && !oldTiposDocOfNrDocsCollectionNewNrDocs.equals(tiposDoc)) {
                        oldTiposDocOfNrDocsCollectionNewNrDocs.getNrDocsCollection().remove(nrDocsCollectionNewNrDocs);
                        oldTiposDocOfNrDocsCollectionNewNrDocs = em.merge(oldTiposDocOfNrDocsCollectionNewNrDocs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tiposDoc.getTipoDoc();
                if (findTiposDoc(id) == null) {
                    throw new NonexistentEntityException("The tiposDoc with id " + id + " no longer exists.");
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
            TiposDoc tiposDoc;
            try {
                tiposDoc = em.getReference(TiposDoc.class, id);
                tiposDoc.getTipoDoc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposDoc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Docs> docsCollectionOrphanCheck = tiposDoc.getDocsCollection();
            for (Docs docsCollectionOrphanCheckDocs : docsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TiposDoc (" + tiposDoc + ") cannot be destroyed since the Docs " + docsCollectionOrphanCheckDocs + " in its docsCollection field has a non-nullable tipoDoc field.");
            }
            Collection<NrDocs> nrDocsCollectionOrphanCheck = tiposDoc.getNrDocsCollection();
            for (NrDocs nrDocsCollectionOrphanCheckNrDocs : nrDocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TiposDoc (" + tiposDoc + ") cannot be destroyed since the NrDocs " + nrDocsCollectionOrphanCheckNrDocs + " in its nrDocsCollection field has a non-nullable tiposDoc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TiposMovStock tipoMovStock = tiposDoc.getTipoMovStock();
            if (tipoMovStock != null) {
                tipoMovStock.getTiposDocCollection().remove(tiposDoc);
                tipoMovStock = em.merge(tipoMovStock);
            }
            Utilizadores userAlteracao = tiposDoc.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getTiposDocCollection().remove(tiposDoc);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = tiposDoc.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getTiposDocCollection().remove(tiposDoc);
                userCriacao = em.merge(userCriacao);
            }
            Collection<TiposDoc> tiposDocCollection = tiposDoc.getTiposDocCollection();
            for (TiposDoc tiposDocCollectionTiposDoc : tiposDocCollection) {
                tiposDocCollectionTiposDoc.getTiposDocCollection().remove(tiposDoc);
                tiposDocCollectionTiposDoc = em.merge(tiposDocCollectionTiposDoc);
            }
            Collection<TiposDoc> tiposDocCollection1 = tiposDoc.getTiposDocCollection1();
            for (TiposDoc tiposDocCollection1TiposDoc : tiposDocCollection1) {
                tiposDocCollection1TiposDoc.getTiposDocCollection().remove(tiposDoc);
                tiposDocCollection1TiposDoc = em.merge(tiposDocCollection1TiposDoc);
            }
            em.remove(tiposDoc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposDoc> findTiposDocEntities() {
        return findTiposDocEntities(true, -1, -1);
    }

    public List<TiposDoc> findTiposDocEntities(int maxResults, int firstResult) {
        return findTiposDocEntities(false, maxResults, firstResult);
    }

    private List<TiposDoc> findTiposDocEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TiposDoc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TiposDoc findTiposDoc(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposDoc.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposDocCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TiposDoc as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
