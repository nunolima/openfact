/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Ivas;
import entidades.IvasPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.DocLinhas;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Parametros;

/**
 *
 * @author User
 */
public class IvasJpaController {

    public IvasJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ivas ivas) throws PreexistingEntityException, Exception {
        if (ivas.getIvasPK() == null) {
            ivas.setIvasPK(new IvasPK());
        }
        if (ivas.getDocLinhasCollection() == null) {
            ivas.setDocLinhasCollection(new ArrayList<DocLinhas>());
        }
        if (ivas.getParametrosCollection() == null) {
            ivas.setParametrosCollection(new ArrayList<Parametros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DocLinhas> attachedDocLinhasCollection = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionDocLinhasToAttach : ivas.getDocLinhasCollection()) {
                docLinhasCollectionDocLinhasToAttach = em.getReference(docLinhasCollectionDocLinhasToAttach.getClass(), docLinhasCollectionDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollection.add(docLinhasCollectionDocLinhasToAttach);
            }
            ivas.setDocLinhasCollection(attachedDocLinhasCollection);
            Collection<Parametros> attachedParametrosCollection = new ArrayList<Parametros>();
            for (Parametros parametrosCollectionParametrosToAttach : ivas.getParametrosCollection()) {
                parametrosCollectionParametrosToAttach = em.getReference(parametrosCollectionParametrosToAttach.getClass(), parametrosCollectionParametrosToAttach.getIdParam());
                attachedParametrosCollection.add(parametrosCollectionParametrosToAttach);
            }
            ivas.setParametrosCollection(attachedParametrosCollection);
            em.persist(ivas);
            for (DocLinhas docLinhasCollectionDocLinhas : ivas.getDocLinhasCollection()) {
                Ivas oldIvasOfDocLinhasCollectionDocLinhas = docLinhasCollectionDocLinhas.getIvas();
                docLinhasCollectionDocLinhas.setIvas(ivas);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
                if (oldIvasOfDocLinhasCollectionDocLinhas != null) {
                    oldIvasOfDocLinhasCollectionDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionDocLinhas);
                    oldIvasOfDocLinhasCollectionDocLinhas = em.merge(oldIvasOfDocLinhasCollectionDocLinhas);
                }
            }
            for (Parametros parametrosCollectionParametros : ivas.getParametrosCollection()) {
                Ivas oldIvasOfParametrosCollectionParametros = parametrosCollectionParametros.getIvas();
                parametrosCollectionParametros.setIvas(ivas);
                parametrosCollectionParametros = em.merge(parametrosCollectionParametros);
                if (oldIvasOfParametrosCollectionParametros != null) {
                    oldIvasOfParametrosCollectionParametros.getParametrosCollection().remove(parametrosCollectionParametros);
                    oldIvasOfParametrosCollectionParametros = em.merge(oldIvasOfParametrosCollectionParametros);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIvas(ivas.getIvasPK()) != null) {
                throw new PreexistingEntityException("Ivas " + ivas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ivas ivas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ivas persistentIvas = em.find(Ivas.class, ivas.getIvasPK());
            Collection<DocLinhas> docLinhasCollectionOld = persistentIvas.getDocLinhasCollection();
            Collection<DocLinhas> docLinhasCollectionNew = ivas.getDocLinhasCollection();
            Collection<Parametros> parametrosCollectionOld = persistentIvas.getParametrosCollection();
            Collection<Parametros> parametrosCollectionNew = ivas.getParametrosCollection();
            Collection<DocLinhas> attachedDocLinhasCollectionNew = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionNewDocLinhasToAttach : docLinhasCollectionNew) {
                docLinhasCollectionNewDocLinhasToAttach = em.getReference(docLinhasCollectionNewDocLinhasToAttach.getClass(), docLinhasCollectionNewDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollectionNew.add(docLinhasCollectionNewDocLinhasToAttach);
            }
            docLinhasCollectionNew = attachedDocLinhasCollectionNew;
            ivas.setDocLinhasCollection(docLinhasCollectionNew);
            Collection<Parametros> attachedParametrosCollectionNew = new ArrayList<Parametros>();
            for (Parametros parametrosCollectionNewParametrosToAttach : parametrosCollectionNew) {
                parametrosCollectionNewParametrosToAttach = em.getReference(parametrosCollectionNewParametrosToAttach.getClass(), parametrosCollectionNewParametrosToAttach.getIdParam());
                attachedParametrosCollectionNew.add(parametrosCollectionNewParametrosToAttach);
            }
            parametrosCollectionNew = attachedParametrosCollectionNew;
            ivas.setParametrosCollection(parametrosCollectionNew);
            ivas = em.merge(ivas);
            for (DocLinhas docLinhasCollectionOldDocLinhas : docLinhasCollectionOld) {
                if (!docLinhasCollectionNew.contains(docLinhasCollectionOldDocLinhas)) {
                    docLinhasCollectionOldDocLinhas.setIvas(null);
                    docLinhasCollectionOldDocLinhas = em.merge(docLinhasCollectionOldDocLinhas);
                }
            }
            for (DocLinhas docLinhasCollectionNewDocLinhas : docLinhasCollectionNew) {
                if (!docLinhasCollectionOld.contains(docLinhasCollectionNewDocLinhas)) {
                    Ivas oldIvasOfDocLinhasCollectionNewDocLinhas = docLinhasCollectionNewDocLinhas.getIvas();
                    docLinhasCollectionNewDocLinhas.setIvas(ivas);
                    docLinhasCollectionNewDocLinhas = em.merge(docLinhasCollectionNewDocLinhas);
                    if (oldIvasOfDocLinhasCollectionNewDocLinhas != null && !oldIvasOfDocLinhasCollectionNewDocLinhas.equals(ivas)) {
                        oldIvasOfDocLinhasCollectionNewDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionNewDocLinhas);
                        oldIvasOfDocLinhasCollectionNewDocLinhas = em.merge(oldIvasOfDocLinhasCollectionNewDocLinhas);
                    }
                }
            }
            for (Parametros parametrosCollectionOldParametros : parametrosCollectionOld) {
                if (!parametrosCollectionNew.contains(parametrosCollectionOldParametros)) {
                    parametrosCollectionOldParametros.setIvas(null);
                    parametrosCollectionOldParametros = em.merge(parametrosCollectionOldParametros);
                }
            }
            for (Parametros parametrosCollectionNewParametros : parametrosCollectionNew) {
                if (!parametrosCollectionOld.contains(parametrosCollectionNewParametros)) {
                    Ivas oldIvasOfParametrosCollectionNewParametros = parametrosCollectionNewParametros.getIvas();
                    parametrosCollectionNewParametros.setIvas(ivas);
                    parametrosCollectionNewParametros = em.merge(parametrosCollectionNewParametros);
                    if (oldIvasOfParametrosCollectionNewParametros != null && !oldIvasOfParametrosCollectionNewParametros.equals(ivas)) {
                        oldIvasOfParametrosCollectionNewParametros.getParametrosCollection().remove(parametrosCollectionNewParametros);
                        oldIvasOfParametrosCollectionNewParametros = em.merge(oldIvasOfParametrosCollectionNewParametros);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                IvasPK id = ivas.getIvasPK();
                if (findIvas(id) == null) {
                    throw new NonexistentEntityException("The ivas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IvasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ivas ivas;
            try {
                ivas = em.getReference(Ivas.class, id);
                ivas.getIvasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ivas with id " + id + " no longer exists.", enfe);
            }
            Collection<DocLinhas> docLinhasCollection = ivas.getDocLinhasCollection();
            for (DocLinhas docLinhasCollectionDocLinhas : docLinhasCollection) {
                docLinhasCollectionDocLinhas.setIvas(null);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
            }
            Collection<Parametros> parametrosCollection = ivas.getParametrosCollection();
            for (Parametros parametrosCollectionParametros : parametrosCollection) {
                parametrosCollectionParametros.setIvas(null);
                parametrosCollectionParametros = em.merge(parametrosCollectionParametros);
            }
            em.remove(ivas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ivas> findIvasEntities() {
        return findIvasEntities(true, -1, -1);
    }

    public List<Ivas> findIvasEntities(int maxResults, int firstResult) {
        return findIvasEntities(false, maxResults, firstResult);
    }

    private List<Ivas> findIvasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Ivas as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ivas findIvas(IvasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ivas.class, id);
        } finally {
            em.close();
        }
    }

    public int getIvasCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Ivas as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
