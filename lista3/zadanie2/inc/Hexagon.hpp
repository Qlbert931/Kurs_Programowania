#pragma once
#include "Figure.hpp"
#include "ClientInput.hpp"

class Hexagon : public Figure
{
public:
    Hexagon(ClientInput input);
    double Area() override;
    double Perimeter() override;
    std::string NameYourself() override;
private:
    double sideLength;
};
