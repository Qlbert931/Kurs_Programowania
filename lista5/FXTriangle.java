package com.example.demo2;

import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

/**
 * Ta klasa kapsułuje Polygon, używa go jako trójkąt i dodaje do niego odpowiednie zachowania
 */
public class FXTriangle extends Polygon {

    Point2D[] p = new Point2D[3];

    /**
     * Konstruktor tworzący trójkąt z 3 danych punktów
     * @param x1 współrzędna x pierwszego punktu
     * @param y1 współrzędna y pierwszego punktu
     * @param x2 współrzędna x drugiego punktu
     * @param y2 współrzędna y drugiego punktu
     * @param x3 współrzędna x trzeciego punktu
     * @param y3 współrzędna y trzeciego punktu
     */
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

    /**
     * Sprawdza czy dany punkt koliduje z trójkątem
     * @param x współrzędna x danego punktu
     * @param y współrzędna y danego punktu
     * @return true gdy koliduje, false gdy nie
     */
    public boolean isHit(double x, double y) {
        double Area = area (p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
        double Area1 = area (x, y, p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
        double Area2 = area (p[0].getX(), p[0].getY(), x, y, p[2].getX(), p[2].getY());
        double Area3 = area (p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), x, y);

        return Math.round(Area) == Math.round(Area1 + Area2 + Area3);
    }

    /**
     * Oblicza pole trójkąta składającego się z 3 punktów
     * @param x1 współrzędna x pierwszego punktu
     * @param y1 współrzędna y pierwszego punktu
     * @param x2 współrzędna x drugiego punktu
     * @param y2 współrzędna y drugiego punktu
     * @param x3 współrzędna x trzeciego punktu
     * @param y3 współrzędna y trzeciego punktu
     * @return pole trójkąta
     */
    double area(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)) / 2.0);
    }

    /**
     * Dodaje wartość do współrzędnej x każdego punktu Trójkąta
     * @param x wartość
     */
    public void addX(double x)
    {
        for(int i = 0; i < 3; i++)
        {
            p[i] = new Point2D(p[i].getX() + x,p[i].getY() );
        }

        getPoints().setAll(p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());

    }

    /**
     * Dodaje wartość do współrzędnej y każdego punktu Trójkąta
     * @param y wartość
     */
    public void addY(double y)
    {
        for(int i = 0; i < 3; i++)
        {
            p[i] = new Point2D(p[i].getX() ,p[i].getY() + y );
        }
        getPoints().setAll(p[0].getX(), p[0].getY(), p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
    }

    /**
     * Odpowiada za poruszanie trójkątem podczas przeciągnięcia
     */
    class FXTriangleEventHandler implements EventHandler<MouseEvent>{

        FXTriangle triangle;
        private double x;
        private double y;

        /**
         * Przesuwa trójkąt na pozycje z wydarzenia
         * @param event wydarzenie
         */
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

        /**
         * Odpowiada za prawidłowe zarządzanie x,y potrzebnych do poruszania
         * @param event wydarzenie
         */
        @Override
        public void handle(MouseEvent event) {

            triangle = (FXTriangle) event.getSource();

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
                x = event.getSceneX();
                y = event.getSceneY();

                if(event.getClickCount() >= 2)
                {
                    setRotate(Main.rotateSlider.getValue());
                }
            }
            if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
                doMove(event);
            }

        }
    }

    /**
     * Odpowiada za skalowanie trójkąta
     */
    class FXTriangleScrollHandler implements EventHandler<ScrollEvent>{

        FXTriangle triangle;

        /**
         * Skaluje trójkąt o wartość z ScrollEvent
         * @param e ScrollEvent
         */
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

        /**
         * Odpowiada za skalowanie trójkąta
         * @param event wydarzenie
         */
        @Override
        public void handle(ScrollEvent event) {

            triangle = (FXTriangle) event.getSource();

            if (event.getEventType()==ScrollEvent.SCROLL){
                doScale(event);
            }
        }
    }

    /**
     *  Odpowiada za ustawianie wyłączanie kolorowej krawędzi gdy mysz opuści trójkąt
     */
    class FXTriangleMouseExited implements EventHandler<MouseEvent>{

        /**
         * Odpowiada za ustawianie wyłączanie kolorowej krawędzi gdy mysz opuści trójkąt
         * @param event
         */
        @Override
        public void handle(MouseEvent event) {

            FXTriangle triangle = (FXTriangle) event.getSource();

            triangle.setStrokeWidth(0);

        }
    }

    /**
     *  Odpowiada za ustawianie włączanie kolorowej krawędzi gdy mysz wejdzie na trójkąt
     */
    class FXTriangleMouseEntered implements EventHandler<MouseEvent>{

        /**
         * Odpowiada za ustawianie włączanie kolorowej krawędzi gdy mysz wejdzie na trójkąt
         * @param event wydarzenie
         */
        @Override
        public void handle(MouseEvent event) {

            FXTriangle triangle = (FXTriangle) event.getSource();

            triangle.setStrokeType(StrokeType.INSIDE);
            triangle.setStrokeWidth(4);
            Color newColor = (Color) triangle.getFill();
            triangle.setStroke(newColor.invert());

        }
    }
}


