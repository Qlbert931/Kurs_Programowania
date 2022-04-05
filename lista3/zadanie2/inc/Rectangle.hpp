#pragma once
#include "Quadrangle.hpp"

class Rectangle : public Quadrangle
{
public:
    static bool checkIfItCouldBeRectangle(ClientInput input);
    Rectangle(ClientInput& input);
    double area() override;
    double perimeter() override;
};
