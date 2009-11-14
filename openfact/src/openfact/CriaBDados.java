/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openfact;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class CriaBDados {
    public static void main(String[] args) {
        Map<String,String> hibernateMap = new HashMap<String,String>();
        hibernateMap.put("hibernate.hbm2ddl.auto", "create-drop");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("openfactPU", hibernateMap);
        EntityManager em = emf.createEntityManager();

        // Drop and Create Database

        em.close();
        emf.close();

    }
}
