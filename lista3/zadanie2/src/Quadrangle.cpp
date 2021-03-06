#include "Quadrangle.hpp"

Quadrangle::Quadrangle(ClientInput input)
{
    sides = readSidesFromInput(input);
    angle = input.NextInput();
}

std::set<double> Quadrangle::readSidesFromInput(ClientInput &input)
{
    std::set<double> result;
    for(int i = 0; i < 4; i++)
    {
        result.insert(input.NextInput());
    }
    return result;
}

int Quadrangle::ChangeInIndex()
{
    return 5;
}
