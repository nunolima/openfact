/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package openfact;

import entidades.Entidades;
import entidades.TipoEntidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        carregaExemploBDados();

        listaTodasEntidades();

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("openfactPU");
//        EntityManager em = emf.createEntityManager();
//
//        TipoEntidades t1 = em.find(TipoEntidades.class, 1L);
//        System.out.println("TipoEntidades: " + t1);
//
//        Entidades e1 = em.find(Entidades.class, 1L);
//        System.out.println("Entidades: " + e1);
//
//        em.close();
//        emf.close();

    }

    private static void carregaExemploBDados() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("openfactPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        TipoEntidades t1 = null;

        try {
            t1 = (TipoEntidades) em.createQuery("select t from TipoEntidades t where t.descricao = 'Fornecedores'").getSingleResult();
        } catch (Exception ex) {
            t1 = new TipoEntidades();
            t1.setDescricao("Fornecedores");
            em.persist(t1);
            System.out.println("TipoEntidade 'Fornecedores' não foi encontrado");
        }

        Entidades e1 = new Entidades();
        e1.setNome("Ana");
        e1.setTipoEntidade(t1);

        Entidades e2 = new Entidades();
        e2.setNome("André");
        e2.setTipoEntidade(t1);

        Entidades e3 = new Entidades();
        e3.setNome("Pedro");
        e3.setTipoEntidade(t1);

        em.persist(e1);
        em.persist(e2);
        em.persist(e3);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    private static void listaTodasEntidades() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("openfactPU");
        EntityManager em = emf.createEntityManager();

        for (Entidades e : (List<Entidades>) em.createNamedQuery("findAllEntidades").getResultList()) {
            System.out.println("Entidade: " + e);
        }

        em.close();
        emf.close();
    }
}
