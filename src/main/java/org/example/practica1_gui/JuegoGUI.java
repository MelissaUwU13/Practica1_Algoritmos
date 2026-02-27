package org.example.practica1_gui;

import Solitaire.SolitaireGame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class JuegoGUI {

    private Stage stage;
    private HBox columnas;
    private ArrayList<ColumnaGUI> columnasGUI = new ArrayList<>();

    private MazoGUI mazoGUI;
    private BasesGUI basesGUI;

    private JuegoControlador controlador;

    public JuegoGUI(Stage stage) {
        this.stage = stage;
    }

    public void setControlador(JuegoControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrar() {

        //creamos la imagen de fondo
        Image fondoImg = new Image(getClass().getResource("/img/fondo.jpeg").toExternalForm());
        ImageView fondo = new ImageView(fondoImg);
        fondo.setFitWidth(1262);
        fondo.setFitHeight(799);

        columnas = new HBox(40);
        columnas.setAlignment(Pos.CENTER);

        for (int i = 0; i < 7; i++) {
            ColumnaGUI colGUI = new ColumnaGUI(i + 1, controlador);
            columnasGUI.add(colGUI);
            columnas.getChildren().add(colGUI.getContenedor());
        }

        mazoGUI = new MazoGUI(controlador);
        basesGUI = new BasesGUI(controlador);

        HBox zonaSuperior = new HBox(40,
                mazoGUI.getContenedor(),
                basesGUI.getContenedor());

        zonaSuperior.setAlignment(Pos.CENTER);

        BorderPane tablero = new BorderPane();
        tablero.setTop(zonaSuperior);
        tablero.setCenter(columnas);

        StackPane root = new StackPane();
        root.getChildren().addAll(fondo, tablero);


        Scene scene = new Scene(root, 1200, 600);

        stage.setTitle("Solitario");
        stage.setScene(scene);
        stage.show();
    }

    public void actualizar(SolitaireGame juego) {

        for (int i = 0; i < 7; i++) {
            columnasGUI.get(i).actualizar(juego.getTableau().get(i));
        }

        mazoGUI.actualizar(juego);
        basesGUI.actualizar(juego);
    }
}