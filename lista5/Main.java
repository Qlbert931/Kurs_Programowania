package com.example.demo2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Główna klasa ustawiająca wszystko
 */
public class Main extends Application {

    /**
     * slider przechowujący w sobie kąt na który chcemy obracać figury
     */
    public static Slider rotateSlider;
    public static ClickPane root;
    public static ColorPicker colorPicker;

    /**
     * metoda startowa
     * @param stage scena
     * @throws IOException wyjątek
     */
    @Override
    public void start(Stage stage) throws IOException {
        root = new ClickPane();
        Scene scene = new Scene(root, 960, 720);

        stage.setTitle("Zadanie 5");

        MenuBar menuBar = new MenuBar();
        Menu figureMenu = new Menu("Figures");
        Menu rotateMenu = new Menu("Rotate");
        Menu infoMenu = new Menu("Info");
        MenuItem circleMenuItem = new MenuItem("Circle");
        circleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {

                if(root.getAmountOfPoints() >= 2)
                {
                    int lastIndex = root.getAmountOfPoints() - 1;
                    double x1 = root.getPointAt(lastIndex - 1).getX();
                    double y1 = root.getPointAt(lastIndex - 1).getY();
                    double x2 = root.getPointAt(lastIndex).getX();
                    double y2 = root.getPointAt(lastIndex).getY();

                    double radius = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

                    FXCircle circle = new FXCircle(x1, y1, radius);
                    root.getChildren().add(circle);

                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Problem");
                    alert.setHeaderText(null);
                    alert.setContentText(
                            "Musisz najpierw kliknąć w 2 punktach");

                    alert.showAndWait();
                }
                root.clearBuffer();
            }
        });

        MenuItem rectangleMenuItem = new MenuItem("Rectangle");
        rectangleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if(root.getAmountOfPoints() >= 2)
                {
                    int lastIndex = root.getAmountOfPoints() - 1;
                    double x1 = root.getPointAt(lastIndex - 1).getX();
                    double y1 = root.getPointAt(lastIndex - 1).getY();
                    double x2 = root.getPointAt(lastIndex).getX();
                    double y2 = root.getPointAt(lastIndex).getY();

                    double x = Math.min(x1, x2);
                    double y = Math.min(y1, y2);
                    double width = Math.abs(x1 - x2);
                    double height = Math.abs(y1 - y2);

                    FXRectangle rectangle = new FXRectangle(x, y, width, height);
                    root.getChildren().add(rectangle);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Problem");
                    alert.setHeaderText(null);
                    alert.setContentText(
                            "Musisz najpierw kliknąć w 2 punktach");

                    alert.showAndWait();
                }
                root.clearBuffer();


            }
        });

        MenuItem triangleMenuItem = new MenuItem("Triangle");
        triangleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if(root.getAmountOfPoints() >= 3)
                {
                    int lastIndex = root.getAmountOfPoints() - 1;
                    double x1 = root.getPointAt(lastIndex).getX();
                    double y1 = root.getPointAt(lastIndex).getY();
                    double x2 = root.getPointAt(lastIndex - 1).getX();
                    double y2 = root.getPointAt(lastIndex - 1).getY();
                    double x3 = root.getPointAt(lastIndex - 2).getX();
                    double y3 = root.getPointAt(lastIndex - 2).getY();

                    FXTriangle triangle = new FXTriangle(x1, y1, x2, y2, x3,y3);
                    root.getChildren().add(triangle);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Problem");
                    alert.setHeaderText(null);
                    alert.setContentText(
                            "Musisz najpierw kliknąć w 3 punktach");

                    alert.showAndWait();
                }
                root.clearBuffer();

            }
        });

        figureMenu.getItems().addAll(rectangleMenuItem, circleMenuItem, triangleMenuItem);

        MenuItem instructionMenuItem = new MenuItem("Instruction");
        instructionMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Instruction");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Aby narysować:" +
                        "\nKoło, kliknij 2 razy w jakieś punkty gdzie 1 punkt określa środek koła a drugi promień koła, następnie w zakładce Figure wybierz Circle" +
                        "\nProstokąt, klikniej 2 razy w jakieś punkty gdzie punkty oznaczają przeciwległe wierzchołki prostokąta, następnie w zakładce Figure wybierz Rectangle " +
                        "\nTrójkąt, kliknij 3 razy w jakieś punkty gdzie punkty oznaczają wierzchołki trójkąta, następnie w zakładce Figure wybierz Triangle" +
                        "\nKlikając na figure i przeciągając ją możesz ją przesunąć w inne miejsce" +
                        "\nKlikając na figure prawym możesz zmienić jej kolor" +
                        "\nZa pomocą Scrolla możesz zwiększać i zmniejszać figure na którą wskazujesz" +
                        "\nW zakładce Rotate możesz ustawić kąt obrotu, a następnie klikając dwukrotnie na figure możesz ją obrócić o dany kąt" +
                        "\nW zakładce Info możesz znaleźć informacje o Autorze oraz Instrukcje");

                alert.showAndWait();
            }
        });

        MenuItem aboutAuthorMenuItem = new MenuItem("About Author");
        aboutAuthorMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About Author");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Nazwa: Zadanie 5" +
                        "\nPrzeznaczenie: Create and modify figures" +
                        "\nAutor: Albert Kołodziejski"
                        );

                alert.showAndWait();
            }
        });

        infoMenu.getItems().addAll(instructionMenuItem, aboutAuthorMenuItem);

        rotateSlider = new Slider();
        rotateSlider.setShowTickMarks(true);
        rotateSlider.setMajorTickUnit(15);
        rotateSlider.setMin(0);
        rotateSlider.setMax(360);
        rotateSlider.setMinWidth(350);
        System.out.println(rotateSlider.getStyle());



        MenuItem rotateMenuItem = new MenuItem();
        rotateMenuItem.setGraphic(rotateSlider);

        rotateMenu.getItems().addAll(rotateMenuItem);

        menuBar.getMenus().addAll(figureMenu, rotateMenu, infoMenu);
        root.getChildren().addAll(menuBar);


        stage.setScene(scene);
        stage.show();
    }

    /**
     * metoda główna wywołująca launch();
     * @param args argumenty
     */
    public static void main(String[] args) {
        launch();
    }
}