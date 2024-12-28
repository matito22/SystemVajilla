package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.TipoDeVajilla;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.repositories.TipoDeVajillaDAO;
import com.example.macdanyapp.repositories.UsuarioDAO;

import java.sql.SQLException;
import java.util.List;

public class TipoDeVajillaService {
    TipoDeVajillaDAO tipoDeVajillaDAO;
    TipoDeVajilla tipoDeVajilla=new TipoDeVajilla();

    public  TipoDeVajillaService() {
        this.tipoDeVajillaDAO = new TipoDeVajillaDAO(); // Inicializas clienteDAO
    }


    public void insertTipoDeVajilla(TipoDeVajilla tipoDeVajilla) throws SQLException {
        try{
            if(tipoDeVajillaDAO.traerTipoDeVajilla(tipoDeVajilla.getNombreTipoDeVajilla())!=null){
                System.out.println("El tipo de vajilla ya existe");
            }else{
                tipoDeVajillaDAO.insertTipoDeVajilla(tipoDeVajilla);
                System.out.println("El tipo de vajilla se agrego correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
        }
    }



    public TipoDeVajilla traerTipoDeVajilla(String nombreTipoDeVajilla) throws SQLException {

        try{
            tipoDeVajilla=tipoDeVajillaDAO.traerTipoDeVajilla(nombreTipoDeVajilla);
            if(tipoDeVajilla!=null){
                System.out.println("Tipo de vajilla encontrado: "+ tipoDeVajilla.getNombreTipoDeVajilla());
            }else{
                System.out.println("Tipo de vajilla no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el tipo de vajilla: " + e.getMessage());
        }

        return tipoDeVajilla;
    }

    public TipoDeVajilla traerTipoDeVajillaPorId(Integer idTipoDeVajilla) throws SQLException {

        try{
            tipoDeVajilla=tipoDeVajillaDAO.traerTipoDeVajillaId(idTipoDeVajilla);
            if(tipoDeVajilla!=null){
                System.out.println("Tipo de vajilla encontrado: "+ tipoDeVajilla.getNombreTipoDeVajilla());
            }else{
                System.out.println("Tipo de vajilla no encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el tipo de vajilla: " + e.getMessage());
        }

        return tipoDeVajilla;
    }



    public void eliminarTipoDeVajilla(String nombreTipoDeVajilla) throws SQLException {
        try{
            if(tipoDeVajillaDAO.traerTipoDeVajilla(nombreTipoDeVajilla)!=null){
                tipoDeVajillaDAO.eliminarTipoDeVajilla(nombreTipoDeVajilla);
            }else{
                System.out.println("Tipo de vajilla no encontrado.");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar el tipo de vajilla: " + e.getMessage());
        }

    }

    public List<TipoDeVajilla> traerListaTipoDeVajilla() throws SQLException {
        List<TipoDeVajilla> tipoDeVajillas = null;
        try{
            tipoDeVajillas= tipoDeVajillaDAO.traerListaTipoDeVajilla();
            if(tipoDeVajillas!=null){
                System.out.println("Tipos de vajilla encontradas: " + tipoDeVajillas.toString());
            }else{
                System.out.println("Tipos de vajilla no encontradas.");
            }


        } catch (SQLException e) {
            System.err.println("Error al traer los tipos de vajilla: " + e.getMessage());
        }
        return tipoDeVajillas;
    }
}
