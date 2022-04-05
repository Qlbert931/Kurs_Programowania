#pragma once

#include "Figure.hpp"
#include "ClientInput.hpp"

class FigureFactory
{
public:
    Figure* createFigure(char type, ClientInput& input);
};
