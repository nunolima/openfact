/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openfact;

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
        // TODO code application logic here

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("openfactPU");
        EntityManager em = emf.createEntityManager();

        // Drop and Create Database

        em.close();
        emf.close();
    }

}
