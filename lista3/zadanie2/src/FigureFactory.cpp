#include "FigureFactory.hpp"
#include "Circle.hpp"
#include "Hexagon.hpp"
#include "Pentagon.hpp"
#include "Square.hpp"
#include "Rectangle.hpp"
#include "Rhombus.hpp"
#include "Exceptions.hpp"

#include <iostream>
#include <vector>

FigureFactory::FigureFactory(int argc, char *argv[]) : input(argc, argv)
{}

Figure* FigureFactory::CreateNextFigure()
{
    Figure * result = nullptr;

    switch (input.NextType())
    {
    case 'o':
        result = new Circle(input);
        break;
    case 'p':
        result = new Pentagon(input);
        break;
    case 's':
        result = new Hexagon(input);
        break;
    case 'c':
        if(Square::CheckIfItCouldBe(input))
            result = new Square(input);
        else if(Rectangle::CheckIfItCouldBe(input))
            result = new Rectangle(input);
        else if(Rhombus::CheckIfItCouldBe(input))
            result = new Rhombus(input);
        else
        {
            std::vector<double> unknow;
            for(int i = 0; i < 5; i++)
                unknow.push_back(input.NextInput());
            throw UnknowQuadrangle(unknow);
        }
    }

    input.ChangeIndexBy(result->ChangeInIndex());
    return result;
}

bool FigureFactory::IsNext()
{
    return input.IsNextType();
}
