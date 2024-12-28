package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.entitys.Estado;
import com.example.macdanyapp.entitys.Multa;
import com.example.macdanyapp.services.AlquilerService;
import com.example.macdanyapp.services.ClienteService;

import javax.swing.event.InternalFrameEvent;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MultaDAO {
    private Connection connection;
    private AlquilerService alquilerService;

    public MultaDAO() {
        this.connection = DatabaseConnection.getConnection();
        this.alquilerService = alquilerService;
    }






    public void insertMulta(Multa multa, Alquiler alquiler) throws SQLException {
        String sql= "INSERT INTO alquiler (monto,fecha,alquiler_id) VALUES (?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setFloat(1, multa.getMonto());
            ps.setDate(2,Date.valueOf(multa.getFecha()));
            ps.setInt(3, alquiler.getIdAlquiler());
            ps.executeUpdate();
        }
    }

    public Multa traerMulta(long idAlquiler) throws SQLException {
        String sql = "SELECT * FROM multa WHERE alquiler_id = ?";
        Multa multa = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idAlquiler);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    multa = new Multa(
                            rs.getInt("idMulta"),
                            rs.getFloat("monto"),
                            rs.getDate("fecha").toLocalDate(),
                            alquilerService.traerAlquiler(rs.getInt("alquiler_id"))
                    );
                }
            }
        }
        return multa; // Retornamos el objeto alquiler
    }


    public void eliminarMulta(long idMulta,Alquiler alquiler) throws SQLException {
        String sql = "DELETE FROM multa WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idMulta);
            ps.executeUpdate();
        }
    }

    public void modificarMulta(LocalDate nuevaFecha, Float nuevoMonto, Integer idMulta) throws SQLException {
        String sql = "UPDATE multa SET fecha = ?, monto = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDate(1, Date.valueOf(nuevaFecha));
            ps.setFloat(2, nuevoMonto);
            ps.setInt(3, idMulta);
            ps.executeUpdate();
        }}
}
