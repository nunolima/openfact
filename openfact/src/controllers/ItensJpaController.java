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
import entidades.CatItens;
import entidades.TiposItens;
import entidades.UnidMedida;
import entidades.Utilizadores;
import entidades.TaxasAdic;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Itens;
import entidades.MovStock;
import entidades.DocLinhas;
import entidades.RelEntItens;
import entidades.ItensAnexos;

/**
 *
 * @author User
 */
public class ItensJpaController {

    public ItensJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itens itens) throws PreexistingEntityException, Exception {
        if (itens.getTaxasAdicCollection() == null) {
            itens.setTaxasAdicCollection(new ArrayList<TaxasAdic>());
        }
        if (itens.getItensCollection() == null) {
            itens.setItensCollection(new ArrayList<Itens>());
        }
        if (itens.getItensCollection1() == null) {
            itens.setItensCollection1(new ArrayList<Itens>());
        }
        if (itens.getMovStockCollection() == null) {
            itens.setMovStockCollection(new ArrayList<MovStock>());
        }
        if (itens.getDocLinhasCollection() == null) {
            itens.setDocLinhasCollection(new ArrayList<DocLinhas>());
        }
        if (itens.getRelEntItensCollection() == null) {
            itens.setRelEntItensCollection(new ArrayList<RelEntItens>());
        }
        if (itens.getItensAnexosCollection() == null) {
            itens.setItensAnexosCollection(new ArrayList<ItensAnexos>());
        }
        if (itens.getItensAnexosCollection1() == null) {
            itens.setItensAnexosCollection1(new ArrayList<ItensAnexos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatItens idCatItem = itens.getIdCatItem();
            if (idCatItem != null) {
                idCatItem = em.getReference(idCatItem.getClass(), idCatItem.getIdCatItem());
                itens.setIdCatItem(idCatItem);
            }
            TiposItens tipoItem = itens.getTipoItem();
            if (tipoItem != null) {
                tipoItem = em.getReference(tipoItem.getClass(), tipoItem.getTipoItem());
                itens.setTipoItem(tipoItem);
            }
            UnidMedida unidMedida = itens.getUnidMedida();
            if (unidMedida != null) {
                unidMedida = em.getReference(unidMedida.getClass(), unidMedida.getUnidMedida());
                itens.setUnidMedida(unidMedida);
            }
            Utilizadores userAlteracao = itens.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao = em.getReference(userAlteracao.getClass(), userAlteracao.getIdUtilizador());
                itens.setUserAlteracao(userAlteracao);
            }
            Utilizadores userCriacao = itens.getUserCriacao();
            if (userCriacao != null) {
                userCriacao = em.getReference(userCriacao.getClass(), userCriacao.getIdUtilizador());
                itens.setUserCriacao(userCriacao);
            }
            Collection<TaxasAdic> attachedTaxasAdicCollection = new ArrayList<TaxasAdic>();
            for (TaxasAdic taxasAdicCollectionTaxasAdicToAttach : itens.getTaxasAdicCollection()) {
                taxasAdicCollectionTaxasAdicToAttach = em.getReference(taxasAdicCollectionTaxasAdicToAttach.getClass(), taxasAdicCollectionTaxasAdicToAttach.getIdTaxaAdic());
                attachedTaxasAdicCollection.add(taxasAdicCollectionTaxasAdicToAttach);
            }
            itens.setTaxasAdicCollection(attachedTaxasAdicCollection);
            Collection<Itens> attachedItensCollection = new ArrayList<Itens>();
            for (Itens itensCollectionItensToAttach : itens.getItensCollection()) {
                itensCollectionItensToAttach = em.getReference(itensCollectionItensToAttach.getClass(), itensCollectionItensToAttach.getIdItem());
                attachedItensCollection.add(itensCollectionItensToAttach);
            }
            itens.setItensCollection(attachedItensCollection);
            Collection<Itens> attachedItensCollection1 = new ArrayList<Itens>();
            for (Itens itensCollection1ItensToAttach : itens.getItensCollection1()) {
                itensCollection1ItensToAttach = em.getReference(itensCollection1ItensToAttach.getClass(), itensCollection1ItensToAttach.getIdItem());
                attachedItensCollection1.add(itensCollection1ItensToAttach);
            }
            itens.setItensCollection1(attachedItensCollection1);
            Collection<MovStock> attachedMovStockCollection = new ArrayList<MovStock>();
            for (MovStock movStockCollectionMovStockToAttach : itens.getMovStockCollection()) {
                movStockCollectionMovStockToAttach = em.getReference(movStockCollectionMovStockToAttach.getClass(), movStockCollectionMovStockToAttach.getIdMovStock());
                attachedMovStockCollection.add(movStockCollectionMovStockToAttach);
            }
            itens.setMovStockCollection(attachedMovStockCollection);
            Collection<DocLinhas> attachedDocLinhasCollection = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionDocLinhasToAttach : itens.getDocLinhasCollection()) {
                docLinhasCollectionDocLinhasToAttach = em.getReference(docLinhasCollectionDocLinhasToAttach.getClass(), docLinhasCollectionDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollection.add(docLinhasCollectionDocLinhasToAttach);
            }
            itens.setDocLinhasCollection(attachedDocLinhasCollection);
            Collection<RelEntItens> attachedRelEntItensCollection = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollectionRelEntItensToAttach : itens.getRelEntItensCollection()) {
                relEntItensCollectionRelEntItensToAttach = em.getReference(relEntItensCollectionRelEntItensToAttach.getClass(), relEntItensCollectionRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollection.add(relEntItensCollectionRelEntItensToAttach);
            }
            itens.setRelEntItensCollection(attachedRelEntItensCollection);
            Collection<ItensAnexos> attachedItensAnexosCollection = new ArrayList<ItensAnexos>();
            for (ItensAnexos itensAnexosCollectionItensAnexosToAttach : itens.getItensAnexosCollection()) {
                itensAnexosCollectionItensAnexosToAttach = em.getReference(itensAnexosCollectionItensAnexosToAttach.getClass(), itensAnexosCollectionItensAnexosToAttach.getItensAnexosPK());
                attachedItensAnexosCollection.add(itensAnexosCollectionItensAnexosToAttach);
            }
            itens.setItensAnexosCollection(attachedItensAnexosCollection);
            Collection<ItensAnexos> attachedItensAnexosCollection1 = new ArrayList<ItensAnexos>();
            for (ItensAnexos itensAnexosCollection1ItensAnexosToAttach : itens.getItensAnexosCollection1()) {
                itensAnexosCollection1ItensAnexosToAttach = em.getReference(itensAnexosCollection1ItensAnexosToAttach.getClass(), itensAnexosCollection1ItensAnexosToAttach.getItensAnexosPK());
                attachedItensAnexosCollection1.add(itensAnexosCollection1ItensAnexosToAttach);
            }
            itens.setItensAnexosCollection1(attachedItensAnexosCollection1);
            em.persist(itens);
            if (idCatItem != null) {
                idCatItem.getItensCollection().add(itens);
                idCatItem = em.merge(idCatItem);
            }
            if (tipoItem != null) {
                tipoItem.getItensCollection().add(itens);
                tipoItem = em.merge(tipoItem);
            }
            if (unidMedida != null) {
                unidMedida.getItensCollection().add(itens);
                unidMedida = em.merge(unidMedida);
            }
            if (userAlteracao != null) {
                userAlteracao.getItensCollection().add(itens);
                userAlteracao = em.merge(userAlteracao);
            }
            if (userCriacao != null) {
                userCriacao.getItensCollection().add(itens);
                userCriacao = em.merge(userCriacao);
            }
            for (TaxasAdic taxasAdicCollectionTaxasAdic : itens.getTaxasAdicCollection()) {
                taxasAdicCollectionTaxasAdic.getItensCollection().add(itens);
                taxasAdicCollectionTaxasAdic = em.merge(taxasAdicCollectionTaxasAdic);
            }
            for (Itens itensCollectionItens : itens.getItensCollection()) {
                itensCollectionItens.getItensCollection().add(itens);
                itensCollectionItens = em.merge(itensCollectionItens);
            }
            for (Itens itensCollection1Itens : itens.getItensCollection1()) {
                itensCollection1Itens.getItensCollection().add(itens);
                itensCollection1Itens = em.merge(itensCollection1Itens);
            }
            for (MovStock movStockCollectionMovStock : itens.getMovStockCollection()) {
                Itens oldIdItemOfMovStockCollectionMovStock = movStockCollectionMovStock.getIdItem();
                movStockCollectionMovStock.setIdItem(itens);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
                if (oldIdItemOfMovStockCollectionMovStock != null) {
                    oldIdItemOfMovStockCollectionMovStock.getMovStockCollection().remove(movStockCollectionMovStock);
                    oldIdItemOfMovStockCollectionMovStock = em.merge(oldIdItemOfMovStockCollectionMovStock);
                }
            }
            for (DocLinhas docLinhasCollectionDocLinhas : itens.getDocLinhasCollection()) {
                Itens oldIdItemOfDocLinhasCollectionDocLinhas = docLinhasCollectionDocLinhas.getIdItem();
                docLinhasCollectionDocLinhas.setIdItem(itens);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
                if (oldIdItemOfDocLinhasCollectionDocLinhas != null) {
                    oldIdItemOfDocLinhasCollectionDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionDocLinhas);
                    oldIdItemOfDocLinhasCollectionDocLinhas = em.merge(oldIdItemOfDocLinhasCollectionDocLinhas);
                }
            }
            for (RelEntItens relEntItensCollectionRelEntItens : itens.getRelEntItensCollection()) {
                Itens oldItensOfRelEntItensCollectionRelEntItens = relEntItensCollectionRelEntItens.getItens();
                relEntItensCollectionRelEntItens.setItens(itens);
                relEntItensCollectionRelEntItens = em.merge(relEntItensCollectionRelEntItens);
                if (oldItensOfRelEntItensCollectionRelEntItens != null) {
                    oldItensOfRelEntItensCollectionRelEntItens.getRelEntItensCollection().remove(relEntItensCollectionRelEntItens);
                    oldItensOfRelEntItensCollectionRelEntItens = em.merge(oldItensOfRelEntItensCollectionRelEntItens);
                }
            }
            for (ItensAnexos itensAnexosCollectionItensAnexos : itens.getItensAnexosCollection()) {
                Itens oldItensOfItensAnexosCollectionItensAnexos = itensAnexosCollectionItensAnexos.getItens();
                itensAnexosCollectionItensAnexos.setItens(itens);
                itensAnexosCollectionItensAnexos = em.merge(itensAnexosCollectionItensAnexos);
                if (oldItensOfItensAnexosCollectionItensAnexos != null) {
                    oldItensOfItensAnexosCollectionItensAnexos.getItensAnexosCollection().remove(itensAnexosCollectionItensAnexos);
                    oldItensOfItensAnexosCollectionItensAnexos = em.merge(oldItensOfItensAnexosCollectionItensAnexos);
                }
            }
            for (ItensAnexos itensAnexosCollection1ItensAnexos : itens.getItensAnexosCollection1()) {
                Itens oldItens1OfItensAnexosCollection1ItensAnexos = itensAnexosCollection1ItensAnexos.getItens1();
                itensAnexosCollection1ItensAnexos.setItens1(itens);
                itensAnexosCollection1ItensAnexos = em.merge(itensAnexosCollection1ItensAnexos);
                if (oldItens1OfItensAnexosCollection1ItensAnexos != null) {
                    oldItens1OfItensAnexosCollection1ItensAnexos.getItensAnexosCollection1().remove(itensAnexosCollection1ItensAnexos);
                    oldItens1OfItensAnexosCollection1ItensAnexos = em.merge(oldItens1OfItensAnexosCollection1ItensAnexos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findItens(itens.getIdItem()) != null) {
                throw new PreexistingEntityException("Itens " + itens + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itens itens) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itens persistentItens = em.find(Itens.class, itens.getIdItem());
            CatItens idCatItemOld = persistentItens.getIdCatItem();
            CatItens idCatItemNew = itens.getIdCatItem();
            TiposItens tipoItemOld = persistentItens.getTipoItem();
            TiposItens tipoItemNew = itens.getTipoItem();
            UnidMedida unidMedidaOld = persistentItens.getUnidMedida();
            UnidMedida unidMedidaNew = itens.getUnidMedida();
            Utilizadores userAlteracaoOld = persistentItens.getUserAlteracao();
            Utilizadores userAlteracaoNew = itens.getUserAlteracao();
            Utilizadores userCriacaoOld = persistentItens.getUserCriacao();
            Utilizadores userCriacaoNew = itens.getUserCriacao();
            Collection<TaxasAdic> taxasAdicCollectionOld = persistentItens.getTaxasAdicCollection();
            Collection<TaxasAdic> taxasAdicCollectionNew = itens.getTaxasAdicCollection();
            Collection<Itens> itensCollectionOld = persistentItens.getItensCollection();
            Collection<Itens> itensCollectionNew = itens.getItensCollection();
            Collection<Itens> itensCollection1Old = persistentItens.getItensCollection1();
            Collection<Itens> itensCollection1New = itens.getItensCollection1();
            Collection<MovStock> movStockCollectionOld = persistentItens.getMovStockCollection();
            Collection<MovStock> movStockCollectionNew = itens.getMovStockCollection();
            Collection<DocLinhas> docLinhasCollectionOld = persistentItens.getDocLinhasCollection();
            Collection<DocLinhas> docLinhasCollectionNew = itens.getDocLinhasCollection();
            Collection<RelEntItens> relEntItensCollectionOld = persistentItens.getRelEntItensCollection();
            Collection<RelEntItens> relEntItensCollectionNew = itens.getRelEntItensCollection();
            Collection<ItensAnexos> itensAnexosCollectionOld = persistentItens.getItensAnexosCollection();
            Collection<ItensAnexos> itensAnexosCollectionNew = itens.getItensAnexosCollection();
            Collection<ItensAnexos> itensAnexosCollection1Old = persistentItens.getItensAnexosCollection1();
            Collection<ItensAnexos> itensAnexosCollection1New = itens.getItensAnexosCollection1();
            List<String> illegalOrphanMessages = null;
            for (RelEntItens relEntItensCollectionOldRelEntItens : relEntItensCollectionOld) {
                if (!relEntItensCollectionNew.contains(relEntItensCollectionOldRelEntItens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelEntItens " + relEntItensCollectionOldRelEntItens + " since its itens field is not nullable.");
                }
            }
            for (ItensAnexos itensAnexosCollectionOldItensAnexos : itensAnexosCollectionOld) {
                if (!itensAnexosCollectionNew.contains(itensAnexosCollectionOldItensAnexos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ItensAnexos " + itensAnexosCollectionOldItensAnexos + " since its itens field is not nullable.");
                }
            }
            for (ItensAnexos itensAnexosCollection1OldItensAnexos : itensAnexosCollection1Old) {
                if (!itensAnexosCollection1New.contains(itensAnexosCollection1OldItensAnexos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ItensAnexos " + itensAnexosCollection1OldItensAnexos + " since its itens1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCatItemNew != null) {
                idCatItemNew = em.getReference(idCatItemNew.getClass(), idCatItemNew.getIdCatItem());
                itens.setIdCatItem(idCatItemNew);
            }
            if (tipoItemNew != null) {
                tipoItemNew = em.getReference(tipoItemNew.getClass(), tipoItemNew.getTipoItem());
                itens.setTipoItem(tipoItemNew);
            }
            if (unidMedidaNew != null) {
                unidMedidaNew = em.getReference(unidMedidaNew.getClass(), unidMedidaNew.getUnidMedida());
                itens.setUnidMedida(unidMedidaNew);
            }
            if (userAlteracaoNew != null) {
                userAlteracaoNew = em.getReference(userAlteracaoNew.getClass(), userAlteracaoNew.getIdUtilizador());
                itens.setUserAlteracao(userAlteracaoNew);
            }
            if (userCriacaoNew != null) {
                userCriacaoNew = em.getReference(userCriacaoNew.getClass(), userCriacaoNew.getIdUtilizador());
                itens.setUserCriacao(userCriacaoNew);
            }
            Collection<TaxasAdic> attachedTaxasAdicCollectionNew = new ArrayList<TaxasAdic>();
            for (TaxasAdic taxasAdicCollectionNewTaxasAdicToAttach : taxasAdicCollectionNew) {
                taxasAdicCollectionNewTaxasAdicToAttach = em.getReference(taxasAdicCollectionNewTaxasAdicToAttach.getClass(), taxasAdicCollectionNewTaxasAdicToAttach.getIdTaxaAdic());
                attachedTaxasAdicCollectionNew.add(taxasAdicCollectionNewTaxasAdicToAttach);
            }
            taxasAdicCollectionNew = attachedTaxasAdicCollectionNew;
            itens.setTaxasAdicCollection(taxasAdicCollectionNew);
            Collection<Itens> attachedItensCollectionNew = new ArrayList<Itens>();
            for (Itens itensCollectionNewItensToAttach : itensCollectionNew) {
                itensCollectionNewItensToAttach = em.getReference(itensCollectionNewItensToAttach.getClass(), itensCollectionNewItensToAttach.getIdItem());
                attachedItensCollectionNew.add(itensCollectionNewItensToAttach);
            }
            itensCollectionNew = attachedItensCollectionNew;
            itens.setItensCollection(itensCollectionNew);
            Collection<Itens> attachedItensCollection1New = new ArrayList<Itens>();
            for (Itens itensCollection1NewItensToAttach : itensCollection1New) {
                itensCollection1NewItensToAttach = em.getReference(itensCollection1NewItensToAttach.getClass(), itensCollection1NewItensToAttach.getIdItem());
                attachedItensCollection1New.add(itensCollection1NewItensToAttach);
            }
            itensCollection1New = attachedItensCollection1New;
            itens.setItensCollection1(itensCollection1New);
            Collection<MovStock> attachedMovStockCollectionNew = new ArrayList<MovStock>();
            for (MovStock movStockCollectionNewMovStockToAttach : movStockCollectionNew) {
                movStockCollectionNewMovStockToAttach = em.getReference(movStockCollectionNewMovStockToAttach.getClass(), movStockCollectionNewMovStockToAttach.getIdMovStock());
                attachedMovStockCollectionNew.add(movStockCollectionNewMovStockToAttach);
            }
            movStockCollectionNew = attachedMovStockCollectionNew;
            itens.setMovStockCollection(movStockCollectionNew);
            Collection<DocLinhas> attachedDocLinhasCollectionNew = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionNewDocLinhasToAttach : docLinhasCollectionNew) {
                docLinhasCollectionNewDocLinhasToAttach = em.getReference(docLinhasCollectionNewDocLinhasToAttach.getClass(), docLinhasCollectionNewDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollectionNew.add(docLinhasCollectionNewDocLinhasToAttach);
            }
            docLinhasCollectionNew = attachedDocLinhasCollectionNew;
            itens.setDocLinhasCollection(docLinhasCollectionNew);
            Collection<RelEntItens> attachedRelEntItensCollectionNew = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollectionNewRelEntItensToAttach : relEntItensCollectionNew) {
                relEntItensCollectionNewRelEntItensToAttach = em.getReference(relEntItensCollectionNewRelEntItensToAttach.getClass(), relEntItensCollectionNewRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollectionNew.add(relEntItensCollectionNewRelEntItensToAttach);
            }
            relEntItensCollectionNew = attachedRelEntItensCollectionNew;
            itens.setRelEntItensCollection(relEntItensCollectionNew);
            Collection<ItensAnexos> attachedItensAnexosCollectionNew = new ArrayList<ItensAnexos>();
            for (ItensAnexos itensAnexosCollectionNewItensAnexosToAttach : itensAnexosCollectionNew) {
                itensAnexosCollectionNewItensAnexosToAttach = em.getReference(itensAnexosCollectionNewItensAnexosToAttach.getClass(), itensAnexosCollectionNewItensAnexosToAttach.getItensAnexosPK());
                attachedItensAnexosCollectionNew.add(itensAnexosCollectionNewItensAnexosToAttach);
            }
            itensAnexosCollectionNew = attachedItensAnexosCollectionNew;
            itens.setItensAnexosCollection(itensAnexosCollectionNew);
            Collection<ItensAnexos> attachedItensAnexosCollection1New = new ArrayList<ItensAnexos>();
            for (ItensAnexos itensAnexosCollection1NewItensAnexosToAttach : itensAnexosCollection1New) {
                itensAnexosCollection1NewItensAnexosToAttach = em.getReference(itensAnexosCollection1NewItensAnexosToAttach.getClass(), itensAnexosCollection1NewItensAnexosToAttach.getItensAnexosPK());
                attachedItensAnexosCollection1New.add(itensAnexosCollection1NewItensAnexosToAttach);
            }
            itensAnexosCollection1New = attachedItensAnexosCollection1New;
            itens.setItensAnexosCollection1(itensAnexosCollection1New);
            itens = em.merge(itens);
            if (idCatItemOld != null && !idCatItemOld.equals(idCatItemNew)) {
                idCatItemOld.getItensCollection().remove(itens);
                idCatItemOld = em.merge(idCatItemOld);
            }
            if (idCatItemNew != null && !idCatItemNew.equals(idCatItemOld)) {
                idCatItemNew.getItensCollection().add(itens);
                idCatItemNew = em.merge(idCatItemNew);
            }
            if (tipoItemOld != null && !tipoItemOld.equals(tipoItemNew)) {
                tipoItemOld.getItensCollection().remove(itens);
                tipoItemOld = em.merge(tipoItemOld);
            }
            if (tipoItemNew != null && !tipoItemNew.equals(tipoItemOld)) {
                tipoItemNew.getItensCollection().add(itens);
                tipoItemNew = em.merge(tipoItemNew);
            }
            if (unidMedidaOld != null && !unidMedidaOld.equals(unidMedidaNew)) {
                unidMedidaOld.getItensCollection().remove(itens);
                unidMedidaOld = em.merge(unidMedidaOld);
            }
            if (unidMedidaNew != null && !unidMedidaNew.equals(unidMedidaOld)) {
                unidMedidaNew.getItensCollection().add(itens);
                unidMedidaNew = em.merge(unidMedidaNew);
            }
            if (userAlteracaoOld != null && !userAlteracaoOld.equals(userAlteracaoNew)) {
                userAlteracaoOld.getItensCollection().remove(itens);
                userAlteracaoOld = em.merge(userAlteracaoOld);
            }
            if (userAlteracaoNew != null && !userAlteracaoNew.equals(userAlteracaoOld)) {
                userAlteracaoNew.getItensCollection().add(itens);
                userAlteracaoNew = em.merge(userAlteracaoNew);
            }
            if (userCriacaoOld != null && !userCriacaoOld.equals(userCriacaoNew)) {
                userCriacaoOld.getItensCollection().remove(itens);
                userCriacaoOld = em.merge(userCriacaoOld);
            }
            if (userCriacaoNew != null && !userCriacaoNew.equals(userCriacaoOld)) {
                userCriacaoNew.getItensCollection().add(itens);
                userCriacaoNew = em.merge(userCriacaoNew);
            }
            for (TaxasAdic taxasAdicCollectionOldTaxasAdic : taxasAdicCollectionOld) {
                if (!taxasAdicCollectionNew.contains(taxasAdicCollectionOldTaxasAdic)) {
                    taxasAdicCollectionOldTaxasAdic.getItensCollection().remove(itens);
                    taxasAdicCollectionOldTaxasAdic = em.merge(taxasAdicCollectionOldTaxasAdic);
                }
            }
            for (TaxasAdic taxasAdicCollectionNewTaxasAdic : taxasAdicCollectionNew) {
                if (!taxasAdicCollectionOld.contains(taxasAdicCollectionNewTaxasAdic)) {
                    taxasAdicCollectionNewTaxasAdic.getItensCollection().add(itens);
                    taxasAdicCollectionNewTaxasAdic = em.merge(taxasAdicCollectionNewTaxasAdic);
                }
            }
            for (Itens itensCollectionOldItens : itensCollectionOld) {
                if (!itensCollectionNew.contains(itensCollectionOldItens)) {
                    itensCollectionOldItens.getItensCollection().remove(itens);
                    itensCollectionOldItens = em.merge(itensCollectionOldItens);
                }
            }
            for (Itens itensCollectionNewItens : itensCollectionNew) {
                if (!itensCollectionOld.contains(itensCollectionNewItens)) {
                    itensCollectionNewItens.getItensCollection().add(itens);
                    itensCollectionNewItens = em.merge(itensCollectionNewItens);
                }
            }
            for (Itens itensCollection1OldItens : itensCollection1Old) {
                if (!itensCollection1New.contains(itensCollection1OldItens)) {
                    itensCollection1OldItens.getItensCollection().remove(itens);
                    itensCollection1OldItens = em.merge(itensCollection1OldItens);
                }
            }
            for (Itens itensCollection1NewItens : itensCollection1New) {
                if (!itensCollection1Old.contains(itensCollection1NewItens)) {
                    itensCollection1NewItens.getItensCollection().add(itens);
                    itensCollection1NewItens = em.merge(itensCollection1NewItens);
                }
            }
            for (MovStock movStockCollectionOldMovStock : movStockCollectionOld) {
                if (!movStockCollectionNew.contains(movStockCollectionOldMovStock)) {
                    movStockCollectionOldMovStock.setIdItem(null);
                    movStockCollectionOldMovStock = em.merge(movStockCollectionOldMovStock);
                }
            }
            for (MovStock movStockCollectionNewMovStock : movStockCollectionNew) {
                if (!movStockCollectionOld.contains(movStockCollectionNewMovStock)) {
                    Itens oldIdItemOfMovStockCollectionNewMovStock = movStockCollectionNewMovStock.getIdItem();
                    movStockCollectionNewMovStock.setIdItem(itens);
                    movStockCollectionNewMovStock = em.merge(movStockCollectionNewMovStock);
                    if (oldIdItemOfMovStockCollectionNewMovStock != null && !oldIdItemOfMovStockCollectionNewMovStock.equals(itens)) {
                        oldIdItemOfMovStockCollectionNewMovStock.getMovStockCollection().remove(movStockCollectionNewMovStock);
                        oldIdItemOfMovStockCollectionNewMovStock = em.merge(oldIdItemOfMovStockCollectionNewMovStock);
                    }
                }
            }
            for (DocLinhas docLinhasCollectionOldDocLinhas : docLinhasCollectionOld) {
                if (!docLinhasCollectionNew.contains(docLinhasCollectionOldDocLinhas)) {
                    docLinhasCollectionOldDocLinhas.setIdItem(null);
                    docLinhasCollectionOldDocLinhas = em.merge(docLinhasCollectionOldDocLinhas);
                }
            }
            for (DocLinhas docLinhasCollectionNewDocLinhas : docLinhasCollectionNew) {
                if (!docLinhasCollectionOld.contains(docLinhasCollectionNewDocLinhas)) {
                    Itens oldIdItemOfDocLinhasCollectionNewDocLinhas = docLinhasCollectionNewDocLinhas.getIdItem();
                    docLinhasCollectionNewDocLinhas.setIdItem(itens);
                    docLinhasCollectionNewDocLinhas = em.merge(docLinhasCollectionNewDocLinhas);
                    if (oldIdItemOfDocLinhasCollectionNewDocLinhas != null && !oldIdItemOfDocLinhasCollectionNewDocLinhas.equals(itens)) {
                        oldIdItemOfDocLinhasCollectionNewDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionNewDocLinhas);
                        oldIdItemOfDocLinhasCollectionNewDocLinhas = em.merge(oldIdItemOfDocLinhasCollectionNewDocLinhas);
                    }
                }
            }
            for (RelEntItens relEntItensCollectionNewRelEntItens : relEntItensCollectionNew) {
                if (!relEntItensCollectionOld.contains(relEntItensCollectionNewRelEntItens)) {
                    Itens oldItensOfRelEntItensCollectionNewRelEntItens = relEntItensCollectionNewRelEntItens.getItens();
                    relEntItensCollectionNewRelEntItens.setItens(itens);
                    relEntItensCollectionNewRelEntItens = em.merge(relEntItensCollectionNewRelEntItens);
                    if (oldItensOfRelEntItensCollectionNewRelEntItens != null && !oldItensOfRelEntItensCollectionNewRelEntItens.equals(itens)) {
                        oldItensOfRelEntItensCollectionNewRelEntItens.getRelEntItensCollection().remove(relEntItensCollectionNewRelEntItens);
                        oldItensOfRelEntItensCollectionNewRelEntItens = em.merge(oldItensOfRelEntItensCollectionNewRelEntItens);
                    }
                }
            }
            for (ItensAnexos itensAnexosCollectionNewItensAnexos : itensAnexosCollectionNew) {
                if (!itensAnexosCollectionOld.contains(itensAnexosCollectionNewItensAnexos)) {
                    Itens oldItensOfItensAnexosCollectionNewItensAnexos = itensAnexosCollectionNewItensAnexos.getItens();
                    itensAnexosCollectionNewItensAnexos.setItens(itens);
                    itensAnexosCollectionNewItensAnexos = em.merge(itensAnexosCollectionNewItensAnexos);
                    if (oldItensOfItensAnexosCollectionNewItensAnexos != null && !oldItensOfItensAnexosCollectionNewItensAnexos.equals(itens)) {
                        oldItensOfItensAnexosCollectionNewItensAnexos.getItensAnexosCollection().remove(itensAnexosCollectionNewItensAnexos);
                        oldItensOfItensAnexosCollectionNewItensAnexos = em.merge(oldItensOfItensAnexosCollectionNewItensAnexos);
                    }
                }
            }
            for (ItensAnexos itensAnexosCollection1NewItensAnexos : itensAnexosCollection1New) {
                if (!itensAnexosCollection1Old.contains(itensAnexosCollection1NewItensAnexos)) {
                    Itens oldItens1OfItensAnexosCollection1NewItensAnexos = itensAnexosCollection1NewItensAnexos.getItens1();
                    itensAnexosCollection1NewItensAnexos.setItens1(itens);
                    itensAnexosCollection1NewItensAnexos = em.merge(itensAnexosCollection1NewItensAnexos);
                    if (oldItens1OfItensAnexosCollection1NewItensAnexos != null && !oldItens1OfItensAnexosCollection1NewItensAnexos.equals(itens)) {
                        oldItens1OfItensAnexosCollection1NewItensAnexos.getItensAnexosCollection1().remove(itensAnexosCollection1NewItensAnexos);
                        oldItens1OfItensAnexosCollection1NewItensAnexos = em.merge(oldItens1OfItensAnexosCollection1NewItensAnexos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = itens.getIdItem();
                if (findItens(id) == null) {
                    throw new NonexistentEntityException("The itens with id " + id + " no longer exists.");
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
            Itens itens;
            try {
                itens = em.getReference(Itens.class, id);
                itens.getIdItem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itens with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RelEntItens> relEntItensCollectionOrphanCheck = itens.getRelEntItensCollection();
            for (RelEntItens relEntItensCollectionOrphanCheckRelEntItens : relEntItensCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Itens (" + itens + ") cannot be destroyed since the RelEntItens " + relEntItensCollectionOrphanCheckRelEntItens + " in its relEntItensCollection field has a non-nullable itens field.");
            }
            Collection<ItensAnexos> itensAnexosCollectionOrphanCheck = itens.getItensAnexosCollection();
            for (ItensAnexos itensAnexosCollectionOrphanCheckItensAnexos : itensAnexosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Itens (" + itens + ") cannot be destroyed since the ItensAnexos " + itensAnexosCollectionOrphanCheckItensAnexos + " in its itensAnexosCollection field has a non-nullable itens field.");
            }
            Collection<ItensAnexos> itensAnexosCollection1OrphanCheck = itens.getItensAnexosCollection1();
            for (ItensAnexos itensAnexosCollection1OrphanCheckItensAnexos : itensAnexosCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Itens (" + itens + ") cannot be destroyed since the ItensAnexos " + itensAnexosCollection1OrphanCheckItensAnexos + " in its itensAnexosCollection1 field has a non-nullable itens1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CatItens idCatItem = itens.getIdCatItem();
            if (idCatItem != null) {
                idCatItem.getItensCollection().remove(itens);
                idCatItem = em.merge(idCatItem);
            }
            TiposItens tipoItem = itens.getTipoItem();
            if (tipoItem != null) {
                tipoItem.getItensCollection().remove(itens);
                tipoItem = em.merge(tipoItem);
            }
            UnidMedida unidMedida = itens.getUnidMedida();
            if (unidMedida != null) {
                unidMedida.getItensCollection().remove(itens);
                unidMedida = em.merge(unidMedida);
            }
            Utilizadores userAlteracao = itens.getUserAlteracao();
            if (userAlteracao != null) {
                userAlteracao.getItensCollection().remove(itens);
                userAlteracao = em.merge(userAlteracao);
            }
            Utilizadores userCriacao = itens.getUserCriacao();
            if (userCriacao != null) {
                userCriacao.getItensCollection().remove(itens);
                userCriacao = em.merge(userCriacao);
            }
            Collection<TaxasAdic> taxasAdicCollection = itens.getTaxasAdicCollection();
            for (TaxasAdic taxasAdicCollectionTaxasAdic : taxasAdicCollection) {
                taxasAdicCollectionTaxasAdic.getItensCollection().remove(itens);
                taxasAdicCollectionTaxasAdic = em.merge(taxasAdicCollectionTaxasAdic);
            }
            Collection<Itens> itensCollection = itens.getItensCollection();
            for (Itens itensCollectionItens : itensCollection) {
                itensCollectionItens.getItensCollection().remove(itens);
                itensCollectionItens = em.merge(itensCollectionItens);
            }
            Collection<Itens> itensCollection1 = itens.getItensCollection1();
            for (Itens itensCollection1Itens : itensCollection1) {
                itensCollection1Itens.getItensCollection().remove(itens);
                itensCollection1Itens = em.merge(itensCollection1Itens);
            }
            Collection<MovStock> movStockCollection = itens.getMovStockCollection();
            for (MovStock movStockCollectionMovStock : movStockCollection) {
                movStockCollectionMovStock.setIdItem(null);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
            }
            Collection<DocLinhas> docLinhasCollection = itens.getDocLinhasCollection();
            for (DocLinhas docLinhasCollectionDocLinhas : docLinhasCollection) {
                docLinhasCollectionDocLinhas.setIdItem(null);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
            }
            em.remove(itens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itens> findItensEntities() {
        return findItensEntities(true, -1, -1);
    }

    public List<Itens> findItensEntities(int maxResults, int firstResult) {
        return findItensEntities(false, maxResults, firstResult);
    }

    private List<Itens> findItensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Itens as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Itens findItens(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itens.class, id);
        } finally {
            em.close();
        }
    }

    public int getItensCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Itens as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
