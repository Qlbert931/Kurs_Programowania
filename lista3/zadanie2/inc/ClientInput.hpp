#pragma once

class ClientInput
{
public:
    ClientInput(int argc, char *argv[]);
    ClientInput(ClientInput& input);
    ~ClientInput();
    double nextInput();
private:
    double *inputs;
    int amountOfInputs;
    int index;
};
