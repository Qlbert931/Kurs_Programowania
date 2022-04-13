import java.util.*;

public class UnknowQuadrangle extends Exception
{
    public UnknowQuadrangle(Vector<Double> _unknow)
    {
        unknow = _unknow;
    }

    public Vector<Double> getUnknow()
    {
        return unknow;
    }

    private Vector<Double> unknow;
};
