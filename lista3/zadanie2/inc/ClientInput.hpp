#pragma once

class ClientInput
{
public:
    ClientInput(int argc, char *argv[]);
    ClientInput(ClientInput& input);
    ~ClientInput();
    double NextInput();
    char NextType();
    bool IsNextType();
    void ChangeIndexBy(int newIndex);
private:
    double *inputs;
    int amountOfInputs;
    int inputsIndex;
    std::string figuresTypes;
    int figuresTypesIndex;
    
    int CountExpectedAmountOfInputs();
};
