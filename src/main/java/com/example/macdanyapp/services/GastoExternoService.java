package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.GastoExterno;
import com.example.macdanyapp.entitys.Proveedor;
import com.example.macdanyapp.repositories.GastoExternoDAO;
import com.example.macdanyapp.repositories.ProveedorDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public class GastoExternoService {
    GastoExternoDAO gastoExternoDAO;
    GastoExterno gastoExternoExistente;

    public GastoExternoService() {
        this.gastoExternoDAO = new GastoExternoDAO(); // Inicializas proveedorDao
    }



    public void insertGastoExterno(GastoExterno gastoExterno) throws SQLException {
        try{
            if(gastoExternoDAO.traerGastoExterno(gastoExterno.getAlquiler().getIdAlquiler()) != null){
                System.out.println("El gasto externo ya existe");
            }else{
                gastoExternoDAO.insertGastoExterno(gastoExterno);
                System.out.println("El gasto externo se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el gasto externo: " + e.getMessage());
        }

    }


    public GastoExterno traerGastoExterno(long idAlquiler) throws SQLException {

        try{
            gastoExternoExistente=gastoExternoDAO.traerGastoExterno(idAlquiler);
            if(gastoExternoExistente!=null){
                System.out.println("Gasto externo encontrado: " + gastoExternoExistente);
            }else{
                System.out.println("Gasto externo no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el gasto externo: " + e.getMessage());
        }

        return gastoExternoExistente;
    }



    public void eliminarGastoExterno(long idAlquiler) throws SQLException {
        try{
            if(gastoExternoDAO.traerGastoExterno(idAlquiler)!=null){
                gastoExternoDAO.eliminarGastoExterno(idAlquiler);
            }else{
                System.out.println("Gasto externo no encontrado.");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el gasto externo: " + e.getMessage());
        }

    }

    public void modificarProveedor(int idAlquiler, String nuevaDescripcion, Float nuevoMonto, LocalDate nuevaFecha) throws SQLException {
        gastoExternoDAO.modificarGastoExterno(idAlquiler, nuevaDescripcion, nuevoMonto, nuevaFecha);
    }
}
