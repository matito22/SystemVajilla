package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.entitys.Estado;
import com.example.macdanyapp.entitys.Multa;
import com.example.macdanyapp.repositories.AlquilerDAO;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AlquilerService {
    AlquilerDAO alquilerDAO;
    Alquiler alquilerExistente;

    public AlquilerService() {
        this.alquilerDAO = new AlquilerDAO(); // Inicializas clienteDAO
    }

    public int insertAlquiler(Alquiler alquiler) throws SQLException {
        int idAlquilerGenerado = -1;
        try{
            idAlquilerGenerado=alquilerDAO.insertAlquiler(alquiler);
                System.out.println("El alquiler se agrego correctamente");
        } catch (SQLException e) {
            System.err.println("Error al insertar el cliente: " + e.getMessage());
        }
        return idAlquilerGenerado;
    }

    public Alquiler traerAlquiler(long idAlquiler) throws SQLException {

        try{
            alquilerExistente=alquilerDAO.traerAlquiler(idAlquiler);
            if(alquilerExistente!=null){
                System.out.println("Alquiler encontrado: " + alquilerExistente);
            }else{
                System.out.println("Alquiler no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el alquiler: " + e.getMessage());
        }

        return alquilerExistente;
    }

    public List<Alquiler> traerAlquilerPorFecha(LocalDate fechaComienzo , LocalDate fehcaFinalizacion) throws SQLException {
        List<Alquiler> alquilerLista = null;
        try{
            alquilerLista= alquilerDAO.traerAlquileresPorFecha(fechaComienzo,fehcaFinalizacion);
            if(alquilerLista!=null){
                System.out.println("Alquileres encontrados: " + alquilerLista);
            }else{
                System.out.println("Alquileres no encontrados.");
            }


        } catch (SQLException e) {
            System.err.println("Error al traer los alquileres: " + e.getMessage());
        }
        return alquilerLista;
    }

    public List<Alquiler> traerAlquilerPorEstado(Estado estado) throws SQLException {
        List<Alquiler> alquilerLista = null;
        try{
            alquilerLista= alquilerDAO.traerAlquilerPorEstado(estado);
            if(alquilerLista!=null){
                System.out.println("Alquileres encontrados: " + alquilerLista);
            }else{
                System.out.println("Alquileres no encontrados.");
            }


        } catch (SQLException e) {
            System.err.println("Error al traer los alquileres: " + e.getMessage());
        }
        return alquilerLista;
    }


    public void eliminarAlquiler(long idAlquiler) throws SQLException {
        try{
            if(alquilerDAO.traerAlquiler(idAlquiler)!=null){
                alquilerDAO.eliminarAlquiler(idAlquiler);
            }else{
                System.out.println("Cliente no encontrado.");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
        }

    }

    public void modificarAlquiler(LocalDate nuevaFechaComienzo, LocalDate nuevaFechaFinalizacion,
                                  LocalTime nuevaHoraComienzo, LocalTime nuevaHoraFinalizacion, int nuevoDiasDeAlquiler,
                                  float nuevoCostoDelivery, float nuevoTotalAlquiler, Estado nuevoEstado, Multa nuevoMulta,
                                  Cliente nuevoCliente) throws SQLException {
        alquilerDAO.modificarAlquiler(nuevaFechaComienzo, nuevaFechaFinalizacion, nuevaHoraComienzo, nuevaHoraFinalizacion, nuevoDiasDeAlquiler, nuevoCostoDelivery, nuevoTotalAlquiler, nuevoEstado, nuevoMulta, nuevoCliente);
    }


}