package dao;

import factory.ConnFactory;
import model.Cliente;
import model.Livro;
import model.Reserva;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    public void  save(Reserva reserva){
        String sql = "INSERT INTO reservas (id_cliente, dataReserva, dataDevolucao, id_livro) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, reserva.getId_cliente());
            pstm.setDate(2, new Date(reserva.getDataReserva().getTime()));
            pstm.setDate(3, new Date(reserva.getDataDevolucao().getTime()));
            pstm.setInt(4, reserva.getId_livro());

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

    public List<Reserva> listar(){
        String sql = "SELECT * FROM reservas_com_detalhes";
        List<Reserva> reservas = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //classe que vai recuperar os dados do banco
        ResultSet rst = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            rst = pstm.executeQuery();

            while (rst.next()){
                Reserva reserva = new Reserva();

                reserva.setId_reserva(rst.getInt("id_reserva"));
                reserva.setId_livro(rst.getInt("id_livro"));
                reserva.setId_cliente(rst.getInt("id_cliente"));
                reserva.setDataReserva(rst.getDate("dataReserva"));
                reserva.setDataDevolucao(rst.getDate("dataDevolucao"));

                Cliente cliente = new Cliente();
                cliente.setId_cliente(rst.getInt("id_cliente")); // Aqui você pode setar o ID do cliente, se necessário
                cliente.setNome(rst.getString("nome_cliente")); // Utilizando o alias definido na view para o nome do cliente
                reserva.setCliente(cliente); // Definindo o cliente na reserva

                // Criando objeto Livro
                Livro livro = new Livro();
                livro.setId_livro(rst.getInt("id_livro")); // Aqui você pode setar o ID do livro, se necessário
                livro.setTitulo(rst.getString("titulo")); // Utilizando o alias definido na view para o título do livro
                reserva.setLivro(livro); // Definindo o livro na reserva
                reservas.add(reserva);
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

        return reservas;
    }

    public void update(Reserva reserva){
        String sql = "UPDATE reservas SET id_cliente = ?, id_livro = ?, dataReserva = ?, dataDevolucao = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, reserva.getId_cliente());
            pstm.setInt(2, reserva.getId_livro());
            pstm.setDate(3, new Date(reserva.getDataReserva().getTime()));
            pstm.setDate(4, new Date(reserva.getDataDevolucao().getTime()));
            pstm.setInt(5, reserva.getId_reserva());

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

    public void delete(Reserva reserva){
        String sql = "DELETE FROM reservas WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnFactory.connection();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, reserva.getId_reserva());

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
