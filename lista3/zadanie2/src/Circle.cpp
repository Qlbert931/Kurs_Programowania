#include <cmath>

#include "Figure.hpp"
#include "ClientInput.hpp"
#include "Circle.hpp"

Circle::Circle(ClientInput& input) : radius(input.nextInput())
{}

double Circle::area() 
{
    return M_PI * radius * radius;
}

double Circle::perimeter()
{
    return M_PI * 2. * radius;
}
