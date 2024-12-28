package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.Pago;
import com.example.macdanyapp.services.AlquilerService;

import java.sql.*;
import java.time.LocalDate;

public class PagoDAO {
    private Connection connection;
    AlquilerService alquilerService = new AlquilerService();

    public PagoDAO() {
        this.connection = DatabaseConnection.getConnection();

    }

    public void insertPago(Pago pago) throws SQLException {
        String sql= "INSERT INTO pago (alquiler_id,monto,montoAdelantado,tipoDePago,fechaDePago,fechaDePagoAdelantado) VALUES (?, ?, ?, ?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,pago.getAlquiler().getIdAlquiler());
            ps.setDouble(2,pago.getMonto());
            ps.setDouble(3,pago.getMontoAdelantado());
            ps.setString(4,pago.getTipoPago());
            ps.setDate(5, Date.valueOf(pago.getFechaDePago()));
            ps.setDate(6,Date.valueOf(pago.getFehcaDePagoAdelantado()));
            ps.executeUpdate();
        }
    }

    public Pago traerPago(long idAlquiler) throws SQLException {
        String sql = "SELECT * FROM pago WHERE alquiler_id = ?";
        Pago pago = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idAlquiler);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                        pago = new Pago(
                                rs.getInt("idPago"),
                                alquilerService.traerAlquiler(rs.getInt("alquiler_id")),
                                rs.getFloat("monto"),
                                rs.getFloat("montoAdelantado"),
                                rs.getString("tipoDePago"),
                                rs.getDate("fechaDePago").toLocalDate(),
                                rs.getDate("fechaDePagoAdelantado").toLocalDate()
                        );
                    }
                }
            }
        return pago;
    }


    public void eliminarPago(long idPago) throws SQLException {
        String sql = "DELETE FROM pago WHERE idPago = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idPago);
            ps.executeUpdate();
        }
    }

    public void modificarPago(int idAlquiler, double nuevoMonto, double nuevoMontoAdelantado, String nuevoTipoDePago, LocalDate nuevaFechaDePago,LocalDate nuevaFechaDePagoAdelantado) throws SQLException {
        String sql = "UPDATE pago SET monto = ?, montoAdelantado = ?, tipoDePago = ?, fechaDePago = ?,fechaDePagoAdelantado = ? WHERE alquiler_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDouble(1,nuevoMonto);
            ps.setDouble(2,nuevoMontoAdelantado);
            ps.setString(3,nuevoTipoDePago);
            ps.setDate(4,Date.valueOf(nuevaFechaDePago));
            ps.setDate(5,Date.valueOf(nuevaFechaDePagoAdelantado));
            ps.setInt(6,idAlquiler);
            ps.executeUpdate();
        }
    }
}
