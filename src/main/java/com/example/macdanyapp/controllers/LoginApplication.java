package com.example.macdanyapp.controllers;

import com.example.macdanyapp.database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class LoginApplication extends Application {




    @Override
    public void start(Stage stage) throws IOException {
        Connection connection = DatabaseConnection.getConnection();

        // Verifica si la conexión fue exitosa
        if (connection != null) {
            System.out.println("Conexión a la base de datos exitosa.");
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("/com/example/macdanyapp/template/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 340);
        stage.setTitle("MacDany's");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}