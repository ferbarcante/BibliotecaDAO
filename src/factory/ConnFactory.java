package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnFactory {
    //funcao para conectar com a database
    public static Connection connection(){
        Connection conn = null;

        String dbname = "livraria";
        String user = "postgres";
        String pass = "123";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname,user,pass);
            if(conn!=null){
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection not Established");
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
}
