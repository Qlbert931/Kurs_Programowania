#include <SFML/Graphics.hpp>
#include <time.h>
#include <math.h>

#include "Ball.hpp"

void Ball::simulate(long double Time)
{
    long double Xposition = (Time * speed);
    Xposition = fmod(Xposition, windowSize.x * 2.0);
    if(Xposition > windowSize.x)
    {
        Xposition = windowSize.x * 2.0 - Xposition;
    }

    sf::Vector2f newPosition(Xposition, body.getPosition().y);
    body.setPosition(newPosition);
}

void Ball::draw(sf::RenderWindow& window)
{
    window.draw(body);
}

Ball::Ball(int y, sf::Vector2u p_windowSize) : windowSize(p_windowSize)
{
    long double multiplayer = 1 - ((rand()%20000000) / 10000000.0f);
    long double deltaSpeed = windowSize.x * 0.3f * multiplayer;
    speed = windowSize.x + deltaSpeed;

    body.setFillColor(sf::Color(rand()%256, rand()%256, rand()%256));
    body.setRadius(10.0f);
    body.setOrigin(body.getRadius(), body.getRadius());
    body.setPosition(sf::Vector2f(0,y));
}
