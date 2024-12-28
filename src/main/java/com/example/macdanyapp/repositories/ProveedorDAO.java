package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorDAO {

    private Connection connection;

    public ProveedorDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void insertProveedor(Proveedor proveedor) throws SQLException {
        String sql= "INSERT INTO proveedor (nombre, contacto,costoAdquisicion) VALUES (?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,proveedor.getNombre());
            ps.setString(2,proveedor.getContacto());
            ps.setFloat(3,proveedor.getCostoAdquisicion());
            ps.executeUpdate();
        }
    }

    public Proveedor traerProveedor(long idProveedor) throws SQLException {
        String sql = "SELECT * FROM proveedor WHERE idProveedor = ?";
        Proveedor proveedor = null;  // Declaramos una variable para almacenar el cliente

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idProveedor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Creamos el objeto Cliente con los datos obtenidos
                    proveedor = new Proveedor(
                            rs.getInt("idProveedor"),
                            rs.getString("nombre"),
                            rs.getString("contacto"),
                            rs.getFloat("costoAdquisicion")
                    );
                }
            }
        }
        return proveedor; // Retornamos el objeto cliente
    }

    public Proveedor traerProveedorPorNombre(String nombreProveedor) throws SQLException {
        String sql = "SELECT * FROM proveedor WHERE nombre = ?";
        Proveedor proveedor = null;  // Declaramos una variable para almacenar el cliente

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreProveedor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Creamos el objeto Cliente con los datos obtenidos
                    proveedor = new Proveedor(
                            rs.getInt("idProveedor"),
                            rs.getString("nombre"),
                            rs.getString("contacto"),
                            rs.getFloat("costoAdquisicion")
                    );
                }
            }
        }
        return proveedor; // Retornamos el objeto cliente
    }


    public void eliminarProveedor(long idProveedor) throws SQLException {
        String sql = "DELETE FROM proveedor WHERE idProveedor = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idProveedor);
            ps.executeUpdate();
        }
    }

    public void modificarProveedor(Long idProveedor,String nuevoNombre,String nuevoContacto) throws SQLException {
        String sql = "UPDATE proveedor SET nombre = ?, contacto = ? WHERE idProveedor = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoContacto);
            ps.setLong(3, idProveedor);
            ps.executeUpdate();
        }}

}
