package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.entitys.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CrearNuevoClienteController {

    @FXML
    public TextField txtApellidoCliente;
    @FXML
    public TextField txtTelefonoCliente;
    @FXML
    public TextField txtDomicilioCliente;
    @FXML
    public TextField txtNombreCliente;
    @FXML
    public Button buttonCrear;

    @FXML
    public Label lblError;

    @FXML
    private boolean buttonCrear() {

        String nombre=txtNombreCliente.getText();
        String apellido=txtApellidoCliente.getText();
        String telefono=txtTelefonoCliente.getText();
        String domicilio=txtDomicilioCliente.getText();

        if(nombre==null || apellido==null || telefono==null || domicilio==null){
            lblError.setText("El nombre no puede estar vacio");
            return false;
        }else{
            Cliente cliente=new Cliente(nombre,apellido,telefono,domicilio);

        }


        return true;
    }}
