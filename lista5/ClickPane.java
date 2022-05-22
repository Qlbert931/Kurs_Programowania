package com.example.demo2;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Ta klasa kapsułuje Pane i dodaje do niego detekcje cliknięć
 */
public class ClickPane extends Pane
{
    private ArrayList<Point2D> points;

    /**
     * Ustawai zachowanie na kliknięciu myszą
     */
    public ClickPane()
    {
        points = new ArrayList<Point2D>();
        setOnMouseClicked(new ClickDetector());
    }

    /**
     * Ta klasa jest odpowiedzialna za wykrywanie i zapisywaniu kliknięć
     */
    class ClickDetector implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event) {
            points.add(new Point2D(event.getX(), event.getY()));
        }
    }

    /**
     * zwrace ilość zapisanych punktów
     * @return ilość zapisanych punktów
     */
    int getAmountOfPoints()
    {
        return points.size();
    }

    /**
     * zwraca punkt na danym indeksie
     * @param index indeks punktu
     * @return punkt na indeksie
     */
    Point2D getPointAt(int index)
    {
        return points.get(index);
    }

    /**
     * Usuwa wszystkie zapisane punkty
     */
    void clearBuffer()
    {
        points.clear();
    }
}
