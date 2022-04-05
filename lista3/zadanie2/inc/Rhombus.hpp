#pragma once
#include "Quadrangle.hpp"

class Rhombus : public Quadrangle
{
public:
    static bool checkIfItCouldBeRhombus(ClientInput input);
    Rhombus(ClientInput& input);
    double area() override;
    double perimeter() override;
};
