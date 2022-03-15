#include <iostream>
#include <stdlib.h>
#include <math.h>
#include <exception>

#include "Exceptions.hpp"
#include "PrimeNumbers.hpp"

using namespace std;

int main(int argc, char *argv[])
{
    if(argc < 2)
    {
        cerr << "Za mało argumentów" << endl;
        return 1;
    }
    
    PrimeNumbers* primes = nullptr; 
    try
    {
        int upperLimit = stoi(argv[1]);
        primes = new PrimeNumbers(upperLimit);
    }
    catch(BadUpperLimitException e)
    {
        cerr << argv[1] << "\tUpper limit nie może być mniejszy niż " << e.minUpperLimit() << endl;
        return 1;
    }
    catch(invalid_argument e)
    {
        cerr << argv[1] << "\tUpper limit powinien być liczbą" << endl;
        return 0;
    }

    for(int i = 2; i < argc; i++)
    {
        try
        {
            int index = stoi(argv[i]);
            int number = primes -> number(index);
            cout << index << "\t" << number << endl;
        }
        catch(BadIndexException e)
        {
            cerr << argv[i] << "\tIndex powinien być pomiędzy 0 a " << e.maxIndex() << endl;
        }
        catch(invalid_argument e)
        {
            cerr << argv[i] << "\tIndex powinien być liczbą" << endl;
        }
    }

    delete primes;
}
