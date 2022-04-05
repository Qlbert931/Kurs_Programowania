#include "FigureFactory.hpp"
#include "Circle.hpp"
#include "Hexagon.hpp"
#include "Pentagon.hpp"
#include "Square.hpp"
#include "Rectangle.hpp"
#include "Rhombus.hpp"


#include <iostream>

Figure* FigureFactory::createFigure(char type, ClientInput& input)
{
    switch (type)
    {
    case 'o':
        return new Circle(input);
    case 'p':
        return new Pentagon(input);
    case 's':
        return new Hexagon(input);
    case 'c':
    {
        if(Square::checkIfItCouldBeSquare(input))
        {
            return new Square(input);
        }
        else if(Rectangle::checkIfItCouldBeRectangle(input))
        {
            return new Rectangle(input);
        }
        else if(Rhombus::checkIfItCouldBeRhombus(input))
        {
            return new Rhombus(input);
        }
    }
    
    default:
        std::cout << "error" << std::endl;
    }
}