package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Stock;
import com.example.macdanyapp.entitys.TipoDeUsuario;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.repositories.StockDAO;
import com.example.macdanyapp.repositories.UsuarioDAO;

import java.sql.SQLException;

public class UsuarioService {
    UsuarioDAO usuarioDAO;
    Usuario usuarioExistente;

    public  UsuarioService() {
        this.usuarioDAO = new UsuarioDAO(); // Inicializas clienteDAO
    }


    public void insertUsuario(Usuario usuario) throws SQLException {
        try{
            if(usuarioDAO.traerUsuario(usuario.getNombreDeUsuario())!=null){
                System.out.println("El usuario ya existe");
            }else{
                usuarioDAO.insertUsuario(usuario);
                System.out.println("El usuario se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
        }
    }

    public Usuario traerUsuario(String nombreDeUsuario) throws SQLException {

        try{
            usuarioExistente=usuarioDAO.traerUsuario(nombreDeUsuario);
            if(usuarioExistente!=null){
                System.out.println("Usuario encontrado: ");
            }else{
                System.out.println("Usuario no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el usuario: " + e.getMessage());
        }

        return usuarioExistente;
    }



    public void eliminarUsuario(String nombreDeUsuario) throws SQLException {
        try{
            if(usuarioDAO.traerUsuario(nombreDeUsuario)!=null){
                usuarioDAO.eliminarUsuario(nombreDeUsuario);
            }else{
                System.out.println("Usuario no encontrado.");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }

    }

    public void modificarStock(long idUsuario,String nuevoNombreDeUsuario, String nuevoPassword, TipoDeUsuario nuevoTipoDeUsuario) throws SQLException {
        usuarioDAO.modificarUsuario(idUsuario,nuevoNombreDeUsuario,nuevoPassword,nuevoTipoDeUsuario);
    }

    public boolean validarUsuario(Usuario usuario) throws SQLException {
        if(usuario!=null){
            usuarioExistente=usuarioDAO.traerUsuario(usuario.getNombreDeUsuario());
        }else{
            return false;
        }


        if(usuarioExistente==null){
            return false;
        }

        return usuarioExistente.equals(usuario);

    }

}
