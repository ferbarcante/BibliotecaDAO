package application;

import dao.LivroDAO;
import dao.ReservaDAO;
import model.Livro;
import model.Reserva;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //fazendo inserção
        Livro livro = new Livro();
        livro.setTitulo("Anna Kariênina");
        livro.setPreco(83);
        livro.setIsbn("938949238121");
        livro.setCategoria("Romance");
        livro.setId_editora(2);

        LivroDAO livroDAO = new LivroDAO();
        livroDAO.save(livro);

        for(Livro l : livroDAO.listar()){
            System.out.println();
            System.out.println("---------------------------------------------------------");
            System.out.println("ID Livro: " + l.getId_livro());
            System.out.println("Título: " + l.getTitulo());
            System.out.println("Preço: " + l.getPreco());
            System.out.println("ISBN: " + l.getIsbn());
            System.out.println("Categoria: " + l.getCategoria());
            System.out.println("ID Editora: " + l.getId_editora());
            System.out.println("---------------------------------------------------------");

        }



       /*
        Reserva reserva = new Reserva();
        Date dataDevolucao = new Date(2024, 1, 10);
        Date dataReserva = new Date(2024, 1, 1 );
        reserva.setDataDevolucao(dataDevolucao);
        reserva.setDataReserva(dataReserva);
        reserva.setId_livro(1);
        reserva.setId_cliente(1);

        ReservaDAO reservaDAO = new ReservaDAO();
        reservaDAO.save(reserva);
        for(Reserva r : reservaDAO.listar()){
            System.out.println();
            System.out.println("---------------------------------------------------------");

            System.out.println("ID Reserva: " + r.getId_reserva());
            System.out.println("ID Livro: " + r.getId_livro());
            System.out.println("ID Cliente: " + r.getId_cliente());
            System.out.println("Data Reserva: " + r.getDataReserva());
            System.out.println("Data Devolução: " + r.getDataDevolucao());
             System.out.println("---------------------------------------------------------");
        }

         */

        //update

    }
}
