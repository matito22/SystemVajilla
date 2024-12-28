package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Multa;
import com.example.macdanyapp.entitys.Pago;
import com.example.macdanyapp.repositories.MultaDAO;
import com.example.macdanyapp.repositories.PagoDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public class PagoService {
    PagoDAO pagoDAO = new PagoDAO();
    Pago pagoExistente;

    public PagoService() {
        this.pagoDAO = new PagoDAO();
    }

    public void insertPago(Pago pago) throws SQLException {
        try{
            if(pagoDAO.traerPago(pago.getAlquiler().getIdAlquiler()) != null){
                System.out.println("El pago ya existe");
            }else{
                pagoDAO.insertPago(pago);
                System.out.println("El pago se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el pago: " + e.getMessage());
        }

    }


    public Pago traerPago(long idAlquiler) throws SQLException {

        try{
            pagoExistente=pagoDAO.traerPago(idAlquiler);
            if(pagoExistente!=null){
                System.out.println("Pago encontrado: " + pagoExistente);
            }else{
                System.out.println("Pago no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el pago: " + e.getMessage());
        }

        return pagoExistente;
    }


    public void eliminarPago(long idAlquiler) throws SQLException {
        try{
            pagoExistente=pagoDAO.traerPago(idAlquiler);
            if(pagoDAO.traerPago(idAlquiler)!=null){
                pagoDAO.eliminarPago(pagoExistente.getIdPago());
            }else{
                System.out.println("Pago no encontrado");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el pago: " + e.getMessage());
        }
    }

    public void modificarPago(int idAlquiler, double nuevoMonto, double nuevoMontoAdelantado, String nuevoTipoDePago, LocalDate nuevaFechaDePago,LocalDate nuevaFechaDePagoAdelantado) throws SQLException {
        pagoDAO.modificarPago(idAlquiler,nuevoMonto,nuevoMontoAdelantado,nuevoTipoDePago,nuevaFechaDePago,nuevaFechaDePagoAdelantado);
    }
}
