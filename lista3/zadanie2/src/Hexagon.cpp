#include <cmath>

#include "Figure.hpp"
#include "ClientInput.hpp"
#include "Hexagon.hpp"

Hexagon::Hexagon(ClientInput input) : sideLength(input.NextInput())
{}

double Hexagon::Area()
{
    return 3. * pow(sideLength, 2.) * sqrt(3.) / 2.;
}

double Hexagon::Perimeter()
{
    return 6. * sideLength;
}

std::string Hexagon::NameYourself()
{
    return "Szesciań";
}
