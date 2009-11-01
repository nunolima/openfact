/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package contactos;

import entidades.Pessoa;
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new JFrameContactos().setVisible(true);
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("Contactos");
                EntityManager em = emf.createEntityManager();
                for (Pessoa pessoa : ((List<Pessoa>)em.createNamedQuery("Pessoas.findAll").getResultList())) {
                    System.out.println("Pessoa: " + pessoa);
                }

            }
        });
    }

}
