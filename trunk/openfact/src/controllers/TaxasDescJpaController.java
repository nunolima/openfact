/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.TaxasDesc;
import entidades.TaxasDescPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.CatEnt;
import entidades.CatItens;
import entidades.Descontos;
import entidades.Utilizadores;

/**
 *
 * @author User
 */
public class TaxasDescJpaController {

    public TaxasDescJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TaxasDesc taxasDesc) throws PreexistingEntityException, Exception {
        if (taxasDesc.getTaxasDescPK() == null) {
            taxasDesc.setTaxasDescPK(new TaxasDescPK());
        }
        taxasDesc.getTaxasDescPK().setIdCatItem(taxasDesc.getCatItens().getIdCatItem());
        taxasDesc.getTaxasDescPK().setDesconto(taxasDesc.getDescontos().getDesconto());
        taxasDesc.getTaxasDescPK().setIdCatEnt(taxasDesc.getCatEnt().getIdCatEnt());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatEnt catEnt = taxasDesc.getCatEnt();
            if (catEnt != null) {
                catEnt = em.getReference(catEnt.getClass(), catEnt.getIdCatEnt());
                taxasDesc.setCatEnt(catEnt);
            }
            CatItens catItens = taxasDesc.getCatItens();
            if (catItens != null) {
                catItens = em.getReference(catItens.getClass(), catItens.getIdCatItem());
                taxasDesc.setCatItens(catItens);
            }
            Descontos descontos = taxasDesc.getDescontos();
            if (descontos != null) {
                descontos = em.getReference(descontos.getClass(), descontos.getDesconto());
                taxasDesc.setDescontos(descontos);
            }
            Utilizadores userAlteracao = taxasDesc.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                taxasDesc.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = taxasDesc.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                taxasDesc.setUserCriacao(userCriacao);
            }
            em.persist(taxasDesc);
            if (catEnt != null) {
                catEnt.getTaxasDescCollection().add(taxasDesc);
                catEnt = em.merge(catEnt);
            }
            if (catItens != null) {
                catItens.getTaxasDescCollection().add(taxasDesc);
                catItens = em.merge(catItens);
            }
            if (descontos != null) {
                descontos.getTaxasDescCollection().add(taxasDesc);
                descontos = em.merge(descontos);
            }
            if (userAlteracao != null) {
                userAlteracao.getTaxasDescCollection().add(taxasDesc);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getTaxasDescCollection().add(taxasDesc);
                userCriacao = em.merge(userCriacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTaxasDesc(taxasDesc.getTaxasDescPK()) != null) {
                throw new PreexistingEntityException("TaxasDesc " + taxasDesc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TaxasDesc taxasDesc) throws NonexistentEntityException, Exception {
        taxasDesc.getTaxasDescPK().setIdCatItem(taxasDesc.getCatItens().getIdCatItem());
        taxasDesc.getTaxasDescPK().setDesconto(taxasDesc.getDescontos().getDesconto());
        taxasDesc.getTaxasDescPK().setIdCatEnt(taxasDesc.getCatEnt().getIdCatEnt());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TaxasDesc persistentTaxasDesc = em.find(TaxasDesc.class, taxasDesc.getTaxasDescPK());
            CatEnt catEntOld = persistentTaxasDesc.getCatEnt();
            CatEnt catEntNew = taxasDesc.getCatEnt();
            CatItens catItensOld = persistentTaxasDesc.getCatItens();
            CatItens catItensNew = taxasDesc.getCatItens();
            Descontos descontosOld = persistentTaxasDesc.getDescontos();
            Descontos descontosNew = taxasDesc.getDescontos();
            Utilizadores userAlteracaoOld = persistentTaxasDesc.getUserAlteracao();
            Utilizadores userAlteracaoNew = taxasDesc.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentTaxasDesc.getUserCriacao();
            Utilizadores userCriacaoNew = taxasDesc.getUserCriacao();
            if (catEntNew != null) {
                catEntNew = em.getReference(catEntNew.getClass(), catEntNew.getIdCatEnt());
                taxasDesc.setCatEnt(catEntNew);
            }
            if (catItensNew != null) {
                catItensNew = em.getReference(catItensNew.getClass(), catItensNew.getIdCatItem());
                taxasDesc.setCatItens(catItensNew);
            }
            if (descontosNew != null) {
                descontosNew = em.getReference(descontosNew.getClass(), descontosNew.getDesconto());
                taxasDesc.setDescontos(descontosNew);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                taxasDesc.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                taxasDesc.setUserCriacao(userCriacaoNew);
            }
            taxasDesc = em.merge(taxasDesc);
            if (catEntOld != null && !catEntOld.equals(catEntNew)) {
                catEntOld.getTaxasDescCollection().remove(taxasDesc);
                catEntOld = em.merge(catEntOld);
            }
            if (catEntNew != null && !catEntNew.equals(catEntOld)) {
                catEntNew.getTaxasDescCollection().add(taxasDesc);
                catEntNew = em.merge(catEntNew);
            }
            if (catItensOld != null && !catItensOld.equals(catItensNew)) {
                catItensOld.getTaxasDescCollection().remove(taxasDesc);
                catItensOld = em.merge(catItensOld);
            }
            if (catItensNew != null && !catItensNew.equals(catItensOld)) {
                catItensNew.getTaxasDescCollection().add(taxasDesc);
                catItensNew = em.merge(catItensNew);
            }
            if (descontosOld != null && !descontosOld.equals(descontosNew)) {
                descontosOld.getTaxasDescCollection().remove(taxasDesc);
                descontosOld = em.merge(descontosOld);
            }
            if (descontosNew != null && !descontosNew.equals(descontosOld)) {
                descontosNew.getTaxasDescCollection().add(taxasDesc);
                descontosNew = em.merge(descontosNew);
            }
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getTaxasDescCollection().remove(taxasDesc);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getTaxasDescCollection().add(taxasDesc);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getTaxasDescCollection().remove(taxasDesc);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getTaxasDescCollection().add(taxasDesc);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TaxasDescPK id = taxasDesc.getTaxasDescPK();
                if (findTaxasDesc(id) == null) {
                    throw new NonexistentEntityException("The taxasDesc with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TaxasDescPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TaxasDesc taxasDesc;
            try {
                taxasDesc = em.getReference(TaxasDesc.class, id);
                taxasDesc.getTaxasDescPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The taxasDesc with id " + id + " no longer exists.", enfe);
            }
            CatEnt catEnt = taxasDesc.getCatEnt();
            if (catEnt != null) {
                catEnt.getTaxasDescCollection().remove(taxasDesc);
                catEnt = em.merge(catEnt);
            }
            CatItens catItens = taxasDesc.getCatItens();
            if (catItens != null) {
                catItens.getTaxasDescCollection().remove(taxasDesc);
                catItens = em.merge(catItens);
            }
            Descontos descontos = taxasDesc.getDescontos();
            if (descontos != null) {
                descontos.getTaxasDescCollection().remove(taxasDesc);
                descontos = em.merge(descontos);
            }
            Utilizadores userAlteracao = taxasDesc.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getTaxasDescCollection().remove(taxasDesc);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = taxasDesc.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getTaxasDescCollection().remove(taxasDesc);
                userCriacao = em.merge(userCriacao);
            }
            em.remove(taxasDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TaxasDesc> findTaxasDescEntities() {
        return findTaxasDescEntities(true, -1, -1);
    }

    public List<TaxasDesc> findTaxasDescEntities(int maxResults, int firstResult) {
        return findTaxasDescEntities(false, maxResults, firstResult);
    }

    private List<TaxasDesc> findTaxasDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TaxasDesc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TaxasDesc findTaxasDesc(TaxasDescPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TaxasDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaxasDescCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TaxasDesc as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
