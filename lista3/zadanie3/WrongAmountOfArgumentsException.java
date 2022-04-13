public class WrongAmountOfArgumentsException extends Exception
{
    public WrongAmountOfArgumentsException(int _diffrence)
    {
        diffrence = _diffrence;
    }

    public int TheDiffrence()
    {
        return diffrence;
    }

private int diffrence;
};
