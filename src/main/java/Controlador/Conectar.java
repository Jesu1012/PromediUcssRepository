package Controlador;
import java.sql.*;

import static Controlador.Conectar.getConexion;

public class Conectar {
    public static synchronized Connection getConexion() {
        Connection conex = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/bdcalculator?serverTimezone=UTC";
            String usr = "root";
            String psw = "as1012#J";//G3an12##
            conex = DriverManager.getConnection(url, usr, psw);
            //          CallableStatement proc = conex.prepareCall("{CALL IniciarBD()}");
            //          proc.execute();
//            System.out.println("Conexion Realizada");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error >> Driver no Instalado!!");
        } catch (SQLException ex) {
            System.out.println(ex+"Error >> de conexion con la BD");
        }
        return conex;
    }
}
class prove{
    public static void main(String[] args) {
        getConexion();
    }
}
