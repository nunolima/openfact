/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.CatEnt;
import entidades.CondPagam;
import entidades.Entidades;
import entidades.ModosExp;
import entidades.TiposEnt;
import entidades.Utilizadores;
import entidades.Zonas;
import java.util.ArrayList;
import java.util.Collection;
import entidades.ContasBanco;
import entidades.MovStock;
import entidades.Fotos;
import entidades.RelEntItens;
import entidades.RelEnt;

/**
 *
 * @author User
 */
public class EntidadesJpaController {

    public EntidadesJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entidades entidades) throws PreexistingEntityException, Exception {
        if (entidades.getZonasCollection() == null) {
            entidades.setZonasCollection(new ArrayList<Zonas>());
        }
        if (entidades.getContasBancoCollection() == null) {
            entidades.setContasBancoCollection(new ArrayList<ContasBanco>());
        }
        if (entidades.getContasBancoCollection1() == null) {
            entidades.setContasBancoCollection1(new ArrayList<ContasBanco>());
        }
        if (entidades.getMovStockCollection() == null) {
            entidades.setMovStockCollection(new ArrayList<MovStock>());
        }
        if (entidades.getFotosCollection() == null) {
            entidades.setFotosCollection(new ArrayList<Fotos>());
        }
        if (entidades.getEntidadesCollection() == null) {
            entidades.setEntidadesCollection(new ArrayList<Entidades>());
        }
        if (entidades.getRelEntItensCollection() == null) {
            entidades.setRelEntItensCollection(new ArrayList<RelEntItens>());
        }
        if (entidades.getRelEntCollection() == null) {
            entidades.setRelEntCollection(new ArrayList<RelEnt>());
        }
        if (entidades.getRelEntCollection1() == null) {
            entidades.setRelEntCollection1(new ArrayList<RelEnt>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatEnt idCatEnt = entidades.getIdCatEnt();
            if (idCatEnt != null) {
                idCatEnt = em.getReference(idCatEnt.getClass(), idCatEnt.getIdCatEnt());
                entidades.setIdCatEnt(idCatEnt);
            }
            CondPagam condPagam = entidades.getCondPagam();
            if (condPagam != null) {
                condPagam = em.getReference(condPagam.getClass(), condPagam.getCondPagam());
                entidades.setCondPagam(condPagam);
            }
            Entidades idEntVendedor = entidades.getIdEntVendedor();
            if (idEntVendedor != null) {
                idEntVendedor = em.getReference(idEntVendedor.getClass(), idEntVendedor.getIdEnt());
                entidades.setIdEntVendedor(idEntVendedor);
            }
            ModosExp idModoExp = entidades.getIdModoExp();
            if (idModoExp != null) {
                idModoExp = em.getReference(idModoExp.getClass(), idModoExp.getIdModoExp());
                entidades.setIdModoExp(idModoExp);
            }
            TiposEnt tipoEnt = entidades.getTipoEnt();
            if (tipoEnt != null) {
                tipoEnt = em.getReference(tipoEnt.getClass(), tipoEnt.getTipoEnt());
                entidades.setTipoEnt(tipoEnt);
            }
            Utilizadores userAlteracao = entidades.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                entidades.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = entidades.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                entidades.setUserCriacao(userCriacao);
            }
            Zonas zona = entidades.getZona();
            if (zona != null) {
                zona = em.getReference(zona.getClass(), zona.getZona());
                entidades.setZona(zona);
            }
            Collection<Zonas> attachedZonasCollection = new ArrayList<Zonas>();
            for (Zonas zonasCollectionZonasToAttach : entidades.getZonasCollection()) {
                zonasCollectionZonasToAttach = em.getReference(zonasCollectionZonasToAttach.getClass(), zonasCollectionZonasToAttach.getZona());
                attachedZonasCollection.add(zonasCollectionZonasToAttach);
            }
            entidades.setZonasCollection(attachedZonasCollection);
            Collection<ContasBanco> attachedContasBancoCollection = new ArrayList<ContasBanco>();
            for (ContasBanco contasBancoCollectionContasBancoToAttach : entidades.getContasBancoCollection()) {
                contasBancoCollectionContasBancoToAttach = em.getReference(contasBancoCollectionContasBancoToAttach.getClass(), contasBancoCollectionContasBancoToAttach.getIdConta());
                attachedContasBancoCollection.add(contasBancoCollectionContasBancoToAttach);
            }
            entidades.setContasBancoCollection(attachedContasBancoCollection);
            Collection<ContasBanco> attachedContasBancoCollection1 = new ArrayList<ContasBanco>();
            for (ContasBanco contasBancoCollection1ContasBancoToAttach : entidades.getContasBancoCollection1()) {
                contasBancoCollection1ContasBancoToAttach = em.getReference(contasBancoCollection1ContasBancoToAttach.getClass(), contasBancoCollection1ContasBancoToAttach.getIdConta());
                attachedContasBancoCollection1.add(contasBancoCollection1ContasBancoToAttach);
            }
            entidades.setContasBancoCollection1(attachedContasBancoCollection1);
            Collection<MovStock> attachedMovStockCollection = new ArrayList<MovStock>();
            for (MovStock movStockCollectionMovStockToAttach : entidades.getMovStockCollection()) {
                movStockCollectionMovStockToAttach = em.getReference(movStockCollectionMovStockToAttach.getClass(), movStockCollectionMovStockToAttach.getIdMovStock());
                attachedMovStockCollection.add(movStockCollectionMovStockToAttach);
            }
            entidades.setMovStockCollection(attachedMovStockCollection);
            Collection<Fotos> attachedFotosCollection = new ArrayList<Fotos>();
            for (Fotos fotosCollectionFotosToAttach : entidades.getFotosCollection()) {
                fotosCollectionFotosToAttach = em.getReference(fotosCollectionFotosToAttach.getClass(), fotosCollectionFotosToAttach.getFotosPK());
                attachedFotosCollection.add(fotosCollectionFotosToAttach);
            }
            entidades.setFotosCollection(attachedFotosCollection);
            Collection<Entidades> attachedEntidadesCollection = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionEntidadesToAttach : entidades.getEntidadesCollection()) {
                entidadesCollectionEntidadesToAttach = em.getReference(entidadesCollectionEntidadesToAttach.getClass(), entidadesCollectionEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection.add(entidadesCollectionEntidadesToAttach);
            }
            entidades.setEntidadesCollection(attachedEntidadesCollection);
            Collection<RelEntItens> attachedRelEntItensCollection = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollectionRelEntItensToAttach : entidades.getRelEntItensCollection()) {
                relEntItensCollectionRelEntItensToAttach = em.getReference(relEntItensCollectionRelEntItensToAttach.getClass(), relEntItensCollectionRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollection.add(relEntItensCollectionRelEntItensToAttach);
            }
            entidades.setRelEntItensCollection(attachedRelEntItensCollection);
            Collection<RelEnt> attachedRelEntCollection = new ArrayList<RelEnt>();
            for (RelEnt relEntCollectionRelEntToAttach : entidades.getRelEntCollection()) {
                relEntCollectionRelEntToAttach = em.getReference(relEntCollectionRelEntToAttach.getClass(), relEntCollectionRelEntToAttach.getRelEntPK());
                attachedRelEntCollection.add(relEntCollectionRelEntToAttach);
            }
            entidades.setRelEntCollection(attachedRelEntCollection);
            Collection<RelEnt> attachedRelEntCollection1 = new ArrayList<RelEnt>();
            for (RelEnt relEntCollection1RelEntToAttach : entidades.getRelEntCollection1()) {
                relEntCollection1RelEntToAttach = em.getReference(relEntCollection1RelEntToAttach.getClass(), relEntCollection1RelEntToAttach.getRelEntPK());
                attachedRelEntCollection1.add(relEntCollection1RelEntToAttach);
            }
            entidades.setRelEntCollection1(attachedRelEntCollection1);
            em.persist(entidades);
            if (idCatEnt != null) {
                idCatEnt.getEntidadesCollection().add(entidades);
                idCatEnt = em.merge(idCatEnt);
            }
            if (condPagam != null) {
                condPagam.getEntidadesCollection().add(entidades);
                condPagam = em.merge(condPagam);
            }
            if (idEntVendedor != null) {
                idEntVendedor.getEntidadesCollection().add(entidades);
                idEntVendedor = em.merge(idEntVendedor);
            }
            if (idModoExp != null) {
                idModoExp.getEntidadesCollection().add(entidades);
                idModoExp = em.merge(idModoExp);
            }
            if (tipoEnt != null) {
                tipoEnt.getEntidadesCollection().add(entidades);
                tipoEnt = em.merge(tipoEnt);
            }
            if (userAlteracao != null) {
                userAlteracao.getEntidadesCollection().add(entidades);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getEntidadesCollection().add(entidades);
                userCriacao = em.merge(userCriacao);
            }
            if (zona != null) {
                zona.getEntidadesCollection().add(entidades);
                zona = em.merge(zona);
            }
            for (Zonas zonasCollectionZonas : entidades.getZonasCollection()) {
                zonasCollectionZonas.getEntidadesCollection().add(entidades);
                zonasCollectionZonas = em.merge(zonasCollectionZonas);
            }
            for (ContasBanco contasBancoCollectionContasBanco : entidades.getContasBancoCollection()) {
                contasBancoCollectionContasBanco.getEntidadesCollection().add(entidades);
                contasBancoCollectionContasBanco = em.merge(contasBancoCollectionContasBanco);
            }
            for (ContasBanco contasBancoCollection1ContasBanco : entidades.getContasBancoCollection1()) {
                Entidades oldIdEntOfContasBancoCollection1ContasBanco = contasBancoCollection1ContasBanco.getIdEnt();
                contasBancoCollection1ContasBanco.setIdEnt(entidades);
                contasBancoCollection1ContasBanco = em.merge(contasBancoCollection1ContasBanco);
                if (oldIdEntOfContasBancoCollection1ContasBanco != null) {
                    oldIdEntOfContasBancoCollection1ContasBanco.getContasBancoCollection1().remove(contasBancoCollection1ContasBanco);
                    oldIdEntOfContasBancoCollection1ContasBanco = em.merge(oldIdEntOfContasBancoCollection1ContasBanco);
                }
            }
            for (MovStock movStockCollectionMovStock : entidades.getMovStockCollection()) {
                Entidades oldIdEntOfMovStockCollectionMovStock = movStockCollectionMovStock.getIdEnt();
                movStockCollectionMovStock.setIdEnt(entidades);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
                if (oldIdEntOfMovStockCollectionMovStock != null) {
                    oldIdEntOfMovStockCollectionMovStock.getMovStockCollection().remove(movStockCollectionMovStock);
                    oldIdEntOfMovStockCollectionMovStock = em.merge(oldIdEntOfMovStockCollectionMovStock);
                }
            }
            for (Fotos fotosCollectionFotos : entidades.getFotosCollection()) {
                Entidades oldEntidadesOfFotosCollectionFotos = fotosCollectionFotos.getEntidades();
                fotosCollectionFotos.setEntidades(entidades);
                fotosCollectionFotos = em.merge(fotosCollectionFotos);
                if (oldEntidadesOfFotosCollectionFotos != null) {
                    oldEntidadesOfFotosCollectionFotos.getFotosCollection().remove(fotosCollectionFotos);
                    oldEntidadesOfFotosCollectionFotos = em.merge(oldEntidadesOfFotosCollectionFotos);
                }
            }
            for (Entidades entidadesCollectionEntidades : entidades.getEntidadesCollection()) {
                Entidades oldIdEntVendedorOfEntidadesCollectionEntidades = entidadesCollectionEntidades.getIdEntVendedor();
                entidadesCollectionEntidades.setIdEntVendedor(entidades);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
                if (oldIdEntVendedorOfEntidadesCollectionEntidades != null) {
                    oldIdEntVendedorOfEntidadesCollectionEntidades.getEntidadesCollection().remove(entidadesCollectionEntidades);
                    oldIdEntVendedorOfEntidadesCollectionEntidades = em.merge(oldIdEntVendedorOfEntidadesCollectionEntidades);
                }
            }
            for (RelEntItens relEntItensCollectionRelEntItens : entidades.getRelEntItensCollection()) {
                Entidades oldEntidadesOfRelEntItensCollectionRelEntItens = relEntItensCollectionRelEntItens.getEntidades();
                relEntItensCollectionRelEntItens.setEntidades(entidades);
                relEntItensCollectionRelEntItens = em.merge(relEntItensCollectionRelEntItens);
                if (oldEntidadesOfRelEntItensCollectionRelEntItens != null) {
                    oldEntidadesOfRelEntItensCollectionRelEntItens.getRelEntItensCollection().remove(relEntItensCollectionRelEntItens);
                    oldEntidadesOfRelEntItensCollectionRelEntItens = em.merge(oldEntidadesOfRelEntItensCollectionRelEntItens);
                }
            }
            for (RelEnt relEntCollectionRelEnt : entidades.getRelEntCollection()) {
                Entidades oldEntidadesOfRelEntCollectionRelEnt = relEntCollectionRelEnt.getEntidades();
                relEntCollectionRelEnt.setEntidades(entidades);
                relEntCollectionRelEnt = em.merge(relEntCollectionRelEnt);
                if (oldEntidadesOfRelEntCollectionRelEnt != null) {
                    oldEntidadesOfRelEntCollectionRelEnt.getRelEntCollection().remove(relEntCollectionRelEnt);
                    oldEntidadesOfRelEntCollectionRelEnt = em.merge(oldEntidadesOfRelEntCollectionRelEnt);
                }
            }
            for (RelEnt relEntCollection1RelEnt : entidades.getRelEntCollection1()) {
                Entidades oldEntidades1OfRelEntCollection1RelEnt = relEntCollection1RelEnt.getEntidades1();
                relEntCollection1RelEnt.setEntidades1(entidades);
                relEntCollection1RelEnt = em.merge(relEntCollection1RelEnt);
                if (oldEntidades1OfRelEntCollection1RelEnt != null) {
                    oldEntidades1OfRelEntCollection1RelEnt.getRelEntCollection1().remove(relEntCollection1RelEnt);
                    oldEntidades1OfRelEntCollection1RelEnt = em.merge(oldEntidades1OfRelEntCollection1RelEnt);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntidades(entidades.getIdEnt()) != null) {
                throw new PreexistingEntityException("Entidades " + entidades + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entidades entidades) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidades persistentEntidades = em.find(Entidades.class, entidades.getIdEnt());
            CatEnt idCatEntOld = persistentEntidades.getIdCatEnt();
            CatEnt idCatEntNew = entidades.getIdCatEnt();
            CondPagam condPagamOld = persistentEntidades.getCondPagam();
            CondPagam condPagamNew = entidades.getCondPagam();
            Entidades idEntVendedorOld = persistentEntidades.getIdEntVendedor();
            Entidades idEntVendedorNew = entidades.getIdEntVendedor();
            ModosExp idModoExpOld = persistentEntidades.getIdModoExp();
            ModosExp idModoExpNew = entidades.getIdModoExp();
            TiposEnt tipoEntOld = persistentEntidades.getTipoEnt();
            TiposEnt tipoEntNew = entidades.getTipoEnt();
            Utilizadores userAlteracaoOld = persistentEntidades.getUserAlteracao();
            Utilizadores userAlteracaoNew = entidades.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentEntidades.getUserCriacao();
            Utilizadores userCriacaoNew = entidades.getUserCriacao();
            Zonas zonaOld = persistentEntidades.getZona();
            Zonas zonaNew = entidades.getZona();
            Collection<Zonas> zonasCollectionOld = persistentEntidades.getZonasCollection();
            Collection<Zonas> zonasCollectionNew = entidades.getZonasCollection();
            Collection<ContasBanco> contasBancoCollectionOld = persistentEntidades.getContasBancoCollection();
            Collection<ContasBanco> contasBancoCollectionNew = entidades.getContasBancoCollection();
            Collection<ContasBanco> contasBancoCollection1Old = persistentEntidades.getContasBancoCollection1();
            Collection<ContasBanco> contasBancoCollection1New = entidades.getContasBancoCollection1();
            Collection<MovStock> movStockCollectionOld = persistentEntidades.getMovStockCollection();
            Collection<MovStock> movStockCollectionNew = entidades.getMovStockCollection();
            Collection<Fotos> fotosCollectionOld = persistentEntidades.getFotosCollection();
            Collection<Fotos> fotosCollectionNew = entidades.getFotosCollection();
            Collection<Entidades> entidadesCollectionOld = persistentEntidades.getEntidadesCollection();
            Collection<Entidades> entidadesCollectionNew = entidades.getEntidadesCollection();
            Collection<RelEntItens> relEntItensCollectionOld = persistentEntidades.getRelEntItensCollection();
            Collection<RelEntItens> relEntItensCollectionNew = entidades.getRelEntItensCollection();
            Collection<RelEnt> relEntCollectionOld = persistentEntidades.getRelEntCollection();
            Collection<RelEnt> relEntCollectionNew = entidades.getRelEntCollection();
            Collection<RelEnt> relEntCollection1Old = persistentEntidades.getRelEntCollection1();
            Collection<RelEnt> relEntCollection1New = entidades.getRelEntCollection1();
            List<String> illegalOrphanMessages = null;
            for (Fotos fotosCollectionOldFotos : fotosCollectionOld) {
                if (!fotosCollectionNew.contains(fotosCollectionOldFotos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fotos " + fotosCollectionOldFotos + " since its entidades field is not nullable.");
                }
            }
            for (RelEntItens relEntItensCollectionOldRelEntItens : relEntItensCollectionOld) {
                if (!relEntItensCollectionNew.contains(relEntItensCollectionOldRelEntItens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelEntItens " + relEntItensCollectionOldRelEntItens + " since its entidades field is not nullable.");
                }
            }
            for (RelEnt relEntCollectionOldRelEnt : relEntCollectionOld) {
                if (!relEntCollectionNew.contains(relEntCollectionOldRelEnt)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelEnt " + relEntCollectionOldRelEnt + " since its entidades field is not nullable.");
                }
            }
            for (RelEnt relEntCollection1OldRelEnt : relEntCollection1Old) {
                if (!relEntCollection1New.contains(relEntCollection1OldRelEnt)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelEnt " + relEntCollection1OldRelEnt + " since its entidades1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCatEntNew != null) {
                idCatEntNew = em.getReference(idCatEntNew.getClass(), idCatEntNew.getIdCatEnt());
                entidades.setIdCatEnt(idCatEntNew);
            }
            if (condPagamNew != null) {
                condPagamNew = em.getReference(condPagamNew.getClass(), condPagamNew.getCondPagam());
                entidades.setCondPagam(condPagamNew);
            }
            if (idEntVendedorNew != null) {
                idEntVendedorNew = em.getReference(idEntVendedorNew.getClass(), idEntVendedorNew.getIdEnt());
                entidades.setIdEntVendedor(idEntVendedorNew);
            }
            if (idModoExpNew != null) {
                idModoExpNew = em.getReference(idModoExpNew.getClass(), idModoExpNew.getIdModoExp());
                entidades.setIdModoExp(idModoExpNew);
            }
            if (tipoEntNew != null) {
                tipoEntNew = em.getReference(tipoEntNew.getClass(), tipoEntNew.getTipoEnt());
                entidades.setTipoEnt(tipoEntNew);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                entidades.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                entidades.setUserCriacao(userCriacaoNew);
            }
            if (zonaNew != null) {
                zonaNew = em.getReference(zonaNew.getClass(), zonaNew.getZona());
                entidades.setZona(zonaNew);
            }
            Collection<Zonas> attachedZonasCollectionNew = new ArrayList<Zonas>();
            for (Zonas zonasCollectionNewZonasToAttach : zonasCollectionNew) {
                zonasCollectionNewZonasToAttach = em.getReference(zonasCollectionNewZonasToAttach.getClass(), zonasCollectionNewZonasToAttach.getZona());
                attachedZonasCollectionNew.add(zonasCollectionNewZonasToAttach);
            }
            zonasCollectionNew = attachedZonasCollectionNew;
            entidades.setZonasCollection(zonasCollectionNew);
            Collection<ContasBanco> attachedContasBancoCollectionNew = new ArrayList<ContasBanco>();
            for (ContasBanco contasBancoCollectionNewContasBancoToAttach : contasBancoCollectionNew) {
                contasBancoCollectionNewContasBancoToAttach = em.getReference(contasBancoCollectionNewContasBancoToAttach.getClass(), contasBancoCollectionNewContasBancoToAttach.getIdConta());
                attachedContasBancoCollectionNew.add(contasBancoCollectionNewContasBancoToAttach);
            }
            contasBancoCollectionNew = attachedContasBancoCollectionNew;
            entidades.setContasBancoCollection(contasBancoCollectionNew);
            Collection<ContasBanco> attachedContasBancoCollection1New = new ArrayList<ContasBanco>();
            for (ContasBanco contasBancoCollection1NewContasBancoToAttach : contasBancoCollection1New) {
                contasBancoCollection1NewContasBancoToAttach = em.getReference(contasBancoCollection1NewContasBancoToAttach.getClass(), contasBancoCollection1NewContasBancoToAttach.getIdConta());
                attachedContasBancoCollection1New.add(contasBancoCollection1NewContasBancoToAttach);
            }
            contasBancoCollection1New = attachedContasBancoCollection1New;
            entidades.setContasBancoCollection1(contasBancoCollection1New);
            Collection<MovStock> attachedMovStockCollectionNew = new ArrayList<MovStock>();
            for (MovStock movStockCollectionNewMovStockToAttach : movStockCollectionNew) {
                movStockCollectionNewMovStockToAttach = em.getReference(movStockCollectionNewMovStockToAttach.getClass(), movStockCollectionNewMovStockToAttach.getIdMovStock());
                attachedMovStockCollectionNew.add(movStockCollectionNewMovStockToAttach);
            }
            movStockCollectionNew = attachedMovStockCollectionNew;
            entidades.setMovStockCollection(movStockCollectionNew);
            Collection<Fotos> attachedFotosCollectionNew = new ArrayList<Fotos>();
            for (Fotos fotosCollectionNewFotosToAttach : fotosCollectionNew) {
                fotosCollectionNewFotosToAttach = em.getReference(fotosCollectionNewFotosToAttach.getClass(), fotosCollectionNewFotosToAttach.getFotosPK());
                attachedFotosCollectionNew.add(fotosCollectionNewFotosToAttach);
            }
            fotosCollectionNew = attachedFotosCollectionNew;
            entidades.setFotosCollection(fotosCollectionNew);
            Collection<Entidades> attachedEntidadesCollectionNew = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionNewEntidadesToAttach : entidadesCollectionNew) {
                entidadesCollectionNewEntidadesToAttach = em.getReference(entidadesCollectionNewEntidadesToAttach.getClass(), entidadesCollectionNewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollectionNew.add(entidadesCollectionNewEntidadesToAttach);
            }
            entidadesCollectionNew = attachedEntidadesCollectionNew;
            entidades.setEntidadesCollection(entidadesCollectionNew);
            Collection<RelEntItens> attachedRelEntItensCollectionNew = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollectionNewRelEntItensToAttach : relEntItensCollectionNew) {
                relEntItensCollectionNewRelEntItensToAttach = em.getReference(relEntItensCollectionNewRelEntItensToAttach.getClass(), relEntItensCollectionNewRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollectionNew.add(relEntItensCollectionNewRelEntItensToAttach);
            }
            relEntItensCollectionNew = attachedRelEntItensCollectionNew;
            entidades.setRelEntItensCollection(relEntItensCollectionNew);
            Collection<RelEnt> attachedRelEntCollectionNew = new ArrayList<RelEnt>();
            for (RelEnt relEntCollectionNewRelEntToAttach : relEntCollectionNew) {
                relEntCollectionNewRelEntToAttach = em.getReference(relEntCollectionNewRelEntToAttach.getClass(), relEntCollectionNewRelEntToAttach.getRelEntPK());
                attachedRelEntCollectionNew.add(relEntCollectionNewRelEntToAttach);
            }
            relEntCollectionNew = attachedRelEntCollectionNew;
            entidades.setRelEntCollection(relEntCollectionNew);
            Collection<RelEnt> attachedRelEntCollection1New = new ArrayList<RelEnt>();
            for (RelEnt relEntCollection1NewRelEntToAttach : relEntCollection1New) {
                relEntCollection1NewRelEntToAttach = em.getReference(relEntCollection1NewRelEntToAttach.getClass(), relEntCollection1NewRelEntToAttach.getRelEntPK());
                attachedRelEntCollection1New.add(relEntCollection1NewRelEntToAttach);
            }
            relEntCollection1New = attachedRelEntCollection1New;
            entidades.setRelEntCollection1(relEntCollection1New);
            entidades = em.merge(entidades);
            if (idCatEntOld != null && !idCatEntOld.equals(idCatEntNew)) {
                idCatEntOld.getEntidadesCollection().remove(entidades);
                idCatEntOld = em.merge(idCatEntOld);
            }
            if (idCatEntNew != null && !idCatEntNew.equals(idCatEntOld)) {
                idCatEntNew.getEntidadesCollection().add(entidades);
                idCatEntNew = em.merge(idCatEntNew);
            }
            if (condPagamOld != null && !condPagamOld.equals(condPagamNew)) {
                condPagamOld.getEntidadesCollection().remove(entidades);
                condPagamOld = em.merge(condPagamOld);
            }
            if (condPagamNew != null && !condPagamNew.equals(condPagamOld)) {
                condPagamNew.getEntidadesCollection().add(entidades);
                condPagamNew = em.merge(condPagamNew);
            }
            if (idEntVendedorOld != null && !idEntVendedorOld.equals(idEntVendedorNew)) {
                idEntVendedorOld.getEntidadesCollection().remove(entidades);
                idEntVendedorOld = em.merge(idEntVendedorOld);
            }
            if (idEntVendedorNew != null && !idEntVendedorNew.equals(idEntVendedorOld)) {
                idEntVendedorNew.getEntidadesCollection().add(entidades);
                idEntVendedorNew = em.merge(idEntVendedorNew);
            }
            if (idModoExpOld != null && !idModoExpOld.equals(idModoExpNew)) {
                idModoExpOld.getEntidadesCollection().remove(entidades);
                idModoExpOld = em.merge(idModoExpOld);
            }
            if (idModoExpNew != null && !idModoExpNew.equals(idModoExpOld)) {
                idModoExpNew.getEntidadesCollection().add(entidades);
                idModoExpNew = em.merge(idModoExpNew);
            }
            if (tipoEntOld != null && !tipoEntOld.equals(tipoEntNew)) {
                tipoEntOld.getEntidadesCollection().remove(entidades);
                tipoEntOld = em.merge(tipoEntOld);
            }
            if (tipoEntNew != null && !tipoEntNew.equals(tipoEntOld)) {
                tipoEntNew.getEntidadesCollection().add(entidades);
                tipoEntNew = em.merge(tipoEntNew);
            }
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getEntidadesCollection().remove(entidades);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getEntidadesCollection().add(entidades);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getEntidadesCollection().remove(entidades);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getEntidadesCollection().add(entidades);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            if (zonaOld != null && !zonaOld.equals(zonaNew)) {
                zonaOld.getEntidadesCollection().remove(entidades);
                zonaOld = em.merge(zonaOld);
            }
            if (zonaNew != null && !zonaNew.equals(zonaOld)) {
                zonaNew.getEntidadesCollection().add(entidades);
                zonaNew = em.merge(zonaNew);
            }
            for (Zonas zonasCollectionOldZonas : zonasCollectionOld) {
                if (!zonasCollectionNew.contains(zonasCollectionOldZonas)) {
                    zonasCollectionOldZonas.getEntidadesCollection().remove(entidades);
                    zonasCollectionOldZonas = em.merge(zonasCollectionOldZonas);
                }
            }
            for (Zonas zonasCollectionNewZonas : zonasCollectionNew) {
                if (!zonasCollectionOld.contains(zonasCollectionNewZonas)) {
                    zonasCollectionNewZonas.getEntidadesCollection().add(entidades);
                    zonasCollectionNewZonas = em.merge(zonasCollectionNewZonas);
                }
            }
            for (ContasBanco contasBancoCollectionOldContasBanco : contasBancoCollectionOld) {
                if (!contasBancoCollectionNew.contains(contasBancoCollectionOldContasBanco)) {
                    contasBancoCollectionOldContasBanco.getEntidadesCollection().remove(entidades);
                    contasBancoCollectionOldContasBanco = em.merge(contasBancoCollectionOldContasBanco);
                }
            }
            for (ContasBanco contasBancoCollectionNewContasBanco : contasBancoCollectionNew) {
                if (!contasBancoCollectionOld.contains(contasBancoCollectionNewContasBanco)) {
                    contasBancoCollectionNewContasBanco.getEntidadesCollection().add(entidades);
                    contasBancoCollectionNewContasBanco = em.merge(contasBancoCollectionNewContasBanco);
                }
            }
            for (ContasBanco contasBancoCollection1OldContasBanco : contasBancoCollection1Old) {
                if (!contasBancoCollection1New.contains(contasBancoCollection1OldContasBanco)) {
                    contasBancoCollection1OldContasBanco.setIdEnt(null);
                    contasBancoCollection1OldContasBanco = em.merge(contasBancoCollection1OldContasBanco);
                }
            }
            for (ContasBanco contasBancoCollection1NewContasBanco : contasBancoCollection1New) {
                if (!contasBancoCollection1Old.contains(contasBancoCollection1NewContasBanco)) {
                    Entidades oldIdEntOfContasBancoCollection1NewContasBanco = contasBancoCollection1NewContasBanco.getIdEnt();
                    contasBancoCollection1NewContasBanco.setIdEnt(entidades);
                    contasBancoCollection1NewContasBanco = em.merge(contasBancoCollection1NewContasBanco);
                    if (oldIdEntOfContasBancoCollection1NewContasBanco != null && !oldIdEntOfContasBancoCollection1NewContasBanco.equals(entidades)) {
                        oldIdEntOfContasBancoCollection1NewContasBanco.getContasBancoCollection1().remove(contasBancoCollection1NewContasBanco);
                        oldIdEntOfContasBancoCollection1NewContasBanco = em.merge(oldIdEntOfContasBancoCollection1NewContasBanco);
                    }
                }
            }
            for (MovStock movStockCollectionOldMovStock : movStockCollectionOld) {
                if (!movStockCollectionNew.contains(movStockCollectionOldMovStock)) {
                    movStockCollectionOldMovStock.setIdEnt(null);
                    movStockCollectionOldMovStock = em.merge(movStockCollectionOldMovStock);
                }
            }
            for (MovStock movStockCollectionNewMovStock : movStockCollectionNew) {
                if (!movStockCollectionOld.contains(movStockCollectionNewMovStock)) {
                    Entidades oldIdEntOfMovStockCollectionNewMovStock = movStockCollectionNewMovStock.getIdEnt();
                    movStockCollectionNewMovStock.setIdEnt(entidades);
                    movStockCollectionNewMovStock = em.merge(movStockCollectionNewMovStock);
                    if (oldIdEntOfMovStockCollectionNewMovStock != null && !oldIdEntOfMovStockCollectionNewMovStock.equals(entidades)) {
                        oldIdEntOfMovStockCollectionNewMovStock.getMovStockCollection().remove(movStockCollectionNewMovStock);
                        oldIdEntOfMovStockCollectionNewMovStock = em.merge(oldIdEntOfMovStockCollectionNewMovStock);
                    }
                }
            }
            for (Fotos fotosCollectionNewFotos : fotosCollectionNew) {
                if (!fotosCollectionOld.contains(fotosCollectionNewFotos)) {
                    Entidades oldEntidadesOfFotosCollectionNewFotos = fotosCollectionNewFotos.getEntidades();
                    fotosCollectionNewFotos.setEntidades(entidades);
                    fotosCollectionNewFotos = em.merge(fotosCollectionNewFotos);
                    if (oldEntidadesOfFotosCollectionNewFotos != null && !oldEntidadesOfFotosCollectionNewFotos.equals(entidades)) {
                        oldEntidadesOfFotosCollectionNewFotos.getFotosCollection().remove(fotosCollectionNewFotos);
                        oldEntidadesOfFotosCollectionNewFotos = em.merge(oldEntidadesOfFotosCollectionNewFotos);
                    }
                }
            }
            for (Entidades entidadesCollectionOldEntidades : entidadesCollectionOld) {
                if (!entidadesCollectionNew.contains(entidadesCollectionOldEntidades)) {
                    entidadesCollectionOldEntidades.setIdEntVendedor(null);
                    entidadesCollectionOldEntidades = em.merge(entidadesCollectionOldEntidades);
                }
            }
            for (Entidades entidadesCollectionNewEntidades : entidadesCollectionNew) {
                if (!entidadesCollectionOld.contains(entidadesCollectionNewEntidades)) {
                    Entidades oldIdEntVendedorOfEntidadesCollectionNewEntidades = entidadesCollectionNewEntidades.getIdEntVendedor();
                    entidadesCollectionNewEntidades.setIdEntVendedor(entidades);
                    entidadesCollectionNewEntidades = em.merge(entidadesCollectionNewEntidades);
                    if (oldIdEntVendedorOfEntidadesCollectionNewEntidades != null && !oldIdEntVendedorOfEntidadesCollectionNewEntidades.equals(entidades)) {
                        oldIdEntVendedorOfEntidadesCollectionNewEntidades.getEntidadesCollection().remove(entidadesCollectionNewEntidades);
                        oldIdEntVendedorOfEntidadesCollectionNewEntidades = em.merge(oldIdEntVendedorOfEntidadesCollectionNewEntidades);
                    }
                }
            }
            for (RelEntItens relEntItensCollectionNewRelEntItens : relEntItensCollectionNew) {
                if (!relEntItensCollectionOld.contains(relEntItensCollectionNewRelEntItens)) {
                    Entidades oldEntidadesOfRelEntItensCollectionNewRelEntItens = relEntItensCollectionNewRelEntItens.getEntidades();
                    relEntItensCollectionNewRelEntItens.setEntidades(entidades);
                    relEntItensCollectionNewRelEntItens = em.merge(relEntItensCollectionNewRelEntItens);
                    if (oldEntidadesOfRelEntItensCollectionNewRelEntItens != null && !oldEntidadesOfRelEntItensCollectionNewRelEntItens.equals(entidades)) {
                        oldEntidadesOfRelEntItensCollectionNewRelEntItens.getRelEntItensCollection().remove(relEntItensCollectionNewRelEntItens);
                        oldEntidadesOfRelEntItensCollectionNewRelEntItens = em.merge(oldEntidadesOfRelEntItensCollectionNewRelEntItens);
                    }
                }
            }
            for (RelEnt relEntCollectionNewRelEnt : relEntCollectionNew) {
                if (!relEntCollectionOld.contains(relEntCollectionNewRelEnt)) {
                    Entidades oldEntidadesOfRelEntCollectionNewRelEnt = relEntCollectionNewRelEnt.getEntidades();
                    relEntCollectionNewRelEnt.setEntidades(entidades);
                    relEntCollectionNewRelEnt = em.merge(relEntCollectionNewRelEnt);
                    if (oldEntidadesOfRelEntCollectionNewRelEnt != null && !oldEntidadesOfRelEntCollectionNewRelEnt.equals(entidades)) {
                        oldEntidadesOfRelEntCollectionNewRelEnt.getRelEntCollection().remove(relEntCollectionNewRelEnt);
                        oldEntidadesOfRelEntCollectionNewRelEnt = em.merge(oldEntidadesOfRelEntCollectionNewRelEnt);
                    }
                }
            }
            for (RelEnt relEntCollection1NewRelEnt : relEntCollection1New) {
                if (!relEntCollection1Old.contains(relEntCollection1NewRelEnt)) {
                    Entidades oldEntidades1OfRelEntCollection1NewRelEnt = relEntCollection1NewRelEnt.getEntidades1();
                    relEntCollection1NewRelEnt.setEntidades1(entidades);
                    relEntCollection1NewRelEnt = em.merge(relEntCollection1NewRelEnt);
                    if (oldEntidades1OfRelEntCollection1NewRelEnt != null && !oldEntidades1OfRelEntCollection1NewRelEnt.equals(entidades)) {
                        oldEntidades1OfRelEntCollection1NewRelEnt.getRelEntCollection1().remove(relEntCollection1NewRelEnt);
                        oldEntidades1OfRelEntCollection1NewRelEnt = em.merge(oldEntidades1OfRelEntCollection1NewRelEnt);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entidades.getIdEnt();
                if (findEntidades(id) == null) {
                    throw new NonexistentEntityException("The entidades with id " + id + " no longer exists.");
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
            Entidades entidades;
            try {
                entidades = em.getReference(Entidades.class, id);
                entidades.getIdEnt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidades with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Fotos> fotosCollectionOrphanCheck = entidades.getFotosCollection();
            for (Fotos fotosCollectionOrphanCheckFotos : fotosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entidades (" + entidades + ") cannot be destroyed since the Fotos " + fotosCollectionOrphanCheckFotos + " in its fotosCollection field has a non-nullable entidades field.");
            }
            Collection<RelEntItens> relEntItensCollectionOrphanCheck = entidades.getRelEntItensCollection();
            for (RelEntItens relEntItensCollectionOrphanCheckRelEntItens : relEntItensCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entidades (" + entidades + ") cannot be destroyed since the RelEntItens " + relEntItensCollectionOrphanCheckRelEntItens + " in its relEntItensCollection field has a non-nullable entidades field.");
            }
            Collection<RelEnt> relEntCollectionOrphanCheck = entidades.getRelEntCollection();
            for (RelEnt relEntCollectionOrphanCheckRelEnt : relEntCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entidades (" + entidades + ") cannot be destroyed since the RelEnt " + relEntCollectionOrphanCheckRelEnt + " in its relEntCollection field has a non-nullable entidades field.");
            }
            Collection<RelEnt> relEntCollection1OrphanCheck = entidades.getRelEntCollection1();
            for (RelEnt relEntCollection1OrphanCheckRelEnt : relEntCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entidades (" + entidades + ") cannot be destroyed since the RelEnt " + relEntCollection1OrphanCheckRelEnt + " in its relEntCollection1 field has a non-nullable entidades1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CatEnt idCatEnt = entidades.getIdCatEnt();
            if (idCatEnt != null) {
                idCatEnt.getEntidadesCollection().remove(entidades);
                idCatEnt = em.merge(idCatEnt);
            }
            CondPagam condPagam = entidades.getCondPagam();
            if (condPagam != null) {
                condPagam.getEntidadesCollection().remove(entidades);
                condPagam = em.merge(condPagam);
            }
            Entidades idEntVendedor = entidades.getIdEntVendedor();
            if (idEntVendedor != null) {
                idEntVendedor.getEntidadesCollection().remove(entidades);
                idEntVendedor = em.merge(idEntVendedor);
            }
            ModosExp idModoExp = entidades.getIdModoExp();
            if (idModoExp != null) {
                idModoExp.getEntidadesCollection().remove(entidades);
                idModoExp = em.merge(idModoExp);
            }
            TiposEnt tipoEnt = entidades.getTipoEnt();
            if (tipoEnt != null) {
                tipoEnt.getEntidadesCollection().remove(entidades);
                tipoEnt = em.merge(tipoEnt);
            }
            Utilizadores userAlteracao = entidades.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getEntidadesCollection().remove(entidades);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = entidades.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getEntidadesCollection().remove(entidades);
                userCriacao = em.merge(userCriacao);
            }
            Zonas zona = entidades.getZona();
            if (zona != null) {
                zona.getEntidadesCollection().remove(entidades);
                zona = em.merge(zona);
            }
            Collection<Zonas> zonasCollection = entidades.getZonasCollection();
            for (Zonas zonasCollectionZonas : zonasCollection) {
                zonasCollectionZonas.getEntidadesCollection().remove(entidades);
                zonasCollectionZonas = em.merge(zonasCollectionZonas);
            }
            Collection<ContasBanco> contasBancoCollection = entidades.getContasBancoCollection();
            for (ContasBanco contasBancoCollectionContasBanco : contasBancoCollection) {
                contasBancoCollectionContasBanco.getEntidadesCollection().remove(entidades);
                contasBancoCollectionContasBanco = em.merge(contasBancoCollectionContasBanco);
            }
            Collection<ContasBanco> contasBancoCollection1 = entidades.getContasBancoCollection1();
            for (ContasBanco contasBancoCollection1ContasBanco : contasBancoCollection1) {
                contasBancoCollection1ContasBanco.setIdEnt(null);
                contasBancoCollection1ContasBanco = em.merge(contasBancoCollection1ContasBanco);
            }
            Collection<MovStock> movStockCollection = entidades.getMovStockCollection();
            for (MovStock movStockCollectionMovStock : movStockCollection) {
                movStockCollectionMovStock.setIdEnt(null);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
            }
            Collection<Entidades> entidadesCollection = entidades.getEntidadesCollection();
            for (Entidades entidadesCollectionEntidades : entidadesCollection) {
                entidadesCollectionEntidades.setIdEntVendedor(null);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            em.remove(entidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entidades> findEntidadesEntities() {
        return findEntidadesEntities(true, -1, -1);
    }

    public List<Entidades> findEntidadesEntities(int maxResults, int firstResult) {
        return findEntidadesEntities(false, maxResults, firstResult);
    }

    private List<Entidades> findEntidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Entidades as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Entidades findEntidades(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadesCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Entidades as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
