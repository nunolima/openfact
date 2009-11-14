/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.CatEnt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TiposEnt;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Entidades;
import entidades.TaxasDesc;

/**
 *
 * @author User
 */
public class CatEntJpaController {

    public CatEntJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CatEnt catEnt) throws PreexistingEntityException, Exception {
        if (catEnt.getTiposEntCollection() == null) {
            catEnt.setTiposEntCollection(new ArrayList<TiposEnt>());
        }
        if (catEnt.getEntidadesCollection() == null) {
            catEnt.setEntidadesCollection(new ArrayList<Entidades>());
        }
        if (catEnt.getTaxasDescCollection() == null) {
            catEnt.setTaxasDescCollection(new ArrayList<TaxasDesc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposEnt tipoEnt = catEnt.getTipoEnt();
            if (tipoEnt != null) {
                tipoEnt = em.getReference(tipoEnt.getClass(), tipoEnt.getTipoEnt());
                catEnt.setTipoEnt(tipoEnt);
            }
            Collection<TiposEnt> attachedTiposEntCollection = new ArrayList<TiposEnt>();
            for (TiposEnt tiposEntCollectionTiposEntToAttach : catEnt.getTiposEntCollection()) {
                tiposEntCollectionTiposEntToAttach = em.getReference(tiposEntCollectionTiposEntToAttach.getClass(), tiposEntCollectionTiposEntToAttach.getTipoEnt());
                attachedTiposEntCollection.add(tiposEntCollectionTiposEntToAttach);
            }
            catEnt.setTiposEntCollection(attachedTiposEntCollection);
            Collection<Entidades> attachedEntidadesCollection = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionEntidadesToAttach : catEnt.getEntidadesCollection()) {
                entidadesCollectionEntidadesToAttach = em.getReference(entidadesCollectionEntidadesToAttach.getClass(), entidadesCollectionEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection.add(entidadesCollectionEntidadesToAttach);
            }
            catEnt.setEntidadesCollection(attachedEntidadesCollection);
            Collection<TaxasDesc> attachedTaxasDescCollection = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollectionTaxasDescToAttach : catEnt.getTaxasDescCollection()) {
                taxasDescCollectionTaxasDescToAttach = em.getReference(taxasDescCollectionTaxasDescToAttach.getClass(), taxasDescCollectionTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollection.add(taxasDescCollectionTaxasDescToAttach);
            }
            catEnt.setTaxasDescCollection(attachedTaxasDescCollection);
            em.persist(catEnt);
            if (tipoEnt != null) {
                tipoEnt.getCatEntCollection().add(catEnt);
                tipoEnt = em.merge(tipoEnt);
            }
            for (TiposEnt tiposEntCollectionTiposEnt : catEnt.getTiposEntCollection()) {
                CatEnt oldIdCatEntOmissaoOfTiposEntCollectionTiposEnt = tiposEntCollectionTiposEnt.getIdCatEntOmissao();
                tiposEntCollectionTiposEnt.setIdCatEntOmissao(catEnt);
                tiposEntCollectionTiposEnt = em.merge(tiposEntCollectionTiposEnt);
                if (oldIdCatEntOmissaoOfTiposEntCollectionTiposEnt != null) {
                    oldIdCatEntOmissaoOfTiposEntCollectionTiposEnt.getTiposEntCollection().remove(tiposEntCollectionTiposEnt);
                    oldIdCatEntOmissaoOfTiposEntCollectionTiposEnt = em.merge(oldIdCatEntOmissaoOfTiposEntCollectionTiposEnt);
                }
            }
            for (Entidades entidadesCollectionEntidades : catEnt.getEntidadesCollection()) {
                CatEnt oldIdCatEntOfEntidadesCollectionEntidades = entidadesCollectionEntidades.getIdCatEnt();
                entidadesCollectionEntidades.setIdCatEnt(catEnt);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
                if (oldIdCatEntOfEntidadesCollectionEntidades != null) {
                    oldIdCatEntOfEntidadesCollectionEntidades.getEntidadesCollection().remove(entidadesCollectionEntidades);
                    oldIdCatEntOfEntidadesCollectionEntidades = em.merge(oldIdCatEntOfEntidadesCollectionEntidades);
                }
            }
            for (TaxasDesc taxasDescCollectionTaxasDesc : catEnt.getTaxasDescCollection()) {
                CatEnt oldCatEntOfTaxasDescCollectionTaxasDesc = taxasDescCollectionTaxasDesc.getCatEnt();
                taxasDescCollectionTaxasDesc.setCatEnt(catEnt);
                taxasDescCollectionTaxasDesc = em.merge(taxasDescCollectionTaxasDesc);
                if (oldCatEntOfTaxasDescCollectionTaxasDesc != null) {
                    oldCatEntOfTaxasDescCollectionTaxasDesc.getTaxasDescCollection().remove(taxasDescCollectionTaxasDesc);
                    oldCatEntOfTaxasDescCollectionTaxasDesc = em.merge(oldCatEntOfTaxasDescCollectionTaxasDesc);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatEnt(catEnt.getIdCatEnt()) != null) {
                throw new PreexistingEntityException("CatEnt " + catEnt + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CatEnt catEnt) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatEnt persistentCatEnt = em.find(CatEnt.class, catEnt.getIdCatEnt());
            TiposEnt tipoEntOld = persistentCatEnt.getTipoEnt();
            TiposEnt tipoEntNew = catEnt.getTipoEnt();
            Collection<TiposEnt> tiposEntCollectionOld = persistentCatEnt.getTiposEntCollection();
            Collection<TiposEnt> tiposEntCollectionNew = catEnt.getTiposEntCollection();
            Collection<Entidades> entidadesCollectionOld = persistentCatEnt.getEntidadesCollection();
            Collection<Entidades> entidadesCollectionNew = catEnt.getEntidadesCollection();
            Collection<TaxasDesc> taxasDescCollectionOld = persistentCatEnt.getTaxasDescCollection();
            Collection<TaxasDesc> taxasDescCollectionNew = catEnt.getTaxasDescCollection();
            List<String> illegalOrphanMessages = null;
            for (TaxasDesc taxasDescCollectionOldTaxasDesc : taxasDescCollectionOld) {
                if (!taxasDescCollectionNew.contains(taxasDescCollectionOldTaxasDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TaxasDesc " + taxasDescCollectionOldTaxasDesc + " since its catEnt field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoEntNew != null) {
                tipoEntNew = em.getReference(tipoEntNew.getClass(), tipoEntNew.getTipoEnt());
                catEnt.setTipoEnt(tipoEntNew);
            }
            Collection<TiposEnt> attachedTiposEntCollectionNew = new ArrayList<TiposEnt>();
            for (TiposEnt tiposEntCollectionNewTiposEntToAttach : tiposEntCollectionNew) {
                tiposEntCollectionNewTiposEntToAttach = em.getReference(tiposEntCollectionNewTiposEntToAttach.getClass(), tiposEntCollectionNewTiposEntToAttach.getTipoEnt());
                attachedTiposEntCollectionNew.add(tiposEntCollectionNewTiposEntToAttach);
            }
            tiposEntCollectionNew = attachedTiposEntCollectionNew;
            catEnt.setTiposEntCollection(tiposEntCollectionNew);
            Collection<Entidades> attachedEntidadesCollectionNew = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionNewEntidadesToAttach : entidadesCollectionNew) {
                entidadesCollectionNewEntidadesToAttach = em.getReference(entidadesCollectionNewEntidadesToAttach.getClass(), entidadesCollectionNewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollectionNew.add(entidadesCollectionNewEntidadesToAttach);
            }
            entidadesCollectionNew = attachedEntidadesCollectionNew;
            catEnt.setEntidadesCollection(entidadesCollectionNew);
            Collection<TaxasDesc> attachedTaxasDescCollectionNew = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollectionNewTaxasDescToAttach : taxasDescCollectionNew) {
                taxasDescCollectionNewTaxasDescToAttach = em.getReference(taxasDescCollectionNewTaxasDescToAttach.getClass(), taxasDescCollectionNewTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollectionNew.add(taxasDescCollectionNewTaxasDescToAttach);
            }
            taxasDescCollectionNew = attachedTaxasDescCollectionNew;
            catEnt.setTaxasDescCollection(taxasDescCollectionNew);
            catEnt = em.merge(catEnt);
            if (tipoEntOld != null && !tipoEntOld.equals(tipoEntNew)) {
                tipoEntOld.getCatEntCollection().remove(catEnt);
                tipoEntOld = em.merge(tipoEntOld);
            }
            if (tipoEntNew != null && !tipoEntNew.equals(tipoEntOld)) {
                tipoEntNew.getCatEntCollection().add(catEnt);
                tipoEntNew = em.merge(tipoEntNew);
            }
            for (TiposEnt tiposEntCollectionOldTiposEnt : tiposEntCollectionOld) {
                if (!tiposEntCollectionNew.contains(tiposEntCollectionOldTiposEnt)) {
                    tiposEntCollectionOldTiposEnt.setIdCatEntOmissao(null);
                    tiposEntCollectionOldTiposEnt = em.merge(tiposEntCollectionOldTiposEnt);
                }
            }
            for (TiposEnt tiposEntCollectionNewTiposEnt : tiposEntCollectionNew) {
                if (!tiposEntCollectionOld.contains(tiposEntCollectionNewTiposEnt)) {
                    CatEnt oldIdCatEntOmissaoOfTiposEntCollectionNewTiposEnt = tiposEntCollectionNewTiposEnt.getIdCatEntOmissao();
                    tiposEntCollectionNewTiposEnt.setIdCatEntOmissao(catEnt);
                    tiposEntCollectionNewTiposEnt = em.merge(tiposEntCollectionNewTiposEnt);
                    if (oldIdCatEntOmissaoOfTiposEntCollectionNewTiposEnt != null && !oldIdCatEntOmissaoOfTiposEntCollectionNewTiposEnt.equals(catEnt)) {
                        oldIdCatEntOmissaoOfTiposEntCollectionNewTiposEnt.getTiposEntCollection().remove(tiposEntCollectionNewTiposEnt);
                        oldIdCatEntOmissaoOfTiposEntCollectionNewTiposEnt = em.merge(oldIdCatEntOmissaoOfTiposEntCollectionNewTiposEnt);
                    }
                }
            }
            for (Entidades entidadesCollectionOldEntidades : entidadesCollectionOld) {
                if (!entidadesCollectionNew.contains(entidadesCollectionOldEntidades)) {
                    entidadesCollectionOldEntidades.setIdCatEnt(null);
                    entidadesCollectionOldEntidades = em.merge(entidadesCollectionOldEntidades);
                }
            }
            for (Entidades entidadesCollectionNewEntidades : entidadesCollectionNew) {
                if (!entidadesCollectionOld.contains(entidadesCollectionNewEntidades)) {
                    CatEnt oldIdCatEntOfEntidadesCollectionNewEntidades = entidadesCollectionNewEntidades.getIdCatEnt();
                    entidadesCollectionNewEntidades.setIdCatEnt(catEnt);
                    entidadesCollectionNewEntidades = em.merge(entidadesCollectionNewEntidades);
                    if (oldIdCatEntOfEntidadesCollectionNewEntidades != null && !oldIdCatEntOfEntidadesCollectionNewEntidades.equals(catEnt)) {
                        oldIdCatEntOfEntidadesCollectionNewEntidades.getEntidadesCollection().remove(entidadesCollectionNewEntidades);
                        oldIdCatEntOfEntidadesCollectionNewEntidades = em.merge(oldIdCatEntOfEntidadesCollectionNewEntidades);
                    }
                }
            }
            for (TaxasDesc taxasDescCollectionNewTaxasDesc : taxasDescCollectionNew) {
                if (!taxasDescCollectionOld.contains(taxasDescCollectionNewTaxasDesc)) {
                    CatEnt oldCatEntOfTaxasDescCollectionNewTaxasDesc = taxasDescCollectionNewTaxasDesc.getCatEnt();
                    taxasDescCollectionNewTaxasDesc.setCatEnt(catEnt);
                    taxasDescCollectionNewTaxasDesc = em.merge(taxasDescCollectionNewTaxasDesc);
                    if (oldCatEntOfTaxasDescCollectionNewTaxasDesc != null && !oldCatEntOfTaxasDescCollectionNewTaxasDesc.equals(catEnt)) {
                        oldCatEntOfTaxasDescCollectionNewTaxasDesc.getTaxasDescCollection().remove(taxasDescCollectionNewTaxasDesc);
                        oldCatEntOfTaxasDescCollectionNewTaxasDesc = em.merge(oldCatEntOfTaxasDescCollectionNewTaxasDesc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = catEnt.getIdCatEnt();
                if (findCatEnt(id) == null) {
                    throw new NonexistentEntityException("The catEnt with id " + id + " no longer exists.");
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
            CatEnt catEnt;
            try {
                catEnt = em.getReference(CatEnt.class, id);
                catEnt.getIdCatEnt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catEnt with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TaxasDesc> taxasDescCollectionOrphanCheck = catEnt.getTaxasDescCollection();
            for (TaxasDesc taxasDescCollectionOrphanCheckTaxasDesc : taxasDescCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CatEnt (" + catEnt + ") cannot be destroyed since the TaxasDesc " + taxasDescCollectionOrphanCheckTaxasDesc + " in its taxasDescCollection field has a non-nullable catEnt field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TiposEnt tipoEnt = catEnt.getTipoEnt();
            if (tipoEnt != null) {
                tipoEnt.getCatEntCollection().remove(catEnt);
                tipoEnt = em.merge(tipoEnt);
            }
            Collection<TiposEnt> tiposEntCollection = catEnt.getTiposEntCollection();
            for (TiposEnt tiposEntCollectionTiposEnt : tiposEntCollection) {
                tiposEntCollectionTiposEnt.setIdCatEntOmissao(null);
                tiposEntCollectionTiposEnt = em.merge(tiposEntCollectionTiposEnt);
            }
            Collection<Entidades> entidadesCollection = catEnt.getEntidadesCollection();
            for (Entidades entidadesCollectionEntidades : entidadesCollection) {
                entidadesCollectionEntidades.setIdCatEnt(null);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            em.remove(catEnt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CatEnt> findCatEntEntities() {
        return findCatEntEntities(true, -1, -1);
    }

    public List<CatEnt> findCatEntEntities(int maxResults, int firstResult) {
        return findCatEntEntities(false, maxResults, firstResult);
    }

    private List<CatEnt> findCatEntEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CatEnt as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CatEnt findCatEnt(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatEnt.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatEntCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from CatEnt as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
