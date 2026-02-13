package org.example.practica1_gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Juego {

    public void mostrar(Stage stage) {

        Image fondoImg = new Image(getClass().getResource("/img/fondo.jpeg").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(1024);
        fondo.setFitHeight(500);

        StackPane root = new StackPane(fondo);

        Scene escenaJuego = new Scene(root, 1024, 500);

        stage.setTitle("Solitario - Juego");
        stage.setScene(escenaJuego);
    }
}
