/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.MovCaixas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Caixas;
import entidades.Utilizadores;

/**
 *
 * @author User
 */
public class MovCaixasJpaController {

    public MovCaixasJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MovCaixas movCaixas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caixas idCx = movCaixas.getIdCx();
            if (idCx != null) {
                idCx = em.getReference(idCx.getClass(), idCx.getIdCx());
                movCaixas.setIdCx(idCx);
            }
            Utilizadores userAlteracao = movCaixas.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                movCaixas.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = movCaixas.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                movCaixas.setUserCriacao(userCriacao);
            }
            em.persist(movCaixas);
            if (idCx != null) {
                idCx.getMovCaixasCollection().add(movCaixas);
                idCx = em.merge(idCx);
            }
            if (userAlteracao != null) {
                userAlteracao.getMovCaixasCollection().add(movCaixas);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getMovCaixasCollection().add(movCaixas);
                userCriacao = em.merge(userCriacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMovCaixas(movCaixas.getIdMovCx()) != null) {
                throw new PreexistingEntityException("MovCaixas " + movCaixas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MovCaixas movCaixas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovCaixas persistentMovCaixas = em.find(MovCaixas.class, movCaixas.getIdMovCx());
            Caixas idCxOld = persistentMovCaixas.getIdCx();
            Caixas idCxNew = movCaixas.getIdCx();
            Utilizadores userAlteracaoOld = persistentMovCaixas.getUserAlteracao();
            Utilizadores userAlteracaoNew = movCaixas.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentMovCaixas.getUserCriacao();
            Utilizadores userCriacaoNew = movCaixas.getUserCriacao();
            if (idCxNew != null) {
                idCxNew = em.getReference(idCxNew.getClass(), idCxNew.getIdCx());
                movCaixas.setIdCx(idCxNew);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                movCaixas.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                movCaixas.setUserCriacao(userCriacaoNew);
            }
            movCaixas = em.merge(movCaixas);
            if (idCxOld != null && !idCxOld.equals(idCxNew)) {
                idCxOld.getMovCaixasCollection().remove(movCaixas);
                idCxOld = em.merge(idCxOld);
            }
            if (idCxNew != null && !idCxNew.equals(idCxOld)) {
                idCxNew.getMovCaixasCollection().add(movCaixas);
                idCxNew = em.merge(idCxNew);
            }
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getMovCaixasCollection().remove(movCaixas);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getMovCaixasCollection().add(movCaixas);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getMovCaixasCollection().remove(movCaixas);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getMovCaixasCollection().add(movCaixas);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = movCaixas.getIdMovCx();
                if (findMovCaixas(id) == null) {
                    throw new NonexistentEntityException("The movCaixas with id " + id + " no longer exists.");
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
            MovCaixas movCaixas;
            try {
                movCaixas = em.getReference(MovCaixas.class, id);
                movCaixas.getIdMovCx();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movCaixas with id " + id + " no longer exists.", enfe);
            }
            Caixas idCx = movCaixas.getIdCx();
            if (idCx != null) {
                idCx.getMovCaixasCollection().remove(movCaixas);
                idCx = em.merge(idCx);
            }
            Utilizadores userAlteracao = movCaixas.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getMovCaixasCollection().remove(movCaixas);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = movCaixas.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getMovCaixasCollection().remove(movCaixas);
                userCriacao = em.merge(userCriacao);
            }
            em.remove(movCaixas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovCaixas> findMovCaixasEntities() {
        return findMovCaixasEntities(true, -1, -1);
    }

    public List<MovCaixas> findMovCaixasEntities(int maxResults, int firstResult) {
        return findMovCaixasEntities(false, maxResults, firstResult);
    }

    private List<MovCaixas> findMovCaixasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from MovCaixas as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MovCaixas findMovCaixas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MovCaixas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovCaixasCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from MovCaixas as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
