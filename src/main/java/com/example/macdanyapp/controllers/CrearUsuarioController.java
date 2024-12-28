package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.TipoDeUsuario;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.services.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.EventObject;

public class CrearUsuarioController {


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


    @FXML
    public void buttonCrear(ActionEvent event) throws SQLException, IOException {
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
            txtNuevoUsuario.clear();
            txtNuevaPassword.clear();
        }


    }


}

