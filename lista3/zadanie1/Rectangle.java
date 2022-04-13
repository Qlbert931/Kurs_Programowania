import java.util.*;

class Rectangle extends Quadrangle
{
    public static boolean CheckIfItCouldBe(ClientInput input)
    {
        Set<Double> checkSides = readSidesFromInput(input);

        if(checkSides.size() > 2)
            return false;
        if(input.NextInput() != 90.)
            return false;
        return true;
    }

    public Rectangle(ClientInput input)
    {
        super(input);
    }

    public double Area()
    {
        Iterator<Double> iterator = sides.iterator();
        return iterator.next() * iterator.next();
    }

    public double Perimeter()
    {
        Iterator<Double> iterator = sides.iterator();
        return 2 * iterator.next()  + 2 * iterator.next();
    }

    public String NameYourself()
    {
        return "Prostokąt";
    }

};
