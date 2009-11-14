/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Menus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TiposUser;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class MenusJpaController {

    public MenusJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Menus menus) throws PreexistingEntityException, Exception {
        if (menus.getTiposUserCollection() == null) {
            menus.setTiposUserCollection(new ArrayList<TiposUser>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TiposUser> attachedTiposUserCollection = new ArrayList<TiposUser>();
            for (TiposUser tiposUserCollectionTiposUserToAttach : menus.getTiposUserCollection()) {
                tiposUserCollectionTiposUserToAttach = em.getReference(tiposUserCollectionTiposUserToAttach.getClass(), tiposUserCollectionTiposUserToAttach.getIdTipoUser());
                attachedTiposUserCollection.add(tiposUserCollectionTiposUserToAttach);
            }
            menus.setTiposUserCollection(attachedTiposUserCollection);
            em.persist(menus);
            for (TiposUser tiposUserCollectionTiposUser : menus.getTiposUserCollection()) {
                tiposUserCollectionTiposUser.getMenusCollection().add(menus);
                tiposUserCollectionTiposUser = em.merge(tiposUserCollectionTiposUser);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMenus(menus.getIdMenu()) != null) {
                throw new PreexistingEntityException("Menus " + menus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Menus menus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menus persistentMenus = em.find(Menus.class, menus.getIdMenu());
            Collection<TiposUser> tiposUserCollectionOld = persistentMenus.getTiposUserCollection();
            Collection<TiposUser> tiposUserCollectionNew = menus.getTiposUserCollection();
            Collection<TiposUser> attachedTiposUserCollectionNew = new ArrayList<TiposUser>();
            for (TiposUser tiposUserCollectionNewTiposUserToAttach : tiposUserCollectionNew) {
                tiposUserCollectionNewTiposUserToAttach = em.getReference(tiposUserCollectionNewTiposUserToAttach.getClass(), tiposUserCollectionNewTiposUserToAttach.getIdTipoUser());
                attachedTiposUserCollectionNew.add(tiposUserCollectionNewTiposUserToAttach);
            }
            tiposUserCollectionNew = attachedTiposUserCollectionNew;
            menus.setTiposUserCollection(tiposUserCollectionNew);
            menus = em.merge(menus);
            for (TiposUser tiposUserCollectionOldTiposUser : tiposUserCollectionOld) {
                if (!tiposUserCollectionNew.contains(tiposUserCollectionOldTiposUser)) {
                    tiposUserCollectionOldTiposUser.getMenusCollection().remove(menus);
                    tiposUserCollectionOldTiposUser = em.merge(tiposUserCollectionOldTiposUser);
                }
            }
            for (TiposUser tiposUserCollectionNewTiposUser : tiposUserCollectionNew) {
                if (!tiposUserCollectionOld.contains(tiposUserCollectionNewTiposUser)) {
                    tiposUserCollectionNewTiposUser.getMenusCollection().add(menus);
                    tiposUserCollectionNewTiposUser = em.merge(tiposUserCollectionNewTiposUser);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = menus.getIdMenu();
                if (findMenus(id) == null) {
                    throw new NonexistentEntityException("The menus with id " + id + " no longer exists.");
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
            Menus menus;
            try {
                menus = em.getReference(Menus.class, id);
                menus.getIdMenu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The menus with id " + id + " no longer exists.", enfe);
            }
            Collection<TiposUser> tiposUserCollection = menus.getTiposUserCollection();
            for (TiposUser tiposUserCollectionTiposUser : tiposUserCollection) {
                tiposUserCollectionTiposUser.getMenusCollection().remove(menus);
                tiposUserCollectionTiposUser = em.merge(tiposUserCollectionTiposUser);
            }
            em.remove(menus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Menus> findMenusEntities() {
        return findMenusEntities(true, -1, -1);
    }

    public List<Menus> findMenusEntities(int maxResults, int firstResult) {
        return findMenusEntities(false, maxResults, firstResult);
    }

    private List<Menus> findMenusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Menus as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Menus findMenus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Menus.class, id);
        } finally {
            em.close();
        }
    }

    public int getMenusCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Menus as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
