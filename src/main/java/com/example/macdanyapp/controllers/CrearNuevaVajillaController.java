package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.TipoDeVajilla;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.services.TipoDeVajillaService;
import com.example.macdanyapp.services.VajillaService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.sql.SQLException;

public class CrearNuevaVajillaController {

    @FXML
    public TextField txtNombreTipoDeVajilla;
    @FXML
    public Label lblTipoVajillaAceptado;
    @FXML
    public TextField txtModeloVajilla;
    @FXML
    public TextField txtColorVajilla;
    @FXML
    public TextField txtTamanioVajilla;
    @FXML
    public TextField txtPrecioIndividualVajilla;
    @FXML
    public Label lblError;
    @FXML
    public Label lblCorrecto;


    TipoDeVajillaService tipoDeVajillaService=new TipoDeVajillaService();
    VajillaService vajillaService=new VajillaService();

    @FXML
    public void buttonCrearVajilla() throws SQLException {
        String nombreNuevoTipoDeVajilla = txtNombreTipoDeVajilla.getText();
        TipoDeVajilla tipoDeVajilla=new TipoDeVajilla(nombreNuevoTipoDeVajilla);
        tipoDeVajillaService.insertTipoDeVajilla(tipoDeVajilla);

        String modeloNuevoTipoDeVajilla = txtModeloVajilla.getText();
        String colorNuevoTipoDeVajilla = txtColorVajilla.getText();
        String tamanioNuevoTipoDeVajilla = txtTamanioVajilla.getText();
        Float precioNuevoTipoDeVajilla = Float.parseFloat(txtPrecioIndividualVajilla.getText());

        //SE CREA EL TIPO DE VAJILLA, PERO NO LA VAJILLA, REVISAAAAAAR

         Vajilla vajilla=new Vajilla(modeloNuevoTipoDeVajilla,colorNuevoTipoDeVajilla,precioNuevoTipoDeVajilla,tamanioNuevoTipoDeVajilla,tipoDeVajilla);

       try{
           vajillaService.insertVajilla(vajilla);
           lblCorrecto.setVisible(true);
           PauseTransition pause = new PauseTransition(Duration.seconds(3));
           pause.setOnFinished(event -> lblCorrecto.setVisible(false));
           pause.play();

       } catch (Exception e) {
           lblCorrecto.setVisible(false);
           lblError.setVisible(true);

       }



    }
}
