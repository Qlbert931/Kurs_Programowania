import java.util.*;

class Square extends Quadrangle
{
    public static boolean CheckIfItCouldBe(ClientInput input)
    {
        Set<Double> checkSides = readSidesFromInput(input);

        if(checkSides.size() > 1)
            return false;
        if(input.NextInput() != 90.)
            return false;
        return true;
    }

    public Square(ClientInput input)
    {
        super(input);
    }

    public double Area()
    {
        return Math.pow(sides.iterator().next(), 2);
    }

    public double Perimeter()
    {
        return sides.iterator().next() * 4.;
    }

    public String NameYourself()
    {
        return "Kwadrat";
    }
};
