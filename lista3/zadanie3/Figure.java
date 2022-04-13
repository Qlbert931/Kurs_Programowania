import java.util.*;

public abstract class Figure
{
    public enum OneArgumentFigures implements OneArgumentFiguresInterface, FigureInterface
    {
        CIRCLE
        {
            public String NameYourself()
            {
                return "Koło";
            }

            public double Area(double radius)
            {
                return Math.PI * radius * radius;
            }

            public double Perimeter(double radius)
            {
                return Math.PI * 2. * radius;
            }

            public boolean CheckIfItCouldBe(ClientInput input)
            {
                return true;
            }

        },

        SQUARE
        {
            public String NameYourself()
            {
                return "Kwadrat";
            }

            public double Area(double side)
            {
                return Math.pow(side, 2);
            }

            public double Perimeter(double side)
            {
                return side * 4;
            }

            public boolean CheckIfItCouldBe(ClientInput input)
            {
                double firstSide = input.NextInput();

                for(int i = 0; i < 3; i++)
                {
                    if(firstSide != input.NextInput())
                        return false;
                }

                if(input.NextInput() != 90.)
                    return false;
                return true;
            }
        },

        PENTAGON
        {
            public String NameYourself()
            {
                return "Pięciokąt";
            }

            public double Area(double side)
            {
                return 5. / 4. * Math.pow(side, 2.) / Math.tan(36. * Math.PI / 180.);
            }
        
            public double Perimeter(double side)
            {
                return 5. * side;
            }

            public boolean CheckIfItCouldBe(ClientInput input)
            {
                return true;
            }
        },

        HEXAGON
        {
            public String NameYourself()
            {
                return "Szesciokąt";
            }

            public double Area(double side)
            {
                return 3. * Math.pow(side, 2.) * Math.sqrt(3.) / 2.;
            }
        
            public double Perimeter(double side)
            {
                return 6. * side;
            }

            public boolean CheckIfItCouldBe(ClientInput input)
            {
                return true;
            }
        }
    }

    enum TwoArgumentFigures implements TwoArgumentFiguresInterface, FigureInterface
    {
        RECTANGLE
        {
            public String NameYourself()
            {
                return "Prostokąt";
            }

            public double Area(double side1, double side2)
            {
                return side1 * side2;
            }

            public double Perimeter(double side1, double side2)
            {
                return 2*side1 + 2*side2; 
            }

            public boolean CheckIfItCouldBe(ClientInput input)
            {
                Set<Double> sides = new LinkedHashSet<Double>(); 

                for(int i = 0; i < 4; i++)
                    sides.add(input.NextInput());
                
                if(sides.size() != 2)
                    return false;

                if(input.NextInput() != 90.)
                    return false;
                return true;
            }
        },

        RHOMBUS
        {
            public String NameYourself()
            {
                return "Romb";
            }

            public double Area(double side, double angle)
            {
                return Math.pow(side, 2) * Math.sin( Math.toRadians(angle) );
            }

            public double Perimeter(double side, double angle)
            {
                return 4 * side;
            }

            public boolean CheckIfItCouldBe(ClientInput input)
            {
                double firstSide = input.NextInput();
                
                for(int i = 0; i < 3; i++)
                {
                    if(firstSide != input.NextInput())
                        return false;
                }

                double angle = input.NextInput();

                if(angle < 0. || angle > 180.)
                    return false;
                return true;
            }
        }
    }
}
