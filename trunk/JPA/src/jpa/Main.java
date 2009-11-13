/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.Autores;
import entidades.Livros;
import entidades.controller.AreasJpaController;
import entidades.controller.AutoresJpaController;
import entidades.controller.EditorasJpaController;
import entidades.controller.LivrosJpaController;
import java.util.ArrayList;
import java.util.Date;

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
//            Autores autor1 = new Autores();
//            autor1.setId(2l);
//            autor1.setNome("Autor1");
//            new AutoresJpaController().create(autor1);
//            //2º AUTOR
//            Autores autor2 = new Autores();
//            autor2.setId(3l);
//            autor2.setNome("Autor2");
//            new AutoresJpaController().create(autor2);
//
//            // EDITORA
//            Editoras editora1 = new Editoras();
//            editora1.setId(1l);
//            editora1.setNome("Editora 1");
//            new EditorasJpaController().create(editora1);
//
//            // AREA
//            Areas area1 = new Areas();
//            area1.setId(1l);
//            area1.setDsc("Programacao Java");
//            new AreasJpaController().create(area1);

            // 1º LIVRO
            Livros livro = new Livros();
            livro.setId(23l);
            livro.setTitulo("E tudo o JAVA levou");
            livro.setIsbn("1234");
            livro.setAquisicaoData(new Date());
            livro.setAquisicaoValor(10);
            livro.setEditoraId(new EditorasJpaController().findEditoras(new Long(1)));
            livro.setAreaId(new AreasJpaController().findAreas(new Long(1)));

            
            ArrayList<Autores> col = new ArrayList<Autores>();
            col.add(new AutoresJpaController().findAutores(new Long(21)));
            col.add(new AutoresJpaController().findAutores(new Long(31)));
            livro.setAutoresCollection(col);

            new LivrosJpaController().create(livro);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
