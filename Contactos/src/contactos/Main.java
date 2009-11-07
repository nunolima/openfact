/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contactos;

import entidades.Pessoa;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Properties bProperties = null;
    private String dbName;
    private Properties dbProperties;

    public void ContactosDao(String contactosNome) {
        this.dbName = contactosNome;
        setDBSystemDir();
        dbProperties = loadDBProperties();
        String driverName = dbProperties.getProperty("derby.driver");
        loadDatabaseDriver(driverName);
    }

    private void setDBSystemDir() {
        // Decide on the db system directory: <userhome>/.addressbook/
        String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + "/.contactos";

        // Set the db system directory.
        System.setProperty("derby.system.home", systemDir);
    }

    private Properties loadDBProperties() {
        InputStream dbPropInputStream = null;
        dbPropInputStream = Main.class.getResourceAsStream("Configuration.properties");
        dbProperties = new Properties();
        try {
            dbProperties.load(dbPropInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dbProperties;
    }

    private void loadDatabaseDriver(String driverName) {
        // Load the Java DB driver.
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
//        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//            new Main().ContactosDao("contactos");
//
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                //new JFrameContactos().setVisible(true);
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("Contactos");
                EntityManager em = emf.createEntityManager();
                for (Pessoa pessoa : ((List<Pessoa>) em.createNamedQuery("Pessoa.findAll").getResultList())) {
                    System.out.println("Pessoa: " + pessoa);
                }

            }
        });
    }
}
