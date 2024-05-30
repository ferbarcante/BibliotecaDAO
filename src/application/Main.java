package application;

import dao.LivroDAO;
import model.Livro;

public class Main {
    public static void main(String[] args) {
        //fazendo inserção
        Livro livro = new Livro();
        livro.setTitulo("Os Irmãos Karmázov");
        livro.setPreco(111);
        livro.setCategoria("Ficção filosófica");
        livro.setIsbn("978-8497930772");

        LivroDAO livroDAO = new LivroDAO();

        for(Livro l : livroDAO.()){
            System.out.println("Livro: " + l.getTitulo());
        }
        livroDAO.listar();
    }
}
