#include <iostream>
#include <stdlib.h>
#include <math.h>
#include <exception>

#include "Exceptions.hpp"
#include "PrimeNumbers.hpp"

using namespace std;

PrimeNumbers::PrimeNumbers(int upperLimit)
{
    if(upperLimit < 2)
    {
        throw BadUpperLimitException(2);
    }

    amountOfPrimes = 0;
    numberStatus numbers[upperLimit + 1];
    
    numbers[0] = COMPOSITE;
    numbers[1] = COMPOSITE;
    for(int i = 2; i <= upperLimit; i++) numbers[i] = PRIME;

    for(int i = 2; i <= sqrt(upperLimit); i++)//sito erastotenesa
    {
        if(numbers[i] == PRIME)
        {
            int multipeOfI = i * i;
            while(multipeOfI <= upperLimit)
            {
                numbers[multipeOfI] = COMPOSITE;
                multipeOfI += i;
            }
        }
    }

    for(int i = 2; i <= upperLimit; i++)
    {
        if(numbers[i] == PRIME) amountOfPrimes++;
    }

    primes = new int[amountOfPrimes];
    
    int index = 0;
    for(int i = 2; i <= upperLimit; i++)
    {
        if(numbers[i] == PRIME)
        {
            primes[index] = i;
            index++;
        }
    }
}

PrimeNumbers::~PrimeNumbers()
{
    delete[] primes;
}

int PrimeNumbers::number(int index)
{
    if(index < 0 || index >= amountOfPrimes)
    {
        throw BadIndexException(amountOfPrimes - 1);
    }
    
    return primes[index];
}
