/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.ContasBanco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Entidades;
import entidades.Utilizadores;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class ContasBancoJpaController {

    public ContasBancoJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContasBanco contasBanco) throws PreexistingEntityException, Exception {
        if (contasBanco.getEntidadesCollection() == null) {
            contasBanco.setEntidadesCollection(new ArrayList<Entidades>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidades idEnt = contasBanco.getIdEnt();
            if (idEnt != null) {
                idEnt = em.getReference(idEnt.getClass(), idEnt.getIdEnt());
                contasBanco.setIdEnt(idEnt);
            }
            Utilizadores userAlteracao = contasBanco.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                contasBanco.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = contasBanco.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                contasBanco.setUserCriacao(userCriacao);
            }
            Collection<Entidades> attachedEntidadesCollection = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionEntidadesToAttach : contasBanco.getEntidadesCollection()) {
                entidadesCollectionEntidadesToAttach = em.getReference(entidadesCollectionEntidadesToAttach.getClass(), entidadesCollectionEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection.add(entidadesCollectionEntidadesToAttach);
            }
            contasBanco.setEntidadesCollection(attachedEntidadesCollection);
            em.persist(contasBanco);
            if (idEnt != null) {
                idEnt.getContasBancoCollection().add(contasBanco);
                idEnt = em.merge(idEnt);
            }
            if (userAlteracao != null) {
                userAlteracao.getContasBancoCollection().add(contasBanco);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getContasBancoCollection().add(contasBanco);
                userCriacao = em.merge(userCriacao);
            }
            for (Entidades entidadesCollectionEntidades : contasBanco.getEntidadesCollection()) {
                entidadesCollectionEntidades.getContasBancoCollection().add(contasBanco);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContasBanco(contasBanco.getIdConta()) != null) {
                throw new PreexistingEntityException("ContasBanco " + contasBanco + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContasBanco contasBanco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContasBanco persistentContasBanco = em.find(ContasBanco.class, contasBanco.getIdConta());
            Entidades idEntOld = persistentContasBanco.getIdEnt();
            Entidades idEntNew = contasBanco.getIdEnt();
            Utilizadores userAlteracaoOld = persistentContasBanco.getUserAlteracao();
            Utilizadores userAlteracaoNew = contasBanco.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentContasBanco.getUserCriacao();
            Utilizadores userCriacaoNew = contasBanco.getUserCriacao();
            Collection<Entidades> entidadesCollectionOld = persistentContasBanco.getEntidadesCollection();
            Collection<Entidades> entidadesCollectionNew = contasBanco.getEntidadesCollection();
            if (idEntNew != null) {
                idEntNew = em.getReference(idEntNew.getClass(), idEntNew.getIdEnt());
                contasBanco.setIdEnt(idEntNew);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                contasBanco.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                contasBanco.setUserCriacao(userCriacaoNew);
            }
            Collection<Entidades> attachedEntidadesCollectionNew = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionNewEntidadesToAttach : entidadesCollectionNew) {
                entidadesCollectionNewEntidadesToAttach = em.getReference(entidadesCollectionNewEntidadesToAttach.getClass(), entidadesCollectionNewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollectionNew.add(entidadesCollectionNewEntidadesToAttach);
            }
            entidadesCollectionNew = attachedEntidadesCollectionNew;
            contasBanco.setEntidadesCollection(entidadesCollectionNew);
            contasBanco = em.merge(contasBanco);
            if (idEntOld != null && !idEntOld.equals(idEntNew)) {
                idEntOld.getContasBancoCollection().remove(contasBanco);
                idEntOld = em.merge(idEntOld);
            }
            if (idEntNew != null && !idEntNew.equals(idEntOld)) {
                idEntNew.getContasBancoCollection().add(contasBanco);
                idEntNew = em.merge(idEntNew);
            }
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getContasBancoCollection().remove(contasBanco);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getContasBancoCollection().add(contasBanco);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getContasBancoCollection().remove(contasBanco);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getContasBancoCollection().add(contasBanco);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            for (Entidades entidadesCollectionOldEntidades : entidadesCollectionOld) {
                if (!entidadesCollectionNew.contains(entidadesCollectionOldEntidades)) {
                    entidadesCollectionOldEntidades.getContasBancoCollection().remove(contasBanco);
                    entidadesCollectionOldEntidades = em.merge(entidadesCollectionOldEntidades);
                }
            }
            for (Entidades entidadesCollectionNewEntidades : entidadesCollectionNew) {
                if (!entidadesCollectionOld.contains(entidadesCollectionNewEntidades)) {
                    entidadesCollectionNewEntidades.getContasBancoCollection().add(contasBanco);
                    entidadesCollectionNewEntidades = em.merge(entidadesCollectionNewEntidades);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contasBanco.getIdConta();
                if (findContasBanco(id) == null) {
                    throw new NonexistentEntityException("The contasBanco with id " + id + " no longer exists.");
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
            ContasBanco contasBanco;
            try {
                contasBanco = em.getReference(ContasBanco.class, id);
                contasBanco.getIdConta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contasBanco with id " + id + " no longer exists.", enfe);
            }
            Entidades idEnt = contasBanco.getIdEnt();
            if (idEnt != null) {
                idEnt.getContasBancoCollection().remove(contasBanco);
                idEnt = em.merge(idEnt);
            }
            Utilizadores userAlteracao = contasBanco.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getContasBancoCollection().remove(contasBanco);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = contasBanco.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getContasBancoCollection().remove(contasBanco);
                userCriacao = em.merge(userCriacao);
            }
            Collection<Entidades> entidadesCollection = contasBanco.getEntidadesCollection();
            for (Entidades entidadesCollectionEntidades : entidadesCollection) {
                entidadesCollectionEntidades.getContasBancoCollection().remove(contasBanco);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            em.remove(contasBanco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContasBanco> findContasBancoEntities() {
        return findContasBancoEntities(true, -1, -1);
    }

    public List<ContasBanco> findContasBancoEntities(int maxResults, int firstResult) {
        return findContasBancoEntities(false, maxResults, firstResult);
    }

    private List<ContasBanco> findContasBancoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ContasBanco as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ContasBanco findContasBanco(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContasBanco.class, id);
        } finally {
            em.close();
        }
    }

    public int getContasBancoCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ContasBanco as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
