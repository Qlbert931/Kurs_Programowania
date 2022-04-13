import java.util.*;

public abstract class Quadrangle extends Figure
{
    public Quadrangle(ClientInput input)
    {
        sides = readSidesFromInput(input);
        angle = input.NextInput();
    }

    public int ChangeInIndex()
    {
        return 5;
    }

    protected static Set<Double> readSidesFromInput(ClientInput input)
    {
        Set<Double> result = new LinkedHashSet<Double>();
        for(int i = 0; i < 4; i++)
        {
            result.add(input.NextInput());
        }
        return result;
    }

    protected Set<Double> sides;
    protected double angle;
};
