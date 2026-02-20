package org.example.practica1_gui;

import Solitaire.DeckOfCards.Carta;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Solitaire.solitaire.SolitaireGame;

public class Juego {
    private SolitaireGame juego;
    private HBox columnas;
    private VBox columnas1;

    public void mostrar(Stage stage) {

        juego = new SolitaireGame();
        columnas = new HBox(20);
        columnas.setTranslateY(100); // bajarlas un poco

        columnas1 = new VBox(20);
        columnas1.setTranslateY(100);


        Image fondoImg = new Image(getClass().getResource("/img/fondo.jpeg").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(1262);
        fondo.setFitHeight(799);

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

        btnMoverPilaBase.setOnAction(e -> {
            juego.moveWasteToFoundation();
            actualizarVista();
        });

        btnDibujar.setOnAction(e -> {
            juego.drawCards();
            actualizarVista();
        });

        btnRecargar.setOnAction(e -> {
            juego.reloadDrawPile();
            actualizarVista();
        });

        btnSalir.setOnAction(e -> stage.close());


        HBox opciones = new HBox(20, btnMoverPilaBase, btnDibujar, btnRecargar, btnMoverCartaBase, btnMoverCartaCarta, btnMoverPilaCarta, btnSalir);

        HBox columnas = new HBox(20);
        VBox columna1 = new VBox(-60);




        StackPane root = new StackPane();
        root.getChildren().addAll(fondo, columnas, opciones);


        Scene escenaJuego = new Scene(root, 1200, 599);
        escenaJuego.getStylesheets().add("/estilo.css");

        stage.setTitle("Solitario - Juego");
        stage.setScene(escenaJuego);
    }

    private Image obtenerImagenCarta(Carta carta) {

        if (!carta.isFaceup()) {
            return new Image(getClass()
                    .getResource("/cartas/reverso.png")
                    .toExternalForm());
        }

        String nombreArchivo = carta.getValor() + "_" + carta.getPalo().name() + ".png";

        return new Image(getClass()
                .getResource("/cartas/" + nombreArchivo)
                .toExternalForm());
    }

    private void actualizarVista() {
        // borrar cartas actuales
        // volver a dibujar según estado actual del juego
    }
}
