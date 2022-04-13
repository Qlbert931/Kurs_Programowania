#pragma once
#include "Figure.hpp"
#include "ClientInput.hpp"

class Pentagon : public Figure
{
public:
    Pentagon(ClientInput input);
    double Area() override;
    double Perimeter() override;
    std::string NameYourself() override;
private:
    double sideLength;
};
