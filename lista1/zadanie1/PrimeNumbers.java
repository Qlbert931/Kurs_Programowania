public class PrimeNumbers
{
    private int primes[];
    private int amountOfPrimes;
    public enum numberStatus
    {
        PRIME, COMPOSITE;
    }

    public PrimeNumbers(int upperLimit) throws BadUpperLimitException
    {
        if(upperLimit < 2)
        {
            throw new BadUpperLimitException(2);
        }
    
        amountOfPrimes = 0;
        numberStatus[] numbers = new numberStatus[upperLimit + 1];

        numbers[0] = numberStatus.COMPOSITE; 
        numbers[1] = numberStatus.COMPOSITE;
        for(int i = 2; i <= upperLimit; i++) numbers[i] = numberStatus.PRIME;
    
        for(int i = 2; i <= Math.sqrt(upperLimit); i++)//sito erastotenesa
        {
            if(numbers[i] == numberStatus.PRIME)
            {
                int multipeOfI = i * i;
                while(multipeOfI <= upperLimit)
                {
                    numbers[multipeOfI] = numberStatus.COMPOSITE;
                    multipeOfI += i;
                }
            }
        }
    
        for(int i = 2; i <= upperLimit; i++)
        {
            if(numbers[i] == numberStatus.PRIME) amountOfPrimes++;
        }
    
        primes = new int[amountOfPrimes];
        
        int index = 0;
        for(int i = 2; i <= upperLimit; i++)
        {
            if(numbers[i] == numberStatus.PRIME)
            {
                primes[index] = i;
                index++;
            }
        }
    }

    public int number(int index) throws BadIndexException
    {
        if(index < 0 || index >= amountOfPrimes)
        {
            throw new BadIndexException(amountOfPrimes - 1);
        }
        
        return primes[index];
    }
}
