#pragma once
#include <exception>
#include <vector>

#include "ClientInput.hpp"

class WrongAmountOfArgumentException : public std::exception
{
public:
    WrongAmountOfArgumentException(int _diffrence) : diffrence(_diffrence) {}
    inline int TheDiffrence()
    {
        return diffrence;
    }

private:
    int diffrence;
};

class WrongFirstArgumentException : public std::exception
{
public:
    WrongFirstArgumentException(char _wrongChar) : wrongChar(_wrongChar) {}
    inline char OnWhich()
    {
        return wrongChar;
    }

private:
    char wrongChar;
};

class TooLowArgumentException : public std::exception 
{
public:
    TooLowArgumentException(double _exlusionMin) : exlusionMin(_exlusionMin) {}
    inline double whatIsExlusionMin()
    {
        return exlusionMin;
    }

private:
    double exlusionMin;
};

class UnknowQuadrangle : public std::exception
{
public:
    UnknowQuadrangle(std::vector<double> _unknow) : unknow(_unknow){}
    inline std::vector<double> getUnknow()
    {
        return unknow;
    }
private:
    std::vector<double> unknow;
};
