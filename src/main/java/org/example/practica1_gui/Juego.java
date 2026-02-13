package org.example.practica1_gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Juego {

    public void mostrar(Stage stage) {

        Image fondoImg = new Image(getClass().getResource("/img/fondo.jpeg").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(1024);
        fondo.setFitHeight(500);

        //Botones
        Button btnMoverPilaBase = new Button("MOVER PILA A BASE");
        Button btnDibujar = new Button("DIBUJAR CARTA");
        Button btnRecargar = new Button("RECARGAR PILA");
        Button btnMoverCartaBase = new Button("MOVER CARTA A BASE");
        Button btnMoverCartaCarta = new Button("MOVER CARTA A OTRA CARTA");
        Button btnMoverPilaCarta = new Button("MOVER PILA A CARTA");
        Button btnSalir = new Button("SALIR");

        btnMoverPilaBase.getStyleClass().add("boton-moverpilabase");
        btnDibujar.getStyleClass().add("boton-dibujar");
        btnRecargar.getStyleClass().add("boton-recargar");
        btnMoverCartaBase.getStyleClass().add("boton-movercartabase");
        btnMoverCartaCarta.getStyleClass().add("boton-movercartacarta");
        btnMoverPilaCarta.getStyleClass().add("boton-moverpilacarta");
        btnSalir.getStyleClass().add("boton-salir");


        btnSalir.setOnAction(e -> stage.close());


        HBox opciones = new HBox(20, btnMoverPilaBase, btnDibujar, btnRecargar, btnMoverCartaBase, btnMoverCartaCarta, btnMoverPilaCarta, btnSalir);

        StackPane root = new StackPane(fondo,opciones);

        Scene escenaJuego = new Scene(root, 1024, 500);
        escenaJuego.getStylesheets().add("/estilo.css");

        stage.setTitle("Solitario - Juego");
        stage.setScene(escenaJuego);
    }
}
