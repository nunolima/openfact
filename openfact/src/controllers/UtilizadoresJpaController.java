/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.Utilizadores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.TiposUser;
import entidades.ContasBanco;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Docs;
import entidades.TiposDoc;
import entidades.MovStock;
import entidades.Caixas;
import entidades.MovCaixas;
import entidades.AnosFiscais;
import entidades.Fotos;
import entidades.Entidades;
import entidades.TaxasDesc;
import entidades.RelEntItens;
import entidades.Itens;

/**
 *
 * @author User
 */
public class UtilizadoresJpaController {

    public UtilizadoresJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Utilizadores utilizadores) throws PreexistingEntityException, Exception {
        if (utilizadores.getContasBancoCollection() == null) {
            utilizadores.setContasBancoCollection(new ArrayList<ContasBanco>());
        }
        if (utilizadores.getContasBancoCollection1() == null) {
            utilizadores.setContasBancoCollection1(new ArrayList<ContasBanco>());
        }
        if (utilizadores.getDocsCollection() == null) {
            utilizadores.setDocsCollection(new ArrayList<Docs>());
        }
        if (utilizadores.getDocsCollection1() == null) {
            utilizadores.setDocsCollection1(new ArrayList<Docs>());
        }
        if (utilizadores.getTiposDocCollection() == null) {
            utilizadores.setTiposDocCollection(new ArrayList<TiposDoc>());
        }
        if (utilizadores.getTiposDocCollection1() == null) {
            utilizadores.setTiposDocCollection1(new ArrayList<TiposDoc>());
        }
        if (utilizadores.getMovStockCollection() == null) {
            utilizadores.setMovStockCollection(new ArrayList<MovStock>());
        }
        if (utilizadores.getCaixasCollection() == null) {
            utilizadores.setCaixasCollection(new ArrayList<Caixas>());
        }
        if (utilizadores.getCaixasCollection1() == null) {
            utilizadores.setCaixasCollection1(new ArrayList<Caixas>());
        }
        if (utilizadores.getMovCaixasCollection() == null) {
            utilizadores.setMovCaixasCollection(new ArrayList<MovCaixas>());
        }
        if (utilizadores.getMovCaixasCollection1() == null) {
            utilizadores.setMovCaixasCollection1(new ArrayList<MovCaixas>());
        }
        if (utilizadores.getAnosFiscaisCollection() == null) {
            utilizadores.setAnosFiscaisCollection(new ArrayList<AnosFiscais>());
        }
        if (utilizadores.getAnosFiscaisCollection1() == null) {
            utilizadores.setAnosFiscaisCollection1(new ArrayList<AnosFiscais>());
        }
        if (utilizadores.getFotosCollection() == null) {
            utilizadores.setFotosCollection(new ArrayList<Fotos>());
        }
        if (utilizadores.getEntidadesCollection() == null) {
            utilizadores.setEntidadesCollection(new ArrayList<Entidades>());
        }
        if (utilizadores.getEntidadesCollection1() == null) {
            utilizadores.setEntidadesCollection1(new ArrayList<Entidades>());
        }
        if (utilizadores.getTaxasDescCollection() == null) {
            utilizadores.setTaxasDescCollection(new ArrayList<TaxasDesc>());
        }
        if (utilizadores.getTaxasDescCollection1() == null) {
            utilizadores.setTaxasDescCollection1(new ArrayList<TaxasDesc>());
        }
        if (utilizadores.getRelEntItensCollection() == null) {
            utilizadores.setRelEntItensCollection(new ArrayList<RelEntItens>());
        }
        if (utilizadores.getRelEntItensCollection1() == null) {
            utilizadores.setRelEntItensCollection1(new ArrayList<RelEntItens>());
        }
        if (utilizadores.getItensCollection() == null) {
            utilizadores.setItensCollection(new ArrayList<Itens>());
        }
        if (utilizadores.getItensCollection1() == null) {
            utilizadores.setItensCollection1(new ArrayList<Itens>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposUser idTipoUser = utilizadores.getIdTipoUser();
            if (idTipoUser != null) {
                idTipoUser = em.getReference(idTipoUser.getClass(), idTipoUser.getIdTipoUser());
                utilizadores.setIdTipoUser(idTipoUser);
            }
            Collection<ContasBanco> attachedContasBancoCollection = new ArrayList<ContasBanco>();
            for (ContasBanco contasBancoCollectionContasBancoToAttach : utilizadores.getContasBancoCollection()) {
                contasBancoCollectionContasBancoToAttach = em.getReference(contasBancoCollectionContasBancoToAttach.getClass(), contasBancoCollectionContasBancoToAttach.getIdConta());
                attachedContasBancoCollection.add(contasBancoCollectionContasBancoToAttach);
            }
            utilizadores.setContasBancoCollection(attachedContasBancoCollection);
            Collection<ContasBanco> attachedContasBancoCollection1 = new ArrayList<ContasBanco>();
            for (ContasBanco contasBancoCollection1ContasBancoToAttach : utilizadores.getContasBancoCollection1()) {
                contasBancoCollection1ContasBancoToAttach = em.getReference(contasBancoCollection1ContasBancoToAttach.getClass(), contasBancoCollection1ContasBancoToAttach.getIdConta());
                attachedContasBancoCollection1.add(contasBancoCollection1ContasBancoToAttach);
            }
            utilizadores.setContasBancoCollection1(attachedContasBancoCollection1);
            Collection<Docs> attachedDocsCollection = new ArrayList<Docs>();
            for (Docs docsCollectionDocsToAttach : utilizadores.getDocsCollection()) {
                docsCollectionDocsToAttach = em.getReference(docsCollectionDocsToAttach.getClass(), docsCollectionDocsToAttach.getIdDoc());
                attachedDocsCollection.add(docsCollectionDocsToAttach);
            }
            utilizadores.setDocsCollection(attachedDocsCollection);
            Collection<Docs> attachedDocsCollection1 = new ArrayList<Docs>();
            for (Docs docsCollection1DocsToAttach : utilizadores.getDocsCollection1()) {
                docsCollection1DocsToAttach = em.getReference(docsCollection1DocsToAttach.getClass(), docsCollection1DocsToAttach.getIdDoc());
                attachedDocsCollection1.add(docsCollection1DocsToAttach);
            }
            utilizadores.setDocsCollection1(attachedDocsCollection1);
            Collection<TiposDoc> attachedTiposDocCollection = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollectionTiposDocToAttach : utilizadores.getTiposDocCollection()) {
                tiposDocCollectionTiposDocToAttach = em.getReference(tiposDocCollectionTiposDocToAttach.getClass(), tiposDocCollectionTiposDocToAttach.getTipoDoc());
                attachedTiposDocCollection.add(tiposDocCollectionTiposDocToAttach);
            }
            utilizadores.setTiposDocCollection(attachedTiposDocCollection);
            Collection<TiposDoc> attachedTiposDocCollection1 = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollection1TiposDocToAttach : utilizadores.getTiposDocCollection1()) {
                tiposDocCollection1TiposDocToAttach = em.getReference(tiposDocCollection1TiposDocToAttach.getClass(), tiposDocCollection1TiposDocToAttach.getTipoDoc());
                attachedTiposDocCollection1.add(tiposDocCollection1TiposDocToAttach);
            }
            utilizadores.setTiposDocCollection1(attachedTiposDocCollection1);
            Collection<MovStock> attachedMovStockCollection = new ArrayList<MovStock>();
            for (MovStock movStockCollectionMovStockToAttach : utilizadores.getMovStockCollection()) {
                movStockCollectionMovStockToAttach = em.getReference(movStockCollectionMovStockToAttach.getClass(), movStockCollectionMovStockToAttach.getIdMovStock());
                attachedMovStockCollection.add(movStockCollectionMovStockToAttach);
            }
            utilizadores.setMovStockCollection(attachedMovStockCollection);
            Collection<Caixas> attachedCaixasCollection = new ArrayList<Caixas>();
            for (Caixas caixasCollectionCaixasToAttach : utilizadores.getCaixasCollection()) {
                caixasCollectionCaixasToAttach = em.getReference(caixasCollectionCaixasToAttach.getClass(), caixasCollectionCaixasToAttach.getIdCx());
                attachedCaixasCollection.add(caixasCollectionCaixasToAttach);
            }
            utilizadores.setCaixasCollection(attachedCaixasCollection);
            Collection<Caixas> attachedCaixasCollection1 = new ArrayList<Caixas>();
            for (Caixas caixasCollection1CaixasToAttach : utilizadores.getCaixasCollection1()) {
                caixasCollection1CaixasToAttach = em.getReference(caixasCollection1CaixasToAttach.getClass(), caixasCollection1CaixasToAttach.getIdCx());
                attachedCaixasCollection1.add(caixasCollection1CaixasToAttach);
            }
            utilizadores.setCaixasCollection1(attachedCaixasCollection1);
            Collection<MovCaixas> attachedMovCaixasCollection = new ArrayList<MovCaixas>();
            for (MovCaixas movCaixasCollectionMovCaixasToAttach : utilizadores.getMovCaixasCollection()) {
                movCaixasCollectionMovCaixasToAttach = em.getReference(movCaixasCollectionMovCaixasToAttach.getClass(), movCaixasCollectionMovCaixasToAttach.getIdMovCx());
                attachedMovCaixasCollection.add(movCaixasCollectionMovCaixasToAttach);
            }
            utilizadores.setMovCaixasCollection(attachedMovCaixasCollection);
            Collection<MovCaixas> attachedMovCaixasCollection1 = new ArrayList<MovCaixas>();
            for (MovCaixas movCaixasCollection1MovCaixasToAttach : utilizadores.getMovCaixasCollection1()) {
                movCaixasCollection1MovCaixasToAttach = em.getReference(movCaixasCollection1MovCaixasToAttach.getClass(), movCaixasCollection1MovCaixasToAttach.getIdMovCx());
                attachedMovCaixasCollection1.add(movCaixasCollection1MovCaixasToAttach);
            }
            utilizadores.setMovCaixasCollection1(attachedMovCaixasCollection1);
            Collection<AnosFiscais> attachedAnosFiscaisCollection = new ArrayList<AnosFiscais>();
            for (AnosFiscais anosFiscaisCollectionAnosFiscaisToAttach : utilizadores.getAnosFiscaisCollection()) {
                anosFiscaisCollectionAnosFiscaisToAttach = em.getReference(anosFiscaisCollectionAnosFiscaisToAttach.getClass(), anosFiscaisCollectionAnosFiscaisToAttach.getAno());
                attachedAnosFiscaisCollection.add(anosFiscaisCollectionAnosFiscaisToAttach);
            }
            utilizadores.setAnosFiscaisCollection(attachedAnosFiscaisCollection);
            Collection<AnosFiscais> attachedAnosFiscaisCollection1 = new ArrayList<AnosFiscais>();
            for (AnosFiscais anosFiscaisCollection1AnosFiscaisToAttach : utilizadores.getAnosFiscaisCollection1()) {
                anosFiscaisCollection1AnosFiscaisToAttach = em.getReference(anosFiscaisCollection1AnosFiscaisToAttach.getClass(), anosFiscaisCollection1AnosFiscaisToAttach.getAno());
                attachedAnosFiscaisCollection1.add(anosFiscaisCollection1AnosFiscaisToAttach);
            }
            utilizadores.setAnosFiscaisCollection1(attachedAnosFiscaisCollection1);
            Collection<Fotos> attachedFotosCollection = new ArrayList<Fotos>();
            for (Fotos fotosCollectionFotosToAttach : utilizadores.getFotosCollection()) {
                fotosCollectionFotosToAttach = em.getReference(fotosCollectionFotosToAttach.getClass(), fotosCollectionFotosToAttach.getFotosPK());
                attachedFotosCollection.add(fotosCollectionFotosToAttach);
            }
            utilizadores.setFotosCollection(attachedFotosCollection);
            Collection<Entidades> attachedEntidadesCollection = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionEntidadesToAttach : utilizadores.getEntidadesCollection()) {
                entidadesCollectionEntidadesToAttach = em.getReference(entidadesCollectionEntidadesToAttach.getClass(), entidadesCollectionEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection.add(entidadesCollectionEntidadesToAttach);
            }
            utilizadores.setEntidadesCollection(attachedEntidadesCollection);
            Collection<Entidades> attachedEntidadesCollection1 = new ArrayList<Entidades>();
            for (Entidades entidadesCollection1EntidadesToAttach : utilizadores.getEntidadesCollection1()) {
                entidadesCollection1EntidadesToAttach = em.getReference(entidadesCollection1EntidadesToAttach.getClass(), entidadesCollection1EntidadesToAttach.getIdEnt());
                attachedEntidadesCollection1.add(entidadesCollection1EntidadesToAttach);
            }
            utilizadores.setEntidadesCollection1(attachedEntidadesCollection1);
            Collection<TaxasDesc> attachedTaxasDescCollection = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollectionTaxasDescToAttach : utilizadores.getTaxasDescCollection()) {
                taxasDescCollectionTaxasDescToAttach = em.getReference(taxasDescCollectionTaxasDescToAttach.getClass(), taxasDescCollectionTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollection.add(taxasDescCollectionTaxasDescToAttach);
            }
            utilizadores.setTaxasDescCollection(attachedTaxasDescCollection);
            Collection<TaxasDesc> attachedTaxasDescCollection1 = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollection1TaxasDescToAttach : utilizadores.getTaxasDescCollection1()) {
                taxasDescCollection1TaxasDescToAttach = em.getReference(taxasDescCollection1TaxasDescToAttach.getClass(), taxasDescCollection1TaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollection1.add(taxasDescCollection1TaxasDescToAttach);
            }
            utilizadores.setTaxasDescCollection1(attachedTaxasDescCollection1);
            Collection<RelEntItens> attachedRelEntItensCollection = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollectionRelEntItensToAttach : utilizadores.getRelEntItensCollection()) {
                relEntItensCollectionRelEntItensToAttach = em.getReference(relEntItensCollectionRelEntItensToAttach.getClass(), relEntItensCollectionRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollection.add(relEntItensCollectionRelEntItensToAttach);
            }
            utilizadores.setRelEntItensCollection(attachedRelEntItensCollection);
            Collection<RelEntItens> attachedRelEntItensCollection1 = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollection1RelEntItensToAttach : utilizadores.getRelEntItensCollection1()) {
                relEntItensCollection1RelEntItensToAttach = em.getReference(relEntItensCollection1RelEntItensToAttach.getClass(), relEntItensCollection1RelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollection1.add(relEntItensCollection1RelEntItensToAttach);
            }
            utilizadores.setRelEntItensCollection1(attachedRelEntItensCollection1);
            Collection<Itens> attachedItensCollection = new ArrayList<Itens>();
            for (Itens itensCollectionItensToAttach : utilizadores.getItensCollection()) {
                itensCollectionItensToAttach = em.getReference(itensCollectionItensToAttach.getClass(), itensCollectionItensToAttach.getIdItem());
                attachedItensCollection.add(itensCollectionItensToAttach);
            }
            utilizadores.setItensCollection(attachedItensCollection);
            Collection<Itens> attachedItensCollection1 = new ArrayList<Itens>();
            for (Itens itensCollection1ItensToAttach : utilizadores.getItensCollection1()) {
                itensCollection1ItensToAttach = em.getReference(itensCollection1ItensToAttach.getClass(), itensCollection1ItensToAttach.getIdItem());
                attachedItensCollection1.add(itensCollection1ItensToAttach);
            }
            utilizadores.setItensCollection1(attachedItensCollection1);
            em.persist(utilizadores);
            if (idTipoUser != null) {
                idTipoUser.getUtilizadoresCollection().add(utilizadores);
                idTipoUser = em.merge(idTipoUser);
            }
            for (ContasBanco contasBancoCollectionContasBanco : utilizadores.getContasBancoCollection()) {
                Utilizadores oldUserAlteracaoOfContasBancoCollectionContasBanco = contasBancoCollectionContasBanco.getUserAlteracao();
                contasBancoCollectionContasBanco.setUserAlteracao(utilizadores);
                contasBancoCollectionContasBanco = em.merge(contasBancoCollectionContasBanco);
                if (oldUserAlteracaoOfContasBancoCollectionContasBanco != null) {
                    oldUserAlteracaoOfContasBancoCollectionContasBanco.getContasBancoCollection().remove(contasBancoCollectionContasBanco);
                    oldUserAlteracaoOfContasBancoCollectionContasBanco = em.merge(oldUserAlteracaoOfContasBancoCollectionContasBanco);
                }
            }
            for (ContasBanco contasBancoCollection1ContasBanco : utilizadores.getContasBancoCollection1()) {
                Utilizadores oldUserCriacaoOfContasBancoCollection1ContasBanco = contasBancoCollection1ContasBanco.getUserCriacao();
                contasBancoCollection1ContasBanco.setUserCriacao(utilizadores);
                contasBancoCollection1ContasBanco = em.merge(contasBancoCollection1ContasBanco);
                if (oldUserCriacaoOfContasBancoCollection1ContasBanco != null) {
                    oldUserCriacaoOfContasBancoCollection1ContasBanco.getContasBancoCollection1().remove(contasBancoCollection1ContasBanco);
                    oldUserCriacaoOfContasBancoCollection1ContasBanco = em.merge(oldUserCriacaoOfContasBancoCollection1ContasBanco);
                }
            }
            for (Docs docsCollectionDocs : utilizadores.getDocsCollection()) {
                Utilizadores oldUserAlteracaoOfDocsCollectionDocs = docsCollectionDocs.getUserAlteracao();
                docsCollectionDocs.setUserAlteracao(utilizadores);
                docsCollectionDocs = em.merge(docsCollectionDocs);
                if (oldUserAlteracaoOfDocsCollectionDocs != null) {
                    oldUserAlteracaoOfDocsCollectionDocs.getDocsCollection().remove(docsCollectionDocs);
                    oldUserAlteracaoOfDocsCollectionDocs = em.merge(oldUserAlteracaoOfDocsCollectionDocs);
                }
            }
            for (Docs docsCollection1Docs : utilizadores.getDocsCollection1()) {
                Utilizadores oldUserCriacaoOfDocsCollection1Docs = docsCollection1Docs.getUserCriacao();
                docsCollection1Docs.setUserCriacao(utilizadores);
                docsCollection1Docs = em.merge(docsCollection1Docs);
                if (oldUserCriacaoOfDocsCollection1Docs != null) {
                    oldUserCriacaoOfDocsCollection1Docs.getDocsCollection1().remove(docsCollection1Docs);
                    oldUserCriacaoOfDocsCollection1Docs = em.merge(oldUserCriacaoOfDocsCollection1Docs);
                }
            }
            for (TiposDoc tiposDocCollectionTiposDoc : utilizadores.getTiposDocCollection()) {
                Utilizadores oldUserAlteracaoOfTiposDocCollectionTiposDoc = tiposDocCollectionTiposDoc.getUserAlteracao();
                tiposDocCollectionTiposDoc.setUserAlteracao(utilizadores);
                tiposDocCollectionTiposDoc = em.merge(tiposDocCollectionTiposDoc);
                if (oldUserAlteracaoOfTiposDocCollectionTiposDoc != null) {
                    oldUserAlteracaoOfTiposDocCollectionTiposDoc.getTiposDocCollection().remove(tiposDocCollectionTiposDoc);
                    oldUserAlteracaoOfTiposDocCollectionTiposDoc = em.merge(oldUserAlteracaoOfTiposDocCollectionTiposDoc);
                }
            }
            for (TiposDoc tiposDocCollection1TiposDoc : utilizadores.getTiposDocCollection1()) {
                Utilizadores oldUserCriacaoOfTiposDocCollection1TiposDoc = tiposDocCollection1TiposDoc.getUserCriacao();
                tiposDocCollection1TiposDoc.setUserCriacao(utilizadores);
                tiposDocCollection1TiposDoc = em.merge(tiposDocCollection1TiposDoc);
                if (oldUserCriacaoOfTiposDocCollection1TiposDoc != null) {
                    oldUserCriacaoOfTiposDocCollection1TiposDoc.getTiposDocCollection1().remove(tiposDocCollection1TiposDoc);
                    oldUserCriacaoOfTiposDocCollection1TiposDoc = em.merge(oldUserCriacaoOfTiposDocCollection1TiposDoc);
                }
            }
            for (MovStock movStockCollectionMovStock : utilizadores.getMovStockCollection()) {
                Utilizadores oldUserCriacaoOfMovStockCollectionMovStock = movStockCollectionMovStock.getUserCriacao();
                movStockCollectionMovStock.setUserCriacao(utilizadores);
                movStockCollectionMovStock = em.merge(movStockCollectionMovStock);
                if (oldUserCriacaoOfMovStockCollectionMovStock != null) {
                    oldUserCriacaoOfMovStockCollectionMovStock.getMovStockCollection().remove(movStockCollectionMovStock);
                    oldUserCriacaoOfMovStockCollectionMovStock = em.merge(oldUserCriacaoOfMovStockCollectionMovStock);
                }
            }
            for (Caixas caixasCollectionCaixas : utilizadores.getCaixasCollection()) {
                Utilizadores oldUserAlteracaoOfCaixasCollectionCaixas = caixasCollectionCaixas.getUserAlteracao();
                caixasCollectionCaixas.setUserAlteracao(utilizadores);
                caixasCollectionCaixas = em.merge(caixasCollectionCaixas);
                if (oldUserAlteracaoOfCaixasCollectionCaixas != null) {
                    oldUserAlteracaoOfCaixasCollectionCaixas.getCaixasCollection().remove(caixasCollectionCaixas);
                    oldUserAlteracaoOfCaixasCollectionCaixas = em.merge(oldUserAlteracaoOfCaixasCollectionCaixas);
                }
            }
            for (Caixas caixasCollection1Caixas : utilizadores.getCaixasCollection1()) {
                Utilizadores oldUserCriacaoOfCaixasCollection1Caixas = caixasCollection1Caixas.getUserCriacao();
                caixasCollection1Caixas.setUserCriacao(utilizadores);
                caixasCollection1Caixas = em.merge(caixasCollection1Caixas);
                if (oldUserCriacaoOfCaixasCollection1Caixas != null) {
                    oldUserCriacaoOfCaixasCollection1Caixas.getCaixasCollection1().remove(caixasCollection1Caixas);
                    oldUserCriacaoOfCaixasCollection1Caixas = em.merge(oldUserCriacaoOfCaixasCollection1Caixas);
                }
            }
            for (MovCaixas movCaixasCollectionMovCaixas : utilizadores.getMovCaixasCollection()) {
                Utilizadores oldUserAlteracaoOfMovCaixasCollectionMovCaixas = movCaixasCollectionMovCaixas.getUserAlteracao();
                movCaixasCollectionMovCaixas.setUserAlteracao(utilizadores);
                movCaixasCollectionMovCaixas = em.merge(movCaixasCollectionMovCaixas);
                if (oldUserAlteracaoOfMovCaixasCollectionMovCaixas != null) {
                    oldUserAlteracaoOfMovCaixasCollectionMovCaixas.getMovCaixasCollection().remove(movCaixasCollectionMovCaixas);
                    oldUserAlteracaoOfMovCaixasCollectionMovCaixas = em.merge(oldUserAlteracaoOfMovCaixasCollectionMovCaixas);
                }
            }
            for (MovCaixas movCaixasCollection1MovCaixas : utilizadores.getMovCaixasCollection1()) {
                Utilizadores oldUserCriacaoOfMovCaixasCollection1MovCaixas = movCaixasCollection1MovCaixas.getUserCriacao();
                movCaixasCollection1MovCaixas.setUserCriacao(utilizadores);
                movCaixasCollection1MovCaixas = em.merge(movCaixasCollection1MovCaixas);
                if (oldUserCriacaoOfMovCaixasCollection1MovCaixas != null) {
                    oldUserCriacaoOfMovCaixasCollection1MovCaixas.getMovCaixasCollection1().remove(movCaixasCollection1MovCaixas);
                    oldUserCriacaoOfMovCaixasCollection1MovCaixas = em.merge(oldUserCriacaoOfMovCaixasCollection1MovCaixas);
                }
            }
            for (AnosFiscais anosFiscaisCollectionAnosFiscais : utilizadores.getAnosFiscaisCollection()) {
                Utilizadores oldUserAlteracaoOfAnosFiscaisCollectionAnosFiscais = anosFiscaisCollectionAnosFiscais.getUserAlteracao();
                anosFiscaisCollectionAnosFiscais.setUserAlteracao(utilizadores);
                anosFiscaisCollectionAnosFiscais = em.merge(anosFiscaisCollectionAnosFiscais);
                if (oldUserAlteracaoOfAnosFiscaisCollectionAnosFiscais != null) {
                    oldUserAlteracaoOfAnosFiscaisCollectionAnosFiscais.getAnosFiscaisCollection().remove(anosFiscaisCollectionAnosFiscais);
                    oldUserAlteracaoOfAnosFiscaisCollectionAnosFiscais = em.merge(oldUserAlteracaoOfAnosFiscaisCollectionAnosFiscais);
                }
            }
            for (AnosFiscais anosFiscaisCollection1AnosFiscais : utilizadores.getAnosFiscaisCollection1()) {
                Utilizadores oldUserCriacaoOfAnosFiscaisCollection1AnosFiscais = anosFiscaisCollection1AnosFiscais.getUserCriacao();
                anosFiscaisCollection1AnosFiscais.setUserCriacao(utilizadores);
                anosFiscaisCollection1AnosFiscais = em.merge(anosFiscaisCollection1AnosFiscais);
                if (oldUserCriacaoOfAnosFiscaisCollection1AnosFiscais != null) {
                    oldUserCriacaoOfAnosFiscaisCollection1AnosFiscais.getAnosFiscaisCollection1().remove(anosFiscaisCollection1AnosFiscais);
                    oldUserCriacaoOfAnosFiscaisCollection1AnosFiscais = em.merge(oldUserCriacaoOfAnosFiscaisCollection1AnosFiscais);
                }
            }
            for (Fotos fotosCollectionFotos : utilizadores.getFotosCollection()) {
                Utilizadores oldUserCriacaoOfFotosCollectionFotos = fotosCollectionFotos.getUserCriacao();
                fotosCollectionFotos.setUserCriacao(utilizadores);
                fotosCollectionFotos = em.merge(fotosCollectionFotos);
                if (oldUserCriacaoOfFotosCollectionFotos != null) {
                    oldUserCriacaoOfFotosCollectionFotos.getFotosCollection().remove(fotosCollectionFotos);
                    oldUserCriacaoOfFotosCollectionFotos = em.merge(oldUserCriacaoOfFotosCollectionFotos);
                }
            }
            for (Entidades entidadesCollectionEntidades : utilizadores.getEntidadesCollection()) {
                Utilizadores oldUserAlteracaoOfEntidadesCollectionEntidades = entidadesCollectionEntidades.getUserAlteracao();
                entidadesCollectionEntidades.setUserAlteracao(utilizadores);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
                if (oldUserAlteracaoOfEntidadesCollectionEntidades != null) {
                    oldUserAlteracaoOfEntidadesCollectionEntidades.getEntidadesCollection().remove(entidadesCollectionEntidades);
                    oldUserAlteracaoOfEntidadesCollectionEntidades = em.merge(oldUserAlteracaoOfEntidadesCollectionEntidades);
                }
            }
            for (Entidades entidadesCollection1Entidades : utilizadores.getEntidadesCollection1()) {
                Utilizadores oldUserCriacaoOfEntidadesCollection1Entidades = entidadesCollection1Entidades.getUserCriacao();
                entidadesCollection1Entidades.setUserCriacao(utilizadores);
                entidadesCollection1Entidades = em.merge(entidadesCollection1Entidades);
                if (oldUserCriacaoOfEntidadesCollection1Entidades != null) {
                    oldUserCriacaoOfEntidadesCollection1Entidades.getEntidadesCollection1().remove(entidadesCollection1Entidades);
                    oldUserCriacaoOfEntidadesCollection1Entidades = em.merge(oldUserCriacaoOfEntidadesCollection1Entidades);
                }
            }
            for (TaxasDesc taxasDescCollectionTaxasDesc : utilizadores.getTaxasDescCollection()) {
                Utilizadores oldUserAlteracaoOfTaxasDescCollectionTaxasDesc = taxasDescCollectionTaxasDesc.getUserAlteracao();
                taxasDescCollectionTaxasDesc.setUserAlteracao(utilizadores);
                taxasDescCollectionTaxasDesc = em.merge(taxasDescCollectionTaxasDesc);
                if (oldUserAlteracaoOfTaxasDescCollectionTaxasDesc != null) {
                    oldUserAlteracaoOfTaxasDescCollectionTaxasDesc.getTaxasDescCollection().remove(taxasDescCollectionTaxasDesc);
                    oldUserAlteracaoOfTaxasDescCollectionTaxasDesc = em.merge(oldUserAlteracaoOfTaxasDescCollectionTaxasDesc);
                }
            }
            for (TaxasDesc taxasDescCollection1TaxasDesc : utilizadores.getTaxasDescCollection1()) {
                Utilizadores oldUserCriacaoOfTaxasDescCollection1TaxasDesc = taxasDescCollection1TaxasDesc.getUserCriacao();
                taxasDescCollection1TaxasDesc.setUserCriacao(utilizadores);
                taxasDescCollection1TaxasDesc = em.merge(taxasDescCollection1TaxasDesc);
                if (oldUserCriacaoOfTaxasDescCollection1TaxasDesc != null) {
                    oldUserCriacaoOfTaxasDescCollection1TaxasDesc.getTaxasDescCollection1().remove(taxasDescCollection1TaxasDesc);
                    oldUserCriacaoOfTaxasDescCollection1TaxasDesc = em.merge(oldUserCriacaoOfTaxasDescCollection1TaxasDesc);
                }
            }
            for (RelEntItens relEntItensCollectionRelEntItens : utilizadores.getRelEntItensCollection()) {
                Utilizadores oldUserAlteracaoOfRelEntItensCollectionRelEntItens = relEntItensCollectionRelEntItens.getUserAlteracao();
                relEntItensCollectionRelEntItens.setUserAlteracao(utilizadores);
                relEntItensCollectionRelEntItens = em.merge(relEntItensCollectionRelEntItens);
                if (oldUserAlteracaoOfRelEntItensCollectionRelEntItens != null) {
                    oldUserAlteracaoOfRelEntItensCollectionRelEntItens.getRelEntItensCollection().remove(relEntItensCollectionRelEntItens);
                    oldUserAlteracaoOfRelEntItensCollectionRelEntItens = em.merge(oldUserAlteracaoOfRelEntItensCollectionRelEntItens);
                }
            }
            for (RelEntItens relEntItensCollection1RelEntItens : utilizadores.getRelEntItensCollection1()) {
                Utilizadores oldUserCriacaoOfRelEntItensCollection1RelEntItens = relEntItensCollection1RelEntItens.getUserCriacao();
                relEntItensCollection1RelEntItens.setUserCriacao(utilizadores);
                relEntItensCollection1RelEntItens = em.merge(relEntItensCollection1RelEntItens);
                if (oldUserCriacaoOfRelEntItensCollection1RelEntItens != null) {
                    oldUserCriacaoOfRelEntItensCollection1RelEntItens.getRelEntItensCollection1().remove(relEntItensCollection1RelEntItens);
                    oldUserCriacaoOfRelEntItensCollection1RelEntItens = em.merge(oldUserCriacaoOfRelEntItensCollection1RelEntItens);
                }
            }
            for (Itens itensCollectionItens : utilizadores.getItensCollection()) {
                Utilizadores oldUserAlteracaoOfItensCollectionItens = itensCollectionItens.getUserAlteracao();
                itensCollectionItens.setUserAlteracao(utilizadores);
                itensCollectionItens = em.merge(itensCollectionItens);
                if (oldUserAlteracaoOfItensCollectionItens != null) {
                    oldUserAlteracaoOfItensCollectionItens.getItensCollection().remove(itensCollectionItens);
                    oldUserAlteracaoOfItensCollectionItens = em.merge(oldUserAlteracaoOfItensCollectionItens);
                }
            }
            for (Itens itensCollection1Itens : utilizadores.getItensCollection1()) {
                Utilizadores oldUserCriacaoOfItensCollection1Itens = itensCollection1Itens.getUserCriacao();
                itensCollection1Itens.setUserCriacao(utilizadores);
                itensCollection1Itens = em.merge(itensCollection1Itens);
                if (oldUserCriacaoOfItensCollection1Itens != null) {
                    oldUserCriacaoOfItensCollection1Itens.getItensCollection1().remove(itensCollection1Itens);
                    oldUserCriacaoOfItensCollection1Itens = em.merge(oldUserCriacaoOfItensCollection1Itens);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUtilizadores(utilizadores.getIdUtilizador()) != null) {
                throw new PreexistingEntityException("Utilizadores " + utilizadores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Utilizadores utilizadores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilizadores persistentUtilizadores = em.find(Utilizadores.class, utilizadores.getIdUtilizador());
            TiposUser idTipoUserOld = persistentUtilizadores.getIdTipoUser();
            TiposUser idTipoUserNew = utilizadores.getIdTipoUser();
            Collection<ContasBanco> contasBancoCollectionOld = persistentUtilizadores.getContasBancoCollection();
            Collection<ContasBanco> contasBancoCollectionNew = utilizadores.getContasBancoCollection();
            Collection<ContasBanco> contasBancoCollection1Old = persistentUtilizadores.getContasBancoCollection1();
            Collection<ContasBanco> contasBancoCollection1New = utilizadores.getContasBancoCollection1();
            Collection<Docs> docsCollectionOld = persistentUtilizadores.getDocsCollection();
            Collection<Docs> docsCollectionNew = utilizadores.getDocsCollection();
            Collection<Docs> docsCollection1Old = persistentUtilizadores.getDocsCollection1();
            Collection<Docs> docsCollection1New = utilizadores.getDocsCollection1();
            Collection<TiposDoc> tiposDocCollectionOld = persistentUtilizadores.getTiposDocCollection();
            Collection<TiposDoc> tiposDocCollectionNew = utilizadores.getTiposDocCollection();
            Collection<TiposDoc> tiposDocCollection1Old = persistentUtilizadores.getTiposDocCollection1();
            Collection<TiposDoc> tiposDocCollection1New = utilizadores.getTiposDocCollection1();
            Collection<MovStock> movStockCollectionOld = persistentUtilizadores.getMovStockCollection();
            Collection<MovStock> movStockCollectionNew = utilizadores.getMovStockCollection();
            Collection<Caixas> caixasCollectionOld = persistentUtilizadores.getCaixasCollection();
            Collection<Caixas> caixasCollectionNew = utilizadores.getCaixasCollection();
            Collection<Caixas> caixasCollection1Old = persistentUtilizadores.getCaixasCollection1();
            Collection<Caixas> caixasCollection1New = utilizadores.getCaixasCollection1();
            Collection<MovCaixas> movCaixasCollectionOld = persistentUtilizadores.getMovCaixasCollection();
            Collection<MovCaixas> movCaixasCollectionNew = utilizadores.getMovCaixasCollection();
            Collection<MovCaixas> movCaixasCollection1Old = persistentUtilizadores.getMovCaixasCollection1();
            Collection<MovCaixas> movCaixasCollection1New = utilizadores.getMovCaixasCollection1();
            Collection<AnosFiscais> anosFiscaisCollectionOld = persistentUtilizadores.getAnosFiscaisCollection();
            Collection<AnosFiscais> anosFiscaisCollectionNew = utilizadores.getAnosFiscaisCollection();
            Collection<AnosFiscais> anosFiscaisCollection1Old = persistentUtilizadores.getAnosFiscaisCollection1();
            Collection<AnosFiscais> anosFiscaisCollection1New = utilizadores.getAnosFiscaisCollection1();
            Collection<Fotos> fotosCollectionOld = persistentUtilizadores.getFotosCollection();
            Collection<Fotos> fotosCollectionNew = utilizadores.getFotosCollection();
            Collection<Entidades> entidadesCollectionOld = persistentUtilizadores.getEntidadesCollection();
            Collection<Entidades> entidadesCollectionNew = utilizadores.getEntidadesCollection();
            Collection<Entidades> entidadesCollection1Old = persistentUtilizadores.getEntidadesCollection1();
            Collection<Entidades> entidadesCollection1New = utilizadores.getEntidadesCollection1();
            Collection<TaxasDesc> taxasDescCollectionOld = persistentUtilizadores.getTaxasDescCollection();
            Collection<TaxasDesc> taxasDescCollectionNew = utilizadores.getTaxasDescCollection();
            Collection<TaxasDesc> taxasDescCollection1Old = persistentUtilizadores.getTaxasDescCollection1();
            Collection<TaxasDesc> taxasDescCollection1New = utilizadores.getTaxasDescCollection1();
            Collection<RelEntItens> relEntItensCollectionOld = persistentUtilizadores.getRelEntItensCollection();
            Collection<RelEntItens> relEntItensCollectionNew = utilizadores.getRelEntItensCollection();
            Collection<RelEntItens> relEntItensCollection1Old = persistentUtilizadores.getRelEntItensCollection1();
            Collection<RelEntItens> relEntItensCollection1New = utilizadores.getRelEntItensCollection1();
            Collection<Itens> itensCollectionOld = persistentUtilizadores.getItensCollection();
            Collection<Itens> itensCollectionNew = utilizadores.getItensCollection();
            Collection<Itens> itensCollection1Old = persistentUtilizadores.getItensCollection1();
            Collection<Itens> itensCollection1New = utilizadores.getItensCollection1();
            List<String> illegalOrphanMessages = null;
            for (ContasBanco contasBancoCollection1OldContasBanco : contasBancoCollection1Old) {
                if (!contasBancoCollection1New.contains(contasBancoCollection1OldContasBanco)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ContasBanco " + contasBancoCollection1OldContasBanco + " since its userCriacao field is not nullable.");
                }
            }
            for (Docs docsCollection1OldDocs : docsCollection1Old) {
                if (!docsCollection1New.contains(docsCollection1OldDocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docs " + docsCollection1OldDocs + " since its userCriacao field is not nullable.");
                }
            }
            for (TiposDoc tiposDocCollection1OldTiposDoc : tiposDocCollection1Old) {
                if (!tiposDocCollection1New.contains(tiposDocCollection1OldTiposDoc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TiposDoc " + tiposDocCollection1OldTiposDoc + " since its userCriacao field is not nullable.");
                }
            }
            for (MovStock movStockCollectionOldMovStock : movStockCollectionOld) {
                if (!movStockCollectionNew.contains(movStockCollectionOldMovStock)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MovStock " + movStockCollectionOldMovStock + " since its userCriacao field is not nullable.");
                }
            }
            for (Caixas caixasCollection1OldCaixas : caixasCollection1Old) {
                if (!caixasCollection1New.contains(caixasCollection1OldCaixas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Caixas " + caixasCollection1OldCaixas + " since its userCriacao field is not nullable.");
                }
            }
            for (MovCaixas movCaixasCollection1OldMovCaixas : movCaixasCollection1Old) {
                if (!movCaixasCollection1New.contains(movCaixasCollection1OldMovCaixas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MovCaixas " + movCaixasCollection1OldMovCaixas + " since its userCriacao field is not nullable.");
                }
            }
            for (AnosFiscais anosFiscaisCollection1OldAnosFiscais : anosFiscaisCollection1Old) {
                if (!anosFiscaisCollection1New.contains(anosFiscaisCollection1OldAnosFiscais)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AnosFiscais " + anosFiscaisCollection1OldAnosFiscais + " since its userCriacao field is not nullable.");
                }
            }
            for (Fotos fotosCollectionOldFotos : fotosCollectionOld) {
                if (!fotosCollectionNew.contains(fotosCollectionOldFotos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fotos " + fotosCollectionOldFotos + " since its userCriacao field is not nullable.");
                }
            }
            for (Entidades entidadesCollection1OldEntidades : entidadesCollection1Old) {
                if (!entidadesCollection1New.contains(entidadesCollection1OldEntidades)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Entidades " + entidadesCollection1OldEntidades + " since its userCriacao field is not nullable.");
                }
            }
            for (TaxasDesc taxasDescCollection1OldTaxasDesc : taxasDescCollection1Old) {
                if (!taxasDescCollection1New.contains(taxasDescCollection1OldTaxasDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TaxasDesc " + taxasDescCollection1OldTaxasDesc + " since its userCriacao field is not nullable.");
                }
            }
            for (RelEntItens relEntItensCollection1OldRelEntItens : relEntItensCollection1Old) {
                if (!relEntItensCollection1New.contains(relEntItensCollection1OldRelEntItens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelEntItens " + relEntItensCollection1OldRelEntItens + " since its userCriacao field is not nullable.");
                }
            }
            for (Itens itensCollection1OldItens : itensCollection1Old) {
                if (!itensCollection1New.contains(itensCollection1OldItens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itens " + itensCollection1OldItens + " since its userCriacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoUserNew != null) {
                idTipoUserNew = em.getReference(idTipoUserNew.getClass(), idTipoUserNew.getIdTipoUser());
                utilizadores.setIdTipoUser(idTipoUserNew);
            }
            Collection<ContasBanco> attachedContasBancoCollectionNew = new ArrayList<ContasBanco>();
            for (ContasBanco contasBancoCollectionNewContasBancoToAttach : contasBancoCollectionNew) {
                contasBancoCollectionNewContasBancoToAttach = em.getReference(contasBancoCollectionNewContasBancoToAttach.getClass(), contasBancoCollectionNewContasBancoToAttach.getIdConta());
                attachedContasBancoCollectionNew.add(contasBancoCollectionNewContasBancoToAttach);
            }
            contasBancoCollectionNew = attachedContasBancoCollectionNew;
            utilizadores.setContasBancoCollection(contasBancoCollectionNew);
            Collection<ContasBanco> attachedContasBancoCollection1New = new ArrayList<ContasBanco>();
            for (ContasBanco contasBancoCollection1NewContasBancoToAttach : contasBancoCollection1New) {
                contasBancoCollection1NewContasBancoToAttach = em.getReference(contasBancoCollection1NewContasBancoToAttach.getClass(), contasBancoCollection1NewContasBancoToAttach.getIdConta());
                attachedContasBancoCollection1New.add(contasBancoCollection1NewContasBancoToAttach);
            }
            contasBancoCollection1New = attachedContasBancoCollection1New;
            utilizadores.setContasBancoCollection1(contasBancoCollection1New);
            Collection<Docs> attachedDocsCollectionNew = new ArrayList<Docs>();
            for (Docs docsCollectionNewDocsToAttach : docsCollectionNew) {
                docsCollectionNewDocsToAttach = em.getReference(docsCollectionNewDocsToAttach.getClass(), docsCollectionNewDocsToAttach.getIdDoc());
                attachedDocsCollectionNew.add(docsCollectionNewDocsToAttach);
            }
            docsCollectionNew = attachedDocsCollectionNew;
            utilizadores.setDocsCollection(docsCollectionNew);
            Collection<Docs> attachedDocsCollection1New = new ArrayList<Docs>();
            for (Docs docsCollection1NewDocsToAttach : docsCollection1New) {
                docsCollection1NewDocsToAttach = em.getReference(docsCollection1NewDocsToAttach.getClass(), docsCollection1NewDocsToAttach.getIdDoc());
                attachedDocsCollection1New.add(docsCollection1NewDocsToAttach);
            }
            docsCollection1New = attachedDocsCollection1New;
            utilizadores.setDocsCollection1(docsCollection1New);
            Collection<TiposDoc> attachedTiposDocCollectionNew = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollectionNewTiposDocToAttach : tiposDocCollectionNew) {
                tiposDocCollectionNewTiposDocToAttach = em.getReference(tiposDocCollectionNewTiposDocToAttach.getClass(), tiposDocCollectionNewTiposDocToAttach.getTipoDoc());
                attachedTiposDocCollectionNew.add(tiposDocCollectionNewTiposDocToAttach);
            }
            tiposDocCollectionNew = attachedTiposDocCollectionNew;
            utilizadores.setTiposDocCollection(tiposDocCollectionNew);
            Collection<TiposDoc> attachedTiposDocCollection1New = new ArrayList<TiposDoc>();
            for (TiposDoc tiposDocCollection1NewTiposDocToAttach : tiposDocCollection1New) {
                tiposDocCollection1NewTiposDocToAttach = em.getReference(tiposDocCollection1NewTiposDocToAttach.getClass(), tiposDocCollection1NewTiposDocToAttach.getTipoDoc());
                attachedTiposDocCollection1New.add(tiposDocCollection1NewTiposDocToAttach);
            }
            tiposDocCollection1New = attachedTiposDocCollection1New;
            utilizadores.setTiposDocCollection1(tiposDocCollection1New);
            Collection<MovStock> attachedMovStockCollectionNew = new ArrayList<MovStock>();
            for (MovStock movStockCollectionNewMovStockToAttach : movStockCollectionNew) {
                movStockCollectionNewMovStockToAttach = em.getReference(movStockCollectionNewMovStockToAttach.getClass(), movStockCollectionNewMovStockToAttach.getIdMovStock());
                attachedMovStockCollectionNew.add(movStockCollectionNewMovStockToAttach);
            }
            movStockCollectionNew = attachedMovStockCollectionNew;
            utilizadores.setMovStockCollection(movStockCollectionNew);
            Collection<Caixas> attachedCaixasCollectionNew = new ArrayList<Caixas>();
            for (Caixas caixasCollectionNewCaixasToAttach : caixasCollectionNew) {
                caixasCollectionNewCaixasToAttach = em.getReference(caixasCollectionNewCaixasToAttach.getClass(), caixasCollectionNewCaixasToAttach.getIdCx());
                attachedCaixasCollectionNew.add(caixasCollectionNewCaixasToAttach);
            }
            caixasCollectionNew = attachedCaixasCollectionNew;
            utilizadores.setCaixasCollection(caixasCollectionNew);
            Collection<Caixas> attachedCaixasCollection1New = new ArrayList<Caixas>();
            for (Caixas caixasCollection1NewCaixasToAttach : caixasCollection1New) {
                caixasCollection1NewCaixasToAttach = em.getReference(caixasCollection1NewCaixasToAttach.getClass(), caixasCollection1NewCaixasToAttach.getIdCx());
                attachedCaixasCollection1New.add(caixasCollection1NewCaixasToAttach);
            }
            caixasCollection1New = attachedCaixasCollection1New;
            utilizadores.setCaixasCollection1(caixasCollection1New);
            Collection<MovCaixas> attachedMovCaixasCollectionNew = new ArrayList<MovCaixas>();
            for (MovCaixas movCaixasCollectionNewMovCaixasToAttach : movCaixasCollectionNew) {
                movCaixasCollectionNewMovCaixasToAttach = em.getReference(movCaixasCollectionNewMovCaixasToAttach.getClass(), movCaixasCollectionNewMovCaixasToAttach.getIdMovCx());
                attachedMovCaixasCollectionNew.add(movCaixasCollectionNewMovCaixasToAttach);
            }
            movCaixasCollectionNew = attachedMovCaixasCollectionNew;
            utilizadores.setMovCaixasCollection(movCaixasCollectionNew);
            Collection<MovCaixas> attachedMovCaixasCollection1New = new ArrayList<MovCaixas>();
            for (MovCaixas movCaixasCollection1NewMovCaixasToAttach : movCaixasCollection1New) {
                movCaixasCollection1NewMovCaixasToAttach = em.getReference(movCaixasCollection1NewMovCaixasToAttach.getClass(), movCaixasCollection1NewMovCaixasToAttach.getIdMovCx());
                attachedMovCaixasCollection1New.add(movCaixasCollection1NewMovCaixasToAttach);
            }
            movCaixasCollection1New = attachedMovCaixasCollection1New;
            utilizadores.setMovCaixasCollection1(movCaixasCollection1New);
            Collection<AnosFiscais> attachedAnosFiscaisCollectionNew = new ArrayList<AnosFiscais>();
            for (AnosFiscais anosFiscaisCollectionNewAnosFiscaisToAttach : anosFiscaisCollectionNew) {
                anosFiscaisCollectionNewAnosFiscaisToAttach = em.getReference(anosFiscaisCollectionNewAnosFiscaisToAttach.getClass(), anosFiscaisCollectionNewAnosFiscaisToAttach.getAno());
                attachedAnosFiscaisCollectionNew.add(anosFiscaisCollectionNewAnosFiscaisToAttach);
            }
            anosFiscaisCollectionNew = attachedAnosFiscaisCollectionNew;
            utilizadores.setAnosFiscaisCollection(anosFiscaisCollectionNew);
            Collection<AnosFiscais> attachedAnosFiscaisCollection1New = new ArrayList<AnosFiscais>();
            for (AnosFiscais anosFiscaisCollection1NewAnosFiscaisToAttach : anosFiscaisCollection1New) {
                anosFiscaisCollection1NewAnosFiscaisToAttach = em.getReference(anosFiscaisCollection1NewAnosFiscaisToAttach.getClass(), anosFiscaisCollection1NewAnosFiscaisToAttach.getAno());
                attachedAnosFiscaisCollection1New.add(anosFiscaisCollection1NewAnosFiscaisToAttach);
            }
            anosFiscaisCollection1New = attachedAnosFiscaisCollection1New;
            utilizadores.setAnosFiscaisCollection1(anosFiscaisCollection1New);
            Collection<Fotos> attachedFotosCollectionNew = new ArrayList<Fotos>();
            for (Fotos fotosCollectionNewFotosToAttach : fotosCollectionNew) {
                fotosCollectionNewFotosToAttach = em.getReference(fotosCollectionNewFotosToAttach.getClass(), fotosCollectionNewFotosToAttach.getFotosPK());
                attachedFotosCollectionNew.add(fotosCollectionNewFotosToAttach);
            }
            fotosCollectionNew = attachedFotosCollectionNew;
            utilizadores.setFotosCollection(fotosCollectionNew);
            Collection<Entidades> attachedEntidadesCollectionNew = new ArrayList<Entidades>();
            for (Entidades entidadesCollectionNewEntidadesToAttach : entidadesCollectionNew) {
                entidadesCollectionNewEntidadesToAttach = em.getReference(entidadesCollectionNewEntidadesToAttach.getClass(), entidadesCollectionNewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollectionNew.add(entidadesCollectionNewEntidadesToAttach);
            }
            entidadesCollectionNew = attachedEntidadesCollectionNew;
            utilizadores.setEntidadesCollection(entidadesCollectionNew);
            Collection<Entidades> attachedEntidadesCollection1New = new ArrayList<Entidades>();
            for (Entidades entidadesCollection1NewEntidadesToAttach : entidadesCollection1New) {
                entidadesCollection1NewEntidadesToAttach = em.getReference(entidadesCollection1NewEntidadesToAttach.getClass(), entidadesCollection1NewEntidadesToAttach.getIdEnt());
                attachedEntidadesCollection1New.add(entidadesCollection1NewEntidadesToAttach);
            }
            entidadesCollection1New = attachedEntidadesCollection1New;
            utilizadores.setEntidadesCollection1(entidadesCollection1New);
            Collection<TaxasDesc> attachedTaxasDescCollectionNew = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollectionNewTaxasDescToAttach : taxasDescCollectionNew) {
                taxasDescCollectionNewTaxasDescToAttach = em.getReference(taxasDescCollectionNewTaxasDescToAttach.getClass(), taxasDescCollectionNewTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollectionNew.add(taxasDescCollectionNewTaxasDescToAttach);
            }
            taxasDescCollectionNew = attachedTaxasDescCollectionNew;
            utilizadores.setTaxasDescCollection(taxasDescCollectionNew);
            Collection<TaxasDesc> attachedTaxasDescCollection1New = new ArrayList<TaxasDesc>();
            for (TaxasDesc taxasDescCollection1NewTaxasDescToAttach : taxasDescCollection1New) {
                taxasDescCollection1NewTaxasDescToAttach = em.getReference(taxasDescCollection1NewTaxasDescToAttach.getClass(), taxasDescCollection1NewTaxasDescToAttach.getTaxasDescPK());
                attachedTaxasDescCollection1New.add(taxasDescCollection1NewTaxasDescToAttach);
            }
            taxasDescCollection1New = attachedTaxasDescCollection1New;
            utilizadores.setTaxasDescCollection1(taxasDescCollection1New);
            Collection<RelEntItens> attachedRelEntItensCollectionNew = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollectionNewRelEntItensToAttach : relEntItensCollectionNew) {
                relEntItensCollectionNewRelEntItensToAttach = em.getReference(relEntItensCollectionNewRelEntItensToAttach.getClass(), relEntItensCollectionNewRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollectionNew.add(relEntItensCollectionNewRelEntItensToAttach);
            }
            relEntItensCollectionNew = attachedRelEntItensCollectionNew;
            utilizadores.setRelEntItensCollection(relEntItensCollectionNew);
            Collection<RelEntItens> attachedRelEntItensCollection1New = new ArrayList<RelEntItens>();
            for (RelEntItens relEntItensCollection1NewRelEntItensToAttach : relEntItensCollection1New) {
                relEntItensCollection1NewRelEntItensToAttach = em.getReference(relEntItensCollection1NewRelEntItensToAttach.getClass(), relEntItensCollection1NewRelEntItensToAttach.getRelEntItensPK());
                attachedRelEntItensCollection1New.add(relEntItensCollection1NewRelEntItensToAttach);
            }
            relEntItensCollection1New = attachedRelEntItensCollection1New;
            utilizadores.setRelEntItensCollection1(relEntItensCollection1New);
            Collection<Itens> attachedItensCollectionNew = new ArrayList<Itens>();
            for (Itens itensCollectionNewItensToAttach : itensCollectionNew) {
                itensCollectionNewItensToAttach = em.getReference(itensCollectionNewItensToAttach.getClass(), itensCollectionNewItensToAttach.getIdItem());
                attachedItensCollectionNew.add(itensCollectionNewItensToAttach);
            }
            itensCollectionNew = attachedItensCollectionNew;
            utilizadores.setItensCollection(itensCollectionNew);
            Collection<Itens> attachedItensCollection1New = new ArrayList<Itens>();
            for (Itens itensCollection1NewItensToAttach : itensCollection1New) {
                itensCollection1NewItensToAttach = em.getReference(itensCollection1NewItensToAttach.getClass(), itensCollection1NewItensToAttach.getIdItem());
                attachedItensCollection1New.add(itensCollection1NewItensToAttach);
            }
            itensCollection1New = attachedItensCollection1New;
            utilizadores.setItensCollection1(itensCollection1New);
            utilizadores = em.merge(utilizadores);
            if (idTipoUserOld != null && !idTipoUserOld.equals(idTipoUserNew)) {
                idTipoUserOld.getUtilizadoresCollection().remove(utilizadores);
                idTipoUserOld = em.merge(idTipoUserOld);
            }
            if (idTipoUserNew != null && !idTipoUserNew.equals(idTipoUserOld)) {
                idTipoUserNew.getUtilizadoresCollection().add(utilizadores);
                idTipoUserNew = em.merge(idTipoUserNew);
            }
            for (ContasBanco contasBancoCollectionOldContasBanco : contasBancoCollectionOld) {
                if (!contasBancoCollectionNew.contains(contasBancoCollectionOldContasBanco)) {
                    contasBancoCollectionOldContasBanco.setUserAlteracao(null);
                    contasBancoCollectionOldContasBanco = em.merge(contasBancoCollectionOldContasBanco);
                }
            }
            for (ContasBanco contasBancoCollectionNewContasBanco : contasBancoCollectionNew) {
                if (!contasBancoCollectionOld.contains(contasBancoCollectionNewContasBanco)) {
                    Utilizadores oldUserAlteracaoOfContasBancoCollectionNewContasBanco = contasBancoCollectionNewContasBanco.getUserAlteracao();
                    contasBancoCollectionNewContasBanco.setUserAlteracao(utilizadores);
                    contasBancoCollectionNewContasBanco = em.merge(contasBancoCollectionNewContasBanco);
                    if (oldUserAlteracaoOfContasBancoCollectionNewContasBanco != null && !oldUserAlteracaoOfContasBancoCollectionNewContasBanco.equals(utilizadores)) {
                        oldUserAlteracaoOfContasBancoCollectionNewContasBanco.getContasBancoCollection().remove(contasBancoCollectionNewContasBanco);
                        oldUserAlteracaoOfContasBancoCollectionNewContasBanco = em.merge(oldUserAlteracaoOfContasBancoCollectionNewContasBanco);
                    }
                }
            }
            for (ContasBanco contasBancoCollection1NewContasBanco : contasBancoCollection1New) {
                if (!contasBancoCollection1Old.contains(contasBancoCollection1NewContasBanco)) {
                    Utilizadores oldUserCriacaoOfContasBancoCollection1NewContasBanco = contasBancoCollection1NewContasBanco.getUserCriacao();
                    contasBancoCollection1NewContasBanco.setUserCriacao(utilizadores);
                    contasBancoCollection1NewContasBanco = em.merge(contasBancoCollection1NewContasBanco);
                    if (oldUserCriacaoOfContasBancoCollection1NewContasBanco != null && !oldUserCriacaoOfContasBancoCollection1NewContasBanco.equals(utilizadores)) {
                        oldUserCriacaoOfContasBancoCollection1NewContasBanco.getContasBancoCollection1().remove(contasBancoCollection1NewContasBanco);
                        oldUserCriacaoOfContasBancoCollection1NewContasBanco = em.merge(oldUserCriacaoOfContasBancoCollection1NewContasBanco);
                    }
                }
            }
            for (Docs docsCollectionOldDocs : docsCollectionOld) {
                if (!docsCollectionNew.contains(docsCollectionOldDocs)) {
                    docsCollectionOldDocs.setUserAlteracao(null);
                    docsCollectionOldDocs = em.merge(docsCollectionOldDocs);
                }
            }
            for (Docs docsCollectionNewDocs : docsCollectionNew) {
                if (!docsCollectionOld.contains(docsCollectionNewDocs)) {
                    Utilizadores oldUserAlteracaoOfDocsCollectionNewDocs = docsCollectionNewDocs.getUserAlteracao();
                    docsCollectionNewDocs.setUserAlteracao(utilizadores);
                    docsCollectionNewDocs = em.merge(docsCollectionNewDocs);
                    if (oldUserAlteracaoOfDocsCollectionNewDocs != null && !oldUserAlteracaoOfDocsCollectionNewDocs.equals(utilizadores)) {
                        oldUserAlteracaoOfDocsCollectionNewDocs.getDocsCollection().remove(docsCollectionNewDocs);
                        oldUserAlteracaoOfDocsCollectionNewDocs = em.merge(oldUserAlteracaoOfDocsCollectionNewDocs);
                    }
                }
            }
            for (Docs docsCollection1NewDocs : docsCollection1New) {
                if (!docsCollection1Old.contains(docsCollection1NewDocs)) {
                    Utilizadores oldUserCriacaoOfDocsCollection1NewDocs = docsCollection1NewDocs.getUserCriacao();
                    docsCollection1NewDocs.setUserCriacao(utilizadores);
                    docsCollection1NewDocs = em.merge(docsCollection1NewDocs);
                    if (oldUserCriacaoOfDocsCollection1NewDocs != null && !oldUserCriacaoOfDocsCollection1NewDocs.equals(utilizadores)) {
                        oldUserCriacaoOfDocsCollection1NewDocs.getDocsCollection1().remove(docsCollection1NewDocs);
                        oldUserCriacaoOfDocsCollection1NewDocs = em.merge(oldUserCriacaoOfDocsCollection1NewDocs);
                    }
                }
            }
            for (TiposDoc tiposDocCollectionOldTiposDoc : tiposDocCollectionOld) {
                if (!tiposDocCollectionNew.contains(tiposDocCollectionOldTiposDoc)) {
                    tiposDocCollectionOldTiposDoc.setUserAlteracao(null);
                    tiposDocCollectionOldTiposDoc = em.merge(tiposDocCollectionOldTiposDoc);
                }
            }
            for (TiposDoc tiposDocCollectionNewTiposDoc : tiposDocCollectionNew) {
                if (!tiposDocCollectionOld.contains(tiposDocCollectionNewTiposDoc)) {
                    Utilizadores oldUserAlteracaoOfTiposDocCollectionNewTiposDoc = tiposDocCollectionNewTiposDoc.getUserAlteracao();
                    tiposDocCollectionNewTiposDoc.setUserAlteracao(utilizadores);
                    tiposDocCollectionNewTiposDoc = em.merge(tiposDocCollectionNewTiposDoc);
                    if (oldUserAlteracaoOfTiposDocCollectionNewTiposDoc != null && !oldUserAlteracaoOfTiposDocCollectionNewTiposDoc.equals(utilizadores)) {
                        oldUserAlteracaoOfTiposDocCollectionNewTiposDoc.getTiposDocCollection().remove(tiposDocCollectionNewTiposDoc);
                        oldUserAlteracaoOfTiposDocCollectionNewTiposDoc = em.merge(oldUserAlteracaoOfTiposDocCollectionNewTiposDoc);
                    }
                }
            }
            for (TiposDoc tiposDocCollection1NewTiposDoc : tiposDocCollection1New) {
                if (!tiposDocCollection1Old.contains(tiposDocCollection1NewTiposDoc)) {
                    Utilizadores oldUserCriacaoOfTiposDocCollection1NewTiposDoc = tiposDocCollection1NewTiposDoc.getUserCriacao();
                    tiposDocCollection1NewTiposDoc.setUserCriacao(utilizadores);
                    tiposDocCollection1NewTiposDoc = em.merge(tiposDocCollection1NewTiposDoc);
                    if (oldUserCriacaoOfTiposDocCollection1NewTiposDoc != null && !oldUserCriacaoOfTiposDocCollection1NewTiposDoc.equals(utilizadores)) {
                        oldUserCriacaoOfTiposDocCollection1NewTiposDoc.getTiposDocCollection1().remove(tiposDocCollection1NewTiposDoc);
                        oldUserCriacaoOfTiposDocCollection1NewTiposDoc = em.merge(oldUserCriacaoOfTiposDocCollection1NewTiposDoc);
                    }
                }
            }
            for (MovStock movStockCollectionNewMovStock : movStockCollectionNew) {
                if (!movStockCollectionOld.contains(movStockCollectionNewMovStock)) {
                    Utilizadores oldUserCriacaoOfMovStockCollectionNewMovStock = movStockCollectionNewMovStock.getUserCriacao();
                    movStockCollectionNewMovStock.setUserCriacao(utilizadores);
                    movStockCollectionNewMovStock = em.merge(movStockCollectionNewMovStock);
                    if (oldUserCriacaoOfMovStockCollectionNewMovStock != null && !oldUserCriacaoOfMovStockCollectionNewMovStock.equals(utilizadores)) {
                        oldUserCriacaoOfMovStockCollectionNewMovStock.getMovStockCollection().remove(movStockCollectionNewMovStock);
                        oldUserCriacaoOfMovStockCollectionNewMovStock = em.merge(oldUserCriacaoOfMovStockCollectionNewMovStock);
                    }
                }
            }
            for (Caixas caixasCollectionOldCaixas : caixasCollectionOld) {
                if (!caixasCollectionNew.contains(caixasCollectionOldCaixas)) {
                    caixasCollectionOldCaixas.setUserAlteracao(null);
                    caixasCollectionOldCaixas = em.merge(caixasCollectionOldCaixas);
                }
            }
            for (Caixas caixasCollectionNewCaixas : caixasCollectionNew) {
                if (!caixasCollectionOld.contains(caixasCollectionNewCaixas)) {
                    Utilizadores oldUserAlteracaoOfCaixasCollectionNewCaixas = caixasCollectionNewCaixas.getUserAlteracao();
                    caixasCollectionNewCaixas.setUserAlteracao(utilizadores);
                    caixasCollectionNewCaixas = em.merge(caixasCollectionNewCaixas);
                    if (oldUserAlteracaoOfCaixasCollectionNewCaixas != null && !oldUserAlteracaoOfCaixasCollectionNewCaixas.equals(utilizadores)) {
                        oldUserAlteracaoOfCaixasCollectionNewCaixas.getCaixasCollection().remove(caixasCollectionNewCaixas);
                        oldUserAlteracaoOfCaixasCollectionNewCaixas = em.merge(oldUserAlteracaoOfCaixasCollectionNewCaixas);
                    }
                }
            }
            for (Caixas caixasCollection1NewCaixas : caixasCollection1New) {
                if (!caixasCollection1Old.contains(caixasCollection1NewCaixas)) {
                    Utilizadores oldUserCriacaoOfCaixasCollection1NewCaixas = caixasCollection1NewCaixas.getUserCriacao();
                    caixasCollection1NewCaixas.setUserCriacao(utilizadores);
                    caixasCollection1NewCaixas = em.merge(caixasCollection1NewCaixas);
                    if (oldUserCriacaoOfCaixasCollection1NewCaixas != null && !oldUserCriacaoOfCaixasCollection1NewCaixas.equals(utilizadores)) {
                        oldUserCriacaoOfCaixasCollection1NewCaixas.getCaixasCollection1().remove(caixasCollection1NewCaixas);
                        oldUserCriacaoOfCaixasCollection1NewCaixas = em.merge(oldUserCriacaoOfCaixasCollection1NewCaixas);
                    }
                }
            }
            for (MovCaixas movCaixasCollectionOldMovCaixas : movCaixasCollectionOld) {
                if (!movCaixasCollectionNew.contains(movCaixasCollectionOldMovCaixas)) {
                    movCaixasCollectionOldMovCaixas.setUserAlteracao(null);
                    movCaixasCollectionOldMovCaixas = em.merge(movCaixasCollectionOldMovCaixas);
                }
            }
            for (MovCaixas movCaixasCollectionNewMovCaixas : movCaixasCollectionNew) {
                if (!movCaixasCollectionOld.contains(movCaixasCollectionNewMovCaixas)) {
                    Utilizadores oldUserAlteracaoOfMovCaixasCollectionNewMovCaixas = movCaixasCollectionNewMovCaixas.getUserAlteracao();
                    movCaixasCollectionNewMovCaixas.setUserAlteracao(utilizadores);
                    movCaixasCollectionNewMovCaixas = em.merge(movCaixasCollectionNewMovCaixas);
                    if (oldUserAlteracaoOfMovCaixasCollectionNewMovCaixas != null && !oldUserAlteracaoOfMovCaixasCollectionNewMovCaixas.equals(utilizadores)) {
                        oldUserAlteracaoOfMovCaixasCollectionNewMovCaixas.getMovCaixasCollection().remove(movCaixasCollectionNewMovCaixas);
                        oldUserAlteracaoOfMovCaixasCollectionNewMovCaixas = em.merge(oldUserAlteracaoOfMovCaixasCollectionNewMovCaixas);
                    }
                }
            }
            for (MovCaixas movCaixasCollection1NewMovCaixas : movCaixasCollection1New) {
                if (!movCaixasCollection1Old.contains(movCaixasCollection1NewMovCaixas)) {
                    Utilizadores oldUserCriacaoOfMovCaixasCollection1NewMovCaixas = movCaixasCollection1NewMovCaixas.getUserCriacao();
                    movCaixasCollection1NewMovCaixas.setUserCriacao(utilizadores);
                    movCaixasCollection1NewMovCaixas = em.merge(movCaixasCollection1NewMovCaixas);
                    if (oldUserCriacaoOfMovCaixasCollection1NewMovCaixas != null && !oldUserCriacaoOfMovCaixasCollection1NewMovCaixas.equals(utilizadores)) {
                        oldUserCriacaoOfMovCaixasCollection1NewMovCaixas.getMovCaixasCollection1().remove(movCaixasCollection1NewMovCaixas);
                        oldUserCriacaoOfMovCaixasCollection1NewMovCaixas = em.merge(oldUserCriacaoOfMovCaixasCollection1NewMovCaixas);
                    }
                }
            }
            for (AnosFiscais anosFiscaisCollectionOldAnosFiscais : anosFiscaisCollectionOld) {
                if (!anosFiscaisCollectionNew.contains(anosFiscaisCollectionOldAnosFiscais)) {
                    anosFiscaisCollectionOldAnosFiscais.setUserAlteracao(null);
                    anosFiscaisCollectionOldAnosFiscais = em.merge(anosFiscaisCollectionOldAnosFiscais);
                }
            }
            for (AnosFiscais anosFiscaisCollectionNewAnosFiscais : anosFiscaisCollectionNew) {
                if (!anosFiscaisCollectionOld.contains(anosFiscaisCollectionNewAnosFiscais)) {
                    Utilizadores oldUserAlteracaoOfAnosFiscaisCollectionNewAnosFiscais = anosFiscaisCollectionNewAnosFiscais.getUserAlteracao();
                    anosFiscaisCollectionNewAnosFiscais.setUserAlteracao(utilizadores);
                    anosFiscaisCollectionNewAnosFiscais = em.merge(anosFiscaisCollectionNewAnosFiscais);
                    if (oldUserAlteracaoOfAnosFiscaisCollectionNewAnosFiscais != null && !oldUserAlteracaoOfAnosFiscaisCollectionNewAnosFiscais.equals(utilizadores)) {
                        oldUserAlteracaoOfAnosFiscaisCollectionNewAnosFiscais.getAnosFiscaisCollection().remove(anosFiscaisCollectionNewAnosFiscais);
                        oldUserAlteracaoOfAnosFiscaisCollectionNewAnosFiscais = em.merge(oldUserAlteracaoOfAnosFiscaisCollectionNewAnosFiscais);
                    }
                }
            }
            for (AnosFiscais anosFiscaisCollection1NewAnosFiscais : anosFiscaisCollection1New) {
                if (!anosFiscaisCollection1Old.contains(anosFiscaisCollection1NewAnosFiscais)) {
                    Utilizadores oldUserCriacaoOfAnosFiscaisCollection1NewAnosFiscais = anosFiscaisCollection1NewAnosFiscais.getUserCriacao();
                    anosFiscaisCollection1NewAnosFiscais.setUserCriacao(utilizadores);
                    anosFiscaisCollection1NewAnosFiscais = em.merge(anosFiscaisCollection1NewAnosFiscais);
                    if (oldUserCriacaoOfAnosFiscaisCollection1NewAnosFiscais != null && !oldUserCriacaoOfAnosFiscaisCollection1NewAnosFiscais.equals(utilizadores)) {
                        oldUserCriacaoOfAnosFiscaisCollection1NewAnosFiscais.getAnosFiscaisCollection1().remove(anosFiscaisCollection1NewAnosFiscais);
                        oldUserCriacaoOfAnosFiscaisCollection1NewAnosFiscais = em.merge(oldUserCriacaoOfAnosFiscaisCollection1NewAnosFiscais);
                    }
                }
            }
            for (Fotos fotosCollectionNewFotos : fotosCollectionNew) {
                if (!fotosCollectionOld.contains(fotosCollectionNewFotos)) {
                    Utilizadores oldUserCriacaoOfFotosCollectionNewFotos = fotosCollectionNewFotos.getUserCriacao();
                    fotosCollectionNewFotos.setUserCriacao(utilizadores);
                    fotosCollectionNewFotos = em.merge(fotosCollectionNewFotos);
                    if (oldUserCriacaoOfFotosCollectionNewFotos != null && !oldUserCriacaoOfFotosCollectionNewFotos.equals(utilizadores)) {
                        oldUserCriacaoOfFotosCollectionNewFotos.getFotosCollection().remove(fotosCollectionNewFotos);
                        oldUserCriacaoOfFotosCollectionNewFotos = em.merge(oldUserCriacaoOfFotosCollectionNewFotos);
                    }
                }
            }
            for (Entidades entidadesCollectionOldEntidades : entidadesCollectionOld) {
                if (!entidadesCollectionNew.contains(entidadesCollectionOldEntidades)) {
                    entidadesCollectionOldEntidades.setUserAlteracao(null);
                    entidadesCollectionOldEntidades = em.merge(entidadesCollectionOldEntidades);
                }
            }
            for (Entidades entidadesCollectionNewEntidades : entidadesCollectionNew) {
                if (!entidadesCollectionOld.contains(entidadesCollectionNewEntidades)) {
                    Utilizadores oldUserAlteracaoOfEntidadesCollectionNewEntidades = entidadesCollectionNewEntidades.getUserAlteracao();
                    entidadesCollectionNewEntidades.setUserAlteracao(utilizadores);
                    entidadesCollectionNewEntidades = em.merge(entidadesCollectionNewEntidades);
                    if (oldUserAlteracaoOfEntidadesCollectionNewEntidades != null && !oldUserAlteracaoOfEntidadesCollectionNewEntidades.equals(utilizadores)) {
                        oldUserAlteracaoOfEntidadesCollectionNewEntidades.getEntidadesCollection().remove(entidadesCollectionNewEntidades);
                        oldUserAlteracaoOfEntidadesCollectionNewEntidades = em.merge(oldUserAlteracaoOfEntidadesCollectionNewEntidades);
                    }
                }
            }
            for (Entidades entidadesCollection1NewEntidades : entidadesCollection1New) {
                if (!entidadesCollection1Old.contains(entidadesCollection1NewEntidades)) {
                    Utilizadores oldUserCriacaoOfEntidadesCollection1NewEntidades = entidadesCollection1NewEntidades.getUserCriacao();
                    entidadesCollection1NewEntidades.setUserCriacao(utilizadores);
                    entidadesCollection1NewEntidades = em.merge(entidadesCollection1NewEntidades);
                    if (oldUserCriacaoOfEntidadesCollection1NewEntidades != null && !oldUserCriacaoOfEntidadesCollection1NewEntidades.equals(utilizadores)) {
                        oldUserCriacaoOfEntidadesCollection1NewEntidades.getEntidadesCollection1().remove(entidadesCollection1NewEntidades);
                        oldUserCriacaoOfEntidadesCollection1NewEntidades = em.merge(oldUserCriacaoOfEntidadesCollection1NewEntidades);
                    }
                }
            }
            for (TaxasDesc taxasDescCollectionOldTaxasDesc : taxasDescCollectionOld) {
                if (!taxasDescCollectionNew.contains(taxasDescCollectionOldTaxasDesc)) {
                    taxasDescCollectionOldTaxasDesc.setUserAlteracao(null);
                    taxasDescCollectionOldTaxasDesc = em.merge(taxasDescCollectionOldTaxasDesc);
                }
            }
            for (TaxasDesc taxasDescCollectionNewTaxasDesc : taxasDescCollectionNew) {
                if (!taxasDescCollectionOld.contains(taxasDescCollectionNewTaxasDesc)) {
                    Utilizadores oldUserAlteracaoOfTaxasDescCollectionNewTaxasDesc = taxasDescCollectionNewTaxasDesc.getUserAlteracao();
                    taxasDescCollectionNewTaxasDesc.setUserAlteracao(utilizadores);
                    taxasDescCollectionNewTaxasDesc = em.merge(taxasDescCollectionNewTaxasDesc);
                    if (oldUserAlteracaoOfTaxasDescCollectionNewTaxasDesc != null && !oldUserAlteracaoOfTaxasDescCollectionNewTaxasDesc.equals(utilizadores)) {
                        oldUserAlteracaoOfTaxasDescCollectionNewTaxasDesc.getTaxasDescCollection().remove(taxasDescCollectionNewTaxasDesc);
                        oldUserAlteracaoOfTaxasDescCollectionNewTaxasDesc = em.merge(oldUserAlteracaoOfTaxasDescCollectionNewTaxasDesc);
                    }
                }
            }
            for (TaxasDesc taxasDescCollection1NewTaxasDesc : taxasDescCollection1New) {
                if (!taxasDescCollection1Old.contains(taxasDescCollection1NewTaxasDesc)) {
                    Utilizadores oldUserCriacaoOfTaxasDescCollection1NewTaxasDesc = taxasDescCollection1NewTaxasDesc.getUserCriacao();
                    taxasDescCollection1NewTaxasDesc.setUserCriacao(utilizadores);
                    taxasDescCollection1NewTaxasDesc = em.merge(taxasDescCollection1NewTaxasDesc);
                    if (oldUserCriacaoOfTaxasDescCollection1NewTaxasDesc != null && !oldUserCriacaoOfTaxasDescCollection1NewTaxasDesc.equals(utilizadores)) {
                        oldUserCriacaoOfTaxasDescCollection1NewTaxasDesc.getTaxasDescCollection1().remove(taxasDescCollection1NewTaxasDesc);
                        oldUserCriacaoOfTaxasDescCollection1NewTaxasDesc = em.merge(oldUserCriacaoOfTaxasDescCollection1NewTaxasDesc);
                    }
                }
            }
            for (RelEntItens relEntItensCollectionOldRelEntItens : relEntItensCollectionOld) {
                if (!relEntItensCollectionNew.contains(relEntItensCollectionOldRelEntItens)) {
                    relEntItensCollectionOldRelEntItens.setUserAlteracao(null);
                    relEntItensCollectionOldRelEntItens = em.merge(relEntItensCollectionOldRelEntItens);
                }
            }
            for (RelEntItens relEntItensCollectionNewRelEntItens : relEntItensCollectionNew) {
                if (!relEntItensCollectionOld.contains(relEntItensCollectionNewRelEntItens)) {
                    Utilizadores oldUserAlteracaoOfRelEntItensCollectionNewRelEntItens = relEntItensCollectionNewRelEntItens.getUserAlteracao();
                    relEntItensCollectionNewRelEntItens.setUserAlteracao(utilizadores);
                    relEntItensCollectionNewRelEntItens = em.merge(relEntItensCollectionNewRelEntItens);
                    if (oldUserAlteracaoOfRelEntItensCollectionNewRelEntItens != null && !oldUserAlteracaoOfRelEntItensCollectionNewRelEntItens.equals(utilizadores)) {
                        oldUserAlteracaoOfRelEntItensCollectionNewRelEntItens.getRelEntItensCollection().remove(relEntItensCollectionNewRelEntItens);
                        oldUserAlteracaoOfRelEntItensCollectionNewRelEntItens = em.merge(oldUserAlteracaoOfRelEntItensCollectionNewRelEntItens);
                    }
                }
            }
            for (RelEntItens relEntItensCollection1NewRelEntItens : relEntItensCollection1New) {
                if (!relEntItensCollection1Old.contains(relEntItensCollection1NewRelEntItens)) {
                    Utilizadores oldUserCriacaoOfRelEntItensCollection1NewRelEntItens = relEntItensCollection1NewRelEntItens.getUserCriacao();
                    relEntItensCollection1NewRelEntItens.setUserCriacao(utilizadores);
                    relEntItensCollection1NewRelEntItens = em.merge(relEntItensCollection1NewRelEntItens);
                    if (oldUserCriacaoOfRelEntItensCollection1NewRelEntItens != null && !oldUserCriacaoOfRelEntItensCollection1NewRelEntItens.equals(utilizadores)) {
                        oldUserCriacaoOfRelEntItensCollection1NewRelEntItens.getRelEntItensCollection1().remove(relEntItensCollection1NewRelEntItens);
                        oldUserCriacaoOfRelEntItensCollection1NewRelEntItens = em.merge(oldUserCriacaoOfRelEntItensCollection1NewRelEntItens);
                    }
                }
            }
            for (Itens itensCollectionOldItens : itensCollectionOld) {
                if (!itensCollectionNew.contains(itensCollectionOldItens)) {
                    itensCollectionOldItens.setUserAlteracao(null);
                    itensCollectionOldItens = em.merge(itensCollectionOldItens);
                }
            }
            for (Itens itensCollectionNewItens : itensCollectionNew) {
                if (!itensCollectionOld.contains(itensCollectionNewItens)) {
                    Utilizadores oldUserAlteracaoOfItensCollectionNewItens = itensCollectionNewItens.getUserAlteracao();
                    itensCollectionNewItens.setUserAlteracao(utilizadores);
                    itensCollectionNewItens = em.merge(itensCollectionNewItens);
                    if (oldUserAlteracaoOfItensCollectionNewItens != null && !oldUserAlteracaoOfItensCollectionNewItens.equals(utilizadores)) {
                        oldUserAlteracaoOfItensCollectionNewItens.getItensCollection().remove(itensCollectionNewItens);
                        oldUserAlteracaoOfItensCollectionNewItens = em.merge(oldUserAlteracaoOfItensCollectionNewItens);
                    }
                }
            }
            for (Itens itensCollection1NewItens : itensCollection1New) {
                if (!itensCollection1Old.contains(itensCollection1NewItens)) {
                    Utilizadores oldUserCriacaoOfItensCollection1NewItens = itensCollection1NewItens.getUserCriacao();
                    itensCollection1NewItens.setUserCriacao(utilizadores);
                    itensCollection1NewItens = em.merge(itensCollection1NewItens);
                    if (oldUserCriacaoOfItensCollection1NewItens != null && !oldUserCriacaoOfItensCollection1NewItens.equals(utilizadores)) {
                        oldUserCriacaoOfItensCollection1NewItens.getItensCollection1().remove(itensCollection1NewItens);
                        oldUserCriacaoOfItensCollection1NewItens = em.merge(oldUserCriacaoOfItensCollection1NewItens);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = utilizadores.getIdUtilizador();
                if (findUtilizadores(id) == null) {
                    throw new NonexistentEntityException("The utilizadores with id " + id + " no longer exists.");
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
            Utilizadores utilizadores;
            try {
                utilizadores = em.getReference(Utilizadores.class, id);
                utilizadores.getIdUtilizador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utilizadores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ContasBanco> contasBancoCollection1OrphanCheck = utilizadores.getContasBancoCollection1();
            for (ContasBanco contasBancoCollection1OrphanCheckContasBanco : contasBancoCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the ContasBanco " + contasBancoCollection1OrphanCheckContasBanco + " in its contasBancoCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<Docs> docsCollection1OrphanCheck = utilizadores.getDocsCollection1();
            for (Docs docsCollection1OrphanCheckDocs : docsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the Docs " + docsCollection1OrphanCheckDocs + " in its docsCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<TiposDoc> tiposDocCollection1OrphanCheck = utilizadores.getTiposDocCollection1();
            for (TiposDoc tiposDocCollection1OrphanCheckTiposDoc : tiposDocCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the TiposDoc " + tiposDocCollection1OrphanCheckTiposDoc + " in its tiposDocCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<MovStock> movStockCollectionOrphanCheck = utilizadores.getMovStockCollection();
            for (MovStock movStockCollectionOrphanCheckMovStock : movStockCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the MovStock " + movStockCollectionOrphanCheckMovStock + " in its movStockCollection field has a non-nullable userCriacao field.");
            }
            Collection<Caixas> caixasCollection1OrphanCheck = utilizadores.getCaixasCollection1();
            for (Caixas caixasCollection1OrphanCheckCaixas : caixasCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the Caixas " + caixasCollection1OrphanCheckCaixas + " in its caixasCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<MovCaixas> movCaixasCollection1OrphanCheck = utilizadores.getMovCaixasCollection1();
            for (MovCaixas movCaixasCollection1OrphanCheckMovCaixas : movCaixasCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the MovCaixas " + movCaixasCollection1OrphanCheckMovCaixas + " in its movCaixasCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<AnosFiscais> anosFiscaisCollection1OrphanCheck = utilizadores.getAnosFiscaisCollection1();
            for (AnosFiscais anosFiscaisCollection1OrphanCheckAnosFiscais : anosFiscaisCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the AnosFiscais " + anosFiscaisCollection1OrphanCheckAnosFiscais + " in its anosFiscaisCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<Fotos> fotosCollectionOrphanCheck = utilizadores.getFotosCollection();
            for (Fotos fotosCollectionOrphanCheckFotos : fotosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the Fotos " + fotosCollectionOrphanCheckFotos + " in its fotosCollection field has a non-nullable userCriacao field.");
            }
            Collection<Entidades> entidadesCollection1OrphanCheck = utilizadores.getEntidadesCollection1();
            for (Entidades entidadesCollection1OrphanCheckEntidades : entidadesCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the Entidades " + entidadesCollection1OrphanCheckEntidades + " in its entidadesCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<TaxasDesc> taxasDescCollection1OrphanCheck = utilizadores.getTaxasDescCollection1();
            for (TaxasDesc taxasDescCollection1OrphanCheckTaxasDesc : taxasDescCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the TaxasDesc " + taxasDescCollection1OrphanCheckTaxasDesc + " in its taxasDescCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<RelEntItens> relEntItensCollection1OrphanCheck = utilizadores.getRelEntItensCollection1();
            for (RelEntItens relEntItensCollection1OrphanCheckRelEntItens : relEntItensCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the RelEntItens " + relEntItensCollection1OrphanCheckRelEntItens + " in its relEntItensCollection1 field has a non-nullable userCriacao field.");
            }
            Collection<Itens> itensCollection1OrphanCheck = utilizadores.getItensCollection1();
            for (Itens itensCollection1OrphanCheckItens : itensCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilizadores (" + utilizadores + ") cannot be destroyed since the Itens " + itensCollection1OrphanCheckItens + " in its itensCollection1 field has a non-nullable userCriacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TiposUser idTipoUser = utilizadores.getIdTipoUser();
            if (idTipoUser != null) {
                idTipoUser.getUtilizadoresCollection().remove(utilizadores);
                idTipoUser = em.merge(idTipoUser);
            }
            Collection<ContasBanco> contasBancoCollection = utilizadores.getContasBancoCollection();
            for (ContasBanco contasBancoCollectionContasBanco : contasBancoCollection) {
                contasBancoCollectionContasBanco.setUserAlteracao(null);
                contasBancoCollectionContasBanco = em.merge(contasBancoCollectionContasBanco);
            }
            Collection<Docs> docsCollection = utilizadores.getDocsCollection();
            for (Docs docsCollectionDocs : docsCollection) {
                docsCollectionDocs.setUserAlteracao(null);
                docsCollectionDocs = em.merge(docsCollectionDocs);
            }
            Collection<TiposDoc> tiposDocCollection = utilizadores.getTiposDocCollection();
            for (TiposDoc tiposDocCollectionTiposDoc : tiposDocCollection) {
                tiposDocCollectionTiposDoc.setUserAlteracao(null);
                tiposDocCollectionTiposDoc = em.merge(tiposDocCollectionTiposDoc);
            }
            Collection<Caixas> caixasCollection = utilizadores.getCaixasCollection();
            for (Caixas caixasCollectionCaixas : caixasCollection) {
                caixasCollectionCaixas.setUserAlteracao(null);
                caixasCollectionCaixas = em.merge(caixasCollectionCaixas);
            }
            Collection<MovCaixas> movCaixasCollection = utilizadores.getMovCaixasCollection();
            for (MovCaixas movCaixasCollectionMovCaixas : movCaixasCollection) {
                movCaixasCollectionMovCaixas.setUserAlteracao(null);
                movCaixasCollectionMovCaixas = em.merge(movCaixasCollectionMovCaixas);
            }
            Collection<AnosFiscais> anosFiscaisCollection = utilizadores.getAnosFiscaisCollection();
            for (AnosFiscais anosFiscaisCollectionAnosFiscais : anosFiscaisCollection) {
                anosFiscaisCollectionAnosFiscais.setUserAlteracao(null);
                anosFiscaisCollectionAnosFiscais = em.merge(anosFiscaisCollectionAnosFiscais);
            }
            Collection<Entidades> entidadesCollection = utilizadores.getEntidadesCollection();
            for (Entidades entidadesCollectionEntidades : entidadesCollection) {
                entidadesCollectionEntidades.setUserAlteracao(null);
                entidadesCollectionEntidades = em.merge(entidadesCollectionEntidades);
            }
            Collection<TaxasDesc> taxasDescCollection = utilizadores.getTaxasDescCollection();
            for (TaxasDesc taxasDescCollectionTaxasDesc : taxasDescCollection) {
                taxasDescCollectionTaxasDesc.setUserAlteracao(null);
                taxasDescCollectionTaxasDesc = em.merge(taxasDescCollectionTaxasDesc);
            }
            Collection<RelEntItens> relEntItensCollection = utilizadores.getRelEntItensCollection();
            for (RelEntItens relEntItensCollectionRelEntItens : relEntItensCollection) {
                relEntItensCollectionRelEntItens.setUserAlteracao(null);
                relEntItensCollectionRelEntItens = em.merge(relEntItensCollectionRelEntItens);
            }
            Collection<Itens> itensCollection = utilizadores.getItensCollection();
            for (Itens itensCollectionItens : itensCollection) {
                itensCollectionItens.setUserAlteracao(null);
                itensCollectionItens = em.merge(itensCollectionItens);
            }
            em.remove(utilizadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Utilizadores> findUtilizadoresEntities() {
        return findUtilizadoresEntities(true, -1, -1);
    }

    public List<Utilizadores> findUtilizadoresEntities(int maxResults, int firstResult) {
        return findUtilizadoresEntities(false, maxResults, firstResult);
    }

    private List<Utilizadores> findUtilizadoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Utilizadores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Utilizadores findUtilizadores(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Utilizadores.class, id);
        } finally {
            em.close();
        }
    }

    public int getUtilizadoresCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Utilizadores as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
