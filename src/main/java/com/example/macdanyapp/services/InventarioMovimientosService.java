package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.InventarioMovimientos;
import com.example.macdanyapp.repositories.InventarioMovimientosDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public class InventarioMovimientosService {

    InventarioMovimientosDAO inventarioMovimientosDAO;
    InventarioMovimientos inventarioMovimientos;

    public InventarioMovimientosService() {
        this.inventarioMovimientosDAO= new InventarioMovimientosDAO(); // Inicializas clienteDAO
    }

    public void insertarInventarioMovimientos(InventarioMovimientos inventarioMovimientos) throws SQLException {
        try{
            if(inventarioMovimientosDAO.traerInventarioMovimientos(inventarioMovimientos.getVajilla().getIdVajilla()) != null){
                System.out.println("El inventario movimiento ya existe");
            }else{
                inventarioMovimientosDAO.insertInventarioMovimientos(inventarioMovimientos);
                System.out.println("El inventario de movimiento se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el inventario de movimiento: " + e.getMessage());
        }

    }


    public InventarioMovimientos traerInventarioMovimientos(long idVajilla) throws SQLException {

        try{
            inventarioMovimientos=inventarioMovimientosDAO.traerInventarioMovimientos(idVajilla);
            if(inventarioMovimientos!=null){
                System.out.println("Inventario de movimientos encontrado: " + inventarioMovimientos);
            }else{
                System.out.println("Inventario de movimientos no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el inventario de movimientos: " + e.getMessage());
        }

        return inventarioMovimientos;
    }


    public void eliminarInventarioMovimientos(long idVajilla) throws SQLException {
        try{
            if(inventarioMovimientosDAO.traerInventarioMovimientos(idVajilla)!=null){
                inventarioMovimientosDAO.eliminarInventarioMovimientos(idVajilla);
            }else{
                System.out.println("Inventario de movimientos no encontrado");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el inventario de movimientos: " + e.getMessage());
        }
    }

    public void modificarInventarioMovimientos(int idVajilla, int nuevaCantidad, String nuevoTipoMovimiento, LocalDate nuevaFechaDeMovimiento) throws SQLException {
        inventarioMovimientosDAO.modificarInventarioMovimientos(idVajilla,nuevaCantidad,nuevoTipoMovimiento,nuevaFechaDeMovimiento);
    }

}
