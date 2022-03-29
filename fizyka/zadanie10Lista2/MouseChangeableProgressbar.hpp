#pragma once

#include <SFML/Graphics.hpp>

#include "Progressbar.hpp"

class MouseChangeableProgressbar : public Progressbar
{
public:
    MouseChangeableProgressbar(float p_x, float p_y, sf::Color backgroundColor, sf::Color fillColor);

    void update(sf::RenderWindow& window);
};