package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.*;
import com.example.macdanyapp.services.TipoDeVajillaService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VajillaDAO {
    private Connection connection;
    TipoDeVajillaService tipoDeVajillaService= new TipoDeVajillaService();

    public VajillaDAO() {
        this.connection = DatabaseConnection.getConnection();

    }

    public void insertVajilla(Vajilla vajilla) throws SQLException {
        String sql= "INSERT INTO vajilla (modelo,color,precioIndividual,tamaño,id_tipo_de_vajilla) VALUES (?, ?, ?, ?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, vajilla.getModelo());
            ps.setString(2,vajilla.getColor());
            ps.setFloat(3,vajilla.getPrecioIndividual());
            ps.setString(4, vajilla.getTamaño());
            ps.setInt(5,vajilla.getTipoDeVajilla().getIdTipoDeVajilla());
            ps.executeUpdate();
        }
    }

    public Vajilla traerVajilla(long idVajilla) throws SQLException {
        String sql = "SELECT * FROM vajilla WHERE idVajilla = ?";
        Vajilla vajilla = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idVajilla);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int tipoDeVajillaId = rs.getInt("id_tipo_de_vajilla");

                    try {
                        vajilla = new Vajilla(
                                rs.getInt("idVajilla"),                // Integer idVajilla
                                rs.getString("modelo"),                 // String modelo
                                rs.getString("color"),                  // String color
                                rs.getString("tamaño"),                 // String tamaño
                                rs.getFloat("precioIndividual"),        // Float precioIndividual
                                tipoDeVajillaService.traerTipoDeVajillaPorId(tipoDeVajillaId)
                        );
                    } catch (IllegalArgumentException e) {
                        // Si el valor del String no corresponde a un valor en el enum, lanzar excepción
                        throw new SQLException("Valor de tipoDeVajilla no válido: " + tipoDeVajillaId);
                    }
                }
            }
        }

        return vajilla;
    }

    public Vajilla traerVajillaPorTipo(int idTipoDeVajilla) throws SQLException {
        String sql = "SELECT * FROM vajilla WHERE id_tipo_de_vajilla = ? LIMIT 1";
        Vajilla vajilla = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idTipoDeVajilla);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    try {
                        vajilla = new Vajilla(
                                rs.getInt("idVajilla"),                // Integer idVajilla
                                rs.getString("modelo"),                 // String modelo
                                rs.getString("color"),                  // String color
                                rs.getString("tamaño"),                 // String tamaño
                                rs.getFloat("precioIndividual"),        // Float precioIndividual
                                tipoDeVajillaService.traerTipoDeVajillaPorId(idTipoDeVajilla)
                        );
                        System.out.println("Vajilla cargada correctamente: " + vajilla);  // Verifica la instancia de la vajilla cargada
                    } catch (IllegalArgumentException e) {
                        throw new SQLException("Valor de tipoDeVajilla no válido: " + idTipoDeVajilla);
                    }
                } else {
                    System.out.println("No se encontró vajilla con id_tipo_de_vajilla = " + idTipoDeVajilla);  // Verifica si el resultado está vacío
                }
            }
        }

        return vajilla;
    }





    public void eliminarVajilla(long idVajilla) throws SQLException {
        String sql = "DELETE FROM vajilla WHERE idVajilla = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idVajilla);
            ps.executeUpdate();
        }
    }

    public void modificarVajilla(Long idVajilla,TipoDeVajilla tipoDeVajilla,String modelo,String color,String tamaño,Float precioIndividual) throws SQLException {
        String sql = "UPDATE vajilla SET tipoDeVajilla = ?, modelo = ?, color = ?, tamaño = ?, precioIndividual = ? WHERE idVajilla = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,tipoDeVajilla.getIdTipoDeVajilla());
            ps.setString(2, modelo);
            ps.setString(3, color);
            ps.setString(4, tamaño);
            ps.setFloat(5, precioIndividual);
            ps.setLong(6, idVajilla);
            ps.executeUpdate();
        }
    }

    public List<Vajilla> traerListaVajilla() throws SQLException {
        String sql = "SELECT * FROM vajilla";
        List<Vajilla> listaVajilla = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        Vajilla vajilla = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer tipoDeVajillaId = rs.getInt("id_tipo_de_vajilla");
                    vajilla = new Vajilla(
                            rs.getInt("idVajilla"),
                            rs.getString("modelo"),
                            rs.getString("color"),
                            rs.getString("tamaño"),
                            rs.getFloat("precioIndividual"),
                            tipoDeVajillaService.traerTipoDeVajillaPorId(tipoDeVajillaId)
                    );
                    listaVajilla.add(vajilla);
                }
            }
        }
        return listaVajilla; // Retornamos el objeto cliente
    }

}
