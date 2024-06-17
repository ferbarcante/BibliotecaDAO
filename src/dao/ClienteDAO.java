package dao;

import factory.ConnFactory;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void  save(Cliente cliente){
        String sql = "INSERT INTO cliente (nome, endereco, cpf, telefone) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getEndereco());
            pstm.setString(3, cliente.getCpf());
            pstm.setString(4, cliente.getTelefone();

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

    public List<Cliente> listar(){
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //classe que vai recuperar os dados do banco
        ResultSet rst = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            rst = pstm.executeQuery();

            while (rst.next()){
                Cliente cliente = new Cliente();

                cliente.setId_cliente(rst.getInt("id_cliente"));
                cliente.setNome(rst.getString("nome"));
                cliente.setEndereco(rst.getString("endereco"));
                cliente.setCpf(rst.getString("cpf"));
                cliente.setTelefone(rst.getString("telefone"));

                clientes.add(cliente);
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

        return clientes;
    }

    public void update(Cliente cliente){
        String sql = "UPDATE cliente SET nome = ?, endereco = ?, telefone = ?, cpf = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getEndereco());
            pstm.setString(3, cliente.getTelefone());
            pstm.setString(4, cliente.getCpf());
            pstm.setInt(5, cliente.getId_cliente());

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

    public void delete(Cliente cliente){
        String sql = "DELETE FROM clientes WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, cliente.getId_cliente());

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
