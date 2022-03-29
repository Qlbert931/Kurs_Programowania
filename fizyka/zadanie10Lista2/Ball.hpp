#pragma once
#include <SFML/Graphics.hpp>

class Ball
{
public:
    void simulate(long double Time);
    void draw(sf::RenderWindow& window);
    Ball(int y, sf::Vector2u p_windowSize);
private:
    sf::Vector2u windowSize;
    long double speed;
    sf::CircleShape body;
};