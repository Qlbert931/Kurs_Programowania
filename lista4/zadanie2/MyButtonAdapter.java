import java.awt.event.*;

public class MyButtonAdapter
{
    MyFrame f;
    MyButtonAdapter(MyFrame f) 
    {
        this.f = f; 
    }

    public void actionPerformed(ActionEvent e) 
    {
        f.action();
    }
}
