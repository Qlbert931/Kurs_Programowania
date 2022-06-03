import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Kapsułuje prostokąt tworząc komórke z której można zrobić wątek
 */
public class MyCell extends Rectangle implements Runnable
{
    /**
     * stan aktywności komórki
     */
    public boolean active = true;
    private int delay;
    private double probability;

    private MyCell neighbourCellLeft;
    private MyCell neighbourCellRight;
    private MyCell neighbourCellUp;
    private MyCell neighbourCellDown;

    private int neighbors;

    private double r;
    private double g;
    private double b;

    /**
     * Tworzy Komurke
     * @param _delay opóźnienie zmianny koloru
     * @param _probability prawdopodobieństwo zmiany koloru
     */
    public MyCell(int _delay, double _probability)
    {
        super();
        delay = _delay;
        probability = _probability;
        setFill(Color.rgb(MyApplication.rand.nextInt(256), MyApplication.rand.nextInt(256), MyApplication.rand.nextInt(256)));
        setOnMouseClicked(mouseEvent -> {
            synchronized (this)
            {
                active = !active;
                if(active)
                {
                    notify();
                }
            }
        });
    }

    /**
     * Ustawia sąsiadów kamórki
     * @param _neighbourCellLeft lewy sąsiad
     * @param _neighbourCellRight prawy sąsiad
     * @param _neighbourCellUp górny sąsiad
     * @param _neighbourCellDown dolny sąsiad
     */
    public void setNeighbours(MyCell _neighbourCellLeft,MyCell _neighbourCellRight,MyCell _neighbourCellUp,MyCell _neighbourCellDown )
    {
        neighbourCellLeft = _neighbourCellLeft;
        neighbourCellRight = _neighbourCellRight;
        neighbourCellUp = _neighbourCellUp;
        neighbourCellDown = _neighbourCellDown;
    }

    /**
     * Odpala za zmiane koloru komórki
     */
    @Override
    public void run() {
        while(true)
        {
            synchronized (this)
            {
                while(!active) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try
            {
                int actualDelay = (int)((MyApplication.rand.nextDouble(1) + 0.5) * delay);
                Thread.sleep(actualDelay);
            }
            catch (InterruptedException e)
            {}

            Color color;

            if(MyApplication.rand.nextDouble(1) <= probability)
            {
                color = Color.rgb(MyApplication.rand.nextInt(256), MyApplication.rand.nextInt(256), MyApplication.rand.nextInt(256));
            }
            else
            {
                synchronized (this)
                {
                    neighbors = 0;
                    r = 0;
                    g = 0;
                    b = 0;

                    if( neighbourCellLeft.active)
                        addNeighbourColor(neighbourCellLeft);

                    if( neighbourCellRight.active)
                        addNeighbourColor(neighbourCellRight);

                    if( neighbourCellUp.active)
                       addNeighbourColor(neighbourCellUp);

                    if( neighbourCellDown.active)
                        addNeighbourColor(neighbourCellDown);


                    if(neighbors != 0)
                    {
                        r /= neighbors;
                        g /= neighbors;
                        b /= neighbors;
                    }
                    else
                    {
                        Color ourColor = (Color) getFill();
                        r = ourColor.getRed();
                        g = ourColor.getGreen();
                        b = ourColor.getBlue();
                    }

                    color = Color.color( r, g, b);
                }

            }

            synchronized (MyApplication.root)
            {
                Platform.runLater(() ->
                {
                    setFill(color);
                });
            }
        }
    }

    /**
     * dodaje kolor sąsiada do zmiennych prywatnych
     * @param neighbour sąsiad
     */
    void addNeighbourColor(MyCell neighbour)
    {
        neighbors++;
        Color newColor = (Color) neighbour.getFill();
        r += newColor.getRed();
        g += newColor.getGreen();
        b  += newColor.getBlue();
    }

}
