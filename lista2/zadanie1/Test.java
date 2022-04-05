public class Test
{
    public static void main(String[] args) 
    {
        if(args.length < 1)
        {
            System.out.println("Za mało argumentów");
            return;
        }

        PascalTriangleRow pascalRow;

        try
        {
            int row = Integer.parseInt(args[0]);
            pascalRow = new PascalTriangleRow(row);
        }
        catch(NumberFormatException e)
        {
            System.out.println(args[0] + "\tPierwszy argument musi być liczbą");
            return;
        }
        catch(TooLowIndexException e)
        {
            System.out.println(args[0] + "\tPierwszy argument nie może być mniejszy niż " + e.minIndex());
            return;
        }

        for(int i = 1; i < args.length; i++)
        {
            try
            {
                int index = Integer.parseInt(args[i]);
                int coefficient = pascalRow.coefficient(index);
                System.out.println(index + "\t" + coefficient);
            }
            catch(NumberFormatException e)
            {
                System.out.println(args[i] + "\tIndex powinien być liczbą");
            }
            catch(TooLowIndexException e)
            {
                System.out.println(args[i] + "\tIndex powinien być większy lub równy " + e.minIndex());
            }
            catch(TooHighIndexException e)
            {
                System.out.println(args[i] + "\tIndex powinien być mniejszy lub równy " + e.maxIndex());
            }
        }
    }
}
