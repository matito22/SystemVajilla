package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.TipoDeUsuario;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.entitys.UsuarioAwareController;
import com.example.macdanyapp.services.UsuarioService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EventObject;

public class CrearUsuarioController implements UsuarioAwareController {

    @FXML
    public Button buttonVolver;
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblUsuarioEnUso;

    @FXML
    private Label lblUsuarioCorrecto;


    @FXML
    private TextField txtNuevoUsuario;

    @FXML
    private PasswordField txtNuevaPassword;

    private Usuario usuario;

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    @FXML
    public void buttonCrear() throws SQLException, IOException {
        lblUsuarioEnUso.setVisible(false);
        lblUsuarioCorrecto.setVisible(false);

        String nuevoUsuario = txtNuevoUsuario.getText();
        String nuevaPassword = txtNuevaPassword.getText();

        Usuario usuarioDB = new Usuario(nuevoUsuario, nuevaPassword, TipoDeUsuario.USUARIO);
        UsuarioService usuarioService = new UsuarioService();

        if (usuarioService.traerUsuario(nuevoUsuario) != null || nuevoUsuario==null || nuevaPassword==null) {
            lblUsuarioEnUso.setText("Usuario en uso");
            lblUsuarioEnUso.setVisible(true);
            lblUsuarioCorrecto.setVisible(false);
        } else {
            usuarioService.insertUsuario(usuarioDB);
            lblUsuarioCorrecto.setText("Usuario creado");
            lblUsuarioCorrecto.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> lblUsuarioCorrecto.setVisible(false));
            pause.play();
            txtNuevoUsuario.clear();
            txtNuevaPassword.clear();
        }


    }

    @FXML
    public void buttonVolver(ActionEvent event) throws IOException {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/macdanyapp/template/TablaOpciones.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la nueva escena
        Object controller = loader.getController();
        if (controller instanceof UsuarioAwareController) {
            // Pasar el usuario al nuevo controlador
            ((UsuarioAwareController) controller).setUsuario(usuario);
        }

        // Cambiar de escena
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


}

