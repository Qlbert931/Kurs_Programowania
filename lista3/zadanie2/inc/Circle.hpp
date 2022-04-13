#pragma once
#include "Figure.hpp"
#include "ClientInput.hpp"

class Circle : public Figure
{
public:
    Circle(ClientInput input);
    double Area() override;
    double Perimeter() override;
    std::string NameYourself() override;
private:
    double radius;
};
