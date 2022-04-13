#include <cmath>

#include "Square.hpp"

bool Square::CheckIfItCouldBe(ClientInput input)
{
    std::set<double> checkSides = readSidesFromInput(input);

    if(checkSides.size() > 1)
        return false;
    if(input.NextInput() != 90.)
        return false;
    return true;
}

Square::Square(ClientInput input) : Quadrangle(input)
{}

double Square::Area()
{
    return pow(*sides.begin(), 2.);
}
    
double Square::Perimeter()
{
    return (*sides.begin()) * 4.;
}

std::string Square::NameYourself()
{
    return "Kwadrat";
}