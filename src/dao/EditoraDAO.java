package dao;

import factory.ConnFactory;
import model.Editora;
import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {

    public void  save(Editora editora){
        String sql = "INSERT INTO editora (nomeFantasia, endereco, telefone) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, editora.getNomeFantasia());
            pstm.setString(2, editora.getEndereco());
            pstm.setString(3, editora.getTelefone());

            pstm.execute();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public List<Editora> listar(){
        String sql = "SELECT * FROM editora";
        List<Editora> editoras = new ArrayList<Editora>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //classe que vai recuperar os dados do banco
        ResultSet rst = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            rst = pstm.executeQuery();

            while (rst.next()){
                Editora editora = new Editora();

                editora.setId_editora(rst.getInt("id_editora"));
                editora.setNomeFantasia(rst.getString("nomeFantasia"));
                editora.setEndereco(rst.getString("endereco"));
                editora.setTelefone(rst.getString("telefone"));

                editoras.add(editora);
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

        return editoras;
    }
}
