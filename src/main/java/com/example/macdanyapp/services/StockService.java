package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Stock;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.repositories.StockDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class StockService {
    StockDAO stockDAO;
    Stock stockExistente;

    public  StockService() {
        this.stockDAO = new StockDAO(); // Inicializas clienteDAO
    }


    public void inserStock(Stock stock) throws SQLException {
        try{
            stockDAO.insertStock(stock);
            System.out.println("El stock se agrego correctamente");
        } catch (SQLException e) {
            System.err.println("Error al insertar el stock: " + e.getMessage());
        }

    }

    public Stock traerStock(long idVajilla) throws SQLException {

        try{
            stockExistente=stockDAO.traerStock(idVajilla);
            if(stockExistente!=null){
                System.out.println("Stock encontrado: " + stockExistente);
            }else{
                System.out.println("Stock no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el stock: " + e.getMessage());
        }

        return stockExistente;
    }

    public List<Stock> traerStockDisponible() throws SQLException {
        List<Stock> listStock = null;
        try{
            listStock= stockDAO.traerStockDisponible();
            if(listStock!=null){
                System.out.println("Stock encontrado: " + listStock);
            }else{
                System.out.println("Stock no encontrado.");
            }


        } catch (SQLException e) {
            System.err.println("Error al traer el stock: " + e.getMessage());
        }
        return listStock;
    }





    public void eliminarStock(long idVajilla) throws SQLException {
        try{
            if(stockDAO.traerStock(idVajilla)!=null){
                stockDAO.eliminarStock(idVajilla);
            }else{
                System.out.println("Stock no encontrado.");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el stock: " + e.getMessage());
        }

    }

    public void modificarStock(Integer nuevaCantidad,Integer idStock) throws SQLException {
       stockDAO.modificarStock(nuevaCantidad,idStock);
    }
}
