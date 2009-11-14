/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Docs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.AnosFiscais;
import entidades.Series;
import entidades.TiposDoc;
import entidades.Utilizadores;
import entidades.HistoricoDocs;
import entidades.MovStock;
import java.util.ArrayList;
import java.util.Collection;
import entidades.DocLinhas;

/**
 *
 * @author User
 */
public class DocsJpaController {

    public DocsJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docs docs) throws PreexistingEntityException, Exception {
        if (docs.getMovStockCollection() == null) {
            docs.setMovStockCollection(new ArrayList<MovStock>());
        }
        if (docs.getDocLinhasCollection() == null) {
            docs.setDocLinhasCollection(new ArrayList<DocLinhas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnosFiscais ano = docs.getAno();
            if (ano != null) {
                ano = em.getReference(ano.getClass(), ano.getAno());
                docs.setAno(ano);
            }
            Series serie = docs.getSerie();
            if (serie != null) {
                serie = em.getReference(serie.getClass(), serie.getSerie());
                docs.setSerie(serie);
            }
            TiposDoc tipoDoc = docs.getTipoDoc();
            if (tipoDoc != null) {
                tipoDoc = em.getReference(tipoDoc.getClass(), tipoDoc.getTipoDoc());
                docs.setTipoDoc(tipoDoc);
            }
            Utilizadores userAlteracao = docs.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                docs.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = docs.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                docs.setUserCriacao(userCriacao);
            }
            HistoricoDocs historicoDocs = docs.getHistoricoDocs();
            if (historicoDocs != null) {
                historicoDocs = em.getReference(historicoDocs.getClass(), historicoDocs.getIdDoc());
                docs.setHistoricoDocs(historicoDocs);
            }
            Collection<MovStock> attachedMovStockCollection = new ArrayList<MovStock>();
            for (MovStock movStockCollectionMovStockToAttach : docs.getMovStockCollection()) {
                movStockCollectionMovStockToAttach = em.getReference(movStockCollectionMovStockToAttach.getClass(), movStockCollectionMovStockToAttach.getIdMovStock());
                attachedMovStockCollection.add(movStockCollectionMovStockToAttach);
            }
            docs.setMovStockCollection(attachedMovStockCollection);
            Collection<DocLinhas> attachedDocLinhasCollection = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionDocLinhasToAttach : docs.getDocLinhasCollection()) {
                docLinhasCollectionDocLinhasToAttach = em.getReference(docLinhasCollectionDocLinhasToAttach.getClass(), docLinhasCollectionDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollection.add(docLinhasCollectionDocLinhasToAttach);
            }
            docs.setDocLinhasCollection(attachedDocLinhasCollection);
            em.persist(docs);
            if (ano != null) {
                ano.getDocsCollection().add(docs);
                ano = em.merge(ano);
            }
            if (serie != null) {
                serie.getDocsCollection().add(docs);
                serie = em.merge(serie);
            }
            if (tipoDoc != null) {
                tipoDoc.getDocsCollection().add(docs);
                tipoDoc = em.merge(tipoDoc);
            }
            if (userAlteracao != null) {
                userAlteracao.getDocsCollection().add(docs);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getDocsCollection().add(docs);
                userCriacao = em.merge(userCriacao);
            }
            if (historicoDocs != null) {
                Docs oldDocsOfHistoricoDocs = historicoDocs.getDocs();
                if (oldDocsOfHistoricoDocs != null) {
                    oldDocsOfHistoricoDocs.setHistoricoDocs(null);
                    oldDocsOfHistoricoDocs = em.merge(oldDocsOfHistoricoDocs);
                }
                historicoDocs.setDocs(docs);
                historicoDocs = em.merge(historicoDocs);
            }
            for (MovStock movStockCollectionMovStock : docs.getMovStockCollection()) {
                Docs oldIdDocOfMovStockCollectionMovStock = movStockCollectionMovStock.getIdDoc();
                movStockCollectionMovStock.setIdDoc(docs);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
                if (oldIdDocOfMovStockCollectionMovStock != null) {
                    oldIdDocOfMovStockCollectionMovStock.getMovStockCollection().remove(movStockCollectionMovStock);
                    oldIdDocOfMovStockCollectionMovStock = em.merge(oldIdDocOfMovStockCollectionMovStock);
                }
            }
            for (DocLinhas docLinhasCollectionDocLinhas : docs.getDocLinhasCollection()) {
                Docs oldDocsOfDocLinhasCollectionDocLinhas = docLinhasCollectionDocLinhas.getDocs();
                docLinhasCollectionDocLinhas.setDocs(docs);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
                if (oldDocsOfDocLinhasCollectionDocLinhas != null) {
                    oldDocsOfDocLinhasCollectionDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionDocLinhas);
                    oldDocsOfDocLinhasCollectionDocLinhas = em.merge(oldDocsOfDocLinhasCollectionDocLinhas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocs(docs.getIdDoc()) != null) {
                throw new PreexistingEntityException("Docs " + docs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docs docs) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docs persistentDocs = em.find(Docs.class, docs.getIdDoc());
            AnosFiscais anoOld = persistentDocs.getAno();
            AnosFiscais anoNew = docs.getAno();
            Series serieOld = persistentDocs.getSerie();
            Series serieNew = docs.getSerie();
            TiposDoc tipoDocOld = persistentDocs.getTipoDoc();
            TiposDoc tipoDocNew = docs.getTipoDoc();
            Utilizadores userAlteracaoOld = persistentDocs.getUserAlteracao();
            Utilizadores userAlteracaoNew = docs.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentDocs.getUserCriacao();
            Utilizadores userCriacaoNew = docs.getUserCriacao();
            HistoricoDocs historicoDocsOld = persistentDocs.getHistoricoDocs();
            HistoricoDocs historicoDocsNew = docs.getHistoricoDocs();
            Collection<MovStock> movStockCollectionOld = persistentDocs.getMovStockCollection();
            Collection<MovStock> movStockCollectionNew = docs.getMovStockCollection();
            Collection<DocLinhas> docLinhasCollectionOld = persistentDocs.getDocLinhasCollection();
            Collection<DocLinhas> docLinhasCollectionNew = docs.getDocLinhasCollection();
            List<String> illegalOrphanMessages = null;
            if (historicoDocsOld != null && !historicoDocsOld.equals(historicoDocsNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain HistoricoDocs " + historicoDocsOld + " since its docs field is not nullable.");
            }
            for (DocLinhas docLinhasCollectionOldDocLinhas : docLinhasCollectionOld) {
                if (!docLinhasCollectionNew.contains(docLinhasCollectionOldDocLinhas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DocLinhas " + docLinhasCollectionOldDocLinhas + " since its docs field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (anoNew != null) {
                anoNew = em.getReference(anoNew.getClass(), anoNew.getAno());
                docs.setAno(anoNew);
            }
            if (serieNew != null) {
                serieNew = em.getReference(serieNew.getClass(), serieNew.getSerie());
                docs.setSerie(serieNew);
            }
            if (tipoDocNew != null) {
                tipoDocNew = em.getReference(tipoDocNew.getClass(), tipoDocNew.getTipoDoc());
                docs.setTipoDoc(tipoDocNew);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                docs.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                docs.setUserCriacao(userCriacaoNew);
            }
            if (historicoDocsNew != null) {
                historicoDocsNew = em.getReference(historicoDocsNew.getClass(), historicoDocsNew.getIdDoc());
                docs.setHistoricoDocs(historicoDocsNew);
            }
            Collection<MovStock> attachedMovStockCollectionNew = new ArrayList<MovStock>();
            for (MovStock movStockCollectionNewMovStockToAttach : movStockCollectionNew) {
                movStockCollectionNewMovStockToAttach = em.getReference(movStockCollectionNewMovStockToAttach.getClass(), movStockCollectionNewMovStockToAttach.getIdMovStock());
                attachedMovStockCollectionNew.add(movStockCollectionNewMovStockToAttach);
            }
            movStockCollectionNew = attachedMovStockCollectionNew;
            docs.setMovStockCollection(movStockCollectionNew);
            Collection<DocLinhas> attachedDocLinhasCollectionNew = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionNewDocLinhasToAttach : docLinhasCollectionNew) {
                docLinhasCollectionNewDocLinhasToAttach = em.getReference(docLinhasCollectionNewDocLinhasToAttach.getClass(), docLinhasCollectionNewDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollectionNew.add(docLinhasCollectionNewDocLinhasToAttach);
            }
            docLinhasCollectionNew = attachedDocLinhasCollectionNew;
            docs.setDocLinhasCollection(docLinhasCollectionNew);
            docs = em.merge(docs);
            if (anoOld != null && !anoOld.equals(anoNew)) {
                anoOld.getDocsCollection().remove(docs);
                anoOld = em.merge(anoOld);
            }
            if (anoNew != null && !anoNew.equals(anoOld)) {
                anoNew.getDocsCollection().add(docs);
                anoNew = em.merge(anoNew);
            }
            if (serieOld != null && !serieOld.equals(serieNew)) {
                serieOld.getDocsCollection().remove(docs);
                serieOld = em.merge(serieOld);
            }
            if (serieNew != null && !serieNew.equals(serieOld)) {
                serieNew.getDocsCollection().add(docs);
                serieNew = em.merge(serieNew);
            }
            if (tipoDocOld != null && !tipoDocOld.equals(tipoDocNew)) {
                tipoDocOld.getDocsCollection().remove(docs);
                tipoDocOld = em.merge(tipoDocOld);
            }
            if (tipoDocNew != null && !tipoDocNew.equals(tipoDocOld)) {
                tipoDocNew.getDocsCollection().add(docs);
                tipoDocNew = em.merge(tipoDocNew);
            }
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getDocsCollection().remove(docs);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getDocsCollection().add(docs);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getDocsCollection().remove(docs);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getDocsCollection().add(docs);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            if (historicoDocsNew != null && !historicoDocsNew.equals(historicoDocsOld)) {
                Docs oldDocsOfHistoricoDocs = historicoDocsNew.getDocs();
                if (oldDocsOfHistoricoDocs != null) {
                    oldDocsOfHistoricoDocs.setHistoricoDocs(null);
                    oldDocsOfHistoricoDocs = em.merge(oldDocsOfHistoricoDocs);
                }
                historicoDocsNew.setDocs(docs);
                historicoDocsNew = em.merge(historicoDocsNew);
            }
            for (MovStock movStockCollectionOldMovStock : movStockCollectionOld) {
                if (!movStockCollectionNew.contains(movStockCollectionOldMovStock)) {
                    movStockCollectionOldMovStock.setIdDoc(null);
                    movStockCollectionOldMovStock = em.merge(movStockCollectionOldMovStock);
                }
            }
            for (MovStock movStockCollectionNewMovStock : movStockCollectionNew) {
                if (!movStockCollectionOld.contains(movStockCollectionNewMovStock)) {
                    Docs oldIdDocOfMovStockCollectionNewMovStock = movStockCollectionNewMovStock.getIdDoc();
                    movStockCollectionNewMovStock.setIdDoc(docs);
                    movStockCollectionNewMovStock = em.merge(movStockCollectionNewMovStock);
                    if (oldIdDocOfMovStockCollectionNewMovStock != null && !oldIdDocOfMovStockCollectionNewMovStock.equals(docs)) {
                        oldIdDocOfMovStockCollectionNewMovStock.getMovStockCollection().remove(movStockCollectionNewMovStock);
                        oldIdDocOfMovStockCollectionNewMovStock = em.merge(oldIdDocOfMovStockCollectionNewMovStock);
                    }
                }
            }
            for (DocLinhas docLinhasCollectionNewDocLinhas : docLinhasCollectionNew) {
                if (!docLinhasCollectionOld.contains(docLinhasCollectionNewDocLinhas)) {
                    Docs oldDocsOfDocLinhasCollectionNewDocLinhas = docLinhasCollectionNewDocLinhas.getDocs();
                    docLinhasCollectionNewDocLinhas.setDocs(docs);
                    docLinhasCollectionNewDocLinhas = em.merge(docLinhasCollectionNewDocLinhas);
                    if (oldDocsOfDocLinhasCollectionNewDocLinhas != null && !oldDocsOfDocLinhasCollectionNewDocLinhas.equals(docs)) {
                        oldDocsOfDocLinhasCollectionNewDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionNewDocLinhas);
                        oldDocsOfDocLinhasCollectionNewDocLinhas = em.merge(oldDocsOfDocLinhasCollectionNewDocLinhas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = docs.getIdDoc();
                if (findDocs(id) == null) {
                    throw new NonexistentEntityException("The docs with id " + id + " no longer exists.");
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
            Docs docs;
            try {
                docs = em.getReference(Docs.class, id);
                docs.getIdDoc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docs with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            HistoricoDocs historicoDocsOrphanCheck = docs.getHistoricoDocs();
            if (historicoDocsOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docs (" + docs + ") cannot be destroyed since the HistoricoDocs " + historicoDocsOrphanCheck + " in its historicoDocs field has a non-nullable docs field.");
            }
            Collection<DocLinhas> docLinhasCollectionOrphanCheck = docs.getDocLinhasCollection();
            for (DocLinhas docLinhasCollectionOrphanCheckDocLinhas : docLinhasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docs (" + docs + ") cannot be destroyed since the DocLinhas " + docLinhasCollectionOrphanCheckDocLinhas + " in its docLinhasCollection field has a non-nullable docs field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            AnosFiscais ano = docs.getAno();
            if (ano != null) {
                ano.getDocsCollection().remove(docs);
                ano = em.merge(ano);
            }
            Series serie = docs.getSerie();
            if (serie != null) {
                serie.getDocsCollection().remove(docs);
                serie = em.merge(serie);
            }
            TiposDoc tipoDoc = docs.getTipoDoc();
            if (tipoDoc != null) {
                tipoDoc.getDocsCollection().remove(docs);
                tipoDoc = em.merge(tipoDoc);
            }
            Utilizadores userAlteracao = docs.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getDocsCollection().remove(docs);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = docs.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getDocsCollection().remove(docs);
                userCriacao = em.merge(userCriacao);
            }
            Collection<MovStock> movStockCollection = docs.getMovStockCollection();
            for (MovStock movStockCollectionMovStock : movStockCollection) {
                movStockCollectionMovStock.setIdDoc(null);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
            }
            em.remove(docs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docs> findDocsEntities() {
        return findDocsEntities(true, -1, -1);
    }

    public List<Docs> findDocsEntities(int maxResults, int firstResult) {
        return findDocsEntities(false, maxResults, firstResult);
    }

    private List<Docs> findDocsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Docs as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Docs findDocs(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docs.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Docs as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
