#include "Rectangle.hpp"

bool Rectangle::CheckIfItCouldBe(ClientInput input)
{
    std::set<double> checkSides = readSidesFromInput(input);

    if(checkSides.size() > 2)
        return false;
    if(input.NextInput() != 90.)
        return false;
    return true;
}

Rectangle::Rectangle(ClientInput input) : Quadrangle(input)
{}

double Rectangle::Area()
{
    std::set<double>::iterator iterator = sides.begin();
    return (*iterator) * (*++iterator);
}

double Rectangle::Perimeter()
{
    std::set<double>::iterator iterator = sides.begin();
    return 2.*(*iterator)  + 2.*(*++iterator);
}

std::string Rectangle::NameYourself()
{
    return "Prostokąt";
}
