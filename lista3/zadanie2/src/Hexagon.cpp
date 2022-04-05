#include <cmath>

#include "Figure.hpp"
#include "ClientInput.hpp"
#include "Hexagon.hpp"

Hexagon::Hexagon(ClientInput& input) : sideLength(input.nextInput())
{}

double Hexagon::area()
{
    return 3. * pow(sideLength, 2.) * sqrt(3.) / 2.;
}

double Hexagon::perimeter()
{
    return 6. * sideLength;
}
