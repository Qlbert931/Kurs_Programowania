#pragma once
#include <set>

#include "Figure.hpp"
#include "ClientInput.hpp"

class Quadrangle: public Figure
{
public:
    Quadrangle(ClientInput& input);
protected:
    std::set<double> sides;
    static std::set<double> readSidesFromInput(ClientInput& input);
    double angle;
};
