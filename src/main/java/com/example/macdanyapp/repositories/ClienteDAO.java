package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.entitys.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void insertCliente(Cliente cliente) throws SQLException {
        String sql= "INSERT INTO cliente (nombre, apellido,domicilio,numeroDeTelefono) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setString(3,cliente.getTelefono());
            ps.setString(4,cliente.getDomicilio());
            ps.executeUpdate();
        }
    }

    public Cliente traerCliente(long idCliente) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE idCliente = ?";
        Cliente cliente = null;  // Declaramos una variable para almacenar el cliente

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Creamos el objeto Cliente con los datos obtenidos
                    cliente = new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("domicilio"),
                            rs.getString("numeroDeTelefono")
                    );
                }
            }
        }

        return cliente; // Retornamos el objeto cliente
    }
    public Cliente traerClientePorNombreYApellido(String nombreCliente, String apellidoCliente) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE nombre = ? AND apellido = ?";
        Cliente cliente = null;  // Declaramos una variable para almacenar el cliente

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreCliente);
            ps.setString(2, apellidoCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Creamos el objeto Cliente con los datos obtenidos
                    cliente = new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("domicilio"),
                            rs.getString("numeroDeTelefono")
                    );
                }
            }
        }

        return cliente; // Retornamos el objeto cliente
    }


    public void eliminarCliente(long idCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idCliente);
            ps.executeUpdate();
        }
    }

    public void modificarCliente(Long idCliente,String nuevoNombre,String nuevoApellido,String nuevoDomicilio,String nuevoNumeroDeTelefono) throws SQLException {
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, domicilio = ?, numeroDeTelefono = ? WHERE idCliente = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoApellido);
            ps.setString(3, nuevoDomicilio);
            ps.setString(4, nuevoNumeroDeTelefono);
            ps.setLong(5, idCliente);
            ps.executeUpdate();
        }
    }

    public List<Cliente> traerListaClientes() throws SQLException {
        String sql = "SELECT * FROM cliente";
        List<Cliente> listaClientes = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        Cliente cliente = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    cliente = new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("numeroDeTelefono"),
                            rs.getString("domicilio")

                    );
                    listaClientes.add(cliente);
                }
            }
        }
        return listaClientes; // Retornamos el objeto cliente
    }

}
