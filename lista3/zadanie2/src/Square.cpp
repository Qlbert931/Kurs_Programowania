#include "Square.hpp"

bool Square::checkIfItCouldBeSquare(ClientInput input)
{
    std::set<double> sides = readSidesFromInput(input);

    if(sides.size() > 1)
        return false;
    if(input.nextInput() != 90.)
        return false;
    return true;
}

Square::Square(ClientInput& input) : Quadrangle(input)
{}

double Square::area()
{
    return (*sides.begin()) * (*sides.begin());
}
    
double Square::perimeter()
{
    return (*sides.begin()) * 4.;
}
