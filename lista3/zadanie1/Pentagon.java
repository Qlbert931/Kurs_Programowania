public class Pentagon extends Figure
{
    public Pentagon(ClientInput input)
    {
        sideLength = input.NextInput();
    }

    public double Area()
    {
        return 5. / 4. * Math.pow(sideLength, 2.) / Math.tan(36. * Math.PI / 180.);
    }

    public double Perimeter()
    {
        return 5. * sideLength;
    }

    public String NameYourself()
    {
        return "Pięciokąt"; 
    }

    private double sideLength;
};
