#pragma once
#include <exception>

class BadUpperLimitException : std::exception 
{
    int min;
public:
    BadUpperLimitException(int p_min) : min(p_min) {}
    inline int minUpperLimit()
    {
        return min;
    }
};

class BadIndexException : std::exception 
{
    int max;
public:
    BadIndexException(int p_max) : max(p_max) {}
    inline int maxIndex()
    {
        return max;
    }
};
