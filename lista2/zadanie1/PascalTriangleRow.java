public class PascalTriangleRow
{
    public PascalTriangleRow(int row) throws TooLowIndexException
    {
        if(row < 0)
        {
            throw new TooLowIndexException(0);
        }

        coefficients = new int[row + 1];
        coefficients[0] = 1;
    
        for(int i = 1; i < coefficients.length; i++)
        {
            coefficients[i] = coefficients[i - 1]*(coefficients.length - i) / i;
        }
    }

    public int coefficient(int index) throws TooHighIndexException, TooLowIndexException
    {
        if(index < 0)
        {
            throw new TooLowIndexException(0);
        }
        else if(index >= coefficients.length)
        {
            throw new TooHighIndexException(coefficients.length - 1);
        }
        return coefficients[index];
    }

    private int coefficients[];
}
