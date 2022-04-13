public class TooLowArgumentException extends Exception 
{
    public TooLowArgumentException(double _exlusionMin)
    {
        exlusionMin = _exlusionMin;
    }

    public double whatIsExlusionMin()
    {
        return exlusionMin;
    }

    private double exlusionMin;
};
