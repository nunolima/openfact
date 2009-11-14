/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.ModosExp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Entidades;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class ModosExpJpaController {

    public ModosExpJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ModosExp modosExp) throws PreexistingEntityException, Exception {
        if (modosExp.getEntidadesCollection() == null) {
            modosExp.setEntidadesCollection(new ArrayList<Entidades>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Entidades> attachedEntidadesCollection = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionEntidadesToAttach : modosExp.getEntidadesCollection()) {
                entidadesCollectionEntidadesToAttach = em.getReference(entidadesCollectionEntidadesToAttach.getClass(), entidadesCollectionEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection.add(entidadesCollectionEntidadesToAttach);
            }
            modosExp.setEntidadesCollection(attachedEntidadesCollection);
            em.persist(modosExp);
            for (Entidades entidadesCollectionEntidades : modosExp.getEntidadesCollection()) {
                ModosExp oldIdModoExpOfEntidadesCollectionEntidades = entidadesCollectionEntidades.getIdModoExp();
                entidadesCollectionEntidades.setIdModoExp(modosExp);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
                if (oldIdModoExpOfEntidadesCollectionEntidades != null) {
                    oldIdModoExpOfEntidadesCollectionEntidades.getEntidadesCollection().remove(entidadesCollectionEntidades);
                    oldIdModoExpOfEntidadesCollectionEntidades = em.merge(oldIdModoExpOfEntidadesCollectionEntidades);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findModosExp(modosExp.getIdModoExp()) != null) {
                throw new PreexistingEntityException("ModosExp " + modosExp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ModosExp modosExp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ModosExp persistentModosExp = em.find(ModosExp.class, modosExp.getIdModoExp());
            Collection<Entidades> entidadesCollectionOld = persistentModosExp.getEntidadesCollection();
            Collection<Entidades> entidadesCollectionNew = modosExp.getEntidadesCollection();
            Collection<Entidades> attachedEntidadesCollectionNew = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionNewEntidadesToAttach : entidadesCollectionNew) {
                entidadesCollectionNewEntidadesToAttach = em.getReference(entidadesCollectionNewEntidadesToAttach.getClass(), entidadesCollectionNewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollectionNew.add(entidadesCollectionNewEntidadesToAttach);
            }
            entidadesCollectionNew = attachedEntidadesCollectionNew;
            modosExp.setEntidadesCollection(entidadesCollectionNew);
            modosExp = em.merge(modosExp);
            for (Entidades entidadesCollectionOldEntidades : entidadesCollectionOld) {
                if (!entidadesCollectionNew.contains(entidadesCollectionOldEntidades)) {
                    entidadesCollectionOldEntidades.setIdModoExp(null);
                    entidadesCollectionOldEntidades = em.merge(entidadesCollectionOldEntidades);
                }
            }
            for (Entidades entidadesCollectionNewEntidades : entidadesCollectionNew) {
                if (!entidadesCollectionOld.contains(entidadesCollectionNewEntidades)) {
                    ModosExp oldIdModoExpOfEntidadesCollectionNewEntidades = entidadesCollectionNewEntidades.getIdModoExp();
                    entidadesCollectionNewEntidades.setIdModoExp(modosExp);
                    entidadesCollectionNewEntidades = em.merge(entidadesCollectionNewEntidades);
                    if (oldIdModoExpOfEntidadesCollectionNewEntidades != null && !oldIdModoExpOfEntidadesCollectionNewEntidades.equals(modosExp)) {
                        oldIdModoExpOfEntidadesCollectionNewEntidades.getEntidadesCollection().remove(entidadesCollectionNewEntidades);
                        oldIdModoExpOfEntidadesCollectionNewEntidades = em.merge(oldIdModoExpOfEntidadesCollectionNewEntidades);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = modosExp.getIdModoExp();
                if (findModosExp(id) == null) {
                    throw new NonexistentEntityException("The modosExp with id " + id + " no longer exists.");
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
            ModosExp modosExp;
            try {
                modosExp = em.getReference(ModosExp.class, id);
                modosExp.getIdModoExp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modosExp with id " + id + " no longer exists.", enfe);
            }
            Collection<Entidades> entidadesCollection = modosExp.getEntidadesCollection();
            for (Entidades entidadesCollectionEntidades : entidadesCollection) {
                entidadesCollectionEntidades.setIdModoExp(null);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            em.remove(modosExp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ModosExp> findModosExpEntities() {
        return findModosExpEntities(true, -1, -1);
    }

    public List<ModosExp> findModosExpEntities(int maxResults, int firstResult) {
        return findModosExpEntities(false, maxResults, firstResult);
    }

    private List<ModosExp> findModosExpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ModosExp as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ModosExp findModosExp(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ModosExp.class, id);
        } finally {
            em.close();
        }
    }

    public int getModosExpCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ModosExp as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
