/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.ItensAnexos;
import entidades.ItensAnexosPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.Itens;

/**
 *
 * @author User
 */
public class ItensAnexosJpaController {

    public ItensAnexosJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItensAnexos itensAnexos) throws PreexistingEntityException, Exception {
        if (itensAnexos.getItensAnexosPK() == null) {
            itensAnexos.setItensAnexosPK(new ItensAnexosPK());
        }
        itensAnexos.getItensAnexosPK().setIdItem(itensAnexos.getItens1().getIdItem());
        itensAnexos.getItensAnexosPK().setIdItemAnexo(itensAnexos.getItens().getIdItem());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itens itens = itensAnexos.getItens();
            if (itens != null) {
                itens = em.getReference(itens.getClass(), itens.getIdItem());
                itensAnexos.setItens(itens);
            }
            Itens itens1 = itensAnexos.getItens1();
            if (itens1 != null) {
                itens1 = em.getReference(itens1.getClass(), itens1.getIdItem());
                itensAnexos.setItens1(itens1);
            }
            em.persist(itensAnexos);
            if (itens != null) {
                itens.getItensAnexosCollection().add(itensAnexos);
                itens = em.merge(itens);
            }
            if (itens1 != null) {
                itens1.getItensAnexosCollection().add(itensAnexos);
                itens1 = em.merge(itens1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findItensAnexos(itensAnexos.getItensAnexosPK()) != null) {
                throw new PreexistingEntityException("ItensAnexos " + itensAnexos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItensAnexos itensAnexos) throws NonexistentEntityException, Exception {
        itensAnexos.getItensAnexosPK().setIdItem(itensAnexos.getItens1().getIdItem());
        itensAnexos.getItensAnexosPK().setIdItemAnexo(itensAnexos.getItens().getIdItem());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensAnexos persistentItensAnexos = em.find(ItensAnexos.class, itensAnexos.getItensAnexosPK());
            Itens itensOld = persistentItensAnexos.getItens();
            Itens itensNew = itensAnexos.getItens();
            Itens itens1Old = persistentItensAnexos.getItens1();
            Itens itens1New = itensAnexos.getItens1();
            if (itensNew != null) {
                itensNew = em.getReference(itensNew.getClass(), itensNew.getIdItem());
                itensAnexos.setItens(itensNew);
            }
            if (itens1New != null) {
                itens1New = em.getReference(itens1New.getClass(), itens1New.getIdItem());
                itensAnexos.setItens1(itens1New);
            }
            itensAnexos = em.merge(itensAnexos);
            if (itensOld != null && !itensOld.equals(itensNew)) {
                itensOld.getItensAnexosCollection().remove(itensAnexos);
                itensOld = em.merge(itensOld);
            }
            if (itensNew != null && !itensNew.equals(itensOld)) {
                itensNew.getItensAnexosCollection().add(itensAnexos);
                itensNew = em.merge(itensNew);
            }
            if (itens1Old != null && !itens1Old.equals(itens1New)) {
                itens1Old.getItensAnexosCollection().remove(itensAnexos);
                itens1Old = em.merge(itens1Old);
            }
            if (itens1New != null && !itens1New.equals(itens1Old)) {
                itens1New.getItensAnexosCollection().add(itensAnexos);
                itens1New = em.merge(itens1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ItensAnexosPK id = itensAnexos.getItensAnexosPK();
                if (findItensAnexos(id) == null) {
                    throw new NonexistentEntityException("The itensAnexos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ItensAnexosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensAnexos itensAnexos;
            try {
                itensAnexos = em.getReference(ItensAnexos.class, id);
                itensAnexos.getItensAnexosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itensAnexos with id " + id + " no longer exists.", enfe);
            }
            Itens itens = itensAnexos.getItens();
            if (itens != null) {
                itens.getItensAnexosCollection().remove(itensAnexos);
                itens = em.merge(itens);
            }
            Itens itens1 = itensAnexos.getItens1();
            if (itens1 != null) {
                itens1.getItensAnexosCollection().remove(itensAnexos);
                itens1 = em.merge(itens1);
            }
            em.remove(itensAnexos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItensAnexos> findItensAnexosEntities() {
        return findItensAnexosEntities(true, -1, -1);
    }

    public List<ItensAnexos> findItensAnexosEntities(int maxResults, int firstResult) {
        return findItensAnexosEntities(false, maxResults, firstResult);
    }

    private List<ItensAnexos> findItensAnexosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ItensAnexos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ItensAnexos findItensAnexos(ItensAnexosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItensAnexos.class, id);
        } finally {
            em.close();
        }
    }

    public int getItensAnexosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ItensAnexos as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
