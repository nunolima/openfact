/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Series;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Docs;
import java.util.ArrayList;
import java.util.Collection;
import entidades.NrDocs;

/**
 *
 * @author User
 */
public class SeriesJpaController {

    public SeriesJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Series series) throws PreexistingEntityException, Exception {
        if (series.getDocsCollection() == null) {
            series.setDocsCollection(new ArrayList<Docs>());
        }
        if (series.getNrDocsCollection() == null) {
            series.setNrDocsCollection(new ArrayList<NrDocs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Docs> attachedDocsCollection = new ArrayList<Docs>();
            for (Docs docsCollectionDocsToAttach : series.getDocsCollection()) {
                docsCollectionDocsToAttach = em.getReference(docsCollectionDocsToAttach.getClass(), docsCollectionDocsToAttach.getIdDoc());
                attachedDocsCollection.add(docsCollectionDocsToAttach);
            }
            series.setDocsCollection(attachedDocsCollection);
            Collection<NrDocs> attachedNrDocsCollection = new ArrayList<NrDocs>();
            for (NrDocs nrDocsCollectionNrDocsToAttach : series.getNrDocsCollection()) {
                nrDocsCollectionNrDocsToAttach = em.getReference(nrDocsCollectionNrDocsToAttach.getClass(), nrDocsCollectionNrDocsToAttach.getNrDocsPK());
                attachedNrDocsCollection.add(nrDocsCollectionNrDocsToAttach);
            }
            series.setNrDocsCollection(attachedNrDocsCollection);
            em.persist(series);
            for (Docs docsCollectionDocs : series.getDocsCollection()) {
                Series oldSerieOfDocsCollectionDocs = docsCollectionDocs.getSerie();
                docsCollectionDocs.setSerie(series);
                docsCollectionDocs = em.merge(docsCollectionDocs);
                if (oldSerieOfDocsCollectionDocs != null) {
                    oldSerieOfDocsCollectionDocs.getDocsCollection().remove(docsCollectionDocs);
                    oldSerieOfDocsCollectionDocs = em.merge(oldSerieOfDocsCollectionDocs);
                }
            }
            for (NrDocs nrDocsCollectionNrDocs : series.getNrDocsCollection()) {
                Series oldSeriesOfNrDocsCollectionNrDocs = nrDocsCollectionNrDocs.getSeries();
                nrDocsCollectionNrDocs.setSeries(series);
                nrDocsCollectionNrDocs = em.merge(nrDocsCollectionNrDocs);
                if (oldSeriesOfNrDocsCollectionNrDocs != null) {
                    oldSeriesOfNrDocsCollectionNrDocs.getNrDocsCollection().remove(nrDocsCollectionNrDocs);
                    oldSeriesOfNrDocsCollectionNrDocs = em.merge(oldSeriesOfNrDocsCollectionNrDocs);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeries(series.getSerie()) != null) {
                throw new PreexistingEntityException("Series " + series + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Series series) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Series persistentSeries = em.find(Series.class, series.getSerie());
            Collection<Docs> docsCollectionOld = persistentSeries.getDocsCollection();
            Collection<Docs> docsCollectionNew = series.getDocsCollection();
            Collection<NrDocs> nrDocsCollectionOld = persistentSeries.getNrDocsCollection();
            Collection<NrDocs> nrDocsCollectionNew = series.getNrDocsCollection();
            List<String> illegalOrphanMessages = null;
            for (Docs docsCollectionOldDocs : docsCollectionOld) {
                if (!docsCollectionNew.contains(docsCollectionOldDocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docs " + docsCollectionOldDocs + " since its serie field is not nullable.");
                }
            }
            for (NrDocs nrDocsCollectionOldNrDocs : nrDocsCollectionOld) {
                if (!nrDocsCollectionNew.contains(nrDocsCollectionOldNrDocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NrDocs " + nrDocsCollectionOldNrDocs + " since its series field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Docs> attachedDocsCollectionNew = new ArrayList<Docs>();
            for (Docs docsCollectionNewDocsToAttach : docsCollectionNew) {
                docsCollectionNewDocsToAttach = em.getReference(docsCollectionNewDocsToAttach.getClass(), docsCollectionNewDocsToAttach.getIdDoc());
                attachedDocsCollectionNew.add(docsCollectionNewDocsToAttach);
            }
            docsCollectionNew = attachedDocsCollectionNew;
            series.setDocsCollection(docsCollectionNew);
            Collection<NrDocs> attachedNrDocsCollectionNew = new ArrayList<NrDocs>();
            for (NrDocs nrDocsCollectionNewNrDocsToAttach : nrDocsCollectionNew) {
                nrDocsCollectionNewNrDocsToAttach = em.getReference(nrDocsCollectionNewNrDocsToAttach.getClass(), nrDocsCollectionNewNrDocsToAttach.getNrDocsPK());
                attachedNrDocsCollectionNew.add(nrDocsCollectionNewNrDocsToAttach);
            }
            nrDocsCollectionNew = attachedNrDocsCollectionNew;
            series.setNrDocsCollection(nrDocsCollectionNew);
            series = em.merge(series);
            for (Docs docsCollectionNewDocs : docsCollectionNew) {
                if (!docsCollectionOld.contains(docsCollectionNewDocs)) {
                    Series oldSerieOfDocsCollectionNewDocs = docsCollectionNewDocs.getSerie();
                    docsCollectionNewDocs.setSerie(series);
                    docsCollectionNewDocs = em.merge(docsCollectionNewDocs);
                    if (oldSerieOfDocsCollectionNewDocs != null && !oldSerieOfDocsCollectionNewDocs.equals(series)) {
                        oldSerieOfDocsCollectionNewDocs.getDocsCollection().remove(docsCollectionNewDocs);
                        oldSerieOfDocsCollectionNewDocs = em.merge(oldSerieOfDocsCollectionNewDocs);
                    }
                }
            }
            for (NrDocs nrDocsCollectionNewNrDocs : nrDocsCollectionNew) {
                if (!nrDocsCollectionOld.contains(nrDocsCollectionNewNrDocs)) {
                    Series oldSeriesOfNrDocsCollectionNewNrDocs = nrDocsCollectionNewNrDocs.getSeries();
                    nrDocsCollectionNewNrDocs.setSeries(series);
                    nrDocsCollectionNewNrDocs = em.merge(nrDocsCollectionNewNrDocs);
                    if (oldSeriesOfNrDocsCollectionNewNrDocs != null && !oldSeriesOfNrDocsCollectionNewNrDocs.equals(series)) {
                        oldSeriesOfNrDocsCollectionNewNrDocs.getNrDocsCollection().remove(nrDocsCollectionNewNrDocs);
                        oldSeriesOfNrDocsCollectionNewNrDocs = em.merge(oldSeriesOfNrDocsCollectionNewNrDocs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = series.getSerie();
                if (findSeries(id) == null) {
                    throw new NonexistentEntityException("The series with id " + id + " no longer exists.");
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
            Series series;
            try {
                series = em.getReference(Series.class, id);
                series.getSerie();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The series with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Docs> docsCollectionOrphanCheck = series.getDocsCollection();
            for (Docs docsCollectionOrphanCheckDocs : docsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Series (" + series + ") cannot be destroyed since the Docs " + docsCollectionOrphanCheckDocs + " in its docsCollection field has a non-nullable serie field.");
            }
            Collection<NrDocs> nrDocsCollectionOrphanCheck = series.getNrDocsCollection();
            for (NrDocs nrDocsCollectionOrphanCheckNrDocs : nrDocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Series (" + series + ") cannot be destroyed since the NrDocs " + nrDocsCollectionOrphanCheckNrDocs + " in its nrDocsCollection field has a non-nullable series field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(series);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Series> findSeriesEntities() {
        return findSeriesEntities(true, -1, -1);
    }

    public List<Series> findSeriesEntities(int maxResults, int firstResult) {
        return findSeriesEntities(false, maxResults, firstResult);
    }

    private List<Series> findSeriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Series as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Series findSeries(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Series.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeriesCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Series as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
