package dao;

import factory.ConnFactory;
import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LivroDAO {
    public void save(Livro livro){
        //INSERT
        String sql = "INSERT INTO livros (titulo, preco, isbn, categoria) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, livro.getTitulo());
            pstm.setDouble(2, livro.getPreco());
            pstm.setString(3, livro.getIsbn());
            pstm.setString(4, livro.getCategoria());

            //executar a query
            pstm.execute();
        }catch (Exception e){
            e.printStackTrace();
        } finally {

            //fechando conexoes
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
