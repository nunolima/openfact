/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.Areas;
import entidades.Autores;
import entidades.Editoras;
import entidades.Livros;
import entidades.controller.AreasJpaController;
import entidades.controller.AutoresJpaController;
import entidades.controller.EditorasJpaController;
import entidades.controller.LivrosJpaController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
////            1º AUTOR
//            Autores autor1 = new Autores();
//            autor1.setId(2L);
//            autor1.setNome("Autor1");
//            new AutoresJpaController().create(autor1);
//            //2º AUTOR
//            Autores autor2 = new Autores();
//            autor2.setId(3L);
//            autor2.setNome("Autor2");
//            new AutoresJpaController().create(autor2);
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
            livro.setId(114L);
            livro.setTitulo("E tudo o JAVA levou 5");
            livro.setIsbn("1238");
            livro.setAquisicaoData(new Date());
            livro.setAquisicaoValor(20);
            livro.setEditoraId(new EditorasJpaController().findEditoras(new Long(1)));
            livro.setAreaId(new AreasJpaController().findAreas(new Long(1)));


            ArrayList<Autores> col = new ArrayList<Autores>();
            col.add(new AutoresJpaController().findAutores(new Long(2)));
            col.add(new AutoresJpaController().findAutores(new Long(3)));
//            col.add(new AutoresJpaController().findAutores(new Long(3)));//Testar duplicação registo
            livro.setAutoresCollection(col);

            new LivrosJpaController().create(livro);

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
