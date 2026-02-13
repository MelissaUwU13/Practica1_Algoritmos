package org.example.practica1_gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Image fondoImg = new Image(getClass().getResource("/img/solitario.png").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(1024);
        fondo.setFitHeight(500);

        //Botones
        Button btnJugar = new Button("JUGAR");
        Button btnSalir = new Button("SALIR");

        btnJugar.getStyleClass().add("boton-jugar");
        btnSalir.getStyleClass().add("boton-salir");



        btnSalir.setOnAction(e -> stage.close());

        btnJugar.setOnAction(e -> {
            Juego juego = new Juego();
            juego.mostrar(stage);
        });

        HBox menu = new HBox(20, btnJugar, btnSalir);
        menu.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(fondo, menu);


        Scene scene = new Scene(root, 1024, 500);
        scene.getStylesheets().add("/estilo.css");

        stage.setTitle("Solitario");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}