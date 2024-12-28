package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.DetalleAlquiler;
import com.example.macdanyapp.entitys.HistorialAlquileres;
import com.example.macdanyapp.repositories.DetalleAlquilerDAO;
import com.example.macdanyapp.repositories.HistorialAlquileresDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.macdanyapp.entitys.Estado.FINALIZADO;

public class HistorialAlquileresService {
    HistorialAlquileresDAO historialAlquileresDAO;
    HistorialAlquileres historialAlquileres;
    List<HistorialAlquileres> historialAlquileresList=new ArrayList<>();


    public HistorialAlquileresService() {
        this.historialAlquileresDAO= new HistorialAlquileresDAO(); // Inicializas clienteDAO
    }

    public void insertHistorialAlquiler(Alquiler alquiler) throws SQLException {
        try{
            if(alquiler.getEstado()!=FINALIZADO){
                System.out.println("El alquiler se encuentra Activo o Pendiente");
            }else{
                historialAlquileresDAO.insertHistorialAlquiler(alquiler);
                System.out.println("El alquiler se agrego correctamente al historial de alquileres");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar al historial de alquiler: " + e.getMessage());
        }

    }


    public List<HistorialAlquileres> traerHistorialDeAlquileres() throws SQLException {

        try{
            historialAlquileresList=historialAlquileresDAO.traerHistorialDeAlquileres();
            if(historialAlquileresList!=null){
                System.out.println("Historial de alquileres encontrado: " + historialAlquileresList);
            }else{
                System.out.println("Historial de alquileres no encontrado");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer la lista de alquileres: " + e.getMessage());
        }

        return historialAlquileresList;
    }

}
