package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.DetalleAlquiler;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.repositories.DetalleAlquilerDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DetalleAlquilerService {

    DetalleAlquilerDAO detalleAlquilerDAO;
    DetalleAlquiler detalleAlquiler;

    public DetalleAlquilerService() {
        this.detalleAlquilerDAO= new DetalleAlquilerDAO(); // Inicializas clienteDAO
    }

    public void insertarDetalleAlquiler(DetalleAlquiler detalleAlquiler) throws SQLException {
        try{
            if(detalleAlquilerDAO.traerDetalleAlquiler(detalleAlquiler.getIdDetalle_Alquiler()) != null){
                System.out.println("El detalle alquiler ya existe");
            }else{
                detalleAlquilerDAO.insertDetalleAlquiler(detalleAlquiler);
                System.out.println("El detalle se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el detalle: " + e.getMessage());
        }

    }


    public DetalleAlquiler traerDetalleAlquiler(long iddetalle_alquiler) throws SQLException {

        try{
            detalleAlquiler=detalleAlquilerDAO.traerDetalleAlquiler(iddetalle_alquiler);
            if(detalleAlquiler!=null){
                System.out.println("Detalle encontrado: " + detalleAlquiler);
            }else{
                System.out.println("Detalle no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer detalle: " + e.getMessage());
        }

        return detalleAlquiler;
    }


    public void eliminarDetalleAlquiler(long iddetalle_alquiler) throws SQLException {
        try{
            if(detalleAlquilerDAO.traerDetalleAlquiler(iddetalle_alquiler)!=null){
                detalleAlquilerDAO.eliminarDetalleAlquiler(iddetalle_alquiler);
            }else{
                System.out.println("Detalle no encontrado");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar detalle: " + e.getMessage());
        }
    }

    public void modificarDetalleAlquiler(Long iddetalle_alquiler, Integer nuevaCantidad, Float nuevoPrecioUnitario, Alquiler nuevoAlquiler, Vajilla nuevaVajilla) throws SQLException {
        detalleAlquilerDAO.modificarDetalleAlquiler(iddetalle_alquiler,nuevaCantidad,nuevoPrecioUnitario,nuevoAlquiler,nuevaVajilla);
    }


    public List<DetalleAlquiler> traerDetallesPorIdAlquiler(int idAlquiler) throws SQLException {
        {
            List<DetalleAlquiler> detalleAlquilerList = null;
            try {
                detalleAlquilerList = detalleAlquilerDAO.traerDetallesPorIdAlquiler(idAlquiler);
                if (detalleAlquilerList != null) {
                    System.out.println("Detalles encontrados: " + detalleAlquilerList);
                } else {
                    System.out.println("Detalles no encontrados.");
                }


            } catch (SQLException e) {
                System.err.println("Error al traer los detalles: " + e.getMessage());
            }
            return detalleAlquilerList;
        }


}}
