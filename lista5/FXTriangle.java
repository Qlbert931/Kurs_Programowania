package com.example.demo2;

import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;


public class FXTriangle extends Polygon {

    Point2D[] p = new Point2D[3];
    public FXTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        super();
        getPoints().addAll(x1, y1, x2, y2, x3, y3);
        p[0] = new Point2D(x1, y1);
        p[1] = new Point2D(x2, y2);
        p[2] = new Point2D(x3, y3);
        setFill(Color.BLUE);
        setOnMouseClicked(new FXTriangleEventHandler());
        setOnMouseDragged(new FXTriangleEventHandler());
        setOnScroll(new FXTriangleScrollHandler());
        setOnMouseExited(new FXTriangleMouseExited());
        setOnMouseEntered(new FXTriangleMouseEntered());
    }

    /// Metoda sprawdza czy najechalismy na figure
    public boolean isHit(double x, double y) {
        double Area = area (p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
        double Area1 = area (x, y, p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
        double Area2 = area (p[0].getX(), p[0].getY(), x, y, p[2].getX(), p[2].getY());
        double Area3 = area (p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), x, y);

        return Math.round(Area) == Math.round(Area1 + Area2 + Area3);
    }

    double area(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)) / 2.0);
    }

    // Zmiana wspolrzednej x prostakata
    public void addX(double x)
    {
        for(int i = 0; i < 3; i++)
        {
            p[i] = new Point2D(p[i].getX() + x,p[i].getY() );
        }

        getPoints().setAll(p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());

    }

    // Zmiana wspolrzednej y prostakata
    public void addY(double y)
    {
        for(int i = 0; i < 3; i++)
        {
            p[i] = new Point2D(p[i].getX() ,p[i].getY() + y );
        }
        getPoints().setAll(p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
    }


    // Implementacja przesuwania
    class FXTriangleEventHandler implements EventHandler<MouseEvent>{

        FXTriangle triangle;
        private double x;
        private double y;

        private void doMove(MouseEvent event) {

            double dx = event.getSceneX() - x;
            double dy = event.getSceneY() - y;

            if (triangle.isHit(x, y)) {
                triangle.addX(dx);
                triangle.addY(dy);

                Point2D center = new Point2D((p[0].getX() + p[1].getX() + p[2].getX()) / 3.0, (p[0].getY() + p[1].getY() + p[2].getY()) / 3.0);


                triangle.addX(x - center.getX());
                triangle.addY(y - center.getY());

                for(int k = 0; k<3; k++)
                {
                    Point2D diff = new Point2D(p[k].getX() - center.getX(), p[k].getY() - center.getY());

                    p[k] = new Point2D(center.getX() + diff.getX(), center.getY() + diff.getY());
                }

                triangle.getPoints().setAll(p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());

            }
            x += dx;
            y += dy;
        }


        @Override
        public void handle(MouseEvent event) {

            triangle = (FXTriangle) event.getSource();

            if (event.getEventType()==MouseEvent.MOUSE_CLICKED){
                x = event.getSceneX();
                y = event.getSceneY();

                if(event.getClickCount() >= 2)
                {
                    setRotate(getRotate() + HelloApplication.rotateSlider.getValue());
                }
            }
            if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
                doMove(event);
            }

        }
    }

    // Implementacja scrollowania
    class FXTriangleScrollHandler implements EventHandler<ScrollEvent>{

        FXTriangle triangle;

        private void doScale(ScrollEvent e) {

            double x = e.getX();
            double y = e.getY();

            if (triangle.isHit(x, y)) {
                Point2D center = new Point2D((p[0].getX() + p[1].getX() + p[2].getX()) / 3.0, (p[0].getY() + p[1].getY() + p[2].getY()) / 3.0);


                triangle.addX(x - center.getX());
                triangle.addY(y - center.getY());
                for(int k = 0; k<3; k++)
                {
                    Point2D diff = new Point2D(p[k].getX() - center.getX(), p[k].getY() - center.getY());

                    p[k] = new Point2D(center.getX() + diff.getX() + diff.getX()*e.getDeltaY()*0.001, center.getY() + diff.getY() + diff.getY()*e.getDeltaY()*0.001);
                }

                triangle.getPoints().setAll(p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());

            }
        }

        @Override
        public void handle(ScrollEvent event) {

            triangle = (FXTriangle) event.getSource();

            if (event.getEventType()==ScrollEvent.SCROLL){
                doScale(event);
            }
        }
    }
}


class FXTriangleMouseExited implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent event) {

        FXTriangle triangle = (FXTriangle) event.getSource();

        triangle.setStrokeWidth(0);

    }
}

class FXTriangleMouseEntered implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent event) {

        FXTriangle triangle = (FXTriangle) event.getSource();

        triangle.setStrokeType(StrokeType.INSIDE);
        triangle.setStrokeWidth(4);
        Color newColor = (Color) triangle.getFill();
        triangle.setStroke(newColor.invert());

    }
}