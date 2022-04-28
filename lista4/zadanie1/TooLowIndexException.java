public class TooLowIndexException  extends Exception
{
    public TooLowIndexException(int p_min)
    {
        min = p_min;
    }
    
    public int minIndex()
    {
        return min;
    }
    
    private int min;
}
