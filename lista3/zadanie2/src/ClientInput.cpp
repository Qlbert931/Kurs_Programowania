#include <iostream>

#include "ClientInput.hpp"

ClientInput::ClientInput(int argc, char *argv[]) : index(0), amountOfInputs(argc - 2)
{
    const int amountOfSkips = 2;
    inputs = new double[amountOfInputs];

    for(int i = amountOfSkips; i < argc; i++)
    {
        inputs[i - amountOfSkips] = std::stod(argv[i]);
    }
}

ClientInput::~ClientInput()
{
    delete[] inputs;
}

ClientInput::ClientInput(ClientInput& input)
{
    amountOfInputs = input.amountOfInputs;
    index = input.index;
    inputs = new double[amountOfInputs];
    for(int i = 0; i < amountOfInputs; i++)
    {
        inputs[i] = input.inputs[i];
    }
}

double ClientInput::nextInput()
{
    double input = inputs[index];
    index++;
    return input;
}

