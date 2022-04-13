#pragma once
#include "Quadrangle.hpp"

class Rhombus : public Quadrangle
{
public:
    static bool CheckIfItCouldBe(ClientInput input);
    Rhombus(ClientInput input);
    double Area() override;
    double Perimeter() override;
    std::string NameYourself() override;
};
