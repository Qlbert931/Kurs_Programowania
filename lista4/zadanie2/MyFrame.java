import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.io.IOException;

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
        Process p;
        try
        {
            p = Runtime.getRuntime().exec("./main " + dane.getText());
        }
        catch(IOException e)
        {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        String result = new String();
        
        try
        {
            while((line = reader.readLine()) != null)
            {
                result += line + '\n';
            }
        }
        catch(IOException e)
        {
            return;
        }

        try
        {
            reader.close();
        }
        catch(IOException e)
        {
            return;
        }

        wynik.setText(result);
        p.destroy();
    }
}