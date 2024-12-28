package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Proveedor;
import com.example.macdanyapp.repositories.ProveedorDAO;

import java.sql.SQLException;

public class ProveedorService {
    ProveedorDAO proveedorDao;
    Proveedor proveedorExistente;

    public ProveedorService() {
        this.proveedorDao = new ProveedorDAO(); // Inicializas proveedorDao
    }

    public void insertProveedor(Proveedor proveedor) throws SQLException {
        try{
            if(proveedorDao.traerProveedorPorNombre(proveedor.getNombre()) != null){
                System.out.println("El Proveedor ya existe");
            }else{
                proveedorDao.insertProveedor(proveedor);
                System.out.println("El proveedor se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el proveedor: " + e.getMessage());
        }

    }


    public Proveedor traerProveedor(long idProveedor) throws SQLException {

        try{
            proveedorExistente=proveedorDao.traerProveedor(idProveedor);
            if(proveedorExistente!=null){
                System.out.println("Proveedor encontrado: " + proveedorExistente);
            }else{
                System.out.println("Proveedor no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el proveedor: " + e.getMessage());
        }

        return proveedorExistente;
    }

    public Proveedor traerProveedorPorNombre(String nombreProveedor) throws SQLException {
        try{
            proveedorExistente=proveedorDao.traerProveedorPorNombre(nombreProveedor);
            if(proveedorExistente!=null){
                System.out.println("Proveedor encontrado: " + proveedorExistente);
            }else{
                System.out.println("Proveedor no encontrado.");
            }


        } catch (SQLException e) {
            System.err.println("Error al traer el proveedor: " + e.getMessage());
        }
        return proveedorExistente;
    }

    public void eliminarProveedor(long idProveedor) throws SQLException {
        try{
            if(proveedorDao.traerProveedor(idProveedor)!=null){
                proveedorDao.eliminarProveedor(idProveedor);
            }else{
                System.out.println("Proveedor no encontrado.");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el proveedor: " + e.getMessage());
        }
        proveedorDao.eliminarProveedor(idProveedor);
    }

    public void modificarProveedor(Long idCliente,String nuevoNombre,String contacto) throws SQLException {
        proveedorDao.modificarProveedor(idCliente,nuevoNombre,contacto);
    }
}
