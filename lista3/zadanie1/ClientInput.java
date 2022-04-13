public class ClientInput
{
    public ClientInput(String[] args) throws TooLowArgumentException, WrongAmountOfArgumentsException, WrongFirstArgumentException
    {
        int amountOfSkips = 1;
        inputsIndex = 0;
        amountOfInputs = args.length - amountOfSkips;

        figuresTypes = args[0];
        figuresTypesIndex = 0;

        if(CountExpectedAmountOfInputs() != amountOfInputs)
        throw new WrongAmountOfArgumentsException(CountExpectedAmountOfInputs() - amountOfInputs);

        inputs = new double[amountOfInputs];

        for(int i = amountOfSkips; i < args.length; i++)
        {
            inputs[i - amountOfSkips] = Double.parseDouble(args[i]);
            if(inputs[i - amountOfSkips] <= 0.)
                throw new TooLowArgumentException(0.);
        }
    }

    public double NextInput()
    {
        double input = inputs[inputsIndex];
        inputsIndex++; 
        return input;
    }

    void ChangeIndexBy(int newIndex)
    {
        inputsIndex += newIndex;
    }
    
    public ClientInput(ClientInput input)
    {
        inputsIndex = input.inputsIndex;
        amountOfInputs = input.amountOfInputs;
        inputs = input.inputs;
        figuresTypes = input.figuresTypes;
        figuresTypesIndex = input.figuresTypesIndex;
    }

    public char NextType()
    {
        char type = figuresTypes.charAt(figuresTypesIndex);
        figuresTypesIndex++;
        return type;
    }

    public boolean IsNextType()
    {
        if(figuresTypesIndex >= figuresTypes.length())
            return false;
        return true;
    }

    private double[] inputs;
    private int amountOfInputs;
    private int inputsIndex;
    private String figuresTypes;
    private int figuresTypesIndex;

    private int CountExpectedAmountOfInputs() throws WrongFirstArgumentException
    {
        int expectedAmontOfInputs = 0;
        for(int i = 0; i < figuresTypes.length(); i++)
        {
            if(figuresTypes.charAt(i) == 'o' || figuresTypes.charAt(i) == 'p' || figuresTypes.charAt(i) == 's')
                expectedAmontOfInputs += 1;
            else if(figuresTypes.charAt(i) == 'c')
                expectedAmontOfInputs += 5;
            else 
                throw new WrongFirstArgumentException(figuresTypes.charAt(i));
        }
        return expectedAmontOfInputs;
    }

};
