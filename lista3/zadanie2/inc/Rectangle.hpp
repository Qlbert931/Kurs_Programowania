#pragma once
#include "Quadrangle.hpp"

class Rectangle : public Quadrangle
{
public:
    static bool CheckIfItCouldBe(ClientInput input);
    Rectangle(ClientInput input);
    double Area() override;
    double Perimeter() override;
    std::string NameYourself() override;
};
