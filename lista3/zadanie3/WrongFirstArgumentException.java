public class WrongFirstArgumentException extends Exception
{
    public WrongFirstArgumentException(char _wrongChar)
    {
        wrongChar = _wrongChar;
    }

    public char OnWhich()
    {
        return wrongChar;
    }

    private char wrongChar;
};
