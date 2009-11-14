/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.RelTiposEnt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TiposEnt;
import entidades.RelEnt;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class RelTiposEntJpaController {

    public RelTiposEntJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RelTiposEnt relTiposEnt) throws PreexistingEntityException, Exception {
        if (relTiposEnt.getRelEntCollection() == null) {
            relTiposEnt.setRelEntCollection(new ArrayList<RelEnt>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposEnt tipoEnt = relTiposEnt.getTipoEnt();
            if (tipoEnt != null) {
                tipoEnt = em.getReference(tipoEnt.getClass(), tipoEnt.getTipoEnt());
                relTiposEnt.setTipoEnt(tipoEnt);
            }
            TiposEnt tipoEntRel = relTiposEnt.getTipoEntRel();
            if (tipoEntRel != null) {
                tipoEntRel = em.getReference(tipoEntRel.getClass(), tipoEntRel.getTipoEnt());
                relTiposEnt.setTipoEntRel(tipoEntRel);
            }
            Collection<RelEnt> attachedRelEntCollection = new ArrayList<RelEnt>();
            for (RelEnt relEntCollectionRelEntToAttach : relTiposEnt.getRelEntCollection()) {
                relEntCollectionRelEntToAttach = em.getReference(relEntCollectionRelEntToAttach.getClass(), relEntCollectionRelEntToAttach.getRelEntPK());
                attachedRelEntCollection.add(relEntCollectionRelEntToAttach);
            }
            relTiposEnt.setRelEntCollection(attachedRelEntCollection);
            em.persist(relTiposEnt);
            if (tipoEnt != null) {
                tipoEnt.getRelTiposEntCollection().add(relTiposEnt);
                tipoEnt = em.merge(tipoEnt);
            }
            if (tipoEntRel != null) {
                tipoEntRel.getRelTiposEntCollection().add(relTiposEnt);
                tipoEntRel = em.merge(tipoEntRel);
            }
            for (RelEnt relEntCollectionRelEnt : relTiposEnt.getRelEntCollection()) {
                RelTiposEnt oldIdRelacaoOfRelEntCollectionRelEnt = relEntCollectionRelEnt.getIdRelacao();
                relEntCollectionRelEnt.setIdRelacao(relTiposEnt);
                relEntCollectionRelEnt = em.merge(relEntCollectionRelEnt);
                if (oldIdRelacaoOfRelEntCollectionRelEnt != null) {
                    oldIdRelacaoOfRelEntCollectionRelEnt.getRelEntCollection().remove(relEntCollectionRelEnt);
                    oldIdRelacaoOfRelEntCollectionRelEnt = em.merge(oldIdRelacaoOfRelEntCollectionRelEnt);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelTiposEnt(relTiposEnt.getIdRelacao()) != null) {
                throw new PreexistingEntityException("RelTiposEnt " + relTiposEnt + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RelTiposEnt relTiposEnt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RelTiposEnt persistentRelTiposEnt = em.find(RelTiposEnt.class, relTiposEnt.getIdRelacao());
            TiposEnt tipoEntOld = persistentRelTiposEnt.getTipoEnt();
            TiposEnt tipoEntNew = relTiposEnt.getTipoEnt();
            TiposEnt tipoEntRelOld = persistentRelTiposEnt.getTipoEntRel();
            TiposEnt tipoEntRelNew = relTiposEnt.getTipoEntRel();
            Collection<RelEnt> relEntCollectionOld = persistentRelTiposEnt.getRelEntCollection();
            Collection<RelEnt> relEntCollectionNew = relTiposEnt.getRelEntCollection();
            if (tipoEntNew != null) {
                tipoEntNew = em.getReference(tipoEntNew.getClass(), tipoEntNew.getTipoEnt());
                relTiposEnt.setTipoEnt(tipoEntNew);
            }
            if (tipoEntRelNew != null) {
                tipoEntRelNew = em.getReference(tipoEntRelNew.getClass(), tipoEntRelNew.getTipoEnt());
                relTiposEnt.setTipoEntRel(tipoEntRelNew);
            }
            Collection<RelEnt> attachedRelEntCollectionNew = new ArrayList<RelEnt>();
            for (RelEnt relEntCollectionNewRelEntToAttach : relEntCollectionNew) {
                relEntCollectionNewRelEntToAttach = em.getReference(relEntCollectionNewRelEntToAttach.getClass(), relEntCollectionNewRelEntToAttach.getRelEntPK());
                attachedRelEntCollectionNew.add(relEntCollectionNewRelEntToAttach);
            }
            relEntCollectionNew = attachedRelEntCollectionNew;
            relTiposEnt.setRelEntCollection(relEntCollectionNew);
            relTiposEnt = em.merge(relTiposEnt);
            if (tipoEntOld != null && !tipoEntOld.equals(tipoEntNew)) {
                tipoEntOld.getRelTiposEntCollection().remove(relTiposEnt);
                tipoEntOld = em.merge(tipoEntOld);
            }
            if (tipoEntNew != null && !tipoEntNew.equals(tipoEntOld)) {
                tipoEntNew.getRelTiposEntCollection().add(relTiposEnt);
                tipoEntNew = em.merge(tipoEntNew);
            }
            if (tipoEntRelOld != null && !tipoEntRelOld.equals(tipoEntRelNew)) {
                tipoEntRelOld.getRelTiposEntCollection().remove(relTiposEnt);
                tipoEntRelOld = em.merge(tipoEntRelOld);
            }
            if (tipoEntRelNew != null && !tipoEntRelNew.equals(tipoEntRelOld)) {
                tipoEntRelNew.getRelTiposEntCollection().add(relTiposEnt);
                tipoEntRelNew = em.merge(tipoEntRelNew);
            }
            for (RelEnt relEntCollectionOldRelEnt : relEntCollectionOld) {
                if (!relEntCollectionNew.contains(relEntCollectionOldRelEnt)) {
                    relEntCollectionOldRelEnt.setIdRelacao(null);
                    relEntCollectionOldRelEnt = em.merge(relEntCollectionOldRelEnt);
                }
            }
            for (RelEnt relEntCollectionNewRelEnt : relEntCollectionNew) {
                if (!relEntCollectionOld.contains(relEntCollectionNewRelEnt)) {
                    RelTiposEnt oldIdRelacaoOfRelEntCollectionNewRelEnt = relEntCollectionNewRelEnt.getIdRelacao();
                    relEntCollectionNewRelEnt.setIdRelacao(relTiposEnt);
                    relEntCollectionNewRelEnt = em.merge(relEntCollectionNewRelEnt);
                    if (oldIdRelacaoOfRelEntCollectionNewRelEnt != null && !oldIdRelacaoOfRelEntCollectionNewRelEnt.equals(relTiposEnt)) {
                        oldIdRelacaoOfRelEntCollectionNewRelEnt.getRelEntCollection().remove(relEntCollectionNewRelEnt);
                        oldIdRelacaoOfRelEntCollectionNewRelEnt = em.merge(oldIdRelacaoOfRelEntCollectionNewRelEnt);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = relTiposEnt.getIdRelacao();
                if (findRelTiposEnt(id) == null) {
                    throw new NonexistentEntityException("The relTiposEnt with id " + id + " no longer exists.");
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
            RelTiposEnt relTiposEnt;
            try {
                relTiposEnt = em.getReference(RelTiposEnt.class, id);
                relTiposEnt.getIdRelacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relTiposEnt with id " + id + " no longer exists.", enfe);
            }
            TiposEnt tipoEnt = relTiposEnt.getTipoEnt();
            if (tipoEnt != null) {
                tipoEnt.getRelTiposEntCollection().remove(relTiposEnt);
                tipoEnt = em.merge(tipoEnt);
            }
            TiposEnt tipoEntRel = relTiposEnt.getTipoEntRel();
            if (tipoEntRel != null) {
                tipoEntRel.getRelTiposEntCollection().remove(relTiposEnt);
                tipoEntRel = em.merge(tipoEntRel);
            }
            Collection<RelEnt> relEntCollection = relTiposEnt.getRelEntCollection();
            for (RelEnt relEntCollectionRelEnt : relEntCollection) {
                relEntCollectionRelEnt.setIdRelacao(null);
                relEntCollectionRelEnt = em.merge(relEntCollectionRelEnt);
            }
            em.remove(relTiposEnt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RelTiposEnt> findRelTiposEntEntities() {
        return findRelTiposEntEntities(true, -1, -1);
    }

    public List<RelTiposEnt> findRelTiposEntEntities(int maxResults, int firstResult) {
        return findRelTiposEntEntities(false, maxResults, firstResult);
    }

    private List<RelTiposEnt> findRelTiposEntEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RelTiposEnt as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RelTiposEnt findRelTiposEnt(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RelTiposEnt.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelTiposEntCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from RelTiposEnt as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
