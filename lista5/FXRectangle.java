package com.example.demo2;

import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;


/**
 * Ta klasa kapsułuje Rectangle i dodaje do niego odpowiednie zachowania
 */
public class FXRectangle extends Rectangle {
    /**
     * Konstruktor
     * @param x współrzędna x lewego górnego wierzchołka
     * @param y współrzędna y lewego górnego wierzchołka
     * @param width szerokość kwadratu
     * @param height wysokość kwadratu
     */
    public FXRectangle(double x, double y, double width, double height) { 
        super(x, y, width, height);
        setFill(Color.RED);
        setOnMouseClicked(new FXRectangleEventHandler());
        setOnMouseDragged(new FXRectangleEventHandler());
        setOnScroll(new FXRectangleScrollHandler());
        setOnMouseExited(new FXRectangleMouseExited());
        setOnMouseEntered(new FXRectangleMouseEntered());
    }

    /**
     * Sprawdza czy dany punkt koliduje z kwadratem
     * @param x współrzędna x danego punktu
     * @param y współrzędna y danego punktu
     * @return true gdy koliduje, false gdy nie
     */
    public boolean isHit(double x, double y) { 
        return getBoundsInLocal().contains(x,y);   
    }

    /**
     * Przesuwa kwadrat na osi X o wartość
     * @param x wartość
     */
    public void addX(double x) {  
        setX(getX()+x);
    }

    /**
     * Przesuwa kwadrat na osi Y o wartość
     * @param y wartość
     */
    public void addY(double y) {  
        setY(getY()+y);
    }

    /**
     * Zmienia szerokość prostokąta o wartość
     * @param w wartość
     */
    public void addWidth(double w) {
        setWidth(getWidth()+w);
    }

    /**
     * Zmienia wysokość prostokąta o wartość
     * @param h wartość
     */
    public void addHeight(double h)
    {
        setHeight(getHeight()+h);
    }


    /**
     * Odpowiada za poruszanie kwadratu podczas przeciągania
     */
  class FXRectangleEventHandler implements EventHandler<MouseEvent>{
    
    FXRectangle rectangle;
    private double x;
    private double y;

        /**
         * Przesuwa kwadrat na pozycje z wydarzenia
         * @param event wydarzenie
         */
    private void doMove(MouseEvent event) {

        double dx = event.getSceneX() - x;
        double dy = event.getSceneY() - y;
  
        // Jesli nacisnelismy na elipse
       if (rectangle.isHit(x, y)) {
            rectangle.addX(dx);
            rectangle.addY(dy);
        }
      x += dx;
      y += dy;            
    }

        /**
         * Odpowiada za prawidłowe zarządzanie x,y potrzebnych do poruszania
         * @param event
         */
    @Override
    public void handle(MouseEvent event) {
  
     rectangle = (FXRectangle) event.getSource();

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
             rectangle.setX(x - rectangle.getWidth()/2);
             rectangle.setY(y - rectangle.getHeight()/2);
             setRotate(Main.rotateSlider.getValue());
         }
      }
      if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
        doMove(event);
      }
  
    }
  }

    /**
     * Odpowiada za skalowanie kwadratu
     */
    class FXRectangleScrollHandler implements EventHandler<ScrollEvent>{

    FXRectangle rectangle;

        /**
         * Skaluje kwadrat o wartość z {@link ScrollEvent}
         * @param e wydarzenie
         */
    private void doScale(ScrollEvent e) {
              
    double x = e.getX();
    double y = e.getY();

    if (rectangle.isHit(x, y)) {                 
            rectangle.addWidth(e.getDeltaY()*0.1);
            rectangle.addHeight(e.getDeltaY()*0.1);
            rectangle.setX(x - rectangle.getWidth()/2);
            rectangle.setY(y - rectangle.getHeight()/2);
        }
    }


        /**
         * Odpowiada za skalowanie kwadratu
         * @param event wydarzenie
         */
    @Override
    public void handle(ScrollEvent event) {
      
        rectangle = (FXRectangle) event.getSource();

      if (event.getEventType()==ScrollEvent.SCROLL){
        doScale(event);
      }
    }
  }

    /**
     * Odpowiada za ustawianie wyłączanie kolorowej krawędzi gdy mysz opuści kwadrat
     */
    class FXRectangleMouseExited implements EventHandler<MouseEvent>{

        /**
         * Odpowiada za ustawianie wyłączanie kolorowej krawędzi gdy mysz opuści kwadrat
         * @param event wydarzenie
         */
        @Override
        public void handle(MouseEvent event) {

            FXRectangle rectangle = (FXRectangle) event.getSource();

            rectangle.setStrokeWidth(0);

        }
    }

    /**
     * Odpowiada za ustawianie włączanie kolorowej krawędzi gdy mysz wejdzie na kwadrat
     */
    class FXRectangleMouseEntered implements EventHandler<MouseEvent>{

        /**
         * Odpowiada za ustawianie włączanie kolorowej krawędzi gdy mysz wejdzie na kwadrat
         * @param event wydarzenie
         */
        @Override
        public void handle(MouseEvent event) {

            FXRectangle rectangle = (FXRectangle) event.getSource();

            rectangle.setStrokeType(StrokeType.INSIDE);
            rectangle.setStrokeWidth(4);
            Color newColor = (Color) rectangle.getFill();
            rectangle.setStroke(newColor.invert());

        }
    }
}
