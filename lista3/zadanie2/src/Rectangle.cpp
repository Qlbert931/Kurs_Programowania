#include "Rectangle.hpp"

bool Rectangle::checkIfItCouldBeRectangle(ClientInput input)
{
    std::set<double> sides = readSidesFromInput(input);

    if(sides.size() > 2)
        return false;
    if(input.nextInput() != 90.)
        return false;
    return true;
}

Rectangle::Rectangle(ClientInput& input) : Quadrangle(input)
{}

double Rectangle::area()
{
    return (*sides.begin()) * (*sides.begin() + 1);
}

double Rectangle::perimeter()
{
    return 2.*(*sides.begin())  + 2.*(*sides.begin() + 1);
}
