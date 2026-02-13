package org.example.practica1_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage inicio) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        inicio.setTitle("Hello!");
        inicio.setScene(scene);
        inicio.show();
    }

    public void end(Stage salir) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);

        salir.setTitle("Salir");
        salir.setScene(scene);
        salir.show();
    }

    public static void main(String[] args) {
        launch();
    }
}