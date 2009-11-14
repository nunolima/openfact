/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.NrDocs;
import entidades.NrDocsPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.AnosFiscais;
import entidades.Series;
import entidades.TiposDoc;

/**
 *
 * @author User
 */
public class NrDocsJpaController {

    public NrDocsJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NrDocs nrDocs) throws PreexistingEntityException, Exception {
        if (nrDocs.getNrDocsPK() == null) {
            nrDocs.setNrDocsPK(new NrDocsPK());
        }
        nrDocs.getNrDocsPK().setTipoDoc(nrDocs.getTiposDoc().getTipoDoc());
        nrDocs.getNrDocsPK().setSerie(nrDocs.getSeries().getSerie());
        nrDocs.getNrDocsPK().setAno(nrDocs.getAnosFiscais().getAno());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnosFiscais anosFiscais = nrDocs.getAnosFiscais();
            if (anosFiscais != null) {
                anosFiscais = em.getReference(anosFiscais.getClass(), anosFiscais.getAno());
                nrDocs.setAnosFiscais(anosFiscais);
            }
            Series series = nrDocs.getSeries();
            if (series != null) {
                series = em.getReference(series.getClass(), series.getSerie());
                nrDocs.setSeries(series);
            }
            TiposDoc tiposDoc = nrDocs.getTiposDoc();
            if (tiposDoc != null) {
                tiposDoc = em.getReference(tiposDoc.getClass(), tiposDoc.getTipoDoc());
                nrDocs.setTiposDoc(tiposDoc);
            }
            em.persist(nrDocs);
            if (anosFiscais != null) {
                anosFiscais.getNrDocsCollection().add(nrDocs);
                anosFiscais = em.merge(anosFiscais);
            }
            if (series != null) {
                series.getNrDocsCollection().add(nrDocs);
                series = em.merge(series);
            }
            if (tiposDoc != null) {
                tiposDoc.getNrDocsCollection().add(nrDocs);
                tiposDoc = em.merge(tiposDoc);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNrDocs(nrDocs.getNrDocsPK()) != null) {
                throw new PreexistingEntityException("NrDocs " + nrDocs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NrDocs nrDocs) throws NonexistentEntityException, Exception {
        nrDocs.getNrDocsPK().setTipoDoc(nrDocs.getTiposDoc().getTipoDoc());
        nrDocs.getNrDocsPK().setSerie(nrDocs.getSeries().getSerie());
        nrDocs.getNrDocsPK().setAno(nrDocs.getAnosFiscais().getAno());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NrDocs persistentNrDocs = em.find(NrDocs.class, nrDocs.getNrDocsPK());
            AnosFiscais anosFiscaisOld = persistentNrDocs.getAnosFiscais();
            AnosFiscais anosFiscaisNew = nrDocs.getAnosFiscais();
            Series seriesOld = persistentNrDocs.getSeries();
            Series seriesNew = nrDocs.getSeries();
            TiposDoc tiposDocOld = persistentNrDocs.getTiposDoc();
            TiposDoc tiposDocNew = nrDocs.getTiposDoc();
            if (anosFiscaisNew != null) {
                anosFiscaisNew = em.getReference(anosFiscaisNew.getClass(), anosFiscaisNew.getAno());
                nrDocs.setAnosFiscais(anosFiscaisNew);
            }
            if (seriesNew != null) {
                seriesNew = em.getReference(seriesNew.getClass(), seriesNew.getSerie());
                nrDocs.setSeries(seriesNew);
            }
            if (tiposDocNew != null) {
                tiposDocNew = em.getReference(tiposDocNew.getClass(), tiposDocNew.getTipoDoc());
                nrDocs.setTiposDoc(tiposDocNew);
            }
            nrDocs = em.merge(nrDocs);
            if (anosFiscaisOld != null && !anosFiscaisOld.equals(anosFiscaisNew)) {
                anosFiscaisOld.getNrDocsCollection().remove(nrDocs);
                anosFiscaisOld = em.merge(anosFiscaisOld);
            }
            if (anosFiscaisNew != null && !anosFiscaisNew.equals(anosFiscaisOld)) {
                anosFiscaisNew.getNrDocsCollection().add(nrDocs);
                anosFiscaisNew = em.merge(anosFiscaisNew);
            }
            if (seriesOld != null && !seriesOld.equals(seriesNew)) {
                seriesOld.getNrDocsCollection().remove(nrDocs);
                seriesOld = em.merge(seriesOld);
            }
            if (seriesNew != null && !seriesNew.equals(seriesOld)) {
                seriesNew.getNrDocsCollection().add(nrDocs);
                seriesNew = em.merge(seriesNew);
            }
            if (tiposDocOld != null && !tiposDocOld.equals(tiposDocNew)) {
                tiposDocOld.getNrDocsCollection().remove(nrDocs);
                tiposDocOld = em.merge(tiposDocOld);
            }
            if (tiposDocNew != null && !tiposDocNew.equals(tiposDocOld)) {
                tiposDocNew.getNrDocsCollection().add(nrDocs);
                tiposDocNew = em.merge(tiposDocNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                NrDocsPK id = nrDocs.getNrDocsPK();
                if (findNrDocs(id) == null) {
                    throw new NonexistentEntityException("The nrDocs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(NrDocsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NrDocs nrDocs;
            try {
                nrDocs = em.getReference(NrDocs.class, id);
                nrDocs.getNrDocsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nrDocs with id " + id + " no longer exists.", enfe);
            }
            AnosFiscais anosFiscais = nrDocs.getAnosFiscais();
            if (anosFiscais != null) {
                anosFiscais.getNrDocsCollection().remove(nrDocs);
                anosFiscais = em.merge(anosFiscais);
            }
            Series series = nrDocs.getSeries();
            if (series != null) {
                series.getNrDocsCollection().remove(nrDocs);
                series = em.merge(series);
            }
            TiposDoc tiposDoc = nrDocs.getTiposDoc();
            if (tiposDoc != null) {
                tiposDoc.getNrDocsCollection().remove(nrDocs);
                tiposDoc = em.merge(tiposDoc);
            }
            em.remove(nrDocs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NrDocs> findNrDocsEntities() {
        return findNrDocsEntities(true, -1, -1);
    }

    public List<NrDocs> findNrDocsEntities(int maxResults, int firstResult) {
        return findNrDocsEntities(false, maxResults, firstResult);
    }

    private List<NrDocs> findNrDocsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from NrDocs as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NrDocs findNrDocs(NrDocsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NrDocs.class, id);
        } finally {
            em.close();
        }
    }

    public int getNrDocsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from NrDocs as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
