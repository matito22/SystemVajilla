package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.DetalleAlquiler;
import com.example.macdanyapp.entitys.Estado;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.services.AlquilerService;
import com.example.macdanyapp.services.VajillaService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DetalleAlquilerDAO {

    private Connection connection;

    public DetalleAlquilerDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    AlquilerService alquilerService = new AlquilerService();
    VajillaService vajillaService = new VajillaService();

    public void insertDetalleAlquiler(DetalleAlquiler detalleAlquiler) throws SQLException {
        String sql= "INSERT INTO detalle_alquiler (cantidad, precio_unitario,alquiler_id,vajilla_id) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, detalleAlquiler.getCantidad());
            ps.setDouble(2, detalleAlquiler.getPrecioUnitario());
            ps.setInt(3,detalleAlquiler.getAlquiler().getIdAlquiler());
            ps.setInt(4,detalleAlquiler.getVajilla().getIdVajilla());
            ps.executeUpdate();
        }
    }

    public DetalleAlquiler traerDetalleAlquiler(long idDetalleAlquiler) throws SQLException {
        String sql = "SELECT * FROM detalle_alquiler WHERE iddetalle_alquiler = ?";
        DetalleAlquiler detalleAlquiler = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idDetalleAlquiler);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int alquilerid = rs.getInt("alquiler_id");
                    int vajillaid=rs.getInt("vajilla_id");
                    detalleAlquiler = new DetalleAlquiler(
                            rs.getInt("cantidad"),
                            rs.getFloat("precio_unitario"),
                            alquilerService.traerAlquiler(alquilerid),
                            vajillaService.traerVajilla(vajillaid)
                    );
                }
            }
        }
        return detalleAlquiler;
    }


    public void eliminarDetalleAlquiler(long idDetalleAlquiler) throws SQLException {
        String sql = "DELETE FROM detalle_alquiler WHERE iddetalle_alquiler = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idDetalleAlquiler);
            ps.executeUpdate();
        }
    }

    public void modificarDetalleAlquiler(Long iddetalle_alquiler,Integer nuevaCantidad,Float nuevoPrecioUnitario,Alquiler nuevoAlquiler,Vajilla nuevaVajilla) throws SQLException {
        String sql = "UPDATE detalle_alquiler SET cantidad = ?, precio_unitario = ?, alquiler_id = ?, vajilla_id = ? WHERE iddetalle_alquiler = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, nuevaCantidad);
            ps.setFloat(2, nuevoPrecioUnitario);
            ps.setInt(3, nuevoAlquiler.getIdAlquiler());
            ps.setInt(4,nuevaVajilla.getIdVajilla());
            ps.setLong(5,iddetalle_alquiler);
            ps.executeUpdate();
        }
    }

    public List<DetalleAlquiler> traerDetallesPorIdAlquiler(int idAlquiler) throws SQLException {
        String sql = "SELECT * FROM detalle_alquiler WHERE alquiler_id = ?";
        List<DetalleAlquiler> listaDetalleAlquileres = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        DetalleAlquiler detalleAlquiler = null;


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,idAlquiler);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int vajillaId = rs.getInt("vajilla_id");

                    detalleAlquiler = new DetalleAlquiler(
                            rs.getInt("iddetalle_alquiler"),
                            rs.getInt("cantidad"),
                            rs.getFloat("precio_unitario"),
                            alquilerService.traerAlquiler(idAlquiler),
                            vajillaService.traerVajilla(vajillaId)

                    );
                    listaDetalleAlquileres.add(detalleAlquiler);
                }
            }
        }
        return listaDetalleAlquileres; // Retornamos el objeto cliente
    }
}
