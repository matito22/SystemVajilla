package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.DetalleAlquiler;
import com.example.macdanyapp.entitys.HistorialAlquileres;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.services.AlquilerService;
import com.example.macdanyapp.services.ClienteService;
import com.example.macdanyapp.services.MultaService;
import com.example.macdanyapp.services.VajillaService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialAlquileresDAO {
    private Connection connection;

    public HistorialAlquileresDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    AlquilerService alquilerService = new AlquilerService();
    MultaService multaService = new MultaService();
    ClienteService clienteService = new ClienteService();

    public void insertHistorialAlquiler(Alquiler alquiler) throws SQLException {
        String sql = "INSERT INTO historial_alquileres (alquiler_id, cliente_id, fechaComienzo, fechaFinalizacion, horaComienzo, horaFinalizacion, diasDeAlquiler, costoDelivery, totalAlquiler, multa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, alquiler.getIdAlquiler());
            ps.setInt(2, alquiler.getCliente().getIdCliente());
            ps.setDate(3, Date.valueOf(alquiler.getFechaComienzo()));
            ps.setDate(4, Date.valueOf(alquiler.getFechaFinalizacion()));
            ps.setTime(5, Time.valueOf(alquiler.getHoraComienzo()));
            ps.setTime(6, Time.valueOf(alquiler.getHoraFinalizacion()));
            ps.setInt(7, alquiler.getDiasAlquiler());
            ps.setDouble(8, alquiler.getCostoDelivery());
            ps.setDouble(9, alquiler.getTotalAlquiler());
            ps.setFloat(10, alquiler.getMulta() != null ? alquiler.getMulta().getMonto() : 0);
            ps.executeUpdate();
        }
    }

    public List<HistorialAlquileres> traerHistorialDeAlquileres() throws SQLException {
        String sql = "SELECT * FROM historial_alquileres";
        List <HistorialAlquileres> historialAlquileres = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                      HistorialAlquileres historial = new HistorialAlquileres(
                           alquilerService.traerAlquiler(rs.getInt("alquiler_id")),
                           clienteService.traerCliente(rs.getInt("cliente_id")),
                           rs.getDate("fechaComienzo").toLocalDate(),
                           rs.getDate("fechaFinalizacion").toLocalDate(),
                           rs.getTime("horaComienzo").toLocalTime(),
                           rs.getTime("horaFinalizacion").toLocalTime(),
                           rs.getInt("diasDeAlquiler"),
                           rs.getFloat("costoDelivery"),
                           rs.getFloat("totalAlquiler"),
                           multaService.traerMulta(rs.getInt("alquiler_id"))
                        );
                        historialAlquileres.add(historial);
                }
            }
        }
        return historialAlquileres;
    }


}
