package dao;

import factory.ConnFactory;
import model.Editora;
import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {


    public void save(Livro livro){
        String sql = "INSERT INTO livros (titulo, preco, isbn, categoria, id_editora) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, livro.getTitulo());
            pstm.setDouble(2, livro.getPreco());
            pstm.setString(3, livro.getIsbn());
            pstm.setString(4, livro.getCategoria());
            pstm.setInt(5, livro.getId_editora());

            pstm.execute();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
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

    public List<Livro> listar(){
        String sql = "SELECT * FROM livros INNER JOIN editora ON livros.id_editora = editora.id_editora";
        List<Livro> livros = new ArrayList<Livro>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            rst = pstm.executeQuery();

            while (rst.next()){
                Livro livro = new Livro();
                livro.setId_livro(rst.getInt("id_livro"));
                livro.setTitulo(rst.getString("titulo"));
                livro.setPreco(rst.getDouble("preco"));
                livro.setIsbn(rst.getString("isbn"));
                livro.setCategoria(rst.getString("categoria"));
                livro.setId_editora(rst.getInt("id_editora"));
                livros.add(livro);

                Editora editora = new Editora();
                editora.setNomefantasia(rst.getString("nomefantasia"));
                livro.setEditora(editora);
            }

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
        return livros;
    }

    public void update(Livro livro){
        String sql = "UPDATE livros SET titulo = ?, preco = ?, isbn = ?, categoria = ?, id_editora = ? WHERE id_livro = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, livro.getTitulo());
            pstm.setDouble(2, livro.getPreco());
            pstm.setString(3, livro.getIsbn());
            pstm.setString(4, livro.getCategoria());
            pstm.setInt(5, livro.getId_editora());
            pstm.setInt(6, livro.getId_livro());

            pstm.execute();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
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

    public void delete(Livro livro){
        String sql = "DELETE FROM livros WHERE id_livro = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, livro.getId_livro());

            pstm.execute();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
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
