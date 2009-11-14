/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.CondPagam;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TiposEnt;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Entidades;

/**
 *
 * @author User
 */
public class CondPagamJpaController {

    public CondPagamJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CondPagam condPagam) throws PreexistingEntityException, Exception {
        if (condPagam.getTiposEntCollection() == null) {
            condPagam.setTiposEntCollection(new ArrayList<TiposEnt>());
        }
        if (condPagam.getEntidadesCollection() == null) {
            condPagam.setEntidadesCollection(new ArrayList<Entidades>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TiposEnt> attachedTiposEntCollection = new ArrayList<TiposEnt>();
            for (TiposEnt tiposEntCollectionTiposEntToAttach : condPagam.getTiposEntCollection()) {
                tiposEntCollectionTiposEntToAttach = em.getReference(tiposEntCollectionTiposEntToAttach.getClass(), tiposEntCollectionTiposEntToAttach.getTipoEnt());
                attachedTiposEntCollection.add(tiposEntCollectionTiposEntToAttach);
            }
            condPagam.setTiposEntCollection(attachedTiposEntCollection);
            Collection<Entidades> attachedEntidadesCollection = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionEntidadesToAttach : condPagam.getEntidadesCollection()) {
                entidadesCollectionEntidadesToAttach = em.getReference(entidadesCollectionEntidadesToAttach.getClass(), entidadesCollectionEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection.add(entidadesCollectionEntidadesToAttach);
            }
            condPagam.setEntidadesCollection(attachedEntidadesCollection);
            em.persist(condPagam);
            for (TiposEnt tiposEntCollectionTiposEnt : condPagam.getTiposEntCollection()) {
                CondPagam oldCondPagamOmissaoOfTiposEntCollectionTiposEnt = tiposEntCollectionTiposEnt.getCondPagamOmissao();
                tiposEntCollectionTiposEnt.setCondPagamOmissao(condPagam);
                tiposEntCollectionTiposEnt = em.merge(tiposEntCollectionTiposEnt);
                if (oldCondPagamOmissaoOfTiposEntCollectionTiposEnt != null) {
                    oldCondPagamOmissaoOfTiposEntCollectionTiposEnt.getTiposEntCollection().remove(tiposEntCollectionTiposEnt);
                    oldCondPagamOmissaoOfTiposEntCollectionTiposEnt = em.merge(oldCondPagamOmissaoOfTiposEntCollectionTiposEnt);
                }
            }
            for (Entidades entidadesCollectionEntidades : condPagam.getEntidadesCollection()) {
                CondPagam oldCondPagamOfEntidadesCollectionEntidades = entidadesCollectionEntidades.getCondPagam();
                entidadesCollectionEntidades.setCondPagam(condPagam);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
                if (oldCondPagamOfEntidadesCollectionEntidades != null) {
                    oldCondPagamOfEntidadesCollectionEntidades.getEntidadesCollection().remove(entidadesCollectionEntidades);
                    oldCondPagamOfEntidadesCollectionEntidades = em.merge(oldCondPagamOfEntidadesCollectionEntidades);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCondPagam(condPagam.getCondPagam()) != null) {
                throw new PreexistingEntityException("CondPagam " + condPagam + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CondPagam condPagam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CondPagam persistentCondPagam = em.find(CondPagam.class, condPagam.getCondPagam());
            Collection<TiposEnt> tiposEntCollectionOld = persistentCondPagam.getTiposEntCollection();
            Collection<TiposEnt> tiposEntCollectionNew = condPagam.getTiposEntCollection();
            Collection<Entidades> entidadesCollectionOld = persistentCondPagam.getEntidadesCollection();
            Collection<Entidades> entidadesCollectionNew = condPagam.getEntidadesCollection();
            Collection<TiposEnt> attachedTiposEntCollectionNew = new ArrayList<TiposEnt>();
            for (TiposEnt tiposEntCollectionNewTiposEntToAttach : tiposEntCollectionNew) {
                tiposEntCollectionNewTiposEntToAttach = em.getReference(tiposEntCollectionNewTiposEntToAttach.getClass(), tiposEntCollectionNewTiposEntToAttach.getTipoEnt());
                attachedTiposEntCollectionNew.add(tiposEntCollectionNewTiposEntToAttach);
            }
            tiposEntCollectionNew = attachedTiposEntCollectionNew;
            condPagam.setTiposEntCollection(tiposEntCollectionNew);
            Collection<Entidades> attachedEntidadesCollectionNew = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionNewEntidadesToAttach : entidadesCollectionNew) {
                entidadesCollectionNewEntidadesToAttach = em.getReference(entidadesCollectionNewEntidadesToAttach.getClass(), entidadesCollectionNewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollectionNew.add(entidadesCollectionNewEntidadesToAttach);
            }
            entidadesCollectionNew = attachedEntidadesCollectionNew;
            condPagam.setEntidadesCollection(entidadesCollectionNew);
            condPagam = em.merge(condPagam);
            for (TiposEnt tiposEntCollectionOldTiposEnt : tiposEntCollectionOld) {
                if (!tiposEntCollectionNew.contains(tiposEntCollectionOldTiposEnt)) {
                    tiposEntCollectionOldTiposEnt.setCondPagamOmissao(null);
                    tiposEntCollectionOldTiposEnt = em.merge(tiposEntCollectionOldTiposEnt);
                }
            }
            for (TiposEnt tiposEntCollectionNewTiposEnt : tiposEntCollectionNew) {
                if (!tiposEntCollectionOld.contains(tiposEntCollectionNewTiposEnt)) {
                    CondPagam oldCondPagamOmissaoOfTiposEntCollectionNewTiposEnt = tiposEntCollectionNewTiposEnt.getCondPagamOmissao();
                    tiposEntCollectionNewTiposEnt.setCondPagamOmissao(condPagam);
                    tiposEntCollectionNewTiposEnt = em.merge(tiposEntCollectionNewTiposEnt);
                    if (oldCondPagamOmissaoOfTiposEntCollectionNewTiposEnt != null && !oldCondPagamOmissaoOfTiposEntCollectionNewTiposEnt.equals(condPagam)) {
                        oldCondPagamOmissaoOfTiposEntCollectionNewTiposEnt.getTiposEntCollection().remove(tiposEntCollectionNewTiposEnt);
                        oldCondPagamOmissaoOfTiposEntCollectionNewTiposEnt = em.merge(oldCondPagamOmissaoOfTiposEntCollectionNewTiposEnt);
                    }
                }
            }
            for (Entidades entidadesCollectionOldEntidades : entidadesCollectionOld) {
                if (!entidadesCollectionNew.contains(entidadesCollectionOldEntidades)) {
                    entidadesCollectionOldEntidades.setCondPagam(null);
                    entidadesCollectionOldEntidades = em.merge(entidadesCollectionOldEntidades);
                }
            }
            for (Entidades entidadesCollectionNewEntidades : entidadesCollectionNew) {
                if (!entidadesCollectionOld.contains(entidadesCollectionNewEntidades)) {
                    CondPagam oldCondPagamOfEntidadesCollectionNewEntidades = entidadesCollectionNewEntidades.getCondPagam();
                    entidadesCollectionNewEntidades.setCondPagam(condPagam);
                    entidadesCollectionNewEntidades = em.merge(entidadesCollectionNewEntidades);
                    if (oldCondPagamOfEntidadesCollectionNewEntidades != null && !oldCondPagamOfEntidadesCollectionNewEntidades.equals(condPagam)) {
                        oldCondPagamOfEntidadesCollectionNewEntidades.getEntidadesCollection().remove(entidadesCollectionNewEntidades);
                        oldCondPagamOfEntidadesCollectionNewEntidades = em.merge(oldCondPagamOfEntidadesCollectionNewEntidades);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = condPagam.getCondPagam();
                if (findCondPagam(id) == null) {
                    throw new NonexistentEntityException("The condPagam with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CondPagam condPagam;
            try {
                condPagam = em.getReference(CondPagam.class, id);
                condPagam.getCondPagam();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The condPagam with id " + id + " no longer exists.", enfe);
            }
            Collection<TiposEnt> tiposEntCollection = condPagam.getTiposEntCollection();
            for (TiposEnt tiposEntCollectionTiposEnt : tiposEntCollection) {
                tiposEntCollectionTiposEnt.setCondPagamOmissao(null);
                tiposEntCollectionTiposEnt = em.merge(tiposEntCollectionTiposEnt);
            }
            Collection<Entidades> entidadesCollection = condPagam.getEntidadesCollection();
            for (Entidades entidadesCollectionEntidades : entidadesCollection) {
                entidadesCollectionEntidades.setCondPagam(null);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            em.remove(condPagam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CondPagam> findCondPagamEntities() {
        return findCondPagamEntities(true, -1, -1);
    }

    public List<CondPagam> findCondPagamEntities(int maxResults, int firstResult) {
        return findCondPagamEntities(false, maxResults, firstResult);
    }

    private List<CondPagam> findCondPagamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CondPagam as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CondPagam findCondPagam(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CondPagam.class, id);
        } finally {
            em.close();
        }
    }

    public int getCondPagamCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from CondPagam as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
