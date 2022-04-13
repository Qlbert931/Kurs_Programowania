#include <iostream>

#include "Exceptions.hpp"
#include "ClientInput.hpp"

ClientInput::ClientInput(int argc, char *argv[]) : inputsIndex(0), amountOfInputs(argc - 2), figuresTypes(argv[1]), figuresTypesIndex(0)
{
    if(CountExpectedAmountOfInputs() != amountOfInputs)
        throw WrongAmountOfArgumentException(CountExpectedAmountOfInputs() - amountOfInputs);

    const int amountOfSkips = 2;
    inputs = new double[amountOfInputs];

    for(int i = amountOfSkips; i < argc; i++)
    {
        inputs[i - amountOfSkips] = std::stod(argv[i]);
        if(inputs[i - amountOfSkips] <= 0.)
            throw TooLowArgumentException(0.);
    }
}

ClientInput::ClientInput(ClientInput& input)
{
    amountOfInputs = input.amountOfInputs;
    inputsIndex = input.inputsIndex;
    figuresTypes = input.figuresTypes;
    figuresTypesIndex = input.figuresTypesIndex;

    inputs = new double[amountOfInputs];
    for(int i = 0; i < amountOfInputs; i++)
    {
        inputs[i] = input.inputs[i];
    }
}

ClientInput::~ClientInput()
{
    delete[] inputs;
}

double ClientInput::NextInput()
{
    double input = inputs[inputsIndex];
    inputsIndex++;
    return input;
}

int ClientInput::CountExpectedAmountOfInputs()
{
    int expectedAmontOfInputs = 0;
    for(int i = 0; i < figuresTypes.length(); i++)
    {
        if(figuresTypes[i] == 'o' || figuresTypes[i] == 'p' || figuresTypes[i] == 's')
            expectedAmontOfInputs += 1;
        else if(figuresTypes[i] == 'c')
            expectedAmontOfInputs += 5;
        else 
            throw WrongFirstArgumentException(figuresTypes[i]);
    }
    return expectedAmontOfInputs;
}

void ClientInput::ChangeIndexBy(int newIndex)
{
    inputsIndex += newIndex;
}

char ClientInput::NextType()
{
    char type = figuresTypes[figuresTypesIndex];
    figuresTypesIndex++;
    return type;
}

bool ClientInput::IsNextType()
{
    if(figuresTypesIndex >= figuresTypes.length())
        return false;
    return true;
}
