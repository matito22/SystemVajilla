package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.GastoExterno;
import com.example.macdanyapp.entitys.Pago;
import com.example.macdanyapp.services.AlquilerService;

import java.sql.*;
import java.time.LocalDate;

public class GastoExternoDAO {
    private Connection connection;


    public GastoExternoDAO() {
        this.connection = DatabaseConnection.getConnection();

    }

    AlquilerService alquilerService = new AlquilerService();

    public void insertGastoExterno(GastoExterno gastoExterno) throws SQLException {
        String sql= "INSERT INTO gastoexterno (descripcion,monto,fecha,alquiler_id) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, gastoExterno.getDescripcion());
            ps.setDouble(2, gastoExterno.getMonto());
            ps.setDate(3,Date.valueOf(gastoExterno.getFecha()));
            ps.setInt(4,gastoExterno.getAlquiler().getIdAlquiler());
            ps.executeUpdate();
        }
    }

    public GastoExterno traerGastoExterno(long idAlquiler) throws SQLException {
        String sql = "SELECT * FROM gastoexterno WHERE alquiler_id = ?";
        GastoExterno gastoExterno = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idAlquiler);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    gastoExterno = new GastoExterno(
                            rs.getString("descripcion"),
                            rs.getFloat("monto"),
                            rs.getDate("fecha").toLocalDate(),
                            alquilerService.traerAlquiler(rs.getInt("alquiler_id"))

                    );
                }
            }
        }
        return gastoExterno;
    }


    public void eliminarGastoExterno(long idAlquiler) throws SQLException {
        String sql = "DELETE FROM gastoexterno WHERE alquiler_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idAlquiler);
            ps.executeUpdate();
        }
    }

    public void modificarGastoExterno(int idAlquiler,String nuevaDescripcion,Float nuevoMonto,LocalDate nuevaFecha) throws SQLException {
        String sql = "UPDATE gastoexterno SET descripcion = ? , monto = ?,fecha = ?  WHERE alquiler_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nuevaDescripcion);
            ps.setFloat(2,nuevoMonto);
            ps.setDate(3,Date.valueOf(nuevaFecha));
            ps.setInt(4,idAlquiler);
            ps.executeUpdate();
        }
    }
}
