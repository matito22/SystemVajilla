package com.example.macdanyapp.repositories;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.*;
import com.example.macdanyapp.services.ClienteService;
import com.example.macdanyapp.services.MultaService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAO {

    private Connection connection;

    public AlquilerDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    ClienteService clienteService = new ClienteService();
    MultaService multaService=new MultaService();

    public int insertAlquiler(Alquiler alquiler) throws SQLException {
        String sql = "INSERT INTO alquiler (fechaComienzo, fechaFinalizacion, horaComienzo, horaFinalizacion, cliente_id, diasDeAlquiler, costoDelivery, totalAlquiler, estado, multa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Asignar valores a los parámetros
            stmt.setDate(1, Date.valueOf(alquiler.getFechaComienzo()));
            stmt.setDate(2, Date.valueOf(alquiler.getFechaFinalizacion()));
            stmt.setTime(3, Time.valueOf(alquiler.getHoraComienzo() + ":00"));
            stmt.setTime(4, Time.valueOf(alquiler.getHoraFinalizacion() + ":00"));
            stmt.setInt(5, alquiler.getCliente().getIdCliente());
            stmt.setInt(6, alquiler.getDiasAlquiler());
            stmt.setFloat(7, alquiler.getCostoDelivery());
            stmt.setFloat(8, alquiler.getTotalAlquiler());
            stmt.setString(9, alquiler.getEstado().toString());

            // Manejo de la multa
            if (alquiler.getMulta() != null && alquiler.getMulta().getIdMulta() != null) {
                stmt.setFloat(10, alquiler.getMulta().getIdMulta());
            } else {
                stmt.setNull(10, Types.FLOAT);
            }

            // Ejecutar la consulta
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating alquiler failed, no rows affected.");
            }

            // Obtener la clave generada
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating alquiler failed, no ID obtained.");
                }
            }
        }
    }
    public Alquiler traerAlquiler(long idAlquiler) throws SQLException {
        String sql = "SELECT * FROM alquiler WHERE idAlquiler = ?";
        Alquiler alquiler = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idAlquiler);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    int clienteId = rs.getInt("cliente_id");
                    String estadoStr=rs.getString("estado");

                    alquiler = new Alquiler(
                            rs.getInt("idAlquiler"),
                            rs.getDate("fechaComienzo").toLocalDate(),
                            rs.getDate("fechaFinalizacion").toLocalDate(),
                            rs.getTime("horaComienzo").toLocalTime(),
                            rs.getTime("horaFinalizacion").toLocalTime(),
                            rs.getInt("diasDeAlquiler"),
                            rs.getFloat("costoDelivery"),
                            rs.getFloat("totalAlquiler"),
                            Estado.valueOf(estadoStr), // Convierte el String a Enum
                            multaService.traerMulta(idAlquiler),
                            clienteService.traerCliente(clienteId) // Traemos el cliente usando el clienteId
                    );
                }
            }
        }
        return alquiler; // Retornamos el objeto alquiler
    }


    public List<Alquiler> traerAlquileresPorFecha(LocalDate fechaComienzo, LocalDate fechaFinalizacion) throws SQLException {
        String sql = "SELECT * FROM alquiler WHERE fechaComienzo BETWEEN ? AND ?";
        List<Alquiler> listaAlquileres = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        Alquiler alquiler = null;


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fechaComienzo));
            ps.setDate(2, Date.valueOf(fechaFinalizacion));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int clienteId = rs.getInt("cliente_id");
                    String estadoStr=rs.getString("estado");
                    alquiler = new Alquiler(
                            rs.getInt("idAlquiler"),
                            rs.getDate("fechaComienzo").toLocalDate(),
                            rs.getDate("fechaFinalizacion").toLocalDate(),
                            rs.getTime("horaComienzo").toLocalTime(),
                            rs.getTime("horaFinalizacion").toLocalTime(),
                            rs.getInt("diasDeAlquiler"),
                            rs.getFloat("costoDelivery"),
                            rs.getFloat("totalAlquiler"),
                            Estado.valueOf(estadoStr), // Convierte el String a Enum
                            multaService.traerMulta(clienteId),
                            clienteService.traerCliente(clienteId) // Traemos el cliente usando el clienteId
                    );
                    listaAlquileres.add(alquiler);
                }
            }
        }
        return listaAlquileres; // Retornamos el objeto cliente
    }


    public List<Alquiler> traerHistorialAlquileres() throws SQLException {
        String sql = "SELECT * FROM alquiler";
        List<Alquiler> listaAlquileres = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        Alquiler alquiler = null;


        try (PreparedStatement ps = connection.prepareStatement(sql)) {


            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int clienteId = rs.getInt("cliente_id");
                    String estadoStr=rs.getString("estado");
                    alquiler = new Alquiler(
                            rs.getInt("idAlquiler"),
                            rs.getDate("fechaComienzo").toLocalDate(),
                            rs.getDate("fechaFinalizacion").toLocalDate(),
                            rs.getTime("horaComienzo").toLocalTime(),
                            rs.getTime("horaFinalizacion").toLocalTime(),
                            rs.getInt("diasDeAlquiler"),
                            rs.getFloat("costoDelivery"),
                            rs.getFloat("totalAlquiler"),
                            Estado.valueOf(estadoStr), // Convierte el String a Enum
                            multaService.traerMulta(clienteId),
                            clienteService.traerCliente(clienteId) // Traemos el cliente usando el clienteId
                    );
                    listaAlquileres.add(alquiler);
                }
            }
        }
        return listaAlquileres; // Retornamos el objeto cliente
    }


    public List<Alquiler> traerAlquilerPorEstado(Estado estado) throws SQLException {
        String sql = "SELECT * FROM alquiler WHERE estado = ?";
        List<Alquiler> listaAlquileres = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        Alquiler alquiler = null;


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,estado.name());

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int clienteId = rs.getInt("cliente_id");
                    String estadoStr=rs.getString("estado");
                    alquiler = new Alquiler(
                            rs.getInt("idAlquiler"),
                            rs.getDate("fechaComienzo").toLocalDate(),
                            rs.getDate("fechaFinalizacion").toLocalDate(),
                            rs.getTime("horaComienzo").toLocalTime(),
                            rs.getTime("horaFinalizacion").toLocalTime(),
                            rs.getInt("diasDeAlquiler"),
                            rs.getFloat("costoDelivery"),
                            rs.getFloat("totalAlquiler"),
                            Estado.valueOf(estadoStr), // Convierte el String a Enum
                            multaService.traerMulta(clienteId),
                            clienteService.traerCliente(clienteId) // Traemos el cliente usando el clienteId
                    );
                    listaAlquileres.add(alquiler);
                }
            }
        }
        return listaAlquileres; // Retornamos el objeto cliente
    }


    public List<Alquiler> traerAlquileresPorFechaYEstado(LocalDate fechaComienzo, LocalDate fechaFinalizacion,Estado estado) throws SQLException {
        String sql = "SELECT * FROM alquiler WHERE fechaComienzo  BETWEEN ? AND ? AND estado = ?";
        List<Alquiler> listaAlquileres = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        Alquiler alquiler = null;


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fechaComienzo));
            ps.setDate(2, Date.valueOf(fechaFinalizacion));
            ps.setString(3, estado.name());

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    System.out.println("ID ALQUILER: "+rs.getInt("idAlquiler"));
                    int clienteId = rs.getInt("cliente_id");
                    String estadoStr=rs.getString("estado");
                    alquiler = new Alquiler(
                            rs.getInt("idAlquiler"),
                            rs.getDate("fechaComienzo").toLocalDate(),
                            rs.getDate("fechaFinalizacion").toLocalDate(),
                            rs.getTime("horaComienzo").toLocalTime(),
                            rs.getTime("horaFinalizacion").toLocalTime(),
                            rs.getInt("diasDeAlquiler"),
                            rs.getFloat("costoDelivery"),
                            rs.getFloat("totalAlquiler"),
                            Estado.valueOf(estadoStr), // Convierte el String a Enum
                            multaService.traerMulta(clienteId),
                            clienteService.traerCliente(clienteId) // Traemos el cliente usando el clienteId
                    );
                    listaAlquileres.add(alquiler);
                }
            }
        }
        return listaAlquileres; // Retornamos el objeto cliente
    }


    public List<Alquiler> traerAlquilerYActivarlo(Estado estado, LocalDate fecha) throws SQLException {
        String sql = "SELECT * FROM alquiler WHERE estado = ? AND fechaComienzo = ?";
        List<Alquiler> listaAlquileres = new ArrayList<>();  // Declaramos una variable para almacenar el cliente
        Alquiler alquiler = null;


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,estado.name());
            ps.setDate(2, Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int clienteId = rs.getInt("cliente_id");
                    String estadoStr=rs.getString("estado");
                    alquiler = new Alquiler(
                            rs.getInt("idAlquiler"),
                            rs.getDate("fechaComienzo").toLocalDate(),
                            rs.getDate("fechaFinalizacion").toLocalDate(),
                            rs.getTime("horaComienzo").toLocalTime(),
                            rs.getTime("horaFinalizacion").toLocalTime(),
                            rs.getInt("diasDeAlquiler"),
                            rs.getFloat("costoDelivery"),
                            rs.getFloat("totalAlquiler"),
                            Estado.valueOf(estadoStr), // Convierte el String a Enum
                            multaService.traerMulta(clienteId),
                            clienteService.traerCliente(clienteId) // Traemos el cliente usando el clienteId
                    );
                    if(alquiler.getFechaComienzo().equals(fecha)){
                        listaAlquileres.add(alquiler);
                    }

                }
            }
        }
        return listaAlquileres; // Retornamos el objeto cliente
    }



    public void eliminarAlquiler(long idAlquiler) throws SQLException {
        String sql = "DELETE FROM alquiler WHERE idAlquiler = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idAlquiler);
            ps.executeUpdate();
        }
    }

    public void modificarAlquiler(float nuevoTotalAlquiler,long idAlquiler) throws SQLException {
        String sql = "UPDATE alquiler SET totalAlquiler=?  WHERE idAlquiler = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setFloat(1, nuevoTotalAlquiler);
            ps.setLong(2,idAlquiler);
            ps.executeUpdate();
        }}

    public void modificarEstadoAlquiler(Estado estado, long idAlquiler)throws SQLException{
        String sql="UPDATE alquiler SET estado=? WHERE idAlquiler=?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, estado.name());
            ps.setLong(2, idAlquiler);
            ps.executeUpdate();
        }
    }

    public void modificarAlquilerCompleto(long idAlquiler,LocalDate fechaComienzo,LocalDate fechaFinalizacion,LocalTime horaComienzo,LocalTime horaFinalizacion,int idCliente,Integer diasAlquiler,float costoDelivery,
    float totalAlquiler,Estado estado) throws SQLException {
        String sql="UPDATE alquiler SET fechaComienzo = ?,fechaFinalizacion = ?,horaComienzo = ?, horaFinalizacion = ?,cliente_id = ?,diasDeAlquiler = ?,costoDelivery = ?,totalAlquiler = ?, estado = ? WHERE idAlquiler = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, fechaComienzo.toString());
            ps.setString(2, fechaFinalizacion.toString());
            ps.setString(3, horaComienzo.toString());
            ps.setString(4, horaFinalizacion.toString());
            ps.setInt(5, idCliente);
            ps.setInt(6, diasAlquiler);
            ps.setFloat(7, costoDelivery);
            ps.setFloat(8, totalAlquiler);
            ps.setString(9, estado.name()); // Cambiado a setInt para el índice correcto
            ps.setLong(10, idAlquiler);    // Cambiado al índice correcto
            ps.executeUpdate();
        }
    }

}
