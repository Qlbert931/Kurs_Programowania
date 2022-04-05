#pragma once
#include "Quadrangle.hpp"


class Square : public Quadrangle
{
public:
    static bool checkIfItCouldBeSquare(ClientInput input);
    Square(ClientInput& input);
    double area() override;
    double perimeter() override;
};
