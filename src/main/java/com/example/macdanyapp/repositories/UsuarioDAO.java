package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.*;


import java.sql.*;


public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        this.connection = DatabaseConnection.getConnection();
    }


    public void insertUsuario(Usuario usuario) throws SQLException {
        String sql= "INSERT INTO usuario (nombreDeUsuario,password,tipoDeUsuario) VALUES (?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, usuario.getNombreDeUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3,usuario.getTipoDeUsuario().name());
            ps.executeUpdate();
        }
    }

    public Usuario traerUsuario(String nombreDeUsuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nombreDeUsuario = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreDeUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    usuario = new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("nombreDeUsuario"),
                            rs.getString("password"),
                            TipoDeUsuario.valueOf(rs.getString("tipoDeUsuario"))
                    );
                }
            }
        }
        return usuario;
    }


    public void eliminarUsuario(String nombreDeUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE nombreDeUsuario = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nombreDeUsuario);
            ps.executeUpdate();
        }
    }

    public void modificarUsuario(long idUsuario, String nuevoNombreDeUsuario, String nuevoPassword, TipoDeUsuario nuevoTipoDeUsuario) throws SQLException {
        String sql = "UPDATE usuario SET nombreDeUsuario =?, password = ? , tipoDeUsuario = ?  WHERE idUsuario = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nuevoNombreDeUsuario);
            ps.setString(2, nuevoPassword);
            ps.setString(3, nuevoTipoDeUsuario.name());
            ps.setLong(4, idUsuario);
            ps.executeUpdate();
        }}

}
