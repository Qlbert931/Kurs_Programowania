#include <cmath>

#include "Figure.hpp"
#include "ClientInput.hpp"
#include "Pentagon.hpp"

Pentagon::Pentagon(ClientInput& input) : sideLength(input.nextInput())
{}

double Pentagon::area()
{
    return 5. / 4. * pow(sideLength, 2.) / tan(36. * M_PI / 180.);
}

double Pentagon::perimeter() 
{
    return 5. * sideLength;
}
