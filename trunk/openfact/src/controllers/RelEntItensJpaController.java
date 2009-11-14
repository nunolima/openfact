/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.RelEntItens;
import entidades.RelEntItensPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Entidades;
import entidades.Itens;
import entidades.TiposEnt;
import entidades.Utilizadores;

/**
 *
 * @author User
 */
public class RelEntItensJpaController {

    public RelEntItensJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RelEntItens relEntItens) throws PreexistingEntityException, Exception {
        if (relEntItens.getRelEntItensPK() == null) {
            relEntItens.setRelEntItensPK(new RelEntItensPK());
        }
        relEntItens.getRelEntItensPK().setTipoEnt(relEntItens.getTiposEnt().getTipoEnt());
        relEntItens.getRelEntItensPK().setIdEnt(relEntItens.getEntidades().getIdEnt());
        relEntItens.getRelEntItensPK().setIdItem(relEntItens.getItens().getIdItem());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidades entidades = relEntItens.getEntidades();
            if (entidades != null) {
                entidades = em.getReference(entidades.getClass(), entidades.getIdEnt());
                relEntItens.setEntidades(entidades);
            }
            Itens itens = relEntItens.getItens();
            if (itens != null) {
                itens = em.getReference(itens.getClass(), itens.getIdItem());
                relEntItens.setItens(itens);
            }
            TiposEnt tiposEnt = relEntItens.getTiposEnt();
            if (tiposEnt != null) {
                tiposEnt = em.getReference(tiposEnt.getClass(), tiposEnt.getTipoEnt());
                relEntItens.setTiposEnt(tiposEnt);
            }
            Utilizadores userAlteracao = relEntItens.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                relEntItens.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = relEntItens.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                relEntItens.setUserCriacao(userCriacao);
            }
            em.persist(relEntItens);
            if (entidades != null) {
                entidades.getRelEntItensCollection().add(relEntItens);
                entidades = em.merge(entidades);
            }
            if (itens != null) {
                itens.getRelEntItensCollection().add(relEntItens);
                itens = em.merge(itens);
            }
            if (tiposEnt != null) {
                tiposEnt.getRelEntItensCollection().add(relEntItens);
                tiposEnt = em.merge(tiposEnt);
            }
            if (userAlteracao != null) {
                userAlteracao.getRelEntItensCollection().add(relEntItens);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getRelEntItensCollection().add(relEntItens);
                userCriacao = em.merge(userCriacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelEntItens(relEntItens.getRelEntItensPK()) != null) {
                throw new PreexistingEntityException("RelEntItens " + relEntItens + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RelEntItens relEntItens) throws NonexistentEntityException, Exception {
        relEntItens.getRelEntItensPK().setTipoEnt(relEntItens.getTiposEnt().getTipoEnt());
        relEntItens.getRelEntItensPK().setIdEnt(relEntItens.getEntidades().getIdEnt());
        relEntItens.getRelEntItensPK().setIdItem(relEntItens.getItens().getIdItem());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RelEntItens persistentRelEntItens = em.find(RelEntItens.class, relEntItens.getRelEntItensPK());
            Entidades entidadesOld = persistentRelEntItens.getEntidades();
            Entidades entidadesNew = relEntItens.getEntidades();
            Itens itensOld = persistentRelEntItens.getItens();
            Itens itensNew = relEntItens.getItens();
            TiposEnt tiposEntOld = persistentRelEntItens.getTiposEnt();
            TiposEnt tiposEntNew = relEntItens.getTiposEnt();
            Utilizadores userAlteracaoOld = persistentRelEntItens.getUserAlteracao();
            Utilizadores userAlteracaoNew = relEntItens.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentRelEntItens.getUserCriacao();
            Utilizadores userCriacaoNew = relEntItens.getUserCriacao();
            if (entidadesNew != null) {
                entidadesNew = em.getReference(entidadesNew.getClass(), entidadesNew.getIdEnt());
                relEntItens.setEntidades(entidadesNew);
            }
            if (itensNew != null) {
                itensNew = em.getReference(itensNew.getClass(), itensNew.getIdItem());
                relEntItens.setItens(itensNew);
            }
            if (tiposEntNew != null) {
                tiposEntNew = em.getReference(tiposEntNew.getClass(), tiposEntNew.getTipoEnt());
                relEntItens.setTiposEnt(tiposEntNew);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                relEntItens.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                relEntItens.setUserCriacao(userCriacaoNew);
            }
            relEntItens = em.merge(relEntItens);
            if (entidadesOld != null && !entidadesOld.equals(entidadesNew)) {
                entidadesOld.getRelEntItensCollection().remove(relEntItens);
                entidadesOld = em.merge(entidadesOld);
            }
            if (entidadesNew != null && !entidadesNew.equals(entidadesOld)) {
                entidadesNew.getRelEntItensCollection().add(relEntItens);
                entidadesNew = em.merge(entidadesNew);
            }
            if (itensOld != null && !itensOld.equals(itensNew)) {
                itensOld.getRelEntItensCollection().remove(relEntItens);
                itensOld = em.merge(itensOld);
            }
            if (itensNew != null && !itensNew.equals(itensOld)) {
                itensNew.getRelEntItensCollection().add(relEntItens);
                itensNew = em.merge(itensNew);
            }
            if (tiposEntOld != null && !tiposEntOld.equals(tiposEntNew)) {
                tiposEntOld.getRelEntItensCollection().remove(relEntItens);
                tiposEntOld = em.merge(tiposEntOld);
            }
            if (tiposEntNew != null && !tiposEntNew.equals(tiposEntOld)) {
                tiposEntNew.getRelEntItensCollection().add(relEntItens);
                tiposEntNew = em.merge(tiposEntNew);
            }
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getRelEntItensCollection().remove(relEntItens);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getRelEntItensCollection().add(relEntItens);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getRelEntItensCollection().remove(relEntItens);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getRelEntItensCollection().add(relEntItens);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RelEntItensPK id = relEntItens.getRelEntItensPK();
                if (findRelEntItens(id) == null) {
                    throw new NonexistentEntityException("The relEntItens with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RelEntItensPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RelEntItens relEntItens;
            try {
                relEntItens = em.getReference(RelEntItens.class, id);
                relEntItens.getRelEntItensPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relEntItens with id " + id + " no longer exists.", enfe);
            }
            Entidades entidades = relEntItens.getEntidades();
            if (entidades != null) {
                entidades.getRelEntItensCollection().remove(relEntItens);
                entidades = em.merge(entidades);
            }
            Itens itens = relEntItens.getItens();
            if (itens != null) {
                itens.getRelEntItensCollection().remove(relEntItens);
                itens = em.merge(itens);
            }
            TiposEnt tiposEnt = relEntItens.getTiposEnt();
            if (tiposEnt != null) {
                tiposEnt.getRelEntItensCollection().remove(relEntItens);
                tiposEnt = em.merge(tiposEnt);
            }
            Utilizadores userAlteracao = relEntItens.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getRelEntItensCollection().remove(relEntItens);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = relEntItens.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getRelEntItensCollection().remove(relEntItens);
                userCriacao = em.merge(userCriacao);
            }
            em.remove(relEntItens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RelEntItens> findRelEntItensEntities() {
        return findRelEntItensEntities(true, -1, -1);
    }

    public List<RelEntItens> findRelEntItensEntities(int maxResults, int firstResult) {
        return findRelEntItensEntities(false, maxResults, firstResult);
    }

    private List<RelEntItens> findRelEntItensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RelEntItens as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RelEntItens findRelEntItens(RelEntItensPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RelEntItens.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelEntItensCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from RelEntItens as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
