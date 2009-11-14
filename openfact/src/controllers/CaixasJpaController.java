/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Caixas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Utilizadores;
import entidades.MovCaixas;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class CaixasJpaController {

    public CaixasJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caixas caixas) throws PreexistingEntityException, Exception {
        if (caixas.getMovCaixasCollection() == null) {
            caixas.setMovCaixasCollection(new ArrayList<MovCaixas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilizadores userAlteracao = caixas.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                caixas.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = caixas.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                caixas.setUserCriacao(userCriacao);
            }
            Collection<MovCaixas> attachedMovCaixasCollection = new ArrayList<MovCaixas>();
            for (MovCaixas movCaixasCollectionMovCaixasToAttach : caixas.getMovCaixasCollection()) {
                movCaixasCollectionMovCaixasToAttach = em.getReference(movCaixasCollectionMovCaixasToAttach.getClass(), movCaixasCollectionMovCaixasToAttach.getIdMovCx());
                attachedMovCaixasCollection.add(movCaixasCollectionMovCaixasToAttach);
            }
            caixas.setMovCaixasCollection(attachedMovCaixasCollection);
            em.persist(caixas);
            if (userAlteracao != null) {
                userAlteracao.getCaixasCollection().add(caixas);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getCaixasCollection().add(caixas);
                userCriacao = em.merge(userCriacao);
            }
            for (MovCaixas movCaixasCollectionMovCaixas : caixas.getMovCaixasCollection()) {
                Caixas oldIdCxOfMovCaixasCollectionMovCaixas = movCaixasCollectionMovCaixas.getIdCx();
                movCaixasCollectionMovCaixas.setIdCx(caixas);
                movCaixasCollectionMovCaixas = em.merge(movCaixasCollectionMovCaixas);
                if (oldIdCxOfMovCaixasCollectionMovCaixas != null) {
                    oldIdCxOfMovCaixasCollectionMovCaixas.getMovCaixasCollection().remove(movCaixasCollectionMovCaixas);
                    oldIdCxOfMovCaixasCollectionMovCaixas = em.merge(oldIdCxOfMovCaixasCollectionMovCaixas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCaixas(caixas.getIdCx()) != null) {
                throw new PreexistingEntityException("Caixas " + caixas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Caixas caixas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caixas persistentCaixas = em.find(Caixas.class, caixas.getIdCx());
            Utilizadores userAlteracaoOld = persistentCaixas.getUserAlteracao();
            Utilizadores userAlteracaoNew = caixas.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentCaixas.getUserCriacao();
            Utilizadores userCriacaoNew = caixas.getUserCriacao();
            Collection<MovCaixas> movCaixasCollectionOld = persistentCaixas.getMovCaixasCollection();
            Collection<MovCaixas> movCaixasCollectionNew = caixas.getMovCaixasCollection();
            List<String> illegalOrphanMessages = null;
            for (MovCaixas movCaixasCollectionOldMovCaixas : movCaixasCollectionOld) {
                if (!movCaixasCollectionNew.contains(movCaixasCollectionOldMovCaixas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MovCaixas " + movCaixasCollectionOldMovCaixas + " since its idCx field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                caixas.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                caixas.setUserCriacao(userCriacaoNew);
            }
            Collection<MovCaixas> attachedMovCaixasCollectionNew = new ArrayList<MovCaixas>();
            for (MovCaixas movCaixasCollectionNewMovCaixasToAttach : movCaixasCollectionNew) {
                movCaixasCollectionNewMovCaixasToAttach = em.getReference(movCaixasCollectionNewMovCaixasToAttach.getClass(), movCaixasCollectionNewMovCaixasToAttach.getIdMovCx());
                attachedMovCaixasCollectionNew.add(movCaixasCollectionNewMovCaixasToAttach);
            }
            movCaixasCollectionNew = attachedMovCaixasCollectionNew;
            caixas.setMovCaixasCollection(movCaixasCollectionNew);
            caixas = em.merge(caixas);
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getCaixasCollection().remove(caixas);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getCaixasCollection().add(caixas);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getCaixasCollection().remove(caixas);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getCaixasCollection().add(caixas);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            for (MovCaixas movCaixasCollectionNewMovCaixas : movCaixasCollectionNew) {
                if (!movCaixasCollectionOld.contains(movCaixasCollectionNewMovCaixas)) {
                    Caixas oldIdCxOfMovCaixasCollectionNewMovCaixas = movCaixasCollectionNewMovCaixas.getIdCx();
                    movCaixasCollectionNewMovCaixas.setIdCx(caixas);
                    movCaixasCollectionNewMovCaixas = em.merge(movCaixasCollectionNewMovCaixas);
                    if (oldIdCxOfMovCaixasCollectionNewMovCaixas != null && !oldIdCxOfMovCaixasCollectionNewMovCaixas.equals(caixas)) {
                        oldIdCxOfMovCaixasCollectionNewMovCaixas.getMovCaixasCollection().remove(movCaixasCollectionNewMovCaixas);
                        oldIdCxOfMovCaixasCollectionNewMovCaixas = em.merge(oldIdCxOfMovCaixasCollectionNewMovCaixas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = caixas.getIdCx();
                if (findCaixas(id) == null) {
                    throw new NonexistentEntityException("The caixas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caixas caixas;
            try {
                caixas = em.getReference(Caixas.class, id);
                caixas.getIdCx();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caixas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<MovCaixas> movCaixasCollectionOrphanCheck = caixas.getMovCaixasCollection();
            for (MovCaixas movCaixasCollectionOrphanCheckMovCaixas : movCaixasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Caixas (" + caixas + ") cannot be destroyed since the MovCaixas " + movCaixasCollectionOrphanCheckMovCaixas + " in its movCaixasCollection field has a non-nullable idCx field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Utilizadores userAlteracao = caixas.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getCaixasCollection().remove(caixas);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = caixas.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getCaixasCollection().remove(caixas);
                userCriacao = em.merge(userCriacao);
            }
            em.remove(caixas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Caixas> findCaixasEntities() {
        return findCaixasEntities(true, -1, -1);
    }

    public List<Caixas> findCaixasEntities(int maxResults, int firstResult) {
        return findCaixasEntities(false, maxResults, firstResult);
    }

    private List<Caixas> findCaixasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Caixas as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Caixas findCaixas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caixas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaixasCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Caixas as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
