#pragma once
#include "Figure.hpp"
#include "ClientInput.hpp"

class Hexagon : public Figure
{
public:
    Hexagon(ClientInput& input);
    double area() override;
    double perimeter() override;
private:
    double sideLength;
};
