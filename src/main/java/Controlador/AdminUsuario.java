package Controlador;

import Modelo.Usuario;

import java.sql.*;

public class AdminUsuario {
    public static Connection con = null;
    public static PreparedStatement pst = null;

    public int Insertar(Usuario usuario){
        int R = 0;
        try {
            con = Conectar.getConexion();
            String sql="Insert into user(idUser,username,age,idCampus,passwords) " +
                    "values (?,?,?,?,?)";
            R = getUltimoId()+1;
            pst = con.prepareStatement(sql);
            pst.setInt(1,R);
            pst.setString(2,usuario.getUserName());
            pst.setInt(3,usuario.getAgeUser());
            pst.setInt(4,usuario.getIdCampus());
            pst.setString(5,usuario.getPasswords());
            pst.executeUpdate();

        }catch (Exception e){
            System.out.println("Error en la sentencia "+ e.getMessage());
        }finally {
            try {
                if(pst != null) pst.close();
                if (con != null) con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar");
            }
        }
        return R;

    }
    public Usuario leerUsuario(String username,String password){
        Usuario user = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = Conectar.getConexion();
            String sql = "SELECT * FROM user WHERE username = ? AND passwords = ?;";
            pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.first()) {
                user = new Usuario();
                user.setIdUser(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setAgeUser(rs.getInt(3));
                user.setIdCampus(rs.getInt(4));
                user.setPasswords(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error en la sentencia: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar: " + e.getMessage());
            }
        }

        return user;

    }
    public boolean verificarUsuarioExistente(String username) {
        boolean usuarioExistente = false;

        try (Connection con = Conectar.getConexion();
             PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ?;");
        ) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        usuarioExistente = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en la sentencia: " + e.getMessage());
        }

        return usuarioExistente;
    }


    public int actualizarUsuario(Usuario usuario){
        int R = 0;
        try {
            con = Conectar.getConexion();
            String sql="Update user " +
                    "set username=?," +
                    "age=?," +
                    "idCampus=?," +
                    "passwords=?" +
                    "where idUser=?";
            pst = con.prepareStatement(sql);
            pst.setString(1,usuario.getUserName());
            pst.setInt(2,usuario.getAgeUser());
            pst.setInt(3,usuario.getIdCampus());
            pst.setString(4,usuario.getPasswords());
            pst.setInt(5,usuario.getIdUser());
            R=pst.executeUpdate();

        }catch (Exception e){
            System.out.println("Error en la sentencia "+ e.getMessage());
        }finally {
            try {
                if(pst != null) pst.close();
                if (con != null) con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar");
            }
        }
        return R;
    }
    public int getUltimoId(){
        Connection con = null;
        PreparedStatement pst = null;
        int R=0;
        ResultSet Registro = null;
        try {
            con = Conectar.getConexion();
            String sql = "Select Max(idUser) AS Ultimo from User;";
            pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Registro = pst.executeQuery();
            Registro.first();
            R = Registro.getInt(1);

        }catch (Exception e){
            System.out.println("Error en la sentencia "+ e.getMessage());
        }finally {
            try {
                if(pst != null) pst.close();
                if (con != null) con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar");
            }
        }
        return R;
    }
}
