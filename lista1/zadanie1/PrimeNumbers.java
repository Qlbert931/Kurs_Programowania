public class PrimeNumbers
{
    private int primes[];
    private int amountOfPrimes;
    
    public enum numberStatus
    {
        PRIME, COMPOSITE;
    }

    public int number(int index) throws BadIndexException
    {
        if(index < 0 || index >= amountOfPrimes)
        {
            throw new BadIndexException(amountOfPrimes - 1);
        }
        
        return primes[index];
    }

    public PrimeNumbers(int upperLimit) throws BadUpperLimitException
    {
        if(upperLimit < 2)
        {
            throw new BadUpperLimitException(2);
        }

        numberStatus[] numbers = makingTabSieveOfEratosthenes(upperLimit);
        amountOfPrimes = countPrimes(numbers);
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

    private numberStatus[] makingTabSieveOfEratosthenes(int upTo)
    {
        numberStatus[] numbers = new numberStatus[upTo + 1];

        numbers[0] = numberStatus.COMPOSITE; 
        numbers[1] = numberStatus.COMPOSITE;
        for(int i = 2; i <= upTo; i++) numbers[i] = numberStatus.PRIME;
    
        for(int i = 2; i <= Math.sqrt(upTo); i++)//sito erastotenesa
        {
            if(numbers[i] == numberStatus.PRIME)
            {
                int multipeOfI = i * i;
                while(multipeOfI <= upTo)
                {
                    numbers[multipeOfI] = numberStatus.COMPOSITE;
                    multipeOfI += i;
                }
            }
        }
        return numbers;
    }

    private int countPrimes(numberStatus[] numbers)
    {
        int amount = 0;
        for(int i = 2; i < numbers.length; i++)
        {
            if(numbers[i] == numberStatus.PRIME) amount++;
        }
        return amount;
    }
}
