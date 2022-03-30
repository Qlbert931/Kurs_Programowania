#include "PascalTriangleRow.hpp"
#include "Exceptions.hpp"

PascalTriangleRow::PascalTriangleRow(int row)
{
    if(row < 0)
    {
        throw TooLowIndexException(0);
    }

    amountOfCoefficients = row + 1;

    coefficients = new int[amountOfCoefficients];
    coefficients[0] = 1;

    for(int i = 1; i < amountOfCoefficients; i++)
    {
        coefficients[i] = coefficients[i - 1]*(amountOfCoefficients - i) / i;
    }
}

int PascalTriangleRow::coefficient(int index)
{
    if(index < 0)
    {
        throw TooLowIndexException(0);
    }
    else if(index >= amountOfCoefficients)
    {
        throw TooHighIndexException(amountOfCoefficients - 1);
    }
    return coefficients[index];
}

PascalTriangleRow::~PascalTriangleRow()
{
    delete[] coefficients;
}