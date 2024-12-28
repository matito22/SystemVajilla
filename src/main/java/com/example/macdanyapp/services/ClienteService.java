package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.repositories.ClienteDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    ClienteDAO clienteDAO;
    Cliente clienteExistente;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO(); // Inicializas clienteDAO
    }

    public void insertCliente(Cliente cliente) throws SQLException {
        try{
            if(clienteDAO.traerClientePorNombreYApellido(cliente.getNombre(), cliente.getApellido()) != null){
                System.out.println("El cliente ya existe");
            }else{
                clienteDAO.insertCliente(cliente);
                System.out.println("El cliente se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el cliente: " + e.getMessage());
        }

    }


    public Cliente traerCliente(long idCliente) throws SQLException {

        try{
            clienteExistente=clienteDAO.traerCliente(idCliente);
            if(clienteExistente!=null){
                System.out.println("Cliente encontrado: " + clienteExistente);
            }else{
                System.out.println("Cliente no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el cliente: " + e.getMessage());
        }

        return clienteExistente;
    }

    public Cliente traerClientePorNombreYApellido(String nombreCliente, String apellidoCliente) throws SQLException {
        try{
            clienteExistente=clienteDAO.traerClientePorNombreYApellido(nombreCliente, apellidoCliente);
            if(clienteExistente!=null){
                System.out.println("Cliente encontrado: " + clienteExistente);
            }else{
                System.out.println("Cliente no encontrado.");
            }


        } catch (SQLException e) {
            System.err.println("Error al traer el cliente: " + e.getMessage());
        }
        return clienteExistente;
    }

    public void eliminarCliente(long idCliente) throws SQLException {
        try{
            if(clienteDAO.traerCliente(idCliente)!=null){
                clienteDAO.eliminarCliente(idCliente);
            }else{
                System.out.println("Cliente no encontrado.");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
        }
        clienteDAO.eliminarCliente(idCliente);
    }

    public void modificarCliente(Long idCliente,String nuevoNombre,String nuevoApellido,String nuevoDomicilio,String nuevoNumeroDeTelefono) throws SQLException {
        clienteDAO.modificarCliente(idCliente,nuevoNombre, nuevoApellido, nuevoDomicilio, nuevoNumeroDeTelefono);
    }



    public List<Cliente> traerListaClientes() throws SQLException {
        List<Cliente> clienteLista = null;
        try{
            clienteLista= clienteDAO.traerListaClientes();
            if(clienteLista!=null){
                System.out.println("Clientes encontrados: " + clienteLista);
            }else{
                System.out.println("Clientes no encontrados.");
            }


        } catch (SQLException e) {
            System.err.println("Error al traer los clientes: " + e.getMessage());
        }
        return clienteLista;
    }


}
