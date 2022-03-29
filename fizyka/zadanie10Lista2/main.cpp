#include <iostream>
#include <SFML/Graphics.hpp>
#include <vector>
#include <iomanip>
#include <time.h>

#include "Progressbar.hpp"
#include "MouseChangeableProgressbar.hpp"
#include "Ball.hpp"

int main()
{
    std::srand(time(NULL));
    sf::RenderWindow window(sf::VideoMode(1020, 800), "Symulacja");

    std::vector<Ball> balls;
    for(int y = 0; y <= window.getSize().y - 100; y++)
    {
        balls.push_back( Ball(y, window.getSize()) );
    }
    
    MouseChangeableProgressbar* timeBar = new MouseChangeableProgressbar(window.getSize().x - 20.0f, 60.0f, sf::Color(100, 100, 100), sf::Color(200, 200, 200));
    timeBar -> setPosition(10.0f, window.getSize().y - 70.0f);

    while (window.isOpen())
    {
        sf::Event event;
        while(window.pollEvent(event))
        {
            if(event.type == sf::Event::Closed)
            {
                window.close();
            }
        }

        window.clear();

        timeBar -> draw(window);
        timeBar -> update(window);
        long double time = 5 * timeBar -> getProgress();

        for(Ball& b : balls)
        {
            b.simulate(time);
            b.draw(window);
        }
       
        window.display();
    }
}