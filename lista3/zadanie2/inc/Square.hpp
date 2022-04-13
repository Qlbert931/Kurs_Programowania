#pragma once
#include "Quadrangle.hpp"

class Square : public Quadrangle
{
public:
    static bool CheckIfItCouldBe(ClientInput input);
    Square(ClientInput input);
    double Area() override;
    double Perimeter() override;
    std::string NameYourself() override;
};
