#include <cmath>

#include "Rhombus.hpp"

bool Rhombus::checkIfItCouldBeRhombus(ClientInput input)
{
    std::set<double> sides = readSidesFromInput(input);

    if(sides.size() > 1)
        return false;
    double angle = input.nextInput();
    if(angle < 0. || angle > 180.)
        return false;
    return true;
}

Rhombus::Rhombus(ClientInput& input) : Quadrangle(input)
{}

double Rhombus::area()
{
    return pow(*sides.begin(), 2.) * sin(angle * M_1_PI / 180.);
}

double Rhombus::perimeter()
{
    return 4. * (*sides.begin());
}
