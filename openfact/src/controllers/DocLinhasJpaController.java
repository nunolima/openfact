/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entidades.DocLinhasPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entidades.DocLinhas;
import entidades.Docs;
import entidades.Itens;
import entidades.Ivas;
import entidades.UnidMedida;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author User
 */
public class DocLinhasJpaController {

    public DocLinhasJpaController() {
        emf = Persistence.createEntityManagerFactory("openfactPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DocLinhas docLinhas) throws PreexistingEntityException, Exception {
        if (docLinhas.getDocLinhasPK() == null) {
            docLinhas.setDocLinhasPK(new DocLinhasPK());
        }
        if (docLinhas.getDocLinhasCollection() == null) {
            docLinhas.setDocLinhasCollection(new ArrayList<DocLinhas>());
        }
        docLinhas.getDocLinhasPK().setIdDoc(docLinhas.getDocs().getIdDoc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // TRATAR este docLinhas2
            DocLinhas docLinhas2 = docLinhas.getDocLinhas();
            if (docLinhas2 != null) {
                docLinhas2 = em.getReference(docLinhas.getClass(), docLinhas2.getDocLinhasPK());
                docLinhas.setDocLinhas(docLinhas);
            }
            Docs docs = docLinhas.getDocs();
            if (docs != null) {
                docs = em.getReference(docs.getClass(), docs.getIdDoc());
                docLinhas.setDocs(docs);
            }
            Itens idItem = docLinhas.getIdItem();
            if (idItem != null) {
                idItem = em.getReference(idItem.getClass(), idItem.getIdItem());
                docLinhas.setIdItem(idItem);
            }
            Ivas ivas = docLinhas.getIvas();
            if (ivas != null) {
                ivas = em.getReference(ivas.getClass(), ivas.getIvasPK());
                docLinhas.setIvas(ivas);
            }
            UnidMedida unidMedida = docLinhas.getUnidMedida();
            if (unidMedida != null) {
                unidMedida = em.getReference(unidMedida.getClass(), unidMedida.getUnidMedida());
                docLinhas.setUnidMedida(unidMedida);
            }
            Collection<DocLinhas> attachedDocLinhasCollection = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionDocLinhasToAttach : docLinhas.getDocLinhasCollection()) {
                docLinhasCollectionDocLinhasToAttach = em.getReference(docLinhasCollectionDocLinhasToAttach.getClass(), docLinhasCollectionDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollection.add(docLinhasCollectionDocLinhasToAttach);
            }
            docLinhas.setDocLinhasCollection(attachedDocLinhasCollection);
            em.persist(docLinhas);
            if (docLinhas != null) {
                docLinhas.getDocLinhasCollection().add(docLinhas);
                docLinhas = em.merge(docLinhas);
            }
            if (docs != null) {
                docs.getDocLinhasCollection().add(docLinhas);
                docs = em.merge(docs);
            }
            if (idItem != null) {
                idItem.getDocLinhasCollection().add(docLinhas);
                idItem = em.merge(idItem);
            }
            if (ivas != null) {
                ivas.getDocLinhasCollection().add(docLinhas);
                ivas = em.merge(ivas);
            }
            if (unidMedida != null) {
                unidMedida.getDocLinhasCollection().add(docLinhas);
                unidMedida = em.merge(unidMedida);
            }
            for (DocLinhas docLinhasCollectionDocLinhas : docLinhas.getDocLinhasCollection()) {
                DocLinhas oldDocLinhasOfDocLinhasCollectionDocLinhas = docLinhasCollectionDocLinhas.getDocLinhas();
                docLinhasCollectionDocLinhas.setDocLinhas(docLinhas);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
                if (oldDocLinhasOfDocLinhasCollectionDocLinhas != null) {
                    oldDocLinhasOfDocLinhasCollectionDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionDocLinhas);
                    oldDocLinhasOfDocLinhasCollectionDocLinhas = em.merge(oldDocLinhasOfDocLinhasCollectionDocLinhas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocLinhas(docLinhas.getDocLinhasPK()) != null) {
                throw new PreexistingEntityException("DocLinhas " + docLinhas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DocLinhas docLinhas) throws NonexistentEntityException, Exception {
        docLinhas.getDocLinhasPK().setIdDoc(docLinhas.getDocs().getIdDoc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocLinhas persistentDocLinhas = em.find(DocLinhas.class, docLinhas.getDocLinhasPK());
            DocLinhas docLinhasOld = persistentDocLinhas.getDocLinhas();
            DocLinhas docLinhasNew = docLinhas.getDocLinhas();
            Docs docsOld = persistentDocLinhas.getDocs();
            Docs docsNew = docLinhas.getDocs();
            Itens idItemOld = persistentDocLinhas.getIdItem();
            Itens idItemNew = docLinhas.getIdItem();
            Ivas ivasOld = persistentDocLinhas.getIvas();
            Ivas ivasNew = docLinhas.getIvas();
            UnidMedida unidMedidaOld = persistentDocLinhas.getUnidMedida();
            UnidMedida unidMedidaNew = docLinhas.getUnidMedida();
            Collection<DocLinhas> docLinhasCollectionOld = persistentDocLinhas.getDocLinhasCollection();
            Collection<DocLinhas> docLinhasCollectionNew = docLinhas.getDocLinhasCollection();
            if (docLinhasNew != null) {
                docLinhasNew = em.getReference(docLinhasNew.getClass(), docLinhasNew.getDocLinhasPK());
                docLinhas.setDocLinhas(docLinhasNew);
            }
            if (docsNew != null) {
                docsNew = em.getReference(docsNew.getClass(), docsNew.getIdDoc());
                docLinhas.setDocs(docsNew);
            }
            if (idItemNew != null) {
                idItemNew = em.getReference(idItemNew.getClass(), idItemNew.getIdItem());
                docLinhas.setIdItem(idItemNew);
            }
            if (ivasNew != null) {
                ivasNew = em.getReference(ivasNew.getClass(), ivasNew.getIvasPK());
                docLinhas.setIvas(ivasNew);
            }
            if (unidMedidaNew != null) {
                unidMedidaNew = em.getReference(unidMedidaNew.getClass(), unidMedidaNew.getUnidMedida());
                docLinhas.setUnidMedida(unidMedidaNew);
            }
            Collection<DocLinhas> attachedDocLinhasCollectionNew = new ArrayList<DocLinhas>();
            for (DocLinhas docLinhasCollectionNewDocLinhasToAttach : docLinhasCollectionNew) {
                docLinhasCollectionNewDocLinhasToAttach = em.getReference(docLinhasCollectionNewDocLinhasToAttach.getClass(), docLinhasCollectionNewDocLinhasToAttach.getDocLinhasPK());
                attachedDocLinhasCollectionNew.add(docLinhasCollectionNewDocLinhasToAttach);
            }
            docLinhasCollectionNew = attachedDocLinhasCollectionNew;
            docLinhas.setDocLinhasCollection(docLinhasCollectionNew);
            docLinhas = em.merge(docLinhas);
            if (docLinhasOld != null && !docLinhasOld.equals(docLinhasNew)) {
                docLinhasOld.getDocLinhasCollection().remove(docLinhas);
                docLinhasOld = em.merge(docLinhasOld);
            }
            if (docLinhasNew != null && !docLinhasNew.equals(docLinhasOld)) {
                docLinhasNew.getDocLinhasCollection().add(docLinhas);
                docLinhasNew = em.merge(docLinhasNew);
            }
            if (docsOld != null && !docsOld.equals(docsNew)) {
                docsOld.getDocLinhasCollection().remove(docLinhas);
                docsOld = em.merge(docsOld);
            }
            if (docsNew != null && !docsNew.equals(docsOld)) {
                docsNew.getDocLinhasCollection().add(docLinhas);
                docsNew = em.merge(docsNew);
            }
            if (idItemOld != null && !idItemOld.equals(idItemNew)) {
                idItemOld.getDocLinhasCollection().remove(docLinhas);
                idItemOld = em.merge(idItemOld);
            }
            if (idItemNew != null && !idItemNew.equals(idItemOld)) {
                idItemNew.getDocLinhasCollection().add(docLinhas);
                idItemNew = em.merge(idItemNew);
            }
            if (ivasOld != null && !ivasOld.equals(ivasNew)) {
                ivasOld.getDocLinhasCollection().remove(docLinhas);
                ivasOld = em.merge(ivasOld);
            }
            if (ivasNew != null && !ivasNew.equals(ivasOld)) {
                ivasNew.getDocLinhasCollection().add(docLinhas);
                ivasNew = em.merge(ivasNew);
            }
            if (unidMedidaOld != null && !unidMedidaOld.equals(unidMedidaNew)) {
                unidMedidaOld.getDocLinhasCollection().remove(docLinhas);
                unidMedidaOld = em.merge(unidMedidaOld);
            }
            if (unidMedidaNew != null && !unidMedidaNew.equals(unidMedidaOld)) {
                unidMedidaNew.getDocLinhasCollection().add(docLinhas);
                unidMedidaNew = em.merge(unidMedidaNew);
            }
            for (DocLinhas docLinhasCollectionOldDocLinhas : docLinhasCollectionOld) {
                if (!docLinhasCollectionNew.contains(docLinhasCollectionOldDocLinhas)) {
                    docLinhasCollectionOldDocLinhas.setDocLinhas(null);
                    docLinhasCollectionOldDocLinhas = em.merge(docLinhasCollectionOldDocLinhas);
                }
            }
            for (DocLinhas docLinhasCollectionNewDocLinhas : docLinhasCollectionNew) {
                if (!docLinhasCollectionOld.contains(docLinhasCollectionNewDocLinhas)) {
                    DocLinhas oldDocLinhasOfDocLinhasCollectionNewDocLinhas = docLinhasCollectionNewDocLinhas.getDocLinhas();
                    docLinhasCollectionNewDocLinhas.setDocLinhas(docLinhas);
                    docLinhasCollectionNewDocLinhas = em.merge(docLinhasCollectionNewDocLinhas);
                    if (oldDocLinhasOfDocLinhasCollectionNewDocLinhas != null && !oldDocLinhasOfDocLinhasCollectionNewDocLinhas.equals(docLinhas)) {
                        oldDocLinhasOfDocLinhasCollectionNewDocLinhas.getDocLinhasCollection().remove(docLinhasCollectionNewDocLinhas);
                        oldDocLinhasOfDocLinhasCollectionNewDocLinhas = em.merge(oldDocLinhasOfDocLinhasCollectionNewDocLinhas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DocLinhasPK id = docLinhas.getDocLinhasPK();
                if (findDocLinhas(id) == null) {
                    throw new NonexistentEntityException("The docLinhas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DocLinhasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocLinhas docLinhas;
            try {
                docLinhas = em.getReference(DocLinhas.class, id);
                docLinhas.getDocLinhasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docLinhas with id " + id + " no longer exists.", enfe);
            }
            // TRATAR ESTE docLinhas2
            DocLinhas docLinhas2 = docLinhas.getDocLinhas();
            if (docLinhas != null) {
                docLinhas.getDocLinhasCollection().remove(docLinhas);
                docLinhas = em.merge(docLinhas);
            }
            Docs docs = docLinhas.getDocs();
            if (docs != null) {
                docs.getDocLinhasCollection().remove(docLinhas);
                docs = em.merge(docs);
            }
            Itens idItem = docLinhas.getIdItem();
            if (idItem != null) {
                idItem.getDocLinhasCollection().remove(docLinhas);
                idItem = em.merge(idItem);
            }
            Ivas ivas = docLinhas.getIvas();
            if (ivas != null) {
                ivas.getDocLinhasCollection().remove(docLinhas);
                ivas = em.merge(ivas);
            }
            UnidMedida unidMedida = docLinhas.getUnidMedida();
            if (unidMedida != null) {
                unidMedida.getDocLinhasCollection().remove(docLinhas);
                unidMedida = em.merge(unidMedida);
            }
            Collection<DocLinhas> docLinhasCollection = docLinhas.getDocLinhasCollection();
            for (DocLinhas docLinhasCollectionDocLinhas : docLinhasCollection) {
                docLinhasCollectionDocLinhas.setDocLinhas(null);
                docLinhasCollectionDocLinhas = em.merge(docLinhasCollectionDocLinhas);
            }
            em.remove(docLinhas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DocLinhas> findDocLinhasEntities() {
        return findDocLinhasEntities(true, -1, -1);
    }

    public List<DocLinhas> findDocLinhasEntities(int maxResults, int firstResult) {
        return findDocLinhasEntities(false, maxResults, firstResult);
    }

    private List<DocLinhas> findDocLinhasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DocLinhas as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DocLinhas findDocLinhas(DocLinhasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DocLinhas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocLinhasCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from DocLinhas as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
