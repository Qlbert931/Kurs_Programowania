package com.example.demo2;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * Ta klasa kapsułuje Circle i dodaje do niego odpowiednie zachowania
 */
public class FXCircle extends Circle {

    /**
     * Konstruktor
     * @param x pozycja koła na współrzędnej x
     * @param y pozycja koła na współrzędnej y
     * @param radius promień koła
     */
    public FXCircle(double x, double y, double radius) {
        super(x, y, radius);
        setFill(Color.GREEN);
        setOnMouseClicked(new FXCircleEventHandler());
        setOnMouseDragged(new FXCircleEventHandler());
        setOnScroll(new FXCircleScrollHandler());
        setOnMouseExited(new FXCircleMouseExited());
        setOnMouseEntered(new FXCircleMouseEntered());
    }

    /**
     * Sprawdza czy dany punkt koliduje z kołem
     * @param x współrzędna x danego punktu
     * @param y współrzędna y danego punktu
     * @return true gdy koliduje, false gdy nie
     */
    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x,y);
    }

    /**
     * Dodaje wartość do współrzędnej x środka koła
     * @param x wartość do dodania
     */
    public void addX(double x) {
        setCenterX(getCenterX()+x);
    }

    /**
     * Dodaje wartość do współrzędnej y środka koła
     * @param y wartość do dodania
     */
    public void addY(double y) {
        setCenterY(getCenterY()+y);
    }

    /**
     * Dodaje wartość do promienia koła
     * @param r wartość do dodania
     */
    public void addRadius(double r)
    {
        setRadius(getRadius() + r);
    }


    /**
     * Odpowiada za poruszanie koła podczas przeciągnięcia
     */
    class FXCircleEventHandler implements EventHandler<MouseEvent>{

        FXCircle circle;
        private double x;
        private double y;

        /**
         * Przesuwa circle na pozycje z wydarzenia
         * @param event wydarzenie
         */
        private void doMove(MouseEvent event) {

            double dx = event.getX() - x;
            double dy = event.getY() - y;

            if(isHit(x,y))
            {
                circle.addX(dx);
                circle.addY(dy);
            }

            x += dx;
            y += dy;
        }


        /**
         * Odpowiada za prawidłowe zarządzanie x,y potrzebnych do poruszania
         * @param event wydarzenie
         */
        @Override
        public void handle(MouseEvent event) {
            circle = (FXCircle) event.getSource();

            if (event.getEventType()==MouseEvent.MOUSE_CLICKED){
                Main.root.getChildren().remove(Main.colorPicker);
                if(event.getButton().equals(MouseButton.SECONDARY))
                {
                    Main.colorPicker = new ColorPicker((Color) getFill());
                    Main.colorPicker.setTranslateX(event.getSceneX());
                    Main.colorPicker.setTranslateY(event.getSceneY());
                    Main.colorPicker.setOnAction(new EventHandler() {
                        public void handle(Event t) {
                            setFill(Main.colorPicker.getValue());
                            Main.root.getChildren().remove(Main.colorPicker);
                            Main.colorPicker = null;
                        }
                    });
                    Main.root.getChildren().add(Main.colorPicker);
                }
                x = event.getX();
                y = event.getY();

            }
            if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
                doMove(event);
            }

        }
    }

    /**
     * Odpowiada za ustawianie wyłączanie kolorowej krawędzi gdy mysz opuści koło
     */
    class FXCircleMouseExited implements EventHandler<MouseEvent>{

        /**
         * Odpowiada za ustawianie wyłączanie kolorowej krawędzi gdy mysz opuści koło
         * @param event wydarzenie
         */
        @Override
        public void handle(MouseEvent event) {

            FXCircle circle = (FXCircle) event.getSource();

            circle.setStrokeWidth(0);
        }
    }

    /**
     * Odpowiada za ustawianie włączanie kolorowej krawędzi gdy mysz wejdzie na koło
     */
    class FXCircleMouseEntered implements EventHandler<MouseEvent>{

        /**
         * Odpowiada za ustawianie włączanie kolorowej krawędzi gdy mysz wejdzie na Circle
         * @param event wydarzenie
         */
        @Override
        public void handle(MouseEvent event) {

            FXCircle circle = (FXCircle) event.getSource();

            circle.setStrokeType(StrokeType.INSIDE);
            circle.setStrokeWidth(4);
            Color newColor = (Color) circle.getFill();
            circle.setStroke(newColor.invert());

        }
    }

    /**
     * Odpowiada za skalowanie koła
     */
    class FXCircleScrollHandler implements EventHandler<ScrollEvent> {

        FXCircle circle;

        /**
         * Skaluje koło o wartość z {@link ScrollEvent}lEvent
         * @param e ScrollEvent
         */
        private void doScale(ScrollEvent e) {

            double x = e.getX();
            double y = e.getY();

            if (circle.isHit(x, y)) {
                circle.addRadius(e.getDeltaY()*0.1);
                circle.setCenterX(x);
                circle.setCenterY(y);
            }
        }

        /**
         * Odpowiada za skalowanie koła
         * @param event wydarzenie
         */
        @Override
        public void handle(ScrollEvent event) {

            circle = (FXCircle) event.getSource();

            if (event.getEventType()==ScrollEvent.SCROLL){
                doScale(event);
            }
        }
    }
}
