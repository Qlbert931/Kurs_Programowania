#pragma once

class PrimeNumbers
{
public:
    PrimeNumbers(int upperLimit);
    ~PrimeNumbers();
    int number(int index);

private:
    int *primes;
    int amountOfPrimes;
    enum numberStatus
    {
        PRIME,
        COMPOSITE
    };

    numberStatus* makingTabSieveOfEratosthenes(int upTo);
    int countPrimes(numberStatus* numbers, int size);
};
