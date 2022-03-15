#pragma once
#include <exception>

class BadUpperLimitException : std::exception 
{
public:
    BadUpperLimitException(int p_min) : min(p_min) {}
    inline int minUpperLimit()
    {
        return min;
    }

private:
    int min;
};

class BadIndexException : std::exception 
{
public:
    BadIndexException(int p_max) : max(p_max) {}
    inline int maxIndex()
    {
        return max;
    }

private:
    int max;
};
