/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.RelEnt;
import entidades.RelEntPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Entidades;
import entidades.RelTiposEnt;

/**
 *
 * @author User
 */
public class RelEntJpaController {

    public RelEntJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RelEnt relEnt) throws PreexistingEntityException, Exception {
        if (relEnt.getRelEntPK() == null) {
            relEnt.setRelEntPK(new RelEntPK());
        }
        relEnt.getRelEntPK().setIdEnt(relEnt.getEntidades().getIdEnt());
        relEnt.getRelEntPK().setIdEntRel(relEnt.getEntidades1().getIdEnt());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidades entidades = relEnt.getEntidades();
            if (entidades != null) {
                entidades = em.getReference(entidades.getClass(), entidades.getIdEnt());
                relEnt.setEntidades(entidades);
            }
            Entidades entidades1 = relEnt.getEntidades1();
            if (entidades1 != null) {
                entidades1 = em.getReference(entidades1.getClass(), entidades1.getIdEnt());
                relEnt.setEntidades1(entidades1);
            }
            RelTiposEnt idRelacao = relEnt.getIdRelacao();
            if (idRelacao != null) {
                idRelacao = em.getReference(idRelacao.getClass(), idRelacao.getIdRelacao());
                relEnt.setIdRelacao(idRelacao);
            }
            em.persist(relEnt);
            if (entidades != null) {
                entidades.getRelEntCollection().add(relEnt);
                entidades = em.merge(entidades);
            }
            if (entidades1 != null) {
                entidades1.getRelEntCollection().add(relEnt);
                entidades1 = em.merge(entidades1);
            }
            if (idRelacao != null) {
                idRelacao.getRelEntCollection().add(relEnt);
                idRelacao = em.merge(idRelacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelEnt(relEnt.getRelEntPK()) != null) {
                throw new PreexistingEntityException("RelEnt " + relEnt + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RelEnt relEnt) throws NonexistentEntityException, Exception {
        relEnt.getRelEntPK().setIdEnt(relEnt.getEntidades().getIdEnt());
        relEnt.getRelEntPK().setIdEntRel(relEnt.getEntidades1().getIdEnt());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RelEnt persistentRelEnt = em.find(RelEnt.class, relEnt.getRelEntPK());
            Entidades entidadesOld = persistentRelEnt.getEntidades();
            Entidades entidadesNew = relEnt.getEntidades();
            Entidades entidades1Old = persistentRelEnt.getEntidades1();
            Entidades entidades1New = relEnt.getEntidades1();
            RelTiposEnt idRelacaoOld = persistentRelEnt.getIdRelacao();
            RelTiposEnt idRelacaoNew = relEnt.getIdRelacao();
            if (entidadesNew != null) {
                entidadesNew = em.getReference(entidadesNew.getClass(), entidadesNew.getIdEnt());
                relEnt.setEntidades(entidadesNew);
            }
            if (entidades1New != null) {
                entidades1New = em.getReference(entidades1New.getClass(), entidades1New.getIdEnt());
                relEnt.setEntidades1(entidades1New);
            }
            if (idRelacaoNew != null) {
                idRelacaoNew = em.getReference(idRelacaoNew.getClass(), idRelacaoNew.getIdRelacao());
                relEnt.setIdRelacao(idRelacaoNew);
            }
            relEnt = em.merge(relEnt);
            if (entidadesOld != null && !entidadesOld.equals(entidadesNew)) {
                entidadesOld.getRelEntCollection().remove(relEnt);
                entidadesOld = em.merge(entidadesOld);
            }
            if (entidadesNew != null && !entidadesNew.equals(entidadesOld)) {
                entidadesNew.getRelEntCollection().add(relEnt);
                entidadesNew = em.merge(entidadesNew);
            }
            if (entidades1Old != null && !entidades1Old.equals(entidades1New)) {
                entidades1Old.getRelEntCollection().remove(relEnt);
                entidades1Old = em.merge(entidades1Old);
            }
            if (entidades1New != null && !entidades1New.equals(entidades1Old)) {
                entidades1New.getRelEntCollection().add(relEnt);
                entidades1New = em.merge(entidades1New);
            }
            if (idRelacaoOld != null && !idRelacaoOld.equals(idRelacaoNew)) {
                idRelacaoOld.getRelEntCollection().remove(relEnt);
                idRelacaoOld = em.merge(idRelacaoOld);
            }
            if (idRelacaoNew != null && !idRelacaoNew.equals(idRelacaoOld)) {
                idRelacaoNew.getRelEntCollection().add(relEnt);
                idRelacaoNew = em.merge(idRelacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RelEntPK id = relEnt.getRelEntPK();
                if (findRelEnt(id) == null) {
                    throw new NonexistentEntityException("The relEnt with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RelEntPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RelEnt relEnt;
            try {
                relEnt = em.getReference(RelEnt.class, id);
                relEnt.getRelEntPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relEnt with id " + id + " no longer exists.", enfe);
            }
            Entidades entidades = relEnt.getEntidades();
            if (entidades != null) {
                entidades.getRelEntCollection().remove(relEnt);
                entidades = em.merge(entidades);
            }
            Entidades entidades1 = relEnt.getEntidades1();
            if (entidades1 != null) {
                entidades1.getRelEntCollection().remove(relEnt);
                entidades1 = em.merge(entidades1);
            }
            RelTiposEnt idRelacao = relEnt.getIdRelacao();
            if (idRelacao != null) {
                idRelacao.getRelEntCollection().remove(relEnt);
                idRelacao = em.merge(idRelacao);
            }
            em.remove(relEnt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RelEnt> findRelEntEntities() {
        return findRelEntEntities(true, -1, -1);
    }

    public List<RelEnt> findRelEntEntities(int maxResults, int firstResult) {
        return findRelEntEntities(false, maxResults, firstResult);
    }

    private List<RelEnt> findRelEntEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RelEnt as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RelEnt findRelEnt(RelEntPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RelEnt.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelEntCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from RelEnt as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
