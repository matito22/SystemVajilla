package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.entitys.Multa;
import com.example.macdanyapp.repositories.ClienteDAO;
import com.example.macdanyapp.repositories.MultaDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public class MultaService {
    MultaDAO multaDAO = new MultaDAO();
    Multa multaExistente;

    public MultaService() {
        this.multaDAO = new MultaDAO();
    }

    public void insertMulta(Multa multa, Alquiler alquiler) throws SQLException {
        try{
            if(multaDAO.traerMulta(alquiler.getIdAlquiler()) != null){
                System.out.println("La multa ya existe");
            }else{
                multaDAO.insertMulta(multa, alquiler);
                System.out.println("La multa se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar la multa: " + e.getMessage());
        }

    }


    public Multa traerMulta(long idAlquiler) throws SQLException {

        try{
            multaExistente=multaDAO.traerMulta(idAlquiler);
            if(multaExistente!=null){
                System.out.println("Multa encontrada: " + multaExistente);
            }else{
                System.out.println("Multa no encontrada.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer la multa: " + e.getMessage());
        }

        return multaExistente;
    }


    public void eliminarMulta(long idMulta,Alquiler alquiler) throws SQLException {
        try{
            if(multaDAO.traerMulta(alquiler.getIdAlquiler())!=null){
                multaDAO.eliminarMulta(idMulta,alquiler);
            }else{
                System.out.println("Multa no encontrada");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar la multa: " + e.getMessage());
        }
    }

    public void modificarMulta(LocalDate nuevaFecha, Float nuevoMonto, Integer idMulta) throws SQLException {
       multaDAO.modificarMulta(nuevaFecha,nuevoMonto,idMulta);
    }

}
