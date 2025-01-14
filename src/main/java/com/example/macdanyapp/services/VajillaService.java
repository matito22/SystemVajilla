package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.entitys.TipoDeVajilla;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.repositories.ClienteDAO;
import com.example.macdanyapp.repositories.VajillaDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VajillaService {
    VajillaDAO vajillaDAO;
    Vajilla vajillaExistente;

    public VajillaService() {
        this.vajillaDAO = new VajillaDAO(); // Inicializas clienteDAO
    }

    public void insertVajilla(Vajilla vajilla) throws SQLException {
        try {
            // Verifica que el tipo de vajilla exista en la base de datos
            TipoDeVajillaService tipoDeVajillaService=new TipoDeVajillaService();
            TipoDeVajilla tipoDeVajilla = tipoDeVajillaService.traerTipoDeVajillaPorId(vajilla.getTipoDeVajilla().getIdTipoDeVajilla());
            if (tipoDeVajilla == null) {
                throw new SQLException("El idTipoDeVajilla no existe en la tabla tipo_de_vajilla: " + vajilla.getTipoDeVajilla().getIdTipoDeVajilla());
            }

            // Verifica si ya existe una vajilla con ese tipo
            if (vajillaDAO.traerVajillaPorTipo(vajilla.getTipoDeVajilla().getIdTipoDeVajilla()) != null) {
                System.out.println("La vajilla ya existe");
            } else {
                // Inserta la nueva vajilla
                vajillaDAO.insertVajilla(vajilla);
                System.out.println("La vajilla se agregó correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar la vajilla: " + e.getMessage());
            throw e; // Relanza la excepción para manejarla en niveles superiores
        }
    }


    public Vajilla traerVajilla(long idVajilla) throws SQLException {
        try {
            vajillaExistente=vajillaDAO.traerVajilla(idVajilla);
            if(vajillaExistente!=null){
                System.out.println("Vajilla encontrada: " + vajillaExistente);

            }else{
                System.out.println("Vajilla no encontrada.");
            }
        }catch (SQLException e) {
            System.err.println("Error al traer la vajilla: " + e.getMessage());
        }
        return vajillaExistente;
    }

    public Vajilla traerVajillaPorTipo(int idTipoDeVajilla) throws SQLException {
        try {
            vajillaExistente=vajillaDAO.traerVajillaPorTipo(idTipoDeVajilla);

            if(vajillaExistente!=null){
                System.out.println("Vajilla encontrada: " + vajillaExistente);

            }else{
                System.out.println("Vajilla no encontrada.");
            }
        }catch (SQLException e) {
            System.err.println("Error al traer la vajilla: " + e.getMessage());
        }
        return vajillaExistente;
    }


    public void eliminarVajilla(long idVajilla) throws SQLException {
        try{
            if(vajillaDAO.traerVajilla(idVajilla)!=null){
                vajillaDAO.eliminarVajilla(idVajilla);
            }else{
                System.out.println("Vajilla no encontrada.");
            }
        }catch(SQLException e){
            System.err.println("Error al eliminar la vajilla: " + e.getMessage());
        }
    }


    public void modificarVajilla(Long idVajilla,TipoDeVajilla tipoDeVajilla,String modelo,String color,String tamaño,Float precioIndividual) throws SQLException {
       vajillaDAO.modificarVajilla(idVajilla,tipoDeVajilla,modelo,color,tamaño,precioIndividual);
    }

    public List<Vajilla> traerListaVajilla() throws SQLException {
        List<Vajilla> vajillaLista = null;
        try{
            vajillaLista= vajillaDAO.traerListaVajilla();
            if(vajillaLista!=null){
                System.out.println("Vajillas encontradas: " + vajillaLista);
            }else{
                System.out.println("Vajillas no encontradas.");
            }


        } catch (SQLException e) {
            System.err.println("Error al traer las vajillas: " + e.getMessage());
        }
        return vajillaLista;
    }
}
