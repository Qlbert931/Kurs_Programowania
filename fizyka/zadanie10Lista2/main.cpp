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
    sf::RenderWindow window(sf::VideoMode(1020, 800), "Symulacja", sf::Style::Close | sf::Style::Resize);

    std::vector<Ball> balls;

    MouseChangeableProgressbar timeBar(window.getSize().x - 20.0f, 60.0f, sf::Color(100, 100, 100), sf::Color(200, 200, 200));

    while (window.isOpen())
    {
        sf::Event event;
        while(window.pollEvent(event))
        {
            switch (event.type)
            {
                case sf::Event::Closed:
                    window.close();
                break;

                case sf::Event::Resized:
                {
                    sf::FloatRect visibleArea(0, 0, event.size.width, event.size.height);
                    window.setView(sf::View(visibleArea));

                    timeBar.setSize(event.size.width - 20.0f, 60.0f);
                    timeBar.setPosition(10.0f, window.getSize().y - 70.0f);


                    balls.clear();
                    for(int y = 0; y <= window.getSize().y - 100; y++)
                    {
                        balls.push_back( Ball(y, window.getSize()) );
                    }
                }
                break;
            }
        }

        window.clear();

        timeBar.draw(window);
        timeBar.update(window);
        long double time = 5 * timeBar.getProgress();

        for(Ball& b : balls)
        {
            b.simulate(time);
            b.draw(window);
        }
       
        window.display();
    }
}