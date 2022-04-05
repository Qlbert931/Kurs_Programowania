#include <iostream>

#include "ClientInput.hpp"
#include "Figure.hpp"
#include "FigureFactory.hpp"

int main(int argc, char *argv[])
{
    ClientInput  input(argc, argv);
    FigureFactory figureFactory;
    int i = 0;
    while(argv[1][i] != '\0')
    {
        Figure* f = figureFactory.createFigure(argv[1][i], input);

        std::cout << f->area() << " " << f->perimeter() << std::endl;
        
        i++;
        delete f;
    }
}
