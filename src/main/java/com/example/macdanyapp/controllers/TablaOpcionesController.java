package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.TipoDeUsuario;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.entitys.UsuarioAwareController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TablaOpcionesController implements UsuarioAwareController {


    @FXML
    private Button mostrarNuevoAlquiler;
    @FXML
    private Button mostrarAlquileresActivos;
    @FXML
    private Button mostrarAlquileresPendientes;
    @FXML
    private Button mostrarAlquileresFinalizados;
    @FXML
    private Button mostrarStockDisponible;
    @FXML
    private Button crearUsuario;
    @FXML
    private Button crearNuevoCliente;

    @FXML
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
        cambiarEscena("/com/example/macdanyapp/template/NuevoAlquiler.fxml");
    }

    @FXML
    private void mostrarAlquileresActivos() throws IOException {
        cambiarEscena("/com/example/macdanyapp/template/AlquileresActivos.fxml");
    }

    @FXML
    private void mostrarAlquileresPendientes() throws IOException {
        cambiarEscena("/com/example/macdanyapp/template/AlquileresPendientes.fxml");
    }

    @FXML
    private void mostrarAlquileresFinalizados() throws IOException {
        cambiarEscena("/com/example/macdanyapp/template/AlquileresFinalizados.fxml");
    }

    @FXML
    private void mostrarStockDisponible() throws IOException {
        cambiarEscena("/com/example/macdanyapp/template/Stock.fxml");
    }

    @FXML
    private void crearNuevoCliente() throws IOException {
        cambiarEscena("/com/example/macdanyapp/template/CrearNuevoCliente.fxml");
    }



    @FXML
    private void crearUsuario() throws IOException {
        if(usuario.getTipoDeUsuario()!=TipoDeUsuario.USUARIO){
            cambiarEscena("/com/example/macdanyapp/template/CrearUsuario.fxml");
        }else{
            laberErrorAdministrador.setVisible(true);
        }
    }


    @FXML
    private void finalizarAlquiler() throws IOException {
        cambiarEscena(("/com/example/macdanyapp/template/FinalizarAlquiler.fxml"));
    }

    private void cambiarEscena(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        // Verificar si el controlador de la nueva escena necesita el objeto `usuario`
        Object controller = loader.getController();
        if (controller instanceof UsuarioAwareController) {
            ((UsuarioAwareController) controller).setUsuario(usuario);
        }

        Stage stage = (Stage) mostrarNuevoAlquiler.getScene().getWindow();
        Scene scene = new Scene(root, 1600, 900);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }




}
