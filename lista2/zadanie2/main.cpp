#include <iostream>
#include <stdlib.h>
#include <exception>

#include "Exceptions.hpp"
#include "PascalTriangleRow.hpp"

int main(int argc, char *argv[])
{
    if(argc < 2)
    {
        std::cout << "Za mało argumentów" << std::endl;
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
        std::cout << argv[1] << "\tPierwszy argument musi być liczbą" << std::endl;
        return 1;
    }
    catch(TooLowIndexException e)
    {
        std::cout << argv[1] << "\tPierwszy argument nie może być mniejszy niż " << e.minIndex() << std::endl;
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
            std::cout << argv[i] << "\tIndex powinien być liczbą" << std::endl;
        }
        catch(TooHighIndexException e)
        {
            std::cout << argv[i] << "\tIndex powinien być mniejszy lub równy " << e.maxIndex() << std::endl;
        }
        catch(TooLowIndexException e)
        {
            std::cout << argv[i] << "\tIndex powinien być większy lub równy " << e.minIndex() << std::endl;
        }
    }

    delete pascalRow;
}
