import java.util.*;

public class Test
{
    public static void main(String[] args)
    {
        ClientInput input;

        try
        {
            input = new ClientInput(args); //TooLowArgumentException, WrongAmountOfArgumentsException, WrongFirstArgumentException
        }
        catch(TooLowArgumentException e){
           return; 
        }
        catch(WrongAmountOfArgumentsException e){
            return; 

        }
        catch(WrongFirstArgumentException e){
            return; 
        }

        while(input.IsNextType())
        {
            String name = "Nierozpoznany czworokąt";
            double Area = 0;
            double Perimeter = 0;

            switch(input.NextType())
            {
                case 'o':
                {
                    double radius = input.NextInput();
                    name = "Koło";
                    Area = Figure.OneArgumentFigures.CIRCLE.Area(radius);
                    Perimeter = Figure.OneArgumentFigures.CIRCLE.Perimeter(radius);
                    break;
                }

                case 's':
                {
                    double side = input.NextInput();
                    name = "Szescian";
                    Area = Figure.OneArgumentFigures.HEXAGON.Area(side);
                    Perimeter = Figure.OneArgumentFigures.HEXAGON.Area(side);
                    break;
                }

                case 'p':
                {
                    double side = input.NextInput();
                    name = "Pięciokąt";
                    Area = Figure.OneArgumentFigures.PENTAGON.Area(side);
                    Perimeter = Figure.OneArgumentFigures.PENTAGON.Perimeter(side);
                    break;
                }
                case 'c':
                {
                    if(Figure.OneArgumentFigures.SQUARE.CheckIfItCouldBe(new ClientInput(input)))
                    {
                        double side = input.NextInput();
                        for(int i = 0; i < 3; i++)
                            input.NextInput();
                        input.NextInput();
                        name = "Kwadrat";
                        Area = Figure.OneArgumentFigures.SQUARE.Area(side);
                        Perimeter = Figure.OneArgumentFigures.SQUARE.Perimeter(side);
                        break;
                    }
                    else if(Figure.TwoArgumentFigures.RECTANGLE.CheckIfItCouldBe(new ClientInput(input)))
                    {
                        Set<Double> sides = new LinkedHashSet<Double>(); 

                        for(int i = 0; i < 4; i++)
                            sides.add(input.NextInput());
                        input.NextInput();
                        Iterator<Double> iterator = sides.iterator();

                        double side1 = iterator.next();
                        double side2 = iterator.next();
                        name = "Prostokąt";
                        Area = Figure.TwoArgumentFigures.RECTANGLE.Area(side1, side2);
                        Perimeter = Figure.TwoArgumentFigures.RECTANGLE.Perimeter(side1, side2);
                        break;
                    }
                    else if(Figure.TwoArgumentFigures.RHOMBUS.CheckIfItCouldBe(new ClientInput(input)))
                    {
                        double side = input.NextInput();
                        for(int i = 0; i < 3; i++)
                            input.NextInput();
                        double angle = input.NextInput();

                        name = "Romb";
                        Area = Figure.TwoArgumentFigures.RHOMBUS.Area(side, angle);
                        Perimeter = Figure.TwoArgumentFigures.RECTANGLE.Perimeter(side, angle);
                        break;

                    }
                    else
                    {
                        Vector<Double> unknow = new Vector<Double>();
                        for(int i = 0; i < 5; i++)
                            unknow.add(input.NextInput());
                        //throw new UnknowQuadrangle(unknow);
                    }
                    
                }
                
            }

            System.out.println(name + " ma pole " + Area + " oraz obwód " + Perimeter);
        }
    }
}
