public class zadanie3
{
    public static int div(int n)
    {
        int maksymalnyDzielnik = 1;

        for(int i = 1; i < n; i++)
        {
            if(n % i == 0)
            {
                maksymalnyDzielnik = i;
            }
        }
        return maksymalnyDzielnik;
    }

    public static void main(String[] args) 
    {
        for(int i = 0; i < args.length; i++)
        {
            try 
            {
                int n = Integer.parseInt(args[i]);
                System.out.println(n + " " + div(n));
            }
            catch (NumberFormatException ex)
            {
                System.out.println(args[i] + " nie jest liczba calkowita");
            }
        }
    }
}