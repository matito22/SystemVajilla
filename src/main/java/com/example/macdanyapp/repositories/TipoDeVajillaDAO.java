package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.TipoDeUsuario;
import com.example.macdanyapp.entitys.TipoDeVajilla;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.entitys.Vajilla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDeVajillaDAO {

    private Connection connection;

    public TipoDeVajillaDAO() {
        this.connection = DatabaseConnection.getConnection();
    }


    public void insertTipoDeVajilla(TipoDeVajilla tipoDeVajilla) throws SQLException {
        String sql= "INSERT INTO tipo_de_vajilla (nombreTipoDeVajilla) VALUES (?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,tipoDeVajilla.getNombreTipoDeVajilla());
            ps.executeUpdate();
        }
    }

    public TipoDeVajilla traerTipoDeVajilla(String nombreTipoDeVajilla) throws SQLException {
        String sql = "SELECT * FROM tipo_de_vajilla WHERE nombreTipoDeVajilla = ?";
        TipoDeVajilla tipoDeVajilla = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreTipoDeVajilla);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    tipoDeVajilla = new TipoDeVajilla(
                            rs.getInt("idTipoDeVajilla"),
                            rs.getString("nombreTipoDeVajilla")
                    );
                }
            }
        }
        return tipoDeVajilla;
    }

    public TipoDeVajilla traerTipoDeVajillaId(Integer idTipoDeVajilla) throws SQLException {
        String sql = "SELECT * FROM tipo_de_vajilla WHERE idTipoDeVajilla = ?";
        TipoDeVajilla tipoDeVajilla = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idTipoDeVajilla);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    tipoDeVajilla = new TipoDeVajilla(
                            rs.getInt("idTipoDeVajilla"),
                            rs.getString("nombreTipoDeVajilla")
                    );
                }
            }
        }
        return tipoDeVajilla;
    }

    public List<TipoDeVajilla> traerListaTipoDeVajilla() throws SQLException {
        String sql = "SELECT * FROM tipo_de_vajilla";
        List<TipoDeVajilla> listaTiposDeVajilla = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        TipoDeVajilla tipoDeVajilla = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tipoDeVajilla = new TipoDeVajilla(
                            rs.getInt("idTipoDeVajilla"),
                            rs.getString("nombreTipoDeVajilla")

                    );
                    listaTiposDeVajilla.add(tipoDeVajilla);
                }
            }
        }
        return listaTiposDeVajilla; // Retornamos el objeto cliente
    }


    public void eliminarTipoDeVajilla(String nombreTipoDeVajilla) throws SQLException {
        String sql = "DELETE FROM tipo_de_vajilla WHERE nombreTipoDeVajilla = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nombreTipoDeVajilla);
            ps.executeUpdate();
        }
    }

}
