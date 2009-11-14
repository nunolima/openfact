/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Fotos;
import entidades.FotosPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Entidades;
import entidades.Utilizadores;

/**
 *
 * @author User
 */
public class FotosJpaController {

    public FotosJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fotos fotos) throws PreexistingEntityException, Exception {
        if (fotos.getFotosPK() == null) {
            fotos.setFotosPK(new FotosPK());
        }
        fotos.getFotosPK().setIdEnt(fotos.getEntidades().getIdEnt());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidades entidades = fotos.getEntidades();
            if (entidades != null) {
                entidades = em.getReference(entidades.getClass(), entidades.getIdEnt());
                fotos.setEntidades(entidades);
            }
            Utilizadores userCriacao = fotos.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                fotos.setUserCriacao(userCriacao);
            }
            em.persist(fotos);
            if (entidades != null) {
                entidades.getFotosCollection().add(fotos);
                entidades = em.merge(entidades);
            }
            if (userCriacao != null) {
                userCriacao.getFotosCollection().add(fotos);
                userCriacao = em.merge(userCriacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFotos(fotos.getFotosPK()) != null) {
                throw new PreexistingEntityException("Fotos " + fotos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fotos fotos) throws NonexistentEntityException, Exception {
        fotos.getFotosPK().setIdEnt(fotos.getEntidades().getIdEnt());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fotos persistentFotos = em.find(Fotos.class, fotos.getFotosPK());
            Entidades entidadesOld = persistentFotos.getEntidades();
            Entidades entidadesNew = fotos.getEntidades();
            Utilizadores userCriacaoOld = persistentFotos.getUserCriacao();
            Utilizadores userCriacaoNew = fotos.getUserCriacao();
            if (entidadesNew != null) {
                entidadesNew = em.getReference(entidadesNew.getClass(), entidadesNew.getIdEnt());
                fotos.setEntidades(entidadesNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                fotos.setUserCriacao(userCriacaoNew);
            }
            fotos = em.merge(fotos);
            if (entidadesOld != null && !entidadesOld.equals(entidadesNew)) {
                entidadesOld.getFotosCollection().remove(fotos);
                entidadesOld = em.merge(entidadesOld);
            }
            if (entidadesNew != null && !entidadesNew.equals(entidadesOld)) {
                entidadesNew.getFotosCollection().add(fotos);
                entidadesNew = em.merge(entidadesNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getFotosCollection().remove(fotos);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getFotosCollection().add(fotos);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FotosPK id = fotos.getFotosPK();
                if (findFotos(id) == null) {
                    throw new NonexistentEntityException("The fotos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FotosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fotos fotos;
            try {
                fotos = em.getReference(Fotos.class, id);
                fotos.getFotosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fotos with id " + id + " no longer exists.", enfe);
            }
            Entidades entidades = fotos.getEntidades();
            if (entidades != null) {
                entidades.getFotosCollection().remove(fotos);
                entidades = em.merge(entidades);
            }
            Utilizadores userCriacao = fotos.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getFotosCollection().remove(fotos);
                userCriacao = em.merge(userCriacao);
            }
            em.remove(fotos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fotos> findFotosEntities() {
        return findFotosEntities(true, -1, -1);
    }

    public List<Fotos> findFotosEntities(int maxResults, int firstResult) {
        return findFotosEntities(false, maxResults, firstResult);
    }

    private List<Fotos> findFotosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fotos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fotos findFotos(FotosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fotos.class, id);
        } finally {
            em.close();
        }
    }

    public int getFotosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Fotos as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
