/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Zonas;
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
public class ZonasJpaController {

    public ZonasJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Zonas zonas) throws PreexistingEntityException, Exception {
        if (zonas.getEntidadesCollection() == null) {
            zonas.setEntidadesCollection(new ArrayList<Entidades>());
        }
        if (zonas.getEntidadesCollection1() == null) {
            zonas.setEntidadesCollection1(new ArrayList<Entidades>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Entidades> attachedEntidadesCollection = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionEntidadesToAttach : zonas.getEntidadesCollection()) {
                entidadesCollectionEntidadesToAttach = em.getReference(entidadesCollectionEntidadesToAttach.getClass(), entidadesCollectionEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection.add(entidadesCollectionEntidadesToAttach);
            }
            zonas.setEntidadesCollection(attachedEntidadesCollection);
            Collection<Entidades> attachedEntidadesCollection1 = new ArrayList<Entidades>();
            for (Entidades entidadesCollection1EntidadesToAttach : zonas.getEntidadesCollection1()) {
                entidadesCollection1EntidadesToAttach = em.getReference(entidadesCollection1EntidadesToAttach.getClass(), entidadesCollection1EntidadesToAttach.getIdEnt());
                attachedEntidadesCollection1.add(entidadesCollection1EntidadesToAttach);
            }
            zonas.setEntidadesCollection1(attachedEntidadesCollection1);
            em.persist(zonas);
            for (Entidades entidadesCollectionEntidades : zonas.getEntidadesCollection()) {
                entidadesCollectionEntidades.getZonasCollection().add(zonas);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            for (Entidades entidadesCollection1Entidades : zonas.getEntidadesCollection1()) {
                Zonas oldZonaOfEntidadesCollection1Entidades = entidadesCollection1Entidades.getZona();
                entidadesCollection1Entidades.setZona(zonas);
                entidadesCollection1Entidades = em.merge(entidadesCollection1Entidades);
                if (oldZonaOfEntidadesCollection1Entidades != null) {
                    oldZonaOfEntidadesCollection1Entidades.getEntidadesCollection1().remove(entidadesCollection1Entidades);
                    oldZonaOfEntidadesCollection1Entidades = em.merge(oldZonaOfEntidadesCollection1Entidades);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findZonas(zonas.getZona()) != null) {
                throw new PreexistingEntityException("Zonas " + zonas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Zonas zonas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zonas persistentZonas = em.find(Zonas.class, zonas.getZona());
            Collection<Entidades> entidadesCollectionOld = persistentZonas.getEntidadesCollection();
            Collection<Entidades> entidadesCollectionNew = zonas.getEntidadesCollection();
            Collection<Entidades> entidadesCollection1Old = persistentZonas.getEntidadesCollection1();
            Collection<Entidades> entidadesCollection1New = zonas.getEntidadesCollection1();
            Collection<Entidades> attachedEntidadesCollectionNew = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionNewEntidadesToAttach : entidadesCollectionNew) {
                entidadesCollectionNewEntidadesToAttach = em.getReference(entidadesCollectionNewEntidadesToAttach.getClass(), entidadesCollectionNewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollectionNew.add(entidadesCollectionNewEntidadesToAttach);
            }
            entidadesCollectionNew = attachedEntidadesCollectionNew;
            zonas.setEntidadesCollection(entidadesCollectionNew);
            Collection<Entidades> attachedEntidadesCollection1New = new ArrayList<Entidades>();
            for (Entidades entidadesCollection1NewEntidadesToAttach : entidadesCollection1New) {
                entidadesCollection1NewEntidadesToAttach = em.getReference(entidadesCollection1NewEntidadesToAttach.getClass(), entidadesCollection1NewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection1New.add(entidadesCollection1NewEntidadesToAttach);
            }
            entidadesCollection1New = attachedEntidadesCollection1New;
            zonas.setEntidadesCollection1(entidadesCollection1New);
            zonas = em.merge(zonas);
            for (Entidades entidadesCollectionOldEntidades : entidadesCollectionOld) {
                if (!entidadesCollectionNew.contains(entidadesCollectionOldEntidades)) {
                    entidadesCollectionOldEntidades.getZonasCollection().remove(zonas);
                    entidadesCollectionOldEntidades = em.merge(entidadesCollectionOldEntidades);
                }
            }
            for (Entidades entidadesCollectionNewEntidades : entidadesCollectionNew) {
                if (!entidadesCollectionOld.contains(entidadesCollectionNewEntidades)) {
                    entidadesCollectionNewEntidades.getZonasCollection().add(zonas);
                    entidadesCollectionNewEntidades = em.merge(entidadesCollectionNewEntidades);
                }
            }
            for (Entidades entidadesCollection1OldEntidades : entidadesCollection1Old) {
                if (!entidadesCollection1New.contains(entidadesCollection1OldEntidades)) {
                    entidadesCollection1OldEntidades.setZona(null);
                    entidadesCollection1OldEntidades = em.merge(entidadesCollection1OldEntidades);
                }
            }
            for (Entidades entidadesCollection1NewEntidades : entidadesCollection1New) {
                if (!entidadesCollection1Old.contains(entidadesCollection1NewEntidades)) {
                    Zonas oldZonaOfEntidadesCollection1NewEntidades = entidadesCollection1NewEntidades.getZona();
                    entidadesCollection1NewEntidades.setZona(zonas);
                    entidadesCollection1NewEntidades = em.merge(entidadesCollection1NewEntidades);
                    if (oldZonaOfEntidadesCollection1NewEntidades != null && !oldZonaOfEntidadesCollection1NewEntidades.equals(zonas)) {
                        oldZonaOfEntidadesCollection1NewEntidades.getEntidadesCollection1().remove(entidadesCollection1NewEntidades);
                        oldZonaOfEntidadesCollection1NewEntidades = em.merge(oldZonaOfEntidadesCollection1NewEntidades);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = zonas.getZona();
                if (findZonas(id) == null) {
                    throw new NonexistentEntityException("The zonas with id " + id + " no longer exists.");
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
            Zonas zonas;
            try {
                zonas = em.getReference(Zonas.class, id);
                zonas.getZona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The zonas with id " + id + " no longer exists.", enfe);
            }
            Collection<Entidades> entidadesCollection = zonas.getEntidadesCollection();
            for (Entidades entidadesCollectionEntidades : entidadesCollection) {
                entidadesCollectionEntidades.getZonasCollection().remove(zonas);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            Collection<Entidades> entidadesCollection1 = zonas.getEntidadesCollection1();
            for (Entidades entidadesCollection1Entidades : entidadesCollection1) {
                entidadesCollection1Entidades.setZona(null);
                entidadesCollection1Entidades = em.merge(entidadesCollection1Entidades);
            }
            em.remove(zonas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Zonas> findZonasEntities() {
        return findZonasEntities(true, -1, -1);
    }

    public List<Zonas> findZonasEntities(int maxResults, int firstResult) {
        return findZonasEntities(false, maxResults, firstResult);
    }

    private List<Zonas> findZonasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Zonas as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Zonas findZonas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Zonas.class, id);
        } finally {
            em.close();
        }
    }

    public int getZonasCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Zonas as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
