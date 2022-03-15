#include <iostream>
#include <stdlib.h>
#include <math.h>
#include <exception>

#include "Exceptions.hpp"
#include "PrimeNumbers.hpp"

using namespace std;

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

PrimeNumbers::PrimeNumbers(int upperLimit)
{
    if(upperLimit < 2)
    {
        throw BadUpperLimitException(2);
    }

    numberStatus *numbers = makingTabSieveOfEratosthenes(upperLimit);
    amountOfPrimes = countPrimes(numbers, upperLimit);
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

    delete[] numbers;
}

PrimeNumbers::numberStatus* PrimeNumbers::makingTabSieveOfEratosthenes(int upTo)
{
    numberStatus *numbers = new numberStatus[upTo + 1];
    
    numbers[0] = COMPOSITE;
    numbers[1] = COMPOSITE;
    for(int i = 2; i <= upTo; i++) numbers[i] = PRIME;

    for(int i = 2; i <= sqrt(upTo); i++)
    {
        if(numbers[i] == PRIME)
        {
            int multipeOfI = i * i;
            while(multipeOfI <= upTo)
            {
                numbers[multipeOfI] = COMPOSITE;
                multipeOfI += i;
            }
        }
    }

    return numbers;
}

int PrimeNumbers::countPrimes(PrimeNumbers::numberStatus* numbers, int maxNumber)
{
    int amount = 0;
    for(int i = 2; i <= maxNumber; i++)
    {
        if(numbers[i] == PRIME) amount++;
    }
    return amount;
}
