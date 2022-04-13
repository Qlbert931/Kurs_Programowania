#include <iostream>
#include <vector>

#include "Exceptions.hpp"
#include "ClientInput.hpp"
#include "Figure.hpp"
#include "FigureFactory.hpp"

int main(int argc, char *argv[])
{
    FigureFactory* figureFactory;
    try
    {
        figureFactory = new FigureFactory(argc, argv);
    }
    catch(WrongFirstArgumentException e)
    {
        std::cerr << "Nieprawdiłowe wywołanie funkcji, " << e.OnWhich() << " nie jest akceptowalnym rodzajem figury, akceptowalne to 'o', 'p', 's', 'c'" << std::endl;
        return 1;
    }
    catch(WrongAmountOfArgumentException e)
    {
        if(e.TheDiffrence() > 0)
            std::cerr << "Nieprawdiłowe wywołanie funkcji, o " << e.TheDiffrence() << " za mało argumentów" << std::endl;
        else
            std::cerr << "Nieprawdiłowe wywołanie funkcji, o " << e.TheDiffrence() << " za dużo argumentów" << std::endl;
        return 1;
    }
    catch(std::invalid_argument e)
    {
        std::cerr << "Nieprawdiłowe wywołanie funkcji, argumenty od drugiego powinny być liczbami" << std::endl;
        return 1;
    }
    catch(TooLowArgumentException e)
    {
        std::cerr << "Nieprawdiłowe wywołanie funkcji, argumenty od drugiego powinny być większe od " << e.whatIsExlusionMin() << std::endl;
        return 1;
    }

    while(figureFactory->IsNext())
    {
        try
        {
            Figure* f = figureFactory->CreateNextFigure();
            std::cout << f->NameYourself() << " ma pole " << f->Area() << " oraz obwód " << f->Perimeter() << std::endl;
            delete f;
        }
        catch(UnknowQuadrangle e)
        {
            std::vector<double> unknow = e.getUnknow(); 
            std::cerr << "Nierozpoznany czworokąt,";
            for(double& d : unknow)
                std::cerr << " " << d;
            std::cerr << " nie jest ani kwadratem, ani prostokątem, ani rombem" << std::endl;
        }
    }

    delete figureFactory;
}
