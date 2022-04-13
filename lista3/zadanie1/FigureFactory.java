import java.util.*;

class FigureFactory
{
    public FigureFactory(String[] args) throws TooLowArgumentException, WrongAmountOfArgumentsException, WrongFirstArgumentException
    {
        input = new ClientInput(args);
    }

    public Figure CreateNextFigure() throws UnknowQuadrangle
    {
        Figure result;

        switch (input.NextType())
        {
        case 'o':
            result = new Circle( new ClientInput(input) );
            break;
        case 'p':
            result = new Pentagon( new ClientInput(input) );
            break;
        case 's':
            result = new Hexagon( new ClientInput(input) );
            break;
        case 'c':
            if(Square.CheckIfItCouldBe( new ClientInput(input) ))
                result = new Square( new ClientInput(input) );
            else if(Rectangle.CheckIfItCouldBe( new ClientInput(input) ))
                result = new Rectangle( new ClientInput(input) );
            else if(Rhombus.CheckIfItCouldBe( new ClientInput(input) ))
                result = new Rhombus( new ClientInput(input) );
            else
            {
                Vector<Double> unknow = new Vector<Double>();
                for(int i = 0; i < 5; i++)
                    unknow.add(input.NextInput());
                throw new UnknowQuadrangle(unknow);
            }
            break;
        default:
            return null;
        }
    
        input.ChangeIndexBy(result.ChangeInIndex());
        return result;
    }

    public boolean IsNext()
    {
        return input.IsNextType();
    }

    private ClientInput input;
};
