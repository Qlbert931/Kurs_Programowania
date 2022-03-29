#pragma once

#include <SFML/Graphics.hpp>

class Progressbar
{
public:
    Progressbar(float width, float height, sf::Color backgroundColor, sf::Color fillColor);

    float getProgress();
    void setProgress(float newProgress);
    void changeProgress(float change);
    void setPosition(float x, float y);
    sf::Vector2f getPosition();
    sf::Vector2f getSize();
    void draw(sf::RenderWindow& window);

private:
    sf::RectangleShape bar;
    sf::RectangleShape background;
};