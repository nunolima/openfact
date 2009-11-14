/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.TiposEnt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.CatEnt;
import entidades.CondPagam;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Entidades;
import entidades.RelEntItens;
import entidades.RelTiposEnt;

/**
 *
 * @author User
 */
public class TiposEntJpaController {

    public TiposEntJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposEnt tiposEnt) throws PreexistingEntityException, Exception {
        if (tiposEnt.getCatEntCollection() == null) {
            tiposEnt.setCatEntCollection(new ArrayList<CatEnt>());
        }
        if (tiposEnt.getEntidadesCollection() == null) {
            tiposEnt.setEntidadesCollection(new ArrayList<Entidades>());
        }
        if (tiposEnt.getRelEntItensCollection() == null) {
            tiposEnt.setRelEntItensCollection(new ArrayList<RelEntItens>());
        }
        if (tiposEnt.getRelTiposEntCollection() == null) {
            tiposEnt.setRelTiposEntCollection(new ArrayList<RelTiposEnt>());
        }
        if (tiposEnt.getRelTiposEntCollection1() == null) {
            tiposEnt.setRelTiposEntCollection1(new ArrayList<RelTiposEnt>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatEnt idCatEntOmissao = tiposEnt.getIdCatEntOmissao();
            if (idCatEntOmissao != null) {
                idCatEntOmissao = em.getReference(idCatEntOmissao.getClass(), idCatEntOmissao.getIdCatEnt());
                tiposEnt.setIdCatEntOmissao(idCatEntOmissao);
            }
            CondPagam condPagamOmissao = tiposEnt.getCondPagamOmissao();
            if (condPagamOmissao != null) {
                condPagamOmissao = em.getReference(condPagamOmissao.getClass(), condPagamOmissao.getCondPagam());
                tiposEnt.setCondPagamOmissao(condPagamOmissao);
            }
            Collection<CatEnt> attachedCatEntCollection = new ArrayList<CatEnt>();
            for (CatEnt catEntCollectionCatEntToAttach : tiposEnt.getCatEntCollection()) {
                catEntCollectionCatEntToAttach = em.getReference(catEntCollectionCatEntToAttach.getClass(), catEntCollectionCatEntToAttach.getIdCatEnt());
                attachedCatEntCollection.add(catEntCollectionCatEntToAttach);
            }
            tiposEnt.setCatEntCollection(attachedCatEntCollection);
            Collection<Entidades> attachedEntidadesCollection = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionEntidadesToAttach : tiposEnt.getEntidadesCollection()) {
                entidadesCollectionEntidadesToAttach = em.getReference(entidadesCollectionEntidadesToAttach.getClass(), entidadesCollectionEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection.add(entidadesCollectionEntidadesToAttach);
            }
            tiposEnt.setEntidadesCollection(attachedEntidadesCollection);
            Collection<RelEntItens> attachedRelEntItensCollection = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollectionRelEntItensToAttach : tiposEnt.getRelEntItensCollection()) {
                relEntItensCollectionRelEntItensToAttach = em.getReference(relEntItensCollectionRelEntItensToAttach.getClass(), relEntItensCollectionRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollection.add(relEntItensCollectionRelEntItensToAttach);
            }
            tiposEnt.setRelEntItensCollection(attachedRelEntItensCollection);
            Collection<RelTiposEnt> attachedRelTiposEntCollection = new ArrayList<RelTiposEnt>();
            for (RelTiposEnt relTiposEntCollectionRelTiposEntToAttach : tiposEnt.getRelTiposEntCollection()) {
                relTiposEntCollectionRelTiposEntToAttach = em.getReference(relTiposEntCollectionRelTiposEntToAttach.getClass(), relTiposEntCollectionRelTiposEntToAttach.getIdRelacao());
                attachedRelTiposEntCollection.add(relTiposEntCollectionRelTiposEntToAttach);
            }
            tiposEnt.setRelTiposEntCollection(attachedRelTiposEntCollection);
            Collection<RelTiposEnt> attachedRelTiposEntCollection1 = new ArrayList<RelTiposEnt>();
            for (RelTiposEnt relTiposEntCollection1RelTiposEntToAttach : tiposEnt.getRelTiposEntCollection1()) {
                relTiposEntCollection1RelTiposEntToAttach = em.getReference(relTiposEntCollection1RelTiposEntToAttach.getClass(), relTiposEntCollection1RelTiposEntToAttach.getIdRelacao());
                attachedRelTiposEntCollection1.add(relTiposEntCollection1RelTiposEntToAttach);
            }
            tiposEnt.setRelTiposEntCollection1(attachedRelTiposEntCollection1);
            em.persist(tiposEnt);
            if (idCatEntOmissao != null) {
                TiposEnt oldTipoEntOfIdCatEntOmissao = idCatEntOmissao.getTipoEnt();
                if (oldTipoEntOfIdCatEntOmissao != null) {
                    oldTipoEntOfIdCatEntOmissao.setIdCatEntOmissao(null);
                    oldTipoEntOfIdCatEntOmissao = em.merge(oldTipoEntOfIdCatEntOmissao);
                }
                idCatEntOmissao.setTipoEnt(tiposEnt);
                idCatEntOmissao = em.merge(idCatEntOmissao);
            }
            if (condPagamOmissao != null) {
                condPagamOmissao.getTiposEntCollection().add(tiposEnt);
                condPagamOmissao = em.merge(condPagamOmissao);
            }
            for (CatEnt catEntCollectionCatEnt : tiposEnt.getCatEntCollection()) {
                TiposEnt oldTipoEntOfCatEntCollectionCatEnt = catEntCollectionCatEnt.getTipoEnt();
                catEntCollectionCatEnt.setTipoEnt(tiposEnt);
                catEntCollectionCatEnt = em.merge(catEntCollectionCatEnt);
                if (oldTipoEntOfCatEntCollectionCatEnt != null) {
                    oldTipoEntOfCatEntCollectionCatEnt.getCatEntCollection().remove(catEntCollectionCatEnt);
                    oldTipoEntOfCatEntCollectionCatEnt = em.merge(oldTipoEntOfCatEntCollectionCatEnt);
                }
            }
            for (Entidades entidadesCollectionEntidades : tiposEnt.getEntidadesCollection()) {
                TiposEnt oldTipoEntOfEntidadesCollectionEntidades = entidadesCollectionEntidades.getTipoEnt();
                entidadesCollectionEntidades.setTipoEnt(tiposEnt);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
                if (oldTipoEntOfEntidadesCollectionEntidades != null) {
                    oldTipoEntOfEntidadesCollectionEntidades.getEntidadesCollection().remove(entidadesCollectionEntidades);
                    oldTipoEntOfEntidadesCollectionEntidades = em.merge(oldTipoEntOfEntidadesCollectionEntidades);
                }
            }
            for (RelEntItens relEntItensCollectionRelEntItens : tiposEnt.getRelEntItensCollection()) {
                TiposEnt oldTiposEntOfRelEntItensCollectionRelEntItens = relEntItensCollectionRelEntItens.getTiposEnt();
                relEntItensCollectionRelEntItens.setTiposEnt(tiposEnt);
                relEntItensCollectionRelEntItens = em.merge(relEntItensCollectionRelEntItens);
                if (oldTiposEntOfRelEntItensCollectionRelEntItens != null) {
                    oldTiposEntOfRelEntItensCollectionRelEntItens.getRelEntItensCollection().remove(relEntItensCollectionRelEntItens);
                    oldTiposEntOfRelEntItensCollectionRelEntItens = em.merge(oldTiposEntOfRelEntItensCollectionRelEntItens);
                }
            }
            for (RelTiposEnt relTiposEntCollectionRelTiposEnt : tiposEnt.getRelTiposEntCollection()) {
                TiposEnt oldTipoEntOfRelTiposEntCollectionRelTiposEnt = relTiposEntCollectionRelTiposEnt.getTipoEnt();
                relTiposEntCollectionRelTiposEnt.setTipoEnt(tiposEnt);
                relTiposEntCollectionRelTiposEnt = em.merge(relTiposEntCollectionRelTiposEnt);
                if (oldTipoEntOfRelTiposEntCollectionRelTiposEnt != null) {
                    oldTipoEntOfRelTiposEntCollectionRelTiposEnt.getRelTiposEntCollection().remove(relTiposEntCollectionRelTiposEnt);
                    oldTipoEntOfRelTiposEntCollectionRelTiposEnt = em.merge(oldTipoEntOfRelTiposEntCollectionRelTiposEnt);
                }
            }
            for (RelTiposEnt relTiposEntCollection1RelTiposEnt : tiposEnt.getRelTiposEntCollection1()) {
                TiposEnt oldTipoEntRelOfRelTiposEntCollection1RelTiposEnt = relTiposEntCollection1RelTiposEnt.getTipoEntRel();
                relTiposEntCollection1RelTiposEnt.setTipoEntRel(tiposEnt);
                relTiposEntCollection1RelTiposEnt = em.merge(relTiposEntCollection1RelTiposEnt);
                if (oldTipoEntRelOfRelTiposEntCollection1RelTiposEnt != null) {
                    oldTipoEntRelOfRelTiposEntCollection1RelTiposEnt.getRelTiposEntCollection1().remove(relTiposEntCollection1RelTiposEnt);
                    oldTipoEntRelOfRelTiposEntCollection1RelTiposEnt = em.merge(oldTipoEntRelOfRelTiposEntCollection1RelTiposEnt);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiposEnt(tiposEnt.getTipoEnt()) != null) {
                throw new PreexistingEntityException("TiposEnt " + tiposEnt + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposEnt tiposEnt) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposEnt persistentTiposEnt = em.find(TiposEnt.class, tiposEnt.getTipoEnt());
            CatEnt idCatEntOmissaoOld = persistentTiposEnt.getIdCatEntOmissao();
            CatEnt idCatEntOmissaoNew = tiposEnt.getIdCatEntOmissao();
            CondPagam condPagamOmissaoOld = persistentTiposEnt.getCondPagamOmissao();
            CondPagam condPagamOmissaoNew = tiposEnt.getCondPagamOmissao();
            Collection<CatEnt> catEntCollectionOld = persistentTiposEnt.getCatEntCollection();
            Collection<CatEnt> catEntCollectionNew = tiposEnt.getCatEntCollection();
            Collection<Entidades> entidadesCollectionOld = persistentTiposEnt.getEntidadesCollection();
            Collection<Entidades> entidadesCollectionNew = tiposEnt.getEntidadesCollection();
            Collection<RelEntItens> relEntItensCollectionOld = persistentTiposEnt.getRelEntItensCollection();
            Collection<RelEntItens> relEntItensCollectionNew = tiposEnt.getRelEntItensCollection();
            Collection<RelTiposEnt> relTiposEntCollectionOld = persistentTiposEnt.getRelTiposEntCollection();
            Collection<RelTiposEnt> relTiposEntCollectionNew = tiposEnt.getRelTiposEntCollection();
            Collection<RelTiposEnt> relTiposEntCollection1Old = persistentTiposEnt.getRelTiposEntCollection1();
            Collection<RelTiposEnt> relTiposEntCollection1New = tiposEnt.getRelTiposEntCollection1();
            List<String> illegalOrphanMessages = null;
            for (RelEntItens relEntItensCollectionOldRelEntItens : relEntItensCollectionOld) {
                if (!relEntItensCollectionNew.contains(relEntItensCollectionOldRelEntItens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelEntItens " + relEntItensCollectionOldRelEntItens + " since its tiposEnt field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCatEntOmissaoNew != null) {
                idCatEntOmissaoNew = em.getReference(idCatEntOmissaoNew.getClass(), idCatEntOmissaoNew.getIdCatEnt());
                tiposEnt.setIdCatEntOmissao(idCatEntOmissaoNew);
            }
            if (condPagamOmissaoNew != null) {
                condPagamOmissaoNew = em.getReference(condPagamOmissaoNew.getClass(), condPagamOmissaoNew.getCondPagam());
                tiposEnt.setCondPagamOmissao(condPagamOmissaoNew);
            }
            Collection<CatEnt> attachedCatEntCollectionNew = new ArrayList<CatEnt>();
            for (CatEnt catEntCollectionNewCatEntToAttach : catEntCollectionNew) {
                catEntCollectionNewCatEntToAttach = em.getReference(catEntCollectionNewCatEntToAttach.getClass(), catEntCollectionNewCatEntToAttach.getIdCatEnt());
                attachedCatEntCollectionNew.add(catEntCollectionNewCatEntToAttach);
            }
            catEntCollectionNew = attachedCatEntCollectionNew;
            tiposEnt.setCatEntCollection(catEntCollectionNew);
            Collection<Entidades> attachedEntidadesCollectionNew = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionNewEntidadesToAttach : entidadesCollectionNew) {
                entidadesCollectionNewEntidadesToAttach = em.getReference(entidadesCollectionNewEntidadesToAttach.getClass(), entidadesCollectionNewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollectionNew.add(entidadesCollectionNewEntidadesToAttach);
            }
            entidadesCollectionNew = attachedEntidadesCollectionNew;
            tiposEnt.setEntidadesCollection(entidadesCollectionNew);
            Collection<RelEntItens> attachedRelEntItensCollectionNew = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollectionNewRelEntItensToAttach : relEntItensCollectionNew) {
                relEntItensCollectionNewRelEntItensToAttach = em.getReference(relEntItensCollectionNewRelEntItensToAttach.getClass(), relEntItensCollectionNewRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollectionNew.add(relEntItensCollectionNewRelEntItensToAttach);
            }
            relEntItensCollectionNew = attachedRelEntItensCollectionNew;
            tiposEnt.setRelEntItensCollection(relEntItensCollectionNew);
            Collection<RelTiposEnt> attachedRelTiposEntCollectionNew = new ArrayList<RelTiposEnt>();
            for (RelTiposEnt relTiposEntCollectionNewRelTiposEntToAttach : relTiposEntCollectionNew) {
                relTiposEntCollectionNewRelTiposEntToAttach = em.getReference(relTiposEntCollectionNewRelTiposEntToAttach.getClass(), relTiposEntCollectionNewRelTiposEntToAttach.getIdRelacao());
                attachedRelTiposEntCollectionNew.add(relTiposEntCollectionNewRelTiposEntToAttach);
            }
            relTiposEntCollectionNew = attachedRelTiposEntCollectionNew;
            tiposEnt.setRelTiposEntCollection(relTiposEntCollectionNew);
            Collection<RelTiposEnt> attachedRelTiposEntCollection1New = new ArrayList<RelTiposEnt>();
            for (RelTiposEnt relTiposEntCollection1NewRelTiposEntToAttach : relTiposEntCollection1New) {
                relTiposEntCollection1NewRelTiposEntToAttach = em.getReference(relTiposEntCollection1NewRelTiposEntToAttach.getClass(), relTiposEntCollection1NewRelTiposEntToAttach.getIdRelacao());
                attachedRelTiposEntCollection1New.add(relTiposEntCollection1NewRelTiposEntToAttach);
            }
            relTiposEntCollection1New = attachedRelTiposEntCollection1New;
            tiposEnt.setRelTiposEntCollection1(relTiposEntCollection1New);
            tiposEnt = em.merge(tiposEnt);
            if (idCatEntOmissaoOld != null && !idCatEntOmissaoOld.equals(idCatEntOmissaoNew)) {
                idCatEntOmissaoOld.setTipoEnt(null);
                idCatEntOmissaoOld = em.merge(idCatEntOmissaoOld);
            }
            if (idCatEntOmissaoNew != null && !idCatEntOmissaoNew.equals(idCatEntOmissaoOld)) {
                TiposEnt oldTipoEntOfIdCatEntOmissao = idCatEntOmissaoNew.getTipoEnt();
                if (oldTipoEntOfIdCatEntOmissao != null) {
                    oldTipoEntOfIdCatEntOmissao.setIdCatEntOmissao(null);
                    oldTipoEntOfIdCatEntOmissao = em.merge(oldTipoEntOfIdCatEntOmissao);
                }
                idCatEntOmissaoNew.setTipoEnt(tiposEnt);
                idCatEntOmissaoNew = em.merge(idCatEntOmissaoNew);
            }
            if (condPagamOmissaoOld != null && !condPagamOmissaoOld.equals(condPagamOmissaoNew)) {
                condPagamOmissaoOld.getTiposEntCollection().remove(tiposEnt);
                condPagamOmissaoOld = em.merge(condPagamOmissaoOld);
            }
            if (condPagamOmissaoNew != null && !condPagamOmissaoNew.equals(condPagamOmissaoOld)) {
                condPagamOmissaoNew.getTiposEntCollection().add(tiposEnt);
                condPagamOmissaoNew = em.merge(condPagamOmissaoNew);
            }
            for (CatEnt catEntCollectionOldCatEnt : catEntCollectionOld) {
                if (!catEntCollectionNew.contains(catEntCollectionOldCatEnt)) {
                    catEntCollectionOldCatEnt.setTipoEnt(null);
                    catEntCollectionOldCatEnt = em.merge(catEntCollectionOldCatEnt);
                }
            }
            for (CatEnt catEntCollectionNewCatEnt : catEntCollectionNew) {
                if (!catEntCollectionOld.contains(catEntCollectionNewCatEnt)) {
                    TiposEnt oldTipoEntOfCatEntCollectionNewCatEnt = catEntCollectionNewCatEnt.getTipoEnt();
                    catEntCollectionNewCatEnt.setTipoEnt(tiposEnt);
                    catEntCollectionNewCatEnt = em.merge(catEntCollectionNewCatEnt);
                    if (oldTipoEntOfCatEntCollectionNewCatEnt != null && !oldTipoEntOfCatEntCollectionNewCatEnt.equals(tiposEnt)) {
                        oldTipoEntOfCatEntCollectionNewCatEnt.getCatEntCollection().remove(catEntCollectionNewCatEnt);
                        oldTipoEntOfCatEntCollectionNewCatEnt = em.merge(oldTipoEntOfCatEntCollectionNewCatEnt);
                    }
                }
            }
            for (Entidades entidadesCollectionOldEntidades : entidadesCollectionOld) {
                if (!entidadesCollectionNew.contains(entidadesCollectionOldEntidades)) {
                    entidadesCollectionOldEntidades.setTipoEnt(null);
                    entidadesCollectionOldEntidades = em.merge(entidadesCollectionOldEntidades);
                }
            }
            for (Entidades entidadesCollectionNewEntidades : entidadesCollectionNew) {
                if (!entidadesCollectionOld.contains(entidadesCollectionNewEntidades)) {
                    TiposEnt oldTipoEntOfEntidadesCollectionNewEntidades = entidadesCollectionNewEntidades.getTipoEnt();
                    entidadesCollectionNewEntidades.setTipoEnt(tiposEnt);
                    entidadesCollectionNewEntidades = em.merge(entidadesCollectionNewEntidades);
                    if (oldTipoEntOfEntidadesCollectionNewEntidades != null && !oldTipoEntOfEntidadesCollectionNewEntidades.equals(tiposEnt)) {
                        oldTipoEntOfEntidadesCollectionNewEntidades.getEntidadesCollection().remove(entidadesCollectionNewEntidades);
                        oldTipoEntOfEntidadesCollectionNewEntidades = em.merge(oldTipoEntOfEntidadesCollectionNewEntidades);
                    }
                }
            }
            for (RelEntItens relEntItensCollectionNewRelEntItens : relEntItensCollectionNew) {
                if (!relEntItensCollectionOld.contains(relEntItensCollectionNewRelEntItens)) {
                    TiposEnt oldTiposEntOfRelEntItensCollectionNewRelEntItens = relEntItensCollectionNewRelEntItens.getTiposEnt();
                    relEntItensCollectionNewRelEntItens.setTiposEnt(tiposEnt);
                    relEntItensCollectionNewRelEntItens = em.merge(relEntItensCollectionNewRelEntItens);
                    if (oldTiposEntOfRelEntItensCollectionNewRelEntItens != null && !oldTiposEntOfRelEntItensCollectionNewRelEntItens.equals(tiposEnt)) {
                        oldTiposEntOfRelEntItensCollectionNewRelEntItens.getRelEntItensCollection().remove(relEntItensCollectionNewRelEntItens);
                        oldTiposEntOfRelEntItensCollectionNewRelEntItens = em.merge(oldTiposEntOfRelEntItensCollectionNewRelEntItens);
                    }
                }
            }
            for (RelTiposEnt relTiposEntCollectionOldRelTiposEnt : relTiposEntCollectionOld) {
                if (!relTiposEntCollectionNew.contains(relTiposEntCollectionOldRelTiposEnt)) {
                    relTiposEntCollectionOldRelTiposEnt.setTipoEnt(null);
                    relTiposEntCollectionOldRelTiposEnt = em.merge(relTiposEntCollectionOldRelTiposEnt);
                }
            }
            for (RelTiposEnt relTiposEntCollectionNewRelTiposEnt : relTiposEntCollectionNew) {
                if (!relTiposEntCollectionOld.contains(relTiposEntCollectionNewRelTiposEnt)) {
                    TiposEnt oldTipoEntOfRelTiposEntCollectionNewRelTiposEnt = relTiposEntCollectionNewRelTiposEnt.getTipoEnt();
                    relTiposEntCollectionNewRelTiposEnt.setTipoEnt(tiposEnt);
                    relTiposEntCollectionNewRelTiposEnt = em.merge(relTiposEntCollectionNewRelTiposEnt);
                    if (oldTipoEntOfRelTiposEntCollectionNewRelTiposEnt != null && !oldTipoEntOfRelTiposEntCollectionNewRelTiposEnt.equals(tiposEnt)) {
                        oldTipoEntOfRelTiposEntCollectionNewRelTiposEnt.getRelTiposEntCollection().remove(relTiposEntCollectionNewRelTiposEnt);
                        oldTipoEntOfRelTiposEntCollectionNewRelTiposEnt = em.merge(oldTipoEntOfRelTiposEntCollectionNewRelTiposEnt);
                    }
                }
            }
            for (RelTiposEnt relTiposEntCollection1OldRelTiposEnt : relTiposEntCollection1Old) {
                if (!relTiposEntCollection1New.contains(relTiposEntCollection1OldRelTiposEnt)) {
                    relTiposEntCollection1OldRelTiposEnt.setTipoEntRel(null);
                    relTiposEntCollection1OldRelTiposEnt = em.merge(relTiposEntCollection1OldRelTiposEnt);
                }
            }
            for (RelTiposEnt relTiposEntCollection1NewRelTiposEnt : relTiposEntCollection1New) {
                if (!relTiposEntCollection1Old.contains(relTiposEntCollection1NewRelTiposEnt)) {
                    TiposEnt oldTipoEntRelOfRelTiposEntCollection1NewRelTiposEnt = relTiposEntCollection1NewRelTiposEnt.getTipoEntRel();
                    relTiposEntCollection1NewRelTiposEnt.setTipoEntRel(tiposEnt);
                    relTiposEntCollection1NewRelTiposEnt = em.merge(relTiposEntCollection1NewRelTiposEnt);
                    if (oldTipoEntRelOfRelTiposEntCollection1NewRelTiposEnt != null && !oldTipoEntRelOfRelTiposEntCollection1NewRelTiposEnt.equals(tiposEnt)) {
                        oldTipoEntRelOfRelTiposEntCollection1NewRelTiposEnt.getRelTiposEntCollection1().remove(relTiposEntCollection1NewRelTiposEnt);
                        oldTipoEntRelOfRelTiposEntCollection1NewRelTiposEnt = em.merge(oldTipoEntRelOfRelTiposEntCollection1NewRelTiposEnt);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tiposEnt.getTipoEnt();
                if (findTiposEnt(id) == null) {
                    throw new NonexistentEntityException("The tiposEnt with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposEnt tiposEnt;
            try {
                tiposEnt = em.getReference(TiposEnt.class, id);
                tiposEnt.getTipoEnt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposEnt with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RelEntItens> relEntItensCollectionOrphanCheck = tiposEnt.getRelEntItensCollection();
            for (RelEntItens relEntItensCollectionOrphanCheckRelEntItens : relEntItensCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TiposEnt (" + tiposEnt + ") cannot be destroyed since the RelEntItens " + relEntItensCollectionOrphanCheckRelEntItens + " in its relEntItensCollection field has a non-nullable tiposEnt field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CatEnt idCatEntOmissao = tiposEnt.getIdCatEntOmissao();
            if (idCatEntOmissao != null) {
                idCatEntOmissao.setTipoEnt(null);
                idCatEntOmissao = em.merge(idCatEntOmissao);
            }
            CondPagam condPagamOmissao = tiposEnt.getCondPagamOmissao();
            if (condPagamOmissao != null) {
                condPagamOmissao.getTiposEntCollection().remove(tiposEnt);
                condPagamOmissao = em.merge(condPagamOmissao);
            }
            Collection<CatEnt> catEntCollection = tiposEnt.getCatEntCollection();
            for (CatEnt catEntCollectionCatEnt : catEntCollection) {
                catEntCollectionCatEnt.setTipoEnt(null);
                catEntCollectionCatEnt = em.merge(catEntCollectionCatEnt);
            }
            Collection<Entidades> entidadesCollection = tiposEnt.getEntidadesCollection();
            for (Entidades entidadesCollectionEntidades : entidadesCollection) {
                entidadesCollectionEntidades.setTipoEnt(null);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            Collection<RelTiposEnt> relTiposEntCollection = tiposEnt.getRelTiposEntCollection();
            for (RelTiposEnt relTiposEntCollectionRelTiposEnt : relTiposEntCollection) {
                relTiposEntCollectionRelTiposEnt.setTipoEnt(null);
                relTiposEntCollectionRelTiposEnt = em.merge(relTiposEntCollectionRelTiposEnt);
            }
            Collection<RelTiposEnt> relTiposEntCollection1 = tiposEnt.getRelTiposEntCollection1();
            for (RelTiposEnt relTiposEntCollection1RelTiposEnt : relTiposEntCollection1) {
                relTiposEntCollection1RelTiposEnt.setTipoEntRel(null);
                relTiposEntCollection1RelTiposEnt = em.merge(relTiposEntCollection1RelTiposEnt);
            }
            em.remove(tiposEnt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposEnt> findTiposEntEntities() {
        return findTiposEntEntities(true, -1, -1);
    }

    public List<TiposEnt> findTiposEntEntities(int maxResults, int firstResult) {
        return findTiposEntEntities(false, maxResults, firstResult);
    }

    private List<TiposEnt> findTiposEntEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TiposEnt as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TiposEnt findTiposEnt(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposEnt.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposEntCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from TiposEnt as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
