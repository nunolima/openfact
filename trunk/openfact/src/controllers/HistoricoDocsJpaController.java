/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.HistoricoDocs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Docs;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class HistoricoDocsJpaController {

    public HistoricoDocsJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistoricoDocs historicoDocs) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Docs docsOrphanCheck = historicoDocs.getDocs();
        if (docsOrphanCheck != null) {
            HistoricoDocs oldHistoricoDocsOfDocs = docsOrphanCheck.getHistoricoDocs();
            if (oldHistoricoDocsOfDocs != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Docs " + docsOrphanCheck + " already has an item of type HistoricoDocs whose docs column cannot be null. Please make another selection for the docs field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docs docs = historicoDocs.getDocs();
            if (docs != null) {
                docs = em.getReference(docs.getClass(), docs.getIdDoc());
                historicoDocs.setDocs(docs);
            }
            em.persist(historicoDocs);
            if (docs != null) {
                docs.setHistoricoDocs(historicoDocs);
                docs = em.merge(docs);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistoricoDocs(historicoDocs.getIdDoc()) != null) {
                throw new PreexistingEntityException("HistoricoDocs " + historicoDocs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoricoDocs historicoDocs) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricoDocs persistentHistoricoDocs = em.find(HistoricoDocs.class, historicoDocs.getIdDoc());
            Docs docsOld = persistentHistoricoDocs.getDocs();
            Docs docsNew = historicoDocs.getDocs();
            List<String> illegalOrphanMessages = null;
            if (docsNew != null && !docsNew.equals(docsOld)) {
                HistoricoDocs oldHistoricoDocsOfDocs = docsNew.getHistoricoDocs();
                if (oldHistoricoDocsOfDocs != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Docs " + docsNew + " already has an item of type HistoricoDocs whose docs column cannot be null. Please make another selection for the docs field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (docsNew != null) {
                docsNew = em.getReference(docsNew.getClass(), docsNew.getIdDoc());
                historicoDocs.setDocs(docsNew);
            }
            historicoDocs = em.merge(historicoDocs);
            if (docsOld != null && !docsOld.equals(docsNew)) {
                docsOld.setHistoricoDocs(null);
                docsOld = em.merge(docsOld);
            }
            if (docsNew != null && !docsNew.equals(docsOld)) {
                docsNew.setHistoricoDocs(historicoDocs);
                docsNew = em.merge(docsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = historicoDocs.getIdDoc();
                if (findHistoricoDocs(id) == null) {
                    throw new NonexistentEntityException("The historicoDocs with id " + id + " no longer exists.");
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
            HistoricoDocs historicoDocs;
            try {
                historicoDocs = em.getReference(HistoricoDocs.class, id);
                historicoDocs.getIdDoc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicoDocs with id " + id + " no longer exists.", enfe);
            }
            Docs docs = historicoDocs.getDocs();
            if (docs != null) {
                docs.setHistoricoDocs(null);
                docs = em.merge(docs);
            }
            em.remove(historicoDocs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoricoDocs> findHistoricoDocsEntities() {
        return findHistoricoDocsEntities(true, -1, -1);
    }

    public List<HistoricoDocs> findHistoricoDocsEntities(int maxResults, int firstResult) {
        return findHistoricoDocsEntities(false, maxResults, firstResult);
    }

    private List<HistoricoDocs> findHistoricoDocsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from HistoricoDocs as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HistoricoDocs findHistoricoDocs(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoricoDocs.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoDocsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from HistoricoDocs as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
