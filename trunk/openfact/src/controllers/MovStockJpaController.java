/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.MovStock;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Docs;
import entidades.Entidades;
import entidades.Itens;
import entidades.TiposMovStock;
import entidades.Utilizadores;

/**
 *
 * @author User
 */
public class MovStockJpaController {

    public MovStockJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MovStock movStock) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docs idDoc = movStock.getIdDoc();
            if (idDoc != null) {
                idDoc = em.getReference(idDoc.getClass(), idDoc.getIdDoc());
                movStock.setIdDoc(idDoc);
            }
            Entidades idEnt = movStock.getIdEnt();
            if (idEnt != null) {
                idEnt = em.getReference(idEnt.getClass(), idEnt.getIdEnt());
                movStock.setIdEnt(idEnt);
            }
            Itens idItem = movStock.getIdItem();
            if (idItem != null) {
                idItem = em.getReference(idItem.getClass(), idItem.getIdItem());
                movStock.setIdItem(idItem);
            }
            TiposMovStock tipoMovStock = movStock.getTipoMovStock();
            if (tipoMovStock != null) {
                tipoMovStock = em.getReference(tipoMovStock.getClass(), tipoMovStock.getTipoMovStock());
                movStock.setTipoMovStock(tipoMovStock);
            }
            Utilizadores userCriacao = movStock.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                movStock.setUserCriacao(userCriacao);
            }
            em.persist(movStock);
            if (idDoc != null) {
                idDoc.getMovStockCollection().add(movStock);
                idDoc = em.merge(idDoc);
            }
            if (idEnt != null) {
                idEnt.getMovStockCollection().add(movStock);
                idEnt = em.merge(idEnt);
            }
            if (idItem != null) {
                idItem.getMovStockCollection().add(movStock);
                idItem = em.merge(idItem);
            }
            if (tipoMovStock != null) {
                tipoMovStock.getMovStockCollection().add(movStock);
                tipoMovStock = em.merge(tipoMovStock);
            }
            if (userCriacao != null) {
                userCriacao.getMovStockCollection().add(movStock);
                userCriacao = em.merge(userCriacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMovStock(movStock.getIdMovStock()) != null) {
                throw new PreexistingEntityException("MovStock " + movStock + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MovStock movStock) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovStock persistentMovStock = em.find(MovStock.class, movStock.getIdMovStock());
            Docs idDocOld = persistentMovStock.getIdDoc();
            Docs idDocNew = movStock.getIdDoc();
            Entidades idEntOld = persistentMovStock.getIdEnt();
            Entidades idEntNew = movStock.getIdEnt();
            Itens idItemOld = persistentMovStock.getIdItem();
            Itens idItemNew = movStock.getIdItem();
            TiposMovStock tipoMovStockOld = persistentMovStock.getTipoMovStock();
            TiposMovStock tipoMovStockNew = movStock.getTipoMovStock();
            Utilizadores userCriacaoOld = persistentMovStock.getUserCriacao();
            Utilizadores userCriacaoNew = movStock.getUserCriacao();
            if (idDocNew != null) {
                idDocNew = em.getReference(idDocNew.getClass(), idDocNew.getIdDoc());
                movStock.setIdDoc(idDocNew);
            }
            if (idEntNew != null) {
                idEntNew = em.getReference(idEntNew.getClass(), idEntNew.getIdEnt());
                movStock.setIdEnt(idEntNew);
            }
            if (idItemNew != null) {
                idItemNew = em.getReference(idItemNew.getClass(), idItemNew.getIdItem());
                movStock.setIdItem(idItemNew);
            }
            if (tipoMovStockNew != null) {
                tipoMovStockNew = em.getReference(tipoMovStockNew.getClass(), tipoMovStockNew.getTipoMovStock());
                movStock.setTipoMovStock(tipoMovStockNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                movStock.setUserCriacao(userCriacaoNew);
            }
            movStock = em.merge(movStock);
            if (idDocOld != null && !idDocOld.equals(idDocNew)) {
                idDocOld.getMovStockCollection().remove(movStock);
                idDocOld = em.merge(idDocOld);
            }
            if (idDocNew != null && !idDocNew.equals(idDocOld)) {
                idDocNew.getMovStockCollection().add(movStock);
                idDocNew = em.merge(idDocNew);
            }
            if (idEntOld != null && !idEntOld.equals(idEntNew)) {
                idEntOld.getMovStockCollection().remove(movStock);
                idEntOld = em.merge(idEntOld);
            }
            if (idEntNew != null && !idEntNew.equals(idEntOld)) {
                idEntNew.getMovStockCollection().add(movStock);
                idEntNew = em.merge(idEntNew);
            }
            if (idItemOld != null && !idItemOld.equals(idItemNew)) {
                idItemOld.getMovStockCollection().remove(movStock);
                idItemOld = em.merge(idItemOld);
            }
            if (idItemNew != null && !idItemNew.equals(idItemOld)) {
                idItemNew.getMovStockCollection().add(movStock);
                idItemNew = em.merge(idItemNew);
            }
            if (tipoMovStockOld != null && !tipoMovStockOld.equals(tipoMovStockNew)) {
                tipoMovStockOld.getMovStockCollection().remove(movStock);
                tipoMovStockOld = em.merge(tipoMovStockOld);
            }
            if (tipoMovStockNew != null && !tipoMovStockNew.equals(tipoMovStockOld)) {
                tipoMovStockNew.getMovStockCollection().add(movStock);
                tipoMovStockNew = em.merge(tipoMovStockNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getMovStockCollection().remove(movStock);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getMovStockCollection().add(movStock);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = movStock.getIdMovStock();
                if (findMovStock(id) == null) {
                    throw new NonexistentEntityException("The movStock with id " + id + " no longer exists.");
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
            MovStock movStock;
            try {
                movStock = em.getReference(MovStock.class, id);
                movStock.getIdMovStock();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movStock with id " + id + " no longer exists.", enfe);
            }
            Docs idDoc = movStock.getIdDoc();
            if (idDoc != null) {
                idDoc.getMovStockCollection().remove(movStock);
                idDoc = em.merge(idDoc);
            }
            Entidades idEnt = movStock.getIdEnt();
            if (idEnt != null) {
                idEnt.getMovStockCollection().remove(movStock);
                idEnt = em.merge(idEnt);
            }
            Itens idItem = movStock.getIdItem();
            if (idItem != null) {
                idItem.getMovStockCollection().remove(movStock);
                idItem = em.merge(idItem);
            }
            TiposMovStock tipoMovStock = movStock.getTipoMovStock();
            if (tipoMovStock != null) {
                tipoMovStock.getMovStockCollection().remove(movStock);
                tipoMovStock = em.merge(tipoMovStock);
            }
            Utilizadores userCriacao = movStock.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getMovStockCollection().remove(movStock);
                userCriacao = em.merge(userCriacao);
            }
            em.remove(movStock);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovStock> findMovStockEntities() {
        return findMovStockEntities(true, -1, -1);
    }

    public List<MovStock> findMovStockEntities(int maxResults, int firstResult) {
        return findMovStockEntities(false, maxResults, firstResult);
    }

    private List<MovStock> findMovStockEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from MovStock as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MovStock findMovStock(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MovStock.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovStockCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from MovStock as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
