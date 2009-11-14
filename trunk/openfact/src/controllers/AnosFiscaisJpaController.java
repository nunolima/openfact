/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.AnosFiscais;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Utilizadores;
import entidades.Docs;
import java.util.ArrayList;
import java.util.Collection;
import entidades.NrDocs;
import entidades.Parametros;

/**
 *
 * @author User
 */
public class AnosFiscaisJpaController {

    public AnosFiscaisJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AnosFiscais anosFiscais) throws PreexistingEntityException, Exception {
        if (anosFiscais.getDocsCollection() == null) {
            anosFiscais.setDocsCollection(new ArrayList<Docs>());
        }
        if (anosFiscais.getNrDocsCollection() == null) {
            anosFiscais.setNrDocsCollection(new ArrayList<NrDocs>());
        }
        if (anosFiscais.getParametrosCollection() == null) {
            anosFiscais.setParametrosCollection(new ArrayList<Parametros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilizadores userAlteracao = anosFiscais.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                anosFiscais.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = anosFiscais.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                anosFiscais.setUserCriacao(userCriacao);
            }
            Collection<Docs> attachedDocsCollection = new ArrayList<Docs>();
            for (Docs docsCollectionDocsToAttach : anosFiscais.getDocsCollection()) {
                docsCollectionDocsToAttach = em.getReference(docsCollectionDocsToAttach.getClass(), docsCollectionDocsToAttach.getIdDoc());
                attachedDocsCollection.add(docsCollectionDocsToAttach);
            }
            anosFiscais.setDocsCollection(attachedDocsCollection);
            Collection<NrDocs> attachedNrDocsCollection = new ArrayList<NrDocs>();
            for (NrDocs nrDocsCollectionNrDocsToAttach : anosFiscais.getNrDocsCollection()) {
                nrDocsCollectionNrDocsToAttach = em.getReference(nrDocsCollectionNrDocsToAttach.getClass(), nrDocsCollectionNrDocsToAttach.getNrDocsPK());
                attachedNrDocsCollection.add(nrDocsCollectionNrDocsToAttach);
            }
            anosFiscais.setNrDocsCollection(attachedNrDocsCollection);
            Collection<Parametros> attachedParametrosCollection = new ArrayList<Parametros>();
            for (Parametros parametrosCollectionParametrosToAttach : anosFiscais.getParametrosCollection()) {
                parametrosCollectionParametrosToAttach = em.getReference(parametrosCollectionParametrosToAttach.getClass(), parametrosCollectionParametrosToAttach.getIdParam());
                attachedParametrosCollection.add(parametrosCollectionParametrosToAttach);
            }
            anosFiscais.setParametrosCollection(attachedParametrosCollection);
            em.persist(anosFiscais);
            if (userAlteracao != null) {
                userAlteracao.getAnosFiscaisCollection().add(anosFiscais);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getAnosFiscaisCollection().add(anosFiscais);
                userCriacao = em.merge(userCriacao);
            }
            for (Docs docsCollectionDocs : anosFiscais.getDocsCollection()) {
                AnosFiscais oldAnoOfDocsCollectionDocs = docsCollectionDocs.getAno();
                docsCollectionDocs.setAno(anosFiscais);
                docsCollectionDocs = em.merge(docsCollectionDocs);
                if (oldAnoOfDocsCollectionDocs != null) {
                    oldAnoOfDocsCollectionDocs.getDocsCollection().remove(docsCollectionDocs);
                    oldAnoOfDocsCollectionDocs = em.merge(oldAnoOfDocsCollectionDocs);
                }
            }
            for (NrDocs nrDocsCollectionNrDocs : anosFiscais.getNrDocsCollection()) {
                AnosFiscais oldAnosFiscaisOfNrDocsCollectionNrDocs = nrDocsCollectionNrDocs.getAnosFiscais();
                nrDocsCollectionNrDocs.setAnosFiscais(anosFiscais);
                nrDocsCollectionNrDocs = em.merge(nrDocsCollectionNrDocs);
                if (oldAnosFiscaisOfNrDocsCollectionNrDocs != null) {
                    oldAnosFiscaisOfNrDocsCollectionNrDocs.getNrDocsCollection().remove(nrDocsCollectionNrDocs);
                    oldAnosFiscaisOfNrDocsCollectionNrDocs = em.merge(oldAnosFiscaisOfNrDocsCollectionNrDocs);
                }
            }
            for (Parametros parametrosCollectionParametros : anosFiscais.getParametrosCollection()) {
                AnosFiscais oldAnoFiscalOfParametrosCollectionParametros = parametrosCollectionParametros.getAnoFiscal();
                parametrosCollectionParametros.setAnoFiscal(anosFiscais);
                parametrosCollectionParametros = em.merge(parametrosCollectionParametros);
                if (oldAnoFiscalOfParametrosCollectionParametros != null) {
                    oldAnoFiscalOfParametrosCollectionParametros.getParametrosCollection().remove(parametrosCollectionParametros);
                    oldAnoFiscalOfParametrosCollectionParametros = em.merge(oldAnoFiscalOfParametrosCollectionParametros);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnosFiscais(anosFiscais.getAno()) != null) {
                throw new PreexistingEntityException("AnosFiscais " + anosFiscais + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AnosFiscais anosFiscais) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnosFiscais persistentAnosFiscais = em.find(AnosFiscais.class, anosFiscais.getAno());
            Utilizadores userAlteracaoOld = persistentAnosFiscais.getUserAlteracao();
            Utilizadores userAlteracaoNew = anosFiscais.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentAnosFiscais.getUserCriacao();
            Utilizadores userCriacaoNew = anosFiscais.getUserCriacao();
            Collection<Docs> docsCollectionOld = persistentAnosFiscais.getDocsCollection();
            Collection<Docs> docsCollectionNew = anosFiscais.getDocsCollection();
            Collection<NrDocs> nrDocsCollectionOld = persistentAnosFiscais.getNrDocsCollection();
            Collection<NrDocs> nrDocsCollectionNew = anosFiscais.getNrDocsCollection();
            Collection<Parametros> parametrosCollectionOld = persistentAnosFiscais.getParametrosCollection();
            Collection<Parametros> parametrosCollectionNew = anosFiscais.getParametrosCollection();
            List<String> illegalOrphanMessages = null;
            for (NrDocs nrDocsCollectionOldNrDocs : nrDocsCollectionOld) {
                if (!nrDocsCollectionNew.contains(nrDocsCollectionOldNrDocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NrDocs " + nrDocsCollectionOldNrDocs + " since its anosFiscais field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                anosFiscais.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                anosFiscais.setUserCriacao(userCriacaoNew);
            }
            Collection<Docs> attachedDocsCollectionNew = new ArrayList<Docs>();
            for (Docs docsCollectionNewDocsToAttach : docsCollectionNew) {
                docsCollectionNewDocsToAttach = em.getReference(docsCollectionNewDocsToAttach.getClass(), docsCollectionNewDocsToAttach.getIdDoc());
                attachedDocsCollectionNew.add(docsCollectionNewDocsToAttach);
            }
            docsCollectionNew = attachedDocsCollectionNew;
            anosFiscais.setDocsCollection(docsCollectionNew);
            Collection<NrDocs> attachedNrDocsCollectionNew = new ArrayList<NrDocs>();
            for (NrDocs nrDocsCollectionNewNrDocsToAttach : nrDocsCollectionNew) {
                nrDocsCollectionNewNrDocsToAttach = em.getReference(nrDocsCollectionNewNrDocsToAttach.getClass(), nrDocsCollectionNewNrDocsToAttach.getNrDocsPK());
                attachedNrDocsCollectionNew.add(nrDocsCollectionNewNrDocsToAttach);
            }
            nrDocsCollectionNew = attachedNrDocsCollectionNew;
            anosFiscais.setNrDocsCollection(nrDocsCollectionNew);
            Collection<Parametros> attachedParametrosCollectionNew = new ArrayList<Parametros>();
            for (Parametros parametrosCollectionNewParametrosToAttach : parametrosCollectionNew) {
                parametrosCollectionNewParametrosToAttach = em.getReference(parametrosCollectionNewParametrosToAttach.getClass(), parametrosCollectionNewParametrosToAttach.getIdParam());
                attachedParametrosCollectionNew.add(parametrosCollectionNewParametrosToAttach);
            }
            parametrosCollectionNew = attachedParametrosCollectionNew;
            anosFiscais.setParametrosCollection(parametrosCollectionNew);
            anosFiscais = em.merge(anosFiscais);
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getAnosFiscaisCollection().remove(anosFiscais);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getAnosFiscaisCollection().add(anosFiscais);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getAnosFiscaisCollection().remove(anosFiscais);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getAnosFiscaisCollection().add(anosFiscais);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            for (Docs docsCollectionOldDocs : docsCollectionOld) {
                if (!docsCollectionNew.contains(docsCollectionOldDocs)) {
                    docsCollectionOldDocs.setAno(null);
                    docsCollectionOldDocs = em.merge(docsCollectionOldDocs);
                }
            }
            for (Docs docsCollectionNewDocs : docsCollectionNew) {
                if (!docsCollectionOld.contains(docsCollectionNewDocs)) {
                    AnosFiscais oldAnoOfDocsCollectionNewDocs = docsCollectionNewDocs.getAno();
                    docsCollectionNewDocs.setAno(anosFiscais);
                    docsCollectionNewDocs = em.merge(docsCollectionNewDocs);
                    if (oldAnoOfDocsCollectionNewDocs != null && !oldAnoOfDocsCollectionNewDocs.equals(anosFiscais)) {
                        oldAnoOfDocsCollectionNewDocs.getDocsCollection().remove(docsCollectionNewDocs);
                        oldAnoOfDocsCollectionNewDocs = em.merge(oldAnoOfDocsCollectionNewDocs);
                    }
                }
            }
            for (NrDocs nrDocsCollectionNewNrDocs : nrDocsCollectionNew) {
                if (!nrDocsCollectionOld.contains(nrDocsCollectionNewNrDocs)) {
                    AnosFiscais oldAnosFiscaisOfNrDocsCollectionNewNrDocs = nrDocsCollectionNewNrDocs.getAnosFiscais();
                    nrDocsCollectionNewNrDocs.setAnosFiscais(anosFiscais);
                    nrDocsCollectionNewNrDocs = em.merge(nrDocsCollectionNewNrDocs);
                    if (oldAnosFiscaisOfNrDocsCollectionNewNrDocs != null && !oldAnosFiscaisOfNrDocsCollectionNewNrDocs.equals(anosFiscais)) {
                        oldAnosFiscaisOfNrDocsCollectionNewNrDocs.getNrDocsCollection().remove(nrDocsCollectionNewNrDocs);
                        oldAnosFiscaisOfNrDocsCollectionNewNrDocs = em.merge(oldAnosFiscaisOfNrDocsCollectionNewNrDocs);
                    }
                }
            }
            for (Parametros parametrosCollectionOldParametros : parametrosCollectionOld) {
                if (!parametrosCollectionNew.contains(parametrosCollectionOldParametros)) {
                    parametrosCollectionOldParametros.setAnoFiscal(null);
                    parametrosCollectionOldParametros = em.merge(parametrosCollectionOldParametros);
                }
            }
            for (Parametros parametrosCollectionNewParametros : parametrosCollectionNew) {
                if (!parametrosCollectionOld.contains(parametrosCollectionNewParametros)) {
                    AnosFiscais oldAnoFiscalOfParametrosCollectionNewParametros = parametrosCollectionNewParametros.getAnoFiscal();
                    parametrosCollectionNewParametros.setAnoFiscal(anosFiscais);
                    parametrosCollectionNewParametros = em.merge(parametrosCollectionNewParametros);
                    if (oldAnoFiscalOfParametrosCollectionNewParametros != null && !oldAnoFiscalOfParametrosCollectionNewParametros.equals(anosFiscais)) {
                        oldAnoFiscalOfParametrosCollectionNewParametros.getParametrosCollection().remove(parametrosCollectionNewParametros);
                        oldAnoFiscalOfParametrosCollectionNewParametros = em.merge(oldAnoFiscalOfParametrosCollectionNewParametros);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = anosFiscais.getAno();
                if (findAnosFiscais(id) == null) {
                    throw new NonexistentEntityException("The anosFiscais with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnosFiscais anosFiscais;
            try {
                anosFiscais = em.getReference(AnosFiscais.class, id);
                anosFiscais.getAno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anosFiscais with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<NrDocs> nrDocsCollectionOrphanCheck = anosFiscais.getNrDocsCollection();
            for (NrDocs nrDocsCollectionOrphanCheckNrDocs : nrDocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AnosFiscais (" + anosFiscais + ") cannot be destroyed since the NrDocs " + nrDocsCollectionOrphanCheckNrDocs + " in its nrDocsCollection field has a non-nullable anosFiscais field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Utilizadores userAlteracao = anosFiscais.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getAnosFiscaisCollection().remove(anosFiscais);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = anosFiscais.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getAnosFiscaisCollection().remove(anosFiscais);
                userCriacao = em.merge(userCriacao);
            }
            Collection<Docs> docsCollection = anosFiscais.getDocsCollection();
            for (Docs docsCollectionDocs : docsCollection) {
                docsCollectionDocs.setAno(null);
                docsCollectionDocs = em.merge(docsCollectionDocs);
            }
            Collection<Parametros> parametrosCollection = anosFiscais.getParametrosCollection();
            for (Parametros parametrosCollectionParametros : parametrosCollection) {
                parametrosCollectionParametros.setAnoFiscal(null);
                parametrosCollectionParametros = em.merge(parametrosCollectionParametros);
            }
            em.remove(anosFiscais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AnosFiscais> findAnosFiscaisEntities() {
        return findAnosFiscaisEntities(true, -1, -1);
    }

    public List<AnosFiscais> findAnosFiscaisEntities(int maxResults, int firstResult) {
        return findAnosFiscaisEntities(false, maxResults, firstResult);
    }

    private List<AnosFiscais> findAnosFiscaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AnosFiscais as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AnosFiscais findAnosFiscais(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AnosFiscais.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnosFiscaisCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from AnosFiscais as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
