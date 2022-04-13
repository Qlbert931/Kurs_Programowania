public class Hexagon extends Figure
{
    public Hexagon(ClientInput input)
    {
        sideLength = input.NextInput();
    }

    public double Area()
    {
        return 3. * Math.pow(sideLength, 2.) * Math.sqrt(3.) / 2.;
    }

    public double Perimeter()
    {
        return 6. * sideLength;
    }

    public String NameYourself()
    {
        return "Szesciań";
    }

    private double sideLength;
};
