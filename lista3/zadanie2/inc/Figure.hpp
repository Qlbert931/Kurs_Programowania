#pragma once
#include <string>

class Figure
{
public:
    virtual double Area() = 0;
    virtual double Perimeter() = 0;
    virtual std::string NameYourself() = 0;
    virtual int ChangeInIndex()
    {
        return 1;
    }
};
