public class BadIndexException extends Exception
{
    private int max;
    public BadIndexException(int p_max)
    {
        max = p_max;
    }
    public int maxIndex()
    {
        return max;
    }
}
