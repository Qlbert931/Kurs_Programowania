#include <iostream>
#include <SFML/Graphics.hpp>
#include <vector>
#include <time.h>
#include <iomanip>

#include "Progressbar.hpp"
#include "VolumeBar.hpp"

class ball
{
public:
    void Update(float deltaTime)
    {
        sf::Vector2f velocity(speed * deltaTime, 0);
        body.move(velocity);
        //std::cout << body.getPosition().x  << " " << velocity.x << std::endl;  
        if(body.getPosition().x + body.getRadius()*2 >= 1000.0f)
        {
            speed = -abs(speed);
        }
        else if(body.getPosition().x <= 0.0f)
        {
            speed = abs(speed);
        }
    }
    void draw(sf::RenderWindow& window)
    {
        window.draw(body);
    }
    ball(float p_speed, sf::Color color, int y) : speed(p_speed)
    {
        body.setFillColor(color);
        body.setRadius(10.0f);
        body.setPosition(sf::Vector2f(0,y));
    }
private:
    float speed;
    sf::CircleShape body;
};

int main()
{
    std::srand(time(NULL));
    sf::RenderWindow window(sf::VideoMode(1000, 800), "Symulacja");

    std::vector<ball> balls;

    for(int i = 0; i <= 700; i++)
    {
        balls.push_back(ball(700.0f + rand()%601, sf::Color(rand()%255, rand()%255, rand()%255), i));
    }

    VolumeBar timeSpeedBar(900.0f, 30.0f, sf::Color(100, 100, 100), sf::Color(200, 200, 200));
    timeSpeedBar.setPosition(50.0f, 750.0f);
    timeSpeedBar.setProgress(0.0f);

    float time = 0.0f;

    sf::Clock clock;
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
        float deltaTime = clock.restart().asSeconds() * timeSpeedBar.getProgress();
        time += deltaTime;

        timeSpeedBar.Draw(&window);
        float bufor;
        timeSpeedBar.Update(&window, &bufor);

        for(ball& b : balls)
        {
            b.Update(deltaTime);
            b.draw(window);
        }
       
        window.display();

        std::cout << "Time: " << std::fixed << std::setw(5) <<  time << " s\r" << std::flush;
        
    }
    
}