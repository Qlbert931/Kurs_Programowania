#include <cmath>

#include "Figure.hpp"
#include "ClientInput.hpp"
#include "Pentagon.hpp"

Pentagon::Pentagon(ClientInput input) : sideLength(input.NextInput())
{}

double Pentagon::Area()
{
    return 5. / 4. * pow(sideLength, 2.) / tan(36. * M_PI / 180.);
}

double Pentagon::Perimeter() 
{
    return 5. * sideLength;
}

std::string Pentagon::NameYourself()
{
    return "Pięciokąt"; 
}
