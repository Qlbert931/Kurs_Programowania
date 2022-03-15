public class BadUpperLimitException extends Exception
{
    private int min;
    public BadUpperLimitException(int p_min)
    {
        min = p_min;
    }
    public int minUpperLimit()
    {
        return min;   
    }
}