#pragma once
#include "Figure.hpp"
#include "ClientInput.hpp"

class Circle : public Figure
{
public:
    Circle(ClientInput& input);
    double area() override;
    double perimeter() override;
private:
    double radius;
};
