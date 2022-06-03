import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

/**
 * Główna klasa uruchamiająca okno
 */

public class MyApplication extends Application {

    static Random rand = new Random();
    static  GridPane root = new GridPane();
    @Override
    /**
     * Odpowiada za stworzenie kamórek i uruchomienie na nich wątków
     */
    public void start(Stage stage) throws IOException {

        int height = Integer.parseInt(getParameters().getNamed().get("n"));// |
        int width = Integer.parseInt(getParameters().getNamed().get("m"));// -
        int delay = Integer.parseInt(getParameters().getNamed().get("k"));
        double probability = Double.parseDouble(getParameters().getNamed().get("p"));

        int minHeight = 360;
        int minWidth = 640;

        int actualHeight;
        int actualWidth;

        actualHeight = Math.max(height, minHeight);
        actualWidth = Math.max(width, minWidth);

        for (int i = 0; i < width; i++)
        {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setFillWidth(true);
            colConst.setPercentWidth(100.0 / width);
            root.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < height; i++)
        {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setFillHeight(true);
            rowConst.setPercentHeight(100.0 / height);
            root.getRowConstraints().add(rowConst);
        }

        Scene scene = new Scene(root, actualWidth, actualHeight);

        MyCell[][] cells = new MyCell[height][width];

        for(int i = 0; i < height; i++)
        {
            for(int k = 0; k < width; k++)
            {
                cells[i][k] = new MyCell(delay, probability);
                cells[i][k].widthProperty().bind(stage.widthProperty().divide(width));
                cells[i][k].heightProperty().bind(stage.heightProperty().divide(height));
                root.add(cells[i][k], k, i);
            }
        }

        for(int i = 0; i < height; i++)
        {
            for(int k = 0; k < width; k++)
            {
                MyCell neighbourCellLeft = null;
                MyCell neighbourCellRight = null;
                MyCell neighbourCellUp = null;
                MyCell neighbourCellDown = null;

                if(i == 0 )
                {
                    neighbourCellUp = cells[height - 1][k];
                    neighbourCellDown = cells[i + 1][k];
                }
                else if(i == height - 1)
                {
                    neighbourCellUp = cells[i-1][k];
                    neighbourCellDown = cells[0][k];
                }
                else
                {
                    neighbourCellUp = cells[i-1][k];
                    neighbourCellDown = cells[i + 1][k];
                }

                if(k == 0 )
                {
                    neighbourCellLeft = cells[i][width - 1];
                    neighbourCellRight = cells[i][k + 1];
                }
                else if(k == width - 1)
                {
                    neighbourCellLeft = cells[i][k - 1];
                    neighbourCellRight = cells[i][0];
                }
                else
                {
                    neighbourCellLeft = cells[i][k - 1];
                    neighbourCellRight = cells[i][k + 1];
                }

                //System.out.println(neighbourCellLeft + " " + neighbourCellRight + " " + neighbourCellDown + " " + neighbourCellUp);

                cells[i][k].setNeighbours(neighbourCellLeft, neighbourCellRight, neighbourCellUp, neighbourCellDown);

                Thread thread = new Thread(cells[i][k]);
                thread.setDaemon(true);
                thread.start();
            }
        }

        stage.setTitle("Lista6");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Główna metoda
     * @param args argumenty
     */
    public static void main(String[] args) {
        launch(args);
    }
}