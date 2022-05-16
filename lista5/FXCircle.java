package com.example.demo2;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class FXCircle extends Circle {

    //TODO: Dodaj enum tworzenia i switche w eventach
    public FXCircle(double x, double y, double radius) {
        super(x, y, radius);
        setFill(Color.GREEN);
        setOnMouseClicked(new FXCircleEventHandler());
        setOnMouseDragged(new FXCircleEventHandler());
        setOnScroll(new FXCircleScrollHandler());
        setOnMouseExited(new FXCircleMouseExited());
        setOnMouseEntered(new FXCircleMouseEntered());
    }

    /// Metoda sprawdza czy najechalismy na figure
    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x,y);
    }

    // Zmiana wspolrzednej x prostakata
    public void addX(double x) {
        setCenterX(getCenterX()+x);
    }

    // Zmiana wspolrzednej y prostakata
    public void addY(double y) {
        setCenterY(getCenterY()+y);
    }

    public void addRadius(double r)
    {
        setRadius(getRadius() + r);
    }


    // Implementacja przesuwania
    class FXCircleEventHandler implements EventHandler<MouseEvent>{

        FXCircle circle;
        private double x;
        private double y;

        private void doMove(MouseEvent event) {

            double dx = event.getX() - x;
            double dy = event.getY() - y;

            // Jesli nacisnelismy na elipse
            if (circle.isHit(x, y)) {
                circle.addX(dx);
                circle.addY(dy);
            }
            x += dx;
            y += dy;
        }


        @Override
        public void handle(MouseEvent event) {
            circle = (FXCircle) event.getSource();

            if (event.getEventType()==MouseEvent.MOUSE_CLICKED){
                x = event.getX();
                y = event.getY();

            }
            if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
                doMove(event);
            }


        }
    }

    // Implementacja scrollowania
    class FXCircleScrollHandler implements EventHandler<ScrollEvent> {

        FXCircle circle;

        private void doScale(ScrollEvent e) {

            double x = e.getX();
            double y = e.getY();

            if (circle.isHit(x, y)) {
                circle.addRadius(e.getDeltaY()*0.1);
                circle.setCenterX(x);
                circle.setCenterY(y);
            }
        }

        @Override
        public void handle(ScrollEvent event) {

            circle = (FXCircle) event.getSource();

            if (event.getEventType()==ScrollEvent.SCROLL){
                doScale(event);
            }
        }
    }
}


class FXCircleMouseExited implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent event) {

        FXCircle rectangle = (FXCircle) event.getSource();

        rectangle.setStrokeWidth(0);

    }
}

class FXCircleMouseEntered implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent event) {

        FXCircle circle = (FXCircle) event.getSource();

        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStrokeWidth(4);
        Color newColor = (Color) circle.getFill();
        circle.setStroke(newColor.invert());

    }
}