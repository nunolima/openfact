/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.Autores;
import entidades.Livros;
import entidades.LivrosAutores;
import entidades.controller.AreasJpaController;
import entidades.controller.AutoresJpaController;
import entidades.controller.EditorasJpaController;
import entidades.controller.LivrosJpaController;
import java.util.ArrayList;
import java.util.Date;
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

        org.apache.log4j.BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);

        try {
            // teste do commit svn
            //1º AUTOR
            Autores autor1 = new Autores();
            autor1.setId(11L);
            autor1.setNome("Autor11");
            new AutoresJpaController().create(autor1);
            //2º AUTOR
            Autores autor2 = new Autores();
            autor2.setId(22L);
            autor2.setNome("Autor22");
            new AutoresJpaController().create(autor2);
//
//            // EDITORA
//            Editoras editora1 = new Editoras();
//            editora1.setId(1L);
//            editora1.setNome("Editora 1");
//            new EditorasJpaController().create(editora1);
//
//            // AREA
//            Areas area1 = new Areas();
//            area1.setId(1L);
//            area1.setDsc("Programacao Java");
//            new AreasJpaController().create(area1);

            // 1º LIVRO
            Livros livro = new Livros();
            livro.setId(555L);
            livro.setTitulo("E tudo o JAVA levou 555");
            livro.setIsbn("15551");
            livro.setAquisicaoData(new Date());
            livro.setAquisicaoValor(20);
            livro.setEditoraId(new EditorasJpaController().findEditoras(new Long(1)));
            livro.setAreaId(new AreasJpaController().findAreas(new Long(1)));

            Autores a1 = new AutoresJpaController().findAutores(new Long(11));
            if (a1 != null) {
                livro.addAutor(a1, true);
            }
            Autores a2 = new AutoresJpaController().findAutores(new Long(22));
            if (a2 != null) {
                livro.addAutor(a2, true);
            }
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

//            em.persist(livro);
            em.merge(livro);
//            em.merge(a2);

            em.getTransaction().commit();

            em.clear();
            em.close();
            emf.close();




//            col.add(new AutoresJpaController().findAutores(new Long(3)));//Testar duplicação registo

            //new LivrosJpaController().create(livro);

//            // 2º LIVRO
//            Livros livro2 = new Livros();
//            livro2.setId(10L);
//            livro2.setTitulo("Mais um livro de JAVA");
//            livro2.setIsbn("2233");
//            livro2.setAquisicaoData(new Date());
//            livro2.setAquisicaoValor(15);
//            livro2.setEditoraId(new EditorasJpaController().findEditoras(new Long(1)));
//            livro2.setAreaId(new AreasJpaController().findAreas(new Long(1)));
//            new LivrosJpaController().create(livro2);
//
//            //3º AUTOR
//            Autores autor3 = new Autores();
//            autor3.setId(1L);
//            autor3.setNome("Autor3");
//            Set<Livros> cSet = new HashSet<Livros>();
//            //cSet.add(new LivrosJpaController().findLivros(new Long(10)));
//            cSet.add(livro2);
//            autor3.setLivrosCollection(cSet);
//            new AutoresJpaController().create(autor3);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
