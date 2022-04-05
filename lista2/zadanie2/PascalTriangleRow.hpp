#pragma once
#include "Exceptions.hpp"

class PascalTriangleRow
{
public:
    PascalTriangleRow(int row);
    ~PascalTriangleRow();
    int coefficient(int index);

private:
    int *coefficients;
    int amountOfCoefficients;
};
