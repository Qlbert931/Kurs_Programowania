#include <cmath>

#include "Figure.hpp"
#include "ClientInput.hpp"
#include "Circle.hpp"

Circle::Circle(ClientInput input) : radius(input.NextInput())
{}

double Circle::Area() 
{
    return M_PI * radius * radius;
}

double Circle::Perimeter()
{
    return M_PI * 2. * radius;
}

std::string Circle::NameYourself()
{
    return "Koło";
}
