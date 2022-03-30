#include <iostream>
#include <stdlib.h>
#include <math.h>
#include <exception>

#include "Exceptions.hpp"
#include "PascalTriangleRow.hpp"

int main(int argc, char *argv[])
{
    if(argc < 2)
    {
        std::cerr << "Za mało argumentów" << std::endl;
        return 1;
    }
    
    PascalTriangleRow *pascalRow;
    try
    {
        int row = std::stoi(argv[1]);
        pascalRow = new PascalTriangleRow(row);
    }
    catch(std::invalid_argument e)
    {
        std::cerr << argv[1] << "\tPierwszy argument musi być liczbą" << std::endl;
        return 1;
    }
    catch(TooLowIndexException e)
    {
        std::cerr << argv[1] << "\tPierwszy argument nie może być mniejszy niż " << e.minIndex() << std::endl;
        return 1;
    }

    for(int i = 2; i < argc; i++)
    {
        try
        {
            int index = std::stoi(argv[i]);
            int coefficient = pascalRow -> coefficient(index);
            std::cout << index << "\t" << coefficient << std::endl;
        }
        catch(std::invalid_argument e)
        {
            std::cerr << argv[i] << "\tIndex powinien być liczbą" << std::endl;
        }
        catch(TooHighIndexException e)
        {
            std::cerr << argv[i] << "\tIndex powinien być mniejszy lub równy " << e.maxIndex() << std::endl;
        }
        catch(TooLowIndexException e)
        {
            std::cerr << argv[i] << "\tIndex powinien być większy lub równy " << e.minIndex() << std::endl;
        }
    }

    delete pascalRow;
}
