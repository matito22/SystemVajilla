package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.InventarioMovimientos;
import com.example.macdanyapp.entitys.TipoMovimiento;
import com.example.macdanyapp.services.VajillaService;

import java.sql.*;
import java.time.LocalDate;

public class InventarioMovimientosDAO {

    private Connection connection;
    VajillaService vajillaService = new VajillaService();


    public InventarioMovimientosDAO() {
        this.connection = DatabaseConnection.getConnection();

    }

    public void insertInventarioMovimientos(InventarioMovimientos inventarioMovimientos) throws SQLException {
        String sql= "INSERT INTO inventariomovimientos (vajilla_id,cantidad,tipoMovimiento,fechaDeMovimiento) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,inventarioMovimientos.getVajilla().getIdVajilla());
            ps.setInt(2,inventarioMovimientos.getCantidad());
            ps.setString(3,inventarioMovimientos.getTipoDeMovimiento().name());
            ps.setDate(4,java.sql.Date.valueOf(inventarioMovimientos.getFechaDeMovimiento()));
            ps.executeUpdate();
        }
    }

    public InventarioMovimientos traerInventarioMovimientos(long idVajilla) throws SQLException {
        String sql = "SELECT * FROM inventariomovimientos WHERE vajilla_id = ?";
        InventarioMovimientos inventarioMovimientos = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idVajilla);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipoDeMovimiento = rs.getString("tipoMovimiento");
                     inventarioMovimientos= new InventarioMovimientos(
                             rs.getInt("idInventario"),
                             vajillaService.traerVajilla(idVajilla),
                             rs.getInt("cantidad"),
                             TipoMovimiento.valueOf(tipoDeMovimiento),
                             rs.getDate("fechaDeMovimiento").toLocalDate()
                     );
                }
            }
        }
        return inventarioMovimientos;
    }


    public void eliminarInventarioMovimientos(long idVajilla) throws SQLException {
        String sql = "DELETE FROM inventariomovimientos WHERE vajilla_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idVajilla);
            ps.executeUpdate();
        }
    }

    public void modificarInventarioMovimientos(int idVajilla,int nuevaCantidad,String nuevoTipoMovimiento,LocalDate nuevaFechaDeMovimiento) throws SQLException {
        String sql = "UPDATE inventariomovimientos SET cantidad = ?, tipoMovimiento = ?, fechaDeMovimiento = ? WHERE vajilla_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,nuevaCantidad);
            ps.setString(2,nuevoTipoMovimiento);
            ps.setDate(3,java.sql.Date.valueOf(nuevaFechaDeMovimiento));
            ps.setLong(4,idVajilla);
            ps.executeUpdate();
        }
    }
}
