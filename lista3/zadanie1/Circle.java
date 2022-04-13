public class Circle extends Figure
{
    public Circle(ClientInput input)
    {
        radius = input.NextInput();
    }

    public double Area()
    {
        return Math.PI * radius * radius;
    }

    public double Perimeter()
    {
        return Math.PI * 2. * radius;
    }

    public String NameYourself()
    {
        return "Koło";
    }

    private double radius;
}
