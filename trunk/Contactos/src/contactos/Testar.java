/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contactos;

import entidades.Teste;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author andre
 */
public class Testar {

    public static void main(String args[]) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Contactos");
        EntityManager em = emf.createEntityManager();
        Teste t = new Teste();
        em.getTransaction().begin();
        t.setId(17L);
        t.setNome("andre");
        t.setTipo("A");
        t.setLocal("B");
        em.persist(t);
        try {
        em.flush();
        } catch(ConstraintViolationException e) {
            System.out.println("Campo dublicado!");
        }
        em.getTransaction().commit();
    }
}
