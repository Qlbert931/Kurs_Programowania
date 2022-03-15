#pragma once

class PrimeNumbers
{
    int *primes;
    int amountOfPrimes;
    enum numberStatus
    {
        PRIME,
        COMPOSITE
    };
public:
    PrimeNumbers(int upperLimit);
    ~PrimeNumbers();
    int number(int index);
};
