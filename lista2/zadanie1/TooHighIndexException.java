public class TooHighIndexException extends Exception
{
    public TooHighIndexException(int p_max)
    {
        max = p_max;
    }

    public int maxIndex()
    {
        return max;
    }

    private int max;
}
