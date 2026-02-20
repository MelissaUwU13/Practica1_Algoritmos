package org.example.practica1_gui;

import DeckOfCards.Carta;
import DeckOfCards.CartaInglesa;
import Solitaire.TableauDeck;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Solitaire.SolitaireGame;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class Juego {
    private SolitaireGame juego;
    private HBox columnas;
    private VBox columnas1;

    public void mostrar(Stage stage) {

        juego = new SolitaireGame();
        columnas = new HBox(40);
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
        opciones.setAlignment(Pos.BOTTOM_CENTER);


        Image imgVacio = new Image(getClass().getResourceAsStream("/Cartas/vacio.png"));
        Image Reverso = new Image(getClass().getResourceAsStream("/Cartas/reverso.png"));

        ImageView espacioMazo = new ImageView(Reverso);
        espacioMazo.setFitWidth(50);
        espacioMazo.setPreserveRatio(true);

        ImageView descarte = new ImageView(imgVacio);
        descarte.setFitWidth(50);
        descarte.setPreserveRatio(true);

        HBox bases = new HBox(20);
        bases.setAlignment(Pos.CENTER);

        for(int i = 0; i < 4; i++){
            ImageView base = new ImageView(imgVacio);
            base.setFitWidth(50);
            base.setPreserveRatio(true);
            bases.getChildren().add(base);
        }


        HBox zonaSuperior = new HBox(40);
        zonaSuperior.setAlignment(Pos.CENTER);

        zonaSuperior.getChildren().addAll(espacioMazo, descarte, bases);

        BorderPane tablero = new BorderPane();

        tablero.setTop(zonaSuperior);
        tablero.setCenter(columnas);
        tablero.setBottom(opciones);

        BorderPane.setAlignment(opciones, Pos.CENTER);
        BorderPane.setMargin(opciones, new Insets(20));

        StackPane root = new StackPane();
        root.getChildren().addAll(fondo, tablero);

        actualizarVista();

        Scene escenaJuego = new Scene(root, 1200, 599);
        escenaJuego.getStylesheets().add("/estilo.css");

        stage.setTitle("Solitario - Juego");
        stage.setScene(escenaJuego);
    }

    private void actualizarVista() {

        columnas.getChildren().clear();

        for (int i = 0; i < 7; i++) {

            VBox col = new VBox(-45);

            TableauDeck colLogica = juego.getTableau().get(i);

            for (CartaInglesa carta : colLogica.getCards()) {

                ImageView cartaView = new ImageView(obtenerImagenCarta(carta));
                cartaView.setFitWidth(50);
                cartaView.setPreserveRatio(true);

                col.getChildren().add(cartaView);
            }

            columnas.getChildren().add(col);
            columnas.setAlignment(Pos.CENTER);
        }
    }

    private Image obtenerImagenCarta(Carta carta) {
        String nombreArchivo;

        if (!carta.isFaceup()) {
            nombreArchivo = "reverso.png";
        } else {
            nombreArchivo = carta.getValor() + "_" + carta.getPalo().name() + ".png";
        }

        return new Image(getClass().getResourceAsStream("/Cartas/" + nombreArchivo));
    }
}
