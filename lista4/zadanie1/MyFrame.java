import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame
{
    private TextArea wynik;
    private TextField dane;

    public MyFrame()
    {
        super("Program");
        setBounds(100,100,640,480);
        addWindowListener(new MyWindowAdapter());
        setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
        setLayout(new BorderLayout());

        wynik = new TextArea();
        dane = new TextField(40);

        dane.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                char ch = e.getKeyChar();
                if(ch=='\n')
                {
                    action();
                }
            }
        });

        add(dane, BorderLayout.NORTH);
        add(wynik, BorderLayout.CENTER);
    }

    public void action()
    {
        String newPascalTriangle = new String();

        int rows;

        try
        {
            rows = Integer.parseInt(dane.getText());
        }
        catch(NumberFormatException e)
        {
            wynik.setText("Argument musi być liczbą");
            return;
        }

        if(rows < 0)
        {
            wynik.setText("Argument nie może być mniejszy niż 0");
            return;
        }

        for(int i = 0; i < rows; i++)
        {
            PascalTriangleRow row;
            try
            {
                row = new PascalTriangleRow(i);
            }
            catch(TooLowIndexException e)
            {
                wynik.setText("KRYTYCZNY BŁĄD PROGRAMU");
                return;
            }

            for(int k = 0; k < i + 1; k++)
            {
                try
                {
                    newPascalTriangle += String.valueOf(row.coefficient(k)) + ' ';
                }
                catch(TooHighIndexException e)
                {
                    wynik.setText("KRYTYCZNY BŁĄD PROGRAMU");
                    return;
                }
                catch(TooLowIndexException e)
                {
                    wynik.setText("KRYTYCZNY BŁĄD PROGRAMU");
                    return;
                }
            }
            newPascalTriangle += '\n';
        }
        
        
        wynik.setText(newPascalTriangle);
    }
}