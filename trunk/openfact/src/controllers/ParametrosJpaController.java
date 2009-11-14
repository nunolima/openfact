/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Parametros;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.AnosFiscais;
import entidades.Ivas;

/**
 *
 * @author User
 */
public class ParametrosJpaController {

    public ParametrosJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Parametros parametros) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnosFiscais anoFiscal = parametros.getAnoFiscal();
            if (anoFiscal != null) {
                anoFiscal = em.getReference(anoFiscal.getClass(), anoFiscal.getAno());
                parametros.setAnoFiscal(anoFiscal);
            }
            Ivas ivas = parametros.getIvas();
            if (ivas != null) {
                ivas = em.getReference(ivas.getClass(), ivas.getIvasPK());
                parametros.setIvas(ivas);
            }
            em.persist(parametros);
            if (anoFiscal != null) {
                anoFiscal.getParametrosCollection().add(parametros);
                anoFiscal = em.merge(anoFiscal);
            }
            if (ivas != null) {
                ivas.getParametrosCollection().add(parametros);
                ivas = em.merge(ivas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findParametros(parametros.getIdParam()) != null) {
                throw new PreexistingEntityException("Parametros " + parametros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Parametros parametros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Parametros persistentParametros = em.find(Parametros.class, parametros.getIdParam());
            AnosFiscais anoFiscalOld = persistentParametros.getAnoFiscal();
            AnosFiscais anoFiscalNew = parametros.getAnoFiscal();
            Ivas ivasOld = persistentParametros.getIvas();
            Ivas ivasNew = parametros.getIvas();
            if (anoFiscalNew != null) {
                anoFiscalNew = em.getReference(anoFiscalNew.getClass(), anoFiscalNew.getAno());
                parametros.setAnoFiscal(anoFiscalNew);
            }
            if (ivasNew != null) {
                ivasNew = em.getReference(ivasNew.getClass(), ivasNew.getIvasPK());
                parametros.setIvas(ivasNew);
            }
            parametros = em.merge(parametros);
            if (anoFiscalOld != null && !anoFiscalOld.equals(anoFiscalNew)) {
                anoFiscalOld.getParametrosCollection().remove(parametros);
                anoFiscalOld = em.merge(anoFiscalOld);
            }
            if (anoFiscalNew != null && !anoFiscalNew.equals(anoFiscalOld)) {
                anoFiscalNew.getParametrosCollection().add(parametros);
                anoFiscalNew = em.merge(anoFiscalNew);
            }
            if (ivasOld != null && !ivasOld.equals(ivasNew)) {
                ivasOld.getParametrosCollection().remove(parametros);
                ivasOld = em.merge(ivasOld);
            }
            if (ivasNew != null && !ivasNew.equals(ivasOld)) {
                ivasNew.getParametrosCollection().add(parametros);
                ivasNew = em.merge(ivasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parametros.getIdParam();
                if (findParametros(id) == null) {
                    throw new NonexistentEntityException("The parametros with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Parametros parametros;
            try {
                parametros = em.getReference(Parametros.class, id);
                parametros.getIdParam();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parametros with id " + id + " no longer exists.", enfe);
            }
            AnosFiscais anoFiscal = parametros.getAnoFiscal();
            if (anoFiscal != null) {
                anoFiscal.getParametrosCollection().remove(parametros);
                anoFiscal = em.merge(anoFiscal);
            }
            Ivas ivas = parametros.getIvas();
            if (ivas != null) {
                ivas.getParametrosCollection().remove(parametros);
                ivas = em.merge(ivas);
            }
            em.remove(parametros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Parametros> findParametrosEntities() {
        return findParametrosEntities(true, -1, -1);
    }

    public List<Parametros> findParametrosEntities(int maxResults, int firstResult) {
        return findParametrosEntities(false, maxResults, firstResult);
    }

    private List<Parametros> findParametrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Parametros as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Parametros findParametros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Parametros.class, id);
        } finally {
            em.close();
        }
    }

    public int getParametrosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Parametros as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
