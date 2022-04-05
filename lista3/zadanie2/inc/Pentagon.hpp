#pragma once
#include "Figure.hpp"
#include "ClientInput.hpp"

class Pentagon : public Figure
{
public:
    Pentagon(ClientInput& input);
    double area() override;
    double perimeter() override;
private:
    double sideLength;
};
