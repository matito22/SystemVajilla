package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.TipoDeUsuario;
import com.example.macdanyapp.entitys.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TablaOpcionesController {

    @FXML
    private StackPane vistaPrincipal;
    @FXML
    private Button mostrarNuevoAlquiler;
    private Usuario usuario;

    @FXML
    private Label laberErrorAdministrador;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }



    @FXML
    private void mostrarNuevoAlquiler() throws IOException {
        cargarVista("/com/example/macdanyapp/template/NuevoAlquiler.fxml");
    }

    @FXML
    private void mostrarAlquileresActivos() throws IOException {
        cargarVista("/com/example/macdanyapp/template/AlquileresActivos.fxml");
    }

    @FXML
    private void mostrarAlquileresPendientes() throws IOException {
        cargarVista("/com/example/macdanyapp/template/AlquileresPendientes.fxml");
    }

    @FXML
    private void mostrarAlquileresFinalizados() throws IOException {
        cargarVista("/com/example/macdanyapp/template/AlquileresFinalizados.fxml");
    }

    @FXML
    private void crearNuevoCliente() throws IOException {
        cargarVista("/com/example/macdanyapp/template/CrearNuevoCliente.fxml");
    }



    @FXML
    private void crearUsuario() throws IOException {
        if(usuario.getTipoDeUsuario()!=TipoDeUsuario.USUARIO){
            cargarVista("/com/example/macdanyapp/template/CrearUsuario.fxml");
        }else{
            laberErrorAdministrador.setVisible(true);
        }
    }

    // Método genérico para cargar cualquier vista en el StackPane
    private void cargarVista(String fxmlPath) throws IOException {
        Parent vista = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        vistaPrincipal.getChildren().clear();  // Limpiar vista anterior
        vistaPrincipal.getChildren().add(vista);  // Mostrar la nueva vista
    }




}
