#pragma once
#include <exception>

class TooLowIndexException : std::exception 
{
public:
    TooLowIndexException(int p_min) : min(p_min) {}
    inline int minIndex()
    {
        return min;
    }

private:
    int min;
};

class TooHighIndexException : std::exception 
{
public:
    TooHighIndexException(int p_max) : max(p_max) {}
    inline int maxIndex()
    {
        return max;
    }

private:
    int max;
};
