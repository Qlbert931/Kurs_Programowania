#pragma once

#include "Figure.hpp"
#include "ClientInput.hpp"

class FigureFactory
{
public:
    FigureFactory(int argc, char *argv[]);
    Figure* CreateNextFigure();
    bool IsNext();
private:
    ClientInput input;
};
