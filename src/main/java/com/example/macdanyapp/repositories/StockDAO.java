package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Multa;
import com.example.macdanyapp.entitys.Stock;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.services.AlquilerService;
import com.example.macdanyapp.services.VajillaService;

import java.sql.*;
import java.time.LocalDate;

public class StockDAO {


    private final Connection connection;
    VajillaService vajillaService;

    public StockDAO() {
        this.connection = DatabaseConnection.getConnection();
        vajillaService = new VajillaService();

    }


    public void insertStock(Stock stock) throws SQLException {
        String sql= "INSERT INTO stock (cantidadDisponible,vajilla_id) VALUES (?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, stock.getCantidadDisponible());
            ps.setInt(2,stock.getVajilla().getIdVajilla());
            ps.executeUpdate();
        }
    }

    public Stock traerStock(long idVajilla) throws SQLException {
        String sql = "SELECT * FROM stock WHERE vajilla_id = ?";
        Stock stock = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idVajilla);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    stock = new Stock(
                            rs.getInt("idStock"),
                            rs.getInt("cantidadDisponible"),
                            vajillaService.traerVajilla(idVajilla)
                    );
                }
            }
        }
        return stock; // Retornamos el objeto alquiler
    }


    public void eliminarStock(long idVajilla) throws SQLException {
        String sql = "DELETE FROM stock WHERE vajilla_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idVajilla);
            ps.executeUpdate();
        }
    }

    public void modificarStock(Integer nuevaCantidad,Integer idStock) throws SQLException {
        String sql = "UPDATE stock SET cantidadDisponible = ? WHERE idStock = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, nuevaCantidad);
            ps.setInt(2, idStock);
            ps.executeUpdate();

        }}

}
