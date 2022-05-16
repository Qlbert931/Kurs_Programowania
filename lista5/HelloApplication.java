package com.example.demo2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class HelloApplication extends Application {

    public static Slider rotateSlider;

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        Scene scene = new Scene(root, 960, 720);

        stage.setTitle("Hello!");

        MenuBar menuBar = new MenuBar();
        Menu figureMenu = new Menu("Figures");
        Menu rotateMenu = new Menu("Rotate");
        Menu infoMenu = new Menu("Info");
        Menu instructionMenu = new Menu("Instruction");
        MenuItem circleMenuItem = new MenuItem("Circle");
        circleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println("Rysujemy koło zaraz!");

                FXCircle circle = new FXCircle(100, 100, 50);
                root.getChildren().add(circle);
                //TODO: Rysowanie koła
            }
        });

        MenuItem rectangleMenuItem = new MenuItem("Rectangle");
        rectangleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println("Rysujemy prostokąt zaraz!");

                FXRectangle rectangle = new FXRectangle(100, 100, 40, 50);
                root.getChildren().add(rectangle);

                //TODO: Rysowanie prostokąta
            }
        });

        MenuItem triangleMenuItem = new MenuItem("Triangle");
        triangleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println("Rysujemy trójkąt zaraz!");

                FXTriangle triangle = new FXTriangle(100, 100, 100, 300, 200,100);
                root.getChildren().add(triangle);
                //TODO: Rysowanie trójkąta
            }
        });

        figureMenu.getItems().addAll(rectangleMenuItem, circleMenuItem, triangleMenuItem);

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

        menuBar.getMenus().addAll(figureMenu, rotateMenu, infoMenu, instructionMenu);
        root.getChildren().addAll(menuBar);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}