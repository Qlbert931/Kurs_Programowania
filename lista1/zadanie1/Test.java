public class Test
{
    public static void main(String[] args) 
    {
        if(args.length < 1)
        {
            System.out.println("Za mało argumentów");
            return;
        }

        PrimeNumbers primes;

        try
        {
            int upperLimit = Integer.parseInt(args[0]);
            primes = new PrimeNumbers(upperLimit);
        }
        catch(BadUpperLimitException e)
        {
            System.out.println(args[0] + "\tUpper limit nie może być mniejszy niż " + e.minUpperLimit());
            return;
        }
        catch(NumberFormatException e)
        {
            System.out.println(args[0] + "\tUpper limit powinien być liczbą");
            return;
        }

        for(int i = 1; i < args.length; i++)
        {
            try
            {
                int index = Integer.parseInt(args[i]);
                int number = primes.number(index);
                System.out.println(index + "\t" + number);
            }
            catch(BadIndexException e)
            {
                System.out.println(args[i] + "\tIndex powinien być pomiędzy 0 a " + e.maxIndex());
            }
            catch(NumberFormatException e)
            {
                System.out.println(args[i] + "\tIndex powinien być liczbą");
            }
        }
    }
}