/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.TiposUser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Menus;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Utilizadores;

/**
 *
 * @author User
 */
public class TiposUserJpaController {

    public TiposUserJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposUser tiposUser) throws PreexistingEntityException, Exception {
        if (tiposUser.getMenusCollection() == null) {
            tiposUser.setMenusCollection(new ArrayList<Menus>());
        }
        if (tiposUser.getUtilizadoresCollection() == null) {
            tiposUser.setUtilizadoresCollection(new ArrayList<Utilizadores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Menus> attachedMenusCollection = new ArrayList<Menus>();
            for (Menus menusCollectionMenusToAttach : tiposUser.getMenusCollection()) {
                menusCollectionMenusToAttach = em.getReference(menusCollectionMenusToAttach.getClass(), menusCollectionMenusToAttach.getIdMenu());
                attachedMenusCollection.add(menusCollectionMenusToAttach);
            }
            tiposUser.setMenusCollection(attachedMenusCollection);
            Collection<Utilizadores> attachedUtilizadoresCollection = new ArrayList<Utilizadores>();
            for (Utilizadores utilizadoresCollectionUtilizadoresToAttach : tiposUser.getUtilizadoresCollection()) {
                utilizadoresCollectionUtilizadoresToAttach = em.getReference(utilizadoresCollectionUtilizadoresToAttach.getClass(), utilizadoresCollectionUtilizadoresToAttach.getIdUtilizador());
                attachedUtilizadoresCollection.add(utilizadoresCollectionUtilizadoresToAttach);
            }
            tiposUser.setUtilizadoresCollection(attachedUtilizadoresCollection);
            em.persist(tiposUser);
            for (Menus menusCollectionMenus : tiposUser.getMenusCollection()) {
                menusCollectionMenus.getTiposUserCollection().add(tiposUser);
                menusCollectionMenus = em.merge(menusCollectionMenus);
            }
            for (Utilizadores utilizadoresCollectionUtilizadores : tiposUser.getUtilizadoresCollection()) {
                TiposUser oldIdTipoUserOfUtilizadoresCollectionUtilizadores = utilizadoresCollectionUtilizadores.getIdTipoUser();
                utilizadoresCollectionUtilizadores.setIdTipoUser(tiposUser);
                utilizadoresCollectionUtilizadores = em.merge(utilizadoresCollectionUtilizadores);
                if (oldIdTipoUserOfUtilizadoresCollectionUtilizadores != null) {
                    oldIdTipoUserOfUtilizadoresCollectionUtilizadores.getUtilizadoresCollection().remove(utilizadoresCollectionUtilizadores);
                    oldIdTipoUserOfUtilizadoresCollectionUtilizadores = em.merge(oldIdTipoUserOfUtilizadoresCollectionUtilizadores);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiposUser(tiposUser.getIdTipoUser()) != null) {
                throw new PreexistingEntityException("TiposUser " + tiposUser + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposUser tiposUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposUser persistentTiposUser = em.find(TiposUser.class, tiposUser.getIdTipoUser());
            Collection<Menus> menusCollectionOld = persistentTiposUser.getMenusCollection();
            Collection<Menus> menusCollectionNew = tiposUser.getMenusCollection();
            Collection<Utilizadores> utilizadoresCollectionOld = persistentTiposUser.getUtilizadoresCollection();
            Collection<Utilizadores> utilizadoresCollectionNew = tiposUser.getUtilizadoresCollection();
            Collection<Menus> attachedMenusCollectionNew = new ArrayList<Menus>();
            for (Menus menusCollectionNewMenusToAttach : menusCollectionNew) {
                menusCollectionNewMenusToAttach = em.getReference(menusCollectionNewMenusToAttach.getClass(), menusCollectionNewMenusToAttach.getIdMenu());
                attachedMenusCollectionNew.add(menusCollectionNewMenusToAttach);
            }
            menusCollectionNew = attachedMenusCollectionNew;
            tiposUser.setMenusCollection(menusCollectionNew);
            Collection<Utilizadores> attachedUtilizadoresCollectionNew = new ArrayList<Utilizadores>();
            for (Utilizadores utilizadoresCollectionNewUtilizadoresToAttach : utilizadoresCollectionNew) {
                utilizadoresCollectionNewUtilizadoresToAttach = em.getReference(utilizadoresCollectionNewUtilizadoresToAttach.getClass(), utilizadoresCollectionNewUtilizadoresToAttach.getIdUtilizador());
                attachedUtilizadoresCollectionNew.add(utilizadoresCollectionNewUtilizadoresToAttach);
            }
            utilizadoresCollectionNew = attachedUtilizadoresCollectionNew;
            tiposUser.setUtilizadoresCollection(utilizadoresCollectionNew);
            tiposUser = em.merge(tiposUser);
            for (Menus menusCollectionOldMenus : menusCollectionOld) {
                if (!menusCollectionNew.contains(menusCollectionOldMenus)) {
                    menusCollectionOldMenus.getTiposUserCollection().remove(tiposUser);
                    menusCollectionOldMenus = em.merge(menusCollectionOldMenus);
                }
            }
            for (Menus menusCollectionNewMenus : menusCollectionNew) {
                if (!menusCollectionOld.contains(menusCollectionNewMenus)) {
                    menusCollectionNewMenus.getTiposUserCollection().add(tiposUser);
                    menusCollectionNewMenus = em.merge(menusCollectionNewMenus);
                }
            }
            for (Utilizadores utilizadoresCollectionOldUtilizadores : utilizadoresCollectionOld) {
                if (!utilizadoresCollectionNew.contains(utilizadoresCollectionOldUtilizadores)) {
                    utilizadoresCollectionOldUtilizadores.setIdTipoUser(null);
                    utilizadoresCollectionOldUtilizadores = em.merge(utilizadoresCollectionOldUtilizadores);
                }
            }
            for (Utilizadores utilizadoresCollectionNewUtilizadores : utilizadoresCollectionNew) {
                if (!utilizadoresCollectionOld.contains(utilizadoresCollectionNewUtilizadores)) {
                    TiposUser oldIdTipoUserOfUtilizadoresCollectionNewUtilizadores = utilizadoresCollectionNewUtilizadores.getIdTipoUser();
                    utilizadoresCollectionNewUtilizadores.setIdTipoUser(tiposUser);
                    utilizadoresCollectionNewUtilizadores = em.merge(utilizadoresCollectionNewUtilizadores);
                    if (oldIdTipoUserOfUtilizadoresCollectionNewUtilizadores != null && !oldIdTipoUserOfUtilizadoresCollectionNewUtilizadores.equals(tiposUser)) {
                        oldIdTipoUserOfUtilizadoresCollectionNewUtilizadores.getUtilizadoresCollection().remove(utilizadoresCollectionNewUtilizadores);
                        oldIdTipoUserOfUtilizadoresCollectionNewUtilizadores = em.merge(oldIdTipoUserOfUtilizadoresCollectionNewUtilizadores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiposUser.getIdTipoUser();
                if (findTiposUser(id) == null) {
                    throw new NonexistentEntityException("The tiposUser with id " + id + " no longer exists.");
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
            TiposUser tiposUser;
            try {
                tiposUser = em.getReference(TiposUser.class, id);
                tiposUser.getIdTipoUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposUser with id " + id + " no longer exists.", enfe);
            }
            Collection<Menus> menusCollection = tiposUser.getMenusCollection();
            for (Menus menusCollectionMenus : menusCollection) {
                menusCollectionMenus.getTiposUserCollection().remove(tiposUser);
                menusCollectionMenus = em.merge(menusCollectionMenus);
            }
            Collection<Utilizadores> utilizadoresCollection = tiposUser.getUtilizadoresCollection();
            for (Utilizadores utilizadoresCollectionUtilizadores : utilizadoresCollection) {
                utilizadoresCollectionUtilizadores.setIdTipoUser(null);
                utilizadoresCollectionUtilizadores = em.merge(utilizadoresCollectionUtilizadores);
            }
            em.remove(tiposUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposUser> findTiposUserEntities() {
        return findTiposUserEntities(true, -1, -1);
    }

    public List<TiposUser> findTiposUserEntities(int maxResults, int firstResult) {
        return findTiposUserEntities(false, maxResults, firstResult);
    }

    private List<TiposUser> findTiposUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TiposUser as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TiposUser findTiposUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposUserCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TiposUser as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
