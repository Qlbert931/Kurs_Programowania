#include <cmath>

#include "Rhombus.hpp"

bool Rhombus::CheckIfItCouldBe(ClientInput input)
{
    std::set<double> checkSides = readSidesFromInput(input);

    if(checkSides.size() > 1)
        return false;
    double angle = input.NextInput();
    if(angle < 0. || angle > 180.)
        return false;
    return true;
}

Rhombus::Rhombus(ClientInput input) : Quadrangle(input)
{}

double Rhombus::Area()
{
    return pow(*sides.begin(), 2.) * sin( angle * M_PI / 180.);
}

double Rhombus::Perimeter()
{
    return 4. * (*sides.begin());
}

std::string Rhombus::NameYourself()
{
    return "Romb";
}
