import java.util.*;

public class Rhombus extends Quadrangle
{
    public static boolean CheckIfItCouldBe(ClientInput input)
    {
        Set<Double> checkSides = readSidesFromInput(input);

        if(checkSides.size() > 1)
            return false;
        double angle = input.NextInput();
        if(angle < 0. || angle > 180.)
            return false;
        return true;
    }
    
    public Rhombus(ClientInput input) 
    {
        super(input);
    }

    public double Area()
    {
        return Math.pow(sides.iterator().next(), 2.) * Math.sin( Math.toRadians(angle) );
    }

    public double Perimeter()
    {
        return 4. * (sides.iterator().next());
    }

    public String NameYourself()
    {
        return "Romb";
    }
};
