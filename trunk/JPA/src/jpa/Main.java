/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.Livros;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PedrodeSousa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
                EntityManager em = emf.createEntityManager();
                for (Livros livro : ((List<Livros>) em.createNamedQuery("Livro.findAll").getResultList())) {
                    System.out.println("Livro: " + livro);
                }
//            }
//        });
    }
}
