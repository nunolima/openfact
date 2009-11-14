/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openfact;

import entidades.Entidades;
import entidades.TipoEntidades;
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("openfactPU");
        EntityManager em = emf.createEntityManager();

        TipoEntidades t1 = em.find(TipoEntidades.class, 1L);
        System.out.println("TipoEntidades: " + t1);

        Entidades e1 = em.find(Entidades.class, 1L);
        System.out.println("Entidades: " + e1);

        em.close();
        emf.close();
        
    }

    private static void carregaExemploBDados(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("openfactPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        TipoEntidades t1 = new TipoEntidades();
        t1.setDescricao("Clientes");

        em.persist(t1);

        Entidades e1 = new Entidades();
        e1.setNome("Nuno");
        e1.setTipoEntidade(t1);

        em.persist(e1);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
